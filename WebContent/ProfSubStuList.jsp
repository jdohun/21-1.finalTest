<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="finalTest.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	ArrayList<MemberVO> List = (ArrayList<MemberVO>)request.getAttribute("List");
	String sub = (String)request.getAttribute("sub");
%>
</head>
<body>
	<p><%=sub%> : 총 수강인원 : <%=List.size() %>명</p>
	<table>
		<tr>
			<th>id</th>
			<th>이름</th>
		</tr>
		<%
			if(List != null && !List.isEmpty()){
				for(int i=0; i < List.size(); ++i){
					MemberVO member = List.get(i);
		%>
		<tr>
			<td><%=member.getId() %></td>
			<td><%=member.getName() %></td>
		</tr>
		<%					
			}
		} %>
	</table>
	<br><a href="index.jsp">Menu</a>
</body>
</html>