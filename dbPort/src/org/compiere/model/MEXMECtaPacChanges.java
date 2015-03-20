/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;

/**
 * Admitting changes
 * 
 * @author Lorena Lama
 * 
 */
public class MEXMECtaPacChanges extends X_EXME_CtaPacChanges implements Worklist {

	/**
	 * Object with the required parameters for the Log
	 * <ul>
	 * <li>Encounter record (EXME_CtaPac) <b>Mandatory</b></li>
	 * <li>Ordering physician <i>(not necessary for Admission / Abstracting)</i></li>
	 * <li>Order type: TO, WO, VO</li>
	 * <li>Read back, indicates whether the order was read back to the prescriber, <i>(does not apply for WO orders)</i></li>
	 * </ul>
	 * 
	 * @author Lorena Lama
	 */
	public static class CtaPacChanges {
		private transient int				medicoId;
		private transient String			orderType;
		private transient Boolean			readback;
		private final transient MEXMECtaPac	ctaPac;

		public CtaPacChanges(final MEXMECtaPac ctapac) {
			if (ctapac == null) {
				throw new IllegalArgumentException();
			}
			this.ctaPac = ctapac;
		}

		public MEXMECtaPacChanges getFrom(final String trxName) {
			final MEXMECtaPacChanges obj = new MEXMECtaPacChanges(ctaPac.getCtx(), 0, trxName);
			obj.setCtapac(ctaPac);
			obj.setEXME_Medico_ID(medicoId);
			obj.setEXME_Enfermeria_ID(Env.getEXME_Enfermeria_ID(ctaPac.getCtx()));
			obj.setOrderType(StringUtils.trimToNull(orderType));
			if (readback != null) {
				obj.setReadBack(readback);
			}
			return obj;
		}

		public boolean isAdmitInfoChanged() {
			return ctaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_Area_ID) //
				|| ctaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_DateOrdered) //
				|| ctaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_TipoPaciente_ID) //
				|| ctaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_ResStatus) //
			;
		}
		/**
		 * Create the log records for Admit date/Service/Patient type/Res status changes
		 * 
		 * @param trxName transaction name
		 * @return
		 */
		public boolean saveEncounterLog(final String trxName) {
			return saveEncounterLog(false, trxName);
		}

		/**
		 * Create the log records for Admit date/Service/Patient type/Res status changes
		 * 
		 * @param isNew force saveRecord
		 * @param trxName transaction name
		 * @return
		 */
		public boolean saveEncounterLog(final boolean isNew, final String trxName) {
			boolean saved;
			if (isNew || Env.getEXME_Medico_ID(ctaPac.getCtx()) > 0) { // RQM #4569
				final MEXMECtaPacChanges rec = getFrom(trxName);
				saved = rec.saveAdmitDateChange(isNew) // DateOrdered
					&& rec.saveTipoPacChange(isNew) // EXME_TipoPac_ID
					&& rec.saveAreaChange(isNew) // EXME_Area_ID
					&& rec.saveResStatusChange(isNew) // EXME_ConsentimientoPac_ID
				;
			} else if (isAdmitInfoChanged()) {
				saved = getFrom(trxName).saveAdmitDateChange(isNew) // DateOrdered
					&& getFrom(trxName).saveTipoPacChange(isNew) // EXME_TipoPac_ID
					&& getFrom(trxName).saveAreaChange(isNew) // EXME_Area_ID
					&& getFrom(trxName).saveResStatusChange(isNew) // EXME_ConsentimientoPac_ID
				;
			} else {
				saved = true;
			}
			return saved;
		}

		public void setMedicoId(final int medicoId) {
			this.medicoId = medicoId;
		}

		public void setOrderType(final String orderType) {
			this.orderType = orderType;
		}

		public void setReadback(final Boolean readback) {
			this.readback = readback;
		}
	}

	/** @return Current service */
	private transient MEXMETipoPaciente	tipoPacAct;

	/** @return Previous Patient type */
	private transient MEXMETipoPaciente	tipoPacAnt;

	/** @return Current Service */
	private transient MEXMEArea			areaAct;

	/** @return Previous service */
	private transient MEXMEArea			areaAnt;

	/** @return Encounter */
	private transient MEXMECtaPac		ctapac;

	private static final long			serialVersionUID	= 1L;
	/** log de la clase */
	private static CLogger				slog				= CLogger.getCLogger(MEXMECtaPacChanges.class);

	/**
	 * @param ctx Context
	 * @param where SQL condition
	 * @param orderBy SQL order by
	 * @param trxName transaction name
	 * @param params parameters
	 * @return list of admitting changes
	 */
	public static List<MEXMECtaPacChanges> getList(final Properties ctx, final String where, final String orderBy, final String trxName,
		final Object... params) {
		return new Query(ctx, Table_Name, where, trxName)//
			.setParameters(params)//
			.setOrderBy(orderBy)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}

	/**
	 * @param ctx Context
	 * @param ctaPac Encounter Id
	 * @param trxName transaction name
	 * @return list of admitting changes by encounter
	 */
	public static List<MEXMECtaPacChanges> getListByCtaPac(final Properties ctx, final int ctaPac, final String trxName) {
		return getListByCtaPac(ctx, ctaPac, trxName, false);
	}
	
	/**
	 * @param ctx Context
	 * @param ctaPac Encounter Id
	 * @param trxName transaction name
	 * @param derechoHabiente
	 * @return list of admitting changes by encounter
	 */
	public static List<MEXMECtaPacChanges> getListByCtaPac(final Properties ctx, final int ctaPac, final String trxName, boolean derechoHabiente) {
		slog.fine("MEXMECtaPacChanges#getListByCtaPac ctaPac: " + ctaPac);
		//Card #1545 ProMujer 
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_CtaPac_ID=? ");
		if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		
		
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
		.setParameters(ctaPac)//
		.setOrderBy(" EXME_CtaPacChanges.Created DESC ")//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
		.list();
	}

	/**
	 * Obtiene los cambios que se han hecho a la cuenta paciente
	 * en los campos Patient Type, Service, Resuscitative Status
	 * y que requieren aprobación del médico seleccionado
	 * 
	 * @param ctx contexto
	 * @param exmeMedicoId medico seleccionado
	 * @return List<MEXMECtaPacChanges>
	 */
	public static List<MEXMECtaPacChanges> getListByDoctor(final Properties ctx, final int exmeMedicoId) {
		slog.fine("MEXMECtaPacChanges#getListByDoctor exmeMedicoId: " + exmeMedicoId);
		return getList(ctx, " EXME_Medico_ID=? AND (Estatus=? OR Authenticated_Date IS NULL)", " EXME_CtaPacChanges.Created DESC ", null,
			exmeMedicoId, ESTATUS_VerbalOrder);
	}

	/**
	 * Obtiene el ultimo admitdate para un tipo de paciente
	 * 
	 * @param ctx
	 * @param exmeCtaPacId Id de cuenta paciente
	 * @param patientTypeID ID de tipó de paciente
	 * @return
	 */
	public static Timestamp getPrevAdmitDate(final Properties ctx, final int exmeCtaPacId, final int patientTypeID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacChanges.AdmitDateAct FROM EXME_CtaPacChanges ");
		sql.append(" WHERE EXME_CtaPacChanges.EXME_CtaPac_ID=? ");
		sql.append(" AND EXME_CtaPacChanges.EXME_TipoPacAct_ID=? ");
		sql.append(" AND EXME_CtaPacChanges.AdmitDateAct IS NOT NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_CtaPacChanges.Created DESC ");
		return DB.getSQLValueTS(null, sql.toString(), exmeCtaPacId, patientTypeID);
	}

	// /**
	// *
	// * @param ctx
	// * @param mCtaPac
	// * @param medicoId
	// * @param enfermeraId
	// * @param isNew
	// * @param trxName
	// * @return
	// * @deprecated
	// */
	// public static boolean saveEncounterLog(final Properties ctx, final MEXMECtaPac mCtaPac, final int medicoId, final int enfermeraId,
	// final boolean isNew, final String trxName) {
	//
	// boolean success = true;
	// final MEXMECtaPacChanges encLog = new MEXMECtaPacChanges(ctx, 0, trxName);
	// encLog.setEXME_CtaPac_ID(mCtaPac.getEXME_CtaPac_ID());
	// encLog.setEXME_Medico_ID(medicoId);
	// encLog.setEXME_Enfermeria_ID(enfermeraId);
	// // if is new or ( patient type or date ordered ) have changed
	// if (isNew //
	// || mCtaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_TipoPaciente_ID) //
	// || mCtaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_DateOrdered)) {// Lama
	// encLog.setEXME_TipoPacAnt_ID(isNew ? 0 : mCtaPac.get_ValueOldAsInt(I_EXME_CtaPac.COLUMNNAME_EXME_TipoPaciente_ID));
	// encLog.setEXME_TipoPacAct_ID(mCtaPac.getEXME_TipoPaciente_ID());
	// if (!isNew && mCtaPac.get_ValueOld(I_EXME_CtaPac.COLUMNNAME_DateOrdered) != null) {
	// encLog.setAdmitDateAnt((Timestamp) mCtaPac.get_ValueOld(I_EXME_CtaPac.COLUMNNAME_DateOrdered));
	// }
	// encLog.setAdmitDateAct(mCtaPac.getDateOrdered());
	// encLog.setEstatus(enfermeraId > 0 ? ESTATUS_VerbalOrder : null);
	// }
	// if (mCtaPac.is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_EstServ_ID)) {
	// final MEXMEEstServ prevEst = new MEXMEEstServ(ctx, mCtaPac.get_ValueOldAsInt(I_EXME_CtaPac.COLUMNNAME_EXME_EstServ_ID), null);
	// final MEXMEEstServ currEst = new MEXMEEstServ(ctx, mCtaPac.getEXME_EstServ_ID(), null);
	//
	// if (prevEst.getEXME_Area_ID() != currEst.getEXME_Area_ID()) {
	// encLog.setEXME_AreaAnt_ID(prevEst.getEXME_Area_ID());
	// encLog.setEXME_AreaAct_ID(currEst.getEXME_Area_ID());
	// encLog.setEstatus(ESTATUS_VerbalOrder);
	// }
	// }
	// // Ultimo
	// final String resstatus = mCtaPac.getResStatusOld();
	// if (resstatus != mCtaPac.getResStatus()) {
	// encLog.setResStatusAnt(resstatus);
	// encLog.setResStatusAct(mCtaPac.getResStatus());
	// encLog.setEstatus(ESTATUS_VerbalOrder);
	// }
	// if (StringUtils.isNotEmpty(encLog.getEstatus())) {
	// success = encLog.save();
	// }
	//
	// return success;
	// }

	/**
	 * @param ctx
	 * @param EXME_CtaPacChanges_ID
	 * @param trxName
	 */
	public MEXMECtaPacChanges(final Properties ctx, final int EXME_CtaPacChanges_ID, final String trxName) {
		super(ctx, EXME_CtaPacChanges_ID, trxName);
		if (is_new()) {
			setAuthenticatedBy(0);
			setAuthenticated_Date(null);
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPacChanges(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
		if (is_new()) {
			setAuthenticatedBy(0);
			setAuthenticated_Date(null);
		}
	}

	/**
	 * Validates the record hasn't been previously authenticated and
	 * whether the order is WO or the user is a physician to authenticate the order
	 */
	public void authenticate() {
		// si no esta autenticado, y el usuario es medico, o la orden esta por escrito
		if (getAuthenticated_Date() == null && (Env.getEXME_Medico_ID(getCtx()) > 0 || ORDERTYPE_WrittenOrder.equals(getOrderType()))) {
			setAuthenticated_Date(Env.getCurrentDate());
			setAuthenticatedBy(MEXMEMedico.getUserId(getCtx(), getEXME_Medico_ID(), true));
			if (!save()) {
				throw new MedsysException();
			}
		}
	}

	/** @return Current service */
	public MEXMEArea getAreaAct() {
		if (areaAct == null) {
			areaAct = new MEXMEArea(getCtx(), getEXME_AreaAct_ID(), get_TrxName());
		}
		return areaAct;
	}

	/** @return Previous service */
	public MEXMEArea getAreaAnt() {
		if (areaAnt == null) {
			areaAnt = new MEXMEArea(getCtx(), getEXME_AreaAnt_ID(), get_TrxName());
		}
		return areaAnt;
	}
	
	/** @return Current service */
	public String getAreaActStr() {
		return getAreaAct() == null ? "" : getAreaAct().getName();
	}

	/** @return Previous service */
	public String getAreaAntStr() {
		return getAreaAnt() == null ? "" : getAreaAnt().getName();
	}

	/** @return Encounter */
	public MEXMECtaPac getCtaPac() {
		if (ctapac == null) {
			ctapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctapac;
	}

	/** @return Patient name */
	@Override
	public String getPatientName() {
		return getEXME_CtaPac_ID() > 0 ? getCtaPac().getNombre_Pac() : "";
	}

	/** @return Record type */
	@Override
	public String getRecordType() {
		return Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_EXME_CtaPac_ID);
	}

	/** @return Current res status name */
	public String getResStatusActStr() {
		return MRefList.getListName(getCtx(), RESSTATUSACT_AD_Reference_ID, super.getResStatusAct());
	}

	/** @return Previous res status name */
	public String getResStatusAntStr() {
		return MRefList.getListName(getCtx(), RESSTATUSANT_AD_Reference_ID, super.getResStatusAnt());
	}

	/** @return Record detail */
	@Override
	public String getSummaryDetail() {
		final StringBuilder summary = new StringBuilder();
		//
		final String msgFrom = Utilerias.getMsg(getCtx(), "agendar.odontologia.De");
		final String msgTo = Utilerias.getMsg(getCtx(), "agendar.odontologia.A");
		final Object obj = null;
		// Patient type
		if (Math.max(getEXME_TipoPacAnt_ID(), getEXME_TipoPacAct_ID()) > 0 && getEXME_TipoPacAnt_ID() != getEXME_TipoPacAct_ID()) {
			appendFromTo(summary, I_EXME_TipoPaciente.COLUMNNAME_EXME_TipoPaciente_ID, //
				msgFrom, getEXME_TipoPacAnt_ID() > 0 ? getTipoPacAnt().getName() : obj,// From
				msgTo, getEXME_TipoPacAct_ID() > 0 ? getTipoPacAct().getName() : obj// To
			);
		}
		// Service
		if (Math.max(getEXME_AreaAnt_ID(), getEXME_AreaAct_ID()) > 0 && getEXME_AreaAnt_ID() != getEXME_AreaAct_ID()) {
			appendFromTo(summary, I_EXME_Area.COLUMNNAME_EXME_Area_ID, //
				msgFrom, getEXME_AreaAnt_ID() > 0 ? getAreaAnt().getName() : obj, // From
				msgTo, getEXME_AreaAct_ID() > 0 ? getAreaAct().getName() : obj // To
			);
		}
		// Admit Date
		if (!TimeUtil.equals(getAdmitDateAnt(), getAdmitDateAct())) {
			appendFromTo(summary, "AdmitDate", //
				msgFrom, getAdmitDateAnt() == null ? getAdmitDateAnt() : Constantes.getSDFDateTime(getCtx()).formatConvert(getAdmitDateAnt()), // From
				msgTo, getAdmitDateAct() == null ? getAdmitDateAct() : Constantes.getSDFDateTime(getCtx()).formatConvert(getAdmitDateAct()) // To
			);
		}
		// Advance directives
		if (!StringUtils.equals(getResStatusAct(), getResStatusAnt())) {
			appendFromTo(summary, I_EXME_CtaPac.COLUMNNAME_ResStatus,//
				msgFrom, getResStatusAntStr(),// From
				msgTo, getResStatusActStr() // To
			);
		}
		return summary.toString();
	}

	/**
	 * @param summary Stringbuilder
	 * @param columnName columname
	 * @param msgFrom
	 * @param objFrom
	 * @param msgTo
	 * @param objTo
	 */
	private void appendFromTo(final StringBuilder summary, final String columnName, final String msgFrom, final Object objFrom, final String msgTo,
		final Object objTo) {
		if (summary.length() > 0) {
			summary.append("<br>");
		}
		summary.append("<b>").append(Msg.translate(getCtx(), columnName)).append("</b><br>");
		if (objFrom != null) {
			summary.append(msgFrom).append(" ").append(objFrom).append(" ");
		}
		if (objTo != null) {
			summary.append(msgTo).append(" ").append(objTo);
		}
	}

	/** @return Current patient type */
	public MEXMETipoPaciente getTipoPacAct() {
		if (tipoPacAct == null) {
			tipoPacAct = new MEXMETipoPaciente(getCtx(), getEXME_TipoPacAct_ID(), get_TrxName());
		}
		return tipoPacAct;
	}

	/** @return Previous Patient type */
	public MEXMETipoPaciente getTipoPacAnt() {
		if (tipoPacAnt == null) {
			tipoPacAnt = new MEXMETipoPaciente(getCtx(), getEXME_TipoPacAnt_ID(), get_TrxName());
		}
		return tipoPacAnt;
	}
	
	/** @return Current patient type */
	public String getTipoPacActStr() {
		return getTipoPacAct() == null ? "" : getTipoPacAct().getName();
	}

	/** @return Previous Patient type */
	public String getTipoPacAntStr() {
		return getTipoPacAnt() == null ? "" : getTipoPacAnt().getName();
	}

	/** @return User name */
	public String getUserName() {
		return getEXME_Enfermeria_ID() > 0 ? getEXME_Enfermeria().getNombre_Enf() : MUser.getUserName(getCtx(), getCreatedBy());
	}

	/** Save admit date changes */
	private boolean saveAdmitDateChange(final boolean isNew) {
		if (isNew || getCtaPac().is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_DateOrdered)) {
			if (!isNew) {
				final Object oldDate = getCtaPac().get_ValueOld(I_EXME_CtaPac.COLUMNNAME_DateOrdered);
				if (oldDate instanceof Timestamp) {
					setAdmitDateAnt((Timestamp) oldDate);
				}
			}
			setAdmitDateAct(getCtaPac().getDateOrdered());
			setEXME_TipoPacAct_ID(getCtaPac().getEXME_TipoPaciente_ID());
			saveLog(!isNew);
		}
		return true;
	}

	/** Save service changes */
	private boolean saveAreaChange(final boolean isNew) {
		if (isNew || getCtaPac().is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_Area_ID)) {
			if (!isNew) {
				final int oldArea = getCtaPac().get_ValueOldAsInt(I_EXME_CtaPac.COLUMNNAME_EXME_Area_ID);
				if (oldArea > 0) {
					setEXME_AreaAnt_ID(oldArea);
				}
			}
			setEXME_AreaAct_ID(getCtaPac().getEXME_Area_ID());

			if (isNew || getEXME_AreaAnt_ID() <= 0) {
				saveLog(false);
			} else {
				final MEXMEAreaDestino tpd = MEXMEAreaDestino.getRecord(getCtx(), getEXME_AreaAnt_ID(), getEXME_AreaAct_ID());
				saveLog(tpd == null || tpd.isRequireAuthentication());
			}
		}
		return true;
	}

	/**
	 * Save encounter change record
	 * 
	 * @param reqAuthen <br>
	 *            <b>true:</b> Validates whether the order is WO or the user is a physician to auto-authenticate the order <br>
	 *            <b>false:</b> changes the status to 'BL'
	 */
	private void saveLog(final boolean reqAuthen) {
		if (reqAuthen) {
			if (Env.getEXME_Medico_ID(getCtx()) == getEXME_Medico_ID() || ORDERTYPE_WrittenOrder.equals(getOrderType())) {
				authenticate();
			}
			if (!save()) {
				throw new MedsysException();
			}
		} else {
			setEstatus(ESTATUS_ByLIP);// no require autenticacion
			if (!save()) {
				throw new MedsysException();
			}
		}
	}

	/** Save res status record */
	private boolean saveResStatusChange(final boolean isNew) {
		if ((isNew || !StringUtils.equals(getCtaPac().getResStatusOld(), getCtaPac().getResStatus()))) {
			// no guardar si ambos son blancos
			if (StringUtils.isNotBlank(getCtaPac().getResStatusOld()) || StringUtils.isNotBlank(getCtaPac().getResStatus())) {
				setResStatusAnt(getCtaPac().getResStatusOld());
				setResStatusAct(getCtaPac().getResStatus());
				saveLog(!isNew);
			}
		}
		return true;
	}

	/** Save patient type change record */
	private boolean saveTipoPacChange(final boolean isNew) {
		if (isNew || getCtaPac().is_ValueChanged(I_EXME_CtaPac.COLUMNNAME_EXME_TipoPaciente_ID)) {
			if (!isNew) {
				setEXME_TipoPacAnt_ID(getCtaPac().get_ValueOldAsInt(I_EXME_CtaPac.COLUMNNAME_EXME_TipoPaciente_ID));
			}
			setEXME_TipoPacAct_ID(getCtaPac().getEXME_TipoPaciente_ID());
			setAdmitDateAct(getCtaPac().getDateOrdered());// requerido para tipo de paciente, guardar la fecha
			if (isNew || getEXME_TipoPacAnt_ID() <= 0) {
				saveLog(false);
			} else {
				final MEXMETipoPacDestino tpd = MEXMETipoPacDestino.getRecord(getCtx(), getEXME_TipoPacAnt_ID(), getEXME_TipoPacAct_ID());
				saveLog(tpd == null || tpd.isRequireAuthentication());
			}
		}
		return true;
	}

	@Override
	public boolean setAction(final String action) {
		if (ACTION_AUTHENTICATE.equals(action)) {
			authenticate();
		}
		return true;
	}

	public void setCtapac(final MEXMECtaPac ctapac) {
		this.ctapac = ctapac;
		setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
	}
	
	/** @return Read back label: Yes / No */
	public String getReadBackStr() {
		return MEXMECuidadosPac.getReadBackLabel(this);
	}
	
	/** @return Order Type label  */
	public String getOrderTypeStr() {
		return MRefList.getListName(getCtx(), ORDERTYPE_AD_Reference_ID, COLUMNNAME_OrderType);
	}
	
	/**
	 * @return 	AuthenticatedBy
	 */
	public String getUserAuthenticated(){
		return getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), getAuthenticatedBy());
	}
	
	/** @return Physician Name */
	public String getNombreMed() {
		if (getEXME_Medico_ID() <= 0) {
			return "";
		}
		return getEXME_Medico().getNombre_Med();
	}
	
	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_Authenticated_Date.hashCode()) {
			comparable = getAuthenticated_Date();
		} else if (type == COLUMNNAME_AuthenticatedBy.hashCode()) {
			comparable = getUserAuthenticated();
		} else if (type == COLUMNNAME_AdmitDateAct.hashCode()) {
			comparable = getAdmitDateAct();
		} else if (type == COLUMNNAME_AdmitDateAnt.hashCode()) {
			comparable = getAdmitDateAnt();
		} else if (type == COLUMNNAME_DepartureDate.hashCode()) {
			comparable = getDepartureDate();
		} else if (type == COLUMNNAME_Estatus.hashCode()) {
			comparable = getEstatus();
		} else if (type == COLUMNNAME_EXME_AreaAct_ID.hashCode()) {
			comparable = getAreaActStr();
		} else if (type == COLUMNNAME_EXME_AreaAnt_ID.hashCode()) {
			comparable = getAreaAntStr();
		} else if (type == COLUMNNAME_EXME_TipoPacAct_ID.hashCode()) {
			comparable = getTipoPacActStr();
		} else if (type == COLUMNNAME_EXME_TipoPacAnt_ID.hashCode()) {
			comparable = getTipoPacAntStr();
		} else if (type == COLUMNNAME_ResStatusAct.hashCode()) {
			comparable = getResStatusActStr();
		} else if (type == COLUMNNAME_ResStatusAnt.hashCode()) {
			comparable = getResStatusAntStr();
		} else if (type == COLUMNNAME_OrderType.hashCode()) {
			comparable = getOrderTypeStr();
		} else if (type == COLUMNNAME_ReadBack.hashCode()) {
			comparable = getReadBackStr();
		} else if(type == COLUMNNAME_EXME_Medico_ID.hashCode()){
			comparable = getNombreMed();
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}

}
