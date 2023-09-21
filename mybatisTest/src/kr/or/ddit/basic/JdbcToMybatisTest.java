package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

/*
	LPROD테이블에 새로운 데이터를 추가한다.
	
	조건) lprod_gu값과 lprod_nm값은 직접 입력 받아서 처리하고,
		lprod_id값은 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다.
		입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아 처리한다.
*/

public class JdbcToMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		/*
		// 1. MyBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 처리하여
		//    SqlSessionFactory객체 생성한다.
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			// 1-1. 환경 설정 파일을 읽어올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			// 1-2. 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SqlSessionFactory객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if(in!=null) try { in.close(); } catch(IOException e) {}
		}
		*/
		
		//---------------------------------------------------------------------
		
		// lprod_id값 구하기  ==> 현재의 lprod_id값 중 제일 큰 값보다 1 큰 값으로 한다.
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();

			int maxnum = session.selectOne("jdbc.getMaxId");
			maxnum++;	// 제일 큰 값 증가하기
			//------------------------------------------------
			
			// 입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아 처리한다.
			String gu;		// 상품 분류 코드가 저장될 변수 선언
			int count = 0;	// 검색한 상품 분류 코드의 개수가 저장될 변수 선언
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next().toUpperCase();
				
				count = session.selectOne("jdbc.getLprodguCount", gu);
				
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
			// 구성된 insert할 데이터를 VO객체에 저장한다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxnum);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("jdbc.insertLprod", lvo);
			
			if(cnt>0) {
				session.commit();
				System.out.println("insert 성공!!!");
			} else {
				System.out.println("insert 실패~~~");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
	}
	
}
