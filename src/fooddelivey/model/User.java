package fooddelivey.model;



public class User 
{
	
	private int id;
	private String name;
	private String password;
	private String email;
	private String role;  //admin or customer
	
	
	public User(int id, String name, String email, String password, String role){
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ "]";
	}
	
	
	
	

}
