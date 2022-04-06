package com.companyname.atm.model;

import java.math.BigDecimal;

import com.companyname.atm.constants.ErrorValues;

/**
 * This class acts as a placeholder for the details required to display
 * some context for an exception, which can be mapped to a JSON Response.
 * 
 * @author Michael McGill
 *
 */

public class ExceptionDetails extends ResponseDetails {
	
	// Constructor
	public ExceptionDetails() {
		super();
	}
	
	/**
	 * Add appropriate response details to our Map
	 * will do this as key/value pair - later translated to JSON
	 * @param bankAccount
	 */
	@Override
	public void setDetails(Object ... object) {
		String errorMessage = object[0].toString();
		responseDetails.put(ErrorValues.ERROR_MESSAGE, errorMessage);
	}

	/**
	 * For Exceptions we wont use this
	 */
	public void setLatestBalance(BigDecimal latestBalance) {
		
	}	
	
}
