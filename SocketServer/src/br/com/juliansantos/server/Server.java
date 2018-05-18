/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.server;

import br.com.juliansantos.connection.ConnectionManager;
import br.com.juliansantos.ui.SystemTray;
import java.io.IOException;
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
    private int port;
    private ServerSocket serverSocket;
    private Socket connection;
    private ServerConnection serverConnection;
    private boolean started;
    private ArrayList<ConnectionManager> connections;

    // Contrutores.
    public Server(int port) {
        this.port = port;
        this.started = false;
        SystemTray.initSystemTray(this);
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

        System.out.println("---------------------------------------------");
        System.out.println("INICIANDO...");

        try {

            System.out.println("CRIANDO LISTENER PARA SERVIDOR");
            serverSocket = new ServerSocket(port);
            connections = new ArrayList<>();

            System.out.println("INICIANDO THREAD COM LISTENER PARA CONEXOES");
            if (!isStarted()) {
                serverConnection = new ServerConnection(this);
                Thread tServerConnection = new Thread(serverConnection);
                tServerConnection.start();
            }

            setStarted(true);

            System.out.println("INICIADO COM SUCESSO!");

        } catch (IOException ex) {
            System.out.println("LISTENER FINALIZADO!");
        }
    }

    // Para servidor.
    public void stopServer() {

        System.out.println("FINALIZANDO...");

        try {

            while (connections.size() > 0) {
                System.out.println("FECHANDO CONEXAO: " + connections.get(0).getConnection().toString());
                System.out.println(connections.get(0).getConnection().getInetAddress().getHostName() + "FINALIZADO!");
                connections.get(0).getConnection().close();
                connections.get(0).setStopThreads(true);
                connections.remove(0);
            }

            if (!serverSocket.isClosed()) {
                System.out.println("FECHANDO LISTENER");
                serverSocket.close();
            }

            setStarted(false);

            System.out.println("FINALIZADO COM SUCESSO!");
            System.out.println("---------------------------------------------");

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
