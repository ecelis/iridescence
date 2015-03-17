package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.SecureEngine;
import org.compiere.util.ValueNamePair;

/**
 * 
 * 
 * @author Lorena Lama
 */
public class MEXMECama extends X_EXME_Cama {
	
	private static final long serialVersionUID = 1L;

	private static final CLogger slog = CLogger.getCLogger(MEXMECama.class);
	
	private MEXMEMejoras mejoras = null;
	private MEXMEHabitacion habitacion = null;

	public static final int LINEAS_CHART = 1;

	public static final int BARRAS_CHART = 2;

	/**
	 * 
	 * @param ctx
	 * @param EXME_Cama_ID
	 * @param trxName
	 */
	public MEXMECama(final Properties ctx, final int EXME_Cama_ID, final String trxName) {
		super(ctx, EXME_Cama_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECama(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Available */
	public static final String ESTATUS_Available = "D";
	/** Occupied */
	public static final String ESTATUS_Occupied = "O";
	/** Maintenance ***/
	public static final String ESTATUS_Maintenance = "M";
	/** Dirty **/
	public static final String ESTATUS_Dirty = "S";
	/** Clean **/
	public static final String ESTATUS_Clean = "L";
	/** Pre - Alta : SEDENA **/
	public static final String ESTATUS_PreDischarge = "P";


    /**
     * Obtenemos la habitacion a la que pertenece la cama.
     * @return
     */
	public MEXMEHabitacion getHabitacion() {
		if (habitacion == null && getEXME_Habitacion_ID() > 0) {
			habitacion = new MEXMEHabitacion(getCtx(), getEXME_Habitacion_ID(), get_TrxName());
		}
		return habitacion;
	}
	
	/**
	 * Returns the bed already assigned to a patients account
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return MEXMECama[]
	 */
	public static MEXMECama[] getFromCtaPac(Properties ctx, int ctaPacID, String trxName) {
		MEXMECama[] retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		try {
			sql.append(" EXME_Cama.EXME_CtaPac_ID=?");
			final Object[] params = new Object[]{Integer.valueOf(ctaPacID)};
			retValue = get(ctx, null, sql.toString(), params, null, trxName);

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		return retValue;

	}

	/**
	 * Returns the bed from value
	 * 
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return MEXMECama
	 */
	public static MEXMECama getFromValue(Properties ctx, String value, String trxName) {
		MEXMECama retValue = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" EXME_Cama.Value=? ");
			final Object[] params = new Object[]{value};
			
			final MEXMECama[] camas = get(ctx, null, sql.toString(), params, null, trxName);
			if(camas != null && camas.length > 0){
				retValue = camas[0];
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		return retValue;

	}
	
	/**
	 * Returns an array of active beds 
	 * 
	 * @param ctx
	 * @param join
	 * @param orderBy
	 * @param trxName
	 * @param value
	 * @return MEXMECama[]
	 */
	public static MEXMECama[] get(Properties ctx, String join, String where, Object[] params, String orderBy, String trxName) {
		MEXMECama[] retValue = null;
		List<MEXMECama> list = new ArrayList<MEXMECama>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			list = new Query(ctx, Table_Name, where, trxName)//
			.setJoins(join == null ? null : new StringBuilder(join))//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(StringUtils.isBlank(orderBy) ? "EXME_Cama.Value" : orderBy)//
			.list();

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		retValue = new MEXMECama[list.size()];
		list.toArray(retValue);

		return retValue;
	}

	/**
	 * Returns a non-census bed
	 */
	public static MEXMECama getRegularBed(MEXMECama[] camas) {
		slog.config("Find regular (censable) bed.");
		MEXMECama cama = null;
		for (int i = 0; i < camas.length; i++) {
			if (camas[i].isCensable()) {
				cama = camas[i];
				break;
			} else {
				continue;
			}
		}
		return cama;
	}

	/**
	 * Returns whether the bed is available or not
	 * 
	 * @param ctx
	 * @param camaID
	 * @param trxName
	 * @return true : Available, false: Occupied
	 */
	public static boolean isBedAvailable(Properties ctx, int camaID, String trxName) {
		boolean retValue;
		if (camaID <= 0 || ctx == null) {
			slog.warning("The Bed is not available. EXME_Cama_ID: " + camaID);
			retValue = false;
		} else {
			retValue = new MEXMECama(ctx, camaID, trxName).isBedAvailable();
		}
		return retValue;
	}

	/**
	 * Returns whether the bed is available or not
	 * 
	 * @return true : Available, false: Occupied
	 */
	public boolean isBedAvailable() {
		log.config("verify bed availability. EXME_Cama_ID: " + getEXME_Cama_ID());
		return  getEXME_Cama_ID() > 0 && 
				getEXME_CtaPac_ID() <= 0 && 
				getEstatus().startsWith(ESTATUS_Available);
	}

	/**
	 * Assigns a bed for a patient account
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param camaID
	 * @param camaAlterna
	 * @param observ
	 * @param trxName
	 */
	public static void assignBed(Properties ctx, int ctaPacID, int camaID, String camaAlterna, String observ,
			boolean saveCtaPac, String trxName) {
		if (camaID <= 0 || ctaPacID <= 0) {
			slog.warning("The EXME_CtaPac_ID or EXME_Cama_ID must be greatter than zero");
		} else {
			new MEXMECama(ctx, camaID, trxName).assignBed(ctaPacID, camaAlterna, observ, saveCtaPac);
		}
	}
	
	/**
	 * Assigns a bed for a patient account
	 * @param ctaPacID
	 * @param camaAlterna
	 * @param observ
	 * @param saveCtaPac
	 */
	public void assignBed(int ctaPacID, String camaAlterna, String observ, boolean saveCtaPac) {
		log.config("Assigning bed. EXME_Cama_ID: " + get_ID() + " for EXME_CtaPac_ID: " + ctaPacID);
		if (ctaPacID <= 0 || is_new() ||
		// is already assigned (after save)
				(ctaPacID > 0 && getEXME_CtaPac_ID() == ctaPacID)) {
			log.info("is already assigned (after save)");
			return;
		}

		// verify bed availability
		if (!isBedAvailable()) {
			throw new MedsysException("error.tableroCamas.noDispo");
		}
		// validate the patient's account
		
		// if patient already has a bed
		if (getRegularBed(getFromCtaPac(getCtx(), ctaPacID, get_TrxName())) != null) {
			throw new MedsysException("error.asigna.cama");
		}
		// assign patient to the bed
		update(ctaPacID,
				getEstatus().endsWith(ESTATUS_Dirty) ? ESTATUS_OccupiedDirty : ESTATUS_OccupiedClean, camaAlterna,
				observ);

		// assign bed to the patient
		if (saveCtaPac) {
			log.config("updating bed/orgTrx to ctapac.");
			final MEXMECtaPac cta = new MEXMECtaPac(getCtx(), ctaPacID, get_TrxName());
			cta.setEXME_Cama_ID(getEXME_Cama_ID());
			cta.setEXME_EstServ_ID(getHabitacion().getEXME_EstServ_ID());
			cta.setAD_OrgTrx_ID(getHabitacion().getEstServ().getAD_OrgTrx_ID());
			if (!cta.save()){
				throw new MedsysException("error.tableroCamas.asignar");
			}
		}
	}
	
	/**
	 * Releases a bed already assign for a patient account
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param camaID
	 * @param trxName
	 */
	public static void releaseBed(Properties ctx, int ctaPacID, int camaID, String trxName) {
		if (camaID > 0) {
			new MEXMECama(ctx, camaID, trxName).releaseBed();
		}
		// remove bed - patient account reference
		if (ctaPacID > 0) {
			slog.config("remove bed - patient account reference. EXME_CtaPac_ID: " + ctaPacID);
			final MEXMECtaPac ctapac = new MEXMECtaPac(ctx, ctaPacID, trxName);
			ctapac.setEXME_Cama_ID(0);
			if (!ctapac.save()) {
				throw new MedsysException("error.noUpdateCtaPac");
			}
		}
	}

	/**
	 * Releases a bed already assign for a discharge/eliminated patient
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param camaID
	 * @param trxName
	 */
	public static void releaseBed(Properties ctx, int ctaPacID, String trxName)  {
		slog.config("releaseBed: "+ctaPacID);
		final MEXMECama[] camas = getFromCtaPac(ctx, ctaPacID, trxName);
		if (camas.length <= 0){
			slog.warning("Bed not found. EXME_CtaPac_ID: "+ctaPacID);
			return;// DO NOTHING ("error.notFound");
		}
		for (int i = 0; i < camas.length; i++) {
			camas[i].releaseBed();
		}
	}

	/**
	 * Updates the information of the bed
	 * 
	 * @param estatus
	 * @param camaAlterna
	 * @param description
	 */
	public void update(int ctapacID, String estatus, String camaAlterna, String description) {
		log.config("Update bed. EXME_Cama_ID: " + get_ID() + " Status: " + estatus + ", EXME_CtaPac_ID: "+ctapacID);
		if (ctapacID > 0) {
			setEXME_CtaPac_ID(ctapacID);
		}
		setCama_Alterna(camaAlterna);
		setDescription(description);
		
		if (estatus != null) {
			if (estatus.endsWith(ESTATUS_Clean)) {
				cleanBed();
			} else if (estatus.startsWith(ESTATUS_Available) && getEXME_CtaPac_ID() > 0) {
				log.warning("Available status, removing ctapac. EXME_Ctapac_ID: " + getEXME_CtaPac_ID()
						+ ", EXME_Cama_ID: " + get_ID());
				setEXME_CtaPac_ID(0);
			} else {
				setEstatus(estatus);
			}
		}
		if (!save()) {
			String error;// = "error.guardar";
			if (is_ValueChanged("EXME_CtaPac_ID")) {
				if (getEXME_CtaPac_ID() > 0) {
					error = "error.tableroCamas.asignar";
				} else {
					error = "error.tableroCamas.liberar";
				}
			} else {
				error = "error.noUpdateCama";
			}
			throw new MedsysException(error);
		}
	}
	
	/**
	 * Reserves an alternative bed for a patient account
	 * 
	 * @param ctx
	 * @param ctaPacID
	 *            - patient account
	 * @param camaID
	 *            - already assigned bed
	 * @param camaAltVal
	 *            - Value from an alternative bed to be assigned
	 * @param camaAltID
	 *            - ID from an alternative bed to be assigned
	 * @param observ
	 * @param trxName
	 */
	public static void reserveBed(Properties ctx, int ctaPacID, int camaID, String camaAltVal, int camaAltID,
			String observ, String trxName)  {
		slog.config("Reserve bed. EXME_Cama_ID: " + camaID + " alternative bed: " + camaAltVal + ", EXME_CtaPac_ID: "
				+ ctaPacID);
		
		// if patient already have an alternative bed
		final MEXMECama[] camas = getFromCtaPac(ctx, ctaPacID, trxName);

		if (camas.length <= 0) {
			throw new MedsysException("error.notFound");
		}
		// validate regular bed
		if (getRegularBed(camas).getCama_Alterna() != null) {
			throw new MedsysException("error.asigna.cama");
		}
		
		MEXMECama camaAlt = null;
		if (camaAltID > 0) {
			camaAlt = new MEXMECama(ctx, camaAltID, trxName);
		} else if (StringUtils.isNotEmpty(camaAltVal)) {
			camaAlt = MEXMECama.getFromValue(ctx, camaAltVal, trxName);
		}

		// validate alternative bed
		if (camaAlt == null || camaAlt.getEXME_Cama_ID() <= 0 || camaAlt.isCensable()
				|| camaAlt.getEXME_CtaPac_ID() > 0) {
			// if the alternative bed has a patient
			throw new MedsysException("error.tableroCamas.buscandoAlterCama");
		}
		// reserve assigned bed
		final MEXMECama cama = new MEXMECama(ctx, camaID, trxName);
		cama.update(-1, ESTATUS_Reserved, camaAlt.getValue(), observ);

		// assign alternative bed
		camaAlt.setEXME_CtaPac_ID(ctaPacID);
		camaAlt.setEstatus(ESTATUS_OccupiedClean);

		if (!camaAlt.save(trxName)) {
			throw new MedsysException("error.tableroCamas.asignar");
		}
	}

	/**
	 * Releases an alternative bed, and changes the status of the original bed
	 * 
	 * @param ctx
	 * @param camaID
	 * @param camaAltID
	 * @param trxName
	 */
	public static void releaseAlternativeBed(Properties ctx, int camaID, int camaAltID, String camaAltVal, String trxName) {
		slog.config("Relese alternative bed. EXME_Cama_ID: " + camaID + " EXME_Cama_Alterna_ID: " + camaAltID
				+ " Alternative Bed: " + camaAltVal);
		MEXMECama camaAlt = null;

		if (camaAltID > 0) {
			camaAlt = new MEXMECama(ctx, camaAltID, trxName);
		} else if (StringUtils.isNotEmpty(camaAltVal)) {
			camaAlt = MEXMECama.getFromValue(ctx, camaAltVal, trxName);
		}

		if (camaAlt != null && camaAlt.getEXME_Cama_ID() > 0) {
			camaAlt.releaseBed();
		} else {
			slog.warning("No alternative bed found.");
		}

		if (camaID > 0) {
			slog.config("Change status of the original bed.");
			final MEXMECama cama = new MEXMECama(ctx, camaID, trxName);
			cama.setCama_Alterna(null);
			cama.setEstatus(ESTATUS_OccupiedClean);
			if (!cama.save(trxName)) {
				throw new MedsysException("error.tableroCamas.liberar");
			}
		}

	}

	/**
	 * Changes the status to "Clean"
	 */
	public void cleanBed() {
		log.config("Cleaning bed. EXME_Cama_ID: " + get_ID());
		if (getEstatus().startsWith(ESTATUS_Available) && getEXME_CtaPac_ID() <= 0) {
			setEstatus(ESTATUS_AvailableClean);
		} else {
			setEstatus(ESTATUS_OccupiedClean);
		}
	}

	/**
	 * Changes the status to Available Dirty (DS) 
	 * and remove patient account reference
	 */
	public void releaseBed()  {
		log.config("Releasing bed. EXME_Cama_ID: " + get_ID());
		setEXME_CtaPac_ID(0);
		setCama_Alterna(null);
		setEstatus(ESTATUS_AvailableDirty);
		// if it's configured, cleans the released bed
		if (getMejoras() != null && getMejoras().isCleanBed()) {
			cleanBed();
		}
		if (!save()) {
			throw new MedsysException("error.tableroCamas.liberar");
		}
	}

	/**
	 * Moves a patient from a bed to another
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param camaID
	 * @param camaAlterna
	 * @param observ
	 * @param trxName
	 */
	public static void changeBed(Properties ctx, int ctaPacID, int camaFromID, int camaToID, String camaAlterna,
			String observ, boolean saveCtaPac, String trxName) {
		slog.config("Relese & Assign bed. EXME_CamaFrom_ID: " + camaFromID + " EXME_CamaTo_ID: " + camaToID
				+ " EXME_CtaPac_ID: " + ctaPacID + " Alternative Bed: " + camaAlterna);
		// releases old bed
		releaseBed(ctx, ctaPacID, camaFromID, trxName);
		// assing new one
		assignBed(ctx, ctaPacID, camaToID, camaAlterna, observ, saveCtaPac, trxName);
	}
	
	/**
	 * Returns all the status of a bed
	 * @param ctx
	 * @return List<ValueNamePair>
	 */
	public List<ValueNamePair> getStatusList() {
		List<ValueNamePair> retValue;
		if (getEXME_CtaPac_ID() > 0) {
			final boolean reservada = MEXMECama.ESTATUS_Reserved.equals(getEstatus());
			// si tiene ctapac, el combo muesra los estatus de ocupado y en cuarentena
			// Si la cama esta reservada, el combo solo muestra esa opcion .-Lama
			retValue = MEXMECama.getOccupiedStatus(getCtx(), true, reservada);
		} else {
			// el combo muestra solo los estatus de diponible y cuarentena
			retValue = MEXMECama.getAvailableStatus(getCtx(), true, false);
		}
		return retValue;
	}

	/**
	 * Returns all the status of a bed
	 * @param ctx
	 * @return List<ValueNamePair>
	 */
	public static List<ValueNamePair> getStatusList(Properties ctx) {
		return MRefList.getList(ctx, ESTATUS_AD_Reference_ID, false, null);
	}

	/**
	 * Returns the status of a bed
	 * @param ctx
	 * @param quarantine
	 * @param reserved
	 * @return ArrayList<ValueNamePair>
	 */
	public static ArrayList<ValueNamePair> getAvailableStatus(Properties ctx, boolean quarantine, boolean reserved) {
		return getListStatus(ctx, ESTATUS_Available, quarantine, reserved);
	}

	/**
	 * Returns the status of a bed
	 * @param ctx
	 * @param quarantine
	 * @param reserved
	 * @return ArrayList<ValueNamePair>
	 */
	public static ArrayList<ValueNamePair> getOccupiedStatus(Properties ctx, boolean quarantine, boolean reserved) {
		return getListStatus(ctx, ESTATUS_Occupied, quarantine, reserved);
	}

	/**
	 * Returns the status of a bed
	 * @param ctx
	 * @param compareStatus
	 * @param quarantine
	 * @param reserved - Add the reserve status
	 * @param isShortV Returns a short version of status
	 * @return ArrayList<ValueNamePair>
	 * FIXME: Revisar metodo.
	 */
	private static ArrayList<ValueNamePair> getListStatus(Properties ctx, String compareStatus, boolean quarantine,
			boolean reserved) {

		final ArrayList<ValueNamePair> retValue = new ArrayList<ValueNamePair>();
		final List<ValueNamePair> listEstatus = getStatusList(ctx);

		for (ValueNamePair estatus : listEstatus) {
			// status to compare
			if (estatus.getValue().startsWith(compareStatus)) {
//				// short version
//				if (isShortV) {
//					final ValueNamePair lvb = new ValueNamePair(compareStatus, Msg.translate(ctx, getEstatusLb(compareStatus)));
//					if (!contains(retValue, lvb)) {
//						retValue.add(lvb);
//					}
//				} else {
					retValue.add(estatus);
//				}
			}
//			// Short version: maintenance
//			else if (isShortV && estatus.getValue().endsWith(ESTATUS_Maintenance)) {
//				final ValueNamePair lvb = new ValueNamePair(ESTATUS_Maintenance, Msg.translate(ctx, getEstatusLb(ESTATUS_Maintenance)));
//				if (!contains(retValue, lvb)) {
//					retValue.add(lvb);
//				}
//			}
			// quarantine / reserved
			else if (quarantine && estatus.getValue().equals(ESTATUS_OnQuarantine) //
					|| reserved && estatus.getValue().equals(ESTATUS_Reserved)) {
				retValue.add(estatus);
			}
		}
		return retValue;
	}

//	/**
//	 * Equals
//	 * @param retValue
//	 * @param elem
//	 * @return
//	 */
//	public static boolean contains(ArrayList<ValueNamePair> retValue, ValueNamePair elem) {
//
//		for (int i = 0; i < retValue.size(); i++) {
//			if (elem.equals(retValue.get(i))  || 
//					elem.getValue().equals(retValue.get(i).getValue())){
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * Returns the system's improvements configuration
	 * @return MEXMEMejoras
	 */
	public MEXMEMejoras getMejoras() {
		if (mejoras == null) {
			mejoras = MEXMEMejoras.get(getCtx());
		}
		return mejoras;
	}

	/**
	 * Sets the real status according the short value.
	 * @param estatus
	 */
	public void setEstatus(String estatus) {

		if (estatus.length() == 1) {
			if (estatus.equals(ESTATUS_Maintenance)) { // when bed's in maintenance
				// if(getEXME_CtaPac_ID()> 0)
				estatus = ESTATUS_OccupiedOnMaintenance; // bed can't be available
				// else
				// status = ESTATUS_AvailableOnMaintenance;
			} else {
				estatus += ESTATUS_Clean;
			}
		}
		super.setEstatus(estatus);
	}
	
//	/**
//	 * Verifies if the status is already added to the list
//	 * @param compareStatus
//	 * @return
//	 */
//	private static String getEstatusLb(String compareStatus) {
//		String estatus = null;
//		if (compareStatus == ESTATUS_Available) {
//			estatus = "ESTATUS_Available";
//		} else if (compareStatus == ESTATUS_Maintenance) {
//			estatus = "ESTATUS_Maintenance";
//		} else if (compareStatus == ESTATUS_Occupied) {
//			estatus = "ESTATUS_Occupied";
//		}
//		return estatus;
//	}
	
    @Override
    protected boolean beforeSave(boolean newRecord) {
    	//mantis #6922. cuando se hace clic en el botón copiar registro 
    	//si hay una cuenta paciente se copia en el nuevo registro
    	if(newRecord){
    		setEXME_CtaPac_ID(0);
    	} else {
			if (getEXME_CtaPac_ID() > 0 && !getEstatus().startsWith(ESTATUS_Occupied)) {
				log.severe("Invalid status for an occupied bed");
				// setEstatus(getEstatus().replace(ESTATUS_Available, ESTATUS_Occupied));
				// return false;//TODO !!
			}
    	}
    	return super.beforeSave(newRecord);
    }
    
    /**
     * Devolvemos una lista con las camas definidas en una habitaci&oacute;n.
     * 
     * @param exmeHabitacionId
     *            La habitaci&oacute;n que estamos consultando
     * @param estatus 
     *		  Lista de estatus para la sentencia IN sin parentesis. p.e: 'SD','SO'
     * @return lista Un ArrayList con las camas de la habitaci&oacute;n
     */
	public static List<MEXMECama> getCamasHabitacion(Properties ctx, 
			int exmeHabitacionId, String estatus, 
			String trxName, String whereClause,
			Object... params) {

		final List<MEXMECama> lista = new ArrayList<MEXMECama>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

        sql.append("SELECT DISTINCT c.*, ")
		.append(" cp.DocumentNo as ctapac, tp.name as tipoPac, ")
		.append(" p.Nombre_Pac AS NombrePac, NVL(e.documentNo,'-') as mrn ")
		.append(" FROM EXME_Cama c ")
		.append(" LEFT JOIN EXME_CtaPac cp ON (c.exme_ctapac_id = cp.exme_ctapac_id AND cp.IsActive = 'Y') ")
		.append(" LEFT JOIN EXME_Paciente p ON (cp.EXME_Paciente_ID = p.EXME_Paciente_ID AND p.isActive = 'Y') ")
    	.append(" LEFT JOIN EXME_Hist_Exp e ON (p.exme_paciente_id = e.exme_paciente_id AND e.isActive = 'Y' ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Hist_Exp", "e"))//Lama: un Mrn activo X organizacion
    	.append(") ")
    	.append(" LEFT JOIN EXME_TipoPaciente tp ON (cp.EXME_TipoPaciente_ID = tp.EXME_TipoPaciente_ID AND tp.isActive = 'Y') ")
		.append(" WHERE c.EXME_Habitacion_ID = ? AND c.IsActive = 'Y' ");
	    
	    if(StringUtils.isNotEmpty(estatus)) {
		    sql.append(" AND c.Estatus IN (").append(estatus).append(") ");
	    }
	    if(StringUtils.isNotEmpty(whereClause)) {
		    sql.append(whereClause);
	    }
	    // ACCESS LEVEL
	    sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "c"));
	    sql.append(" ORDER BY c.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			pstmt.setInt(index++, exmeHabitacionId);
			if (params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMECama cama = new MEXMECama(ctx, rs, trxName);
				cama.setCtaPacDocNo(rs.getString("ctapac"));
				cama.setNombrePac(SecureEngine.decrypt(rs.getString("NombrePac")));
				cama.setExpediente(rs.getString("mrn"));
				cama.setTipoPac(rs.getString("tipoPac"));
				lista.add(cama);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCamasHabitacion (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
       return lista;
       
    }
	
	private String nombrePac = null;
	private String ctaPacDocNo = null;
	private String expediente = null;//gderreza
	private String tipoPac = null;

	private MEXMECtaPac ctapac = null;
	public MEXMECtaPac getCtaPac() {
		if(ctapac == null && getEXME_CtaPac_ID() > 0){
			ctapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}		
		return ctapac;
	}
	
	public String getNombrePac() {
		if (nombrePac == null && getCtaPac() != null && getCtaPac().getPaciente() != null) {
			nombrePac = getCtaPac().getPaciente().getNombre_Pac();
		}
		return nombrePac;
	}

	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}

	public String getCtaPacDocNo() {
		if (ctaPacDocNo == null && getCtaPac() != null ) {
			ctaPacDocNo = getCtaPac().getDocumentNo();
		}
		return ctaPacDocNo;
	}

	public void setCtaPacDocNo(String ctaPacDocNo) {
		this.ctaPacDocNo = ctaPacDocNo;
	}

	public String getExpediente() {
		if (expediente == null && getCtaPac() != null && getCtaPac().getPaciente() != null) {
			expediente = getCtaPac().getPaciente().getExpediente();
		}
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getTipoPac() {
		return tipoPac;
	}

	public void setTipoPac(String tipoPac) {
		this.tipoPac = tipoPac;
	}

	/**
	 * Obtiene las camas disponibles limpias 
	 * @param ctx
	 * @param roomId - filtra la camas de una habitacion en especifico (opcional)
	 * @param includeBedId - agrega en el listado la cama (opcional)
	 * @param censable - filtra las censables o no censables (opcional)
	 * @return
	 */
	public static List<KeyNamePair> getAvailableBeds(Properties ctx, int roomId, int includeBedId, Boolean censable){
		
		final StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		sql.append("SELECT EXME_Cama_ID, Name ");
		sql.append("FROM EXME_Cama ");
		sql.append("WHERE isActive='Y' ");
		if(roomId > 0) {
			params.add(roomId);
			sql.append(" AND EXME_Habitacion_ID=? ");
		}
		params.add(ESTATUS_AvailableClean);
		if(includeBedId > 0) {
			sql.append(" AND (Estatus IN (?) OR EXME_Cama_ID=?) ");	
			params.add(includeBedId);
		} else {
			sql.append(" AND Estatus IN (?) ");
		}
		if(censable != null) {
			params.add(DB.TO_STRING(censable));
			sql.append(" AND IsCensable=? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Name ");
		
		return Query.getListKN(sql.toString(), null, params.toArray());
	}
}
