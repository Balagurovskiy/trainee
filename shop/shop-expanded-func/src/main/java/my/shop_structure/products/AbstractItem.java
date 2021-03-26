package my.shop_structure.products;

public abstract class AbstractItem {

	protected final String name;
	
	protected AbstractItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
