/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq;

/**
 *
 * @author thanhtd
 */
public class ThreadContainer {
   public static Thread receiver = new Thread(new ReceiverThread());
}
