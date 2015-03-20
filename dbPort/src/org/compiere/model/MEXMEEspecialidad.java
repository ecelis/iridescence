/*
 * Created on 29-abr-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 * @author gisela lee
 * @modified lorena lama
 */
public class MEXMEEspecialidad extends X_EXME_Especialidad {
    
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEspecialidad.class);
	
    /**
     * @param ctx
     * @param EXME_Especialidad_ID
     * @param trxName
     */
    public MEXMEEspecialidad(Properties ctx, int EXME_Especialidad_ID, String trxName) {
        super(ctx, EXME_Especialidad_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEEspecialidad(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
	/**
	 * Listado de especialidad activas con nivel de acceso
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param trxName
	 *            Trx
	 * @return Listado, puede ser vacio, nunca nulo
	 */
	public static List<KeyNamePair> getList(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Especialidad.EXME_Especialidad_ID, EXME_Especialidad.Name ");
		sql.append(" FROM EXME_Especialidad ");
		sql.append(" WHERE EXME_Especialidad.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" ORDER BY EXME_Especialidad.name ");
		return Query.getListKN(sql.toString(), trxName);

	}

	/**
	 * Deprecado
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @deprecated use @link
	 *             {@link MEXMEEspecialidad#getList(Properties, String)}
	 */
	public static List<LabelValueBean> get(Properties ctx, String trxName) {

		List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Especialidad.EXME_Especialidad_ID, EXME_Especialidad.Name ");
		sql.append(" FROM EXME_Especialidad ");
		sql.append(" WHERE EXME_Especialidad.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" ORDER BY EXME_Especialidad.EXME_Especialidad_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean esp = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_Especialidad_ID"));
				list.add(esp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}
		return list;

	}
    
    public final static int AMBOS = 0;
    public final static int DOCTOR = 1;
    public final static int ENFERMERA = 2;
    

    /**
     * Devolvemos una lista de especialidades.
     * @param ctx
     * @param blankLine
     * @param trxName
     * @return
     * @throws SQLException
     * @deprecated use @link
	 *             {@link MEXMEEspecialidad#getList(Properties, String)}
     */
    public static ArrayList<LabelValueBean> getLstEspecialidad(Properties ctx, boolean blankLine, int type, Boolean isDefault, String trxName) throws SQLException {
    	StringBuffer sql = new StringBuffer();
    	ArrayList<LabelValueBean> lstEspec = null;

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
    		sql.append (" SELECT EXME_Especialidad.EXME_Especialidad_ID, EXME_Especialidad.Name ")
    		.append("  FROM EXME_Especialidad ")
    		.append(" WHERE IsActive = 'Y' ");
    		
			if (isDefault != null) {
				sql.append(" and isDefault = ? ");
			}
    		
			switch (type) {
			case DOCTOR:
				sql.append(" AND EXME_Especialidad.isMed = 'Y' ");
				break;
			case ENFERMERA:
				sql.append(" AND EXME_Especialidad.isMed = 'N' and EXME_Especialidad.isEnf = 'Y' ");
				break;
			default:
				break;
			}
    		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
    		.append(" ORDER BY Name ");

    		pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (isDefault != null) {
				pstmt.setString(1, DB.TO_STRING(isDefault));
			}
    		rs = pstmt.executeQuery();

    		lstEspec = new ArrayList<LabelValueBean>();
    		
    		if (blankLine)
    			lstEspec.add(new LabelValueBean("", "0"));

    		while(rs.next()) {
    			LabelValueBean lvb = 
    				new LabelValueBean(
    						rs.getString("Name"),
    						String.valueOf(rs.getInt("EXME_Especialidad_ID"))
    				);
    			lstEspec.add(lvb);
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return lstEspec;

    }
    
//    public static ArrayList<LabelValueBean> getLstSubEspecialidades(Integer especialidad, Integer medico) throws SQLException {
//    	StringBuffer sql = new StringBuffer();
//    	ArrayList<LabelValueBean> lstEspec = null;
//
//    	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//
//    	try {
//    		
//    	 sql.append ("	SELECT esp.EXME_Especialidad_ID, esp.Name ")
//    		.append ("	FROM EXME_Especialidad esp")
//    		.append ("	INNER join EXME_MedicoEsp medesp on (esp.EXME_Especialidad_ID = medesp.EXME_Especialidad_ID)")
//    		.append ("	WHERE esp.IsActive = 'Y'") 
//    		.append ("	AND medesp.EXME_Medico_ID = ?")
//    		.append ("	AND esp.Ref_EXME_Especialidad_ID = ?")
//    		.append ("	ORDER BY esp.Name ");
//
//    		pstmt = DB.prepareStatement(sql.toString(), null);
//    		pstmt.setInt(1, medico.intValue());
//    		pstmt.setInt(2, especialidad.intValue());
//    		
//    		rs = pstmt.executeQuery();
//
//    		lstEspec = new ArrayList<LabelValueBean>();
//    	
//    		while(rs.next()) {
//    			LabelValueBean lvb = 
//    				new LabelValueBean(
//    						rs.getString("Name"),
//    						String.valueOf(rs.getInt("EXME_Especialidad_ID"))
//    				);
//    			lstEspec.add(lvb);
//    		}
//
//    	} catch (Exception e) {
//    		s_log.log(Level.SEVERE, sql.toString(), e);
//    	}finally {
//    		DB.close(rs,pstmt);
//    	}
//    	
//    	lstEspec.add(0, new LabelValueBean(" ","0"));
//		
//    	return lstEspec;
//
//    }
	
    public static String getLstSubEspecialidadesID_String(Integer especialidad) throws SQLException {
    	StringBuffer sql = new StringBuffer();
    	StringBuilder subespecialidades= new StringBuilder();

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	try {
    		
    	 sql.append ("	SELECT esp.EXME_Especialidad_ID, esp.Name ")
    		.append ("	FROM EXME_Especialidad esp")
    		.append ("	WHERE esp.IsActive = 'Y'") 
    		.append ("	AND esp.Ref_EXME_Especialidad_ID = ?")
    		.append ("	ORDER BY esp.Name ");

    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setInt(1, especialidad.intValue());
    		
    		rs = pstmt.executeQuery();
            int aux = 0;
    		while(rs.next()) {
    			if(aux == 0){
    				subespecialidades.append(rs.getInt("EXME_Especialidad_ID"));
    				aux++;
    			}else{
    				subespecialidades.append(", ").append(rs.getInt("EXME_Especialidad_ID"));
    			}
    			
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return subespecialidades.toString();

    }
	
	private List<MEXMECitaMedicaView> appointments = new ArrayList<MEXMECitaMedicaView>();

	public List<MEXMECitaMedicaView> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<MEXMECitaMedicaView> appointments) {
		this.appointments = appointments;
	}
	
	//Expert: Lama
	protected boolean beforeSave(boolean newRecord) {

		//Validamos que el color no sea repetido
		if(getAD_Color_ID() > 0 && is_ValueChanged("AD_Color_ID")){
			int count = 0;
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT AD_Color_ID FROM EXME_Especialidad WHERE AD_Color_ID = ?");
			
			count = DB.getSQLValue(get_TrxName(),sql.toString(),getAD_Color_ID());
			
			if(count>0){
				log.saveError("error.color.repeated", Msg.getElement(getCtx(), "EXME_Especialidad_ID "));
				return false;
			}
		}		
		return true;
	} //  beforeSave

	/**
	 * Returns a specialty based on the search key
	 * @param ctx The application context
	 * @param value The key to search for
	 * @param trxName The transaction name
	 * @return A specialty if the search key is found, null otherwise
	 */
	public static MEXMEEspecialidad getByValue(Properties ctx, String value,
			String trxName) {
		
		MEXMEEspecialidad retVal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EXME_Especialidad WHERE Value=? ";
		
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setString(1, value);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEEspecialidad(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getByValue ...", e);
		} finally {
			DB.close(rs,pstmt);
		}
		
		return retVal;
	}
	
	/**
	 * Returns a specialty based on the search key
	 * @param ctx The application context
	 * @param value The key to search for
	 * @param trxName The transaction name
	 * @return A specialty if the search key is found, null otherwise
	 */
	public static MEXMEEspecialidad getByName(Properties ctx, String name,
			String trxName) {
		
		MEXMEEspecialidad retVal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EXME_Especialidad WHERE Name=? ";
		
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setString(1, name);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEEspecialidad(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getByName ...", e);
		} finally {
			DB.close(rs,pstmt);
		}
		
		return retVal;
	}
	

	public static int getEspIDByValue(Properties ctx, String value, String trxName) {
		int ret = DB.getSQLValue(trxName, "SELECT EXME_Especialidad_ID FROM EXME_Especialidad WHERE Value = ? ", value);
		if (ret <= 0) {
			MEXMEEspecialidad retVal = new MEXMEEspecialidad(ctx, 0, null);
			retVal.setValue(value);
			retVal.setSpecType(MEXMEEspecialidad.SPECTYPE_MedicalSpecialty);
			retVal.setName(value);

			if (retVal.save()) {
				ret = retVal.getEXME_Especialidad_ID();
			}

		}
		return ret;
	}
	   
		/**
		 * Para WTreatments
		 */
		public static String[] columns = new  String[]{MEXMEEspecialidad.COLUMNNAME_Value, MEXMEEspecialidad.COLUMNNAME_Name, 
			MEXMEEspecialidad.COLUMNNAME_Description};
		
		
		/**
		 * Retorna la lista de especialidades TIPO OFFICE VISIT
		 * @param ctx
		 * @param trxName
		 * @return
		 */
		public static List<MEXMEEspecialidad> getLstSpecialtiesOV(Properties ctx, String trxName){
			final List<MEXMEEspecialidad> lstEspecialidades = new ArrayList<MEXMEEspecialidad>();
			final StringBuilder sql = new StringBuilder();
			
//			sql.append ("select es.*, cu.exme_cuestionario_id as questID  ")
//			.append("from exme_cuestionario cu ")
//			.append("inner join exme_especialidad es on cu.exme_especialidad_id = es.exme_especialidad_id ")
//			.append("where cu.exme_especialidad_id is not null ")
//			.append("and es.spectype = 'V' ")
//			.append("ORDER BY es.value ");
			
			sql.append("select es.*, gcu.exme_grupocuestionario_id as gquestID ")
			.append("from exme_grupocuestionario gcu ")
			.append("inner join exme_especialidad es on gcu.exme_especialidad_id = es.exme_especialidad_id ")
			.append("where gcu.exme_especialidad_id is not null ")
			.append("and es.spectype = 'V' ")
			.append("ORDER BY es.value  ");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				rs = pstmt.executeQuery();
				MEXMEEspecialidad aux = null;
				while (rs.next()) {				
					aux = new MEXMEEspecialidad(ctx, rs, null);
					aux.setGquestID(rs.getInt("gquestID"));
					lstEspecialidades.add(aux);
				}
			} catch (SQLException ex) {
				s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
			return lstEspecialidades;
		}
		
		
		public int gquestID = 0;

		public int getGquestID() {
			return gquestID;
		}

		public void setGquestID(int gquestID) {
			this.gquestID = gquestID;
		}

				
		
}