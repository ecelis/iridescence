/**
 * 
 */
package org.compiere.process;

/**
 * @author lama
 */
public interface Worklist {

	public static final String	ACTION_AUTHENTICATE	= "AU";
	public static final String	ACTION_DISCONTINUE	= "DI";
	public static final String	ACTION_REVIEW		= "RV";
	public static final String	ACTION_RENEW		= "RN";

	/**
	 * Get Patient name
	 * 
	 * @return Patient name
	 */
	public String getPatientName();

	/**
	 * Get Summary
	 * 
	 * @return Summary of order
	 */
	public String getSummaryDetail();

	/**
	 * Process action
	 * 
	 * @return true if success
	 */
	public boolean setAction(String action);

	/**
	 * Type of record, the table name can be used.
	 * 
	 * @return
	 */
	public String getRecordType();
}
