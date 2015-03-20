package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author twry
 * Modificado por Lama: Se utiliza clase Query, revisión de PMD
 */
public class MEXMECtaPacPaq extends X_EXME_CtaPacPaq {

	/** serialVersionUID */
	private static final long serialVersionUID = -830349988325607575L;
	/** static logger */
	private static CLogger slog = CLogger.getCLogger(MEXMECtaPacPaq.class);
	/** obj. paquete base */
	private transient MEXMEPaqBaseVersion mPaqBaseVersion = null;
	/** obj. cuenta paciente */
	private transient MEXMECtaPac mCtaPac = null;
	/** si se ha eliminado */
//	private boolean borrado = false;
	/** si se ha agregado */
//	private transient boolean esNuevo = true;
	/** se guardara */
//	private boolean guardarEnBitacora = false;
	/** si es paquete o minipaquete */
//	private boolean esPaqueteFalso = false;
	/** cargo del paquete */
	private transient MCtaPacDet ctaPacDet = null;

	/**
	 * Constructor
	 * 
	 * @param ctx : Contexto
	 * @param rs : ResultSet
	 * @param trxName : nombre de transaccion
	 */
	public MEXMECtaPacPaq(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx : Contexto
	 * @param EXME_CtaPacPaq_ID : id
	 * @param trxName : nombre de transaccion
	 */
	public MEXMECtaPacPaq(final Properties ctx, final int EXME_CtaPacPaq_ID, final String trxName) {
		super(ctx, EXME_CtaPacPaq_ID, trxName);
	}
	/**
	 * listado de paquetes relacionados a cuenta paciente
	 * 
	 * @param ctx : Contexto
	 * @param ctapacId : ctapac id
	 * @param orderBy : ordenamiento de la consulta
	 * @param trxName : nombre de la transaccion
	 * @return List<MEXMECtaPacPaq> listado de relacion paq-cta
	 */
	public static List<MEXMECtaPacPaq> getFromCtaPac(final Properties ctx, final int ctapacId, final String orderBy, final String trxName) {
		return get(ctx, " EXME_CtaPacPaq.EXME_CtaPac_ID=? ", orderBy, trxName, ctapacId);
	}
	/**
	 * listado de paquetes relacionados a cuenta paciente
	 * 
	 * @param ctx : Contexto
	 * @param whereClause : condiciones de la consulta
	 * @param orderBy : ordenamiento de la consulta
	 * @param trxName : nombre de la transaccion
	 * @return List<MEXMECtaPacPaq> listado de relacion paq-cta
	 */
	public static List<MEXMECtaPacPaq> get(final Properties ctx, final String whereClause, final String orderBy, final String trxName, Object... params) {
		return new Query(ctx, Table_Name, StringUtils.startsWith(StringUtils.trim(whereClause), "AND") ? " 1=1 " + whereClause : whereClause, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(params)//
			.setOrderBy(orderBy)//
			.list();
	}

	/** @return objeto de la version del paquete */
	public MEXMEPaqBaseVersion getPaqBaseVersion() {
		if (mPaqBaseVersion == null && getEXME_PaqBase_Version_ID() > 0) {
			mPaqBaseVersion = new MEXMEPaqBaseVersion(getCtx(), getEXME_PaqBase_Version_ID(), get_TrxName());
		}
		return mPaqBaseVersion;
	}

	/** @return Objeto de la cuenta paciente */
	public MEXMECtaPac getCtaPac() {
		if (mCtaPac == null && getEXME_CtaPac_ID() > 0) {
			mCtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return mCtaPac;
	}


	/**
	 * Cambia en la tabla EXME_CtaPacPaq (Relacion ctapac-paquetes) la extension
	 * de los paquetes
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static boolean cambiarExtension(final Properties ctx,
			final int pCtaPacExtID, final int pCtaPacID,
			final int pPaqBaseVersionID, final String trxName) {
		final StringBuilder sql = new StringBuilder(100);
		sql.append("EXME_CtaPacPaq.IsActive='Y' ");
		sql.append(" AND EXME_CtaPacPaq.EXME_CtaPac_ID=? ");
		sql.append(" AND EXME_CtaPacPaq.EXME_PaqBase_Version_ID=? ");
		final MEXMECtaPacPaq ctaPacPaq = new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(pCtaPacID, pPaqBaseVersionID)//
			.first();
		ctaPacPaq.setEXME_CtaPacExt_ID(pCtaPacExtID);
		return ctaPacPaq.save();
	}

	/**
	 * Crea el cargo del paquete
	 * 
	 * @param estServLog
	 * @return
	 */
	public MCtaPacDet cargo(final MEXMEEstServ estServLog) {
		MCtaPacDet ctaPacDet = new MCtaPacDet(getCtx(), 0, get_TrxName());
		ctaPacDet.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		ctaPacDet.setLine();
		ctaPacDet.setDescription(getPaqBaseVersion().getName());
		ctaPacDet.setDateOrdered(Env.getCurrentDate());
		ctaPacDet.setQtyDelivered(Env.ONE);
		ctaPacDet.setQtyOrdered(Env.ONE);
		ctaPacDet.setQtyEntered(Env.ONE);
		ctaPacDet.setQtyPaquete(Env.ONE);
		ctaPacDet.setQtyPendiente(Env.ONE);

		ctaPacDet.setM_Product_ID(getPaqBaseVersion().getM_Product_ID());
		ctaPacDet.setC_UOM_ID(getPaqBaseVersion().getProduct().getC_UOM_ID());
		ctaPacDet.setEXME_PaqBase_Version_ID(getEXME_PaqBase_Version_ID());
		ctaPacDet.setM_Warehouse_ID(Env.getM_Warehouse_ID(getCtx()));
		ctaPacDet.setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));// Organizacion
		ctaPacDet.setEXME_CtaPacExt_ID(getCtaPac().getEXME_CtaPacExt_ID());
		ctaPacDet.setCosto(Env.ZERO);
		ctaPacDet.setC_Currency_ID(Env.getC_Currency_ID(getCtx()));
		ctaPacDet.setDateDelivered(Env.getCurrentDate());
		ctaPacDet.setTipoArea(estServLog.getTipoArea());
		ctaPacDet.setEXME_Area_ID(estServLog.getEXME_Area_ID());
//		MPrecios pv = PrecioVenta.getPrice(getCtx(),
//				estServLog.getTipoArea(), ctaPacDet.getM_Product_ID(),
//				ctaPacDet.getQtyOrdered(), ctaPacDet.getC_UOM_ID(),
//				PreciosVenta.PVH, 0, 0, ctaPacDet.getM_Warehouse_Sol_ID(),
//				ctaPacDet.getM_Warehouse_ID(), estServLog.getEXME_Area_ID(),
//				ctaPacDet.getDateOrdered(), true, null);
		
		final MPrecios preVta = GetPrice.getPriceCargo(getCtx(), ctaPacDet);

		ctaPacDet = preVta.preciosActual(ctaPacDet);
		ctaPacDet.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_BasePack);

		if (ctaPacDet.getC_Tax_ID() <= 0) {
			ctaPacDet.setC_Tax_ID(MEXMETax.getDefaultTaxID(getCtx(), get_TrxName()));
		}
		return ctaPacDet;
	}

	/**
	 * Buscar el cargo del paquete en la cuenta paciente
	 * 
	 * @return MCtaPacDet
	 */
	public MCtaPacDet getCargo() {
		if (ctaPacDet == null) {
			final StringBuilder sql = new StringBuilder();
			sql.append(" EXME_CtaPacDet.IsActive='Y' ");
			sql.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID=? ");
			sql.append(" AND EXME_CtaPacDet.EXME_PaqBase_Version_ID=? ");
			sql.append(" AND EXME_CtaPacDet.TipoLinea=? ");
			ctaPacDet = new Query(getCtx(), MCtaPacDet.Table_Name, sql.toString(), get_TrxName())//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(getEXME_CtaPac_ID(), getEXME_PaqBase_Version_ID(), MCtaPacDet.TIPOLINEA_BasePack)//
				.first();
		}
		return ctaPacDet;
	}

	/**
	 * Guarda la relacion paquete(s) cuenta
	 * 
	 * @param ctx : Contexto
	 * @param listboxDetalle : List<MEXMECtaPacPaq> listado de obj. con la version del paquete
	 * @param ctapac : id de la cuenta paciente
	 * @param trxName : Nombre de la transaccion
	 */
	public static boolean save(final Properties ctx,
			final List<MEXMECtaPacPaq> listboxDetalle, final int ctapac,
			final String trxName) {
		//
		final MEXMEEstServ estServLog = MEstServAlm.getEstServ(ctx, Env.getM_Warehouse_ID(ctx), null);
//		boolean success = true;
//		boolean modificado = false;
		final MEXMECtaPac mctapac = new MEXMECtaPac(ctx, ctapac, null);
		final List<MEXMECtaPacPaq> listActive = new ArrayList<MEXMECtaPacPaq>();
//		try {
			// Listado de paquetes
			for (MEXMECtaPacPaq paq :listboxDetalle) {
				// Si esta activa y es nueva la relacion
				if (paq.isActive() && paq.getEXME_CtaPacPaq_ID() <= 0) {
					// busca si se relaciono con anterioridad.
					final List<MEXMECtaPacPaq> lst = paq.getCtaPacPaqPrevio();
					// no hay registros previos
					if(lst.isEmpty()){
						paq.setEXME_CtaPac_ID(mctapac.getEXME_CtaPac_ID());
						paq.setEXME_CtaPacExt_ID(mctapac.getEXME_CtaPacExt_ID());
						// Se guarda la relacion cta paquete
						if (paq.save(trxName)) {
							listActive.add(paq);
						} else {
							throw new MedsysException();
//							success = false; break;
						}
						// se agrega el cargo del paquete
						if (!paq.cargo(estServLog).save(trxName)) {
							throw new MedsysException();
//							success = false; break;
						}
					} else {
						// Se activa el registro EXME_CtaPacPaq (primer registro)
						lst.get(0).setEXME_CtaPacExt_ID(mctapac.getEXME_CtaPacExt_ID());
						lst.get(0).setIsActive(true);
						if (lst.get(0).save(trxName)) {
							listActive.add(lst.get(0));
						} else {
							throw new MedsysException();
//							success = false; break;
						}
						// Se buscan cargos previos para ese paquete
						final List<MCtaPacDet> lstCharge = paq.getCargoPrevio();
						// Si no existe un cargo previo se crea
						if(lstCharge.isEmpty()){
							if (!lst.get(0).cargo(estServLog).save(trxName)) {
								throw new MedsysException();
//								success = false; break;
							}
						} else {
							// Si existe un cargo previo se actuva
							lstCharge.get(0).setEXME_CtaPacExt_ID(mctapac.getEXME_CtaPacExt_ID());
							lstCharge.get(0).setIsActive(true);
							lstCharge.get(0).setCostAverage(BigDecimal.ZERO);
							lstCharge.get(0).setCostStandard(BigDecimal.ZERO);
							lstCharge.get(0).setPriceLastPO(BigDecimal.ZERO);
							if (!lstCharge.get(0).save(trxName)) {
								throw new MedsysException();
//								success = false; break;
							}
						}
					}
				} else {
					// Si se ha borrado y tenia ID
					if (!paq.isActive() && paq.getEXME_CtaPacPaq_ID() > 0) {
						paq.setIsActive(false);
						
						if (paq.save(trxName)) {
//							modificado = true;
						} else {
							throw new MedsysException();
//							success = false; break;
						}
						
						final MCtaPacDet cargo = paq.getCargo();
						if(cargo!=null){
							cargo.setIsActive(false);
							if (!cargo.save(trxName)) {
								throw new MedsysException();
//								success = false; break;
							}
						}
					}
				}// fin else
			}

			// Actualizar cargos de la cuenta paciente
			// que no haya ocurrido ningun error, que existan paquetes a relacionar nuevos o recien activados
			// o que se haya quitado alguno y que se haga el match correctamente
//			if (/*success && */(!listActive.isEmpty() || modificado) && !ChargePackages.getMatch(Env.getCtx(), ctapac, trxName)) {
//				slog.log(Level.SEVERE, "Imposible Match");
//			}
//		} catch (Exception ex) {
//			success = false;
//			slog.log(Level.SEVERE, "MEXMECtaPacPaq.save", ex);
//		}
		return true;
	}

//	public boolean isNew() {
//		return esNuevo;
//	}
//	public void setNew(final boolean isNew) {
//		this.esNuevo = isNew;
//	}

	/** Busca la relacion cuenta paquete inactivos  */
	public List<MEXMECtaPacPaq> getCtaPacPaqPrevio(){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_CtaPacPaq.IsActive='N' ");
		sql.append(" AND EXME_CtaPacPaq.EXME_CtaPac_ID=? ");
		sql.append(" AND EXME_CtaPacPaq.EXME_PaqBase_Version_ID=? ");
		return new Query(getCtx(), Table_Name, sql.toString(), get_TrxName())//
			.setOnlyActiveRecords(false)//
			.addAccessLevelSQL(true)//
			.setParameters(getEXME_CtaPac_ID(), getEXME_PaqBase_Version_ID())//
			.setOrderBy("Created DESC")//
			.list();
	}

	/**
	 * Reactivar cargo de paquete
	 * @return
	 */
	public List<MCtaPacDet> getCargoPrevio(){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_CtaPacDet.IsActive='N' ");
		sql.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID=? ");
		sql.append(" AND EXME_CtaPacDet.EXME_PaqBase_Version_ID=? ");
		sql.append(" AND EXME_CtaPacDet.tipoLinea=? ");
		return new Query(getCtx(), MCtaPacDet.Table_Name, sql.toString(), get_TrxName())//
			.setOnlyActiveRecords(false)//
			.addAccessLevelSQL(true)//
			.setParameters(getEXME_CtaPac_ID(), getEXME_PaqBase_Version_ID(), MCtaPacDet.TIPOLINEA_BasePack)//
			.setOrderBy("Created DESC")//
			.list();
	}
	
	/** @return Nombre del paquete base y version */
	public String getPackageName() {
		final StringBuilder str = new StringBuilder();
		if (getEXME_PaqBase_Version_ID() > 0) {
			if (getPaqBaseVersion().getEXME_PaqBase_ID() > 0) {
				str.append(getPaqBaseVersion().getPaquete().getName());
				str.append(" - ");
			}
			str.append(getPaqBaseVersion().getName());
		}
		return str.toString();
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// Si es nuevo, o se reactiva, se debe hacer el balance de paquete
		if ((newRecord || is_ValueChanged("isActive")) && success && isActive()) {
			return createNewBalance();
		} else if (is_ValueChanged("isActive") && !isActive()) {
			MEXMESaldoPaquete.deleteAll(getCtx(), getEXME_CtaPac_ID(), getEXME_PaqBase_Version_ID(), get_TrxName());
			
			MCtaPacDet charge = getCargo();
			
			boolean saved = true;

			if (charge != null) {
				charge.setCostAverage(BigDecimal.ZERO);
				charge.setCostStandard(BigDecimal.ZERO);
				charge.setPriceLastPO(BigDecimal.ZERO);

				saved = charge.save(get_TrxName());
			}
			
			return saved && super.afterSave(newRecord, success);
		} else {
			return super.afterSave(newRecord, success);
		}
	}
	
	/**
	 * Crea el nuevo balance, usa la misma trx del objeto
	 * 
	 * @return
	 */
	public boolean createNewBalance() {
		 return createNewBalance(getCtx(), getEXME_CtaPac_ID(), getEXME_PaqBase_Version_ID(), get_TrxName());
	}
	
	/**
	 * Crear el balance del paquete
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param paqBaseVersionId
	 *            Versión del Paquete
	 * @param trxName
	 *            Transacción
	 * @return True = exito
	 */
	public static boolean createNewBalance(Properties ctx, int ctaPacId, int paqBaseVersionId, String trxName) {
		boolean retValue = true;
		
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);
		int adUserId = Env.getAD_User_ID(ctx);

		MEXMEPaqBaseVersion version = new MEXMEPaqBaseVersion(ctx, paqBaseVersionId, null);

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("  EXME_SaldoPaquete ");
			sql.append("WHERE ");
			sql.append("  EXME_PaqBase_Version_ID = ? AND ");
			sql.append("  EXME_CtaPac_ID = ? ");

			DB.executeUpdate(sql.toString(), new Object[] { paqBaseVersionId, ctaPacId }, trxName);

			sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append("  exme_ctapacdet ");
			sql.append("SET ");
			sql.append("  tipolinea = ? ");
			sql.append(" WHERE ");
			sql.append("  exme_ctapac_id = ? AND ");
			sql.append("  tipolinea = ? AND ");
			sql.append("  exme_paqbase_version_id = ? AND ");
			sql.append("  seDevolvio = 'N' AND ");
			sql.append("  isFacturado = 'N' AND ");
			sql.append("  isActive = 'Y' ");

			DB.executeUpdate(sql.toString(), new Object[] { MCtaPacDet.TIPOLINEA_Charge, ctaPacId, MCtaPacDet.TIPOLINEA_Exempt, paqBaseVersionId }, trxName);

			List<MEXMEPaqBaseDet> lst = version.getLstDetalle();

			outter: for (MEXMEPaqBaseDet det : lst) {

				for (int i = 0; i < det.getCantidad().intValue(); i++) {
					Object[] arr = new Object[14];
					
					arr[0] = 1244322; // Secuencia EXME_SaldoPaquete
					arr[1] = Env.getCurrentDate();
					arr[2] = Env.getCurrentDate();
					arr[3] = "Y";
					arr[4] = adUserId;
					arr[5] = adUserId;
					arr[6] = adClientId;
					arr[7] = adOrgId;
					arr[8] = ctaPacId;
					arr[9] = paqBaseVersionId;
					arr[10] = det.getM_Product_ID();
					arr[11] = det.getOp_Uom();
					arr[12] = BigDecimal.ONE;
					arr[13] = BigDecimal.ONE;

					int res = DB.executeUpdate(MEXMESaldoPaquete.SQL_INSERT, arr, trxName);

					if (res < 1) {
						retValue = false;
						break outter;
					}
				}
			}
		} catch (Exception e) {
			retValue = false;
			slog.log(Level.SEVERE, null, e);
		}

		return retValue;
	}
	
	private String packName;
	private String versionName;

	public String getPackName() {
		if (StringUtils.isEmpty(packName)) {
			getPaqBaseVersion();
			packName = getPaqBaseVersion().getPaquete().getName();
		}
		return packName;
	}

	public String getVersionName() {
		if (StringUtils.isEmpty(versionName)) {
			versionName = getPaqBaseVersion().getName();
		}
		return versionName;
	}
}