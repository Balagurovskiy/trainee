package my.rabbit_chat.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Request implements Serializable{
	private static final long serialVersionUID = 111L;
	private static Logger logger = LoggerFactory.getLogger(Request.class);
	private final long uuid;
	private final String  message;
	private final LocalDateTime  time;
	
	public Request(long uuid, String  message) {
		this.uuid = uuid;
		this.message = message;
		time = LocalDateTime.now();
	}

	public String getUuid() {
		return String.valueOf(uuid);
	}

	public String getMessage() {
		return message;
	}
	
	public LocalDateTime getTime() {
		return time;
	}

	public byte[] getBytes() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	   	ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			logger.warn("Request IOException on ObjectOutputStream write");
		}
		finally {
			try {
				bos.close();
			} catch (IOException ex) {
				logger.warn("Request IOException on ByteArrayOutputStream close");
			}
		}
		return bos.toByteArray();
	}
	
	public static Request cast(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object o = null;
		try {
			in = new ObjectInputStream(bis);
			o = in.readObject(); 
		} catch (IOException | ClassNotFoundException e) {
			logger.warn("Request IOException on ObjectInput readObject");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				logger.warn("Request IOException on ObjectInput close");
			}
		}
		return (Request) o;
	}
}
