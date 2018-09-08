package com.ApplicationScheduler.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.*;
import com.ApplicationScheduler.Exception.ApplicationSchedulerException;
import com.ApplicationScheduler.model.User;

/**
 * Login/Logout Service: Performs functionalities of validating the username and password.
 * @author Saranya
 *
 */
public class LoginService {
	
	/**
	 * Login Validation.
	 * @param name
	 * @param password
	 * @return user object
	 * @throws ApplicationSchedulerException
	 * @throws ParseException
	 * @throws Exception
	 */
	public static User login(String name, String password) throws ApplicationSchedulerException, ParseException, Exception {
			
			User user = new User();
			//Read JSON file.
			String obj = new JSONParser().parse(new FileReader("C:/Users/Saranya/workspace/ApplicationScheduler/users.json")).toString();
			JSONArray jsonarray = new JSONArray(obj);
        	
			for(int i=0; i< jsonarray.length(); i++){
		        org.json.JSONObject val = jsonarray.getJSONObject(i);
		        String nid = val.getString("name");
		        String pass = val.getString("password");
		        String tim = val.getString("timezone");
	            String rol = val.getString("role");
	            
	            //User and Password validation
		        if(nid.equals(name) && pass.equals(password)){
	            	user.setName(nid);
	            	user.setPassword(pass);
					user.setTimezone(tim);
					user.setRole(rol);	
					return user;
	            }
		        if(!(name.equals(nid)) && password.equals(pass)){
		        	throw new ApplicationSchedulerException("Invalid username");
	            }
		        if(name.equals(nid) && !(password.equals(pass))){
		        	throw new ApplicationSchedulerException("Invalid password");
	            }
		    }	
			return user;
	}
	
	/**
	 * Logout functionality.
	 * @param id
	 * @return true - valid user, false - invalid user
	 * @throws ApplicationSchedulerException
	 * @throws JSONException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException
	 */
	public static boolean userValidation(String id) throws ApplicationSchedulerException, JSONException, FileNotFoundException, IOException, org.json.simple.parser.ParseException{
		
		User user = new User();
		String obj = new JSONParser().parse(new FileReader("C:/Users/Saranya/workspace/ApplicationScheduler/users.json")).toString();
		JSONArray jsonarray = new JSONArray(obj);
    	
		for(int i=0; i< jsonarray.length(); i++){
	        org.json.JSONObject val = jsonarray.getJSONObject(i);
	        String nid = val.getString("name");
            
	        if(nid.equals(id)){
	        	return true;
			} else {
				throw new ApplicationSchedulerException("User does not exist");
			}
		}
		return false;
	}
}
