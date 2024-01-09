package gatesPackage;
import exceptionsPackage.*;

public class NOR extends Gate {
	public NOR() throws invalidValueException{
		this(0, 0);
	}
	public NOR(Object...obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() throws invalidValueException {
		int[] inps = inputsToInts();
		int outputOR = 0;
		for(Integer o: inps){
			if(o == 1) {
				outputOR = 1;
			}
		}
		try {
			output = (outputOR == 1) ? 0 : 1;
			return output;
		} catch (Exception e) {
			return 0;
		}
	}
}
