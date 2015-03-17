/*

 * Created on 31-mar-2005

 *

 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * <b>Fecha:</b> 31/Marzo/2005<p>
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/01/24 23:06:56 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.5 $
 */

public class MEXMEAreaCaja extends X_EXME_AreaCaja {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5015427831394731728L;


	/**
     * @param ctx
     * @param EXME_AreaCaja_ID
     * @param trxName
     */
    public MEXMEAreaCaja(Properties ctx, int EXME_AreaCaja_ID, String trxName) {
        super(ctx, EXME_AreaCaja_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEAreaCaja(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
   
    /**
	 * Obtiene el numero de documento externo siguente para la factura 
	 * y actualiza EXME_AreaCaja
	 * @param EXME_AreaCaja_ID Area de Cajas
	 * @param trxName Nombre de la transacci&oacute;n.
	 * @return El siguente n&uacute;mero de documento externo, null si error.
	 */

	public static String createCounterDocExt(Properties ctx, int EXME_AreaCaja_ID, String trxName) throws Exception{
	    String strDocumentNoExt = null;
	    long documentNoExt = 0;

	    
		try {
			MEXMEAreaCaja area = new MEXMEAreaCaja(ctx, EXME_AreaCaja_ID, trxName);
			
			strDocumentNoExt = "0"; // Zero by default aaranda@xpt
			Object docNoNotaCre = area.getDocNoNotaCre();
			if (docNoNotaCre != null){
				if (docNoNotaCre instanceof Long) {
					// Is a Long instance of long aaranda@xpt
					documentNoExt = Long.parseLong(area.getDocNoNotaCre());
					documentNoExt++;
					strDocumentNoExt = String.valueOf(documentNoExt);
				}else{
					// Is a String,  instance of String aaranda@xpt
					strDocumentNoExt =  docNoNotaCre.toString();
				}
			}	
		    strDocumentNoExt = (area.getPrefixNotaCre()!=null?area.getPrefixNotaCre().trim():"") 
		    			     + strDocumentNoExt
		    			     + (area.getSuffixNotaCre()!=null?area.getSuffixNotaCre().trim():"");
			
		    //verificar si el numero obtenido es valido
            String whereclause = "prefix||startno||suffix";
            if(!isValido(ctx,strDocumentNoExt,whereclause,trxName)){
                throw new Exception("error.caja.secuencia");
            }
            
            area.setDocumentNoExt(String.valueOf(documentNoExt));
			if(!area.save(trxName)) {
			    DB.rollback(false, trxName);
			    throw new Exception("error.caja.areaCaja.noSave");
			}
			
		} catch (Exception e) {
			DB.rollback(false, trxName);
		    throw e;
		}

	    return strDocumentNoExt;
	}



    /**
	 * Obtiene el numero de documento externo siguente para la nota de credito 
	 * y actualiza EXME_AreaCaja
	 * @param EXME_AreaCaja_ID Area de Cajas
	 * @param trxName Nombre de la transacci&oacute;n.
	 * @return El siguente n&uacute;mero de documento externo, null si error.
	 */
	public static String createCounterDocNotaCre(Properties ctx, int EXME_AreaCaja_ID, String trxName) throws Exception{
	    String strDocumentNoExt = null;
	    long documentNoExt = 0;
	    
		try {
			MEXMEAreaCaja area = new MEXMEAreaCaja(ctx, EXME_AreaCaja_ID, trxName);
			
			strDocumentNoExt = "0"; // Zero by default aaranda@xpt
			if (area.getDocNoNotaCre() != null) {
				try {
					documentNoExt = Long.parseLong(area.getDocNoNotaCre());
					documentNoExt++;
					strDocumentNoExt = String.valueOf(documentNoExt);
				} catch (NumberFormatException ex) {
					strDocumentNoExt = area.getDocNoNotaCre();
				}
			}	
		    
		    strDocumentNoExt = (area.getPrefixNotaCre()!=null?area.getPrefixNotaCre().trim():"") 
		    			     + strDocumentNoExt
		    			     + (area.getSuffixNotaCre()!=null?area.getSuffixNotaCre().trim():"");
			
		    //verificar si el numero obtenido es valido
            String whereclause = "prefixnotacre||startnonotacre||suffixnotacre";
            if(!isValido(ctx,strDocumentNoExt,whereclause,trxName)){
                throw new Exception("error.caja.secuencia");
            }
            
            area.setDocNoNotaCre(String.valueOf(documentNoExt));
			if(!area.save(trxName)) {
//			    DB.rollback(false, trxName);
			    throw new Exception("error.caja.areaCaja.noSave");
			}
			
		} catch (Exception e) {
//			DB.rollback(false, trxName);
		    throw e;
		}
	    
	    return strDocumentNoExt;
	}

    /**
	 * Obtiene el numero de recibo siguente para el pago y 
	 * actualiza EXME_AreaCaja
	 * @param EXME_AreaCaja_ID Area de Cajas
	 * @param trxName Nombre de la transacci&oacute;n.
	 * @return El siguente n&uacute;mero de documento externo, null si error.
	 */
	public static String createCounterRecibo(Properties ctx, int EXME_AreaCaja_ID, String trxName) throws Exception{

        String strDocumentNoExt = null;
	    long documentNoExt = 0;
	    
		try {
			MEXMEAreaCaja area = new MEXMEAreaCaja(ctx, EXME_AreaCaja_ID, trxName);
			
		    documentNoExt = Long.parseLong(area.getDocNoRecibo());
		    documentNoExt++;
    
		    strDocumentNoExt = (area.getPrefixRecibo()!=null?area.getPrefixRecibo().trim():"") 
		    			     + String.valueOf(documentNoExt)
		    			     + (area.getSuffixRecibo()!=null?area.getSuffixRecibo().trim():"");

            
            //verificar si el numero obtenido es valido
            String whereclause = "prefixrecibo||startnorecibo||suffixrecibo";
            if(!isValido(ctx,strDocumentNoExt,whereclause,trxName)){
                throw new Exception("error.caja.secuencia");
            }

            area.setDocNoRecibo(String.valueOf(documentNoExt));

            if(!area.save(trxName)) {
			    DB.rollback(false, trxName);
			    throw new Exception("error.caja.areaCaja.noSave");
			}
			
		} catch (Exception e) {
			DB.rollback(false, trxName);
		    throw e;		  
		}
		
	    return strDocumentNoExt;
	}
    
    /**
     * Verificar que el numero sea valido
     * 
     * @param ctx
     * @param DocumentNoExt
     * @param AreaCaja_ID
     * @param trxName
     * @return
     */
	public static boolean isValido(Properties ctx, String docNoExt, String whereclause, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT exme_areacaja_id")
			.append(" FROM exme_areacaja ")
			.append(" WHERE " + whereclause + " = ? ")
			.append(" AND isActive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		// if(rs.next()) isValido = false;
		return DB.getSQLValue(trxName, sql.toString(), docNoExt)<=0;
	}
    
    /**
     *  beforeSave
     *  @see org.compiere.model.PO#beforeSave(boolean)
     *  @param newRecord
     *  @return true
     */
    protected boolean beforeSave (boolean newRecord)
    {
        CharSequence[] num = {"0","1","2","3","4","5","6","7","8","9"};
        String errMsg = "";
        for (int a=0; a < num.length; a++)  { 
            if((getPrefix()!= null && getPrefix().contains(num[a])) 
            	|| (getPrefixNotaCar()!=null && getPrefixNotaCar().contains(num[a])) 
                || (getPrefixNotaCre()!=null && getPrefixNotaCre().contains(num[a]))
                || (getPrefixRecibo()!=null && getPrefixRecibo().contains(num[a]))){
                errMsg = "El prefijo no puede contener numeros";
                break;
            }
            if((getSuffix()!= null && getSuffix().contains(num[a]))
            	|| (getSuffixNotaCar() != null && getSuffixNotaCar().contains(num[a])) 
                || (getSuffixNotaCre() != null && getSuffixNotaCre().contains(num[a]))
                || (getSuffixRecibo() != null && getSuffixRecibo().contains(num[a]))){
                errMsg = "El sufijo no puede contener numeros";
                break;
            }
         }
        
        if (errMsg.length() > 0) {
            log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
            return false;
        }
        
        return true;
    }   //  beforeSave

    
    /**
	 * Obtiene el numero de recibo siguente para el pago y 
	 * actualiza EXME_AreaCaja
	 * @param EXME_AreaCaja_ID Area de Cajas
	 * @param trxName Nombre de la transacci&oacute;n.
	 * @return El siguente n&uacute;mero de documento externo, null si error.
	 */
	public static String create_CounterRecibo(Properties ctx, int EXME_AreaCaja_ID, String trxName) throws Exception {

		String strDocumentNoExt = null;
		long documentNoExt = 0;

		MEXMEAreaCaja area = new MEXMEAreaCaja(ctx, EXME_AreaCaja_ID, trxName);

		if (area==null || area.get_ID()<=0) {
			throw new Exception(Utilerias.getLabel("msj.sinAreadeCajaRelacionada"));
		}

		documentNoExt = Long.parseLong(area.getDocNoRecibo());
		documentNoExt++;

		strDocumentNoExt = (area.getPrefixRecibo() != null ? area.getPrefixRecibo().trim() : "")
				+ String.valueOf(documentNoExt) + (area.getSuffixRecibo() != null ? area.getSuffixRecibo().trim() : "");

		//verificar si el numero obtenido es valido
		String whereclause = "prefixrecibo||startnorecibo||suffixrecibo";
		if (!isValido(ctx, strDocumentNoExt, whereclause, trxName)) {
			throw new Exception(Utilerias.getLabel("error.caja.secuencia"));
		}

		area.setDocNoRecibo(String.valueOf(documentNoExt));

		if (!area.save(trxName)) {
			throw new Exception(Utilerias.getLabel("error.caja.areaCaja.noSave"));
		}

		return strDocumentNoExt;
	}
	
	public static List<MEXMEAreaCaja> getAreaCajaLst(String whereClause, String trxName){
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName)
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).list();
	}
}