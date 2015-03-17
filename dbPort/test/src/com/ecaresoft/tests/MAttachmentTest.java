/**
 * 
 */
package com.ecaresoft.tests;

import java.util.UUID;

import junit.framework.TestCase;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * @author lama
 *
 */
public class MAttachmentTest extends TestCase  {

	/**@see junit.framework.TestCase#setUp() */
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}


	/**
	 * Test method for {@link org.compiere.model.MAttachment#toString()}.
	 */
	@Test
	public void testToString() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#beforeSave(boolean)}.
	 */
	@Test
	public void testBeforeSave() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getTextMsg()}.
	 */
	@Test
	public void testGetTextMsg() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#get(java.util.Properties, int, int)}.
	 */
	@Test
	public void testGetPropertiesIntInt() {
		// force null
		assertNull(MAttachment.get(Env.getCtx(), -2, -2));
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if(file!=null){
			assertNotNull(MAttachment.get(file.getCtx(), file.getAD_Table_ID(), file.getRecord_ID()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addTextMsg(java.lang.String)}.
	 */
	@Test
	public void testAddTextMsg() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addEntry(java.io.File)}.
	 */
	@Test
	public void testAddEntryFile() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addEntry(java.lang.String, byte[])}.
	 */
	@Test
	public void testAddEntryStringByteArray() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addEntry(java.lang.String, byte[], java.lang.String)}.
	 */
	@Test
	public void testAddEntryStringByteArrayString() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addEntry(org.compiere.model.MAttachmentEntry)}.
	 */
	@Test
	public void testAddEntryMAttachmentEntry() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntry(java.lang.String)}.
	 */
	@Test
	public void testGetEntryString() {
		// force null
		final String id = "name_" + UUID.randomUUID();
		assertNull(new MAttachment(Env.getCtx(), 0, null).getEntry(id));

		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if (file != null) {
			final MAttachmentEntry entry = new MAttachmentEntry(id, new byte[8192]);
			if (file.addEntry(entry)) {
				assertEquals(entry, file.getEntry(id));
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntry(int)}.
	 */
	@Test
	public void testGetEntryInt() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntries()}.
	 */
	@Test
	public void testGetEntries() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#deleteEntry(int)}.
	 */
	@Test
	public void testDeleteEntryInt() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#deleteEntry(org.compiere.model.MAttachmentEntry)}.
	 */
	@Test
	public void testDeleteEntryMAttachmentEntry() {
//		xds vfail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntryCount()}.
	 */
	@Test
	public void testGetEntryCount() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntryName(int)}.
	 */
	@Test
	public void testGetEntryName() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#dumpEntryNames()}.
	 */
	@Test
	public void testDumpEntryNames() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntryData(int)}.
	 */
	@Test
	public void testGetEntryData() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntryFile(int, java.lang.String)}.
	 */
	@Test
	public void testGetEntryFileIntString() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getEntryFile(int, java.io.File)}.
	 */
	@Test
	public void testGetEntryFileIntFile() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#updateEntry(int, java.io.File)}.
	 */
	@Test
	public void testUpdateEntryIntFile() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#updateEntry(int, byte[])}.
	 */
	@Test
	public void testUpdateEntryIntByteArray() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#getAttachId(org.compiere.model.PO)}.
	 */
	@Test
	public void testGetAttachId() {
		// force 0
		assertTrue(MAttachment.getAttachId(new MEXMEEstServ(Env.getCtx(), 0, null)) <= 0);
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if(file!=null){
			// generamos el objeto PO relacionado
			final PO pomodel = MTable.get(Env.getCtx(), file.getAD_Table_ID()).getPO(file.getRecord_ID(), null);
			final int colIdx = pomodel.get_ColumnIndex(MAttachment.COLUMNNAME_AD_Attachment_ID);
			if( colIdx >= 0 ) {
				assertEquals(MAttachment.getAttachId(pomodel) , pomodel.get_ValueAsInt(colIdx));
			} else {
				assertEquals(MAttachment.getAttachId(pomodel) , file.getAD_Attachment_ID());
			}
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#get(java.util.Properties, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetPropertiesIntIntString() {
		// force null
		assertNull(MAttachment.get(Env.getCtx(), -2, -2, "hgxujc"));
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class, " title IS NOT NULL ", true);
		if(file!=null){
			assertNotNull(MAttachment.get(file.getCtx(), file.getAD_Table_ID(), file.getRecord_ID(), file.getTitle()));
		}
	}

	/**
	 * Test method for {@link org.compiere.model.MAttachment#addFileEntry(java.lang.String, byte[])}.
	 */
	@Test
	public void testAddFileEntry() {
		final MAttachment file = TestUtils.getRandomPO(MAttachment.class);
		if (file != null) {
			assertNotNull(file.addFileEntry("testFile", new byte[8192]));
		}
	}

}
