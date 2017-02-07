package com.advanz.erp.util.exception;

public class AdvanzErpGenericException  extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -7063721251190684323L;

	public AdvanzErpGenericException(String message) {
	        super(message);
	    }

	    public AdvanzErpGenericException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
