package Ch4;

public class LinkedStack<E> implements Stack<E> {
	// SinglyLinkedList 클래스를 이용하여 스택 구현
	SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	public int size() {
		return list.size;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean isFull() {
		return false;	// Memory full은 일어나지 않는다는 가정
	}
	
	public void push(E e) {
		list.addFirst(e); 	// push는 addFirst로 구현
	}
	
	public E top() {
		if(isEmpty())
			return null;
		return list.get(0);	// top은 get(0)
	}
	
	public E pop() {
		if(isEmpty())
			return null;
		E item = top();
		list.remove(0);		// pop은 remove(0) 또는 deleteFirst로 구현
		return item;
	}	}
