package main;

import java.util.Scanner;
import java.util.Stack;

public class stackCalculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		/** Accept infix expression **/
		System.out.println("Enter expression");
		String infix = scan.next();

		String postfix = postfix(infix);
		System.out.println("\nPostfix expression : " + postfix);

		System.out.println("\nResult: "+ solveRPN(postfix));
	}

	/** enum **/
	private enum Precedence {
		lparen(0), rparen(1), plus(2), minus(3), divide(4), times(5), mod(6), eos(7), operand(8);

		private int index;

		Precedence(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	/** in stack precedence **/
	private static final int[] isp = { 0, 19, 12, 12, 13, 13, 13, 0 };
	/** incoming character precedence **/
	private static final int[] icp = { 20, 19, 12, 12, 13, 13, 13, 0 };
	/** operators **/
	private static final char[] operators = { '{', '}', '+', '-', '/', '*', '%', ' ' };
	/** precedence stack **/
	private static Precedence[] stack;
	/** stack top pointer **/
	private static int top;

	/** pop element from stack **/
	private static Precedence pop() {
		return stack[top--];
	}

	/** push element onto stack **/
	private static void push(Precedence ele) {
		stack[++top] = ele;
	}

	/** get precedence token for symbol **/
	public static Precedence getToken(char symbol) {
		switch (symbol) {
		case '(':
			return Precedence.lparen;
		case ')':
			return Precedence.rparen;
		case '+':
			return Precedence.plus;
		case '-':
			return Precedence.minus;
		case '/':
			return Precedence.divide;
		case '*':
			return Precedence.times;
		case '%':
			return Precedence.mod;
		case ' ':
			return Precedence.eos;
		default:
			return Precedence.operand;
		}
	}

	/** Function to convert infix to postfix **/
	public static String postfix(String infix) {
		String postfix = "";
		top = 0;
		stack = new Precedence[infix.length()];
		stack[0] = Precedence.eos;
		Precedence token;
		for (int i = 0; i < infix.length(); i++) {
			token = getToken(infix.charAt(i));
			/** if token is operand append to postfix **/
			if (token == Precedence.operand)
				postfix = postfix + infix.charAt(i) + " ";
			/**
			 * if token is right parenthesis pop till matching left parenthesis
			 **/
			else if (token == Precedence.rparen) {
				while (stack[top] != Precedence.lparen)
					postfix = postfix + operators[pop().getIndex()] + " ";
				/** discard left parenthesis **/
				pop();
			}
			/**
			 * else pop stack elements whose precedence is greater than that of
			 * token
			 **/
			else {
				while (isp[stack[top].getIndex()] >= icp[token.getIndex()])
					postfix = postfix + operators[pop().getIndex()] + " ";
				push(token);
			}
		}
		/** pop any remaining elements in stack **/
		while ((token = pop()) != Precedence.eos)
			postfix = postfix + operators[token.getIndex()] + " ";

		return postfix;
	}

	public static int solveRPN(String rpn) {
		Stack<Integer> stack = new Stack<Integer>();
		String operators = new String("*/+-");
		int operand1 = 0, operand2 = 0, result = 0;

		for (int i = 0; i < rpn.length(); i++) {

			// if a ' ' or ',' is encountered just ignore it
			if (rpn.charAt(i) == ' ' || rpn.charAt(i) == ',')
				continue;

			// Check if the token is a digit and push it to the top of the
			// stack.
			else if (isDigit(rpn.charAt(i))) {
				int op = 0;

				// If the token has more than one digit we multiply by 10
				// and add the digit to the operator.
				// We do -'0' to get the correct result since we are dealing
				// with chars.
				while (i < rpn.length() && isDigit(rpn.charAt(i))) {
					op = (op * 10) + (rpn.charAt(i) - '0');
					i++;
				}
				i--;
				stack.push(op);
			}

			// check if token is an operator by comparing it to the string of
			// operators
			else if (operators.contains(rpn.substring(i, i + 1))) {

				// assign operator 1 and operator 2 and remove them from the
				// stack.
				operand2 = (int) stack.pop();
				operand1 = (int) stack.pop();

				// use the operator, operand1, and operand2 to evaluate.
				result = evaluate(rpn.substring(i, i + 1), operand1, operand2);

				// push the result to the top of the stack
				stack.push(result);
			}
		}
		// return final result of the expression
		return stack.peek();

	}

	public static boolean isDigit(int n) {
		// This function check if n is a digit
		if (n >= '0' && n <= '9')
			return true;
		else
			return false;
	}

	public static int evaluate(String operator, int op1, int op2) {
		// This function uses the correct evaluation by the given operator
		// to calculate the expression between operand1 and operand2.
		if (operator.equals("*")) {
			return op1 * op2;
		} else if (operator.equals("/")) {
			return op1 / op2;
		} else if (operator.equals("+")) {
			return op1 + op2;
		} else if (operator.equals("-")) {
			return op1 - op2;
		} else {
			return 0;
		}
	}
}
