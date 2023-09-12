package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection 클래스 ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 URL의 내용 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/");
		
		// URLConnection객체 구하기 ==> URL 객체를 이용하여 구한다.
		URLConnection urlCon = url.openConnection();
		
		// Header정보 가져오기
		//	==> 구한 Map객체의 key값은 Header정보의 이름을 나타내고
		//				  value값은 Header정보의 값을 나타낸다.
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println("---------------------------------------------------------------");
		
		// 해당 URL이 지정한 문서의 내용을 가져오기 (즉 index.html문서 내용 가져오기)
/*		
		// 방법1 ==> URLConnection객체를 이용하는 방법
		// 파일을 가져오기 위한 입력 스트림 객체 생성
		InputStream in = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 자료 내용을 읽어와 화면에 출력하기
		while(true) {
			String str = br.readLine();	// 한 줄씩 읽어오기
			if(str == null) break;
			System.out.println(str);
		}
		
		br.close();
*/	
		
		// 방법2 ==> URL객체를 이용하는 방법 (URL객체의 openStream()메서드 이용)
		InputStream in2 = url.openStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2, "utf-8"));
		
		String temp;
		while( (temp = br2.readLine()) != null) {
			System.out.println(temp);
		}
		
		br2.close();
	}

}
