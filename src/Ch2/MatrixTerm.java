package Ch2;

public class MatrixTerm {	// 0이 아닌 항 하나를 저장
	int row, col, value;	// 그 항의 행/열/값을 저장 
	
	public MatrixTerm(int row, int col, int value) { // 생성자
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	public String toString() {
		return "(row=" + row + ",col=" + col + ",value=" + value + ')';
	}
}
