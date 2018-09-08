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
		Doctor docDetails = new Doctor();
		
		docDetails = DoctorService.appointments(role);
		try {
			responseObject = mapper.writeValueAsString(docDetails);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(responseObject).build();
	}
}
