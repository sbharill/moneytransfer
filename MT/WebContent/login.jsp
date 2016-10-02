<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,com.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
</head>
<body>
      <form name="login" action="login" method="POST">
         User Email: <input type="text" name="useremail">
         User Password: <input type="password" name="userpassword">
         <input type="hidden" name="todo" value="login">
         <input type="submit" value="Login">
      </form>
      <form name="signup" action="signup" method="POST">
         <input type="hidden" name="todo" value="gotosignup">
         <input type="submit" value="Sign Up">
      </form>
        <%
        String error = (String)request.getAttribute("error");
        if(error == null){
        %>
		<p>
		
		</p>
        <%
        }        
        else{
        %>
		<p>
		<%=request.getAttribute("error")%>
		</p>
		<%
		session.invalidate();
		} 
		%>
        <%
        String message = (String)request.getAttribute("message");
        if(message == null){
        %>
		<p>
		
		</p>
        <%
        }        
        else{
        %>
		<p>
		<%=request.getAttribute("message")%>
		</p>
		<%} 
		//session.invalidate();
		%>		
</body>
</html>