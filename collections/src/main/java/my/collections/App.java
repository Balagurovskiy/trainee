package my.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import my.collections.linkedlist.TraineeLinkedList;

public class App {
	private static void loadData(List<String> l) {
		String testData = "qwertyuiop";
		for (int i = 0; i < testData.length(); i++) {
			l.add(testData.substring(0, i));
		}
	}
 
	
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		loadData(list);
			list.add("X");
		list.forEach(l->System.out.println("foreach : "+l));
//		String[] ss = {"xxx","yyy","yyy","yyy","yyy","yyy","yyy","yyy","yyy","yyy","yyy","yyy","yyy"};
//		String[] qq = list.toArray(ss);
//		for (int i = 0; i < qq.length; i++) {
//			System.out.println(">>"+qq[i]);
//		}
	}
	
}
