package com.training.bean;

public class NewUserBean {
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String webSite;
	private String password;
	private String role;

	public NewUserBean() {
	}

	public NewUserBean(String userName, String email, String firstName, String lastName, String webSite, String password, String role) {
		super();
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.webSite = webSite;
		this.password = password;
		this.role = role;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getwebSite() {
		return webSite;
	}

	public void setwebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "NewUserBean [userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", webSite=" + webSite + ", password=" + password + ", role=" + role + "]";
	}

}
