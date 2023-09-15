package kr.or.ddit.basic;

public class DocTest {

	public static void main(String[] args) {
		System.out.println("test");
		
		JavaDocTest test = null;
		
		test.methodAdd(1, 2);
		test.methodSub();
		
		// MVC패턴에서 사용하는 클래스들
		// 1) VO 또는 DTO객체
		// 2) DAO객체
		// 3) Service객체
		// 위 객체들의 역할(쓰임새)에 대하여 공부하기
	}

}
