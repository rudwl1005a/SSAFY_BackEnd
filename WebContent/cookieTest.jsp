<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cookie Test</h1>
	<%
		// java code가 들어감
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			System.out.println(c.getName());
			out.println("<h4>" + c.getName() + " / " + c.getValue() + "</h4>");
		}
	%>
</body>
</html>