package my.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class Query {
	private BufferedReader in;
	private BufferedReader systemIn; 
	private BufferedWriter out;
	
	public Query(Socket socket) {
		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
			this.systemIn = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
			this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8"))); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String sreceive() {
		try {
			return systemIn.readLine();
		} catch (IOException e) {
			close();
		}
		return null;
	}
	public String receive() {
		try {
			return in.readLine();
		} catch (IOException e) {
			close();
		}
		return null;
	}
	public void send(String line) {
		try {
			out.write(line + "\r\n");
			out.flush();
		} catch (IOException e) {
			close();
		}
	}
	public void close() {
		try {
			systemIn.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
