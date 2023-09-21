package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() {}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;	// 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("member.insertMember", memVo);
			
			// insert, update, delete작업이 성공하면 commit()을 처리한다.
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("member.deleteMember", memId);
			
			// insert, update, delete작업이 성공하면 commit()을 처리한다.
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("member.updateMember", memVo);
			
			// insert, update, delete작업이 성공하면 commit()을 처리한다.
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		
		List<MemberVO> memList = null;	// 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			memList = session.selectList("member.getAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return memList;
	}

	@Override
	public int getMemIdCount(String memId) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			count = session.selectOne("member.getMemIdCount", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		// key값 정보 ==> 회원ID(memid), 수정할컬럼명(field), 수정할데이터(data)
//		try {
//			conn = DBUtil3.getConnection();
//			String sql = "update mymember set " + paramMap.get("field") + " = ? "
//					+ " where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, paramMap.get("data"));
//			pstmt.setString(2, paramMap.get("memid"));
//			
//
//			cnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
//		}
		
		return cnt;
	}
	
}
