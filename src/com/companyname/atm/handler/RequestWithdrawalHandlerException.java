package com.companyname.atm.handler;

/** Exception class to handle errors thrown by
 *  <code>BankAccountSummaryHandler</code>
 * 
 * @author Michael McGill
 *
 */

public class RequestWithdrawalHandlerException extends HandlerException {
	
	// Constructor
	public RequestWithdrawalHandlerException(int messageCode) {
		super(messageCode);
	}
}
