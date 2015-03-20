package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MClasCliente extends X_EXME_ClasCliente{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MClasCliente.class);
	/**	ClasCliente Cache				*/
	private static CCache<String,MClasCliente> s_clasCliente = null;

	/**
	 * 
	 * @param ctx
	 * @param EXME_INP_AdmisionHosp_ID
	 * @param trxName
	 */
	public MClasCliente(Properties ctx, int EXME_ClasCliente_ID, String trxName) {
		super(ctx, EXME_ClasCliente_ID, trxName);
	}
	
	   /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MClasCliente(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
	 * Recuperamos un registro de clasificacion del cliente con respecto al Value
	 * @param ctx
	 * @param Value
	 * @param trxName
	 * @return 
	 * @throws SQLException
	 */
	public static MClasCliente getForValue(Properties ctx, String value, String trxName) throws SQLException {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MClasCliente clasificacion = null;

		try {

			sql.append("SELECT * FROM EXME_ClasCliente ")
			.append("WHERE IsActive = 'Y' AND Value = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				clasificacion = new MClasCliente(ctx, rs, trxName);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getForExpediente (" + sql + ")", e);
			throw e;
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}

		return clasificacion;
	}
	
	/**
	 *	Return Array of Towns Council of Region
	 * 	@param ctx context
	 *  @param C_Region_ID
	 *  @return MTownCouncil Array
	 */
	@SuppressWarnings("unchecked")
	public static MClasCliente[] getClasificaciones(Properties ctx)
	{
		if (s_clasCliente == null || s_clasCliente.size() == 0)
			loadAllTownCouncil(ctx);
		ArrayList<MClasCliente> list = new ArrayList<MClasCliente>();
		Iterator<MClasCliente> it = s_clasCliente.values().iterator();
		while (it.hasNext())
		{
			MClasCliente c = it.next();
				list.add(c);
		}
		//  Sort it
		MClasCliente[] retValue = new MClasCliente[list.size()];
		list.toArray(retValue);
		Arrays.sort(retValue, new MClasCliente(ctx, 0, null));
		return retValue;
	}	//	getTownCouncil
	
	
	/**
	 * 	Load TownCouncil (cached)
	 *	@param ctx context
	 */
	private static void loadAllTownCouncil(Properties ctx)
	{
		s_clasCliente = new CCache<String,MClasCliente>("EXME_ClasCliente", 100);
		String sql = "SELECT * FROM EXME_ClasCliente WHERE IsActive='Y' ";
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_ClasCliente");
        Statement stmt = null;
        ResultSet rs = null;
        
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MClasCliente c = new MClasCliente (ctx, rs, null);
				s_clasCliente.put(String.valueOf(c.getEXME_ClasCliente_ID()), c);
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, stmt);
            rs = null;
            stmt = null;
        }
		s_log.fine(s_clasCliente.size() + " - default=");
	}	//	loadAllRegions
    
    
    /**
     * Regresa ID de la clasificacion
     * @param Value 
     * @return
     */
    public static int getEXME_ClasCliente_ID( Properties ctx, String value)
        {       
		String sql = " SELECT EXME_ClasCliente_ID FROM  EXME_ClasCliente WHERE IsActive = 'Y' AND upper(Value) like  '%"
				+ value + "%' ";
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_ClasCliente");
		return DB.getSQLValue(null, sql);
    }
}