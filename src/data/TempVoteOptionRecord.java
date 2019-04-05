package data;

public class TempVoteOptionRecord {
	
	private String Description;
	private int voteCount;

	public TempVoteOptionRecord(String description) {
		Description = description;
		voteCount = 0;
	}
	public TempVoteOptionRecord(String description, int votecount) {
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
