package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.JdbcBoardVO;

public interface IJdbcBoardDao {
	/**
	 * JdbcBoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardVo DB에 insert할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int insertBoard(JdbcBoardVO boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공:1, 작업실패:0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * JdbcBoardVO에 저장된 자료를 이용하여 update작업을 수행하는 메서드
	 * 
	 * @param boardVo 수정할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int updateBoard(JdbcBoardVO boardVo);
	
	/**
	 * DB에 jdbc_board테이블의 전체 데이터를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return JdbcBoardVO객체를 저장하고 있는 List객체
	 */
	public List<JdbcBoardVO> getAllBoard();
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 가져와 반환하는 메서드
	 * 
	 * @param boardNo 검색할 게시글 번호
	 * @return 게시글 번호에 맞는 자료가 있으면 해당 게시글 정보를 갖는 JdbcBoardVO객체,
	 * 			자료가 없으면 null 반환
	 */
	public JdbcBoardVO getBoard(int boardNo);
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글의 제목
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<JdbcBoardVO> getSearchBoard(String title);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo 조회수를 증가할 게시글 번호
	 * @return 작업성공:1, 작업실패:0
	 */
	public int setCountIncrement(int boardNo);
}
