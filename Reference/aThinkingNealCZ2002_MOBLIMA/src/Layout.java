import java.io.Serializable;

/**
 * The class containing the layout of cinema rooms, containing the informations like Seat, class of the room and some methods.
 */
public class Layout implements Serializable {
    private Seat[][] layout;
    private static final long serialVersionUID = 1L;
    private Cinema.CinemaClass movieclass = Cinema.CinemaClass.normal;
    private String seatIDAlphabets = "ABCDEFGHIJ";

//    enum Movieclass{
//        platinum,golden,normal
//    }

    /**
     * Instantiates a new Layout.
     *
     * @param cid         the cid
     * @param cinemaClass the cinema class
     */
    public Layout(int cid, Cinema.CinemaClass cinemaClass) {
        int dimension;
        if (cinemaClass == Cinema.CinemaClass.platinum)
            dimension = 4; // if it's platinum, cinema has 3*3 seats;
        else if (cinemaClass == Cinema.CinemaClass.golden)
            dimension = 6;
        else
            dimension = 10;
        layout = new Seat[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                layout[i][j] = new Seat(i, j, false);
        movieclass=cinemaClass;

    }

    /**
     * Get class cinema . cinema class.
     *
     * @return the cinema . cinema class
     */
    public Cinema.CinemaClass GetClass() {
        return movieclass;
    }

    /**
     * Display the layout of one Movie Schedule, including availability of seats, the Screen and Entrance location
     */
    public void displayLayout() {
        for (int t = 0; t<Math.floor(4*layout.length)-5; t++)
            System.out.print(" ");
        System.out.print("[SCREEN]\n"); 
        for (int i=0; i<layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                if (j==0)
                    System.out.print(" \\_"+"_| ");
                if (j==(Math.floor(0.5*layout[0].length)))
                    System.out.print(" |_"+"_| ");
                System.out.print("  "+layout[i][j].GetId()+"  ");
                if (j==(layout[0].length-1))
                    System.out.print(" |_"+"_/ ");
            }
            System.out.print("\n");
//            boolean res=layout[1][5].bookSeat();
            for (int j = 0; j < layout[0].length; j++) {
                if (j==0)
                    System.out.print(" \\_"+"_| ");
                if (j==(Math.floor(0.5*layout[0].length)))
                    System.out.print(" |_"+"_| ");
                if (layout[i][j].GetOccu()){
                    System.out.print(" [XX] ");
                } else{
                    System.out.print(" [  ] ");
                }
                if (j==(layout[0].length-1))
                    System.out.print(" |_"+"_/ ");
            }
            System.out.print("\n");
            System.out.print("\n");

        }
    }

    /**
     * Get the cinema layout.
     *
     * @return the seat [ ] [ ]
     */
    public Seat[][] getLayout(){
        return layout;
    }

    /**
     * Change the status of one seat from occupied to available
     *
     * @param seatID the changed seat
     */
    public void cancelSeat(String seatID) {

        int x = seatIDAlphabets.indexOf(seatID.charAt(0));
        int y = Integer.parseInt(String.valueOf(seatID.charAt(1)));
        layout[x][y].cancelBooking();
    }

    /**
     * Change the status of one seat from available to occupied
     *
     * @param seatID the changed seat
     */
    public void bookSeat(String seatID) {
        int x = seatIDAlphabets.indexOf(seatID.charAt(0));
        int y = Integer.parseInt(String.valueOf(seatID.charAt(1)));
        layout[x][y].bookSeat();
    }

    public boolean checkValidSeatID(String seatID) {
        int x = seatIDAlphabets.indexOf(seatID.charAt(0));
        int y = Integer.parseInt(String.valueOf(seatID.charAt(1)));
        if (x >= 0 && x < this.layout[0].length && y >= 0 && y < this.layout[0].length)
            return true;
        else return false;
    }
}
