package fooddelivey.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.MenuItem;
import fooddelivey.model.Restaurant;



public class RestaurantService
{
	ReviewService reviewService = new ReviewService();
	List<Restaurant> restaurants;
	
	
	
	public RestaurantService() {
		this.restaurants = new ArrayList<Restaurant>();
		loadRestaurantsFromFile();
	}
	
	
	public void loadRestaurantsFromFile()
	{
		
		try(BufferedReader reader = new BufferedReader(new FileReader("restaurants.txt")))
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split(",");
				if(parts.length == 3)
				{
					int id = Integer.parseInt(parts[0]);
					String name = parts[1];
					String address = parts[2];
					Restaurant res =  new Restaurant(id, name, address);
					res.setMenuItems(loadMenuItems(id));
					restaurants.add(res);
					
				}				
			}
						
		}catch(Exception ie)
		{
			  System.out.println("📁 No existing restaurants found.");
		}
				
	}
	
	
	public List<MenuItem> loadMenuItems(int restId)
	{
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		String fileName = "menu_restaurant_" + restId + ".txt";

		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 4) {
	                int id = Integer.parseInt(parts[0]);
	                String name = parts[1];
	                double price = Double.parseDouble(parts[2]);
	                String desc = parts[3];
	                menuItems.add(new MenuItem(id, name, price, desc));
	            }
	        }
		}catch(Exception io)
	        {
	        	System.out.println("MEnu items not found");
	        }
		return menuItems;
		
	}
	
	
	public void saveRestaurantToFile(Restaurant restaurant)
	{
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("restaurants.txt", true))) {
	        String line = restaurant.getId() + "," + restaurant.getName() + "," + restaurant.getAddress();
	        writer.write(line);
	        writer.newLine();
	    } catch (IOException e) {
	        System.out.println("❌ Error saving restaurant.");
	    }
		
	
	}
	

	public void addRestaurant(String name,String address)
	{
		int restId = restaurants.size() + 1;
		Restaurant rest = new Restaurant(restId, name, address);
	
		restaurants.add(rest);
		saveRestaurantToFile(rest);
		
		System.out.println("Restaurant added successfullyyyy!!!!!");
	}
	
	
	public Restaurant getRestaurantById(int id)
	{
		for(Restaurant rest : restaurants)
		{
			if(rest.getId() == id)
			{
				return rest;
			}
		}
		return null;
	}
	
	public void addMenuItem(int restId,String itemName,double price,String desc)
	{
		Restaurant rest = getRestaurantById(restId);
		if(rest != null)
		{
			int itemId = rest.getMenuItems().size()+1;
			MenuItem menuItem = new MenuItem(itemId, itemName, price, desc);
			
			rest.addMenuItem(menuItem);
			saveMenuItemToFile(restId,menuItem);
			System.out.println("Menu item Added in restaurant :"+rest.getName());
		}else
		{
			System.out.println("Menu item not able to add : No Restaurant Available");
		}	
	}
	
	

	
	private void saveMenuItemToFile(int restId, MenuItem item) 
	{
		
		
		
		String filename = "menu_restaurant_" + restId + ".txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
	        String line = item.getId() + "," + item.getName() + "," + item.getPrice() + "," + item.getDescription();
	        writer.write(line);
	        writer.newLine();
	    } catch (IOException e) {
	        System.out.println("❌ Error saving menu item.");
	    }
		
		
		
	}


	public void viewMenuItems(int restId)
	{
		Restaurant rest = getRestaurantById(restId);
		if(rest != null)
		{
			List<MenuItem> menuItems = rest.getMenuItems();
			if(menuItems.isEmpty())
			{
				System.out.println("No menu items added");
			}else
			{
				for(MenuItem item : rest.getMenuItems())
				{
					System.out.println(item);
				}
			}
			
		}else
		{
			System.out.println("Menu item not able to add : No Restaurant Available");
		}
		
	}
	
	
	/**
	 * 
	 * Serach Restaurant
	 */
	
	public void searchRestaurants(String keyword)
	{
		keyword = keyword.toLowerCase();
		boolean found = false;
		
		for(Restaurant rest : restaurants)
		{
			if(rest.getName().toLowerCase().contains(keyword) || 
					  rest.getAddress().toLowerCase().contains(keyword))
			{
				System.out.println(rest);
				found = true;
			}		
		}
		if(!found)
		{
			 System.out.println("❌ No restaurants found for keyword: " + keyword);
		}
	}
	
	
	/****
	 * 
	 * Filter and Search Functionality
	 * 
	 */
	
	
	public void searchMenuByName(int restaurantId,String keyword)
	{
		
		Restaurant rest = getRestaurantById(restaurantId);
		
		if(rest == null)
		{
			System.out.println("Restaurant not available!!");
			return;
		}
		
		
		List<MenuItem> menuItem = rest.getMenuItems();
		boolean found = false;
		
		for(MenuItem item : menuItem)
		{
		    if(item.getName().toLowerCase().contains(keyword))
		    {
			    System.out.println(item);
			     found = true;
		     }
		}
		if(!found)
		{
			System.out.println("No item found with keyword!!");
		}
		
	}
	
	
	public void filterMenyByPrice(int restaurantId,double minPrice,double maxPrice)
	{
		
       Restaurant rest = getRestaurantById(restaurantId);
		
		if(rest == null)
		{
			System.out.println("Restaurant not available!!");
			return;
		}
		

		List<MenuItem> menuItem = rest.getMenuItems();
		boolean found = false;
		
		for(MenuItem item : menuItem)
		{
		    if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
		    {
			    System.out.println(item);
			     found = true;
		     }
		}
		if(!found)
		{
			System.out.println("No item found with price range!!"+minPrice+ " Max PRice "+maxPrice);
		}
		
		
	}
	
	
	

	public void viewAllRestaurant()
	{
		if(restaurants.isEmpty())
		{
			System.out.println("no restaurant: Availabale");
			return;
		}
		for(Restaurant rest : restaurants)
		{
			double avgRating = reviewService.getAvgRating(rest.getId());
			System.out.println(rest + " | ⭐ Avg Rating: " + String.format("%.2f", avgRating));
			
		}
		
	}
	
	public List<Restaurant> getAllRestaurants()
	{
		return restaurants;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
