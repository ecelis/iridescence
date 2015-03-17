package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MProduct;
import org.compiere.model.MSubstitute;
import org.compiere.model.MUOM;
import org.compiere.model.X_I_M_Substitute;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * 
 * Proceso para importar los paquetes base
 * 
 * <b>Modificado: </b> $Author: otorres $
 * <p>
 * <b>En :</b> $Date: 2006/10/11 17:12:08 $
 * <p>
 * 
 * @author Omar Torres Atonal
 * @version $Revision: 1.7 $
 */

public class ImportProductoSustituto extends SvrProcess {

	/** Delete old Imported */
	private boolean m_deleteOldImported = false;

	/**
	 * Constructor por defecto.
	 */

	public ImportProductoSustituto() {
		super();

	}

	/**
	 * Obtener los valores de los par&aacute;metros
	 */

	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();

			if (name.equals("DeleteOldImported")) {
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}

	/**
	 * Corre el proceso.
	 * 
	 * @return Un mensaje de estado
	 * @throws Exception
	 */

	protected String doIt() throws Exception {

		List<X_I_M_Substitute> lista = MSubstitute.getDataToImport(getCtx(), null);

		Trx trx = null;

		try {

			trx = Trx.get(Trx.createTrxName("impSust"), true);

			for (int x = 0; x < lista.size(); x++) {
				StringBuilder error = new StringBuilder();

				boolean imported = false;

				X_I_M_Substitute databaseObject = lista.get(x);

				int productId = MProduct.getIdFromValueOrg(getCtx(), databaseObject.getM_Product_Value(),true);

				int sustitutoId = MProduct.getIdFromValue(databaseObject.getProd_Substitute_Value(), 0, false);

				int uomId = MUOM.getUOMID(databaseObject.getC_UOM_Value());

				int uomSustitutoId = MUOM.getUOMID(databaseObject.getUOM_Substitute_Value());

				BigDecimal qty = databaseObject.getQtyTarget();

				if (productId <= 0) {
					error.append(Utilerias.getAppMsg(getCtx(), "error.ImportProduct.noExisteProd", databaseObject.getM_Product_Value()));
					error.append(".");
				}

				if (sustitutoId <= 0) {
					error.append(Constantes.SPACE);
					error.append(Utilerias.getAppMsg(getCtx(), "error.ImportProduct.noExisteSust", databaseObject.getProd_Substitute_Value()));
					error.append(".");
				}

				if (uomId <= 0) {
					error.append(Constantes.SPACE);
					error.append(Utilerias.getAppMsg(getCtx(), "error.ImportProduct.noExisteUnidadProd", databaseObject.getC_UOM_Value()));
					error.append(".");
				}

				if (uomSustitutoId <= 0) {
					error.append(Constantes.SPACE);
					error.append(Utilerias.getAppMsg(getCtx(), "error.ImportProduct.noExisteUnidadSust", databaseObject.getUOM_Substitute_Value()));
					error.append(".");
				}

				if (BigDecimal.ZERO.compareTo(qty) >= 0) {
					error.append(Constantes.SPACE);
					error.append(Utilerias.getAppMsg(getCtx(),"error.encCtaPac.cant0oMenor"));
					error.append(".");
				}

				if (StringUtils.isEmpty(error.toString())) {
					MSubstitute sustituto = new MSubstitute(getCtx(), 0, null);

					sustituto.setM_Product_ID(productId);

					sustituto.setC_UOM_ID(uomId);

					sustituto.setSubstitute_ID(sustitutoId);

					sustituto.setUOM_Substitute_ID(uomSustitutoId);

					sustituto.setQtyTarget(qty);

					sustituto.setValidFrom(Env.getCurrentDate());

					sustituto.setValidTo(Env.getCurrentDate());

					try {
						if (sustituto.save(trx.getTrxName())) {
							imported = true;
						} else {
							error.append(Constantes.SPACE);
							error.append(Utilerias.getAppMsg(getCtx(), "msg.noIdentidicado"));
							error.append(".");
						}
					} catch (Exception ex) {
						error.append(Constantes.SPACE);
						error.append(ex.getMessage());
						error.append(".");

						log.log(Level.SEVERE, null, ex);
					}
				}

				databaseObject.setI_IsImported(imported);
				databaseObject.setProcessed(true);
				databaseObject.setDescription(error.toString());
				databaseObject.save(trx.getTrxName());
			}

			Trx.commit(trx);

		} catch (Exception ex) {
			log.log(Level.SEVERE, null, ex);
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}

		return null;
	}
}