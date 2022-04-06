package com.companyname.atm.service;

import com.companyname.atm.constants.ErrorValues;

/** Exception class to handle error with the Bank Accouunt Service
 * 
 * @author Michael McGill
 *
 */

public class NotesDispenseServiceException extends Exception {
	int code;
	
	public NotesDispenseServiceException(int messageCode) {
		super(ErrorValues.getErrorMessage(messageCode));
		this.code = messageCode;
	}
	
	public int getCode() {
		return code;
	}
}