package kr.or.ddit.basic;

//yield()메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldTestThread th1 = new YieldTestThread("1번 쓰레드");
		YieldTestThread th2 = new YieldTestThread("2번 쓰레드");

		th1.start();
		th2.start();

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("******************** 11111111111111");

		th1.work = false;

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("******************** 222222222222222");

		th1.work = true;

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("******************** 3333333333333");

		th1.stop = true;
		th2.stop = true;

	}

}

//yield()메서드 연습용 쓰레드
class YieldTestThread extends Thread {
	public boolean stop = false;
	public boolean work = true;

	// 생성자
	public YieldTestThread(String name) {
		super(name); // 쓰레드의 이름 설정한다.
	}

	@Override
	public void run() {
		while (!stop) {
			if (work) {
				System.out.println(getName() + " 작업 중...");
			} else {
				System.out.println(getName() + " 양보...");
				Thread.yield();
			}
		}
	}

}