package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MDocMedsys  extends X_EXME_DocMedsys {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3556659379886953155L;


	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MDocMedsys.class);

    
    public static String PRENOTAEXT = "PRE NOTA DE CREDITO DE FACT POR EXTENSION";
    public static String NOTAEXT = "NOTA DE CREDITO DE FACT POR EXTENSION";
    public static String PREFACTURAEXT = "PRE FACTURA DE FACT POR EXTENSION";
    public static String FACTURAEXT = "FACTURA DE FACT POR EXTENSION";
    
    public static String PRENOTACE = "PRE NOTA DE CREDITO FACT DIRECTA";
    public static String NOTACE = "NOTA DE CREDITO DE FACT DIRECTA";
    public static String PREFACTURACE = "PRE FACTURA DE FACT DIRECTA";
    public static String FACTURACE = "FACTURA DE FACT DIRECTA";
   
    public static String PRENOTACARGO = "PRE NOTA DE CARGO";
    public static String NOTACARGO = "NOTA DE CARGO";
    
    public static String RECIBOPAGO = "RECIBO DE PAGO";
   
    
    
    /**
     * @param ctx
     * @param ID
     * @param trxName
     */
    public MDocMedsys(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MDocMedsys(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public static String get(Properties ctx, String nameFormat, String trxName){
        String name = null;
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;
        
        // Revisamos si el tipo de producto se encuentra como EXCLUIDO para el sobreprecio.
        sql.append( " SELECT f.Name FROM EXME_DocMedsys LEFT JOIN EXME_Formato f ")
           .append(" ON( f.EXME_Formato_ID = EXME_DocMedsys.EXME_Formato_ID ) " )
           .append(" WHERE EXME_DocMedsys.Name = ? "); 
            
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_DocMedsys"));
         
        try {

            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setString(1, nameFormat);
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                name = rs.getString("Name");
            }
       	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}

  
        return name;
    }
    
    /**
     * Obtenemos el nombre del documento Medsys, de acuerdo a determinada factura. 
     * @author Mike Loera
     * @param ctx
     * @param factura
     * @param dt
     * @return nombre
     */
      public static String getDocumento(Properties ctx, MInvoice factura, MDocType dt){
      	String nombre = null;
          if(!factura.isAfecta_Caja()){
              if (MDocType.DOCBASETYPE_ARInvoice.equals(dt.getDocBaseType())){
                  if(factura.getEXME_CtaPacExt_ID()>0)
                      nombre = MDocMedsys.PREFACTURAEXT;
                  else
                  	nombre = MDocMedsys.PREFACTURACE;
              }else{ 
                  if (MDocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType())){
                      if(factura.getEXME_CtaPacExt_ID()>0)
                      	nombre = MDocMedsys.PRENOTAEXT;
                      else
                      	nombre =  MDocMedsys.PRENOTACE;
                      
                  }else{
                      if (MDocType.DOCBASETYPE_ARDebitMemo.equals(dt.getDocBaseType()))
                      	nombre = MDocMedsys.PRENOTACARGO;
                  }
              }
          }else{
              if (MDocType.DOCBASETYPE_ARInvoice.equals(dt.getDocBaseType())){
                  if(factura.getEXME_CtaPacExt_ID()>0)
                  	nombre = MDocMedsys.FACTURAEXT;
                  else
                  	nombre = MDocMedsys.FACTURACE;
              }else{ 
                  if (MDocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType())){
                      if(factura.getEXME_CtaPacExt_ID()>0)
                      	nombre = MDocMedsys.NOTAEXT;
                      else
                      	nombre = MDocMedsys.NOTACE;
                      
                  }else{
                      if (MDocType.DOCBASETYPE_ARDebitMemo.equals(dt.getDocBaseType()))
                      	nombre = MDocMedsys.NOTACARGO;
                  }
              }
          }
       return nombre;
      }
  
}
