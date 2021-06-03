<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String loginType = (String)request.getSession().getAttribute("loginType");
		if(loginType != null){
			if(loginType.equals("prof")){
				response.sendRedirect("MenuProf.jsp");
			}
			else if(loginType.equals("stu")){
				response.sendRedirect("MenuStudent.jsp");
			}
		}
		
		String result = (String)request.getAttribute("result");
		if(result != null){
	%>
		<p><%=result %></p>
	<%
	}
	%>
	<h3>YongIn LMS</h3>
	
	<form action="login.do" method="post">
		ID: <input type="text" name="id"><br>
		Password : <input type="text" name="pwd"><br>
		<input type="radio" id="Professor" name="loginType" value="prof" checked="checked">Professor
		<input type="radio" id="Student" name="loginType" value="stu">Student
		<br><input type="submit" value="LOGIN">
	</form><br>
	
</body>
</html>