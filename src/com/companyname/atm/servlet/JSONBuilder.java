package com.companyname.atm.servlet;

import java.util.Map.Entry;

import com.companyname.atm.constants.AppValues;
import com.companyname.atm.model.ResponseDetails;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This class takes a <code>ResponseDetails</code> object
 * and uses the values from it to build valid JSON
 * @author Michael McGill
 *
 */

public class JSONBuilder extends JsonFactory {
	
	/**
	 * Build up the JSON Response from the values set on the <code>ResponseObject</code> if its a successful response
	 * @param responseObject
	 */
	public static String buildJSONResponse(ResponseDetails responseDetails) {
		// create our Return JSON String
		String json = "";
		// Create ObjectMapper instance
		ObjectMapper mapper = new ObjectMapper();

		// Create an ObjectNode object to represent the response as JSON
		ObjectNode responseNode = mapper.createObjectNode();
		
		// If Successful we will populate accordingly
		responseNode.put(AppValues.SUCCESS_DESC, responseDetails.isSuccess());
		
		// Create an ObjectNode to represent the application messages as JSON
		ObjectNode details = mapper.createObjectNode();
		
		// Add a new entry for each record
		for (Entry<String, Object> entry : responseDetails.getDetails().entrySet()) {
			details.put(entry.getKey(), entry.getValue().toString());
		}
		
		// prepend the messages with the response
		responseNode.set(AppValues.DATA_DESC, details); 
		
		try {
			// convert `ObjectNode` to pretty-print JSON
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseNode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// Return JSON
		return json;
	}

}