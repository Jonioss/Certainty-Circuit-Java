package mainPacakge;
import FlipFlops.*;
import exceptionsPackage.*;
import gatesPackage.*;
import integratedCircuitsPackage.*;

public class mainClass {
	public static void main(String args[]) throws invalidValueException {
		Gate G1 = new XOR(0, 1);
		Gate G2 = new AND(1, 0);
		Gate G3 = new OR(G1.getOutput(), 0);
		Gate G4 = new AND(G2.getOutput(), 1);
		MUX41 mux = new MUX41(G1, G2, G3, G4, 1, 1);
		System.out.println("Output of MUX: " + mux.getOutput());
		System.out.println("\nTruth Table of MUX41: ");
		mux.printTruthTable();
		
		Clock clk = new Clock(50);
		System.out.println("\nOutput of Clock (Period = 50ms) at 143ms is: " + clk.getOutput(143));
	}
}
