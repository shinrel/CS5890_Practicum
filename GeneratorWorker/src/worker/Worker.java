/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rabbitmq.RabbitMqConnection;
import rabbitmq.RabbitMqConnectionManager;
import utils.SnappyUtils;

/**
 *
 * @author thanhtd
 */
public class Worker {
    public static final int RETRY_NUM = 100;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int tryNum = 0;
        RabbitMqConnectionManager.init();
        JobExecutor executor = new JobExecutor();
        while(true) {
            if (tryNum > RETRY_NUM) {
                break;
            }
            String msg = "";
            RabbitMqConnection connection = RabbitMqConnectionManager.acquireConnection();
            try {
				
                msg = connection.receiveMsgFromQueue();
                if(msg.equals("")) continue;
                String res = executor.run(msg);
//                System.out.println("Before compress: " + res.length());
//                String compressedMsg = SnappyUtils.compress(res);
//                compressedMsg = SnappyUtils.decompress(res);
//                compressedMsg = SnappyUtils.compress(res);
//                System.out.println("After compress:" + compressedMsg.length());
                if(connection.sendMsgFromWorkerToMerging(res)) {
                    connection.sendAckOk();
                }
            } catch (ShutdownSignalException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
//                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (Exception ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ConsumerCancelledException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
//                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (Exception ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            RabbitMqConnectionManager.releaseConnection(connection);
        }
    }
    
}
