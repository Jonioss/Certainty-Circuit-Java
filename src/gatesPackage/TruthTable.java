package gatesPackage;

import java.util.LinkedList;

import exceptionsPackage.InvalidTableLengthException;
import exceptionsPackage.invalidValueException;

public class TruthTable {
	
	private LinkedList<Integer> cells = new LinkedList<>(); // The outputs
	private int length = 0; // The length of the "cells" linked list
	
	public String[] vars = {"a", "b", "c", "d", "e"};
	
	LinkedList<int[]> ll_inputs = new LinkedList<int[]>();

	public TruthTable(int... objects) throws Exception{
		length = objects.length;
		if(length != 2 && length != 4 && length != 8 && length != 16 && length != 32) {
			throw new InvalidTableLengthException();
		}
		
		for(int i: objects) {
			if(!isValidValue(i)) {
				throw new invalidValueException();
			}
			cells.add(i);
		}
	}
	
	public String toString() {
		return makeTruthTable();
	}
	
	public String simplify() {
		@SuppressWarnings("unused")
		String temp = makeTruthTable();
		
		String out = "";
		
		for(int i = 0; i < length; i++) {
			if(cells.get(i) == 0) continue;
			if(out == "") {
				out += "Output: ";
			}
			else {
				out += " + ";
			}
			for(int j = 0; j < ll_inputs.get(i).length; j++) {
				out += vars[j];
				if(ll_inputs.get(i)[ll_inputs.get(i).length - j - 1] == 0) {
					out += "'";
				}
			}
		}
		
		return out;
	}
	
	public String makeTruthTable() {
		
		String out = "";
		
		for(int i = 0; i < length; i++) {
			
			int[] inputs = new int[log2(length)];
			
			int temp = i;
			for(int j = 0; j < inputs.length; j++) {
				inputs[j] = temp % 2;
				temp = temp / 2;
			}
			for(int j = 0; j < inputs.length; j++) {
				out += "   " + Integer.toString(inputs[inputs.length - j - 1]);
				out += "   |";
			}
			
			ll_inputs.add(inputs);
			
			out += "|   " + Integer.toString(cells.get(i));
			out += "\n";
		}
		return out;
	}
	
	private int log2(int n) {
		
		return (int) (Math.log(n) / Math.log(2));
		
	}
	
	// Checks if the given integer has a valid boolean value (0 or 1)
	private static boolean isValidValue(int input) {
		if(input == 0 || input == 1)
			return true;
		return false;
	}
	
}
