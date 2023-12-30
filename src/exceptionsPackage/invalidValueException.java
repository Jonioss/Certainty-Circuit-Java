package exceptionsPackage;

public class invalidValueException extends Exception{
	private static final long serialVersionUID = 1L;

	public invalidValueException() {
		super("Invalid value");
	}
}
