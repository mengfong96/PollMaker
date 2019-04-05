package data;

import java.util.ArrayList;

public class TempPollRecord {
	
	private String pollID;
	private String pollTitle;
	private String expiredDate; 
	private int voteLimit; 
	private ArrayList<String> voterID = new ArrayList<String>();
	private ArrayList<TempVoteOptionRecord> votes = new ArrayList<TempVoteOptionRecord>();
	
	public TempPollRecord(String pollID, String pollTitle, String expiredDate, int voteLimit, ArrayList<TempVoteOptionRecord> option) {
		this.pollID = pollID;
		this.expiredDate = expiredDate;
		this.pollTitle = pollTitle;
		this.voteLimit = voteLimit;
		this.votes = option;
	}
	
	public TempPollRecord(String pollID, String pollTitle,  String expiredDate, int voteLimit, ArrayList<TempVoteOptionRecord> option, ArrayList<String> voterID) {
		this.pollID = pollID;
		this.expiredDate = expiredDate;
		this.pollTitle = pollTitle;
		this.voteLimit = voteLimit;
		this.votes = option;
		this.voterID= voterID;
	}

	public String getPollID() {
		return pollID;
	}

	public String getPollTitle() {
		return pollTitle;
	}

	public ArrayList<String> getVoter() {
		//return voter;
		return voterID;
	}
	
	public String getExpiredDate(){ 
		return expiredDate; 
	}

	public ArrayList<TempVoteOptionRecord> getAllVoteOptions() {
		return votes;
	}
	public TempVoteOptionRecord getVoteOption(int index) {
		return votes.get(index);
	}
	
	public int getvoteLimit(){ 
		return voteLimit; 
		
	}
	public void voteLimit(int voteLimit) { 
		this.voteLimit = voteLimit; 
	}
	
	public boolean isPollExpired(){
		boolean status = false;
		
		return status;
	}
	
	public int getCurrentTotalVoteCount(){
		int totalVoteCount=0;
		for(TempVoteOptionRecord count: votes){
			totalVoteCount+=count.getVoteCount();
		}
		return totalVoteCount;
	}
	
	public void RecordVoter(String id) {
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
