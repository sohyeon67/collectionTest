package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// userid, pass, chkid 파라미터의 값을 구한다.
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("pass");
		String chkId = request.getParameter("chkid");	// checkbox의 값
		
		// userid값을 갖는 Cookie객체 생성
		Cookie loginCookie = new Cookie("USERID", userId);
		
		if(chkId==null) { // 체크박스가 체크되지 않으면...
			loginCookie.setMaxAge(0);	// Cookie를 삭제하기 위해서 유지시간을 0으로 설정
		}
		
		// 쿠키를 다시 저장한다.
		response.addCookie(loginCookie);
		
		String contextPath = request.getContextPath();
		
		// id: test, pass: 1234
		// 로그인 성공 cookieMain.jsp, 실패 cookieLogin.jsp
		if(userId.equals("test") && userPass.equals("1234")) {
			// Dispatcher하면 딜레이 생김..?
			
			response.sendRedirect(contextPath + "/basic/cookie/cookieMain.jsp");
			//request.getRequestDispatcher("/basic/cookie/cookieMain.jsp").forward(request, response);;
		} else {
			response.sendRedirect(contextPath + "/basic/cookie/cookieLogin.jsp");
			//request.getRequestDispatcher("/basic/cookie/cookieLogin.jsp").forward(request, response);;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}