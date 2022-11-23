package moblima;
import java.io.*;
/**
 * Allows <code>CustAcc</code> to add <code>Reviews</code> for a <code>Movie</code> object.
 * @see Movie
 * @see CustCtr
 */
public class Reviews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8819093047305119458L;
	/**
	 * stores content <code>String</code> of <code>Reviews</code> object.
	 */
	private String content;
	/**
	 * stores rating <code>int</code> of <code>Reviews</code> object.
	 */
	private int rating;
	/**
	 * stores name of reviewer <code>String</code> of <code>Reviews</code> object.
	 */
	private String reviewer;
	/**
	 * <code>Reviews</code> constructor.
	 * @param content
	 * @param rating
	 * @param reviewer
	 */
	public Reviews(String content, int rating, String reviewer){
		this.content = content;
		this.rating = rating;
		this.reviewer = reviewer;
	}
	/**
	 * 
	 * @return <code>int</code> containing the rating of the review.
	 */
	public int getRating(){
		return this.rating;
	}
	
	/**
	 * 
	 * @return <code>String</code> containing the name of the reviewer.
	 */
	public String getReviewer(){
		return this.reviewer;
	}
	/**
	 * 
	 * @return <code>String</code> containing the content of the review.
	 */
	public String getContent(){
		return this.content;
	}
	
}
