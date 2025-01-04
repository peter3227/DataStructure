package Ch1;

public class SelectionSort {
	public void sort(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {  // list[i]부터 list[n-1]까지 정렬.
			int min = i;						  // 최소값이 i에 있다고 일단 가정
			for (int k = i + 1; k < A.length; k++)// i 위치 다음의 모든 놈에 대해
				if(A[k] < A[min])				  // 더 작은 것이 있으면
					min = k;					  // 최소값을 이 놈으로…
			int temp = A[i];					  // 최소값과 i의 내용을 교체
			A[i] = A[min];
			A[min] = temp;
		}
	}
	
	public static void main(String[] args) {
		SelectionSort driver = new SelectionSort();
		int[] input = {10, 4, 5, 2, 1, 8, 3, 6};
		driver.sort(input);
		for (int i = 0; i < input.length; i++)
			System.out.print(input[i] + " ");
	}
}
