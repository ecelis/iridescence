package org.compiere.process;



import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;



/**
 * Proceso de importaci&oacute;n de la tabla de Conversiones
 * de Unidad de Medida.<p>
 * Creado: 22/Mar/2005<p>
 * Modificado: $Date: 2005/03/23 01:23:39 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class ImportUomConversion extends SvrProcess {
    
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
    public ImportUomConversion() {
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
			sql = new StringBuffer ("DELETE I_Uom_Conversion "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_Uom_Conversion "
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
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Reset=" + no);
		
		//	Set Uom
		sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET X12de355=(SELECT X12de355 FROM C_Uom u WHERE u.IsDefault='Y'"
			+ " AND u.AD_Client_ID=i.AD_Client_ID ");
		
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		
		sql.append("WHERE X12de355 IS NULL AND C_Uom_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),null);
		log.fine("Set Uom Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET C_Uom_ID=(SELECT C_Uom_ID FROM C_Uom u"
			+ " WHERE i.X12de355=u.X12de355 AND u.AD_Client_ID=i.AD_Client_ID) "
			+ "WHERE C_Uom_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Uom=" + no);
		//
		sql = new StringBuffer ("UPDATE I_Uom_Conversion "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Uom, ' "
			+ "WHERE C_Uom_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid Uom=" + no);
		
		//	Set Uom To
		sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET X12de355To=(SELECT X12de355 FROM C_Uom u WHERE u.IsDefault='Y'"
			+ " AND u.AD_Client_ID=i.AD_Client_ID ");
		
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		
		sql.append("WHERE X12de355To IS NULL AND C_Uom_To_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Uom To Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET C_Uom_To_ID=(SELECT C_Uom_ID FROM C_Uom u"
			+ " WHERE i.X12de355To=u.X12de355 AND u.AD_Client_ID=i.AD_Client_ID) "
			+ "WHERE C_Uom_To_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Uom To=" + no);
		//
		sql = new StringBuffer ("UPDATE I_Uom_Conversion "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Uom To, ' "
			+ "WHERE C_Uom_To_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid Uom To=" + no);

		// Set Product
		sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p"
			+ " WHERE p.Value=i.M_Product_Value "
			+ " AND p.AD_Client_ID = i.AD_Client_ID) "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Product=" + no);
		//
		/*sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "
			+ "WHERE M_Product_ID IS NULL "
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("Invalid Product=" + no);*/
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		//int noInsertPO = 0;
		//int noUpdatePO = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_Uom_Conversion_ID, C_Uom_Conversion_ID, "
			+ "M_Product_ID, C_Uom_ID, MultiplyRate, DivideRate "
			+ "FROM I_Uom_Conversion WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
		{
			//	Insertar conversiones de uom a partir de la importacion
			PreparedStatement pstmt_insertUomConversion = conn.prepareStatement
				("INSERT INTO C_Uom_Conversion (C_Uom_Conversion_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "C_Uom_ID,C_Uom_To_ID,MultiplyRate,DivideRate,M_Product_ID) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,"
				+ "C_Uom_ID,C_Uom_To_ID,MultiplyRate,DivideRate,M_Product_ID "
				+ "FROM I_Uom_Conversion "
				+ "WHERE I_Uom_Conversion_ID=?");


			//	Update uom conversion from Import
			PreparedStatement pstmt_updateUomConversion = conn.prepareStatement
				("UPDATE C_Uom_Conversion "
				+ "SET (Updated,UpdatedBy,C_Uom_ID, C_Uom_To_ID, MultiplyRate, DivideRate, M_Product_ID)= "
				+ "(SELECT "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,C_Uom_ID, C_Uom_To_ID, MultiplyRate, DivideRate, M_Product_ID"
				+ " FROM I_Uom_Conversion WHERE I_Uom_Conversion_ID=?) "
				+ "WHERE C_Uom_Conversion_ID=?");

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_Uom_Conversion SET I_IsImported='Y', C_Uom_Conversion_ID=?, "
				+ "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_Uom_Conversion_ID=?");

			//
			pstmt = DB.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_Uom_Conversion_ID = rs.getInt(1);
				int C_Uom_Conversion_ID = rs.getInt(2);
				int M_Product_ID = rs.getInt(3);
				
				boolean newUomConversion = C_Uom_Conversion_ID == 0;
				log.fine("I_Uom_Conversion_ID=" + I_Uom_Conversion_ID + ", C_Uom_Conversion_ID=" + C_Uom_Conversion_ID);

				//	Conversiones de Unidad de Medida
				if (newUomConversion)			//	Insert new Uom Conversion
				{
					try
					{
						C_Uom_Conversion_ID = DB.getNextID(m_AD_Client_ID, "C_UOM_Conversion", null);
						if (C_Uom_Conversion_ID <= 0)
							throw new DBException("No Next ID (" + C_Uom_Conversion_ID + ")");
						
						//Si existe el producto, La UdM debe ser la de almacenamiento del producto
						if(M_Product_ID!=0) {
							MProduct producto = MProduct.get(getCtx(), M_Product_ID); 
							if(producto.getC_UOM_ID()!=rs.getInt("C_Uom_ID")) {
								log.warning("La Unidad de Medida debe ser la de almacenamiento del producto");
								sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
									.append(DB.TO_STRING("Insert Uom Conversion: " +
											"La Unidad de Medida debe ser la de almacenamiento del producto"))
									.append(" WHERE I_Uom_Conversion_ID=").append(I_Uom_Conversion_ID);
								DB.executeUpdate(sql.toString());
								continue;
							}
						}
						
						//se remueven las validaciones por que los factores de multiplicacion y division no deben estar limitados
						//solicitado por hgutierrez
						//Multiplicar por fracciones o dividir entre enteros
					/*	if(rs.getDouble("MultiplyRate")>1) {
							log.warning("La Tasa de Multiplicacion debe ser una fraccion");
							sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
								.append(DB.TO_STRING("Insert Uom Conversion: " +
										"La Tasa de Multiplicacion debe ser una fraccion"))
								.append(" WHERE I_Uom_Conversion_ID=").append(I_Uom_Conversion_ID);
							DB.executeUpdate(sql.toString());
							continue;
						}
						
						//Multiplicar por fracciones o dividir entre enteros
						if(rs.getDouble("DivideRate")<1) {
							log.warning("La Tasa de Division debe ser mayor o igual a 1");
							sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
								+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
								.append(DB.TO_STRING("Insert Uom Conversion: " +
										"La Tasa de Division debe ser mayor o igual a 1"))
								.append(" WHERE I_Uom_Conversion_ID=").append(I_Uom_Conversion_ID);
							DB.executeUpdate(sql.toString());
							continue;
						}
						*/
						pstmt_insertUomConversion.setInt(1, C_Uom_Conversion_ID);
						pstmt_insertUomConversion.setInt(2, I_Uom_Conversion_ID);
						//
						no = pstmt_insertUomConversion.executeUpdate();
						log.finer("Insert Uom Conversion = " + no);
						noInsert++;
					}
					catch (Exception ex)
					{
						log.warning("Insert Uom Conversion  - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insert Uom Conversion: " + ex.toString()))
							.append(" WHERE I_Uom_Conversion_ID=").append(I_Uom_Conversion_ID);
						DB.executeUpdate(sql.toString(),null);
						continue;
					}
				}
				else					//	Update Uom Conversion
				{
					pstmt_updateUomConversion.setInt(1, I_Uom_Conversion_ID);
					pstmt_updateUomConversion.setInt(2, C_Uom_Conversion_ID);
					try
					{
						no = pstmt_updateUomConversion.executeUpdate();
						log.finer("Update Uom Conversion = " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
						log.warning("Update Uom Conversion - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_Uom_Conversion i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Uom Conversion: " + ex.toString()))
							.append(" WHERE I_Uom_Conversion_ID=").append(I_Uom_Conversion_ID);
						DB.executeUpdate(sql.toString(),null);
						continue;
					}
					
				}
				
				//	Update I_Uom_Conversion
				pstmt_setImported.setInt(1, C_Uom_Conversion_ID);
				pstmt_setImported.setInt(2, I_Uom_Conversion_ID);
				no = pstmt_setImported.executeUpdate();
				//
				conn.commit();
			}
			rs.close();
			pstmt.close();

			//
			pstmt_insertUomConversion.close();
			pstmt_updateUomConversion.close();
			pstmt_setImported.close();
            pstmt_insertUomConversion=null;
            pstmt_updateUomConversion=null;
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
			log.log(Level.SEVERE, "doIt", e);
            rs=null;
            pstmt=null;
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
		sql = new StringBuffer ("UPDATE I_Uom_Conversion "
			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),null);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_Uom_Conversion_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_Uom_Conversion_ID@: @Updated@");
		
		return "";
        
    }

}