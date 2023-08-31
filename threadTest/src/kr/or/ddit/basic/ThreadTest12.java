package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
	10마리의 말들이 경주하는 프로그램 작성하기
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 이 클래스는 말이름(String), 등수(int), 현재위치(int)를
	멤버 변수로 갖는다. 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다.
	(Comparable 인터페이스 구현)
	
	조건)
	
	1) 경기 구간은 1 ~ 50구간으로 되어 있다.
	2) 경기가 끝나면 등수 순으로 출력한다.
	3) 경기 중 중간 중간에 아래와 같이 각 말들의 위치를 나타내시오.
	예시)
	  01번말 : -------->--------------------------------
	  02번말 : -------------->--------------------------
	  ...
	  10번말 : ----------->-----------------------------
*/

public class ThreadTest12 {

	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] {
			new	Horse("01번말"), 
			new	Horse("02번말"), 
			new	Horse("03번말"), 
			new	Horse("04번말"), 
			new	Horse("05번말"), 
			new	Horse("06번말"), 
			new	Horse("07번말"), 
			new	Horse("08번말"), 
			new	Horse("09번말"), 
			new	Horse("10번말")
		};
		
		for(Horse h : horseArr) {
			h.start();
		}
		
		for(Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		List<Horse> horseList = Arrays.asList(horseArr);
		Collections.sort(horseList);
		
		for(Horse h : horseList) {
			System.out.println(h.getRanking() + "등 : " + h.getHorseName());
		}

	}

}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int ranking;
	private int position;
	private static int rankCount;
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.getRanking(), h.getRanking());
	}
	
	@Override
	public void run() { // 1000 0100
		for(int i=1; i<=50; i++) {
			position = i;
			System.out.print(horseName + " : ");
			for(int j=1; j<i; j++) {
				System.out.print("-");
			}
			System.out.print(">");
			for(int j=i+1; j<=50; j++) {
				System.out.print("-");
			}
			System.out.println();
			try {
				// 0 ~ 499 사이의 난수를 이용하여 일시 정지 시킨다.
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(horseName + " 말 도착!");
		rankCount++;
		setRanking(rankCount);
	}

}


