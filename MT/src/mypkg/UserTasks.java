package mypkg;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public List<SubmittedMT> getSubmittedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<SubmittedMT> submittedMTs = qe.returnUserSubmittedMTs(usr.getUsername());
		return submittedMTs;
	}

	public List<MatchedMT> getMatchedMTs(User usr){
		QueryExecutor qe = new QueryExecutor();
		List<MatchedMT> matchedMTs = qe.returnUserMatchedMTs(usr.getUsername());
		return matchedMTs;
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
