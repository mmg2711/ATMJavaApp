package com.companyname.atm.handler;

import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.model.ExceptionDetails;
import com.companyname.atm.model.ResponseDetails;

/** Abstract class that will be subclassed by more granular Handler Exception classes
 * Created this to share common feature of building ResponseDetails object
 * 
 * @author Michael McGill
 *
 */

public abstract class HandlerException extends Exception {
	
	private int code;
	private ResponseDetails exceptionDetails;
	
	/**
	 * Constructor
	 * 
	 * @param messageCode
	 */
	public HandlerException(int messageCode) {
		super(ErrorValues.getErrorMessage(messageCode));
		this.code = messageCode;
		exceptionDetails = new ExceptionDetails();
	}
	
	/**
	 * Return the code
	 * 
	 * @return a code that represents the exception
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 *  We will use details recorded about the exception and create a ResponseDetails object.
	 *  This will be later modeled to JSON to provide meaningful response to the end user.
	 * 
	 * @return	An object that models the details about this Exception
	 */
	public ResponseDetails getExceptionDetails() {
		exceptionDetails.setDetails(ErrorValues.getErrorMessage(code));
		return exceptionDetails;
	}
	
}
