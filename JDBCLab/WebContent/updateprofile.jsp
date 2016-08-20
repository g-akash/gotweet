
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="object.*"  %>
<%@ page import="database.Functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String id = (String)session.getAttribute("id");
List<Junta> lj = new ArrayList<Junta>();
lj = Functions.getjunta(id);
String name = lj.get(0).name;
String dob = lj.get(0).dob;
String email = lj.get(0).email_id;
%>

<center> <h2>Update Profile</h2></center>

<div class="container">
  <form class="form-horizontal" action="Updateprofile" method="post">

    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Username:</label>
      <div class="col-sm-10">
		<p class="form-control-static"><%=id %> </p>
      </div>
    </div>

   	<input type="hidden"  name = "id" value ="<%=id%>"  >
   
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="<%=name %>" name="name">
      </div>
    </div>


    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Email:</label>
      <div class="col-sm-10">          
        <input type="email" class="form-control" id="email" name="email" value="<%= email%>">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">DOB:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" name="dob" value= "<%=dob %>" >
      </div>
    </div>


    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form>
</div>

<center><a href="changepassword.jsp?id=<%=id %>" class="btn btn-link">Change Password</a></center>
</body>
</html>
