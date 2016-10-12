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
      <form name="submitMT" action="createmt" method="POST">
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th colspan=2>I want to transfer</th>
        </tr>
        <tr>
         	<td>Amount</td>
         	<td><input type="text" name="amount"></td>
        </tr>
        <tr>
         	<td>From Country</td>
         	<td>
         	
         	<select name="fromCountry">
         	
            <%
            List<Country> cc = (List<Country>) request.getAttribute("Countries");
            User usr = (User) session.getAttribute("user");
            for (Country item : cc) {
            %>
        	  <option value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
            <%
            }
            %>            
			</select>
         	</td>
        </tr>
        <tr>
         	<td>To Country</td>
         	<td>
         	
         	<select name="toCountry">
            <%
            for (Country item : cc) {
            %>
        	  <option value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
            <%
            }
            %>            
			</select>
         	</td>
        </tr> 
        <tr>
        	<td>Before Date</td>
         	<td><input type="text" name="beforeDate"></td>
        </tr>        
      </table>
		 <input type="hidden" name="userId" value="<%= usr.getUserid()%>">
         <input type="hidden" name="todo" value="createMT">
         <input type="submit" value="Submit">
      </form>
      <form name="gotohome" action="home" method="POST">      
         <input type="hidden" name="todo" value="gotohome">
         <input type="submit" value="Home">
      </form>     
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