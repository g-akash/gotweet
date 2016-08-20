<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.Functions" %>
<%@ page import="java.util.*" %>
<%@ page import="object.*"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">

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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<body>
<%
String id = (String)session.getAttribute("id");
String id1 = request.getParameter("id1");
List<Junta> lj = Functions.searchpeople(id1);
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
      <a class="navbar-brand" href="homepage.jsp">Home</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="profilepage.jsp?id1=<%=id%>"><%=id %> <span class="sr-only">(current)</span></a></li>
        <li><a href="updateprofile.jsp?id=<%=id %>">Edit Profile</a></li>
        <li><a href="message.jsp?uid=<%=id%>">Messages</a></li>
      </ul>

      <form class="navbar-form navbar-left" role="search" action="searchresults.jsp" method="post">
        <div class="form-group">
		  <input type="hidden"  name = "id" value ="<%=id%>" >
          <input type="text" name="id1" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<center>
<h3>Displaying the search results for string "<%=id1%>"</h3>
<br><br>
<table>
<%
for(int i=0;i<lj.size();i++){
%>

<tr><td><span style="text-align:left"> <b>Name :</b><%=Functions.getname(lj.get(i).username) %>
</span><td/></tr>

<tr><td><span style="text-align:left"> <b>Handle :</b>@<a href="profilepage.jsp?id1=<%=lj.get(i).username%>"><%=lj.get(i).username%> </a>
</span><br><br><td/></tr>

<%}%>
</table>
</center>
</body>
</html>