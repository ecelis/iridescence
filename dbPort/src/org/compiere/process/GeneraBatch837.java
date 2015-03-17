package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import org.compiere.model.X_HL7_Note;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.confHL7.MessageGenerator;

public class GeneraBatch837 extends SvrProcess { 

	int pacienteId = 0;
	
	protected void prepare() {
		this.pacienteId = this.getRecord_ID();
	}
	
	private HashMap<String, String> buscaResp997() {
		HashMap<String,String> datos = new HashMap<String,String>();
		PreparedStatement pstmt = null;
        ResultSet rs = null;	
		StringBuilder sqlSelect  = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sqlSelect.append("SELECT HL7_NOTE_ID, TEXTMSG FROM HL7_NOTE WHERE PROCESS = '997' AND PROCESSED = 'N'");
        try {
			pstmt = DB.prepareStatement(sqlSelect.toString(),null);
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
	
	private String getInvoiceID() {
		StringBuilder res = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		int ap = 0;
		PreparedStatement pstmt = null;
        ResultSet rs = null;	
		StringBuilder sqlSelect  = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sqlSelect.append("SELECT C_INVOICE_ID FROM C_INVOICE WHERE ISGENERATED = 'N' ");
        try {
			pstmt = DB.prepareStatement(sqlSelect.toString(),null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (ap == 0)
					res.append(rs.getString("C_INVOICE_ID"));
				else
					res.append(","+rs.getString("C_INVOICE_ID"));
				ap++;
			}
        }	catch (Exception e) {
				e.printStackTrace();
				return null;
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
		return res.toString();
	}
	
	protected String doIt() throws Exception {
		String res = null;
		
		res = getInvoiceID();
		if (res == null) {
			return "No hay registros pendientes de generar mensaje 837P.";
		}
		MessageGenerator mg = new MessageGenerator(getCtx(), true);
		mg.generateMessage(res, "C_Invoice", null, null);
		
		//Espera un tiempo determinado por la respuesta
		Timestamp hora = DB.getTimestampForOrg(Env.getCtx());
		long horaFinal = hora.getTime() + ((1000) * 60);
		HashMap<String, String> respuesta = new HashMap<String, String>();
		
		while (Calendar.getInstance().getTimeInMillis() < horaFinal && respuesta.isEmpty()) {
			
			respuesta = this.buscaResp997();
			
			try {
				if (!respuesta.isEmpty())
					break;
				Thread.sleep(1000 * 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Calendar.getInstance().getTimeInMillis() >= horaFinal || respuesta.isEmpty()) {
			res = "No se recibio confirmacion de recepcion de reclamo.";
		} else {
			res = respuesta.get("Respuesta");
			int noteId = Integer.valueOf(respuesta.get("idReg")).intValue();
			X_HL7_Note note = new X_HL7_Note(getCtx(), noteId, null);
			note.setProcessed(true);
			note.save();
		}
		
		return res;
	}
}
