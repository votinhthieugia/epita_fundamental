/**
 * ApplicationController.java
 */

package com.hf.fundamental.controller;

import com.hf.fundamental.dao.DataConfiguration;
import com.hf.fundamental.dao.Storage;


public class ApplicationController {
	private static IdentityControllerInterface identityController;
	private static UserControllerInterface userController;

	/**
	 * Get the identity controller instance.
	 */
	public static IdentityControllerInterface getIdentityController() {
		return identityController;
	}

	/**
	 * Get the user controller instance. 
	 */
	public static UserControllerInterface getUserController() {
		return userController;
	}
	
	/**
	 * Initialize application.
	 * @param: dbType 
	 */
	public static void init(int dbType) {
		try {
			DataConfiguration.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			switch (dbType) {
			case Storage.XML:
				identityController = XMLIdentityController.getInstance(dbType);
				userController = XMLUserController.getInstance(dbType);
				break;
			default:
				identityController = SQLIdentityController.getInstance(dbType);
				userController = SQLUserController.getInstance(dbType);
				break;
			}
		} catch (Exception e) {
			// TODO: Do something here and display the error.
		}
	}
	
	public static void main(String[] args) {
		ViewController main = ViewController.getInstance();
		main.start();
	}
}