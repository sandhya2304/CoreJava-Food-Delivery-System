package fooddelivey.model;




public class Review
{
	
	private int userId;
	private int restId;
	private int rating;
	private String comment;
	
	public Review(int userId, int restId, int rating, String comment) {
		super();
		this.userId = userId;
		this.restId = restId;
		this.rating = rating;
		this.comment = comment;
	}

	public int getUserId() {
		return userId;
	}

	public int getRestId() {
		return restId;
	}

	public int getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public String toString() {
		return "Review [rating=" + rating + ", comment=" + comment + "]";
	}
	
	
	
	

}
