package Ch2;

import java.util.ArrayList;

public class SparseMatrix {	// 희소 행렬을 나타내는 클래스
	int mRows, mCols, mTerms;	// 행의 수, 열의 수, 0이 아닌 원소 수를 저장
	ArrayList<MatrixTerm> smArray;	// 0이 아닌 항의 ArrayList를 저장
	
	public SparseMatrix(int row, int col) {	// 	생성자
		mRows = row;
		mCols = col;
		mTerms = 0;
	}
	
	public String toString() { return "희소 행렬: " + smArray; }
	
	public SparseMatrix transpose() {
		SparseMatrix B = new SparseMatrix(mCols, mRows);
		if(mTerms > 0) {
			for(int c = 0; c < mCols; c++) // A의 열 순서로 전치 연산 실행(c: 현재 열)
				for(int i = 0; i < mTerms; i++) // A에서 c 열을 찾자. (A는 행 순서로 저장)
				if(smArray.get(i).col == c)
					B.addTerm(c, smArray.get(i).row, smArray.get(i).value);
		}
		return B;
	}
	
	public void addTerm(int row, int col, int value) {	//행렬의 마지막에 새로운 항 추가
		if(row < 0 || row >= mRows || col < 0 || col >= mCols)
			return;
		smArray.add(new MatrixTerm(row, col, value));
		mTerms++;
	}
	
	public SparseMatrix fastTranspose() {
		SparseMatrix B = new SparseMatrix(mCols, mRows);	//	전치 행렬
		int[] rowTerms = new int[mCols];	// 배열로 구현
		int[] startingPos = new int[mCols]; // 배열로 구현
		int i,j;
		
		if(mTerms > 0) {
			for(i = 0; i < mTerms; i++) {
				rowTerms[smArray.get(i).col]++;	// 각 열의 개수 파악
				B.addTerm(0, 0, 0);	// ArrayList에 항 추가 
			}
			startingPos[0] = 0;	// row_terms를 이용하여 시작 위치 계산
			for(i = 1; i < mCols; i++)
				startingPos[i] = startingPos[i-1] + rowTerms[i-1];
			for(i = 0; i < mTerms; i++) { // 전체 항에 대해 한번만 실행
				j = startingPos[smArray.get(i).col]++;	// 행의 열이 들어갈 곳
				MatrixTerm term = B.smArray.get(j);	// 그 곳에 i항을 복사 
				term.col = smArray.get(i).row;
				term.row = smArray.get(i).col;
				term.value = smArray.get(i).value;
			}
		}
		return B;
	}
	
	public SparseMatrix multiply(SparseMatrix B) {
		if(mCols != B.mRows) {	// A의 열의 수와 B의 행의 수는 동일
			System.out.println("곱할 수 없는 행렬입니다.");
			return null;
		}
		SparseMatrix D = new SparseMatrix(mRows, B.mCols);
		SparseMatrix newB = B.fastTranspose();
		
		smArray.add(new MatrixTerm(mRows, -1, -1));	// 끝을 표시하는 데이터 추가
		newB.smArray.add(new MatrixTerm(B.mCols, -1, -1));
		int row = smArray.get(0).row;	// row: A의 현재 행
		int sum = 0, rowBegin = 0,i,j;
		
		for(i = 0; i < mTerms;) {
			int column = newB.smArray.get(0).row;	//B의 현재 열
			for(j = 0; j <= newB.mTerms; ) {	//A의 현재 행과 B의 현재 열을 곱하기
				if(smArray.get(i).row != row) {	//A의 현재 행을 벗어남
					if(sum != 0) { D.addTerm(rowBegin,  column,  sum); sum = 0;}
					i = rowBegin;	//A는 원 위치 
					for(;newB.smArray.get(j).row == column; j++);
					column = newB.smArray.get(j).row;	//B는 다음 열 
				}
				else if (newB.smArray.get(j).row != column) {	//B의 현재 열을 벗어남 
					if(sum != 0) {D.addTerm(rowBegin,  column,  sum); sum = 0;}
					i = rowBegin;	// A는 원 위치 
					column = newB.smArray.get(i).row;	// B는 다음 열
				}
				else {
					switch(Integer.compare(smArray.get(i).col, newB.smArray.get(j).col)) {
						case -1: i++; break;	//A[i].col < newB[j].col -> A 증가 
						case 1: j++; break;		//A[i].col > newB[j].col -> B 증가
						case 0: sum += smArray.get(i).value * newB.smArray.get(j).value;
							i++; j++;	// 계산 후, A와 B를 모두 진행
					}
				}
			}
			for (; smArray.get(i).row == row; i++); //B의 모든 원소를 처리한 후,
			row = smArray.get(i).row;	//A의 현재 행을 다음 행으로.
			rowBegin = i;
		}	// end of for statement
		smArray.remove(smArray.size()-1); // 끝을 표시하는 데이터 삭제
		return D;
	}
}
