package Ch1;

import java.util.Scanner;

public class MagicSquare {
    public void magic(int n) {
        int[][] square;

        if (n % 2 == 0) { // square의 크기는 홀수여야 함
            System.out.println("오류 발생: 홀수를 입력하세요.");
            return;
        }

        square = new int[n][n]; // 배열은 기본적으로 0으로 초기화
        square[0][n / 2] = 1; // 첫 행의 중간부터 시작
        int x = 0, y = n / 2;

        for (int key = 2; key <= n * n; key++) {
            int i = (x - 1 < 0) ? n - 1 : x - 1; // 위쪽 행
            int j = (y - 1 < 0) ? n - 1 : y - 1; // 왼쪽 열
            if (square[i][j] > 0)
                x = (x + 1) % n; // 못 갈 경우 아래로 이동
            else { // 갈 수 있을 경우, 대각선 위로 이동
                x = i;
                y = j;
            }
            square[x][y] = key; // 변경된 위치에 값 추가
        }

        System.out.printf("크기가 %d인 마방진:\n", n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) // `i++` 대신 `j++`가 되어야 함 (오류 수정)
                System.out.printf(" %3d", square[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("마방진의 크기를 입력하세요 (홀수만 가능): ");
        int n = scanner.nextInt();

        MagicSquare ms = new MagicSquare();
        ms.magic(n);

        scanner.close();
    }
}

