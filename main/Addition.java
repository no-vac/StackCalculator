
public class Addition implements BinOperation{

	@Override
	public double evaluate(double left, double right) {
		return left+right;
	}

	// Overload for other data types
}
