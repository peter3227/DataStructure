package Ch1;

public class Permutation {
	public void perm(char[] A, int k) {
		if(k == A.length - 1) {	// 단 하나의 순열만 존재. 그냥 출력하자...
			for (int i = 0; i < A.length; i++)
				System.out.print(A[i] + " ");
			System.out.println();
		}
		else {	// 하나 이상의 순열 존재, 재귀적으로 출력 
			for (int i = k; i < A.length; i++) {
				swap(A, i, k);
				perm(A, k+1);
				swap(A, i, k);
			}
		}
	}
private void swap(char[] A, int i, int k) { char temp = A[k]; A[k] = A[i]; A[i] = temp; }

public static void main(String[] args) {
	Permutation driver = new Permutation();
	char[] A = {'a', 'b', 'c', 'd'};
	driver.perm(A, 0);
}
}
