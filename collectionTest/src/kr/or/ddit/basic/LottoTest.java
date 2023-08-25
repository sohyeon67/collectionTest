package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
      거스름돈도 계산하여 출력한다.)

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 900  <-- 입력
	
	입력 금액이 너무 적습니다. 로또번호 구입 실패!!!

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 101000  <-- 입력
	
	입력 금액이 너무 많습니다. 로또번호 구입 실패!!!
			
   	 ==========================
         	Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다


 */

public class LottoTest {
	private ArrayList<Integer> numList; // 난수가 저장될 리스트

	private Scanner scan = new Scanner(System.in);

	// 1 ~ 45 사이의 서로 다른 난수 6개를 만들어서 리스트에 저장하는 메서드 (Set 이용)
	public void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();

		// 난수 6개 만들기
		while (numSet.size() < 6) {
			numSet.add((int) (Math.random() * 45 + 1));
		}

		// 만들어진 난수를 List에 저장한다.
		numList = new ArrayList<Integer>(numSet);

		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);

	}

	public void buy() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int coin = Integer.parseInt(scan.nextLine());
		int change = 0;
		int count = 0;

		if (coin < 1000) {
			System.out.println();
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
		} else if (coin >= 101000) {
			System.out.println();
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
		} else {

			count = coin / 1000;
			change = coin % 1000;

			System.out.println();
			System.out.println("행운의 로또번호는 아래와 같습니다.");

			for (int i = 1; i <= count; i++) {
				createNum();
//				System.out.println("로또번호" + i + " : " + numList);
		        System.out.print("로또번호" + i + " : ");
		        for (int j = 0; j < numList.size(); j++) {
		            System.out.print(numList.get(j));
		            if (j < numList.size() - 1) {
		                System.out.print(","); // 마지막 숫자 뒤에 쉼표를 넣지 않음
		            }
		        }
		        System.out.println(); // 줄바꿈
			}
			
			System.out.println();
			System.out.println("받은 금액은 " + coin + "원이고 거스름돈은 " + change + "원입니다.");
		}
		System.out.println();

	}

	public void home() {
		while (true) {
			System.out.println("==========================");
			System.out.println("        Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("  1. Lotto 구입");
			System.out.println("  2. 프로그램 종료");
			System.out.println("==========================");

			System.out.print("메뉴 선택 >> ");

			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				buy();
				break;
			default:
				System.out.println();
				System.out.println("감사합니다");
				System.exit(0);
			}

		}

	}

	public static void main(String[] args) {
		new LottoTest().home();
	}

}
