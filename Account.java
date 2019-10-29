
import java.io.Serializable;

public class Account implements Serializable {
	private int accID;
	private String username;
	private String password;
	private int acadYear;
	private String userEmail;


	public Account() {}
	public Account(int accID, String username, String password, int acadYear, String userEmail) {
		this.accID = accID;
		this.username = username;
		this.password = password;
		this.acadYear = acadYear;
		this.userEmail = userEmail;
	}

	public int getAccID() {
		return accID;
	}
	public void setAccID(int accID) {
		this.accID = accID;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public int getAcadYear() {
		return acadYear;
	}
	public void setAcadYear(int acadYear) {
		this.acadYear = acadYear;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return userEmail;
	}
	public void setEmail(String userEmail) {
		this.userEmail = userEmail;
	}



}
