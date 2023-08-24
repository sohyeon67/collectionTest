package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
	/*
	- List와 Set의 차이점
	1. List
		- 데이터의 순서(index)가 있다.
		- 중복되는 데이터를 저장할 수 있다.
		
	2. Set
		- 데이터의 순서(index)가 없다.
		- 중복되는 데이터를 저장할 수 없다.
	*/
		HashSet hs1 = new HashSet();
		
		// Set에 데이터 추가하기 ==> add()메서드를 이용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("set 데이터 ==> " + hs1);
		System.out.println("set의 개수 ==> " + hs1.size());
		System.out.println();
		
		// Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 isAdd = " + isAdd);
		System.out.println("set 데이터 ==> " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 isAdd = " + isAdd);
		System.out.println("set 데이터 ==> " + hs1);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 해당 자료를 삭제한 후 추가해 주는 방법을 사용한다.
		
		// 삭제하는 메서드 ==> remove(삭제할자료)
		//				반환값 : 삭제성공(true), 삭제실패(false)
		// 전체 자료 삭제 ==> clear()
		
		// "FF"데이터를 "EE"로 변경하기
		hs1.remove("FF");
		System.out.println("삭제 후 set 데이터 ==> " + hs1);
		hs1.add("EE");
		System.out.println("set 데이터 ==> " + hs1);
		
//		hs1.clear();
//		System.out.println("clear명령 후 set 데이터 ==> " + hs1);
		
		/*
		Set의 데이터는 순서(index)가 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		그래서 데이터를 하나씩 차례로 얻기 위해서는 Iterator형 객체로 변환해야 한다.
		
		- Set형의 데이터들을 Iterator형의 객체로 변환하는 메서드 ==> iterator()
		 */
		
		Iterator it = hs1.iterator();	// Set 데이터를 Iterator로 변환하기
		
		// Iterator객체의 hasNext()메서드
		// 		==> Iterator의 포인터가 가리키는 곳 다음 번째에 데이터가 있는지 검사한다.
		// 			(데이터가 있으면 true, 데이터가 없으면 false를 반환한다.)
		System.out.println();
		System.out.println("Iterator를 이용한 데이터 출력하기...");
		while(it.hasNext()) {
			// Iterator객체의 next()메서드
			//  ==> Iterator의 포인터를 다음번째 위치로 이동한 후, 이동한 위치의 데이터를 반환한다.
			System.out.println(it.next());
		}
		System.out.println();
		
		System.out.println("향상된 for문 이용하기");
		for(Object obj : hs1) {
			System.out.println(obj);
		}
		
		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자
		// 번호는 1번부터 23번까지 있고, 추첨할 인원은 3명이다.
		// 당첨 번호를 출력해 보시오.
		
		// 최소값부터 최대값 사이의 정수형 난수값 만들기
		// (int)(Math.random() * (최대값-최소값+1) + 최소값)
		
		HashSet<Integer> testSet = new HashSet<Integer>();
		while(testSet.size() < 3) {
			testSet.add((int)(Math.random() * 23 + 1)); // 중복값 추가 x
		}
		System.out.println("당첨자 번호 : " + testSet);
		System.out.println();
		
		// Set유형의 자료를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
		System.out.println("List 데이터 출력...");
		for(int i=0; i<testList.size(); i++) {
			System.out.println(i + "번째 자료 ==> " + testList.get(i));
		}
		
	}

}
