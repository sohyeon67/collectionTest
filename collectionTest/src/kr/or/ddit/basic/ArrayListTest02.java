package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/* 
 	문제) 5명의 사람 이름을 입력 받아 ArrayList에 추가한 후에 
 		ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 모두 출력하시오.
 		(단, 입력은 Scanner 객체를 이용한다.)
 */

public class ArrayListTest02 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 이름 입력 >> ");
			list.add(sc.nextLine());
		}

		System.out.println();

		// 방법1
		System.out.println("---- 방법1 ----");
		for (String str : list)
			if (str.indexOf("김") == 0) {
				System.out.println(str);
			}

		// 방법2
		System.out.println("---- 방법2 ----");
		for (String str : list) {
			if (str.charAt(0) == '김') {
				System.out.println(str);
			}
		}

		// 방법3
		System.out.println("---- 방법3 ----");
		for (String str : list) {
			if (str.contains("김")) {
				System.out.println(str);
			}
		}

		// 방법4
		System.out.println("---- 방법4 ----");
		for (String str : list) {
			if (str.matches("김.*")) {
				System.out.println(str);
			}
		}

		// 방법5
		System.out.println("---- 방법5 ----");
		for (String str : list) {
			if (str.substring(0, 1).equals("김")) { //str.substring(0,1)=="김" 주소값 비교라 하면 안됨
				System.out.println(str);
			}
		}

	}

}
