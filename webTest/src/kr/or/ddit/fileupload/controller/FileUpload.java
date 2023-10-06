package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

/*
	- Servlet 3.0이상에서 파일 업로드를 처리하려면 서블릿에 @MultipartConfig 에노테이션을 설정해야 한다.
	
	- @MultipartConfig 에노테이션에 설정하는 변수들..
	  1) location : 업로드한 파일이 임시적으로 저장될 경로를 지정한다.(기본값 : "")
	  2) fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한
	  		임시 디렉토리에 저장한다. (단위 : byte, 기본값 : 0 (무조건 임시 디렉토리 사용))
	  3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1(무제한))
	  4) maxRequestSize : 서버로 전송되는 Request데이터 전체의 최대 크기 (단위 : byte, 기본값 : -1(무제한))
*/
@WebServlet("/fileUpload.do")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 100
)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get방식으로 요청할 때는 file을 업로드할 수 있는 폼 문서로 이동시킨다.
		request.getRequestDispatcher("/basic/fileupload/fileUploadForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드된 파일들이 저장될 폴더 설정 (서버 컴퓨터에 있는 폴더)
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 지정된 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터 데이터 : " + userName);
		
		//-------------------------------------------
		
		// 수신 받은 파일 데이터 처리하기
		
		String fileName = "";	// 파일명이 저장될 변수 선언
		
		// Upload한 파일 목록이 저장될 List객체 생성
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		/*
		- Servlet 3.0 이상에서 새롭게 추가된 Part객체 처리용 메서드
		1) Request객체.getParts() ==> 전체 Part객체를 Collection객체에 담아서 반환한다.
		2) Request객체.getPart("이름") ==> 지정된 '이름'을 가진 개별 Part객체를 반환한다.
			'이름' ==> <form>태그 안의 입력 요소의 name속성값으로 구별한다.)
		*/
		
		// 전체 Part객체 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			
			fileName = extractFileName(part);	// Part에서 파일명을 구해온다.
			
			// 구해온 파일명이 빈문자열("")이면 파일이 아닌 일반 파라미터가 된다.
			if(!"".equals(fileName)) {	// 파일 여부를 검사

				// 1개의 Upload 파일에 대한 정보를 저장할 VO객체 생성
				FileInfoVO fvo = new FileInfoVO();
				
				fvo.setFile_writer(userName);		// 작성자를 VO에 저장
				fvo.setOrigin_file_name(fileName);	// 원래의 파일명을 VO에 저장
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID를 이용하여 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString() + "_" + fileName;
				
				// 이 값을 VO에 저장 파일명으로 저장
				fvo.setSave_file_name(saveFileName);
				
				// 파일 크기는 Part객체의 getSize()메서드를 이용하여 구한다. (단위 : byte)
				// byte값 ==> KB로 변환해서 저장
				fvo.setFile_size((long)Math.ceil(part.getSize()/1024.0));
				
				// 파일을 지정한 폴더에 저장하기
				// part객체의 write()메서드를 이용한다.
				try {
					part.write(uploadPath + File.separator + saveFileName);	// 파일 저장
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 저장한 파일 정보를 List에 추가
				fileList.add(fvo);
				
			} // if문 끝...
			
		} // for문의 끝...
		
		// Upload된 파일 정보를 DB에 추가한다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		// List에 저장된 데이터들을 하나씩 차례로 DB에 추가한다.
		for(FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// 작업이 완료되면 파일 목록을 보여주는 페이지로 이동한다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
	} // doPost()메서드 끝...
	
	//----------------------------------------
	/*
	- Part객체의 구조
	
	1) 파일이 아닌 일반 파라미터 데이터일 경우
	
	------------------------3242dsfsd34dsjk ==> Part를 구분하는 구분선
	content-disposition: form-data; name="username"	==> 파라미터 명
						==> 빈줄
	Hong				==> 파라미터 값
	
	*/
	
	/*
	2) 파일일 경우
	
	------------------------3242dsfsd34dsjk ==> Part를 구분하는 구분선
	content-disposition: form-data; name="upfile1"; filename="test1.txt" ==> 파일 정보
	content-type : text/plain		==> 파일의 종류
									==> 빈줄
	abcd1234						==> 파일의 내용
	
	*/
	
	// Part구조 안에서 파일명을 찾는 메서드
	// ==> 파일일 때는 '해당 파일명'을 반환하고 파일이 아닐 때는 빈문자열("")을 반환한다.
	private String extractFileName(Part part) {
		String fileName = "";	// 반환값이 저장될 변수
		String value = part.getHeader("content-disposition");
		// value = form-data; name="upfile1"; filename="test1.txt"
		
		String[] items = value.split(";");
		// form-data
		// name="upfile1"
		// filename="test1.txt"
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		
		return fileName;
	}

}
