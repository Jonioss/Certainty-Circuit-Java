package mainPacakge;
import FlipFlops.*;
import exceptionsPackage.*;
import gatesPackage.*;

public class mainClass {
	public static void main(String args[]) throws invalidValueException {
		Gate G1 = new XOR(0, 0);
		Gate G2 = new AND(1, 1);
		Gate G3 = new OR(G1, G2);
		System.out.println(G3.getOutput());
		
		Clock clk = new Clock(50);
		System.out.println(clk.getOutput(143));
	}
}
