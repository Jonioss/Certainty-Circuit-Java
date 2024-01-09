package integratedCircuitsPackage;

import gatesPackage.Gate;

public class Encoder42 extends Gate{
	
	private int out0, out1;
	
	// Constructor methods
	public Encoder42() throws Exception {
		this(0, 0, 0, 0);
	}
	public Encoder42(Object...obj) throws Exception {
		super(obj);
	}
	
	@Override
	public int getOutput(){
		System.out.println("Do not use getOutput() from Encoder; Use getOutput0() or getOutput1()");
		return 0;
	}
	
	// To return each of the 2 outputs of a 4-2 Encoder
	public int getOutput1() throws Exception{
		
		int[] inps = inputsToInts();
		
		if(inps[3] == 1) {
			this.out1 = 1;
		}
		else if(inps[2] == 1) {
			this.out1 = 1;
		}
		else {
			this.out1 = 0;
		}
		return this.out1;
	}
	public int getOutput0() throws Exception{
		
		int[] inps = inputsToInts();
		
		if(inps[3] == 1) {
			this.out0 = 1;
		}
		else if(inps[1] == 1) {
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
}
