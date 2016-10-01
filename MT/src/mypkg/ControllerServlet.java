package mypkg;
 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
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
	        		  List<SubmittedMT> submittedMTs = (ArrayList<SubmittedMT>) usertasks.getSubmittedMTs(usr);
	        		  List<MatchedMT> matchedMTs = (ArrayList<MatchedMT>) usertasks.getMatchedMTs(usr);
	        		  session.setAttribute("submittedMTs", submittedMTs);
	        		  session.setAttribute("matchedMTs", matchedMTs);
	        		  session.setAttribute("user", usr);	        		  
	        		  nextPage = "/showHome.jsp";
	        	  }
	        	  else{
	         		  nextPage = "/login.jsp";
	         		  session.setAttribute("error", "Wrong Password");
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
    		  if(success) session.setAttribute("message", "SignUp success, please login.");
    		  else session.setAttribute("message", "SignUp failure, please try again.");
     		  nextPage = "/login.jsp";
    	  }
      }
      else{
    	  String todo = request.getParameter("todo");
    	  if(todo == null){
    		  nextPage = "/login.jsp";
    	  }
    	  else if (todo.equals("createMT")){
    		  SystemTasks commontasks = new SystemTasks();
    		  List<Country> countries= (ArrayList<Country>) commontasks.getCountries();
    		  request.setAttribute("Countries", countries);
            //  List<Country> cc = (List<Country>) request.getAttribute("countries");
             // for (Country item : cc) {
          	 // item.getIdCountry();
             // }
    		  
    		  
    		  nextPage = "/createMT.jsp"; 
    	  }
    	  else if (todo.equals("submitleave")){
    		  UsedLeave usedLeaves = new UsedLeave();
              String idLeave = request.getParameter("leavetype");
              usedLeaves.setIdLeave(Integer.parseInt(idLeave));
              usedLeaves.setIdUser(request.getParameter("iduser"+idLeave));
              usedLeaves.setDays(Integer.parseInt(request.getParameter("days"+idLeave)));
              usedLeaves.setFromDate(request.getParameter("fromdate"+idLeave));
              usedLeaves.setToDate(request.getParameter("todate"+idLeave));
              usedLeaves.setUserEmail(request.getParameter("useremail"+idLeave));
              usedLeaves.setType(request.getParameter("type"+idLeave));
              usedLeaves.setApproved("N");
    		  currUser.submitLeaves(usedLeaves);
    		  List<UsedLeave> usedLeavesList = (List<UsedLeave>)session.getAttribute("usedLeaves");
    		  usedLeavesList.add(usedLeaves);
    		  session.setAttribute("usedLeaves", usedLeavesList);
    		  List<UnUsedLeave> unUsedLeavesList = (List<UnUsedLeave>)session.getAttribute("unUsedLeaves");
    		  Iterator iter = unUsedLeavesList.iterator();
	          while (iter.hasNext()) {
	        	  UnUsedLeave unUsedLeave = (UnUsedLeave)iter.next();
	              if (unUsedLeave.getIdLeaveType().equals(idLeave) && unUsedLeave.getIdUser().equals(usedLeaves.getIdUser())) {
	            	int latestBalance = unUsedLeave.getBalance()-usedLeaves.getDays();
	            	currUser.updateUserLeavesBalance(unUsedLeave, latestBalance);
	            	unUsedLeave.setBalance(latestBalance);
	               }
	          }
    		  session.setAttribute("unUsedLeaves", unUsedLeavesList);
    		  nextPage = "/submitLeavesMsg.jsp"; 
    	  }
    	  else if (todo.equals("showactionleave")){
    		  nextPage = "/actionLeaves.jsp"; 
    	  }
    	  else if (todo.equals("actionleave")){
              String idLeaves[] = request.getParameterValues("idleave");
              String action = request.getParameter("leaveaction");
              if(action.equals("approve")){
            	  currUser.approveLeaves(idLeaves);
            	  List<ApproveLeave> approveList = (List<ApproveLeave>)session.getAttribute("approveLeaves");
            	  Iterator iter = approveList.iterator();
    	          while (iter.hasNext()) {
    	        	  ApproveLeave approveLeave = (ApproveLeave)iter.next();
    	        	  for(int i=0;i<idLeaves.length;i++){  
	    	              if (String.valueOf(approveLeave.getIdLeave()).equals(idLeaves[i])) {
	    	            	  approveLeave.setApproved("Y");
	    	              }
    	        	  }
    	          }
    	          session.setAttribute("approveLeaves", approveList);
              }
              else if(action.equals("reject")){
            	  currUser.rejectLeaves(idLeaves);
            	  List<ApproveLeave> approveList = (List<ApproveLeave>)session.getAttribute("approveLeaves");
            	  Iterator iter = approveList.iterator();
    	          while (iter.hasNext()) {
    	        	  ApproveLeave approveLeave = (ApproveLeave)iter.next();
    	        	  for(int i=0;i<idLeaves.length;i++){  
	    	              if (String.valueOf(approveLeave.getIdLeave()).equals(idLeaves[i])) {
	    	            	  int latestBalance = currUser.getLeavesBalance(approveLeave);
	    	            	  System.out.println(latestBalance);
	    	            	  currUser.updateUserRejectedLeavesBalance(approveLeave, latestBalance+approveLeave.getDays());	    	            	  
	    	            	  approveLeave.setApproved("R");
	    	              }
    	        	  }
    	          }
    	          session.setAttribute("approveLeaves", approveList);
              }
              nextPage = "/actionLeavesMsg.jsp"; 
    		  
    		  /*
    		  List<UsedLeave> usedLeaves = (ArrayList<UsedLeave>) usr.getUsedLeaves();
    		  List<UnUsedLeave> unUsedLeaves = (ArrayList<UnUsedLeave>) usr.getUnUsedLeaves();
    		  session.setAttribute("usedLeaves", usedLeaves);    		  
    		  ApproveLeave usedLeaves = new UsedLeave();
              String idLeave = request.getParameter("leavetype");
              usedLeaves.setIdLeave(Integer.parseInt(idLeave));
              usedLeaves.setIdUser(request.getParameter("iduser"+idLeave));
              usedLeaves.setDays(Integer.parseInt(request.getParameter("days"+idLeave)));
              usedLeaves.setFromDate(request.getParameter("fromdate"+idLeave));
              usedLeaves.setToDate(request.getParameter("todate"+idLeave));
              usedLeaves.setUserEmail(request.getParameter("useremail"+idLeave));
              usedLeaves.setType(request.getParameter("type"+idLeave));
    		  currUser.submitLeaves(usedLeaves);
    		  List<UsedLeave> usedLeavesList = (List<UsedLeave>)session.getAttribute("usedLeaves");
    		  usedLeavesList.add(usedLeaves);
    		  session.setAttribute("usedLeaves", usedLeavesList);
    		  List<UnUsedLeave> unUsedLeavesList = (List<UnUsedLeave>)session.getAttribute("unUsedLeaves");
    		  Iterator iter = unUsedLeavesList.iterator();
	            while (iter.hasNext()) {
	            	UnUsedLeave unUsedLeave = (UnUsedLeave)iter.next();
	               if (unUsedLeave.getIdLeaveType().equals(idLeave) && unUsedLeave.getIdUser().equals(usedLeaves.getIdUser())) {
	            	   int latestBalance = unUsedLeave.getBalance()-usedLeaves.getDays();
	            	   currUser.updateUserLeavesBalance(unUsedLeave, latestBalance);
	            	   unUsedLeave.setBalance(latestBalance);
	               }
	            }
    		  session.setAttribute("unUsedLeaves", unUsedLeavesList);
    		  nextPage = "/submitLeavesMsg.jsp";*/ 
    	  }    	  
    	  else if (todo.equals("showleave")){
    		  nextPage = "/showLeaves.jsp"; 
    	  }

    	  /*
          String todo = request.getParameter("todo");
		  if (todo == null) {
		         // First access - redirect to order.jsp
		         nextPage = "/order.jsp";
		      } else if (todo.equals("add")) {
		         // Submitted by order.jsp, with request parameters bookID and qty.
		         // Create a CartItem for the selected book, and add it into the cart
		         CartItem newCartItem = new CartItem(
		                 Integer.parseInt(request.getParameter("bookID")),
		                 Integer.parseInt(request.getParameter("qty")));
		         if (theCart == null) { // shopping cart is empty
		            theCart = new ArrayList();
		            theCart.add(newCartItem);
		            session.setAttribute("cart", theCart);  // binds cart to session
		         } else {
		            // Check if this book is already in the cart.
		            // If so, update the qty ordered. Otherwise, add it to the cart.
		            boolean found = false;
		            Iterator iter = theCart.iterator();
		            while (!found && iter.hasNext()) {
		               CartItem aCartItem = (CartItem)iter.next();
		               if (aCartItem.getBookID() == newCartItem.getBookID()) {
		                  aCartItem.setQtyOrdered(aCartItem.getQtyOrdered()
		                        + newCartItem.getQtyOrdered());
		                  found = true;
		               }
		            }
		            if (!found) { // Add it to the cart
		               theCart.add(newCartItem);
		            }
		         }
		         // Back to order.jsp for more order
		         nextPage = "/order.jsp";
		      } else if (todo.equals("remove")) {
		         // Submitted by order.jsp, with request parameter cartIndex.
		         // Remove the CartItem of cartIndex in the cart
		         int cartIndex = Integer.parseInt(request.getParameter("cartIndex"));
		         theCart.remove(cartIndex);
		         // Back to order.jsp for more order
		         nextPage = "/order.jsp";
		      } else if (todo.equals("checkout")) {
		         // Submitted by order.jsp.
		         // Compute the total price for all the items in the cart
		         float totalPrice = 0;
		         int totalQtyOrdered = 0;
		         for (CartItem item: theCart) {
		            float price = item.getPrice();
		            int qtyOrdered = item.getQtyOrdered();
		            totalPrice += price * qtyOrdered;
		            totalQtyOrdered += qtyOrdered;
		         }
		         // Format the price in float to 2 decimal places
		         StringBuilder sb = new StringBuilder();
		         Formatter formatter = new Formatter(sb);  // Send all output to sb
		         formatter.format("%.2f", totalPrice);     // 2 decimal places
		         // Put the total price and qty in request header
		         request.setAttribute("totalPrice", sb.toString());
		         request.setAttribute("totalQtyOrdered", totalQtyOrdered + "");
		         // Dispatch to checkout.jsp
		         nextPage = "/checkout.jsp";
		      }*/
 	  }
      ServletContext servletContext = getServletContext();
      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
      requestDispatcher.forward(request, response); 	  
}
}