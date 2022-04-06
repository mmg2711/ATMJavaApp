package com.companyname.atm.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.companyname.atm.constants.AppValues;

/**
 * This abstract class acts as a placeholder for some default details required
 * to display that can be mapped to all JSON responses.
 * @author Michael McGill
 */
public abstract class ResponseDetails {
	
	protected boolean success;
	protected Map<String, Object> responseDetails;
	
	// Constructor - initialise the Map of messages
	public ResponseDetails() {
		responseDetails = new LinkedHashMap();
	}
	
	/**
	 * Is this Response Object going to be a success
	 * @return a boolean value
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/** Set a flag to indicate this response object should be built succesfully
	 * @param success
	 */
	public void setSuccess(boolean success) {
		// If a success call is passed, also set the Return Code
		this.success = success;
	}

	/**
	 * Return the map containing our output messages
	 * @return a <code>responseDetails</code> object.
	 */
	public Map<String, Object> getDetails() {
		return responseDetails;
	}

	
/*	*//** Set the latest Balance - as a big decimal
	 * @param latestBalance
	 *//*
	public abstract void setLatestBalance(BigDecimal latestBalance);*/
	
	/**
	 * Implementing class will decide how to populate a more custom response
	 * and pass in as many objects as it wants to get the details it needs
	 * @param object
	 */
	public abstract void setDetails(Object ... varargs);
	
}
