package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;


public class MEstudiosOBX extends X_EXME_EstudiosOBX {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEstudiosOBX.class);

	public MEstudiosOBX(Properties ctx, int EXME_EstudiosOBX_ID, String trxName) {
		super(ctx, EXME_EstudiosOBX_ID, trxName);
	}

	public MEstudiosOBX(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	
	/**
     * Obtenemos una lista con los datos correspondientes a las indicaciones
     * prescritas
     * 
     * @param actPac
     *                   Description of the Parameter
     * @param tipo
     *                   Description of the Parameter
     * @return Una lista con las indicaciones prescritas
     * @exception Exception
     *                         Description of the Exception
     */
	public static boolean hasOBX(Properties ctx, int actPacInd) throws Exception {
		boolean hasOBX = false;
		if (actPacInd > 0) {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT Count(*) obxCount FROM EXME_EstudiosOBX obx ");
			sql.append("WHERE isActive='Y' ");// Lama
			sql.append("AND obx.EXME_ActPacienteInd_ID=? ");
			// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEstudiosOBX.Table_Name));
			hasOBX = DB.getSQLValue(null, sql.toString(), actPacInd) > 0;
		}
		return hasOBX;
	}
	
	/**
	 * Gets the studies of a patient activity
	 * 
	 * @param ctx
	 *            Context
	 * @param actPacIndId
	 *            Patient Activity Indication
	 * @param discardNoNum
	 *            if true discard no numerical data
	 * @param trxName
	 * @return List<MEstudiosOBX>
	 */
	public static List<MEstudiosOBX> getEstudios(Properties ctx, int actPacIndId, boolean discardNoNum, String trxName) {
		//Properties ctx, int actPacIndId, boolean onlyActives, boolean discardNoNum, String trxName, String whereClause, boolean isReferral
		//return getEstudios(ctx, actPacIndId, true, discardNoNum, trxName, null, false);
		return getEstudios(ctx, actPacIndId, discardNoNum, false, trxName);
	}
	
	/**
	 * Gets the studies of a patient activity
	 * 
	 * @param ctx
	 *            Context
	 * @param actPacIndId
	 *            Patient Activity Indication
	 * @param discardNoNum
	 *            if true discard no numerical data
	 * @param isReferral
	 *            medical referral
	 * @param trxName
	 * @return List<MEstudiosOBX>
	 */
	public static List<MEstudiosOBX> getEstudios(Properties ctx, int actPacIndId, boolean discardNoNum, boolean isReferral, String trxName) {
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_EstudiosOBX ");
		sql.append("WHERE isActive = ? ");//Lama
		sql.append("AND EXME_ActPacienteInd_ID = ? ");
		if (!isReferral) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		}		
		sql.append(" ORDER BY Created");
		final List<MEstudiosOBX> lista = new ArrayList<MEstudiosOBX>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, DB.TO_STRING(true));
			pstmt.setInt(2, actPacIndId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEstudiosOBX eobx = new MEstudiosOBX(ctx, rs, trxName);
				if (discardNoNum) {
					if (StringUtils.isNumeric(eobx.getObservation())) {
						lista.add(eobx);
					}
				} else {
					lista.add(eobx);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lista;
//		return getEstudios(ctx, actPacIndId, true, discardNoNum, trxName, null, isReferral);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEstudiosOBX> getFromCtaPac(Properties ctx, int EXME_CtaPac_ID, String trxName){
		final List<MEstudiosOBX> lista = new ArrayList<MEstudiosOBX>();
		final StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select EXME_EstudiosObx.* from exme_estudiosobx ")
		.append(" inner join exme_actpacienteind apac on EXME_EstudiosObx.exme_actpacienteind_id = apac.exme_actpacienteind_id ")
		.append(" inner join exme_actpacienteindh apach on apac.exme_actpacienteindh_id = apach.exme_actpacienteindh_id ")
		.append(" inner join exme_ctapac cta on apach.exme_ctapac_id = cta.exme_ctapac_id")     
		.append(" WHERE EXME_EstudiosObx.isActive = 'Y' ")//Lama
		.append(" AND EXME_EstudiosObx.codeid > 0 AND cta.exme_ctapac_id = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEstudiosOBX list = new MEstudiosOBX(ctx, rs, trxName);
				lista.add(list);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}
	
	/**
	 * Obtiene los estudios desde un Paciente ID
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEstudiosOBX> getFromPac(Properties ctx, int EXME_Paciente_ID, String trxName){
		final List<MEstudiosOBX> lista = new ArrayList<MEstudiosOBX>();
		final StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		sql.append("SELECT DISTINCT eobx.* FROM EXME_EstudiosObx eobx ")
		.append("INNER JOIN EXME_ActPacienteInd apac ON eobx.EXME_ActPacienteInd_ID = apac.EXME_ActPacienteInd_ID ")
		.append("INNER JOIN EXME_ActPacienteIndH apach ON apac.EXME_ActPacienteIndH_ID = apach.EXME_ActPacienteIndH_ID ")
		.append("INNER JOIN EXME_CtaPac cp ON apach.EXME_CtaPac_ID  = cp.EXME_CtaPac_ID ")     
		.append("INNER JOIN EXME_ActPaciente ap ON cp.EXME_CtaPac_ID= ap.EXME_CtaPac_ID ")
		.append("INNER JOIN EXME_Paciente pac ON ap.EXME_Paciente_ID = pac.EXME_Paciente_ID ")
		.append("WHERE eobx.isActive = 'Y' AND eobx.CodeID > 0 AND eobx.EXME_EstudiosObx_ID IS NOT NULL AND pac.EXME_Paciente_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEstudiosOBX list = new MEstudiosOBX(ctx, rs, trxName);
				lista.add(list);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}
	
	public String getResultStatusStr() {
		return MRefList.getListName(getCtx(), RESULTSTATUS_AD_Reference_ID, super.getResultStatus());
	}

	public String getAbnormalFlagStr() {
		return MRefList.getListName(getCtx(), ABNORMALFLAGS_AD_Reference_ID, super.getAbnormalFlags());
	}

	private MEXMELoinc	loinc;

	public MEXMELoinc getLoinc() {
		if (loinc == null && SYSTEMCODE_LOINC.equals(getSystemCode()) && getCodeID() > 0) {
			loinc = new MEXMELoinc(getCtx(), getCodeID(), null);
		}
		return loinc;
	}

	public String getLoincString() {
		final StringBuilder str = new StringBuilder();
		str.append(getCodeOBX());
		if (getLoinc() != null && StringUtils.isNotEmpty(loinc.getLong_Common_Name())) {
			str.append(" ");
			str.append(loinc.getLong_Common_Name());
		}
		return str.toString();
	}
	
//	/**
//  * Obtenemos una lista con los datos correspondientes a las indicaciones
//  * prescritas
//  * 
//  * @param actPac
//  *                   Description of the Parameter
//  * @param tipo
//  *                   Description of the Parameter
//  * @return Una lista con las indicaciones prescritas
//  * @exception Exception
//  *                         Description of the Exception
//  */
//	public static List<OBXView> getListaOBX(Properties ctx, int actPacInd) {
//		final List<OBXView> lista = new ArrayList<OBXView>();
//		
//		final StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
//
//		 sql.append("SELECT obx.codeObx,") 
//		    .append(" obx.Observation,")
//		    .append(" obx.RangeReference,")
//		    .append(" obx.SystemCode,")
//		    .append(" DECODE(u.name, NULL, obx.UOMValue, u.name) UOMValue")
//		    .append(" FROM EXME_EstudiosOBX obx")
//		    .append(" LEFT JOIN C_UOM u ON u.c_uom_id = obx.c_uom_id")
//		    .append(" WHERE obx.isActive = 'Y' ")//Lama
//			.append(" AND obx.EXME_ActPacienteInd_ID = ? ")
//		 	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEstudiosOBX.Table_Name, "obx"));    
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, actPacInd);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final OBXView obx = new OBXView();
//				obx.setCodeObx(rs.getString("codeObx"));
//				obx.setObservation(rs.getString("Observation"));
//				obx.setRangeReference(rs.getString("RangeReference"));
//				obx.setSystemCode(rs.getString("SystemCode"));
//				obx.setUOMValue(rs.getString("UOMValue"));
//				lista.add(obx);
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getListaOBX", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lista;
//	}

//	/**
//	 * saveObx guarda un OBX a partir del bean OBXView (Interfaz)
//	 * ctx Properties
//	 * trxName transaccion
//	 * bean Bean
//	 * ind  ActPacienteInd 
//	 * return salvado exitoso o no
//	 */
//	public static boolean saveOBXList(final Properties ctx, final String trxName, final OBXView bean, MEXMEActPacienteInd ind ){
//		
//		int seqNo = DB.getSQLValue(trxName, "SELECT max(COALESCE(seqNo,0))+1 " +
//				"FROM EXME_EstudiosOBX WHERE EXME_ActPacienteInd_ID=?", ind.get_ID());
//		
//		MEstudiosOBX obx = new MEstudiosOBX(ctx, 0, null);
//		MEXMELoinc loinc = MEXMELoinc.getFromValue(ctx, bean.getCodeObx(), trxName);
//		
//		if (loinc != null) {
//		   obx.setCodeID(loinc.getEXME_Loinc_ID());
//		}else{
//		   obx.setCodeID(0);
//		}
//		obx.setSequence(++seqNo * 10);
//		obx.setResultStatus(bean.getResultStatus());
//		
//		obx.setSystemCode(bean.getSystemCode());
//		obx.setCodeOBX(bean.getCodeObx());
//		obx.setObservation(bean.getObservation());
//		obx.setRangeReference(bean.getRangeReference());
//		obx.setObservationDate(bean.getObservationDate());
//		
//		obx.setDateApproved(bean.getDateApproved());
//		obx.setEXME_ActPacienteInd_ID(ind.get_ID());
//		// Si el almacen llego por interfaz vacio se obtienen los datos 
//		// De la ventana Warehouse Locator. 
//		obx.setLabName(StringUtils.isEmpty(bean.getLabName()) 
//				? ind.getEXME_ActPacienteIndH().getM_Warehouse().getName() 
//						: bean.getLabName());
//		obx.setLabAddress(StringUtils.isEmpty(bean.getLabAddress()) 
//				? ind.getWarehouseLocation()
//						:bean.getLabAddress());
//		if (bean.getLabAddressID()>0){
//			obx.setC_Location_ID(bean.getLabAddressID());
//		}
//		
//		obx.setEXME_ActPacienteInd_ID(ind.getEXME_ActPacienteInd_ID());
//		
//		MEXMEspecimenCondicion cond = MEXMEspecimenCondicion.getCondicion(ctx, bean.getSpecimenCond(), trxName);
//	    if (cond != null){
//	    	ind.getEXME_ActPacienteIndH().setEXME_EspecimenCondicion_ID(cond.getEXME_EspecimenCondicion_ID());
//	    }
//		if (bean.getUOMID()>0){
//			int cUOMID = MUOM.getUOMID(bean.getUOMValue());
//			if (cUOMID > 0){
//				obx.setC_UOM_ID(cUOMID);
//			}
//			obx.setUOMValue(bean.getUOMValue());
//		}
//		return (obx.save(trxName));
//	}
	
}
