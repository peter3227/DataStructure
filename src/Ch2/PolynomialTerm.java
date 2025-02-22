package Ch2;

public class PolynomialTerm {	// 한 항을 표현
	public double coef;	//	항의 계수 
	public int exp;	//	항의 지수
	
	public PolynomialTerm(double coef, int exp) {// 생성자
		this.coef = coef;
		this.exp = exp;
	}
	
	public String toString() {
		return "(" + "coef=" +	coef + ", exp=" + exp + ')';
	}
}