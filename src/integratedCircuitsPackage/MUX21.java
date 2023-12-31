package integratedCircuitsPackage;

import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public final class MUX21{
	
	// Inputs, Select-Input and Output
	private int in0, in1;
	private int sel;
	private int output;
	
	// Constructors for the 2-1 Multiplexer
	public MUX21() throws invalidValueException {
		this(0, 0, 0);
	}
	public MUX21(int inpA, int inpB, int select) throws invalidValueException{
		if(!Gate.isValidValue(inpA) || !Gate.isValidValue(inpB) || !Gate.isValidValue(select)) {
			throw new invalidValueException("Invalid Input values in MUX21 (" + this.toString() + ")");
		}
		else {
			this.in0 = inpA;
			this.in1 = inpB;
			this.sel = select;
		}
	}
	public MUX21(Gate G1, Gate G2, int select) throws invalidValueException {
		if(!Gate.isValidValue(select)) {
			throw new invalidValueException();
		}
		this.in0 = G1.getOutput();
		this.in1 = G2.getOutput();
		this.sel = select;
	}
	
	// Will return the output of the 2-1 Multiplexer
	public int getOutput() throws invalidValueException{
		output = 0;
		if(this.sel == 0) {
			output = this.in0;
		}
		else if(this.sel == 1) {
			output = this.in1;
		}
		else {
			throw new invalidValueException("Invalid value encountered in MUX21's (" + this.toString() + ") getOutput() method.");
		}
		return output;
	}
	
	// Changes the inputs to the MUX
	public void changeInputs(int new0, int new1, int newSel) throws invalidValueException {
		if(!Gate.isValidValue(new0) || !Gate.isValidValue(new1) || !Gate.isValidValue(newSel)) {
			throw new invalidValueException();
		}
		in0 = new0;
		in1 = new1;
		sel = newSel;
	}
	public void changeInputs(Gate newG0, Gate newG1, int newSel) throws invalidValueException {
		if(!Gate.isValidValue(newSel)) {
			throw new invalidValueException();
		}
		in0 = newG0.getOutput();
		in1 = newG1.getOutput();
		sel = newSel;
	}
	
	// Will print the MUX 2-1 truth table
	public void printTruthTable() {
		System.out.println("sel\toutput");
		System.out.println("0\tInput 0");
		System.out.println("1\tInput 1");
	}
}
