package my.rabbit_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.rabbit_chat.utils.AbstractMessanger;
import my.rabbit_chat.utils.AbstractPerformer;
import my.rabbit_chat.utils.Request;

public class Client extends AbstractMessanger{
	private static Logger logger = LoggerFactory.getLogger(Client.class);
	private String buffer;
	private BufferedReader systemIn; 
	
	protected Client(long uuid, String target) {
		super(uuid);
		this.target = target;
		buffer = "";
		systemIn = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
	}

	@Override
	public void receive() {
		while (!stop) {
			while(executor.hasTask()) {
				Request req = (Request)executor.receive();
				System.out.println("[ id:" + req.getUuid() + " | " + req.getMessage()+ " | " + req.getTime() + " ]");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.warn("receive thread sleep InterruptedException");
			}
		}
	}

	@Override
	public void send() {
		executor.send(target, new Request(uuid, " connected "));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(!stop && (buffer = systemIn.readLine()) != null) {
						if(buffer.equals("exit")) {
							stop = true;
							executor.close();
							break ;
						}
						if (buffer.length() > 0) {
							executor.send(target, new Request(uuid, buffer));
						}
					}
				} catch (IOException e) {
					logger.warn("receive thread sleep InterruptedException");
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		long i = 111L;
		System.out.println("Client openned with uuid : " + i);
		Client c = new Client(i, "0");
		c.send();
		c.receive();
	}
}
