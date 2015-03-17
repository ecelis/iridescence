package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MEXMEProductPrice;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPrice;
import org.compiere.model.X_I_EXME_ProductoPrecio;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Proceso para importar precios de productos por unidad de medida para una version de lista de precios
 * 
 * <b>Modificado: </b> $Author: vgarcia $
 * <p>
 * <b>En :</b> $Date: 2006/10/11 17:12:08 $
 * <p>
 * 
 * @author Twry Perez
 * @version $Revision: 1.7 $
 */
public class ImportProductoPrecio extends SvrProcess {

	/** Client to be imported to */
	private int m_AD_Client_ID = 0;

	/** Delete old Imported */
	private boolean m_deleteOldImported = false;

	/** Organization to be imported to */
	private int m_AD_Org_ID = 0;

	/** Lista de Precios */
	private int m_M_PriceList_ID = 0;

	/** Version de Lista de Precios */

	private int m_M_PriceList_Version_ID = 0;

	/** Sobreescribir version lista precios */
	private boolean m_Sobreescribir = true;

	/**
	 * Constructor por defecto.
	 */
	public ImportProductoPrecio() {
		super();
	}

	/**
	 * Obtener los valores de los par&aacute;metros
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal) para[i].getParameter()).intValue();

			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal) para[i].getParameter()).intValue();

			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());

			else if (name.equals("M_PriceList_ID"))
				m_M_PriceList_ID = ((BigDecimal) para[i].getParameter()).intValue();

			else if (name.equals("M_PriceList_Version_ID"))
				m_M_PriceList_Version_ID = ((BigDecimal) para[i].getParameter()).intValue();

			else if (name.equals("Sobreescribir"))
				m_Sobreescribir = "Y".equals(para[i].getParameter());

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	/**
	 * Corre el proceso.
	 * 
	 * @return Un mensaje de estado
	 * @throws Exception
	 */
	protected String doIt() throws Exception {

		StringBuffer sql = null;
		int no = 0;

		// aumentar la parte del cliente en el sql
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		// **** Prepare ****

		// Delete Old Imported
		if (m_deleteOldImported) {
			sql = new StringBuffer("DELETE I_EXME_ProductoPrecio " + "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Delete Old Impored =" + no);
		}

		// Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio " + "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(
				m_AD_Client_ID).append(")," + " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append(
				")," + " IsActive = COALESCE (IsActive, 'Y')," + " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
						+ " CreatedBy = COALESCE (CreatedBy, 0)," + " Updated = COALESCE (Updated, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
						+ " UpdatedBy = COALESCE (UpdatedBy, 0)," + " I_ErrorMsg = NULL," + " I_IsImported = 'N' "
						+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Reset=" + no);

		// establecemos el producto por medio del codigo
		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio i " + "SET M_Product_ID = (SELECT M_Product_ID "
				+ " FROM M_Product p WHERE p.Value = i.M_Product_Value " + " AND i.AD_Client_ID = p.AD_Client_ID) "
				+ "WHERE M_Product_ID IS NULL " + "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Set Producto=" + no);
		//

		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Producto no existe,' "
				+ "WHERE M_Product_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0)
			log.warning("Producto no existe=" + no);

		// MConfigEC config = MConfigEC.get(getCtx(), get_TrxName());
		// obtenemos la lista de precios
		//MPriceList pl = new MPriceList(getCtx(), m_M_PriceList_ID, null);
		log.warning("Lista de Precios = " + m_M_PriceList_ID);

		// MPriceListVersion listaVers = pl.getPriceListVersion(new Timestamp(System.currentTimeMillis ()));
		log.warning("Version de Lista de Precios = " + m_M_PriceList_Version_ID);

		if(m_M_PriceList_Version_ID <= 0){
//			 establecemos la version de la lista de precios
			sql = new StringBuffer(" UPDATE I_EXME_ProductoPrecio i SET i.M_PriceList_Version_ID = ")
				.append("( SELECT v.M_PriceList_Version_ID FROM  M_PriceList_Version v WHERE v.name = i.PriceList_Version_Name )")
				.append(" WHERE i.M_PriceList_Version_ID IS NULL AND i.I_IsImported = 'N' ").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Version de lista de precios=" + no);
		} else {
//			 establecemos la version de la lista de precios
			sql = new StringBuffer(" UPDATE I_EXME_ProductoPrecio i " + " SET i.M_PriceList_Version_ID = ").append(
					m_M_PriceList_Version_ID)
					.append(" WHERE M_PriceList_Version_ID IS NULL " + "AND i.I_IsImported = 'N' ").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Version de lista de precios=" + no);
		}

		// establecemos la unidad de medida
		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio i " + "SET i.C_Uom_ID = (SELECT p.C_Uom_ID "
				+ " FROM C_Uom p WHERE p.X12DE355 = i.X12DE355" + " AND i.AD_Client_ID = p.AD_Client_ID) "
				+ "WHERE i.C_Uom_ID IS NULL " + "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Unidad de Medida=" + no);
		//
		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No existe la unidad de medida,' "
				+ "WHERE C_Uom_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0)
			log.warning("No existe la unidad de medida=" + no);

		/** ********* si los precios estan en 0, no se toma en cuenta ******************** */
		/*
		 * sql = new StringBuffer ("UPDATE I_EXME_ProductoPrecio " + "SET I_IsImported='E',
		 * I_ErrorMsg=I_ErrorMsg||'ERR=Precios en 0,' " + "WHERE PriceList = 0 AND PriceStd = 0 AND PriceLimit = 0 " + "
		 * AND I_IsImported<>'Y'").append(clientCheck); no = DB.executeUpdate(sql.toString(), null); if (no != 0)
		 * log.warning("Precios en 0=" + no);
		 */

		int noInsert = 0;
		//int noUpdate = 0;

		// Set Imported = Y
		PreparedStatement pstmt_setImported = DB.prepareStatement("UPDATE I_EXME_ProductoPrecio SET I_IsImported='Y', "
				+ "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_EXME_ProductoPrecio_ID=?", null);

		// Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer("SELECT I_EXME_ProductoPrecio_ID " + "FROM I_EXME_ProductoPrecio WHERE I_IsImported='N'")
				.append(clientCheck).append(" AND M_Product_ID IS NOT NULL AND C_UOM_ID IS NOT NULL");

		Trx trx = Trx.get("ImoProdPre", true);
		String trxName = trx.getTrxName();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			int iProductPriceID;

			while (rs.next()) {
				iProductPriceID = rs.getInt("I_EXME_ProductoPrecio_ID");

				X_I_EXME_ProductoPrecio iPP = new X_I_EXME_ProductoPrecio(getCtx(), iProductPriceID, null);

				MPriceListVersion plv = null;
				if (iPP.getM_PriceList_Version_ID() != 0)
					plv = new MPriceListVersion(getCtx(), iPP.getM_PriceList_Version_ID(), null);
				else
					plv = new MPriceListVersion(getCtx(), m_M_PriceList_Version_ID, null);

				// checamos si ya existe el precio del producto para la unidad de medida
				// Expert: Proyecto #102 Posteo, Costos y Precios
        		// Depreciada la tabla EXME_ProductoPrecio
				MProductPrice mpp = MEXMEProductPrice.get(getCtx(), plv.getM_PriceList_Version_ID(), iPP
						.getM_Product_ID(), null);

				if (mpp == null) {
					// insertar el precio del producto
					// creamos el precio asociado a la version de la lista
					mpp = new MEXMEProductPrice(getCtx(), 0, trxName);
					mpp.setClientOrg(plv.getAD_Client_ID(), plv.getAD_Org_ID());
					mpp.setM_PriceList_Version_ID(plv.getM_PriceList_Version_ID());
					mpp.setM_Product_ID(iPP.getM_Product_ID());
					mpp.setPrices(iPP.getPriceList(), iPP.getPriceStd(), iPP.getPriceLimit());
					
				} else {
					// actualizar si selecciono sobreescribir
					if (!m_Sobreescribir)
						continue;
					else {
						mpp.setClientOrg(plv.getAD_Client_ID(), plv.getAD_Org_ID());
						mpp.setM_PriceList_Version_ID(plv.getM_PriceList_Version_ID());
						mpp.setM_Product_ID(iPP.getM_Product_ID());
						mpp.setPrices(iPP.getPriceList(), iPP.getPriceStd(), iPP.getPriceLimit());
						//mpp.setC_UOM_ID(iPP.getC_UOM_ID());
					}
				}

				if (!mpp.save(trxName)) {
					DB.rollback(false, trxName);

					if (noInsert > 0)
						noInsert--;
					no++;

					log.warning("Importando Precios - No fue posible insertar o actualizar el precio");
					sql = new StringBuffer(
							"UPDATE I_EXME_ProductoPrecio p "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'No fue posible insertar o actualizar el precio,'")
							.append("WHERE I_EXME_ProductoPrecio_ID=").append(iProductPriceID);
					DB.executeUpdate(sql.toString(), null);
					continue;
				}

				// completamos la transaccion
				DB.commit(true, trxName);

				noInsert++;

				// actualizamos los datos en tabla de importacion
				pstmt_setImported.setInt(1, iProductPriceID);
				pstmt_setImported.executeQuery();
			}
		} catch (Exception e) {
			DB.rollback(false, trxName);

			e.printStackTrace();
			log.warning("Importando Precios excepcion:" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			rs = null;
			pstmt = null;
		}

		// Set Error to indicator to not imported
		sql = new StringBuffer("UPDATE I_EXME_ProductoPrecio " + "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
				+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		addLog(0, null, new BigDecimal(no), "@Errors@");
		addLog(0, null, new BigDecimal(noInsert), "@I_EXME_ProductoPrecio_ID@: @Inserted@");

		trx.close();

		return "";
	}

}