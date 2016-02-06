/**
 * AplicationController.java
 */
package com.hf.fundamental.controller;

import com.hf.fundamental.dao.Storage;

/**
 * The {@code ApplicationController} class links the <i>View Layer </i>with the <i>Implementation layer</i>.
 * @author Hoang / Favio
 *
 */
public class ApplicationController {
	private static IdentityControllerInterface identityController;
	private static UserControllerInterface userController;

	/**
	 * Get the <i>Identity Controller</i> instance.
	 * @return a new IdentityControllerInterface
	 */
	public static IdentityControllerInterface getIdentityController() {
		return identityController;
	}

	/**
	 * Get the <i>User Controller</i> instance.
	 * @return UserControllerInterface
	 */
	public static UserControllerInterface getUserController() {
		return userController;
	}
	
	/**
	 * Initialize application.
	 * @param: dbType mode of storage, 1. XML 2. Derby
	 */
	public static void init(int dbType) {
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
	
	/**
	 * Main method of the application
	 * @param args
	 */
	public static void main(String[] args) {
		ViewController main = ViewController.getInstance();
		main.start();
	}
}