package gatesPackage;
import exceptionsPackage.*;

public class NAND extends Gate{
	public NAND() throws invalidValueException{
		this(0, 0);
	}
	public NAND(Object...obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() {
		try {
			int outputAND = (new AND(inputs)).getOutput();
			NOT gateNot = new NOT(outputAND);
			output = gateNot.getOutput();
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
