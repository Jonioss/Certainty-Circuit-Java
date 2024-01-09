package gatesPackage;
import exceptionsPackage.*;

public class XNOR extends Gate{
	public XNOR() throws invalidValueException{
		this(0, 0);
	}
	public XNOR(Object...obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() {
		try {
			XOR gateXOR = new XOR(inputs);
			NOT gateNOT = new NOT(gateXOR.getOutput());
			output = gateNOT.getOutput();
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
