package web.socket.chat;

public class MessageParser {

	private final static String SPLITER = "#";
	private final static String SPLITER_PRIVATE = "@";
	private static String[] data = new String[3];
	
	public static void messageToData(String message) {
		if(message.contains(SPLITER)) {
			String[] tempdata = message.split(SPLITER);
			if (tempdata.length == 3) {
				data = tempdata;
			}
		}
	}
	
	public static User extractUser() {
		return new User(null, data[1], data[0]);
	}
	public static String extractMessage() {
		return new String(data[2]);
	}
	public static boolean isPrivateMessage() {
		return extractMessage().contains(SPLITER_PRIVATE);
	}
	public static String[] extractPrivateMessage() {
		String[] tempdata = extractMessage().split(SPLITER_PRIVATE);
		if (tempdata.length == 2) {
			return tempdata;
		}
		return new String[2];
	}
}
