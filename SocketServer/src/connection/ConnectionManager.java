/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageManagerReader;
import message.MessageManagerWriter;
import server.Server;

/**
 *
 * @author Julian
 */
public class ConnectionManager implements Runnable {
    
    private Server server;
    private Socket connection;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private MessageManagerReader mmr;
    private MessageManagerWriter mmw;

    public ConnectionManager(Server server, Socket connection) {
        
        this.server = server;
        this.connection =  connection;
        
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
    
    @Override
    public void run() {

        mmr = new MessageManagerReader(this);
        Thread tReader = new Thread(mmr);
        tReader.start();

        mmw = new MessageManagerWriter(this);
        Thread tWriter = new Thread(mmw);
        tWriter.start();
        
        while (!connection.isClosed()) {
            try {
                System.out.println("CONECTADO");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        mmr.setStopped(true);
        mmw.setStopped(true);
        System.out.println("DESCONECTOU");
    }

}
