package moblima;

import java.io.Serializable;

public class CinemaSeat implements Serializable{

	private static final long serialVersionUID = -3379918496585890262L;
	private boolean isTaken;
	private SeatType seatType;

	public CinemaSeat(SeatType seatType){
		isTaken = false;
		this.seatType = seatType;
	}
	
	public boolean getIsTaken() {
		return isTaken;
	}
	
	public void seatIsTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	public SeatType getSeatType() {
		return seatType;
	}
	
	public static class SeatOccupiedException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3520635930531900084L;
		
		public SeatOccupiedException() {
			
		}
		
		public SeatOccupiedException(String message){
	        super(message);
	    }
	}
}
