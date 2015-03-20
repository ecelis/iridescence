package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MProductCategory;
import org.compiere.model.X_I_Product_Category;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * Proceso de importaci&oacute;n de la tabla de Categorias de Producto.<p>
 * Creado: 11/Mar/2005<p>
 * Modificado: $Date: 2005/03/18 15:12:02 $<p>
 * Por: $Author: mrojas $<p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.3 $
 */
public class ImportProductCategory extends SvrProcess {
    
    /**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	//private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;
    
    /**
     * Constructor por defecto.
     */
    public ImportProductCategory() {
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
			sql = new StringBuffer ("DELETE I_Product_Category "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_Product_Category "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Reset=" + no);
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		//int noInsertPO = 0;
		//int noUpdatePO = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_Product_Category_ID, M_Product_Category_ID "
			+ "FROM I_Product_Category WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			//	Insertar categoria de productos a partir de la importacion
			PreparedStatement pstmt_insertProductCategory = conn.prepareStatement
				("INSERT INTO M_Product_Category (M_Product_Category_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "Value,Name,PlannedMargin) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ "Value,Name,0 "
				+ "FROM I_Product_Category "
				+ "WHERE I_Product_Category_ID=?");


			//	Update Product Category from Import
			PreparedStatement pstmt_updateProductCategory = conn.prepareStatement
				("UPDATE M_Product_Category "
				+ "SET (Value,Name,Updated,UpdatedBy)= "
				+ "(SELECT Value,Name,SysDate,UpdatedBy"
				+ " FROM I_Product_Category WHERE I_Product_Category_ID=?) "
				+ "WHERE M_Product_Category_ID=?");

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_Product_Category SET I_IsImported='Y', M_Product_Category_ID=?, "
				+ "Updated=SysDate, Processed='Y' WHERE I_Product_Category_ID=?");

			//
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_Product_Category_ID = rs.getInt(1);
				int M_Product_Category_ID = rs.getInt(2);
				boolean newProductCategory = M_Product_Category_ID == 0;
				log.fine("I_Product_Category_ID=" + I_Product_Category_ID + ", M_Product_Category_ID=" + M_Product_Category_ID);

				//	Product Category
				if (newProductCategory)			//	Insert new ProductCategory
				{
					try
					{
					    /*
						M_Product_Category_ID = DB.getNextID(m_AD_Client_ID, "M_Product_Category", null);
						if (M_Product_Category_ID <= 0)
							throw new DBException("No Next ID (" + M_Product_Category_ID + ")");
						pstmt_insertProductCategory.setInt(1, M_Product_Category_ID);
						pstmt_insertProductCategory.setInt(2, I_Product_Category_ID);
						//
						no = pstmt_insertProductCategory.executeUpdate();
						log.finer("Insert Product Category = " + no);
						noInsert++;
						*/
					    X_I_Product_Category pC = new X_I_Product_Category(getCtx(), I_Product_Category_ID, null);
					    MProductCategory p = new MProductCategory(pC);
					    
					    if(p.save()) {
					        M_Product_Category_ID = p.getM_Product_Category_ID();
					        log.finer("Insert Product");
							noInsert++;
					    } else {
							sql = new StringBuffer ("UPDATE I_Product_Category i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insert Product category failed"))
								.append("WHERE I_ProductCategory_ID=").append(I_Product_Category_ID);
							DB.executeUpdate(sql.toString(), null);
							continue;
						}
					    
					}
					catch (Exception ex)
					{
						log.warning("Insertando categoria de producto - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_Product_Category i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insertando categoria producto: " + ex.toString()))
							.append("WHERE I_Product_Category_ID=").append(I_Product_Category_ID);
						DB.executeUpdate(sql.toString(), null);
						continue;
					}
				}
				else					//	Update ProductCategory
				{
					pstmt_updateProductCategory.setInt(1, I_Product_Category_ID);
					pstmt_updateProductCategory.setInt(2, M_Product_Category_ID);
					try
					{
						no = pstmt_updateProductCategory.executeUpdate();
						log.finer("Update Product Category = " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
						log.warning("Update Product Category - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_Product_Category i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Product Category: " + ex.toString()))
							.append("WHERE I_Product_Category_ID=").append(I_Product_Category_ID);
						DB.executeUpdate(sql.toString(), null);
						continue;
					}
					
				}
				
				//	Update I_Product_Category
				pstmt_setImported.setInt(1, M_Product_Category_ID);
				pstmt_setImported.setInt(2, I_Product_Category_ID);
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
		sql = new StringBuffer ("UPDATE I_Product_Category "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@M_Product_Category_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@M_Product_Category_ID@: @Updated@");
		
		return "";
        
    }

}
