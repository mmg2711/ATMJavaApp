package com.companyname.atm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.companyname.atm.datastore.BankAccountDataStore;
import com.companyname.atm.handler.RequestAccountSummaryHandler;
import com.companyname.atm.handler.RequestAccountSummaryHandlerException;
import com.companyname.atm.handler.RequestWithdrawalHandler;
import com.companyname.atm.handler.RequestWithdrawalHandlerException;
import com.companyname.atm.model.ResponseDetails;

public class RequestWithdrawalRESTServlet extends HttpServlet {

	/**
	 * We will not support GET for this request
	 * @param request
	 * @param response
	 * 
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}

	/**
	 * This should support POST
	 * @param request
	 * @param response
	 * 
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Create variable for JSON
		String json;
		try {
			// We will use the handler to perform the intended function
			RequestWithdrawalHandler handler = new RequestWithdrawalHandler();
			
			// Get back a <code>ResponseDetails</code> with the result
			ResponseDetails responseDetails = handler.executeWithdrawal(request);
			
			// Use JSONBuilder class to create valid JSON
			json = JSONBuilder.buildJSONResponse(responseDetails);
			
		} catch (RequestWithdrawalHandlerException ex) {
			// Print Stack trace
			ex.printStackTrace();
			
			// If there is an error build JSON from the exception details
			json = JSONBuilder.buildJSONResponse(ex.getExceptionDetails());
		}

		// Return it to the response
		response.getOutputStream().println(json);
	}
}