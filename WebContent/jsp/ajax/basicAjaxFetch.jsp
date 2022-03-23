<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String contextPath = request.getContextPath();
%>
<body>
	<h1 id="title">Before</h1>
	<hr>
	<button id="btnGet">Get</button>
	<script>
		window.onload = function(){
			document.querySelector("#btnGet").onclick = function(){
				getAjax();
			}
		}	
		
		async function getAjax(){
			let response = await fetch("<%= contextPath %>/BasicAjaxServlet"); // text
			let text = await response.text();
			document.querySelector("#title").innerHTML = text;
		}
	</script>
</body>
</html>