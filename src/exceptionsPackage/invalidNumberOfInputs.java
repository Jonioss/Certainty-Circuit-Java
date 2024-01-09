package exceptionsPackage;

public class invalidNumberOfInputs extends Exception{

	private static final long serialVersionUID = 1L;
	
	public invalidNumberOfInputs() {
		super("Invalid Number of Inputs");
	}
	public invalidNumberOfInputs(String msg) {
		super(msg);
	}

}
