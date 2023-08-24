package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 문제) 학번(int), 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화한다.
	(이 때 총점은 세 과목의 점수를 이용해서 초기화 한다.)
 	 
	이 Student객체는 List에 저장하여 관리한다.
	
	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성하여
	정렬한 결과를 출력하시오.
	
	(등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.) ==> StudentTest클래스에서 처리한다.
*/

public class StudentTest {

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		studentList.add(new Student(1, "홍길동", 50, 60, 70));
		studentList.add(new Student(2, "이순신", 90, 80, 70));
		studentList.add(new Student(3, "성춘향", 70, 60, 50));
		studentList.add(new Student(4, "강감찬", 50, 70, 60));
		studentList.add(new Student(5, "일지매", 70, 70, 70));
		
		// 등수 구하기...??
		
		
		System.out.println("정렬전...");
		for (Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("-----------------------------------------------");
		
		Collections.sort(studentList);
		System.out.println("학번의 오름차순 정렬후...");
		for (Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("-----------------------------------------------");
		
		Collections.sort(studentList, new SortSumDesc());
		System.out.println("총점의 내림차순 정렬후...");
		
		int rank = 5;
		for (Student s : studentList) {
			s.setRank(rank--);
			System.out.println(s);
		}
	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student s) {

		return Integer.compare(this.getNum(), s.getNum());
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", rank=" + rank + "]";
	}
	
	

}

class SortSumDesc implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		if(Integer.compare(s1.getSum(), s2.getSum()) == 0) {
			return s1.getName().compareTo(s2.getName());
		} else {
			return Integer.compare(s1.getSum(), s2.getSum()) * -1;
		}
	}
	
}
