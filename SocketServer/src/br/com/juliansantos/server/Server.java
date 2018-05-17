/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.server;

import br.com.juliansantos.connection.ConnectionManager;
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

    private int port;
    private ServerSocket serverSocket;
    private Socket connection;
    private boolean started;
    private ArrayList<ConnectionManager> connections;

    /**
     * Contructor
     *
     * @param port
     */
    public Server(int port) {
        this.port = port;
        this.connections = new ArrayList<>();
        startServer();
    }

    /**
     * Method: getPort
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * Method: setPort
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Method: isStarted
     *
     * @return
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Method: setStarted
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Method: startServer
     */
    public final void startServer() {

        try {

            serverSocket = new ServerSocket(port);

            while (!serverSocket.isClosed()) {

                // Aceita conexao do cliente.
                connection = serverSocket.accept();

                // Cria thread para gerenciar conexao.
                ConnectionManager connectionManager = new ConnectionManager(this, connection);
                connectionManager.start();

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method: stopServer
     */
    public void stopServer() {
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
