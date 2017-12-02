
public class Division implements BinOperation{

	@Override
	public double evaluate(double left, double right) {
		// No handling of error, this should throw an exception
		return (right==0)?0:left/right;
	}
	
	// Overload for other data types
}