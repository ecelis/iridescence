package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;

import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEVistaElegibilidad;
import org.compiere.model.X_HL7_Note;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.confHL7.MessageGenerator;

public class SolicitaEleg extends SvrProcess { 

	private static CLogger s_log = CLogger.getCLogger (SolicitaEleg.class);
	
	int pacienteId = 0;
	int EXME_VistaElegibilidad_ID = 0;
	MEXMEVistaElegibilidad velig = null;
	
	protected void prepare() {
		this.EXME_VistaElegibilidad_ID = this.getRecord_ID();
	}
	
	private int buscaEleg(int idPac, int C_BPartner_ID) {
		int res = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;	
		StringBuilder sqlSelect  = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sqlSelect.append("SELECT COUNT(*) as TOTAL FROM EXME_VISTAELEGIBILIDAD WHERE EXME_PACIENTE_ID = ? AND C_BPartner_ID = ? AND ISEXPIRED = 'N' ");
        try {
			pstmt = DB.prepareStatement(sqlSelect.toString(),null);
			pstmt.setInt(1, idPac);
			pstmt.setInt(2, C_BPartner_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt("TOTAL");
			}
        }	catch (Exception e) {
				e.printStackTrace();
			} finally {
	            try {
	                if (rs != null)
	                    rs.close();
	                if (pstmt != null)
	                    pstmt.close();
	            } catch (SQLException e) {
	            }
	            rs = null;
	            pstmt = null;
	        }
		return res;
	}
	
	private HashMap<String, String> buscaResp270(int idVista) {
		HashMap<String,String> datos = new HashMap<String,String>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;	
		StringBuilder sqlSelect  = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sqlSelect.append("SELECT N.HL7_NOTE_ID, N.TEXTMSG ")
        		 .append("FROM HL7_NOTE N ")
        		 .append("WHERE N.RECORD_ID = ? ")
        		 .append("AND PROCESS = '271' AND PROCESSED = 'N'");
        try {
			pstmt = DB.prepareStatement(sqlSelect.toString(),null);
			pstmt.setInt(1, idVista);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				datos.put("idReg", new String(rs.getString("HL7_NOTE_ID")));
				datos.put("Respuesta", new String(rs.getString("TextMsg")));
			}
        }	catch (Exception e) {
				e.printStackTrace();
				datos.clear();
				return datos;			
			} finally {
	            try {
	                if (rs != null)
	                    rs.close();
	                if (pstmt != null)
	                    pstmt.close();
	            } catch (SQLException e) {
	            }
	            rs = null;
	            pstmt = null;
	        }
		return datos;
	}
	
	protected String doIt() throws Exception {
		String res = null;
		velig = new MEXMEVistaElegibilidad(getCtx(), EXME_VistaElegibilidad_ID, null);
		pacienteId = velig.getEXME_Paciente_ID();
		
		// Registra el AD_Org_ID del logueo en el registro del paciente - sirve para reconocimiento del lugar desde donde fue solicitada la elegibilidad
		Trx m_trx = Trx.get(Trx.createTrxName("saveAD_Org_Elid"), true);
		String trxName = m_trx.getTrxName();
		boolean success = true;
		try {
			MEXMEPaciente pac = new MEXMEPaciente(getCtx(), pacienteId, trxName);
			pac.setAD_Org_Elig_ID(Env.getAD_Org_ID(getCtx()));
			if (!pac.save(trxName)) {
				success = false;
			}
		} catch (Exception ex) {
			s_log.log(Level.WARNING, "saveAD_Org_Elid", ex);
		} finally {
			if (success) {
				m_trx.commit();
			} else {
				m_trx.rollback();
			}
		}
		
		if (buscaEleg(pacienteId, velig.getC_BPartner_ID()) > 0) {
			res = Msg.getMsg(getCtx(), "Elig_NoExpired");
			
			this.getProcessInfo().setFlagInfo(true);
			this.getProcessInfo().setMsgDialog(res);
			
			addLog(res);			
			return res;
			//return "La elegibilidad ya se ha solicitado en un lapso de 24 horas. Esta disponible en la pesta#a Beneficios.";
		}
		
		MessageGenerator mg = new MessageGenerator(getCtx(), true);
		mg.generateMessage(EXME_VistaElegibilidad_ID, "EXME_VistaElegibilidad");
		
		//Espera un tiempo determinado por la respuesta
		Timestamp hora = DB.getTimestampForOrg(getCtx());
		long horaFinal = hora.getTime() + ((2000) * 60);
		HashMap<String, String> respuesta = new HashMap<String, String>();
		
		while (Calendar.getInstance().getTimeInMillis() < horaFinal && respuesta.isEmpty()) {
			
			respuesta = this.buscaResp270(EXME_VistaElegibilidad_ID);
			
			try {
				if (!respuesta.isEmpty())
					break;
				Thread.sleep(1000 * 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Calendar.getInstance().getTimeInMillis() >= horaFinal || respuesta.isEmpty()) {
			res = Msg.getMsg(getCtx(), "Elig_NoResponse");

			this.getProcessInfo().setFlagError(true);
			this.getProcessInfo().setMsgDialog(res);
			
			addLog(res);
			return res;
			//res = "No se recibio respuesta de elegibilidad de la aseguradora.";
		} else {
			res = respuesta.get("Respuesta");
			addLog(res);
			
			int noteId = Integer.valueOf(respuesta.get("idReg")).intValue();
			X_HL7_Note note = new X_HL7_Note(getCtx(), noteId, null);
			note.setProcessed(true);
			note.save();
			
			this.getProcessInfo().setFlagInfo(true);
			this.getProcessInfo().setMsgDialog(res);
		}
		
		res = null;
		
		return res;
	}
}
