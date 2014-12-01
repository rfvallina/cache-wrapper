package com.rfvallina.utils.exception;


public class ApplicationException extends Exception {
	private static final long serialVersionUID = 5167488042797326347L;
	
	private ExceptionMessage error;
	
	public ApplicationException(final ExceptionMessage error) {
		super();
		System.out.println(error.getDescription());
		this.error = error;
	}
	
	public ApplicationException(final ExceptionMessage error, final String msg) {
		super(msg);
		System.out.println(msg + " : " + error.getDescription());
		this.error = error;
	}
	
	public ApplicationException(final ExceptionMessage error, final Throwable t) {
		super(t);
		System.out.println(error.getDescription());
		this.error = error;		
	}
	
	public ApplicationException(final ExceptionMessage error, final String msg, final Throwable t) {
		super(msg, t);
		System.out.println(msg + " : " + error.getDescription());
		this.error = error;		
	}

	public ExceptionMessage getError() {
		return error;
	}
	
	
}
