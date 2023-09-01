package kr.or.ddit.basic;

import java.util.Arrays;

/*
	10마리의 말들이 경주하는 프로그램 작성하기
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버 변수로 갖는다. 
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다.
	(Comparable 인터페이스 구현)
	
	조건)
	1) 경기 구간은 1 ~ 50구간으로 되어 있다.
	2) 경기가 끝나면 등수 순으로 출력한다.
	3) 경기 중 중간 중간에 아래와 같이 각 말들의 위치를 나타내시오.
	예시)
	  01번말 : ----->---------------------------------------
	  02번말 : --------->-----------------------------------
	  ...
	  10번말 : ------->-------------------------------------
	
	
*/
public class ThreadTest12 {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("01번말"), new Horse("02번말"), new Horse("03번말"),	
			new Horse("04번말"), new Horse("05번말"), new Horse("06번말"),	
			new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),	
			new Horse("10번말")	
		};
		
		GameBoard gb = new GameBoard(horses);
		
		// 경주 시작 ==> 쓰레드 시작
		for(Horse h : horses) {
			h.start();
		}
		
		gb.start();   // 말의 현재 위치 출력용 쓰레드 실행
		
		// 모든 말의 경주가 끝날때까지 기다린다.
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		gb.interrupt();
		
		try {
			gb.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝......");
		System.out.println();
		
		// 등수의 오름차순으로 정렬한다.
		Arrays.sort(horses);
		
		System.out.println("    === 경기 결과 ===");
		for(Horse h : horses) {
			System.out.println(h);
		}
		
	}

}

/*
말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버 변수로 갖는다. 
그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다.
(Comparable 인터페이스 구현)
*/
class Horse extends Thread implements Comparable<Horse> {
	// 등수를 구하기 위한 변수(각 말들(쓰레드들)이 공통으로 사용한다.)
	public static int currentRank = 0;		
	
	private String horseName;		// 말이름
	private int rank;				// 등수
	private int location;			// 현재위치
	
	// 생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다.";
	}
	
	// 등수의 오름차순으로 정렬하는 정렬 기준 구성하기
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}
	
	@Override
	public void run() {
		// 경기 구간은 1 ~ 50구간으로 되어 있다.
		for(int i=1; i<=50; i++) {
			this.location = i;   // 말의 현재 위치를 저장한다.
			try {
				Thread.sleep((int)(Math.random() * 500));  // 0 ~ 499사이의 난수
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 현재 말의 경기가 끝난다. ==> 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
		
	}
	
	
}

/*
3) 경기 중 중간 중간에 아래와 같이 각 말들의 위치를 나타내시오.
	예시)
	  01번말 : ----->---------------------------------------
	  02번말 : --------->-----------------------------------
	  ...
	  10번말 : ------->-------------------------------------
*/
class GameBoard extends Thread{
	private Horse[] horses;
	
	// 생성자
	public GameBoard(Horse[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모들 말들의 경기가 종료 되었는지 여부를 검사한다.
//			if(Horse.currentRank==horses.length) {
//				break;
//			}
			
			// interrupt()메서드를 이용한 종료 처리
			if(this.isInterrupted()) {
				break;
			}
			
			
			// 각 텀 사이의 빈줄 출력
			for(int i=1; i<=15; i++) {
				System.out.println();
			}
			
			
			for(Horse h : horses) {
				// 01번말 : ----->---------------------------------------
				System.out.print(h.getHorseName() + " : ");
				
				for(int i=1; i<=50; i++) {
					if(h.getLocation()==i) {  // 말의 현재 위치 확인 
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();  // 줄바꿈
				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				this.interrupt();
			}
			
			
			
		}
	}
	
	
}





