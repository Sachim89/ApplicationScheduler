package com.ApplicationScheduler.Controller;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import org.json.JSONException;
import org.json.JSONObject;
import com.ApplicationScheduler.Exception.ApplicationSchedulerException;
import com.ApplicationScheduler.model.BookingDetails;
import com.ApplicationScheduler.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * WelcomeController:; Displays the date and time for scheduling appointment and also validates it.
 * @author Saranya
 *
 */
@Path("/appointment")
public class WelcomeController {
	
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Books an appointment
	 * @param booking
	 * @return
	 * @throws IOException 
	 * @throws JSONException 
	 * 
	 */
	@Path("/book")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response bookApt(String bookingDetailsString) throws IOException{
		
		String responseObject = "";
		BookingDetails bookingDetails = new BookingDetails();
		
		try {
			JSONObject bookingRequestDetails = new JSONObject(bookingDetailsString);
			
			bookingDetails = BookingService.book(bookingRequestDetails);
			responseObject = mapper.writeValueAsString(bookingDetails);
		} catch (ApplicationSchedulerException e) {
			responseObject = responseObject + e;
			return Response.status(Status.BAD_REQUEST).entity(responseObject).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(responseObject).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
}