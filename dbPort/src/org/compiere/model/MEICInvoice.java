/*
 * Created on 11-mar-2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;

/**
 * Modelo de eicinvoices
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/11/02 21:34:31 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.17 $
 */
public class MEICInvoice extends X_EI_C_Invoice {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6795361296617581479L;

	public static final String EI_C_Invoice_ID = "EI_C_Invoice_ID"; 
    
    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEICInvoice.class);



    /**
     * @param ctx
     * @param EI_C_Invoice_ID
     * @param trxName
     */
    public MEICInvoice(Properties ctx, int EI_C_Invoice_ID, String trxName) {
        super(ctx, EI_C_Invoice_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEICInvoice(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


 
    
    /**
     * Obtenemos los eicinvoices a partir de ciertos criterios.
     * incluyendo aquellos que sean facturadores especiales
     * @param ctx
     * @param where
     * @param all Indica si se retornan tambien inactivos o solo activos.
     * @param rowNum Numero m�ximo de registros a traer
     * @return
     * @deprecated Este metodo no tiene referencia alguna, no se cambio el rownum.
     * @author jcantu Modificado el 12 de Julio del 2012.
     */
	public static MEICInvoice[] getAll(Properties ctx, String whereClause, String orderBy
                                , String trxName, boolean all, int rowNum) {
        
        @SuppressWarnings("unused")
		Properties prop = WebEnv.getXPT_Properties();

        ArrayList<MEICInvoice> list = new ArrayList<MEICInvoice>();
        String sql = "SELECT * FROM (SELECT * FROM EI_C_Invoice WHERE AD_Client_ID = ? "; 

        if(!all){
            sql += "AND isActive = 'Y' ";
        }
        
        if (whereClause != null)
            sql +=  whereClause;

        if(orderBy != null && orderBy.length() > 0)
            sql += " ORDER BY " + orderBy;
        else
            sql += " ORDER BY EI_C_Invoice_ID DESC ";

        // Limitamos el numero de registros regresados.
        sql += ") WHERE ROWNUM <= " + rowNum;
             
        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
            //pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID")); -- Busqueda a Nivel Cliente.
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MEICInvoice pac = new MEICInvoice(ctx, rs, trxName);
                list.add(pac);
            }
            rs.close();
            pstmt.close();
            pstmt = null;
	    rs = null;
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "get", e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close ();
            } catch (Exception e) {}
            pstmt = null;
        }

        //
        MEICInvoice[] pacs = new MEICInvoice[list.size()];
        list.toArray(pacs);
        return pacs;

    }
    
    /**
	 * Obtenemos los eicinvoices a partir de ciertos criterios.
	 * @param ctx
	 * @param where
	 * @param all Indica si se retornan tambien inactivos o solo activos.
	 * @return
	 */
	public static MEICInvoice[] get(Properties ctx, String whereClause, String orderBy
	                            , String trxName, boolean all) {
	    
	    Properties prop = WebEnv.getXPT_Properties();

		ArrayList<MEICInvoice> list = new ArrayList<MEICInvoice>();
		String sql = "SELECT * FROM (SELECT * FROM EI_C_Invoice WHERE AD_Client_ID = ? "; 

		if(!all){
		    sql += "AND isActive = 'Y' ";
		}
		
		sql += "AND IsFactEspec = 'N'";
		    
		if (whereClause != null)
			sql +=  whereClause;

		if(orderBy != null && orderBy.length() > 0)
			sql += " ORDER BY " + orderBy;
		else
		    sql += " ORDER BY EI_C_Invoice_ID DESC ";

		// Limitamos el numero de registros regresados.
		sql += ") WHERE ROWNUM <= " + prop.getProperty(WebEnv.NoRegistros);
		     
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
			//pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID")); -- Busqueda a Nivel Cliente.
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
			    MEICInvoice pac = new MEICInvoice(ctx, rs, trxName);
				list.add(pac);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close ();
			} catch (Exception e) {}
			pstmt = null;
		}

		//
		MEICInvoice[] pacs = new MEICInvoice[list.size()];
		list.toArray(pacs);
		return pacs;

	}
	
	/**
	 * Crea un "template" para crear un nuevo eicinvoice. 
	 * @return
	 */
	public static MEICInvoice createTemplate(Properties ctx, String trxName){
	    
	    MEICInvoice eicinvoice = null;
	    
	    eicinvoice = new MEICInvoice(ctx, 0, trxName);
	    
	    // Los eicinvoices siempre seran org *
	    eicinvoice.setAD_Org_ID(0);
	    
	    // Establecemos los valores por defecto.
	    
	    return eicinvoice;
	    
	}
	

	

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord) {
	    

		return true;
	}	//	beforeSave
	
	/**
	 * 
	 */
    protected boolean beforeDelete() {
        

        
        return true;
        
    }
    
    /**
     * WARNING: Creado para Interfaces Medsys.
     * @author hassan reyes
     */
    public void setClientOrg(int ad_client_id, int ad_org_id){
        setAD_Client_ID(ad_client_id);
        setAD_Org_ID(ad_org_id);
    }

  

	
    /**
     * Crea una copia a partir de un origen
     * @param origin
     * @param EXME_CtaPacExt_ID
     * @param trxName
     * @return
     */
    public static MEICInvoice copyFrom(Properties ctx, MEICInvoice origin, int EI_C_Invoice_ID, String trxName){
        
        MEICInvoice eicinvoice = null;
        
            
            eicinvoice = new MEICInvoice(ctx, EI_C_Invoice_ID, trxName);
            
            eicinvoice.setAD_Client_ID(origin.getAD_Client_ID());
            eicinvoice.setAD_Org_ID(origin.getAD_Org_ID());
            eicinvoice.setErrorDescription(origin.getErrorDescription());
            
          
        
        return eicinvoice;
    } 
}