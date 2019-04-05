package logic;

public class Admin {
	private String adminID;
	private String adminUsername;
	private String adminPassword;
	
	public Admin(String adminID, String adminUsername, String adminPassword) {
		this.adminID = adminID;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminName) {
		this.adminUsername = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
