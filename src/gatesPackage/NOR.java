package gatesPackage;
import exceptionsPackage.*;

public class NOR extends Gate {
	private int outputOR;
	public NOR() throws invalidValueException{
		this(0, 0);
	}
	public NOR(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public NOR(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		outputOR = (inputA + inputB > 0) ? 1 : 0;
		try {
			NOT gateNot = new NOT(outputOR);
			output = gateNot.getOutput();
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
