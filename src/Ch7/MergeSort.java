package Ch7;


import java.util.Arrays;

public class MergeSort extends AbstractSort {
	private static void merge(Comparable[] list, Comparable[] sorted, int lo, int mid, int hi) {
	// list[lo],..., mid과 list[mid+1],...,list[hi]는 각각 정렬된 리스트 
	// 두 개의 리스트를 병합하여 정렬된 sorted[lo],...,sorted[hi]를 생성
		
		int i = lo, j = mid + 1;	//i: 첫 번째 리스트 색인, j: 두 번째 리스트 색인
		for(int k = lo; k <= hi; k++) { //k: sorted[]의 색인
			if(i > mid)
				sorted[k] = list[j++];	//두 번째 리스트의 나머지를 sorted[]에 복사 
			else if(j > hi)
				sorted[k] = list[i++];	//첫 번째 리스트의 나머지를 sorted[]에 복사 
			else if(less(list[j], list[i]))
				sorted[k] = list[j++];
			else
				sorted[k] = list[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] src = a;
		Comparable[] dst = new Comparable[a.length], tmp;
		int N = a.length;
		
		for(int n = 1; n < N; n *= 2) {	// n:병합할 부분 배열의 크기 
			for(int i = 0; i < N; i += 2*n) // i: 병합할 부분 배열의 위치 
				merge(src, dst, i, i+n-1, Math.min(i+2*n-1, N-1)); // (4,4,3)이 남았을 때?
			tmp = src; src = dst; dst =tmp;
		}
		if (src != a)	System.arraycopy(src,  0,  a,  0, N);
	}
	
	 public static void main(String[] args) {
	        Integer[] array = {38, 27, 43, 3, 9, 82, 10};
	        System.out.println("Before sorting: " + Arrays.toString(array));
	        
	        MergeSort.sort(array);
	        
	        System.out.println("After sorting: " + Arrays.toString(array));
	    }
}
