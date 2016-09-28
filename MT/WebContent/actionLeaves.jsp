<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*, mypkg.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <form name="approveLeave" action="shopping" method="POST">
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th>&nbsp;</th>
            <th>Id</th>
            <th>Email</th>
            <th>Type</th>
            <th>From</th>
            <th>To</th>
            <th>Days</th>
         </tr>
         <%
         List<ApproveLeave> approveLeave = (List<ApproveLeave>) session.getAttribute("approveLeaves");
         int cor = 1;
         for (ApproveLeave item : approveLeave) {
        	 if(item.getApproved().equals("N")){
         %>
	         <tr>
	         	<td><input type="checkbox" name="idleave" value="<%= item.getIdLeave()%>"></td>
	            <td><%= cor %></td>
	            <td><%= item.getUserEmail()%></td>
	            <td><%= item.getType()%></td>
	            <td><%= item.getFromDate()%></td>
	            <td><%= item.getToDate() %></td>
	            <td><%= item.getDays() %></td>                                    
	          </tr>
         <%
         cor = cor +1;
        	 }
         } // for
         //session.invalidate();
         %>
      </table>
		 <input type="radio" name="leaveaction" value="approve">Approve
		 <input type="radio" name="leaveaction" value="reject">Reject	
         <input type="hidden" name="todo" value="actionleave"><br> 
         <input type="submit" value="Submit">
      </form>      
</body>
</html>