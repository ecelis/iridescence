package org.compiere.process;

import java.math.*;
import java.sql.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Importaci&oacute;n de M&eacute;dicos
 *
 * 	@author 	miguel angel rojas 
 * 	@version 	$Id: ImportExmeProductSActiva.java,v 1.4 2006/02/09 23:29:52 gisela Exp $
 */
public class ImportExmeProductSActiva extends SvrProcess
{
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (ImportExmeProductSActiva.class);
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			 else if (name.equals("AD_Org_ID"))
					m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "ImportExmeMedico.prepare - Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_ProductSActiva "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString());
		log.fine("Reset=" + no);

		
		//	establecer sustancia activa
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva i "
			+ "SET EXME_SActiva_ID=(SELECT EXME_SActiva_ID FROM EXME_SActiva s"
			+ " WHERE TRIM(i.EXME_SActiva_Value)=TRIM(s.Value) AND s.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE EXME_SActiva_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer sustancia activa=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Sustancia Activa no valida, ' "
			+ "WHERE EXME_SActiva_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("Sustancia Activa no valida=" + no);

		// establecer producto
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva i "
			+ "Set M_Product_ID=(SELECT M_Product_ID FROM M_Product p"
			+ " WHERE TRIM(p.Value)=TRIM(i.M_Product_Value) "
			+ " AND p.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer producto=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Producto no valido, ' "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("Producto no valido=" + no);
		

		//	-------------------------------------------------------------------
		int noInsert = 0;
		//int noUpdate = 0;

		//	Go through Records
		sql = new StringBuffer ("SELECT I_EXME_ProductSActiva_ID, M_Product_ID,"
			+ "EXME_SActiva_ID, EXME_SActiva_ID, "
			+ "Description "
			+ "FROM I_EXME_ProductSActiva "
			+ "WHERE I_IsImported='N'").append(clientCheck);
		//Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		try
		{
			
			//	Set Imported = Y
			/*PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_EXME_Medico SET I_IsImported='Y',"
				+ " EXME_Medico_ID=?, C_Location_ID=?, C_Location_ID_Cons = ? AD_User_ID=?, "
				+ " Updated=SysDate, Processed='Y' WHERE I_EXME_Medico_ID=?");*/
			//
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
			    String trxName            = Trx.createTrxName();
			    int iExmeProductSActivaID = rs.getInt("I_EXME_ProductSActiva_ID");
			    //int mProductID            = rs.getInt("M_Product_ID");
			    //int exmeSActivaID         = rs.getInt("EXME_SActiva_ID");
			    
			    try {
			    	s_log.log(Level.SEVERE,"registro: " + rs.getRow());
			       // System.out.println("registro: " + rs.getRow());
			        s_log.log(Level.SEVERE,"trxName: " + trxName);
			       // System.out.println("trxName: " + trxName);
			        X_I_EXME_ProductSActiva iPSA = 
			            new X_I_EXME_ProductSActiva(getCtx(), iExmeProductSActivaID, trxName);
			        
			        
			        
			        MProductSActiva psa = new MProductSActiva(iPSA, trxName);
			        
			        if(!psa.save(trxName)) {
			            if(noInsert > 0)
				            noInsert--;
				        no++;
				        DB.rollback(false, trxName);
				        Trx trx = Trx.get(trxName, false);
				        trx.close();
				        log.log(Level.WARNING,"Insertando Producto - S. Activa: no se inserto el registro");
				        sql = new StringBuffer("UPDATE I_EXME_ProductSActiva " +
				                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
				                .append("'No se inserto el registro'")
				                .append(" WHERE I_EXME_ProductSActiva_ID=").append(iExmeProductSActivaID);
				        DB.executeUpdate(sql.toString());
				        continue;
			        }
			        
			        //lo marcamos como importado y procesado
			        iPSA.setI_IsImported(true);
			        iPSA.setProcessed(true);
			        
			        if(!iPSA.save(trxName)) {
				        log.log(Level.WARNING,"No se actualizo el registro: " + iPSA);
				        DB.rollback(true, trxName);
				        if(noInsert > 0)
				            noInsert--;
				        no++;
			        } else {
			            DB.commit(true, trxName);
			            noInsert++;
			        }
			        
			        Trx trx = Trx.get(trxName, false);
			        trx.close();
			    
			    } catch (Exception e){
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        DB.rollback(false, trxName);
			        Trx trx = Trx.get(trxName, false);
			        trx.close();
			        log.log(Level.WARNING,"Insertando Prod. - S. Activa: ", e);
			        sql = new StringBuffer("UPDATE I_EXME_ProductSActiva " +
			                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insertar Medico: " + e.toString()))
			                .append("WHERE I_EXME_Medico_ID=").append(iExmeProductSActivaID);
			        DB.executeUpdate(sql.toString());
			    }
				
			}	//	for all I_EXME_Medico
			
			//actualizamos la tabla de importacion para indicar que ya corrio el proceso
			/*Iterator iterImportados = lstImportados.iterator();
			while(iterImportados.hasNext()) {
			    X_I_EXME_Medico iMedico = (X_I_EXME_Medico)iterImportados.next();
			    
			    if(!iMedico.save())
			        log.log(Level.WARNING,"No se actualizo el medico importado: " + iMedico);
			    
			}*/
		}
		catch (SQLException e)
		{
			throw new Exception ("ImportExmeProductSActiva.doIt", e);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_ProductSActiva "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_ProductSActiva_ID@: @Inserted@");
		return "";
	}	//	doIt

}	//	ImportBPartner
