package Ch1;

public class BinarySearch {
	
	private int compare(int x, int y) {
		return (x < y) ? -1 : (x > y) ? 1 : 0;
	}
	
	public int bsearch(int[] A, int key) {
		for (int left = 0, right = A.length -1; left <= right;) {
			int middle = (left + right) / 2;
			switch (compare(A[middle], key)) {
			case -1: left = middle + 1; break;
			case 1: right = middle - 1; break;
			case 0: return middle; 
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] array = {1, 4, 8, 11, 13, 16, 23};
        int key = 8;
        int result = binarySearch.bsearch(array, key);
        System.out.println("Index: "+ result);

    }
}  