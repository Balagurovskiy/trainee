package my.shop_structure;
import java.util.Scanner;

import my.shop_structure.actions.IAction;
import my.shop_structure.actions.MainMenu;
import my.shop_structure.bucket.Bucket;
import my.shop_structure.products.ProductStash;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean work = true;
		
		ProductStash stash = new ProductStash();
		Bucket bucket = new Bucket();
		IAction currentAction = new MainMenu(stash, bucket);
		
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
