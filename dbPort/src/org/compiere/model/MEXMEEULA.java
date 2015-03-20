/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.Msg;

/**
 * End User License Agreement business model.
 * @author mrojas
 */
public class MEXMEEULA extends X_EXME_EULA {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// the current EULA detail
	private MEXMEEULADt current = null;
	
	
	/**
	 * @param ctx
	 * @param EXME_EULA_ID
	 * @param trxName
	 */
	public MEXMEEULA(Properties ctx, int EXME_EULA_ID, String trxName) {
		super(ctx, EXME_EULA_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEULA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Get the current EULA detail.
	 * @return The current EUAL detail.
	 */
	public MEXMEEULADt getCurrent() {
		return current;
	}
	
	/**
	 * Sets the current EULA detail.
	 * @param current The current EULA detail.
	 */
	public void setCurrent(MEXMEEULADt current) {
		this.current = current;
	}
	
	

	/**
	 * Get the EULA from the context data. Loads the current detail.
	 * @param ctx The application context.
	 * @return The EULA for the organization in the context.
	 */
	public static MEXMEEULA getFromEnv(Properties ctx) {
		MEXMEEULA retVal = null;
		
		MEXMEEULADt det = MEXMEEULADt.getCurrent(ctx);
		if(det != null) {
			retVal = (MEXMEEULA) det.getEXME_EULA();
			retVal.setCurrent(det);
		}
		
		
		return retVal;
	}
	
	
	/**
	 * Gets the current HTML document for the EULA that match the current
	 * environment data. 
	 * @param ctx The Application environment.
	 * @return A String with the HTML document for the current EULA.
	 * @deprecated se usa funcionalidad de Attachement 
	 */
	public String getDocument() {
		String retVal = null;
		
		if(current == null)  {
			throw new MedsysException(Msg.getMsg(getCtx(), "NoCurrentEULA"));
		} else {
			byte[] document = current.getAttachmentData("html");
			
			if(document.length > 0) {
				retVal = new String(document);
			} else {
				throw new MedsysException(Msg.getMsg(getCtx(), "NoCurrentEULADocument"));
			}
		}
		
		return retVal;
	}
}
