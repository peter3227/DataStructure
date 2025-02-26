package Ch3;

public interface Stack<E> {
	int size();	//스택의 원소 수
	boolean isEmpty();	//스택이 비었으면 true
	boolean isFull();	//스택이 꽉 찼으면 true
	void push(E e);	//스택 top에 e를 추가
	E top();	//스택 top의 원소를 반환
	E pop();	//top의 원소를 삭제하고 반환
}
