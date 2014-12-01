package com.rfvallina.utils.exception;

import com.rfvallina.utils.exception.ExceptionMessage;

public enum GenericExceptionMessage implements ExceptionMessage {

	//Generic error codes must be in the hundred of 100
	CANNOT_PARSE_DATE(100, "Date cannot be parsed"),
	CACHE_VALUE_TYPE_NOT_PERMITTED(101, "Cache value type not permitted. Only string or integer values are allowed"),
	CACHE_NOT_INITIALIZED(102, "Cache system could not be initialized");

	int code;
	String description;

	GenericExceptionMessage(final int code, final String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
	
}