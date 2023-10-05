<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script>
$(function() {
	$('#lprodBtn').on('click', function() {
		$.ajax({
			url : '<%= request.getContextPath()%>/lprod/lprodList.do',
			type : 'post',
			// data : ==> 서버로 보낼 파라미터가 없으면 생략 가능
			
			success : function(resData) {
				let htmlCode = "<table border='1'>";
				htmlCode += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>"
				
				$.each(resData, function(i, v) {
					htmlCode += "<tr>";
					htmlCode += "<td>" + v.lprod_id + "</td>";
					htmlCode += "<td>" + v.lprod_gu + "</td>";
					htmlCode += "<td>" + v.lprod_nm + "</td>";
					htmlCode += "</tr>";
				});
				htmlCode += "</table>";
				
				$('#result').html(htmlCode);
			},
			dataType : 'json'
		});
	});
	
	$('#lprodBtn2').on('click', function() {
		location.href = "<%= request.getContextPath()%>/lprod/lprodList2.do";
	});
});
</script>
</head>
<body>
<form>
	<input type="button" id="lprodBtn" value="Lprod자료 가져오기(Ajax)">
	<input type="button" id="lprodBtn2" value="Lprod자료 가져오기(Non Ajax)">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>