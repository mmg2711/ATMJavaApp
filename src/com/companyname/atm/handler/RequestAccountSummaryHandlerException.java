package com.companyname.atm.handler;

/** Exception class to handle errors thrown by
 *  <code>BankAccountSummaryHandler</code>
 * 
 * @author Michael McGill
 *
 */

public class RequestAccountSummaryHandlerException extends HandlerException {
	
	// Constructor
	public RequestAccountSummaryHandlerException(int messageCode) {
		super(messageCode);
	}
}
