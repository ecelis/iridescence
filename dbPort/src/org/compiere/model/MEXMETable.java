//CREADA EL DIA 06/JUNIO/2007

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;

public class MEXMETable extends X_AD_Table{

	 /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	public MEXMETable(Properties ctx, int EXME_Table_ID, String trxName) {
	        super(ctx, EXME_Table_ID, trxName);
	    }
	 
	 public MEXMETable(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }
	
	
	 public static boolean  volumen(Properties ctx, String nombreTabla, String trxName) {
	       // boolean isValido = true;
	        
	        String sql = " SELECT ISHIGHVOLUME FROM AD_TABLE " +
	                " WHERE isActive = 'Y' and tablename = '"+nombreTabla+"' " ;
	        
	        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "AD_Table");
	                            
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        boolean alto = false;
	        try {
	            pstmt = DB.prepareStatement(sql,trxName);
	            
	            rs = pstmt.executeQuery();
	        
	            if(rs.next()){
	            	//La columna de la base de datos es String
	            	if(rs.getString(1).trim().equalsIgnoreCase("Y"))
	            		alto = true;
	            } 
	        }
	        catch (SQLException e) {
	            e.printStackTrace();//FIXME
	        } finally {
	        	DB.close(rs, pstmt);
	            rs = null;
	            pstmt = null;
	        }
	        return alto;
	 }
}
