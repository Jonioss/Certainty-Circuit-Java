package gatesPackage;
import exceptionsPackage.*;

public class NOT extends Gate{
	public NOT() throws invalidValueException{
		this(0);
	}
	public NOT(int input) throws invalidValueException{
		try {
			if(input != 1 && input != 0) { throw new invalidValueException(); }
			inputA = input;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public NOT(Gate G1) throws invalidValueException{
		inputA = G1.getOutput();
	}
	@Override
	public int[] getInputs() {
		int[] input = {inputA};
		return input;
	}
	@Override
	public void printTruthTable() {
		int temp = inputA;
		System.out.println("A\tOutput");
		inputA = 0;
		System.out.println(inputA + "\t" + this.getOutput());
		inputA = 1;
		System.out.println(inputA + "\t" + this.getOutput());
		inputA = temp;
	}
	@Override
	public int getOutput() {
		output = (inputA == 1) ? 0 : 1;
		return output;
	}
}
