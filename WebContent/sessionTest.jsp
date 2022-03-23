<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Session Test</h1>
	<%
		String userId = (String) session.getAttribute("userId");
		int[] intArray = (int[]) session.getAttribute("intArray");
		List<String> strList = (List<String>) session.getAttribute("strList");
		List<?> strList2 = (List<?>) session.getAttribute("strList");
	%>
	
	<%
		if(userId != null){
	%>
			<b><%= userId %></b>
	<%
		}
	%>
	<hr>
	<%
		if(intArray != null){
			for(int i : intArray){
	%>
				<b><%= i %> </b>
	<%
			}
		}
	%>
	<hr>
	<%
		if(strList != null){
			for(String str : strList){
	%>
				<b><%= str %> </b>
	<%
			}
		}
	%>
	<hr>
	<%
		if(strList2 != null){
			for(Object str : strList2){
	%>
				<b><%= (String) str %> </b>
	<%
			}
		}
	%>
</body>
</html>