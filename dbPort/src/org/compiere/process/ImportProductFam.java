package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMEProductFam;
import org.compiere.model.X_I_EXME_ProductFam;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Proceso de importaci&oacute;n de la tabla de Familia de Productos.<p>
 * Creado: 01/Abril/2005<p>
 * Modificado: $Date: 2005/04/02 00:48:03 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */

public class ImportProductFam extends SvrProcess {
    
    /**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;
    
    /**
     * Constructor por defecto.
     */
    public ImportProductFam() {
        super();
    }
    
    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {
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
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
    }

    /**
     * Corre el proceso.
     * @return Un mensaje de estado
     * @throws Exception
     */
    protected String doIt() throws Exception {
        
        StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****
        //	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_ProductFam "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_ProductFam "
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
		log.info("Reset=" + no);
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		//int noInsertPO = 0;
		//int noUpdatePO = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_EXME_ProductFam_ID, EXME_ProductFam_ID "
			+ "FROM I_EXME_ProductFam WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
		{
			//	Insertar la familia de productos a partir de la importacion
			PreparedStatement pstmt_insertProductFam = conn.prepareStatement
				("INSERT INTO EXME_ProductFam (EXME_ProductFam_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "Value,Name,PlannedMargin) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ "Value,Name,0 "
				+ "FROM I_EXME_ProductFam "
				+ "WHERE I_EXME_ProductFam_ID=?");


			//	Update Product Fam from Import
			PreparedStatement pstmt_updateProductFam = conn.prepareStatement
				("UPDATE EXME_ProductFam "
				+ "SET (Value,Name,Updated,UpdatedBy)= "
				+ "(SELECT Value,Name,SysDate,UpdatedBy"
				+ " FROM I_EXME_ProductFam WHERE I_EXME_ProductFam_ID=?) "
				+ "WHERE EXME_ProductFam_ID=?");

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_EXME_ProductFam SET I_IsImported='Y', EXME_ProductFam_ID=?, "
				+ "Updated=SysDate, Processed='Y' WHERE I_EXME_ProductFam_ID=?");

			//
			pstmt = DB.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
           
			while (rs.next())
			{
				int I_EXME_ProductFam_ID = rs.getInt(1);
				int EXME_ProductFam_ID = rs.getInt(2);
				boolean newProductFam = EXME_ProductFam_ID == 0;
				log.fine("I_EXME_ProductFam_ID=" + I_EXME_ProductFam_ID + ", EXME_ProductFam_ID=" + EXME_ProductFam_ID);

				//	Familia de Productos
				if (newProductFam)			//	Insert new Product Fam
				{
					try
					{
						/*EXME_ProductFam_ID = DB.getNextID(m_AD_Client_ID, "EXME_ProductFam", null);
						if (EXME_ProductFam_ID <= 0)
							throw new DBException("No Next ID (" + EXME_ProductFam_ID + ")");
						pstmt_insertProductFam.setInt(1, EXME_ProductFam_ID);
						pstmt_insertProductFam.setInt(2, I_EXME_ProductFam_ID);
						//
						no = pstmt_insertProductFam.executeUpdate();
						log.finer("Insert Product Fam = " + no);
						noInsert++;*/
					    
					    X_I_EXME_ProductFam pF = new X_I_EXME_ProductFam(getCtx(), I_EXME_ProductFam_ID, null);
					    MEXMEProductFam p = new MEXMEProductFam(pF);
					    
					    if(p.save()) {
					        EXME_ProductFam_ID = p.getEXME_ProductFam_ID();
					        log.finer("Insert Product");
							noInsert++;
					    } else {
							sql = new StringBuffer ("UPDATE I_EXME_ProductFam i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insert Product category failed"))
								.append("WHERE I_EXME_ProductFam_ID=").append(I_EXME_ProductFam_ID);
							DB.executeUpdate(sql.toString());
							continue;
						}

					}
					catch (Exception ex)
					{
						log.warning("Insert Product Fam - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_ProductFam i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insert Product Fam: " + ex.toString()))
							.append("WHERE I_EXME_ProductFam_ID=").append(I_EXME_ProductFam_ID);
						DB.executeUpdate(sql.toString());
						continue;
					}
				}
				else	//	Update Product Fam
				{
					pstmt_updateProductFam.setInt(1, I_EXME_ProductFam_ID);
					pstmt_updateProductFam.setInt(2, EXME_ProductFam_ID);
					try
					{
						no = pstmt_updateProductFam.executeUpdate();
						log.finer("Update Product Fam = " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
						log.warning("Update Product Fam - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_ProductFam i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Product Fam : " + ex.toString()))
							.append("WHERE I_EXME_ProductFam_ID=").append(I_EXME_ProductFam_ID);
						DB.executeUpdate(sql.toString());
						continue;
					}
				}
				
				//	Update I_EXME_ProductFam
				pstmt_setImported.setInt(1, EXME_ProductFam_ID);
				pstmt_setImported.setInt(2, I_EXME_ProductFam_ID);
				no = pstmt_setImported.executeUpdate();
				//
				conn.commit();
			}
			rs.close();
			pstmt.close();

			//
			pstmt_insertProductFam.close();
			pstmt_updateProductFam.close();
			pstmt_setImported.close();
			//
			conn.close();
			conn = null;
		}
		catch (SQLException e)
		{
			try
			{
				if (conn != null)
					conn.close();
				conn = null;
				if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
			}
			catch (SQLException ex){}
            rs=null;
            pstmt=null;
			log.log(Level.SEVERE, "doIt", e);
			throw new Exception ("doIt", e);
		}
		finally
		{
			if (conn != null)
				conn.close();
			conn = null;
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            rs=null;
            pstmt=null;
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_ProductFam "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_ProductFam_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_ProductFam_ID@: @Updated@");
		
		return "";
    }
}