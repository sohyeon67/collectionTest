package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	// 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content)"
					+ " values(board_seq.nextVal, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getWriter());
			pstmt.setString(3, boardVo.getContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public BoardVO detailBoard(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVo = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 게시물 정보가 저장될 VO객체 생성
				boardVo = new BoardVO();
				
				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다.
				boardVo.setNo(rs.getInt("board_no"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setWriter(rs.getString("board_writer"));
				boardVo.setDate(rs.getDate("board_date"));
				boardVo.setCnt(rs.getInt("board_cnt"));
				boardVo.setContent(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_title = ?, board_content = ? "
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNo());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public BoardVO searchBoard(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVo = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new BoardVO();
				
				// ResultSet에서 각 컬럼들을 가져와 VO의 멤버변수에 저장한다.
				boardVo.setNo(rs.getInt("board_no"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setWriter(rs.getString("board_writer"));
				boardVo.setDate(rs.getDate("board_date"));
				boardVo.setCnt(rs.getInt("board_cnt"));
				boardVo.setContent(rs.getString("board_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(boardList==null) boardList = new ArrayList<BoardVO>();
				
				// 한 개의 레코드가 저장될 VO객체 생성
				BoardVO boardVo = new BoardVO();
				
				// ResultSet에서 각 컬럼들을 가져와 VO의 멤버변수에 저장한다.
				boardVo.setNo(rs.getInt("board_no"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setWriter(rs.getString("board_writer"));
				boardVo.setDate(rs.getDate("board_date"));
				boardVo.setCnt(rs.getInt("board_cnt"));
				boardVo.setContent(rs.getString("board_content"));
				
				// 구성된 VO객체를 List에 추가한다.
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return boardList;
	}

}
