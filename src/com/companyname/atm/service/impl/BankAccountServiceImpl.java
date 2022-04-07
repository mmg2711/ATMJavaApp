package com.companyname.atm.service.impl;

import java.math.BigDecimal;

import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.datastore.BankAccountDataStore;
import com.companyname.atm.model.BankAccount;
import com.companyname.atm.service.BankAccountService;
import com.companyname.atm.service.BankAccountServiceException;

/**
 * This class represents method calls that interacts with Bank Account Data Store
 * @author Michael McGill
 */
public class BankAccountServiceImpl implements BankAccountService {
	final String CLASSNAME = this.getClass().getName();
	private static BankAccountDataStore bankAccountDataStore;
	
	// This Constructor mimicks pulling data from a data source
	public BankAccountServiceImpl() {
		bankAccountDataStore = BankAccountDataStore.getInstance();
	}
	
	/**
	 * Check to see does the Account exist
	 * @param accountNumber
	 * @throws BankAccountServiceException
	 */
	@Override
	public boolean isAccountValid(String accountNumber) throws BankAccountServiceException {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");
		
		boolean success = false;
		success = bankAccountDataStore.getBankAccounts().containsKey(accountNumber);
		// If the Account was not found in the DataStore
		if(!success)
			// Invalid Account 
			throw new BankAccountServiceException(ErrorValues.ACCOUNT_NUMBER_NOT_FOUND_ERROR_CODE);
		System.out.println(CLASSNAME + "." + METHODNAME + ": " + success + "\n");
		return success;
	}

	/** Check to see if the PIN matches for this Account - This method should never be executed before ensuring the Account is valid
	 * @param accountNumber
	 * @param pinCode
	 * @throws BankAccountServiceException
	 */
	@Override
	public boolean isPINValid(String accountNumber, int pinCode) throws BankAccountServiceException {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");
		
		boolean success = false;
		// Get our BankAccount
		BankAccount bankAccount = bankAccountDataStore.getBankAccounts().get(accountNumber);
		if (bankAccount.getAccountNumber().equals(accountNumber) 
				&& (bankAccount.getPinCode() == pinCode))
			success = true;

		// If a match could not be found
		if(!success)
			// Bad PIN
			throw new BankAccountServiceException(ErrorValues.INCORRECT_PIN_ERROR_CODE);
		
		System.out.println(CLASSNAME + "." + METHODNAME + ": " + success + "\n");
		return success;
	}
	
	/** Return a bank Account Object based on its accountNumber and PIN Code, if it is indeed a valid bank Account
	 * @param accountNumber
	 * @param pinCode
	 */
	@Override
	public BankAccount getValidBankAccount(String accountNumber, int pinCode) throws BankAccountServiceException {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering\n");
		
		// Create a BankAccount object
		BankAccount bankAccount = null;
		
		// Check the Account is valid the PIN is correct
		if(isAccountValid(accountNumber) && isPINValid(accountNumber, pinCode)) {
			// Get our BankAccount
			bankAccount = bankAccountDataStore.getBankAccounts().get(accountNumber);	
		}
		
		System.out.println(CLASSNAME + "." + METHODNAME + ":" + " Returning a Bank Account\n");
		return bankAccount;	
	}

	/**
	 * Update the balance of a <code>BankAccoint</code> after a withdrawal transaction
	 * @param bankAccount
	 * @param withdrawalAmount
	 */
	@Override
	public void updateAfterWithdrawal(BankAccount bankAccount, BigDecimal withdrawalAmount)
			throws BankAccountServiceException {
		BigDecimal currentBalance = bankAccount.getBalance();
		BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
		bankAccount.setBalance(newBalance);
		
	}
}
