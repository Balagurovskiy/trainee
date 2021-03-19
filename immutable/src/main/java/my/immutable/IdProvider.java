package my.immutable;

public class IdProvider {

	private static long count;
	private long id;

	public IdProvider() {
		id = count;
		count++;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	protected Object clone(){
		IdProvider idp = new IdProvider();
		idp.setId(id);
		return idp;
	}
}
