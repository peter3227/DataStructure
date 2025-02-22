package Ch2;

import java.util.ArrayList;

public class Polynomial {
	private final ArrayList<PolynomialTerm> termArray;
	
	public Polynomial() {
		termArray = new ArrayList<>();	// 초기에는 항이 없는 리스트
	}
	
	public void appendTerm(double coef, int exp) {
		termArray.add(new PolynomialTerm(coef, exp));	// 새로운 항을 마지막에 추가
	}
	
	@Override
	public String toString() {
		return "다항식:" + termArray;
	}
	
	public Polynomial add(Polynomial B) {
		Polynomial D = new Polynomial();
		
		int posA = 0, posB = 0;
		double sum;
		while (posA < termArray.size() && posB < B.termArray.size()) {
			PolynomialTerm termA = termArray.get(posA);
			PolynomialTerm termB = B.termArray.get(posB);
			switch(Integer.compare(termA.exp, termB.exp)) {
				case 1: D.appendTerm(termA.coef, termA.exp);	// A.exp > B.exp
					posA++;	// A만 증가
					break;
				case -1: D.appendTerm(termB.coef, termB.exp);	// B.exp > A.exp
					posB++;	// B만 증가
					break;
				case 0: sum = termA.coef + termB.coef;	//A.exp == B.exp
					if(sum != 0.0)	//계수의 합이 0이 아니면
						D.appendTerm(sum, termA.exp); //합을 계수로 하는 항을 추가
					posA++; posB++;	//A와 B를 모두 증가 
			}
		}
		// A의 나머지 항들을 d에 모두 추가. 항이 없을 경우?
		for(; posA < termArray.size(); posA++)
			D.appendTerm(termArray.get(posA).coef, termArray.get(posA).exp);
		
		// B의 나머지 항들을 d에 모두 추가.
		for(; posB < B.termArray.size(); posB++)
			D.appendTerm(B.termArray.get(posB).coef, B.termArray.get(posB).exp);
		
		return D;
	}
	
	public static void main(String[] args) {
		Polynomial A = new Polynomial();
		Polynomial B = new Polynomial();
		
		A.appendTerm(3, 14);
		A.appendTerm(2, 8);
		A.appendTerm(5, 1);
		A.appendTerm(1, 0);
		B.appendTerm(8, 14);
		B.appendTerm(-3, 10);
		B.appendTerm(10, 6);
		B.appendTerm(-5, 1);
		System.out.println("A " + A);
		System.out.println("B " + B);
		System.out.println("A + B " + A.add(B));
	}
}
