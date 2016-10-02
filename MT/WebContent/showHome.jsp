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

	  <h1>Matched</h1>
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
         List<MatchedMT> mmt = (List<MatchedMT>) session.getAttribute("matchedMTs");
         int cor2 = 1;
         for (MatchedMT item : mmt) {
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

	  <br>

      <form name="createMT" action="process" method="POST">
         <input type="hidden" name="todo" value="goToCreateMT">
         <input type="submit" value="Create MT">
      </form>
</body>
</html>