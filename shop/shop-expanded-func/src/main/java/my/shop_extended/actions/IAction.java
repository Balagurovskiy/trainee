package my.shop_extended.actions;

public interface IAction {
	public void createInterface();
	public void acceptRequest(String request);
	public boolean hasNext();
	public IAction next();
	public boolean stop();
}
