package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class SetEx01 {

	public static void main(String[] args) {
		/* Set 기본 예제 - 순서 X, 중복 X */
		HashSet<Integer> set = new HashSet<Integer>();
		
		/* add(객체) : 객체를 추가, 중복된 경우 false 리턴 */
		System.out.println(set.add(1));
		System.out.println(set.add(1));
		System.out.println(set.add(100));
		System.out.println(set.add(1000));
		
		/* remove(객체) : 객체를 제거, 없으면 false 리턴 */
		System.out.println(set.remove(20));
		System.out.println(set.remove(100));
		
		/* contains(객체) : 객체가 있으면 true, 없으면 false */
		System.out.println(set.contains(11));
		System.out.println(set.contains(1));
		
		/* size() : 저장된 개수 */
		System.out.println(set.size());
		System.out.println(set);
		
		/* addAll(컬렉션) : 컬렉션에 있는 원소들을 추가, 컬렉션 인터페이스 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(set);
		list.add(8);
		list.add(9);
		list.add(10);
		System.out.println(list);
		
		/* Collections.shuffle(리스트) : 리스트를 섞어 줌 */
		Collections.shuffle(list);
		System.out.println(list);
		
		/* 향상된 for 문, Iterator */
		HashSet<Integer> set1 = new HashSet<Integer>();
		set1.add(1);
		set1.add(5);
		set1.add(9);
		set1.add(2);
		set1.add(3);
		for(Integer tmp : set1) {
			System.out.print(tmp+" ");
		}
		System.out.println();
		Iterator<Integer> it = set1.iterator();
		while(it.hasNext()) {
			Integer num = it.next();
			System.out.print(num+" ");
		}
		System.out.println();
		
		/* 1~10 사이의 랜덤한 수 6개를 중복되지 않게 생성해서 저장하고 출력하는 코드 */
		int min = 1, max = 10;
		HashSet<Integer> randomSet = new HashSet<Integer>();
		ArrayList<Integer> randomList = new ArrayList<Integer>();
		while(randomSet.size() < 6) {
			Integer random = (int)(Math.random() * (max - min + 1) + min);
			randomSet.add(random);
		}
		randomList.addAll(randomSet);
		Collections.shuffle(randomList);
		System.out.println(randomList);
		
		/* 1~45 사이의 중복되지 않은 6개의 번호와 1개의 보너스 번호를 랜덤으로 생성하고
		 * 사용자가 번호를 6개 입력해서 몇등인지 맞추는 코드 
		 * 1등 : 번호 6개, 2등 : 번호 5개와 보너스 번호, 3등 : 번호 5개, 4등 : 번호 4개, 5등 : 번호 3개, 나머지 꽝 */
		System.out.println("-------------------------------------------------");
		min = 1; max = 45;
		HashSet<Integer> lottoSet = new HashSet<Integer>();
		ArrayList<Integer> lottoList = new ArrayList<Integer>();
		while(lottoSet.size() < 7) {
			Integer random = (int)(Math.random() * (max - min + 1) + min);
			lottoSet.add(random);
		}
		lottoList.addAll(lottoSet);
		Integer bonus = lottoList.remove(6);
		System.out.println("로또 번호 : "+lottoList+", 보너스 번호 : "+bonus);
		
		Scanner scan = new Scanner(System.in);
		HashSet<Integer> mySet = new HashSet<Integer>();
		ArrayList<Integer> myList = new ArrayList<Integer>();
		System.out.print("1~45 사이의 번호 6개 입력 : ");
		while(mySet.size() < 6) {
			mySet.add(scan.nextInt());
		}
		myList.addAll(mySet);
		System.out.println("내 번호 : "+myList);
		
		int lottoCount = 0, bonusCount = 0;
		for(int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if(lottoList.get(i) == myList.get(j)) {
					lottoCount++;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			if(myList.get(i) == bonus) {
				bonusCount++;
			}
		}
		if(lottoCount == 6) {
			System.out.println("1등입니다.");
		}
		else if(lottoCount == 5 && bonusCount == 1) {
			System.out.println("2등입니다.");
		}
		else if(lottoCount == 5) {
			System.out.println("3등입니다.");
		}
		else if(lottoCount == 4) {
			System.out.println("4등입니다.");
		}
		else if(lottoCount == 3) {
			System.out.println("5등입니다.");
		}
		else {
			System.out.println("꽝입니다.");
		}
	}
}