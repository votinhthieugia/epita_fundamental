package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.datamodel.Identity;

public class XMLIdentityController implements IdentityControllerInterface{

	private static XMLIdentityController instance;
	private IdentityDAO identityDAO;
	
	public static XMLIdentityController getInstance() throws Exception {
		if (instance == null) {
			instance = new XMLIdentityController();
		}
		return instance;
	}
	
	@Override
	public void create(Identity newIdentity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Identity identity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Identity identity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Identity> search(Identity identity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Identity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
