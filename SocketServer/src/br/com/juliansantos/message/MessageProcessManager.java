/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void run() {

        while (!connectionManager.isStopThreads()) {

            while (connectionManager.existMessageInputList()) {

                message = connectionManager.nextMessageInputList();

                messageProcess = gson.toJson(message);

                connectionManager.addMessageOutputList(message);
                connectionManager.removeMessageInputList();

                synchronized (connectionManager.getKeyOutputList()) {
                    connectionManager.getKeyOutputList().notifyAll();
                }

            }

            synchronized (connectionManager.getKeyInputList()) {
                try {
                    connectionManager.getKeyInputList().wait();
                } catch (InterruptedException ex) {
                    System.out.println("THREAD MMW INTERROMPIDA: " + ex.getMessage());
                }
            }
        }

    }
}
