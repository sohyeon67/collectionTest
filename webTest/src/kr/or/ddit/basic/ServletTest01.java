package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서블릿이란? 컨테이너(서블릿 엔진)에 의해 관리되는 자바 기반 웹 컴포넌트로서
 * 			동적인 웹 컨텐츠 생성을 가능하게 해 준다.
 * 
 * 이 예제는 배포 서술자(web.xml)을 이용하여 실행할 Servlet을 등록하여 처리하는 예제이다.
 */


/*
 * http://localhost:80/webTest/servletTest01.do
 * 
 * - http 		==> 프로토콜
 * - localhost 	==> 서버 컴퓨터 이름(또는 도메인명) 또는 서버의 IP주소
 * - 80			==> Port번호 (80번일 경우 생략이 가능하다.)
 * - /webTest	==> 컨텍스트 패스 (보통 웹 프로젝트 이름으로 지정한다.)
 * - /servletTest01.do ==> 서블릿 요청 패턴
 * 
 */



// servlet클래스는 HttpServlet클래스를 상속해서 작성한다.
public class ServletTest01 extends HttpServlet {
	// 이 클래스에서는 service()메서드 또는 doGet()메서드, doPost()메서드를 재정의해서
	// 처리할 내용을 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 자동으로 호출된다.
	
	// 위 메서드의 매개변수로 주어지는 객체들
	// 1) HttpServletRequest객체 ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// 2) HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	// 응답 문서의 인코딩 방식 설정
		response.setContentType("text/html; charset=utf-8");	// 응답 문서의 ContentType 설정
		
		// 처리한 내용을 응답으로 보내기 위해서 PrintWriter객체를 생성한다.
		PrintWriter out = response.getWriter();	// 클라이언트와 연결된 PrintWriter객체 생성
		
		// 처리한 내용을 출력한다. (==> 응답으로 전송한다.)
		
		// 방법1 : append()메서드 이용하기
		out.append("<html>")
			.append("<head><meta charset='utf-8'><title>첫번째 Servlet</title></head>")
			.append("<body>")
			.append("<h2 style='text-align:center;'>")
			.append("안녕하세요, 첫번째 Servlet 프로그램입니다.</h2>")
			.append("</body>")
			.append("</html>");
	}

	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
