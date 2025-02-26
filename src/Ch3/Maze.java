package Ch3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

public class Maze {
	int[][] maze;	//미로를 저장한 배열
	Offset[] move = new Offset[8];	//8개의 이동 방향을 저장한 배열
	int mRows, mCols;
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		System.out.print("미로를 저장한 파일 이름?");
		String fname = sc.nextLine();
		Maze algorithm = new Maze(fname);	//파일 이름을 생성자에게 전달
		algorithm.path(11, 15);	//미로의 출력 좌표를 인자로 전달
		sc.close();
	}
	
	public Maze(String fname) throws FileNotFoundException{	//생성자 함수
		 int i, j;
		 Scanner sc = new Scanner(new File(fname));
		 mRows = sc.nextInt();	mCols = sc.nextInt(); 	//행과 열을 파일에서 입력
		 maze = new int[mRows+2][mCols+2];
		 
		 // Boundary 영역을 1로 설정
		 for(i = 0; i < mCols + 2; i++)	maze[0][i] = maze[mRows+1][i] = 1;
		 for(j = 0; j < mRows + 2; j++)	maze[j][0] = maze[j][mCols+1] = 1;
		 
		 //파일로부터 미로 정보 입력
		 for(i = 1; i <= mRows; i++)
			 for(j = 1; j <= mCols; j++)	maze[i][j] = sc.nextInt();
		 
		 //move 배열 초기화
		 move[0] = new Offset(-1, 0);	move[1] = new Offset(-1, 1);	move[2] = new Offset(0, 1);
		 move[3] = new Offset(1, 1);	move[4] = new Offset(1, 0);	move[5] = new Offset(1, -1);
		 move[6] = new Offset(0, -1);	move[7] = new Offset(-1, -1);
		 sc.close();
	}
	
	public void path(int exitRow, int exitCol) {	//종점의 좌표를 입력
		int[][] mark = new int[mRows+2][mCols+2];	//이미 가본 길을 표시하는 배열
		boolean found = false;
		int row = 0, col = 0, dir;
		
		ArrayStack<Items> stack = new ArrayStack<Items>(mRows * mCols);
		mark[1][1] = 1;
		stack.push(new Items(1, 1, 2)); 	//시작점의 좌표를 스택에 추가. 방향은  E
		while(!stack.isEmpty() && !found){
			Items position = stack.pop();
			row = position.x;	col = position.y;	dir = position.dir;
			while(dir < 8 && !found) {
				int nextRow = row + move[dir].x;	int nextCol = col + move[dir].y;
				if(nextRow == exitRow && nextCol == exitCol)	found = true;	//경로를 발견
				else if(maze[nextRow][nextCol] == 0 && mark[nextRow][nextCol] == 0) {
					mark[nextRow][nextCol] = 1;	//아직 안 가본 길
					stack.push(new Items(row, col, ++dir));	//현재 좌표를 stack에 저장

				row = nextRow; col = nextCol; dir = 0;	//안 가본 길로 전진. 방향은 북쪽
				}
				else ++dir;
			}
		}
		
		if(found) {	//stack에 저장된 경로 출력
			System.out.println("This path is:");
			System.out.printf("%5s %5s\n", "row", "col");
			dumpStack(stack);
			System.out.printf("%5d %5d\n", row, col);
			System.out.printf("%5d %5d\n", exitRow, exitCol);	
		}
		else
			System.out.printf(" The maze does not have a path\n");
	}
	
	private void dumpStack(ArrayStack<Items>stack) {
		if(stack.isEmpty())
			return;
		Items position = stack.pop();
		dumpStack(stack);
		System.out.printf("%5d %5d\n", position.x, position.y);
	}
}
