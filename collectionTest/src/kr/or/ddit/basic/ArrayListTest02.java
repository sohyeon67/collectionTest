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
		ArrayList<String> nameList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력 >> ");
			nameList.add(sc.nextLine());
		}

		System.out.println();

		System.out.println("---- 방법1 ----");
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).substring(0, 1).equals("김")) { // 문자열 비교 equals()
				System.out.println(nameList.get(i));
			}
		}

		System.out.println("---- 방법2 ----");
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
			}
		}

		System.out.println("---- 방법3 ----");
		for (String str : nameList)
			if (str.indexOf("김") == 0) {
				System.out.println(str);
			}

		System.out.println("---- 방법4 ----");
		for (String str : nameList) {
			if (str.charAt(0) == '김') {
				System.out.println(str);
			}
		}

		// 밑에 두개는 수업때 안함
		System.out.println("---- 방법5 ----");
		for (String str : nameList) {
			if (str.contains("김")) { // "김"이 포함되면 true라서 적절하지 않음
				System.out.println(str);
			}
		}

		System.out.println("---- 방법6 ----");
		for (String str : nameList) {
			if (str.matches("김.*")) {
				System.out.println(str);
			}
		}

	}

}
