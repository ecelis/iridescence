/*
 * Created on 7/07/2005
 *
 */
package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.M_Element;
import org.compiere.model.MTable;
import org.compiere.model.MWindow;

/**
 * @author Hassan Reyes
 * 
 * Le agrega internacionalizacion.
 *
 */
public class MTranslation {
    
    /**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (MTranslation.class);

    public static M_Element getElement(Properties ctx, String columnName, String language){
        
        if (columnName == null || columnName.length() == 0)
			return null;
        
        // Si es Ingles, No necesitamos ir a la traduccion.
        if(language == null || language.equals("") || 
                language.equals("en_US") || language.substring(0,2).equals("en_")){
            return M_Element.get(ctx, columnName);
        }
        
		M_Element retValue = null;
		String sql = "SELECT * FROM AD_Element_Trl trl, AD_Element e WHERE e.AD_Element_ID = trl.AD_Element_ID AND UPPER(e.ColumnName)=? AND trl.AD_Language = ?";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString (1, columnName.toUpperCase().trim());
			pstmt.setString (2, language);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new M_Element (ctx, rs.getInt(1), null);
				retValue.setName(rs.getString("Name"));
				retValue.setPrintName(rs.getString("PrintName"));
				retValue.setDescription(rs.getString("Description"));
				retValue.setHelp(rs.getString("Help"));
				
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
            if (rs != null)
                rs.close ();
            rs = null;
		}
		catch (Exception e)
		{
			pstmt = null;
            rs = null;
		}
		return retValue;
    }

    /**
     * Regresa un String con el Nombre del Elemento.
     * Si este no se encuentra entonces regresa "".
     * @param ctx
     * @param columnName
     * @param language
     * @return
     */
    public static String getElement_Name(Properties ctx, String columnName, String language){
        String name = "";
        M_Element element = getElement(ctx, columnName, language);
        
        if(element != null)
            name = element.getName();
        
        return name;
    }
    
    /**
     * 
     * @param ctx
     * @param windowName
     * @param language
     * @return
     */
    public static MWindow getWindow(Properties ctx, String windowName, String language){
        
        if (windowName == null || windowName.length() == 0)
			return null;
        
        // Si es Ingles, No necesitamos ir a la traduccion.
        if(language == null || language.equals("") || 
                language.equals("en_US") || language.substring(0,2).equals("en_")){
            return new MWindow(ctx
                    , DB.getSQLValue(null, "SELECT AD_WINDOW_ID FROM AD_WINDOW WHERE UPPER(NAME) = '" + windowName.toUpperCase() + "'")
                    , null);
        }
        
		MWindow retValue = null;
		String sql = "SELECT * FROM AD_Window_Trl trl, AD_Window w WHERE w.AD_Window_ID = trl.AD_Window_ID AND TRIM(UPPER(w.Name))=? AND trl.AD_Language = ?";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString (1, windowName.toUpperCase().trim());
			pstmt.setString (2, language);
			rs = pstmt.executeQuery ();
			if (rs.next ()){
				retValue = new MWindow (ctx, rs.getInt(1), null);
				retValue.setName(rs.getString("Name"));
				retValue.setDescription(rs.getString("Description"));
				retValue.setHelp(rs.getString("Help"));
            }	
			rs.close ();
			pstmt.close ();
			pstmt = null;
            rs = null;
			
			//sino encontro traduccion regresara en el idioma del systema (ingles)
			//String trxName = null;
			if(retValue == null)
			    return new MWindow(ctx
	                    , DB.getSQLValue(null, "SELECT AD_WINDOW_ID FROM AD_WINDOW WHERE UPPER(NAME) = '" + windowName.toUpperCase() + "'")
	                    , null);
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
            if (rs != null)
                rs.close ();
            rs = null;
		}
		catch (Exception e)
		{
			pstmt = null;
            rs = null;
		}
		return retValue;
    }
    
    /**
     * Regresa un String con el Nombre de la Ventana.
     * Si este no se encuentra entonces regresa "".
     * @param ctx
     * @param windowName
     * @param language
     * @return
     */
    public static String getWindow_Name(Properties ctx, String windowName, String language){
        String name = "";
        MWindow window = getWindow(ctx, windowName, language);
        
        if(window != null)
            name = window.getName();
        
        return name;
    }
    
    /**
     * Regresa un String con el Nombre de la Ventana.
     * Si este no se encuentra entonces regresa "".
     * @param ctx
     * @param windowName
     * @param language
     * @return
     */
    public static String getWindow_NameFromTable(Properties ctx, String tableName, String language){
        String name = "";
        MTable table = getTable(ctx, tableName, language);
        
        if(table == null )
        	return name;
        
        if ( !table.isView()){
        	MWindow window = new MWindow(ctx, table.getAD_Window_ID(), table.get_TrxName());
        	
        	if(window .get_ID()>0){
	        	if(language == null || language.equals("") || 
	        			language.equals("en_US") || language.substring(0,2).equals("en_")){
	        		name = window.getName();
	        	}else{
	        		name = getWindow_Name(ctx, window.getName(), language);
	        	}
        	} else {
        		if(language == null || language.equals("") || 
            			language.equals("en_US") || language.substring(0,2).equals("en_")){
            		name = table.getName();
            	}else{
            		name = getTable_Name(ctx, tableName, language);
            	}        	
        	}
        	
        } else { 
        	if(language == null || language.equals("") || 
        			language.equals("en_US") || language.substring(0,2).equals("en_")){
        		name = table.getName();
        	}else{
        		name = getTable_Name(ctx, tableName, language);
        		
        	}        	

        }
        return name;
    }
    
    /**
     * 
     * @param ctx
     * @param TableName
     * @param language
     * @return
     */
    public static MTable getTable(Properties ctx, String tableName, String language){
        
        if (tableName == null || tableName.length() == 0)
			return null;
        
        // Si es Ingl�s, No necesitamos ir a la traducci�n.
        if(language == null || language.equals("") || 
                language.equals("en_US") || language.substring(0,2).equals("en_")){
            return new MTable(ctx
                    , DB.getSQLValue(null, "SELECT AD_Table_ID FROM AD_Table WHERE UPPER(TABLENAME) = '" + tableName.toUpperCase() + "'")
                    , null);
        }
        
		MTable retValue = null;
		String sql = " SELECT t.AD_Table_ID, trl.Name as Name FROM AD_Table_Trl trl, AD_Table t " +
				" WHERE T.AD_Table_ID = trl.AD_Table_ID AND UPPER(t.TableName)=? " +
				" AND trl.AD_Language = ?";
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString (1, tableName.toUpperCase().trim());
			pstmt.setString (2, language);
			rs = pstmt.executeQuery ();
			if (rs.next ()){
				retValue = new MTable (ctx, rs.getInt("AD_Table_ID"), null);
				retValue.setName(rs.getString("Name"));
			}	
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
            if (rs != null)
                rs.close ();
            rs = null;
		}
		catch (Exception e)
		{
			pstmt = null;
            rs = null;
		}
		return retValue;
    }
    
    /**
     * Regresa un String con el Nombre de la Tabla.
     * Si este no se encuentra entonces regresa "".
     * @param ctx
     * @param TableName
     * @param language
     * @return
     */
    public static String getTable_Name(Properties ctx, String tableName, String language){
        String name = "";
        MTable table = getTable(ctx, tableName, language);
        
        if(table != null)
            name = table.getName();
        
        return name;
    }
}
