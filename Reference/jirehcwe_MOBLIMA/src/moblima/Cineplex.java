package moblima;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
	
	private static final long serialVersionUID = 3346347368664976796L;
	private int numOfCinema = 3;
	private String name;
	private ArrayList<Cinema> cinemaList;
	
	public Cineplex(String name, int num) {
		this.name = name;
		numOfCinema = num;
		cinemaList = new ArrayList<Cinema>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		}
	
	public int getNumOfCinema() {
		return numOfCinema;
	}
	
	public void setNumOfCinema(int num) {
		numOfCinema = num;
	}
	
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}	
	
}