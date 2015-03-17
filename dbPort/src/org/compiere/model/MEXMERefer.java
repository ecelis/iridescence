package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 * 
 * @author Lorena Lama
 *
 */
public class MEXMERefer extends X_EXME_Refer {

	private static CLogger		slog = CLogger.getCLogger (MEXMERefer.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MEXMERefer(Properties ctx, int EXME_Refer_ID, String trxName) {
		super(ctx, EXME_Refer_ID, trxName);
	}

	public MEXMERefer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MEXMEPaciente m_paciente = null;
	
	
	/**
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERefer> getPacientes(Properties ctx, String whereClause, boolean contrarefer, String trxName){
		
		List<MEXMERefer> retValue = new ArrayList<MEXMERefer>();
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			sql.append("SELECT EXME_Refer.* FROM EXME_Refer ")
				.append(" INNER JOIN EXME_Paciente  ON (EXME_Refer.EXME_paciente_ID = EXME_PACIENTE.EXME_paciente_ID ) ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," WHERE ","EXME_Refer"));
			
			if(whereClause != null)
				sql.append(whereClause);
			
			
			sql.append("AND EXME_Refer.Ref_EXME_Refer_ID IS").append(contrarefer ? " NOT NULL " : " NULL ")
				.append(" ORDER BY EXME_Refer.documentNo DESC ");
			
			psmt = DB.prepareStatement(sql.toString(),null);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				MEXMERefer refer = new MEXMERefer(ctx,rs,trxName);
//				String label = null;
//				label = refer.getEstatusName();
//				refer.setEstatusLbl(label);
				retValue.add(refer);
//				refer = null;
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, psmt);
		}
		
		return retValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public MEXMEPaciente getPaciente(){
		
		if( m_paciente == null  && getEXME_Paciente_ID() > 0){
			m_paciente = new MEXMEPaciente(getCtx(),getEXME_Paciente_ID(),get_TrxName());
		}
		
		return m_paciente;
	}
	
	private MOrg m_org = null;
	
	
	/**
	 * 
	 * @return
	 */
	public MOrg getOrgEnv(){
		
		if( m_org == null  && getAD_OrgEnv_ID() > 0){
			m_org = new MOrg(getCtx(),getAD_OrgEnv_ID(),get_TrxName());
		}
		
		return m_org;
	}
	
	/**
	 * 
	 * @return
	 */
//	public String getEstatusName() {
//		String estatusName = null;
//		if (getEstatus() != null) {
//			estatusName = MRefList.getListName(getCtx(), ESTATUS_AD_Reference_ID, getEstatus());
//		}
//
//		return estatusName;
//	}
	
	
//	private String estatusLbl = null;
//	
//	public String getEstatusLbl() {
//		return estatusLbl;
//	}
//
//	public void setEstatusLbl(String estatusLbl) {
//		this.estatusLbl = estatusLbl;
//	}
	
	/**
	 * 
	 * @return
	 */
//	public String getDiagnostico() {
//		try {
//			if (getEXME_Diagnostico_ID() > 0)
//				return MDiagnostico.getDiagnosticoName(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, "Error", e);
//		}
//		return null;
//	}
	
	/**
	 * 
	 * @return
	 */
//	public String getEstatusCita() {
//		String statusCita = null;
//		if (m_cita == null && getEXME_Paciente_ID()> 0)
//			m_cita = getCitaFromPacient(getCtx(), getEXME_Paciente_ID(), " AND EXME_CitaMedica.AD_Org_ID = " + getAD_OrgRec_ID(), get_TrxName());
//		
//		if (m_cita != null) {
//			statusCita = m_cita.isConfirmada() ? "IP" : "DR";
//			statusCita = m_cita.getEstatus().equals("6") ? "CO" : statusCita;
//		}
//
//		return statusCita;
//	}
	
//	private MEXMECitaMedica m_cita = null;
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 *
	public static MEXMECitaMedica getCitaFromPacient(Properties ctx, int EXME_Paciente_ID, String whereClause, String trxName){
		
		MEXMECitaMedica retValue = null;
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			sql.append("SELECT EXME_CitaMEdica.* FROM EXME_CitaMedica ")
				.append(" INNER JOIN EXME_Paciente  ON ( EXME_CitaMedica.EXME_paciente_ID = EXME_PACIENTE.EXME_paciente_ID ) ")
				
				//.append(MEXMELookupInfo.addAccessLevelSQL(ctx," WHERE ","EXME_CitaMedica"))
				
				.append(" WHERE EXME_CitaMedica.isActive = 'Y' ")
				.append(" AND EXME_CitaMedica.Estatus <> '5' ")
				.append(" AND UPPER(EXME_CitaMedica.description) = 'REFER' ")
				.append(" AND EXME_PACIENTE.EXME_paciente_ID = ? ")
				.append(" AND EXME_CitaMedica.AD_Client_ID = ? ");
			
			if(whereClause != null)
				sql.append(whereClause);
			
			psmt = DB.prepareStatement(sql.toString(),null);
			psmt.setInt(1,EXME_Paciente_ID);
			psmt.setInt(2,Env.getContextAsInt(ctx, "#AD_Client_ID"));
			rs = psmt.executeQuery();
			
			if(rs.next()){
				retValue = new MEXMECitaMedica(ctx,rs,null);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, psmt);
		}
		
		return retValue;
	}*/
	
//	private boolean visible = false;
//
//
//	public boolean isVisible() {
//		return visible;
//	}
//
//	public void setVisible(boolean visible) {
//		this.visible = visible;
//	}
//	
	
//	public MEXMECitaMedica getCita() {
//		if (m_cita == null && getEXME_Paciente_ID()> 0)
//			m_cita = getCitaFromPacient(getCtx(), getEXME_Paciente_ID(), " AND EXME_CitaMedica.AD_Org_ID = " + getAD_OrgEnv_ID(), get_TrxName());
//		
//		return m_cita;
//	}
	
//	public static int isPacRefer(Properties ctx, int EXME_Paciente_ID, String trxName, boolean isLastRefer){
//		
//		int retValue = 0;
//		
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		
//		try {
//			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//			if(isLastRefer){
//				sql.append("SELECT EXME_REFER_ID from(select EXME_REFER_ID from EXME_REFER ")
//				.append("WHERE EXME_PACIENTE_ID = ? ORDER BY EXME_Refer_ID desc) WHERE ROWNUM = 1");
//			}else{//Trae el exme_refer_id actual para el paciente- Lizeth de la Garza
//				sql.append("select EXME_REFER_ID from EXME_REFER where EXME_PACIENTE_ID = ? and ISACTIVE = 'Y' ");
//			}
//		
//			psmt = DB.prepareStatement(sql.toString(),null);
//			psmt.setInt(1,EXME_Paciente_ID);
//			rs = psmt.executeQuery();
//			
//			if(rs.next()){
//				retValue = rs.getInt(1);
//			}
//			
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, "Error", e);
//		} finally {
//			DB.close(rs, psmt);
//		}
//		
//		return retValue;
//	}
	
	/**
	 * Lizeth de la Garza - Metodo que devuelve el �ltimo estatus de referencia para el paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return String estatus
	 */
	
	/*public static String getEstatusRefer(Properties ctx, int EXME_Paciente_ID, String trxName){
		String estatus = null;
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
				sql.append("SELECT estatus FROM ")
				.append("(SELECT estatus FROM EXME_Refer ")
				.append("WHERE EXME_Paciente_id = ? ORDER BY exme_refer_id desc)WHERE rownum = 1");
				
			psmt = DB.prepareStatement(sql.toString(),null);
			psmt.setInt(1,EXME_Paciente_ID);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				estatus = rs.getString(1);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, psmt);
		}
		return estatus;
	}
	*/
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		success = saveDiag(newRecord);	
		return success;
	}
	
	/**
	 * Save/Update diagnostic of patient
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean saveDiag(boolean newRecord) {
		if (getEXME_Paciente_ID() <= 0 || getActPacienteID() <= 0)// find actPaciente
			return false;

//		int diagOldID = is_ValueChanged(COLUMNNAME_EXME_Diagnostico_ID) ? get_ValueOldAsInt(COLUMNNAME_EXME_Diagnostico_ID) : 0;
		int diagNewID = newRecord || is_ValueChanged(COLUMNNAME_EXME_Diagnostico_ID) ? getEXME_Diagnostico_ID() : 0;

		if (diagNewID <= 0) // if new value is null return
			return true;// not saved

		return MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), COLUMNNAME_EXME_Diagnostico_ID, null, null);
		
	}
	
	private int actPacienteID = 0;

	public int getActPacienteID() {

		if (actPacienteID <= 0) {
			if(getEXME_ActPaciente_ID() <= 0)
				createAct();
			else
				actPacienteID = getEXME_ActPaciente_ID();
		}
		return actPacienteID;
	}

	/**
	 * Creates an activity of patient
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean createAct(){
		if (getEXME_Paciente_ID() <= 0 && getEXME_Especialidad_ID() <= 0)
			return false;
		
		MEXMEActPaciente actPac = new MEXMEActPaciente(getCtx(), 0, get_TrxName());
		actPac.setEXME_Paciente_ID(getEXME_Paciente_ID());
		actPac.setEXME_Especialidad_ID(getEXME_Especialidad_ID());
		actPac.setTipoArea(Env.getTipoArea(getCtx()));
		actPac.setFecha(DB.getTimestampForOrg(Env.getCtx()));
		actPac.setName(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_EXME_Refer_ID));
		
		if(!actPac.save()){
			log.log(Level.SEVERE, "EXME_ActPaciente.save" );
			return false;
		}		
		actPacienteID = actPac.getEXME_ActPaciente_ID();
		
		return true;
	}
	
	
}
