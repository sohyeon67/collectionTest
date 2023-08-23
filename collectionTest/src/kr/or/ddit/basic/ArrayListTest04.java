package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ArrayListTest04 {

	public static void main(String[] args) {
		ArrayList<String> nameList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> longNameIndex = new ArrayList<Integer>();
		int maxLength = 0;

		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명 >> ");
			nameList.add(sc.nextLine());
		}

		maxLength = nameList.get(0).length();

		// 최대 길이
		for (int i = 1; i < nameList.size(); i++) {
			if (maxLength < nameList.get(i).length()) {
				maxLength = nameList.get(i).length();
			}
		}
		
		// 최대 길이인 애들
		for(int i=0; i<nameList.size(); i++) {
			if(nameList.get(i).length()==maxLength) {
				longNameIndex.add(i);
			}
		}
		
		System.out.println("-- 제일 긴 별명 --");
		for(int i=0; i<longNameIndex.size(); i++) {
			System.out.println(nameList.get(longNameIndex.get(i)));
		}

	}

}
