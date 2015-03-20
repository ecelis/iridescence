package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla de Subtipos de Producto.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/02/09 23:31:32 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.3 $
 */
public class ImportExmeTipoProd extends SvrProcess {
    
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
    public ImportExmeTipoProd() {
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
			sql = new StringBuffer ("DELETE I_EXME_TipoProd "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated, 	ProductType
		sql = new StringBuffer ("UPDATE I_EXME_TipoProd "
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
		int noInsertPO = 0;
		int noUpdatePO = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_EXME_TipoProd_ID, EXME_TipoProd_ID "
			+ "FROM I_EXME_TipoProd WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		try
		{
			//	Insertar subtipo de producto a partir de la importacion
			PreparedStatement pstmt_insertProduct = conn.prepareStatement
				("INSERT INTO EXME_TipoProd (EXME_TipoProd_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "Value,Name,Description) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ "Value,Name,Description "
				+ "FROM I_EXME_TipoProd "
				+ "WHERE I_EXME_TipoProd_ID=?");


			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_EXME_TipoProd SET I_IsImported='Y', EXME_TipoProd_ID=?, "
				+ "Updated=SysDate, Processed='Y' WHERE I_EXME_TipoProd_ID=?");

			//
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_EXME_TipoProd_ID = rs.getInt(1);
				int EXME_TipoProd_ID = rs.getInt(2);
				//boolean newProduct = EXME_TipoProd_ID == 0;
				log.fine("I_EXME_TipoProd_ID=" + I_EXME_TipoProd_ID + ", EXME_TipoProd_ID=" + EXME_TipoProd_ID);

					try
					{
						EXME_TipoProd_ID = DB.getNextID(m_AD_Client_ID, "EXME_TipoProd", null);
						if (EXME_TipoProd_ID <= 0)
							throw new DBException("No Next ID (" + EXME_TipoProd_ID + ")");
						pstmt_insertProduct.setInt(1, EXME_TipoProd_ID);
						pstmt_insertProduct.setInt(2, I_EXME_TipoProd_ID);
						//
						no = pstmt_insertProduct.executeUpdate();
						log.finer("Insert Product = " + no);
						noInsert++;
					}
					catch (Exception ex)
					{
						log.warning("Insertando subtipo de producto - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_TipoProd i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insertando subtipo producto: " + ex.toString()))
							.append("WHERE I_Product_ID=").append(I_EXME_TipoProd_ID);
						DB.executeUpdate(sql.toString());
						continue;
						
					}
				}
			if (rs != null)
				rs.close ();
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
    		rs = null;
			rs=null;
			pstmt=null;

			//
    		if (pstmt_insertProduct != null)
    			pstmt_insertProduct.close();
    		if (pstmt_setImported != null)
    			pstmt_setImported.close();

    		pstmt_insertProduct= null;
    		pstmt_setImported= null;
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
			}
			catch (SQLException ex)
			{
			}
			log.log(Level.SEVERE, "doIt", e);
			throw new Exception ("doIt", e);
		}
		finally
		{
			if (conn != null)
				conn.close();
			conn = null;
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_Product "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@M_Product_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@M_Product_ID@: @Updated@");
		addLog (0, null, new BigDecimal (noInsertPO), "@M_Product_ID@ @Purchase@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdatePO), "@M_Product_ID@ @Purchase@: @Updated@");
		return "";
        
    }

}
