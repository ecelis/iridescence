package org.compiere.model;

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

/**
 * Creado: Agosto/2009
 * <p>
 * 
 * @author Lizeth de la Garza
 *         Modified by Lorena Lama (April 2012)
 */
public class MEXMECuidadosPac extends X_EXME_CuidadosPac implements Worklist, IDiscontinueOrder {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;
	/** s_log */
	private static CLogger		slog				= CLogger.getCLogger(MEXMECuidadosPac.class);

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 */
	public MEXMECuidadosPac(final Properties ctx, final int EXME_CuidadoPac_ID, final String trxName) {
		super(ctx, EXME_CuidadoPac_ID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECuidadosPac(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * - Se obtiene el valor del siguiente folio
	 * 
	 * @param trxName
	 * @param tableName - Nombre de la Tabla
	 * @param whereClause - sentencia WHERE
	 * @return int folio
	 * @author Liz de la Garza
	 */
	public static int nextFolio(final String trxName, final String tableName, final String whereClause, Object... params) {
		slog.config("nextFolio>> " + tableName);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COALESCE(MAX(folio),0)+1 FROM  ").append(tableName).append(" ");
		if (whereClause != null) {
			sql.append(whereClause);
		}
		return DB.getSQLValue(trxName, sql.toString(), params);
	}

	
	/**
	 * Obtener la lista de ultimo folio de cuidados del Paciente por Cuenta Paciente
	 * 
	 * @param ctx context
	 * @param ctaPacID Encounter Id
	 * @param active if not null add IsActive=? where clause
	 * @param encounterFormId if grather than zero add EXME_EncounterForms_ID=? where clause
	 * @param trxName transaction name
	 * @return
	 */
	public static List<MEXMECuidadosPac> get(final Properties ctx, final int ctaPacID, final Boolean active, final int encounterFormId,
		final String trxName) {
		return get(ctx, ctaPacID, active, encounterFormId, trxName, true);
	}
	/**
	 * Obtener la lista de ultimo folio de cuidados del Paciente por Cuenta Paciente
	 * 
	 * @param ctx context
	 * @param ctaPacID Encounter Id
	 * @param active if not null add IsActive=? where clause
	 * @param encounterFormId if grather than zero add EXME_EncounterForms_ID=? where clause
	 * @param trxName transaction name
	 * @param derechoHabiente
	 * @return
	 */
	public static List<MEXMECuidadosPac> get(final Properties ctx, final int ctaPacID, final Boolean active, final int encounterFormId,
		final String trxName, boolean derechoHabiente) {
		
		final List<Object> lista = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_CtaPac_ID=? ");
		lista.add(ctaPacID);
		if (active != null) {
			sql.append(" AND ISACTIVE=? ");
			lista.add(active);
		}
		if (encounterFormId > 0) {
			lista.add(encounterFormId);
			sql.append(" AND EXME_ENCOUNTERFORMS_ID=? ");
		}
		//Card #1545 ProMujer 
		if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(lista)//
			.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente )//
			.list();
	}
	
	/**
	 * Discontinues the order
	 * 
	 * @param trxName
	 */
	public void discontinue(String trxName) {
		discontinue(trxName, null);
	}
	
	/**
	 * Discontinues the order
	 * @param trxName
	 * @param params
	 * @see MEXMEDiscontinueOrder
	 */
	@Override
	public void discontinue(String trxName, DiscontinueParams params) {
		if (getDiscontinuedDate() == null) {
			setDiscontinuedBy(Env.getAD_User_ID(getCtx()));
			if (params == null) {
				setDiscontinuedDate(Env.getCurrentDate());
			} else {
				// Card #746 - Authentication of Discontinuation Orders
				setDiscontinuedDate(params.getDate());
			}
			setIsActive(false);
			if (!save(trxName)) {
				throw new MedsysException();
			}
		}
	}
	
	/**
	 * Notes the order
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
	 * Authenticates the order
	 * 
	 * @param trxName
	 */
	public void authenticate(String trxName) {
		if (isActive()) {
			// si no esta autenticado, y el usuario es medico, o la orden esta por escrito
			if (getAuthenticated_Date() == null && (Env.getEXME_Medico_ID(getCtx()) > 0 || ORDERTYPE_WrittenOrder.equals(getOrderType()))) {
				setAuthenticatedBy(MEXMEMedico.getUserId(getCtx(), getEXME_Medico_ID(), true));
				setAuthenticated_Date(Env.getCurrentDate());
				if (!save(trxName)) {
					throw new MedsysException();
				}
			}
		} else if (Env.getEXME_Medico_ID(getCtx()) > 0) {
			// Card #746 - Authentication of Discontinuation Orders
			MEXMEDiscontinueOrder.authenticate(this, get_TrxName());
		}
	}

	/** @return Read back label: Yes / No */
	public String getReadBackStr() {
		return getReadBackLabel(this);
	}

	/**@return Noted label: User Pin (or name) and date (dd/MM HH:mm)*/
	public String getNotedStr() {
		return getUserPINDate(getNotedBy(), getNotedDate());
	}
	
	
	/** @return Read back label: Yes / No 	 */
	public static String getReadBackLabel(final PO model) {
		return MRefList.getListName(model.getCtx(), READBACK_AD_Reference_ID, StringUtils.trim(model.get_ValueAsString(COLUMNNAME_ReadBack)));
	}

	/**  @return User Pin (or name) and date (dd/MM HH:mm) */
	public static String getUserPINDate(final int userId, final Timestamp date) {
		final StringBuilder noted = new StringBuilder();
		if (userId > 0 && date != null) {
			noted.append(MUser.getUserPIN(userId));
			noted.append(" ");
			noted.append(Constantes.getDateTimeNoYear(Env.getCtx()).formatConvert(date));
		}
		return noted.toString();
	}

	@Override
	public String getPatientName() {
		return ((MEXMECtaPac)getEXME_CtaPac()).getPatientName();
	}

	@Override
	public String getSummaryDetail() {
		final StringBuilder str = new StringBuilder();
		// Instruction
		str.append(super.getDescription()).append(". ");
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
		return getMsgTranslate(COLUMNNAME_EXME_CuidadosPac_ID);
	}

	public String getNombreMed() {
		if (getEXME_Medico_ID() <= 0) {
			return "";
		}
		return getEXME_Medico().getNombre_Med();
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
		if (!isActive() && dis == null) {
			dis = MEXMEDiscontinueOrder.getFrom(this);
		}
		return dis;
	}

	/** @return Discontinued date */
	public Timestamp getDiscontinuedDate() {
		return getDis() == null ? null : dis.getDiscontinuedDate();
	}
	
	/** @return Discontinued by */
	public String getDiscontinuedByStr() {
		return getDis() == null ? "" : MUser.getUserName(getCtx(), dis.getCreatedBy());
	}

	/** @return DiscontinuedOrderTypeStr() */
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
		} else if (type == COLUMNNAME_Description.hashCode()) {
			comparable = getDescription();
		} else if (!isActive() && getDis() != null) {
			comparable = dis.onCompare(type);
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}

}
