<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
// JSP문서에서 HttpSession객체는 'session'이라는 이름으로 이미 생성되어 있다.
String sessionValue = (String)session.getAttribute("userId");
String display1 = "";
String display2 = "";

if(sessionValue!=null) {
	display1 = "none";
} else {
	display2 = "none";
}

%>
</head>
<body>
<form action="<%= request.getContextPath()%>/sessionLogin.do" method="post" style="display : <%= display1%>;">
<table>
<tr>
	<td width="50px"> ID : </td>
	<td><input type="text" name="userid" placeholder="ID를 입력하세요"></td>
</tr>
<tr>
	<td> PASS : </td>
	<td><input type="password" name="pass" placeholder="PassWord를 입력하세요"></td>
</tr>
<tr>
	<td colspan="2" style="text-align: center;"><input type="submit" value="Login"></td>
</tr>
</table>
</form>
<div id="logout" style="display : <%= display2%>;">
	<h3><%= sessionValue%>님 반갑습니다.</h3>
	<a href="<%= request.getContextPath()%>/sessionLogout.do">로그아웃</a>
</div>
</body>
</html>