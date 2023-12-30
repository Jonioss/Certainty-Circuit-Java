package gatesPackage;
import exceptionsPackage.*;

public class NAND extends Gate{
	private int outputAND;
	public NAND() throws invalidValueException{
		this(0, 0);
	}
	public NAND(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public NAND(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		outputAND = inputA * inputB;
		try {
			NOT gateNot = new NOT(outputAND);
			output = gateNot.getOutput();
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
