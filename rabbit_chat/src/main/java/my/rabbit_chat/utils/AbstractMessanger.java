package my.rabbit_chat.utils;


public abstract class AbstractMessanger {

	protected final long uuid;
	protected Executor executor;
	protected boolean stop;
	protected String target;
	
	protected AbstractMessanger(long uuid) {
		target = "";
		stop = false;
		this.uuid = uuid;
		executor = new Executor(String.valueOf(uuid));
	}
	
	abstract public void receive() ;
	
	abstract public void send();
	
	public void setTarget(String target) {
		this.target = target;
	}

	
}
