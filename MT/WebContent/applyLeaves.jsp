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
      <form name="submitLeave" action="shopping" method="POST">
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
         	<th>&nbsp;</th>
            <th>Select Leave Type</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Days</th>
         </tr>
         <%
         List<UnUsedLeave> unUsedLeave = (List<UnUsedLeave>) session.getAttribute("unUsedLeaves");
         for (UnUsedLeave item : unUsedLeave) {
         %>
         <tr>
            <td><input type="radio" name="leavetype" value="<%= item.getIdLeaveType()%>"></td>
            <td><input type="hidden" name="iduser<%= item.getIdLeaveType()%>" value="<%= item.getIdUser() %>"><%= item.getType() %></td>
            <td><input type="hidden" name="useremail<%= item.getIdLeaveType()%>" value="<%= item.getUserEmail()%>"><input type="text" name="fromdate<%= item.getIdLeaveType()%>"></td>
            <td><input type="hidden" name="type<%= item.getIdLeaveType()%>" value="<%= item.getType()%>"><input type="text" name="todate<%= item.getIdLeaveType()%>"></td>
            <td>
            <select name="days<%= item.getIdLeaveType()%>">
            <%
            for(int i=0;i<item.getBalance();i++){
            %>            
			  <option value="<%= i+1 %>"><%= i+1 %></option>
            <%
            }
            %>            
			</select>
            </td>                                    
          </tr>
         <%
         } // for
         //session.invalidate();
         %>
      </table>
         <input type="hidden" name="todo" value="submitleave">
         <input type="submit" value="Submit Leave">
      </form>
</body>
</html>