<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,mypkg.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MT Home</title>
</head>
<body>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
         </tr>
         <%
         List<SubmittedMT> smt = (List<SubmittedMT>) session.getAttribute("submittedMTs");
         int cor = 1;
         for (SubmittedMT item : smt) {
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