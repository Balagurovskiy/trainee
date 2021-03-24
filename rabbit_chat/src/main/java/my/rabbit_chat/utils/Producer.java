package my.rabbit_chat.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer extends AbstractPerformer{
	
	private static Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Override
	protected void perform(Channel channel, String target, Request message) {
		try {
			channel.basicPublish("", target, false, null, message.getBytes());
		} catch (UnsupportedEncodingException e) {
			logger.warn(String.format("Could not make publish to the channel : %s ; with encoding : %s", target, ENCODING));
		} catch (IOException e) {
			logger.warn(String.format("Could not make publish to the channel : %s", target));
		}
	}
	public void send(String target, Request message) {
		try(Connection connection = factory.newConnection()){
			Channel channel = createChannel(connection);
			declareQueue(channel, target);
			perform(channel, target, message);
			channel.close();
		} catch (IOException | TimeoutException e1) {
			logger.warn("Connection exception");
		}
	}
}
