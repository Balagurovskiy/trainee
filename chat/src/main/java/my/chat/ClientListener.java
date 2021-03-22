package my.chat;

import java.util.Objects;

public class ClientListener implements IListener{
	
	private String request;
	private String responce;
	private Query query;
	
	@Override
	public void setQuery(Query query) {
		this.query = query;
		request = "";
		responce = "";
	}

	@Override
	public boolean hasWork() {
		boolean active = request != null || responce != null;
		boolean exit = request == null || request.equals("#exit");
		return active && !exit;
	}

	@Override
	public void doWork() {
		if (Objects.nonNull(query)) {
			System.out.print(" [ You ] : ");
			if ((request = query.sreceive()) != null) {
				query.send(request);
			}
			if ((responce = query.receive()) != null) {
				System.out.println(responce);
			}
		}
	}
}
