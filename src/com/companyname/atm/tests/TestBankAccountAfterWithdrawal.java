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
class TestBankAccountAfterWithdrawal {
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
	
	// Test the balance of a Bank Account after a withdrawal
	@Test
	void testValidAccount() {
		BankAccount bankAccount = null;
		try {
			// Get a valid bankAccount
			bankAccount = bankAccountService.getValidBankAccount("123456789", 1234);
			
			// There is 800 in it to start - remove the withdrawal amount
			bankAccountService.updateAfterWithdrawal(bankAccount, withdrawalAmount);
		} catch (BankAccountServiceException e) {
			fail("The Bank Account returned from the Service should match the one in Data Source");
		}
		
		// It should leav us with 465, check it against the balance on the BankAccount from the Data Stoe
		BigDecimal expected = new BigDecimal(465);
		assertEquals(expected, bankAccountDataStore.getBankAccount("123456789").getBalance());
	}

	
}
