package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용하여 정하고,
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력 받는다.
	
	입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초 안에 입력이 없으면 게임에 진 것으로 처리하고 프로그램을 종료한다.
	
	5초 안에 입력이 있으면 승패를 구해서 결과를 출력한다.
	
	결과예시)
	1) 5초 안에 입력을 못했을 경우
		-- 결  과 --
	시간 초과로 당신이 졌습니다...
	
	2) 5초 안에 입력했을 경우
		-- 결  과 --
		컴퓨터 : 가위
		당 신 : 바위
		결 과 : 당신이 이겼습니다.
		

 */

public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new DataInput2();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
	}

}

//데이터를 입력하는 쓰레드 클래스
class DataInput2 extends Thread {
	// 입력 완료 여부를 확인하기 위한 변수 선언 (쓰레드에서 공통으로 사용할 변수)
	public static boolean inputCheck = false;
	
	
	@Override
	public void run() {
		int com = (int)(Math.random()*3+1);
		String comInput = (com == 1)? "가위" : (com == 2? "바위" : "보");
		
		String userInput = JOptionPane.showInputDialog("입력하세요...");
		inputCheck = true;	// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		
		int user = (userInput.equals("가위"))? 1 : (userInput.equals("바위")? 2 : 3);
		int result = user-com;	//0:비김 1,-2:이김 -1,2:짐 
		
		System.out.println("-- 결과 --");
		System.out.println("컴퓨터 : " + comInput);
		System.out.println("당  신 : " + userInput);
		if(result==0)
			System.out.println("결  과 : 비겼습니다.");
		else if(result==1 || result==-2)
			System.out.println("결  과 : 당신이 이겼습니다.");
		else
			System.out.println("결  과 : 당신이 졌습니다.");

	}
}

//카운트 다운을 진행하는 쓰레드 클래스
class CountDown2 extends Thread {
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			// 입력이 완료되었는지 여부를 검사한다.
			// ==> 입력이 완료되면 쓰레드를 종료시킨다.
			if(DataInput2.inputCheck == true) {
				return;		// run()메서드가 종료되면 쓰레드도 종료된다.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("-- 결과 --");
		System.out.println("시간 초과로 당신이 졌습니다...");
		System.exit(0);
	}
}