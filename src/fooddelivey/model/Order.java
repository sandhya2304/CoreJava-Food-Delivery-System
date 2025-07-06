package fooddelivey.model;

import java.util.List;

public class Order
{
	
	    private int orderId;
	    private int customerId;
	    private int restaurantId;
	    private List<MenuItem> items;
	    private double totalAmount;
	    private String status; // e.g., "PLACED", "DELIVERED"
		
	    
	    public Order(int orderId, int customerId, int restaurantId, List<MenuItem> items, double totalAmount,
				String status) {
			super();
			this.orderId = orderId;
			this.customerId = customerId;
			this.restaurantId = restaurantId;
			this.items = items;
			this.totalAmount = totalAmount;
			this.status = status;
		}


		public int getOrderId() {
			return orderId;
		}


		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}


		public int getCustomerId() {
			return customerId;
		}


		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}


		public int getRestaurantId() {
			return restaurantId;
		}


		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}


		public List<MenuItem> getItems() {
			return items;
		}


		public void setItems(List<MenuItem> items) {
			this.items = items;
		}


		public double getTotalAmount() {
			return totalAmount;
		}


		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		@Override
		public String toString() {
			return "Order [orderId=" + orderId + ", totalAmount=" + totalAmount + ", status=" + status + "]";
		}
	    
	    
	    
	    
	    

}
