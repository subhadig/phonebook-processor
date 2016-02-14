package com.subhadip.phonebookprocessor.exception;

public class PhonebookProcessorException extends RuntimeException {

	public PhonebookProcessorException() {
		super();
	}

	public PhonebookProcessorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhonebookProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhonebookProcessorException(String message) {
		super(message);
	}

	public PhonebookProcessorException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6765370484807842936L;

}
