/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;
import br.com.juliansantos.entity.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julia
 */
public class MessageManagerSend implements Runnable {

    private boolean stopped = false;
    private Message message;
    private ConnectionManager connectionManager;

    public MessageManagerSend(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public void run() {

        while (true) {

            if (connectionManager.existMessageOutputList()) {
                
                message = new Message(0, "");
                
                try {
                    if (message.getTyprMessage() == Message.IN) {
                        connectionManager.getDataOutputStream().writeUTF(message.getMessage());
                    } else {
                        connectionManager.getDataOutputStream().writeUTF(message.getMessage());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MessageManagerSend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

}
