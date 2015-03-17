package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

public class MEXMETipoPaciente extends X_EXME_TipoPaciente {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MEXMETipoPaciente.class);
	
	/**
	 * constructor
	 * @param ctx
	 * @param EXME_TipoPaciente_ID
	 * @param trxName
	 */
	public MEXMETipoPaciente(final Properties ctx, final int EXME_TipoPaciente_ID, final String trxName) {
		super(ctx, EXME_TipoPaciente_ID, trxName);
	}

	/**
	 * constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETipoPaciente(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene los Tipos de Paciente (LabelValueBean) 
	 * (todos los activos)
	 * ordenados por nombre
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated use {@link KeyNamePair} instead {@link #getList(Properties)}
	 */
	public static List<LabelValueBean> getTiposPaciente(final Properties ctx){

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<LabelValueBean> tipoPacienteLista = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			sql.append(" SELECT EXME_TIPOPACIENTE.EXME_TIPOPACIENTE_ID, EXME_TIPOPACIENTE.NAME  ");
			sql.append("  FROM EXME_TIPOPACIENTE ");
			sql.append(" WHERE EXME_TIPOPACIENTE.IsActive = 'Y' ");
			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_TIPOPACIENTE.NAME ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				final LabelValueBean resultado = new LabelValueBean(rs.getString(2), rs.getString(1));
				tipoPacienteLista.add(resultado);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMETipoPaciente.getTiposPaciente",e);
		} finally {
			DB.close(rs, pstmt);
		}
		return tipoPacienteLista;
	}
	
	/**
	 * Obtiene los Tipos de Paciente (todos los activos)
	 * ordenados por nombre
	 * @param ctx
	 * @return
	 */
	public static List<KeyNamePair> getList(final Properties ctx) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_TipoPaciente.EXME_TipoPaciente_ID, EXME_TipoPaciente.NAME  ");
		sql.append("  FROM EXME_TipoPaciente ");
		sql.append(" WHERE EXME_TipoPaciente.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_TipoPaciente.NAME ");
		return Query.getListKN(sql.toString(), null);
	}
	
	/**
	 * Todos los tipos de paciente por tipo de area
	 * @param ctx
	 * @param areaType
	 * @return
	 */
	public static MEXMETipoPaciente getPatientTypeByAreaType(final Properties ctx, final String areaType) {
		return MEXMETipoPaciente.getPatientTypeByAreaType(ctx, areaType, null);
	}
	
	/**
	 * Obtiene Todos los tipos de paciente por tipo de area y que esten activos
	 * 
	 * @param ctx el contexto de la aplicacion
	 * @param areaType el tipo de area
	 * @param trxName el nombre de la transaccion
	 * @return el objeto MEXMETipoPaciente.
	 * @author jcantu.
	 */
	public static MEXMETipoPaciente getPatientTypeByAreaType(final Properties ctx, final String areaType, final String trxName) {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		MEXMETipoPaciente patientType = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			
//			sql.append(" SELECT EXME_TIPOPACIENTE.*  ");
//			sql.append(" FROM EXME_TIPOPACIENTE ");
//			sql.append(" WHERE EXME_TIPOPACIENTE.IsActive = 'Y' ");
//			sql.append(" AND EXME_TIPOPACIENTE.TipoArea = ? ");
//			
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setString(1, areaType);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {				
//				patientType = new MEXMETipoPaciente(ctx, rs, null);				
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "MEXMETipoPaciente.getPatientTypeByAreaType",e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, " EXME_TIPOPACIENTE.TipoArea=? ", trxName)//
			.setOnlyActiveRecords(true)//
			.setParameters(areaType)//
			.addAccessLevelSQL(true)//
			.first();
	}

	/**
	 * Copiar valores de system a la organizacion
	 * 
	 * @param ctx el contexto de la aplicacion
	 * @param trxName la transaccion actual
	 * @author jcantu Modificado por Jesus Cantu el 14 de Junio del 2012
	 */
	public static void copyFromSystem(final Properties ctx, final String trxName) {
		final StringBuilder sql = new StringBuilder("select * from EXME_TipoPaciente where isActive = ? and AD_Client_ID = ? and AD_Org_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMETipoPaciente tpn = new MEXMETipoPaciente(ctx, 0, trxName);
				final MEXMETipoPaciente tp = new MEXMETipoPaciente(ctx, rs, trxName);
				PO.copyValues(tp, tpn);
				/*No es necesario concatenar nada ya ya que se modificaron los indices de la tabla.
				 *Para que sean CLIENTE-ORG-Name y CLIENTE-ORG-VALUE
				 *Los registros ya estan regionalizados en la BD de MX al Español y en Ingles en la de USA.
				 */
				//tpn.setName(tpn.getName() + " " + MSetup.abc(new MOrg(ctx, Env.getAD_Org_ID(ctx), trxName).getName()));
				tpn.save();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMETipoPaciente.copyFromSystem = " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Obtiene todos los Tipos de Paciente
	 * por nivel de acceso
	 * ordenados por nombre
	 * @param ctx
	 * @param trxName
	 * @return Regresa el listado de los objetos MEXMETipoPaciente
	 * @throws Exception 
	 */
	public static List<MEXMETipoPaciente> get(final Properties ctx){
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT *  ");
//		sql.append("  FROM EXME_TIPOPACIENTE ");
//		sql.append(" WHERE EXME_TIPOPACIENTE.IsActive = 'Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		sql.append(" ORDER BY EXME_TIPOPACIENTE.NAME ");
//		return MEXMETipoPaciente.get(ctx, sql.toString(), null);
		return new Query(ctx, Table_Name, null, null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_TIPOPACIENTE.NAME ")//
			.list();
	}
	
	/**
	 * Ejecuta el query
	 * @param ctx
	 * @param sql
	 * @param params
	 * @return Regresa el listado de los objetos MEXMETipoPaciente
	 */
	public static List<MEXMETipoPaciente> get(final Properties ctx, final String sql, final List<?> params) {
		final List<MEXMETipoPaciente> tipoPacienteLista = new ArrayList<MEXMETipoPaciente>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tipoPacienteLista.add(new MEXMETipoPaciente(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMETipoPaciente.get = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return tipoPacienteLista;
	}

	/**
	 * Todos los tipos de paciente por tipo de area
	 * @param ctx
	 * @param tipoAreas
	 * @return
	 */
	public static List<MEXMETipoPaciente> get(final Properties ctx, final String tipoAreas){
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT *  ");
//		sql.append("  FROM EXME_TIPOPACIENTE ");
//		sql.append(" WHERE EXME_TIPOPACIENTE.IsActive = 'Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		sql.append(" AND EXME_TIPOPACIENTE.TipoArea IN (");
//		sql.append(tipoAreas);
//		sql.append(") ORDER BY EXME_TIPOPACIENTE.NAME ");
//		return MEXMETipoPaciente.get(ctx, sql.toString(), null);
		return new Query(ctx, Table_Name, new StringBuilder(" EXME_TIPOPACIENTE.TipoArea IN (").append(tipoAreas).append(") ").toString(), null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_TIPOPACIENTE.NAME ")//
			.list();
	}
	
	/**
	 * Value, Name Patient type list
	 * @param ctx
	 * @return List<ValueNamePair>
	 */
	public static List<ValueNamePair> getAll(final Properties ctx){
//		final List<ValueNamePair> array = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		sql.append("SELECT Value, Name FROM exme_tipopaciente WHERE isactive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
		
//		try{
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()){
//				array.add(new ValueNamePair(rs.getString(MEXMETipoPaciente.COLUMNNAME_Value), rs.getString(MEXMETipoPaciente.COLUMNNAME_Name)));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
//		}finally {
//			DB.close(rs, pstmt);
//		}		
//		return array;
		return Query.getListVN(sql.toString(), null);
	}
	/**
	 * Discharge summary
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Discharge summary
	 */
	public static List<DischargeSummaryReportData> getDischargeSummaryRepor(){
		final List<DischargeSummaryReportData> values = new ArrayList<DischargeSummaryReportData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		try {
			final StringBuilder reporte = new StringBuilder(SQLFinancialRpts.dischargeSummary());
			pstm = DB.prepareStatement(reporte.toString(), null);
			rs = pstm.executeQuery();
//			values.addAll(createListgetDischargeSummaryRepor(rs));
			while(rs.next()){
				final DischargeSummaryReportData rValues = new DischargeSummaryReportData();
				rValues.setPacType(rs.getString("PACTYPE"));
				rValues.setPacTypeValue(rs.getString("PACVALUE"));
				rValues.setValue(rs.getInt("VALUE"));
				values.add(rValues);
			}

		}catch(Exception e){
			log.log(Level.SEVERE, e.getMessage(), e);
			
		}finally {
			DB.close(rs,pstm);
		}
		return values;
	}
//	/**
//	 * Discharge summary
//	 * @return lst of values a mostrar en el reporte Discharge summary
//	 */
//	public static ArrayList<DischargeSummaryReportData> createListgetDischargeSummaryRepor(final ResultSet rs){
//		ArrayList<DischargeSummaryReportData> lst = new ArrayList<DischargeSummaryReportData>();
//		try{
//			while(rs.next()){
//				DischargeSummaryReportData rValues = new DischargeSummaryReportData();
//				rValues.setPacType(rs.getString("PACTYPE"));
//				rValues.setPacTypeValue(rs.getString("PACVALUE"));
//				rValues.setValue(rs.getInt("VALUE"));
//				lst.add(rValues);
//			}
//		}catch(Exception e){
//			log.log(Level.SEVERE, e.getMessage(), e);
//		}
//		return lst;
//	}
	
	/**
	 * Obtiene los Tipos de Paciente Todos los activos y que no tengan place of
	 * service
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @return Regresa el listado de los objetos MEXMETipoPaciente
	 */
	public static List<MEXMETipoPaciente> getTiposPacienteNotPOS(final Properties ctx, final String trxName) {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		final List<MEXMETipoPaciente> tipoPacienteLista = new ArrayList<MEXMETipoPaciente>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			sql.append(" SELECT * ");
//			sql.append(" FROM EXME_TIPOPACIENTE ");
//			sql.append(" WHERE EXME_TIPOPACIENTE.IsActive = 'Y' AND EXME_TIPOPACIENTE.EXME_POS_ID IS NULL ");
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				tipoPacienteLista.add(new MEXMETipoPaciente(ctx, rs, null));
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "MEXMETipoPaciente.getTiposPacienteNotPOS", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return tipoPacienteLista;
		return new Query(ctx, Table_Name, " EXME_TIPOPACIENTE.EXME_POS_ID IS NULL ", trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_TIPOPACIENTE.NAME ")//
			.list();
	}
	
	/**
	 * Obtiene el Billtype del TipoPaciente
	 * 
	 * @return Regresa un objeto MEXMETypeOfBill
	 */
	public MEXMETypeOfBill getBillType() {
		final MEXMETypeOfBill billType = new MEXMETypeOfBill(getCtx(), getEXME_TypeOfBill_ID(), null);
		return billType;
	}
	
	/**
	 * After Save
	 * 
	 * @param newRecord
	 * @param success
	 * @return success
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
		// Copy the place of service to accounts in case they don't have it
		// Se agregan las correctas validaciones.- Lama
		if (MEXMEPOS.isUpdateAfterSavePOS(success, this)) {
			final List<MEXMECtaPac> lista = MEXMECtaPac.getCuentaPacienteNotPOS(getCtx(), getEXME_TipoPaciente_ID(), null);
			for (MEXMECtaPac cuenta : lista) {
				cuenta.setEXME_POS_ID(getEXME_POS_ID());
				if (!cuenta.save(get_TrxName())) {
					log.severe(cuenta.getDocumentNo()+" "+cuenta.toString());
				}
			}
		}

		return success;
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		boolean retValue = true;
		if (retValue) {
			// Copy the place of service from the organization in case it doesn't have it
			// Se agregan las correctas validaciones .- Lama
			MEXMEPOS.setPOSId(this, MOrg.Table_Name);
		}
		return retValue;
	}

}
