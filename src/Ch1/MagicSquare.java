package Ch1;

public class MagicSquare {
	public void magic(int n) {
		int[][] square;
		
		if(n % 2 == 0) {	//square의 크기는 홀수일 것!
			System.out.println("오류 발생: 홀수를 ");
			return;
		}
		square = new int[n][n];	//Java에서 배열은 0으로 초기화 
		square[0][n/2] = 1;	//첫 행의 중간부터 시작
		int x = 0, y = n/2;
		
		for(int key = 2; key <= n*n; key++) {
			int i = (x - 1 < 0)? n - 1 : x - 1;	// 위쪽 행
			int j = (y - 1 < 0)? n - 1 : y - 1;	// 왼쪽 행 
			if(square[i][j] > 0)
				x = (x + 1) % n;	//못 갈 경우, 아래로
			else {	//갈 수 있을 경우, i와 j를 대각선 위로.
				x = i;
				y = j;
			}
			square[x][y] = key;	//변경된 위치에 다음 수 추가
		}
		
		System.out.printf("크기가 %d인 마방진: \n", n);	//생성된 magic square를 출력
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; i++)
				System.out.printf(" %3d", square[i][j]);
			System.out.println();	//한 줄에 한 행씩 출력 
		}
	}

}
