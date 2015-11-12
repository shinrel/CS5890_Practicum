/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import constants.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import rabbitmq.RabbitMqConnection;
import rabbitmq.RabbitMqConnectionManager;
import sorting.algorithm.Heap;
import utils.SnappyUtils;
import utils.Utils;

/**
 *
 * @author thanhtd
 */
public class DistributedMode extends RunningMode {
    private RabbitMqConnection connection;
    private final int RETRY_NUM = 100;
    private StringBuilder result;
    
    public DistributedMode()
    {
        super();
        mode = "Distributed";
        RabbitMqConnectionManager.init();
        
    }
    public String run(int num) {
        this.size = num;
        t1 = System.currentTimeMillis();
        splitJob();
        String res = merge();
        t2 = System.currentTimeMillis();
        return res;
    }
    public void splitJob()
    {
        
        int from = 0;
        int step = size/Constants.NUM_WORKERS;
        int to = 0;
        connection = RabbitMqConnectionManager.acquireConnection();
        for (int i = 0; i < Constants.NUM_WORKERS; i++) {
            
            if (i < (Constants.NUM_WORKERS - 1)) {
                to = from + step;
            } else {
                to = size;
            }
            String WORKER_NAME = Constants.WORKER_PREFIX + (i+1);
            int num = to - from;
            boolean done = connection.sendMsgToWorker(String.valueOf(num), WORKER_NAME);
            from = to;
        }
        RabbitMqConnectionManager.releaseConnection(connection);
    }
    
    public String merge()
    {
        int tryNum = 0;
        result = new StringBuilder();
        
//        String[] res = new String[Constants.NUM_WORKERS];
        int index = 0;
        while(true) {
            if (tryNum > RETRY_NUM) {
                break;
            }
            String msg = "";
         
            RabbitMqConnection connection = RabbitMqConnectionManager.acquireConnection();
            if (index == Constants.NUM_WORKERS) break;
            try {	
                msg = connection.receiveMsgFromQueue();
                connection.sendAckOk();
                if (msg.length() == 0) continue;
//                res[index++] = msg;
//                System.out.println(msg);
                if (index == 0) {   
//                    result.append(SnappyUtils.decompress(msg));
                    result.append(msg);
                } else {
                    result.append("\n"); 
//                    result.append(SnappyUtils.decompress(msg));
                    result.append(msg);
                }
                index ++;
            } catch (ShutdownSignalException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
//                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (ConsumerCancelledException e) {
                try {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                    connection.close();
//                    Thread.currentThread().sleep(constants.Constants.WAIT_TIME*1000);
                    tryNum ++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
            RabbitMqConnectionManager.releaseConnection(connection);
        }
        
        return result.toString();

    }
    
    
    
//    private int[] findMin(int[] nums, boolean[] flags) {
//        int min = 2147483647;
//        int ind = -1;
//        for (int i = 1; i < nums.length; i++) {
//            if (flags[i]) {
//                if (min > nums[i]) {
//                    min = nums[i];
//                    ind = i;
//                }
//            }
//        }
//        int[] res = new int[2];
//        res[0] = ind;
//        res[1] = min;
//        return res;
//    }
    
    @Override
    protected  String getResult()
    {
        return result.toString();
    }
}
