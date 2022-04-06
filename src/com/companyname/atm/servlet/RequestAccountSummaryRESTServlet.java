package com.companyname.atm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.companyname.atm.handler.RequestAccountSummaryHandler;
import com.companyname.atm.handler.RequestAccountSummaryHandlerException;
import com.companyname.atm.model.ResponseDetails;

/**
 * Simple Servlet class for building REST Response
 * @author Michael McGill
 *
 */
public class RequestAccountSummaryRESTServlet extends HttpServlet {

	/**
	 * Since this should work from a browser we will add Support for GET
	 * @param request
	 * @param response
	 * 
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Create variable for JSON
		String json;
		try {
			// We will use the handler to perform the intended function
			RequestAccountSummaryHandler handler = new RequestAccountSummaryHandler();
			
			// Get back a <code>ResponseDetails</code> with the result
			ResponseDetails responseDetails = handler.executeAccountSummaryRequest(request);
			
			// Use JSONBuilder class to create valid JSON
			json = JSONBuilder.buildJSONResponse(responseDetails);
			
		} catch (RequestAccountSummaryHandlerException ex) {
			// Print Stack trace
			ex.printStackTrace();
			
			// If there is an error build JSON from the exception details
			json = JSONBuilder.buildJSONResponse(ex.getExceptionDetails());
		}

		// Return it to the response
		response.getOutputStream().println(json);
	}

	/**
	 * This should support POST
	 * @param request
	 * @param response
	 * 
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Call doGet
		doGet(request, response);
	}
}