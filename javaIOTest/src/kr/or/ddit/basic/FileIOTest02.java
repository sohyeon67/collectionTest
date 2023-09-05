package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 바이트 기반 스트림을 이용한 파일로 출력하기 예제
		try {
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/out.txt"); // 없으면 생성, 있으면 덮어쓰기
			
			for(char ch='A'; ch<='Z'; ch++) {
				fout.write(ch);		// ch변수의 데이터를 파일로 출력한다.
			}
			
			System.out.println("출력 작업 완료!!!");
			
			// 스트림 닫기
			fout.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			
		}
	}

}
