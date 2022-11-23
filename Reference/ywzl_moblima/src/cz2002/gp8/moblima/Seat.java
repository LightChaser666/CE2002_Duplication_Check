package cz2002.gp8.moblima;



/**
 * This represents a seat in a cinema for the corresponding showtime.
 * @author Lloyd
 */
public class Seat {
	private int row;
	private int col;
	private boolean taken;
	
    /**
     * Standard constructor for Seat. Creates a seat with the row and col passed in.
     * @param row
     * @param col
     */
    public Seat(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}
	
    /**
     * This assigns a seat to a user after they selected it for a booking.
     */
    public void assignSeat() {
		taken = true;
	}
	
    /**
     * This lets us know if a seat is taken or not.
     * @return true if seat is taken.
     */
    public boolean isTaken() {
		return taken;
	}
	
    /**
     * This returns alphabet of the row instead of an integer.
     * @return alphabet (char).
     */
    public char getRowAlphabet() {
		return (char) (row+65);
	}
	
    /**
     * This returns the seat number by its row (alphabet) and column.
     * @return alphabet character for row, integer for column.
     */
    public String getSeatNum() { 
		return getRowAlphabet() + ":" + col;
	}

	/**
	 * Returns row of a seat.
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set the row of the seat.
	 * @param row of the seat
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Returns the column of a seat.
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set the column of the seat.
	 * @param col of the seat
	 */
	public void setCol(int col) {
		this.col = col;
	}

}
