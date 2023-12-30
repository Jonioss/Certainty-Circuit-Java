package gatesPackage;
import exceptionsPackage.*;

public class AND extends Gate{
	public AND() throws invalidValueException{
		this(0, 0);
	}
	public AND(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public AND(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		output = inputA * inputB;
		return output;
	}
}
