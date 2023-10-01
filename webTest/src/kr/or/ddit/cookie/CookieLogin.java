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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("id");
		String userPass = request.getParameter("pass");
		String check = request.getParameter("check");
		
		// 체크하고 로그인하면 id를 쿠키에 저장, 쿠키에 id값이 저장되어 있으면 id가 나타나도록 함. 체크박스 유지
		if(check!=null) {
			Cookie idCookie = new Cookie("id", userId);
			response.addCookie(idCookie);
		} else {
			// 해제하고 로그인하면 쿠키의 id를 삭제하고 체크박스 해제
			Cookie[] cookieArr = request.getCookies();
			for(Cookie cookie : cookieArr) {
				if("id".equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		// id: test, pass: 1234
		// 로그인 성공 cookieMain.jsp, 실패 cookieLogin.jsp
		if(userId.equals("test") && userPass.equals("1234")) {
			request.getRequestDispatcher("/basic/cookie/cookieMain.jsp").forward(request, response);;
		} else {
			request.getRequestDispatcher("/basic/cookie/cookieLogin.jsp").forward(request, response);;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
