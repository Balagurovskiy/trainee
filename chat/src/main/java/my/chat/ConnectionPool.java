package my.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool implements Runnable{
	List<Connection> all;
	private boolean isActive;
	private boolean isWaiting;
	private ServerSocket server;
	
	public ConnectionPool(ServerSocket server) {
		all = new CopyOnWriteArrayList<>();
		isActive = true;
		isWaiting = false;
		this.server = server;
	}
	public void put(Connection c) {
		all.add(c);
	}
	@Override
	public void run() {
		while(all.isEmpty()) {
			wait1Sec();
		}
		isWaiting = false;
		isActive = true;
		while(isActive) {
			isActive = false;
			for(Connection connection : all) {
				if (connection.isActive()) {
					isActive = true;
				}
			}
			wait1Sec();
		}
		System.out.println(" [ Connection Pool ] : client list is empty");
		try {
			this.server.close();
		} catch (IOException e) {
		}
	}
	private void wait1Sec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean isActive() {
		return isActive;
	}
	public boolean isWaiting() {
		return isWaiting;
	}
	
}
