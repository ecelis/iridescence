/**
 * 
 */
package com.ecaresoft.tests;

import junit.framework.TestCase;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MAttachmentFile;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 */
public class MAttachmentFileTest extends TestCase {

	/** @see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#validateEntries(org.compiere.model.MAttachment)}.
	 */
	public void testValidateEntries() {
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if (file == null) {
			MAttachmentFile.validateEntries(file);// metodo void, validar por nullpointerexception
		} else {
			// Proceso: requiere transaccion
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("testValidateEntries"), true);
				MAttachmentFile.validateEntries(file);// metodod void, validar por exceptiones
				Trx.rollback(trx, true);// rollback, close
			} catch (Exception e) {
				Trx.rollback(trx, true);// rollback, close
				fail(e.toString());
			} finally {
				Trx.close(trx, true);// rollback, close
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#getFiles(org.compiere.model.MAttachment)}.
	 */
	public void testGetFiles() {
		final MAttachmentFile file = TestUtils.getRandomPO(MAttachmentFile.class);
		if (file == null) {
			// forzar lista vacia
			assertTrue(MAttachmentFile.getFiles(new MAttachment(Env.getCtx(), 0, null)).isEmpty());
		} else {
			// forzar lista con valores
			assertFalse(MAttachmentFile.getFiles(file.getAD_Attachment()).isEmpty());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#MAttachmentFile(org.compiere.model.MAttachment)}.
	 */
	public void testMAttachmentFileMAttachment() {
		// contructor, validar excepciones
		new MAttachmentFile(new MAttachment(Env.getCtx(), 0, null));
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if (file != null) {
			new MAttachmentFile(file);
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#getAD_Attachment()}.
	 */
	public void testGetAD_Attachment() {
		final MAttachmentFile file = TestUtils.getRandomPO(MAttachmentFile.class);
		if (file == null) {
			assertTrue(new MAttachmentFile(Env.getCtx(), 0, null).getAD_Attachment().is_new());
		} else {
			assertEquals(file.getAD_Attachment().get_ID(), file.getAD_Attachment_ID());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#getAD_AttachmentType()}.
	 */
	public void testGetAD_AttachmentType() {
		final MAttachmentFile file = TestUtils.getRandomPO(MAttachmentFile.class);
		if (file == null) {
			assertTrue(new MAttachmentFile(Env.getCtx(), 0, null).getAD_AttachmentType().is_new());
		} else {
			assertEquals(file.getAD_AttachmentType().get_ID(), file.getAD_AttachmentType_ID());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#getEntry()}.
	 */
	public void testGetEntry() {
		final MAttachmentFile file = TestUtils.getRandomPO(MAttachmentFile.class);
		if (file == null) {
			assertNull(new MAttachmentFile(Env.getCtx(), 0, null).getEntry());
		} else if(file.getAD_Attachment().getEntryCount() > 0){
			assertNotNull(file.getEntry());
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachmentFile#setEntry(org.compiere.model.MAttachmentEntry)}.
	 */
	public void testSetEntry() {
		final MAttachmentFile file = new MAttachmentFile(Env.getCtx(), 0, null);
		if (file != null) {
			final String name = "testname";
			MAttachmentEntry mEntry = new MAttachmentEntry(name, new byte[8192]);
			file.setEntry(mEntry);// void
			assertNotNull(file.getEntry());
			assertEquals(name, file.getFileName());
		}
	}

}
