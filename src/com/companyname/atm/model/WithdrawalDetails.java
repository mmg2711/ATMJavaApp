package com.companyname.atm.model;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import com.companyname.atm.constants.AppValues;

/**
 * This class acts as a placeholder for the details required to display
 * withdrawal information and the latest balance
 * which can be mapped to JSON.
 * @author Michael McGill
 */

public class WithdrawalDetails extends ResponseDetails {
	final String CLASSNAME = this.getClass().getName();
	
	// Constructor
	public WithdrawalDetails() {
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
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");		
		
		// cast our first object to a <code>Denominations</code>
		Denominations denominations = (Denominations)object[0];
		
		// cast our second object to a <code>BankAccount</code> and then gets it Balance
		String bankBalance = object[1].toString();
		
		responseDetails.put(AppValues.BALANCE_DESC, bankBalance);
		responseDetails.put(AppValues.NOTE_50_DESC, denominations.getNumberOf50s());
		responseDetails.put(AppValues.NOTE_20_DESC, denominations.getNumberOf20s());
		responseDetails.put(AppValues.NOTE_10_DESC, denominations.getNumberOf10s());
		responseDetails.put(AppValues.NOTE_05_DESC, denominations.getNumberOf5s());
	}	
	
}
