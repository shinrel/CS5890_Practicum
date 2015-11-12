package config;


import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.google.common.collect.Maps;

public class Configuration {
	
	
	private String RABBIT_MQ_SECTION = "rabbitmq";
	private Ini ini;
	private Map<String, Object> mapSettings;
	private boolean isLoaded = false;
	
	public static final Configuration config = new Configuration();
	
	public static Configuration getInstance()
	{
		
		return config;
	}
	
	public Configuration() {
		init();
	}
	
	private void init() {
		
//		String filename = "/home/thanhtd/wala/zoota_payment/wala.zoota.services/conf/conf.ini";
		String filename = "config/config.ini";
		try {
			ini = new Ini(new File(filename));
			mapSettings = Maps.newHashMap();	
			loadConfiguration(); 
				
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadConfiguration()
	{
		if (ini == null) {
			return;
		} else {
                    mapSettings.clear();

                    //rabbitmq
                    Ini.Section rabbitmqSection = ini.get(RABBIT_MQ_SECTION);
                    RabbitMQSection rabbitmq = null;
                    if (rabbitmqSection != null) {
                            String rabbitUserName = rabbitmqSection.get("rabbit_user_name");
                            String rabbitPassword = rabbitmqSection.get("rabbit_user_pass");
                            String rabbitHost = rabbitmqSection.get("rabbit_host");
                            int rabbitPort = Integer.parseInt(rabbitmqSection.get("rabbit_port"));
                            String msgExchangeName = rabbitmqSection.get("distributed_exchange_name");
                            String mergingQueue = rabbitmqSection.get("merging_queue");
                            String listenQueue = rabbitmqSection.get("listen_queue");
                            int numWorker = Integer.parseInt(rabbitmqSection.get("num_worker"));
                                
                            rabbitmq = new RabbitMQSection(mergingQueue, listenQueue, rabbitHost, rabbitPort, 
                                                            rabbitUserName, rabbitPassword, msgExchangeName, numWorker);
                    }

                    mapSettings.put(RABBIT_MQ_SECTION, rabbitmq);
		}

	}
	
	public RabbitMQSection getRabbitMqConfig()
	{
            if (!mapSettings.containsKey(RABBIT_MQ_SECTION) || ini == null) return null;
            else {
                    return (RabbitMQSection)mapSettings.get(RABBIT_MQ_SECTION);
            }
	}
	
	
	
	public static void  main(String args[]) 
	{
            Configuration conf = Configuration.getInstance();
            System.out.println(conf.getRabbitMqConfig());
	}
	
}
