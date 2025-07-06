package fooddelivey.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant 
{
	
	private int id;
	private String name;
	private String address;
    private List<MenuItem> menuItems;
    
    
    
    
	public Restaurant(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.menuItems = new ArrayList<MenuItem>();
	}
	
	public void addMenuItem(MenuItem item)
	{
		this.menuItems.add(item);
	}



	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public List<MenuItem> getMenuItems() {
		return menuItems;
	}




	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + "]";
	}




	
    
	
	
}
