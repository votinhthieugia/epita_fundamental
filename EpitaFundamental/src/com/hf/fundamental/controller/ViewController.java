package com.hf.fundamental.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;

import com.hf.fundamental.view.IdentityDetailView;
import com.hf.fundamental.view.IdentitySearchView;
import com.hf.fundamental.view.LoginView;
import com.hf.fundamental.view.MenuView;
import com.hf.fundamental.view.ViewIndex;

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

	public void addFrame(int index, JFrame frame) {
		listFrames.put(index, frame);
	}

	public JFrame getFrame(int index){		
		return listFrames.get(index);		
	}

	public static ViewController getInstance(){
		if (instance == null) {
			instance = new ViewController();
		}
		return instance;
	}

	public void start() {
		SwingUtilities.invokeLater(new Runnable() {			      
			@Override
			public void run() {                
				runApp();
			}		
		});		
	}

	public void showView(int index){
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

			System.out.println(nextView.toString());
			nextView.setVisible(true);
			setCurrentView(nextView);
			break;
		case ViewIndex.DETAIL:
			nextView = listFrames.get(index);

			if (nextView == null) {
				nextView = new IdentityDetailView();
			}

			System.out.println(nextView.toString());
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
