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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ServerConsole;

/**
 *
 * @author Julian
 */
public class Server implements Runnable {

    private int port;
    private ServerConsole console;
    private ServerSocket serverSocket;
    private Socket connection;

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

    public final void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            while (serverSocket.isBound()) {
                connection = serverSocket.accept();
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
    
    public void logStartServer() {
        
        Calendar c = Calendar.getInstance();
        String date = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(c.get(Calendar.MONTH)) + "/" + String.valueOf(c.get(Calendar.YEAR)));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY) + ":" + String.valueOf(c.get(Calendar.MINUTE)) + ":" + String.valueOf(c.get(Calendar.SECOND)));
        
        console.writeInConsole("----------------------------------------------");
        console.writeInConsole("INICIANDO SERVER - ServersSocket 1.0");
        console.writeInConsole("----------------------------------------------");
        console.writeInConsole("Date: " + date);
        console.writeInConsole("Time: " + hour);
        console.writeInConsole("----------------------------------------------");
    }
    
    public void logStoptServer() {
        
        Calendar c = Calendar.getInstance();
        String date = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(c.get(Calendar.MONTH)) + "/" + String.valueOf(c.get(Calendar.YEAR)));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY) + ":" + String.valueOf(c.get(Calendar.MINUTE)) + ":" + String.valueOf(c.get(Calendar.SECOND)));
        
        console.writeInConsole("----------------------------------------------");
        console.writeInConsole("FINALIZANDO SERVER - ServersSocket 1.0");
        console.writeInConsole("----------------------------------------------");
        console.writeInConsole("Date: " + date);
        console.writeInConsole("Time: " + hour);
        console.writeInConsole("----------------------------------------------");
    }
    
    @Override
    public void run() {
        logStartServer();
        startServer();
        logStoptServer();
    }
}
