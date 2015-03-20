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

public class MCtaPacPaq extends X_EXME_CtaPacPaq {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** static logger   */
	private static CLogger s_log = CLogger.getCLogger(MCtaPacPaq.class);
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MCtaPacPaq(Properties ctx, ResultSet rs, String trxName) {
	    super(ctx, rs, trxName);
	}
	
	
	public MCtaPacPaq(Properties ctx, int EXME_CtaPacPaq_ID, String trxName) {
	    super(ctx, EXME_CtaPacPaq_ID, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	
	public static List<MCtaPacPaq> get(Properties ctx, String  whereClause, String orderBy, String trxName){
		
		final List<MCtaPacPaq> lista = new ArrayList<MCtaPacPaq>();
		
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_CtaPacPaq WHERE EXME_CtaPacPaq.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacPaq"));

		if (whereClause != null) {
			sql.append(whereClause);
		}
		if (orderBy != null && orderBy.length() > 0) {
			sql.append(" ORDER BY ").append(orderBy);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MCtaPacPaq ctaPacPaq = new MCtaPacPaq(ctx, rs, trxName);
				lista.add(ctaPacPaq);
			}
			
	 	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}

		return lista;
	}
	
	/**
	 * Arreglo
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public MCtaPacPaq[] getArray(Properties ctx, String  whereClause, String orderBy, String trxName){
		List<MCtaPacPaq> list = get(ctx, whereClause, orderBy, trxName);
		MCtaPacPaq[] ctasPac = new MCtaPacPaq[list.size()];
		list.toArray(ctasPac);
		return ctasPac;
	}
	
	/**
	 * Label Value Bean
	 * @param ctx
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getLV(Properties ctx, String  whereClause, String orderBy, String trxName){
	
		List<LabelValueBean> resultList = new ArrayList<LabelValueBean>();
		List<MCtaPacPaq> lista = get(ctx, whereClause, orderBy, trxName);
		for(int i=0; i<lista.size(); i++){
			MCtaPacPaq pack =(MCtaPacPaq)lista.get(i);
			resultList.add(new LabelValueBean(pack.getPaqBase_Version().getName(), 
					String.valueOf(pack.getEXME_PaqBase_Version_ID())));
			
		}
		
		return resultList;
	}
	
	private MEXMEPaqBaseVersion m_paqBaseVersion = null;
	/**
	 * MPaqBaseVersion
	 * @return
	 */
	public MEXMEPaqBaseVersion getPaqBase_Version(){
		
		if(m_paqBaseVersion != null)
			return m_paqBaseVersion;
			
		if(getEXME_PaqBase_Version_ID()<=0)
			return null;
		
		m_paqBaseVersion = new MEXMEPaqBaseVersion(getCtx(), getEXME_PaqBase_Version_ID(), get_TrxName());
		
		return m_paqBaseVersion;
	}
	
	private boolean borrado = false;

	public boolean getBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	private boolean guardarEnBitacora = false;
	
	public boolean getGuardarEnBitacora(){
		return guardarEnBitacora;
	}
	
	public void setGuardarEnBitacora(boolean guardarEnBitacora){
		this.guardarEnBitacora = guardarEnBitacora;
	}
	
	private boolean esPaqueteFalso = false;
	
	public boolean getEsPaqueteFalso(){
		return esPaqueteFalso;
	}
	
	public void setEsPaqueteFalso(boolean esPaqueteFalso){
		this.esPaqueteFalso = esPaqueteFalso;
	}
	/**
	 * Revisamos si el paquete ya esta en uso o no, para asi no permitir su edicion
	 * @param ctx
	 * @param _EXME_PaqBase_Version_ID
	 * @param trxName
	 * @return
	 */
	public static boolean estaEnUso(Properties ctx, int EXME_PaqBase_Version_ID, String trxName){

		boolean resultado = false;

		//Buscamos si alguna cuenta paciente esta haciendo referencia al paquete.
		StringBuilder sql = new StringBuilder();
		//Se cambio el query, eruiz
		 sql.append(" select EXME_CtaPac.documentno, cpe.extensionno from EXME_CtaPacDet ttc ")
				 .append("inner join exme_ctapacext cpe on ( cpe.EXME_ctapacext_id = ttc.exme_ctapacext_id ) ") 
				 .append("inner join exme_ctapac EXME_CtaPac on ( EXME_CtaPac.EXME_ctapac_id = ttc.exme_ctapac_id ) ") 
				 .append("where ttc.exme_paqbase_version_id = ? ")  
				 .append("and cpe.c_invoice_id > 0 ");
			

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPac"));	

		PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
		    //System.out.println("SQL > " + sql);
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_PaqBase_Version_ID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){

				if(rs.getInt(1) > 0)

					resultado = true;

			}

	 	} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}		
		return resultado;

	}

}