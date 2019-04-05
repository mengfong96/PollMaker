package data;

public class pendingList {
	private String pendingID;
	private String pendingUsername;
	private String pendingEmail;
	private String pendingFirstName;
	private String pendingLastName;
	private String password;
	private boolean account_status;
	
	public pendingList(String pendingID, String pendingUsername, String pendingEmail, String pendingFirstName,
			String pendingLastName, String password) {
		this.pendingID = pendingID;
		this.pendingUsername = pendingUsername;
		this.pendingEmail = pendingEmail;
		this.pendingFirstName = pendingFirstName;
		this.pendingLastName = pendingLastName;
		this.password = password;
		this.account_status = false;
	}
	public String getPendingID() {
		return pendingID;
	}
	public void setPendingID(String pendingID) {
		this.pendingID = pendingID;
	}
	public String getPendingUsername() {
		return pendingUsername;
	}
	public void setPendingUsername(String pendingUsername) {
		this.pendingUsername = pendingUsername;
	}
	public String getPendingEmail() {
		return pendingEmail;
	}
	public void setPendingEmail(String pendingEmail) {
		this.pendingEmail = pendingEmail;
	}
	public String getPendingFirstName() {
		return pendingFirstName;
	}
	public void setPendingFirstName(String pendingFirstName) {
		this.pendingFirstName = pendingFirstName;
	}
	public String getPendingLastName() {
		return pendingLastName;
	}
	public void setPendingLastName(String pendingLastName) {
		this.pendingLastName = pendingLastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getAccount_status() {
		return account_status;
	}
	public void ChangeAccount_status(boolean account_status) {
		this.account_status = account_status;
	}
	
}
