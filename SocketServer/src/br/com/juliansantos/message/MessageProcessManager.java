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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageProcessManager implements Runnable {

    private boolean stopped = false;
    private ConnectionManager connectionManager;
    private String messageProcess;
    private Message message;
    private Gson gson;

    public MessageProcessManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {

        while (!connectionManager.isStopThreads()) {

            while (connectionManager.existMessageInputList()) {

                message = connectionManager.nextMessageInputList();

                gson = new GsonBuilder().create();
                messageProcess = gson.toJson(message);

                connectionManager.addMessageOutputList(message);
                connectionManager.removeMessageInputList();

                System.out.println("PROC: " + messageProcess);

                synchronized (connectionManager.getKeyOutputList()) {
                    connectionManager.getKeyOutputList().notifyAll();
                }

            }

            synchronized (connectionManager.getKeyInputList()) {
                try {
                    System.out.println("INICIO ESPERA MPM");
                    connectionManager.getKeyInputList().wait();
                    System.out.println("FIM ESPERA MPM");
                } catch (InterruptedException ex) {
                    Logger.getLogger(MessageProcessManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
