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
		Horse[] horses = new Horse[] {
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
		
		GameState gs = new GameState(horses);
		
		for(Horse h : horses) {
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		Arrays.sort(horses);
		
		for(Horse h : horses) {
			System.out.println(h.getRanking() + "등 : " + h.getHorseName());
		}

	}

}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int ranking;
	private int position;
	
	public static int rankCount;
	
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
	public void run() {
		// 위치
		for(int i=1; i<=50; i++) {
			position = i;
			
			try {
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

class GameState extends Thread {
	private Horse[] horses;

	public GameState(Horse[] horse) {
		this.horses = horse;
	}
	
	@Override
	public void run() {
		
		while(true) {
			if(Horse.rankCount == horses.length) {
				break;
			}
			
			// 말 하나씩 위치 찍어주기
			for(int i=0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				
				// 현재 위치에 찍어주기
				for(int j=1; j<=50; j++) {
					if(j == horses[i].getPosition()) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
				
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println();
			System.out.println();
		}
		
	}
	
}
