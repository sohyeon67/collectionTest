package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

// DB에 저장된 파일 목록을 가져와 View로 보내는 Servlet
@WebServlet("/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		// 전체 목록 가져오기
		List<FileInfoVO> fileList = service.getAllFileinfo();
		
		// 가져온 전체 목록을 View로 보낸다.
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/basic/fileupload/fileList.jsp")
					.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
