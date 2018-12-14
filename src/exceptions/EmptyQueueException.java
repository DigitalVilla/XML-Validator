package exceptions;

public class EmptyQueueException extends Exception {
	public EmptyQueueException() {
		super();
	}

	public EmptyQueueException(String message) {
		super(message);
	}
}
