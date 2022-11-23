package moblima;
import java.util.Calendar;
/**
 * Inherits from <code> Account</code> class.
 * @see Show
 *
 */
public class ShowIMAX extends Show {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9014464972543649951L;
	/**
	 * Stores the <code>Base Price</code> of an <code> IMAX Show </code>.
	 */
	private static float basePrice;
	/**
	 * <code>ShowIMAX</code> constructor.
	 * @param movie
	 * @param showTime
	 * @param cinemaLayout
	 */
	public ShowIMAX(String movie, Calendar showTime, char[][] cinemaLayout) {
		super(movie, showTime, cinemaLayout);
	}
	/**
	 * @return <code>float</code> value of <code>Show</code>'s base price. Method is static for saving in the database.
	 */
	public static float getBasePriceForSaving() {
		return basePrice;
	}
	
	/**
	 * @return <code>float</code> value of <code>ShowDigital</code>'s base price.
	 */
	public float getBasePrice(){
		return basePrice;
	}
	
	/**
	 * Allows <code>StaffAcc</code> to set the <code>Base Price</code> of an <code>IMAX Show</code>.
	 * @see StaffCtr
	 */
	public static void setBasePrice(float newBasePrice){
		ShowIMAX.basePrice = newBasePrice; 
	}
	
	/**
	 * @return <code>String</code> containing show type.
	 */
	public String getShowType() {
		return "IMAX";
	}

}
