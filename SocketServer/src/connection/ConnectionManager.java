/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import entity.Message;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageManagerReader;
import message.MessageManagerSend;
import message.MessageManagerWriter;
import server.Server;

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
    private MessageManagerSend mms;
    private List<Message> messageListRead;
    private List<Message> messageListFinish;

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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public MessageManagerReader getMmr() {
        return mmr;
    }

    public void setMmr(MessageManagerReader mmr) {
        this.mmr = mmr;
    }

    public MessageManagerWriter getMmw() {
        return mmw;
    }

    public void setMmw(MessageManagerWriter mmw) {
        this.mmw = mmw;
    }

    public MessageManagerSend getMms() {
        return mms;
    }

    public void setMms(MessageManagerSend mms) {
        this.mms = mms;
    }

    public List<Message> getMessageListRead() {
        return messageListRead;
    }

    public void setMessageListRead(List<Message> messageListRead) {
        this.messageListRead = messageListRead;
    }

    public List<Message> getMessageListFinish() {
        return messageListFinish;
    }

    public void setMessageListFinish(List<Message> messageListFinish) {
        this.messageListFinish = messageListFinish;
    }

    public boolean existMessageRead() {
        return messageListRead.size() > 0;
    }

    public void addMessageListRead(int typeMessage, String message) {
        Message messageObjet = new Message(typeMessage, message);
        messageObjet.read();
        messageListRead.add(messageObjet);
    }

    public Message readNextMessage() {
        Message message =  messageListRead.get(1);
        messageListFinish.add(message);
        messageListRead.remove(1);
        return message;
    }

    @Override
    public void run() {

        mmr = new MessageManagerReader(this);
        mmr.run();

        mmw = new MessageManagerWriter(this);
        mmw.run();

        mms = new MessageManagerSend(this);
        mms.run();

    }

}
