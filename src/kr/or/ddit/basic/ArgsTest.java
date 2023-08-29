package kr.or.ddit.basic;

public class ArgsTest {
	
	/*
	접근제한자 반환값타입 메서드명( 매개변수들... ) {
	
	}
	
	public static void test(int a, int b) {
		
	}
	*/
	
	// 매개변수로 받은 정수들의 합계를 구하는 메서드를 작성하시오.
	// (단, 이 정수들의 개수는 상황에 따라 다를 수 있다.)
	
	// 배열 이용하기
	public int sumArr(int[] data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// 가변형 인수 ==> 메서드를 호출할 때 사용되는 인수의 개수가 호출할 때 마다 다를 때 사용한다.
	// - 가변형 인수는 구현한 메서드 안에서는 배열로 처리된다.
	// - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	
	public int sumArg(int...data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤 쪽에 배치해야 한다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		return name + "씨의 점수 ==> " + sum;
	}

	public static void main(String[] args) {
		//test(10, 20);
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300, 400};	// 배열 선언과 초기화를 동시에 할 때
		
		// 배열 선언과 초기화를 동시에 할 수도 있고 따로 따로 할 수도 있다.
		int[] nums2 = new int[] {100, 200, 300, 400};
//		int[] nums2;
//		nums2 = new int[] {100, 200, 300, 400};
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5}));
		System.out.println("------------------------------------");
		System.out.println(test.sumArg(100,200,300,400));
		System.out.println(test.sumArg(1,2,3,4,5));
		System.out.println("------------------------------------");
		System.out.println(test.sumArg2("홍길동", 1,2,3,4,5,6,7,8,9,10));
	}

}
