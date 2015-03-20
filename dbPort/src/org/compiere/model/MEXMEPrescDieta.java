package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.model.MEXMEDiscontinueOrder.IDiscontinueOrder;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class MEXMEPrescDieta extends X_EXME_PrescDieta implements Worklist, IDiscontinueOrder {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7828597418395673971L;
	@SuppressWarnings("unused")
	private static CLogger		log					= CLogger.getCLogger(MEXMEPrescDieta.class);

	public MEXMEPrescDieta(Properties ctx, int EXME_PrescDieta_ID, String trxName) {
		super(ctx, EXME_PrescDieta_ID, trxName);
	}

	public MEXMEPrescDieta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa la dieta prescrita de una cuenta paciente dependiendo de la estaci�n de servicio
	 * NOTA!! DESCONTINUA LA PRESCRIPCION DE DIETAS EXISTENTE SI ES MENOR A LA FECHA ACTUAL
	 * @param ctx
	 * @param ctapacID
	 * @param estacionID
	 * @param trxName
	 * @return MEXMEPrescDieta
	 */
	public static MEXMEPrescDieta getActual(Properties ctx, int ctapacID, String trxName) {
		MEXMEPrescDieta retValue = get(ctx, ctapacID, true, -1, trxName);
		// validar, si tiene una hora especifica para ser descontinuada
		if (retValue != null && retValue.getFechaFin() != null) {
			if (retValue.getFechaFin().getTime() <= System.currentTimeMillis()) {
				retValue.discontinue(trxName);
				retValue = getActual(ctx, ctapacID, trxName);
			}
		}
		return retValue;
	}

	/**
	 * Returns the ordered diet for the patient encounter
	 * 
	 * @param ctx
	 * @param ctapacID
	 * @param valid If not null filters by valid diet order
	 * @param encounterFormId if greather than zero filters by the encounter form
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrescDieta get(final Properties ctx, final int ctapacID, final Boolean valid, final int encounterFormId, final String trxName) {
		return getQuery(ctx, ctapacID, valid, encounterFormId, trxName).first();
	}

	/**
	 * Arma el objeto Query
	 * @param ctx
	 * @param ctapacID
	 * @param valid
	 * @param encounterFormId
	 * @param trxName
	 * @return Query
	 */
	public static Query getQuery(final Properties ctx,//
			final int ctapacID,//
			final Boolean valid, //
			final int encounterFormId, //
			final String trxName) {
		return getQuery(ctx, ctapacID, valid, encounterFormId, trxName, false);
	}
	
	/**
	 * Arma el objeto Query
	 * @param ctx
	 * @param ctapacID
	 * @param valid
	 * @param encounterFormId
	 * @param trxName
	 * @param derechoHabiente
	 * @return Query
	 */
	public static Query getQuery(final Properties ctx,//
			final int ctapacID,//
			final Boolean valid, //
			final int encounterFormId, //
			final String trxName, //
			boolean derechoHabiente) {
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		sql.append(" EXME_CTAPAC_ID=? ");
		params.add(ctapacID);
		
		if (valid != null) {
			sql.append(" AND VIGENTE=? ");
			params.add(DB.TO_STRING(valid));
		}
		if (encounterFormId > 0) {
			sql.append(" AND EXME_EncounterForms_ID=? ");
			params.add(encounterFormId);
		}
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
				.setOrderBy("FechaFin,FechaInicio");
	}

	private List<MEXMEPrescDietaDet>	detail;

	public List<MEXMEPrescDietaDet> getDetail(boolean requery) {
		if (detail == null || requery) {
			detail = MEXMEPrescDietaDet.get(getCtx(), get_ID(), get_TrxName());
		}
		return detail;
	}

	public List<MEXMEPrescDietaDet> getDetail() {
		return getDetail(false);
	}

	/**
	 * Regresa la historia de dietas de una cuenta paciente.
	 * 
	 * @param ctx
	 * @param ctapacID
	 * @param encounterFormId
	 * @param trxName
	 * @return List<MEXMEPrescDieta>
	 */
	public static List<MEXMEPrescDieta> getHistory(final Properties ctx, final int ctapacID, final int encounterFormId, final String trxName) {
		return getQuery(ctx, ctapacID, false, encounterFormId, trxName).list();
	}

	public MEXMEPrescDietaDet newPrescDietaDet(int dietId, int sequence) {
		final MEXMEPrescDietaDet det = new MEXMEPrescDietaDet(getCtx(), 0, get_TrxName());
		det.setEXME_Dieta_ID(dietId);
		det.setEXME_PrescDieta_ID(get_ID());
		det.setSequence(new BigDecimal(sequence));// FIXME: cambiar
		if (!det.save()) {
			throw new MedsysException(Utilerias.getMsg(getCtx(), "error.dietas.noSave"));
		}
		return det;
	}

	/**
	 * Discontinues a prescription (EXME_PrescDiet.Vigente='N')
	 * 
	 * @param trxName
	 */
	public void discontinue(String trxName) {
		discontinue(trxName, null);
	}
	
	/**
	 * Discontinues a prescription (EXME_PrescDiet.Vigente='N')
	 *
	 * @see MEXMEDiscontinueOrder
	 */
	@Override
	public void discontinue(final String trxName, final DiscontinueParams params) {
		if (isVigente()) {
			if(params == null){
				setDiscontinuedDate(Env.getCurrentDate());
				setFechaFin(Env.getCurrentDate());
			} else {
				// Card #746 - Authentication of Discontinuation Orders
				setDiscontinuedDate(params.getDate());
				setMotivoCancel(params.getComments());
				setFechaFin(params.getDate());
			}
			setCanceledBy(Env.getAD_User_ID(getCtx()));
			setVigente(false);
			if (!save(trxName)) {
				throw new MedsysException();
			}
		}
	}

	/**
	 * Authenticates a prescription
	 * 
	 * @param trxName
	 */
	public void authenticate(String trxName) {
		// si no esta autenticado, y el usuario es medico, o la orden esta por escrito
		if (isVigente()) {
			if (!isAuthenticated() && (Env.getEXME_Medico_ID(getCtx()) > 0 || ORDERTYPE_WrittenOrder.equals(getOrderType()))) {
				setAuthenticated(true);
				setAuthenticated_Date(Env.getCurrentDate());
				setAuthenticatedBy(MEXMEMedico.getUserId(getCtx(), getEXME_Medico_ID(), true));
				if (!save(trxName)) {
					throw new MedsysException();
				}
			}
		} else if (Env.getEXME_Medico_ID(getCtx()) > 0) {
			// Card #746 - Authentication of Discontinuation Orders
			MEXMEDiscontinueOrder.authenticate(this, get_TrxName());
		}
	}

	/**
	 * Discontinues a prescription (EXME_PrescDiet.Vigente='N')
	 * 
	 * @param trxName
	 */
	public void note(String trxName) {
		if (getNotedDate() == null && Env.getEXME_Enfermeria_ID(getCtx()) > 0) {
			setNotedBy(Env.getAD_User_ID(getCtx()));
			setNotedDate(Env.getCurrentDate());
			if (!save(trxName)) {
				throw new MedsysException();
			}
		}
	}

	/**
	 * Returns all diets included in the prescription
	 * 
	 * @param includeMain - Incluides the main diet (sequence=0)
	 * @param asHTML returns the string with HTML tags
	 * @return
	 */
	public String getDietsStr(boolean includeMain, boolean asHTML) {
		final StringBuilder dietStr = new StringBuilder();
		final List<MEXMEPrescDietaDet> detail = getDetail();
		// Main Diet. Seq. #1
		if (!detail.isEmpty()) { // Sub Item, Seq. # 2 - 4
			final String breakLine = asHTML ? "<br>" : Constantes.NEWLINE;
			for (int i = 0; i < detail.size(); i++) {
				final MEXMEPrescDietaDet mDetail = detail.get(i);
				if (mDetail.getEXME_Dieta_ID() <= 0 || (i == 0 && !includeMain)) {
					continue;
				}
				if (dietStr.length() > 0) {
					if (asHTML) {
						dietStr.append("<br>");
					} else {
						dietStr.append(breakLine);
					}
				}
				dietStr.append(mDetail.getEXME_Dieta().getName());
			}
		}
		return dietStr.toString();
	}

	/**
	 * Regresa las dietas vigentes
	 * @param ctx
	 * @param exmeCtaPacId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPrescDieta> getVigentes(final Properties ctx, final int exmeCtaPacId, final String trxName) {
		return getQuery(ctx, exmeCtaPacId, true, -1, trxName).list();
	}

	/**
	 * Regresa las dietas vigentes
	 * @param ctx
	 * @param sql where clause, should start with AND
	 * @param params array of parameters (can be null)
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPrescDieta> getVigentes(final Properties ctx, final String sql, final Object[] params, final String trxName) {
		final StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sqlWhere.append(" VIGENTE='Y' ");
		if (StringUtils.isNotEmpty(sql)) {
			sqlWhere.append(sql);
		}
		return new Query(ctx, Table_Name, sqlWhere.toString(), trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(params)//
				.setOrderBy(COLUMNNAME_FechaInicio + " desc") //
				.list();
	}

	/** @return Read back label: Yes / No */
	public String getReadBackStr() {
		return MEXMECuidadosPac.getReadBackLabel(this);
	}

	/**  @return Noted label: User Pin (or name) and date (dd/MM HH:mm) */
	public String getNotedStr() {
		return MEXMECuidadosPac.getUserPINDate(getNotedBy(), getNotedDate());
	}

	@Override
	public String getPatientName() {
		return  ((MEXMECtaPac)getEXME_CtaPac()).getPatientName();
	}

	@Override
	public String getSummaryDetail() {
		final StringBuilder str = new StringBuilder();
		// main diet
		if (!getDetail().isEmpty()) {
			str.append("<b>").append(getMsgTranslate(MEXMEPrescDietaDet.COLUMNNAME_EXME_Dieta_ID)).append("</b>: ");
			str.append(getDietsStr(true, true));
		}
		if (getFechaInicio() != null) {
			str.append("<br><b>").append(Utilerias.getMsg(getCtx(), "msg.moConsultarCitas.finicio"));
			str.append("</b>: ");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getFechaInicio()));
			if (getFechaFin() != null) {
				str.append(" <b>").append(getMsgTranslate(I_EXME_PlanMed.COLUMNNAME_EndDate));
				str.append("</b>: ");
				str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getFechaFin()));
			}
		}
		// order type
		if (StringUtils.isNotBlank(getOrderType())) {
			str.append("<br><b>").append(getMsgTranslate(COLUMNNAME_OrderType)).append("</b>: ");
			str.append(getOrderType());
			if (StringUtils.isNotBlank(getReadBack())) {
				str.append("  <b>").append(getMsgTranslate(COLUMNNAME_ReadBack)).append("</b>: ");
				str.append(getReadBackStr());
			}
		}
		return str.toString();
	}

	@Override
	public boolean setAction(String action) {
		if (ACTION_AUTHENTICATE.equals(action)) {
			if (Env.getEXME_Medico_ID(getCtx()) > 0) {
				authenticate(get_TrxName());
			} else if (Env.getEXME_Enfermeria_ID(getCtx()) > 0) {
				note(get_TrxName());
			}
		} else if (ACTION_DISCONTINUE.equals(action)) {
			discontinue(get_TrxName());
		}
		return true;
	}
	
	@Override
	public String getRecordType() {
		return getMsgTranslate(MEXMEDieta.COLUMNNAME_EXME_Dieta_ID);
	}

	/** @return Physician Name */
	public String getNombreMed() {
		if (getEXME_Medico_ID() <= 0) {
			return "";
		}
		return getEXME_Medico().getNombre_Med();
	}

	/** @return ADAT: Yes / No */
	public String getIsAdatS() {
		return Constantes.yesNoStr(getCtx(), isADAT());
	}

	/** @return Main Diet Name */
	public String getNameDieta() {
		final List<MEXMEPrescDietaDet> det = getDetail();
		String nameDieta = "";
		if(det != null && !det.isEmpty()){
			nameDieta = det.get(0).getEXME_Dieta().getName();
		}
		return nameDieta;
	}

	@Override
	public boolean isAuthenticatedDisc() {
		// Card #746 - Authentication of Discontinuation Orders
		return MEXMEDiscontinueOrder.isAuthenticatedDisc(this);
	}

	/** @return enteredBy */
	public String getUserEntered() {
		return MUser.getUserName(getCtx(), getCreatedBy());
	}

	/** @return AuthenticatedBy */
	public String getUserAuthenticated() {
		return getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), getAuthenticatedBy());
	}

	/** @return NotedBy */
	public String getUserNoted() {
		return getNotedDate() == null ? "" : MUser.getUserName(getCtx(), getNotedBy());
	}

	/** @return OrderType */
	public String getOrderTypeStr() {
		return MRefList.getListName(getCtx(), X_EXME_PrescRXDet.ORDERTYPE_AD_Reference_ID, getOrderType());
	}

	public MEXMEDiscontinueOrder	dis	= null;

	public MEXMEDiscontinueOrder getDis() {
		if (!isVigente() && dis == null) {
			dis = MEXMEDiscontinueOrder.getFrom(this);
		}
		return dis;
	}
	
	/** @return Discontinued date */
	public Timestamp getDiscontinuedDate() {
		return getDis() == null ? null : dis.getDiscontinuedDate();
	}

	/** @return Discontinued by */
	public String getDiscontinuedBy() {
		return getDis() == null ? "" : MUser.getUserName(getCtx(), dis.getCreatedBy());
	}

	/**  @return DiscontinuedOrderTypeStr() */
	public String getDiscontinuedOrderTypeStr() {
		return getDis() == null ? "" : dis.getOrderTypeStr();
	}

	/** @return DiscontinuedNombre_Med */
	public String getDiscontinuedNombre_Med() {
		return getDis() == null ? "" : dis.getEXME_Medico().getNombre_Med();
	}
	
	/** @return Authenticated date */
	public Timestamp getDiscontinuedAuthenticatedDate() {
		return getDis() == null ? null : dis.getAuthenticated_Date();
	}

	/** @return DiscontinuedAuthenticatedBy */
	public String getUserDiscontinuedAuthenticatedBy() {
		return getDis() == null || dis.getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), dis.getAuthenticatedBy());
	}

	/** @return DiscontinuedReadBackStr */
	public String getDiscontinuedReadBackStr() {
		return getDis() == null ? "" : MEXMECuidadosPac.getReadBackLabel(dis);
	}
	
	/** @return subIten */
	public String getSubItem() {
		return getDietsStr(false, false);
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_Authenticated_Date.hashCode()) {
			comparable = getAuthenticated_Date();
		} else if (type == COLUMNNAME_AuthenticatedBy.hashCode()) {
			comparable = getUserAuthenticated();
		} else if (type == COLUMNNAME_DiscontinuedDate.hashCode()) {
			comparable = getDiscontinuedDate();
		} else if (type == COLUMNNAME_EXME_Medico_ID.hashCode()) {
			comparable = getNombreMed();
		} else if (type == COLUMNNAME_NotedBy.hashCode()) {
			comparable = getUserNoted();
		} else if (type == COLUMNNAME_NotedDate.hashCode()) {
			comparable = getNotedDate();
		} else if (type == COLUMNNAME_OrderType.hashCode()) {
			comparable = getOrderTypeStr();
		} else if (type == COLUMNNAME_ReadBack.hashCode()) {
			comparable = getReadBackStr();
		} else if (!isVigente() && getDis() != null) {
			comparable = getDis().onCompare(type);
		} else if(type == I_EXME_PrescDietaDet.COLUMNNAME_EXME_PrescDieta_ID.hashCode()){
			comparable = getNameDieta();
		} else if(type == I_EXME_PrescDietaDet.COLUMNNAME_EXME_Dieta_ID.hashCode()) {
			comparable = getDietsStr(false, false);
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}

}
