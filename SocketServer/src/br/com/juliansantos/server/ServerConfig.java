/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.server;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author Julian A. Santos
 */
public class ServerConfig {

    private final String pathServerIni = getClass().getClassLoader().getResource("").getFile() + "src/main/resources/settings/SocketServer.ini";
    private final String logPathDefault = getClass().getClassLoader().getResource("").getFile() + "src/main/resources/logs/";

    private int port;
    private int connectionLimit;
    private String logPath;
    private ServerConfigRead serverReadConfig;
    private ServerConfigWriter serverWriterConfig;

    // CONMTRUCTORS
    // -----------------------------------------------------------------------
    public ServerConfig() {
    }

    // GETTERS AND SETTERS
    // -----------------------------------------------------------------------
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getConnectionLimit() {
        return connectionLimit;
    }

    public void setConnectionLimit(int connectionLimit) {
        this.connectionLimit = connectionLimit;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    // USER METHODS
    // -----------------------------------------------------------------------
    public boolean loadIniFile() {

        boolean loadSuccessfully = false;
        File fileIni = new File(pathServerIni);

        if (!fileIni.exists()) {

        }

        try {
            Preferences ini = new IniPreferences(new Ini(fileIni));
            port = ini.node("server").getInt("port", 27000);
            connectionLimit = ini.node("server").getInt("connection_limit", 0);
            logPath = ini.node("server").get("log_path", logPathDefault);
            logPath = logPath.isEmpty() ? logPathDefault : logPath;
            loadSuccessfully = true;
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(
                    null,
                    "Failed to load ServerSocket.ini configuration file!\n" + ex.getMessage(),
                    "loadIniFile:Failed",
                    JOptionPane.ERROR_MESSAGE);
            loadSuccessfully = false;
        }
        return loadSuccessfully;
    }

}
