package kr.or.ddit.basic;

/*
	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
	이 때 사용하던 자원을 정리하지 못하고 쓰레드가 종료되어
	다른 쓰레드의 실행에 영향을 줄 수 있다.
	그래서 stop()메서드는 비추천으로 되어 있다.

 */

public class ThreadTest13 {

	public static void main(String[] args) {
		/*
		ThreadStopTest01 th1 = new ThreadStopTest01();
		
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		//th1.stop();
		th1.setStop(true);
		*/
		
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}

}

// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest01 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println(" 쓰레드의 처리내용 실행 중...");
		}
		
		System.out.println("사용하던 자원을 정리한다...");
		System.out.println("쓰레드 종료...");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest02 extends Thread {
	@Override
	public void run() {
		/*
		// 방법1 ==> interrupt()메서드와 sleep()메서드를 이용하는 방법
		try {
			while(true) {
				System.out.println("쓰레드 실행 중...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		*/
		
		// 방법2
		while(true) {
			System.out.println("Thread 실행 중...");
			
			// interrupt()메서드가 호출되었는지 여부를 검사한다.
			
			/*
			// 검사방법1 ==> 쓰레드의 인스턴스 메서드 중 isInterrupted()메서드를 이용한다.
			// 		isInterrupted()메서드는 interrupt()메서드가 호출되면 true 반환한다.
			if(this.isInterrupted()) {
				break;
			}
			*/
			
			// 검사방법2 ==> 쓰레드의 정적(static) 메서드인 interrupted()메서드를 이용한다.
			//		interrupted()메서드 ==> interrupt()메서드가 호출되면 true반환한다.
			if(Thread.interrupted()) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("자원 정리...");
		System.out.println("쓰레드 끝..............");
	}
}
