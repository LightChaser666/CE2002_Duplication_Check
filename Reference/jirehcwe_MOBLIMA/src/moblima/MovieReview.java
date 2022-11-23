package moblima;

import java.io.Serializable;

public class MovieReview implements Serializable {
	
	private static final long serialVersionUID = 48572957668402758L;
	private String review;
	private int rating;
	
	//constructor
	public MovieReview(String review, int rating)
	{
		this.review = review;
		this.rating = rating;
	}

	public String getReview()
	{
		return review;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public void setReview(String review, int rating)
	{
		this.review = review;
		this.rating = rating;
	}
}