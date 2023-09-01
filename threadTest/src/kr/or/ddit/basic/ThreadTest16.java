package kr.or.ddit.basic;

// 은행의 입출금을 쓰레드로 처리하는 예제


public class ThreadTest16 {	// 공통 객체
	private int balance;	// 잔액이 저장될 변수
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드 (반환값 : 출금 성공 => true, 출금 실패 => false)
	public boolean withdraw(int money) {
		if(balance >= money) {
			for(int i=1; i<=100000000; i++) {	// 시간 지연용
				int a = i + 3;
			}
			
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
