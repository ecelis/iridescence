package com.ecaresoft.log;

import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author odelarosa
 * 
 */
public class User {
	private int		id;
	private String	name;

	public User() {}

	public User(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public User(final Properties ctx) {
		this.id = Env.getAD_User_ID(ctx);
		this.name = Env.getAD_User_Name(ctx);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
