package gatesPackage;
import exceptionsPackage.*;

public class OR extends Gate{
	public OR() throws invalidValueException{
		this(0, 0);
	}
	public OR(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public OR(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		output = (inputA + inputB > 0) ? 1 : 0;
		return output;
	}
}
