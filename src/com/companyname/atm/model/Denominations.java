package com.companyname.atm.model;

/**
 * Simple model class that represents the denominations to be dispensed as part of a Withdrawal request
 * @author Michael McGill
 */

public class Denominations {
	private int numberOf5s;
	private int numberOf10s;
	private int numberOf20s;
	private int numberOf50s;

	/**
	 * Return number of 5s
	 * @return number of Notes
	 */
	public int getNumberOf5s() {
		return numberOf5s;
	}

	/**
	 * Set the number of 5s
	 * @param numberOf5s
	 */
	public void setNumberOf5s(int numberOf5s) {
		this.numberOf5s = numberOf5s;
	}
	
	/**
	 * Return number of 10s
	 * @return number of Notes
	 */
	public int getNumberOf10s() {
		return numberOf10s;
	}

	/**
	 * Set the number of 10s
	 * @param numberOf10s
	 */
	public void setNumberOf10s(int numberOf10s) {
		this.numberOf10s = numberOf10s;
	}

	/**
	 * Return number of 20s
	 * @return number of Notes
	 */
	public int getNumberOf20s() {
		return numberOf20s;
	}

	/**
	 * Set the number of 20s
	 * @param numberOf20s
	 */
	public void setNumberOf20s(int numberOf20s) {
		this.numberOf20s = numberOf20s;
	}

	/**
	 * Return number of 50s
	 * @return number of Notes
	 */
	public int getNumberOf50s() {
		return numberOf50s;
	}

	/**
	 * Set the number of 50s
	 * @param numberOf50s
	 */
	public void setNumberOf50s(int numberOf50s) {
		this.numberOf50s = numberOf50s;
	}
	
	/**
	 * Overriding so can use in unit test
	 */
	@Override
    public boolean equals(Object o) {
    	 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Denominations)) {
            return false;
        }
         
        // Case to denoDenominations object
        Denominations denominations = (Denominations) o;
         
        // Compare the data members and return accordingly
        return (this.numberOf10s == denominations.numberOf10s
        		&& this.numberOf20s == denominations.numberOf20s
        		&& this.numberOf50s == denominations.numberOf50s);
    }

}