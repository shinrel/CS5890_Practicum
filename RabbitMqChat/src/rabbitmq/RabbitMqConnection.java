package rabbitmq;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;





import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import config.Configuration;
import config.RabbitMQSection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitMqConnection {
	
	protected  ConnectionFactory factory;
	protected  Connection connection;
	protected  Channel innerChannel, outterChannel;
	protected  String username;
	protected  String host;
	protected  QueueingConsumer consumer;
	protected  String password;
	protected  int port; 
	protected  String notifyQueueName, msgQueueName; 
	protected QueueingConsumer.Delivery delivery;
	protected final int MAX_TRY_TIMES = 20;
        protected String loginName;
        protected RabbitMQSection rabbitConfig;
	public boolean init() 
	{
		
		boolean connected = false;
		int tries = 0;
		while (!connected && tries < MAX_TRY_TIMES) {
		
                        Configuration config = Configuration.getInstance();
                        rabbitConfig = config.getRabbitMqConfig();
	
			username = rabbitConfig.getUsername();
			host = rabbitConfig.getHost();
			msgQueueName = rabbitConfig.getMsgQueuePrefix() + constants.Constants.loginName;
                        System.out.println("Message queue name: " + msgQueueName);
                        password = rabbitConfig.getPassword();
			port = rabbitConfig.getPort();
	
			boolean isDone = true;
			try {
				if (factory == null) {
					factory = new ConnectionFactory();
				} 
				factory.setHost(host);
				factory.setPort(port);
				factory.setUsername(username);
				factory.setPassword(password);
				if (connection == null || !connection.isOpen()) {
					connection = factory.newConnection();
				} 
				
                                //serving my messages.
				if (innerChannel == null || !innerChannel.isOpen()) {
					innerChannel = connection.createChannel();
					consumer = new QueueingConsumer(innerChannel);
					innerChannel.basicConsume(msgQueueName, false, consumer);
					System.out.println("Serving at queue: " + msgQueueName + "  done");
				}
                                
                                //sending messages to others
                                if (outterChannel == null || !outterChannel.isOpen()) {
                                    outterChannel = connection.createChannel();
                                }
				
				
	//			addExchangeQueue(pushRedisQueueName, channelPushRedis);
				
				
			} catch (java.io.IOException ex) {
				ex.printStackTrace();
				isDone = false;
				close();
				connected = false;
				tries++;
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				isDone = false;
				close();
				connected = false;
				tries++;
				continue;
			}
			connected = true;
		}
		return connected;
	}
	
        
        public void setLoginName(String loginName)
        {
            this.loginName = loginName;
        }
        
	protected boolean reconnect()
	{
		close();
		return init();
	}
	
	/*protected boolean isInitialized()
	{
		
		if (connection == null || !connection.isOpen()) {
			close();
			return false;
		}
		
		checkChannel(channelPushRedis, consumerPushRedis);
		
		return true;
	}
	
	private void checkChannel(Channel channel, Consumer consumer) 
	{
		if (channel == null || !channel.isOpen()) {
			if (channel != null) {
				close();
			}
			
			try {
				channel = connection.createChannel();
				consumer = new QueueingConsumer(channel);
				channel.basicConsume(pushRedisQueueName, false, consumer);
			} catch (IOException e) {
				close();
				e.printStackTrace();
				logger.error("init channel error");
			}
			
		}
	}*/
	
	private void addExchangeQueue(String queueName, Channel channel)
	{
		boolean connected = false;
		int tries = 0;
		while (!connected && tries < MAX_TRY_TIMES) {
		
			try {
				String exchangeName = queueName;
				channel.exchangeDeclare(exchangeName, "direct", true, false, false, null);
				channel.queueDeclare(queueName, false, false, false, null);
				channel.queueBind(queueName, queueName, queueName);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tries++;
				connected = false;
				reconnect();
				continue;
			}
			connected = true;
		}
	}
	
	public void close()
	{
		try {
			
			if (connection  != null && connection.isOpen() ) {
				connection.close();
			}
			
			if (innerChannel != null && innerChannel.isOpen()) {
				innerChannel.close();
				consumer = null;
			}
                        if (outterChannel != null && outterChannel.isOpen()) {
				outterChannel.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //sleep 5s
	}
	
        public boolean sendMsgToFriend(String message, String friendLoginName)
        {
            try {
                outterChannel.basicPublish(rabbitConfig.getMsgExchangeName(), rabbitConfig.getMsgQueuePrefix() + friendLoginName, null , message.getBytes());
                return true;
            } catch (IOException ex) {
                Logger.getLogger(RabbitMqConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
	
	public String receiveMsgFromQueue() 
	{
//		if (!isInitialized()) init();
		boolean received = false;
		int tries = 0;
		while (!received && tries < MAX_TRY_TIMES) {
			try {
				delivery = consumer.nextDelivery();
			} catch (ShutdownSignalException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				tries++;
				received = false;
				reconnect();
				continue;
			} catch (ConsumerCancelledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tries++;
				received = false;
				reconnect();
				continue;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tries++;
				received = false;
				reconnect();
				continue;
			}
			received = true;
		}
		String message = new String(delivery.getBody());
		return message;
	}
	
	public void sendAckOk() 
	{
//          if (!isInitialized()) reconnect();
            boolean received = false;
            int tries = 0;
            while (!received && tries < MAX_TRY_TIMES) {
                try {
                    innerChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    tries++;
                    received = false;
                    reconnect();
                    continue;
                }
                received = true;
            }
	}
}
