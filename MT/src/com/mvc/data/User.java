package com.mvc.data;

import java.util.List;

import com.mvc.dao.QueryExecutor;

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

}
