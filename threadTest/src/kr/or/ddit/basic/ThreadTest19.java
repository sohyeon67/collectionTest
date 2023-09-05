package kr.or.ddit.basic;

public class ThreadTest19 {

	public static void main(String[] args) {
		// 공통 객체 생성
		DataBox box = new DataBox();
		
		ProducerThread th1 = new ProducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th1.start();
		th2.start();
		
	}

}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread {
	private DataBox box;

	// 생성자
	public ConsumerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = box.getValue();
		}
	}
}

// 데이터를 넣어주는 쓰레드
class ProducerThread extends Thread {
	private DataBox box;

	// 생성자
	public ProducerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			box.setValue("저장용 데이터 " + i);
		}
	}
}

// 데이터를 공통으로 사용하는 class
class DataBox {
	private String value;
	
	/*
	value값이 null이면 value에 문자열이 채워질 때까지 기다리고
	value에 값이 있으면 해당 문자열을 반환한다.
	(이 때, 반환 후에는 value값을 null로 만든다.)
	*/
	public synchronized String getValue() {
		if(value==null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// value에 데이터가 있을 때의 처리 내용을 작성한다.
		String temp = value;
		System.out.println("쓰레드가 읽은 데이터 : " + temp);
		value = null;
		
		notify();
		
		return temp;
	}
	
	/*
		value변수에 값이 있으면 value가 null이 될 때까지 기다린다.
		value가 null이 되면 새로운 데이터값을 value에 저장한다.
	*/
	public synchronized void setValue(String value) {
		if(this.value!=null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// this.value의 값이 null일 때 처리할 내용을 작성한다.
		this.value = value;
		System.out.println("Thread에서 새로 저장한 데이터 : " + value);
		
		notify();
	}
}