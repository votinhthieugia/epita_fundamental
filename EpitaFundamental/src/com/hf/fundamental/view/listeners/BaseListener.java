/**
 * BaseListener.java
 */
package com.hf.fundamental.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <i>View</i> actionListener 
 * @author Hoang / Favio
 *
 */
public abstract class BaseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}
	
	protected void execute() {};
}