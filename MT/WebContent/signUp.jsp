<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" import="java.util.*,com.mvc.data.*" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>JobOnRef</title>
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
        <li><a href="#" onclick='document.signup.submit();'>Sign Up</a></li> 
      </ul>
    </div>
  </div>
</nav>
<div class="container">
    <div class="row">
      <div class="col-sm-6 col-md-offset-3">
        <%
        Message msg = (Message)request.getAttribute("msg");
        if(msg == null){
        }        
        else{
        	if(msg.getMessageType().equals("Danger")){
                %>   		
        		<div class="alert alert-danger">
      		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>		
    		    <strong><%=msg.getContent()%></strong>
    		    </div>
    		    <%          		
        	}
        	else if(msg.getMessageType().equals("Success")){
                %>   		
        		<div class="alert alert-success">
      		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>		
    		    <strong><%=msg.getContent()%></strong>
    		    </div>
    		    <%
        	}
        }
		session.invalidate();
		%>        
      <form name="signUp" action="signup" method="POST">
            <div class="form-group">
  			  <label for="firstname">First Name:</label>            
              <input type="text" class="form-control" id="firstname" name="firstname">
            </div> 
            <div class="form-group">
  			  <label for="lastname">Last Name:</label>            
              <input type="text" class="form-control" id="lastname" name="lastname">
            </div>   
            <div class="form-group">
  			  <label for="email">Email:</label>            
              <input type="email" class="form-control" id="email" name="email">
            </div>   
            <div class="form-group">
  			  <label for="password">Password:</label>            
              <input type="password" class="form-control" id="password" name="password">
            </div>   
            <div class="form-group">
  			  <label for="retypepassword">Retype Password:</label>            
              <input type="password" class="form-control" id="retypepassword" name="retypepassword">
            </div>                                                        
			<div class="form-group">
			  <label for="country">Country:</label>
			  <select class="form-control" id="country" name="country">
	            <%
	            List<Country> cc = (List<Country>) request.getAttribute("Countries");
	            for (Country item : cc) {
	            %>
	        	  <option value="<%= item.getIdCountry() %>"><%= item.getNameCountry() %></option>
	            <%
	            }
	            %>
			  </select>
			</div>			  
	        <input type="hidden" name="todo" value="signup">
			<button type="submit" class="btn btn-primary">Submit</button>
      </form>
      <br>
      
      <form name="cancelsignup" action="process" method="POST">      
         <input type="hidden" name="todo" value="cancelsignup">
		 <button type="submit" class="btn btn-primary">Cancel</button>
      </form>        
</body>
</html>
