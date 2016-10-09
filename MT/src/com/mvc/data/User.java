package com.mvc.data;

import java.util.List;

import com.mvc.dao.QueryExecutor;

public class User {
private String userid;	
private String firstname;
private String lastname;
private String useremail;
private int idcountry;
private String userpassword;
private boolean authenticated;

public User()
{
	this.useremail = "";
	this.userpassword = "";
	this.authenticated = false;
	this.firstname = "";
	this.lastname = "";
	this.idcountry = 0;
	this.userid = "";
}

public User(String useremail, String userpassword)
{
	this.useremail = useremail;
	this.userpassword = userpassword;
	this.authenticated = false;
}
public void setUserpassword(String userpassword) {
	this.userpassword = userpassword;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getUseremail() {
	return useremail;
}
public void setUseremail(String useremail) {
	this.useremail = useremail;
}
public String getUserpassword() {
	return userpassword;
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
public int getIdcountry() {
	return idcountry;
}
public void setIdcountry(int idcountry) {
	this.idcountry = idcountry;
}

}
