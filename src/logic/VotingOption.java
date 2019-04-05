package logic;

public class VotingOption {
	private String Description;
	private int voteCount;

	public VotingOption(String description) {
		Description = description;
		voteCount = 0;
	}
	
	public VotingOption(String description, int votecount) {
		Description = description;
		voteCount = votecount;
	}
	
	public void addVoteCount(){
		voteCount+=1;
	}
	public int getVoteCount(){
		return voteCount;
	}
	public String getDescription(){
		return Description;
	}
}
