package com.ApplicationScheduler.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import com.ApplicationScheduler.model.Doctor;
import com.ApplicationScheduler.services.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DoctorController: Performs functionalities in displaying the scheduled appointment respective of their timezone. 
 * @author Saranya
 *
 */
@Path("/doctor")
public class DoctorController {
	
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@Path("/apts")
	@GET
	public Response Apt(@QueryParam("role") String role) throws Exception {
		String responseObject = "";
		JSONObject docDetails;
		
		docDetails = DoctorService.appointments(role);
		Iterator<?> keys = docDetails.keys();
		while(keys.hasNext()) {
		    String key = (String) keys.next();
		    org.json.JSONArray results = docDetails.getJSONArray(key);
		    for(int i = 0 ; i < results.length() ; i++){
		    	responseObject = responseObject + results.get(i).toString();
		    }
		}
		
		return Response.status(Status.OK).entity(docDetails.toString()).build();
	}
}
