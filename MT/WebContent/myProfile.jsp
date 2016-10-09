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
      <form name="profile" action="process" method="POST">
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th colspan=2>Profile</th>
        </tr>
         <%
         User user = (User) session.getAttribute("user");
         %>
        <tr>
         	<td>First Name</td>
         	<td><input type="text" name="firstname" value="<%= user.getFirstname()%>"></td>
        </tr>
        <tr>
         	<td>Last Name</td>
         	<td><input type="text" name="lastname" value="<%= user.getLastname()%>"></td>
        </tr>    
        <tr>
         	<td>Email</td>
         	<td><input type="text" name="email" value="<%= user.getUseremail()%>"></td>
        </tr>
         	<td>Country</td>
         	<td>
         	
         	<select name="idcountry">
            <%
            List<Country> cc = (List<Country>) request.getAttribute("Countries");
            for (Country item : cc) {
            	if(item.getIdCountry() == user.getIdcountry()){
                  %>
              	  <option selected value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
                  <%            		
            	}
            	else{
		            %>
		        	  <option value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
		            <%
 	            }
            }
            %>            
			</select>
         	</td>
        </tr>
      </table>
      	 <input type="hidden" name="iduser" value="<%= user.getUserid()%>">
         <input type="hidden" name="todo" value="saveprofile">
         <input type="submit" value="Submit">
      </form>
      <form name="gotohome" action="process" method="POST">      
         <input type="hidden" name="todo" value="gotohome">
         <input type="submit" value="Home">
      </form>      
</body>
</html>