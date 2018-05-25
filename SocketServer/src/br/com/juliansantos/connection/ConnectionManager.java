/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.connection;

import br.com.juliansantos.message.Message;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.juliansantos.message.MessageManagerReader;
import br.com.juliansantos.message.MessageManagerWriter;
import br.com.juliansantos.message.MessageProcessManager;
import br.com.juliansantos.server.Server;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Julian
 */
public class ConnectionManager extends Thread {

    private long id;
    private Server server;
    private Socket connection;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private MessageManagerReader mmr;
    private MessageManagerWriter mmw;
    private MessageProcessManager mpm;
    private List<Message> messageInputList = new ArrayList<>();
    private List<Message> messageOutputList = new ArrayList<>();
    private boolean stopThreads = false;

    private Object keyInputList = new Object();
    private Object keyOutputList = new Object();

    public ConnectionManager(Server server, Socket connection) {

        this.server = server;
        this.connection = connection;

        try {
            is = connection.getInputStream();
            os = connection.getOutputStream();
            dataInputStream = new DataInputStream(is);
            dataOutputStream = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Socket getConnection() {
        return connection;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public MessageManagerReader getMmr() {
        return mmr;
    }

    public MessageManagerWriter getMmw() {
        return mmw;
    }

    public MessageProcessManager getMpm() {
        return mpm;
    }

    public List<Message> getMessageInputList() {
        return messageInputList;
    }

    public void setMessageInputList(List<Message> messageInputList) {
        this.messageInputList = messageInputList;
    }

    public List<Message> getMessageOutputList() {
        return messageOutputList;
    }

    public void setMessageOutputList(List<Message> messageOutputList) {
        this.messageOutputList = messageOutputList;
    }

    public Object getKeyInputList() {
        return keyInputList;
    }

    public Object getKeyOutputList() {
        return keyOutputList;
    }

    public boolean isStopThreads() {
        return stopThreads;
    }

    public void setStopThreads(boolean stopThreads) {
        this.stopThreads = stopThreads;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.server);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ConnectionManager other = (ConnectionManager) obj;
        if (!Objects.equals(this.server.getPort(), other.server.getPort())) {
            return false;
        }
        if (!Objects.equals(this.connection, other.connection)) {
            return false;
        }
        return true;
    }

    // Controle de Mensagens.
    public boolean existMessageInputList() {
        return messageInputList.size() > 0;
    }

    public void addMessageInputList(Message message) {
        messageInputList.add(message);
    }

    public Message nextMessageInputList() {
        return messageInputList.get(0);
    }

    public Message getMessageInputList(int index) {
        return messageInputList.get(index);
    }

    public void removeMessageInputList() {
        messageInputList.remove(0);
    }

    public boolean existMessageOutputList() {
        return messageOutputList.size() > 0;
    }

    public void addMessageOutputList(Message message) {
        messageOutputList.add(message);
    }

    public Message nextMessageOutputList() {
        return messageOutputList.get(0);
    }

    public Message getMessageOutputList(int index) {
        return messageOutputList.get(index);
    }

    public void removeMessageOutputList() {
        messageOutputList.remove(0);
    }

    @Override
    public void run() {

        ThreadGroup threadGroup = new ThreadGroup(String.valueOf(connection.getPort()));

        mmr = new MessageManagerReader(this);
        Thread tmmr = new Thread(threadGroup, mmr);
        tmmr.start();

        mmw = new MessageManagerWriter(this);
        Thread tmmw = new Thread(threadGroup, mmw);
        tmmw.start();

        mpm = new MessageProcessManager(this);
        Thread tmpm = new Thread(threadGroup, mpm);
        tmpm.start();

        try {

            while (true) {

                synchronized (this) {
                    // Aguarda 1 seg. para proxima tentativa de leitura do inputstrean.
                    wait(2000);
                }

                // Testa envio via DataOutputStream para verificar conexão.
                getDataOutputStream().writeBoolean(true);

            }

        } catch (IOException | InterruptedException ex) {

            // Ativa variavel de controle de Threads.
            setStopThreads(true);

            // Acorda Threads em wait.
            synchronized (getKeyInputList()) {
                getKeyInputList().notifyAll();
            }

            // Acorda Threads em wait.
            synchronized (getKeyOutputList()) {
                getKeyOutputList().notifyAll();
            }

            // Remove conexao atual do servidor.
            server.getConnectionList().remove(this);

            // Registra Log.
            getServer().addToLog("CONNECTION TO CLIENT INTERRUPTED WITH MESSAGE: " + ex.getMessage().toUpperCase());
        }
    }

}
