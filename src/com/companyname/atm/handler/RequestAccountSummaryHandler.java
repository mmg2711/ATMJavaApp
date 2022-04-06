package com.companyname.atm.handler;

import javax.servlet.http.HttpServletRequest;

import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.model.AccountSummaryDetails;
import com.companyname.atm.model.BankAccount;
import com.companyname.atm.model.ResponseDetails;
import com.companyname.atm.service.BankAccountService;
import com.companyname.atm.service.BankAccountServiceException;
import com.companyname.atm.service.impl.BankAccountServiceImpl;

/**
 * This class implements a flow to get the summary of a Customer's Bank Account Details and set them on an AccountSummaryDetails object
 * @author Michael McGill
 *
 */
public class RequestAccountSummaryHandler {
	final String CLASSNAME = this.getClass().getName();
	
	/**
	 * This method populates the <code>ResponseDetails</code> object based on the data returned from our Data Source.
	 * The <code>ResponseDetails</code> can subsequently be queried to determine an appropriate JSON message to construct
	 * @param request
	 * @return an object that encapsulates the Response Details
	 */
	public ResponseDetails executeAccountSummaryRequest(HttpServletRequest request) throws RequestAccountSummaryHandlerException {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");
		
		// Get the accountNumber and pinCode from the Request Parameters
		String accountNumber = request.getParameter("accountNumber");
		String pinCodeString = request.getParameter("pinCode");
	
		ResponseDetails summaryDetails = new AccountSummaryDetails();
		BankAccountService service = new BankAccountServiceImpl();
		int pinCode;
		
		try {
			// First, lets do some validation - we will check the PIN is numeric
			try {
				pinCode = Integer.parseInt(pinCodeString);
			} catch(NumberFormatException ex) {
				throw new RequestAccountSummaryHandlerException(ErrorValues.BAD_PIN_CHARS_ERROR_CODE);
			}
			
			System.out.println(CLASSNAME + "." + METHODNAME + ":" + " build a response object\n");
			// Retrieve the BankAccount Object
			BankAccount bankAccount =  service.getValidBankAccount(accountNumber, pinCode);
			
			// Set the fields we want on the BankAccountSummaryResponse object
			summaryDetails.setSuccess(true);
			summaryDetails.setDetails(bankAccount);
			
		} catch (BankAccountServiceException ex) { // Service Exception
			throw new RequestAccountSummaryHandlerException(ex.getCode());
		
		}
		
		return summaryDetails;
	}
}
