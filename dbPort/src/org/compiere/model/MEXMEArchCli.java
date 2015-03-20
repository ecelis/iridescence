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
import org.compiere.util.Env;

public class MEXMEArchCli extends X_EXME_ArchCli{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3344309976575175056L;
	private static CLogger      log = CLogger.getCLogger (MEXMEArchCli.class);

	public MEXMEArchCli(Properties ctx, int EXME_ArchCli_ID, String trxName) {
		super(ctx, EXME_ArchCli_ID, trxName);
	}
	
	public MEXMEArchCli(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	
	public static int getNextExpediente(Properties ctx, int AD_Client_ID, String trxName) {

        int retValue = 0;
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT NVL(MAX(codigo),0)+1 AS DefaultValue FROM EXME_ArchCli WHERE isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ArchCli"));
        
        	//.append(AD_Client_ID);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            if (rs.next()) 
                retValue=rs.getInt(1);
            
    	} catch (Exception e) {
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
     
        return retValue;
    }
	
	
	
	public static String getExiste(Properties ctx, int EXME_Paciente_ID, String trxName){
        String result = null;
        StringBuilder sql = new StringBuilder();
        
         sql.append(" SELECT EXME_Archcli.value FROM EXME_Archcli ")
            .append(" WHERE EXME_Archcli.Isactive = 'Y' AND EXME_ARchCli.EXME_Paciente_ID = ? ");
       
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ArchCli"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_Paciente_ID);
            rs = pstmt.executeQuery();
            if (rs.next()) 
                result = rs.getString("Value");
    
    	} catch (Exception e) {    		
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return result;
	}
	
	
	public static MEXMEArchCli getByValue(Properties ctx,String value, String trxName) throws SQLException {
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEArchCli exp = null;

		try {

			sql.append("SELECT * FROM EXME_ArchCli ")
			.append("WHERE value = '")
			.append(value)
			.append("' AND AD_Client_ID = ")
			.append(Env.getAD_Client_ID(ctx));

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				exp = new MEXMEArchCli(ctx, rs, trxName);
			}
			

		} catch (SQLException e) {
			log.log(Level.SEVERE, "getForExpediente (" + sql + ")", e);
			
			throw e;
		} finally {
			DB.close(rs, pstmt);

			rs = null;
			pstmt = null;
		}

		return exp;
	}

	public static MEXMEArchCli getTieneExp(Properties ctx, int EXME_Paciente_ID, String trxName){
		MEXMEArchCli exp = null;
        StringBuilder sql = new StringBuilder(); 
		//MEXMEArchCli exp = null;      
		
		 sql.append(" SELECT * FROM EXME_Archcli ")
            .append(" WHERE EXME_Archcli.Isactive = 'Y' AND EXME_ArchCli.EXME_Paciente_ID = ? ")
            .append("AND Name like '%EXPE%' OR Name Like '%xpe%'");
       
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ArchCli"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_Paciente_ID);
            rs = pstmt.executeQuery();
            if (rs.next()) 
            	exp = new MEXMEArchCli(ctx, rs, trxName);
    
    	} catch (Exception e) {    		
    		log.log(Level.SEVERE, sql.toString(), e);
    		
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return exp;
	}
	
	public static MEXMEArchCli getExpfromPac(Properties ctx, int EXME_Paciente_ID, String trxName){
		MEXMEArchCli exp = null;
        StringBuilder sql = new StringBuilder(); 
		//MEXMEArchCli exp = null;      
		
		 sql.append(" SELECT * FROM EXME_Archcli ")
            .append(" WHERE EXME_Archcli.Isactive = 'Y' AND EXME_ArchCli.EXME_Paciente_ID = ? ");
            
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ArchCli"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_Paciente_ID);
            rs = pstmt.executeQuery();
            if (rs.next()) 
            	exp = new MEXMEArchCli(ctx, rs, trxName);
    
    	} catch (Exception e) {    		
    		log.log(Level.SEVERE, sql.toString(), e);
    		
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return exp;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param block
	 * @param orderBlock
	 * @param trxName
	 * @return
	 * @deprecated No tiene ninguna referencia de uso. Jesus Cantu. 11 Julio 2012
	 * 			   por lo anterior no se modifico la sentencia rownum.
	 */
    public static List<MEXMEArchCli> getLines(Properties ctx, String whereClause, 
            String orderBy, boolean block, String orderBlock, /*MEXMEMejoras mejora,*/
            String trxName){
      
        //Properties prop = WebEnv.getXPT_Properties();

    	List<MEXMEArchCli> lista = new ArrayList<MEXMEArchCli>();
    	
        StringBuilder sql = new StringBuilder("SELECT EXME_ArchCli.* ")
                    .append("FROM EXME_ArchCli WHERE EXME_ArchCli.Isactive = 'Y' ");
        
        if (whereClause != null)
            sql.append(whereClause);

        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"EXME_ArchCli"));
        
        if(orderBy != null && orderBy.length() > 0)
            sql.append(" ORDER BY ").append(orderBy);
        
        if(block){
            sql = new StringBuilder("SELECT * FROM (").append(sql).append(") WHERE ROWNUM <= ");
            
            // Para el Numero de registros por bloque se tomar� el valor definido en la tabla
            // EXME_Mejoras siempre y cuando este sea mayor a cero, de lo contrario, 
            // o de no existir registro en mejoras, tomar� el valor del "Paths.properties"
            /*sql.append(mejora != null && mejora.getRegBloque() > 0
                    ? mejora.getRegBloque() 
                            : prop.getProperty(WebEnv.NoRegistros));*/
            sql.append(MEXMEMejoras.getMaxQueryRecords(ctx));//Lama

            if(orderBlock!=null && !orderBlock.equals("")){
                sql.append(" ORDER BY ROWNUM ").append(orderBlock);
            }
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	MEXMEArchCli result = new MEXMEArchCli(ctx, rs, trxName);
            	lista.add(result);
            }
    
    	} catch (Exception e) {
    		log.log(Level.SEVERE, sql.toString(), e);
    		
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return lista;
    }
    
    /**
     * 
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @return
     * Rev.ok
     */
    public static MEXMEArchCli[] getExpediente(Properties ctx, String whereClause, 
            String orderBy, String trxName){
      
    	MEXMEArchCli[] result = null;
        ArrayList<MEXMEArchCli> list = new ArrayList<MEXMEArchCli>();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT EXME_ArchCli.* ") 
        	.append(" FROM EXME_ArchCli ")
        	.append(" INNER JOIN exme_paciente pac on(EXME_ArchCli.exme_paciente_id=pac.exme_paciente_id and pac.isactive = 'Y') "  )
        	.append(" left join exme_ctapac ctp on(ctp.exme_paciente_id=pac.exme_paciente_id and ctp.isactive='Y' ")
        	//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
        	.append(" and ctp.EncounterStatus ='")
        	.append(MEXMECtaPac.ENCOUNTERSTATUS_Admission).append("') " )
            .append( " WHERE EXME_ArchCli.Isactive = 'Y' ");
       
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ArchCli"));
        
        if (whereClause != null)
            sql.append(whereClause);

        if(orderBy != null && orderBy.length() > 0)
            sql.append(" ORDER BY ").append(orderBy);

        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            while  (rs.next()) {
            	MEXMEArchCli expediente= new MEXMEArchCli(ctx, rs, trxName);
                list.add(expediente);
            }
    
    	} catch (Exception e) {    		
    		log.log(Level.SEVERE, sql.toString(), e);
    		
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	
    	result = new MEXMEArchCli[list.size()];
		list.toArray(result);

    	
        return result;
    }
    
    
    public MEXMEPaciente getPaciente() {

        if(getEXME_Paciente_ID() <= 0)
            return null;

        MEXMEPaciente m_paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());

        return m_paciente;
    }

}
