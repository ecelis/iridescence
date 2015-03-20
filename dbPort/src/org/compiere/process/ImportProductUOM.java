package org.compiere.process;



import java.math.BigDecimal;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.logging.Level;

import org.compiere.model.X_EXME_Product_UOM;

import org.compiere.model.X_I_EXME_Product_UOM;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.compiere.util.Trx;



/**
 *	Importaci&oacute;n de Presentaciones de Salida
 *
 * 	@author 	Gisela Lee
 * 	@version 	$Id: ImportProductUOM.java,v 1.5 2006/10/11 17:12:08 vgarcia Exp $
 */
public class ImportProductUOM extends SvrProcess
{
    /**	Static Logger				*/
    private static CLogger		s_log = CLogger.getCLogger (ImportProductUOM.class);
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
				log.log(Level.SEVERE, "ImportProductUOM.prepare - Unknown Parameter: " + name);
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
			sql = new StringBuffer ("DELETE I_EXME_Product_Uom "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString());
		log.fine("Reset=" + no);

		// establecer producto
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom i "
			+ "SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p"
			+ " WHERE TRIM(i.M_Product_Value) = TRIM(p.Value) "
			+ " AND p.AD_Client_ID IN (i.AD_Client_ID)) "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer producto=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Producto no valido, ' "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("Producto no valido=" + no);
		
		// establecer almacen
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom i "
			+ "SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w"
			+ " WHERE TRIM(i.M_Warehouse_Value) = TRIM(w.Value)"
			+ " AND w.AD_Client_ID IN (i.AD_Client_ID)) "
			+ "WHERE M_Warehouse_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer almacen=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Almacen no valido, ' "
			+ "WHERE M_Warehouse_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("Almacen no valido=" + no);
		
		// establecer unidad de medida
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom i "
				+ "SET X12DE355 = "
				+ "(SELECTX12DE355 FROM C_UOM u WHERE u.IsDefault='Y' AND u.AD_Client_ID IN (0,i.AD_Client_ID) ");
		
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		
		sql.append(" WHERE X12DE355 IS NULL AND C_UOM_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer UDM Default=" + no);
		
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom i "
			+ "SET C_UOM_ID=(SELECT C_UOM_ID FROM C_UOM u"
			+ " WHERE TRIM(i.X12DE355) = TRIM(u.X12DE355)"
			+ " AND u.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE C_UOM_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.fine("Establecer UDM=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=UDM no valida, ' "
			+ "WHERE C_UOM_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.config("UDM no valido=" + no);
		

		//	-------------------------------------------------------------------
		int noInsert = 0;
		//int noUpdate = 0;

		//	Go through Records
		sql = new StringBuffer ("SELECT I_EXME_Product_Uom_ID "
			+ "FROM I_EXME_Product_Uom "
			+ "WHERE I_IsImported='N'").append(clientCheck);
		
		String sql1 =  "SELECT M_PRODUCT_ID, M_WAREHOUSE_ID, C_UOM_ID  FROM I_EXME_Product_Uom " +
		"WHERE I_EXME_Product_Uom_ID=? and I_IsImported='N'" +clientCheck;

        String sql2  = "SELECT * FROM  EXME_Product_Uom WHERE M_PRODUCT_ID=? AND M_WAREHOUSE_ID=? and C_UOM_ID=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;
        ResultSet rs2= null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {

                String trxName   = Trx.createTrxName();
			    int iProductUomID = rs.getInt("I_EXME_Product_Uom_ID");
			    
			    ps1 = DB.prepareStatement(sql1);
				ps1.setInt(1, iProductUomID);
				
				rs1 = ps1.executeQuery();
				
				if(rs1.next()){
					ps2 = DB.prepareStatement(sql2);
					ps2.setInt(1, rs1.getInt(1));
					ps2.setInt(2, rs1.getInt(2));
					ps2.setInt(3, rs1.getInt(3));
					
					rs2 = ps2.executeQuery();
                
    			    try {
    			    	s_log.log(Level.INFO,"registro: " + rs.getRow());
    			        s_log.log(Level.INFO,"trxName: " + trxName);
    
    			        X_I_EXME_Product_UOM iProdUom = 
    			            new X_I_EXME_Product_UOM(getCtx(), iProductUomID, trxName);
    	        
    			        X_EXME_Product_UOM mProdUom = null;
    			        
    			        if(rs2.next()){
    		        	
    			        	mProdUom = new X_EXME_Product_UOM(getCtx(), rs2, trxName);
    				        //establecer los valores
    				        mProdUom.setM_Product_ID(iProdUom.getM_Product_ID());
    				        mProdUom.setM_Warehouse_ID(iProdUom.getM_Warehouse_ID());
    				        mProdUom.setC_UOM_ID(iProdUom.getC_UOM_ID());
    		        
    			        } else{
    		        	
    			            mProdUom = new X_EXME_Product_UOM(getCtx(), 0, trxName);
    				        //establecer los valores
    				        mProdUom.setM_Product_ID(iProdUom.getM_Product_ID());
    				        mProdUom.setM_Warehouse_ID(iProdUom.getM_Warehouse_ID());
    				        mProdUom.setC_UOM_ID(iProdUom.getC_UOM_ID());
    		        	
    			        }
                        
    			        if(!mProdUom.save(trxName)) {
    			            if(noInsert > 0)
    				            noInsert--;
    				        no++;
    				        DB.rollback(false, trxName);
    				        Trx trx = Trx.get(trxName, false);
    				        trx.close();
    				        log.log(Level.WARNING,"Insertando Presentaciones de Salida: no se inserto el registro");
    				        sql = new StringBuffer("UPDATE I_EXME_Product_Uom " +
    				                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
    				                .append("'No se inserto el registro'")
    				                .append(" WHERE I_EXME_Product_Uom_ID=").append(iProductUomID);
    				        DB.executeUpdate(sql.toString());
    				        continue;
    			        }
    			        
    			        //lo marcamos como importado y procesado
    			        iProdUom.setI_IsImported(true);
    			        iProdUom.setProcessed(true);
    			        
    			        if(!iProdUom.save(trxName)) {
    				        log.log(Level.WARNING,"No se actualizo el registro: " + iProdUom);
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
    			        log.log(Level.WARNING,"Insertando Presentaciones de Salida: ", e);
    			        sql = new StringBuffer("UPDATE I_EXME_Product_Uom " +
    			                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                                .append(DB.TO_STRING("Insertar Presentaciones de Salida: " + e.toString()))
    			                .append("WHERE I_EXME_Product_Uom_ID=").append(iProductUomID);
    			        DB.executeUpdate(sql.toString());
    			    }
                }
                rs1.close();
                rs2.close();
                ps1.close();
                ps2.close();
			}	//	for all I_EXME_Medico
			
		}
		catch (SQLException e)
		{
            try
            {
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
                if(rs1 != null)
                    rs1.close();
                if(rs2 != null)
                    rs2.close();
                if(ps1 != null)
                    ps1.close();
                if(ps2 != null)
                    ps2.close();
            }
            catch (SQLException ex){}
            log.log(Level.SEVERE, "doIt", e);
            rs=null;
            pstmt=null;
            rs1=null;
            rs2=null;
            ps1=null;
            ps2=null;
			throw new Exception ("ImportProductUOM.doIt", e);
		}
        finally
        {
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            if(rs1 != null)
                rs1.close();
            if(rs2 != null)
                rs2.close();
            if(ps1 != null)
                ps1.close();
            if(ps2 != null)
                ps2.close();
            rs=null;
            pstmt=null;
            rs1=null;
            rs2=null;
            ps1=null;
            ps2=null;
        }

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_Product_Uom "
			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_Product_UOM_ID@: @Inserted@");
		return "";
	}	//	doIt

}	//	ImportProductUOM