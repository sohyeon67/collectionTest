package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반의 스트림을 이용하여 파일 내용 읽어와 출력하기
		try {
			// 파일 입력용 스트림 객체 생성
			
			// 방법1
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// 방법2
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c;		// 읽어온 데이터가 저장될 변수
			while( (c = fin.read()) != -1 ) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)c);
			}
			
			// 작업 완료 후 스트림 닫기
			fin.close();
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
			e.printStackTrace();
		}
	}

}
