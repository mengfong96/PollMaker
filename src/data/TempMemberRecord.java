package data;

import java.util.ArrayList;

public class TempMemberRecord {
	
	private String memberID;
	private String memberUsername;
	private String memberEmail;
	private String memberFirstName;
	private String memberLastName;
	private String password;
	private ArrayList<TempPollRecord> polls = new ArrayList<TempPollRecord>();
	
	public TempMemberRecord(String memberID, String memberUsername, String memberEmail, String memberFirstName,
			String memberLastName, String password) {
		this.memberID = memberID;
		this.memberUsername = memberUsername;
		this.memberEmail = memberEmail;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.password = password;
		
	}
	public TempMemberRecord(String memberID, String memberUsername, String memberEmail, String memberFirstName,
			String memberLastName, String password, ArrayList<TempPollRecord> polls) {
		this.memberID = memberID;
		this.memberUsername = memberUsername;
		this.memberEmail = memberEmail;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.password = password;
		this.polls = polls;
	}
	public String getMemberID() {
		return memberID;
	}
	public String getMemberUsername() {
		return memberUsername;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.memberEmail = email;
	}
	public ArrayList<TempPollRecord> getAllPolls() {
		return polls;
	}

	public void addPolls(TempPollRecord poll) {
		this.polls.add(poll);
	}

	public boolean hasAnyPoll(){
		boolean status = true;
		if(polls.isEmpty())
			return status = false;
		return status;	
	}
	
	public void removePoll(String id){
		
		for(int i=0; i<polls.size();i++){
			if(polls.get(i).getPollID().equals(id)){
				polls.remove(i);
			}
			
		}
		
	}
	
	public boolean pollOwned(String pollID){
		boolean owned = false;
		for(TempPollRecord temp: polls){
			if(temp.getPollID().equals(pollID))
				return owned = true;
		}
		return owned;
	}
}
