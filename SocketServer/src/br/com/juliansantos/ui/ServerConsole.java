/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.ui;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import br.com.juliansantos.server.Server;

/**
 *
 * @author Julia
 */
public class ServerConsole extends javax.swing.JFrame {

    Server server;

    /**
     * Creates new form ServerConsole
     */
    public ServerConsole() {

        initComponents();
        initOthersComponents();
        initSystemTray();
        setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsole = new javax.swing.JTextArea();
        jBtnStart = new javax.swing.JButton();
        jBtnStop = new javax.swing.JButton();
        jBtnClear = new javax.swing.JButton();
        jBtnClient = new javax.swing.JButton();
        jTextFieldPort = new javax.swing.JTextField();
        jLabelPort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaConsole.setEditable(false);
        jTextAreaConsole.setColumns(20);
        jTextAreaConsole.setRows(5);
        jTextAreaConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextAreaConsole);

        jBtnStart.setText("Start");
        jBtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStartActionPerformed(evt);
            }
        });

        jBtnStop.setText("Stop");
        jBtnStop.setEnabled(false);
        jBtnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStopActionPerformed(evt);
            }
        });

        jBtnClear.setText("Clear");
        jBtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClearActionPerformed(evt);
            }
        });

        jBtnClient.setText("Client");
        jBtnClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClientActionPerformed(evt);
            }
        });

        jTextFieldPort.setText("27000");

        jLabelPort.setText("Port");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnClient, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(jLabelPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnStart)
                    .addComponent(jBtnStop)
                    .addComponent(jBtnClear)
                    .addComponent(jBtnClient)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPort))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStartActionPerformed
        // TODO add your handling code here:
        server = new Server(Integer.parseInt(jTextFieldPort.getText()));
        server.start();

        jBtnStart.setEnabled(false);
        jBtnStop.setEnabled(true);
    }//GEN-LAST:event_jBtnStartActionPerformed

    private void jBtnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStopActionPerformed
        // TODO add your handling code here:
        server.stopServer();
        jBtnStart.setEnabled(true);
        jBtnStop.setEnabled(false);
    }//GEN-LAST:event_jBtnStopActionPerformed

    private void jBtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClearActionPerformed
        // TODO add your handling code here:
        jTextAreaConsole.setText("");
    }//GEN-LAST:event_jBtnClearActionPerformed

    private void jBtnClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClientActionPerformed
        // TODO add your handling code here:
        EventQueue.invokeLater(new Client());
    }//GEN-LAST:event_jBtnClientActionPerformed

    public void initOthersComponents() {

        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                setVisible(false);
            }

        });

    }

    public void initSystemTray() {

        try {

            PopupMenu popupMenu = new PopupMenu("Menu");

            MenuItem menuItemOpen = new MenuItem("Open");
            menuItemOpen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
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
                }
            });

            MenuItem menuItemStop = new MenuItem("Stop");
            menuItemStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
            Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\resources\\Media\\icon.png");

            TrayIcon trayIcon = new TrayIcon(icon, "JASWSLauncher Server", popupMenu);
            trayIcon.addMouseListener(new MouseAdapter() {
            });

            SystemTray tray = SystemTray.getSystemTray();
            tray.add(trayIcon);

        } catch (AWTException ex) {
            Logger.getLogger(ServerConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnClear;
    private javax.swing.JButton jBtnClient;
    private javax.swing.JButton jBtnStart;
    private javax.swing.JButton jBtnStop;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaConsole;
    private javax.swing.JTextField jTextFieldPort;
    // End of variables declaration//GEN-END:variables

    //GETTERS AND SETTERS
    public JTextArea getjTextAreaConsole() {
        return jTextAreaConsole;
    }

    public void setjTextAreaConsole(JTextArea jTextAreaConsole) {
        this.jTextAreaConsole = jTextAreaConsole;
    }

    public JButton getjButtonClear() {
        return jBtnClear;
    }

    public void setjButtonClear(JButton jButtonClear) {
        this.jBtnClear = jButtonClear;
    }

    public JButton getjButtonStart() {
        return jBtnStart;
    }

    public void setjButtonStart(JButton jButtonStart) {
        this.jBtnStart = jButtonStart;
    }

    public JButton getjButtonStop() {
        return jBtnStop;
    }

    public void setjButtonStop(JButton jButtonStop) {
        this.jBtnStop = jButtonStop;
    }

    public void writeInConsole(String text) {
        jTextAreaConsole.append(text);
        jTextAreaConsole.append("\n");
    }

    public void writeInConsole(String text, boolean appendText) {

        if (appendText) {
            jTextAreaConsole.append(text);
        } else {
            jTextAreaConsole.setText(text);
        }

        jTextAreaConsole.append("\n");

    }
}
