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
package org.compiere.process;

import java.math.*;
import java.sql.*;

import org.compiere.model.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Importar Paquete Base Version - Socio de Negocios 
 *	
 *  @author gisela lee
 *  @version $Id: ImportPaqBaseVersionBP,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ImportPaqBaseVersionBP extends SvrProcess
{
	
	/**	Client to be imported to			*/
	private int				p_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			p_DeleteOldImported = false;

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
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("doIt - AD_Client_ID=" + p_AD_Client_ID
			+ ",DeleteOldImported=" + p_DeleteOldImported);
		//
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + p_AD_Client_ID;
		//	****	Prepare	****

		//	Delete Old Imported
		if (p_DeleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_PaqBase_BP "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, Location, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (p_AD_Client_ID).append ("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = ").append(getAD_User_ID()).append(","
			+ " I_ErrorMsg = NULL,"
			+ " Processed = 'N',"	
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info ("Reset =" + no);

		//	Org
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP o "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org =" + no);
			
		//	PaqBase_Version_Name
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP i "
			+ "SET EXME_PaqBase_Version_Name = (SELECT EXME_PaqBase_Version_ID FROM EXME_PaqBase_Version c"
			+ " WHERE c.Name=i.EXME_PaqBase_Version_Name AND c.AD_Client_ID IN (0,i.AD_Client_ID) AND c.IsActive='Y') "
			+ "WHERE EXME_PaqBase_Version_ID IS NULL AND EXME_PaqBase_Version_Name IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no > 0)
			log.fine("Set PaqBase_Version_Name =" + no);
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP i "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid PaqBase_Version_Name, ' "
			+ "WHERE (EXME_PaqBase_Version_ID IS NULL"
			+ " OR NOT EXISTS (SELECT * FROM EXME_PaqBase_Version c "
				+ "WHERE i.EXME_PaqBase_Version_ID=c.EXME_PaqBase_Version_ID AND c.IsActive='Y'"
				+ " AND c.AD_Client_ID IN (0,i.AD_Client_ID)))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid EXME_PaqBase_Version =" + no);
		
		//	BPartner
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP i "
			+ "SET C_BPartner_ID = (SELECT C_BPartner_ID FROM C_BPartner c"
			+ "	WHERE c.Value=i.Value AND c.AD_Client_ID IN (0,i.AD_Client_ID) AND c.IsActive='Y') "
			+ "WHERE C_BPartner_ID IS NULL AND Value IS NOT NULL"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no > 0)
			log.fine("Set BPartner =" + no);
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP i "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid BPartner, ' "
			+ "WHERE (C_BPartner_ID IS NULL"
			+ " OR NOT EXISTS (SELECT * FROM C_BPartner c "
				+ "WHERE i.C_BPartner_ID=c.C_BPartner_ID AND c.IsActive='Y'"
				+ " AND c.AD_Client_ID IN (0,i.AD_Client_ID)))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid BPartner =" + no);

		commit();
		/*********************************************************************/

		int noInsert = 0;
		sql = new StringBuffer ("SELECT * FROM I_EXME_PaqBase_BP "
			+ "WHERE I_IsImported='N'").append (clientCheck)
			.append(" ORDER BY EXME_PaqBase_Version_ID, C_BPartner_ID");
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				X_I_EXME_PaqBase_BP imp = new X_I_EXME_PaqBase_BP (getCtx(), rs, get_TrxName());
				X_EXME_PaqBase_BP paq = new X_EXME_PaqBase_BP (getCtx(), 
						imp.getEXME_PaqBase_BP_ID(),
						get_TrxName());
				paq.setEXME_PaqBase_Version_ID(imp.getEXME_PaqBase_Version_ID());
				paq.setC_BPartner_ID(imp.getC_BPartner_ID());
				if (paq.save())
				{
					imp.setEXME_PaqBase_BP_ID(paq.getEXME_PaqBase_BP_ID());
					imp.setI_IsImported(true);
					imp.setProcessed(true);
					imp.save();
					noInsert++;
				}
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_BP "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noInsert), "@EXME_PaqBase_BP_ID@: @Inserted@");
		return "";
	}	//	doIt

}	//	ImportPaqBaseVersionBP
