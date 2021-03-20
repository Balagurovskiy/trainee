package my.chat;

import java.util.concurrent.atomic.AtomicLong;

public class ServerListener implements IListener{

	private String request;
	private String responce;
	private Query query;
	private boolean callToExit;
	
	private static AtomicLong count = new AtomicLong(0l);
	private final long ID;
	
	public ServerListener() {
		ID = count.incrementAndGet();
		System.out.println(" [ Server ] : client accepted with id [ " + ID + " ] ");
	}
	
	@Override
	public void setQuery(Query query) {
		this.query = query;
		request = "";
		responce = "";
		callToExit = false;
	}

	@Override
	public boolean hasWork() {
		boolean active = request != null || responce != null;
		boolean exit = request == null || callToExit;
		return active && !exit;
	}

	@Override
	public void doWork() {
		if ((request = query.receive()) != null) {
			
			System.out.println("\t[ Client ] : [ " + ID + " ] : " + request);
			
			if (request.equals("#exit")) {
				callToExit = true;
				responce = "\t[ Server ] : bye bye";
			} else {
				responce = "\t[ Server ] : got your message (" + request + ")";
			}
			if(request.contains("#")) {
				responce = "\t[ Chat Bot ] : " + ChatBot.answer(request); 
			}
			query.send(responce); 
		}
	}
}