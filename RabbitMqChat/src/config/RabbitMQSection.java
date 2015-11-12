package config;

public class RabbitMQSection {
	private String notifyQueue; //offline - online notification
	private String msgQueuePrefix;
	private String host;
	private int port;
	private String username;
	private String password;
        private String msgExchangeName;
        private String onoffExchangeName;
	
	public RabbitMQSection() 
	{
		
	}
	
	public RabbitMQSection(String notifyQueue, String msgQueuePrefix, String host, int port,
			String username, String password, String msgExchangeName, String onoffExchangeName) {
		super();
		this.notifyQueue = notifyQueue;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.msgQueuePrefix = msgQueuePrefix;
                this.msgExchangeName = msgExchangeName;
                this.onoffExchangeName = onoffExchangeName;
	}

	public String getMsgExchangeName()
        {
            return this.msgExchangeName;
        }
        public String getOnOffExchangeName()
        {
            return this.onoffExchangeName;
        }

	public String getNotifyQueue() {
		return notifyQueue;
	}

	public void setNotifyQueue(String notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public String getMsgQueuePrefix() {
		return msgQueuePrefix;
	}

	public void setMsgQueuePrefix(String msgQueuePrefix) {
		this.msgQueuePrefix = msgQueuePrefix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString()
	{
		return "username: " + username + " password: " + password + " queueName : " + notifyQueue + " host: " + host + " port : " + port; 
				
	}
	
	
}
