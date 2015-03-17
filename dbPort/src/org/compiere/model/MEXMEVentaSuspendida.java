package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Asael Sepulveda
 */
public class MEXMEVentaSuspendida extends X_EXME_VentaSuspendida {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEVentaSuspendida.class);
    
    /**
     * @param ctx
     * @param EXME_VentaSuspendida_ID
     * @param trxName
     */
    public MEXMEVentaSuspendida(Properties ctx, int EXME_VentaSuspendida_ID, String trxName) {
        super(ctx, EXME_VentaSuspendida_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEVentaSuspendida(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
  
    private MEXMERfc m_rfc = null;
    
    public MEXMERfc getRfc() {
    	
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);    
    	
    	return m_rfc;
    }
    
    /**
     * getUsuario
     * 
     * Devuelve el nombre del ad_user de acuerdo a su exme_operador_id
     * @return
     */
    public String getADUserName() {

    	MEXMEOperador operador = new MEXMEOperador(this.getCtx(), this.getEXME_Operador_ID(), null);
    	return MUser.getNameOfUser(operador.getAD_User_ID());
    }
    
    public String getOperadorName() {
   	
    	MEXMEOperador operador = new MEXMEOperador(this.getCtx(), this.getEXME_Operador_ID(), null);
    	return operador.getName();
    }
    
    public String getValue() {
    	String valor = null;
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null); 
    	
    	if(m_rfc.getEXME_RFC_ID() == 0 ){
	    	MBPartner bPartner = new MBPartner(this.getCtx(), this.getC_BPartner_ID(), null);
	    	if (bPartner != null) {
	    		valor = bPartner.getTaxID();
	    	} 
    	}else{
    		valor = m_rfc.getRfc();
    	}

    	return  valor;//MEXMERfc.getValueById(this.getCtx(), this.getEXME_RFC_ID(), null);
    }
    
    public String getBPartnerValue() {
    	MBPartner bPartner = new MBPartner(this.getCtx(), this.getC_BPartner_ID(), null);
    	return bPartner.getValue();
    }

    public String getDireccion() {
    	String direccion = null;
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);
    	if(m_rfc.getEXME_RFC_ID() == 0 ){
    		MBPartnerLocation bplocation = MEXMEBPartner.getLocations (this.getCtx(), this.getC_BPartner_ID(), null);
    		if(bplocation != null){
	    		MLocation location = new MLocation(this.getCtx(), bplocation.getC_Location_ID(), null);
	    		direccion = location.getAddress1();
	    		location = null;
	    		//m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);
    		}
	    	bplocation = null;  		
    	}else{
    		direccion = m_rfc.getAddress1();
    	}
    	
    	return direccion;//MEXMERfc.getDireccionById(this.getCtx(), this.getEXME_RFC_ID(), null);
    }
    
    public String getDelegacion() {
    	String delegacion = null;
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);
    	if(m_rfc.getEXME_RFC_ID() == 0 ){
    		MBPartnerLocation bplocation = MEXMEBPartner.getLocations (this.getCtx(), this.getC_BPartner_ID(), null);
    		if(bplocation != null){
    			MLocation location = new MLocation(this.getCtx(), bplocation.getC_Location_ID(), null);
	    		if(location.getEXME_TownCouncil_ID() > 0)
	    			delegacion = location.getTownCouncil().getName();
	    		//m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);  	    		
	    		location = null;
    		}
    		bplocation = null;
    	}else{
    		delegacion = m_rfc.getDelegacion();
    	}  
    	
    	return delegacion;//MEXMERfc.getDireccionById(this.getCtx(), this.getEXME_RFC_ID(), null);
    }
    
    public String getCodigoPostal() {
    	String codigo = null;
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);
    	if(m_rfc.getEXME_RFC_ID() == 0 ){
    		MBPartnerLocation bplocation = MEXMEBPartner.getLocations (this.getCtx(), this.getC_BPartner_ID(), null);
    		if(bplocation != null){
    			MLocation location = new MLocation(this.getCtx(), bplocation.getC_Location_ID(), null);
	    		codigo = location.getPostal();
	    		//m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);    	    		
	    		location = null;
    		}
    		bplocation = null;
    	}else{
    		codigo = m_rfc.getPostal();
    	}    
    	
    	return codigo;//MEXMERfc.getCodigoPostalById(this.getCtx(), this.getEXME_RFC_ID(), null);
    }
    
    public String getRegionName() {
    	String region = null;
    	if(m_rfc == null)
    		m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);
    	if(m_rfc.getEXME_RFC_ID() == 0 ){
    		MBPartnerLocation bplocation = MEXMEBPartner.getLocations (this.getCtx(), this.getC_BPartner_ID(), null);
    		if(bplocation != null){
	    		MLocation location = new MLocation(this.getCtx(), bplocation.getC_Location_ID(), null);
	    		if(location.getC_Region_ID() > 0)
	    			region = MRegion.get(this.getCtx(), location.getC_Region_ID()).getName();
	    		//m_rfc = new MEXMERfc(this.getCtx(), this.getEXME_RFC_ID(), null);     	    		
	    		location = null;
    		}
    		bplocation = null;
    	}else{
    		region = MRegion.get(this.getCtx(), m_rfc.getC_Region_ID()).getName();
    	}  
    	
    	//int regionId = MEXMERfc.getRegionIdById(this.getCtx(), this.getEXME_RFC_ID(), null);
    	return region;
    }
    
    /**
     * getAll
     * 
     * Devuelve un ArrayList con las ventas suspendidas dado su ID.
     * 
     * @param ctx
     * @param EXME_VentaSuspendida_ID	ID de la venta suspendida cuyo detalle se recuperar�
     * @param whereClause				Para agregar condiciones extras al query
     * @param orderBy					Para ordenar el resultado
     * @param trxName
     * @return
     */
    public static ArrayList<MEXMEVentaSuspendida> getAll(Properties ctx, String whereClause, String orderBy, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        ArrayList<MEXMEVentaSuspendida> retValue = null;

        sql.append("SELECT * FROM EXME_VentaSuspendida WHERE isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_VentaSuspendida"));
        
        if (whereClause != null) {
        	sql.append(whereClause);
        }
        
        if (orderBy != null) {
        	sql.append("order By ").append(orderBy);
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            //pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            //pstmt.setInt(2, Env.getAD_Org_ID(ctx));
            rs = pstmt.executeQuery ();
            
            retValue = new ArrayList<MEXMEVentaSuspendida>();
            
            while (rs.next ()) {
                retValue.add(new MEXMEVentaSuspendida(ctx, rs, trxName));
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		}finally {
			DB.close(rs, pstmt);
		}
        return retValue;
    }
    
    /**
     * getByOperadorId
     * 
     * Devuelve un ArrayList con las ventas suspendidas de un operador dado su ID.
     * 
     * @param ctx
     * @param EXME_VentaSuspendida_ID	ID de la venta suspendida cuyo detalle se recuperar�
     * @param whereClause				Para agregar condiciones extras al query
     * @param orderBy					Para ordenar el resultado
     * @param trxName
     * @return
     */
    public static ArrayList<MEXMEVentaSuspendida> getByOperadorId(Properties ctx, int EXME_Operador_Id, String whereClause, String orderBy, String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

        ArrayList<MEXMEVentaSuspendida> retValue = null;

        sql.append("SELECT * FROM EXME_VentaSuspendida WHERE EXME_Operador_ID = ? and isActive = 'Y' ");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_VentaSuspendida"));
        
        if (whereClause != null) {
        	sql.append(whereClause);
        }
        
        if (orderBy != null) {
        	sql.append("order By ").append(orderBy);
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement (sql.toString(), trxName);
            pstmt.setInt(1, EXME_Operador_Id);
            //pstmt.setInt(2, Env.getAD_Client_ID(ctx));
            //pstmt.setInt(3, Env.getAD_Org_ID(ctx));
            
            rs = pstmt.executeQuery ();
            
            retValue = new ArrayList<MEXMEVentaSuspendida>();
            
            while (rs.next ()) {
                retValue.add(new MEXMEVentaSuspendida(ctx, rs, trxName));
            }

        } catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		}finally {
			DB.close(rs, pstmt);
		}
        return retValue;
    }
}
