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
      <div class="col-sm-9">
		  <ul class="nav nav-pills">
		    <li class="active"><a data-toggle="pill" href="#home">Home</a></li>
		    <li><a data-toggle="pill" href="#menu1">Menu 1</a></li>
		    <li><a data-toggle="pill" href="#menu2">Menu 2</a></li>
		    <li><a data-toggle="pill" href="#menu3">Menu 3</a></li>
		  </ul>

		  <div class="tab-content">
		    <div id="home" class="tab-pane fade in active">
		      <h3>HOME</h3>
		      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
		    </div>
		    <div id="menu1" class="tab-pane fade">
		      <h3>Menu 1</h3>
		      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
		    </div>
		    <div id="menu2" class="tab-pane fade">
		      <h3>Menu 2</h3>
		      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
		    </div>
		    <div id="menu3" class="tab-pane fade">
		      <h3>Menu 3</h3>
		      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
		    </div>
		  </div>      
		</div>
      <div class="col-sm-3">
        <%
        Message msg = (Message)request.getAttribute("msg");
        if(msg == null){
        }        
        else{
        %>
		<div class="alert alert-danger">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>		
		  <strong><%=msg.getContent()%></strong>
		</div>   		
		<%
		session.invalidate();
		} 
		%>      
          <form name="login" action="login" method="POST">
            <div class="form-group">
              <input type="email" class="form-control" id="email" placeholder="Enter email" name="useremail">
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="userpassword">
            </div>
            <div class="checkbox">
              <label><input type="checkbox">Remember me</label>
            </div>
            <input type="hidden" name="todo" value="login">
            <button type="submit" class="btn btn-default">Login</button>
          </form>
        </div>
    </div>
</div>  
      <form name="logout" action="logout" method="POST">
         <input type="hidden" name="todo" value="logout">
      </form> 
      <form name="signup" action="signup" method="POST">
         <input type="hidden" name="todo" value="gotosignup">
      </form>      
</body>
</html>
