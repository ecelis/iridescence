package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMENacionalidad extends X_EXME_Nacionalidad{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -7653537669282592657L;

	/**
     * @param ctx
     * @param EXME_Nacionalidad_ID
     * @param trxName
     */
	public MEXMENacionalidad(Properties ctx, int EXME_Nacionalidad_ID, String trxName) {
        super(ctx, EXME_Nacionalidad_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMENacionalidad(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Metodo que nos trae <strong> Description </strong> y <strong> EXME_Nacionalidad_ID </strong>
     * de todas las nacionalidades existentes para poder desplegarlas en un combo
     * @param ctx
     * @param trxName
     * @return
     */
    public static List<KeyNamePair> getAll(Properties ctx, String trxName)  {
    	final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EXME_Nacionalidad_ID, Description ");
    	sql.append(" FROM EXME_Nacionalidad WHERE isActive='Y' ");		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));	
		sql.append(" ORDER BY Description");
    	return Query.getListKN(sql.toString(), trxName);	
    }
    
    /**
     * Regresa la nacionalidad por defecto del sistema
     * @param ctx
     * @param trxName
     * @return
     */
    public static int getDefault(Properties ctx, String trxName)  {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EXME_Nacionalidad_ID ");
    	sql.append(" FROM EXME_Nacionalidad WHERE isActive='Y' AND isDefault='Y' ");		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
    	return DB.getSQLValue(trxName, sql.toString());	
    }
    
    /**
     * Regresa la nacionalidad relacionada a un pais
     * @param ctx
     * @param trxName
     * @return
     */
    public static int getByCountry(Properties ctx, int countryId, String trxName)  {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EXME_Nacionalidad_ID ");
    	sql.append(" FROM EXME_Nacionalidad  ");		
    	sql.append(" WHERE isActive='Y'AND C_Country_ID=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
    	return DB.getSQLValue(trxName, sql.toString(), countryId);	
    }
}
