/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import connection.ConnectionManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ServerConsole;

/**
 *
 * @author Julian
 */
public class Server {

    private int port;
    private ServerConsole console;
    private ServerSocket serverSocket;

    public Server(ServerConsole serverConsole, int port) {
        this.port = port;
        console = serverConsole;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerConsole getConsole() {
        return console;
    }

    public void setConsole(ServerConsole console) {
        this.console = console;
    }

    public final void initServer() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket connection = serverSocket.accept();
                ConnectionManager connectionManager = new ConnectionManager(this, connection);
                Thread t = new Thread(connectionManager);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {
        try {
            if (serverSocket.isBound()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
