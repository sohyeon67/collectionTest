package kr.or.ddit.basic;

public class ThreadTest01 {

	public static void main(String[] args) {
		// '*'문자를 200개 출력하는 기능과 '$'문자를 200개 출력하는 기능 구현하기
		
		// 싱글 쓰레드 프로그램
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
		System.out.println();
		
	}

}
