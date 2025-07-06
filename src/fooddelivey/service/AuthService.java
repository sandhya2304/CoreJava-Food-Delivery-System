package fooddelivey.service;

import java.util.ArrayList;
import java.util.List;

import fooddelivey.model.User;



public class AuthService
{
	
	private List<User> users;
	private static final String ADMIN_EMAIL = "admin@foodapp.com";
    private static final String ADMIN_PASSWORD = "admin123";
	
	
	public AuthService()
	{
		this.users = new ArrayList<User>();
	}
	
	
	public List<User> getUsers() {
		return users;
	}
	
	public User register(String name,String email,String password)
	{
		int newId = users.size() + 1;
		User newUser = new User(newId, name,email, password,"CUSTOMER");
		users.add(newUser);
		
		System.out.println("Registration succeesfull :) ");
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

}
