/**
 * ViewController.java
 */
package com.hf.fundamental.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.view.CreateIdentityView;
import com.hf.fundamental.view.IdentityDetailView;
import com.hf.fundamental.view.IdentitySearchView;
import com.hf.fundamental.view.LoginView;
import com.hf.fundamental.view.MenuView;
import com.hf.fundamental.view.ViewIndex;

/**
 * The {@code ViewController} class sets a map containing the instances of the <i>Views </i>, then it controls
 * whenever a View may be close and reopens it when required
 * @author Favio
 *
 */
public class ViewController {

	// Map that stores the instances of all the views
	private static Map<Integer ,JFrame> listFrames = new HashMap<Integer, JFrame>();		
	private static ViewController instance;
	private static JFrame currentView;

	/**
	 * @return the currentView
	 */
	public JFrame getCurrentView() {
		return currentView;
	}

	/**
	 * @param currentView the currentView to set
	 */
	public  void setCurrentView(JFrame currentView) {
		ViewController.currentView = currentView;
	}

	private ViewController(){

	}

	/**
	 * Add JFrame to the listFrames Map
	 * @param type of view
	 * @param jFrame
	 */
	public void addFrame(int index, JFrame frame) {
		listFrames.put(index, frame);
	}

	/**
	 * Return the instance of the JFrame, given a View type
	 * @param ViewIndex
	 * @return JFrame
	 */
	public JFrame getFrame(int index){		
		return listFrames.get(index);		
	}

	/**
	 * 
	 * @return instance of the ViewController
	 */
	public static ViewController getInstance(){
		if (instance == null) {
			instance = new ViewController();
		}
		return instance;
	}

	/**
	 * The method {@code start()} implements the <i>Runnable </i> interface to invoke {@code RunApp()} method
	 */
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {			      
			@Override
			public void run() {                
				runApp();
			}		
		});		
	}

	/**
	 * The method {@code showView()} displays a View given a {@link ViewIndex} 
	 * @param ViewIndex
	 */
	public void showView(int index) {
		showView(index, null);
	}
	
	/**
	 * Method overloading of {@code showView(int)}
	 * @param index
	 * @param data
	 */
	public void showView(int index, Object data) {
		System.out.println("ShowView");
		if (getCurrentView() != null) {
			getCurrentView().setVisible(false);			
		}		
		switch (index) {
		case ViewIndex.LOGIN:
			JFrame nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new LoginView();
			}

			nextView.setVisible(true);
			setCurrentView(nextView);
			break;
		case ViewIndex.LIST:
			nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new IdentitySearchView();
			}

			System.out.println(nextView.toString());
			nextView.setVisible(true);
			setCurrentView(nextView);
			break;
		case ViewIndex.MENU:
			nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new MenuView();
			}

			nextView.setVisible(true);
			setCurrentView(nextView);
			break;
		case ViewIndex.DETAIL:
			nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new IdentityDetailView();
			}
			System.out.println(nextView.toString());			

			setCurrentView(nextView);
			
			if (data != null) {
				((IdentityDetailView)nextView).setIdentity((Identity)data);
			}
			
			nextView.setVisible(true);
			break;
		case ViewIndex.CREATE:
			nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new CreateIdentityView();
			}

			nextView.setVisible(true);
			setCurrentView(nextView);
			break;
		default:
			break;
		}
	}


	/**
	 * Sets the Theme to the frames
	 */
	/**
	 * The method {@code runApp} sets the theme to all the {@link JFrame} Views
	 */
	private void runApp() {
		JFrame.setDefaultLookAndFeelDecorated(true);                   
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ChallengerDeepSkin");
		SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceBrownTheme");		
		try {						
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
			setCurrentView(loginView);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
