<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload = function() {
	document.getElementById("getBtn").addEventListener("click", function() {
		location.href = "http://localhost/webTest/servletTest03.do";
	});
}
</script>
</head>
<body>
<h2>서블릿 요청 연습</h2>
<br><hr><br>

<h3>GET방식 요청하기1 ==> 링크 방식</h3>
<a href="http://localhost/webTest/servletTest03.do">Get방식 요청 1</a>
<br><hr><br>

<h3>GET방식 요청하기2 ==> form태그의 method속성 이용하기</h3>
<!-- 
	<form>태그의 속성
	action속성 ==> <form>태그에서 구성하여 서버로 보낸 데이터를 받아서 처리할
				문서명을 지정하는 속성
	method속성 ==> get 또는 post (이 속성이 생략되면 기본적으로 GET방식으로 처리된다.)
 -->
<form action="http://localhost/webTest/servletTest03.do" method="get">
	<input type="submit" value="Get방식 요청2">
</form>
<br><hr><br>

<h3>GET방식 요청하기3 ==> javaScript의 location.href속성 이용하기</h3>
<form>
	<input type="button" value="get방식 요청3" id="getBtn">
</form>
<br><hr><br>

<h3>POST방식 요청하기 ==> form태그의 method속성을 'post'로 지정한 경우</h3>
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit" value="POST방식 요청">
</form>

</body>
</html>