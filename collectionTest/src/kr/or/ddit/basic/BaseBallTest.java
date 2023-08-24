package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
문제 ) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	컴퓨터의 숫자는 난수를 이용하여 구한다. (1 ~ 9사이의 난수 3개)
	(스트라이크는 S, 볼은 B로 나타낸다.)
	
예시)
	컴퓨터의 난수 ==> 9 5 7

실행예시)
	숫자입력 >> 3 5 6
	3 5 6 ==> 1S 0B
	숫자입력 >> 7 8 9
	7 8 9 ==> 0S 2B
	숫자입력 >> 9 7 5
	9 7 5 ==> 1S 2B
	숫자입력 >> 9 5 7
	9 5 7 ==> 3S 0B
	
	축하합니다...
	당신은 4번만에 맞췄습니다...
	
*/
public class BaseBallTest {

	public static void main(String[] args) {
		HashSet<Integer> random = new HashSet<Integer>();
		while(random.size() < 3) {
			random.add((int)(Math.random() * 9 + 1));
		}
		ArrayList<Integer> comList = new ArrayList<Integer>(random);
		ArrayList<Integer> userList = new ArrayList<Integer>();
		
		System.out.println("컴퓨터 : " + comList);
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		while(true) {
			int strike = 0;
			int ball = 0;
			count++;
			System.out.print("숫자입력 >> ");
			for(int i=0; i<3; i++) {
				userList.add(sc.nextInt());
			}
			
			for(int num : userList) {
				System.out.print(num + " ");
			}
			System.out.print("==> ");
			
			for(int i=0; i<3; i++) {
				if(userList.contains(comList.get(i))) {
					if(userList.get(i) == comList.get(i)) {
						strike++;
					} else {
						ball++;
					}
				}
			}
			System.out.println(strike + "S " + ball + "B" );
			userList.clear();
			
			if(strike == 3) {
				System.out.println("축하합니다...");
				System.out.println("당신은 " + count + "번만에 맞췄습니다...");
				break;
			}
		}
	}
}
