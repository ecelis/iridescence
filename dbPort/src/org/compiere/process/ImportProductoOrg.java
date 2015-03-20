package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCountry;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.MEXMEProductoPrecio;
import org.compiere.model.MEXMERevenueCodes;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MIntervencion;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.X_I_EXME_ProductoOrg;
import org.compiere.model.X_M_Product;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * Import the "charge master". </br>This process loads data into the EXME_ProductoOrg </br>table and related ones, such as the product, </br>account info, costs, prices (EXME_ProductoPrecio).
 *
 * @author mrojas
 *
 */
public class ImportProductoOrg extends SvrProcess {
	private static List<String> classes;
	private static final String NOTFOUND = "NotFound";

	static {
		if (ImportProductoOrg.classes == null) {
			ImportProductoOrg.classes = new ArrayList<String>();
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_Laboratory);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_OtherService);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_Procedures);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_XRay);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_Immunization);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_Anesthesic);
			ImportProductoOrg.classes.add(X_M_Product.PRODUCTCLASS_Surgeries);
		}
	}

	private transient int adClientId;
	private transient int adOrgId;
	private transient int newmProductId = -1;

	private final transient List<X_I_EXME_ProductoOrg> lst = new ArrayList<X_I_EXME_ProductoOrg>();


	@Override
	protected String doIt() throws Exception {


		try {
			// Reiniciamos los datos de los registros que no pudieron importarse previamente
			initData();

			// Si no es méxico se actualizan modificadores
			if (!MCountry.isMexico(getCtx())) {
				updateModifiers();
			}

			// Actualizar product category
			updateProductCategory();

			// Actualizar Tax category
			updateTaxCategory();

			// Actualizar unidad de medida
			updateUDMS();

			// load data to be imported
			loadImportData();

			boolean isCorrect = false;
			Trx trx = null;
			StringBuilder errMsg = new StringBuilder();
			for (final X_I_EXME_ProductoOrg iProductoOrg : lst) {
				// Se crea un nuevo objeto por registro en la lista
				errMsg = new StringBuilder();
				try {
					trx = Trx.get(Trx.createTrxName("IPO_MAIN"), true);

					if (StringUtils.isNotEmpty(iProductoOrg.getCPT())) {
						errMsg.append(insertCPT(iProductoOrg, trx));
					} else if (StringUtils.isNotEmpty(iProductoOrg.getNDC())) {
						errMsg = insertNDC(iProductoOrg, trx);
					} else if (StringUtils.isNotEmpty(iProductoOrg.getOther())) {
						errMsg = insertNoNDC(iProductoOrg, trx);
					}

					if (errMsg.length() > 0) {
						Trx.rollback(trx);
					} else {
						Trx.commit(trx);
					}

					isCorrect = true;
				} catch (final Exception ex) {
					log.log(Level.SEVERE, "", ex);
					Trx.rollback(trx);

					errMsg.append(ex.getLocalizedMessage());
				} finally {
					Trx.close(trx);
					setStatus(
							iProductoOrg.getI_EXME_ProductoOrg_ID(),
							StringUtils.trimToNull(errMsg.toString()),
							errMsg.length() == 0,
							null
					);
				}
			} //for

			if(isCorrect){
				final MEXMEConfigPre configPre = MEXMEConfigPre.get(Env.getCtx(), null);

				if(MEXMEConfigPre.BUSQUEDAPRECIO_LP.equals(configPre.getBusquedaPrecio())){ //solo si los precios se guardan en lista de precios
					// Actualiza el id de la lista de precios en base a su nombre
					updatePriceListID();
					// Crea nueva versiòn para la lista de precios
					createNewVersions();
				}
				// Actualizar precios
				updatePrice();

				markAsProcessed();
			}

			MEXMEProductoOrg.rebuild(getCtx());

		} catch (final Exception ex) {
			log.log(Level.SEVERE, null, ex);
		}

		return null;
	}


	private void loadImportData() {
		final StringBuilder sBuilder = new StringBuilder("Select *  ");

		sBuilder.append(" FROM i_exme_productoorg  ")
		.append(" WHERE AD_Client_ID  = ? ")
		.append(" and AD_Org_ID = ? ")
		.append(" and IsActive = 'Y' ")
		.append(" and I_IsImported = 'N'")
		.append(" and Processed = 'N'")
		.append(" and i_ErrorMsg IS NULL ");

		PreparedStatement pstmt = null;
		ResultSet resultset = null;

		try {
			pstmt = DB.prepareStatement(sBuilder.toString(), null);
			pstmt.setInt(1, adClientId);
			pstmt.setInt(2, adOrgId);

			resultset = pstmt.executeQuery();

			while (resultset.next()) {
				lst.add(new X_I_EXME_ProductoOrg(getCtx(), resultset, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(resultset, pstmt);
		}

	}


	/**
	 * Creación de versiones de listas de precios
	 *
	 * @return
	 */
	public StringBuilder createNewVersions() {

		PreparedStatement pstmt = null;
		ResultSet resultS = null;
		int priceListID;
		int esquemaID = 0;
		final StringBuilder errMsg = new StringBuilder();

		try {
			// not for system level, so client and org must be > 0
			if (adClientId > 0 && adOrgId > 0) {

				final StringBuilder sBuilder = new StringBuilder("SELECT M_PRICELIST_ID ");
				sBuilder.append(" FROM I_EXME_ProductoOrg ")
				.append(" WHERE  AD_Client_ID = ?")
				.append(" AND AD_Org_ID = ?")
				.append(" AND IsActive = 'Y'")
				.append(" AND I_IsImported = 'Y'")
				.append(" AND Processed = 'N'")
				.append(" AND I_ErrorMsg IS NULL")
				.append(" AND M_PRICELIST_ID > 0")
				.append(" AND M_PRICELIST_version_ID is null")
				.append(" GROUP BY M_PRICELIST_ID ");

				pstmt = DB.prepareStatement(sBuilder.toString(), null);

				pstmt.setInt(1, adClientId);
				pstmt.setInt(2, adOrgId);

				// Genera una nueva version con la fecha actual
				resultS = pstmt.executeQuery();
				final MPriceListVersion version = new MPriceListVersion(Env.getCtx(), 0, null);
				version.setValidFrom(new Timestamp(System.currentTimeMillis()));

				while (resultS.next()) {

					priceListID = resultS.getInt("M_PRICELIST_ID");

					// Crear nueva lista de precios
					// MPriceListVersion version = new MPriceListVersion(Env.getCtx(),0,null);
					version.setM_PriceList_ID(priceListID);
					version.setName();

					// Regresa todo el listado de esquemas de descuento
					final List<MDiscountSchema> lstSchedma = MDiscountSchema.getList(Env.getCtx(), null);

					// Seleccionar el primero
					if (!lstSchedma.isEmpty()) {
						esquemaID = lstSchedma.get(0).getM_DiscountSchema_ID();
					}

					version.setM_DiscountSchema_ID(esquemaID);
					version.setIsActive(true);

					if (version.save()) {
						updatePriceListVersionID(priceListID, version.getM_PriceList_Version_ID());
					} else {
						errMsg.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND)).append(',');
					}
				}

			} else {
				errMsg.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND)).append(',');
			}

		} catch (final SQLException e) {
			log.log(Level.SEVERE, null, e);
			errMsg.append(e.getLocalizedMessage()).append(',');
		} finally {
			DB.close(resultS, pstmt);
		}

		return errMsg;

	}


	/**
	 * Actualiza los registros previos para ser procesados de nuevo
	 */
	private void initData() {
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg i ");
		SQL.append(" SET I_ErrorMsg = NULL,");
		SQL.append(" Processed = 'N' ");
		SQL.append(" WHERE I_IsImported = 'N' ");
		final int numero = DB.executeUpdate(SQL.toString(), null);
		log.finest("Reset=" + numero);
	}



	/**
	 * Creación de producto relacionado de un CPT
	 *
	 * @param ipo
	 * @param trx
	 * @return
	 * @throws SQLException
	 */
	private String insertCPT(final X_I_EXME_ProductoOrg ipo, final Trx trx) {

		final int exmeIntervId = MIntervencion.getIdFromValue(ipo.getCPT());

		final StringBuilder errMsg = new StringBuilder();

		if (exmeIntervId <= 0) {
			errMsg.append(Msg.getElement(getCtx(), "EXME_Intervencion_ID"));
			errMsg.append(Constantes.SPACE);
			errMsg.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND)).append(',');
		}

		if (StringUtils.isEmpty(ipo.getOther())) {
			errMsg.append(Msg.getElement(getCtx(), "Value"));
			errMsg.append(Constantes.SPACE);
			errMsg.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND)).append(',');
		}

		if (!ImportProductoOrg.classes.contains(ipo.getProductClass())) {
			errMsg.append(Utilerias.getAppMsg(getCtx(), "msj.claseEquivocada"));
		}

		if (errMsg.length() == 0) {

			//the product could be alredy in the client/org
			newmProductId = MProduct.getIdFromValue(ipo.getOther(), adOrgId, true);

			if(newmProductId <= 0) {
				errMsg.append(insertNewProduct(ipo, exmeIntervId, trx));
			}

			if (errMsg.length() == 0) {
				updateProduct(ipo.getI_EXME_ProductoOrg_ID(), newmProductId, trx.getTrxName());
				final int revCodeId = MEXMERevenueCodes.getIdFromValue(ipo.getRev_Code(), trx.getTrxName());
				errMsg.append(
						MEXMEProductoOrg.insertChargeMaster(
								getCtx(),
								newmProductId,
								ipo,
								revCodeId,
								trx
						)
				);
			}
		}

		return errMsg.toString();
	}

	/**
	 * Insertar producto por medio del ndc (No crea productos, asume que existen)
	 *
	 * @param ipo
	 * @param trx
	 * @return
	 */
	private StringBuilder insertNDC(final X_I_EXME_ProductoOrg ipo, final Trx trx) {

		final StringBuilder errMsg = new StringBuilder();

		// Si el medicamento existe como producto // the product exists?
		final int mProductId = MProduct.getIdFromValue(ipo.getNDC(), 0, true);

		// Si el producto existe se sigue con el proceso, caso contrario marca el error tanto para USA como para Mexico
		if (mProductId > 0) {

			updateProduct(ipo.getI_EXME_ProductoOrg_ID(), mProductId, trx.getTrxName());

			// Inserta en el charge master tanto para Mexico como para USA
			final int revCodeId = MEXMERevenueCodes.getIdFromValue(ipo.getRev_Code(), trx.getTrxName());
			if ("US".equals(MCountry.get(getCtx(), Env.getC_Country_ID(getCtx())).getCountryCode())
					&& revCodeId <= 0) {
					// the product does not exist
					errMsg.append(Msg.getElement(getCtx(), "EXME_revenueCode_ID"))
					.append(' ')
					.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND))
					.append(',');
			}

			// do the carge master stuff (EXME_ProductoOrg)
			final String err =
					MEXMEProductoOrg.insertChargeMaster(
							getCtx(),
							mProductId,
							ipo,
							revCodeId,
							trx
					);

			if (StringUtils.isNotEmpty(err)) {
				errMsg.append(err).append(',');
			}

		} else {
			// the product does not exist
			errMsg.append(Msg.getElement(getCtx(), X_M_Product.COLUMNNAME_M_Product_ID)).append(' ').append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND)).append(',');
		}

		return errMsg;
	}

	/**
	 * Creación del nuevo producto
	 *
	 * @param value
	 * @param name
	 * @param description
	 * @param UOMName
	 * @param UOMVolumeName
	 * @param CategoryID
	 * @param cTaxCategoryID
	 * @param intervencionId
	 * @param productType
	 * @param productClass
	 * @param EXME_TipoProd_ID
	 * @param ProductFam_ID
	 * @param IsStocked
	 * @param trx
	 * @return
	 */
	private String insertNewProduct(final X_I_EXME_ProductoOrg ipo, final int exmeIntervencionId, final Trx trx) {

		final StringBuffer retVal = new StringBuffer();

		try {

			if (X_M_Product.PRODUCTCLASS_Drug.equals(ipo.getProductClass())) {
				retVal.append(Utilerias.getAppMsg(getCtx(), "error.registrarMed.noautorizacion"));
			} else if (MProduct.existsInSystem(getCtx(), ipo.getOther(), null) != null) {
				retVal.append(Utilerias.getAppMsg(getCtx(), "error.duplicate.key"));
			} else if (ImportProductoOrg.classes.contains(ipo.getProductClass())
					&& exmeIntervencionId < 1) {
				final Object[] args = {Utilerias.getAppMsg(getCtx(),"msg.sSC.CPTandHCPCS")};
				retVal.append(Utilerias.getAppMsg(getCtx(), "error.citas.requerido", args));
			} else if (!ImportProductoOrg.classes.contains(ipo.getProductClass())
					&& exmeIntervencionId > 0) {
				retVal.append(Utilerias.getAppMsg(getCtx(), "msj.claseEquivocada"));
			} else {

				final MUOM uom = MUOM.getByName(getCtx(), ipo.getUOM_Value(), null);
				final MUOM uomVolume = MUOM.getByName(getCtx(), ipo.getC_UOMVolume_Value(), null);

				if (uom != null && uomVolume != null && uom.getC_UOM_ID() > 0 && uomVolume.getC_UOM_ID() > 0) {

					MUOMConversion rates = null;

					if( uom.getC_UOM_ID() != uomVolume.getC_UOM_ID() ){
						rates =
								MEXMEUOMConversion.getUomsConversion(
										Env.getCtx(),
										uom.getC_UOM_ID(),
										uomVolume.getC_UOM_ID(),
										null
										);
					}

					if(rates != null || uom.getC_UOM_ID() == uomVolume.getC_UOM_ID()){

						final MProduct mProduct = new MProduct(ipo);
						mProduct.setC_UOM_ID(uom.getC_UOM_ID());
						mProduct.setC_UOMVolume_ID(uomVolume.getC_UOM_ID());

						if(ImportProductoOrg.classes.contains(ipo.getProductClass())){
							mProduct.setIsStocked(false);
						} else {
							mProduct.setIsStocked(ipo.isStocked());
						}

						if (exmeIntervencionId > 0) {
							mProduct.setEXME_Intervencion_ID(exmeIntervencionId);
						}


						if (mProduct.save()) {
							log.info("Product insert ID = " + mProduct.getM_Product_ID());
							newmProductId = mProduct.getM_Product_ID();
						} else {
							retVal.append(Msg.getElement(getCtx(), X_M_Product.COLUMNNAME_M_Product_ID))
							.append(' ').append(Msg.getMsg(getCtx(), "SaveIgnored"));

							log.warning("Product NO insert Value = " + ipo.getOther());
						}

					} else {
						retVal.append(Utilerias.getAppMsg(getCtx(), "error.udm.noExisteConversion"));
					}
				} else {
					retVal.append(Msg.getElement(getCtx(), "C_UOM_ID"))
					.append(' ')
					.append(Msg.getMsg(getCtx(), ImportProductoOrg.NOTFOUND));
				}
			}
		} catch (final Exception e) {
			log.log(Level.WARNING, "Inserting product " + ipo.getDescription(), e);
			retVal.append(e.getMessage());
		}

		return retVal.toString();
	}

	/**
	 * Creación de productos que no son ni medicamentos ni cpts (puede o no existir el producto)
	 *
	 * @param ipo
	 * @param trx
	 * @return
	 * @throws SQLException
	 */
	private StringBuilder insertNoNDC(final X_I_EXME_ProductoOrg ipo, final Trx trx) throws SQLException {
		final StringBuilder errMsg = new StringBuilder();
		final int revenueId = MEXMERevenueCodes.getIdFromValue(ipo.getRev_Code(), null);

		final int mProductId = MProduct.getIdFromValue(ipo.getOther(), adOrgId, true);

		if (mProductId > 0) {
			// yes, we have it, look for it into charge master
			final String err =
					MEXMEProductoOrg.insertChargeMaster(
							getCtx(),
							mProductId,
							ipo,
							revenueId,
							trx
					);

			if (err != null && !err.isEmpty()) {
				errMsg.append(err).append(',');
			}

		} else {
			newmProductId = 0;
			// the product does not exist in the current organization
			// insert record into M_Product
			String err = insertNewProduct(ipo, 0, trx);
			if (StringUtils.isEmpty(err)) {
				updateProduct(ipo.getI_EXME_ProductoOrg_ID(), newmProductId, trx.getTrxName());
				err =
						MEXMEProductoOrg.insertChargeMaster(
								getCtx(),
								newmProductId,
								ipo,
								revenueId,
								trx
						);
			} else {
				errMsg.append(err).append(',');
			}
		}

		return errMsg;

	}

	/**
	 * Creación del producto precio
	 *
	 * @param productID
	 * @param versionID
	 * @param udm
	 * @param udmVol
	 * @param price
	 * @param priceVol
	 */
	public void insertProductPrice(final int productID, final int versionID,
			final int udm, final int udmVol, final BigDecimal price,
			final BigDecimal priceVol) {

		final StringBuilder stringBuild = new StringBuilder("INSERT INTO M_ProductPrice ");
		stringBuild.append(" ( m_pricelist_version_id, m_product_id, ad_client_id, ad_org_id, isactive, ");
		stringBuild.append(" createdby, updatedby , c_uom_id, c_uomvolume_id, ");
		stringBuild.append(" pricelist, pricestd, pricelimit, pricelist_vol,pricestd_vol, pricelimit_vol )");
		stringBuild.append("values( ");
		stringBuild.append(" ?, ?, ?, ?, ?, ");
		stringBuild.append(" ?, ?, ?, ? , ");
		stringBuild.append(" ?, ?, ?, ?, ? ,? )");

		PreparedStatement pstmt = null;

		try {

			pstmt = DB.prepareStatement(stringBuild.toString(), null);
			pstmt.setInt(1, versionID);
			pstmt.setInt(2, productID);
			pstmt.setInt(3, adClientId);
			pstmt.setInt(4, adOrgId);
			pstmt.setString(5, "Y");
			pstmt.setInt(6, Env.getAD_User_ID(getCtx()));
			pstmt.setInt(7, Env.getAD_User_ID(getCtx()));
			pstmt.setInt(8, udm);
			pstmt.setInt(9, udmVol);
			pstmt.setBigDecimal(10, price);
			pstmt.setBigDecimal(11, price);
			pstmt.setBigDecimal(12, price);
			pstmt.setBigDecimal(13, priceVol);
			pstmt.setBigDecimal(14, priceVol);
			pstmt.setBigDecimal(15, priceVol);

			final int numRec = pstmt.executeUpdate();
			log.info("Product/Org insert = " + numRec);

		} catch (final SQLException e) {
			log.severe(e.getMessage());
		} finally {
			DB.close(pstmt);
		}

	}

	/**
	 * Marca registros como procesados
	 */
	private void markAsProcessed() {
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg ");
		SQL.append(" SET Processed = 'Y' ");
		DB.executeUpdate(SQL.toString(), null);
	}

	@Override
	protected void prepare() {
		adClientId = Env.getAD_Client_ID(getCtx());
		adOrgId = Env.getAD_Org_ID(getCtx());
	}

	/**
	 * Actualización de estado
	 *
	 * @param iExmeProductoOrgId
	 *            Registro importador
	 * @param errMsg
	 *            Mensaje de error
	 * @param isImported
	 *            Si es o no importado
	 */
	private void setStatus(final int iExmeProductoOrgId, final String errMsg,
			final boolean isImported, final String trxName) {
		DB.executeUpdate(
				"UPDATE I_EXME_ProductoOrg SET I_ErrorMsg = ?, I_IsImported = ? WHERE I_EXME_ProductoOrg_ID = ?",
				new Object[] { errMsg, isImported, iExmeProductoOrgId },
				trxName
		);
	}

	/**
	 * Actualización de modificadores
	 */
	private void updateModifiers() {

		// Actualiza con la informacion proporcionada en el formato, los modificadores (esto aplica solamente para USA)
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg i  ");
		SQL.append(" SET exme_modifier1_id = (SELECT exme_modifiers_id  FROM exme_modifiers WHERE value = modifier_1 ) ");
		SQL.append(" WHERE modifier_1 IS NOT NULL ");
		SQL.append(" AND AD_Client_ID =  ? ");
		SQL.append(" AND AD_Org_ID = ? ");
		SQL.append(" AND I_IsImported = 'N' ");
		SQL.append(" AND Processed = 'N' ");

		int noRecords = DB.executeUpdate(SQL.toString(), new Integer[] { adClientId, adOrgId }, null);
		log.info("Records updated for modifier1 = " + noRecords);

		final StringBuilder stringBu = new StringBuilder("UPDATE I_EXME_ProductoOrg i  ");
		stringBu.append(" SET exme_modifier2_id = (SELECT exme_modifiers_id  FROM exme_modifiers WHERE value = modifier_2 ) ");
		stringBu.append(" WHERE modifier_2 IS NOT NULL ");
		stringBu.append(" AND AD_Client_ID =  ? ");
		stringBu.append(" AND AD_Org_ID = ? ");
		stringBu.append(" AND I_IsImported = 'N' ");
		stringBu.append(" AND Processed = 'N' ");

		noRecords = DB.executeUpdate(stringBu.toString(), new Integer[] { adClientId, adOrgId }, null);
		log.info("Records updated for modifier2 = " + noRecords);

		final StringBuilder sBuilder = new StringBuilder("UPDATE I_EXME_ProductoOrg i  ");
		sBuilder.append(" SET exme_modifier3_id = (SELECT exme_modifiers_id  FROM exme_modifiers WHERE value = modifier_3 ) ");
		sBuilder.append(" WHERE modifier_3 IS NOT NULL ");
		sBuilder.append(" AND AD_Client_ID =  ? ");
		sBuilder.append(" AND AD_Org_ID = ? ");
		sBuilder.append(" AND I_IsImported = 'N' ");
		sBuilder.append(" AND Processed = 'N' ");

		noRecords = DB.executeUpdate(sBuilder.toString(), new Integer[] { adClientId, adOrgId }, null);
		log.info("Records updated for modifier3 = " + noRecords);

		final StringBuilder stringBuild = new StringBuilder("UPDATE I_EXME_ProductoOrg i  ");
		stringBuild.append(" SET exme_modifier4_id = (SELECT exme_modifiers_id  FROM exme_modifiers WHERE value = modifier_4 ) ");
		stringBuild.append(" WHERE modifier_4 IS NOT NULL ");
		stringBuild.append(" AND AD_Client_ID =  ? ");
		stringBuild.append(" AND AD_Org_ID = ? ");
		stringBuild.append(" AND I_IsImported = 'N' ");
		stringBuild.append(" AND Processed = 'N' ");

		noRecords = DB.executeUpdate(stringBuild.toString(), new Integer[] { adClientId, adOrgId }, null);
		log.info("Records updated for modifier4 = " + noRecords);

	}

	/**
	 * Actualización de precios
	 */
	private void updatePrice() {

		ResultSet resultS = null;
		PreparedStatement pstmt = null;

		final MEXMEConfigPre configPre = MEXMEConfigPre.get(Env.getCtx(), null);

		try {

			final StringBuilder sBuilder = new StringBuilder("SELECT * ");
			sBuilder.append(" FROM I_EXME_ProductoOrg ");
			sBuilder.append(" WHERE AD_Client_ID = ? ");
			sBuilder.append(" AND AD_Org_ID = ? ");
			sBuilder.append(" AND IsActive = 'Y' ");
			if(MEXMEConfigPre.BUSQUEDAPRECIO_LP.equals(configPre.getBusquedaPrecio())){
				sBuilder.append(" AND m_pricelist_version_id IS NOT NULL ");
				sBuilder.append(" AND M_PRICELIST_ID > 0 ");
				sBuilder.append(" AND pricelist_vol IS NOT NULL ");
			}
			sBuilder.append(" AND I_ErrorMsg IS NULL ");
			sBuilder.append(" AND I_IsImported = 'Y'");
			sBuilder.append(" AND Processed = 'N'");
			sBuilder.append(" AND M_Product_ID > 0");

			pstmt = DB.prepareStatement(sBuilder.toString(), null);

			pstmt.setInt(1, adClientId);
			pstmt.setInt(2, adOrgId);

			resultS = pstmt.executeQuery();

			while (resultS.next()) {

				if(MEXMEConfigPre.BUSQUEDAPRECIO_LP.equals(configPre.getBusquedaPrecio())){
					insertProductPrice(
							resultS.getInt(X_I_EXME_ProductoOrg.COLUMNNAME_M_Product_ID),
							resultS.getInt("m_pricelist_version_id"),
							resultS.getInt("C_UOM_ID"),
							resultS.getInt("C_UOMVolume_ID"),
							resultS.getBigDecimal("price"),
							resultS.getBigDecimal("pricelist_vol")
					);
				} else {
					insertProductoPrecio(
							resultS.getInt(X_I_EXME_ProductoOrg.COLUMNNAME_M_Product_ID),
							resultS.getBigDecimal("price")
					);
				}

			}

		} catch (final SQLException e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(resultS, pstmt);
		}
	}

	/**
	 * Actualización de lista de precios
	 */
	public void updatePriceListID() {

		final StringBuilder sql = new StringBuilder("UPDATE I_EXME_ProductoOrg ");
		sql.append(" SET m_pricelist_id = (SELECT M_PriceList_ID FROM M_PriceList");
		sql.append(" where  isActive = 'Y' and name = m_pricelist_name AND  AD_Client_ID = ? AND AD_Org_ID = ? ) ");
		sql.append(" WHERE AD_Client_ID =  ? ");
		sql.append(" AND AD_Org_ID = ? ");
		sql.append(" AND m_pricelist_name IS NOT NULL ");
		sql.append(" AND I_IsImported = 'Y' ");
		sql.append(" AND Processed = 'N' ");

		final int noRecords = DB.executeUpdate(sql.toString(), new Integer[] { adClientId, adOrgId, adClientId, adOrgId }, null);
		log.info("Records updated for PRICELISTID = " + noRecords);

	}

	/**
	 * Actualización de versión de lista de precios
	 *
	 * @param priceListID
	 * @param versionID
	 */
	public void updatePriceListVersionID(final int priceListID, final int versionID) {

		// update modifiers

		final StringBuilder stringB = new StringBuilder("UPDATE I_EXME_ProductoOrg");
		stringB.append(" SET m_pricelist_version_id = ? ");
		stringB.append(" WHERE m_pricelist_id = ? ");
		stringB.append(" AND AD_Client_ID = ?");
		stringB.append(" AND AD_Org_ID = ? ");
		stringB.append(" AND I_IsImported = 'Y'");
		stringB.append(" AND Processed = 'N'");

		final int noRecords = DB.executeUpdate(stringB.toString(), new Integer[] { versionID, priceListID, adClientId, adOrgId }, null);
		log.info("Records updated for pricelistversionID = " + noRecords);

	}

	private void updateProduct(final int ipoId, final int productId, final String trxName) {
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg ");
		SQL.append(" SET M_PRODUCT_ID = ? ");
		SQL.append(" WHERE I_EXME_ProductoOrg_ID = ? ");

		DB.executeUpdate(SQL.toString(), new Object[] { productId, ipoId }, trxName);
	}

	/**
	 * Actualiza el <b>M_Product_Category_ID</b> para los registros que tengan <b>M_product_category_value diferente de nulo</b> y <b>M_Product_Category_ID nulo</b>
	 * Se agrega el nivel de acceso. Jesus Cantu el 14 Feb 2014.
	 */
	private void updateProductCategory() {
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg i  ");
		SQL.append(" SET M_Product_Category_ID = ( SELECT M_Product_Category_ID     ");
		SQL.append(" FROM M_Product_Category WHERE Value = i.M_product_category_value AND ad_client_id in (0, ?))  ");
		SQL.append(" WHERE I_IsImported<>'Y' ");
		SQL.append(" AND Processed = 'N' ");
		SQL.append(" AND M_Product_Category_ID IS NULL ");
		SQL.append(" AND M_product_category_value  IS NOT NULL  ");
		SQL.append(" AND AD_Client_ID = ?");
		SQL.append(" AND AD_Org_ID = ? ");
		DB.executeUpdate(SQL.toString(), new Object[] {adClientId, adClientId, adOrgId}, null);
	}

	/**
	 * Actualiza <b>c_taxcategory_id</b> de los registros que tengan <b>c_taxcategory_name diferente de nulo</b> y <b>c_taxcategory_id nulo</b>
	 * Se agrega el nivel de acceso. Jesus Cantu el 14 Feb 2014.
	 */
	private void updateTaxCategory() {
		final StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg im ");
		SQL.append(" SET c_taxcategory_id = ( SELECT c_taxcategory_id     ");
		SQL.append(" FROM c_taxcategory WHERE Name = im.c_taxcategory_name AND ad_client_id = ? AND AD_Org_ID = ?)  ");
		SQL.append(" WHERE I_IsImported<>'Y' ");
		SQL.append(" AND Processed = 'N' ");
		SQL.append(" AND c_taxcategory_id IS NULL ");
		SQL.append(" AND c_taxcategory_name  IS NOT NULL  ");
		SQL.append(" AND AD_Client_ID = ?");
		SQL.append(" AND AD_Org_ID = ? ");
		DB.executeUpdate(SQL.toString(), new Object[] {adClientId, adOrgId, adClientId, adOrgId}, null);
	}

	/**
	 * Actualizar <b>c_uom_id y c_uomvolume_id</b> cuando <b>uom_value y c_uomvolume_value no sean nulas</b> y <b>c_uom_id y c_uomvolume_id sean nulas</b>
	 */
	private void updateUDMS() {
		StringBuilder SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg pr  ");
		SQL.append(" SET c_uom_id = ( SELECT c_uom_id      ");
		SQL.append(" FROM c_uom WHERE Name = pr.uom_value )  ");
		SQL.append(" WHERE I_IsImported<>'Y' ");
		SQL.append(" AND Processed = 'N' ");
		SQL.append(" AND c_uom_id IS NULL ");
		SQL.append(" AND uom_value  IS NOT NULL  ");
		SQL.append(" AND AD_Client_ID = ?");
		SQL.append(" AND AD_Org_ID = ? ");
		DB.executeUpdate(SQL.toString(), new Object[] {adClientId, adOrgId}, null);

		SQL = new StringBuilder("UPDATE I_EXME_ProductoOrg p  ");
		SQL.append(" SET c_uomvolume_id = ( SELECT c_uom_id      ");
		SQL.append(" FROM c_uom WHERE Name = p.c_uomvolume_value )  ");
		SQL.append(" WHERE I_IsImported<>'Y' ");
		SQL.append(" AND Processed = 'N' ");
		SQL.append(" AND c_uomvolume_id IS NULL ");
		SQL.append(" AND c_uomvolume_value  IS NOT NULL  ");
		SQL.append(" AND AD_Client_ID = ?");
		SQL.append(" AND AD_Org_ID = ? ");
		DB.executeUpdate(SQL.toString(), new Object[] {adClientId, adOrgId}, null);
	}


	/**
	 * Actualización de precios
	 */
	private void insertProductoPrecio(final int productID, final BigDecimal price) {

		try {

			final MEXMEProductoPrecio precio = new MEXMEProductoPrecio(Env.getCtx(),0,null);
			precio.setM_Product_ID(productID);
			precio.setPriceLimit(price);
			precio.setPriceList(price);
			precio.setPriceStd(price);

			if (precio.save()) {
				log.info("EXME_PRODUCTOPRECIO insert");
			}

		} catch (final Exception e) {
			log.severe(e.getMessage());
		}
	}
}
