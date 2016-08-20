<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="database.StudentInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>Result</h3>
<p>
<%
	String id = request.getParameter("id");
	String option = request.getParameter("option");
	
	if(id==null || id.equalsIgnoreCase("") ||
			option==null || option.equalsIgnoreCase("") ){
		out.print("Invalid input");
	} else if(option.equalsIgnoreCase("getCourses")){
		List<String> courseList=StudentInfo.getCourseList(id);
		for(String course:courseList) {
			out.print(course+"<br/>");
		}
	} else if(option.equalsIgnoreCase("getInstructors")){
		
	} else if(option.equalsIgnoreCase("getYearCredit")) {
		
	} else{
		out.print("Option not supported");
	}

%>
</p>
</center>
</body>
</html>