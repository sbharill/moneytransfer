package com.mvc.tasks;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mvc.dao.QueryExecutor;
import com.mvc.data.Country;
import com.mvc.data.User;

public class SystemTasks {
	
	public List<Country> getCountries() {
		QueryExecutor qe = new QueryExecutor();
		List<Country> countries = qe.returnCountries();
		return countries;
		}
	public User getUserProfile(HttpServletRequest request) {
		QueryExecutor qe = new QueryExecutor();
		String userData[] = qe.returnUserProfile(request.getParameter("idprofile"));
		User user = new User();
		user.setUserid(userData[0]);
		user.setUseremail(userData[1]);
		user.setUserpassword(userData[2]);
		user.setFirstname(userData[3]);
		user.setLastname(userData[4]);
		user.setIdcountry(Integer.parseInt(userData[5]));
		return user;
		}	
	
}
