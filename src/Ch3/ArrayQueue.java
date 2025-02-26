package Ch3;

public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int front = -1;	//FRONT와 REAR를 0으로 초기화?
	private int rear = -1;
	
	public ArrayQueue() {this(CAPACITY);}
	
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public int size() {return rear - front;}
	
	public boolean isEmpty() {return size() == 0;}
	
	public boolean isFull() {return rear == data.length - 1;}
	
	public void enqueue(E e) throws IllegalStateException{
		if(isFull()) throw new IllegalStateException("Queue full");
		data[++rear] = e;
	}
	
	public E first() {
		if(isEmpty())	return null;
		return data[front+1];
	}
	
	public E dequeue() throws IllegalStateException{
		if(isEmpty())	return null;
		E item = data[front+1];
		data[++front] = null;
		return item;
	}
	
	public static void main(String[] args) {
		Queue<Integer>  Q = new ArrayQueue<>(10);
		Q.enqueue(5);
		Q.enqueue(3);
		System.out.println(Q.size());
		System.out.println(Q.dequeue());
		System.out.println(Q.isEmpty());
		System.out.println(Q.dequeue());
		System.out.println(Q.isEmpty());
		System.out.println(Q.dequeue());
		Q.enqueue(1);
		Q.enqueue(2);
		Q.enqueue(3);
		System.out.println(Q.first());
		Q.enqueue(4);
		System.out.println(Q.size());
		System.out.println(Q.dequeue());
	}
}
