/******************************************************************************
 * Expert Sistemas Comptucacionales
 * Medsys ERP and CRM intelligent
 *  Samuel Cardenas
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;
import org.compiere.util.DBTools;

/**
 *  @author Sam Cardenas
 *  @version $Id: ExplicitCleaner.java,v 1.2 2007/01/20 00:51:01 scardenas  $
 */
public class DBProcessKiller extends SvrProcess
{
	private int		p_AD_Client_ID = -1;
	
/**
 * 
 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Client_ID")){
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
				if (p_AD_Client_ID == 0 || p_AD_Client_ID==-1){
					try{
					log.log(Level.SEVERE, "Invalid Parameter is not possible to run process : " + name+" with AD_Client_ID "+ p_AD_Client_ID);
		            throw new Exception("Invalid AD_Client_ID for process, it can't be NULL, 0 or -1");
					}catch (Exception e){e.printStackTrace();}
				}
			}
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

/**
 * 
 */
	protected String doIt() throws Exception
	{
		log.info(" DBProcessKiller : doIt");
		log.info("doIt - AD_User_ID=" + p_AD_Client_ID);
		
		if (p_AD_Client_ID>0){
		DBTools.processKiller();
		return "Process Killer executed succesfully" ;
		}
		else
		return "Process Killer not executed" ;
	}
	


}