package guiPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import FlipFlops.Clock;
import exceptionsPackage.invalidValueException;
import gatesPackage.Gate;

public class WavePanel extends JPanel{

	private static final long serialVersionUID = 1047256441641923933L;
	
	Gate G;
	Object[] inputs = {0, 1};
	int inputsStop = 0; // Y position at which the inputs stop
	
	int time = 0;
	
	Color inputColor = Color.BLUE;
	Color outputColor = Color.GREEN;
	
	int pady = 50;
	int period = 50;
	
	protected void setGate(Gate newG) {
		G = newG;
	}
	
	protected void setInputs(Object[] newInputs) {
		inputs = newInputs;
	}
	
	protected void setColors(Color inColor, Color outColor) {
		inputColor = inColor;
		outputColor = outColor;
	}
	
	protected void setGrid(int newPadY, int newPeriod) {
		pady = newPadY;
		period = newPeriod;
	}
	
	public void paintComponent(Graphics g){
		
		paintGrid(g);
		try {
			paintInputs(g);
			paintGateOutput(g);
		} catch (invalidValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void paintGrid(Graphics g) {
		
		g.setColor(Color.gray);
		
		for(int i = 0; i < getHeight(); i = i + pady) {
			g.drawLine(0, i, getWidth(), i);
		}
		
		for(int i = 0; i < getWidth(); i = i + period) {
			g.drawLine(i, 0, i, getHeight());
		}
	}
	
	private void paintInputs(Graphics g) throws invalidValueException {
		
		g.setColor(inputColor);
		
		int middle, high, low=0, prev, current;
		
		for(int i = 0; i < inputs.length; i++) {
			
			middle = (3*i + 2) * pady;
			high = middle - pady;
			low = middle + pady;
			
			prev = middle;
			current = middle;
			
			for(int j = 0; j < getWidth(); j = j + period) {
				
				if(inputs[i] instanceof Integer) {
					if((int)inputs[i] == 0) {
						current = low;
					}
					else {
						current = high;
					}
				}
				else if(inputs[i] instanceof Clock) {
					if(((Clock)inputs[i]).getOutput(time) == 0) {
						current = low;
					}
					else {
						current = high;
					}	
				}
				else if(inputs[i] instanceof Gate) {
					if(((Gate)inputs[i]).getOutput() == 0) {
						current = low;
					}
					else {
						current = high;
					}
				}
				
				if(current != prev) {
					g.drawLine(j, current, j, prev);
				}
				g.drawLine(j, current, j+period, current);
				prev = current;
				time += period;
			}
		}
		inputsStop = low + 2*pady;
		time = 0;
	}
	
	private void paintGateOutput(Graphics g) throws invalidValueException {
		int middle = inputsStop;
		int high = middle - pady;
		int low = middle + pady;
		
		int prev = middle;
		int current = middle;
		
		time = 0;
		for(int i = 0; i < getWidth(); i = i + period) {
			
			G.setTime(time);
			
			// Set color for the wave
			g.setColor(outputColor);
			
			// Check output
			if(G.getOutput() == 1) {
				current = high;
			}
			else if(G.getOutput() == 0) {
				current = low;
			}
			else {
				g.setColor(Color.RED);
				g.drawLine(i, middle, i+period, middle);
			}
			
			if(current != prev) {
				g.drawLine(i, current, i, prev);
			}
			
			// Finally, draw the line
			g.drawLine(i, current, i+period, current);
			prev = current;
			time += period;
		}
		time = 0;
	}
	
}
