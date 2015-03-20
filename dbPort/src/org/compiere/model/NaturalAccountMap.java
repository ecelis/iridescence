/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.*;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.*;

/**
 *  Natural Account (HashMap) Management.
 *  <String,MElementValue>
 *  <pre>
 *  The key is a String of the column name (e.g. SUSPENSEBALANCING_ACCT)
 *  The value is an NaturalAccount
 *
 *  a) Account information are loaded via the parse functions
 *  b) Accounts are created via the createAccounts function
 *  c) retrieve the C_ElementValue_ID for the given key
 *  </pre>
 *
 *  @author Jorg Janke
 *  @version $Id: NaturalAccountMap.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *	@param <K> key 
 *	@param <V> value
 */
public final class NaturalAccountMap<K,V> extends CCache<K,V>
{
	/**
	 *  Constructor.
	 *  Parse File does the processing
	 *  @param ctx context
	 *	@param trxName transaction
	 */
	public NaturalAccountMap(Properties ctx, String trxName)
	{
		super("NaturalAccountMap", 100);
		m_ctx = ctx;
		m_trxName = trxName;
	}   //  NaturalAccountMap

	/** Delimiter       */
	private String      m_delim = ",";
	/** KeyNo           */
	private static int  s_keyNo = 0;
	/** Context			*/
	private Properties	m_ctx = null;
	/** Transaction		*/
	private String		m_trxName = null;
	/** Map of Values and Element	*/
	//was changed to TreeMap to order with the first column of the file [Account_Value]. //mguerrero
	private TreeMap<String,MElementValue> 	m_valueMap = new TreeMap<>(); 
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(NaturalAccountMap.class);

	/**
	 *  Read and Parse File
	 * 	@param file Accounts file
	 *  @return error message or "" if OK
	 */
	public String parseFile(InputStream file) {
		// log.config(file.getAbsolutePath());
		String line = null;
		try {
			// see FileImport
			BufferedReader in = new BufferedReader(new InputStreamReader(file), 10240);
			//	not safe see p108 Network pgm
			String errMsg = "";

			//  read lines
			int count = 0;
			while ((line = in.readLine()) != null && errMsg.length() == 0){
				errMsg = parseLine(line);
				count++;
			}
			line = "";
			in.close();
			
			if(m_valueMap.size() != count) {
				log.severe("Error: Accounts added: " + m_valueMap.size() + " total lines: " + count);
			}
			//  Error
			if (errMsg.length() != 0){
				return errMsg;
			}
		} catch (Exception ioe) {
			log.log(Level.SEVERE, ioe.getMessage(), ioe);
			String s = ioe.getLocalizedMessage();
			if (s == null || s.length() == 0){
				s = ioe.toString();
			}
			return "Parse Error: Line=" + line + " - " + s;
		}
		return "";
	}   //  parse

	/**
	 *  Create Account Entry for Default Accounts only.
	 *  @param line line with info
	 *  Line format (9 fields)
	 *   1	A   [Account Value]
	 *   2	B   [Account Name]
	 *   3	C   [Description]
	 *   4	D   [Account Type]
	 *   5	E   [Account Sign]
	 *   6	F   [Document Controlled]
	 *   7	G   [Summary Account]
	 * 	 8	H   [Default_Account]
	 * 	 9	I   [Parent Value] - ignored
	 *   10 J   [Level]
	 *   11 K   [GroupAcct] 
	 *
	 *  @return error message or "" if OK
	 *  @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String parseLine(String line) throws Exception {
		log.config(line);

		//  Fields with ',' are enclosed in "
		StringBuffer newLine = new StringBuffer();
		StringTokenizer st = new StringTokenizer(line, "\"", false);
		newLine.append(st.nextToken());         //  first part
		while (st.hasMoreElements()) {
			String s = st.nextToken(); // enclosed part
			newLine.append(s.replace(',', ' ')); // remove ',' with space
			if (st.hasMoreTokens()) {
				newLine.append(st.nextToken()); // unenclosed
			}
		}
		//  add space at the end        - tokenizer does not count empty fields
		newLine.append(" ");

		//  Parse Line - replace ",," with ", ,"    - tokenizer does not count empty fields
		String pLine = Util.replace(newLine.toString(), ",,", ", ,");
		pLine = Util.replace(pLine, ",,", ", ,");
		st = new StringTokenizer(pLine, ",", false);
		// All fields there ?
		if (st.countTokens() == 1) {
			log.severe("Ignored: Require ',' as separator - " + pLine);
			return "";
		}
		if (st.countTokens() < 9) {
			log.severe("Ignored: FieldNumber wrong: " + st.countTokens() + " - " + pLine);
			return "";
		}

		//  Fill variables
		String Value = null, Name = null, Description = null,
			AccountType = null, AccountSign = null, IsDocControlled = null,
			IsSummary = null, Default_Account = null, groupAcct = null, parent=null;
		//
		for (int i = 0; i < 11 && st.hasMoreTokens(); i++) {
			String s = st.nextToken().trim();
			if (s == null) {
				s = "";
			}
			//  Ignore, if is it header line
			if (s.startsWith("[") && s.endsWith("]")) {
				log.severe("Ignored: Invalid Account Value " + pLine);
				return "";
			}
			//
			if (i == 0) { // A - Value
				Value = s;
			} else if (i == 1) { // B - Name
				Name = s;
			} else if (i == 2) { // C - Description
				Description = s;
			} else if (i == 3) { // D - Type
				AccountType = s.length() > 0 ? String.valueOf(s.charAt(0)) : "E";
			} else if (i == 4) { // E - Sign
				AccountSign = s.length() > 0 ? String.valueOf(s.charAt(0)) : "N";
			} else if (i == 5) { // F - DocControlled
				IsDocControlled = s.length() > 0 ? String.valueOf(s.charAt(0)) : "N";
			} else if (i == 6) { // G - IsSummary
				IsSummary = s.length() > 0 ? String.valueOf(s.charAt(0)) : "N";
			} else if (i == 7) { // H - Default_Account
				Default_Account = s;
			} else if (i == 8) { // J - Parent
				parent = s;
			} else if (i == 10) { // K - Group Acct
				groupAcct = s;
			}
		}

		//	Ignore if Value & Name are empty (no error message)
		if ((Value == null || Value.length() == 0) && (Name == null || Name.length() == 0)) {
			log.severe("Ignored: No Account Value " + pLine);
			return "";
		}

		//  Default Account may be blank
		//  Need to load the entire file. //mguerrero
		if (Default_Account == null || Default_Account.length() == 0){
			Default_Account = String.valueOf(s_keyNo++);
			//log.log(Level.SEVERE, "Ignored: No Default Account " + pLine);  
			//return "";
		}
		//	No Summary Account
		if (IsSummary == null || IsSummary.length() == 0){
			IsSummary = "N";
		}
		//  Need to load the entire file. //mguerrero 
		if (!IsSummary.equals("N")){
			//log.log(Level.SEVERE, "Ignored: Is Summary Account " + pLine);		
			//return "";
		}
			
		//  Validation
		if (AccountType == null || AccountType.length() == 0) {
			AccountType = "E";
		}
		if (AccountSign == null || AccountSign.length() == 0) {
			AccountSign = "N";
		}
		if (IsDocControlled == null || IsDocControlled.length() == 0) {
			IsDocControlled = "N";
		}

	//	log.config( "Value=" + Value + ", AcctType=" + AccountType
	//		+ ", Sign=" + AccountSign + ", Doc=" + docControlled
	//		+ ", Summary=" + summary + " - " + Name + " - " + Description);

		try {
			//	Try to find - allows to use same natutal account for multiple default accounts 
			MElementValue na = (MElementValue)m_valueMap.get(Value);
			if (na == null) {
				//  Create Account - save later
				na = new MElementValue(m_ctx, Value, Name, Description,
					AccountType, AccountSign,
					IsDocControlled.toUpperCase().startsWith("Y"), 
					IsSummary.toUpperCase().startsWith("Y"), m_trxName);
				m_valueMap.put(Value, na);
			} else {
				log.severe("Repeated Account Value " + pLine);
			}
			// Group Account SAT. //mguerrero
			if (StringUtils.isNotBlank(groupAcct)) {
				final MEXMEGroupAcct mGroupAcct = MEXMEGroupAcct.getGroupValue(m_ctx, groupAcct, null);
				if (mGroupAcct != null) {
					na.setEXME_GroupAcct_ID(mGroupAcct.getEXME_GroupAcct_ID());
				}
			}
			// Parent Account. //mguerrero
			if(StringUtils.isNotBlank(parent)){
				na.setParentValue(parent);
			}
			//  Add to Cache
			put((K)Default_Account.toUpperCase(), (V)na);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return (e.getMessage());
		}
		return "";
	}   //  parseLine

	/**
	 *  Save all Accounts
	 *
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 * 	@param C_Element_ID element
	 * 	@return true if created
	 */
	public boolean saveAccounts(int AD_Client_ID, int AD_Org_ID,
			int C_Element_ID) {
		log.config("");
		Iterator<Entry<String, MElementValue>> iterator = m_valueMap.entrySet().iterator(); 
		// this.values().iterator();

		while (iterator.hasNext()) {
			final MElementValue na = iterator.next().getValue();
			na.setAD_Client_ID(AD_Client_ID);
			na.setAD_Org_ID(AD_Org_ID);
			na.setC_Element_ID(C_Element_ID);
			// Parent Account. //mguerrero
			if(StringUtils.isNotBlank(na.getParentValue())) {
				int parentId = DB.getSQLValue(na.get_TrxName(), 
						"SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_Element_ID=? AND Value=? "
						, C_Element_ID, na.getParentValue());
				if(parentId > 0){
					na.setParentElementValue_ID(parentId);
				}
			}
			if (!na.save()) {
				return false;
			}
		}
		return true;
	}   //  saveAccounts

	/**
	 *  Get ElementValue
	 * 	@param key key
	 *  @return 0 if error
	 */
	public int getC_ElementValue_ID(String key) {
		MElementValue na = (MElementValue) this.get(key);
		if (na == null) {
			return 0;
		}
		return na.getC_ElementValue_ID();
	}  //  getC_ElementValue_ID

}   //  NaturalAccountMap
