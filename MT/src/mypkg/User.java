package mypkg;

import java.util.List;

public class User {
private String userid;	
private String username;
private String userpassword;
private boolean authenticated;

public User(String username, String userpassword)
{
	this.username = username;
	this.userpassword = userpassword;
	this.authenticated = false;
}
public void setUserpassword(String userpassword) {
	this.userpassword = userpassword;
}
public String getUserpassword() {
	return userpassword;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUsername() {
	return username;
}
public void setAuthenticated(boolean authenticated) {
	this.authenticated = authenticated;
}
public boolean isAuthenticated() {
	return authenticated;
}

public void setUserid(String userid) {
	this.userid = userid;
}
public String getUserid() {
	return userid;
}

public List<UsedLeave> getUsedLeaves(){
	QueryExecutor qe = new QueryExecutor();
	List<UsedLeave> usedLeaves = qe.returnUserUsedLeaves(this.username);
	return usedLeaves;
}

public List<SubmittedMT> getSubmittedMTs(){
	QueryExecutor qe = new QueryExecutor();
	List<SubmittedMT> submittedMTs = qe.returnUserSubmittedMTs(this.username);
	return submittedMTs;
}

public List<MatchedMT> getMatchedMTs(){
	QueryExecutor qe = new QueryExecutor();
	List<MatchedMT> matchedMTs = qe.returnUserMatchedMTs(this.username);
	return matchedMTs;
}

public List<UnUsedLeave> getUnUsedLeaves(){
	QueryExecutor qe = new QueryExecutor();
	List<UnUsedLeave> unUsedLeaves = qe.returnUserUnUsedLeaves(this.username);
	return unUsedLeaves;
}

public List<ApproveLeave> getApproveLeaves(){
	QueryExecutor qe = new QueryExecutor();
	List<ApproveLeave> approveLeaves = qe.returnUserApproveLeaves(this.username);
	return approveLeaves;
}

public boolean submitLeaves(UsedLeave usedLeaves){
	QueryExecutor qe = new QueryExecutor();
	qe.submitUserLeaves(usedLeaves);
	return true;
}

public boolean updateUserLeavesBalance(UnUsedLeave unUsedLeaves, int latestBalance){
	QueryExecutor qe = new QueryExecutor();
	qe.updateUserLeavesBalance(unUsedLeaves, latestBalance);
	return true;
}

public boolean approveLeaves(String[] idLeaves){
	QueryExecutor qe = new QueryExecutor();
	qe.approveUserLeaves(idLeaves);
	return true;
}

public boolean rejectLeaves(String[] idLeaves){
	QueryExecutor qe = new QueryExecutor();
	qe.rejectUserLeaves(idLeaves);
	return true;
}

public boolean updateUserRejectedLeavesBalance(ApproveLeave approveLeaves, int latestBalance){
	QueryExecutor qe = new QueryExecutor();
	qe.updateUserRejectedLeavesBalance(approveLeaves, latestBalance);
	return true;
}

public int getLeavesBalance(ApproveLeave approveLeaves){
	QueryExecutor qe = new QueryExecutor();
	return qe.getUserLeavesBalance(approveLeaves);
}

}
