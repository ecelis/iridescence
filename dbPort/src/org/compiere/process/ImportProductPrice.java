package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MProductPrice;
import org.compiere.model.X_I_ProductPrice;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Importaci&oacute;n de precios a versi&oacute;n
 * de lista de precios.
 * 
 * @author mrojas
 * @version $Revision: 1.4 $
 */
public class ImportProductPrice extends SvrProcess {
    
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
    public ImportProductPrice() {
        super();
    }
    
    
    /**
     * Obtener los valores de los par&aacute;metros
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
			sql = new StringBuffer ("DELETE I_ProductPrice "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_ProductPrice "
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
		
		//establecemos el producto por medio del codigo de barras
		sql = new StringBuffer("UPDATE I_ProductPrice i " +
		        "SET M_Product_ID = (SELECT M_Product_ID "+
		        " FROM M_Product p WHERE p.UPC = i.UPC "+
		        " AND i.AD_Client_ID = p.AD_Client_ID) " +
		        "WHERE M_Product_ID IS NULL " +
		        "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.info("Codigo de barras=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ProductPrice "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Codigo de barras no existe,' "
			+ "WHERE M_Product_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		if (no != 0)
			log.warning("Codigo de barras no existe=" + no);
		
//		establecemos la version de la lista de precios
		sql = new StringBuffer("UPDATE I_ProductPrice i " +
		        "SET M_PriceList_Version_ID = (SELECT M_PriceList_Version_ID "+
		        " FROM M_PriceList_Version p WHERE p.Name = i.PriceList_Version_Name"+
		        " AND i.AD_Client_ID = p.AD_Client_ID) " +
		        "WHERE M_PriceList_Version_ID IS NULL " +
		        "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.info("Version de lista de precios=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ProductPrice "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No existe version de lista de precios,' "
			+ "WHERE M_PriceList_Version_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		if (no != 0)
			log.warning("No existe version de lista de precios=" + no);
		
//		establecemos la version de la lista de precios
		sql = new StringBuffer("UPDATE I_ProductPrice i " +
		        "SET C_BPartner_ID = (SELECT C_BPartner_ID "+
		        " FROM C_BPartner p WHERE p.Value = i.BPartner_Value"+
		        " AND i.AD_Client_ID = p.AD_Client_ID) " +
		        "WHERE C_BPartner_ID IS NULL " +
		        "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.info("Proveedor=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ProductPrice "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No existe el proveedor,' "
			+ "WHERE C_BPartner_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		if (no != 0)
			log.warning("No existe el proveedor=" + no);
		
		
		// si los precios estan en 0, no se toma en cuenta
		sql = new StringBuffer ("UPDATE I_ProductPrice "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Precios en 0,' "
				+ "WHERE PriceList = 0 AND PriceStd = 0 AND PriceLimit = 0 "
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			if (no != 0)
				log.warning("Precios en 0=" + no);
		
		
			// establecer unidad de medida
			sql = new StringBuffer ("UPDATE I_ProductPrice i "
					+ "SET X12DE355 = "
					+ "(SELECT X12DE355 FROM C_UOM u WHERE u.IsDefault='Y' AND u.AD_Client_ID IN (0,i.AD_Client_ID) ");
					
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
			
			sql = new StringBuffer ("UPDATE I_ProductPrice i "
				+ "SET C_UOM_ID=(SELECT C_UOM_ID FROM C_UOM u"
				+ " WHERE TRIM(i.X12DE355) = TRIM(u.X12DE355)"
				+ " AND u.AD_Client_ID IN (0, i.AD_Client_ID)) "
				+ "WHERE C_UOM_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.fine("Establecer UDM=" + no);
			/* no es requerida
			sql = new StringBuffer ("UPDATE I_ProductPrice "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=UDM no valida, ' "
				+ "WHERE C_UOM_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString());
			log.config("UDM no valido=" + no);*/
			
		
		int noInsert = 0;
		//int noUpdate = 0;
		
		//para recuperar la conversion entre unidad de compra y almacenamiento
		PreparedStatement pstmtConversion =
		    DB.prepareStatement(
		            "SELECT DivideRate FROM C_UOM_Conversion " +
		            "WHERE C_UOM_ID = ? AND C_UOM_To_ID = ? " +
		            clientCheck
		            );

		// Set Imported = Y
		PreparedStatement pstmt_setImported = 
			DB.prepareStatement("UPDATE I_ProductPrice SET I_IsImported='Y', "
			+ "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_ProductPrice_ID=?");

		// Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_ProductPrice_ID "
			+ "FROM I_ProductPrice WHERE I_IsImported='N'").append(clientCheck);
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
		    pstmt = DB.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			ResultSet rsConv = null;
			String trxName = null;
			int iProductPriceID;
			int udmProv = 0;
			BigDecimal tasa = null;
			
			while(rs.next()) {
			    iProductPriceID = rs.getInt("I_ProductPrice_ID");
			    
			    trxName = Trx.createTrxName();
			    X_I_ProductPrice iPP = new X_I_ProductPrice(getCtx(), iProductPriceID, null);
			    
			    MPriceListVersion plv = new MPriceListVersion(getCtx(), iPP.getM_PriceList_Version_ID(), null);
			    if(plv.getM_PriceList_Version_ID() <= 0) {
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - Version de Lista de precios no existe");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Version de Lista de precios no existe,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //recuperamos el producto para determinar la unidad de almacenamiento
			    MProduct producto = new MProduct(getCtx(), iPP.getM_Product_ID(), null);
			    if(producto.getM_Product_ID() <= 0) {
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - Producto no existe");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Producto no existe,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //recuperamos los datos de compra del producto
			    MProductPO productoPO[] = MProductPO.getOfProduct(getCtx(), producto.getM_Product_ID(), null);
			    if(productoPO == null || productoPO.length <= 0) {
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - No hay datos de compra del producto");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'No hay datos de compra del producto,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //buscamos la unidad del proveedor
			    for(int i = 0; i < productoPO.length; i++) {
			        MProductPO ppo = productoPO[i];
			        if(ppo.getC_BPartner_ID() == iPP.getC_BPartner_ID()) {
			            udmProv = ppo.getC_UOM_ID();
			            break;
			        }
			    }
			    
			    if(udmProv == 0){
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - No hay unidad de medida para el proveedor");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'No hay unidad de medida para el proveedor,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //si es la misma udm no buscamos la tasa
			    if(udmProv==producto.getC_UOM_ID()) {
			        tasa = new BigDecimal(1);
			    } else {
				    //recuperamos la tasa de conversion
				    pstmtConversion.setInt(1, udmProv);
				    pstmtConversion.setInt(2, producto.getC_UOM_ID());
				    rsConv = pstmtConversion.executeQuery();
				    if(rsConv.next())
				        tasa = rsConv.getBigDecimal("DivideRate");
				    rsConv.close();
			    }
			    
			    if(tasa == null || tasa.doubleValue() == 0) {
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - No hay conversion entre unidad de compra y almacenamiento");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'No hay conversion entre unidad de compra y almacenamiento,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //creamos el precio asociado a la version de la lista
			    MProductPrice mpp = new MProductPrice(plv, iPP, trxName);
			    //establecemos los precios aplicando la conversion de unidad de compra a almacenamiento
			    mpp.setPriceList(new BigDecimal(iPP.getPriceList().floatValue() / tasa.floatValue()));
			    mpp.setPriceStd(new BigDecimal(iPP.getPriceStd().floatValue() / tasa.floatValue()));
			    mpp.setPriceLimit(new BigDecimal(iPP.getPriceLimit().floatValue() / tasa.floatValue()));
			    //mpp.setC_UOM_ID(iPP.getC_UOM_ID()); expert: TWRY
			    
			    if(!mpp.save(trxName)) {
			        DB.rollback(false, trxName);
			        
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        
			        log.warning("Importando Precios - No fue posible insertar el precio");
					sql = new StringBuffer ("UPDATE I_ProductPrice p "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'No fue posible insertar el precio,'")
						.append("WHERE I_ProductPrice_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString());
					continue;
			    }
			    
			    //completamos la transaccion
			    DB.commit(true, trxName);
			    
			    noInsert++;
			    
			    //actualizamos los datos en tabla de importacion
				pstmt_setImported.setInt(1, iProductPriceID);
				pstmt_setImported.executeQuery();
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    log.warning("Importando Precios:" + e);
		}finally{
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            rs=null;
            pstmt=null;
		}
		
//		Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_ProductPrice "
			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@I_ProductPrice_ID@: @Inserted@");
        
        return "";
    }

}
