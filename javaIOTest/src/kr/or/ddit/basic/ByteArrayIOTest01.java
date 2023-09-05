package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		// 입력용 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		// 입력용 스트림을 이용하여 데이터를 읽어와서
		// 출력용 스트림을 이용하여 출력하기
		
		int data;		// 읽어온 자료가 저장될 변수
		
		// read()메서드 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while( (data = bin.read()) != -1) { // 읽어오기
			
			// 읽어온 자료를 처리하는 작업을 작성한다.
			
			bout.write(data);	// 출력하기
		}
		
		// 출력된 스트림값을 배열로 변환하기
		outSrc = bout.toByteArray();
		
		// 입출력 작업이 완료되면 사용했던 자원(스트림 객체)을 반납한다.
		// ==> 이 때는 close()메서드를 사용한다.
		try {
			bin.close();
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		System.out.println(" inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
	}

}
