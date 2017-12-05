
import java.util.Stack;

public class EvaluateReversePolish {

	public static void main(String[] args) {
		String expression = new String("8 2 / 5 * 2 *");
		Stack<Integer> stack = new Stack<Integer>();
		String operators = new String("*/+-");
		int operand1 = 0, operand2 = 0, result = 0;

		for (int i = 0; i < expression.length(); i++) {

			// if a ' ' or ',' is encountered just ignore it
			if (expression.charAt(i) == ' ' || expression.charAt(i) == ',')
				continue;

			// Check if the token is a digit and push it to the top of the
			// stack.
			else if (isDigit(expression.charAt(i))) {
				int op = 0;

				// If the token has more than one digit we multiply by 10
				// and add the digit to the operator.
				// We do -'0' to get the correct result since we are dealing
				// with chars.
				while (i < expression.length() && isDigit(expression.charAt(i))) {
					op = (op * 10) + (expression.charAt(i) - '0');
					i++;
				}
				i--;
				stack.push(op);
			}

			// check if token is an operator by comparing it to the string of
			// operators
			else if (operators.contains(expression.substring(i, i + 1))) {

				// assign operator 1 and operator 2 and remove them from the
				// stack.
				operand2 = (int) stack.pop();
				operand1 = (int) stack.pop();

				// use the operator, operand1, and operand2 to evaluate.
				result = evaluate(expression.substring(i, i + 1), operand1, operand2);

				// push the result to the top of the stack
				stack.push(result);
			}
		}
		// print final result of the expression
		System.out.println(stack.peek());

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
