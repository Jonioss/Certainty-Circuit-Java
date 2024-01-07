# Certainty-Circuit-Java

## Table Of Contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Usage](#usage)
* [Future](#future)

## Introduction
Circuit Simulation Software currently in development

## Technologies
* JDK Version: 21

## Usage
### User Interface
The UI consists of:
- The Button panel, with appropriate buttons to save, load and run circuit design,
- The Code panel, where the user writes the code to simulate a circuit,
- The Console panel.

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
MUX21 mux21 = new MUX21(input0, input1, select); // If you want integer inputs
MUX21 mux21 = new MUX21(G0, G1, select) // If you want Gate inputs and select as integer
```
#### MUX41
To create a 4-to-1 Multiplexer run the command:
```
MUX41 mux41 = new MUX41(input0, input1, input2, input3, select0, select1); // If you want integer inputs
MUX41 mux41 = new MUX41(G0, G1, G2, G3, select0, select1) // If you want Gate inputs with select0 and select1 as integers
```
#### Encoder42
To Create a 4-to-2 Encoder run the command:
```
Encoder42 ENC = new Encoder42(in0, in1, in2, in3); // If you want integer inputs
Encoder42 ENC = new Encoder42(G1, G2, G3, G4); // If you want Gate inputs
```
### Waveforms
To create a simple gate waveform, use:
```
Gate G1 = new AND(0, 1); // Create the gate
Wave w = new Wave(G1); // Create the wave
w.showWave(); // Show the wave
```
You can also have the first input to the wave's gate be a clock:
```
Gate G1 = new AND(0, 1); // The first input doesn't matter later since it becomes a clock
Clock clk = new Clock(50); // 50 is the period in picoseconds
Wave w = new Wave(G1, clk); // Create the wave
w.showWave(); // Show the wave
```

## Future
- Future updates with sequential circuits coming soon
- MUX81, Encoder83 and more integrated circuits coming soon
- Expansion upon waveforms; ability to test an entire circuit rather than just one gate


