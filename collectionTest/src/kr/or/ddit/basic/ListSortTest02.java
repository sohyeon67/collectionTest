package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------------------------");

		// Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬하시오.
		// (클래스명 : SortNumDesc )

		// 외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(memList, new SortNumDesc());
		System.out.println("내림차순 정렬후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
	}

}

class Member {
	private int num;
	private String name;
	private String tel;

	// 단축키 : alt+shift+s
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

}

class SortNumDesc implements Comparator<Member> { // Member를 정렬
	// compare()메서드의 매개변수는 서로 인접한 2개의 데이터가 저장된다고 가정하여 처리한다.

	// compare()메서드의 반환값의 의미
	// 반환값이 '0'은 두 값이 같다. (순서는 바뀌지 않는다.)
	// 반환값이 '양수'일 경우에는 앞, 뒤의 순서를 바꾼다.
	// 반환값이 '음수'일 경우에는 순서를 바꾸지 않는다.

	// 예) 오름차순 정렬일 경우 ==> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현하면 된다.

	@Override
	public int compare(Member o1, Member o2) {
		// 내림차순
		// compareTo() 메서드 사용
//		if(Integer.valueOf(o1.getNum()).compareTo(o2.getNum()) > 0) {
//			return -1;
//		} else if(Integer.valueOf(o1.getNum()).compareTo(o2.getNum()) < 0) {
//			return 1;
//		} else {
//			return 0;
//		}

		if (o1.getNum() > o2.getNum()) {
			return -1;
		} else if (o1.getNum() < o2.getNum()) {
			return 1;
		} else {
			return 0;
		}
	}
}