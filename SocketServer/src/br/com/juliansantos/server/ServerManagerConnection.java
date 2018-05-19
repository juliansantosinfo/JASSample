package br.com.juliansantos.server;

import br.com.juliansantos.connection.ConnectionManager;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian A. Santos
 */
public class ServerManagerConnection implements Runnable {

    private final Server server;
    private Socket connection;

    public ServerManagerConnection(Server server) {
        this.server = server;
    }

    public void waitConnection() {

        while (!server.getServerSocket().isClosed()) {

            try {

                // Aceita conexao do cliente.
                connection = server.getServerSocket().accept();

                // Registra log da conexao no servidor.
                server.addToLog("ACCEPT NOVA CONEXAO DE " + connection.getInetAddress().getHostName());

                // Cria thread para gerenciar conexao.
                ConnectionManager connectionManager = new ConnectionManager(server, connection);
                connectionManager.start();

                // Add connection a lista.
                server.getConnectionList().add(connectionManager);

            } catch (IOException ex) {
                server.addToLog(ex.getMessage().toUpperCase());
            }
        }

    }

    @Override
    public void run() {
        waitConnection();
    }

}
