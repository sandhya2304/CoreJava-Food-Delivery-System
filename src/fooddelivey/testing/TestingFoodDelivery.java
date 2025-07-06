package fooddelivey.testing;

import java.util.Scanner;

import fooddelivey.model.Restaurant;
import fooddelivey.model.User;
import fooddelivey.service.AuthService;
import fooddelivey.service.RestaurantService;

public class TestingFoodDelivery
{

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        AuthService authService = new AuthService();
	        RestaurantService restaurantService = new RestaurantService();
	        User currentUser = null;

	        while (true) {
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
	                System.out.println("7. Logout");
	                System.out.print("Choose option: ");
	                int choice = sc.nextInt();
	                sc.nextLine();

	                switch (choice) {
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
	                        System.out.println("🔒 Logged out!");
	                        currentUser = null;
	                        break;
	                    default:
	                        System.out.println("⚠️ Invalid choice.");
	                }

	            } else {
	                System.out.println("\n👤 Customer Menu");
	                System.out.println("7. Logout");
	                System.out.print("Choose option: ");
	                int choice = sc.nextInt();
	                sc.nextLine();

	                if (choice == 7) {
	                    currentUser = null;
	                    System.out.println("🔒 Logged out!");
	                } else {
	                    System.out.println("⚠️ Invalid choice.");
	                }
	            }
	        }
	    }
	}

		
	

