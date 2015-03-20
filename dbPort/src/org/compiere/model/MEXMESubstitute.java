/*
 * Created on 05-abr-2005
 *
 */
package org.compiere.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.factext.RegCtaPacDet;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author hassan reyes
 *
 */
public class MEXMESubstitute extends X_M_Substitute {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7968344177246941004L;

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMESubstitute.class);

//	private int scalade = 2;

    /**
     * @param ctx
     * @param M_Substitute_ID
     * @param trxName
     */
    public MEXMESubstitute(Properties ctx, int M_Substitute_ID, String trxName) {
        super(ctx, M_Substitute_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMESubstitute(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Obtenemos el producto substituto de un producto dado. Si el producto no tiene
     * un sustituto relacionado de forma anversa e inverse == true entonces se obtendara de forma inversa.
     * Siempre y cuando se encuentre dentro de la fechas validas. 
     * @param ctx
     * @param M_Product_ID
     * @param inverse si no se logo de forma anversa, lo intenta de forma inversa.
     * @return
     */
    public static MProduct[] getSubstitutes(Properties ctx, int M_Product_ID, boolean inverse, String trxName){
        
        MProduct[] retValue = null;
        List<MProduct> list = new ArrayList<MProduct>();
        
        if(ctx == null || M_Product_ID <= 0)
            return retValue;
        
        String sql = "SELECT Substitute_ID FROM M_Substitute WHERE isActive = 'Y' AND M_Product_ID = ? " +
				//" AD_Client_ID = ?  AND AD_Org_ID IN (0,?) AND  " +
				"AND ValidFrom <= ? AND ValidTo >= ? ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_Substitute");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		pstmt = DB.prepareStatement(sql, trxName);
		pstmt.setInt(1, M_Product_ID);
		pstmt.setDate(2, new Date(System.currentTimeMillis()));
		pstmt.setDate(3, new Date(System.currentTimeMillis()));
		
		rs = pstmt.executeQuery();
		while (rs.next()) {
		    MProduct product = new MProduct(ctx, rs.getInt(1), trxName);
		    list.add(product);
		}
		
	}
	catch (Exception e) {
		s_log.log(Level.SEVERE, e.getMessage(), e);
       } finally {
    	   DB.close(rs, pstmt);
       }
	
	retValue = new MProduct[list.size()];
	list.toArray(retValue);
	
	if(list.size() == 0 && inverse){
	    
	    sql = "SELECT M_Product_ID FROM M_Substitute WHERE isActive = 'Y' AND Substitute_ID = ? " +
			//"AD_Client_ID = ? AND AD_Org_ID IN (0,?) AND  " +
			"AND ValidFrom >= ? AND ValidTo <= ? ";
	    
	    sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_Substitute");
		
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setDate(2, new Date(System.currentTimeMillis()));
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
			    MProduct product = new MProduct(ctx, rs.getInt(1), trxName);
			    list.add(product);
			}
			
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
		
		retValue = new MProduct[list.size()];
		list.toArray(retValue);
	    
	}
	
       return retValue;
       
   }
   
    /**
     * Obtenemos le MEXMESubstitute.
     * @param ctx
     * @param M_Product_ID
     * @param Substitute_ID
     * @param trxName
     * @return
     */
    public static MEXMESubstitute get(Properties ctx, int M_Product_ID, int Substitute_ID,
			String trxName) {
		MEXMESubstitute retValue = null;
       
       String sql = "SELECT * FROM M_Substitute WHERE isActive = 'Y' AND M_Product_ID = ? "
				//AD_Client_ID = ? AND AD_Org_ID IN (0,?) AND"
				+ "AND Subtitute_ID = ? " + "AND ValidFrom >= ? AND ValidTo <= ? ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_Substitute");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, Substitute_ID);
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			pstmt.setDate(4, new Date(System.currentTimeMillis()));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMESubstitute(ctx, rs, trxName);

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}
   
   
   /**
     * Obtenemo la cantidad equivalente del producto objetivo en base al producto origen.
     * @param originProduct
     * @param targetProduct
     * @param originQty
     * @return
     */
 /*   public static BigDecimal convert(Properties ctx, int M_OriginProduct_ID, int M_TargetProduct_ID
           , BigDecimal originQty, String trxName){
       
       BigDecimal targetQty = Env.ZERO;
       
       MEXMESubstitute substitute = get(ctx, M_OriginProduct_ID, M_TargetProduct_ID, trxName);
       
       targetQty = substitute.convert(originQty);
       
       return targetQty;
       
   }*/
   
   /**
    * Obtenemos la cantidad equivalente del producto subtituto para cubrir la cantidad qty.
    * @param qty cantidad en UOM de almacenamiento (product.C_UOM_ID) 
    * @return
    */
   /*public BigDecimal convert(BigDecimal qty){
       
       BigDecimal qtyOrigin_converted = qty.divide(getQtyOrigin(),getScalade(), BigDecimal.ROUND_HALF_UP);
       
       // No pueden ser decimas.
       if(qtyOrigin_converted.compareTo(Env.ONE) < 0)
           return Env.ZERO;
       
       return qtyOrigin_converted.multiply(getQtyTarget());
       
   }
   
   
   /**
	 * Obtener todos los detalles de cargos facturados de Productos Sustitutos.
	 * Agrupados por cuenta paciente en Array de mapa de registros.
	 * 
	 * Arturo
	 * @return
	 */
	public static HashMap<String,ArrayList<RegCtaPacDet>> getAllCargosSust(Properties ctx, HashMap<String, String> mParams) {
		
		HashMap<String,ArrayList<RegCtaPacDet>> mDetSust = new HashMap<String,ArrayList<RegCtaPacDet>>(1000);
		ArrayList<RegCtaPacDet> paqBaseVerList = new ArrayList<RegCtaPacDet>(1000);; 
		
		String detSust_id = null;
		
//		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(1000);
		//Consulta que obtiene el detalle en sustitutos de la version de los paquetes 
		// relacionados a las cuentas pacientes, que cumplan con los criterios de selección.
		 sql.append("SELECT pbd.EXME_Paqbase_Version_Id")
		.append(", s.M_Product_Id AS PRODUCTO_ORIGINAL")
		.append(", s.Substitute_Id AS PRODUCTO_SUSTITUTO")
		.append(", pbd.C_Uom_ID")
		.append(", pbd.Cantidad")


		.append(" FROM EXME_PaqBaseDet pbd")
		.append("  INNER JOIN M_Substitute s ON ( pbd.M_Product_ID = s.M_Product_ID )")

		.append(" WHERE pbd.IsActive = 'Y'")
		.append("  AND pbd.EXME_PaqBase_Version_ID IN( ")
		         .append(" SELECT cpp.EXME_PaqBase_Version_ID")
		         .append(" FROM EXME_CtaPac CtP ")
		         .append("  INNER JOIN EXME_CtaPacPaq cpp ON ( ctp.EXME_CtaPac_ID = cpp.EXME_CtaPac_ID )")
		         .append(" WHERE ")
		                .append(" to_date(TO_CHAR(CtP.DateOrdered, 'dd/mm/yyyy') , 'dd/mm/yyyy') ")
		                .append("      BETWEEN TO_DATE(?, 'dd/MM/yyyy') ")
		                .append("      AND TO_DATE(?, 'dd/MM/yyyy')")
		                .append("  AND CtP.Documentno  >= ?")
		                .append("  AND CtP.Documentno  <= ?")
		                .append("  AND CtP.AD_Client_ID = ?")
		                .append("  AND CtP.AD_Org_ID    = ?")
		                .append(" GROUP BY cpp.EXME_PaqBase_Version_ID")
		      .append(")")
		.append("ORDER BY pbd.EXME_PaqBase_Version_ID");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, mParams.get("FechaIni"));
			pstmt.setString(2, mParams.get("FechaFin"));
			pstmt.setString(3, mParams.get("CtaPacIniValue"));
			pstmt.setString(4, mParams.get("CtaPacFinValue"));
			pstmt.setInt(5, Integer.valueOf(mParams.get("AD_Client_ID")));
			pstmt.setInt(6, Integer.valueOf(mParams.get("AD_Org_ID")));
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				
				// Si es una nueva cta paciente 
				if (detSust_id != null){
					if (!detSust_id.equals(rs.getString("EXME_Paqbase_Version_ID")) ){
						mDetSust.put(detSust_id, paqBaseVerList);
						paqBaseVerList = new ArrayList<RegCtaPacDet>(1000);
					}
				}
				
				// Creamos un nuevo registro de ctaPacDet "cargos"
				RegCtaPacDet regCtaPacDet = new RegCtaPacDet(); ;
				detSust_id = rs.getString("EXME_Paqbase_Version_ID");
				
				regCtaPacDet.setValue("EXME_Paqbase_Version_Id", rs.getString("EXME_Paqbase_Version_Id")); 
				regCtaPacDet.setValue("PRODUCTO_ORIGINAL", 	rs.getString("PRODUCTO_ORIGINAL")); 
				regCtaPacDet.setValue("PRODUCTO_SUSTITUTO", rs.getString("PRODUCTO_SUSTITUTO")); 
				regCtaPacDet.setValue("C_Uom_ID", rs.getString("C_Uom_ID")); 
				regCtaPacDet.setValue("Cantidad", rs.getString("Cantidad")); 
				
				//Agregar el nuevo registro del a la lista y agregar al mapa del ctaPac
				paqBaseVerList.add(regCtaPacDet);
				
				
			}
			
			mDetSust.put(detSust_id, paqBaseVerList);
			
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs=null;
			pstmt = null;
		}

		//MCtaPacDet[] ctasPacDet = new MCtaPacDet[list.size()];
		//list.toArray(ctasPacDet);

		return mDetSust;

	}
   
   
   /**
	 * Obtener lista Cuenta de paciente detalle
	 * Arturo
	 * @return
	 * @throws SQLException 
	 */
	public static HashMap<String,ArrayList<RegCtaPacDet>> getAllDetalleSust(Properties ctx, HashMap<String, Object> mParams, boolean rangoFechas) throws SQLException {
		
		HashMap<String, ArrayList<RegCtaPacDet>> mDetSust = new HashMap<String, ArrayList<RegCtaPacDet>>(50);
		ArrayList<RegCtaPacDet> paqBaseVerList = new ArrayList<RegCtaPacDet>(50);
		String detSust_id = null;
		StringBuilder sql = new StringBuilder(1000);

		// Consulta que obtiene el detalle en sustitutos de la version de los paquetes
		// relacionados a las cuentas pacientes, que cumplan con los criterios de seleccion.
		sql.append(" SELECT ")
		.append("    pbd.EXME_Paqbase_Version_Id ")
		.append("  , pbd.C_Uom_ID ")
		.append("  , pbd.Cantidad ")
		.append("  , s.isactive ")
		.append("  , s.M_Product_Id  AS PRODUCTO_ORIGINAL ")
		.append("  , s.Substitute_Id AS PRODUCTO_SUSTITUTO ")
		.append("  FROM  EXME_CtaPacPaq cpp ")
		.append("  INNER JOIN EXME_CtaPac ctp ON ( cpp.EXME_CtaPac_ID = ctp.EXME_ctaPac_ID   ) ")
		.append("  INNER JOIN EXME_PaqBase_Version pbv ON ( cpp.exme_paqbase_version_id = pbv.exme_paqbase_version_id ) ")
		.append("  INNER JOIN  EXME_PaqBaseDet pbd ON ( pbv.EXME_paqbase_version_id = pbd.exme_paqbase_version_id  ) ")
		.append("  INNER JOIN M_Substitute s  ON  (  pbd.M_Product_ID = s.M_Product_ID  AND s.IsActive   = 'Y'  ")
		.append("  AND s.validfrom  <= CtP.DateOrdered ")
		.append("  AND s.validto      >= CtP.DateOrdered ")
		.append("  ) ")
		.append("  INNER JOIN EXME_CtaPacDet cpd ON ( s.substitute_id = cpd.M_Product_ID and  cpp.exme_ctapac_id = cpd.exme_ctapac_id ) ")
		.append("  WHERE cpp.IsActive  = 'Y' ");
		if(!rangoFechas){
			sql.append("  AND to_date ( TO_CHAR ( CtP.DateOrdered, ")
			.append(DB.getDateMask(ctx)).append(") , ")
			.append(DB.getDateMask(ctx)).append(") ");
		}else{
			sql.append("  AND to_date ( TO_CHAR ( cpp.Created, ").append(DB.getDateMask(ctx)).append(") , ").append(DB.getDateMask(ctx)).append(") ");
		}
		
		sql.append("  BETWEEN TO_DATE(?, ")
		.append(DB.getDateMask(ctx))
		.append(") AND TO_DATE(?, ")
		.append(DB.getDateMask(ctx))
		.append(") ")
		.append("  AND CtP.Documentno       >= ? ")
		.append("  AND CtP.Documentno       <= ? ");
		
		if (mParams.containsKey("Clause") && mParams.get("Clause") != null){
			sql.append(mParams.get("Clause"));
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPac", "CtP"))
		.append("ORDER BY pbd.EXME_PaqBase_Version_ID, pbd.EXME_PaqBaseDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, (String) mParams.get("FechaIni"));
			pstmt.setString(2, (String) mParams.get("FechaFin"));
			pstmt.setString(3, (String) mParams.get("CtaPacIniValue"));
			pstmt.setString(4, (String) mParams.get("CtaPacFinValue"));
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				
				// Si es una nueva cta paciente 
				if (detSust_id != null){
					if (!detSust_id.equals(rs.getString("EXME_Paqbase_Version_ID")) ){
						mDetSust.put(detSust_id, paqBaseVerList);
						paqBaseVerList = new ArrayList<RegCtaPacDet>(50);
					}
				}
				
				// Creamos un nuevo registro de ctaPacDet "cargos"
				RegCtaPacDet regCtaPacDet = new RegCtaPacDet(); ;
				detSust_id = rs.getString("EXME_Paqbase_Version_ID");
				
				regCtaPacDet.setValue("EXME_PAQBASE_VERSION_ID", rs.getInt("EXME_Paqbase_Version_Id")); 
				regCtaPacDet.setValue("PRODUCTO_ORIGINAL", 	rs.getInt("PRODUCTO_ORIGINAL")); 
				regCtaPacDet.setValue("PRODUCTO_SUSTITUTO", rs.getInt("PRODUCTO_SUSTITUTO")); 
				regCtaPacDet.setValue("C_UOM_ID", rs.getInt("C_Uom_ID")); 
				regCtaPacDet.setValue("CANTIDAD", rs.getInt("Cantidad")); 
				regCtaPacDet.setValue("CTD_TMP", rs.getInt("Cantidad")); 
				
				//Agregar el nuevo registro del a la lista y agregar al mapa del ctaPac
				paqBaseVerList.add(regCtaPacDet);
			}
			
			if (detSust_id != null){
				mDetSust.put(detSust_id, paqBaseVerList);
			}
			
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("Lista de sustitutos:" + e.getMessage() );
		} finally {
			DB.close(rs, pstmt);
			rs=null;
			pstmt = null;
		}

		return mDetSust;
	}
   
   /*
   public int getScalade() {
       return scalade;
   }
   public void setScalade(int scalade) {
       this.scalade = scalade;
   }
   */
   
   /**
    * Obtenemos el producto substituto de un producto dado. Si el producto no tiene
    * un sustituto relacionado de forma anversa e inverse == true entonces se obtendara de forma inversa.
    * Siempre y cuando se encuentre dentro de la fechas validas. 
    * @param ctx
    * @param M_Product_ID
    * @param inverse si no se logo de forma anversa, lo intenta de forma inversa.
    * @return
    */
   /*public static MEXMESubstitute[] getSustitutos(Properties ctx, int M_Product_ID, int C_UOM_ID, boolean inverse, String trxName){
       
       MEXMESubstitute[] retValue = null;
       List list = new ArrayList();
       
       if(ctx == null || M_Product_ID <= 0)
           return retValue;
       
       String sql = "SELECT Substitute_ID FROM M_Substitute WHERE AD_Client_ID = ? " +
			" AND AD_Org_ID IN (0,?) AND isActive = 'Y' AND M_Product_ID = ? " +
			" AND ValidFrom >= ? AND ValidTo <= ? " +
			" AND UOM_Substitute_ID = ? " +
			" AND C_UOM_ID = ? ";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try
	{
		pstmt = DB.prepareStatement(sql, trxName);
		pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
		pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
		pstmt.setInt(3, M_Product_ID);
		pstmt.setDate(4, new Date(System.currentTimeMillis()));
		pstmt.setDate(5, new Date(System.currentTimeMillis()));
		pstmt.setInt(6, C_UOM_ID);
		pstmt.setInt(7, C_UOM_ID);
		
		rs = pstmt.executeQuery();
		while (rs.next())
		{
		    MEXMESubstitute product = new MEXMESubstitute(ctx, rs.getInt(1), trxName);
		    list.add(product);
		    
		}
		
	}
	catch (Exception e) {
	s_log.log(Level.SEVERE, e.getMessage(), e);
	} finally{
       DB.close(rs, pstmt);
       }
		
		retValue = new MEXMESubstitute[list.size()];
	list.toArray(retValue);
	
	if(list.size() == 0 && inverse){
	    
	    sql = "SELECT M_Product_ID FROM M_Substitute WHERE AD_Client_ID = ? " +
			"AND AD_Org_ID IN (0,?) AND isActive = 'Y' AND Substitute_ID = ? " +
			"AND ValidFrom >= ? AND ValidTo <= ? ";
		
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
			pstmt.setInt(3, M_Product_ID);
			pstmt.setDate(4, new Date(System.currentTimeMillis()));
			pstmt.setDate(5, new Date(System.currentTimeMillis()));
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
			    MEXMESubstitute product = new MEXMESubstitute(ctx, rs.getInt(1), trxName);
			    list.add(product);
				}
			
		}
		catch (Exception e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        	} finally {
        	DB.close(rs, pstmt);
        }
		
		retValue = new MEXMESubstitute[list.size()];
		list.toArray(retValue);
	    
	}
	
       return retValue;
       
   }*/
   
}
