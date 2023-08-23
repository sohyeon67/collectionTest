package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	 문제1) 5명의 별명을 입력 받아 ArrayList에 추가한 후 이들 중
	 	별명의 길이가 제일 긴 별명을 출력하시오.
	 	(단, 각 별명의 길이는 모두 다르게 입력한다.)
	 	
	 문제2) 문제1에서 입력할 때 각 별명의 길이가 같은 것이 있을 경우에 대하여 처리하시오.
*/

public class ArrayListTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> aliasList = new ArrayList<String>();
		String longName = null;
		int maxLength = 0;

		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명 >> ");
			aliasList.add(sc.nextLine());
		}

		maxLength = aliasList.get(0).length();

		for (int i = 1; i < aliasList.size(); i++) {
			if (maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
				longName = aliasList.get(i);
			}
		}

		System.out.println("제일 긴 별명 : " + longName);

	}

}
