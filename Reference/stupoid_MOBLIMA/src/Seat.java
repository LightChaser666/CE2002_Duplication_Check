
public class Seat {
	int row;
	int col;
	boolean taken;

	public Seat(int row, int col) {
		this.row = row;
		this.col = col;
		this.taken = false;
	}
	
	public void assignSeat() {
		taken = true;
	}

}
