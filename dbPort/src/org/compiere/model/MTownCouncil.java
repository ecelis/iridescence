package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	TownCouncil Model
 *	
 *  @author Valentin Garcia
 *  @version $Id: MTownCouncil.java,$
 */
public final class MTownCouncil extends X_EXME_TownCouncil
//implements Comparator, Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


/**
 * 	Load TownCouncil (cached)
 *	@param ctx context
 */
private static void loadAllTownCouncil(Properties ctx)
{
	s_townCouncil = new CCache<String,MTownCouncil>("EXME_TownCouncil", 100);
	String sql = "SELECT * FROM EXME_TownCouncil  WHERE IsActive='Y'";
	PreparedStatement pstmt = null;
    ResultSet rs = null;
	try
	{
		pstmt = DB.prepareStatement(sql, null);
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			MTownCouncil t = new MTownCouncil (ctx, rs, null);
			s_townCouncil.put(String.valueOf(t.getEXME_TownCouncil_ID()), t);
			if (t.isDefault())
				s_default = t;
		}
	}
	catch (SQLException e)
	{
		s_log.log(Level.SEVERE, sql, e);
    }finally {
        DB.close(rs,pstmt);
        pstmt=null;
        rs=null;
	}
	s_log.fine(s_townCouncil.size() + " - default=" + s_default);
}	//	loadAllRegions

/**
 * 	Get TownCouncil (cached)
 * 	@param ctx context
 *	@param C_TownCouncil_ID ID
 *	@return TownCouncil
 */
public static MTownCouncil get (Properties ctx, int EXME_TownCouncil_ID)
{
	if (s_townCouncil == null || s_townCouncil.size() == 0)
		loadAllTownCouncil(ctx);
	String key = String.valueOf(EXME_TownCouncil_ID);
	MTownCouncil t = (MTownCouncil)s_townCouncil.get(key);
	if (t != null)
		return t;
	t = new MTownCouncil (ctx, EXME_TownCouncil_ID, null);
	if (t.getEXME_TownCouncil_ID() == EXME_TownCouncil_ID)
	{
		s_townCouncil.put(key, t);
		return t;
	}
	return null;
}	//	get

/**
 * 	Get Default TownCouncil
 * 	@param ctx context
 *	@return TownCouncil or null
 */
public static MTownCouncil getDefault (Properties ctx)
{
	if (s_townCouncil == null || s_townCouncil.size() == 0)
		loadAllTownCouncil(ctx);
	return s_default;
}	//	get

/**
 *	Return TownsCouncil as Array
 * 	@param ctx context
 *  @return TownCouncil Array
 */
@SuppressWarnings("unchecked")
public static MTownCouncil[] getTownCouncil(Properties ctx)
{
	if (s_townCouncil == null || s_townCouncil.size() == 0)
		loadAllTownCouncil(ctx);
	MTownCouncil[] retValue = new MTownCouncil[s_townCouncil.size()];
	s_townCouncil.values().toArray(retValue);
	Arrays.sort(retValue, new MTownCouncil(ctx, 0, null));
	return retValue;
}	//	getTownCouncil

/**
 *	Return Array of Towns Council of Region
 * 	@param ctx context
 *  @param C_Region_ID
 *  @return MTownCouncil Array
 */
@SuppressWarnings("unchecked")
public static MTownCouncil[] getTownCouncil (Properties ctx, int C_Region_ID)
{
	if (s_townCouncil == null || s_townCouncil.size() == 0)
		loadAllTownCouncil(ctx);
	ArrayList<MTownCouncil> list = new ArrayList<MTownCouncil>();
	Iterator<MTownCouncil> it = s_townCouncil.values().iterator();
	while (it.hasNext())
	{
		MTownCouncil m = it.next();
		if (m.getC_Region_ID() == C_Region_ID)
			list.add(m);
	}
	//  Sort it
	MTownCouncil[] retValue = new MTownCouncil[list.size()];
	list.toArray(retValue);
	Arrays.sort(retValue, new MTownCouncil(ctx, 0, null));
	return retValue;
}	//	getTownCouncil

/**	Municipio Cache				*/
private static CCache<String,MTownCouncil> s_townCouncil = null;
/** Default Municipio				*/
private static MTownCouncil		s_default = null;
/**	Static Logger				*/
private static CLogger		s_log = CLogger.getCLogger (MTownCouncil.class);


/**************************************************************************
 *	Create empty TownCouncil
 * 	@param ctx context
 * 	@param EXME_TownCouncil_ID id
 *	@param trxName transaction
 */
public MTownCouncil (Properties ctx, int EXME_TownCouncil_ID, String trxName)
{
	super (ctx, EXME_TownCouncil_ID, trxName);
	if (EXME_TownCouncil_ID == 0)
	{
	}
}   //  MMunic

/**
 *	Create Municipio from current row in ResultSet
 * 	@param ctx context
 *  @param rs result set
 *	@param trxName transaction
 */
public MTownCouncil (Properties ctx, ResultSet rs, String trxName)
{
	super(ctx, rs, trxName);
}	//	MMunicipio

/**
 * 	Parent Constructor
 *	@param region region
 *	@param municipioName Municipio Name
 */
public MTownCouncil (MRegion region, String townCouncilName)
{
	super (region.getCtx(), 0, region.get_TrxName());
	setC_Region_ID(region.getC_Region_ID());
	setName(townCouncilName);
}   //  MTownCouncil

/**
 *	Return Name
 *  @return Name
 */
public String toString()
{
	return getName();
}   //  toString

/**
 *  Compare
 *  @param o1 object 1
 *  @param o2 object 2
 *  @return -1,0, 1
 */
public int compare(Object o1, Object o2)
{
	String s1 = o1.toString();
	if (s1 == null)
		s1 = "";
	String s2 = o2.toString();
	if (s2 == null)
		s2 = "";
	return s1.compareTo(s2);
}	//	compare
}// MMunicipio