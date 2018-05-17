/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.connection.ConnectionManager;

/**
 *
 * @author Julian
 */
public class MessageProcessManager implements Runnable{
    
    private boolean stopped = false;
    private ConnectionManager connectionManager;

    public MessageProcessManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    @Override
    public void run() {
        
        while (!stopped) {
            while (connectionManager.existMessageInputList()) {
                connectionManager.addMessageOutputList("");
            }
        }
        
    }
    
}
