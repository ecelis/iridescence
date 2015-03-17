/*

 * Created on 31-mar-2005

 *

 */

package org.compiere.model;



import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author hassan reyes
 *
 */
public class MCtaPacFam extends X_EXME_CtaPacFam {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = -4915356448530270494L;

    /**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCtaPacFam.class);

    /**
     * Variables miembro 
     */
    MEXMEProductFam m_productFam = null;
  
    /**
     * @param ctx
     * @param EXME_CtaPacFam_ID
     * @param trxName
     */

    public MCtaPacFam(Properties ctx, int EXME_CtaPacFam_ID, String trxName) {
        super(ctx, EXME_CtaPacFam_ID, trxName);
    }



    /**
     * @param ctx
     * @param rs
     * @param trxName
     */

    public MCtaPacFam(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    

    /**
     * Borramos las lineas referentes al descuento por Familia
     * @param ctx
     * @param EXME_CtaPacExt_ID
     * @param trxName
     * @return
     */
    public static boolean getCtaPacFamDelete(Properties ctx, int EXME_CtaPacExt_ID, String trxName){
        int noReg = 0;

        String sql = " SELECT * FROM EXME_CtaPacFam " +
        	    	" WHERE EXME_CtaPacExt_ID = ? " +
        	    	" AND IsActive = 'Y' ";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try {

            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, EXME_CtaPacExt_ID);
            rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacFam ctaPacFam = new MCtaPacFam(ctx, rs, trxName);
				ctaPacFam.delete(true);				
			}            
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
        	DB.close(rs, pstmt);
        }
        return noReg >= 0;
    }

  
	/**
	 * Obtenemos los Descuentos por Familia de productos.
	 * 
	 * @param reQuery
	 * @param createTemplates
	 *            true para crear nuevos modelos (con ID cero) de las familias
	 *            que no esten relacionadas.
	 * @return
	 */
	public static MCtaPacFam[] getCtasPacFamXExt(Properties ctx, int EXME_CtaPacExt_ID, String trxName) {

		ArrayList<MCtaPacFam> list = new ArrayList<MCtaPacFam>();

		String sql = " SELECT * FROM EXME_CtaPacFam "
				+ " WHERE  IsActive = 'Y' AND EXME_CtaPacExt_ID = "
				+ EXME_CtaPacExt_ID;

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacFam");
		
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacFam ctaPacFam = new MCtaPacFam(ctx, rs,
						trxName);
				list.add(ctaPacFam);

			}
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
        	DB.close(rs, pstmt);
        }


		MCtaPacFam[] ctasPacFam = new MCtaPacFam[list.size()];
		list.toArray(ctasPacFam);

		return ctasPacFam;

	}  


	/**
	 * 
	 */
    public boolean equals(Object cmp) {

        if(!super.equals(cmp))
            return false;

        if(!(cmp instanceof MCtaPacFam))
            return false;

        if(((MCtaPacFam)cmp).getEXME_CtaPacExt_ID() != getEXME_CtaPacExt_ID()
                || ((MCtaPacFam)cmp).getEXME_ProductFam_ID() != getEXME_ProductFam_ID())
            return false;

        return true;

    }

    /**
     * Familia del producto
     * @return
     */
    private MEXMEProductFam productFam = null;
    
    public MEXMEProductFam getProductFam(){
    	
    	if(productFam == null || getEXME_ProductFam_ID()<=0)
    		productFam = new MEXMEProductFam(getCtx(), getEXME_ProductFam_ID(), get_TrxName());
    		
    	return productFam;

    }

    private BigDecimal taxAmt = Env.ZERO;

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    private int secuencia = 0;
    
	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Obtenemos los Descuentos por Familia de productos.
	 * 
	 * @param reQuery
	 * @param createTemplates
	 *            true para crear nuevos modelos (con ID cero) de las familias
	 *            que no esten relacionadas.
	 * @return
	 */
	public static MCtaPacFam get(Properties ctx, int EXME_ProductFam_ID, int EXME_CtaPacExt_ID, String trxName) {

		MCtaPacFam ctaPacFam = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
		sql.append(" SELECT * FROM EXME_CtaPacFam ")
			.append(" WHERE IsActive = 'Y'  AND EXME_ProductFam_ID = ")
			.append(EXME_ProductFam_ID)
			.append(" AND EXME_CtaPacExt_ID = ")
			.append(EXME_CtaPacExt_ID);

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacFam"));
		
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			if (rs.next()) 
				ctaPacFam = new MCtaPacFam(ctx, rs, trxName);
				
			
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
        	DB.close(rs, pstmt);
        }
		return ctaPacFam;

	}
	
	
	
	   /**
     * Obtenemos
     * @return
     */

    public static List<MCharge> getCtaPacFamPorExtension(Properties ctx, int EXME_CtaPacExt_ID, String trxName){
        List<MCharge> list = new ArrayList<MCharge>();

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
        	
        	sql.append(" SELECT * FROM EXME_CtaPacFam ")
        	.append(" WHERE EXME_CtaPacExt_ID = ? ")
        	.append(" AND IsActive = 'Y' ");

        PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try
		{
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_CtaPacExt_ID);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                list.add(new MCharge(ctx, rs, trxName));
            }
        }
        catch (Exception e){
            s_log.log(Level.SEVERE, sql.toString(), e);
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return list;
    }  

    /**
	 * Descuentos Por Familia de Productos
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param lineas
	 * @param ctaPacFam
	 * @param trxName
	 * @return
	 */
	public static boolean getCtaPacFamInsert(Properties ctx, int EXME_CtaPacExt_ID, 
			MCtaPacFam[] ctaPacFam, String trxName) {//34214
		try {
			
			//Grabamos los nuevos descuentos por familia siempre y cuando no sea nulo
			if(ctaPacFam != null) {
				for (int i = 0; i < ctaPacFam.length; i++) {
					
					ctaPacFam[i].set_TrxName(trxName);
					
					if(ctaPacFam[i].getDiscountPorcent().compareTo(Env.ZERO)>0){
						
						MCtaPacFam nuevo = new MCtaPacFam(ctx, 0, trxName);
						nuevo.setBaseAmt(ctaPacFam[i].getBaseAmt());
						nuevo.setDiscountAmt(ctaPacFam[i].getBaseAmt().multiply(ctaPacFam[i].getDiscountPorcent().divide(Env.ONEHUNDRED, new MathContext(100))));
						nuevo.setDiscountPorcent(ctaPacFam[i].getDiscountPorcent());
						nuevo.setEXME_CtaPacExt_ID(ctaPacFam[i].getEXME_CtaPacExt_ID());
						nuevo.setEXME_ProductFam_ID(ctaPacFam[i].getEXME_ProductFam_ID());
						
						if(!nuevo.save(trxName)){
							s_log.severe(" " + CLogger.retrieveError());//Recuperar los errores
						}
						
						nuevo  = null;
					}
				}
			}
		} catch (Exception s) {
			s_log.log(Level.SEVERE, "Familias de producto de las cargos de la extencion ...", s);
			s.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	   /**
     * Obtenemos
     * @return
     */

    public static boolean getDescXFam(Properties ctx, int EXME_CtaPacExt_ID, String trxName){
        boolean result = false;

        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
        	
        	sql.append(" SELECT * FROM EXME_CtaPacFam ")
        	.append(" WHERE EXME_CtaPacExt_ID = ? ")
        	.append(" AND IsActive = 'Y' AND DiscountPorcent IS NOT NULL AND DiscountPorcent > 0");

        PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try
		{
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_CtaPacExt_ID);
            rs = pstmt.executeQuery();
            
            if (rs.next()){
                result = true;
            }
        }
        catch (SQLException e){
            s_log.log(Level.SEVERE, sql.toString(), e);
            return false;
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return result;
    }
    
    
    
	/*****************************************************************************************************/
	
	
	/** DESCUENTOS POR FAMILIA **/

	/**
	 * Arreglo con las familias de productos que est�n relacionadas a los cargos hechos a la
	 * extension que se quiere calcular 
	 * @param ctx       Contexto para obtener cliente organizacion
	 * @param EXME_CtaPacExt_ID  Extension de la cuenta paciente
	 * @param configPre Configuracion de precios necesaria para excluir los productos de coaseguro y deducible
	 * @param trxName   Nombre de la transacci�n
	 * @return          Arreglo con las familias con su monto base para la aplicacion del descuento
	 * @throws SQLException 
	 *
	public static HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>> getCtaPacFam(
			Properties ctx, int EXME_CtaPacExt_ID, 	MConfigPre configPre, String trxName)
			throws SQLException {
		String sqlWhere= " AND cpd.M_Product_ID NOT IN ("
			+ configPre.getCoaseguro_ID()
			+ ", "
			+ configPre.getDeducible_ID()
			+ ") ";
		return getCtaPacFam(ctx, EXME_CtaPacExt_ID, sqlWhere, trxName);
	}

	
	

	/**
	 * Retorna las Familias de productos 
	 * correspondientes a los cargos de la extension
	 * junto con su total de lo cargado por familia
	 * @throws SQLException 
	 *
	public static HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>> getCtaPacFam(
			Properties ctx, int EXME_CtaPacExt_ID, String where, String trxName) throws SQLException {


		HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>> hshMpDescFam = 
			new HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>>();


		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT cpd.EXME_CtaPacDet_ID, cpf.EXME_CtaPacFam_ID, pf.EXME_ProductFam_ID ")
		.append("    FROM EXME_CtaPacDet cpd ")
		.append("    INNER JOIN M_Product p        ON cpd.M_Product_ID      = p.M_Product_ID  ")
		.append("    INNER JOIN EXME_ProductFam pf ON p.EXME_ProductFam_ID  = pf.EXME_ProductFam_ID   ")
		.append("    LEFT  JOIN EXME_CtaPacFam cpf ON pf.EXME_ProductFam_ID = cpf.EXME_ProductFam_ID ")
		.append("                                     AND cpf.EXME_CtaPacExt_id = ?  ")
		.append("    WHERE cpd.IsActive = 'Y'   ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_CtaPacDet", "cpd"))
		.append("    AND cpd.EXME_CtaPacExt_id = ?  ")
		.append("    AND cpd.QtyInvoiced > 0   ");  

		if(where!=null)
			sql.append(where);

		sql.append(" ORDER BY pf.EXME_ProductFam_ID, cpf.DiscountPorcent, cpf.EXME_CtaPacFam_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			pstmt.setInt(2, EXME_CtaPacExt_ID);
			rs = pstmt.executeQuery();

			int IdFam = 0;
			int cont = 0;
			int IdCtaPacFam = 0;
			int IdProductFam = 0;
			BigDecimal monto = Env.ZERO;
			ArrayList<MTTCtaPacDet> list = new ArrayList<MTTCtaPacDet>();

			while (rs.next()) {
				IdCtaPacFam  = rs.getInt("EXME_CtaPacFam_ID");//Puede ser cero
				IdProductFam = rs.getInt("EXME_ProductFam_ID");//Nunca debe ser cero

				//Cargo
				MTTCtaPacDet cargo = new MTTCtaPacDet(ctx, rs.getInt("EXME_CtaPacDet_ID"), trxName);
				monto = monto.add(cargo.getLineNetAmt());
				list.add(cargo);

				//Si no existe en el mapa se agrega al final de toda la lista
				//No entra la primera vez ya que cont es = 0
				if(IdFam!=IdProductFam && cont>0){

					//No siempre existe
					MCtaPacFam descFam = new MCtaPacFam(ctx, IdCtaPacFam, trxName);
					descFam.setBaseAmt(monto);
					descFam.setEXME_ProductFam_ID(IdProductFam);
					descFam.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
					descFam.setSecuencia(cont);

					//Lista de cargos y objeto de familias
					HashMap<MCtaPacFam, List<MTTCtaPacDet>> cor = new HashMap<MCtaPacFam, List<MTTCtaPacDet>>();
					cor.put(descFam, list);

					//Id de familias HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>>();
					hshMpDescFam.put(IdProductFam,cor);

					list.clear();
					monto=Env.ZERO;
				}

				cont++;
				IdFam = IdProductFam; 

			}

			//Agrega la ultima familia de producto
			if(list!=null && list.size()>0){
				//Puede ser cero si el CtaPacFam no existe
				MCtaPacFam descFam = new MCtaPacFam(ctx, IdCtaPacFam, trxName);
				descFam.setBaseAmt(monto);
				descFam.setEXME_ProductFam_ID(IdProductFam);
				descFam.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
				descFam.setSecuencia(cont);

				//Lista de cargos y objeto de familias
				HashMap<MCtaPacFam, List<MTTCtaPacDet>> cor = new HashMap<MCtaPacFam, List<MTTCtaPacDet>>();
				cor.put(descFam, list);

				//Id de familias HashMap<Integer, HashMap<MCtaPacFam, List<MTTCtaPacDet>>>();
				hshMpDescFam.put(IdProductFam,cor);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCtaPacFam", e);
			return null;
		} finally {
			if(rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			rs = null;
			pstmt = null;
		}

		return hshMpDescFam;
	}*/
 }

