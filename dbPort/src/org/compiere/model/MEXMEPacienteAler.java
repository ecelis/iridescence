package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.CreatedUpdatedInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;
import org.compiere.util.Language;

/**
 * 
 * Patient Allergies
 *
 */
public class MEXMEPacienteAler extends X_EXME_PacienteAler implements GridItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEPacienteAler.class);
	

	public MEXMEPacienteAler(Properties ctx, int pacienteAlerID, String trxName) {
		super(ctx, pacienteAlerID, trxName);
	}
	
	public MEXMEPacienteAler(Properties ctx, ResultSet rs, String trx) {
		 super(ctx, rs, trx);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param CONCEPTTYPE
	 * @return
	 */
	public static ArrayList<String> getAlergiasPacienteFDBConceptId(Properties ctx, int pacienteID, String CONCEPTTYPE) {

		ArrayList<String> list = new ArrayList<String>();

		StringBuilder sql = null;
		
		if(CONCEPTTYPE.equalsIgnoreCase("5")){			
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT distinct gd.drug_id ");
			sql.append(" FROM EXME_PacienteAler  ");
			sql.append(" inner JOIN EXME_SActiva sa ON (sa.EXME_SACTIVA_ID = EXME_PacienteAler.EXME_SActiva_ID AND sa.CONCEPTTYPE = ? ) ");
			sql.append(" inner join m_product p on  p.trade_name_id = sa.conceptid ");
			sql.append(" inner join exme_genproduct gp on gp.exme_genproduct_id = p.exme_genproduct_id ");
			sql.append(" inner join exme_gendrug gd on gd.exme_gendrug_id = gp.exme_gendrug_id ");
			sql.append(" WHERE EXME_PacienteAler.EXME_Paciente_Id = ? ");
		}else{			
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT distinct EXME_SActiva.HICSEQNO ");
			sql.append("FROM EXME_PacienteAler ");
			sql.append(" JOIN EXME_SActiva ON (EXME_PacienteAler.EXME_SACTIVA_ID = EXME_SActiva.EXME_SActiva_ID AND EXME_SActiva.CONCEPTTYPE = ? ) ");
			sql.append("WHERE EXME_PacienteAler.EXME_Paciente_Id = ? ");
			sql.append(" AND EXME_PacienteAler.ESTATUS NOT IN ('E','S')");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, CONCEPTTYPE);
			pstmt.setInt(2, pacienteID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_SACTIVA_ID
	 * @return
	 */
	public static String getAlergiasFROMsACTIVA(Properties ctx, int EXME_SACTIVA_ID) {

		String list = null;

		StringBuilder sql = null;
					
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT distinct gd.drug_id ");
			sql.append(" FROM EXME_PacienteAler  ");
			sql.append(" inner JOIN EXME_SActiva sa ON (sa.EXME_SACTIVA_ID = EXME_PacienteAler.EXME_SActiva_ID AND sa.CONCEPTTYPE = '5' ) ");
			sql.append(" inner join m_product p on  p.trade_name_id = sa.conceptid ");
			sql.append(" inner join exme_genproduct gp on gp.exme_genproduct_id = p.exme_genproduct_id ");
			sql.append(" inner join exme_gendrug gd on gd.exme_gendrug_id = gp.exme_gendrug_id ");
			sql.append(" where sa.exme_sactiva_id =  ? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_SACTIVA_ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = rs.getString(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	
	/**
	 *  Determina si el paciente es alergico o no a alguna de las sustancias
	 *  activas del producto que se le esta indicando.
	 *
	 *@param  producto       El producto indicado.
	 *@param  paciente       Description of the Parameter
	 *@return                Un valor boolean indicando si tiene o no alergia a
	 *      alguna sustancia del producto.
	 *@exception  Exception  Description of the Exception
	 */
	public static boolean esAlergico(Properties ctx, long producto, long paciente) throws Exception {
		boolean isAllergic = false;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" SELECT EXME_PacienteAler.EXME_PacienteAler_ID ");
			sql.append(" FROM EXME_PacienteAler ");
			sql.append(" INNER JOIN EXME_ProductSActiva s ON (EXME_PacienteAler.EXME_SActiva_ID=s.EXME_SActiva_ID) ");
			sql.append(" INNER JOIN M_Product           p ON (s.M_Product_ID=p.M_Product_ID) ");
			sql.append(" WHERE EXME_PacienteAler.EXME_Paciente_ID =? "); 
			sql.append(" AND   				   p.M_Product_ID     =? ");
			sql.append(" AND   EXME_PacienteAler.Estatus         <>? "); 
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			isAllergic = DB.getSQLValue(null, sql.toString(), paciente, producto, ESTATUS_Inactive) > 0;
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		return isAllergic;
	}
	
	/**
	 *  Determina si la alergia del paciente no se dio de alta previamente.
	 *
	 *@param  ctx    		 Contexto
	 *@param  allergyType    Tipo de alergia
	 *@param  allergyId       		 ID de la alergia o de la sustancia activa
	 *@param  patientId       Description of the Parameter
	 *@return                Un valor boolean indicando si tiene o no alergia a
	 *      alguna sustancia del producto.
	 *@exception  Exception  Description of the Exception
	 */
	public static boolean validaAlergia(final Properties ctx, final String allergyType, final long allergyId, final long patientId) {
		boolean isAllergic = false;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			// Defecto 228: Debido al constraint de BD, no validar registros activos.
			sql.append(" SELECT EXME_PacienteAler.EXME_PacienteAler_ID ");
			sql.append(" FROM EXME_PacienteAler ");
			sql.append(" WHERE EXME_PacienteAler.EXME_Paciente_ID =?  "); // 1 -patientId
			if (X_EXME_Alergia.TIPOALERGIA_DrugAllergy.equals(allergyType)) {
				sql.append(" AND   EXME_PacienteAler.EXME_SActiva_ID  =? ");// 2 -allergyId
			} else {
				sql.append(" AND   EXME_PacienteAler.EXME_Alergia_ID  =? ");// 2 -allergyId
			}
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name)); // El constraint no valida cliente/org
			isAllergic = DB.getSQLValue(null, sql.toString(), patientId, allergyId) > 0;
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		return isAllergic;
	}

	private String alergia = null;
	private IDColumn idColumn = null; 
	private MSActiva sActiva = null; 
	private MEXMEAlergia allergy = null;
	private String sactivaName = null;
	
	/** @return the name of the allergy, whether is a Drug or other. */
	public String getAlleryName() {
		String retValue;
		if (getEXME_SActiva_ID() > 0) {
			retValue = getSactivaName();
		} else if (getEXME_Alergia_ID() > 0) {
			retValue = getAlergia();
		} else {
			retValue = StringUtils.EMPTY;
		}
		return retValue;
	}
	 
	public String getSactivaName() {
		if(sactivaName == null && getEXME_SActiva_ID() > 0){
			sactivaName = getsActiva().getName();
		}
		return sactivaName;
	}

	public void setSactivaName(String sactivaName) {
		this.sactivaName = sactivaName;
	}
	 
	public MEXMEAlergia getAllergy() {
		if(allergy == null && getEXME_Alergia_ID() > 0){
			allergy = new MEXMEAlergia(getCtx(), getEXME_Alergia_ID(), get_TrxName());
		}
		return allergy;
	}
	public void setAllergy(MEXMEAlergia m_allergy) {
		this.allergy = m_allergy;
	}
	
	public MSActiva getsActiva() {
		if(sActiva == null && getEXME_SActiva_ID() > 0){
			sActiva = new MSActiva(getCtx(), getEXME_SActiva_ID(), get_TrxName());
		}
		return sActiva;
	}

	public String getAlergia() {
		if(alergia == null && getEXME_Alergia_ID() > 0){
			alergia = getAllergy().getName();
		}
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	@Deprecated
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getEXME_PacienteAler_ID());
		}
		return idColumn;
	}
	@Deprecated
	public String[] getColumns() {
		return new String[]{};
	}
	 

	/**
	 *  Regresa el historico de alergias
	 *
	 *@param  ctx    		 Contexto
	 *@param  ID       		 ID de la alergia o de la sustancia activa
	 *@param  paciente       Description of the Parameter
	 *@return  List<MEXMEPacienteAler>
	 *@exception  Exception  Description of the Exception
	 */
	public static List<MEXMEPacienteAler> getHistoria(Properties ctx, int patientID){
		return getHistoria(ctx, patientID, null);
	}
	public static List<MEXMEPacienteAler> getHistoria(Properties ctx, int patientID, String whereClause, Object...params){
		return getHistoria(ctx, patientID, false, whereClause, params);
	}
	
	public static List<MEXMEPacienteAler> getHistoria(Properties ctx, int patientID, boolean derechoHabiente, String whereClause, Object...params){
		List<MEXMEPacienteAler> historia = new ArrayList<MEXMEPacienteAler>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
		sql.append(" SELECT PA.*, ")
		.append(" SA.NAME AS SActiveName, ALER.NAME AS Alergia, ALER.ALERGENICO AS Alergenico, ALER.TIPOALERGIA AS TipoAlergiaMto, PROD.NAME AS Platillo ")
		.append(" FROM EXME_PACIENTEALER PA ")
		.append(" LEFT JOIN EXME_SACTIVA SA ON PA.EXME_SACTIVA_ID = SA.EXME_SACTIVA_ID ")
		.append(" LEFT JOIN EXME_ALERGIA ALER ON PA.EXME_ALERGIA_ID = ALER.EXME_ALERGIA_ID ")
		.append(" LEFT JOIN M_PRODUCT PROD ON ALER.M_PRODUCT_ID = PROD.M_PRODUCT_ID ")
		.append(" WHERE PA.EXME_Paciente_ID =  ?")
		.append(whereClause == null ? "" : whereClause);
		//Card #1545 ProMujer 
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "PA"));
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, "PA"));
		}
		
		sql.append(" ORDER BY PA.CREATED DESC");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, patientID);
			if (params != null && params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEPacienteAler obj = new MEXMEPacienteAler(ctx, rs, null);
				obj.setSactivaName(rs.getString("SActiveName"));
				obj.setAlergia(rs.getString("Alergia"));
				historia.add(obj);
			} 
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}        
        return historia;
	}
	
	/**
	 *  Regresa el historico de alergias, sin las alergias de tipo erroneas
	 *
	 *@param  ctx    		 Contexto
	 *@param  ID       		 ID de la alergia o de la sustancia activa
	 *@param  paciente       Description of the Parameter
	 *@return  List<MEXMEPacienteAler>
	 *@exception  Exception  Description of the Exception
	 */
	public static List<MEXMEPacienteAler> getHistAlergia(Properties ctx, int patientID){
        return getHistoria(ctx, patientID, " AND trim(PA.ESTATUS)=? ", X_EXME_PacienteAler.ESTATUS_ConfirmedOrVerified);
	}
	
	/**
	 * Alergias del Paciente
	 * 
	 * @param ctx  Contexto
	 * @param pacId App
	 * @return Lista en forma de String de las alergias
	 */
	public static String getAllergies(Properties ctx, int pacId) {
		List<MEXMEPacienteAler> alergias = MEXMEPacienteAler.getHistAlergia(ctx, pacId);

		List<String> str = new ArrayList<String>();

		for (MEXMEPacienteAler aler : alergias) {
			if (aler.getSactivaName() != null) {
				str.add(aler.getSactivaName());
			}
		}

		return StringUtils.join(str, ",");
	}
	/**
	 * Obtiene la reaccion del paciente a la sustancia activa del producto.
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param M_Product_ID
	 * @return
	 * @author lhernandez
	 */
	public static List<MEXMEPacienteAler> getReaccionSustActiva(Properties ctx, int EXME_Paciente_ID, int M_Product_ID) {

		List<MEXMEPacienteAler> reacciones = new ArrayList<MEXMEPacienteAler>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT pa.EXME_PacienteAler_ID, pa.EXME_SActiva_ID, sa.name as sActiva, pa.reaccion, aler.EXME_Alergia_ID, aler.name as alergia, aler.alergenico   ")
		.append("FROM EXME_PacienteAler pa  ")
		.append("LEFT JOIN EXME_SActiva sa ON (pa.EXME_SActiva_ID = sa.EXME_SActiva_ID)  ")
		.append("LEFT JOIN EXME_ProductSActiva psa ON (sa.EXME_SActiva_ID = psa.EXME_SActiva_ID)  ")
		.append("LEFT JOIN EXME_Alergia aler ON (pa.EXME_Alergia_ID = aler.EXME_Alergia_ID)  ")
		.append("WHERE pa.exme_paciente_id = ? AND (psa.M_PRoduct_ID = ? OR aler.M_Product_ID = ?) ")
		//.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pa"))
		;

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, M_Product_ID);
			pstmt.setInt(3, M_Product_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MEXMEPacienteAler pAler = new MEXMEPacienteAler(ctx, rs, null);
				pAler.setSactivaName(rs.getString("sActiva"));
				pAler.setAlergia(rs.getString("alergia"));
//				pAler.setAlergenico(rs.getString("alergenico"));				
				reacciones.add(pAler);
			}
		
		} catch (Exception e) {
			s_log.log(Level.SEVERE, " MEXMEPacienteAler.getReaccionSustActiva " + sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}
		
		return reacciones;
	}	
	
	/**
	 * Obtenemos una lista con las alergias de un paciente
	 * 
	 * @param paciente El paciente a obtener sus alergias
	 * @return La lista con las sustancias activas a las que es alergico
	 *         el paciente
	 */
	public static List<BasicoTresProps> getAlergias (Properties ctx, int paciente, String trxName) {
        final List<BasicoTresProps> retValue = new ArrayList<BasicoTresProps>();
        final boolean isBaseLang=Language.isBaseLanguage (Env.getAD_Language(ctx));
        final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT EXME_SActiva.EXME_SActiva_ID, ");
        sql.append(" CASE WHEN ").append(isBaseLang ? "r." : "tr.").append("Name IS NOT NULL ");
        sql.append(" THEN EXME_SActiva.Name||' ('||").append(isBaseLang ? "r." : "tr.").append("Name||')' ");
        sql.append(" ELSE EXME_SActiva.Name END AS Name, ");
		sql.append("EXME_PacienteAler.Description, EXME_PacienteAler.EXME_PacienteAler_ID ");
		sql.append("FROM EXME_SActiva ");
		sql.append("INNER JOIN EXME_PacienteAler ON (EXME_SActiva.EXME_SActiva_ID = EXME_PacienteAler.EXME_SActiva_ID) ");
		sql.append("LEFT JOIN ad_ref_list r ON (EXME_PacienteAler.severidad=r.value AND r.ad_reference_id=? AND r.value<>?) ");
		if(!isBaseLang) {
			sql.append("LEFT JOIN ad_ref_list_trl tr ON (r.ad_ref_list_id=tr.ad_ref_list_id AND tr.AD_Language=?) ");
		}
		sql.append("WHERE EXME_SActiva.IsActive='Y' ");
		sql.append("AND EXME_PacienteAler.IsActive='Y' ");
		sql.append("AND EXME_PacienteAler.EXME_Paciente_ID=? ");
		sql.append("AND trim(EXME_PacienteAler.Estatus)<>? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_SActiva.Name");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            int idx=0;
            pstmt.setInt(++idx, SEVERIDAD_AD_Reference_ID);
            pstmt.setString(++idx, SEVERIDAD_Unknow);
            if(!isBaseLang){
            	  pstmt.setString(++idx, Env.getAD_Language(ctx));
            }
            pstmt.setInt(++idx, paciente);
            pstmt.setString(++idx, ESTATUS_Inactive);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                final BasicoTresProps alergias = new BasicoTresProps();
                alergias.setId(rs.getLong("EXME_SActiva_ID"));
                alergias.setNombre(rs.getString("Name"));
                alergias.setDescripcion(rs.getString("Description"));
                alergias.setValue(rs.getString("EXME_PacienteAler_ID"));
                retValue.add(alergias);
            }
            
		}catch (SQLException e) {
			s_log.log(Level.SEVERE, "getAlergias"+sql.toString(), e);
		}finally {
        	DB.close(rs, pstmt);
        }

        return retValue;
    }   

	/**
	 * 
	 * @param ctx
	 * @param conceptid
	 * @return
	 */
	public static String getAlergiasPacienteFDBConceptId(Properties ctx, int conceptid) {

		String drugId = null;

		StringBuilder sql = null;
		
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT distinct gd.drug_id ");
			sql.append(" FROM EXME_SActiva sa ");
			sql.append(" inner join m_product p on p.trade_name_id = sa.conceptid ");
			sql.append(" inner join exme_genproduct gp on gp.exme_genproduct_id = p.exme_genproduct_id ");
			sql.append(" inner join exme_gendrug gd on gd.exme_gendrug_id = gp.exme_gendrug_id ");
			sql.append(" WHERE sa.CONCEPTTYPE = '5' AND sa.conceptid = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_SActiva.Table_Name, "sa"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, conceptid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				drugId = rs.getString(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return drugId;
	}
	
	/**
	 * Trae informacion de usuarios que crearon y/o actualizaron el registro de la alergia
	 * 
	 * @param ctx
	 * @param Exme_PacienteAler_ID
	 * @return
	 * @throws Exception
	 */
	public static String getInfoAlergia(Properties ctx, int Exme_PacienteAler_ID) throws Exception {
		CreatedUpdatedInfo infoAler = new CreatedUpdatedInfo();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT ")
		.append("EXME_PacienteAler.EXME_PacienteAler_ID AS Paciente_Aler_ID, EXME_PacienteAler.CreatedBy AS Created_By, EXME_PacienteAler.UpdatedBy AS Updated_By, ")
		.append("TO_CHAR(EXME_PacienteAler.Created, 'dd/mm/yyyy hh24:mi') AS Fecha_C, TO_CHAR(EXME_PacienteAler.Updated, 'dd/mm/yyyy hh24:mi') AS Fecha_U, ")
		.append("medC.nombre_med AS Med_C, medU.nombre_med AS med_u, moC.AD_User_ID AS Med_Org_C, moU.AD_User_ID As Med_Org_U, ")
		.append("asiC.Name AS Asi_C, asiU.Name AS Asi_U, Enfc.nombre_enf AS Enf_C, Enfu.nombre_enf AS Enf_U ")
		.append("FROM EXME_PacienteAler ")
		.append("LEFT JOIN EXME_Medico_Org moC ON (moC.AD_User_ID = EXME_PacienteAler.CreatedBy AND moC.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND moC.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Medico_Org moU ON (moU.AD_User_ID = EXME_PacienteAler.UpdatedBy AND moU.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND moU.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Asistente asiC ON (asiC.AD_User_ID = EXME_PacienteAler.CreatedBy AND asiC.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND asiC.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Asistente asiU ON (asiU.AD_User_ID = EXME_PacienteAler.UpdatedBy AND asiU.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND asiU.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Enfermeria enfC ON (enfC.AD_User_ID = EXME_PacienteAler.CreatedBy AND enfC.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND enfC.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Enfermeria enfU ON (enfU.AD_User_ID = EXME_PacienteAler.UpdatedBy AND enfU.AD_Client_ID =  EXME_PacienteAler.AD_Client_ID AND enfU.AD_Org_ID = EXME_PacienteAler.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Medico medC ON medC.EXME_Medico_ID = moC.EXME_Medico_ID ")
		.append("LEFT JOIN EXME_Medico medU ON medU.EXME_Medico_ID = moU.EXME_Medico_ID ")
		.append("WHERE EXME_PacienteAler.IsActive = 'Y' AND EXME_PacienteAler.EXME_PacienteAler_ID = ? ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" ORDER BY EXME_PacienteAler.CreatedBy");


		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Exme_PacienteAler_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				infoAler.setCreatedBy(rs.getInt(2));
				infoAler.setUpdatedBy(rs.getInt(3));
				infoAler.setDateCreated(rs.getString(4));
				infoAler.setDateUpdated(rs.getString(5));
				infoAler.setMedCreated(rs.getString(6));
				infoAler.setMedUpdated(rs.getString(7));
				infoAler.setAsiCreated(rs.getString(10));
				infoAler.setAsiUpdated(rs.getString(11));
				infoAler.setEnfCreated(rs.getString(12));
				infoAler.setEnfUpdated(rs.getString(13));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getInfoAlergia", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return infoAler.getCreatedUpdatedInfo(ctx);
	}
	
	/**
	 * Busca las severidades de las alergias a medicamentos del paciente.
	 * Y regresa el color hexadecimal de la severidad mayor.
	 * Card: http://control.ecaresoft.com/card/617/
	 * @param ctx
	 * @param pacienteId
	 * @return Color Hexadecimal (e.g. #c0c0c0)
	 * @author Lama
	 */
	public static String getSeverityColor(Properties ctx, int pacienteId) {
		// concatenamos los valores de las alergias de paciente de tipo SActiva,
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(distinct pa.severidad||':#'||r.colorhex)||',' ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" array_to_string(ARRAY_AGG(distinct pa.severidad||':#'||r.colorhex), ',')||',' ");
		} else {
			sql.append(" null ");
		}
		sql.append(" as color ");
		sql.append("FROM exme_pacientealer pa ");
		sql.append("INNER JOIN ad_ref_list r ON (pa.severidad=r.value ");
		sql.append(" AND r.ad_reference_id=? AND r.value<>? AND r.colorhex IS NOT NULL) ");
		sql.append("WHERE pa.exme_sactiva_id IS NOT NULL ");
		sql.append("AND pa.exme_paciente_id=? ");
		sql.append("AND pa.estatus NOT IN (?,?) ");
		final String str = DB.getSQLValueString(null, sql.toString(),
		// parameters
			SEVERIDAD_AD_Reference_ID, SEVERIDAD_Unknow, pacienteId, ESTATUS_Erroneous, ESTATUS_Inactive);

		final String status;
		if (StringUtils.isNotBlank(str)) {
			if (str.contains(SEVERIDAD_Severe + ":")) {
				status = StringUtils.substringBetween(str, SEVERIDAD_Severe + ":", ",");
			} else if (str.contains(SEVERIDAD_Moderate + ":")) {
				status = StringUtils.substringBetween(str, SEVERIDAD_Moderate + ":", ",");
			} else if (str.contains(SEVERIDAD_Mild + ":")) {
				status = StringUtils.substringBetween(str, SEVERIDAD_Mild + ":", ",");
			} else {
				status = "";
			}
		} else {
			status = "";
		}
		return status;
	}
}
