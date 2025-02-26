package Ch3;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int top = -1;	// top이 0로 초기화될 경우?
	
	public ArrayStack() {this(CAPACITY);}
	
	public ArrayStack(int capacity) {
		data = (E[])new Object[capacity];
	}
	
	public int size() {return top + 1;}
	
	public boolean isEmpty() {return top == -1;}
	
	public boolean isFull() {return size() == data.length;}
	
	public void push(E e) throws IllegalStateException{
		if(isFull()) throw new IllegalStateException("Stack full");
		data[++top] = e;
	}
	
	public E top() {
		if(isEmpty())	return null;
		return data[top];
	}
	
	public E pop() {
		if(isEmpty())	return null;
		E item = data[top];
		data[top--] = null;	//for garbage collection
		return item;
	}
	
	public static void main(String[] args) {
		Stack<Integer> S = new ArrayStack<>();
		
		S.push(5);
		S.push(3);
		System.out.println(S.size());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		S.push(7);
		S.push(9);
		System.out.println(S.top());
		S.push(4);
		System.out.println(S.size());
		System.out.println(S.pop());
		S.push(6);
		S.push(8);
		System.out.println(S.pop());
	}

}
