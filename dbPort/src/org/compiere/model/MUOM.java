package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.OptionItem;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

/**
 *	Unit Of Measure Model
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MUOM.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MUOM extends X_C_UOM
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** X12 Element 355 Code	Minute	*/
	private static final String		X12_MINUTE = "MJ";
	/** X12 Element 355 Code	Hour	*/
	private static final String		X12_HOUR = "HR";
	/** X12 Element 355 Code	Day 	*/
	private static final String		X12_DAY = "DA";
	/** X12 Element 355 Code	Work Day (8 hours / 5days)	 	*/
	private static final String		X12_DAY_WORK = "WD";
	/** X12 Element 355 Code	Week 	*/
	private static final String		X12_WEEK = "WK";
	/** X12 Element 355 Code	Month 	*/
	private static final String		X12_MONTH = "MO";
	/** X12 Element 355 Code	Work Month (20 days / 4 weeks) 	*/
	private static final String		X12_MONTH_WORK = "WM";
	/** X12 Element 355 Code	Year 	*/
	private static final String		X12_YEAR = "YR";

	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MUOM.class);

	private List<MEXMESignoVital> signos = null;

	private String detalleGrafica = null;

	private String	m_trlName = null;

	public String getDetalleGrafica() {
		return detalleGrafica;
	}

	public void setDetalleGrafica(String detalleGrafica) {
		this.detalleGrafica = detalleGrafica;
	}

	public List<MEXMESignoVital> getSignos() {
		if (signos == null) {
			signos = MEXMESignoVital.getFromUom(getCtx(), getC_UOM_ID(),
					get_TrxName());
		}
		return signos;
	}

	public void setSignos(List<MEXMESignoVital> signos) {
		this.signos = signos;
	}

	/**
	 * 	Get Minute C_UOM_ID
	 *  @param ctx context
	 * 	@return C_UOM_ID for Minute
	 */
	public static int getMinute_UOM_ID (Properties ctx)
	{
		if (Ini.isClient())
		{
			Iterator<MUOM> it = s_cache.values().iterator();
			while (it.hasNext())
			{
				MUOM uom = (MUOM)it.next();
				if (uom.isMinute()) {
					return uom.getC_UOM_ID();
				}
			}
		}
		//	Server
		int C_UOM_ID = 0;
		String sql = "SELECT C_UOM_ID FROM C_UOM "
				+ "WHERE IsActive='Y' AND X12DE355='MJ'";	//	HardCoded
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				C_UOM_ID = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return C_UOM_ID;
	}	//	getMinute_UOM_ID

	/**
	 * 	Get Default C_UOM_ID
	 *	@param ctx context for AD_Client
	 *	@return C_UOM_ID
	 */
	public static int getDefault_UOM_ID (Properties ctx)
	{
		String sql = "SELECT C_UOM_ID "
				+ "FROM C_UOM "
				+ "WHERE AD_Client_ID IN (0,?) "
				+ "ORDER BY IsDefault DESC, AD_Client_ID DESC, C_UOM_ID";
		return DB.getSQLValue(null, sql, Env.getAD_Client_ID(ctx));
	}	//	getDefault_UOM_ID

	/*************************************************************************/

	/**	UOM Cache				*/
	private static CCache<Integer,MUOM>	s_cache 
	= new CCache<Integer,MUOM>("C_UOM", 30);

	/**
	 * 	Get UOM from Cache
	 * 	@param ctx context
	 *	@param C_UOM_ID ID
	 * 	@return UOM
	 */
	public static MUOM get (Properties ctx, int C_UOM_ID)
	{
		if (s_cache.size() == 0) {
			loadUOMs (ctx);
		}
		//

		MUOM uom = (MUOM)s_cache.get(C_UOM_ID);

		if (uom == null) {
			uom = new MUOM (ctx, C_UOM_ID, null);
			s_cache.put(C_UOM_ID, uom);
		}

		//
		return uom;
	}	//	getUOMfromCache

	/**
	 * 	Get Precision
	 * 	@param ctx context
	 *	@param C_UOM_ID ID
	 * 	@return Precision
	 */
	public static int getPrecision (Properties ctx, int C_UOM_ID)
	{
		MUOM uom = get(ctx, C_UOM_ID);
		return uom.getStdPrecision();
	}	//	getPrecision

	/**
	 * 	Load All UOMs
	 * 	@param ctx context
	 */
	private static void loadUOMs (Properties ctx)
	{
		String sql = MRole.getDefault(ctx, false).addAccessSQL(
				"SELECT * FROM C_UOM "
						+ "WHERE IsActive='Y'",
						"C_UOM", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MUOM uom = new MUOM(ctx, rs, null);
				s_cache.put (uom.getC_UOM_ID(), uom);
			}

		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}	//	loadUOMs


	/**************************************************************************
	 *	Constructor.
	 *	@param ctx context
	 *  @param C_UOM_ID UOM ID
	 *  @param trxName transaction
	 */
	public MUOM (Properties ctx, int C_UOM_ID, String trxName)
	{
		super (ctx, C_UOM_ID, trxName);
		if (C_UOM_ID == 0)
		{
			//	setName (null);
			//	setX12DE355 (null);
			setIsDefault (false);
			setStdPrecision (2);
			setCostingPrecision (6);
		}
	}	//	UOM

	/**
	 *	Load Constructor.
	 *	@param ctx context
	 *  @param rs result set
	 *  @param trxName transaction
	 */
	public MUOM (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	UOM

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("UOM[ID=");
		sb.append(get_ID()).append(", Name=").append(getName());
		return sb.toString();
	}	//	toString

	/**
	 * 	Round qty
	 *	@param qty quantity
	 *	@param stdPrecision true if std precisison
	 *	@return rounded quantity
	 */
	public BigDecimal round (BigDecimal qty, boolean stdPrecision)
	{
		int precision = getStdPrecision();

		if (!stdPrecision) {
			precision = getCostingPrecision();
		}

		if (qty.scale() > precision) {
			qty = qty.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}

		return qty;
	}	//	round

	/**
	 * 	Minute
	 *	@return true if UOM is minute
	 */
	public boolean isMinute()
	{
		return X12_MINUTE.equals(getX12DE355());
	}
	/**
	 * 	Hour
	 *	@return true if UOM is hour
	 */
	public boolean isHour()
	{
		return X12_HOUR.equals(getX12DE355());
	}
	/**
	 * 	Day
	 *	@return true if UOM is Day
	 */
	public boolean isDay()
	{
		return X12_DAY.equals(getX12DE355());
	}
	/**
	 * 	WorkDay
	 *	@return true if UOM is work day
	 */
	public boolean isWorkDay()
	{
		return X12_DAY_WORK.equals(getX12DE355());
	}
	/**
	 * 	Week
	 *	@return true if UOM is Week
	 */
	public boolean isWeek()
	{
		return X12_WEEK.equals(getX12DE355());
	}
	/**
	 * 	Month
	 *	@return true if UOM is Month
	 */
	public boolean isMonth()
	{
		return X12_MONTH.equals(getX12DE355());
	}
	/**
	 * 	WorkMonth
	 *	@return true if UOM is Work Month
	 */
	public boolean isWorkMonth()
	{
		return X12_MONTH_WORK.equals(getX12DE355());
	}
	/**
	 * 	Year
	 *	@return true if UOM is year
	 */
	public boolean isYear()
	{
		return X12_YEAR.equals(getX12DE355());
	}

	/**
	 * Obtener la Unidad de Medida en Factura Proveedor
	 * @deprecated there are no explicit references to this method.
	 */
	public List<OptionItem> getUOMProviderInvoice (Integer mProductID, boolean emptyRow){
		List<OptionItem> l = null;
		StringBuilder sb = new StringBuilder(" SELECT * FROM C_UOM WHERE ")
		.append(" (EXISTS (SELECT * FROM C_UOM uu WHERE C_UOM.C_UOM_ID=uu.C_UOM_ID AND IsDefault='Y' AND ?=0) ")
		.append(" OR EXISTS (SELECT * FROM M_Product p WHERE C_UOM.C_UOM_ID=p.C_UOM_ID AND ?=p.M_Product_ID) ")
		.append(" OR EXISTS (SELECT * FROM M_Product p INNER JOIN C_UOM_Conversion c ON (p.C_UOM_ID=c.C_UOM_ID AND p.M_Product_ID=c.M_Product_ID) WHERE C_UOM.C_UOM_ID=c.C_UOM_TO_ID AND ?=p.M_Product_ID)) ");
		StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(), this.get_TableName()));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, mProductID);
			pstmt.setInt(3, mProductID);
			rs = pstmt.executeQuery();
			l = new ArrayList<OptionItem>();
			if (emptyRow) {
				l.add(new OptionItem("-1", ""));
			}
			while (rs.next()) {
				l.add(new OptionItem(String.valueOf(rs.getString("C_UOM_ID")), rs.getString("NAME")));
			}

		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getUOMProviderInvoice", e);
		}finally{
			DB.close(rs, pstmt);
		}
		return l;
	}


	/**
	 * Obtiene las unidades de medida (ID, Name)
	 * @return
	 * @deprecated there are no explicit referentes to this method.
	 */
	public Vector<OptionItem> getUnidadMedida() {

		String sql = "select c_uom_id, name from c_uom where isactive='Y'";

		Vector<OptionItem> unidades = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setInt(1, projectPhaseId);
			rs = pstmt.executeQuery();
			unidades = new Vector<OptionItem>();

			while (rs.next()) {
				unidades.add(new OptionItem(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			s_log.log(Level.WARNING, "", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return unidades;
	}


	/**
	 * Obtiene la Unidad de Medida a partir del Value
	 * @return int C_UOM_ID
	 */

	public static int getUOMID(String value) {

		String sql = "SELECT C_UOM_ID FROM C_UOM C WHERE C.name = ? ";
		int UOMId = 0;
		try {

			UOMId = DB.getSQLValue(null, sql, value);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLines", e);
		}

		return UOMId;
	}

	/**
	 * Get a Unit of Measure name from his ID.
	 * @param c_udm_id The UOM id.
	 * @return The name of the unit.
	 */
	public String getUdmName(int c_udm_id) {

		String sql = "select name from c_uom where c_uom_id = ?";
		String udmName = null;
		try {

			udmName = DB.getSQLValueStringEx(get_TrxName(), sql, c_udm_id);

		} catch (Exception e) {
			log.log(Level.SEVERE, "getLines", e);
		}


		return udmName;
	}
	/**
	 * Obtiene las unidades de medida de los signos vitales de una cuenta paciente
	 * @param ctx Contexto
	 * @param EXME_CtaPac_ID Cuenta Paciente
	 * @param EXME_CitaMedica Cita Médica
	 * @param fechaIni Fecha desde
	 * @param fechaFin Fecha Fin
	 * @param trxName Nombre de transacci�n
	 * @return
	 */
	public static List<MUOM> getFromCtaPac(Properties ctx, int EXME_CtaPac_ID, int EXME_CitaMedica_ID,
			int EXME_Paciente_ID, String fechaIni, String fechaFin, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select distinct(uom.C_UOM_ID), uom.* from C_UOM uom ");
		sql.append("inner join EXME_SignoVital sig on uom.C_UOM_ID = sig.C_UOM_ID ");
		sql.append("inner join EXME_SignoVitalDet det on det.EXME_SignoVital_ID = sig.EXME_SignoVital_ID ");
		if (EXME_CtaPac_ID > 0) {
			sql.append("where det.EXME_CtaPac_ID = ? ");
		}
		else if (EXME_CitaMedica_ID > 0) {
			sql.append("where det.EXME_CitaMedica_ID = ? ");
		}
		else if (EXME_Paciente_ID > 0) {
			sql.append("where det.EXME_PAciente_ID = ? ");
		}
		sql.append("and trim(det.estatus)=? ");

		if (DB.isOracle()) {
			sql.append("and trunc(det.FECHA) >= to_date(?,'dd/MM/yyyy') ");
			sql.append("and trunc(det.FECHA) <= to_date(?,'dd/MM/yyyy') ");
		} else if (DB.isPostgreSQL()) {
			sql.append("and DATE_TRUNC('day', det.FECHA) >= to_date(?,'dd/MM/yyyy') ");
			sql.append("and DATE_TRUNC('day', det.FECHA) <= to_date(?,'dd/MM/yyyy') ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMESignoVitalDet.Table_Name, "det"));

		sql.append(" order by uom.Name ");

		final List<MUOM> lista = new ArrayList<MUOM>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			if (EXME_CtaPac_ID > 0) {
				pstmt.setInt(index++, EXME_CtaPac_ID);
			}
			else if (EXME_CitaMedica_ID > 0) {
				pstmt.setInt(index++, EXME_CitaMedica_ID);
			}
			else if (EXME_Paciente_ID > 0) {
				pstmt.setInt(index++, EXME_Paciente_ID);
			}
			pstmt.setString(index++, MEXMESignoVitalDet.ESTATUS_Active);
			pstmt.setString(index++, fechaIni);
			pstmt.setString(index++, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MUOM uom = new MUOM(ctx, rs, trxName);
				uom.setNameConv(uom.getTrlName());
				if (MUser.convertirUnidades(ctx)) {
					int cuomID = MUOMConversion.getCUOMToID(ctx, uom.getC_UOM_ID(), MUser.getSistMedicionUsuario(ctx));
					if (cuomID > 0) {
						MUOM uom2 = new MUOM(ctx, cuomID, trxName);
						uom.setNameConv(uom2.getTrlName());
					}
				}
				lista.add(uom);
			}

		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	public static List<KeyNamePair> getUomList(Properties ctx, String trxName) {
		return getUomList(ctx, null, trxName);
	}

	public static List<KeyNamePair> getUomList(Properties ctx, String where, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// Lama: usar metodo tmb para idioma base
		boolean base = Language.isBaseLanguage(Env.getAD_Language(ctx));
		sql.append("SELECT C_UOM.C_UOM_ID, ").append(base ? "C_UOM" : "ct").append(".Name as name, C_UOM.isdefault ");
		sql.append("FROM C_UOM ");
		if (!base) {
			sql.append("INNER JOIN C_UOM_Trl ct ON (C_UOM.C_UOM_ID = ct.C_UOM_ID AND ct.AD_Language=?) ");
		}
		sql.append(" WHERE C_UOM.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (StringUtils.isNotEmpty(where)) {
			sql.append(where);
		}
		sql.append(" ORDER BY C_UOM.isdefault DESC, Name ");
		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(!base){
				pstmt.setString(1, Env.getAD_Language(ctx));
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt("C_UOM_ID"), rs.getString("Name")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}


	public MUOM(Properties ctx, int C_UOM_ID, String trxName,
			List<MEXMESignoVital> signos) {
		super(ctx, C_UOM_ID, trxName);
		this.signos = signos;
	}

	public MUOM(Properties ctx, int C_UOM_ID, String trxName,
			List<MEXMESignoVital> signos, String detalleGrafica) {
		super(ctx, C_UOM_ID, trxName);
		this.signos = signos;
		this.detalleGrafica = detalleGrafica;
	}

	public String getTrlName() {
		if (m_trlName == null && getC_UOM_ID()>0) {
			m_trlName = get_Translation("Name", Env.getAD_Language(getCtx()));
		}
		if (m_trlName == null){
			m_trlName = getName();
		}
		return m_trlName;
	}

	/**
	 * Metodo de motor de conversion de unidades de medida
	 * @param unidadMedida
	 * @param cantidad
	 * @return BigDecimal
	 * @autor rsolorzano
	 */
	public static BigDecimal convertirMedida(Properties ctx, int cuomID, BigDecimal cantidad, String sistemaMedicion){
		// obtenemos la unidad de medida correspondiente
		int cuomToID = MUOMConversion.getCUOMToID(ctx, cuomID, sistemaMedicion);
		// convertimos la cantidad
		//convertirMedida(ctx, cuomID, cuomToID, cantidad);
		return MUOMConversion.convert(cuomID, cuomToID, cantidad, true); 

	}
	/**
	 * Metodo de motor de converion de unidades de medida
	 * @param unidadMedida
	 * @param cantidad
	 * @return BigDecimal
	 * @autor rsolorzano
	 *
	public static BigDecimal convertirMedida(Properties ctx, int cuomID, int cuomToID, BigDecimal cantidad) {
		// convertimos la cantidad
		return MUOMConversion.convert(cuomID, cuomToID, cantidad, true);
	}*/

	/**
	 * rsolorzano 10/03/2010
	 * obtiene el sistema de medicion de la unidad de medida
	 * @param ctx
	 * @return
	 */
	public static String getSistMedicionUOM(Properties ctx, int uomID){
		return new MUOM(ctx, uomID, null).getSistemaMedicion();
	}

	private String nameConv = null;

	public String getNameConv() {
		return nameConv;
	}

	public void setNameConv(String nameConv) {
		this.nameConv = nameConv;
	}

	/**
	 * Unidad de medida por medio del X12DE355
	 * @param ctx
	 * @param value X12DE355
	 * @param trxName 
	 * @return
	 */
	public static MUOM get(Properties ctx, String value, String trxName) {
		//		StringBuilder st = new StringBuilder("select * from C_Uom where X12DE355 = ? ");
		//		MUOM unidad = null;
		//		PreparedStatement pstmt = null;
		//		ResultSet rs = null;
		//		try {
		//			pstmt = DB.prepareStatement(st.toString(), null);
		//			pstmt.setString(1, value);
		//			rs = pstmt.executeQuery();
		//			if (rs.next()) {
		//				unidad = new MUOM(ctx, rs, trxName);
		//			}
		//		} catch (Exception e) {
		//			s_log.log(Level.SEVERE, st.toString(), e);
		//		} finally {
		//			DB.close(rs,pstmt);
		//		}
		return new Query(ctx, Table_Name, " X12DE355=? ", trxName).setParameters(value).first();
	}

	/**
	 * Obtenemos la traduccion de la unidad de medida, a partir de su ID y el idioma especificado.
	 * En caso de no tener traduccion, retorna el valor de la columna Name en C_UOM
	 * @param uomID
	 * @return retVal - UOM Translated
	 */
	public static String getTranslatedUOM(int uomID, String language){
		String retVal = null;
		final StringBuilder sql = new StringBuilder(" SELECT coalesce(trl.name, uom.name) FROM c_uom uom ");
		sql.append(" LEFT JOIN c_uom_trl trl ON (trl.c_uom_id = uom.c_uom_id AND trl.ad_language =? ) ");
		sql.append(" WHERE uom.c_uom_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, language);
			pstmt.setInt(2, uomID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Obtenemos el id de la unidad de medida, a partir del nombre y el idioma especificado.
	 * @param uomName nombre de la UdM
	 * @return id del UDM
	 * @deprecated use {@link #getByName(Properties, String, String)}
	 */
	public static int getUOMIdName(String uomName, String language){
		int retVal = 0;
		//Solo deben existir UDM activas y con client_id 0; solo debera regresar un valor
		final StringBuilder sql = new StringBuilder(" SELECT coalesce(trl.c_uom_id, uom.c_uom_id) FROM c_uom uom ");
		sql.append(" LEFT JOIN c_uom_trl trl ON (trl.c_uom_id = uom.c_uom_id AND trl.ad_language =? ) ");
		sql.append(" WHERE (uom.name = ? or trl.name = ? ) AND uom.isactive = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, language);
			pstmt.setString(2, uomName);
			pstmt.setString(3, uomName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getInt(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Gets a unit of measure by name.
	 * @param ctx The application context.
	 * @param name The name of the unit of measure.
	 * @param trxName The transaction name
	 * @return a Unit of Measure element.
	 */
	public static MUOM getByName(Properties ctx, String name, String trxName) {
		MUOM retVal = null;

		Query query = null;

		//translated or not?
		if(Language.isBaseLanguage(Env.getLanguage(ctx).getAD_Language())) {
			query = new Query(ctx, Table_Name, "Name = ?", trxName).setParameters(name);
			query.setOnlyActiveRecords(true);
		} else {
			StringBuilder join = 
					new StringBuilder(" INNER JOIN C_UOM_TRL ON (C_UOM.C_UOM_ID = C_UOM_TRL.C_UOM_ID AND AD_Language = ?) ");
			query = 
					new Query(ctx, Table_Name, "C_UOM_TRL.Name = ?", trxName)
			.setJoins(join)
			.setOnlyActiveRecords(true)
			.setParameters(Env.getLanguage(ctx).getAD_Language(), name);
		}

		List<PO> results = query.list(); 

		if(!results.isEmpty()) {
			retVal = (MUOM)results.get(0); 
		}

		return retVal;
	}
	
	/**
	 * Obtenemos las UDM apartir de un producto
	 * @param product producto del que buscamos sus unidades
	 * @return
	 */
	public static List<ValueNamePair> getUOMbyProd(Properties ctx, MProduct product, String language){;
		List<ValueNamePair> lista = new ArrayList<ValueNamePair>();
		//Solo deben existir UDM activas y con client_id 0; solo debera regresar un valor
		final StringBuilder sql = new StringBuilder(" SELECT * FROM c_uom uom ");
		sql.append(" LEFT JOIN c_uom_trl trl ON (trl.c_uom_id = uom.c_uom_id AND trl.ad_language =? ) ");
		sql.append(" WHERE (uom.c_uom_id = ? or uom.c_uom_id = ? ) AND uom.isactive = 'Y'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, language);
			pstmt.setInt(2, product.getC_UOM_ID());
			pstmt.setInt(3, product.getC_UOMVolume_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MUOM uom = new MUOM(ctx, rs, null);
				lista.add(new ValueNamePair(String.valueOf(uom.getC_UOM_ID()), uom.getTrlName()));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 * valida si el name ya se utiliza en alguna udm de system
	 * @param ctx
	 * @param name
	 * @param trxName
	 * @return
	 */
	public static MUOM existsInSystem(Properties ctx, String name, String trxName){
		
		Query query = new Query(ctx, Table_Name, "Name = ? and AD_Org_ID = 0 and AD_Client_ID = 0 ", trxName).setParameters(name);
		MUOM result = (MUOM) query.first();
		
		return result;
	}
	
	/**
	 * Se obtiene la uom dependiendo del nombre, utilizando el cliente+org de forma opcional.
	 * @param ctx
	 * @param name
	 * @param trxName
	 * @param isByOrg
	 * @return
	 */
	public static MUOM getByName(Properties ctx, String name, String trxName, boolean accessLevel){
		
		MUOM result = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * FROM C_UOM uom WHERE uom.name = ? AND uom.isactive = 'Y'	");

		if(accessLevel) {
			//sql.append("AND AD_Client_ID = ? AND AD_Org_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "uom"));
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			if (rs.next()){
				result = new MUOM(ctx, rs, null);
			}
				

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getIdByValue", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return result;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		
		/**validacion para evitar names repetidos entre system y cliente**/
		MUOM uomTMP = MUOM.existsInSystem(Env.getCtx(), getName(), null); 
		
		if(uomTMP!=null && uomTMP.getC_UOM_ID()!= getC_UOM_ID()){
			log.saveError("Error", Utilerias.getAppMsg(Env.getCtx(), "error.duplicate.key")); 
			return false;
		}else{
			if(Env.getAD_Client_ID(Env.getCtx())==0 && Env.getAD_Org_ID(Env.getCtx())==0){
				uomTMP = MUOM.getByName(Env.getCtx(), getName(), null, false);
				if(uomTMP!=null && uomTMP.getC_UOM_ID()!= getC_UOM_ID()){
					log.saveError("Error", Utilerias.getAppMsg(Env.getCtx(), "msj.allergy.error")); 
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 *  Devuelve el nombre de la unidad de medida.
	 *
	 *@param  udm         Description of the Parameter
	 *@return             Un valor String con el nombre de la unidad de medida.
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      la unidad de medida.
	 */
	public static String nombreUDM(Properties ctx, long udm) throws Exception {
        String name = null;
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

        sql.append(" SELECT C_UOM.Name, uom.Name as Trad FROM C_UOM ")
        .append(" LEFT OUTER JOIN C_UOM_Trl uom ON (C_UOM.C_UOM_ID = uom.C_UOM_ID AND uom.AD_Language='")
        .append(Env.getAD_Language(Env.getCtx())).append("') ")
        .append(" WHERE C_UOM.C_UOM_ID = ? ")
		.append(" AND C_UOM.IsActive = 'Y' ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_UOM"));
        
        pstmt = DB.prepareStatement(sql.toString(), null);
        pstmt.setLong(1, udm);
		try {
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("Trad");
				if(name == null){
					name = rs.getString("Name");
				}
			} else {
				throw new Exception("No existe la unidad de medida o esta inactiva.");
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
       
        return name;
	}
}	//	MUOM
