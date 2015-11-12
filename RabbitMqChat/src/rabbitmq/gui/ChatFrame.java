/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq.gui;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import rabbitmq.RabbitMqConnection;
import rabbitmq.RabbitMqConnectionManager;
import rabbitmq.ThreadContainer;

/**
 *
 * @author thanhtd
 */
public class ChatFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public ChatFrame() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        userNameLabel.setText(constants.Constants.loginName);
        RabbitMqConnectionManager.init();
        String[] initStrings = { "thanhtran_02", "thanhtran_01" };
        DefaultListModel listModel = new DefaultListModel<String>();
        for (String s : initStrings) {
            if (!s.equals(constants.Constants.loginName)) {
                listModel.addElement(s);
            }
        }
        friendList.setModel(listModel);
        
        friendList.setSelectedIndex(0);
                
        
        
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contactPnl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        historyChatPnl = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        historyTxtArea = new javax.swing.JTextArea();
        chatPnl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatTxtArea = new javax.swing.JTextArea();
        sendBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        contactPnl.setBackground(new java.awt.Color(255, 255, 255));
        contactPnl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacts", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        friendList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "thanhtran_02", "thanhtran_01" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(friendList);

        javax.swing.GroupLayout contactPnlLayout = new javax.swing.GroupLayout(contactPnl);
        contactPnl.setLayout(contactPnlLayout);
        contactPnlLayout.setHorizontalGroup(
            contactPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
        );
        contactPnlLayout.setVerticalGroup(
            contactPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        historyChatPnl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        historyTxtArea.setEditable(false);
        historyTxtArea.setColumns(20);
        historyTxtArea.setRows(5);
        jScrollPane3.setViewportView(historyTxtArea);

        javax.swing.GroupLayout historyChatPnlLayout = new javax.swing.GroupLayout(historyChatPnl);
        historyChatPnl.setLayout(historyChatPnlLayout);
        historyChatPnlLayout.setHorizontalGroup(
            historyChatPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        historyChatPnlLayout.setVerticalGroup(
            historyChatPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        chatPnl.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        chatTxtArea.setColumns(20);
        chatTxtArea.setRows(2);
        jScrollPane2.setViewportView(chatTxtArea);

        javax.swing.GroupLayout chatPnlLayout = new javax.swing.GroupLayout(chatPnl);
        chatPnl.setLayout(chatPnlLayout);
        chatPnlLayout.setHorizontalGroup(
            chatPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );
        chatPnlLayout.setVerticalGroup(
            chatPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        sendBtn.setText("Send");
        sendBtn.setName("sendBtn"); // NOI18N
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Hello:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contactPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(historyChatPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chatPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userNameLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(contactPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(historyChatPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chatPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        System.out.println("Log out and close connection");
//        try {
//            ThreadContainer.receiver.join();                
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }//GEN-LAST:event_formWindowClosing

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
       RabbitMqConnection connection = RabbitMqConnectionManager.acquireConnection();
       String friendLoginName = this.friendList.getSelectedValue();
       connection.sendMsgToFriend(constants.Constants.loginName + ":" + this.chatTxtArea.getText(), friendLoginName);
       connection.sendMsgToFriend(constants.Constants.loginName + ":" + this.chatTxtArea.getText(), constants.Constants.loginName);
       RabbitMqConnectionManager.releaseConnection(connection);
       this.chatTxtArea.setText("");
    }//GEN-LAST:event_sendBtnActionPerformed

    public boolean appendMsg(String msg)
    {
        String text = this.historyTxtArea.getText();
        text = text + "\n" + msg;
        this.historyTxtArea.setText(text);
        return true;
    }
    
    private RabbitMqConnection connection;
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatPnl;
    private javax.swing.JTextArea chatTxtArea;
    private javax.swing.JPanel contactPnl;
    private javax.swing.JList<String> friendList;
    private javax.swing.JPanel historyChatPnl;
    private javax.swing.JTextArea historyTxtArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton sendBtn;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}