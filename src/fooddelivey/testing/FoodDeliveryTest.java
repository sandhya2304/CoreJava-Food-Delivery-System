package fooddelivey.testing;

import java.util.Arrays;
import java.util.List;

import fooddelivey.model.MenuItem;
import fooddelivey.model.Order;
import fooddelivey.model.User;
import fooddelivey.service.AuthService;
import fooddelivey.service.OrderService;
import fooddelivey.service.RestaurantService;
import fooddelivey.service.ReviewService;

public class FoodDeliveryTest
{

	public static void main(String[] args) 
	{
		
		     AuthService authService = new AuthService();
	        RestaurantService restaurantService = new RestaurantService();
	        OrderService orderService  = new OrderService();
	        ReviewService reviewService = new ReviewService();
	        
	        
	     // TC-01: Register a new user
	        User u1 = authService.register("sandy", "sandy@gmail", "1234");
	        assert u1 != null : "❌ TC-01 Failed: User not registered.";
	        System.out.println("✅ TC-01 Passed: User registered.");
	        
	     // TC-02: Register with same email
	        User dup = authService.register("sandy", "sandy@gmail", "1234");
	        assert dup == null : "❌ TC-02 Failed: Duplicate email allowed.";
	        System.out.println("✅ TC-02 Passed: Duplicate email blocked.");
	        
	        // TC-03: Successful login
	        User loggedIn = authService.login("sandy@gmail","1234");
	        assert loggedIn != null : "❌ TC-03 Failed: Login failed.";
	        System.out.println("✅ TC-03 Passed: Login success.");
	        
	        
	     // TC-04: Wrong password
	        User failLogin = authService.login("sandy@gmail", "wrongpass");
	        assert failLogin == null : "❌ TC-04 Failed: Logged in with wrong password.";
	        System.out.println("✅ TC-04 Passed: Login failed as expected.");
	        
	        
	        // TC-05: Wallet top-up
	        double before = u1.getWalletBalance();
	        authService.topUpWallet(u1, 500.0);
	        assert u1.getWalletBalance() == before + 500 : "❌ TC-05 Failed: Wallet top-up incorrect.";
	        System.out.println("✅ TC-05 Passed: Wallet top-up successful.");   
	        

	        // TC-06: Add restaurant + place order
	        restaurantService.addRestaurant("Taco Bell", "Delhi");
	        restaurantService.addMenuItem(1, "Taco", 150.0, "Crispy");
	        List<MenuItem> items = restaurantService.getRestaurantById(1).getMenuItems();
	        orderService.placeOrders(u1.getId(), restaurantService.getRestaurantById(1),
	        		   Arrays.asList(items.get(0)),u1,"WALLET");

	        Order lastOrder = orderService.getAllOrders().get(orderService.getAllOrders().size() - 1);
	        assert lastOrder.getTotalAmount() == 150.0 : "❌ TC-06 Failed: Order total incorrect.";
	        System.out.println("✅ TC-06 Passed: Order placed correctly.");

	        // TC-07: Cancel order and refund
	        double balanceBeforeCancel = u1.getWalletBalance();
	        orderService.cancelOrder(lastOrder.getOrderId(), u1.getId(), u1, authService);
	        assert lastOrder.getStatus().equals("CANCELLED") : "❌ TC-07 Failed: Order not cancelled.";
	        assert u1.getWalletBalance() == balanceBeforeCancel + lastOrder.getTotalAmount() : "❌ TC-07 Failed: Refund failed.";
	        System.out.println("✅ TC-07 Passed: Order cancelled and wallet refunded.");
	        
	        

	}

}
