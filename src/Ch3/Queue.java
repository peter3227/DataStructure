package Ch3;

public interface Queue<E> {
	int size();
	boolean isEmpty();
	boolean isFull();
	void enqueue(E e);	//큐의 rear에 원소 추가
	E first();	//front의 원소를 반환
	E dequeue();	//front의 원소를 삭제하고 반환
}
