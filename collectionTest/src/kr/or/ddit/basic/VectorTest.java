package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();

		System.out.println("처음 크기 : " + v1.size());

		// 데이터 추가하기1 : add(추가할 데이터)
		// 반환값 : 추가 성공(true), 추가 실패(false)

		v1.add("aaaa");
		v1.add(new Integer(111));
		v1.add(123); // 오토박싱, 오토 언박싱이 지원된다.
		v1.add('a');
		v1.add(true);

		boolean r = v1.add(3.14);

		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);

		// 데이터 추가하기2 : addElement(추가할 데이터)
		// ==> 이전 버전의 프로그램과 호환성을 위해서 남아 있는 메서드
		v1.addElement("CCC");

		System.out.println("v1 ==> " + v1);

		// 데이터 추가하기3 : add(index, 추가할데이터)
		// ==> 'index'번째에 '추가할 데이터'를 끼워 넣는다.
		// ==> 'index'는 0부터 시작한다.
		// ==> 반환값은 없다.
		v1.add(1, "KKK");
		System.out.println("v1 ==> " + v1);

		// 데이터 꺼내오기 : get(index)
		// ==> 'index'번째의 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));

		// Integer iTemp = v1.get(2);
		int iTemp = (int) v1.get(2); // v1.get()한 데이터는 Object. int로 오토언박싱
		System.out.println("2번째 데이터 : " + iTemp);

		System.out.println();

		// 데이터 수정(변경)하기 : set(index, 새로운데이터)
		// ==> 'index'번째의 데이터를 '새로운 데이터'로 변경한다.
		// ==> 반환값 : 변경되기 전의 원래의 데이터가 반환된다.
		String sTemp = (String) v1.set(0, "zzzz");
		System.out.println("v1 ==> " + v1);
		System.out.println("원래의 데이터 : " + sTemp);

		// 데이터 삭제하기1 : remove(index)
		// ==> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 ==> " + v1);

		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 ==> " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);

		// 데이터 삭제하기2 : remove(삭제할데이터)
		// ==> '삭제할 데이터'를 찾아서 삭제한다.
		// ==> '삭제할 데이터'가 여러개이면 이 중에 제일 첫번째 자료가 삭제된다.
		// ==> 반환값 : 삭제성공(ture), 삭제실패(false)
		// ==> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 한다.

		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 ==> " + v1);

		// v1.remove(123); 에러
		// v1.remove(new Integer(123)); // 방법1 ==> 자바버전 1.9 이상에서는 사용 불가로 변경됨
		v1.remove(Integer.valueOf(123)); // 방법2

		System.out.println("123 삭제 후 v1 ==>" + v1);

		// v1.remove('a'); // ArrayIndexOutOfBoundsException: Array index out of range: 97
		v1.remove(Character.valueOf('a'));
		System.out.println("삭제 후 v1 ==> " + v1);

		v1.remove(3.14);
		System.out.println("삭제 후 v1 ==> " + v1);

		v1.remove(true);
		System.out.println("삭제 후 v1 ==> " + v1);

		// 전체 데이터 삭제하기 : clear()
		v1.clear();
		System.out.println("clear() 후 v1 ==> " + v1);
		System.out.println("현재 크기 : " + v1.size());
		System.out.println();

		// -----------------------------------------------------------
		/*
		  제네릭타입(Generic Type) ==> 클래스 내부에서 사용할 데이터 타입을 객체를 생성할 때 외부에서 지정해 주는 기법으로 객체를
		  선언할 때 괄호(< >)안에 그 객체의 내부에서 사용할 데이터의 타입을 지정해 주는 것을 말한다. 
		  - 이런 식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다. 
		  - 이 때 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이어야 한다. 
		  	그래서 int는 Integer, boolean은 Boolean, char는 Character 등으로 대체해서 사용해야 한다. 
		  - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		Vector<Integer> v2 = new Vector<Integer>(); // int형 자료만 저장할 수 있는 Vector
		Vector<String> v3 = new Vector<String>(); // String형 자료만 저장할 수 있는 Vector

		v3.add("안녕하세요");
		// v3.add(123); // 오류 : 지정한 제네릭 타입과 다른 종류의 데이터를 저장할 수 없다.

		String sTemp2 = v3.get(0); // 형변환 없이 데이터를 꺼내올 수 있다. 안에 들어있는 것이 String 밖에 없어서

		Vector<Vector> vv = new Vector<Vector>();
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
		System.out.println("--------------------------------------------");
		// ----------------------------------------------------------------------------------

		v3.clear();
		System.out.println("v3의 size : " + v3.size());

		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");

		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");

		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);

		// 데이터 삭제하기3 : removeAll(Collection객체)
		// ==> 벡터의 데이터 중에서 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다.
		// ==> 반환값 : 성공(ture), 실패(false)
		v3.removeAll(v4);

		System.out.println("삭제 작업 후 v3 => " + v3);
		System.out.println("삭제 작업 후 v4 => " + v4);
		System.out.println("--------------------------------------------");
		System.out.println();
		v3.clear();

		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");

		// 벡터에 저장된 데이터들을 순서대로 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문 사용)
		for (int i = 0; i < v3.size(); i++) {
			System.out.println(i + "번째 데이터 : " + v3.get(i));
		}
		System.out.println("--------------------------------------------");
		
		for(String str : v3) {
			System.out.println(str);
		}

	}

}
