<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="finalTest.vo.SubjectVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>	
<% ArrayList<SubjectVO> List = (ArrayList<SubjectVO>)request.getAttribute("List");%>
<body>
	<table>
		<tr>
			<th>과목번호</th>
			<th>과목명</th>
		</tr>
	<% 
		if(List != null && !List.isEmpty()){
			for(int i = 0; i < List.size(); ++i){
				SubjectVO subject = List.get(i);
	%>
		<tr>
			<td><%=subject.getId() %></td>
			<td><%=subject.getName() %></td>
			<td>
				<form action="StuSubDelete.stu" method="post">
					<input type="hidden" name="sId" value=<%=subject.getId()%>>
					<input type="submit" value="수강취소">
				</form>
			</td>
		</tr>
	<%	
			}
		}
		else{
	%>
		<tr><td colspan="2">신청한 과목이 없습니다.</td></tr>
	<%	}	%>
	</table>
	<br><a href="index.jsp">Menu</a>
</body>
</html>