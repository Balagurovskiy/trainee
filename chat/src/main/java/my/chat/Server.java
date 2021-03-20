package my.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
	
	private ServerSocket server;
	
	public Server(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(" [ Server ] : started"); 
		System.out.println(" [ Server ] : waiting for a clients ...");
		
		ConnectionPool connectionPool = new ConnectionPool(server);
		new Thread(connectionPool).start();
		
		while(connectionPool.isActive()) {
			try {
				Connection connection = null;
				try {
					connection = new Connection(server.accept());
				}catch (SocketException e) {
				}
				if (Objects.nonNull(connection)) {
					connection.setListener(new ServerListener());
					connectionPool.put(connection);
					new Thread(connection).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" [ Server ] : stoped");
	}
	public static void main(String args[]) 
	{ 
		new Server(5000); 
	} 
}
