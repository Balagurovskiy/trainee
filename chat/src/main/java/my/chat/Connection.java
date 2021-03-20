package my.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Connection implements AutoCloseable, Runnable{
	private Socket socket;
	private Query query;
	private IListener listener;
	private boolean isReady;
	private boolean isActive;
	private boolean isClosed;

	public Connection(Socket socket) {
		isReady = false;
		isActive = false;
		isClosed = false;
		this.socket = socket;
		this.query = new Query(socket);
	}
	public Connection(String address, int port) {
		try {
			this.socket = new Socket(address, port);
			this.query = new Query(socket);
		} catch (IOException e) {
			System.out.println("Exception ! There is no connection on : " + address + " | " + port);
		}
	}
	
	public void setListener(IListener listener) {
		this.listener = listener;
		if (Objects.isNull(listener)) {
			throw new IllegalArgumentException();
		}
		if (Objects.nonNull(this.query)) {
			isReady = true;
		}
	}

	@Override
	public void run() {
		if (isReady) {
			listener.setQuery(query);
			isReady = false;
			while(listener.hasWork()) {
				isActive = true;
				listener.doWork();
			}
			close();
		} else {
			throw new NullPointerException();
		}
	}
	
	public void close() {
		if (isActive) {
			isClosed = true;
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			query.close();
		}
		isReady = false;
		isActive = false;
	}
	
	public boolean isReady() {
		return isReady;
	}
	public boolean isActive() {
		return isActive;
	}
	public boolean isClosed() {
		return isClosed;
	}
}
