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
public class BaseThread {
    public BaseThread()
    {
        
    }
    protected RabbitMqConnection connection;
    protected RabbitMqConnection acquireConnection()
    {
        connection = RabbitMqConnectionManager.acquireConnection();
        return connection;
    }
}
