package moblima;

import java.io.Serializable; 
import java.time.LocalDateTime;

public class CinemaShow implements Serializable, Comparable<CinemaShow>{

	private static final long serialVersionUID = 707618588443761880L;
	private CinemaSeat[][] seats;
	private Movie movie;
	private LocalDateTime showtime;

	
	public CinemaShow(Movie movie, LocalDateTime showtime){
		seats = new CinemaSeat [8][8];
		
		for(int i = 0; i < 8; i++){
			if(i == 6 || i == 7){
				for(int j = 0; j < 8; j++){
					seats[i][j] = new CinemaSeat(SeatType.COUPLE);
				}
			}else{
				for(int j = 0; j < 8; j++){
					seats[i][j] = new CinemaSeat(SeatType.NORMAL);
				}
			}
		}
		
		this.showtime = showtime;
		this.movie = movie;
	}
	
	
	
	public LocalDateTime getShowtime() {
		return showtime;
	}
	
	public void setShowtime(LocalDateTime time) {
		showtime = time;
	}

	public Movie getMovie() {
		return movie;
	}

	public void printSeating(){
		System.out.println();;
		System.out.println("==================================");
		System.out.println("=          MOVIE SCREEN          =");
		System.out.println("==================================");
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 8; j++){
				if(j == 0){
					System.out.print(" " + (char) (65 + i) + " ");
				}
				if(j == 4){
					System.out.print("    ");
				}
				System.out.print("[");
				if(seats[i][j].getIsTaken()){
					System.out.print("X");
				}else{
					System.out.print(" ");
				}
				System.out.print("]");
				if(j == 7){
					System.out.print(" " + (char) (65 + i));
				}
			}
			System.out.println();
		}
		for(int i = 6; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(j == 0){
					System.out.print(" " + (char) (65 + i) + " ");
				}
				if(j == 4){
					System.out.print("    ");
				}
				if(j%2 == 0){
					System.out.print("[");
				}else{
					System.out.print(" ");
				}
				if(seats[i][j].getIsTaken()){
					System.out.print("X");
				}else{
					System.out.print(" ");
				}
				if(j%2 == 1){
					System.out.print("]");
				}else{
					System.out.print(" ");
				}
				if(j == 7){
					System.out.print(" " + (char) (65 + i));
				}
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
		System.out.println("    1  2  3  4      5  6  7  8    ");
		System.out.println("==================================");
		System.out.println("[X] = Seat Taken                  ");
		System.out.println("[ ] = Seat Available              ");
		System.out.println("==================================");
	}

	public CinemaSeat[][] getSeats(){
		return seats;
	}

	public void setSeat(int x, int y){	
		this.seats[x][y].seatIsTaken(true); 			
	}
	
	public boolean isSeatOccupied(int x, int y) {
		return this.seats[x][y].getIsTaken();
	}

	@Override
	public int compareTo(CinemaShow o) {
		return getShowtime().compareTo(o.getShowtime());
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
}

	