
import java.util.NoSuchElementException;

public class Stack {
	public int size;
	public int top;
	public int[] array;
	
	public Stack () {
		size = 0;
		top = -1;
		array = null;
	}
	public Stack (int _size) {
		size = _size;
		top = -1;
		array = new int[size];
	}
	public boolean empty () {
		if (top == 0) 
		return true;
		else
		return false;
	}
	public void push (int x) {
		if (top < size) {
			top = top + 1;
			array[top] = x; }
		else
			System.err.println(); //extra credit(?)
	}
	public int pop () {
		if (empty()) {
		return -1; 
		}
		else {
		    top = top - 1;	
			return array[top + 1];
		}
	}
	/*
	 * Convert stack to string in the format of #size, [#elements]
	 */
	public String toString () {
		String str;
		
		str = size + ", [";
		for (int i = 0; i <= top; i++)
			str += array[i] + ", ";
		
		str += "]";
		return str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s;
		
		s = new Stack(10);
		for (int i = 0; i < 5; i++)
			s.push(i);
		System.out.println(s.toString());
		
		for (int i = 0; i < 2; i++)
			s.pop();
		System.out.println(s.toString());
	}
}