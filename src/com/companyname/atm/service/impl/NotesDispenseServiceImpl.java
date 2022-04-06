package com.companyname.atm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.companyname.atm.constants.AppValues;
import com.companyname.atm.constants.ErrorValues;
import com.companyname.atm.datastore.NotesInventoryDataStore;
import com.companyname.atm.model.BankNote;
import com.companyname.atm.model.Denominations;
import com.companyname.atm.service.NotesDispenseService;
import com.companyname.atm.service.NotesDispenseServiceException;

//TODO Comment this class

public class NotesDispenseServiceImpl implements NotesDispenseService {
	final String CLASSNAME = this.getClass().getName();
	private static NotesInventoryDataStore notesInventoryDataStore;
	private Denominations denominations;
	private int dispensed50s, dispensed20s, dispensed10s, dispensed5s;
	
	// Variable to keep track of how much is left to dispense
	private BigDecimal dispenseRemainder;
	
	// A map returned to subscribers of this service containing a result
	private Map<String, Integer> denominationsMap;
	
	// This Constructor mimicks pulling data from a data source
	public NotesDispenseServiceImpl() {
		notesInventoryDataStore = NotesInventoryDataStore.getInstance();
		denominationsMap = new HashMap();
	}
	
	public Denominations getNotesDenominations(BigDecimal withdrawalAmount) throws NotesDispenseServiceException {
		try {
			// Initialise a Dominations object
			denominations = new Denominations();
			
			// Before we start dispensing assign the withdrawalAmount to the dispenseRemainder so we can track our denomination count
			dispenseRemainder = withdrawalAmount;
			
			// Start with the largest 50 - we will take this from the Data Store & set the result on the <code>Denominations</code> object
			dispensed50s = getDispensedBankNote(notesInventoryDataStore.getBankNote("50"));
			denominations.setNumberOf50s(dispensed50s);
			
			// Then, if applicable will dispense 20 denominations & set the result on the <code>Denominations</code> object
			dispensed20s = getDispensedBankNote(notesInventoryDataStore.getBankNote("20"));
			denominations.setNumberOf20s(dispensed20s);
			
			// Then, if applicable will dispense 10 denominations & set the result on the <code>Denominations</code> object
			dispensed10s = getDispensedBankNote(notesInventoryDataStore.getBankNote("10"));
			denominations.setNumberOf10s(dispensed10s);
			
			// Then, if applicable will dispense 5 denominations & set the result on the <code>Denominations</code> object
			dispensed5s = getDispensedBankNote(notesInventoryDataStore.getBankNote("5"));
			denominations.setNumberOf5s(dispensed5s);
			
		} catch (Exception ex) {
			throw new NotesDispenseServiceException(ErrorValues.DENOMINATIONS_DISPENSE_CODE);
		}

		return denominations;
		
	}
	
	/**
	 * Check if we can dispense each denomiation - starting with the largest
	 * @return the number of this notes we can dispense
	 */
	private int getDispensedBankNote(BankNote bankNote) {
		final String METHODNAME = new Object() {}.getClass().getEnclosingMethod().getName();
		System.out.println(CLASSNAME + "." + METHODNAME + ": Entering for BankNote " + bankNote.getNoteValue() + "\n");
		int dispensedBankNotes = 0;
		
		
		// Check if there is money left to dispense
		if(dispenseRemainder.compareTo(BigDecimal.ZERO) > 0) {
			int maxDispense;
			int notesAvailable;
			
			// Lets see how many of this denomination could cover what is remaining to be dispensed
			maxDispense = dispenseRemainder.divide(bankNote.getNoteValue(), RoundingMode.DOWN).intValue();
			
			// If its more than zero we can check our inventory
			if(maxDispense > 0) {
				// How many in inventory for that <code>BankNote</code>
				notesAvailable = bankNote.getNumberAvailable();
				
				BigDecimal denominationCashValue;
				
				// If there is more notes in the inventory than we need we can dispense the maxDispense amount of this note
				if(notesAvailable > maxDispense) {
					// Populate the count of notes we are dispensing
					dispensedBankNotes = maxDispense;
					
					// Calculate the cashValue we are giving out in this note so we can count it towards the dispense amount
					denominationCashValue = bankNote.getNoteValue().multiply(new BigDecimal(maxDispense));
					
				// There is less notes or equal in the inventory than the Max we could give out is the amount in the inventory
				} else {
					// Populate the count of notes we are dispensing
					dispensedBankNotes = notesAvailable;
					
					// Calculate the cashValue we are giving out in this note so we can count it towards the dispense amount
					denominationCashValue = bankNote.getNoteValue().multiply(new BigDecimal(notesAvailable));
				}
				
				// Update the <code>BankNote</code> inventory
				bankNote.setNumberAvailable(notesAvailable - dispensedBankNotes);
				
				// Update the cash amount left to dispense
				dispenseRemainder = dispenseRemainder.subtract(denominationCashValue);
			}
			
			System.out.println(CLASSNAME + "." + METHODNAME + ": Dispensed number of " + bankNote.getNoteValue() + "  Notes is " + dispensedBankNotes
					+ ", remaining amount to dispense is " + dispenseRemainder + "\n");
			
		}
		return dispensedBankNotes;
	}

}
