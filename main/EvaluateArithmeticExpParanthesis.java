
import java.util.Stack;

public class EvaluateArithmeticExpParanthesis {
	private static int ind=0;
	public static Double processExpression(String str){
		ind=0;
		Stack<String> ops = new Stack<>();
		Stack<Double> vals = new Stack<>();
		
		while(ind<str.length()){
			String s=""+str.charAt(ind);
			if(s.equals("("));
			else if (s.equals("+")||s.equals("*")||s.equals("-")||s.equals("/")) ops.push(s);
			else if (s.equals(")")) {
				String op=ops.pop();
				if(op.equals("+")) vals.push(vals.pop()+vals.pop());
				else if (op.equals("*")) vals.push(vals.pop()*vals.pop());
				else if (op.equals("-")) vals.push(-1*vals.pop()+vals.pop());
			}
			else
				vals.push(Double.parseDouble(s));
			ind++;
		}
		if(ops.isEmpty())
			return vals.pop();
		else{
			while(!ops.isEmpty()){
			String op=ops.pop();
			if(op.equals("+")) vals.push(vals.pop()+vals.pop());
			else if (op.equals("*")) vals.push(vals.pop()*vals.pop());
			else if (op.equals("-")) vals.push(-1*vals.pop()+vals.pop());
			}
			return vals.pop();
		}
	}
}
