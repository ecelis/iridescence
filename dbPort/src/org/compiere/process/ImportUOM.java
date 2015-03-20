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
 * Proceso de importaci&oacute;n de la tabla de Unidades de Medida.<p>
 * Creado: 11/Mar/2005<p>
 * Modificado: $Date: 2006/10/11 17:12:08 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author miguel angel rojas
 * @version $Revision: 1.4 $
 */
public class ImportUOM extends SvrProcess {
    
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
    public ImportUOM() {
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
			sql = new StringBuffer ("DELETE I_UOM "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_UOM "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString());
		log.info("Reset=" + no);
		
		// Establecer Unidad de medida

		sql = new StringBuffer ("UPDATE I_UOM i SET C_UOM_ID=(SELECT C_UOM_ID FROM C_UOM u " +
				"WHERE TRIM(i.name)=TRIM(u.name) " +
				"AND i.AD_Client_ID IN (u.AD_Client_ID)) " +
				"WHERE C_UOM_ID IS NULL " +
				"AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString());

		log.fine("Establecer unidad de medida=" + no);


		log.config("Unidad de medida no valida=" + no);
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
 		//int noInsertPO = 0;
		//int noUpdatePO = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_UOM_ID, C_UOM_ID "
			+ "FROM I_UOM WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			//	Insertar categoria de productos a partir de la importacion
			PreparedStatement pstmt_insertProductCategory = conn.prepareStatement
				("INSERT INTO C_UOM (C_UOM_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "X12DE355, UOMSymbol, Name, StdPrecision, CostingPrecision) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,"
				+ "TRIM(X12DE355), TRIM(UOMSymbol), Name, StdPrecision, CostingPrecision "
				+ "FROM I_UOM "
				+ "WHERE I_UOM_ID=?");


			//	Actualiza Unidad de medida desde la importacion
			PreparedStatement pstmt_updateProductCategory = conn.prepareStatement
				("UPDATE C_UOM "
				+ "SET (X12DE355,UOMSymbol,Name,StdPrecision,CostingPrecision,Updated,UpdatedBy)= "
				+ "(SELECT TRIM(X12DE355),TRIM(UOMSymbol),Name,StdPrecision,CostingPrecision,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy"
				+ " FROM I_UOM WHERE I_UOM_ID=?) "
				+ "WHERE C_UOM_ID=?");

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_UOM SET I_IsImported='Y', C_UOM_ID=?, "
				+ "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_UOM_ID=?");

			//
			pstmt = DB.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_UOM_ID = rs.getInt(1);
				int C_UOM_ID = rs.getInt(2);
				boolean newUOM = C_UOM_ID == 0;
				log.fine("I_UOM_ID=" + I_UOM_ID + ", C_UOM_ID=" + C_UOM_ID);

				//	Unidad de medida
				if (newUOM)			//	Insertar nueva unidad de medida
				{
					try
					{
						C_UOM_ID = DB.getNextID(m_AD_Client_ID, "C_UOM", null);
						if (C_UOM_ID <= 0)
							throw new DBException("No Next ID (" + C_UOM_ID + ")");
						pstmt_insertProductCategory.setInt(1, C_UOM_ID);
						pstmt_insertProductCategory.setInt(2, I_UOM_ID);
						//
						no = pstmt_insertProductCategory.executeUpdate();
						log.finer("Insertar unidad de medida = " + no);
						noInsert++;
					}
					catch (Exception ex)
					{
						log.warning("Insertando unidad de medida - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_UOM u "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insertando unidad de medida: " + ex.toString()))
							.append("WHERE I_UOM_ID=").append(I_UOM_ID);
						DB.executeUpdate(sql.toString());
						continue;
					}
				}
				else					//	Update ProductCategory
				{
					pstmt_updateProductCategory.setInt(1, I_UOM_ID);
					pstmt_updateProductCategory.setInt(2, C_UOM_ID);
					try
					{
						no = pstmt_updateProductCategory.executeUpdate();
						log.finer("Actualizar Unidad de Medida = " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
						log.warning("Actualizar Unidad de Medida - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_UOM u "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Actualizar Unidad de Medida: " + ex.toString()))
							.append("WHERE I_UOM_ID=").append(I_UOM_ID);
						DB.executeUpdate(sql.toString());
						continue;
					}
					
				}
				
				//	Update I_Product_Category
				pstmt_setImported.setInt(1, C_UOM_ID);
				pstmt_setImported.setInt(2, I_UOM_ID);
				no = pstmt_setImported.executeUpdate();
				//
				conn.commit();
			}
			rs.close();
			pstmt.close();

			//
			pstmt_insertProductCategory.close();
			pstmt_updateProductCategory.close();
			pstmt_setImported.close();
            pstmt_insertProductCategory=null;
            pstmt_updateProductCategory=null;
            pstmt_setImported=null;
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
			catch (SQLException ex)
			{
			}
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
		sql = new StringBuffer ("UPDATE I_UOM "
			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_UOM_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_UOM_ID@: @Updated@");
		
		return "";
        
    }

}
