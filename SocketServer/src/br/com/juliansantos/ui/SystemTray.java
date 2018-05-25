/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.ui;

import br.com.juliansantos.server.Server;
import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author Julian Santos
 */
public class SystemTray {

    public static void initSystemTray(Server server) {

        try {

            PopupMenu popupMenu = new PopupMenu("Menu");

            MenuItem menuItemStart = new MenuItem();
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
            MenuItem menuItemConsole = new MenuItem("Console");
            menuItemConsole.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Console console = new Console(server);
                }
            });

            MenuItem menuItemExit = new MenuItem("Exit Application");
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

            popupMenu.add(menuItemStart);
            popupMenu.add(menuItemStop);
            popupMenu.addSeparator();
            popupMenu.add(menuItemClient);
            popupMenu.add(menuItemConsole);
            popupMenu.addSeparator();
            popupMenu.add(menuItemExit);
            popupMenu.addSeparator();
            popupMenu.add(menuItemAbout);

            Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/icon.png");

            TrayIcon trayIcon = new TrayIcon(icon, "JASWSLauncher Server", popupMenu);
            trayIcon.addMouseListener(new MouseAdapter() {
            });

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            tray.add(trayIcon);

        } catch (AWTException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void initSystemTraySwing(Server server) {

        try {

            JPopupMenu popupMenu = new JPopupMenu("Menu");

            JMenuItem menuItemStart = new JMenuItem("Start");
            menuItemStart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/start-icon.png")));
            menuItemStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (server.isStarted()) {
                    } else {
                        server.startServer();
                    }
                }
            });

            JMenuItem menuItemStop = new JMenuItem("Stop");
            menuItemStop.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/stop-icon.png")));
            menuItemStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (server.isStarted()) {
                        server.stopServer();
                    } else {
                    }
                }
            });

            JMenuItem menuItemClient = new JMenuItem("Client");
            menuItemClient.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/client-icon.png")));
            menuItemClient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventQueue.invokeLater(new Client());
                }
            });

            JMenuItem menuItemConsole = new JMenuItem("Console");
            menuItemConsole.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/console-icon.png")));
            menuItemConsole.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(new Console(server)).start();
                }
            });

            JMenuItem menuItemExit = new JMenuItem("Exit Application");
            menuItemExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/exit-icon.png")));
            menuItemExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Runtime r = Runtime.getRuntime();
                    r.exit(0);
                }
            });

            JMenuItem menuItemExitMenu = new JMenuItem("Exit Menu");
            menuItemExitMenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/about-icon.png")));
            menuItemExitMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });

            popupMenu.add(menuItemStart);
            popupMenu.add(menuItemStop);
            popupMenu.addSeparator();
            popupMenu.add(menuItemClient);
            popupMenu.add(menuItemConsole);
            popupMenu.addSeparator();
            popupMenu.add(menuItemExit);
            popupMenu.addSeparator();
            popupMenu.add(menuItemExitMenu);

            menuItemStart.setFont(new Font("Impact", Font.ITALIC, 20));
            menuItemStop.setFont(new Font("Impact", Font.ITALIC, 20));
            menuItemClient.setFont(new Font("Impact", Font.ITALIC, 20));
            menuItemConsole.setFont(new Font("Impact", Font.ITALIC, 20));
            menuItemExit.setFont(new Font("Impact", Font.ITALIC, 20));
            menuItemExitMenu.setFont(new Font("Impact", Font.ITALIC, 20));

            Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/media/images/trayicons/systemtray-icon.png");

            TrayIcon trayIcon = new TrayIcon(icon, "JASWSLauncher Server");
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.setLocation(e.getX(), e.getY());
                        popupMenu.setInvoker(popupMenu);
                        popupMenu.setVisible(true);
                    }
                }
            });

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            tray.add(trayIcon);

        } catch (AWTException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
