<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,com.mvc.data.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MT Home</title>
</head>
<body>
	  <h1>Submitted</h1>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
            <th>Interested</th>
         </tr>
         <%
         List<MT> smt = (List<MT>) session.getAttribute("submittedMTs");
         int cor = 1;
         int sno = 1;
         String emailSubmittedBy = "";
         String fromCountry = "";
         String toCountry = "";
         String beforeDateMT = "";
         int amount = 0;  
         String interested = "";
         String idInterested = "";         
         String interestedAll = "";
         
         for (MT item : smt) {
        	 if(cor == 1){
                 emailSubmittedBy = item.getEmailSubmittedBy();
                 fromCountry = item.getFromCountry();
                 toCountry = item.getToCountry();
                 beforeDateMT = item.getBeforeDateMT();
                 amount = item.getAmount();  
                 interested = item.getInterested();
                 idInterested = item.getIdInterested();                 
                 interestedAll = "<form name=\"goToUserProfile\" action=\"profile/"+item.getInterested()+"\" method=\"POST\"><input type=\"hidden\" name=\"todo\" value=\"goToUserProfile\"><input type=\"hidden\" name=\"idprofile\" value=\""+idInterested+"\"><input type=\"submit\" value=\""+interested+"\"></form>";  
        	 }
        	 else{
	        	 if(emailSubmittedBy.equals(item.getEmailSubmittedBy()) && fromCountry.equals(item.getFromCountry()) && toCountry.equals(item.getToCountry()) && beforeDateMT.equals(item.getBeforeDateMT()) && amount == item.getAmount()){
	        		 interestedAll = interestedAll + "," + "<form name=\"goToUserProfile\" action=\"profile/"+item.getInterested()+"\" method=\"POST\"><input type=\"hidden\" name=\"todo\" value=\"goToUserProfile\"><input type=\"hidden\" name=\"idprofile\" value=\""+item.getIdInterested()+"\"><input type=\"submit\" value=\""+item.getInterested()+"\"></form>";
	        	 }
	        	 else{
			         %>
			         <tr>
			            <td><%= sno %></td>
			            <td><%= emailSubmittedBy%></td>
			            <td><%= fromCountry%></td>
			            <td><%= toCountry%></td>
			            <td><%= beforeDateMT%></td>
			            <td><%= amount%></td> 
			            <td><%= interestedAll%></td>                                    
			          </tr>
			         <%
	                 emailSubmittedBy = item.getEmailSubmittedBy();
	                 fromCountry = item.getFromCountry();
	                 toCountry = item.getToCountry();
	                 beforeDateMT = item.getBeforeDateMT();
	                 amount = item.getAmount();  
	                 interested = item.getInterested();
	                 interestedAll = interested;
	                 sno = sno + 1;
	        	 }
        	 }
        	 if(cor == smt.size()){
		         %>
		         <tr>
		            <td><%= sno %></td>
		            <td><%= emailSubmittedBy%></td>
		            <td><%= fromCountry%></td>
		            <td><%= toCountry%></td>
		            <td><%= beforeDateMT%></td>
		            <td><%= amount%></td> 
		            <td><%= interestedAll%></td>                                    
		          </tr>
		         <%        		 
        	 }
         cor = cor +1;
         }
         %>
      </table>

	  <h1>Matched</h1>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
            <th>Interested?</th> 
         </tr>
         <%
         List<MT> mmt = (List<MT>) session.getAttribute("matchedMTs");
         List<Interest> i = (List<Interest>) session.getAttribute("interests");        		 
         User user = (User) session.getAttribute("user");
         int cor2 = 1;
         for (MT item : mmt) {
        	 if(i.size() > 0) {
        	 for (Interest item2 : i) {
        		 if(item.getIdMT() == item2.getIdMT())
        		 {
         %>
			         <tr>
			            <td><%= cor2 %></td>
			            <td><%= item.getEmailSubmittedBy()%></td>
			            <td><%= item.getFromCountry()%></td>
			            <td><%= item.getToCountry()%></td>
			            <td><%= item.getBeforeDateMT()%></td>
			            <td><%= item.getAmount()%></td> 
			            <td>Interest Shown
							<form name="removeInterest" action="process" method="POST">
			         		<input type="hidden" name="todo" value="removeInterest">
			         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
			         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
			         		<input type="submit" value="Remove Interest">
			      			</form>			            
			            </td>                                    
			          </tr>
         <%
        		 }
        		 else{
        	         %>
			         <tr>
			            <td><%= cor2 %></td>
			            <td><%= item.getEmailSubmittedBy()%></td>
			            <td><%= item.getFromCountry()%></td>
			            <td><%= item.getToCountry()%></td>
			            <td><%= item.getBeforeDateMT()%></td>
			            <td><%= item.getAmount()%></td> 
			            <td><form name="showInterest" action="process" method="POST">
			         		<input type="hidden" name="todo" value="showInterest">
			         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
			         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
			         		<input type="submit" value="Show Interest">
			      			</form>
			      		</td>                                    
			          </tr>
         <%        			 
        		 }
        	 }
         cor2 = cor2 +1;
         }
        	 else{
    	         %>
		         <tr>
		            <td><%= cor2 %></td>
		            <td><%= item.getEmailSubmittedBy()%></td>
		            <td><%= item.getFromCountry()%></td>
		            <td><%= item.getToCountry()%></td>
		            <td><%= item.getBeforeDateMT()%></td>
		            <td><%= item.getAmount()%></td> 
		            <td><form name="showInterest" action="process" method="POST">
		         		<input type="hidden" name="todo" value="showInterest">
		         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
		         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
		         		<input type="submit" value="Show Interest">
		      			</form>
		      		</td>                                    
		          </tr>
     <%           		 
        	 }
        		 
         } 
         %>
      </table>
	  <br>

      <form name="createMT" action="createmt" method="POST">
         <input type="hidden" name="todo" value="goToCreateMT">
         <input type="submit" value="Create MT">
      </form>
      <form name="goToMyProfile" action="myprofile" method="POST">
         <input type="hidden" name="todo" value="goToMyProfile">
         <input type="submit" value="Profile">
      </form>
      <form name="logout" action="logout" method="POST">
         <input type="hidden" name="todo" value="logout">
         <input type="submit" value="Logout">
      </form>               
</body>
</html>