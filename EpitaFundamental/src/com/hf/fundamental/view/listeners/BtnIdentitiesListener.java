package com.hf.fundamental.view.listeners;

import com.hf.fundamental.controller.ViewController;
import com.hf.fundamental.view.ViewIndex;

public class BtnIdentitiesListener extends BaseListener {
	@Override
	protected void execute() {
		ViewController.getInstance().showView(ViewIndex.READALL);
	}
}