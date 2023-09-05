package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		/*
		- 방법1
			1) Thread클래스를 상속한 class를 작성한 후 이 class의 인스턴스를 생성한다.
			2) 이 생성된 인스턴스의 start()메서드를 호출해서 실행한다.
		*/
		MyThread1 th1 = new MyThread1();	// 인스턴스 생성
		th1.start();		// 쓰레드 실행
		
		/*
		- 방법2-1
			1) Runnable인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성한다.
			2) Thread클래스의 인스턴스를 생성할 때 Runnable인터페이스를 구현한 class의
				인스턴스를 생성자의 인수값으로 넣어준다.
		 	3) 생성된 Thread클래스의 인스턴스의 start()메서드를 호출해서 실행한다.
		*/
		MyRunner2 r2 = new MyRunner2();	// Runnable인터페이스를 구현한 클래스의 인스턴스 생성
		Thread th2 = new Thread(r2);	// Thread의 인스턴스를 생성할 때 Runnable구현체의 인스턴스를 주입한다.
		th2.start();
		// MyRunner2 eee = new MyRunner2(); 재사용 가능
		
		// 방법2-2 ==> 익명 구현체를 이용하는 방법
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		// --------------------------------------------
		Thread th3 = new Thread(r3);
		th3.start();
		
		System.out.println("main()메서드 끝...");
	}

}

// 방법1
// Thread클래스를 상속한 class를 작성한다.
class MyThread1 extends Thread {
	// run()메서드를 재정의 한다.
	@Override
	public void run() {
		// 이 run()메서드 안에는 쓰레드에서 처리할 내용을 작성한다.
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간); ==> 주어진 시간 동안 작업을 잠시 멈춘다.
				// 		'시간'은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}	
	}
}
// -------------------------------------

// 방법2-1
// Runnable인터페이스를 구현한 class를 작성한다.
class MyRunner2 implements Runnable {
	// run()메서드를 재정의 한다.
	@Override
	public void run() {
		// 이 run()메서드 안에는 쓰레드에서 처리할 내용을 작성한다.
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}
