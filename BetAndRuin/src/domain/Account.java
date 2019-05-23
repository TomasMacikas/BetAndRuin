package domain;

import javax.persistence.*;

@Entity
public class Account {
	private String firstname;
	private String lastname;
	private String email;
	private String birthday;
	private Boolean isLogged;
	private Boolean isAdmin;
	private float walletFunds;
	@Id
	private String username;

	private String password;

	private String gender;

	public Account() {
		super();
	}
	
	public Account(String fname, String lname, String em, String d, String uname, String passw, String gend, int funds,
			Boolean logged, Boolean admin) {
		firstname = fname;
		lastname = lname;
		email = em;
		birthday = d;
		username = uname;
		password = passw;
		gender = gend;
		walletFunds = funds;
		isLogged = logged;
		isAdmin = admin;
	}

	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public float getWalletFunds() {
		return walletFunds;
	}

	public void setWalletFunds(float walletFunds) {
		this.walletFunds = walletFunds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void changeToLogout() {
		isLogged = false;
	}
}
