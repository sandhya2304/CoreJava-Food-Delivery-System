package fooddelivey.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.MenuItem;
import fooddelivey.model.Order;
import fooddelivey.model.Restaurant;
import fooddelivey.model.User;

public class OrderService
{
	
	List<Order> orders;
	
	
	
	public OrderService()
	{
		this.orders = new ArrayList<Order>();
		loadOrdersFromFile();
	}
	
	
	private void loadOrdersFromFile()
	{
	    try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt")))
	    {
	        String line;
	        while ((line = reader.readLine()) != null) 
	        {
	            String[] parts = line.split(",", 7);
	            if (parts.length == 7) 
	            {
	                int orderId = Integer.parseInt(parts[0]);
	                int customerId = Integer.parseInt(parts[1]);
	                int restaurantId = Integer.parseInt(parts[2]);
	                double total = Double.parseDouble(parts[3]);
	                String status = parts[4];
	                String paymentStatus = parts[5];
	                String[] itemNames = parts[6].split("\\|");

	                List<MenuItem> items = new ArrayList<>();
	                for (String name : itemNames) {
	                    items.add(new MenuItem(0, name, 0.0, "")); // basic representation
	                }

	                Order order = new Order(orderId, customerId, restaurantId, items, total, status, paymentStatus);
	                orders.add(order);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("📁 No previous orders found.");
	    }
	}

	
	
	
	private void saveOrderToFile(Order order)
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true)))
	    {
	        StringBuilder itemsStr = new StringBuilder();
	        for (MenuItem item : order.getItems())
	        {
	            itemsStr.append(item.getName()).append("|");
	        }
	        if (itemsStr.length() > 0) itemsStr.deleteCharAt(itemsStr.length() - 1); // remove last |

	        String line = order.getOrderId() + "," +
	                      order.getCustomerId() + "," +
	                      order.getRestaurantId() + "," +
	                      order.getTotalAmount() + "," +
	                      order.getStatus() + "," +
	                      order.getPaymentStatus()+ "," +
	                      itemsStr.toString();

	        writer.write(line);
	        writer.newLine();
	    } catch (IOException e) {
	        System.out.println("❌ Error saving order to file.");
	    }
	}

	
	
	public void placeOrders(int custId,Restaurant rest,List<MenuItem> menuItems,
			    User user,String paymentMode)
	{
		
		int orderId = orders.size()+1;
		double total = 0.0;
		
		for(MenuItem item:menuItems)
		{
			total += item.getPrice();
		}
		
		String paymentStatus;
		if(paymentMode.equalsIgnoreCase("wallet"))
		{
			if(user.getWalletBalance() >= total)
			{
				user.setWalletBalance(user.getWalletBalance() - total);
				paymentStatus = "Paid";
	            System.out.println("✅ Paid ₹" + total + " using Wallet. Remaining Balance: ₹" + user.getWalletBalance());

			}else
			{
				System.out.println("Insuffiecient wallet balance :");
				return;
			}
		}else
		{
			paymentStatus = "Pending";
			System.out.println("🧾 Payment will be collected on delivery.");
		}
		
		
		Order order = new Order(orderId, custId, rest.getId(),menuItems, total,"PLACED",paymentStatus);
		orders.add(order);
		saveOrderToFile(order);
		System.out.println("Order placed :) " +orderId  +"Total :" +total);
		
		
	}
	
	
	public void viewOrdersByUser(int userId)
	{
		for(Order oo : orders)
		{
			if(oo.getCustomerId() == userId )
			{
				System.out.println(oo);
			}
		}
		
		
	}
	
	public void viewOrdersByCustomer(int custID)
	{
		boolean found = false;
		
		for(Order order : orders)
		{
			if(order.getCustomerId() == custID)
			{
				found = true;
				System.out.println(order);
			}
		}
		if(!found)
		{
			System.out.println("no order history found with customer id:");
		}
		
	}
	
	
	public void viewAllOrders()
	{
		if(orders.isEmpty())
		{
			System.out.println("no order found");
		}
		for(Order order:orders)
		{
			System.out.println(order);
		}	
	}
	
	
	public void updateOrderStatus(int orderId,String newStatus)
	{
		for(Order order:orders)
		{
			if(order.getOrderId() == orderId)
			{
				order.setStatus(newStatus);
				System.out.println("Order status updated to :" +newStatus);
				updateOrderInFile();
				return;
			}
		}
		System.out.println("Order not found!!!");
		
	}


	private void updateOrderInFile()
	{	
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt")))
		{
			for(Order order : orders)
			{
				StringBuilder itemStr = new StringBuilder();
				for(MenuItem menuItem : order.getItems())
				{
					itemStr.append(menuItem.getName()).append("|");
				}
				if(itemStr.length() > 0)
					 itemStr.deleteCharAt(itemStr.length() - 1);
				
				       String line = order.getOrderId()+ "," +
                        order.getCustomerId() + "," +
                        order.getRestaurantId() + "," +
                        order.getTotalAmount()+ "," +
                        order.getStatus() + "," +
                        order.getPaymentStatus() + "," +
                        itemStr.toString();

          writer.write(line);
          writer.newLine();
			}
			
		}catch(Exception io)
		{
			
			System.out.println("❌ Failed to update orders file.");
		}	
	}
	

}
