package testPackage;
import exceptionsPackage.*;
import java.awt.Color;
import FlipFlops.*;
import gatesPackage.*;
import guiPackage.*;
import integratedCircuitsPackage.*;
import mainPackage.*;

@SuppressWarnings("unused")
public class TestClass {
public void test() {
try {
Gate G1;
		try {
			G1 = new XOR(0, 1);
			Gate G2 = new AND(1, 0);
			Gate G3 = new OR(G1.getOutput(), 0);
			Gate G4 = new AND(G2.getOutput(), 0);
			MUX41 mux = new MUX41(G1, G2, G3, G4, 1, 1);
			System.out.println("Output of MUX: " + mux.getOutput());
			System.out.println("\nTruth Table of MUX41: ");
			mux.printTruthTable();
			
			System.out.println();
			Encoder42 ENC = new Encoder42(G1, G2, G3, G4);
			System.out.println("Outputs of Encoder42: \n- Output 0: " + ENC.getOutput0() + "\n- Output 1: " + ENC.getOutput1());
			
			Clock clk = new Clock(50);
			System.out.println("\nOutput of Clock (Period = 50ms) at 143ms is: " + clk.getOutput(143));
			
			Wave w = new Wave(G1); // Make the Wave Object
			int[] inputs = {1, 1}; // The inputs
			w.setTitle("Wave of XOR"); // Set the title
			w.setWaveColors(Color.CYAN, Color.MAGENTA); // Set colors (input, output), default is blue and green
			w.setInputs(inputs); // Set the inputs
			w.setDimensions(30, 50); // Set the dimensions (distance in y, period in x)
			w.showWave(); // Show the wave
		} catch (invalidValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
} catch(Exception e) {
}
}
}

