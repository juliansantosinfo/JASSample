/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import connection.ConnectionManager;
import entity.Message;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageManagerReader implements Runnable {

    private boolean stopped = false;
    private ConnectionManager connectionManager;
    private DataInputStream dataInputStream;
    private String message;

    public MessageManagerReader() {
    }

    public MessageManagerReader(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.dataInputStream = connectionManager.getDataInputStream();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public void run() {

        while (!stopped) {

            message = "";

            try {
                
                while (dataInputStream.available() > 0) {
                    message += (char) dataInputStream.read();
                }
                
                if (!message.isEmpty()) {
                    connectionManager.addMessageListRead(Message.IN, message);
                }
                
                Thread.sleep(1000);
                
            } catch (IOException ex) {
                Logger.getLogger(MessageManagerReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MessageManagerReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
