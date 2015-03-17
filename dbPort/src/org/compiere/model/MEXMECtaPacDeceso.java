/**
 * 
 */
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

/**
 * Modelo de la tabla CtaPacDeceso datos de la muerte del paciente
 * 
 * @author Lorena Lama
 * Se comentan codigo agregado para manejo de modelo PO desde un jsp de struts
 */
public class MEXMECtaPacDeceso extends X_EXME_CtaPacDeceso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755220249092977148L;
	private static CLogger s_log = CLogger.getCLogger(MEXMECtaPacDeceso.class);
	
	/**
	 * @param ctx
	 * @param EXME_CtaPacDeceso_ID
	 * @param trxName
	 */
	public MEXMECtaPacDeceso(Properties ctx, int EXME_CtaPacDeceso_ID, String trxName) {
		super(ctx, EXME_CtaPacDeceso_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPacDeceso(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Copia el deceso a partir de un objeto origen
	 * (llena los datos existentes del objeto de origen)
	 * @param object
	 * @return
	 */
	public static MEXMECtaPacDeceso copyFrom(MEXMECtaPacDeceso object) throws Exception {
		MEXMECtaPacDeceso newObject = new MEXMECtaPacDeceso(object.getCtx(), 0, object.get_TrxName());
		copyValues(object, newObject);

		if (object.m_paciente != null) // paciente
			newObject.setPaciente(0, object.m_paciente);

		if (object.m_ctaPac != null) // cta paciente
			newObject.setCtaPac(0, object.m_ctaPac);

		return newObject;
	}
	
	//ctapac
//	private String ctaPacDoc = null;
	private MEXMECtaPac m_ctaPac = null;
	//paciente
//	private String pacienteValue = null;
//	private String pacienteName = null;
	private MEXMEPaciente m_paciente = null;
	
	/**
	 * Paciente
	 * @param pacienteID
	 * @param paciente
	 * @throws Exception
	 */
	public void setPaciente(int pacienteID, MEXMEPaciente paciente) throws Exception {
		if (paciente == null) {
			if (pacienteID > 0)
				paciente = new MEXMEPaciente(getCtx(), pacienteID, null);
			else
				throw new Exception("error.find.paciente");
		}
		this.m_paciente = paciente;
//		setPacienteName(paciente.getNombre_Pac());
//		setPacienteValue(paciente.getValue());
	}
	
	
	public static String getCauseOfDeath(Properties ctx, int EXME_Paciente) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		final StringBuilder causes = new StringBuilder();
		try {
			sql.append("SELECT * FROM EXME_ActPacienteDiag  ");
			sql.append(" WHERE isActive='Y' AND ad_table_id=? ");
			sql.append(" AND record_id in ( ");
			sql.append("                   SELECT cd.EXME_CtaPacDeceso_ID FROM EXME_CtaPacDeceso cd ");
			sql.append("                   INNER JOIN EXME_CtaPac cp ON (cd.EXME_CtaPac_ID=cp.EXME_CtaPac_ID) ");
			sql.append("                   WHERE cd.isActive='Y' AND cd.Estatus='1' ");
			sql.append(" 	                 AND cp.isActive='Y' AND cp.EXME_Paciente_ID=?");
			sql.append("                   ) ");
			sql.append(" AND estatus <> 'I' ");
			sql.append(" ORDER BY created ");
			//NOTA: Como es de paciene, no agregar AccessLevel 

			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, Table_ID);
			psmt.setInt(2, EXME_Paciente);
			rs = psmt.executeQuery();

			while (rs.next()) {
				final String temp = rs.getString("diagnosis");
				final String diag = StringUtils.substringAfter(temp, " - ");
				if (causes.length() > 0) {
					causes.append(", ");
				}
				causes.append(StringUtils.isBlank(diag) ? temp : diag);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		return causes.toString();

	}
	/**
	 * Cuenta Paciente
	 * @param ctapacID
	 * @param ctapac
	 * @throws Exception
	 */
	public void setCtaPac(int ctapacID, MEXMECtaPac ctapac) throws Exception {
		if (ctapac == null) {
			if (ctapacID > 0)
				ctapac = new MEXMECtaPac(getCtx(), ctapacID, null);
			else
				throw new Exception("error.find.ctapac");
		}
		this.m_ctaPac = ctapac;
		setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
//		setCtaPacDoc(ctapac.getDocumentNo());
		if (m_paciente == null)
			this.setPaciente(0, ctapac.getPaciente());
	}

	/**
	 * Obtiene la informacio a partir de la cuenta paciente
	 * @param ctx 
	 * @param EXME_CtaPac_ID
	 * @param newObj  - true: regresa un objeto instanciado ID = 0, si no encuentra el registro para la Cta Pac.
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPacDeceso getOfCtaPac(Properties ctx, int EXME_CtaPac_ID, boolean newObj, String trxName){
		List<MEXMECtaPacDeceso> lst = getOfCtaPac(ctx, EXME_CtaPac_ID, newObj, trxName, new StringBuffer(" AND COALESCE(estatus,'1') = ? "), ESTATUS_Active);
		if(lst.isEmpty()){
			return null;
		}
		return lst.get(0);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param newObj
	 * @param trxName
	 * @param where
	 * @param param
	 * @return
	 */
	public static List<MEXMECtaPacDeceso> getOfCtaPac(Properties ctx, int EXME_CtaPac_ID, boolean newObj, String trxName, StringBuffer where, Object... param){
		List<MEXMECtaPacDeceso> retValue = new ArrayList<MEXMECtaPacDeceso>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM EXME_CtaPacDeceso WHERE EXME_CtaPac_ID = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			if(where != null){
				sql.append(where);
			}
			sql.append(" ORDER BY created DESC ");
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, EXME_CtaPac_ID);
			
			for (int i = 0; i < param.length; i++) {
				DB.setParameter(psmt, i+2, param[i]);
			}
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MEXMECtaPacDeceso ctapac = new MEXMECtaPacDeceso(ctx, rs, trxName);
				ctapac.setCtaPac(EXME_CtaPac_ID, null);
				retValue.add(ctapac);
			}
			
			if (retValue.isEmpty() && newObj) {
				retValue.add(new MEXMECtaPacDeceso(ctx, 0, trxName));
				retValue.get(0).setCtaPac(EXME_CtaPac_ID, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return retValue;

	}
	
	/*** valores de lista no obligatorios, cambia "" por null ***
	
	public void setMortMaternaTiempo(String MortMaternaTiempo) {
		if (MortMaternaTiempo != null && MortMaternaTiempo.equals(""))
			MortMaternaTiempo = null;
		super.setMortMaternaTiempo(MortMaternaTiempo);
	}

	public void setMortMaternaCausa(String MortMaternaCausa) {
		if (MortMaternaCausa != null && MortMaternaCausa.equals(""))
			MortMaternaCausa = null;
		super.setMortMaternaCausa(MortMaternaCausa);
	}

	public void setTipoCtaPac(String TipoCtaPac) {
		if (TipoCtaPac != null && TipoCtaPac.equals(""))
			TipoCtaPac = null;
		super.setTipoCtaPac(TipoCtaPac);
	}

	public void setIntoxEtilica(String IntoxEtilica) {
		if (IntoxEtilica != null && IntoxEtilica.equals(""))
			IntoxEtilica = null;
		super.setIntoxEtilica(IntoxEtilica);
	}
	
	public void setCitaDe(String CitaDe) {
		if (CitaDe == null || CitaDe.equals(""))
			;
		else
			super.setCitaDe(CitaDe);
	}*/

	public MEXMECtaPac getM_ctaPac() {
		return m_ctaPac;
	}
	
//	public String getCtaPacDoc() {
//		return ctaPacDoc;
//	}
//
//	public void setCtaPacDoc(String ctaPacDoc) {
//		this.ctaPacDoc = ctaPacDoc;
//	}
//
//	public String getPacienteValue() {
//		return pacienteValue;
//	}
//
//	public void setPacienteValue(String pacienteValue) {
//		this.pacienteValue = pacienteValue;
//	}
//
//	public String getPacienteName() {
//		return pacienteName;
//	}
//
//	public void setPacienteName(String pacienteName) {
//		this.pacienteName = pacienteName;
//	}
	
	public boolean complete(List<BasicoTresProps> diags, String trxName) {
		setEstatus(X_EXME_CtaPacDeceso.ESTATUS_Active);
		setCitaDe(X_EXME_CtaPacDeceso.CITADE_FirstTime);
		if (!save(trxName)) {
			return false;
		}
		final MEXMEActPaciente[] lst = getM_ctaPac().getActividades(false);
		if (lst.length <= 0) {
			return false;
		}
		final MEXMEActPaciente act = lst[0];
		for (BasicoTresProps btp : diags) {
			if (StringUtils.isBlank(btp.getValue()) && btp.getId() <= 0) {
				continue;
			}
			if (!MActPacienteDiag.saveDiagnostic(this, //
				act.getEXME_ActPaciente_ID(), //
				btp.getNombre(),//
				(int) btp.getId(),//
				btp.getValue(),//
				null)) {
				return false;
			}
		}
		final MEXMEPaciente paciente = getM_ctaPac().getPaciente();
		if (paciente.getFecha_Muerte() == null || getFecha_Muerte().compareTo(paciente.getFecha_Muerte()) != 0) {
			paciente.setFecha_Muerte(getFecha_Muerte());
			paciente.setCreateHL7_A08(true);//Card #1231
			return paciente.save(trxName);
		}
		return true;
	}
	
	public String cancel(String trxName) throws Exception{
		
		setEstatus(X_EXME_CtaPacDeceso.ESTATUS_Canceled);
		if (!save(trxName)) {
			return "Error al cancelar el deceso";// "error.deceso.cancel"
		}
		// itera
		for (MActPacienteDiag diag : getDiagnosis()) {
			diag.setEstatus(MActPacienteDiag.ESTATUS_Inactive); // ? isActive = 'N' no los muestra
			// guardar
			if (!diag.save()) {
				return "Error al cancelar el diagnostico";//"error.decesoDiag.cancel"
			}
		}
		// quitarle la fecha de muerte al paciente
		final MEXMEPaciente paciente = getM_ctaPac().getPaciente();
		if (paciente.getFecha_Muerte() != null) {
			paciente.setFecha_Muerte(null);
			paciente.setCreateHL7_A08(true);//Card #1231
			if (!paciente.save(trxName)) {
				return "error.pacExt.noUpdatePac";
			}
		}
		return null;
	}
	
	
	public List<MActPacienteDiag> getDiagnosis() {
		return MActPacienteDiag.get(getCtx(), getEXME_CtaPacDeceso_ID(), I_EXME_CtaPacDeceso.Table_ID, 0, get_TrxName());
	}
	
	/** MessageGenerator: Create message ADT A08  (Card #1231) */
	public void createHl7() {
		if(m_ctaPac != null && m_ctaPac.getPaciente().isCreateHL7_A08()) {
			getM_ctaPac().getPaciente().createHl7();
		}
	}

}
