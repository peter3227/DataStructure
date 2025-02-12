package Ch7;

public class QuickSort extends AbstractSort {

    // Partition 메서드
    private static int partition(Comparable[] a, int left, int right) {
        int i = left, j = right + 1;
        Comparable pivot = a[left]; // a[left]를 기준 값(pivot key)으로 선정
        while (true) {
            while (less(a[++i], pivot)) { // pivot보다 큰 데이터를 만나면 stop
                if (i == right) break;
            }
            while (less(pivot, a[--j])) { // pivot보다 작은 데이터를 만나면 stop
                if (j == left) break;
            }
            if (i >= j) break; // left..right의 모든 데이터 scan 완료
            swap(a, i, j); // 두 개의 데이터를 교환
        }
        swap(a, left, j); // pivot을 두 리스트의 경계점으로 이동
        return j;
    }

    // 두 요소를 교환하는 메서드
    private static void swap(Comparable[] a, int i, int k) {
        Comparable temp = a[k];
        a[k] = a[i];
        a[i] = temp;
    }

    // 정렬 메서드
    private static void sort(Comparable[] a, int left, int right) {
        if (left >= right)
            return;
        int j = partition(a, left, right);
        sort(a, left, j - 1);    // 왼쪽 리스트를 다시 정렬
        sort(a, j + 1, right);   // 오른쪽 리스트를 다시 정렬
    }

    // public 정렬 메서드
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    // 테스트용 메인 메서드
    public static void main(String[] args) {
        // 정수 배열 테스트
        Integer[] intArray = {12, 4, 9, 5, 8, 3, 7};
        System.out.println("정렬 전: ");
        printArray(intArray);
        QuickSort.sort(intArray);
        System.out.println("정렬 후: ");
        printArray(intArray);

        // 문자열 배열 테스트
        String[] strArray = {"사과", "오렌지", "바나나", "배", "포도"};
        System.out.println("\n정렬 전: ");
        printArray(strArray);
        QuickSort.sort(strArray);
        System.out.println("정렬 후: ");
        printArray(strArray);

        // 이미 정렬된 배열 테스트
        Integer[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("\n정렬 전 (이미 정렬된 배열): ");
        printArray(sortedArray);
        QuickSort.sort(sortedArray);
        System.out.println("정렬 후 (변화 없어야 함): ");
        printArray(sortedArray);

        // 역순으로 정렬된 배열 테스트
        Integer[] reverseSortedArray = {7, 6, 5, 4, 3, 2, 1};
        System.out.println("\n정렬 전 (역순으로 정렬된 배열): ");
        printArray(reverseSortedArray);
        QuickSort.sort(reverseSortedArray);
        System.out.println("정렬 후: ");
        printArray(reverseSortedArray);
    }

    // 배열을 출력하는 헬퍼 메서드
    private static void printArray(Comparable[] array) {
        for (Comparable elem : array) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
