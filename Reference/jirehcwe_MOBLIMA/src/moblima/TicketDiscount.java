package moblima;

import java.io.Serializable;

public class TicketDiscount implements Serializable {
	
	private static final long serialVersionUID = 96857686746573625L;
	
	private String discountName;
	private int discountValue;
	
	//default constructor
	public TicketDiscount() { 
		discountName = null;
		discountValue = 0;
	}
	
	//constructor
	public TicketDiscount(String type, int value)
	{
		discountName = type;
		discountValue = value;
	}
	
	public String getDiscountName() {
		return discountName;
	}
	
	public void setDiscountName(String type) {
		discountName = type;
	}
	
	public int getDiscountValue() {
		return discountValue;
	}
	
	public void setDiscountValue(int value) {
		discountValue = value;
	}
	
	

}
