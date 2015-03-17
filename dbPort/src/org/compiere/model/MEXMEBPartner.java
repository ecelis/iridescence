/*
 * Created on 01-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author GUEST_MTY
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MEXMEBPartner extends MBPartner {
    
	/** serialVersionUID */
	private static final long serialVersionUID = 4324118797772975194L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEBPartner.class);

    /**
     * Constructor
     * @param ctx Contexto
     */
    public MEXMEBPartner(Properties ctx) {
        super(ctx);
    }

    /** Constructor
     * @param ctx Contexto
     * @param rs  ResultSet
     * @param trxName Nombre de transaccion
     */
    public MEXMEBPartner(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /** Constructor
     * @param ctx
     * @param C_BPartner_ID
     * @param trxName
     */
    public MEXMEBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
        super(ctx, C_BPartner_ID, trxName);
    }

    /**
     * @param impBP
     */
    public MEXMEBPartner(X_I_BPartner impBP) {
        super(impBP);
    }
    
    /**
     * Key
     * @param C_BPartner_ID
     */
    public void setId(int C_BPartner_ID){
        super.setC_BPartner_ID(C_BPartner_ID);
    }
    
    /**
     * Key
     */
    public int getId(){
        return super.getC_BPartner_ID();
    }

	/**
	 * Indica si el BP es generica
	 */
	public boolean isGenericPac(){
		return BP_CLASS_P.equals(getBP_Class());
	}
	
	/**
	 * Indica si el BP es aseguradora o Particular.
	 */
	public boolean isAseguradora(){
		return isFacturarAseg();		
	}
	
	/** Indica si el BP es Particular o no
	 */
	public boolean isParticular(){
		return !isFacturarAseg();		
	}
	
	/**
	 *  Indica si el BP es aseguradora o Particular.
	 * @return
	 */
	public boolean getIsAseguradora(){
	    return isAseguradora();
	}

	/**
     * Retornamos el nombre completo del BPartner.
     * Apellido1 + Apellido2 + Appellido3 + Name + Nombre2
     * @return Nombre completo del BPartner.
     */    
    public String getFullName(){
        
        StringBuffer fullName = new StringBuffer();
        
        fullName.append((getName2() == null ? "" : getName2() + " "));
        fullName.append((getName() == null ? "" : getName() + " "));
        
        return fullName.toString().trim();
    }
    
    /**
     *  Get BPartner with Value
     *  @param ctx context 
     *  @param Value value
     *  @param sensitive Sensitivoa  May y Min.
     *  @return BPartner or null
     */
    public static MEXMEBPartner get (Properties ctx, String value, String trxName)
    {
        if (StringUtils.isEmpty(value)){
            return null;
		}
        return new Query(ctx, Table_Name, "UPPER(Value)=?", trxName)
        			.setParameters(value.toUpperCase())
        			.addAccessLevelSQL(true)
        			.first();
    }   //  get
    
    /**
     * Obj MLocation
     * @param ctx
     * @param socioId
     * @return
     */
    public static MLocation getMLocation(final Properties ctx, final int socioId){
    	MLocation locationsoc = null;
    	MBPartnerLocation bplocation = MEXMEBPartner.getLocations (ctx, socioId, null);
    	if(bplocation!=null){
    		locationsoc =  new MLocation(ctx, bplocation.getC_Location_ID(), null);
    	}
    	return locationsoc;
    }
    
    /**
     *  Get All Locations
     *  @param reload if true locations will be requeried
     *  @return locations
     */
    public static MBPartnerLocation getLocations (Properties ctx, int C_BPartner_ID, String trxName)
    {
    	s_log.log(Level.FINEST, " getLocations INCIO " + DB.getDateForOrg(ctx));
    	MBPartnerLocation loc = new Query(ctx, MBPartnerLocation.Table_Name, "C_BPartner_Location.C_BPartner_ID=?", trxName)
					.setOnlyActiveRecords(true)
    				.setParameters(C_BPartner_ID)
					.addAccessLevelSQL(true)
					.setOrderBy("C_BPartner_Location.Created DESC")
					.first();
        s_log.log(Level.FINEST, " getLocations FIN " + DB.getDateForOrg(ctx)); 
        return loc;
    }   //  getLocations
    
	/**
	 * 	Get All Locations
	 * 	@param reload if true locations will be requeried
	 *	@return locations
	 */
	public MBPartnerLocation getLocationPac()  {
		return getLocationPac(getCtx(), getAD_Client_ID(), getC_BPartner_ID(), get_TrxName());
	} // getLocations
	
	
	/**
	 * Get All Locations
	 * @param reload if true locations will be requeried
	 * @return locations
	 * @throws SQLException 
	 */
	public static MBPartnerLocation getLocationPac(Properties ctx, int AD_Client_ID, int C_BPartner_ID, String trxName){
		MBPartnerLocation dir = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM C_BPartner_Location WHERE isActive = 'Y' ")
		//Comentado mientras se revisa el nivel de acceso
		//.append(" and ad_client_id = ? ")
		.append(" AND C_BPartner_ID = ? ")
		.append(" and isremitto = 'N' ")
		.append(" ORDER BY Created DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			//pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(1, C_BPartner_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dir = new MBPartnerLocation(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return dir;
	} // getLocations
	
	public final static String[] column = new String[] { MBPartner.COLUMNNAME_Value,
			MBPartner.COLUMNNAME_Name, MBPartner.COLUMNNAME_TaxID };
}
