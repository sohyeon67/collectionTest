package kr.or.ddit.basic;

// 쓰레드에서 객체를 공통으로 사용하는 예제


/*
	원주율을 계산하는 쓰레드와
	계산된 원주율을 출력하는 쓰레드가 있다.
	
	원주율을 관리하는 객체가 필요하다.
	이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
	
*/

public class ThreadTest14 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		// 쓰레드 객체들을 생성하고 공통으로 사용할 객체를 각각의 쓰레드에 주입한다.
		CalcPIThread th1 = new CalcPIThread();
		th1.setSd(sd);
		
		PrintPIThread th2 = new PrintPIThread(sd);
		
		th1.start();
		th2.start();

	}

}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread {
	private ShareData sd;	// 공통으로 사용할 객체의 참조값이 저장될 변수 선언
	
	// 방법1 ==> setter를 이용하여 공통으로 사용할 객체 초기화하기
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
		원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 -...) * 4
				1 - 3 + 5 - 7 + 9 - ...
				
				0 - 1 + 2 - 3 + 4 - 5... ==> 2로 나눈 몫
		*/
		double sum = 0.0;
		for(int i=1; i<2_000_000_000; i+=2) {
			if((i/2) % 2 == 0) {	// 몫이 짝수일 때...
				sum += (1.0 / i);
			} else {
				sum -= (1.0 / i);
			}
		}
		
		sd.result = sum * 4;	// 계산 결과를 공통객체의 result변수에 저장한다.
		sd.isOk = true;			// 계산이 완료됨을 설정한다.
		
	}
}

// 계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread {
	private ShareData sd;	// 공통으로 사용할 객체의 참조값이 저장될 변수 선언
	
	// 방법2 ==> 생성자를 이용하여 공통으로 사용할 객체 초기화하기
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk==true) {	// 계산이 완료되면 반복문을 빠져 나온다.
				break;
			}
		}
		
		// 계산된 결과를 출력한다.
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
	
}

// 원주율을 관리하는 객체 (공통으로 사용할 객체)
class ShareData {
	public double result;		// 계산된 원주율이 저장될 변수
	public volatile boolean isOk;		// 계산이 완료되었는지 여부를 나타내는 변수
	
	// volatile ==> CPU의 각 코어에 캐시가 있는데 이 캐시를 사용하지 않고
	//				직접 메모리에서 데이터값을 입출력하도록 한다.
}