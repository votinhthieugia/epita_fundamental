/**
 * BtnIdentitiesListener.java
 */
package com.hf.fundamental.view.listeners;

import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.view.ViewIndex;

/**
 * <i>View</i> actionListener 
 * @author Hoang / Favio
 *
 */
public class BtnIdentitiesListener extends BaseListener {
	@Override
	protected void execute() {
		ViewController.getInstance().showView(ViewIndex.LIST);
	}
}