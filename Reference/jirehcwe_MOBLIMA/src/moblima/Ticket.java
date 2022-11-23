package moblima;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public abstract class Ticket implements Serializable {
	
	private static final long serialVersionUID = 84759385764544637L;
	
	static float BASE_PRICE_TICKET = 10;
	
	
	private float price;
	private String discount = "None";
	private String cineplex; 
	private String cinema; 
	private String movie; 
	private String time; 
	private String seat;
	private String TID = "XXXYYYYMMDDhhmm";
	
	private String ticketholderName;
	private String email;
	private int mobileNumber;
	
	private CinemaEnum classOfCinema;
	private LocalDateTime movieDay;
	private MovieType mType;
	
	
	
	public Ticket(String seat, int age, int cinemaIndex, int cineplexIndex, int showtimeIndex, String ticketholderName, String email, int mobileNum){	//create new constructor. use calculate ticket price to set the price
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		
		this.cineplex 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getName(); //use cineplex index to get
		this.cinema 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaID();; //use cinema index to get
		this.movie 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getTitle();
		this.time 				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime().format(formatter);
		this.seat 				= seat;
		this.classOfCinema		= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getClassOfCinema();
	    this.movieDay 			= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getShowtime();
	    this.ticketholderName	= ticketholderName;
	    this.email 				= email;
	    this.mobileNumber		= mobileNum;
	    this.mType				= CineplexDatabase.cineplexList.get(cineplexIndex -1).getCinemaList().get(cinemaIndex-1).getCinemaShowList().get(showtimeIndex-1).getMovie().getMovieType();		
		this.price 				= calculateTicketPrice();
		this.discount 			= getDiscount();		
		this.TID 				= this.cinema.concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
		
	}
	
	public float getTicketPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public LocalDateTime getMovieDay() {
		return movieDay;
	}

	public abstract float calculateTicketPrice();
	
	public String getTicketholderName() {
		return ticketholderName;
	}
	
	public MovieType getMovieType() {
		return mType;
	}
	
	public void setTicketPrice(float price) {
		this.price = price;
	}

	
	public void setCineplex(String cineplexName){
		cineplex = cineplexName;
	}
	public void setCinema(String cinemaName){
		cinema = cinemaName;
	}
	
	public CinemaEnum getCinemaType(){
		return classOfCinema;
	}
	public void setMovie(String movie){
		this.movie = movie;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setSeat(String seat){
		this.seat= seat;
	}
	public void printTicket(){
		System.out.println("============================================");
		System.out.println("~~~~~~~~~~~~~~~~~~~Ticket~~~~~~~~~~~~~~~~~~~");
		System.out.println("	Customer: " + ticketholderName + "		");
		System.out.println("	Cineplex: " + cineplex + "				");
		System.out.println("	Cinema: " + cinema + "					");
		System.out.println("											");
		System.out.println("	Movie: " + this.movie + "				");
		System.out.println("	Date: " + this.movieDay.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
		System.out.println("	Time: " + this.time + "					");
		System.out.println("	Seat: " + this.seat + "					");
		System.out.println("											");
		System.out.println("											");
		System.out.println("	Email: " + email + "					");
		System.out.println("	Number: " + mobileNumber + "			");	
		System.out.println("	Transaction ID: " + this.TID + "		");

	}
}
