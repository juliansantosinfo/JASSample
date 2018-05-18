/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.server;

import br.com.juliansantos.connection.ConnectionManager;
import br.com.juliansantos.ui.SystemTray;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian Santos
 */
public class Server extends Thread {

    // Variaveis globais.
    private Socket connection;
    private ServerSocket serverSocket;
    private ServerManagerConnection serverManagerConnection;
    private int port;
    private boolean started;
    private ArrayList<ConnectionManager> connections;
    private File logFile;
    private PrintWriter printWriter;

    // Contrutores.
    public Server(int port) {
        
        this.port = port;
        this.started = false;
        
        try {
            this.logFile = new File("src/main/resources/logs/SocketServer.log");
            this.printWriter = new PrintWriter(new FileWriter(logFile));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SystemTray.initSystemTraySwing(this);
    }

    // GETTERS e SETTERS.
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public ArrayList<ConnectionManager> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<ConnectionManager> connections) {
        this.connections = connections;
    }

    // Inicia servidor.
    public final void startServer() {

        try {

            printWriter.println("---------------------------------------------");
            printWriter.println("INICIANDO...");
            printWriter.println("CRIANDO LISTENER PARA SERVIDOR");

            serverSocket = new ServerSocket(port);
            connections = new ArrayList<>();

            printWriter.println("INICIANDO THREAD COM LISTENER PARA CONEXOES");

            if (!isStarted()) {
                serverManagerConnection = new ServerManagerConnection(this);
                Thread tServerConnection = new Thread(serverManagerConnection);
                tServerConnection.start();
            }

            setStarted(true);

            printWriter.println("INICIADO COM SUCESSO!");

        } catch (IOException ex) {
            printWriter.println("LISTENER FINALIZADO!");
        }

        printWriter.flush();

    }

    // Para servidor.
    public void stopServer() {

        printWriter.println("FINALIZANDO...");

        try {

            while (connections.size() > 0) {
                printWriter.println("FECHANDO CONEXAO: " + connections.get(0).getConnection().toString());
                printWriter.println(connections.get(0).getConnection().getInetAddress().getHostName() + "FINALIZADO!");
                connections.get(0).getConnection().close();
                connections.get(0).setStopThreads(true);
                connections.remove(0);
            }

            if (!serverSocket.isClosed()) {
                printWriter.println("FECHANDO LISTENER");
                serverSocket.close();
            }

            setStarted(false);

            printWriter.println("FINALIZADO COM SUCESSO!");
            printWriter.println("---------------------------------------------");

        } catch (IOException ex) {
            printWriter.println(ex.getMessage());
        }

        printWriter.flush();
        printWriter.close();
    }
}
