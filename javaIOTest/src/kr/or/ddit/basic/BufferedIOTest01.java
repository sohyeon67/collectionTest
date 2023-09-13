package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferText.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(int i='1'; i<='9'; i++) {
				// cpu => buffer
				bout.write(i);
			}
			
			//bout.flush();	// 출력 버퍼에 남아 있는 데이터를 강제로 모두 출력 시킨다.
			
			bout.close();	// 보조 스트림을 닫으면 보조 스트림에서 사용한 
							// 기반 스트림도 같이 닫힌다.
							// Buffered스트림의 close()메서드에는 flush기능이 내장되어 있다.
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
