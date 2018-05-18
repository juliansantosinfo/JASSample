package br.com.juliansantos.server;

import br.com.juliansantos.connection.ConnectionManager;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julia
 */
public class ServerManagerConnection implements Runnable {

    private Server server;
    private Socket connection;

    public ServerManagerConnection(Server server) {
        this.server = server;
    }

    public void waitConnection() {

        while (!server.getServerSocket().isClosed()) {

            try {

                // Aceita conexao do cliente.
                connection = server.getServerSocket().accept();

                // Cria thread para gerenciar conexao.
                ConnectionManager connectionManager = new ConnectionManager(server, connection);
                connectionManager.start();

                // Add connection a lista.
                server.getConnections().add(connectionManager);

            } catch (IOException ex) {
            }
        }

    }

    @Override
    public void run() {
        waitConnection();
    }

}