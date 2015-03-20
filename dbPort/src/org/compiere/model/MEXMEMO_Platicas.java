package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.mo.MO_Platica_VO;

public class MEXMEMO_Platicas extends X_EXME_MO_Platicas {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/** Static logger           */
	    private static CLogger s_log = CLogger.getCLogger(MEXMEMO_PiezaDental.class);

	    
	    /**
	     * @param ctx
	     * @param EXME_CitaMedica_ID
	     * @param trxName
	     */
	    public MEXMEMO_Platicas(Properties ctx, int MEXMEMO_PlaticasID, String trxName) {
	        super(ctx, MEXMEMO_PlaticasID, trxName);
	    }

	    /**
	     * @param ctx
	     * @param rs
	     * @param trxName
	     */
	    public MEXMEMO_Platicas(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }

	
	    /**
		 * Obtiene catalogo de platicas relacionado al paciente. 
		 *
		 * @param Integer pacienteID
		 * @return ArrayList platicasImpartidas
		 * 
		 * @throws Exception
		 */ 
	    public static ArrayList<MO_Platica_VO> getPlaticas(Integer pac) {
			
	    	ArrayList<MO_Platica_VO> platicas = new ArrayList<MO_Platica_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		    	
			 sql.append("	select")
			 	.append("	exme_mo_platicas.exme_mo_platicas_id,")
			 	.append("	exme_mo_platicas.name,")
			 	.append("	exme_mo_platicas.description,")
			 	.append("	exme_mo_platicas.value,")
			 	.append("	exme_mo_platicaspaciente.exme_mo_platicaspaciente_id,")
			 	.append("	exme_mo_platicaspaciente.exme_paciente_id,")
			 	.append("	exme_mo_platicaspaciente.exme_citamedica_id,")
			 	.append("	exme_mo_platicaspaciente.exme_mo_platicas_id")
			 	.append("	from")
			 	.append("	exme_mo_platicas")
			 	.append("	left JOIN exme_mo_platicaspaciente")
			 	.append("	ON (exme_mo_platicaspaciente.exme_mo_platicas_id=exme_mo_platicas.exme_mo_platicas_id AND") 
			 	.append("	exme_mo_platicaspaciente.exme_paciente_id = ?)")			 	
			 	.append("	inner join exme_configodonto on (exme_mo_platicas.exme_especialidad_id = exme_configodonto.exme_especialidad_id)");

			 
	    		
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	
	    	MO_Platica_VO platica  = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) { 
					
					platica  = new MO_Platica_VO();
			    				    	
					platica.setId(new Integer(rs.getInt(1)));
					platica.setName(rs.getString(2));
					platica.setDescription(rs.getString(3));
					platica.setValue(rs.getString(4));
					platica.setPlaticapacienteID(new Integer(rs.getInt(5)));
					platica.setPac(new Integer(rs.getInt(6)));
					platica.setCita(new Integer(rs.getInt(7)));
					
					
					
			    	platicas.add(platica);
			    	
				}
				
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			return platicas;
	    }
  

}
