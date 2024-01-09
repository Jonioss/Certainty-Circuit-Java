package gatesPackage;
import exceptionsPackage.*;

public class OR extends Gate{
	public OR() throws invalidValueException{
		this(0, 0);
	}
	public OR(Object...obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() throws invalidValueException {
		int[] inps = inputsToInts();
		output = 0;
		for(Integer o: inps){
			if(o == 1) {
				output = 1;
			}
		}
		return output;
	}
}
