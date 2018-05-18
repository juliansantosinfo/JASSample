/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.connection;

import br.com.juliansantos.entity.Message;
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

/**
 *
 * @author Julian
 */
public class ConnectionManager extends Thread {

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

    public boolean existMessageInputList() {
        return messageInputList.size() > 0;
    }

    public void addMessageInputList(Message message) {
        messageInputList.add(message);
    }

    public void addMessageInputList(String message) {
        Message messageObjet = new Message(message);
        messageObjet.read();
        messageInputList.add(messageObjet);
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

    public void addMessageOutputList(String message) {
        Message messageObjet = new Message(message);
        messageObjet.read();
        messageOutputList.add(messageObjet);
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

        mmr = new MessageManagerReader(this);
        Thread tmmr = new Thread(mmr);
        tmmr.start();

        mmw = new MessageManagerWriter(this);
        Thread tmmw = new Thread(mmw);
        tmmw.start();

        mpm = new MessageProcessManager(this);
        Thread tmpm = new Thread(mpm);
        tmpm.start();

    }

}
