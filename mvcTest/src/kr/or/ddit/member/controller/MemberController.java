package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberController {
	private IMemberService service; // Service객체 변수 선언
	private Scanner scan;

	// 생성자
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 시작 메서드
	public void startMember() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;
			case 3: // 수정 ==> 전체 항목 수정
				updateMember();
				break;
			case 4: // 전체 출력
				displayAllMember();
				break;
			case 5: // 수정2 ==> 원하는 항목 수정
				updateMember2();
				break;
			case 0: // 끝.
				System.out.println();
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println();
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요...");
			}
		}
	}
	
	// 원하는 항목만 수정하는 메서드
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원 ID >> ");
		String id = scan.next();
		
		if(service.getMemIdCount(id)==0) {
			System.out.println(id + "은(는) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		int num;	// 수정 항목 선택 번호가 저장될 변수
		String updateField = null;	// 수정할 컬럼명이 저장될 변수
		String updateTitle = null;	// 수정할 데이터를 입력 받을 때 출력할 메시지
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1.비밀번호    2.회원이름    3.전화번호    4.회원주소");
			System.out.println("------------------------------------");
			System.out.print("수정할 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
			case 1:
				updateField = "mem_pass";
				updateTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateTitle = "회원주소";
				break;
			default:
				System.out.println("수정할 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요...");
			}
		}while(num < 1 || num > 4);
		
		scan.nextLine();	// 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		// 수정할 정보를 Map에 저장한다.
		// key값 정보 ==> 회원ID(memid), 수정할컬럼명(field), 수정할데이터(data)
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memid", id);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt > 0) {
			System.out.println(id + "회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + "회원 정보 수정 실패~~~");
		}
	}

	// 전체 회원 정보 출력
	private void displayAllMember() {
		// DB에 저장된 데이터 가져오기
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println(" ID\t 비밀번호\t 이름\t 전화번호\t\t 주소");
		System.out.println("----------------------------------------------------");
		if(memList==null || memList.size()==0) {
			System.out.println("\t등록된 회원 정보가 하나도 없습니다.");
		} else {
			for(MemberVO memVo : memList) {
				System.out.println(memVo.getMem_id() + "\t" + memVo.getMem_pass() + "\t"
				+ memVo.getMem_name() + "\t" + memVo.getMem_tel() + "\t" + memVo.getMem_addr());
			}
		}
		
		System.out.println("----------------------------------------------------");

	}

	// 수정 메서드
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원 ID >> ");
		String id = scan.next();

		if (service.getMemIdCount(id) == 0) {
			System.out.println(id + "은(는) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}

		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.next();

		System.out.print("새로운 회원이름 >> ");
		String newName = scan.next();

		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();

		// 입력받은 수정할 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(newPass);
		memVo.setMem_name(newName);
		memVo.setMem_tel(newTel);
		memVo.setMem_addr(newAddr);

		int cnt = service.updateMember(memVo);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 수정 완료!!!");
		} else {
			System.out.println(id + "회원 정보 수정 실패~~~");
		}
	}

	// 삭제 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("삭제할 회원 ID >> ");
		String id = scan.next();

		if (service.getMemIdCount(id) == 0) {
			System.out.println(id + "은(는) 없는 회원ID 입니다...");
			System.out.println("삭제 작업을 마칩니다...");
			return;
		}

		int cnt = service.deleteMember(id);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 삭제 성공!!!");
		} else {
			System.out.println(id + "회원 정보 삭제 실패~~~");
		}
	}

	// 추가 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("새롭게 추가할 회원 정보를 입력하세요...");

		int count = 0;
		String id = null; // 입력한 회원ID가 저장될 변수

		do {
			System.out.print("회원ID >> ");
			id = scan.next();

			count = service.getMemIdCount(id);

			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
				System.out.println();
			}

		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String pass = scan.next();

		System.out.print("회원이름 >> ");
		String name = scan.next();

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();

		// 입력 받은 데이터들을 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);

		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + "회원 정보 추가 실패~~~");
		}

	}

	// 메뉴를 출력하고 입력받은 선택 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("== 작업 선택 ==");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println("------------");

		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}

}
