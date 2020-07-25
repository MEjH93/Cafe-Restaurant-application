package com.comtrade.exceptions;

public class ExceptionSQL extends Exception{

	    public ExceptionSQL() {
	    }

	    public ExceptionSQL(String message) {
	        super(message);
	    }

	    public ExceptionSQL(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ExceptionSQL(Throwable cause) {
	        super(cause);
	    }

	    public ExceptionSQL(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
	}

