
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
<title>Profile Page</title>
<style type="text/css">
 form.inline{
  display : inline-block;
}
.floatright{
/*	float:right; */
	position:fixed;
	right:40px;
}
</style>
</head>
<body>
<% 
String id;
String id1="";
id1 = request.getParameter("id1");
id = (String)session.getAttribute("uid");
//if(id==null || id1==null){
//	response.sendRedirect("login.jsp");   // go to the login page.	
//	System.out.print("rfrf");
//}
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
        <%if(id.equals(id1)){
           out.print("<li><a href=\"updateprofile.jsp\">Edit Profile</a></li>");
           out.print("<li><a href=\"message.jsp\">Messages</a></li>");
        }
        %>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
	<h2>&nbsp&nbsp&nbsp<%=Functions.getname(id1) %></h2><br>
	<% 
	if(id1.equals("jarvis")){		
	%>
<figure>	<img src="jarvis.jpg"  style="width:140px;align:left;position:absolute;left:20px" alt="image">
</figure>
	<%}else {%>
<figure>	<img src="a.jpg"  style="height:140px;align:left;position:absolute;left:10px" alt="image">
</figure>
<%}
if(!id.equals(id1)){
	if(!Functions.isfollow(id, id1)){
%>
	<form action="Follow" method="post">
	<input type="hidden"  name = "id" value ="<%=id%>"  >
	<input type="hidden"  name = "id1" value ="<%=id1%>"  >
	<input type = "submit" value="Follow" class="btn btn-success btn-lg floatright">
	</form>
<%}
	else{
%>
	<form action="Unfollow" method="post">
	<input type="hidden"  name = "id" value ="<%=id%>"  >
	<input type="hidden"  name = "id1" value ="<%=id1%>"  >
	<input type = "submit" value="Unfollow" class="btn btn-danger btn-lg floatright">
	</form>	
<%}
}
%>
<br>
<center>
<h4><a href="followers.jsp?id1=<%=id1%>">Followers : <%= Functions.getmyfollowers(id1) %></a></h4>
<h4><a href="followed.jsp?id1=<%=id1%>">Following : <%= Functions.getiamfollowing(id1)%></a></h4>
<br>
<h3>List of tweets</h3><br>

<table width="50%" class="table-hover">
<tr>
</tr>
<%
List<Tweets> tlist = new ArrayList<Tweets>();
tlist = Functions.getweets(id1);
for(int i=0;i<tlist.size();i++){
  %><tr><td><span style="text-align:left"> <b><%=Functions.getname(tlist.get(i).user_id) %> </b>@<a href="profilepage.jsp?id1=<%=tlist.get(i).user_id%>"><%=tlist.get(i).user_id %> </a></span><%
%><br>
   <%= tlist.get(i).body %>
  <a href="comment.jsp?twid=<%=tlist.get(i).tweet_id%>"> Read More </a><br>
  
  <div style="text-align:left"><form action="Like1" method="post" class="inline">
  <input type="hidden"  name = "tid" value ="<%=tlist.get(i).tweet_id%>"  >
  <input type="hidden"  name = "id" value ="<%=id%>" >
  <input type="hidden"  name = "id1" value ="<%=id1%>" >
  <input type = "submit" value="Like" class="btn btn-primary btn-xs">
  </form>
<% 
int num_likes = tlist.get(i).num_likes;
int num_retweets = tlist.get(i).num_retweets;
out.print("<span class=\"badge\"><h7>"+num_likes+"</h7></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");

%>  
  <form action="Retweet1" method="post" class="inline">
  <input type="hidden"  name = "tid" value ="<%=tlist.get(i).tweet_id%>"  >
  <input type="hidden"  name = "id" value ="<%=id%>"  >
  <input type="hidden"  name = "id1" value ="<%=id1%> " >
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
