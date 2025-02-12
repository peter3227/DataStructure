package Ch7;

public class InsertionSort extends AbstractSort {
	
	public static void sort(Comparable[] a) {
		// n개의데이터를저장하는a[] 배열에서삽입정렬수행
		int i, j;
		for (i = 1; i < a.length; i++) {	// 0부터 i-1까지는 정렬된 상태
			Comparable next = a[i];			// i번째 데이터가 들어갈 위치 파악
			for (j = i - 1; j >= 0 && less(next, a[j]); j--)
				a[j+1] = a[j];				// 큰 데이터들은 아래로 이동
			a[j+1] = next;
		}
	}

}
