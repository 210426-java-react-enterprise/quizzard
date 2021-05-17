package com.revature.quizzard.servlet;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.datasource.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet Hierarchy
 *
 * 			Servlet 	ServletConfig 	Serializable 	(interfaces)
 * 					^
 * 			Generic Servlet (abstract class)
 * 					^
 * 			HttpServlet (abstract class)
 * 					^
 * 			Custom Servlet (class)
 */
public class UserServlet extends HttpServlet {

	private UserService service = new UserService(new UserDAO(), new Session(null));
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// need to register a user
		// 1. gather information out of the request sent by the form
		/*
			post /uri http/1.1
			<headers>
			content-type:

			username=joe
			password=swanson
			email=something@guy.com
		 */
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fn = req.getParameter("firstname");
		String ln = req.getParameter("lastname");
		int age = Integer.parseInt(req.getParameter("age"));

		// 2. construct an AppUser with that information
		AppUser user = new AppUser(username, password, email, fn, ln, age);

		// 3. save the user in the db with the register method from the service
		service.register(user);

		// 4. optional: create method for sending actual information back to the client
		/*
			Status code levels
				100 - Information
				200 - OK (empty)
				300 - Redirect
				400 - Client side error
				500 - Server side error
		 */

		resp.setStatus(202);

		// This will get the PrintWriter associated with the Response. This PrintWriter will
		// write to the body of the response
		resp.getWriter()
	}



}
