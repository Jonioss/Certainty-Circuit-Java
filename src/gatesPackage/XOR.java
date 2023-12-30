package gatesPackage;
import exceptionsPackage.*;

public class XOR extends Gate{
	private int outputRaw;
	public XOR() throws invalidValueException{
		this(0, 0);
	}
	public XOR(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public XOR(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		try {
			NOT notA = new NOT(inputA);
			NOT notB = new NOT(inputB);
			outputRaw = inputA * notB.getOutput() + notA.getOutput() * inputB;
			output = (outputRaw == 0) ? 0 : 1;
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
