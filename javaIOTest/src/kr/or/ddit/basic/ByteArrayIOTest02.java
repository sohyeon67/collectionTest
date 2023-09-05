package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		// 스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		try {
			// bin.available() ==> 읽어올 수 있는(남아있는) 데이터의 개수 반환
			while(bin.available()>0) {
//				bin.read(temp);		// 배열 크기만큼 읽어오기
//				bout.write(temp);	// 배열의 데이터 출력하기
				
				// read(byte[] 배열명) ==> 반환값 : 실제 읽어온 데이터 개수
				int len = bin.read(temp);
				
				// temp배열의 내용 중 0번째 부터 len 개수 만큼 출력한다.
				bout.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp => " 
								+ Arrays.toString(temp));
			}
			
			outSrc = bout.toByteArray();
			
			System.out.println(" inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			// 스트림 닫기
			bin.close();
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
