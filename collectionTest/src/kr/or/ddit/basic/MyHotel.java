package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*

문제) 호텔 객실을 관리하는 프로그램을 작성하시오.

   조건1)  호텔 객실을 나타내는 Room클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성되어 있고			     
           방번호와 방종류는 다음과 같다.
           - 201~209 : 싱글룸
           - 301~309 : 더블룸
           - 401~409 : 스위트룸

   조건2) 전체 객실 관리는 Map을 이용한다.
          (Map의 key값은 방번호로 하고 value값은 Room의 인스턴스로 한다.)

          호텔 객실을 관리하는 객체의 생성자에서는 방번호와 방종류를 초기화한다.




실행예시)

*********************************************
       호텔문을 열었습니다. 어서오십시요.
*********************************************


-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	1     <--- 입력

----------------------------------------------
   체크인 작업
----------------------------------------------
 * 201~209 : 싱글룸
 * 301~309 : 더블룸
 * 401~409 : 스위트룸
----------------------------------------------
방 번호 입력 >> 101     <--- 입력
101호 객실은 존재하지 않습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	1     <--- 입력

----------------------------------------------
   체크인 작업
----------------------------------------------
 * 201~209 : 싱글룸
 * 301~309 : 더블룸
 * 401~409 : 스위트룸
----------------------------------------------
방 번호 입력 >> 201     <--- 입력
누구를 체크인 하시겠습니까?
이름 입력 >> 홍길동
체크인이 완료되었습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	1     <--- 입력

----------------------------------------------
   체크인 작업
----------------------------------------------
 * 201~209 : 싱글룸
 * 301~309 : 더블룸
 * 401~409 : 스위트룸
----------------------------------------------
방 번호 입력 >> 201     <--- 입력
201호 객실은 이미 손님이 있습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	3     <--- 입력

----------------------------------------------
		현재 객실 상태
----------------------------------------------
방 번호	 방 종류	 투숙객 이름
----------------------------------------------
201	 싱글룸 	홍길동	
202	 싱글룸 	  -	
203	 싱글룸 	  -	
204	 싱글룸 	  -	
205	 싱글룸 	  -	
206	 싱글룸 	  -	
207	 싱글룸 	  -	
208	 싱글룸 	  -	
209	 싱글룸 	  -	
301	 더블룸 	  -	
302	 더블룸 	  -	
303	 더블룸 	  -	
304	 더블룸 	  -	
305	 더블룸 	  -	
306	 더블룸 	  -	
307	 더블룸 	  -	
308	 더블룸 	  -	
309	 더블룸 	  -	
401	스위트룸	  -	
402	스위트룸	  -	
403	스위트룸	  -	
404	스위트룸	  -	
405	스위트룸	  -	
406	스위트룸	  -	
407	스위트룸	  -	
408	스위트룸	  -	
409	스위트룸	  -	
----------------------------------------------

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	2    <--- 입력

----------------------------------------------
   체크아웃 작업
----------------------------------------------
체크아웃 할 방 번호를 입력하세요.
방번호 입력 >> 101    <--- 입력
101호 객실은 존재하지 않습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	2    <--- 입력

----------------------------------------------
   체크아웃 작업
----------------------------------------------
체크아웃 할 방 번호를 입력하세요.
방번호 입력 >> 303    <--- 입력
303호 객실에는 체크인 한 사람이 없습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	2    <--- 입력

----------------------------------------------
   체크아웃 작업
----------------------------------------------
체크아웃 할 방 번호를 입력하세요.
방번호 입력 >> 201    <--- 입력
201호 객실의 홍길동님 체크아웃을 완료하였습니다.

-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	3    <--- 입력


----------------------------------------------
		현재 객실 상태
----------------------------------------------
방 번호	 방 종류	 투숙객 이름
----------------------------------------------
201	 싱글룸 	  -	
202	 싱글룸 	  -	
203	 싱글룸 	  -	
204	 싱글룸 	  -	
205	 싱글룸 	  -	
206	 싱글룸 	  -	
207	 싱글룸 	  -	
208	 싱글룸 	  -	
209	 싱글룸 	  -	
301	 더블룸 	  -	
302	 더블룸 	  -	
303	 더블룸 	  -	
304	 더블룸 	  -	
305	 더블룸 	  -	
306	 더블룸 	  -	
307	 더블룸 	  -	
308	 더블룸 	  -	
309	 더블룸 	  -	
401	스위트룸	  -	
402	스위트룸	  -	
403	스위트룸	  -	
404	스위트룸	  -	
405	스위트룸	  -	
406	스위트룸	  -	
407	스위트룸	  -	
408	스위트룸	  -	
409	스위트룸	  -	
----------------------------------------------


-----------------------------------------------------------
어떤 업무를 하시겠습니까?
1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료
-----------------------------------------------------------
선택>>	4    <--- 입력

*********************************************
       호텔문을 닫았습니다.
*********************************************

 */

public class MyHotel {
	private HashMap<Integer, Room> hotelMap;
	private Scanner scan;

	// 생성자 ==> 객실 초기화 작업
	public MyHotel() {
		hotelMap = new HashMap<Integer, Room>();
		scan = new Scanner(System.in);

		for (int i = 201; i <= 209; i++) {
			hotelMap.put(i, new Room(i, "싱글룸"));
		}

		for (int i = 301; i <= 309; i++) {
			hotelMap.put(i, new Room(i, "더블룸"));
		}

		for (int i = 401; i <= 409; i++) {
			hotelMap.put(i, new Room(i, "스위트룸"));
		}
	}

	public static void main(String[] args) {
		new MyHotel().startMyHotel();
	}

	public void startMyHotel() {
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				displayState();
				break;
			case 4:
				System.out.println();
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다...");
			}
		}

	}

	private void checkIn() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");

		int roomNum = scan.nextInt();

		String roomType;
		if (roomNum >= 201 && roomNum <= 209) {
			roomType = "싱글룸";
		} else if (roomNum >= 301 && roomNum <= 309) {
			roomType = "더블룸";
		} else if (roomNum >= 401 && roomNum <= 409) {
			roomType = "스위트룸";
		} else {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}

		if (!hotelMap.get(roomNum).getName().equals("-")) {
			System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		Room room = new Room(roomNum, roomType);
		room.setName(name);
		hotelMap.put(roomNum, room);

		System.out.println("체크인이 완료되었습니다.");
	}

	private void displayState() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");

		Set<Integer> hotelSet = hotelMap.keySet();
		
		ArrayList<Integer> hotelList = new ArrayList<Integer>(hotelSet);
		
		Collections.sort(hotelList);
		for(int key : hotelList) {
			Room r = hotelMap.get(key);
			System.out.println(r.getRoomNum() + "\t" + r.getRoomType() + "\t" + r.getName());
		}
	}

	private void checkOut() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");

		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int roomNum = scan.nextInt();

		
		String roomType;
		if (roomNum >= 201 && roomNum <= 209) {
			roomType = "싱글룸";
		} else if (roomNum >= 301 && roomNum <= 309) {
			roomType = "더블룸";
		} else if (roomNum >= 401 && roomNum <= 409) {
			roomType = "스위트룸";
		} else {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		String name = hotelMap.get(roomNum).getName();
		
		if (name.equals("-")) {
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		
		hotelMap.get(roomNum).setName("-");	// remove쓰면 안됨
		System.out.println(roomNum + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");
		
		
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택 >> ");

		return scan.nextInt();
	}

}

class Room {
	private int roomNum;
	private String roomType;
	private String name;

	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		name = "-";
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}