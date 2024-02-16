package exceptionsPackage;

public class InvalidTableLengthException extends Exception{

private static final long serialVersionUID = 1L;
	
	public InvalidTableLengthException() {
		super("Invalid Number of Cells in Truth Table");
	}
	public InvalidTableLengthException(String msg) {
		super(msg);
	}
	
}
