package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MPHREvento extends X_PHR_Evento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MPHREvento.class);
	
	public MPHREvento(Properties ctx, int PHR_Evento_ID, String trxName) {
		super(ctx, PHR_Evento_ID, trxName);
	}

	public MPHREvento(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Lista de Eventos del paciente
	 * @param ctx
	 * @param pacienteId
	 * @return
	 */
	public static List<MPHREvento> getAllEvents(Properties ctx, int pacienteId){
		
		List<MPHREvento> lst = new ArrayList<MPHREvento>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		sb.append("SELECT * ")
		.append("FROM PHR_EVENTO ")
		.append("WHERE  ")
		.append("PHR_EVENTO.EXME_PACIENTE_ID = ? ")
		.append("ORDER BY PHR_EVENTO.CREATED DESC ");
		
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, pacienteId);
			result = pstmt.executeQuery();
			
			while(result.next()){
				MPHREvento evento = new MPHREvento(ctx, result, null); 
				lst.add(evento);
			}
		}catch(SQLException e){
			log.log(Level.SEVERE, "getAllEvents(Properties ctx, int pacienteId) " + e.getMessage());
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
}
