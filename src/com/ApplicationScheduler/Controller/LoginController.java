package com.ApplicationScheduler.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import com.ApplicationScheduler.Exception.ApplicationSchedulerException;
import com.ApplicationScheduler.model.User;
import com.ApplicationScheduler.services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LoginController: Performs login & logout  functionalities by validating the name and password.
 * @author Saranya
 *
 */
@Path("/user")
public class LoginController {
	
	ObjectMapper mapper = new ObjectMapper();
	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * Login User
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Path("/login")
	@GET
	public Response loginUser(@QueryParam("name") String name, @QueryParam("password") String password) throws Exception {
		User user = new User();
		String responseObject = "";
		try {
			user = LoginService.login(name, password);
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute(name, user);
			session.setMaxInactiveInterval(420);
		} catch (ApplicationSchedulerException e) {
			responseObject = responseObject + e;
			return Response.status(Status.BAD_REQUEST).entity(responseObject).build();
		}
		try {
			responseObject = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return Response.status(Status.OK).entity(responseObject).build();
	}
	
	/**
	 * Logout User
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Path("/logout")
	@GET
	public Response logout(@QueryParam("id") String id) throws FileNotFoundException, JSONException, IOException, ParseException {
		String response = "";
		try {
			if (LoginService.userValidation(id)) 
			{
				response = "User successfully logged out";
				HttpSession session = httpServletRequest.getSession(false);
				session.removeAttribute(id);
				session.getMaxInactiveInterval();
			}
		}  catch (ApplicationSchedulerException e) {
			return Response.status(Status.BAD_REQUEST).entity("The user does not exist").build();
		}
		return Response.status(Status.OK).entity(response).build();
	}
}