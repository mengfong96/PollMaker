package logic;

import java.util.ArrayList;

public class Member {
	private String memberID;
	private String memberUsername;
	private String memberEmail;
	private String memberFirstName;
	private String memberLastName;
	private String password;
	private ArrayList<Poll> polls = new ArrayList<Poll>();
	
	public Member(String memberID, String memberUsername, String memberEmail, String memberFirstName,
			String memberLastName, String password) {
		this.memberID = memberID;
		this.memberUsername = memberUsername;
		this.memberEmail = memberEmail;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.password = password;
	}
	
	public Member(String memberID, String memberUsername, String memberEmail, String memberFirstName,
			String memberLastName, String password, ArrayList<Poll> polls) {
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

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberFirstName() {
		return memberFirstName;
	}

	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	public String getMemberLastName() {
		return memberLastName;
	}

	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Poll> getAllPolls() {
		return polls;
	}

	public void addPolls(Poll pollID) {
		this.polls.add(pollID);
	}

	public boolean hasAnyPoll(){
		boolean status = false;
		if(polls.size() != 0)
			return status = true;
		return status;	
	}
	
	public void removePoll(String id){
		int index=0;
		for(int i=0; i<polls.size();i++){
			if(polls.get(i).getPollID().equals(id))
				index=i;
		}
		polls.remove(index);
		
	}
	
	public boolean pollOwned(String pollID){
		boolean owned = false;
		for(Poll temp: polls){
			if(temp.getPollID() == pollID)
				return owned = true;
		}
		return owned;
	}
}
