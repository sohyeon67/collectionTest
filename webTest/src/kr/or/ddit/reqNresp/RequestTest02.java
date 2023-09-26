package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 값 받기
		int num1 = Integer.parseInt(request.getParameter("num1"));
		String op = request.getParameter("op");
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		double result = 0.0;	// 계산된 결과가 저장될 변수
		boolean calcOk = true;
		switch(op) {
			case "+": result = num1 + num2; break;
			case "-": result = num1 - num2; break;
			case "*": result = num1 * num2; break;
			case "/": 
				if(num2!=0) {
					result = (double)num1 / num2;
				} else {
					calcOk = false;
				}
				break;
			case "%": 
				if(num2!=0) {
					result = num1 % num2;
				} else {
					calcOk = false;
				}
				break;
		}
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>"
				+ "<title>Request연습 계산기</title></head>");
		out.println("<body>");
		out.println("<h2>계산 결과</h2><hr>");
		out.printf("%d %s %d = ", num1, op, num2);
		if(calcOk) { // 계산 성공
			out.println(result);
		} else { // 계산 실패
			out.println("계산 불능(0으로 나누기)");
		}
		
		out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
