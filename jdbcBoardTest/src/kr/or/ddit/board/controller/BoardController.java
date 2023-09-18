package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class BoardController {
	private IBoardService service;
	private Scanner scan;
	
	// 생성자
	public BoardController() {
		service = new BoardServiceImpl();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new BoardController().startBoard();
	}
	
	public void startBoard() {
		while(true) {
			int choice = displayMenu();
			scan.nextLine();
			switch(choice) {
			case 1:
				insertBoard();
				break;
			case 2:
				detailBoard();
				break;
			case 3:
				//searchBoard();
				break;
			case 0:
				System.out.println();
				System.out.println("게시판 프로그램 종료....");
			default:
				System.out.println();
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요...");
			}
		}
	}
	
	private void detailBoard() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int no = scan.nextInt();
		
		BoardVO boardVo = service.detailBoard(no);
		
		System.out.println();
		System.out.println(no + "번글 내용");
		System.out.println("------------------------------------------------------------");
		if(boardVo==null) {
			System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
			return;
		} else {
			System.out.println("- 제  목 : " + boardVo.getTitle());
			System.out.println("- 작성자 : " + boardVo.getWriter());
			System.out.println("- 내  용 : " + boardVo.getContent());
			System.out.println("- 작성일 : " + boardVo.getDate());
			System.out.println("- 조회수 : " + boardVo.getCnt());
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		int choice = scan.nextInt();
		scan.nextLine();
		System.out.println();
		
		switch(choice) {
		case 1:
			updateBoard(no);
			break;
		case 2:
			deleteBoard(no);
			break;
		case 3:
			return;
		
		}
		
	}
	
	private void updateBoard(int no) {
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 데이터들을 VO객체에 저장한다.
		BoardVO boardVo = new BoardVO();
		boardVo.setNo(no);
		boardVo.setTitle(title);
		boardVo.setContent(content);
				
		int cnt = service.updateBoard(boardVo);
		
		if (cnt > 0) {
			System.out.println(no + "번글이 수정되었습니다.");
		} else {
			System.out.println(no + "번글이 수정되지 않았습니다.");
		}
				
		
	
	}
	
	private void deleteBoard(int no) {
		
	}
	
	private void insertBoard() {
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 데이터들을 VO객체에 저장한다.
		BoardVO boardVo = new BoardVO();
		boardVo.setTitle(title);
		boardVo.setWriter(writer);
		boardVo.setContent(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if (cnt > 0) {
			System.out.println("새글이 추가되었습니다.");
		} else {
			System.out.println("새글이 추가되지 않았습니다.");
		}
		
	}
	
	private void displayAllBoard() {
		List<BoardVO> boardList = service.getAllBoard();
		
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		if(boardList==null || boardList.size()==0) {
			System.out.println("\t등록된 게시물이 하나도 없습니다.");
		} else {
			for(BoardVO boardVo : boardList) {
				System.out.println(boardVo.getNo() + "\t" + boardVo.getTitle() + "\t" 
									+ boardVo.getWriter() + "\t" + boardVo.getCnt());
			}
		}
		
		System.out.println("-------------------------------------------------------------");
		
	}
	
	private int displayMenu() {
		displayAllBoard();
		System.out.println();
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		return scan.nextInt();
	}

}
