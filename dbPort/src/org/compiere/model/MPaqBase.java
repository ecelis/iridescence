/*
 * Created on 28/11/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;


/**
 * @author TWRY TANIA PEREZ ROMERO 
 *
 * @deprecated
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MPaqBase { 
//
//extends X_EXME_PaqBase {
//
//    /** serialVersionUID */
//	private static final long serialVersionUID = 6042018214605588885L;
//	
//	/** Static logger           */
//    private static CLogger s_log = CLogger.getCLogger(MEXMEMO_Expediente.class);
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_PaqBase_ID
//	 * @param trxName
//	 */
//	public MPaqBase(Properties ctx, int EXME_PaqBase_ID, String trxName) {
//        super(ctx, EXME_PaqBase_ID, trxName);
//    }
//
//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MPaqBase(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//    
//    /**
//	 * ID
//	 * @return
//	 */
//	public int getId() {
//
//		if(getEXME_PaqBase_ID() > 0)
//			return getEXME_PaqBase_ID();
//		else
//			return 0;
//	}
//    
//    /**
//    * Obenemos una lista de Paquetes para el Cliente y Organizacion, ya sean todos o solo los activos
//    * @param  soloActivos Indica si regresara solo paquetes activos o todos
//    */
//    public static List<MEXMEPaqBase> getLstPaqBase(Properties ctx, String cadena, String trxName){
//        
//        List<MEXMEPaqBase> resultados = new ArrayList<MEXMEPaqBase>();
//        
//        if (ctx == null) {
//            return null;
//        }
//        
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append(" SELECT EXME_PaqBase.EXME_PaqBase_ID FROM EXME_PaqBase ");
//        sql.append(" WHERE EXME_PaqBase.IsActive = 'Y' ");
//        
//        if (cadena != null) {
//            sql.append(cadena);
//        }
//            
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//                
//        sql.append(" ORDER BY EXME_PaqBase.EXME_PaqBase_ID DESC "); 
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), null);
//            rs = pstmt.executeQuery();
//            
//            while (rs.next()) {
//            	MEXMEPaqBase paq = new MEXMEPaqBase(ctx, rs.getInt("EXME_PaqBase_ID"), trxName);
//                resultados.add(paq);
//            }
//        } catch  (SQLException sqle) {
//        	s_log.log(Level.SEVERE, "While closing Objects - get - sql: " + sql, sqle);
//            
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//        
//        return resultados;
//    }
//    
//    /**
//     * Obtenemos todos las versiones del paquete para la empresa logeada (expert)
//     * @param ctx
//     * @param cadena
//     * @param value
//     * @param trxName
//     * @return
//     */
//    public static List<MPaqBaseVersion> getPaqBaseVersion(Properties ctx, String cadena, int value, String trxName){
//        
//        if(ctx == null)
//            return null;
//        
//        ArrayList<MPaqBaseVersion> list = new ArrayList<MPaqBaseVersion>();
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
//        sql.append(" SELECT * ") 
//        .append(" FROM EXME_PaqBase_Version ")
//        .append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' ")
//        .append(" AND EXME_PaqBase_Version.EXME_PaqBase_ID = ? ");
//
//        if (cadena != null) {
//            sql.append(cadena);
//        }
//            
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBase_Version"));
//        sql.append(" ORDER BY EXME_PaqBase_Version.EXME_PaqBase_Version_ID DESC ");
//                    
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, value);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                MPaqBaseVersion paquete = new MPaqBaseVersion(ctx, rs, trxName);
//                list.add(paquete);
//            }
//        }
//        catch (Exception e) {
//        	s_log.log(Level.SEVERE, "While closing Objects - getPaqBaseVersion - sql: " + sql, e);
//        }
//        finally {
//            DB.close(rs, pstmt);
//        }
//
//        //
//        return list;
//        
//    }
//    
//    /**
//     * Retorna solo las versiones de paquetes que pueden aplicar para la creacion de la ctapaciente
//     * considerardo la fecha de vigencia de cada version del paquete
//     * @param ctx
//     * @param trxName
//     * @return
//     */
//    public static List<MPaqBaseVersion> paquetes(Properties ctx, String fechaHasta, 
//    		String fechaCuenta, String fechaActual, int bpartner, String trxName){
//        List<MPaqBaseVersion> lista =  new ArrayList<MPaqBaseVersion>();
//        
//        //traerme todos los paquetes
//        List<MEXMEPaqBase> lstPaquetes = getLstPaqBase(ctx, "  AND EXME_PaqBase.IsMiniPack = 'N' ", trxName);
//        //List lstPaquetes = get(ctx, "  AND EXME_PaqBase.C_BPartner_ID = " + bpartner + " OR  EXME_PaqBase.C_BPartner_ID is null ", trxName);
//        
//        //SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
//        
//        //barremos los paquetes para obtener las versiones 
//        for(int i=0; i<lstPaquetes.size(); i++){
//        	MEXMEPaqBase paquete = (MEXMEPaqBase)lstPaquetes.get(i);
//            
//            //Busco la fecha de vigencia anterior o = a la fecha de la cuenta paciente
//            String fecha = getFechaVersiones(ctx, null, paquete.getEXME_PaqBase_ID(), fechaHasta, fechaCuenta, trxName);
//            
//            //todas las versiones segun paquete
//            String cadena = " AND EXME_PaqBase_Version.ValidTo >= TO_DATE('"+ fechaHasta +"', 'dd/mm/yyyy') " +
//                            " AND EXME_PaqBase_Version.ValidFROM <= TO_DATE('"+ fechaActual+"', 'dd/mm/yyyy') ";
//            
//            //si la fecha no es nula la toma como fecha limite inferior
//            if(fecha != null)
//                cadena += " AND EXME_PaqBase_Version.ValidFROM >= TO_DATE('" + fecha +"', 'dd/mm/yyyy') ";
//            
//         
//            //obtengo todas la versiones entre un rango de fechas
//            List<MPaqBaseVersion> lstVersiones = getPaqBaseVersion(ctx, cadena, paquete.getEXME_PaqBase_ID(), trxName);
//            
//            //barro las versiones para incluirlas en la lista
//            for (int j=0; j<lstVersiones.size(); j++) {
//                MPaqBaseVersion vers = (MPaqBaseVersion)lstVersiones.get(j);
//                lista.add(vers);
//            }
//            
//        }
//        
//        return lista;
//    }
//    
//    /**
//     * 
//     * @param ctx
//     * @param cadena
//     * @param paquete
//     * @param fechaHasta
//     * @param fechaCuenta
//     * @param trxName
//     * @return
//     */
//    public static String getFechaVersiones(Properties ctx, String cadena, int paquete, String fechaHasta,
//            String fechaCuenta, String trxName){
//    	
//        if (ctx == null) {
//            return null;
//        }
//        
//        String fecha = null;
//        
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append(" SELECT TO_CHAR(EXME_PaqBase_Version.ValidFrom, 'dd/mm/yyyy') AS fecha FROM EXME_PaqBase_Version ");
//        sql.append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' "); 
//        sql.append(" AND EXME_PaqBase_Version.EXME_PaqBase_ID = ").append(paquete);
//        sql.append(" AND EXME_PaqBase_Version.ValidTo >= TO_DATE('").append(fechaHasta).append("', 'dd/mm/yyyy') "); 
//        sql.append(" AND EXME_PaqBase_Version.ValidFROM <= TO_DATE('");
//        sql.append(fechaCuenta).append("', 'dd/mm/yyyy') "); //fecha cuenta
//        
//        if (DB.isOracle()) {
//        	sql.append( " AND ROWNUM = 1 ");
//        }
//        
//        if (cadena != null) {
//            sql.append(cadena);
//        }
//            
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
//        
//        sql.append(" ORDER BY EXME_PaqBase_Version.ValidFROM DESC ");
//        
//        if (DB.isPostgreSQL()) {
//        	 sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
//        }
//                    
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            rs = pstmt.executeQuery();
//            
//            if (rs.next()) {
//                fecha = rs.getString("fecha");
//            }
//            
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, "getFechaVersiones - sql: " + sql, e);
//        } finally {
//            try {
//            	if (rs != null) {
//            		rs.close();
//            	}
//                if (pstmt != null) {
//                    pstmt.close ();
//                }
//            } catch (Exception e) {
//            	s_log.log(Level.SEVERE, "While closing Objects - getFechaVersiones - sql: " + sql, e);
//            }
//        }
//
//        //
//        return fecha;
//    }
//    
//   public boolean getMinipack(){
//	   return isMiniPack();
//   }
   
/*   public int getIdBPartner(){
	   return getC_BPartner_ID();
   }
   
   public MBPartner getBpartner(){
	   return new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
   } */
}
