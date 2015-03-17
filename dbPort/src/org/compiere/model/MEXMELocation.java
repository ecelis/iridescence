package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMELocation extends MLocation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7394919753587733896L;
	/**	Static Logger				*/
	private static CLogger	s_log = CLogger.getCLogger(MEXMELocation.class);

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Location_ID id
	 *	@param trxName transaction
	 */
	public MEXMELocation (Properties ctx, int C_Location_ID, String trxName)
	{
		super (ctx, C_Location_ID, trxName);
		if (C_Location_ID == 0)
		{
			MCountry defaultCountry = MCountry.getDefault(getCtx()); 
			setCountry(defaultCountry);
			MRegion defaultRegion = MRegion.getDefault(getCtx());
			if (defaultRegion != null 
				&& defaultRegion.getC_Country_ID() == defaultCountry.getC_Country_ID())
				setRegion(defaultRegion);
		}
	}	//	MLocation
	

    /**
     * Crea una copia a partir de un origen
     * @param origin
     * @param EXME_CtaPacExt_ID
     * @param trxName
     * @return
     */
    public static MLocation copyFrom(Properties ctx, int originId, int C_Location_ID, String trxName){
        
        MLocation location = null;
        MLocation origin = new MLocation(ctx, originId, trxName);
        try{
            location = new MLocation(ctx, C_Location_ID, trxName);
            
            location.setAD_Client_ID(origin.getAD_Client_ID());
            location.setAD_Org_ID(origin.getAD_Org_ID());
            location.setIsActive(origin.isActive());
            location.setAddress1(origin.getAddress1());
            location.setAddress2(origin.getAddress2());
            location.setCity(origin.getCity());
            location.setPostal(origin.getPostal());
            location.setPostal_Add(origin.getPostal_Add());
            location.setC_Country_ID(origin.getC_Country_ID());
            location.setC_Region_ID(origin.getC_Region_ID());
            location.setC_City_ID(origin.getC_City_ID());
            location.setRegionName(origin.getRegionName());
            location.setAddress3(origin.getAddress3());
            location.setAddress4(origin.getAddress4());
            location.setEXME_TownCouncil_ID(origin.getEXME_TownCouncil_ID());

            location.setNumExt(origin.getNumExt()!= null?origin.getNumExt():"");
            location.setNumIn(origin.getNumIn() != null?origin.getNumIn():"");


        }catch (Exception e) {
            s_log.log(Level.SEVERE, e.getMessage());
            location = null;
        }
        
        return location;
    }
    

    /**
     * Reemplaza una direccion por la de otro objeto direccion
     * @param origin
     * @param EXME_CtaPacExt_ID
     * @param trxName
     * @return
     */
    public static MLocation copyFrom(Properties ctx, MLocation origin, MLocation location, String trxName){
        
        try{
            
            location.setAD_Client_ID(origin.getAD_Client_ID());
            location.setAD_Org_ID(origin.getAD_Org_ID());
            location.setIsActive(origin.isActive());
            location.setAddress1(origin.getAddress1());
            location.setAddress2(origin.getAddress2());
            location.setCity(origin.getCity());
            location.setPostal(origin.getPostal());
            location.setPostal_Add(origin.getPostal_Add());
            location.setC_Country_ID(origin.getC_Country_ID());
            location.setC_Region_ID(origin.getC_Region_ID());
            location.setC_City_ID(origin.getC_City_ID());
            location.setRegionName(origin.getRegionName());
            location.setAddress3(origin.getAddress3());
            location.setAddress4(origin.getAddress4());
            location.setEXME_TownCouncil_ID(origin.getEXME_TownCouncil_ID());

            if(origin.getNumExt() != null)
            	location.setNumExt(origin.getNumExt());
            if(origin.getNumIn() != null)
            	location.setNumIn(origin.getNumIn());


        }catch (Exception e) {
            s_log.log(Level.SEVERE, e.getMessage());
            location = null;
        }
        
        return location;
    }
    
    /**
     * Es direccion de algun Socio de negocios ?
     * @param ctx
     * @param C_Location_ID
     * @param trxName
     * @return
     */
	public static boolean esDireccionDeSocio (Properties ctx, int C_Location_ID, int extensionNo, String trxName)
	{
		boolean loc = false;
		String sql = "SELECT * FROM C_BPartner_Location WHERE C_Location_ID=?";
        PreparedStatement pstmt = null;//Expert
        ResultSet rs = null;//Expert
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Location_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				loc = true;
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql + " - " + C_Location_ID, e);
        }finally {//Expert
        	DB.close(rs,pstmt);
            pstmt=null;
            rs=null;
		}
		return loc;
	}	//	getBPLocation


    /**
     * Es direccion de algun Socio de negocios ?
     * @param ctx
     * @param C_Location_ID
     * @param trxName
     * @return
     */
	public static boolean esDireccionDeExtension (Properties ctx, int C_Location_ID, int extensionNo, String trxName)
	{
		boolean loc = false;
		String sql = "SELECT * FROM EXME_CtaPacExt WHERE C_Location_ID = ? and ExtensionNo <> ? ";
        PreparedStatement pstmt = null;//Expert
        ResultSet rs = null;//Expert
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Location_ID);
			pstmt.setInt(2, extensionNo);
			rs = pstmt.executeQuery();
			if (rs.next())
				loc = true;
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql + " - " + C_Location_ID, e);
        }finally {//Expert
        	DB.close(rs,pstmt);
            pstmt=null;
            rs=null;
		}
		return loc;
	}	//	getBPLocation

}
