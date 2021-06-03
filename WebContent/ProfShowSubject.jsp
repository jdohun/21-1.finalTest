<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="finalTest.vo.SubjectVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
 	ArrayList<SubjectVO> List = (ArrayList<SubjectVO>)request.getAttribute("List");
%>
</head>
<body>
	<table>
		<tr>
			<th>과목번호</th>
			<th>과목명</th>
			<th>최대인원</th>
			<th>수강인원</th>
		</tr>
		<%
			if(List != null && !List.isEmpty()){
				for(int i=0; i < List.size(); ++i){
					SubjectVO subject = List.get(i);
		%>
		<tr>
			<td>
				<a href="ProfShowSubStu.prof?sub=<%=subject.getName()%>&sId=<%=subject.getId()%>"><%=subject.getId()%></a>
			</td>
			<td><%=subject.getName() %></td>
			<td><%=subject.getCount() %></td>
			<td></td>
		</tr>
		<%					
				}
			}
			else{
		%>
			<tr><td colspan="4">등록한 강의가 없습니다</td></tr>
		<%
			}
		%>
	</table>
	<br><a href="index.jsp">Menu</a>
</body>
</html>