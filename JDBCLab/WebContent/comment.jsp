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
<title>Comments</title>
</head>
<body>
<%


String id = "";
id = (String)session.getAttribute("uid");
int tid = Integer.parseInt(request.getParameter("twid"));
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
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<pre><center>View Comments</center></pre><br>



<center>
Want to pass a comment?
<form action="Comment" method="post">
<br>
<!-- <input type="text" name="body" size="40" placeholder="Write Here"> -->
<textarea rows="3" cols="50" placeholder="Write Here" name="body"></textarea>
<input type="hidden" name = "id" value ="<%=id%>" ><br>
<input type="hidden" name = "tid" value ="<%=tid%>" >
<input type="submit"  class="btn btn-default">
</form>
</center>
<br>
<br>

<%
Tweets tw = Functions.getatweet(tid);
%>
<center>
<table width="50%" style="background-color:#E5FFFF">
<tr>
<td style="padding:10px" height="100px">
<span style="text-align:left"> <b><%=tw.user_id %> </b>@<a href="profilepage.jsp?id1=<%=tw.user_id%>"><%=tw.user_id%> </a></span>
<br>
<%=tw.body%>
</td>
</tr>
</table>
<br>
<table width="50%" class="table-hover">
<%
List<Comments> clist = new ArrayList<Comments>();
clist = Functions.getcomments(tid);
for(int i=0;i<clist.size();i++){
  %>
  <tr>
  <td>
  <span style="text-align:left"> <b><%=Functions.getname(clist.get(i).user_id) %> </b>@<a href="profilepage.jsp?id1=<%=clist.get(i).user_id%>"><%=clist.get(i).user_id %> </a></span>
  <br>
   <b>Body: </b><%=clist.get(i).body%><br><br>
   </td>
   </tr>
<%
}
%>
</table>
</center>

</body>
</html>