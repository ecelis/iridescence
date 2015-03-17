package org.compiere.model;



/**
 *  Bean for not to bring every data<p>
 * 
 * Creado: 2008/05/10<p>
 * Modificado: $Author: scardenas $<p>
 * Fecha: $Date: 2008/05/10 05:05:17 $<p>
 *
 * @author samuel cardenas
 * @version $Revision: 1.1 $
 */
public class AccessCatalogBean{
	
	private String value=null;
	private int originalAccess=0;
	private int multiAccess=0;
	private int uniqueAccess=0;
	private int AD_Table_ID=0;
	private int AD_Table_AccessLevel=0;
	
	
	public int getMultiAccess() {
		return multiAccess;
	}
	public void setMultiAccess(int multiAccess) {
		this.multiAccess = multiAccess;
	}
	public int getOriginalAccess() {
		return originalAccess;
	}
	public void setOriginalAccess(int originalAccess) {
		this.originalAccess = originalAccess;
	}
	public int getUniqueAccess() {
		return uniqueAccess;
	}
	public void setUniqueAccess(int uniqueAccess) {
		this.uniqueAccess = uniqueAccess;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getAD_Table_AccessLevel() {
		return AD_Table_AccessLevel;
	}
	public void setAD_Table_AccessLevel(int table_AccessLevel) {
		AD_Table_AccessLevel = table_AccessLevel;
	}
	public int getAD_Table_ID() {
		return AD_Table_ID;
	}
	public void setAD_Table_ID(int table_ID) {
		AD_Table_ID = table_ID;
	}
	
	
}