package fooddelivey.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fooddelivey.model.MenuItem;
import fooddelivey.model.Restaurant;
import fooddelivey.model.User;
import fooddelivey.service.AuthService;
import fooddelivey.service.OrderService;
import fooddelivey.service.RestaurantService;
import fooddelivey.service.ReviewService;

public class TestingFoodDelivery
{

	    public static void main(String[] args) 
	    {
	        Scanner sc = new Scanner(System.in);
	        AuthService authService = new AuthService();
	        RestaurantService restaurantService = new RestaurantService();
	        OrderService orderService  = new OrderService();
	        ReviewService reviewService = new ReviewService();
	        
	        
	        User currentUser = null;

	        while (true) 
	        {
	            System.out.println("\n🍔 Welcome to Food Delivery System");

	            if (currentUser == null) {
	                System.out.println("1. Register");
	                System.out.println("2. Login");
	                System.out.print("Choose option: ");
	                int choice = sc.nextInt();
	                sc.nextLine();

	                switch (choice) {
	                    case 1:
	                        System.out.print("Enter name: ");
	                        String name = sc.nextLine();
	                        System.out.print("Enter email: ");
	                        String email = sc.nextLine();
	                        System.out.print("Enter password: ");
	                        String password = sc.nextLine();
	                        authService.register(name, email, password);
	                        break;

	                    case 2:
	                        System.out.print("Enter email: ");
	                        String loginemail = sc.nextLine();
	                        System.out.print("Enter password: ");
	                        String loginpass = sc.nextLine();
	                        currentUser = authService.login(loginemail, loginpass);

	                        if (currentUser != null) {
	                            if (currentUser.getRole().equals("ADMIN")) {
	                                System.out.println("👨‍💼 Logged in as Admin");
	                            } else {
	                                System.out.println("👤 Logged in as Customer");
	                            }
	                        }
	                        break;

	                    default:
	                        System.out.println("⚠️ Invalid choice.");
	                }

	            } else if (currentUser.getRole().equals("ADMIN")) {
	                System.out.println("\n👨‍💼 Admin Menu");
	                System.out.println("3. Add Restaurant");
	                System.out.println("4. View All Restaurants");
	                System.out.println("5. Add Menu Item to Restaurant");
	                System.out.println("6. View Menu of a Restaurant");
	                System.out.println("7. View All Orders");
	                System.out.println("8. Update Order Status");
	                System.out.println("9. Logout");
	                System.out.println("10. View Reports");

	                System.out.print("Choose option: ");
	                int choice = sc.nextInt();
	                sc.nextLine();

	                switch (choice)
	                {
	                    case 3:
	                        System.out.println("Enter Restaurant name:");
	                        String restname = sc.nextLine();
	                        System.out.println("Enter Restaurant address:");
	                        String restAddress = sc.nextLine();
	                        restaurantService.addRestaurant(restname, restAddress);
	                        break;
	                    case 4:
	                        System.out.println("View All Restaurant:");
	                        restaurantService.viewAllRestaurant();
	                        break;
	                    case 5:
	                        System.out.println("View All Restaurant:");
	                        restaurantService.viewAllRestaurant();
	                        System.out.print("Enter restaurant ID: ");
	                        int rid = sc.nextInt();
	                        sc.nextLine();
	                        System.out.print("Enter item name: ");
	                        String itemName = sc.nextLine();
	                        System.out.print("Enter price: ");
	                        double price = sc.nextDouble();
	                        sc.nextLine();
	                        System.out.print("Enter description: ");
	                        String desc = sc.nextLine();
	                        restaurantService.addMenuItem(rid, itemName, price, desc);
	                        break;
	                    case 6:
	                        System.out.print("Enter restaurant ID: ");
	                        int viewId = sc.nextInt();
	                        sc.nextLine();
	                        restaurantService.viewMenuItems(viewId);
	                        break;
	                    case 7:
	                       orderService.viewAllOrders();
	                        break;
	                    case 8:
	                    	System.out.println("update order!");
	                    	orderService.viewAllOrders();

	                    	System.out.println("Enter order id to update:");
	                    	int orderId = sc.nextInt();
	                    	sc.nextLine(); // ✅ flush the newline

	                    	System.out.print("Enter new status (PREPARING/OUT_FOR_DELIVERY/DELIVERED): ");
	                    	String status = sc.nextLine().toUpperCase();

	                    	orderService.updateOrderStatus(orderId, status);
	                    	break;
	                    case 9:
	                        System.out.println("🔒 Logged out!");
	                        currentUser = null;
	                        break;
	                    case 10:
	                    	orderService.showAdminReport(restaurantService, authService);
	                    	break;
	                    default:
	                        System.out.println("⚠️ Invalid choice.");
	                }

	            } else {
	                System.out.println("\n👤 Customer Menu");
	                System.out.println("3. View All Restaurants");
	                System.out.println("4. View Menu of a Restaurant");
	                System.out.println("5. Place Order");
	                System.out.println("6. View My Orders");
	                System.out.println("7. Search Menu by Name");
	                System.out.println("8. Filter Menu by Price");
	                System.out.println("9. View Wallet Balance");
	                System.out.println("10. Top up your wallet");
	                System.out.println("11. Cancel My Order");
	                System.out.println("12. Rate and Review a Restaurant");
	                System.out.println("13. View Reviews of a Restaurant");
	                System.out.println("14. Search Restaurant by Name or Address");
	                System.out.println("15. Logout");

	                
	                System.out.print("Choose option: ");
	                int choice = sc.nextInt();
	                sc.nextLine();
	                
	                switch(choice)
	                {
	                case 3:
	                	System.out.println("View All Restaurants");
	                	restaurantService.viewAllRestaurant();
	                	break;
	                case 4:
	                	System.out.println("View menu of a restaurant :");
	                	System.out.println("Enter restaurant id: ");
	                	int restId = sc.nextInt();
	                	restaurantService.viewMenuItems(restId);
	                	break;
	                case 5:
	                	System.out.println("Place ORder :");
	                	restaurantService.viewAllRestaurant();
	                	System.out.println("Enter restaurant ID  you order from :");
	                	int restID = sc.nextInt();
	                	sc.nextLine();
	                	Restaurant rest = restaurantService.getRestaurantById(restID);
	                
	                	if(rest == null)
	                	{
	                		System.out.println("Invalid restaurant id");
	                		break;
	                	}
	                	
	                	List<MenuItem> menuItems = rest.getMenuItems();
	                	if(menuItems.isEmpty())
	                	{
	                		System.out.println("No items available :");
	                		break;
	                	}
	                	
	                	

                		// Show the list of food items with numbers (1, 2, 3...) 
                		//and their names, prices, and descriptions.
	                	System.out.println("Menu :");
	                	for(int i = 0;i < menuItems.size();i++)
	                	{
	                		
	                		System.out.println((i+1)+". "+menuItems.get(i));
	                	}
	                	
	                	List<MenuItem> selectedItems = new ArrayList<MenuItem>();
	                	while(true)
	                	{
	                		System.out.println("Enter item number to add to order (0 to finish)");
	                		int itemNum = sc.nextInt();
	                		sc.nextLine();
	                		
	                		if(itemNum == 0) break;
	                		if(itemNum < 1 || itemNum > menuItems.size())
	                		{
	                			System.out.println("Invalid item number :");
	                		}else
	                		{
	                			selectedItems.add(menuItems.get(itemNum - 1));
	                			System.out.println("menu items addess");
	                		}
	                		
	                		
	                	}
	                	
	                	
	                	if(!selectedItems.isEmpty())
	                	{
	                		System.out.println("Choose payment method : Wallet | COD ");
	                		String payment = sc.nextLine();
	                		orderService.placeOrders(currentUser.getId(),rest, selectedItems,currentUser,payment);
	                	}else
	                	{
	                		System.out.println("No items slecetced :");
	                	}
	                	
	                	break;
	                case 6:
	                    orderService.viewOrdersByCustomer(currentUser.getId());
	                    break;
	                case 7:
	                	restaurantService.viewAllRestaurant();
	                	
	                	System.out.println("Enter restaurant id to search in: ");
	                	int searchRestIdd = sc.nextInt();
	                	sc.nextLine();
	                	System.out.println("Enter keword of item");
	                	String keyword = sc.nextLine();
	                    restaurantService.searchMenuByName(searchRestIdd, keyword);
	                    break;
	                case 8:
	                	
	                	restaurantService.viewAllRestaurant();
	                	System.out.println("Enter restaurant id to filter: ");
	                	int filterRestId = sc.nextInt();
	                	sc.nextLine();
	                	System.out.println("Enter min price in double");
	                	double minPrice = sc.nextDouble();
	                	sc.nextLine();
	                	System.out.println("Enter max price in double");
	                	double maxPrice = sc.nextDouble();
	                	sc.nextLine();
	                	restaurantService.filterMenyByPrice(filterRestId, minPrice, maxPrice);
	                    break;
	                case 9:
	                    System.out.println("💰 Your wallet balance: ₹" + currentUser.getWalletBalance());
	                    break;
	                case 10:
	                	System.out.println("Enter amount to top up :");
	                	double amt =sc.nextDouble();
	                	sc.nextLine();
	                	authService.topUpWallet(currentUser, amt);
	                    System.out.println("✅ Wallet topped up! New Balance: ₹" + currentUser.getWalletBalance());
                        break;
                       	
	                case 11:
	                    orderService.viewOrdersByUser(currentUser.getId());
	                    System.out.println("Enter order id to cancel :");
	                    int cancelID = sc.nextInt();
	                    sc.nextLine();
	                    orderService.cancelOrder(cancelID, currentUser.getId(), currentUser,authService);
	                    break;	
	                case 12:
	                	restaurantService.viewAllRestaurant();
	                	System.out.println("Enter restId to rate :");
	                	int restRate = sc.nextInt();
	                	sc.nextLine();
	                	System.out.print("Enter rating (1-5): ");
	                   
	                	int rating = sc.nextInt();
	                    sc.nextLine();

	                    System.out.print("Write your comment: ");
	                    String comment = sc.nextLine();

	                    reviewService.addReview(currentUser.getId(), restRate, rating, comment); 
	                	
	                    break;
	                case 13:
	                	restaurantService.viewAllRestaurant();
	                    System.out.println("View revies of a restaurant :");
	                    int restToview = sc.nextInt();
	                    sc.nextLine();
	                    reviewService.printAllReview(restToview);
	                    break;
	                case 14:
	                	System.out.println("Serach Restaurant by name or address");
	                	String searchKeyword = sc.nextLine();
	                	restaurantService.searchRestaurants(searchKeyword);
	                	break;
	                case 15:
	                    currentUser = null;
	                    System.out.println("🔒 Logged out!");
	                    break;

	                default:
	                    System.out.println("⚠️ Invalid choice.");
	                }

	               
	            }
	        }
	    }
	}

		
	

