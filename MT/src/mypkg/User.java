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

}
