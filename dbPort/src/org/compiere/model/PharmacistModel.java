/**
 * 
 */
package org.compiere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.MPharmacistWorkList;
import org.compiere.model.bpm.TransferRequirement;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Modelo para el llenado de los datos
 * que mostrara cada una de las pestañas del worklist del farmaceutico
 * @author twry
 * 
 */
public class PharmacistModel {
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(PharmacistModel.class);
	private final int key;
	private final String name;
	private final String tableName;
	private MPharmacistWorkList query;

	/** MEDS */
	public static final int B_MEDS = 21;

	/**
	 * PharmacistModel
	 * 
	 * @param key
	 * @param name
	 * @param tableName
	 */
	public PharmacistModel(final int key, final String name,
			final String tableName) {
		super();
		this.key = key;
		this.name = name;
		this.tableName = tableName;
	}

	public int getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public MPharmacistWorkList getQuery() {
		return query;
	}

	public String getTableName() {
		return tableName;
	}

	public void setQuery(final String pSelect, final String pFrom,
			final String orderby, final String pCount, final Integer[] params) {
		this.query = new MPharmacistWorkList(Env.getCtx(), pSelect, pFrom, orderby, pCount, null)//
				.setParameters(params);
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Consulta a la base de datos para obtener
	 * los datos a mostrar en las pestañas del worklist
	 * @param ctx Conexto
	 * @param authenticated Si autenticar o no
	 * @param reviewed Si revisados o no
	 * @param prn Si son prn o no
	 * @param autostop si es auto-detenido
	 * @return Modelo
	 */
	public static PharmacistModel getTabMeds(final Properties ctx,
			final Boolean authenticated, final boolean reviewed,
			final boolean prn, final boolean autostop) {
		// solo un almacen surte medicamentos
		final int mWarehouseId = TransferRequirement.getWarehouseAssId(Env.getCtx());
		final List<Integer> params = new ArrayList<Integer>();
		
		final StringBuilder count  = new StringBuilder(" SELECT COUNT(*) ")
		.append(" FROM ( ")
		.append("      SELECT DISTINCT EXME_PrescRXDet.EXME_PrescRXDet_ID ");
		
		final StringBuilder select = new StringBuilder(
				" SELECT EXME_PrescRXDet.*                        ")
				.append(" , mv.DocumentNo AS backorder            ")
				.append(" , COALESCE (tablaPO.Existe,0) AS Existe ")
				.append(" , COALESCE (tablaST.QtyAvailable,0) AS QtyAvailable ")
				.append(" , dis.EXME_Dispense_ID              ");

		final StringBuilder sql = new StringBuilder(" FROM  EXME_PrescRXDet ")
		.append("    INNER JOIN EXME_PrescRX rx ")
		.append("    ON (rx.EXME_PrescRX_ID=EXME_PrescRXDet.EXME_PrescRX_ID AND rx.Tipo = ")
		.append(DB.TO_STRING(X_EXME_PrescRX.TIPO_MedicalPrescription))
		.append(" ) ")
		
		// Conteo 1-1
		.append("    LEFT JOIN EXME_Dispense dis ")
		.append("    ON dis.EXME_PrescRXDet_ID = EXME_PrescRXDet.EXME_PrescRXDet_ID AND dis.IsActive = 'Y' AND dis.undeliveredqty > 0 ")
		
		// Backorder 1-1 Activo **
		.append("    LEFT JOIN M_Movement mv ")
		.append("    ON mv.EXME_PrescRXDet_ID = dis.EXME_PrescRXDet_ID AND mv.IsActive = 'Y' AND mv.BackOrder = 'Y' AND mv.DocStatus = 'DR' ")

		// Chargemaster 1-1 POR GENERICO
//		.append("    LEFT JOIN M_Product p ")
//		.append("    ON EXME_PrescRXDet.EXME_GenProduct_ID = p.EXME_GenProduct_ID AND p.IsActive  = 'Y' ")
//		.append("    LEFT JOIN EXME_ProductoOrg po ")
//		.append("    ON p.M_Product_id = po.M_Product_id AND po.IsActive  = 'Y' AND po.AD_Org_ID = ")
		.append("    LEFT JOIN ( ")
		.append("    SELECT DISTINCT p.EXME_GenProduct_ID, COUNT(*) as Existe ")
		.append("    FROM   EXME_ProductoOrg po ")
		.append("    INNER  JOIN M_Product   p  ON  p.M_Product_id = po.M_Product_ID ")
		.append("    WHERE  po.IsActive  = 'Y'  ")
		.append("    AND    po.AD_Org_ID = ? "); params.add(Env.getAD_Org_ID(ctx));
		sql
		.append("    AND p.IsActive  = 'Y' ")
		.append("    AND p.EXME_GenProduct_ID IS NOT NULL ")
		.append("    GROUP BY p.EXME_GenProduct_ID ")
		.append("    ) as tablaPO ON  tablaPO.EXME_GenProduct_ID = EXME_PrescRXDet.EXME_GenProduct_ID ")

		// Existencias 1-1 POR GENERICO
//		.append(" LEFT JOIN EXME_StockWarehouse_V stock ")
//		.append(" ON po.M_Product_ID = stock.M_Product_ID AND stock.AD_Org_ID = ")
//		.append(Env.getAD_Org_ID(ctx))
//		.append(" AND stock.m_warehouse_id = ")
		.append("    LEFT JOIN ( ")
		.append("    SELECT p.EXME_GenProduct_ID, SUM(stock.qtyavailable) as qtyavailable ")
		.append("    FROM   EXME_StockWarehouse_V stock ")
		.append("    INNER  JOIN M_Product p ON  p.M_Product_id = stock.M_Product_ID AND p.IsActive  = 'Y' ")
		.append("                            AND p.EXME_GenProduct_ID IS NOT NULL ")
		.append("    WHERE  stock.AD_Org_ID = ? "); params.add(Env.getAD_Org_ID(ctx));
		sql
		.append("    AND    stock.m_warehouse_id = ? "); params.add(mWarehouseId<1?Env.getM_Warehouse_ID(ctx):mWarehouseId);
		sql
		.append("    GROUP  BY p.EXME_GenProduct_ID ")
		.append("    ) as tablaST ON  tablaST.EXME_GenProduct_ID = EXME_PrescRXDet.EXME_GenProduct_ID AND tablaST.qtyavailable > 0 ")

		// Nivel de almacenamiento
//		.append(" LEFT JOIN M_Replenish re ")
//		.append(" ON po.M_Product_ID = re.M_Product_ID AND re.AD_Org_ID = ")
//		.append(Env.getAD_Org_ID(ctx))
//		.append(" AND re.m_warehouse_id = ")
//		.append(Env.getM_Warehouse_ID(ctx))

		// Condicion
		.append(" WHERE EXME_PrescRXDet.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",MEXMEPrescRXDet.Table_Name));

		// Etiqueta
		final StringBuilder title = new StringBuilder();
		title.append(Utilerias.getLabel("msj.meds"));
		title.append(" (");
		if (prn) {
			if (autostop){
				title.append(Utilerias.getLabel("msj.autoStopped"));
				sql.append(" AND EXME_PrescRXDet.Dose = to_char(?, 'FM9') AND ");
				params.add(Integer.parseInt(X_EXME_PrescRXDet.DOSE_Auto));
				// esta vencida
				if (DB.isPostgreSQL()) {
					sql.append("EXME_PrescRXDet.LastDay<now()");
				} else {
					sql.append("EXME_PrescRXDet.LastDay<sysdate");
				}
			} else {
				title.append(Utilerias.getLabel("msj.prn"));
			}
		} else {
			if (authenticated == null) {
				title.append(Utilerias.getLabel("cierre.pendientes"));
				sql.append(" AND EXME_PrescRXDet.isDelivered ='N' "); // Que no esten surtidos
			} else {
				if (authenticated) {
					title.append(Utilerias.getLabel("msj.authenticated"));
					sql.append(" AND EXME_PrescRXDet.Authenticated='Y' ");
				} else {
					title.append(Utilerias.getLabel("msj.notAuthenticated"));
					sql.append(" AND (EXME_PrescRXDet.Authenticated_Date IS NULL OR EXME_PrescRXDet.Authenticated='N') ");
				}
			}
		}
		title.append(")");

		// Tipo de farmaceutico
		if (sql.length() > 0) {
			sql.append(" AND ");
		}
		//(Draft)pestaña revisadas por mi : RXReview EXME_Pharmacist_ID (ID del logueo) DEBEN traer datos
		// AND (RXReview IS NOT null OR EXME_Pharmacist_ID=Env.getEXME_Pharmacist_ID)
		
		//(Worklist: pendientes) Todas las demas RXReview EXME_Pharmacist_ID DEBEN ser NULAS
	    // AND (RXReview IS null OR EXME_Pharmacist_ID Is NULL)
		// AND Tu condicion del engrane
		
		if (X_EXME_Pharmacist.TYPE_Technician.equals(Env
				.getEXME_PharmacistType(ctx))) {
			sql.append(" (EXME_PrescRXDet.RXReview ");
			sql.append(reviewed ? "IS NOT NULL OR" : "IS NULL OR");
			sql.append(" COALESCE(EXME_PrescRXDet.EXME_PharmacistT_ID,0) = ? ");
		} else {
			sql.append(" (EXME_PrescRXDet.RXReviewC ");
			sql.append(reviewed ? "IS NOT NULL OR" : "IS NULL OR");
			sql.append(" COALESCE(EXME_PrescRXDet.EXME_Pharmacistc_ID,0) = ? ");
		}
		params.add(reviewed ? Env.getEXME_Pharmacist_ID(ctx) : 0);
		sql.append(" ) ");
		
		// PRN
		sql.append(prn ? " AND EXME_PrescRXDet.IsPRN = 'Y' "
				: " AND EXME_PrescRXDet.IsPRN = 'N' ");

		// Order
		final String orderby = " Order By EXME_PrescRXDet.Created DESC ";

		// Contador
		count.append(sql.toString())
		.append(" ) AS TABLA ");
		
		Integer[] arparams = new Integer[params.size()];
		params.toArray(arparams);
		
		// Query
		final PharmacistModel pair = new PharmacistModel(B_MEDS,
				title.toString(), I_EXME_PrescRXDet.Table_Name);
		pair.setQuery(select.toString(), sql.toString(), orderby, count.toString(), arparams);
		
		slog.log(Level.INFO, null, sql.toString());
		return pair;
	}
}
