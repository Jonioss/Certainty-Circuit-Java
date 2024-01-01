package guiPackage;

import java.awt.Color;
import javax.swing.JFrame;
import gatesPackage.Gate;

public class Wave extends JFrame{
	
	private static final long serialVersionUID = -1929616008267398828L;
	
	WavePanel wp = new WavePanel();
	
	public Wave(Gate G) {
		this(800, 500, G);
	}
	public Wave(int width, int height, Gate G) {
		
		wp.setGate(G);
		this.add(wp);
		
		this.setTitle("Wave");
		//this.setUndecorated(true);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.BLACK);
	}
	
	public void setWaveColors(Color inColor, Color outColor) {
		wp.setColors(inColor, outColor);
	}
	public void setInputs(int[] inputs) {
		wp.setInputs(inputs);
	}
	public void setDimensions(int pady, int time) {
		wp.setGrid(pady, time);
	}
	public void setName(String newName) {
		this.setTitle(newName);
	}
	public void showWave() {
		this.setVisible(true);
	}
	
}
