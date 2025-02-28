package Ch4;

public class LinkedQueue<E> implements Stack<E>{
	 // SinglyLinkedList클래스를이용하여큐구현
	SinglyLinkedList<E> list = new SinglyLinkedList<>();
	 public int size(){
		return list.size;
	 }
	 
	 public boolean isEmpty(){
		 return list.isEmpty();
	 }
	 
	 public boolean isFull(){
		 return false; // Memory full은 일어나지 않는다는 가정
	 }
	 
	 public void enqueue(E e){
		 list.addLast(e); // enqueue는 addLast로 구현
		}
		 public E first(){
			 return list.get(0);
		 }
		 
		 public E dequeue(){
			 if (isEmpty())
				 return null;
			 E element = first();
			 list.remove(0); // dequeue는 remove(0) 또는 deleteFirst로 구현
			 return element;
		 }

		@Override
		public void push(E e) {
			 list.addFirst(e); // push는addFirst로구현
			
		}

		@Override
		public E top() {
			 if (isEmpty())
				 return null;
			 return list.get(0); // top은get(0)
		}

		@Override
		public E pop() {
			if (isEmpty())
				 return null;
			E item = top();
			list.remove(0); // pop은remove(0) 또는deleteFirst로구현
			return item;
		}
	}
