package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	회원 관리를 하는 프로그램을 작성하시오.
	( MYMEMBER 테이블 이용 )
	
	아래의 메뉴를 구성하고 각 메뉴의 기능을 구현하시오. (CRUD 기능 구현하기)
	메뉴예시)
	== 작업 선택 ==
	 1. 자료 추가		==> C (insert문)
	 2. 자료 삭제		==> D (delete문)
	 3. 자료 수정		==> U (update문)
	 4. 전체 자료 출력	==> R (select문)
	 0. 작업 끝.
	------------
	
	조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
	
*/
public class JdbcTest06 {
	private Scanner scan = new Scanner(System.in);
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public static void main(String[] args) {
		new JdbcTest06().startMyMember();
	}
	
	public void startMyMember() {
		System.out.println("**********************");
		System.out.println("      프 로 그 램 시 작");
		System.out.println("**********************");
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				printAll();
				break;
			case 0:
				System.out.println("작업을 종료합니다...");
				System.exit(0);
			default:
				System.out.println("작업 번호 오류");
			}
		}
	}
	
	// 1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
	private void insert() {
		System.out.println(" -- 추가 작업 --");
		try {
			conn = DBUtil.getConnection();
			
			String id = null;
			int count = 0;
			
			String sql1 = "select count(*) cnt from mymember "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql1);
			do {
				System.out.print("회원 ID >> ");
				id = scan.next();
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("회원 ID 중복");
					System.out.println("다시 입력하세요...");
					System.out.println();
				}
			} while(count > 0);
			
			System.out.print("비밀번호 >> ");
			String pass = scan.next();
			
			System.out.print("회원이름 >> ");
			String name = scan.next();
			
			System.out.print("전화번호 >> ");
			String tel = scan.next();
			scan.nextLine();
			
			System.out.print("주소 >> ");
			String addr = scan.nextLine();
			
			String sql2 = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int insertCnt = pstmt.executeUpdate();
			if(insertCnt > 0) {
				System.out.println("자료 추가 성공");
			} else {
				System.out.println("자료 추가 실패");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
	// 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	private void delete() {
		System.out.println(" -- 삭제 작업 --");
		System.out.print("삭제할 회원 ID >> ");
		String id = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int deleteCnt = pstmt.executeUpdate();
			if(deleteCnt > 0) {
				System.out.println("자료 삭제 성공");
			} else {
				System.out.println("자료 삭제 실패");
			}
			System.out.println();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
	// 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
	private void update() {
		System.out.println(" -- 수정 작업 --");
		System.out.print("회원 ID >> ");
		String id = scan.next();
		
		System.out.print("수정할 비밀번호 >> ");
		String pass = scan.next();
		
		System.out.print("수정할 회원이름 >> ");
		String name = scan.next();
		
		System.out.print("수정할 전화번호 >> ");
		String tel = scan.next();
		scan.nextLine();
		
		System.out.print("수정할 주소 >> ");
		String addr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			
			int updateCnt = pstmt.executeUpdate();
			if(updateCnt > 0) {
				System.out.println("자료 수정 성공");
			} else {
				System.out.println("자료 수정 실패");
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
	private void printAll() {
		int count = 0;
		System.out.println(" -- 전체 출력 --");
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from mymember";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("회원 ID : " + rs.getString("mem_id"));
				System.out.println("비밀번호 : " + rs.getString("mem_pass"));
				System.out.println("회원이름 : " + rs.getString("mem_name"));
				System.out.println("전화번호 : " + rs.getString("mem_tel"));
				System.out.println("주      소 : " + rs.getString("mem_addr"));
				System.out.println("-------------------------------------");
				count++;
			}
			
			if(count == 0) {
				System.out.println("데이터가 없습니다!!!");
			}
			
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
	private int displayMenu() {
		System.out.println("== 작업 선택 ==");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 0. 작업 끝.");
		System.out.println("------------");
		
		System.out.print("메뉴 선택 >> ");
		return scan.nextInt();
	}

}
