<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Encountered</title>
</head>
<body>
<center>

<%int s = Integer.parseInt(request.getParameter("num")); 
 if(s==1){
	 out.println("<h3>Invalid username or password, try again</h3>");
 }
 else if(s==2){
	 out.print("<h3>Username already taken</h3>");
 }
 else if(s==3){
	 out.print("<h3>Please write the date in \"YYYY-MM-DD\" format</h3>");
 }
 else if(s==4){
	 out.print("<h3>Invalid credentials, some fields left unfilled</h3>");
 }
 else if(s==5){
	 out.print("<h3>Search field left blank</h3>");
 }
 else if(s==6){
	 out.print("<h3>Passwords dont match, try again</h3>");
 }
 else if(s==7){
	 out.print("<h3>Length exceeded 140 characters</h3>");
 }
 else if(s==8){
	 out.print("<h3>Cannot follow/unfollow yourself</h3>");
 }
 else if(s==9){
	 out.print("<h3>Original password incorrect, try again</h3>");
 }
%>
</center>
</body>
</html>