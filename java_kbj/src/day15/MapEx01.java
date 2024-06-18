package day15;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapEx01 {

	public static void main(String[] args) {
		/* Map 기본 예제 - key, value 2개 값을 이용, key 는 중복X, value 는 중복O */
		HashMap<String, String> map  = new HashMap<String, String>();
		
		/* put(key, value) : key 와 value 를 추가, 이미 key 가 등록되어 있으면 새 value 를 저장하고 기존 value 반환 */
		map.put("admin", "admin123");
		System.out.println(map.put("admin", "admin12345"));
		System.out.println(map);
		
		/* remove(key) : 주어진 key 와 일치하는 key 가 있으면 삭제하고 value 반환 */
		map.remove("admin");
		System.out.println(map);
		
		/* containsKey(key) : 주어진 key 와 일치하는 key 가 있는지 없는지 반환 */
		System.out.println(map.containsKey("admin"));
		System.out.println(map.containsKey("asdasd"));
		map.put("abc123", "def123");
		map.put("asdasd", "def123");
		System.out.println(map);
		
		/* Map 을 이용한 반복문 예제 */
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("2024160001", "고길동");
		map1.put("2024160002", "홍길동");
		map1.put("2024135001", "임꺽정");
		
		// 방법1. keySet 이용
		Set<String> set1 = map1.keySet();
		Iterator<String> it1 = set1.iterator();
		while(it1.hasNext()) {
			String key = it1.next();
			String value = map1.get(key);
			System.out.println(key+" : "+value);
		}
		
		// 방법2. EntrySet 이용, Entry 클래스를 이용하여 set 으로 만들고, 반복문을 활용
		Set<Map.Entry<String, String>> entrySet = map1.entrySet();
		Iterator<Map.Entry<String, String>> it2 = entrySet.iterator();
		while(it2.hasNext()) {
			Map.Entry<String, String> tmp = (Map.Entry<String, String>)it2.next();
			String key = tmp.getKey();
			String value = tmp.getValue();
			System.out.println(key+" : "+value);
		}
		
		/* 인터페이스 다형성과 Map 의 활용 */
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "안녕하세요");
		map2.put("age", 21);
	}
}