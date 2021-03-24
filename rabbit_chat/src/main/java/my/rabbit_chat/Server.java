package my.rabbit_chat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.rabbit_chat.utils.AbstractMessanger;
import my.rabbit_chat.utils.Request;

public class Server extends AbstractMessanger{
	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private Map<String, List<Request>> clients;
	
	protected Server(long uuid) {
		super(uuid);
	}

	@Override
	public void receive() {
		clients = new HashMap<>();
		while (!stop) {
			while(executor.hasTask()) {
				Request req = (Request)executor.receive();
				if (clients.containsKey(req.getUuid())) {
					clients.get(req.getUuid()).add(req);
				} else {
					List<Request> requests = new LinkedList<Request>();
					requests.add(req);
					clients.put(req.getUuid(), requests);
				}
				System.out.println("[ id:" + req.getUuid() + " | " + req.getMessage()+ " | " + req.getTime() + " ]");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.warn("receive thread sleep InterruptedException");
			}
		}
	}

	private boolean hasActiveRquest() {
		return clients.entrySet().stream().anyMatch(e -> !e.getValue().isEmpty());
	}

	@Override
	public void send() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop) {
					if (hasActiveRquest()) {
						clients.entrySet().forEach(e -> {
							e.getValue().forEach(req -> {
								clients.entrySet().forEach(ie -> {
									if (ie.getKey() != e.getKey()) {
										executor.send(ie.getKey(), req);
									}
								});
							});
							e.getValue().clear();
						});
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						logger.warn("receive thread sleep InterruptedException");
					}
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		long i = 0L;
		System.out.println("Server openned with uuid : " + i);
		Server s = new Server(i);
		s.send();
		s.receive();
	}
}
