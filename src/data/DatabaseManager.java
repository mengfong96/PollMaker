package data;

import java.util.ArrayList;

import logic.Member;

public class DatabaseManager {
	
	private ArrayList<TempMemberRecord> memberRegister= new ArrayList<TempMemberRecord>();
	private TempAdminRecord admin;
	private ArrayList<pendingList> list = new ArrayList<pendingList>();
	private ArrayList<TempPollRecord> pollList = new ArrayList<TempPollRecord>();
	
	private String pollID4form = ""; 
	private String expiredDateForm = ""; 
	
	//Default setup everytime run program
	public DatabaseManager(){
	
		admin = new TempAdminRecord("A101", "Weeb", "123456");
		TempMemberRecord member = new TempMemberRecord("A101","Victor","lehv001@gmail.com","Victor","Lau","123");
		TempMemberRecord member1 = new TempMemberRecord("A102","MF","lehv002@gmail.com","MF","Lee","123");
		TempMemberRecord member2 = new TempMemberRecord("A103","MFC","lehv003@gmail.com","MF","Lee","123");
		memberRegister.add(member);
		memberRegister.add(member1);
		memberRegister.add(member2);
		
		pendingList pending1 = new pendingList("M101", "Victor1" , "lehv003@gmail.com", "Victor", "Lau Eng Howe", "123");
		pendingList pending2 = new pendingList("M102", "UserABC", "abc@ukm.my","Alvin", "Lau", "abc");
		pendingList pending3 = new pendingList("M103", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pendingA = new pendingList("M104", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending4 = new pendingList("M105", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending5 = new pendingList("M106", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending6 = new pendingList("M107", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending7 = new pendingList("M108", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending8 = new pendingList("M109", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending9 = new pendingList("M110", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending10 = new pendingList("M111", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending11 = new pendingList("M112", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending12 = new pendingList("M113", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending13 = new pendingList("M114", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending14 = new pendingList("M115", "User123", "123@ukm.my","Alice", "Chin", "123");
		pendingList pending15 = new pendingList("M116", "User123", "123@ukm.my","Alice", "Chin", "123");
		
		list.add(pending1);
		list.add(pending2);
		list.add(pending3);
		list.add(pendingA);
		list.add(pending4);
		list.add(pending5);
		list.add(pending6);
		list.add(pending7);
		list.add(pending8);
		list.add(pending9);
		list.add(pending10);
		list.add(pending11);
		list.add(pending12);
		list.add(pending13);
		list.add(pending14);
		list.add(pending15);
		
		ArrayList<TempVoteOptionRecord> options = new ArrayList<TempVoteOptionRecord>(); 
		options.add(new TempVoteOptionRecord("Yes"));
		options.add(new TempVoteOptionRecord("No"));
		for(int i=0; i<16;i++){
			options.add(new TempVoteOptionRecord(String.valueOf(i)));
		}
		TempPollRecord poll2 = new TempPollRecord("P101","Default Question","25-12-2017 17:05",50, options);
		poll2.RecordVoter(member2.getMemberID());
		pollList.add(poll2);
		member.addPolls(poll2);
		
		TempPollRecord poll3 = new TempPollRecord("P102","Do you think I am handosme? or Am I ugly? or am I pretty? or am I nothing?","25-12-2017 12:40",50, options);
		poll3.RecordVoter(member1.getMemberID());
		pollList.add(poll3);
		member.addPolls(poll3);
		
		TempPollRecord poll4 = new TempPollRecord("P103","Testing Question 2","25-12-2017 12:40",50, options);
		poll4.RecordVoter(member1.getMemberID());
		pollList.add(poll4);
		member.addPolls(poll4);
		
		TempPollRecord poll5 = new TempPollRecord("P104","Testing Question 3","25-12-2017 12:40",50, options);
		poll5.RecordVoter(member2.getMemberID());
		pollList.add(poll5);
		member.addPolls(poll5);
		
		TempPollRecord poll6 = new TempPollRecord("P105","Testing Question 4","25-12-2017 12:40",50, options);
		pollList.add(poll6);
		member.addPolls(poll6);
		
		TempPollRecord poll7 = new TempPollRecord("P106","Testing Question 5","25-12-2017 12:40",50, options);
		pollList.add(poll7);
		member.addPolls(poll7);
		
		TempPollRecord poll8 = new TempPollRecord("P107","Testing Question 6","25-12-2017 12:40",50, options);
		pollList.add(poll8);
		member.addPolls(poll8);
		
		TempPollRecord poll9 = new TempPollRecord("P108","Testing Question 7","25-12-2017 12:40",50, options);
		pollList.add(poll9);
		member.addPolls(poll9);
		
	}
	//registration
	public void AddToPendingList(String pendingUsername, String pendingEmail, String pendingFirstName, String pendingLastName, String password){
		String pendingID;
		if(list.isEmpty())
			pendingID = "M101";
		else{
			String index = list.get(list.size()-1).getPendingID();
			int order = 101+Integer.valueOf(index.substring(2,4));
			pendingID = "M" + Integer.toString(order);
		}
		
		pendingList pending2  = new pendingList(pendingID, pendingUsername, pendingEmail, pendingFirstName, pendingLastName, password);
		list.add( pending2 );
	}
	//Create new Member from pendinglist
	public void AddToTempMemberRecord(String memberUsername, String Email, String FirstName, String LastName, String password){
		String memberID;
		if(memberRegister.isEmpty())
			memberID = "A101";
		else{
			int order = 101+memberRegister.size();
			memberID = "A" + Integer.toString(order);
		}
		TempMemberRecord memberRecord  = new TempMemberRecord(memberID, memberUsername, Email, FirstName, LastName, password);
		memberRegister.add(memberRecord);
	}
	//Get pending account
	public pendingList getPendingList(int index){
		return list.get(index);
	}
	
	public pendingList getPendingRequest(String id) {
		for (pendingList temp: list) {
			if (temp.getPendingID().equals(id))
				return temp;
		}
		return null;
	}
	//remove from list
	public void removeFromPendingList(String id){
		pendingList temp = getPendingRequest(id);
		list.remove(temp);
	}
	//Get all pending request
	public ArrayList<pendingList> getAllPendingMember(){
		return list;
	}
	//check if email was used
	public boolean verifyPendingEmail(String email){
		for (pendingList pending: list) {
			if (pending.getPendingEmail().equals(email))
				return true;
		}
		return false;
	}
	public boolean verifyMemberEmail(String email){
		for (TempMemberRecord member: memberRegister) {
			if (member.getMemberEmail().equals(email))
				return true;
		}
		return false;
	}
	//check if username was used
	public boolean verifyPendingUsername(String Username){
		for (pendingList pending: list) {
			if (pending.getPendingUsername().equals(Username))
				return true;
		}
		return false;
	}
	public boolean verifyMemberUsername(String Username){
		for (TempMemberRecord member: memberRegister) {
			if (member.getMemberUsername().equals(Username))
				return true;
		}
		return false;
	}
	//member log in
	public boolean verifyMember(String username, String password) {
		for (TempMemberRecord member: memberRegister) {
			if (member.getMemberUsername().equals(username) && member.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	public ArrayList<TempMemberRecord> getAllMember() {
		return memberRegister;
	}
	
	public TempMemberRecord getMember(String username, String password) {
		for (TempMemberRecord member: memberRegister) {
			if (member.getMemberUsername().equals(username) && member.getPassword().equals(password))
				return member;
		}
		return null;
	}
	
	public TempMemberRecord getMember(String id) {
		for (TempMemberRecord member: memberRegister) {
			if (member.getMemberID().equals(id))
				return member;
		}
		return null;
	}
	//Member change password
	public boolean ChangeMemberPassword(TempMemberRecord member, String password){
		boolean status = false;
		for(int i=0; i<memberRegister.size();i++){
			if(memberRegister.get(i).equals(member)){
				memberRegister.get(i).setPassword(password);
				return status=true;
			}
		}
		return status;
	}
	//Member change email
	public boolean ChangeMemberEmail(TempMemberRecord member, String email){
		boolean status = false;
		for(int i=0; i<memberRegister.size();i++){
			if(memberRegister.get(i).equals(member)){
				memberRegister.get(i).setEmail(email);
				return status=true;
			}
		}
		return status;
	}
	//Check if username and password entered is still in pending list 
	public boolean isPendingforVerification(String username, String password) {
		for (pendingList pending: list) {
			if (pending.getPendingUsername().equals(username) && pending.getPassword().equals(password))
				if(!pending.getAccount_status())
					return true;
		}
		return false;
	}
	//Admin log in
	public boolean verifyAdmin(String username, String password) {
		if (admin.getAdminUsername().equals(username) && admin.getAdminPassword().equals(password))
			return true;
		return false;
	}
	
	public TempAdminRecord getAdmin(String username, String password) {
		if (admin.getAdminUsername().equals(username) && admin.getAdminPassword().equals(password))
			return admin;
	return null;
	}
	
	public boolean ChangeAdminPassword(TempAdminRecord admin1, String password){
		boolean status = false;
			admin.setAdminPassword(password);
			status=true;
		return status;
	}
	//Create New Poll and Add poll into member class
	public void CreatePoll(String tempPollTitle, String tempExpiredDate, String tempVoteLimit, ArrayList<String> option, Member member){ 
		String tempPollID; 
		ArrayList<TempVoteOptionRecord> votes = new ArrayList<TempVoteOptionRecord>(); 
		
		if(pollList.isEmpty())
			tempPollID = "P101";
		else{
			String index = pollList.get(pollList.size()-1).getPollID();
			int order =101 + Integer.valueOf(index.substring(2,4));
			tempPollID = "P" + Integer.toString(order);
		}
		
		for(String vote : option) { 
			TempVoteOptionRecord record = new TempVoteOptionRecord(vote); 
			votes.add(record); 
		}
		
		TempPollRecord poll2 = new TempPollRecord(tempPollID, tempPollTitle,tempExpiredDate, Integer.parseInt(tempVoteLimit), votes);
		pollList.add(poll2);
		
		//Add poll into member class
		TempMemberRecord temp = getMember(member.getMemberUsername(),member.getPassword());
		temp.addPolls(poll2);
		
		pollID4form = tempPollID; 
		expiredDateForm = tempExpiredDate; 
	}
	
	public ArrayList<TempPollRecord> getAllPoll(){
		return pollList;
	}
	
	public TempPollRecord getPoll(String pollID){
		for(TempPollRecord temp : pollList){
			if(temp.getPollID().equals(pollID))
				return temp;
		}
		return null;
	}
	
	public boolean isPollIDvalid(String id){
		for (TempPollRecord poll: pollList) {
			if (poll.getPollID().equals(id))
				return true;
		}
		return false;
	}
	public String getPollID(){ 
			
			return pollID4form; 
	}
		
	public String getExpiredDate(){ 
			
			return expiredDateForm; 
	}
	
	public void addVote(String id, String description){
	
		for(int j=0; j<pollList.size();j++){
			if(pollList.get(j).getPollID() == id){
				
				for(int i=0; i<pollList.get(j).getAllVoteOptions().size();i++){
					TempVoteOptionRecord temp = pollList.get(j).getAllVoteOptions().get(i);
					String title = temp.getDescription();
					if(title.equals(description))
						pollList.get(j).getAllVoteOptions().get(i).addVoteCount();
				}
			}
		}
	}
	
	public int getAllVoteCount(String id){
		TempPollRecord temp = getPoll(id);
		return temp.getCurrentTotalVoteCount();
	}
	
	public void recordVoter(String memberID, String pollID){
		//TempMemberRecord tempMember = getMember(memberID);
		TempPollRecord tempPoll =  getPoll(pollID);
		for(int i=0; i<pollList.size(); i++){
			if(pollList.get(i).equals(tempPoll))
				pollList.get(i).RecordVoter(memberID);
		}
		
	}
	
	public boolean memberHasVoted(String memberID, String pollID){
		TempPollRecord tempPoll = getPoll(pollID);
		boolean status = false;
		for(int i=0; i<pollList.size();i++){
			if(pollList.get(i).equals(tempPoll)){
				for(int j=0; j<pollList.get(i).getVoter().size(); j++){
					if(pollList.get(i).getVoter().get(j) == memberID){
						status = true;
					}
				}
			}
		}
		return status;
	}
	
	public ArrayList<String> getVotedPoll(String memberID){
		ArrayList<String> tempID = new ArrayList<String>();
		for(TempPollRecord temp: pollList){
			if(memberHasVoted(memberID, temp.getPollID()))
				tempID.add(temp.getPollID());	
		}
		return tempID;
	}
	
	public ArrayList<TempPollRecord> getMyPoll(String memberID){
		ArrayList<TempPollRecord> tempID = new ArrayList<TempPollRecord>();
		TempMemberRecord member = getMember(memberID);
		ArrayList<TempPollRecord> tempPoll = member.getAllPolls();
		for(TempPollRecord temp: tempPoll){
			tempID.add(temp);
		}
		return tempID;
	}
	
	public void removePollfromMember(String memberId, String pollId){
		TempMemberRecord member = getMember(memberId);
		TempPollRecord temp = getPoll(pollId);
		for(int i=0; i<memberRegister.size(); i++){
			if(memberRegister.get(i).equals(member)){
				memberRegister.get(i).removePoll(pollId);
			}
		}
		pollList.remove(temp);
	}
	
}
