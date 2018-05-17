/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;
import br.com.juliansantos.entity.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private String messageInput;
    private Message message;
    private Gson gson;

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

            messageInput = "";

            try {

                while (dataInputStream.available() > 0) {
                    messageInput += (char) dataInputStream.read();
                }

                if (!messageInput.isEmpty()) {
                    messageInput = messageInput.substring(2);
                    System.out.println("LIDO: " + messageInput);
                    gson = new GsonBuilder().create();
                    message = gson.fromJson(messageInput, Message.class);
                    connectionManager.addMessageInputList(message);
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
