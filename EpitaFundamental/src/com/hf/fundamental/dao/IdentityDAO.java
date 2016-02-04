/**
 * IdentityDAO.java
 */

package com.hf.fundamental.dao;

import java.util.List;

import com.hf.fundamental.datamodel.Identity;

public interface IdentityDAO {
	public void create(Identity identity);
	public List<Identity> readAll();
	public List<Identity> search(Identity identity);
	public void modify(Identity identity);
	public void delete(Identity identity);
}