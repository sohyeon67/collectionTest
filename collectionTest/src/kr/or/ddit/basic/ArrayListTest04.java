package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/*
  문제2) 5명의 별명을 입력 받아 ArrayList에 추가한 후 이들 중
	 	별명의 길이가 제일 긴 별명을 출력하시오.
	 	(단, 각 별명의 길이는 같은 것이 있을 수 있다.)
 */

public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<String> aliasList = new ArrayList<String>();

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 별명 >> ");
			aliasList.add(sc.nextLine());
		}

		System.out.println();

		// 제일 긴 별명의 길이가 저장될 변수 선언 ==> 이 변수는 첫번재 데이터의 길이로 초기화 한다.
		int maxLength = aliasList.get(0).length();

		// 최대 길이
		for (int i = 1; i < aliasList.size(); i++) {
			if (maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}
		
		// 나는 길이가 긴(같은) 인덱스만 따로 모아서 저장했었는데 그럴 필요가 없음

		System.out.println("-- 제일 긴 별명 --");
		for (int i = 0; i < aliasList.size(); i++) {
			if(maxLength == aliasList.get(i).length()) {
				System.out.println(aliasList.get(i));
			}
		}

	}

}
