package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEEnfControlCuest extends X_EXME_EnfControlCuest{

	private static final long serialVersionUID = -1148235538976877068L;
	 /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEnfControlCuest.class);
	
	public MEXMEEnfControlCuest(Properties ctx, int EXME_EnfControlCuest_ID,
			String trxName) {
		super(ctx, EXME_EnfControlCuest_ID, trxName);		
	}

	public MEXMEEnfControlCuest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);		
	}

	/** Obtiene el registro de cuestionario guardado
	  * @param proyecto
	  * @param cuestionario
	  * @param medico
	  * @return observaciones
	  * @throws Exception
	  * @autor rvelazquez
	  */	    
	 public static MEXMEEnfControlCuest getCuest(Properties ctx, String trxName, Integer proyecto, Integer cuestionario, Integer medico) throws Exception{
	     	
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		 MEXMEEnfControlCuest cuest = null;
			   
		 sql.append("	select ac.*")    
			 .append("	from exme_enfcontrolcuest ac")    
			 .append("	where ac.exme_proyecto_id = ?") 
			 .append("	and ac.exme_cuestionario_id = ?")    			 
			 .append("	and ac.exme_medico_id = ?");
		  
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, proyecto.intValue());
			pstmt.setInt(2, cuestionario.intValue());
			pstmt.setInt(3, medico.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cuest = new MEXMEEnfControlCuest(ctx, rs, trxName);
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEEnfControlCuest.getCuest", e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		     
		     return cuest;
		 }
}
