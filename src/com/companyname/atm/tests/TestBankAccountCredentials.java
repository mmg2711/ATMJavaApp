package com.companyname.atm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.datastore.BankAccountDataStore;
import com.companyname.atm.datastore.NotesInventoryDataStore;
import com.companyname.atm.model.BankAccount;
import com.companyname.atm.model.Denominations;
import com.companyname.atm.service.BankAccountService;
import com.companyname.atm.service.BankAccountServiceException;
import com.companyname.atm.service.NotesDispenseService;
import com.companyname.atm.service.NotesDispenseServiceException;
import com.companyname.atm.service.impl.BankAccountServiceImpl;
import com.companyname.atm.service.impl.NotesDispenseServiceImpl;

/**
 * Class to exercise some unit tests
 * @author Michael McGill
 *
 */
class TestBankAccountCredentials {
	// Create service variables
	static BankAccountService bankAccountService = null;
	
	// Variables for Data Stores
	static BankAccountDataStore bankAccountDataStore = null;
	
	// Variable we can reuse for withdrawal transaction
	BigDecimal withdrawalAmount = null;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			// Create Account Summary Service object
			bankAccountService = new BankAccountServiceImpl();
			
			// Retrieve the Data Stores
			bankAccountDataStore = BankAccountDataStore.getInstance();
			
			// Create BigDecimal with 335
			withdrawalAmount = new BigDecimal(335);
			
		} catch (Exception e) {
			fail("Could not create instance of AccountSummaryService");
		}
	}
	
	// Test a valid Bank Account
	@Test
	void testValidAccount() {	
		try {
			// Try to retrieve a <code>BankAccount</code> and see if it matches the same one in the data Source
			assertEquals(bankAccountDataStore.getBankAccount("123456789"), bankAccountService.getValidBankAccount("123456789", 1234));
		} catch (BankAccountServiceException e) {
			fail("The Bank Account returned from the Service should match the one in Data Source");
		}
	}
	
	// Test custom exception for Invalid Account
	@Test
	void testInvalidAccount() {
		// Passing it an Account Number known not to exist
		BankAccountServiceException thrown = assertThrows(BankAccountServiceException.class, () -> {
			bankAccountService.getValidBankAccount("11223344", 1234);
		});

		assertTrue(thrown.getMessage().contains(ErrorValues.getErrorMessage(ErrorValues.ACCOUNT_NUMBER_NOT_FOUND_ERROR_CODE)));
	}
	
	// Test custom exception for Invalid PIN
	@Test
	void testInvalidPIN() {
		// Passing it a valid Account but an incorrect PIN
		BankAccountServiceException thrown = assertThrows(BankAccountServiceException.class, () -> {
			bankAccountService.getValidBankAccount("123456789", 4321);
		});

		assertTrue(thrown.getMessage().contains(ErrorValues.getErrorMessage(ErrorValues.INCORRECT_PIN_ERROR_CODE)));
	}
	
}
