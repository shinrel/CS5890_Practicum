package rabbitmq;


import java.util.concurrent.ArrayBlockingQueue;

public class RabbitMqConnectionManager {
    
	private static ArrayBlockingQueue<RabbitMqConnection> connections;
	public static void init()
	{
		connections = new ArrayBlockingQueue<RabbitMqConnection>(constants.Constants.NUM_RABBITMQ_CONNECTION, true);
//		for (int i = 0; i < 10; i++) {
			RabbitMqConnection connection = new RabbitMqConnection();
                     
			connection.init();
			connections.add(connection);
//		}
	}
	
	public static RabbitMqConnection acquireConnection()
	{
		return connections.peek();
	}
	
	public static void releaseConnection(RabbitMqConnection connection)
	{
		return;
	}
}
