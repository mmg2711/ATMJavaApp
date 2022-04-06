package com.companyname.atm.model;

import java.math.BigDecimal;

import com.companyname.atm.constants.AppValues;

/**
 * This class acts as a placeholder for the details required to display
 * an Account Summary to the end user, including balance and amount available
 * to withdraw, which can be mapped to JSON.
 * 
 * @author Michael McGill
 *
 */

public class AccountSummaryDetails extends ResponseDetails {
	
	// Constructor
	public AccountSummaryDetails() {
		super();
	}
	
	/**
	 * Add appropriate response details to our Map
	 * will do this as key/value pair - later translated to JSON
	 * @param bankAccount
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws ClassCastException
	 */
	@Override
	public void setDetails(Object ... object) throws ArrayIndexOutOfBoundsException, ClassCastException {
		// Cast our object to a <code>BankAccount</code>
		BankAccount bankAccount = (BankAccount)object[0];
		BigDecimal bankBalance = bankAccount.getBalance();
		BigDecimal withdrawable = bankAccount.getWithdrawableAmount();
		responseDetails.put(AppValues.BALANCE_DESC, bankBalance);
		responseDetails.put(AppValues.WITHDRAWABLE_DESC, withdrawable);
	}	
	
}
