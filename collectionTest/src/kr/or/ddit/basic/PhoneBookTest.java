package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고, 
		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
		
		(Map의 구조는 key값으로 입력한 '이름'을 사용하고, value값으로는 'Phone클래스의 인스턴스'로 한다.)
		변수 선언 예시 ) HashMap<String, Phone> 변수명;
		
		이 프로그램에는 다음과 같은 메뉴가 있는데 각 메뉴의 기능을 구현한다.
		----------------
		다음 메뉴를 선택하세요.
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		----------------
		
		- 검색, 삭제 기능은 '이름'을 입력 받아 처리한다.
		
실행 예시)
		----------------
		다음 메뉴를 선택하세요.
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		----------------
		메뉴 선택 >> 1
		
		새롭게 등록할 전화번호 정보를 입력하세요.
		이      름 >> 홍길동
		전화번호 >> 010-1234-5678
		주      소 >> 대전시 중구 오류동
		
		'홍길동' 전화번호 등록 완료!!!
		
		----------------
		다음 메뉴를 선택하세요.
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		----------------
		메뉴 선택 >> 1
		
		새롭게 등록할 전화번호 정보를 입력하세요.
		이      름 >> 홍길동
		
		'홍길동'은 이미 등록된 사람입니다..
		
		----------------
		다음 메뉴를 선택하세요.
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		----------------
		메뉴 선택 >> 5
		
		-------------------------------------------
		번호	이름		전화번호			주소
		-------------------------------------------
		1	홍길동	010-1234-5678	대전시 중구 오류동
			~~~
			~~~
		-------------------------------------------
		출력 끝...
		
		----------------
		다음 메뉴를 선택하세요.
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		----------------
		메뉴 선택 >> 0
		
		프로그램을 종료합니다.

*/

public class PhoneBookTest {
	private HashMap<String, Phone> phone = new HashMap<String, Phone>();
	private Scanner scan = new Scanner(System.in);
	private List<Phone> phoneList = new ArrayList<Phone>();

	String name;
	String address;
	String tel;

	public void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이      름 >> ");
		name = scan.nextLine();
		if (phone.containsKey(name)) {
			System.out.println();
			System.out.println("'" + name + "'은 이미 등록된 사람입니다..");
			System.out.println();
		} else {
			System.out.print("전화번호 >> ");
			tel = scan.nextLine();
			System.out.print("주      소 >> ");
			address = scan.nextLine();

			Phone p = new Phone(name, address, tel);
			phone.put(name, p);
			phoneList.add(p);
			System.out.println("'" + name + "' 전화번호 등록 완료!!!");
			System.out.println();
		}
	}

	public void update() {
		System.out.println();
	    System.out.print("전화번호를 수정할 이름을 입력하세요 >> ");
	    name = scan.nextLine();
	    
	    if(phone.containsKey(name)) {
			for (int i = 0; i < phoneList.size(); i++) {
				if (phoneList.get(i).getName().equals(name)) {
		            System.out.print("새로운 전화번호 >> ");
		            tel = scan.nextLine();
//		            System.out.print("새로운 주소 >> ");
//		            address = scan.nextLine();
		            
		            phone.get(name).setTel(tel);
//		            phone.get(name).setAddress(address);

		            
		            System.out.println("'" + name + "' 전화번호 변경 완료!!!");
		            System.out.println();
				}
			}
		} else {
			System.out.println("정보가 없습니다...");
			System.out.println();
		}
	}

	public void delete() {
		System.out.println();
		System.out.print("삭제할 이름을 입력하세요 >> ");
		name = scan.nextLine();
		
		if(phone.containsKey(name)) {
			for (int i = 0; i < phoneList.size(); i++) {
				if (phoneList.get(i).getName().equals(name)) {
					phoneList.remove(i);
					phone.remove(name);
					System.out.println("'" + name + "' 전화번호 삭제 완료!!!");
					System.out.println();
				}
			}
		} else {
			System.out.println("정보가 없습니다...");
			System.out.println();
		}

	}

	public void search() {
		System.out.println();
		System.out.print("검색할 이름을 입력하세요 >> ");
		name = scan.nextLine();
		
		if(phone.containsKey(name)) {
			for (Phone phone : phoneList) {
				if (phone.getName().equals(name)) {
					System.out.println();
					System.out.println("-------------------------------------------");
					System.out.println("   이름          전화번호                         주소");
					System.out.println("-------------------------------------------");
					System.out.printf("%-12s", phone.getName());
					System.out.printf("%-15s", phone.getTel());
					System.out.printf("%-20s\n", phone.getAddress());
					System.out.println("-------------------------------------------");
					System.out.println("검색 완료...");
					System.out.println();
				}
			}
		} else {
			System.out.println("정보가 없습니다...");
			System.out.println();
		}
	}

	public void print() {
		int num = 1;

		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("번호      이름          전화번호                         주소");
		System.out.println("-------------------------------------------");
		for (Phone phone : phoneList) {
			System.out.printf("%-5d", num++);
			System.out.printf("%-12s", phone.getName());
			System.out.printf("%-15s", phone.getTel());
			System.out.printf("%-20s\n", phone.getAddress());
		}
		System.out.println("-------------------------------------------");
		System.out.println("출력 끝...");
		System.out.println();
	}

	public void home() {
		while (true) {
			System.out.println("------------------");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("------------------");
			System.out.print("메뉴 선택 >> ");

			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				print();
				break;
			default:
				System.out.println();
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}

	}

	public static void main(String[] args) {
		PhoneBookTest phone = new PhoneBookTest();
		Scanner scan = new Scanner(System.in);

		phone.home();

	}

}

class Phone {
	private String name;
	private String address;
	private String tel;

	public Phone() {
		super();
	}

	public Phone(String name, String address, String tel) {
		super();
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}