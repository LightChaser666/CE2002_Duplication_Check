package cz2002.gp8.moblima;



/**
 * Ticket represents the types of ticket and the corresponding prices to be sold at the Cinema
 * @author Lloyd
 */
public class Ticket {
	private String type;
	private double price;
	
    /**
     * Creates a ticket with that type and price
     * @param type of ticket e.g. Weekday, PH
     * @param price of ticket
     */
    public Ticket(String type, double price) {
		this.type = type;
		this.price = price;
	}
	
    /**
     * Prints out the type of ticket followed by the price
     */
    public void displayTicket() {
		System.out.println(type + " - $" + price);
	}
	
    /**
     * Get the type of ticket
     * @return the String of the Ticket type
     */
    public String getType() {
		return type;
	}
	
    /**
     * Get the price of the ticket
     * @return the Double value of the Ticket
     */
    public double getPrice() {
		return price;
	}
	
    /**
     * Increments the Ticket price by a certain amount
     * @param increment The amount to increment the price by
     */
    public void incPrice(double increment) {
		this.price += increment;
	}
	
    /**
     * Set the price of the Ticket to a certain amount
     * @param price to set the Ticket to
     */
    public void setPrice(double price) {
		this.price = price;
	}

}
