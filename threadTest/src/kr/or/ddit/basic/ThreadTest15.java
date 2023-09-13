package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();	// 공통 객체 생성
		
		TestThread th1 = new TestThread("test1", sObj);
		TestThread th2 = new TestThread("test2", sObj);
		
		th1.start();
		th2.start();
		
		
	}

}

class TestThread extends Thread {
	private ShareObject sObj;

	public TestThread(String name, ShareObject sObj) {
		super(name);		// 쓰레드의 name 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			sObj.add();
		}
	}
	
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//public synchronized void add() {	// 방법1 ==> 메서드에 동기화 설정을 한다.
	public void add() {
		synchronized (this) {	// 방법2 ==> 동기화 블럭을 이용하여 설정한다.
			int n = sum;
			
			n += 10;	// 10 증가
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}