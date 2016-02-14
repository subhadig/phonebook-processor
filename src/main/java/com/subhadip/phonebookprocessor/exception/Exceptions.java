package com.subhadip.phonebookprocessor.exception;

public enum Exceptions {

	FILE_NOT_FOUND_1("The file");
	
	private String message;
	
	private Exceptions(String message) {
		this.message = message;
	}
	
	public Exception get() {
		return new Exception(message);
	}
	
	public PhonebookProcessorException getBusinessException() {
		return new PhonebookProcessorException(message);
	}
}
