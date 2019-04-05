package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import data.DatabaseManager;
import data.pendingList;
import data.TempAdminRecord;
import data.TempMemberRecord;
import data.TempPollRecord;
import data.TempVoteOptionRecord;

public class System{
	private String name;
	private DatabaseManager db = new DatabaseManager();
	private Admin admin;
	private pendingMember pendinglist;
	
	public System(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isValidMember(String username, String password) {
		return db.verifyMember(username, password);
	}
	
	public Member getMember(String username, String password){
		TempMemberRecord memberlist = db.getMember(username, password);
		//Check if member owns any poll
		if(memberlist.hasAnyPoll()){
			ArrayList<Poll> data = new ArrayList<Poll>();
 			for(TempPollRecord poll: memberlist.getAllPolls()){
 				Poll temp = getPoll(poll.getPollID());
				data.add(temp);
			}
 			Member	member = new Member(memberlist.getMemberID(),memberlist.getMemberUsername(),memberlist.getMemberEmail(), memberlist.getMemberFirstName(),memberlist.getMemberLastName(),memberlist.getPassword(), data);
			return member;
		}else{
			Member	member = new Member(memberlist.getMemberID(),memberlist.getMemberUsername(),memberlist.getMemberEmail(), memberlist.getMemberFirstName(),memberlist.getMemberLastName(),memberlist.getPassword());
			return member;
		}
	}
	
	public boolean isValidAdmin(String username, String password) {
		return db.verifyAdmin(username, password);
	}

	public Admin getAdmin(String username, String password) {
		TempAdminRecord adminlist = db.getAdmin(username, password);
		admin = new Admin (adminlist.getAdminID(),adminlist.getAdminUsername(),adminlist.getAdminPassword());
		return admin;
	}
	//Manage Account
	public boolean ChangeAdminPassword(Admin admin, String password){
		TempAdminRecord adminList = db.getAdmin(admin.getAdminUsername(),admin.getAdminPassword());
		if(db.ChangeAdminPassword(adminList, password)){
			admin.setAdminPassword(password);
			return true;
		}
		return false;
	}
	
	public void AddToPendingList(String pendingUsername, String pendingEmail, String pendingFirstName, String pendingLastName, String password){
		db.AddToPendingList(pendingUsername, pendingEmail, pendingFirstName, pendingLastName, password);
	}
	
	public void AddToMemberRecord(String memberUsername, String Email, String FirstName, String LastName, String password){
		db.AddToTempMemberRecord(memberUsername, Email, FirstName, LastName, password);
	}

	public boolean verifyMemberEmail(String email) {
		return db.verifyMemberEmail(email);
	}

	public boolean verifyMemberUsername(String username) {
		return db.verifyMemberUsername(username);
	}
	
	public boolean verifyPendingEmail(String email) {
		return db.verifyPendingEmail(email);
	}

	public boolean verifyPendingUsername(String username) {
		return db.verifyPendingUsername(username);
	}
	
	public boolean isPendingforVerification(String username, String password) {
		return db.isPendingforVerification(username, password);
	}
	
	public pendingMember getPendingMember(int index){
		pendingList list = db.getPendingList(index);
		pendinglist = new pendingMember(list.getPendingID(),list.getPendingUsername(),list.getPendingEmail(),list.getPendingFirstName(),list.getPendingLastName(),list.getPassword());
		return pendinglist;
	}
	
	public ArrayList<pendingMember> getAllpendingMember(){
		ArrayList<pendingMember> pendingMemberList = new ArrayList<pendingMember>();
		ArrayList <pendingList> memberPendingList = db.getAllPendingMember();
		
		for(pendingList tempPendingMember : memberPendingList){
			pendingMember pendingmember = new pendingMember(tempPendingMember.getPendingID(), tempPendingMember.getPendingUsername(), tempPendingMember.getPendingEmail() ,tempPendingMember.getPendingFirstName(), tempPendingMember.getPendingLastName(), tempPendingMember.getPassword());
			pendingMemberList.add(pendingmember);
		}
		return pendingMemberList;
	}
	
	public void removeFromPendinglist(String Id){
		db.removeFromPendingList(Id);
	}
	//Member change password
	public boolean ChangeMemberPassword(Member member, String password){
		TempMemberRecord memberlist = db.getMember(member.getMemberUsername(),member.getPassword());
		if(db.ChangeMemberPassword(memberlist, password)){
			member.setPassword(password);
			return true;
		}
		return false;
	}
	//Member change email
	public boolean ChangeMemberEmail(Member member, String email){
		TempMemberRecord memberlist = db.getMember(member.getMemberUsername(),member.getPassword());
		if(db.ChangeMemberEmail(memberlist, email)){
			member.setMemberEmail(email);
			return true;
		}
		return false;
	}
	//Create poll 
	public void CreatePoll(String pendingPollTitle, String tempExpiredDate, String tempVoteLimit, ArrayList<String> tempPollOption, Member member){
		db.CreatePoll(pendingPollTitle, tempExpiredDate,tempVoteLimit, tempPollOption,member);
	}
	
	public String getPollID(){ 
			
			String a = db.getPollID(); 
			return a; 
		}
	
	public String getExpiredDate(){ 
	
			String a = db.getExpiredDate(); 
			return a; 
		}
	//Check if poll ID is valid
	public boolean isPollIDvalid(String id){
		return db.isPollIDvalid(id);
	}
	//Check if poll are expired
	public boolean isPollExpired(String expireDate) { 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
		LocalDateTime expiredDate = LocalDateTime.parse(expireDate, dtf);
		return LocalDateTime.now().isAfter(expiredDate);
	}
	//Pass data to logic
	private ArrayList<VotingOption> convertPoll(ArrayList<TempVoteOptionRecord> option){
		ArrayList<VotingOption> vote = new ArrayList<VotingOption>();
		for(TempVoteOptionRecord votes : option){
			VotingOption data = new VotingOption(votes.getDescription(), votes.getVoteCount());
			vote.add(data);
		}
		return vote;
	}
	//Get poll through ID
	 public Poll getPoll(String id){
		TempPollRecord tempPoll = db.getPoll(id);
		 
			if(tempPoll.getAllVoter().isEmpty()){
				Poll polls = new Poll(tempPoll.getPollID(), tempPoll.getPollTitle(), tempPoll.getExpiredDate(), tempPoll.getvoteLimit(),convertPoll(tempPoll.getAllVoteOptions()));
				return polls;
			}
			else {
				Poll polls = new Poll(tempPoll.getPollID(), tempPoll.getPollTitle(), tempPoll.getExpiredDate(), tempPoll.getvoteLimit(),convertPoll(tempPoll.getAllVoteOptions()), tempPoll.getAllVoter());
				return polls;
			}
	 }
	 //Remove poll from member
	 public void removePollfromMember(String MemberID, String PollID){
		 db.removePollfromMember(MemberID, PollID);		 
	 }
	 //Check if member has voted
	 public boolean memberHasVoted(String memberID, Poll poll){
		 return db. memberHasVoted(memberID, poll.getPollID());
	 }
	 //Add Vote count
	 public void addVote(Poll poll, VotingOption option){
		 db.addVote(poll.getPollID(), option.getDescription());
	 }
	 public int getAllVoteCount(Poll poll){
		 return db.getAllVoteCount(poll.getPollID());
	 }
	 //Add voter into the poll
	 public void recordVoter(Member member, Poll poll){
		 db.recordVoter(member.getMemberID(), poll.getPollID());
	 }
	 //Get all poll from db
	 public ArrayList<Poll> getAllPoll(){
		 ArrayList<TempPollRecord> list = db.getAllPoll();
		 ArrayList<Poll> polls = new ArrayList<Poll>();
		 
		 for(TempPollRecord temp : list){
			 Poll poll;
			//if(temp.getAllVoter().isEmpty()){
			//	poll = new Poll(temp.getPollID(), temp.getPollTitle(), temp.getExpiredDate(), temp.getvoteLimit(),convertPoll(temp.getAllVoteOptions()));
			//}
			//else {
				poll = new Poll(temp.getPollID(), temp.getPollTitle(), temp.getExpiredDate(), temp.getvoteLimit(),convertPoll(temp.getAllVoteOptions()), temp.getAllVoter());
			//}
			 polls.add(poll);
		 }
		 return polls; 
	 }
	 //Get poll where member voted
	 public ArrayList<Poll> getVotedPoll(String memberID){
		 ArrayList<String> pollID = db.getVotedPoll(memberID);
		 ArrayList<Poll> pollList = new  ArrayList<Poll>();
		 for(String ID: pollID){
			if(isPollExpired(getPoll(ID).getExpiredDate()) || getPoll(ID).getAllVoter().size() == getPoll(ID).getvoteLimit())
				 pollList.add(getPoll(ID));
		 }
		 return pollList;
	 }
	 //Get poll created by member 
	 public ArrayList<Poll> getMyPoll(String memberId){
		 ArrayList<TempPollRecord> poll = db.getMyPoll(memberId);
		 ArrayList<Poll> pollList = new  ArrayList<Poll>();
		 
		 for(TempPollRecord temp: poll){
			 Poll polls;
			 if(temp.getAllVoter().isEmpty()){
					 polls = new Poll(temp.getPollID(), temp.getPollTitle(), temp.getExpiredDate(), temp.getvoteLimit(),convertPoll(temp.getAllVoteOptions()));
			 }else {
					 polls = new Poll(temp.getPollID(), temp.getPollTitle(), temp.getExpiredDate(), temp.getvoteLimit(),convertPoll(temp.getAllVoteOptions()), temp.getAllVoter());
			 }	
			 pollList.add(polls);
		 }
		 return pollList;
	 }
	 
	

	
	
	
}
