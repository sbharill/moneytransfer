package com.mvc.tasks;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mvc.dao.QueryExecutor;
import com.mvc.data.MT;
import com.mvc.data.User;

public class UserTasks {
	public User verifyPassword(User usr) {
		QueryExecutor qe = new QueryExecutor();
		String userData[] = qe.returnUserData(usr.getUsername());
		if(userData!=null){
		String qePassword = userData[1];
		String qeId = userData[0];
		if (!qePassword.equals("") && usr.getUserpassword().equals(qePassword)){
			usr.setUserid(qeId);
			usr.setAuthenticated(true);
			return usr;
		}
		else{
			return null;
		}
		}
		else return null;
		}

	public List<MT> getSubmittedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MT> submittedMTs = qe.returnUserSubmittedMTs(usr.getUsername());
		return submittedMTs;
	}

	public List<MT> getMatchedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MT> matchedMTs = qe.returnUserMatchedMTs(usr.getUsername());
		return matchedMTs;
	}

	public List<MT> getInterestShownMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MT> interestShownMTs = qe.returnUserInterestShownMTs(usr.getUsername());
		return interestShownMTs;
	}
	
	public List<MT> getInterestReceivedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MT> interestReceivedMTs = qe.returnUserInterestReceivedMTs(usr.getUsername());
		return interestReceivedMTs;
	}	

	public boolean signUp(HttpServletRequest request) {
		String signUpData[] = new String[5];
		signUpData[0] = request.getParameter("firstname");
		signUpData[1] = request.getParameter("lastname");
		signUpData[2] = request.getParameter("email");
		signUpData[3] = request.getParameter("password");
		signUpData[4] = request.getParameter("retypepassword");
		
		if (!signUpData[3].equals(signUpData[4])) return false;
		
		QueryExecutor qe = new QueryExecutor();
		boolean success = qe.submitSignUpData(signUpData);
		return success;
	
	}

	public boolean createMT(HttpServletRequest request) {
		String createMTData[] = new String[5];
		createMTData[0] = request.getParameter("amount");
		createMTData[1] = request.getParameter("fromCountry");
		createMTData[2] = request.getParameter("toCountry");
		createMTData[3] = request.getParameter("beforeDate");
		createMTData[4] = request.getParameter("userId");		
		
		QueryExecutor qe = new QueryExecutor();
		boolean success = qe.submitCreateMTData(createMTData);
		return success;
	
	}
}
