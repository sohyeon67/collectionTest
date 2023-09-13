package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("일지매");
		p2.setNum(1);
		p2.setName("홍길동");
		
		Person p3 = p1;
		
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);
		System.out.println();
		
		System.out.println(p1.equals(p2));	// 재정의 전 : false, 후 : true
		System.out.println(p1.equals(p3));

		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("set의 개수 : " + testSet.size());
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());
		
		/*
		- equals()메서드		==> 두 객체의 내용이 같은지 비교하는 연산자 (동등성비교)
		- hashCode()메서드	==> 두 객체가 같은 객체인지를 비교하는 메서드 (동일성비교)
		
		 */
	}

}

// num변수의 값과 name변수의 값 두 가지 모두 같으면 true를 반환하도록 재정의 해보자.
class Person {
	private int num;
	private String name;
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if(this==obj) {		// 참조값(주소값)이 같은지 검사
//			return true;
//		}
//		if(obj==null) {
//			return false;
//		}
//		
//		if(this.getClass() != obj.getClass()) {
//			return false;
//		}
//		
//		Person that = (Person)obj;	// 매개변수의 값을 현재 객체 유형으로 형변환한다.
//		
//		// 각 변수값들이 모두 같은지 검사한다.
//		return this.num == that.num && Objects.equals(this.name, that.name);
//		
//	}
//	
//	@Override
//	public int hashCode() {
//		return Objects.hash(num, name);
//	}
	
}