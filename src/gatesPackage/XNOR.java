package gatesPackage;
import exceptionsPackage.*;

public class XNOR extends Gate{
	public XNOR() throws invalidValueException{
		this(0, 0);
	}
	public XNOR(int inA, int inB) throws invalidValueException{
		try {
			if((inA != 1 && inA != 0) || (inB != 1 && inB != 0)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public XNOR(Gate G1, Gate G2) {
		super(G1, G2);
	}
	@Override
	public int getOutput() {
		try {
			XOR gateXOR = new XOR(inputA, inputB);
			NOT gateNOT = new NOT(gateXOR.getOutput());
			output = gateNOT.getOutput();
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
