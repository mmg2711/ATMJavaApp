package com.companyname.atm.datastore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.companyname.atm.model.BankAccount;

/**
 * Example DataStore class that provides access to mocked up Data.
 * Acting as if its a Database source.
 * In the real world we could replace this class with one that connects to a Database.
 * Implementing as Singleton so data persists throughout a session.
 * 
 * @author Michael McGill
 */

public class BankAccountDataStore {

	// Map of Account Numbers to Bank Account instances.
	private Map<String, BankAccount> bankAccountMap = new HashMap<>();

	// This class is a singleton and should not be instantiated directly!
	private static BankAccountDataStore instance = new BankAccountDataStore();

	/**
	 * Get the instance of this Data store. 
	 * Should only ever be one.
	 * @return a data store instance
	 */
	public static BankAccountDataStore getInstance() {
		return instance;
	}

	// Private constructor so people know to use the getInstance() function instead.
	private BankAccountDataStore() {
		// data
		bankAccountMap.put("123456789", new BankAccount("123456789", 1234, new BigDecimal("800"), new BigDecimal(200)));
		bankAccountMap.put("987654321", new BankAccount("987654321", 4321, new BigDecimal("1230"), new BigDecimal("150")));
	}

	/**
	 * Get a BankAccount object - we are using accountNumber as the key
	 * @param accountNumber
	 * @return BankAccount
	 */
	public BankAccount getBankAccount(String accountNumber) {
		return bankAccountMap.get(accountNumber);
	}

	/**
	 * Add a new Bank Account to the Map
	 * @param bankAccount
	 */
	public void putBankAccount(BankAccount bankAccount) {
		bankAccountMap.put(bankAccount.getAccountNumber(), bankAccount);
	}

	/**
	 * Retrieve all Bank Accounts
	 * @return a list of Bank Accounts
	 */
	public Map<String, BankAccount> getBankAccounts() {
		return bankAccountMap;
	}
}