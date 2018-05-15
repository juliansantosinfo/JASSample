/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import connection.ConnectionManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class Server {
    
    private ConnectionManager connectionManager;
    
    public Server() {
        initServer();
    }
    
    public final void initServer() {
        
        try {
            
            ServerSocket serverSocket = new ServerSocket(27000);
            
            while (true) {
                
                Socket connection = serverSocket.accept();
                
                connectionManager = new ConnectionManager(this, connection);
                Thread t = new Thread(connectionManager);
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
