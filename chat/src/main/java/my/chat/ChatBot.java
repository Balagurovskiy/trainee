package my.chat;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ChatBot {
	private static Map<String, String> answers = new HashMap<>();
	static {
		answers.put("#exit", "Good Bye! Please come again! ");
		answers.put("#weather", "Weather ? Hmm... This should help <https://www.google.com/search?q=weather> ");
		answers.put("#what", "What ? This is a good question! Creating answer[ 0 / 999] . . . ");
		answers.put("#time", "Time ? " + LocalTime.now().toString() + " ");
	}
	
	public static String answer(String request) {
		String res = "";
		if(request.contains("#")) {
			for (Iterator iterator = answers.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, String> a = (Entry<String, String>) iterator.next();
				if(request.contains(a.getKey())) {
					res += a.getValue();
				}
			}
		}
		return (res.isEmpty()) ? "Sorry, I will find out about this!" : res;
	}
}
