package com.companyname.atm.model;

/**
 * Represents a Bank Note and its inventory
 * @author Michael McGill
 * 
 */

import java.math.BigDecimal;

public class BankNote {

	private BigDecimal noteValue;
	private int numberAvailable;

	// Initialisation Constructor
	public BankNote(String noteValue, int numberAvailable) {
		this.noteValue = new BigDecimal(noteValue);
		this.numberAvailable = numberAvailable;
	}

	
	/**
	 * Return the value of this Note.
	 * @return Value of Note as BigDecimal.
	 */
	public BigDecimal getNoteValue() {
		return noteValue;
	}

	/**
	 * Set the value of this <code>BankNote</code> object.
	 * @param noteValue
	 */
	public void setNoteValue(String noteValue) {
		this.noteValue = new BigDecimal(noteValue);
	}

	/**
	 * Return the number of this Note that is avaialable.
	 * @return Number of Notes as int.
	 */
	public int getNumberAvailable() {
		return numberAvailable;
	}

	/**
	 * Set the number of Notes available
	 * @param noteValue
	 */
	public void setNumberAvailable(int numberAvailable) {
		this.numberAvailable = numberAvailable;
	}
	
}