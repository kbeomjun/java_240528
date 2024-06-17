package day14;

public class GenericEx01 {

	public static void main(String[] args) {
		/* 제네릭 클래스 
		 * T extends 클래스A - T로 들어올 수 있는 클래스의 종류를 클래스A와 클래스A의 자식클래스들만 올 수 있게 제한, 클래스A의 메서드를 사용가능
		 * T super 클래스A - 클래스A와 클래스A의 조상클래스들만 올 수 있음 */
		Array<String> list1 = new Array<>(10);
		list1.set(0, "안녕하세요");
		list1.set(1, "사과");
		list1.print();
		
		Array<Integer> list2 = new Array<>(10);
		list2.set(0, 1);
		list2.set(1, 10);
		list2.print();
		
		String[] list3 = new String[10];
		list3[0] = "안녕";
		list3[1] = "귤";
		printArray(list3);
		
		Integer[] list4 = new Integer[10];
		list4[0] = 100;
		list4[1] = 1000;
		printArray(list4);
	}
	/* 제너릭 메서드 */
	public static <T> void printArray(T[] array) {
		for(T element : array) {
			if(element != null) {
				System.out.println(element);
			}
		}
	}
}

class Array <T>{
	private T[] list;
	
	@SuppressWarnings("unchecked")
	public Array(int size) {
		list = (T[]) new Object[size];
	}
	
	public void set(int index, T data) {
		if(index >= list.length) {
			return;
		}
		list[index] = data;
	}
	
	public T get(int index) {
		if(index >= list.length || index < 0) {
			return null;
		}
		return list[index];
	}
	
	public void setList(T[] list) {
		this.list = list;
	}
	
	public T[] getList() {
		return list;
	}
	
	public void print() {
		for(int i = 0; i < list.length; i++) {
			if(list[i] != null) {
				System.out.println(list[i]);
			}
		}
	}
}

class Test<A, B>{
	private A data1;
	private B data2;
}