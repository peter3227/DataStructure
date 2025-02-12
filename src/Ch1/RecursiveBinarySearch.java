package Ch1;

public class RecursiveBinarySearch {
	public int RecursiveBinarySearch(int[] A, int key) {
		return bsearch(A, key, 0, A.length - 1);
	}
	
	private int bsearch(int[] A, int key, int left, int right) {
		if(left <= right) {
			int middle = (left + right) / 2;
			switch (compare(A[middle], key)) {
			case -1 : return bsearch(A, key, middle + 1, right);
			case 1 : return bsearch(A, key, left, middle - 1);
			case 0 : return middle;	//key == A[middle]
			}
		}
		return -1; //not found
	}
	
	private int compare(int x, int y) {
		return (x < y) ? -1 : (x > y) ? 1 : 0;
	}

}
