package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author mvrodriguez
 * @deprecated
 */
public class MEXMETSaldoCtaPac extends X_EXME_T_SaldoCtaPac 

{	
	private static final long serialVersionUID = 1L;
	
	/** static logger   */
	private static CLogger s_log = CLogger.getCLogger(MEXMETSaldoCtaPac.class);

	public MEXMETSaldoCtaPac(Properties ctx, int MEXME_T_SaldoCtaPac_ID, String trxName){
		super (ctx, MEXME_T_SaldoCtaPac_ID, trxName);
    }

	public MEXMETSaldoCtaPac(Properties ctx, ResultSet rs, String trxName){
      super (ctx, rs, trxName);
	}
	
//	public MEXMETSaldoCtaPac(Properties ctx, MEXMECtaPac m_ctaPac, String trxName){
//		super (ctx, 0, trxName);
//		setEXME_CtaPac_ID(m_ctaPac.getEXME_CtaPac_ID());
//		setEXME_Paciente_ID(m_ctaPac.getPaciente().getEXME_Paciente_ID());
//		setC_BPartner_ID(m_ctaPac.getPaciente().getC_BPartner_ID());
//		setEXME_Cama_ID(m_ctaPac.getEXME_Cama_ID());
//
//		MEXMEHabitacion hab = m_ctaPac.getHabitacion(ctx);
//
//		if(hab!=null){
//			setEXME_Habitacion_ID(hab.getEXME_Habitacion_ID());
//			setTipoArea(hab.getEstServ().getTipoArea());
//		}else{
//			setEXME_Habitacion_ID(0);
//			setTipoArea(null);
//		}
//	}

	/**
     * 
     * @param ctx
     * @param orgIdIni
     * @param orgIdFin
     * @param documentNoIni
     * @param documentNoFin
     * @param fechaIni
     * @param fechaFin
     * @param trxName
     * @return
     * @throws SQLException
     * @deprecated No tiene ninguna referencia. No se corrige rownum. modificado por Jesus Cantu 12 Julio 2012.
	 */
    public static String[][] getInfo(Properties ctx, int orgIdIni, int orgIdFin,String documentNoIni, String documentNoFin,
														String fechaIni, String fechaFin, String trxName)
	throws SQLException{
		
		String arreglo[][]=null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum, ha.exme_habitacion_id, ca.exme_cama_id, ha.tipoarea, cp.exme_ctapac_id, p.exme_paciente_id, cb.c_bpartner_id ")
			.append(" from	EXME_CtaPac cp ")
			.append(" left join EXME_Paciente p on (p.EXMe_paciente_id = cp.EXME_Paciente_id) ")
			.append(" left join EXME_Cama ca on (ca.EXMe_cama_id = cp.EXME_cama_id) ")
			.append(" left join EXME_Habitacion ha on (ha.EXme_habitacion_id = ca.EXMe_habitacion_id) ")
			.append(" left join C_BPartner cb on (cb.c_bpartner_id = p.c_bpartner_id) ")
			.append(" where cp.ad_org_id between ")
			.append(orgIdIni).append(" AND ").append(orgIdFin)
			.append(" and	cp.documentno between ").append(documentNoIni).append(" AND ").append(documentNoFin)
			.append(" and cp.dateordered between to_date(' ").append(fechaIni)
			.append("','dd/mm/yyyy') and to_date('").append(fechaFin).append("','dd/mm/yyyy') ")
			.append(" order by rownum desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
            
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery(sql.toString());

			ResultSetMetaData rsmd = rs.getMetaData();

			int cols =  rsmd.getColumnCount();
			
			boolean primerRegistro=false;				
			
			for(int y = 0; rs.next(); y++)
			{
				if (!primerRegistro){
					arreglo = new String [rs.getInt("ROWNUM")][cols-1];
					primerRegistro=true;
				}
				for (int x=0; x<cols-1; x++) {
					if (rs.getString(x+2)== null){
						arreglo[y][x] = "";
					} else {
						arreglo[y][x] = rs.getString(x+2);
					}
				}
			}
			
			
			
        } catch (Exception e) {
        	
        } finally {
			DB.close(rs, pstmt);
		}
        return arreglo;
	}
  
	/**
     * 
     * @param ctx
     * @param documentNoIni
     * @param documentNoFin
     * @param fechaIni
     * @param fechaFin
     * @param trxName
     * @return
     * @throws SQLException
     * @Deprecated Se deprecia por no tener ninguna referencia. No se corrige rownum. Jesus Cantu. 12 Julio 2012.
	 */
    public static String[][] getInfos(Properties ctx,String documentNoIni, String documentNoFin,
			String fechaIni, String fechaFin, String trxName)
	throws SQLException{

		String arreglo[][]=null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum, ha.exme_habitacion_id, ca.exme_cama_id, ha.tipoarea, ")
		.append(" EXME_CtaPac.exme_ctapac_id, p.exme_paciente_id, cb.c_bpartner_id ")
		.append(" from	EXME_CtaPac ")
		.append(" left join EXME_Paciente p on (p.EXMe_paciente_id = EXME_CtaPac.EXME_Paciente_id) ")
		.append(" left join EXME_Cama ca on (ca.EXMe_cama_id = EXME_CtaPac.EXME_cama_id) ")
		.append(" left join EXME_Habitacion ha on (ha.EXme_habitacion_id = ca.EXMe_habitacion_id) ")
		.append(" left join C_BPartner cb on (cb.c_bpartner_id = p.c_bpartner_id) ")
		.append(" where EXME_CtaPac.documentno between ").append(documentNoIni).append(" AND ").append(documentNoFin)
		.append(" and EXME_CtaPac.dateordered between to_date(' ").append(fechaIni)
		.append("','dd/mm/yyyy') and to_date('").append(fechaFin).append("','dd/mm/yyyy') ");
		
		sql =  new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPac"));
		
		sql.append(" order by rownum desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery(sql.toString());

			ResultSetMetaData rsmd = rs.getMetaData();

			int cols =  rsmd.getColumnCount();

			boolean primerRegistro=false;				

			for(int y = 0; rs.next(); y++){
            	if (!primerRegistro){
            		arreglo = new String [rs.getInt("ROWNUM")][cols-1];
            		primerRegistro=true;
            	}
            	for (int x=0; x<cols-1; x++) {
            	  if (rs.getString(x+2)== null){
            		arreglo[y][x] = "";
            	   } else {
            		arreglo[y][x] = rs.getString(x+2);
                   }
                }
            }
			
        } catch (Exception e) {
    		
        } finally {
			DB.close(rs, pstmt);
		}

        return arreglo;
	}

	/**
     * Noelia: Metodo que en base al nombre de una tabla y la session indica si existe o no dicha tabla
     * @param ctx
     * @param ADSessionID
     * @param trxName
     * @param tableName
     * @return
     * @throws SQLException
	 */
    public static int isSessionAlready(Properties ctx, int ADSessionID, String tableName, boolean accessLevel, String trxName)
	throws SQLException{
		
		String sql = "select "+ tableName +"_ID from  "+ tableName +" where ad_session_id = " + ADSessionID;
		
		if(accessLevel)
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, tableName);
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int regreso = 0;
        
		try {	
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery(sql);
			while(rs.next())
			{
				regreso = 1;
			}
			
				
		} catch (Exception e) {
        	
        } finally {
			DB.close(rs, pstmt);
		}

		return regreso;
	}		
		
	/**
     * Noelia: Elimina la tabla deseada, para una determinada session
     * @param ctx
     * @param ADSessionID
     * @param trxName
     * @param tableName
     * @throws SQLException
	 */
    public static void delete(Properties ctx, int ADSessionID, String tableName, boolean accessLevel, String trxName)
	throws SQLException{
    	//PIRUET
		String sql = "DELETE FROM "+ tableName +" WHERE AD_Session_ID = " + ADSessionID;
		if(accessLevel)
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, tableName);
	
		PreparedStatement pstmt = null;

		try {

			pstmt = DB.prepareStatement(sql, trxName);
			int noReg = pstmt.executeUpdate();
			s_log.fine(tableName +" Num de Registros borrados = " + noReg);
			
				
		} catch (Exception e) {
        	 
        } finally {
			DB.close(pstmt);
		}
		DB.commit(true, trxName);
	}
	
	/**
     * 
     * @param ctx
     * @param orgIdIni
     * @param orgIdFin
     * @param trxName
     * @return
     * @throws SQLException
	 */
    public static String[][] getInfoEstServ(Properties ctx, int orgIdIni, int orgIdFin, String trxName) throws SQLException{

		String arreglo[][]=null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select  rownum, c.value, c.cama_alterna, e.expediente,cp.documentno, p.value, p.apellido1 ||''|| p.apellido2 ||''|| p.name ||''|| p.nombre2, " + 
		" cb.name, mc.value, c.ad_client_id, h.exme_habitacion_id, h.value, es.description")
		.append(" FROM EXME_CAMA C INNER JOIN EXME_CTAPAC CP ON (CP.EXME_CAMA_ID = C.EXME_CAMA_ID )")
		.append(" LEFT JOIN EXME_MOTIVOCITA MC ON (MC.EXME_MOTIVOCITA_ID = CP.EXME_MOTIVOCITA_ID) ")
		.append(" INNER JOIN EXME_HABITACION H ON (H.EXME_HABITACION_ID = C.EXME_HABITACION_ID) ")
		.append(" INNER JOIN EXME_PACIENTE P ON (P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID) ")
		.append(" INNER JOIN C_BPARTNER CB ON (CB.C_BPARTNER_ID = P.C_BPARTNER_ID) ")
		.append(" LEFT JOIN EXME_HIST_EXP E ON (E.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID)")
		.append(" INNER join Exme_estserv es ON  (ES.Exme_estserv_id = H.Exme_estserv_id )")
		.append(" where cp.ad_org_id between ")
		.append(orgIdIni).append(" AND ").append(orgIdFin)
        .append(" AND cp.isActive = 'Y' ")//.-Lama

		.append(" order by rownum desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery(sql.toString());
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int cols =  rsmd.getColumnCount();
			
			boolean primerRegistro=false;				
			
			for(int y = 0; rs.next(); y++)
			{
				if (!primerRegistro){
					arreglo = new String [rs.getInt("ROWNUM")][cols-1];
					primerRegistro=true;
				}
				for (int x=0; x<cols-1; x++) {
					if (rs.getString(x+2)== null){
						arreglo[y][x] = "";
					} else {
						arreglo[y][x] = rs.getString(x+2);
					}
				}
				
			}
			
			
		} catch (Exception e) {
			
		} finally {
			DB.close(rs, pstmt);
		}
		
		return arreglo;
	}
    
    
    /**
     * Guarda las lineas en la tabla para un rango de cuentas paciente
     * 
     * @param ctx
     * @param documentNoIni
     * @param documentNoFin
     * @param whereClause
     * @param trxName
     * @return
     * @throws SQLException
     *
    public static boolean saldoTemp(Properties ctx,String documentNoIni, 
                                    String documentNoFin, String join, 
                                    String whereClause, String trxName)throws SQLException{

        StringBuffer sql = new StringBuffer();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
            
        try {
            //session
            int session = Env.getContextAsInt(ctx, "#AD_Session_ID");
            
            sql.append("SELECT EXME_CtaPac.EXME_CtaPac_ID, C_BPartner.C_BPartner_ID, ")
                .append(" NVL(EXME_Habitacion.TipoArea,EXME_CtaPac.TipoArea),")
                .append(" NVL(EXME_Cama.EXME_Cama_ID,0), NVL(EXME_Habitacion.EXME_Habitacion_ID,0) ")
                .append(" FROM EXME_CtaPac ")
                .append(" INNER JOIN EXME_Paciente ON (EXME_Paciente.EXME_Paciente_ID = EXME_CtaPac.EXME_Paciente_ID AND EXME_Paciente.isActive ='Y')")
                .append(" INNER JOIN C_BPartner ON (C_BPartner.C_BPartner_ID = EXME_Paciente.C_BPartner_ID)")
                .append(" LEFT JOIN EXME_Cama ON (EXME_Cama.EXME_Cama_ID = EXME_CtaPac.EXME_Cama_ID)")
                .append(" LEFT JOIN EXME_Habitacion ON (EXME_Habitacion.EXME_Habitacion_ID = EXME_Cama.EXME_Habitacion_ID)");
            
            if(join!=null && join.length()>0)
                sql.append(join);
            
            sql//.append(" WHERE EXME_CtaPac.AD_Client_ID = ").append(Env.getContextAsInt(ctx, "#AD_Client_ID"))
                //.append(" AND EXME_CtaPac.AD_Org_ID IN (0,").append(Env.getContextAsInt(ctx, "#AD_Org_ID")).append(")")
                .append(" WHERE EXME_CtaPac.documentNo BETWEEN ").append(documentNoIni).append(" AND ").append(documentNoFin);
            
            sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPac"));

            if(whereClause!=null && whereClause.length()>0)
                sql.append(whereClause);
            
            sql.append(" ORDER BY EXME_CtaPac.documentNo ");
            
            //System.out.println("Session -->"+session+"EXMETSaldoCtaPac SQL --> "+sql.toString());
            
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery(sql.toString());
            
            while(rs.next()){
                //creamos un nuevo registro en la tabla temp
                MEXMETSaldoCtaPac s_ctapac = new MEXMETSaldoCtaPac(ctx,0,trxName);

                s_ctapac.setAD_Session_ID(session);
                
                s_ctapac.setEXME_CtaPac_ID(rs.getInt(1));
                s_ctapac.setC_BPartner_ID(rs.getInt(2));
                s_ctapac.setTipoArea(rs.getString(3));
                s_ctapac.setEXME_Cama_ID(rs.getInt(4));
                s_ctapac.setEXME_Habitacion_ID(rs.getInt(5));
                
                MEXMECtaPac ctaPac = new MEXMECtaPac(ctx,rs.getInt(1),trxName);              
                
                s_ctapac.setEXME_Paciente_ID(ctaPac.getEXME_Paciente_ID());

   //             s_ctapac.setSaldo(ctaPac.getSaldo());//Saldo
                //Omar de la Rosa, en caso del que los cargos sean nulos seteamos un cero
                if(ctaPac.getCargos()!=null)
                	s_ctapac.setCargos(ctaPac.getCargos());
                else
                	s_ctapac.setCargos(BigDecimal.ZERO);
                s_ctapac.setAnticipos(ctaPac.getPagosAmt());//Anticipos
                s_ctapac.setPagos(ctaPac.getFacturas());//pagos // java encapsulation
                
                if (!s_ctapac.save(trxName)){
                    DB.rollback(false, trxName);
                    s_log.log(Level.SEVERE, "Error al guardar en MEXMETSaldoCtaPac "+s_ctapac.getEXME_CtaPac_ID());
                    return false;
                }
                DB.commit(false, trxName);
            }

        } catch (Exception e) {
            s_log.log(Level.SEVERE, "Error MEXMETSaldoCtaPac.saldoTemp "+e);
            return false;
        } finally {
			DB.close(rs, pstmt);
		}

        return true;
    }*/
    
    public static MEXMETSaldoCtaPac getSaldoByCtaPac(Properties ctx, int EXME_CtaPac_ID, int AD_Sesion_ID, String trxName) throws Exception{
    	
    	StringBuilder sql = new StringBuilder("SELECT * FROM EXME_T_SALDOCTAPAC WHERE AD_SESSION_ID = ? AND EXME_CTAPAC_ID = ?");
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	MEXMETSaldoCtaPac saldo = null;
    	
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, AD_Sesion_ID);
    		pstmt.setInt(2, EXME_CtaPac_ID);
    		
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			saldo = new MEXMETSaldoCtaPac(ctx, rs, trxName);
    		}
    		
		} catch (Exception e) {
			throw new Exception("error.msjError");
		} finally {
			DB.close(rs, pstmt);
		}
		
		return saldo;       	
    }
	
}
