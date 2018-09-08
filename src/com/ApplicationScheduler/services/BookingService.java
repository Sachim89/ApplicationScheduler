package com.ApplicationScheduler.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import com.ApplicationScheduler.Exception.ApplicationSchedulerException;
import com.ApplicationScheduler.model.BookingDetails;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Booking Services: Performs functionalities of validating the date and time.
 * It also writes the validated date and time respective of their timezone in each json files.
 * @author Saranya
 *
 */
public class BookingService {
	
	static HashMap<String,ArrayList> dateTime = new HashMap<String,ArrayList>();
	static HashMap<String,ArrayList> drTime = new HashMap<String,ArrayList>();
	
	/**
	 * Date & Time validation and writing JSON file.
	 * @param bookingDetails
	 * @return BookingDetails object
	 * @throws ApplicationSchedulerException
	 * @throws JSONException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static BookingDetails book(JSONObject bookingDetails) throws ApplicationSchedulerException, JSONException, JsonGenerationException, JsonMappingException, IOException {
		
		BookingDetails details = new BookingDetails();
		ObjectMapper objectMapper = new ObjectMapper();
		
		//Assign values to BookingDetails model
		details.setDate(bookingDetails.getString("date"));
		details.setTime(bookingDetails.getString("time"));
	
		String valueRole = bookingDetails.getString("role");
		 
		if(valueRole.equalsIgnoreCase("patient")){
			System.out.println(valueRole);
			
			String inputDate = details.getDate()+ " " + details.getTime();
		    DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		    LocalDateTime local = LocalDateTime.parse(inputDate, sourceFormatter);
		   
			ZonedDateTime zoned = local.atZone(ZoneId.of("America/Montreal"));
			
			//Calculates time according to given timezone: "IndianStandardTime(+5:30GMT)"
		    ZonedDateTime zonedIND = zoned.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
		    
		  //Calculates time according to given timezone: "Pacific Standard Time (-7 GMT)"
		    ZonedDateTime zonedUS = zoned.withZoneSameInstant(ZoneId.of("America/Montreal"));	
				
			ArrayList<String> times = new ArrayList<String>();
			ArrayList<String> times_dr = new ArrayList<String>();
			
			if(!(dateTime.containsKey(bookingDetails.getString("date")))){
				times.add(zonedIND.toLocalTime().toString());
				times_dr.add(zonedUS.toLocalTime().toString());
				dateTime.put(bookingDetails.getString("date"),times);
				drTime.put(bookingDetails.getString("date"),times_dr);
			}
			else{
				ArrayList values = dateTime.get(bookingDetails.getString("date"));
				for(Object x : values.toArray()){
					if(x.equals(bookingDetails.getString("time"))){
						System.out.println(x);
						throw new ApplicationSchedulerException("You have an appointment scheduled");
					}
				}
				dateTime.get(bookingDetails.getString("date")).add(zonedIND.toLocalTime().toString());
				drTime.get(bookingDetails.getString("date")).add(zonedUS.toLocalTime().toString());
			}
			
			objectMapper.writeValue(new File("C:/Users/Saranya/workspace/ApplicationScheduler/test.json"), dateTime);
			
			details.setDate(bookingDetails.getString("date"));
			details.setTime(zonedIND.toLocalTime().toString());
			
			//Doctor
			objectMapper.writeValue(new File("C:/Users/Saranya/workspace/ApplicationScheduler/doctor.json"), drTime);
		}		
	
		return details;
	}
}