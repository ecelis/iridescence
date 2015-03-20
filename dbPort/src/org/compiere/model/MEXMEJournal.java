/**
 * 
 */
package org.compiere.model;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Sistemas
 * @deprecated
 */
public class MEXMEJournal { 
//
//extends MJournal{
//	
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * 	Standard Constructor
//	 *	@param ctx context
//	 *	@param GL_Journal_ID id
//	 *	@param trxName transaction
//	 */
//	public MEXMEJournal (Properties ctx, int GL_Journal_ID, String trxName)
//	{
//		super (ctx, GL_Journal_ID, trxName);
//		if (GL_Journal_ID == 0)
//		{
//			setCurrencyRate (Env.ONE);
//			setDateAcct (DB.getTimestampForOrg(ctx));
//			setDateDoc (DB.getTimestampForOrg(ctx));
//			setDocAction (DOCACTION_Complete);
//			setDocStatus (DOCSTATUS_Drafted);
//			setPostingType (POSTINGTYPE_Actual);
//			setTotalCr (Env.ZERO);
//			setTotalDr (Env.ZERO);
//			setIsApproved (false);
//			setIsPrinted (false);
//			setPosted (false);
//			setProcessed(false);
//		}
//	}	//	MJournal
//		

}
