package my.chat;

public interface IListener {
	public void setQuery(Query query);
	public boolean hasWork();
	public void doWork();
}
