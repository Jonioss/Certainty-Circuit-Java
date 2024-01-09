package gatesPackage;
import exceptionsPackage.*;

public class AND extends Gate{
	public AND() throws invalidValueException{
		this(0, 0);
	}
	public AND(Object... obj) throws invalidValueException{
		super(obj);
	}
	@Override
	public int getOutput() throws invalidValueException {
		int[] inps = inputsToInts();
		output = 1;
		for(Integer o: inps){
			output *= o;
		}
		return output;
	}
	
}
