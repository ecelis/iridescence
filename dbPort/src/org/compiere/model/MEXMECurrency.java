package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;

/**
 * Modelo de Moneda (hereda de MCurrency)
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/08/31 15:29:06 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.4 $
 */public class MEXMECurrency extends MCurrency {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMECurrency.class);

	/**
	 * 	Currency Constructor
	 *	@param ctx context
	 *	@param C_Currency_ID id
	 */

	public MEXMECurrency (Properties ctx, int C_Currency_ID, String trxName)
	{
		super (ctx, C_Currency_ID, trxName);
		if (C_Currency_ID == 0)
		{
			setIsEMUMember (false);
			setIsEuro (false);
			setStdPrecision (2);
			setCostingPrecision (4);
		}
	}	//	MEXMECurrency

	/**
	 * 	Currency Constructor
	 *	@param ctx context
	 *	@param ISO_Code ISO
	 *	@param Description Name
	 *	@param CurSymbol symbol
	 *	@param StdPrecision prec
	 *	@param CostingPrecision prec
	 */
	public MEXMECurrency (Properties ctx, String ISO_Code,
		String Description, String CurSymbol, int StdPrecision, int CostingPrecision, String trxName)
	{
		super(ctx, 0, trxName);
		setISO_Code(ISO_Code);
		setDescription(Description);
		setCurSymbol(CurSymbol);
		setStdPrecision (StdPrecision);
		setCostingPrecision (CostingPrecision);
		setIsEMUMember (false);
		setIsEuro (false);
	}	//	MEXMECurrency
   
	/**
	* Obtenemos las monedas para la empresa y organizacion logeada
	* en el idioma logeado
	*
	*  @param ctx Contexto
	*  @return Una lista con las monedas
	*/
	public static MEXMECurrency[] get(Properties ctx, String trxName) {
		//List lstResultado = new ArrayList();
		
		String idioma = Env.getContext(ctx, "#AD_Language");
		boolean base = Language.isBaseLanguage(idioma);
		String sql = null;
		
		ArrayList<MEXMECurrency> list = new ArrayList<MEXMECurrency>();
		//Moneda por empresa y organizacion
		if(!base)
			//Si no es el idioma base => cargar traducciones
			sql = "SELECT c.C_Currency_ID, t.Description "
			    + "FROM C_Currency c, C_Currency_Trl t "
			    + "WHERE c.C_Currency_ID = t.C_Currency_ID AND "
			    + "c.AD_Client_ID IN (0, ?) AND "
			    + "c.AD_Org_ID IN (0, ?) AND "
			    + "t.AD_Language = ? AND "
			    + "c.IsActive = 'Y' "
			    + "ORDER BY t.Description";
		else
			//Si es el idioma base => tomar el nombre de C_Currency
			sql = "SELECT c.C_Currency_ID, c.Description "
			    + "FROM C_Currency c "
			    + "WHERE c.AD_Client_ID IN (0, ?) AND "
			    + "c.AD_Org_ID IN (0, ?) AND "
			    + "c.IsActive = 'Y' "
			    + "ORDER BY c.Description";
			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, Env.getContextAsInt(ctx, "#AD_Client_ID")); 
			pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
			if(!base) 
				pstmt.setString(3, idioma);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
			    MEXMECurrency moneda = new MEXMECurrency(ctx, rs.getInt(1), trxName);
				list.add(moneda);
			}
           
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECurrency.get", e);
        }finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs=null;
        }
		//
		MEXMECurrency[] motivo = new MEXMECurrency[list.size()];
		list.toArray(motivo);
		return motivo;
	}
	
    /**
    * Obtenemos las monedas para la empresa y organizacion logeada
    * en el idioma logeado
    *
    *  @param ctx Contexto
    *  @return Una lista con las monedas
    */
    public static List<KeyNamePair> getMoneda(Properties ctx) throws Exception {
        List<KeyNamePair> lstResultado = new ArrayList<KeyNamePair>();
        
        String idioma = Env.getContext(ctx, "#AD_Language");
        boolean base = Language.isBaseLanguage(idioma);
        String sql = null;
        
        //Moneda por empresa y organizacion
        if(!base)
            //Si no es el idioma base => cargar traducciones
            sql = "SELECT c.C_Currency_ID, t.Description "
                + "FROM C_Currency c, C_Currency_Trl t "
                + "WHERE c.C_Currency_ID = t.C_Currency_ID AND "
                + "c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "t.AD_Language = ? AND "
                + "c.IsActive = 'Y' "
                + "ORDER BY t.Description";
        else
            //Si es el idioma base => tomar el nombre de C_Currency
            sql = "SELECT c.C_Currency_ID, c.Description "
                + "FROM C_Currency c "
                + "WHERE c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "c.IsActive = 'Y' "
                + "ORDER BY c.Description";
            
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setLong(1, Env.getContextAsInt(ctx, "#AD_Client_ID")); 
            pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
            if(!base) 
                pstmt.setString(3, idioma);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
            	KeyNamePair knp = new KeyNamePair(rs.getInt("C_Currency_ID"),rs.getString("Description"));
                lstResultado.add(knp);
            }

        } catch (SQLException e) {
            throw new Exception("error.caja.moneda");
        }finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs=null;
        }
        return lstResultado;
    }
    
    public static List<KeyNamePair> getISO(Properties ctx) throws Exception {
        List<KeyNamePair> lstResultado = new ArrayList<KeyNamePair>();
        
        String idioma = Env.getContext(ctx, "#AD_Language");
        boolean base = Language.isBaseLanguage(idioma);
        String sql = null;
        
        //Moneda por empresa y organizacion
        if(!base)
            //Si no es el idioma base => cargar traducciones
            sql = "SELECT c.C_Currency_ID, c.iso_code "
                + "FROM C_Currency c, C_Currency_Trl t "
                + "WHERE c.C_Currency_ID = t.C_Currency_ID AND "
                + "c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "t.AD_Language = ? AND "
                + "c.IsActive = 'Y' "
                + "ORDER BY c.iso_code";
        else
            //Si es el idioma base => tomar el nombre de C_Currency
            sql = "SELECT c.C_Currency_ID, c.iso_code "
                + "FROM C_Currency c "
                + "WHERE c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "c.IsActive = 'Y' "
                + "ORDER BY c.iso_code";
            
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setLong(1, Env.getContextAsInt(ctx, "#AD_Client_ID")); 
            pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
            if(!base) 
                pstmt.setString(3, idioma);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
            	KeyNamePair knp = new KeyNamePair(rs.getInt("C_Currency_ID"),rs.getString("iso_code"));
                lstResultado.add(knp);
            }

        } catch (SQLException e) {
            throw new Exception("error.caja.moneda");
        }finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs=null;
        }
        return lstResultado;
    }

    

    /**
    * Obtenemos el nombre de una moneda en el idioma logeado para el id dado
    *
    *  @param ctx Contexto
    *  @param id El identificador de la moneda
    *  @return El nombre de la moneda
    */
    public static String getMonedaDescription(Properties ctx, int moneda) throws Exception {
        String description = null;
        String idioma = Env.getContext(ctx, "#AD_Language");
        boolean base = Language.isBaseLanguage(idioma);
        String sql = null;
        
        //Moneda por empresa y organizacion
        if(!base)
            //Si no es el idioma base => cargar traducciones
            sql = "SELECT t.Description "
                + "FROM C_Currency c, C_Currency_Trl t "
                + "WHERE c.C_Currency_ID = t.C_Currency_ID AND "
                + "c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "c.C_Currency_ID = ? AND "
                + "t.AD_Language = ? AND "
                + "c.IsActive = 'Y' "
                + "ORDER BY t.Description";
        else
            //Si es el idioma base => tomar el nombre de C_Currency
            sql = "SELECT c.Description "
                + "FROM C_Currency c "
                + "WHERE c.AD_Client_ID IN (0, ?) AND "
                + "c.AD_Org_ID IN (0, ?) AND "
                + "c.IsActive = 'Y' AND "
                + "c.C_Currency_ID = ? "
                + "ORDER BY c.Description";
            
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setLong(1, Env.getContextAsInt(ctx, "#AD_Client_ID")); 
            pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
            pstmt.setLong(3, moneda);

            if(!base) 
                pstmt.setString(4, idioma);
            rs = pstmt.executeQuery();
            if(rs.next()){
                description = rs.getString("Description");
            }

        } catch (SQLException e) {
            throw new Exception("error.caja.moneda");
        }finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs=null;
        }
        return description;
    }
    
    
    /**
     * Obtenemos el nombre de una moneda en el idioma logeado para el id dado
     *
     *  @param ctx Contexto
     *  @param id El identificador de la moneda
     *  @return El nombre de la moneda
     */
     public String getMonedaDescription() throws Exception {
         
    	 String description = "";
         String idioma = Env.getContext(getCtx(), "#AD_Language");
         boolean base = Language.isBaseLanguage(idioma);
         StringBuilder sql = new StringBuilder(200);
         
         //Moneda por empresa y organizacion
         if(!base)
        	 //Si no es el idioma base => cargar traducciones
        	 sql.append(" SELECT t.Description ")
        	 .append(" FROM C_Currency INNER JOIN C_Currency_Trl t ON (  C_Currency.C_Currency_ID = t.C_Currency_ID )")
        	 .append(" WHERE C_Currency.IsActive = 'Y'  ")
        	 .append(" AND C_Currency.C_Currency_ID = ?  ")
        	 .append(" AND t.AD_Language = ? ")
        	 .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_Currency"))
        	 .append(" ORDER BY t.Description");
         else
        	 //Si es el idioma base => tomar el nombre de C_Currency
        	 sql.append(" SELECT C_Currency.Description ")
        	 .append(" FROM C_Currency c ")
        	 .append(" WHERE C_Currency.IsActive = 'Y' ")
        	 .append(" AND  C_Currency.C_Currency_ID = ? ")
        	 .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_Currency"))
        	 .append(" ORDER BY C_Currency.Description");
         
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
             pstmt = DB.prepareStatement(sql.toString(), null);
             pstmt.setLong(1, getC_Currency_ID());
             if(!base) 
                 pstmt.setString(2, idioma);
             rs = pstmt.executeQuery();
             if(rs.next()){
                 description = rs.getString("Description");
             }

         } catch (SQLException e) {
             throw new Exception("error.caja.moneda");
         }finally {
        	 DB.close(rs, pstmt);
             pstmt = null;
             rs=null;
         }
         return description;
     }
}