package kr.or.ddit.basic;


/*
	제네릭 클래스 작성 방법
형식)
	class 클래스명<제네릭타입글자>{
		제네릭타입글자 변수명;		// 변수 선언에 제네릭을 사용할 경우
		...
		
		제네릭타입글자 메서드명( 매개변수들... ) {	// 메서드의 반환값에 제네릭을 사용할 경우
			~~~
			return 값;
		}
		
		반환값타입 메서드명(제네릭타입글자 변수명, ...) {	// 메서드의 매개변수에 제네릭을 사용할 경우
			~~~
			return 값;
		}
	}
*/


class NonGeneric {
	private Object value;
	
	public void setValue(Object value) {
		this.value=value;
	}
	
	public Object getValue() {
		return value;
	}
}

class MyGeneric<T> {
	private T value;
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
}

public class GenericTest {
	
	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라");
		
		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);
		
		String rtnNg1 = (String)ng1.getValue();
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		int rtnNg2 = (int)ng2.getValue();
		System.out.println("정수 반환값 rtnNg2 => " + rtnNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		mg1.setValue("우리나라");
//		mg1.setValue(1000);
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		mg2.setValue(500);
//		mg2.setValue("가나다라");
		
		String rtnMg1 = mg1.getValue();
		System.out.println("제네릭 문자열 반환값 : " + rtnMg1);
		int rtnMg2 = mg2.getValue();
		System.out.println("제네릭 정수형 반환값 : " + rtnMg2);
		
		// ---------------------------------------------
		String temp = (String)ng2.getValue();	// 제네릭을 사용하지 않았을 때 문제점. 문법적으로는 에러x.
		System.out.println("temp = " + temp);

	}

}
