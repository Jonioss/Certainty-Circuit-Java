# Certainty-Circuit-Java

## Table Of Contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Usage](#usage)
* [Future](#future)

## Introduction
Circuit Simulation Software and IDE currently in development

## Technologies
* JDK Version: 21

## Usage
### User Interface
The UI consists of:
- The Menu panel, with appropriate buttons to save, load and run circuit design,
- The Code panel, where the user writes the code to simulate a circuit,
- The Console panel.

### Pre-made code
When the user runs the application, the code panel will already have a few lines of code to point the user towards the right direction.
```
public Main() {

	try {

		// Implement your circuit here

	} catch(Exception e) { e.printStackTrace(); }
}

// Use this area to implement custom circuits
```
The pre-generated lines of code should not be altered. Instead, the user should follow the instructions in the comments.

### Logic Gate Usage
To create a logic gate, type the code:
```
Gate G1 = new AND(0, 1);
```
The above example is creating a simple AND gate with inputs 0 and 1.
The Gates constructor can also have Gates and integers as inputs. Such a Gate would be created with:
```
// Create two simple gates
Gate G1 = new AND(0, 1);
Gate G2 = new OR(1, 0);

// Use the above gates as inputs to another gate along with the input 1
Gate G3 = new NAND(G1, G2, 1);
```
To get the output of a gate as int, use the command:
```
G1.getOutput();
```
To change the inputs to the gate, use the command:
```
G3.setInputs(Gate g1, Gate g2, ..., int i1, int i2, ...);
```
To print the truth table of a gate to the console, use:
```
G1.printTruthTable();
```
### IC Usage

Current ICs available include:
* [MUX21](#mux21)
* [MUX41](#mux41)
* [Encoder42](#encoder42)

#### MUX21
To create a 2-to-1 Multiplexer run the command:
```
MUX21 mux21 = new MUX21(input0, input1, select); // Inputs can be integers, gates or clocks
```
#### MUX41
To create a 4-to-1 Multiplexer run the command:
```
MUX41 mux41 = new MUX41(input0, input1, input2, input3, select0, select1); // Inputs can be integers, gates or clocks
```
#### Encoder42
To Create a 4-to-2 Encoder run the command:
```
Encoder42 ENC = new Encoder42(G1, in1, G2, in3); // Inputs can be integers, gates or clocks
```
### Truth Tables
Truth Tables are used for instant simplification of a circuit using the Karnaugh method.
To create a simple TruthTable object, use:
```
TruthTable tt = new TruthTable(int, int, int, ...);
```
The arguments in the TruthTable's constructor are all integers, and represent the outputs of the unknown circuit for inputs 0, 1, 2, 3 etc. Obviously, they can only have value of 0 or 1.
To print the TruthTable in table format, simply print it as such:
```
write(tt); OR System.out.println(tt);
```
To use the Karnaugh method and print out the result, one may use:
```
write(tt.simplify()); // tt.simplify() returns the circuit in String format
```
The above prints out the result of the Karnaugh method. Variable a is always the LSB (first digit of inputs) and the last of the variables in alphabetical order is the MSB (last digit of inputs).
### Waveforms
To create a simple gate waveform, use:
```
Gate G1 = new AND(0, 1); // Create the gate
Wave w = new Wave(G1); // Create the wave
w.showWave(); // Show the wave
```
You can also have any input to the wave's gate be a clock:
```
Clock clk = new Clock(50); // 50 is the period in picoseconds
Gate G1 = new AND(clk, 1); // We choose the first input to be a clock and the second to be HIGH for this example
Wave w = new Wave(G1); // Create the wave
w.showWave(); // Show the wave
```
To change the appearance of the waveform, use:
```
w.setWaveColors(Color inputsColor, Color outputColor) // Default is Color.BLUE and Color.GREEN respectively
w.setDimensions(int halfPeriod, int height) // Default s 50 and 50 respectively
w.setName(String newName) // Default is "Wave"
```
before using w.showWave().
### Custom Circuit Implementation

In order to implement a custom circuit, the user should do so outside of the Main public method.
Example code for implementing a custom circuit with integer inputs is shown below:
```
Gate circuit(int in1, int in2) throws Exception{
	
	Gate customCircuit = new Gate(); // Create the custom integrated circuit as one gate 

	// Implement the usage of this circuit
	Gate g1 = new AND(0, in1);
	Gate g2 = new OR(1, in2);
	Gate g3 = new XOR(g1, g2);


	customCircuit.setInputs(in1, in2); // Set the inputs
	customCircuit.setNewOutput(g3.getOutput()); // Set the output
	return customCircuit; // Return the custom integrated circuit
}
```
Afterwards, inside the Main method, the user can use the following code to test the usage of
the custom circuit:
```
Wave w = new Wave(circuit(1, 0));
w.showWave();
```

Another example using clocks would be:
```
void circuit(Clock in1, Clock in2) throws Exception{

	// Implement the usage of this circuit
	Gate G1 = new AND(in1, in2);
	Gate G2 = new XOR(1, in2);
	Gate G3 = new OR(G1, G2);

	Wave w = new Wave(G3);
	w.setInputs(in1, in2);
	w.showWave();
}
```
Afterwards, inside the Main method, the user can use the following code to test the usage of
the custom circuit:
```
Clock clk1 = new Clock(50);
Clock clk2 = new Clock(100);
circuit(clk1, clk2);
```

## Future
- Future updates with sequential circuits coming soon
- Updates to TruthTable and unknown circuit simplification coming soon
