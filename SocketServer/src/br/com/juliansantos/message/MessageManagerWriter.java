/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;
import br.com.juliansantos.entity.Message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageManagerWriter implements Runnable {

    private boolean stopped = false;
    private ConnectionManager connectionManager;
    private DataOutputStream dataOutputStream;
    private Message message;

    public MessageManagerWriter() {
    }

    public MessageManagerWriter(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.dataOutputStream = connectionManager.getDataOutputStream();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean isStopped) {
        this.stopped = isStopped;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        while (!stopped) {
            
            while (connectionManager.existMessageOutputList()) {
                
                try {
                    dataOutputStream.writeUTF(message.getMessage());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    Logger.getLogger(MessageManagerWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MessageManagerWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

}
