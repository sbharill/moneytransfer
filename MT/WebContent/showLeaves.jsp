<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,mypkg.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Type</th>
            <th>From</th>
            <th>To</th>
            <th>Days</th>
         </tr>
         <%
         List<UsedLeave> usedLeave = (List<UsedLeave>) session.getAttribute("usedLeaves");
         int cor = 1;
         for (UsedLeave item : usedLeave) {
         %>
         <tr>
            <td><%= cor %></td>
            <td><%= item.getUserEmail()%></td>
            <td><%= item.getType()%></td>
            <td><%= item.getFromDate()%></td>
            <td><%= item.getToDate() %></td>
            <td><%= item.getDays() %></td>                                    
          </tr>
         <%
         cor = cor +1;
         } // for
         //session.invalidate();
         %>
      </table>
      <form name="applyLeave" action="shopping" method="POST">
         <input type="hidden" name="todo" value="applyleave">
         <input type="submit" value="Apply Leave">
      </form>
      <form name="approveLeave" action="shopping" method="POST">
         <input type="hidden" name="todo" value="showactionleave">
         <input type="submit" value="Approve/Reject Leave">
      </form>      
</body>
</html>