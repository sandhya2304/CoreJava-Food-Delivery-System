package fooddelivey.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.Review;

public class ReviewService 
{
	
	public void addReview(int userId,int restId,int rating,String comment)
	{
		
		 String fileName = "reviews_restaurant_" + restId + ".txt";
		
		 try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
		 {
			 writer.write(userId+","+restId+","+comment);
			 writer.newLine();
			 System.out.println("Review Submitted");
			 
			 
		 }catch(IOException io)
		 {
			 System.out.println("Failed to Save Review");
		 }
		
	}
	
	
	public List<Review> loadReviews(int restId)
	{
		  List<Review> reviews  = new ArrayList<Review>();
		  String fileName = "reviews_restaurant_" + restId + ".txt";
		  
		  try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		  {
			  
			  String line;
			  while((line = reader.readLine()) != null)
			  {
				  String[] parts = line.split(",",3);
				  if(parts.length == 3)
				  {
					 int userId= Integer.parseInt( parts[0] );
					 int rating = Integer.parseInt(parts[1]);
	                   String comment = parts[2];
	                   reviews.add(new Review(userId, restId, rating, comment));
				  }
			  }
			  
			  
		  }catch(IOException ei)
		  {
			  
		  }
		return reviews;	
	}
	
	public double getAvgRating(int restID)
	{
		List<Review> reviews = loadReviews(restID);
		if(reviews.isEmpty())
			return 0.0;
		
		int total = 0;
		for(Review review : reviews)
		{
			total += review.getRating();
		}
		return (double) total/reviews.size();
		
	}
	
	public void printAllReview(int restID)
	{
		
		List<Review> reviews = loadReviews(restID);
		if(reviews.isEmpty())
		{
			System.out.println("no review yet :");
			return;
		}
		
		for(Review review : reviews)
		{
			System.out.println(review);
		}
		 
		
	}
	
}
