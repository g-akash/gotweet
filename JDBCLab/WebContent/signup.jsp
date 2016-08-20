
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>SignUp Form</h2>

<div class="container">
  <form class="form-horizontal" action="Signup" method="post">

    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Username:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" placeholder="Enter username" name="id">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" placeholder="Enter name" name="name">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Retype Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd1">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Email:</label>
      <div class="col-sm-10">          
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">DOB:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" placeholder="YYYY-MM-DD" name="dob">
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


<!--  
<form action="Signup" method="post">

<br>
Enter first name:
<input type = "text" name = "fname">
<br>
Enter last name:
<input type = "text" name = "lname">
<br>
Enter id:
<input type = "text" name = "id">
<br>
Enter password:
<input type = "password" name = "pwd">
<br>
Retype Password:
<input type = "password" name = "pwd1">
<br>
Enter Date Of Birth:
<input type = "text" name = "dob" value="YYYY-MM-DD">
<br>
Enter email:
<input type = "text" name = "email">
<br>
<input type="submit"><br>

</form>
-->
</center>
</body>
</html>
