
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
/**
 * 
 * @author m_alrajab
 * Simple GUI interface for testing the algorithm
 * The main purpose of this implementation to show how inheritance, polymorphism
 * overridden methods, interface and other OOP topics are used in the text calculator
 * 
 * The KeyAdapter is used instead of KeyListener
 * 
 * This only process simple and single arithmetic expression
 * 
 */
public class TestClass {
	private static String str="";
	private static Map<Character, BinOperation> operations = new HashMap<>();
	public static void main(String[] args) {
		operations.put('*', new Multiplication());
		operations.put('+', new Addition());
		operations.put('-', new Subtraction());
		 operations.put('\\', new Division());

		JFrame frame=new JFrame("Text Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// This is a Grid layout of 2 rows and 1 col.
		frame.setLayout(new GridLayout(2, 1));
		JTextField txtField=new JTextField();
		txtField.setFont(new Font("Ariel", Font.PLAIN, 38));
		JLabel lbl=new JLabel();
		lbl.setFont(new Font("Ariel", Font.PLAIN, 28));
		frame.getContentPane().add(txtField);
		txtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {				
				if(e.getKeyChar()=='='){
					str=txtField.getText().split("=")[0];
					// evaluate expression like 4.3+4=
					//lbl.setText(txtField.getText()+"= "+	processExp(str) ) ;

					// evaluate an expression like ((5*3)-(5+4))=
					lbl.setText(txtField.getText()+"= "+	EvaluateArithmeticExpParanthesis.processExpression(str) ) ;
				}
			}
		});
		frame.getContentPane().add(lbl);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	
	public static double processExp(String str){
		double inputA, inputB;char operator;
		Pattern pt=Pattern.compile("\\+|\\*|\\\\|\\-|"); 
		String str1= pt.split(str)[0];
		inputA = Double.valueOf(str1);
		operator = str.split(str1)[1].charAt(0);
		inputB = Double.valueOf(pt.split(str)[1]);
		BinOperation op = operations.get(operator);
		return 	op.evaluate(inputA, inputB)  ;
		
	}
}
