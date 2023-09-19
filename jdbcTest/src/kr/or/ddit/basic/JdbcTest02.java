package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력 받아
// 		LPROD_ID가 입력한 값보다 큰 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		System.out.print("lprod_id값 입력 >> ");
		int lprodId = scan.nextInt();


		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB시스템에 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc03", "java");

			// 3. 질의

			// 3-1. SQL문 작성 ==> 문자열로 작성
			String sql = "select * from lprod where lprod_id > " + lprodId;

			// 3-2. Statement객체 생성 ==> 질의를 수행하는 객체 생성
			// ==> Connection객체를 이용해서 생성한다.
			stmt = conn.createStatement();

			// 3-3. SQL문을 DB서버로 보내서 결과를 얻어온다.
			// (Statement객체나 PreparedStatement객체 이용)
			// ==> (지금은 실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에
			// 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);

			// 4. 결과 처리하기 ==> 가져온 결과를 화면에 한 레코드씩 출력해 보기

			// ResultSet객체에 저장된 데이터를 차례로 꺼내오려면
			// 반복문과 ResultSet객체의 next()메서드를 이용한다.

			System.out.println(" == 쿼리문 실행 결과 ==");
			// ResultSet객체의 next()메서드
			// ==> ResultSet객체에 저장된 데이터를 가리키는 포인터를
			// ==> 다음번째의 레코드 위치로 이동시키고
			// ==> 그 곳에 데이터가 있으면 true, 없으면 false를 반환한다.
			while (rs.next()) {
				// ResultSet객체의 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식1) ResultSet객체.get자료형이름("컬럼명 또는 alias명") 대소문자 상관없음
				// 형식2) ResultSet객체.get자료형이름(컬럼번호) ==> 컬럼번호는 1번부터 시작
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : " + rs.getString("lprod_nm"));
				System.out.println("------------------------------------------");
			}
			System.out.println("출력 작업 끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 사용했던 자원 반납 ==> 보통 객체가 생성된 순서의 역순으로 닫아준다.
			if (rs != null) try { rs.close();} catch (SQLException e) {}
			if (stmt != null) try { stmt.close();} catch (SQLException e) {}
			if (conn != null) try { conn.close();} catch (SQLException e) {}
		}

	}

}
