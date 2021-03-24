package my.rabbit_chat.utils;

public class Executor {
	private Consumer consumer;
	private Producer producer;
	
	private String base;
	
	public Executor(String _base) {
		this.consumer = new Consumer();
		this.producer = new Producer();
		this.base = _base;
		new Thread(new Runnable() {
			@Override
			public void run() {
				consumer.receive(base);
			}
		}).start();
	}
	public void send(String target, Request message) {
		producer.send(target, message);
	}
	public boolean hasTask() {
		return consumer.hasNext();
	}
	public Object receive() {
		return consumer.next();
	}
	
	public void close() {
		consumer.close();
	}
}
