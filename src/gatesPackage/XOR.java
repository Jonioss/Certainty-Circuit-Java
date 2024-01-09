package gatesPackage;
import exceptionsPackage.*;

public class XOR extends Gate{
	public XOR() throws invalidValueException{
		this(0, 0);
	}
	public XOR(Object...obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() throws invalidValueException {
		// how many inputs are 1 
		int numsOfOne = 0;
		int[] inps = inputsToInts();
		output = 0;
		try {
			for(Integer i: inps) {
				if(i == 1) {
					numsOfOne++;
				}
			}
			if(numsOfOne == 1) {
				output = 1;
			}
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
