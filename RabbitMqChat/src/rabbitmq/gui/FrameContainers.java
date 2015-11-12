/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq.gui;

import javax.swing.JFrame;

/**
 *
 * @author thanhtd
 */
public class FrameContainers {
    public static ChatFrame chatFrame;
    public static LoginFrame loginFrame;
    public static void initLoginFrame()
    {
        loginFrame = new LoginFrame();
    }
    public static void initChatFrame()
    {
        chatFrame = new ChatFrame();
    }
}
