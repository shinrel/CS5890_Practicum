/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import rabbitmq.RabbitMqConnectionManager;

/**
 *
 * @author thanhtd
 */
public class RabbitMqChat {

    
    
    public static void main(String[] args) {
        
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameContainers.initLoginFrame();
                    FrameContainers.loginFrame.setVisible(true);
                    FrameContainers.loginFrame.setTitle("Demo Chat");
                    FrameContainers.loginFrame.setIconImage(ImageIO.read(new File("more/chaticon.png")));
//                    chatFrame.setVisible(true);
//                    chatFrame.setTitle("Demo Chat");
//                    chatFrame.setIconImage(ImageIO.read(new File("more/chaticon.png")));
                } catch (IOException ex) {
                    Logger.getLogger(RabbitMqChat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
