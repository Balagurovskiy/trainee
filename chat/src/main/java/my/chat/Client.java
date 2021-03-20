package my.chat;

public class Client {
	
	public Client(String address, int port) 
	{ 
		try(Connection connection = new Connection(address, port)){ 
			System.out.println(" [ You ] : connected");
			connection.setListener(new ClientListener());
			connection.run();
		}
	}
	
	public static void main(String args[]) 
	{ 
		Client client = new Client("127.0.0.1", 5000); 
	} 
}
