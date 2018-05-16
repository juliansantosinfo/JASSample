/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import connection.ConnectionManager;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageManagerReader implements Runnable {

    private ConnectionManager connectionManager;
    private DataInputStream dataInputStream;

    public MessageManagerReader() {
    }

    public MessageManagerReader(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.dataInputStream = connectionManager.getDataInputStream();
    }

    @Override
    public void run() {

        String message = "";

        while (true) {
            try {
                while (dataInputStream.available() > 0) {
                    message += (char) dataInputStream.read();
                }
                if (!message.isEmpty()) {
                    message = connectionManager.getConnection().getInetAddress().toString() + ": " + message;
                    connectionManager.getServer().getConsole().writeInConsole(message);
                    message = "";
                }

            } catch (IOException ex) {
                Logger.getLogger(MessageManagerReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
