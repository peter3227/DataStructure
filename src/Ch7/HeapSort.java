package Ch7;

import java.util.Arrays;

public class HeapSort extends AbstractSort {
	private static void adjust(Comparable[] a, int root, int n) {
		// root를 제외한 트리의 나머지 부분은 max heap으로 이미 구성
		// list[root]의 위치를 조정하여 max heap을 재구성
		Comparable rootkey = a[root];
		int child = root * 2 + 1;	// left child: root가 인덱스 0에 저장되었다고 가정
		while (child <= n) {
			if (child < n && less(a[child], a[child+1]))
				child += 1;	// 왼쪽 또는 오른쪽 중에서 큰 값을 child로 설정. 
			if (less(a[child], rootkey))	break;
			else {	// child가 크면 부모 자리로 이동
				int parent = (child - 1)/2;
				a[parent] = a[child];
				child = child*2 + 1;
			}
		}
		a[(child-1)/2] = rootkey;
	}
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		
		for(int i = N/2; i >= 0; i--)	// 초기 max heap을 구성
			adjust(a, i, N-1);	// n/2은 child를 갖는 첫번째 노드
		for(int i = N-1; i>0; i--) {
			swap(a, 0, i);	// root를 맨 뒤의 원소와 교환 
			adjust(a, 0, i-1);	// 원소를 하나 적게 가진 heap을 재구성 
		}
	}
	
//	void heapsort(int list[], int n)
//	{// list[1]부터 list[n]까지를 오름차순으로 정렬
//		int i, j;
//		int temp;
//		
//		for(i = n/2; i > 0; i--)	// 초기 max heap을 구성
//			adjust(list, i, n);		// n/2은 child를 갖는 첫번째 노드
//		for(i = n-1; i>0; i--) {
//			swap(list[1], list[i+1], temp);	//root를 맨 뒤의 원소와 교환
//			adjust(list, 1, i);	// 원소를 하나 적게 가진 heap을 재구성 
//		}
//		
//	}
	
	// 두 요소를 교환하는 메서드
    private static void swap(Comparable[] a, int i, int k) {
        Comparable temp = a[k];
        a[k] = a[i];
        a[i] = temp;
    }
    
	
	 public static void main(String[] args) {
	        Integer[] array = {38, 27, 43, 3, 9, 82, 10};
	        System.out.println("Before sorting: " + Arrays.toString(array));
	        
	        HeapSort.sort(array);
	        
	        System.out.println("After sorting: " + Arrays.toString(array));
	    }
}
