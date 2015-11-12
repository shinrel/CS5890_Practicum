package config;

public class RabbitMQSection {
	private String mergingQueue, listenQueue;
	private String host;
	private int port;
	private String username;
	private String password;
        private String distributedExchangeName;
        private int numWorker;
	
	public RabbitMQSection() 
	{
		
	}
	
	public RabbitMQSection(String mergingQueue, String listenQueue, String host, int port,
			String username, String password, String distributedExchangeName, int numWorker) {
		super();
                this.listenQueue = listenQueue;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.mergingQueue = mergingQueue;
                this.distributedExchangeName = distributedExchangeName;
                this.numWorker = numWorker;
                
	}
        
        public String getListenQueu()
        {
            return this.listenQueue;
        }

        public int getNumWorker()
        {
            return this.numWorker;
        }
        
	public String getDistributedExchangeName()
        {
            return this.distributedExchangeName;
        }
      

	public String getMergingQueue() {
		return mergingQueue;
	}

	public void setMergingQueue(String mergingQueue) {
		this.mergingQueue = mergingQueue;
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
		return "username: " + username + " password: " + password + " queueName : " + mergingQueue + " host: " + host + " port : " + port + " exchange name: " + distributedExchangeName; 
				
	}
	
	
}
