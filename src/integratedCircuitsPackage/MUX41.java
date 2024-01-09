package integratedCircuitsPackage;

import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public class MUX41 extends Gate{
	
	private int output;
	
	// Constructors for the 4-1 Multiplexer
	public MUX41() throws Exception {
		this(0, 0, 0, 0, 0, 0);
	}
	public MUX41(Object...obj) throws Exception{
		super(obj);
		if(obj.length != 6) {
			throw new invalidValueException();
		}
	}
	
	// Will return the output of the 4-1 Multiplexer
	@Override
	public int getOutput() throws invalidValueException{
		
		int[] inps = inputsToInts();
		
		output = 0;
		if(inps[5] == 0 && inps[4] == 0) {
			output = inps[0];
		}
		else if(inps[5] == 0 && inps[4] == 1) {
			output = inps[1];
		}
		else if(inps[5] == 1 && inps[4] == 0) {
			output = inps[2];
		}
		else if(inps[5] == 1 && inps[4] == 1) {
			output = inps[3];
		}
		else {
			throw new invalidValueException("Invalid value encountered in MUX41's (" + this.toString() + ") getOutput() method.");
		}
		return output;
	}
	
	// Will print the MUX 4-1 truth table
	@Override
	public void printTruthTable() {
		System.out.println("sel1\tsel0\toutput");
		System.out.println("0\t0\tInput 0");
		System.out.println("0\t1\tInput 1");
		System.out.println("1\t0\tInput 2");
		System.out.println("1\t1\tInput 3");
	}
}
