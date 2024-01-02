# Java-Logic-Gates

## Table Of Contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Usage](#usage)
* [Future](#future)

## Introduction
Necessary code to implement logic gates and logic circuits in Java.

## Technologies
* JDK Version: 21

## Usage
### Basic Gate Usage
To create a logic gate, type the code:
```
Gate G1 = new AND(0, 1);
```
The above example is creating a simple AND gate with inputs 0 and 1.
The Gates constructor can also have Gates as inputs. Such a Gate would be created with:
```
// Create two simple gates
Gate G1 = new AND(0, 1);
Gate G2 = new OR(1, 0);

// Use the above gates as inputs to another gate
Gate G3 = new XOR(G1, G2);
```
To get the output of a gate as int, use the command:
```
G1.getOutput();
```
To change the inputs to the gate, use the command:
```
G3.changeInputs(0, 1); // If you want to use integers

// or

G3.changeInputs(G1, G2); // If you want to use Gates
```
To print the truth table of a gate to the console, use:
```
G1.printTruthTable();
```

## Future
- Future updates with sequential circuits coming soon
- MUX81, Encoder83 and more integrated circuits coming soon
- Updates to logic gates coming soon


