package moblima;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	
	private static final long serialVersionUID = 528082458209220230L;
	private ArrayList<CinemaShow> CinemaShowList;
	private CinemaEnum classOfCinema;
	private String cinemaID;

	public Cinema(CinemaEnum classOfCinema, String cinemaID) {
		CinemaShowList = new ArrayList<CinemaShow>();
		this.classOfCinema = classOfCinema;
		this.cinemaID = cinemaID;
	}
	
	public String getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(String cinemaID) {
		this.cinemaID = cinemaID;
	}

	public CinemaEnum getClassOfCinema() {
		return classOfCinema;
	}

	public ArrayList<CinemaShow> getCinemaShowList() {
		return CinemaShowList;
	}
	
	public void addCinemaShow(CinemaShow show){
		CinemaShowList.add(show);
	}
	
	public void removeCinemaShow(int index){
		CinemaShowList.remove(index - 1);
	}
}
