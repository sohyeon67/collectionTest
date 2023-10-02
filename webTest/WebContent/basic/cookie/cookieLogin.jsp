<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String userId = "";
String chk = "";

Cookie[] cookieArr = request.getCookies();
if(cookieArr!=null) {
	for(Cookie cookie : cookieArr) {
		if("id".equals(cookie.getName())) {
			userId = cookie.getValue();
			chk = "checked";
			break;
		}
	}
}

%>
</head>
<body>
<form action="/webTest/cookieLoginServlet.do" method="get">
<table>
	<tr>
		<td width="50px">ID : </td>
		<td><input type="text" placeholder="ID 입력하세요." name="id" value="<%= userId%>"></td>
	</tr>
	<tr>
		<td>PASS : </td>
		<td><input type="password" placeholder="PassWord 입력하세요." name="pass"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="checkbox" name="check" <%= chk%>> id 기억하기<br>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="submit" value="Login">
		</td>
	</tr>
</table>
</form>
</body>
</html>