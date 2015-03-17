
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;


public class MEXMEElementValue extends MElementValue{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	@SuppressWarnings("unused")
	private static CLogger		s_log = CLogger.getCLogger (MEXMEElementValue.class);
	
	public MEXMEElementValue(Properties ctx, int EXME_Dieta_ID, String trxName) {
		super(ctx, EXME_Dieta_ID, trxName);
	}

    public MEXMEElementValue(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
/*    public static MElementValue getByValue(Properties ctx, String elementValueValue) {
		
    	MElementValue retValue = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM C_ElementValue WHERE Value = ? AND isActive = 'Y' ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_ElementValue");

		try {
			pstmt = DB.prepareStatement(sql, null);

			pstmt.setString(1, elementValueValue);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				retValue = new MElementValue(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs,pstmt);
		}

		return retValue;
	}
    */
    /**
     * getByIds
     * 
     * Devuelve un ArrayList con los MElementValue que est�n entre un rango de Id's.
     *  Si el Id inicial es mayor al final el m�todo regresa un ArrayList vac�o.
     * @param ctx
     * @param idIni	Id inicial
     * @param idFin Id final
     * @return ArrayList con los MElementValue encontrados dentro del rango de Id's.
     * @return ArrayList vac�o si el Id inicial es mayor al final.
     */
    /*public static ArrayList getByIds(Properties ctx, int idIni, int idFin) {
		
    	ArrayList <MElementValue>retValue = null;
    	
    	if (idIni > idFin){

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM C_ElementValue WHERE C_ElementValueId BETWEEN ? AND ? AND isActive = 'Y' ";
	
			try {
				pstmt = DB.prepareStatement(sql, null);
	
				pstmt.setInt(1, idIni);
				pstmt.setInt(2, idFin);
				rs = pstmt.executeQuery();
				
				retValue = new ArrayList();
				
				while (rs.next()) {
					retValue.add(new MElementValue(ctx, rs, null));
				}
	
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "", e);
			} finally {
				DB.close(rs,pstmt);
			}
    	} else {
    		retValue = new ArrayList(0);
    	}

		return retValue;
	}*/
}
