/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rabbitmq.gui.FrameContainers;

/**
 *
 * @author thanhtd
 */
public class  ReceiverThread extends BaseThread implements Runnable {

    public ReceiverThread()
    {
        super();
        System.out.println("Receiving Thread start");
    }
    @Override
    public void run() {
//        System.out.println("Begin to run...");
        String msg = "";
        int tryNum = 0;
        while (true) {
            
            RabbitMqConnection connection = acquireConnection();
            try {
				
                msg = connection.receiveMsgFromQueue();
//                System.out.println("Receiving: " + msg);
                
                boolean success = FrameContainers.chatFrame.appendMsg(msg);
                if (success) {
                        connection.sendAckOk();
                }
//              System.out.println("Process done");
            } catch (ShutdownSignalException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReceiverThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ConsumerCancelledException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReceiverThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
			
			
            RabbitMqConnectionManager.releaseConnection(connection);
        }
    }
    
}
