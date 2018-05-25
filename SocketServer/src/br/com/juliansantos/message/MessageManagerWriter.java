/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Julian
 */
public class MessageManagerWriter implements Runnable {

    private boolean stopped = false;
    private ConnectionManager connectionManager;
    private DataOutputStream dataOutputStream;
    private String messageOutput;
    private Message message;
    private Gson gson;

    public MessageManagerWriter() {
    }

    public MessageManagerWriter(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.dataOutputStream = connectionManager.getDataOutputStream();
        this.gson = new GsonBuilder().create();
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

        while (!connectionManager.isStopThreads()) {

            while (connectionManager.existMessageOutputList()) {

                message = connectionManager.nextMessageOutputList();

                try {

                    messageOutput = gson.toJson(message);

                    dataOutputStream.writeUTF(messageOutput);
                    dataOutputStream.flush();

                    connectionManager.removeMessageOutputList();

                } catch (IOException ex) {
                    System.out.println("CONEXAO COM SERVIDOR INTERROMPIDA: " + ex.getMessage());
                    connectionManager.setStopThreads(true);
                }
            }

            synchronized (connectionManager.getKeyOutputList()) {
                try {
                    connectionManager.getKeyOutputList().wait();
                } catch (InterruptedException ex) {
                    System.out.println("THREAD MMW INTERROMPIDA: " + ex.getMessage());
                }
            }

        }
    }

}
