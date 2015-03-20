/******************************************************************************
 * Expert Sistemas Comptucacionales
 * Medsys ERP and CRM intelligent
 *  Samuel Cardenas
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;
import org.compiere.util.EnviromentTools;
/**
 *	
 *	
 *  @author Sam Cardenas
 *  @version $Id: FinalObjects.java,v 1.2 2007/01/20 00:51:01 scardenas  $
 */
public class FinalObjects extends SvrProcess
{
	private int		p_AD_User_ID = -1;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perrform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("CleanVM Process : CleanVM : doIt");
		log.info("doIt - AD_User_ID=" + p_AD_User_ID);		
		EnviromentTools.callingFinal();
		return "finals invoked" ;
	}	//	doIt
}	//	FinalObjects
