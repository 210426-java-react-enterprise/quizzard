package com.revature.quizzard.screens;

import com.revature.quizzard.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen {

	public boolean prompt(BufferedReader consoleReader) {
		String username;
		String password;
		UserDAO account = new UserDAO();
		try  {
			System.out.print("Username: ");
			username = consoleReader.readLine();

			System.out.print("Password: ");
			password = consoleReader.readLine();

			if (account.readCredentials(username, password)) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return false;

	}

}
