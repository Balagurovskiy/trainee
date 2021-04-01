package my.shop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import my.shop.actions.IAction;
import my.shop.actions.PreloadCustomer;
import my.shop.products.ProductStash;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean work = true;
		
		ProductStash stash = new ProductStash();
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1111")) {
			
			IAction currentAction = new PreloadCustomer(stash, null, connection);
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
			
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		scanner.close();
	}
}
