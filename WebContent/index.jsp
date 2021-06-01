<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String result = (String)request.getAttribute("result"); %>
	<% if(result != null){
	%>
		<p><%=result %></p>
	<%
	}
	%>
	<h3>YongIn LMS</h3>
	
	<h4>교수 로그인</h4><br>
	<form action="login.do?select=prof" method="post">
		ID: <input type="text" name="id"><br>
		Password : <input type="text" name="pwd"><br>
		<br><input type="submit" value="LOGIN">
	</form><br>
	
	<h4>학생 로그인</h4><br>
	<form action="login.do?select=stu" method="post">
		ID: <input type="text" name="id"><br>
		Password : <input type="text" name="pwd"><br>
		<br><input type="submit" value="LOGIN">
	</form>
	
	<!-- <input type="radio" id="Professor" name="select" checked="checked" onchange="javascript: form.action='login.do?select=prof';">Professor
		<input type="radio" id="Student" name="select" onchange="javascript: form.action='login.do?select=stu';">Student -->
</body>
</html>