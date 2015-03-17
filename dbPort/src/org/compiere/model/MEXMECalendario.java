/**
* @author Armando Garcia
* @version $Revision: 1.0 $
*/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMECalendario extends X_EXME_Calendario{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger      slog = CLogger.getCLogger (MEXMECalendario.class);
	
	 /**
     * @param ctx
     * @param ID
     */
    public MEXMECalendario(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);

    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMECalendario(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);

    }
    
    
public static List<String> getDayofMonth(Properties ctx, String trxName,int month,int year) throws Exception {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    List<String> resultados = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{ 
		    if(ctx == null)
	            return null;

	
		    
				sql.append(" SELECT to_char(fecha,'DD/MM/YYYY')fecha ")
						.append(" FROM exme_calendario c")
						.append(" WHERE c.mes= ")
						.append(month)
						.append(" and c.year= ")
						.append(year)
					    .append(" ORDER BY fecha"); 

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String resultado = rs.getString("fecha");
				resultados.add(resultado);
			
			}
		} catch (Exception e) { 
			slog.log(Level.SEVERE, "", e);
			throw e;
		} finally {
		    rs = null;
			pstmt = null; 
			sql = null;
		}
		
	
		return resultados; 
	}
public static String getDayofWeek(Properties ctx, String trxName,String date,boolean upper) throws Exception {
	 
	
	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    String resultado = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{ 
	    if(ctx == null)
            return null;


	    if(upper){
			sql.append(" SELECT ")
					.append("UPPER ( ")
					.append("to_char(to_date('")
					.append(date)
					.append("','DD/MM/YYYY'), 'day'))dia ")
					.append(" FROM dual"); 
			}
	    else{
	    	
			sql.append(" SELECT ")
			.append("( to_char(to_date('")
			.append(date)
			.append("','DD/MM/YYYY'), 'day'))dia ")
			.append(" FROM dual"); 
	    	
	    }
	    

		pstmt = DB.prepareStatement(sql.toString(), null);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			resultado = rs.getString("dia");
			
		
		}
	} catch (Exception e) { 
		slog.log(Level.SEVERE, "", e);
		throw e;
	} finally {
	    rs = null;
		pstmt = null; 
		sql = null;
	}
	

	return resultado; 
}

public static String getMonth(Properties ctx, String trxName,int month) throws Exception {
	
	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String resultado=null;
	try{ 
	    if(ctx == null)
            return null;
			sql.append("  SELECT DISTINCT upper(to_CHAR(TO_dATE(to_char(c.mes+1),'mm'),'month')) MES ")
					.append(" FROM exme_calendario c ")
					.append(" where c.mes= ")
					.append(month); 

		pstmt = DB.prepareStatement(sql.toString(), null);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			resultado = rs.getString("MES");		
		}
	} catch (Exception e) { 
		slog.log(Level.SEVERE, "", e);
		throw e;
	} finally {
	    rs = null;
		pstmt = null; 
		sql = null;
	}
	

	return resultado; 
}
public static List<LabelValueBean> getYears(Properties ctx, String trxName) throws Exception {
	
	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{ 
	    if(ctx == null)
            return null;

			sql.append("SELECT DISTINCT  year  ")
					.append(" FROM  exme_calendario "); 

		pstmt = DB.prepareStatement(sql.toString(), null);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			LabelValueBean resultado = new LabelValueBean(rs.getString("year"),rs.getString("year"));
			resultados.add(resultado);
		
		}
	} catch (Exception e) { 
		slog.log(Level.SEVERE, "", e);
		throw e;
	} finally {
	    rs = null;
		pstmt = null; 
		sql = null;
	}
	return resultados; 
}
}
