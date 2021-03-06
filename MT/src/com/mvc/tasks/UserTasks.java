package com.mvc.tasks;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mvc.dao.QueryExecutor;
import com.mvc.data.MT;
import com.mvc.data.User;
import com.mvc.data.Interest;

public class UserTasks {
	public User verifyPassword(User usr) {
		QueryExecutor qe = new QueryExecutor();
		String userData[] = qe.returnUserData(usr.getUseremail());
		if(userData!=null){
			String qeId = userData[0];
			String qePassword = userData[1];
			String qeFirstName = userData[2];
			String qeLastName = userData[3];
			int qeidCountry = Integer.parseInt(userData[4]);
			if (!qePassword.equals("") && usr.getUserpassword().equals(qePassword)){
				usr.setUserid(qeId);
				usr.setAuthenticated(true);
				usr.setFirstname(qeFirstName);
				usr.setLastname(qeLastName);
				usr.setIdcountry(qeidCountry);
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
		List<MT> submittedMTs = qe.returnUserSubmittedMTs(usr.getUserid());
		return submittedMTs;
	}

	public List<MT> getMatchedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MT> matchedMTs = qe.returnUserMatchedMTs(usr.getUserid());
		return matchedMTs;
	}
/*
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
*/
	public boolean signUp(HttpServletRequest request) {
		String signUpData[] = new String[6];
		signUpData[0] = request.getParameter("firstname");
		signUpData[1] = request.getParameter("lastname");
		signUpData[2] = request.getParameter("email");
		signUpData[3] = request.getParameter("password");
		signUpData[4] = request.getParameter("retypepassword");
		signUpData[5] = request.getParameter("country");
		
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
	
	public boolean showInterestMT(HttpServletRequest request) {
		String idMT= request.getParameter("idMT");
		String idUser= request.getParameter("idUser");		
		QueryExecutor qe = new QueryExecutor();
		boolean success = qe.submitShowInterestData(idMT, idUser);
		return success;
	}
	
	public boolean removeInterestMT(HttpServletRequest request) {
		String idMT= request.getParameter("idMT");
		String idUser= request.getParameter("idUser");		
		QueryExecutor qe = new QueryExecutor();
		boolean success = qe.submitRemoveInterestData(idMT, idUser);
		return success;
	}	
	
	public List<Interest> getInterests(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<Interest> interests = qe.returnUserInterests(usr.getUserid());
		return interests;
	}
	
	public User saveProfile(HttpServletRequest request) {
		String signUpData[] = new String[5];
		signUpData[0] = request.getParameter("iduser");		
		signUpData[1] = request.getParameter("firstname");
		signUpData[2] = request.getParameter("lastname");
		signUpData[3] = request.getParameter("email");
		signUpData[4] = request.getParameter("idcountry");
		
		QueryExecutor qe = new QueryExecutor();
		String userData[] = qe.submitSaveProfile(signUpData);
		User usr = new User();
		usr.setUserid(userData[0]);
		usr.setUseremail(userData[1]);
		usr.setAuthenticated(true);
		usr.setFirstname(userData[2]);
		usr.setLastname(userData[3]);
		usr.setIdcountry(Integer.parseInt(userData[4]));
		return usr;
	
	}	
}
