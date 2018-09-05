/* 
 * Copyright (C) 2018 Julian A. Santos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
