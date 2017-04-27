package exceptions;

public class InPastException extends Exception{

	private static final long serialVersionUID = 1L;
	private final static String MESSAGE = "The time sent is not the present or future.";

	public InPastException() {
		super(MESSAGE);
	}
	
}
