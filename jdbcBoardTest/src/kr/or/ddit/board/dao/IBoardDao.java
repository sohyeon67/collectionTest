package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 그 결과를 작성해서
 * Service에게 전달하는 DAO의 interface
 * 
 * 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 * 
 * @author PC-03
 *
 */

public interface IBoardDao {
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardVo DB에 insert할 자료가 저장될 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 게시물번호를 매개변수로 받아서 BoardVO를 반환하는 메서드
	 * 
	 * @param no 게시글번호
	 * @return BoardVO객체
	 */
	public BoardVO detailBoard(int no);
	
	/**
	 * BoardVO자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param boardVo update할 게시물 정보가 저장된 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시물번호를 매개변수로 받아서 해당 게시물을 삭제하는 메서드
	 * 
	 * @param no 삭제할 게시물번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int no);
	
	/**
	 * 제목을 매개변수로 받아서 해당 게시물을 검색하는 메서드
	 * 
	 * @param title 검색할 제목
	 * @return BoardVO객체
	 */
	public List<BoardVO> searchBoard(String title);
	
	/**
	 * DB에 저장된 전체 게시물 정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return BoardVO객체가 저장된 List
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 게시물 조회수를 증가시키는 메서드
	 * @param BoardVo 
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int addCnt(int no);
}
