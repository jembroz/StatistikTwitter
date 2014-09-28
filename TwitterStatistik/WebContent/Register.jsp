<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.s3.project.bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Logged Successfully</title>
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
		<td><a href="Home.jsp">Home </a></td>
		<td><a href="Register.jsp">Register</a></td>
		<td><a href="TweetDeck.jsp">Tweet Deck</a></td>
		<td>
		LogOut
		</td>
	</tr>
	<tr>
				<td colspan="4"><a href="InputTwitter.jsp">Register Twitter User </a></td>
	</tr>
	<tr>
				<td colspan="4"><a href="InputUser.jsp">Register Application User </a></td>
	</tr>
	<tr>
		<td colspan="4">
		<p>Twitter Statistik Application </br>
		How are you to day?</p>
		</td>
	</tr>
	</table>
	   
</form>
</body>
</html>