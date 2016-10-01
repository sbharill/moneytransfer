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
      <form name="signUp" action="process" method="POST">
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th colspan=2>Sign Up</th>
        </tr>
        <tr>
         	<td>First Name</td>
         	<td><input type="text" name="firstname"></td>
        </tr>
        <tr>
         	<td>Last Name</td>
         	<td><input type="text" name="lastname"></td>
        </tr>    
        <tr>
         	<td>Email</td>
         	<td><input type="text" name="email"></td>
        </tr>
        <tr>
         	<td>Password</td>
         	<td><input type="password" name="password"></td>
        </tr>
        <tr>
         	<td>Retype Password</td>
         	<td><input type="password" name="retypepassword"></td>
        </tr>  
        <tr>
         	<td>Country</td>
         	<td>
         	
         	<select name="country">
            <%
            List<Country> cc = (List<Country>) request.getAttribute("Countries");
            for (Country item : cc) {
            %>
        	  <option value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
            <%
            }
            %>            
			</select>
         	</td>
        </tr>
      </table>
         <input type="hidden" name="todo" value="signup">
         <input type="submit" value="Submit">
      </form>
</body>
</html>