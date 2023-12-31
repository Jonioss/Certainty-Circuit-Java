package integratedCircuitsPackage;

import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public class MUX41 {
	// Inputs, Select-Input and Output
	private int in0, in1, in2, in3;
	private int sel0, sel1;
	private int output;
	
	// Constructors for the 4-1 Multiplexer
	public MUX41() throws invalidValueException {
		this(0, 0, 0, 0, 0, 0);
	}
	public MUX41(int inpA, int inpB, int inpC, int inpD, int select0, int select1) throws invalidValueException{
		if(!Gate.isValidValue(inpA) || !Gate.isValidValue(inpB) || !Gate.isValidValue(inpC) || !Gate.isValidValue(inpD) ||
				!Gate.isValidValue(select0) || !Gate.isValidValue(select1)) {
			throw new invalidValueException("Invalid Input/Select values in MUX41 (" + this.toString() + ")");
		}
		else {
			this.in0 = inpA;
			this.in1 = inpB;
			this.in2 = inpC;
			this.in3 = inpD;
			this.sel0 = select0;
			this.sel1 = select1;
		}
	}
	public MUX41(Gate G1, Gate G2, Gate G3, Gate G4, int select0, int select1) throws invalidValueException {
		if(!Gate.isValidValue(select0) || !Gate.isValidValue(select1)) {
			throw new invalidValueException("Invalid Input/Select values in MUX41 (" + this.toString() + ")");
		}
		this.in0 = G1.getOutput();
		this.in1 = G2.getOutput();
		this.in2 = G3.getOutput();
		this.in3 = G4.getOutput();
		this.sel0 = select0;
		this.sel1 = select1;
	}
	
	// Will return the output of the 4-1 Multiplexer
	public int getOutput() throws invalidValueException{
		output = 0;
		if(this.sel1 == 0 && this.sel0 == 0) {
			output = this.in0;
		}
		else if(this.sel1 == 0 && this.sel0 == 1) {
			output = this.in1;
		}
		else if(this.sel1 == 1 && this.sel0 == 0) {
			output = this.in2;
		}
		else if(this.sel1 == 1 && this.sel0 == 1) {
			output = this.in3;
		}
		else {
			throw new invalidValueException("Invalid value encountered in MUX41's (" + this.toString() + ") getOutput() method.");
		}
		return output;
	}
	
	// Changes the inputs to the MUX
	public void changeInputs(int inpA, int inpB, int inpC, int inpD, int select0, int select1) throws invalidValueException {
		if(!Gate.isValidValue(inpA) || !Gate.isValidValue(inpB) || !Gate.isValidValue(inpC) || !Gate.isValidValue(inpD) ||
				!Gate.isValidValue(select0) || !Gate.isValidValue(select1)) {
			throw new invalidValueException("Invalid Input/Select values in MUX41 (" + this.toString() + ")");
		}
		this.in0 = inpA;
		this.in1 = inpB;
		this.in2 = inpC;
		this.in3 = inpD;
		this.sel0 = select0;
		this.sel1 = select1;
	}
	public void changeInputs(Gate G1, Gate G2, Gate G3, Gate G4, int select0, int select1) throws invalidValueException {
		if(!Gate.isValidValue(select0) || !Gate.isValidValue(select1)) {
			throw new invalidValueException("Invalid Input/Select values in MUX41 (" + this.toString() + ")");
		}
		this.in0 = G1.getOutput();
		this.in1 = G2.getOutput();
		this.in2 = G3.getOutput();
		this.in3 = G4.getOutput();
		this.sel0 = select0;
		this.sel1 = select1;
	}
	
	// Will print the MUX 4-1 truth table
	public void printTruthTable() {
		System.out.println("sel1\tsel0\toutput");
		System.out.println("0\t0\tInput 0");
		System.out.println("0\t1\tInput 1");
		System.out.println("1\t0\tInput 2");
		System.out.println("1\t1\tInput 3");
	}
}
