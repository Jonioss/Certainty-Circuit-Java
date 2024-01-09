package integratedCircuitsPackage;

import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public class Encoder42 {
	
	// 4 Inputs, 2 Outputs
	@SuppressWarnings("unused")
	private int in0, in1, in2, in3;
	private int out0, out1;
	
	// Constructor methods
	public Encoder42() throws invalidValueException {
		this(0, 0, 0, 0);
	}
	public Encoder42(int in0, int in1, int in2, int in3) throws invalidValueException {
		if(!Gate.isValidValue(in0) || !Gate.isValidValue(in1) || !Gate.isValidValue(in2) || !Gate.isValidValue(in3)) {
			throw new invalidValueException("Invalid Input values in Encoder42 (" + this.toString() + ")");
		}
		this.in0 = in0;
		this.in1 = in1;
		this.in2 = in2;
		this.in3 = in3;
	}
	public Encoder42(Gate G0, Gate G1, Gate G2, Gate G3) throws invalidValueException {
		this.in0 = G0.getOutput();
		this.in1 = G1.getOutput();
		this.in2 = G2.getOutput();
		this.in3 = G3.getOutput();
	}
	
	// To return each of the 2 outputs of a 4-2 Encoder
	public int getOutput1() {
		if(this.in3 == 1) {
			this.out1 = 1;
		}
		else if(this.in2 == 1) {
			this.out1 = 1;
		}
		else {
			this.out1 = 0;
		}
		return this.out1;
	}
	public int getOutput0() {
		if(this.in3 == 1) {
			this.out0 = 1;
		}
		else if(this.in1 == 1) {
			this.out0 = 1;
		}
		else {
			this.out0 = 0;
		}
		return this.out0;
	}
	
	// Prints the typical truth table of the 4-2 Encoder
	public void printTruthTable() {
		System.out.println("In3\tIn2\tIn1\tIn0\tOut1\tOut0");
		System.out.println("0\t0\t0\t0\t0\t0\t");
		System.out.println("0\t0\t0\t1\t0\t0\t");
		System.out.println("0\t0\t1\tx\t0\t1\t");
		System.out.println("0\t1\tx\tx\t1\t0\t");
		System.out.println("1\tx\tx\tx\t1\t1\t");
	}
	
	// To change the Encoder's inputs
	public void changeInputs(int in0, int in1, int in2, int in3) throws invalidValueException {
		if(Gate.isValidValue(in0) && Gate.isValidValue(in1) && Gate.isValidValue(in2) && Gate.isValidValue(in3)) {
			this.in0 = in0;
			this.in1 = in1;
			this.in2 = in2;
			this.in3 = in3;
		}
		else {
			throw new invalidValueException("Invalid Input values in Encoder42 (" + this.toString() + ")");
		}
	}
	/*public void setInputs(Gate G0, Gate G1, Gate G2, Gate G3) {
		this.in0 = G0.getOutput();
		this.in1 = G1.getOutput();
		this.in2 = G2.getOutput();
		this.in3 = G3.getOutput();
	}*/
}
