package com.companyname.atm.handler;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.model.BankAccount;
import com.companyname.atm.model.Denominations;
import com.companyname.atm.model.ResponseDetails;
import com.companyname.atm.model.WithdrawalDetails;
import com.companyname.atm.service.BankAccountService;
import com.companyname.atm.service.BankAccountServiceException;
import com.companyname.atm.service.NotesDispenseService;
import com.companyname.atm.service.NotesDispenseServiceException;
import com.companyname.atm.service.impl.BankAccountServiceImpl;
import com.companyname.atm.service.impl.NotesDispenseServiceImpl;

/**
 * This class implements a flow to withdraw money from a Customer's Account and advise them of how the amount is being dispensed.
 * @author Michael McGill
 */
public class RequestWithdrawalHandler {
	final String CLASSNAME = this.getClass().getName();
	
	/**
	 * This method populates the <code>ResponseDetails</code> object based on the data returned from our Data Source.
	 * The <code>ResponseDetails</code> can subsequently be queried to determine an appropriate JSON message to construct
	 * @param request
	 * @return an object that encapsulates the Response Details
	 */
	//public ResponseDetails executeWithdrawal(String accountNumber, String pinCodeString, String withdrawalAmountString) throws RequestWithdrawalHandlerException {
	public ResponseDetails executeWithdrawal(HttpServletRequest request) throws RequestWithdrawalHandlerException {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");
		
		// Get the accountNumber and pinCode from the Request Parameters
		String accountNumber = request.getParameter("accountNumber");
		String pinCodeString = request.getParameter("pinCode");
		String withdrawalAmountString = request.getParameter("withdrawal");
		
		ResponseDetails withdrawalDetails = new WithdrawalDetails();
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		NotesDispenseService withdrawService = new NotesDispenseServiceImpl();
		BigDecimal withdrawalAmount;
		int pinCode;
		
		try {
			// First, lets do some validation - we will check the PIN is numeric, we will use the BankAccountService for this
			try {
				pinCode = Integer.parseInt(pinCodeString);
			} catch(NumberFormatException ex) {
				throw new RequestWithdrawalHandlerException(ErrorValues.BAD_PIN_CHARS_ERROR_CODE);
			}
			
			// Lets try to retrieve a valid <code>BankAccount</code> object
			BankAccount bankAccount =  bankAccountService.getValidBankAccount(accountNumber, pinCode);
			
			// Then, we will check the amountToWitdraw is numeric
			try {
				withdrawalAmount = new BigDecimal(withdrawalAmountString);
			} catch(NumberFormatException ex) {
				throw new RequestWithdrawalHandlerException(ErrorValues.BAD_WITHDRAWAL_CHARS_ERROR_CODE);
			}
			
			// Make sure its a positive withdrawal amount
			if (withdrawalAmount.compareTo(BigDecimal.ZERO) < 0 ) {
				throw new RequestWithdrawalHandlerException(ErrorValues.NEGATIVE_WITHDRAWAL_INPUT_AMOUNT_ERROR);
			}
			
			// If we have got here, we will check its a valid withdrawable amount - ie it must be a value in multiples of 5
			if(withdrawalAmount.remainder(new BigDecimal(5)).compareTo(BigDecimal.ZERO) != 0 ) {
				throw new RequestWithdrawalHandlerException(ErrorValues.INCORRECT_WITHDRAWAL_AMOUNT_ERROR_CODE);
			}
			
			// Check the amountToWithdraw does not exceed the maximum that can be taken from this account
			if(withdrawalAmount.compareTo(bankAccount.getWithdrawableAmount()) > 0) {
				throw new RequestWithdrawalHandlerException(ErrorValues.INSUFFICIENT_FUNDS_ERROR_CODE);
			}
						
			// At this point it should be good to complete the Transaction and update the Bank Account accordingly
			bankAccountService.updateAfterWithdrawal(bankAccount, withdrawalAmount);
			
			// Call service to decide in what denominations the notes should be dispensed
			Denominations denominations = withdrawService.getNotesDenominations(withdrawalAmount);
			
			// Create ResponseDetails object, initialise with the latest Balance and set some values
			withdrawalDetails.setSuccess(true);
			withdrawalDetails.setDetails(denominations, bankAccount.getBalance());
			
			System.out.println(CLASSNAME + "." + METHODNAME + ": Withdrawing " + withdrawalAmount + "\n");	
			
		} catch (BankAccountServiceException ex) { // Service Exception
			throw new RequestWithdrawalHandlerException(ex.getCode());	
			
		} catch (NotesDispenseServiceException ex) { // Service Exception
			throw new RequestWithdrawalHandlerException(ex.getCode());
			
		} catch(ClassCastException ex) {
			throw new RequestWithdrawalHandlerException(420);
			
		} catch(ArrayIndexOutOfBoundsException ex) {
			throw new RequestWithdrawalHandlerException(400);
			
		} catch(Exception ex) {
			throw new RequestWithdrawalHandlerException(400);
		}
		
		
		return withdrawalDetails;
	}
}
