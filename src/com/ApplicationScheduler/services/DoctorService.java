package com.ApplicationScheduler.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.ApplicationScheduler.model.Doctor;

/**
 * Doctor Services: Performs functionalities of reading the JSON file which contains the schedule appointments.
 * @author Saranya
 *
 */
public class DoctorService {
	
	//static Doctor dr = new Doctor();

	/**
	 * Reads schedules appointment JSON file.
	 * @param bookingDetails
	 * @return doctor object
	 * @throws ParseException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject appointments(String bookingDetails) throws ParseException, FileNotFoundException, IOException, JSONException {
		
		JSONObject jsonObject = parseJSONFile("C:/Users/Saranya/workspace/ApplicationScheduler/doctor.json");
		Iterator<?> keys = jsonObject.keys();
		return jsonObject;
		
	/*	while(keys.hasNext()) {
		    String key = (String) keys.next();
		    dr.setDate(key);
		    JSONArray results = jsonObject.getJSONArray(key);
		    for(int i = 0 ; i < results.length() ; i++){
				dr.setTime(results.get(i).toString());
				return dr;
			}
		    return dr;
		}
		return dr;*/
			
	}
	/**
	 * Parsing JSON file.
	 * @param filename
	 * @return JSONObject file
	 * @throws JSONException
	 * @throws IOException
	 */
	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
}