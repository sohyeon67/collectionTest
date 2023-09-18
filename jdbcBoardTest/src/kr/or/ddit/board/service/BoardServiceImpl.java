package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;	// DAO객체 변수 선언
	
	// 생성자
	public BoardServiceImpl() {
		dao = new BoardDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public BoardVO detailBoard(int no) {
		return dao.detailBoard(no);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int no) {
		return dao.deleteBoard(no);
	}

	@Override
	public BoardVO searchBoard(String title) {
		return dao.searchBoard(title);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

}
