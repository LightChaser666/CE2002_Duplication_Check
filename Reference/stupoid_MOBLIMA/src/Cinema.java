
public class Cinema {
	String name;
	Seat[][] seats;

	public Cinema(String name, int rows, int cols) {
		this.name = name;
		seats = new Seat[rows][cols];
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				seats[i][j] = new Seat(i,j);
			}
		}
	}
}
