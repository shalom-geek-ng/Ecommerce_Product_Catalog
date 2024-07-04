package com.PrunnyProductCatalog.EcommerceProductCatalog.Exceptions;

public class ResponseNotFoundException extends RuntimeException{
	public ResponseNotFoundException(String message) {
        super(message);
    }
	 public ResponseNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	 protected ResponseNotFoundException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
super(message, cause, enableSuppression, writableStackTrace);
}
}
