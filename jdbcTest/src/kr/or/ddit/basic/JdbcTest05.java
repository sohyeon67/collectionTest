package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD테이블에 새로운 데이터를 추가한다.
	
	조건) lprod_gu값과 lprod_nm값은 직접 입력 받아서 처리하고,
		lprod_id값은 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다.
		입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아 처리한다.
*/

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "pc03", "java");
			
			conn = DBUtil.getConnection();
			
			// lprod_id값 구하기  ==> 현재의 lprod_id값 중 제일 큰 값보다 1 큰 값으로 한다.
			String sql = "select nvl(max(lprod_id),0) maxnum from lprod";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int maxnum = 0;
			if(rs.next()) {
				maxnum = rs.getInt(1);			// 컬럼 번호 이용
//				maxnum = rs.getInt("maxnum");	// alias명 이용
			}
			maxnum++;	// 제일 큰 값 증가하기
			//------------------------------------------------
			
			// 입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아 처리한다.
			String gu;		// 상품 분류 코드가 저장될 변수 선언
			int count = 0;	// 검색한 상품 분류 코드의 개수가 저장될 변수 선언
			
			String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
			pstmt = conn.prepareStatement(sql2);
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next().toUpperCase();
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu +
							"는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
					System.out.println();
				}
			} while(count>0);
			
			//------------------------------------------------
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			//------------------------------------------------
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxnum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("insert 성공!!!");
			} else {
				System.out.println("insert 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
	}
	
}
