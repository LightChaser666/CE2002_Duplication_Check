package org.gp3.moblima.view.moviegoer;

import org.gp3.moblima.controller.Manager;
import org.gp3.moblima.controller.PriceManager;
import org.gp3.moblima.model.*;
import org.gp3.moblima.view.BaseMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.gp3.moblima.view.IOUtil.*;
/**
 * Menu to buy ticket
 */
public class BuyTicketMenu extends BaseMenu {
    private final Movie movie;
    private Manager manager = Manager.getInstance();
    private PriceManager priceManager = PriceManager.getInstance();
    private boolean isstudent = false;
    private boolean issenior = false;
    public BuyTicketMenu(BaseMenu previousMenu, Movie movie) {
        super(previousMenu);
        this.movie = movie;
    }
    /**
     * List all the slots avaliable according to the movie information
     * Ask user to select slots, seats.
     * Ask user information whether they are student or senoir
     * Get user's information about name, email and phone number
     * Provide the total ticket price to the user
     * @return  GoerMainMenu
     */
    @Override
    public BaseMenu execute() {

        ArrayList<String> choices = new ArrayList<>();
        int c;
        printTitle("Buy Ticket Menu");

        if(movie.getSlots().isEmpty())
        {
            println("Sorry, there is no available slot currently.");
            return this.getPreviousMenu();
        }

        for(Slot slot:movie.getSlots())
        {
            choices.add(slot.getCinema().getName() + " -- " + slot.getFormattedDate() + " " + slot.getFormattedTime());
        }
        printMenuItems(choices, 0);
        c = readChoice("Please Choose a slot", 0, choices.size());
        Slot slot = movie.getSlots().get(c);

        ArrayList<ArrayList<Seat>> seats = slot.getSeats();
        int col = seats.get(0).size(), row=seats.size();

        //find seat & select seat
        ArrayList<Seat>selected = new ArrayList<>();
        while(confirm("Continue to select seats?"))
        {
            displaySeats(seats,row,col);
            selected.add(chooseSeats(seats,row,col));
        }

        if (selected.size() != 0) {
            ArrayList<Ticket> tickets = new ArrayList<>();


            if (confirm("Are you eligible for student discount?")) {
                isstudent = true;
                Constant.TicketType ticketType = priceManager.getTicketType(slot, isstudent, issenior);
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            } else if (confirm("Are you eligible for senior discount?")) {
                issenior = true;
                Constant.TicketType ticketType = priceManager.getTicketType(slot, isstudent, issenior);
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            } else {
                Constant.TicketType ticketType = priceManager.getTicketType(slot, isstudent, issenior);
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            }


            Ticket ticket = tickets.get(0);
            double totalprice = priceManager.getPrice(ticket.getTickettype(), ticket.getMovietype(), slot.isPlatinum(), slot.isSneakOrFirstWeekorblockbuster()) * tickets.size();
            Date currentTime = Calendar.getInstance().getTime();
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(currentTime);
            String tid = slot.getCinema().getCinemaCode() + timeStamp;
            Booking booking = new Booking(tid, slot, currentTime, totalprice, movie, slot.getCinema(), tickets);

            User user;
            if (confirm("Do you have an account")) {
                user = login();
            } else {
                String name = read("New Name: ");
                String email = read("New Email: ");
                String phone = read("New Phone number: ");
                while(Character.isLetter(phone.charAt(0))){
                    phone = read("Phone number should start with the numeric value, please re-enter ");
                }
                user = new User(name, phone, email);
                manager.add(Constant.Tables.USER, user);
            }

            println("Total price is S$" + booking.getTotalPrice() + " (Inclusive of GST).");
            if (confirm("Confirm to book? ")) {
                for (Seat seat : selected) {
                    seat.setSelected(false);
                    seat.setOcccupied(true);
                }
                user.addBookings(booking);
                booking.setUser(user);
                manager.add(Constant.Tables.BOOKING, booking);
                movie.addTicketSales(tickets.size());
                for (Ticket ticket1 : tickets) manager.add(Constant.Tables.TICKET, ticket1);
                println("Booking successful, tid=" + tid);

            } else {
                for (Seat seat : selected)
                    seat.setSelected(false);
            }
            while (readInt("Press 0 to return to previous menu: ") != 0) ;
        }
        return this.getPreviousMenu();
    }
    /**
     * Print out the layout of the seats in the current slots,
     * including seats avaliable, seats occupied and seats chosen
     * With corridor printed out in the middle of the layout
     */
    private void displaySeats(ArrayList<ArrayList<Seat>> seats, int row, int col)
    {
        Seat seat;
        printTitle(" Select Seats");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            print(" ");
        println("|      Screen       |");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++) {
            print(" ");
        }
        println("---------------------");

        print("    ");
        int new_row = 0;
        for(int i=0; i<col; i++){
            if (new_row != col / 2 - 1) {
                printf(i + 1);
            } else {
                print("  ");
                i--;
            }
            new_row++;
        }

        println();
        boolean flag = false;
        for(int i =0; i<row; i++)
        {
            new_row = 0;
            printf(i + 1);
            for(int j=0; j<col; j++)
            {
                if (new_row != col / 2 - 1) {
                    seat = seats.get(i).get(j);
                    if (seat.isOcccupied()) {
                        print("[X]");
                    } else if (seat.isSelected()) {
                        print("[#]");
                    } else
                        print("[ ]");
                } else {
                    print("   ");
                    j--;
                }
                new_row++;
            }
            print(" ");
            printf(i + 1);
            println();
        }

        println("");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            print(" ");
        println("----------");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            print(" ");
        println("|Entrance|\n");
        println("([ ] Available  [#] Seat Selected  [X] Sold)");
    }

    /**
     * Method to ask user to select a row and column,
     * check whether the seat is avaliable, and update the information in the data base
     */
    private Seat chooseSeats(ArrayList<ArrayList<Seat>> seats, int row, int col) {
        println("Please choose your seat(s).");
        int i,j;
        do {
            i = readInt("Please input row number",1,row);
            j = readInt("Please input col number",1,col);
            i--;j--;
            if (seats.get(i).get(j).isOcccupied() || seats.get(i).get(j).isSelected())
                println("Already been taken/selected please choose another seats.");
            else break;
        } while (true);

        seats.get(i).get(j).setSelected(true);
        println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));

        return seats.get(i).get(j);
    }
}
