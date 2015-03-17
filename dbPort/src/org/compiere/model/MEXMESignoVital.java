/* Created on 25/04/2005
 * 
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.install.MEXMERangoSV;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

public class MEXMESignoVital extends X_EXME_SignoVital {
	private static final long	serialVersionUID	= 1L;

	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MEXMESignoVital.class);
	// unidad de medida original
	private MUOM				uom					= null;

	// valor convertido
	private VitalSignsUtils		utils				= null;
	private BigDecimal			valueMinTo			= null;
	private BigDecimal			valueMaxTo			= null;

	/**
	 * @param ctx
	 * @param ID
	 * @param trxName
	 */
	public MEXMESignoVital(Properties ctx, int EXME_SignoVital_ID, String trxName) {
		super(ctx, EXME_SignoVital_ID, trxName);
		if (get_ID() > 0 && getC_UOM_ID() > 0) {
			convertFromDB();
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESignoVital(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		convertFromDB();
	}

	// /**
	// *
	// * @param ctx
	// * @param EXME_SignoVital_ID
	// * @param trxName
	// * @param seleccionado
	// */
	// public MEXMESignoVital(Properties ctx, int EXME_SignoVital_ID, String trxName,
	// boolean seleccionado) {
	// this(ctx, EXME_SignoVital_ID, trxName);
	// this.seleccionado = seleccionado;
	// }

	// /** @ deprecated no hay referencias *
	// public static MEXMESignoVital[] get(Properties ctx, String trxName) {
	// if (ctx == null) {
	// return null;
	// }
	// // Solo para un cliente definido
	// if (Env.getAD_Client_ID(ctx) <= 0 || Env.getAD_Org_ID(ctx) < 0) {
	// return null;
	// }
	// final List<MEXMESignoVital> lista = new Query(ctx, Table_Name, "", trxName)//
	// .setOnlyActiveRecords(true)//
	// .addAccessLevelSQL(true)//
	// .setOrderBy("EXME_SignoVital.Secuencia")//
	// .list();
	// final MEXMESignoVital[] formas = new MEXMESignoVital[lista.size()];
	// lista.toArray(formas);
	// return formas;
	// }*/

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVital> getSignosVitales(Properties ctx, String trxName) {
		if (ctx == null) {
			return null;
		}
		final List<MEXMESignoVital> lista = new ArrayList<MEXMESignoVital>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_SignoVital");
		sql.append(" WHERE EXME_SignoVital.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_SignoVital.Secuencia ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MEXMESignoVital(ctx, rs, trxName));
			}

		}
		catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs);
		}
		return lista;
	}

	/** @deprecated */
	private String	valorSt	= "";

	/** @deprecated */
	public void setValorSt(String valorSt) {
		if (StringUtils.isBlank(valorSt) || Utilerias.isNumeric(valorSt)) {
			this.valorSt = valorSt;
		}
	}

	/** @deprecated */
	public String getValorSt() {
		return valorSt;
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @param @deprecated convert - No se usa
	 * @param windowType
	 * @return
	 */
	public static List<MEXMESignoVital> getSignosVitales(Properties ctx, String trxName, boolean convert, String windowType) {
		return getSignosVitales(ctx, trxName, convert, windowType, null);
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @param @deprecated convert - No se usa
	 * @param windowType
	 * @param whereClause
	 * @param params
	 * @return
	 */
	public static List<MEXMESignoVital> getSignosVitales(Properties ctx, String trxName, boolean convert, String windowType,
		StringBuilder whereClause, Object... params) {
		if (ctx == null) {
			return null;
		}
		List<MEXMESignoVital> lista = new ArrayList<MEXMESignoVital>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_SignoVital.* ");
		sql.append(" FROM EXME_SignoVital ");
		sql.append(" INNER JOIN C_UOM ON (EXME_SIGNOVITAL.C_UOM_ID = C_UOM.C_UOM_ID) ");
		sql.append(" WHERE EXME_SignoVital.IsActive='Y' ");
		if (StringUtils.isNotEmpty(windowType)) {
			sql.append(" AND EXME_SignoVital.WindowType IN(?,?) ");
		}
		if (whereClause != null && whereClause.length() > 0) {
			sql.append(whereClause);
		}
		if (Env.getUserPatientID(ctx) < 0) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}
		sql.append(" ORDER BY EXME_SignoVital.Secuencia ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			if (StringUtils.isNotEmpty(windowType)) {
				pstmt.setString(i++, windowType);
				pstmt.setString(i++, MEXMESignoVital.WINDOWTYPE_All);
			}
			for (Object param : params) {
				DB.setParameter(pstmt, i++, param);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMESignoVital obj = new MEXMESignoVital(ctx, rs, trxName);
				lista.add(obj);
			}

		}
		catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtenemos el Nombre de Signos Vitales
	 * 
	 * @deprecated usado solo por clases struts GraficoECEAction / PopupGraficoAction
	 *
	public static String getNombreSignoVital(Properties ctx, long xXSignoVitalID, String trxName) throws Exception {
		String sql = "SELECT EXME_SignoVital.name FROM EXME_SignoVital WHERE EXME_SignoVital.EXME_signovital_id = ? ";
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, Table_Name);
		final String retValue = DB.getSQLValueString(trxName, sql, xXSignoVitalID);

		if (retValue == null) {
			throw new Exception("No existe la Signo Vital.");
		}
		return retValue;
	}*/

	/**
	 * Validamos si no han pasado mas de 24 horas de la fecha acutal contra la fecha
	 * que se registro los signos vitales
	 * 
	 * @param fecha
	 * @return true si no han pasado 24 horas, false si ya paso mas de de 24 hrs
	 * @deprecated no hay referencias
	 * 
	 *             public static boolean menor24hrs(java.util.Date fecha) {
	 *             boolean resultado = true;
	 *             Calendar cal = Calendar.getInstance();
	 *             try{
	 *             //fecha actual menos un dia
	 *             cal.add(Calendar.DATE, -1);
	 * 
	 *             //si la fecha actual menos un dia es mayor que la fecha del signo
	 *             if (cal.getTime().getTime() > fecha.getTime()){
	 *             resultado = false;
	 *             }
	 * 
	 *             } catch (Exception e){
	 *             slog.log(Level.SEVERE, e.getMessage(), e);
	 *             }
	 *             return resultado;
	 *             }
	 */

	/**
	 * Obtenemos los Signos Vitales para el combo con espacio en blanco
	 * 
	 * @param ctx,
	 *            Propiedades
	 * @param blanco,
	 *            Con espacio en blanco
	 * @return Lista (LabelValueBean) con los signos vitales
	 * @exception Exception
	 *                Description of the Exception
	 */
	public static List<LabelValueBean> getSignoVitalCombo(Properties ctx, boolean blanco, String trxName) {
		return Query.getList("SELECT EXME_SignoVital_ID, Name FROM EXME_SignoVital", trxName);// Lama
	}

	/**
	 * Obtenemos un Signo Vital
	 * 
	 * @param ctx,
	 *            Propiedades
	 * @param
	 * @return Lista con los signos vitales
	 * @exception Exception
	 *                Description of the Exception
	 * @deprecated usado solo por clases struts DetalleAction / DetHosAction
	 *
	public static List<SigVitalesDet> getSignoVital(Properties ctx, int signoID, String trxName) {
		List<SigVitalesDet> resultados = new ArrayList<SigVitalesDet>();

		int org = Env.getContextAsInt(ctx, "#AD_Org_ID");// FIXME: ? depende el nivel de acceso
		int client = Env.getContextAsInt(ctx, "#AD_Client_ID");
		// int user = Env.getContextAsInt(ctx, "#AD_User_ID");
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(
			"SELECT EXME_SignoVital.EXME_SignoVital_ID, EXME_SignoVital.Value, EXME_SignoVital.Name, EXME_SignoVital.ValorMin, EXME_SignoVital.ValorMax, EXME_SignoVital.Secuencia ")
			.
			// append(" FROM EXME_SignoVital  WHERE EXME_SignoVital.IsActive = 'Y' AND EXME_SignoVital.AD_Client_ID IN (0," ). no debe de tomar en
			// cuenta si estan desactivados **gderreza**
			append(" FROM EXME_SignoVital  WHERE  EXME_SignoVital.AD_Client_ID IN (0,").append(client)
			.append(") AND  EXME_SignoVital.AD_Org_ID IN (0, ").append(org).append(")");
		if (signoID != 0)
			sql.append(" AND EXME_SignoVital.EXME_SignoVital_ID =  ").append(signoID);

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SignoVital"));

		sql.append("ORDER BY EXME_SignoVital.Secuencia ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SigVitalesDet signos = new SigVitalesDet();
				signos.setSigVitID(rs.getLong("EXME_signovital_id"));
				signos.setValue(rs.getString("EXME_signovital_id"));
				signos.setName(rs.getString("name"));
				signos.setValorMin(rs.getDouble("valormin"));
				signos.setValorMax(rs.getDouble("valormax"));
				signos.setSecuencia(rs.getInt("secuencia"));
				resultados.add(signos);
			}
		}
		catch (SQLException e) {
			slog.log(Level.SEVERE, "getSignoVital (" + sql + ")", e);

		}
		finally {
			DB.close(rs);
			rs = null;
			pstmt = null;
		}

		return resultados;
	}*/

	/**
	 * Obtenemos el signo vital en base a su value.
	 * 
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MEXMESignoVital getByValue(Properties ctx, String value, String trxName) {
		final MEXMESignoVital retValue = new Query(ctx, Table_Name, "UPPER(EXME_SIGNOVITAL.Value)=UPPER(?)", trxName)//
			.setParameters(value)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(Env.getUserPatientID(ctx) < 0)//
			.first();
		return retValue;
	}

	/**
	 * Obtenemos la formula relacionada con el signovital.
	 * 
	 * @return
	 */
	public MEXMEFormulaSigVital getFormulaSigVital() {
		MEXMEFormulaSigVital retValue = null;
		if (getEXME_FormulaSigVital_ID() > 0) {
			retValue = new MEXMEFormulaSigVital(getCtx(), getEXME_FormulaSigVital_ID(), get_TrxName());
		}
		return retValue;
	}

	public static List<MEXMESignoVital> getFromUom(Properties ctx, int C_UOM_ID, String trxName) {
		final List<MEXMESignoVital> retValue = new Query(ctx, Table_Name, "EXME_SignoVital.C_UOM_ID=?", trxName)//
			.setParameters(C_UOM_ID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy("EXME_SignoVital.NAME")//
			.list();
		return retValue;
	}

	// private boolean seleccionado = false;
	//
	// public boolean isSeleccionado() {
	// return seleccionado;
	// }
	//
	// public void setSeleccionado(boolean seleccionado) {
	// this.seleccionado = seleccionado;
	// }

	// /**@ deprecated *
	// public String getUnidadMedida() {
	// return getUomTo(true).getX12DE355();
	// }*/

	/**
	 * rsolorzano 10/03/2010
	 * revisamos el sistema de medici�n del signo vital en caso que sea necesaria una conversi�n
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean convertirUnidades(Properties ctx, int uomID) {

		boolean convertir = false;

		String sistemaMedicionUM = null;
		sistemaMedicionUM = MUOM.getSistMedicionUOM(ctx, uomID);

		String sistemaMedicionUser = null;
		sistemaMedicionUser = MUser.getSistMedicionUsuario(ctx);

		if (sistemaMedicionUM != null && sistemaMedicionUM.length() > 0 && sistemaMedicionUser != null && sistemaMedicionUser.length() > 0) {
			if (sistemaMedicionUM.equals(MUOM.SISTEMAMEDICION_None)) {
				convertir = false;
			} else if (sistemaMedicionUM.equals(sistemaMedicionUser)) {
				convertir = false;
			} else if (!sistemaMedicionUM.equals(sistemaMedicionUser)) {
				convertir = true;
			}
		} else {
			convertir = false;
		}

		return convertir;

	}

	// private int signoDetID = 0;
	//
	// public int getSignoDetID() {
	// return signoDetID;
	// }
	//
	// public void setSignoDetID(int signoDetID) {
	// this.signoDetID = signoDetID;
	// }

	/**
	 * beforeDelete
	 * Verify vital sign formulas **mantis 4525.**
	 */
	@Override
	protected boolean beforeDelete() {
		// Verificar si el signo vital tiene una formula asociada.
		if (MEXMEFormulaSigVital.findSignoById(this.getCtx(), getEXME_SignoVital_ID())) {
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@UsedInFormula@"));
			return false;
		}

		final List<MEXMEFormulaSigVital> lista = MEXMEFormulaSigVital.getListAll(getCtx(), null);
		if (!lista.isEmpty()) {

			for (MEXMEFormulaSigVital fsv : lista) {
				if (isUsedInFormula(fsv, false)) {
					log.saveError("Error", Msg.parseTranslation(getCtx(), "@UsedInFormula@"));
					return false;
				}
			}
		}

		return super.beforeDelete();
	}

	/**
	 * beforeDelete
	 * Verify vital sign formulas **mantis 4525.**
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean success = true;
		if (newRecord || is_ValueChanged(COLUMNNAME_Name)) {
			if (StringUtils.isAlphanumeric(getInterpreterKey())) { // if name contains invalid characters
				if (!newRecord) {// only if value changed
					final List<MEXMEFormulaSigVital> lista = MEXMEFormulaSigVital.getListAll(getCtx(), null);
					for (MEXMEFormulaSigVital fsv : lista) {
						// if the formula was changed
						if (isUsedInFormula(fsv, true) && fsv.is_Changed()) {
							success = fsv.save(get_TrxName());// update formula
							if (!success) {
								break;
							}
						}
					}
				}
			} else {
				log.saveError("Error", Utilerias.getMsg(getCtx(), "patient.error.aphanumericOnly"));
				success = false;
			}
		}
		return success;
	}

	/**
	 * @ deprecated *
	 * public int getOriginalUOMID() {
	 * return getUom().get_ID();//Lama
	 * }
	 */

	public MUOM getUom() {
		if (uom == null) {
			uom = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
		}
		return uom;
	}

	public void setUom(MUOM uom) {
		this.uom = uom;
	}

	public void setUomTo(MUOM uom) {
		getUtils().setUomTo(uom);
	}

	/**
	 * unidad de medida para las conversiones
	 * 
	 * @param origWhenIsNull
	 *            - true: regresa el valor de C_UOM_ID (metrico) cuando no haya uom de conversion<br>
	 *            - false: regresa null si no hay uom de conversion
	 * @return
	 */
	public MUOM getUomTo(boolean origWhenIsNull) {
		if (origWhenIsNull && getUtils().getUomTo() == null) {
			return getUom();
		}
		return getUtils().getUomTo();
	}

	/**
	 * verificamos los rangos de los signos vitales de acuerdo a la edad y sexo (por ejemplo el de
	 * masa corporal)
	 */
	public List<MEXMERangoSV> getRanks(final String sex, final int age) {
		List<MEXMERangoSV> rankLst;
		if (StringUtils.isNotEmpty(sex)) {
			rankLst = MEXMERangoSV.getRanks(getCtx(), getEXME_SignoVital_ID(), sex, age, get_TrxName());
			for (MEXMERangoSV rank : rankLst) {
				if (rank.isDefault()) {
					setValorMin(rank.getValorIni());
					setValorMax(rank.getValorFin());

					valueMinTo = rank.getValorIniTo();
					valueMinTo = rank.getValorIniTo();
					break;
				}
			}
		} else {
			rankLst = new ArrayList<MEXMERangoSV>();
		}
		return rankLst;
	}

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public StringBuilder validateConversion(Locale locale) {
		final StringBuilder errors = new StringBuilder();

		if (getUom() == null) {
			errors.append(Utilerias.getMessage(getCtx(), locale, "error.sigvital.unidadMedida", getName()));
			errors.append(Constantes.NEWLINE);
		} else if (!getUom().isDefault()) {
			errors.append(Utilerias.getMessage(getCtx(), locale, "error.sigvital.unidadMedida.default", getName()));
			errors.append(Constantes.NEWLINE);
		} else if (getUom().getSistemaMedicion() == null) {
			errors.append(Utilerias.getMessage(getCtx(), locale, "error.unidad.sistemaMedicion", getUom().getName(), getName()));
			errors.append(Constantes.NEWLINE);
		} else if (MUser.convertirUnidades(getCtx()) && !VitalSignsUtils.isConfigured(getCtx(), getC_UOM_ID())) {
			final String sisMedicion = MRefList.getListName(getCtx(), MUser.SISTEMAMEDICION_AD_Reference_ID, MUser.getSistMedicionUsuario(getCtx()));
			errors.append(Utilerias.getMessage(getCtx(), locale, "error.unidad.conversion", getUom().getName(), getName(), sisMedicion));
			errors.append(Constantes.NEWLINE);
		}
		return errors;
	}

	public VitalSignsUtils getUtils() {
		if (utils == null) {
			convertFromDB();
		}
		return utils;
	}

	/**
	 * Convierte los valores valueMin y valueMax si el usuario requiere conversion
	 * y los asigna en valueMinTo y valueMaxTo
	 */
	public void convertFromDB() {
		utils = new VitalSignsUtils(getCtx(), getC_UOM_ID());
		if (utils.getUomTo() != null) {
			valueMinTo = utils.convertFromDB(super.getValorMin());
			valueMaxTo = utils.convertFromDB(super.getValorMax());
		}
	}

	/**
	 * Regresa el valor convertido si el usuario esta configurado que requiere conversion, de lo
	 * contrario regresa el valor original de la base de datos
	 * 
	 * @return valor minimo
	 */
	public BigDecimal getValorMinTo() {
		if (getUtils().getUomTo() != null && valueMinTo != null) {
			return valueMinTo;
		} else {
			return super.getValorMin();
		}
	}

	/**
	 * Regresa el valor convertido si el usuario esta configurado que requiere conversion, de lo
	 * contrario regresa el valor original de la base de datos
	 * 
	 * @return valor maximo
	 */
	public BigDecimal getValorMaxTo() {
		if (getUtils().getUomTo() != null && valueMaxTo != null) {
			return valueMaxTo;
		} else {
			return super.getValorMax();
		}
	}

	private String	interpreterKey;

	public String getInterpreterKey() {
		if (interpreterKey == null) {
			if (Constantes.HG_LG.equals(getName())) {
				StringUtils.replace(getName(), "/", "_");
			}			
			interpreterKey = StringUtils.remove(StringUtils.remove(getName(), " "), ".").trim();
		}else {
			if (Constantes.HG_LG.equals(getName())) {
				interpreterKey =StringUtils.replace(getName(), "/", "_");
			}
		}
		return interpreterKey;
	}

	public boolean assignFormula(MEXMEFormulaSigVital formula) {
		if (formula != null) {
			set_TrxName(formula.get_TrxName());
			setEXME_FormulaSigVital_ID(formula.get_ID());
			return save();
		}
		return true;
	}

	/**
	 * Validates if the vital sign is being used by a formula
	 * @param fsv - Vital sign's formula
	 * @param replace - if true, replaces the vital's name contained in the formula with the new one
	 * @return
	 */
	public boolean isUsedInFormula(final MEXMEFormulaSigVital fsv, boolean replace) {
		boolean usedInFormula = false; 
		if (fsv == null || StringUtils.isBlank(fsv.getFormula())) {// formula is mandatory
			return usedInFormula;
		} // name to compare, if requires replace takes the oldValue
		final String vitalName = replace ? StringUtils.remove(StringUtils.remove(((String) get_ValueOld(COLUMNNAME_Name)).trim(), " "), ".") : getInterpreterKey();
		if (!fsv.getFormula().contains(vitalName)) { // if vital name is contained inside the formula
			return usedInFormula;
		}
		int index = 0;
		final StringBuilder newFormula = new StringBuilder(); // replaced formula
		final StringBuilder formulaTmp = new StringBuilder(fsv.getFormula()); // formula
		try {
			while (formulaTmp.indexOf(vitalName) > -1) { // do while name is contained in the formula
				int indexI = formulaTmp.indexOf(vitalName);
				index = indexI + vitalName.length();
				boolean found = formulaTmp.length() == index || // found at the end of the formula
					// the next character is not alphanumeric : (A-Z & 0-9)
					(!StringUtils.isAlphanumeric(String.valueOf(formulaTmp.charAt(index))) && //
					(indexI == 0 || // found at the beginning of the formula
					// the previous character is not alphanumeric : (A-Z & 0-9)
					!StringUtils.isAlphanumeric(String.valueOf(formulaTmp.charAt(indexI - 1)))//
					));
				if (found) {
					if (replace) { // replaces the new value
						formulaTmp.replace(indexI, index, getInterpreterKey());
						index = indexI + getInterpreterKey().length();// update index
					} else {
						usedInFormula = true;
						break;
					}
				}
				if (replace) { // creating the new formula
					newFormula.append(formulaTmp.substring(0, index));
				}
				formulaTmp.delete(0, index);
			}
			if (replace) { // creating the new formula
				newFormula.append(formulaTmp);
				fsv.setFormula(newFormula.toString());
			}
		}
		catch (Exception e) {
			log.severe(e.toString());
		}
		return usedInFormula;
	}

}
