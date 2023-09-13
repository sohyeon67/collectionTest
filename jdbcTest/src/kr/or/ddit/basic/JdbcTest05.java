package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int lprodId = 0;
		String lprodGu = null;
		String lprodNm = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "pc03", "java");
			
			System.out.println("=== 새로운 데이터 추가하기 ===");
			while(true) {
				System.out.print("lprod_gu : ");
				lprodGu = scan.next().toUpperCase();
				
				String checkSql = "select count(*) from lprod "
						+ "where lprod_gu='" + lprodGu + "'";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(checkSql);
				if(rs.next()) {
					if(rs.getInt(1) == 0) {
						break;
					}
					System.out.println("이미 존재하는 lprod_gu값 입니다.");
					System.out.println("다시 입력해주세요...");
				}
			}
			System.out.print("lprod_nm : ");
			lprodNm = scan.next();
			
			String maxSql = "select max(lprod_id) from lprod";
			rs = stmt.executeQuery(maxSql);
			if(rs.next()) {
				lprodId = rs.getInt(1)+1;
			}
			
			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values( ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			if(cnt == 1) System.out.println("데이터 추가 성공!!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		
	}

}
