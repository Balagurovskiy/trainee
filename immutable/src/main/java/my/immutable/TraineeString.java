package my.immutable;

import java.time.LocalDate;
import java.util.Objects;

public final class TraineeString {
	
	private final int COUNT;
	private final String STRING;
	private final LocalDate CREADTED;
	
	private final IdProvider ID;
	
	public TraineeString(String string) {
		if (Objects.isNull(string)) {
			throw new IllegalArgumentException();
		}
		this.STRING = string;
		this.COUNT = this.STRING.length();
		this.CREADTED = LocalDate.now();
		
		this.ID = new IdProvider();
	}

	public IdProvider getIdProvider() {
		return (IdProvider) ID.clone();
	}

	public long getCount() {
		return COUNT;
	}

	public String getString() {
		return STRING;
	}

	public LocalDate getCreated() {
		return CREADTED;
	}

	@Override
	public Object clone(){
		TraineeString copy = new TraineeString(STRING);
		return copy;
	}
	@Override
	public boolean equals(Object obj) {
		if(Objects.isNull(obj)) {
			return false;
		}
		if (obj instanceof TraineeString) {
			TraineeString that = (TraineeString) obj;
			if (that.getString().equals(this.STRING)) {
				if (that.getCount() == this.COUNT) {
					if(that.getCreated().equals(CREADTED)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
