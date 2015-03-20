/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MProductPO;
import org.compiere.model.PO;
import org.compiere.model.X_C_BPartner;
import org.compiere.model.X_I_EXME_Product_PO;
import org.compiere.model.X_M_Product;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Importar Costos en base al proveedor actual
 * Import Costs based in current vendor
 * @author twry
 */
public class ImportProductPO extends SvrProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	/** Reemplazar los datos previos */
	private boolean         replace = false;
	/** */
	private String table = StringUtils.EMPTY;
	/** */
	private String clientCheck =  StringUtils.EMPTY;
	
	/**
	 * 
	 */
//	public ImportProductPO() {
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("Sobreescribir"))
				replace = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "ImportProductPO.prepare - Unknown Parameter: " + name);
		}
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		log.log(Level.CONFIG, "Starting ImportProductPO.doIt import ....");
		
		clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		table = "I_EXME_Product_PO";
		
		// borrar los registros anteriores
		deleteOld();
		// Actualiza los datos obligatorios Client, Org, IsActive, Created/Updated
		updateRecords();
		// Actualiza el id del producto
		updateValue(X_M_Product.COLUMNNAME_M_Product_ID, "M_Product_Value", X_M_Product.Table_Name);
		// Actualiza la tabla del socio
		updateValue(X_C_BPartner.COLUMNNAME_C_BPartner_ID, "C_BPartner_Value", X_C_BPartner.Table_Name);
		
		// Actualizacion de la tabla de importacion
		//DB.commit(true, get_TrxName());
		
		// Actualiza las demas columna de udm 
		updateColumns();
				
		return insertUpdate();
	}
	
	/**
	 * Borrar los registros previamente cargados
	 */
	private void deleteOld(){
		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			StringBuffer sql = new StringBuffer ("DELETE ").append(table)
					.append("WHERE I_IsImported='Y'").append(clientCheck);
			int no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Impored =" + no);
		}
	}
	
	/**
	 * Actualizar las columnas obligatorias
	 */
	private void updateRecords(){
		// Set Client, Org, IsActive, Created/Updated
		StringBuffer sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),")
				.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
				.append(" IsActive = COALESCE (IsActive, 'Y'),")
				.append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),")
				.append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
				.append(" I_ErrorMsg = NULL,")
				.append(" I_IsImported = 'N' ")
				.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		int no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);
	}
	
	/**
	 * A nivel de cliente buscar el id por medio del value
	 * @param columnID
	 * @param columnValue
	 * @param tabla
	 */
	private boolean updateValue(String columnID, String columnValue, String tabla){
		//	Existing ? Match Value
		StringBuffer sql = new StringBuffer ("UPDATE ").append(table).append(" i ")
		.append(" SET  ").append(columnID)
		.append("       = (        ")
		.append("           SELECT ").append(columnID).append(" FROM ").append(tabla).append(" p     ")
		.append("           WHERE trim(p.Value) = trim(i.").append(columnValue).append(") AND p.AD_Client_ID IN (i.AD_Client_ID,0) ")
		.append("         )        ")
		.append(" WHERE i.").append(columnValue).append(" IS NOT NULL AND i.").append(columnID).append(" IS NULL ")
		.append(" AND   i.I_IsImported='N' AND i.AD_Client_ID = ").append(m_AD_Client_ID);
		int no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found "+columnID + ": " + no);
		
		// Actualizar los que no hubieran sido encontrados por el value
		return updateValueOk(columnID, tabla)==0;// true: ok
	}
		
	/**
	 * Verificar que todos los registros se actualizaron
	 * @param columnID
	 * @param tabla
	 */
	private int updateValueOk(String columnID, String tabla){
		StringBuffer sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET I_IsImported = 'E', ")
				.append("     I_ErrorMsg   = COALESCE(I_ErrorMsg, '') || ' ERR=Invalid ' || ")
				.append(DB.TO_STRING(columnID))
				.append(" WHERE ").append(columnID).append(" IS NULL ")
				.append(" AND I_IsImported<>'Y' ").append(clientCheck);

		int no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid "+columnID+ " = " + no);
		return no;
	}
	
	/**
	 * Actualizar la columna de unidad de medida
	 */
	private void updateColumns(){
		// Set uom
		StringBuffer sql = 
				new StringBuffer ("UPDATE ").append(table).append(" AS i ")
				.append(" SET   C_UOM_ID    = ( SELECT p.C_UOM_ID FROM M_Product p WHERE p.M_Product_ID = i.M_Product_ID ) ")
				.append(" WHERE i.C_UOM_ID IS NULL   ")
				.append(" AND   i.I_IsImported='N' AND i.AD_Client_ID = ").append(m_AD_Client_ID);
		int no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);

		sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET I_IsImported='E', I_ErrorMsg=COALESCE(I_ErrorMsg, '') || ' ERR=Invalid UOM ' ") 
				.append(" WHERE C_UOM_ID IS NULL ")
				.append(" AND I_IsImported<>'Y'  ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid C_UOM_ID =" + no);

		// Set Currency
		/*sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET   C_CURRENCY_ID =  ").append(getC_Currency_ID(getCtx()))
				.append(" WHERE C_CURRENCY_ID IS NULL ")
				.append(" AND   i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset=" + no);

		sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid CURRENCY' ") 
				.append(" WHERE C_CURRENCY_ID IS NULL ")
				.append(" AND I_IsImported<>'Y'       ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid C_CURRENCY_ID = " + no);*/
		
		// Set uom
		sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET   VENDORPRODUCTNO = M_Product_Value ")
				.append(" WHERE VENDORPRODUCTNO IS NULL   ")
				.append(" AND   I_IsImported='N' AND AD_Client_ID = ").append(m_AD_Client_ID);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);

		sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET   I_IsImported='E', I_ErrorMsg=COALESCE(I_ErrorMsg, '') || ' ERR=Invalid VENDORPRODUCTNO ' ") 
				.append(" WHERE VENDORPRODUCTNO IS NULL ")
				.append(" AND   I_IsImported<>'Y'  ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid VENDORPRODUCTNO =" + no);
		
		
		sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET   I_IsImported='E', I_ErrorMsg = COALESCE(I_ErrorMsg, '') || ' ERR=Invalid BPartner_Value ' ") 
				.append(" WHERE C_BPartner_ID IS NULL ")
				.append(" AND   I_IsImported<>'Y'  ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Invalid C_BPartner_Value =" + no);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public String insertUpdate() throws java.lang.Exception {
		// 
		int    noInsert = 0;
		int    noUpdate = 0;
		int       total = 0;
		
		ResultSet    rs = null;
		Statement pstmt = null;
		String columnID = "M_Product_ID";
		//	Go through Records
		StringBuffer sql = 
				new StringBuffer (" SELECT * FROM ")
				.append(table).append(" WHERE I_IsImported='N' ")
				.append(clientCheck);
		
		
		String sqlUpd = 
				"UPDATE I_EXME_Product_PO SET I_IsImported = 'Y', Processed = 'Y' " +
				"WHERE M_Product_ID = ? AND C_BPartner_ID = ?";
		
		Trx trx = null;
		
		try {
			// Cuantos registros se deberian de importar
 			pstmt = DB.createStatement();
			rs = pstmt.executeQuery(sql.toString());
			
			MAcctSchema as = 
					new MAcctSchema(getCtx(), Env.getC_AcctSchema_ID(getCtx()), null);
					
			
			
			while (rs.next()) {
				
				trx = Trx.get(Trx.createTrxName(), true);
				
				total++;
				int mProductId = rs.getInt("M_Product_ID");
				X_I_EXME_Product_PO mProductPOImp = new X_I_EXME_Product_PO(getCtx(), rs, trx.getTrxName());
				MProductPO mProductPONew = null;

				// buscamos que no exista el registro
				// esta tabla es multikey
				MProductPO po  = 
						MProductPO.getOfProduct(
								getCtx(), 
								mProductPOImp.getM_Product_ID(), 
								mProductPOImp.getC_BPartner_ID()
						);
				
				if (po == null) {
					// si no existe el registro se crea
					mProductPONew = new MProductPO(getCtx(), 0, trx.getTrxName());
					PO.copyValues(mProductPOImp, mProductPONew);
					mProductPONew.setM_Product_ID(mProductPOImp.getM_Product_ID());
					mProductPONew.setC_BPartner_ID(mProductPOImp.getC_BPartner_ID());
					mProductPONew.setC_Currency_ID(as.getC_Currency_ID());
					
				} else 
					// si existe el registro y se permite actualizar se actualiza el registro
					if(replace) {
						mProductPONew = po;
						mProductPONew.setIsCurrentVendor(mProductPOImp.isCurrentVendor());
						mProductPONew.setPriceList(mProductPOImp.getPriceList());
						if(mProductPOImp.getUPC()!=null){
							mProductPONew.setUPC(mProductPOImp.getUPC());
						}
				}
				
				if (mProductPONew == null){
					log.finest("No Insert/ No update" + table + " " + mProductId);

				} else if(mProductPONew.save()) {
					log.finest("Insert " + table);
					noInsert++;
					
					int updated = DB.executeUpdate(
							sqlUpd, 
							new Integer[]{
									mProductPOImp.getM_Product_ID(), 
									mProductPOImp.getC_BPartner_ID()
							}, 
							trx.getTrxName()
					);
					
					if(updated > 0) {
						trx.commit();
					} else {
						trx.rollback();
					}
////				} else if(mProductPONew.geteErrorBD()!=null ){
//					log.finest("no Insert " + table + " error " + mProductPONew.geteErrorBD().getValue());
//					log.finest("no Insert " + table + " error " + mProductPONew.geteErrorBD().getName());
				}
				
				
				trx.close();
			}
		} catch (SQLException e) {	//	doIt
			throw new Exception ("ImportProductPO.doIt", e);
		} finally {
			log.finest(" Total records " + table + ": " + total);
			updateFinal(noInsert, noUpdate, columnID);
		}
		return "";
	}//ok
	
	/**
	 * updateFinal
	 * @param noInsert
	 * @param noUpdate
	 * @param columnID
	 */
	private void updateFinal(int noInsert, int noUpdate, String columnID){
		// Set Error to indicator to not imported
		StringBuffer sql = new StringBuffer ("UPDATE ").append(table)
				.append(" SET I_IsImported='N', Updated = ");

		if (DB.isOracle()) {			
			sql.append(" SysDate ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" now() ");
		}

		sql.append(" WHERE I_IsImported <> 'Y' AND I_IsImported <> 'E'").append(clientCheck);

		int no = DB.executeUpdate(sql.toString(),get_TrxName());

		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@"+columnID+"@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@"+columnID+"@: @Updated@");
	}
}
