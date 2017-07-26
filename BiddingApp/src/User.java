
import java.sql.*;


public class User 
{
	private String name, username, password, securityQ, answer;
	private int credits, userID, contactNo;

	static int ID;

	public User()
	{
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID() {
		this.userID = ID;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public User(String name, String username, String password, String securityQ, String answer, int credits, int userID,
			int contactNo) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.securityQ = securityQ;
		this.answer = answer;
		this.credits = credits;
		this.userID = userID;
		this.contactNo = contactNo;
	}
	
	void addCredits(int credits)
	{
		this.credits += credits;
	}
}
