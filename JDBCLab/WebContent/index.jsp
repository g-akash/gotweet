
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GoTweet</title>
<style type="text/css">
.link {
    margin: 0;
    border: 0;
    background: none;
    overflow: visible;
    color: blue;
    cursor: pointer;
}
.chatForm, .declineForm {
    display: inline-block;
}
form.inline{
	display : inline-block;
}
.centered{
position:fixed;
top:3%;
right:3%;
}
body{
/*background-image: url("social-networking.jpg");*/
}
h1 {
	font-family: 'Sans-Serif';
	font-size: 45px;
	font-weight: normal;
	position:absolute;
	left:30px;
	top:10px;
}
.bar{
	background-color: #b0c4de;
}
.preface {
	font-size:150%;
    position: absolute;
    top:150px;
    left: 20px;
    width: 300px;
    padding: 10px;
}
</style>
</head>
<body>
<a href="" style="color:#6699FF"><h1>GoTweet</h1></a>
<!--  
<div class="jumbotron"><h2><center>GoTweet</center> </h2></div>
-->
<center>
<div class="centered">
<a href="login.jsp" class="btn btn-primary btn-lg">Login</a>
<a href="signup.jsp" class="btn btn-primary btn-lg">Sign Up</a>
</div>
</center>

<img alt="connect" src="social-networking.jpg" height = "800px" width = "800px" align="right" style="top:70px;position:relative">

<div class="preface">Welcome.<br>Feel Free to be a part of the GoTweet Family. Hope you have a good time.</div>

<div style="bottom:20px;left:20px;position: absolute;"> <a href = "about.html">About Us</a> </div>

</body>
</html>
