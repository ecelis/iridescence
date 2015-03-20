package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

public class MPHRPacienteCondMed extends X_PHR_PacienteCondMed{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MPHRPacienteCondMed.class);
	
	public MPHRPacienteCondMed(Properties ctx, int PHR_PacienteCondMed_ID,
			String trxName) {
		super(ctx, PHR_PacienteCondMed_ID, trxName);
	}
	
	public MPHRPacienteCondMed(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Regresa la lista de condiciones médicas de un paciente 
	 * @param ctx
	 * @param pacienteId
	 * @param active (especificar si son condiciones activas o no)
	 * @return
	 */
	public static List<MPHRPacienteCondMed> getConditions(Properties ctx, int pacienteId, boolean active){
		List<MPHRPacienteCondMed> lst = new ArrayList<MPHRPacienteCondMed>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		sb.append("SELECT * ")
		.append("FROM PHR_PACIENTECONDMED ")
		.append("WHERE PHR_PACIENTECONDMED.ISACTIVE = ? ")
		.append("AND PHR_PACIENTECONDMED.EXME_PACIENTE_ID = ? ")
		.append("ORDER BY PHR_PACIENTECONDMED.CREATED DESC ");
		
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setString(1, (active?"Y":"N")); 
			pstmt.setInt(2, pacienteId);
			result = pstmt.executeQuery();
			
			while(result.next()){
				MPHRPacienteCondMed condition = new MPHRPacienteCondMed(ctx, result, null); 
				lst.add(condition);
			}
		}catch(SQLException e){
			log.log(Level.SEVERE, "getConditions(Properties ctx, int pacienteId, boolean active) " + e.getMessage());
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
				
				if(result != null)
					result.close();
				
				pstmt = null;
				result = null;
			}catch(SQLException e){
				log.log(Level.SEVERE, "While closing pstmt and result " + e.getMessage());
			}
		}
		return lst;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getEXME_CtaPac_ID() > 0){
			if(getEXME_Paciente_ID() <= 0){
				Object obj = Utilerias.getFieldFrom(getCtx(), 
						MEXMECtaPac.Table_Name, MEXMECtaPac.COLUMNNAME_EXME_Paciente_ID, MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID, getEXME_CtaPac_ID());
				if(obj == null){
					return false;
				}
				if(obj instanceof BigDecimal)
					setEXME_Paciente_ID(((BigDecimal) obj).intValue());
				else if (obj instanceof Integer) {
					setEXME_Paciente_ID((Integer) obj);
				} else {
					setEXME_Paciente_ID(Integer.parseInt(obj.toString()));
				}
			}
			Timestamp fecha = DB.getTimestampForOrg(getCtx());
			if(getFechaDiagnostico() != null && getFechaDiagnostico().after(fecha)){
				log.saveError("Error", Utilerias.getMsg(getCtx(), "enfermeria.proc.fechaPost"));
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		success = saveDiag(newRecord);	
		return success;
	}
	private int actPacienteID = 0;
	
	public int getActPacienteID() {

		if (actPacienteID <= 0) {
			if (getEXME_CtaPac_ID() > 0) {
				MEXMEActPaciente actPac = MEXMEActPaciente.getActPaciente(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());
				if (actPac == null)
					return actPacienteID;
				actPacienteID = actPac.getEXME_ActPaciente_ID();
			}
		}
		return actPacienteID;
	}
	
	/**
	 * Save/Update diagnostic of patient
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean saveDiag(boolean newRecord){
		if (getEXME_CtaPac_ID() <= 0 || getActPacienteID() <= 0)// find actPaciente
			return true;

//		if (newRecord) { 
//			// create new actpacdiag
//			if (getEXME_Diagnostico_ID() > 0) { 
//				if(!saveDiag(0, getEXME_Diagnostico_ID()))
//					return false;
//			}
//		}
//		else {
//			if (is_ValueChanged(COLUMNNAME_EXME_Diagnostico_ID)) {
//				if(!saveDiag(get_ValueOldAsInt(COLUMNNAME_EXME_Diagnostico_ID), getEXME_Diagnostico_ID()))
//					return false;
//			}
//		}
		
		//TODO ?? Se requiere en esta tabla??
		MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), COLUMNNAME_EXME_Diagnostico_ID, null, null);
		return true;
	}
	
//	public boolean saveDiag(int diagOldID, int diagNewID){
//		// if new value is null return
//		if (diagNewID <= 0)
//			return true;// not saved
//		if(getActPacienteID() <= 0)// if new value is null return
//			return false;// not saved - mandatory value
//		MActPacienteDiag diag = null;
//		// find if already exist
//		if (diagOldID > 0){// find if already exist
//			diag = MActPacienteDiag.get(getCtx(), get_ID(), Table_ID, getActPacienteID(), diagOldID, null);
//		}
//		if(diag == null){ 
//			diag = new MActPacienteDiag(getCtx(), 0, get_TrxName());
//		}
//		diag.setAD_Table_ID(Table_ID);
//		diag.setRecord_ID(get_ID());
//		diag.setFecha_Diagnostico(getFechaDiagnostico());
//		diag.setEXME_ActPaciente_ID(getActPacienteID());
//		diag.setEXME_Diagnostico_ID(diagNewID);
//		
//		return diag.save(get_TrxName());
//	}
	public MDiagnostico getEXME_Diagnostico() {
		return new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
	}
}
