package com.companyname.atm.service;

import java.math.BigDecimal;
import java.util.Map;

import com.companyname.atm.model.BankNote;
import com.companyname.atm.model.Denominations;

public interface NotesDispenseService {

	public Denominations getNotesDenominations(BigDecimal withdrawalAmount) throws NotesDispenseServiceException;

}
