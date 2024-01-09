package integratedCircuitsPackage;

import exceptionsPackage.invalidNumberOfInputs;
import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public final class MUX21 extends Gate{
	
	private int output;
	
	// Constructors for the 2-1 Multiplexer
	public MUX21() throws Exception {
		this(0, 0, 0);
	}
	public MUX21(Object...obj) throws Exception{
		super(obj);
		if(obj.length != 3) {
			throw new invalidNumberOfInputs();
		}
	}
	
	// Will return the output of the 2-1 Multiplexer
	@Override
	public int getOutput() throws invalidValueException{
		int[] inps = inputsToInts();
		output = 0;
		if(inps[2] == 0) {
			output = inps[0];
		}
		else if(inps[2] == 1) {
			output = inps[1];
		}
		else {
			throw new invalidValueException("Invalid value encountered in MUX21's (" + this.toString() + ") getOutput() method.");
		}
		return output;
	}
	
	// Will print the MUX 2-1 truth table
	@Override
	public void printTruthTable() {
		System.out.println("sel\toutput");
		System.out.println("0\tInput 0");
		System.out.println("1\tInput 1");
	}
}
