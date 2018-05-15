/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageManagerWriter implements Runnable {

    private String message;
    private OutputStream os;
    private DataOutputStream dataOutputStream;

    public MessageManagerWriter() {
    }

    public MessageManagerWriter(OutputStream os) {
        this.os = os;
        dataOutputStream = new DataOutputStream(os);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }
    
    @Override
    public void run() {
        
        message = "";
        
        while (true) {
            if (!message.isEmpty()) {
                try {
                    dataOutputStream.writeChars(message);
                    message = "";
                } catch (IOException ex) {
                    Logger.getLogger(MessageManagerWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
