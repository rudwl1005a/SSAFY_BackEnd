<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("list");
%>
<body>
	<h4>사용자 목록</h4>
	<table>
		<tbody>
<%
			if(list != null){
				for(String name : list) {
%>
					<tr>
						<td><%= name %></td>
					</tr>
<%		
				}
			}
%>

<%
			if(list == null || list.size() == 0){
%>
				<tr>
					<td>사용자가 없습니다.</td>
				</tr>
<%
			}
%>
		</tbody>
	</table>
</body>
</html>