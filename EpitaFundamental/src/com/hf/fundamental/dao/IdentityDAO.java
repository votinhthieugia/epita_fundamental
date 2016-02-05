/**
 * IdentityDAO.java
 */

package com.hf.fundamental.dao;

import java.util.List;

import com.hf.fundamental.datamodel.Identity;

public interface IdentityDAO {
	public void create(Identity identity) throws Exception;
	public List<Identity> readAll() throws Exception;
	public List<Identity> search(Identity identity) throws Exception;
	public void modify(Identity identity) throws Exception;
	public void delete(Identity identity) throws Exception;
}