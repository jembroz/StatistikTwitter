<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.s3.project.bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Logged Successfully</title>
<link href="js/dropdown-menu.css" media="screen" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="">
<table border="1" width="800px" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="4" >
		<% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>
	   Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<ul id="navigation" class="nav-main">
			<li><a href="Home.jsp">Home</a></li>
		    
		    <li class="list"><a href="#">Register</a>
			<ul class="nav-sub">
		    	<li><a href="InputTwitter.jsp">Register Twitter User</a></li>
				<li><a href="InputUser.jsp">Register Application User</a></li> 
			</ul>
		
		    <li class="list"><a href="#">Enquiry</a>
		    <ul class="nav-sub">
		    	<li><a href="ShowUserTwitter.jsp">Show User Twitter</a></li>
		    	<li><a href="TweetDeck.jsp">Tweet Deck</a></li>
		    </ul>
		    <li><a href="#">About</a></li>
		    <li><a href="#">Logout</a></li>
		 </ul>
		 </td>
	</tr>
	<tr>
		<td colspan="4">
		
		<p>Twitter Statistik Application </br>
		How are you to day? </p>
		</td>
	</tr>
</table>
	   
</form>
</body>
</html>