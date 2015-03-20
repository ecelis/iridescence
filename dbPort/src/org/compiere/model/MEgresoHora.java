/*
 * Created on 31-ene-2006
 *
 */
package org.compiere.model;

import java.sql.ResultSet;import java.util.Properties;

/**
 * @author Omar Torres
 *@deprecated tabla Medsys will be removed
 */
public class MEgresoHora extends X_EXME_Egreso_Hora {	private static final long serialVersionUID = 1L;	/**	Static Logger				*///	@SuppressWarnings("unused")//	private static CLogger		s_log = CLogger.getCLogger (MEgresoHora.class);    	    public MEgresoHora(Properties ctx, int EXME_Egreso_Hora_ID, String trxName)    {        super (ctx, EXME_Egreso_Hora_ID, trxName);    }    /**     * @param ctx     * @param rs     * @param trxName     */
    public MEgresoHora(Properties ctx, ResultSet rs, String trxName)    {    	super (ctx, rs, trxName);    }    //    public static MEgresoHora getEgresoHora(Properties ctx, int pacienteID)//    {//    	String sql = "Select * From EXME_Egreso_Hora where EXME_Paciente_ID = " + pacienteID + " and AD_Client_ID = " //    	 + ctx.getProperty("#AD_Client_ID");//    	//    	ResultSet rs = null;//    	PreparedStatement ps = null;//    	try //    	{//    		ps = DB.prepareStatement(sql, null);//            rs = ps.executeQuery();//            if (rs.next()){//            	return new MEgresoHora(ctx, rs, null);//            } //            //    	}//    	catch (Exception e) {//    		   		//    		//    	}//    	//    	finally {//    		//    		try {//    		if (ps != null)//    		ps.close();//    		//    		if(rs != null)//    			rs.close();//    		//    		ps = null;//    		rs = null;//    	}//    		catch (Exception e) {//    			//    		}//    	}//    	//    	return null;//    }    
}
