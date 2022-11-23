package moblima;

import java.io.Serializable;
import java.time.MonthDay;

public class PublicHoliday implements Serializable, Comparable<PublicHoliday>{

	private static final long serialVersionUID = 7423582918178200639L;
	private String description;
	private MonthDay date;
	
	public PublicHoliday(String description, MonthDay date) {
		this.description = description;
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MonthDay getDate() {
		return date;
	}

	public void setDate(MonthDay date) {
		this.date = date;
	}

	@Override
	public int compareTo(PublicHoliday o) {
		return getDate().compareTo(o.getDate());
	}
}
