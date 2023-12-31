package FlipFlops;

public class Clock {
	private int period; // Period of clock in milliseconds
	private int out;
	
	public Clock() {
		this(100);
	}
	public Clock(int Period) {
		this.period = Period;
		out = 1; // Starts positive
	}
	
	public int getOutput(int currentTime) {
		out = ((currentTime / period) % 2 == 0) ? 1 : 0;
		return out;
	}
	
}
