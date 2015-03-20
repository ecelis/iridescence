package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 * Modelo de la tabla m_pricelist
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/08/16 21:35:33 $<p>
 *
 * @author Tania Perez
 * @version $Revision: 1.1 $
 */
public class MEXMEPriceList extends MPriceList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPriceList.class);
    /**
     * @param ctx
     * @param M_PriceList_ID
     * @param trxName
     */
    public MEXMEPriceList(Properties ctx, int M_PriceList_ID, String trxName) {
        super(ctx, M_PriceList_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEPriceList(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    
    public MCurrency getMoneda(){
    	return new MCurrency(getCtx(), getC_Currency_ID(), get_TrxName());
    }
    
    public int getPriceListID(){
    	return getM_PriceList_ID();
    }
    
    public String getDefecto(){
    	String valor = Msg.translate(getCtx(),"No");
    	if(isDefault())
    		valor = Msg.translate(getCtx(),"Yes");
    	return valor;
    }
    
    public String getUtilizaPrecioLimite(){
    	String valor = Msg.translate(getCtx(),"No");
    	if(isEnforcePriceLimit())
    		valor = Msg.translate(getCtx(),"Yes");
    	return valor;
    }
    
    public String getListaPreciosVta(){
    	String valor = Msg.translate(getCtx(),"No");
    	if(isSOPriceList())
    		valor = Msg.translate(getCtx(),"Yes");
    	return valor;
    }
    
    public String getImpuestoIncluido(){
    	String valor = Msg.translate(getCtx(),"No");
    	if(isTaxIncluded())
    		valor = Msg.translate(getCtx(),"Yes");
    	return valor;
    }

    public String getBasePriceList(){
    	String valor = "";
    	MEXMEPriceList lista = null;
    	if(getBasePriceList_ID()>0){
    		lista = new MEXMEPriceList(getCtx(), getBasePriceList_ID(), get_TrxName());
    		if(lista!=null)
    			valor = lista.getName();
    	}
    	return valor;
    }
    /**
     * Solo es un mensaje para poder visualizarlo en el jsp
     * y sirva como liga para poder editar el registro de la 
     * lista
     * @return
     */
    public String getEditar(){
    	String valor = Msg.translate(getCtx(),"Editar");
    	if(valor==null || valor.equalsIgnoreCase("")){
    		valor="Editar";
    	}
    	return valor;
    }
    
    /**
   * Obtenemos la informacion de las listas de precios
   *  
   * @param ctx
   * @param id cash
   * @param trxName
   * @return
   * @throws Exception
   */

    public static List<MEXMEPriceList> get(Properties ctx, String trxName) 
    throws Exception{
    	
    	List<MEXMEPriceList> lista = new ArrayList<MEXMEPriceList>();
    	if(ctx == null){
    		return null;
    	}
    	
    	MEXMEPriceList retValue = null;
    	String sql = " SELECT * FROM M_PriceList WHERE IsActive = 'Y' ";
    	
    	sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_PriceList");
    	
    	sql += " ORDER BY Created DESC ";
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try
    	{	
    		pstmt = DB.prepareStatement (sql, trxName);
    		rs = pstmt.executeQuery ();
    		while(rs.next()){
    			retValue = new MEXMEPriceList(ctx, rs, trxName);
    			lista.add(retValue);
    		}
    		
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	
    	
    	return lista;
    	
    }

    /**
     * Obtenemos la informacion de las listas de precios
     * 
     * @param ctx
     * @param id cash
     * @param trxName
     * @return
     * @throws Exception
     */

 /*     public static List getLabelValue(Properties ctx, String trxName) 
      throws Exception{
      	
      	List lista = new ArrayList();
      	if(ctx == null){
      		return null;
      	}
      	
      	String sql = " SELECT M_PriceList_ID, Name FROM M_PriceList WHERE IsActive = 'Y' ";
      	
      	sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_PriceList");
      	
      	sql += " ORDER BY Created DESC ";
      	
      	PreparedStatement pstmt = null;
      	ResultSet rs = null;
      	
      	try
      	{	
      		pstmt = DB.prepareStatement (sql, trxName);
      		rs = pstmt.executeQuery ();
      		while(rs.next()){
      			LabelValueBean elemento = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("M_PriceList_ID")));
      			lista.add(elemento);
      		}
      		
      	} catch (Exception e) {
      		s_log.log(Level.SEVERE, sql.toString(), e);
      	}finally {
			DB.close(rs, pstmt);
      	}
      	
      	
      	return lista;
      	
      }
*/

  	/**
  	 *  Devuelve una lista de listas de precios relacionadas a una empresa y
  	 *  organizaci&oacute;n.
  	 *
  	 *@param  ctx         Contexto
  	 *@return             Una lista con las listas de precios de la
  	 *      organizaci&oacute;n
  	 *@throws  Exception  en caso de ocurrir un error al procesar la consulta.
  	 *@deprecated use {@link #getLstSOPriceList(Properties)} ya no se debe usar LabelValueBean
  	 */
  	public static List<LabelValueBean> getLstPriceList(Properties ctx)
  			 throws Exception {

  		String sql = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;


  		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
  		sql = "SELECT M_PriceList.M_PriceList_ID, M_PriceList.Name FROM M_PriceList "
  				 + "WHERE M_PriceList.IsActive = 'Y' AND M_PriceList.issopricelist  = 'Y' ";
  		
  		
  		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_PriceList");
  		
  		sql += " ORDER BY M_PriceList.Name";

  		pstmt = null;
  		rs = null;

  		try {
  			pstmt = DB.prepareStatement(sql, null);
  			rs = pstmt.executeQuery();

  			while (rs.next()) {
  				lista.add(new LabelValueBean(
  						rs.getString("Name")
  						, String.valueOf(rs.getLong("M_PriceList_ID"))));
  			}
  		} catch (Exception e) {
      		s_log.log(Level.SEVERE, sql.toString(), e);
      	}finally {
      		DB.close(rs,pstmt);
      	}

  		return lista;
  	}
  	
  	/**
  	 * Regresa el listado de las listas de precios de venta existentes para el cliente y 
  	 * organizacion
  	 * @param ctx
  	 * @return
  	 */
  	public static List<MEXMEPriceList> getLstSOPriceList(Properties ctx){

 		StringBuilder sql = new StringBuilder();
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;


 		List<MEXMEPriceList> lista = new ArrayList<MEXMEPriceList>();
 		
 		sql.append(" SELECT * FROM M_PriceList WHERE M_PriceList.IsActive = 'Y' AND M_PriceList.issopricelist  = 'Y' ");
 		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_PriceList"));
 		sql.append(" ORDER BY M_PriceList.Name ");

 		pstmt = null;
 		rs = null;

 		try {
 			pstmt = DB.prepareStatement(sql.toString(), null);
 			rs = pstmt.executeQuery();

 			while (rs.next()) {
 				lista.add(new MEXMEPriceList(ctx, rs, null));
 			}
 		} catch (Exception e) {
     		s_log.log(Level.SEVERE, sql.toString(), e);
     	}finally {
     		DB.close(rs,pstmt);
     	}

 		return lista;
 	}

    /**
     * Metodo para obtener todas las listas de precios proveedor
     * @param ctx
     * @return La lista de Precios
     * @author eruiz
     */
    
    public static List<LabelValueBean> getPriceList(Properties ctx){
    	
    	List <LabelValueBean>lista = new ArrayList<LabelValueBean>();
    	StringBuilder sql = new StringBuilder(200); 
    	sql.append(" SELECT M_PriceList_ID, Name FROM M_PriceList ")
    	.append(" WHERE IsSOPriceList = 'N' AND IsActive = 'Y' ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_PriceList"));
    	
    	ResultSet rs = null;
    	PreparedStatement psmt = null;
    	
    	try{
    		psmt = DB.prepareStatement(sql.toString(), null);
    		rs = psmt.executeQuery();
    		
    		while(rs.next()){
    			lista.add(new LabelValueBean(rs.getString("Name"), "" + rs.getInt("M_PriceList_ID")));
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();//FIXME
    	} finally {
    		DB.close(rs, psmt);
    	}
    	
    	return lista;
    }
    
    private MPriceListVersion priceList = null;
    
    /**
     * Ultima version de lista de precios
     * @return
     */
    public MPriceListVersion getVersionActual(){
    	if(priceList==null)
    		priceList = getPriceListVersion(DB.convert(getCtx(), new Date()));
    	return priceList;
    }
    /**
     * Metodo que indica si la lista de precio es de compra o venta
     * @param listVersionId lista a revisar
     * @return Y si es compre N si no lo es.
     */
    
    public static String getIsSOPriceList(int listVersionId){
    	String value = "N";
    	StringBuilder sql = new StringBuilder(200); 
    	sql.append(" SELECT issopricelist FROM m_pricelist")
    	.append(" WHERE IsActive = 'Y' AND  m_pricelist_id in (")
    	.append(" SELECT m_pricelist_id FROM m_pricelist_version WHERE m_pricelist_version_id = ?) ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx()," ","M_PriceList"));
    	
    	ResultSet rs = null;
    	PreparedStatement psmt = null;
    	
    	try{
    		psmt = DB.prepareStatement(sql.toString(), null);
    		psmt.setInt(1, listVersionId);
    		rs = psmt.executeQuery();
    		
    		while(rs.next()){
    			value = rs.getString(1);
    		}
    		
    	}catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
    		DB.close(rs, psmt);
    	}
    	
    	return value;
    	
    }
    
    /**
	 * 	Get Default Price List for Client (cached)
	 *	@param ctx context
	 *	@param IsSOPriceList SO or PO
	 *	@return PriceList or null
	 */
	public static MPriceList getDefaultPL (Properties ctx, boolean IsSOPriceList)
	{
		StringBuilder sql = new StringBuilder(200); 
		MPriceList retValue = null;
		
		sql.append(" SELECT * FROM M_PriceList ")
		   .append(" WHERE IsActive = 'Y'    ")
		   .append(" AND IsDefault='Y'       ")
		   .append(" AND IsSOPriceList=?   ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx()," ","M_PriceList"))
		   .append(" ORDER BY M_PriceList_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1,IsSOPriceList?"Y":"N");
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MPriceList (ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return retValue;
	}	//	getDefault
	
	public static List<MEXMEPriceList> getList(Properties ctx, boolean isSOPriceList){

 		StringBuilder sql = new StringBuilder();
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;


 		List<MEXMEPriceList> lista = new ArrayList<MEXMEPriceList>();
 		
 		sql.append(" SELECT * FROM M_PriceList WHERE M_PriceList.IsActive = 'Y' AND M_PriceList.isSOPriceList  = ? ");
 		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_PriceList"));
 		sql.append(" ORDER BY M_PriceList.Name");

 		pstmt = null;
 		rs = null;

 		try {
 			pstmt = DB.prepareStatement(sql.toString(), null);
 			pstmt.setString(1, isSOPriceList?"Y":"N");
 			rs = pstmt.executeQuery();

 			while (rs.next()) {
 				lista.add(new MEXMEPriceList(ctx, rs, null));
 			}
 		} catch (Exception e) {
     		s_log.log(Level.SEVERE, sql.toString(), e);
     	}finally {
     		DB.close(rs,pstmt);
     	}

 		return lista;
 	}
	
}
