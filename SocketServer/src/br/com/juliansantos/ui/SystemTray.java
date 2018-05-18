/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.ui;

import br.com.juliansantos.server.Server;
import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julia
 */
public class SystemTray {

    public static void initSystemTray(Server server) {

        try {

            PopupMenu popupMenu = new PopupMenu("Menu");

            MenuItem menuItemOpen = new MenuItem("Open");
            menuItemOpen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });

            MenuItem menuItemExit = new MenuItem("Exit");
            menuItemExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Runtime r = Runtime.getRuntime();
                    r.exit(0);
                }
            });

            MenuItem menuItemAbout = new MenuItem("About");
            menuItemAbout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });

            MenuItem menuItemStart = new MenuItem("Start");
            menuItemStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (server.isStarted()) {
                    } else {
                        server.startServer();
                    }
                }
            });

            MenuItem menuItemStop = new MenuItem("Stop");
            menuItemStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (server.isStarted()) {
                        server.stopServer();
                    } else {
                    }
                }
            });

            MenuItem menuItemClient = new MenuItem("Client");
            menuItemClient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventQueue.invokeLater(new Client());
                }
            });

            popupMenu.add(menuItemOpen);
            popupMenu.add(menuItemExit);
            popupMenu.addSeparator();
            popupMenu.add(menuItemStart);
            popupMenu.add(menuItemStop);
            popupMenu.add(menuItemClient);
            popupMenu.addSeparator();
            popupMenu.add(menuItemAbout);
            //Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\resources\\Media\\icon.png");
            Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/Media/icon.png");

            TrayIcon trayIcon = new TrayIcon(icon, "JASWSLauncher Server", popupMenu);
            trayIcon.addMouseListener(new MouseAdapter() {
            });

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            tray.add(trayIcon);

        } catch (AWTException ex) {
            Logger.getLogger(ServerConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
