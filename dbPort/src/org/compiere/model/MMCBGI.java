package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
//FIXME: Documentar clase, verificar si se sigue usando o no 
public class MMCBGI extends X_EXME_MCBGI {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private Properties ctx = null;
	
	/** Constructor */
	public MMCBGI(Properties ctx, ResultSet rs, String trxName) {
	    super(ctx, rs, trxName);
	    this.ctx = ctx;
	}
	
	/**
     * Verifica si el Business partner asociado al paciente tiene
     * acceso a los medicamentos genericos
     * @param ctx El contexto de la aplicacion
     * @param idPaciente Identificador del paciente en cuestion
     * @param trxName El nombre de la transaccion
     *  @return Una bandera con el resultado
     */
    
    public static String bpHasAccessToGI(Properties ctx, int idPaciente,  String trxName)
    {
    	String valid="false";
    	 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    		
    		sql.append(" SELECT * FROM exme_paciente ")
    		.append(" INNER JOIN c_bpartner on ( exme_paciente.c_bpartner_seg_id = c_bpartner.c_bpartner_id ) ") 
    		.append(" WHERE exme_paciente.IsActive = 'Y' ANd exme_paciente.exme_paciente_id= ? ")
    		.append(" AND c_bpartner.IsGI='Y' ")
    		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPaciente.Table_Name));
    	 PreparedStatement pstmt = null;
         ResultSet rs = null;
         
                  
          try {
              pstmt = DB.prepareStatement(sql.toString(), trxName);
              pstmt.setInt(1, idPaciente);
              rs = pstmt.executeQuery();
              if(rs.next()) //si encontro resultados
            	  valid="true";

          } 
          catch (SQLException e) {
              e.printStackTrace();
          } finally {
        	  DB.close(rs,pstmt);
          }
         return valid;
    }
    
    /**
     * Devuelve un colection con los medicamentos genericos intercambiables 
     * para el producto indicado en 'MCB' 
     * @param ctx El contexto de la aplicacion
     * @param idMCB Identificador del medicamento del cuadro basico para el 
     * cual buscamos sus genericos intercambiables 
     * @param trxName El nombre de la transaccion
     *  @return Una coleccion con la informacion de los genericos
     */
    
    public static Collection<MMCBGI> getGenericosIntercambiables(Properties ctx, int idMCB, String trxName)
    {
    	Collection<MMCBGI> lista = new ArrayList<MMCBGI>();

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
    	sql.append(" select distinct exme_mcbgi.* ")
    	.append(" FROM  exme_mcbgi exme_mcbgi ")
    	.append(" INNER JOIN m_storage storage ON (storage.m_product_id = exme_mcbgi.m_productgi_id and storage.m_locator_id = ?) ")
    	.append(" WHERE exme_mcbgi.isactive = 'Y' ")
    	.append(" AND exme_mcbgi.m_product_id = ? ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_MCBGI", "exme_mcbgi"));
    	
    	 PreparedStatement pstmt = null;
         ResultSet rs = null;
         
          try {
              pstmt = DB.prepareStatement(sql.toString(), trxName);
              pstmt.setInt(1, Integer.parseInt(ctx.getProperty("#M_Locator_ID")));
              pstmt.setInt(2, idMCB);
              
              rs = pstmt.executeQuery();
              while(rs.next()){
            	  MMCBGI prod = new MMCBGI(ctx, rs, trxName);
            	  lista.add(prod);
              }
	     
          } 
          catch (SQLException e) {
              e.printStackTrace();
          }finally{
        	 DB.close(rs,pstmt);
          }
         return lista;
    }

    
//    public MProduct getProducto(){
//    	return new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
//    }
//    
//    public MProduct getProductoGI(){
//    	return new MProduct(getCtx(), getM_ProductGI_ID(), get_TrxName());
//    }
//    
//    public MStorage getStorage(){
//    	return MStorage.get(getCtx(), Integer.parseInt(ctx.getProperty("#M_Locator_ID")), getM_Product_ID(), 0, null);
//    }
//    
//    public BigDecimal getQtyAvailable(){
//    	return MStorage.getQtyAvailable(Integer.parseInt(ctx.getProperty("#M_Warehouse_ID")), getM_Product_ID(), 0, null);
//    }
//    
//    public BigDecimal getQtyOnHand(){
//    	return MStorage.getQtyOnHand(Integer.parseInt(ctx.getProperty("#M_Locator_ID")), getM_Product_ID(), 0, null);
//    }
//    
//    public int getQtyUnconfirmed(){
//    	return MMovementLine.getQtyUnconfirmed(getCtx(), getM_Product_ID(), null);
//    }

}
