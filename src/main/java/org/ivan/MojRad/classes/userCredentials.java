package org.ivan.MojRad.classes;

public class userCredentials {

	private String email;
	private String password;
	
	

	public userCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	public userCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
