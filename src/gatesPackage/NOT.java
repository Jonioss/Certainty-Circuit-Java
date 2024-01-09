package gatesPackage;
import exceptionsPackage.*;

public class NOT extends Gate{
	public NOT() throws invalidValueException{
		this(0);
	}
	public NOT(Object input) throws invalidValueException{
		try {
			if(input instanceof Integer) {
				if((int)input != 1 && (int)input != 0) { throw new invalidValueException(); }
				inputs[0] = (int)input;
			}
			else if(input instanceof Gate) {
				inputs[0] = ((Gate) input).getOutput();
			}
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	@Override
	public void printTruthTable() {
		System.out.println("A\tOutput");
		System.out.println(0 + "\t" + 1);
		System.out.println(1 + "\t" + 0);
	}
	@Override
	public int getOutput() throws invalidValueException {
		int[] inps = inputsToInts();
		output = (inps[0] == 1) ? 0 : 1;
		return output;
	}
}
