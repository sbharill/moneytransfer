<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,com.mvc.data.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th colspan=2>Profile</th>
        </tr>
         <%
         User user = (User) session.getAttribute("otheruser");
         %>
        <tr>
         	<td>First Name</td>
         	<td><%= user.getFirstname()%></td>
        </tr>
        <tr>
         	<td>Last Name</td>
         	<td><%= user.getLastname()%></td>
        </tr>    
        <tr>
         	<td>Email</td>
         	<td><%= user.getUseremail()%></td>
        </tr>
         	<td>Country</td>
         	<td><%= user.getIdcountry()%></td>
        </tr>
      </table>
      <form name="gotohome" action="../home" method="POST">      
         <input type="hidden" name="todo" value="gotohome">
         <input type="submit" value="Home">
      </form>       
</body>
</html>