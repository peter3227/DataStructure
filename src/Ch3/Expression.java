package Ch3;

import java.util.Scanner;

public class Expression {
	enum Op { lparen, rparen, plus, minus, times, divide, mod, eos, operand }
	private int[] isp = { 0, 19, 12, 12, 13, 13, 13, 0 };
	 private int[] icp = { 20, 19, 12, 12, 13, 13, 13, 0 };
	 /* isp and icp arrays - index is value of precedence
	 lparen, rparen, plus, minus, times, divide, mod, eos */
	Stack<Integer> stack = new ArrayStack<>();  // global stack
	String e;	// input string
	
	public int eval(String e) {	// 문자열 e에 저장된 postfix 수식 계산
		Stack<Integer> stack = new ArrayStack<>();
		for(int i = 0; i < e.length(); i++) {
			Op token = nextToken(e.charAt(i));
			if (token == Op.operand)	stack.push(e.charAt(i) - '0');	// 피연산자를 만나면 스택에 저장
			else {	// 연산자
				int op2 = stack.pop(); 	// stack에서 피연산자 2개를 제거한 후 이를 이용하여
				int op1 = stack.pop();	//수식을 제거한 후 결과를 다시 stack에 저장
				switch(token) {
				case plus : stack.push(op1 + op2); break;
				case minus : stack.push(op1 - op2); break;
				case times : stack.push(op1 * op2); break;
				case divide : stack.push(op1 / op2); break;
				case mod : stack.push(op1 % op2);
		}}}
		return stack.pop();  // return result
	}
	
	private Op nextToken(char ch) {	// 수식 문자의 enumeration 값을 return
		switch(ch) {
		case '(' : return Op.lparen;
		case ')' : return Op.rparen;
		case '+' : return Op.plus;
		case '-' : return Op.minus;
		case '*' : return Op.times;
		case '/' : return Op.divide;
		case '%' : return Op.mod;
		case ' ' : return Op.eos;
		default : return Op.operand;	// 수식에서 피연산자는 한 문자로 구성된다고 가정.
		}
	}
	
	public String postfix(String e) {
		Op token;
		StringBuilder sb = new StringBuilder();	// postfix의 문자를 저장하는 용도
		Stack<Op> stack = new ArrayStack<>();
		stack.push(Op.eos);
		for(int i = 0; i < e.length(); i++) {
			token = nextToken(e.charAt(i));
			if (token == Op.operand)	sb.append(e.charAt(i)); 	// 피연산자는 바로 출력
			else if (token == Op.rparen) {		// 오른쪽 괄호면
				while(stack.top() != Op.lparen)	// 왼쪽 괄호를 만날 때까지
					sb.append(printToken(stack.pop())); 	// 스택에서 계속 제거하여 출력
				stack.pop(); 	// 왼쪽 괄호 제거
			} else {
				while(isp[stack.top().ordinal()] >= icp[token.ordinal()])	// isp가 큰 연산자를 제거
					sb.append(printToken(stack.pop())); 	// 한 후, 출력
				stack.push(token); 		// 입력 연산자를 stack에 추가
			}	}
		while(stack.top() != Op.eos)	sb.append(printToken(stack.pop()));	//stack을 비우기
		return sb.toString(); }
	
	private char printToken(Op token) {
		switch (token) {
		case plus : return '+';
		case minus : return '-';
		case times : return '*';
		case divide : return '/';
		case mod : return '%';
		default : return ' ';
		}
	}
	public static void main(String[] args) {
		 Expression algorithm = new Expression();
		 Scanner sc= new Scanner(System.in);
		 System.out.print("Infix notation? ");
		 String infix = sc.nextLine();
		 String postfix = algorithm.postfix(infix);
		 System.out.println("Postfix notation = " + postfix);
		 System.out.println("실행결과= " + algorithm.eval(postfix));
		 sc.close();
		 }
}
