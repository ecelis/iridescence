package com.ecaresoft.tests;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import com.ecaresoft.util.PasswordHandler;

public class PasswordHandlerTest {

	private final PasswordHandler passwdHndlr = new PasswordHandler(new Properties());

	@Test
	public void testCheckPasswordWrongMinLength() {
		String errMsg = passwdHndlr.checkPassword("2Naaffg", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongMaxLength() {
		String errMsg = passwdHndlr.checkPassword("jhl4mwfxc6pnwjbxo8y77L", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithWhiteSpace() {
		String errMsg = passwdHndlr.checkPassword("2N 70ffgq", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithAlphaSeq() {
		String errMsg = passwdHndlr.checkPassword("Abcdejhl4m", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithNumSeq() {
		String errMsg = passwdHndlr.checkPassword("aaff12345", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithNoDigits() {
		String errMsg = passwdHndlr.checkPassword("ANaaffgq", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithUserName() {
		String errMsg = passwdHndlr.checkPassword("9usuarioq", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWithQwerty() {
		String errMsg = passwdHndlr.checkPassword("qwerty4476", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordWrongWith4EqualChars() {
		String errMsg = passwdHndlr.checkPassword("2nnnn7fg", "usuario");
		assertNotNull(errMsg);
	}

	@Test
	public void testCheckPasswordRightPassword() {
		String errMsg = passwdHndlr.checkPassword("uqftn5vx", "usuario");
		assertNull(errMsg);
	}

}