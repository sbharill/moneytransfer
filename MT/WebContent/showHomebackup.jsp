<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,com.mvc.data.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>MT Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="style/localStyle.css">  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">MT</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="#">Home</a></li>
        <!--
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
            <li><a href="#">Page 1-3</a></li>            
          </ul>
        </li>
        -->
        <li><a href="#">How It Works</a></li>        
        <li><a href="#">Contact Us</a></li>
      </ul>
	  <form class="navbar-form navbar-left" role="search">
	    <div class="form-group">
	      <input type="text" class="form-control" placeholder="Search">
	    </div>
	    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
	  </form>      
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">My MT</a></li>      
        <li><a href="#" onclick='document.goToMyProfile.submit();'>Profile</a></li>
        <li><a href="#" onclick='document.logout.submit();'><span class="glyphicon glyphicon-user"></span>Logout</a></li>      
      </ul>
    </div>
  </div>
</nav>
<div class="container">
    <div class="row">
      <div class="col-sm-6 col-md-offset-3">
	  <h1>Submitted</h1>
      <table class="table">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
            <th>Interested</th>
         </tr>
         <%
         List<MT> smt = (List<MT>) session.getAttribute("submittedMTs");
         int cor = 1;
         int sno = 1;
         String emailSubmittedBy = "";
         String fromCountry = "";
         String toCountry = "";
         String beforeDateMT = "";
         int amount = 0;  
         String interested = "";
         String idInterested = "";         
         String interestedAll = "";
         
         for (MT item : smt) {
        	 if(cor == 1){
                 emailSubmittedBy = item.getEmailSubmittedBy();
                 fromCountry = item.getFromCountry();
                 toCountry = item.getToCountry();
                 beforeDateMT = item.getBeforeDateMT();
                 amount = item.getAmount();  
                 interested = item.getInterested();
                 idInterested = item.getIdInterested();                 
                 interestedAll = "<form name=\"goToUserProfile\" action=\"profile/"+item.getInterested()+"\" method=\"POST\"><input type=\"hidden\" name=\"todo\" value=\"goToUserProfile\"><input type=\"hidden\" name=\"idprofile\" value=\""+idInterested+"\"><button type=\"submit\" class=\"btn btn-link\">"+interested+"</button></form>";  
        	 }
        	 else{
	        	 if(emailSubmittedBy.equals(item.getEmailSubmittedBy()) && fromCountry.equals(item.getFromCountry()) && toCountry.equals(item.getToCountry()) && beforeDateMT.equals(item.getBeforeDateMT()) && amount == item.getAmount()){
	        		 interestedAll = interestedAll + "," + "<form name=\"goToUserProfile\" action=\"profile/"+item.getInterested()+"\" method=\"POST\"><input type=\"hidden\" name=\"todo\" value=\"goToUserProfile\"><input type=\"hidden\" name=\"idprofile\" value=\""+item.getIdInterested()+"\"><button type=\"submit\" class=\"btn btn-link\">"+item.getInterested()+"</button></form>";
	        	 }
	        	 else{
			         %>
			         <tr>
			            <td><%= sno %></td>
			            <td><%= emailSubmittedBy%></td>
			            <td><%= fromCountry%></td>
			            <td><%= toCountry%></td>
			            <td><%= beforeDateMT%></td>
			            <td><%= amount%></td> 
			            <td><%= interestedAll%></td>                                    
			          </tr>
			         <%
	                 emailSubmittedBy = item.getEmailSubmittedBy();
	                 fromCountry = item.getFromCountry();
	                 toCountry = item.getToCountry();
	                 beforeDateMT = item.getBeforeDateMT();
	                 amount = item.getAmount();  
	                 interested = item.getInterested();
	                 interestedAll = interested;
	                 sno = sno + 1;
	        	 }
        	 }
        	 if(cor == smt.size()){
		         %>
		         <tr>
		            <td><%= sno %></td>
		            <td><%= emailSubmittedBy%></td>
		            <td><%= fromCountry%></td>
		            <td><%= toCountry%></td>
		            <td><%= beforeDateMT%></td>
		            <td><%= amount%></td> 
		            <td><%= interestedAll%></td>                                    
		          </tr>
		         <%        		 
        	 }
         cor = cor +1;
         }
         %>
      </table>

	  <h1>Matched</h1>
      <table class="table">
         <tr>
            <th>#</th>         
            <th>Email</th>
            <th>From Country</th>
            <th>To Country</th>
            <th>Before Date</th>
            <th>Amount</th>
            <th>Interested?</th> 
         </tr>
         <%
         List<MT> mmt = (List<MT>) session.getAttribute("matchedMTs");
         List<Interest> i = (List<Interest>) session.getAttribute("interests");        		 
         User user = (User) session.getAttribute("user");
         int cor2 = 1;
         for (MT item : mmt) {
        	 if(i.size() > 0) {
        	 for (Interest item2 : i) {
        		 if(item.getIdMT() == item2.getIdMT())
        		 {
         %>
			         <tr>
			            <td><%= cor2 %></td>
			            <td><%= item.getEmailSubmittedBy()%></td>
			            <td><%= item.getFromCountry()%></td>
			            <td><%= item.getToCountry()%></td>
			            <td><%= item.getBeforeDateMT()%></td>
			            <td><%= item.getAmount()%></td> 
			            <td>Interest Shown
							<form name="removeInterest" action="process" method="POST">
			         		<input type="hidden" name="todo" value="removeInterest">
			         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
			         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
			         		<input type="submit" value="Remove Interest">
			      			</form>			            
			            </td>                                    
			          </tr>
         <%
        		 }
        		 else{
        	         %>
			         <tr>
			            <td><%= cor2 %></td>
			            <td><%= item.getEmailSubmittedBy()%></td>
			            <td><%= item.getFromCountry()%></td>
			            <td><%= item.getToCountry()%></td>
			            <td><%= item.getBeforeDateMT()%></td>
			            <td><%= item.getAmount()%></td> 
			            <td><form name="showInterest" action="process" method="POST">
			         		<input type="hidden" name="todo" value="showInterest">
			         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
			         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
			         		<input type="submit" value="Show Interest">
			      			</form>
			      		</td>                                    
			          </tr>
         <%        			 
        		 }
        	 }
         cor2 = cor2 +1;
         }
        	 else{
    	         %>
		         <tr>
		            <td><%= cor2 %></td>
		            <td><%= item.getEmailSubmittedBy()%></td>
		            <td><%= item.getFromCountry()%></td>
		            <td><%= item.getToCountry()%></td>
		            <td><%= item.getBeforeDateMT()%></td>
		            <td><%= item.getAmount()%></td> 
		            <td><form name="showInterest" action="process" method="POST">
		         		<input type="hidden" name="todo" value="showInterest">
		         		<input type="hidden" name="idMT" value="<%= item.getIdMT()%>">
		         		<input type="hidden" name="idUser" value="<%= user.getUserid()%>">         		
		         		<input type="submit" value="Show Interest">
		      			</form>
		      		</td>                                    
		          </tr>
     <%           		 
        	 }
        		 
         } 
         %>
      </table>
	  <br>

      <form name="createMT" action="createmt" method="POST">
         <input type="hidden" name="todo" value="goToCreateMT">
         <input type="submit" value="Create MT">
      </form>
      <form name="goToMyProfile" action="myprofile" method="POST">
         <input type="hidden" name="todo" value="goToMyProfile">
      </form>
      <form name="logout" action="logout" method="POST">
         <input type="hidden" name="todo" value="logout">
      </form>
		<div class="modal fade" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Select to Share Number</h4>
		      </div>
		      <div class="modal-body">
		        <p>One fine body&hellip;</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Save changes</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->                     
</body>
</html>