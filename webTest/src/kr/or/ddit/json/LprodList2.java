package kr.or.ddit.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.LprodVO;

@WebServlet("/lprod/lprodList2.do")
public class LprodList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// Service대신 Dao객체 이용
		LprodDaoImpl dao = LprodDaoImpl.getInstance();
		
		List<LprodVO> lprodList = dao.getAllLprod();
		
		request.setAttribute("lprodList", lprodList);
		
		request.getRequestDispatcher("/basic/json/lprodList.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
