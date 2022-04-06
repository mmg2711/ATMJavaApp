package com.companyname.atm.datastore;

import java.util.HashMap;
import java.util.Map;

import com.companyname.atm.model.BankAccount;
import com.companyname.atm.model.BankNote;

/**
 * Example DataStore class that provides access to mocked up Data.
 * Acting as if its a Database source.
 * In the real world we could replace this class with one that connects to a Database.
 * Implementing as Singleton so data persists throughout a session.
 * @author Michael McGill
 */

public class NotesInventoryDataStore {
	
	// Map of NoteTypes to Number of each Note.
	private Map<String, BankNote> notesInventoryMap = new HashMap<>();

	// This class is a singleton and should not be instantiated directly!
	private static NotesInventoryDataStore instance = new NotesInventoryDataStore();

	/**
	 * Get the instance of this Data store. 
	 * Should only ever be one.
	 * @return a data store instance
	 */
	public static NotesInventoryDataStore getInstance() {
		return instance;
	}

	// Private constructor so people know to use the getInstance() function instead.
	private NotesInventoryDataStore() {
		// Lets add 1500 in different denomimations of each Bank Note		
		notesInventoryMap.put("50",  new BankNote("50", 10));
		notesInventoryMap.put("20",  new BankNote("20", 30));
		notesInventoryMap.put("10",  new BankNote("10", 30));
		notesInventoryMap.put("5",  new BankNote("5", 20));
	}
	
	/**
	 * Get a BankAccount object - we are using accountNumber as the key
	 * @param accountNumber
	 * @return an object representing a <code>BankNote</code>
	 */
	public BankNote getBankNote(String noteValue) {
		return notesInventoryMap.get(noteValue);
	}
}
