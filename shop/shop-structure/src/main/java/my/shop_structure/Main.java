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

//	TODO
//	1. Our shop will have 2 product groups (food and not food.)
//	2. Application must be started from the console.
//	3. After an action has been chosen current choice must be processed.
//	4. After a finish/exit has been typed the app should be closed.
//
//	Actions:
//
//	Show product list.
//	Add chosen product to the bucket
//	Show products in the bucket
//	Delete(particular) product from the bucket
//	Clear the bucket
//	Functional must be covered by tests.
}
