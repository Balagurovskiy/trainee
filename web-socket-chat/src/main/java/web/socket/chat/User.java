package web.socket.chat;

public final class User {
	final private String name;
	final private String pass;
	final private String log;
	
	public User(String name, String pass, String log) {
		this.log = log;
		this.name = name;
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public String getLog() {
		return log;
	}
}
