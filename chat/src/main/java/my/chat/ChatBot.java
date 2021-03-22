package my.chat;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class ChatBot {
	private static Map<String, String> answers = new HashMap<>();
	static {
		answers.put("#exit", "Good Bye! Please come again! ");
		answers.put("#weather", "Weather ? Hmm... This should help <https://www.google.com/search?q=weather> ");
		answers.put("#what", "What ? This is a good question! Creating answer[ 0 / 999] . . . ");
		answers.put("#time", "Time ? " + LocalTime.now().toString() + " ");
	}
	
	public static String answer(String request) {
		Optional<Entry<String, String>> res = null;
		if(request.contains("#")) {
			res = answers.entrySet().stream().filter(entry -> request.contains(entry.getKey())).findFirst();
		}
		return (res.isPresent()) ? res.get().getValue() : "Sorry, I will find out about this!";
	}
}
