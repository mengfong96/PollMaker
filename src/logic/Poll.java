package logic;

import java.util.ArrayList;

public class Poll {
	private String pollID;
	private String pollTitle;
	private String expiredDate; 
	private int voteLimit; 
	private ArrayList<String> voterID = new ArrayList<String>();
	private ArrayList<VotingOption> votes;
	
	public Poll(String pollID, String pollTitle, String expiredDate, int voteLimit, ArrayList<VotingOption> option) {
		this.pollID = pollID;
		this.expiredDate = expiredDate;
		this.pollTitle = pollTitle;
		this.voteLimit = voteLimit;
		this.votes = option;
	}
	
	public Poll(String pollID, String pollTitle,  String expiredDate, int voteLimit, ArrayList<VotingOption> option, ArrayList<String> voterID) {
		this.pollID = pollID;
		this.expiredDate = expiredDate;
		this.pollTitle = pollTitle;
		this.voteLimit = voteLimit;
		this.votes = option;
		this.voterID = voterID;
	}

	public String getPollID() {
		return pollID;
	}

	public void setPollID(String pollID) {
		this.pollID = pollID;
	}

	public String getExpiredDate(){ 
		return expiredDate; 
	}

	public String getPollTitle() {
		return pollTitle;
	}
	
	public int getvoteLimit(){ 
		return voteLimit; 
		
	}
	public void voteLimit(int voteLimit) { 
		this.voteLimit = voteLimit; 
	}

	public void setPollTitle(String pollTitle) {
		this.pollTitle = pollTitle;
	}
	
	public ArrayList<VotingOption> getAllVoteOptions(){
		return votes;
	}
	
	public int getCurrentTotalVoteCount(){
		int totalVoteCount=0;
		for(VotingOption count: votes){
			totalVoteCount+=count.getVoteCount();
		}
		return totalVoteCount;
	}

	public void RecordVoter(String id) {
		//this.voter.add(voter);
		this.voterID.add(id);
	}
	
	public boolean hasVoted(String id){
		for(String temp: voterID){
			if(temp.equals(id))
				return true;
		}
		return false;
	}
	
	public ArrayList<String> getAllVoter(){
		return voterID;
	}
	
	
	
	
	
}
