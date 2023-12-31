package gatesPackage;
import exceptionsPackage.*;

public class Gate {
	// 2 inputs of the Gate and the output, 1 bit each
	protected int inputA, inputB, output = 0;
	
	// Constructors for the Gate Object
	public Gate() throws invalidValueException {
		this(0, 0);
	}
	public Gate (int inA, int inB) throws invalidValueException{
		try {
			if(!isValidValue(inA) || !isValidValue(inB)) { throw new invalidValueException(); }
			inputA = inA;
			inputB = inB;
		} catch (invalidValueException e){
			System.out.println(e);
			System.exit(1);
		}
	}
	public Gate (Gate G1, Gate G2) {
		inputA = G1.getOutput();
		inputB = G2.getOutput();
	}
	
	// Returns the output of the Gate
	public int getOutput() {
		return output;
	}
	
	// Returns the inputs as integer matrix
	public int[] getInputs() {
		int[] inputs = {inputA, inputB};
		return inputs;
	}
	
	// Changes the inputs to the Gate
	public void changeInputs(int newA, int newB) throws invalidValueException {
		if(!isValidValue(newA) || !isValidValue(newB)) {
			throw new invalidValueException();
		}
		inputA = newA;
		inputB = newB;
	}
	public void changeInputs(Gate newG1, Gate newG2) {
		inputA = newG1.getOutput();
		inputB = newG2.getOutput();
	}
	
	// Prints the truth table of the Gate
	public void printTruthTable() throws invalidValueException {
		System.out.println("A\tB\tOutput");
		int tempA = inputA; int tempB = inputB;
		changeInputs(0, 0);
		System.out.println(inputA + "\t" + inputB + "\t" + getOutput());
		changeInputs(0, 1);
		System.out.println(inputA + "\t" + inputB + "\t" + getOutput());
		changeInputs(1, 0);
		System.out.println(inputA + "\t" + inputB + "\t" + getOutput());
		changeInputs(1, 1);
		System.out.println(inputA + "\t" + inputB + "\t" + getOutput());
		inputA = tempA; inputB = tempB;
	}
	
	public static boolean isValidValue(int input) {
		if(input == 0 || input == 1)
			return true;
		return false;
	}
}
