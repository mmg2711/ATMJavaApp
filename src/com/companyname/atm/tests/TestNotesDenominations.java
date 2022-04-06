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
class TestNotesDenominations {
	// Create service variables
	static NotesDispenseService notesDispenseService = null;
	
	// Variables for Data Stores
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

	// Check the expected denominations are given out
	@Test
	void testGetNotesDenominations() {
		BankAccount bankAccount = null;
		Denominations denominations = null;
		
		try {
			// Call service to decide in what denominations the notes should be dispensed
			denominations = notesDispenseService.getNotesDenominations(new BigDecimal("335"));
			
		} catch(NotesDispenseServiceException ex) {
			fail("Exception thrown");
		} catch(Exception ex) {
			fail("Exception thrown");
		}
		
		// Create an object with the expected Denominations
		Denominations expected = new Denominations();
		expected.setNumberOf50s(6);
		expected.setNumberOf20s(1);
		expected.setNumberOf10s(1);
		expected.setNumberOf5s(1);
		
		// Check are they equal - equals() method in Denomimations overloaded to support this comparison
		assertEquals(expected, denominations);
	}
	
}
