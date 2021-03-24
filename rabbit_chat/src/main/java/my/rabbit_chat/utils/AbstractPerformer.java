package my.rabbit_chat.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class AbstractPerformer {

	private static Logger logger = LoggerFactory.getLogger(AbstractPerformer.class);
	
	protected ConnectionFactory factory;
	protected final static String ENCODING = "UTF-8";
	
	protected AbstractPerformer(){
		this.factory = new ConnectionFactory();
	}
	protected Channel createChannel(Connection connection) {
		Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			logger.warn("Could not create channel !!!");
		}
		return channel;
	}
	protected void declareQueue(Channel channel, String target) {
		try {
//			channel.queueDelete(target);
			channel.queueDeclare(target, false, false, false, null);
		} catch (IOException e) {
			logger.warn(String.format("Could not connect to the channel : %s", target));
		}
	}
	
	protected abstract void perform(Channel channel, String target, Request message);
}
