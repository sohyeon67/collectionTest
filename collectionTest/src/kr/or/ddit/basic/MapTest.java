package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
	/*
	Map ==> key값과 value값을 한 쌍으로 관리하는 객체
		- key값은 중복을 허용하지 않고 순서(index)가 없다. (Set의 특징을 갖는다.)
		- value값은 중복을 허용한다.
	 */
		HashMap<String, String> map = new HashMap<String, String>();
		
		// 자료 추가 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map에 저장된 데이터의 개수 : " + map.size());
		System.out.println("map => " + map);
		
		// 자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 추가한 값으로 덮어쓴다.
		map.put("addr", "서울");
		System.out.println("map => " + map);

		// 자료 삭제 ==> remove(key값) : key값이 같은 자료를 찾아서 삭제한다.
		//			반환값 => 삭제된 자료의 value값
//		String removeTel = map.remove("tel");
//		System.out.println("map => " + map);
//		System.out.println("삭제된 값 => " + removeTel);
		
		// 자료 읽기 ==> get(key값) : key값과 같이 저장된 value값을 반환한다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println();
		
		// key값의 존재 여부 알아내기 => containsKey(key값)
		// 			=> 해당 key값이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		System.out.println();
		
		// Map에 저장된 모든 데이터를 차례로 가져와 처리하기
		
		// 1. keySet()메서드 이용하기
		//	  keySet()메서드 ==> Map의 모든 key값들을 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("------------------------------------------");
		
		// 2. keySet을 향상된 for문으로 처리하기
//		for(String key : keySet) {
		for(String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " => " + value);
		}
		System.out.println("------------------------------------------");
		
		// 3. value값만 읽어와 처리하기 ==> values()메서드 이용
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("------------------------------------------");

	}

}
