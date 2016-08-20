
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.Functions" %>
<%@ page import="java.util.*" %>
<%@ page import="object.*"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
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
</head>
<body style="background-color:E0ffff">
<% 
String id = (String)session.getAttribute("id");
if(id==null){
	response.sendRedirect("login.jsp");   // go to the login page.	
}
//String id = request.getParameter("id");
//session.removeAttribute("id");
//out.println(id+"<br><br>");
session.setAttribute("id", id);
session.setAttribute("uid", id);
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
        <li><a href="updateprofile.jsp">Edit Profile</a></li>
        <li><a href="message.jsp">Messages</a></li>
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

<!--  
Go to my profile:
<a href="profilepage.jspid1=<%=id%>"><%=id %> </a>
-->
<form action="Tweet" method="post" role="form">
<div class="col-xs-4">
<label for="ex1">Write Something</label>
<input type="text" class="form-control" id="ex1" name="body">
</div>
<input type="hidden"  name = "id" value ="<%=id%>" >
<br><br>
<input type="submit" class="link" >
</form>
<br>
<center>
<h3>List of tweets</h3><br>

<table width="50%" class="table-hover">
<tr>
</tr>
<%
List<Tweets> tlist = new ArrayList<Tweets>();
tlist = Functions.getpublictweets(id);
for(int i=0;i<tlist.size();i++){
	%><tr><td><span style="text-align:left"> <b><%=Functions.getname(tlist.get(i).user_id) %> </b>@<a href="profilepage.jsp?id1=<%=tlist.get(i).user_id%>"><%=tlist.get(i).user_id %> </a></span><%
%><br>
	 <%= tlist.get(i).body %>
	<a href="comment.jsp?twid=<%=tlist.get(i).tweet_id%>"> Read More </a><br>
	
	<div style="text-align:left"><form action="Like" method="post" class="inline">
	<input type="hidden"  name = "tid" value ="<%=tlist.get(i).tweet_id%>"  >
	<input type="hidden"  name = "id" value ="<%=id%>" >
	<input type = "submit" value="Like" class="btn btn-primary btn-xs">
	</form>
<% 
int num_likes = tlist.get(i).num_likes;
int num_retweets = tlist.get(i).num_retweets;
out.print("<span class=\"badge\"><h7>"+num_likes+"</h7></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");

%>	
	<form action="Retweet" method="post" class="inline">
	<input type="hidden"  name = "tid" value ="<%=tlist.get(i).tweet_id%>"  >
	<input type="hidden"  name = "id" value ="<%=id%>"  >
	<input type = "submit" value="Retweet" class="btn btn-primary btn-xs">
	</form>
<% 
out.print("<span class=\"badge\"><h7>"+num_retweets+"</h7></span></div><br>");

//out.print("#retweets"+num_retweets+"<br><br></td></tr>");
}
%>
</table>
</center>
</body>
</html>
