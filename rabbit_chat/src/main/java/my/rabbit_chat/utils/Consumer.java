package my.rabbit_chat.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Consumer extends AbstractPerformer {
	private static Logger logger = LoggerFactory.getLogger(Consumer.class);

	private volatile Queue<Object> received;
	private Connection connection;
	private Channel channel;
	
	private boolean notInnited;
	
	public Consumer() {
		super();
		notInnited = true;
	}
	
	@Override
	protected void perform(Channel channel, String target, Request _message) {
		received = new LinkedList<>();
		try {
			channel.basicConsume(target, true, new DeliverCallback() {
				@Override
				public void handle(String consumerTag, Delivery message) throws IOException {
					if (Objects.nonNull(received)) {
						received.add(Request.cast(message.getBody()));
					}
				}
			},
			new CancelCallback() {
				@Override
				public void handle(String consumerTag) throws IOException {
				}
			});
			
			
		} catch (IOException e) {
			logger.warn("Consume exception");
		}
	}
	private void initQueue(String target) {
		if (notInnited) {
			declareQueue(channel, target);
		}
		notInnited = false;
	}
	public void receive(String target) {
		received = null;
		try{
			connection = factory.newConnection();
			channel = createChannel(connection);
			declareQueue(channel, target);
			perform(channel, target, null);
		} catch (IOException | TimeoutException e) {
			logger.warn("Connection creation exception");
		}
	}
	
	public boolean hasNext() {
		return Objects.nonNull(received) && !received.isEmpty();
	}
	public Object next() {
		return received.poll();
	}
	
	public void close(){
		try {
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			logger.warn("Connection/channel closing exception");
		}
		
	}

}
