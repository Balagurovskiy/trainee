package my.shop_extended;
import java.util.Scanner;

import my.shop_extended.actions.IAction;
import my.shop_extended.products.ProductStash;
import my.shop_extended.actions.PreloadCustomer;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean work = true;
		
		ProductStash stash = new ProductStash();
		IAction currentAction = new PreloadCustomer(stash, null);
		
		while(work) {
			currentAction.createInterface();
			currentAction.acceptRequest( scanner.next() );
			if (currentAction.hasNext()) {
				currentAction = currentAction.next();
			}
			if(currentAction.stop()) {
				work = false;
			}
		}
		scanner.close();
	}
}
