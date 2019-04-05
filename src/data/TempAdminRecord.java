package data;

public class TempAdminRecord {
	private String adminID;
	private String adminUsername;
	private String adminPassword;
	
	public TempAdminRecord(String adminID, String adminUsername, String adminPassword) {
		this.adminID = adminID;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}

	public String getAdminID() {
		return adminID;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
	
	public void setAdminPassword(String adminPwd) {
		this.adminPassword = adminPwd;
	}
	
}
