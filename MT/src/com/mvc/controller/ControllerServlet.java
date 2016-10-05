package com.mvc.controller;
 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mvc.data.Country;
import com.mvc.data.MT;
import com.mvc.data.User;
import com.mvc.data.Interest;
import com.mvc.tasks.SystemTasks;
import com.mvc.tasks.UserTasks;
 
//@WebServlet("/shopping")  // Define URL pattern (for servlet 3.0 only)
public class ControllerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 /*
   @Override
   public void init(ServletConfig conf) throws ServletException {
      super.init(conf);
   }
 */
   //@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);  // Same as doPost()
   }
 
   //@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // Retrieve the current session, or create a new session if no session exists.
      HttpSession session = request.getSession(true);
      String nextPage = ""; 
      // Retrieve the shopping cart of the current session.
      //List<CartItem> theCart = (ArrayList<CartItem>) session.getAttribute("cart");
      User currUser = (User) session.getAttribute("user");
      if(currUser == null){
    	  String todo = request.getParameter("todo");
    	  if(todo == null){
    		  nextPage = "/login.jsp";
    	  }
    	  else if (todo.equals("login")){
	          String useremail = request.getParameter("useremail");
	          String userpassword = request.getParameter("userpassword");
	          if(useremail == null || useremail.equals("")){
	        	  nextPage = "/login.jsp";
	          }
	          else{
	        	  User usr = new User(useremail, userpassword);
	        	  UserTasks usertasks = new UserTasks();
	        	  if (usertasks.verifyPassword(usr)!=null && usertasks.verifyPassword(usr).isAuthenticated()){
	        		  List<MT> submittedMTs = (ArrayList<MT>) usertasks.getSubmittedMTs(usr);
	        		  List<MT> matchedMTs = (ArrayList<MT>) usertasks.getMatchedMTs(usr);
	        		  List<MT> interestShownMTs = (ArrayList<MT>) usertasks.getInterestShownMTs(usr);
	        		  List<MT> interestReceivedMTs = (ArrayList<MT>) usertasks.getInterestReceivedMTs(usr);
	        		  List<Interest> interests = (ArrayList<Interest>) usertasks.getInterests(usr);	        		  
	        		  session.setAttribute("submittedMTs", submittedMTs);
	        		  session.setAttribute("matchedMTs", matchedMTs);
	        		  session.setAttribute("interestShownMTs", interestShownMTs);
	        		  session.setAttribute("interestReceivedMTs", interestReceivedMTs);
	        		  session.setAttribute("interests", interests);	        		  
	        		  session.setAttribute("user", usr);	        		  
	        		  nextPage = "/showHome.jsp";
	        	  }
	        	  else{
	         		  nextPage = "/login.jsp";
	         		  request.setAttribute("error", "Wrong Password");
	         	  }
	          }
    	  }
    	  else if (todo.equals("gotosignup")){
        	  SystemTasks commontasks = new SystemTasks();
    		  List<Country> countries= (ArrayList<Country>) commontasks.getCountries();
    		  request.setAttribute("Countries", countries);
     		  nextPage = "/signUp.jsp";
    	  }
    	  else if (todo.equals("signup")){
    		  UserTasks usertasks = new UserTasks();
    		  boolean success =  usertasks.signUp(request);
    		  if(success) request.setAttribute("message", "SignUp success, please login.");
    		  else request.setAttribute("message", "SignUp failure, please try again.");
     		  nextPage = "/login.jsp";
    	  }
      }
      else{
    	  String todo = request.getParameter("todo");
    	  if(todo == null){
    		  nextPage = "/login.jsp";
    	  }
    	  else if (todo.equals("goToCreateMT")){
    		  SystemTasks commontasks = new SystemTasks();
    		  List<Country> countries= (ArrayList<Country>) commontasks.getCountries();
    		  request.setAttribute("Countries", countries);
    		  nextPage = "/createMT.jsp"; 
    	  }
    	  else if (todo.equals("createMT")){
    		  UserTasks usertasks = new UserTasks();
    		  boolean success =  usertasks.createMT(request);
    		  if(success) request.setAttribute("message", "Create MT success.");
    		  else request.setAttribute("message", "Create MT failure, please try again.");
    		  SystemTasks commontasks = new SystemTasks();
    		  List<Country> countries= (ArrayList<Country>) commontasks.getCountries();
    		  request.setAttribute("Countries", countries);
     		  nextPage = "/createMT.jsp";
    	  }
    	  else if (todo.equals("showInterest")){
    		  UserTasks usertasks = new UserTasks();
    		  boolean success =  usertasks.showInterestMT(request);
    		  if(success) request.setAttribute("message", "Show Interest MT success.");
    		  else request.setAttribute("message", "Show Interest MT failure, please try again.");
     		  nextPage = "/showHome.jsp";
    	  }
    	  else if (todo.equals("removeInterest")){
    		  UserTasks usertasks = new UserTasks();
    		  boolean success =  usertasks.removeInterestMT(request);
    		  if(success) request.setAttribute("message", "Remove Interest MT success.");
    		  else request.setAttribute("message", "Remove Interest MT failure, please try again.");
     		  nextPage = "/showHome.jsp";
    	  }     	  
 	  }
      ServletContext servletContext = getServletContext();
      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
      requestDispatcher.forward(request, response); 	  
}
}