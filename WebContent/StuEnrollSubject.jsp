<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="finalTest.vo.SubjectVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% SubjectVO subject = (SubjectVO)request.getAttribute("subject"); %>
<body>
	<h4>Subject</h4>
	<form action="searchSub.stu" method="get">
		ID : <input type="text" name="sId">
		<input type="submit" value="Search">
	</form>
	
	<%
		if(subject != null){
	%>
		<table>
			<tr>
				<td><%=subject.getId() %></td>
				<td><%=subject.getName() %></td>
				<td>
					<form action="StuEnroll.stu" method="post">
						<input type="hidden" name="sId" value=<%=subject.getId() %>>
						<input type="submit" value="Enroll">
					</form>
				</td>
			</tr>
		</table>
	<%} %>
	<br><a href="MenuStudent.jsp">Menu</a>
</body>
</html>