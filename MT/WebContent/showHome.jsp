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
         </tr>
         <%
         List<MT> smt = (List<MT>) session.getAttribute("submittedMTs");
         int cor = 1;
         for (MT item : smt) {
         %>
         <tr>
            <td><%= cor %></td>
            <td><%= item.getEmailSubmittedBy()%></td>
            <td><%= item.getFromCountry()%></td>
            <td><%= item.getToCountry()%></td>
            <td><%= item.getBeforeDateMT()%></td>
            <td><%= item.getAmount()%></td>                                    
          </tr>
         <%
         cor = cor +1;
         } // for
         //session.invalidate();
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
			            <td>Interest Shown</td>                                    
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

	  <h1>Interested Shown</h1>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
         </tr>
         <%
         List<MT> ismt = (List<MT>) session.getAttribute("interestShownMTs");
         int cor3 = 1;
         for (MT item : ismt) {
         %>
         <tr>
            <td><%= cor3 %></td>
            <td><%= item.getEmailSubmittedBy()%></td>
            <td><%= item.getFromCountry()%></td>
            <td><%= item.getToCountry()%></td>
            <td><%= item.getBeforeDateMT()%></td>
            <td><%= item.getAmount()%></td>                                    
          </tr>
         <%
         cor3 = cor3 +1;
         } // for
         //session.invalidate();
         %>
      </table>

	  <h1>Interested Received</h1>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
         </tr>
         <%
         List<MT> irmt = (List<MT>) session.getAttribute("interestReceivedMTs");
         int cor4 = 1;
         for (MT item : irmt) {
         %>
         <tr>
            <td><%= cor3 %></td>
            <td><%= item.getEmailSubmittedBy()%></td>
            <td><%= item.getFromCountry()%></td>
            <td><%= item.getToCountry()%></td>
            <td><%= item.getBeforeDateMT()%></td>
            <td><%= item.getAmount()%></td>                                    
          </tr>
         <%
         cor4 = cor4 +1;
         } // for
         //session.invalidate();
         %>
      </table>

	  <br>

      <form name="createMT" action="process" method="POST">
         <input type="hidden" name="todo" value="goToCreateMT">
         <input type="submit" value="Create MT">
      </form>
</body>
</html>