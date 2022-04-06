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
class TestNotesInventory {
	// Create service variables
	static NotesDispenseService notesDispenseService = null;
	
	// Variables for Data Stores
	static BankAccountDataStore bankAccountDataStore = null;
	static NotesInventoryDataStore notesInventoryDataStore = null;
	
	// Variable we can reuse for withdrawal transaction
	BigDecimal withdrawalAmount = null;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			// Create Account Summary Service object
			notesDispenseService = new NotesDispenseServiceImpl();
			
			// Retrieve the Data Stores
			notesInventoryDataStore = NotesInventoryDataStore.getInstance();
			
			// Create BigDecimal with 335
			withdrawalAmount = new BigDecimal(335);
			
		} catch (Exception e) {
			fail("Could not create instance of AccountSummaryService");
		}
	}
	
	// Check the Inventory in the Data Store after an amount has been withdrawn
	@Test
	void testNotesInventoryCount() {
		try {
			// Call service to decide in what denominations the notes should be dispensed
			notesDispenseService.getNotesDenominations(withdrawalAmount);
			
		} catch(NotesDispenseServiceException ex) {
			fail("Exception thrown");
		} catch(Exception ex) {
			fail("Exception thrown");
		}
		
		// This should leave us with (4 x €50) (29 x €20) (29 x €10) and (19 x €5)
		int expected50s = 4, expected20s = 29, expected10s = 29, expected5s = 19;
		
		// Check the expected against what is in the Data Store
		assertEquals(expected50s, notesInventoryDataStore.getBankNote("50").getNumberAvailable());
		assertEquals(expected20s, notesInventoryDataStore.getBankNote("20").getNumberAvailable());
		assertEquals(expected10s, notesInventoryDataStore.getBankNote("10").getNumberAvailable());
		assertEquals(expected5s, notesInventoryDataStore.getBankNote("5").getNumberAvailable());	
	}

	
}
