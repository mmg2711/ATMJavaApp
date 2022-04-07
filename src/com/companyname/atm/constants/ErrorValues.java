package com.companyname.atm.constants;

import java.util.HashMap;
import java.util.Map;

import com.companyname.atm.datastore.BankAccountDataStore;

public final class ErrorValues {
	
	// Define some Error Codes for different outputs - these are just some custom ones I created
	public static final int GENERAL_ERROR_CODE = 420;
	public static final int SERVICE_ERROR_CODE = 421;
	public static final int ACCOUNT_NUMBER_NOT_FOUND_ERROR_CODE = 422;
	public static final int BAD_PIN_CHARS_ERROR_CODE = 430;
	public static final int INCORRECT_PIN_ERROR_CODE = 431;
	public static final int BAD_WITHDRAWAL_CHARS_ERROR_CODE = 440;
	public static final int NEGATIVE_WITHDRAWAL_INPUT_AMOUNT_ERROR = 441;
	public static final int INCORRECT_WITHDRAWAL_AMOUNT_ERROR_CODE = 442;
	public static final int INSUFFICIENT_FUNDS_ERROR_CODE = 443;
	public static final int DENOMINATIONS_DISPENSE_CODE = 450;
	public static final int DENOMINATIONS_INSUFFICIENT_INVENTORY_CODE = 450;
	
	// General return messages
	public static final String ERROR_MESSAGE = "error";
	
	// Create a HashMap with a few Error Codes to help different responses
	private static Map<Integer, String> errorCodes = new HashMap<>();
	
	static {
		errorCodes.put(ErrorValues.GENERAL_ERROR_CODE, "Unknown error has occurred. Try again later.");
		errorCodes.put(ErrorValues.SERVICE_ERROR_CODE, "There was an error with this service!");
		errorCodes.put(ErrorValues.ACCOUNT_NUMBER_NOT_FOUND_ERROR_CODE, "No Account with the ID was found!");
		errorCodes.put(ErrorValues.BAD_PIN_CHARS_ERROR_CODE, "Check your PIN Code characters are numeric!");
		errorCodes.put(ErrorValues.INCORRECT_PIN_ERROR_CODE, "Pin Code in invalid!");
		errorCodes.put(ErrorValues.BAD_WITHDRAWAL_CHARS_ERROR_CODE, "Check the characters of your withdrawal amount.");
		errorCodes.put(ErrorValues.NEGATIVE_WITHDRAWAL_INPUT_AMOUNT_ERROR, "Please enter a positive value.");
		errorCodes.put(ErrorValues.INCORRECT_WITHDRAWAL_AMOUNT_ERROR_CODE, "Withdrawal amount is incorrect. Please input a value in multiples of 10.");
		errorCodes.put(ErrorValues.INSUFFICIENT_FUNDS_ERROR_CODE, "Insufficient funds to withdraw this amount!.");
		errorCodes.put(ErrorValues.DENOMINATIONS_DISPENSE_CODE, "Problem trying to dispense Notes!.");
		errorCodes.put(ErrorValues.DENOMINATIONS_INSUFFICIENT_INVENTORY_CODE, "Not enough notes in this ATM to cover the withdrawal!.");
	}
	
	public static String getErrorMessage(int code) {
		return errorCodes.get(code);
	}

}
