package fooddelivey.service;

import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.MenuItem;
import fooddelivey.model.Restaurant;



public class RestaurantService
{
	
	List<Restaurant> restaurants;
	
	
	
	public RestaurantService() {
		this.restaurants = new ArrayList<Restaurant>();
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
			System.out.println(rest);
		}
		
	}
	
	public List<Restaurant> getAllRestaurants()
	{
		return restaurants;
	}
	
	

	public void addRestaurant(String name,String address)
	{
		int restId = restaurants.size() + 1;
		Restaurant rest = new Restaurant(restId, name, address);
		restaurants.add(rest);
		
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
			System.out.println("Menu item Added in restaurant :"+rest.getName());
		}else
		{
			System.out.println("Menu item not able to add : No Restaurant Available");
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

}
