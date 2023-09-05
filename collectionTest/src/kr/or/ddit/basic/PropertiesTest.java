package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
	/*
		Properties객체
		- Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		
		- Map은 key값과 value값에 모든 종류의 객체를 사용할 수 있지만
		  Properties는 key값과 value값에 String만 사용할 수 있다.
		  
		- Map은 put(), get()메서드를 이용하여 데이터를 입출력 하지만
		Properties는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력 한다.
		
		- Properties는 데이터를 파일로 입출력하는 기능이 있다.
		
	 */
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		int age = 30;
//		prop.setProperty("age2", age + "");
		prop.setProperty("age2", String.valueOf(age));
		
		prop.setProperty("tel", "010-1111-2222");
		prop.setProperty("addr", "대전");
		
		//---------------------------------------------------
		
		String name = prop.getProperty("name");
		int tempAge = Integer.parseInt(prop.getProperty("age"));
		int tempAge2 = Integer.parseInt(prop.getProperty("age2"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + tempAge);
		System.out.println("나이2 : " + tempAge2);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
	}

}
