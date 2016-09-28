package mypkg;

import java.util.List;

public class user {
private String userid;	
private String username;
private String userpassword;
private boolean authenticated;

public user(String username, String userpassword)
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

public boolean verifyPassword() {
	QueryExecutor qe = new QueryExecutor();
	String userData[] = qe.returnUserData(this.username);
	if(userData!=null){
	String qePassword = userData[1];
	String qeId = userData[0];
	if (!qePassword.equals("") && this.userpassword.equals(qePassword)){
		this.setUserid(qeId);
		return true;
	}
	else{
		return false;
	}
	}
	else return false;
	}

public List<UsedLeave> getUsedLeaves(){
	QueryExecutor qe = new QueryExecutor();
	List<UsedLeave> usedLeaves = qe.returnUserUsedLeaves(this.username);
	return usedLeaves;
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
