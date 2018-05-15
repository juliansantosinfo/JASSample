/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class MessageManagerReader implements Runnable {

    private InputStream is;
    private DataInputStream dataInputStream;

    public MessageManagerReader() {
    }

    public MessageManagerReader(InputStream is) {
        this.is = is;
        this.dataInputStream = new DataInputStream(is);
    }

    @Override
    public void run() {

        String message = "";

        while (true) {
            try {
                while (dataInputStream.available() > 0) {
                    message += (char) dataInputStream.read();
                }
                if (!message.isEmpty()) {
                    System.out.println(message);
                    message = "";
                }
                
            } catch (IOException ex) {
                Logger.getLogger(MessageManagerReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
