package gatesPackage;
import FlipFlops.Clock;
import exceptionsPackage.*;

public class Gate {
	// 2 inputs of the Gate and the output, 1 bit each
	protected Object[] inputs;
	protected int output = 0;
	protected int setOutput = -1;
	Gate clk;
	protected int currentTime = 0; // For when the inputs are clocks
	
	// Constructors for the Gate Object
	public Gate() throws invalidValueException{
		this(0, 0);
	}
	public Gate (Object... obj) throws invalidValueException{
		inputs = obj;
	}
	
	public void setInputs(Object... new_in) throws invalidValueException {
		for(Object i: new_in) {
			if(i instanceof Integer) {
				if(!isValidValue((int) i)) {
					throw new invalidValueException();
				}
			}
		}
		inputs = new_in;
	}
	
	// Set the time in ps
	public void setTime(int newTime) {
		currentTime = newTime;
	}
	
	public void setNewOutput(int newSetOutput) {
		if(isValidValue(newSetOutput)) {
			setOutput = newSetOutput;
		}
	}
	
	// Returns the output of the Gate
	public int getOutput() throws invalidValueException {
		if(setOutput == -1) {
			return output;
		}
		else {
			return setOutput;
		}
	}
	
	// Returns the inputs as integer matrix
	public Object[] getInputs() {
		return inputs;
	}
	
	// Prints the truth table of the Gate for two inputs
	public void printTruthTable() throws invalidValueException {
		System.out.println("A\tB\tOutput");
		this.setInputs(0, 0);
		System.out.println(0 + "\t" + 0 + "\t" + getOutput());
		this.setInputs(0, 1);
		System.out.println(0 + "\t" + 1 + "\t" + getOutput());
		this.setInputs(1, 0);
		System.out.println(1 + "\t" + 0 + "\t" + getOutput());
		this.setInputs(1, 1);
		System.out.println(1 + "\t" + 1 + "\t" + getOutput());
	}
	
	
	// Checks if the given integer has a valid boolean value (0 or 1)
	public static boolean isValidValue(int input) {
		if(input == 0 || input == 1)
			return true;
		return false;
	}
	
	public int[] inputsToInts() throws invalidValueException {
		int[] inps = new int[inputs.length];
		int i = 0;
		
		for(Object o: inputs) {
			if(o instanceof Integer) {
				if(isValidValue((int) o)) {
					inps[i] = (int) o;
					i++;
				}
				else {
					throw new invalidValueException("Invalid value encountered in inputs to a Gate object");
				}
			}
			else if(o instanceof Gate) {
				inps[i] = ((Gate) o).getOutput();
				i++;
			}
			else if(o instanceof Clock) {
				inps[i] = ((Clock) o).getOutput(currentTime);
				i++;
			}
			else {
				throw new invalidValueException("Invalid Object in Gate's inputs");
			}
		}
		
		return inps;
	}
	
}
