package fooddelivey.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.Restaurant;
import fooddelivey.model.User;



public class AuthService
{
	
	private List<User> users;
	private static final String ADMIN_EMAIL = "admin@foodapp.com";
    private static final String ADMIN_PASSWORD = "admin123";
	
	
	public AuthService()
	{
		this.users = new ArrayList<User>();
		loadUsersFromFile();
	}
	
	
	public void loadUsersFromFile() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length != 6) {
	                System.out.println("⚠️ Skipping malformed user line: " + line);
	                continue;
	            }

	            try {
	                int id = Integer.parseInt(parts[0]);
	                String name = parts[1];
	                String email = parts[2];
	                String password = parts[3];
	                double wallet = Double.parseDouble(parts[4]);
	                String role = parts[5];

	                User user = new User(id, name, email, password, role);
	                user.setWalletBalance(wallet);
	                users.add(user);
	            } catch (Exception ex) {
	                System.out.println("❌ Error parsing user line: " + line);
	            }
	        }
	    } catch (IOException ie) {
	        System.out.println("📁 No existing users file found. Starting fresh.");
	    }
	}

	
	
	private void saveUserToFile(User user)
	{
		
	  try(BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt",true)))
		{
		    String line = user.getId() +","+user.getName()+"," +user.getEmail()
		            +","+user.getPassword()+","+user.getWalletBalance()+","+user.getRole();
		  
		    writer.write(line);
			writer.newLine();
			
			
		}catch(Exception io)
		{
			
			System.out.println("❌ Error saving user to file.");
	    }
		
		
	}
	
	public boolean isUserIdExists(int id) {
	    return users.stream().anyMatch(u -> u.getId() == id);
	}

	
	

	public User register(String name, String email, String password) {
	    // Check if email already exists
	    boolean emailExists = users.stream()
	        .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
	    if (emailExists) {
	        System.out.println("⚠️ Email already registered. Try logging in.");
	        return null;
	    }

	    // Generate new ID (auto-increment, even if some users were deleted before)
	    int newId = users.stream()
	        .mapToInt(User::getId)
	        .max()
	        .orElse(0) + 1;

	    // Create user with default wallet balance of 0.0
	    User newUser = new User(newId, name, email, password, "CUSTOMER");
	    newUser.setWalletBalance(0.0);

	    // Save to memory and file
	    users.add(newUser);
	    saveUserToFile(newUser);

	    System.out.println("✅ Registration successful for: " + name);
	    return newUser;
	}

	
	public User login(String email,String password)
	{
		 // Admin login
        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            return new User(0, "Admin", ADMIN_EMAIL, ADMIN_PASSWORD, "ADMIN");
        }

        // Customer login
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("✅ Login successful!");
                return user;
            }
        }
		
		System.out.println("Credentials invalid :(");
		return null;
	}
	
	
	public void updateUsersFile() 
	{
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) 
	    {
	        for (User user : users) {
	            String line = user.getId() + "," +
	                          user.getName() + "," +
	                          user.getEmail() + "," +
	                          user.getPassword() + "," +
	                          user.getRole() + "," +
	                          user.getWalletBalance();
	            writer.write(line);
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        System.out.println("❌ Error updating users file.");
	    }
	}

	
	public void topUpWallet(User user,double amt)
	{
		if(amt <= 0)
		{
			System.out.println("Invalid amt:");
			return;
		}
		user.setWalletBalance(user.getWalletBalance() + amt);
	    System.out.println("✅ Wallet topped up successfully! New Balance: ₹" + user.getWalletBalance());
        updateUsersFile();
		
	}
	


	public List<User> getUsers() {
		return users;
	}


	public User getUserById(int userId) 
	{
		for(User user : users)
		{
			if(user.getId() == userId)
			{
				return user;
			}
		}
		return null;
	}
	

}
