<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.Functions" %>
<%@ page import="java.util.*" %>
<%@ page import="object.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<title>messages</title>
<style type="text/css">
 form.inline{
  display : inline-block;
}
.mysize{
width: 300px;
}
</style>
</head>
<body>
<%
String id = (String)session.getAttribute("uid");
if(id==null){
	response.sendRedirect("login.jsp");   // go to the login page.	
}
%>


<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="homepage.jsp?">Home</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="profilepage.jsp?id1=<%=id%>"><%=id %> <span class="sr-only">(current)</span></a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>


<pre><center>View Messages</center></pre><br>

<center>
Want to write to someone?
<form action="Message" method="post">
To:
<input type="text" name="id1">
<br><br>
<!-- <input type="text" name="body" size="40" placeholder="Write Here"> -->
<textarea rows="4" cols="50" placeholder="Write Here" name="body"></textarea>
<input type="hidden" name = "id" value ="<%=id%>" ><br>
<input type="submit"  class="btn btn-default">
</form>
</center>
<br>
<br>

<center>
<table width="50%">
<%
List<Messages> mlist = new ArrayList<Messages>();
mlist = Functions.getmessages(id);
for(int i=0;i<mlist.size();i++){
	%>
	<tr>
	<td>
	<span style="text-align:left"><b>From:</b> <%= mlist.get(i).sender %> </span><br>
	<span style="text-align:left"><b>To:</b> <%= mlist.get(i).receiver %> </span><br>
	 <b>Message:</b> <%= mlist.get(i).body %><br><br></td></tr>	
<% 
}
%>
</table>
</center>

</body>
</html>
