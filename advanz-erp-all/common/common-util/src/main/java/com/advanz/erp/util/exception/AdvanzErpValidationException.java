package com.advanz.erp.util.exception;

public class AdvanzErpValidationException  extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -7063721251190684323L;

	public AdvanzErpValidationException(String message) {
	        super(message);
	    }

	    public AdvanzErpValidationException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
