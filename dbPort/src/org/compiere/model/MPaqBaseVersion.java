/*
 * Created on 04-abr-2005
 *
 */
package org.compiere.model;

/**
 * @author hassan reyes
  * @deprecated
 */
public class MPaqBaseVersion { 
//
//extends MEXMEPaqBaseVersion implements GridItem {
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4343154201837660804L;

//	/**	Static Logger				*/
//	private static CLogger		s_log = CLogger.getCLogger (MPaqBaseVersion.class);
////    /** PO Producto */
////    private MProduct product = null;
//    /** PO Paquete base */
//    private MEXMEPaqBase paqBase = null;
//    
////    /**
////     * Variables miembro. 
////     */
////    private MPaqBaseDet[] m_paqBaseDet = null; 
//
//    /**
//     * @param ctx
//     * @param EXME_PaqBase_Version_ID
//     * @param trxName
//     */
//    public MPaqBaseVersion(Properties ctx, int EXME_PaqBase_Version_ID,
//            String trxName) {
//        super(ctx, EXME_PaqBase_Version_ID, trxName);
//    }

//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MPaqBaseVersion(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
    
//    /**
//     * Obtenemos todos las versiones del paquete para la empresa logeada
//     * @param ctx El contexto de la aplicacion
//     * @param trxName El nombre de la transaccion
//     * @return
//     */
//    public static MPaqBaseVersion[] get(Properties ctx, String cadena, 
//            String fechaDesde, String fechaHasta, String trxName){
//        
//        if(ctx == null)
//            return null;
//        
//        ArrayList<MPaqBaseVersion> list = new ArrayList<MPaqBaseVersion>();
//		String sql = " SELECT EXME_PaqBase_Version.EXME_PaqBase_Version_ID " + 
//                     " FROM EXME_PaqBase_Version " + 
//                     " WHERE EXME_PaqBase_Version.IsActive = 'Y'  ";
//        /**+
//                     " AND EXME_PaqBase_Version.ValidFrom >= " +
//                     " (SELECT MAX(EXME_PaqBase_Version.validfrom) " +
//                     " FROM EXME_PaqBase_Version " + 
//                     " WHERE EXME_PaqBase_Version.IsActive = 'Y' " + 
//                     " AND EXME_PaqBase_Version.Validfrom < TO_DATE('" + fechaDesde + "', +DB.getDateMask(ctx)+") " +
//                     " AND EXME_PaqBase_Version.ValidTo >= TO_DATE('" + fechaHasta + "', +DB.getDateMask(ctx)+" ) ";
//        */
//        if(cadena != null)
//            sql += cadena;
//            
//        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_PaqBase_Version");
//        
//        sql+= " ORDER BY Name";
//		            
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql, trxName);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next())
//			{
//			    MPaqBaseVersion paquete = new MPaqBaseVersion(ctx, rs.getInt(1), trxName);
//				list.add(paquete);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPaqBase_Version.get", e);
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (Exception e) {
//			}
//			pstmt = null;
//		}
//
//		//
//		MPaqBaseVersion[] paquete = new MPaqBaseVersion[list.size()];
//		list.toArray(paquete);
//		return paquete;
//
//	}
    
//    /**
//     * Obtenemos las lineas del paquete.
//     * @param reQuery
//     * @return
//     */
//    public MPaqBaseDet[] getLineas(boolean reQuery){
//        
//        if(m_paqBaseDet == null || m_paqBaseDet.length == 0 || reQuery){
//            m_paqBaseDet = getLineasArray(getCtx(),null, false);
//        }
//        
//        return m_paqBaseDet;
//    }
    
//    public List<MPaqBaseDet>  getLineas(Properties ctx, String trxName) {
//		return getLineas(ctx, null, false, trxName);
//	}
    
    
//    /**
//     * Obtenemos las lineas de la Version del Paquete.
//     * @return
//     */
//    public List<MPaqBaseDet> getLineas(Properties ctx, String whereClause, boolean conversion, String trxName){
//        
//        List<MPaqBaseDet> list = new ArrayList<MPaqBaseDet>();
//        
//        StringBuilder sql = new StringBuilder("SELECT * FROM EXME_PaqBaseDet WHERE EXME_PaqBase_Version_ID = ? AND IsActive = 'Y' ");
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
//        sql.append("ORDER BY Secuencia ASC");
//        
//        if(whereClause != null)
//            sql.append(whereClause);
//        
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next())
//			{
//			    MPaqBaseDet paqBaseVersion = new MPaqBaseDet(getCtx(), rs, trxName);
//			    paqBaseVersion.setCantidadOld(paqBaseVersion.getCantidad());
//			    list.add(paqBaseVersion);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			rs=null;
//		}
//		catch (Exception e)
//		{
//		    e.printStackTrace();
//			log.log(Level.SEVERE, "getLineas", e);
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//		
//		//MPaqBaseDet[] paqBaseDets = new MPaqBaseDet[list.size()];
//		//list.toArray(paqBaseDets);
//		
//		return list;
//        
//    }
//    /**
//     * Obtenemos las lineas de la Version del Paquete.
//     * @return
//     */
//    public MPaqBaseDet[] getLineasArray(Properties ctx, String whereClause, boolean conversion){
//        
//        ArrayList<MPaqBaseDet> list = new ArrayList<MPaqBaseDet>();
//        
//        String sql = "SELECT * FROM EXME_PaqBaseDet WHERE EXME_PaqBase_Version_ID = ? AND IsActive = 'Y' ";
//        
//        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql , "EXME_PaqBaseDet");
//        
//        if(whereClause != null)
//            sql += whereClause;
//        
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next())
//			{
//			    MPaqBaseDet paqBaseVersion = new MPaqBaseDet(getCtx(), rs, get_TrxName());
//			    list.add(paqBaseVersion);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			rs=null;
//		}
//		catch (Exception e)
//		{
//		    e.printStackTrace();
//			log.log(Level.SEVERE, "getLineas", e);
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//		
//		MPaqBaseDet[] paqBaseDets = new MPaqBaseDet[list.size()];
//		list.toArray(paqBaseDets);
//		
//		return paqBaseDets;
//        
//    }
//    /**
//     * Remplazamos las lineas.
//     * (Usar con discrecion).
//     *
//     */
//    public void setLineas(MPaqBaseDet[] paqBaseDets){
//        
//        m_paqBaseDet = paqBaseDets;
//        
//    }

    
//    /**
//	 * Obtener el detalle de la version de cada paquete dado un rango de Cta. Pac. y el rango de fechas
//	 * Arturo
//	 * @return
//     * @throws SQLException 
//	 */
//	public static HashMap<String,ArrayList<RegCtaPacDet>> getAllPaqVersion(Properties ctx, HashMap<String, Object> mParams, boolean rangoFechas) throws SQLException {
//		
//		HashMap<String,ArrayList<RegCtaPacDet>> mPaqVersion = new HashMap<String,ArrayList<RegCtaPacDet>>(50);
//		ArrayList<RegCtaPacDet> paqVersionrList = new ArrayList<RegCtaPacDet>(50);
//		
//		String detPaqVer_id = null;
//		
//		StringBuilder sql = new StringBuilder(1000);
//		
//		//Obtener el detalle de la version de paquetes para un rango de cuentas paciente.
//		sql.append("SELECT ctP.EXME_CtaPac_ID ")
//          .append(" ,cpp.EXME_PaqBase_Version_ID")
//          .append(" FROM EXME_CtaPac CtP")
//          .append("  INNER JOIN EXME_CtaPacPaq cpp ON ( ctp.EXME_CtaPac_ID = cpp.EXME_CtaPac_ID )")
//          .append(" WHERE"); 
//		   
//  		if(!rangoFechas){
//  			sql.append(" TO_DATE(TO_CHAR(CtP.DateOrdered, ")
//  			.append(DB.getDateMask(ctx)).append(") , ").append(DB.getDateMask(ctx)).append(")");
//  		}else{
//  			sql.append(" TO_DATE(TO_CHAR(cpp.Created, ").append(DB.getDateMask(ctx))
//  			.append(") , ").append(DB.getDateMask(ctx)).append(")");
//  		}
//		          		
//        sql.append(" BETWEEN TO_DATE(?, ").append(DB.getDateMask(ctx)).append(")")
//        .append(" AND TO_DATE(?,  ").append(DB.getDateMask(ctx)).append(")")
//        .append(" AND CtP.DocumentNo  >= ? ")
//        .append(" AND CtP.DocumentNo  <= ? ");
//        
//        if (mParams.containsKey("Clause") && mParams.get("Clause") != null){
//        	sql.append(mParams.get("Clause"));
//        }
//		 
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPac", "CtP"))
//		   .append(" ORDER BY ctP.EXME_CtaPac_ID");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, (String) mParams.get("FechaIni"));
//			pstmt.setString(2, (String) mParams.get("FechaFin"));
//			pstmt.setString(3, (String) mParams.get("CtaPacIniValue"));
//			pstmt.setString(4, (String) mParams.get("CtaPacFinValue"));
//			rs = pstmt.executeQuery();
//			
//			
//			while (rs.next()) {
//				
//				// Si es una nueva cta paciente 
//				if (detPaqVer_id != null){
//					if (!detPaqVer_id.equals(rs.getString("EXME_CtaPac_ID")) ){
//						mPaqVersion.put(detPaqVer_id, paqVersionrList);
//						paqVersionrList = new ArrayList<RegCtaPacDet>(50);
//					}
//				}
//				
//				// Creamos un nuevo registro de ctaPacDet "cargos"
//				RegCtaPacDet regCtaPacDet = new RegCtaPacDet(); ;
//				detPaqVer_id = rs.getString("EXME_CtaPac_ID");
//				
//				regCtaPacDet.setValue("EXME_PAQBASE_VERSION_ID", rs.getInt("EXME_PaqBase_Version_ID")); 
//				regCtaPacDet.setValue("EXME_CTAPAC_ID", rs.getInt("EXME_CtaPac_ID")); 
//				
//				//Agregar el nuevo registro del a la lista y agregar al mapa del ctaPac
//				paqVersionrList.add(regCtaPacDet);
//				
//			}
//			if (detPaqVer_id != null){
//				mPaqVersion.put(detPaqVer_id, paqVersionrList);
//			}
//			
//			rs.close();
//			rs=null;
//			pstmt.close();
//			pstmt = null;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SQLException("Lista de versiones de paquetes:" + e.getMessage() );
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close ();
//				if (rs != null)
//					rs.close ();
//			} catch (Exception e) {}
//			rs=null;
//			pstmt = null;
//		}
//
//		//MCtaPacDet[] ctasPacDet = new MCtaPacDet[list.size()];
//		//list.toArray(ctasPacDet);
//
//		return mPaqVersion;
//
//	}

  
    
//    /**
//	 * ID 
//	 * @return
//	 */
//	public int getId() {
//
//		if(getEXME_PaqBase_Version_ID() > 0)
//			return getEXME_PaqBase_Version_ID();
//		else
//			return 0;
//	}
//	
    

    
//    /**
//     * Producto
//     * @return
//     */
//    public MProduct getProduct() {
//    	if(product == null && getM_Product_ID() > 0){
//    		product = new MProduct(getCtx(), getM_Product_ID(), null);
//    	}
//    	return product;
//    }
//	
//	/**
//	 * Lista de precios
//	 * @return
//	 */
//	public MPriceList getPriceList() {
//		return new MPriceList(getCtx(), getM_PriceList_ID(), null);
//	}
//
//	/**
//	 * Moneda
//	 * @return
//	 */
//	public MEXMECurrency getCurrency() {
//		return new MEXMECurrency(getCtx(), getC_Currency_ID(), null);
//	}
//    
	
//    /**
//   * Obtenemos la Version especifica del paquete que fue reservada a la cuenta paciente.
//   * @return
//   */
//	public MEXMEPaqBase getPaqBase() {
//		if (paqBase == null && getEXME_PaqBase_ID() > 0)
//			paqBase = new MEXMEPaqBase(getCtx(), getEXME_PaqBase_ID(), get_TrxName());
//		return paqBase;
//	}
//
//	public void setPaqBase(MEXMEPaqBase pack) {
//		paqBase = pack;
//	}
    
//    /**
//     * Obtenemos la Version especifica del paquete que fue reservada a la cuenta paciente.
//     * @return
//     */
//    public MEXMETax getTax() {
//
//        if(getC_Tax_ID() > 0)
//            return new MEXMETax(getCtx(), getC_Tax_ID(), get_TrxName());
//        else
//            return null;
//
//    }
    
//    /**
//     * monto total de la version del paquete a precio de lista 
//     */
//    public BigDecimal totalListaAmt = Env.ZERO;
//
//	public BigDecimal getTotalListaAmt() {
//		return totalListaAmt;
//	}
//
//	public void setTotalListaAmt(BigDecimal totalListaAmt) {
//		this.totalListaAmt = totalListaAmt;
//	}

//	/**
//	 * Secuencia 
//	 */
//	public int secuencia = 0;
//
//	public int getSecuencia() {
//		return secuencia;
//	}
//
//	public void setSecuencia(int secuencia) {
//		this.secuencia = secuencia;
//	}
//	
//	/**
//	 * Metodo get para struts
//	 * @return
//	 */
//	public boolean getActive() {
//		return isActive();
//	}
	
//	/**
//	 * CtaPaciente a la que se le asigno el paquete
//	 */
//	private int ctaPacID = 0;
//
//	public int getCtaPacID() {
//		return ctaPacID;
//	}
//
//	public void setCtaPacID(int ctaPacID) {
//		this.ctaPacID = ctaPacID;
//	}
	
	
//	   /**
//     * Obtenemos las lineas de la Version del Paquete.
//     * @return
//     */
//    public static MPaqBaseDet[] getLineas(Properties ctx, int EXME_CtaPac_ID, String trxName){
//        
//        ArrayList<MPaqBaseDet> list = new ArrayList<MPaqBaseDet>();
//        
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//         sql.append(" SELECT pbd.*, ") 
//            .append(" NVL((SELECT c.dividerate FROM C_UOM_Conversion c ") 
//            .append(" WHERE c.IsActive='Y'  ")
//            .append((MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_UOM_Conversion").replace("C_UOM_Conversion.","c.")))//nivel de acceso
//          //.append(" AND c.AD_Client_ID = ? ").append(" AND c.AD_Org_ID IN ( 0, ? ) ") 
//        	.append(" AND c.C_UOM_To_ID = p.C_UOM_ID ")        	   
//        	.append(" AND c.C_UOM_ID IN ( SELECT C_UOM_ID FROM M_Product ") 
//        	.append(" WHERE M_Product_ID = pbd.M_Product_ID )  ") 
//        	.append(" ), 1) * pbd.Cantidad AS CantidadUOM ") 
//            .append(" FROM EXME_PaqBaseDet pbd ") 
//            .append(" LEFT JOIN M_Product p ON (p.M_Product_ID = pbd.M_Product_ID AND p.isActive = 'Y' ) ") 
//            .append(" WHERE pbd.EXME_PaqBase_Version_ID IN ") 
//        	.append(" (SELECT cpq.EXME_PaqBase_Version_ID FROM ") 
//        	.append(" EXME_CtaPacPaq cpq ") 
//        	.append(" WHERE cpq.EXME_CtaPac_ID = ? ) ")
//        	.append((MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_PaqBaseDet")).replace("EXME_PaqBaseDet.","pbd."))//nivel de acceso
//            .append(" ORDER BY pbd.EXME_PaqBase_Version_ID ");
//        
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			//pstmt.setInt(1, Env.getAD_Client_ID(ctx));
//			//pstmt.setInt(2, Env.getAD_Org_ID(ctx));
//			pstmt.setInt(1, EXME_CtaPac_ID);
//		
//			ResultSet rs = pstmt.executeQuery();
//		
//			while (rs.next())
//			{
//			    MPaqBaseDet paqBaseVersion = new MPaqBaseDet(ctx, rs, trxName);
//			    paqBaseVersion.setQtyPendiente(rs.getBigDecimal("CantidadUOM"));
//			    list.add(paqBaseVersion);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//		}
//		catch (Exception e)
//		{
//		    e.printStackTrace();
//		    s_log.log(Level.SEVERE, "getLineas", e);
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//		
//		MPaqBaseDet[] paqBaseDets = new MPaqBaseDet[list.size()];
//		list.toArray(paqBaseDets);
//		
//		return paqBaseDets;
//        
//    }
//	 
    
//    public String getReferencia(){
//    	return "Version: " + getName() + " Paquete: " + getPaqBase().getName();
//    }
//    
//	/**
//     * Obtenemos las lineas de la Version del Paquete.
//     * @return
//     */
//    public static List<MEXMEBPartner> getLineasSocios(Properties ctx, int paqBaseVersionID, String trxName){
//        
//    	List<MEXMEBPartner> lista = new ArrayList<MEXMEBPartner>();
//    	
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append(" SELECT C_BPartner_ID ") 
//           .append(" FROM EXME_PaqBase_BP ") 
//           .append(" WHERE IsActive = 'Y' ") 
//           .append(" AND EXME_PaqBase_Version_ID = ").append(paqBaseVersionID);
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBase_BP"));
//        
//        sql.append(" ORDER BY EXME_PaqBase_Version_ID ");
//    
//        
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			ResultSet rs = pstmt.executeQuery();
//		
//			while (rs.next())
//			{
//			    MEXMEBPartner paqBaseVersion = new MEXMEBPartner(ctx, rs.getInt("C_BPartner_ID"), trxName);
//			    lista.add(paqBaseVersion);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//		}
//		catch (Exception e)
//		{
//		    e.printStackTrace();
//		    s_log.log(Level.SEVERE, "getLineas", e);
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//		
//		return lista;
//        
//    }
    
//    
//    /**
//     * Obtenemos todos las versiones del paquete para la empresa logeada
//     * @param ctx El contexto de la aplicacion
//     * @param trxName El nombre de la transaccion
//     * @return
//     */
//    public static boolean getFechaValid(Properties ctx, String fecha, int EXME_PaqBase_Version_ID,
//            int EXME_PaqBase_ID, String trxName) {
//        boolean result = true;
//
//        if (ctx == null)
//            return false;
//        
//		StringBuilder sql = new StringBuilder("SELECT EXME_PaqBase_Version.EXME_PaqBase_Version_ID ")
//                          .append("FROM EXME_PaqBase_Version ")
//                          .append("WHERE EXME_PaqBase_Version.IsActive = 'Y' ")
//                          .append("AND EXME_PaqBase_Version.Validfrom = TO_DATE('")
//                          .append(fecha).append("', ").append(DB.getDateMask(ctx)).append(") ")
//                          .append("AND EXME_PaqBase_Version.EXME_PaqBase_Version_ID <> ? ")
//                          .append("AND EXME_PaqBase_Version.EXME_PaqBase_ID = ? ");
//       
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBase_Version"));
//        
//        sql.append(" ORDER BY Name");
//
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_PaqBase_Version_ID);
//            pstmt.setInt(2, EXME_PaqBase_ID);
//
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                result = false;
//            }
//            rs.close();
//            pstmt.close();
//            pstmt = null;
//            // expert:
//            rs = null;
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, "MPaqBase_Version.get", e);
//        } finally {
//            try {
//                if (pstmt != null)
//                    pstmt.close();
//            } catch (Exception e) {}
//            pstmt = null;
//        }
//
//        return result;
//
//    }
//    
    
//    /**
//     * Obtenemos todos las versiones del paquete para la empresa logeada
//     * @param ctx El contexto de la aplicacion
//     * @param trxName El nombre de la transaccion
//     * @return
//     */
//    public static boolean getPaqValid(Properties ctx, String fecha, int C_BPartner_ID, int EXME_PaqBase_Version_ID, String trxName){
//        boolean result = false;
//    	
//        if(ctx == null)
//            return result;
//        
//        String accessLevelPBV = MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBase_Version");
//        
//		String sql = " SELECT * " +
//		" FROM EXME_PaqBase_Version pbv " +
//		" INNER JOIN EXME_PaqBase pb ON (pb.EXME_PaqBase_ID = pbv.EXME_PaqBase_ID AND pb.IsActive = 'Y' AND pb.IsMiniPack = 'Y') " +
//		" WHERE pbv.IsActive = 'Y' " +
//		" AND pbv.EXME_PaqBase_Version_ID = ? " +
//		" AND pbv.ValidTo >= TO_DATE('?', "+DB.getDateMask(ctx)+") " +
//		" AND pbv.ValidFrom <= TO_DATE('?', "+DB.getDateMask(ctx)+") " +
//		" AND " +
//		" ( pbv.EXME_Paqbase_Version_ID IN " +
//		" 		( " +
//		" 			SELECT pbvs.EXME_PaqBase_Version_ID " +
//		" 			FROM EXME_PaqBase_Version pbvs " +
//		" 			INNER JOIN EXME_PaqBase pbs ON (pbs.EXME_PaqBase_ID = pbvs.EXME_PaqBase_ID AND pbs.IsActive = 'Y' AND pbs.IsMiniPack = 'Y') " +
//		" 			INNER JOIN EXME_PaqBase_BP pbbps ON (pbbps.EXME_PaqBase_Version_ID = pbvs.EXME_PaqBase_Version_ID AND pbbps.IsActive = 'Y' ) " +
//		" 			WHERE pbvs.IsActive = 'Y' " +
//		" 			AND pbvs.general = 'N' " +
//		"           AND pbvs.EXME_PaqBase_Version_ID = ? " +
//		"  			AND pbvs.ValidTo >= TO_DATE('?', "+DB.getDateMask(ctx)+") " +
//		" 			AND pbvs.ValidFrom <= TO_DATE('?', "+DB.getDateMask(ctx)+") " +
//		"           AND pbbps.EXME_PaqBase_Version_ID = ? " +
//		" 			AND pbbps.C_BPartner_ID = ? " +
//		//nivel de acceso
//		accessLevelPBV.replace("EXME_PaqBase_Version.","pbvs.") +
//		" 		) " +
//		" 	  OR pbv.General = 'Y' " +	
//		" 	) " +
//		//nivel de acceso
//		accessLevelPBV.replace("EXME_PaqBase_Version.","pbv.");
//  	            
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			pstmt.setString(2, fecha);
//			pstmt.setString(3, fecha);
//			pstmt.setInt(4, EXME_PaqBase_Version_ID);
//			pstmt.setString(5, fecha);
//			pstmt.setString(6, fecha);
//			pstmt.setInt(7, EXME_PaqBase_Version_ID);
//			pstmt.setInt(8, C_BPartner_ID);
//			
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//			   result = true;
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			//expert:
//			rs=null;
//		}
//		catch (Exception e)
//		{
//			s_log.log(Level.SEVERE, "MPaqBase_Version.get", e);
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//
//
//		return result;
//        
//    }
//    
//    
//    /**
//     * Obtenemos todos las versiones del paquete para la empresa logeada
//     * @param ctx El contexto de la aplicacion
//     * @param trxName El nombre de la transaccion
//     * @return
//     */
//    public static List<MPaqBaseVersion> getPaqVerValid(Properties ctx, String fecha, int C_BPartner_ID, String trxName){
//    	List<MPaqBaseVersion> lista =  new ArrayList<MPaqBaseVersion>();
//    	
//        if(ctx == null)
//            return null;
//        
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append(" SELECT EXME_PaqBase_Version.* " +
//		" FROM EXME_PaqBase_Version " +
//		" INNER JOIN EXME_PaqBase pb ON (pb.EXME_PaqBase_ID = EXME_PaqBase_Version.EXME_PaqBase_ID AND pb.IsActive = 'Y' AND pb.IsMiniPack = 'Y') " +
//		" WHERE EXME_PaqBase_Version.IsActive = 'Y' " +
//		" AND EXME_PaqBase_Version.ValidTo >= TO_DATE(SUBSTR(?,1,10), ").append(DB.getDateMask(ctx)).append(") " +
//		" AND EXME_PaqBase_Version.ValidFrom <= TO_DATE(SUBSTR(?,1,10), ").append(DB.getDateMask(ctx)).append(") " +
//		" AND " +
//		" ( EXME_PaqBase_Version.EXME_Paqbase_Version_ID IN " +
//		" 		( " +
//		" 			SELECT pbvs.EXME_PaqBase_Version_ID " +
//		" 			FROM EXME_PaqBase_Version pbvs " +
//		" 			INNER JOIN EXME_PaqBase pbs ON (pbs.EXME_PaqBase_ID = pbvs.EXME_PaqBase_ID AND pbs.IsActive = 'Y' AND pbs.IsMiniPack = 'Y') " +
//		" 			INNER JOIN EXME_PaqBase_BP pbbps ON (pbbps.EXME_PaqBase_Version_ID = pbvs.EXME_PaqBase_Version_ID AND pbbps.IsActive = 'Y' ) " +
//		" 			WHERE pbvs.IsActive = 'Y' " +
//		" 			AND pbvs.general = 'N' " +
//		"  			AND pbvs.ValidTo >= TO_DATE(SUBSTR(?,1,10), ").append(DB.getDateMask(ctx)).append(") " +
//		" 			AND pbvs.ValidFrom <= TO_DATE(SUBSTR(?,1,10), ").append(DB.getDateMask(ctx)).append(") " +
//		" 			AND pbbps.C_BPartner_ID = ? " +
//		//" 			AND pbvs.AD_Client_ID = ? " + 
//		//"			AND pbvs.AD_Org_ID IN (0,?) " +
//		//nivel de acceso
//		MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,"pbvs") +
//		" 		) " +
//		" 	  OR EXME_PaqBase_Version.General = 'Y' " +	
//		" 	) ");
//		
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//        
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//  	            
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setString(1, fecha);
//			pstmt.setString(2, fecha);
//			pstmt.setString(3, fecha);
//			pstmt.setString(4, fecha);
//			pstmt.setInt(5, C_BPartner_ID);
//			//pstmt.setInt(6, Env.getContextAsInt(ctx, "#AD_Client_ID"));
//			//pstmt.setInt(7, Env.getContextAsInt(ctx, "#AD_Org_ID"));
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				MPaqBaseVersion vers = new MPaqBaseVersion(ctx, rs, trxName);
//                lista.add(vers);
//			}
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPaqBase_Version.get : " + sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//			}
//
//		return lista;
//        
//    }
//    public static boolean verPackGenerales(Properties ctx, int C_BPartner_ID, 
//            String fecha, String trxName){
//    	return !getPackGenerales(ctx, C_BPartner_ID, fecha, false, trxName).isEmpty() ;
//    }
    
//    public static  List<LabelValueBean> getPackGeneralesLVB(Properties ctx, int C_BPartner_ID, 
//            String fecha){
//    	List<LabelValueBean> lstLVB = new ArrayList<LabelValueBean>();
//    	List<MPaqBaseVersion> lst = MPaqBaseVersion.getPackGenerales(ctx, C_BPartner_ID, fecha, false, null);
//    	for (int i = 0; i < lst.size(); i++) {
//    		lstLVB.add(new LabelValueBean(lst.get(i).getName(), String.valueOf(lst.get(i).getEXME_PaqBase_Version_ID())));
//		}
//    	return lstLVB ;
//    }
//    
//    /**
//     * 
//     * @param ctx
//     * @param cadena
//     * @param fechaDesde
//     * @param fechaHasta
//     * @param trxName
//     * @return
//     */
//    public static List<MPaqBaseVersion> getPackGenerales(Properties ctx, int C_BPartner_ID, 
//            String fecha, boolean general, String trxName){
//        
//    	 List<MPaqBaseVersion> retValue = new ArrayList<MPaqBaseVersion>();
//		
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append(" SELECT EXME_PaqBase_Version.* " )
//	    .append(" FROM EXME_PaqBase_Version " )
//	    .append(" INNER JOIN EXME_PaqBase pb ON (pb.EXME_PaqBase_ID = EXME_PaqBase_Version.EXME_PaqBase_ID AND pb.IsActive = 'Y' AND pb.IsMiniPack = 'N') " )
//	    .append(C_BPartner_ID > 0 ?" INNER JOIN EXME_PaqBase_BP pbbps ON (pbbps.EXME_PaqBase_Version_ID = EXME_PaqBase_Version.EXME_PaqBase_Version_ID AND pbbps.IsActive = 'Y' )  " :"")
//	    .append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' " )
//	    .append(" AND EXME_PaqBase_Version.general = ?  " )
//	    .append(" AND EXME_PaqBase_Version.ValidTo >= TO_DATE(?,  ").append(DB.getDateMask(ctx)).append(") " )
//	    .append(" AND EXME_PaqBase_Version.ValidFrom <= TO_DATE(?, ").append(DB.getDateMask(ctx)).append(") " )
//	    .append(C_BPartner_ID > 0 ?" AND pbbps.C_BPartner_ID = ?  ":"")
//	    .append(" AND EXME_PaqBase_Version.validfrom IN  ")
//	    .append(" 		( " )
//	    .append(" 			SELECT max(pbvs.validfrom)  " )
//	    .append(" 			FROM EXME_PaqBase_Version pbvs " )
//	    .append(" 			INNER JOIN EXME_PaqBase pbs ON (pbs.EXME_PaqBase_ID = pbvs.EXME_PaqBase_ID AND pbs.IsActive = 'Y' AND pbs.IsMiniPack = 'N') " )
//	    .append(C_BPartner_ID > 0 ?" 			INNER JOIN EXME_PaqBase_BP pbbps ON (pbbps.EXME_PaqBase_Version_ID = pbvs.EXME_PaqBase_Version_ID AND pbbps.IsActive = 'Y' ) " :"")
//	    .append(" 			WHERE pbvs.IsActive = 'Y' " )
//	    .append(" 			AND pbvs.general = ? " )
//	    .append("  			AND pbvs.ValidTo >= TO_DATE(?, ").append(DB.getDateMask(ctx)).append(") " )
//	    .append(" 			AND pbvs.ValidFrom <= TO_DATE(?, ").append(DB.getDateMask(ctx)).append(") " )
//	    .append(C_BPartner_ID > 0 ?" 			AND pbbps.C_BPartner_ID = ? " :"")
//	    //nivel de acceso
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBase_Version", "pbvs"))
//	    .append(" 			group by pbs.exme_paqbase_id")
//	    .append(" 		) ");
//	         
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBase_Version"));
//                    
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			int i = 0;
//			pstmt.setString(++i, DB.TO_STRING(general));
//			pstmt.setString(++i, fecha);
//			pstmt.setString(++i, fecha);
//			if (C_BPartner_ID > 0)
//				pstmt.setInt(++i, C_BPartner_ID);
//			pstmt.setString(++i, DB.TO_STRING(general));
//			pstmt.setString(++i, fecha);
//			pstmt.setString(++i, fecha);
//			if (C_BPartner_ID > 0)
//				pstmt.setInt(++i, C_BPartner_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MPaqBaseVersion pack = new MPaqBaseVersion(ctx, rs, trxName);
//				pack.setPaqBase(new MPaqBase(ctx, pack.getEXME_PaqBase_ID(), trxName));
//				retValue.add(pack);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPaqBase_Version.get", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return retValue;
//
//	}
    
//    /**
//     * Calculamos el total de la version sin impuestos a partir del detalle
//     */
//    public void setTotalAmt() {
//        
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append("SELECT SUM(LineNetAmt) FROM EXME_PaqBaseDet ")
//        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
//        	.append("AND IsActive = 'Y' ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
//        ResultSet rs = null;
//        
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				setTotalAmt(rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP));
//			} else {
//				setTotalAmt(Env.ZERO);
//			}
//
//
//		} catch (Exception e)	{
//			log.log(Level.SEVERE, "setTotalAmt", e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close ();
//				}
//				
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "Closing objects", e);
//			}
//		}
//    }
//    
//    /**
//     * Calculamos el total de la version con impuestos a partir del detalle
//     */
//    public void setTaxAmt() {
//    	
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append("SELECT SUM(TaxAmt) FROM EXME_PaqBaseDet ")
//        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
//        	.append("AND IsActive = 'Y' ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
//        
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//			    setTaxAmt(rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP));
//			} else {
//				setTaxAmt(Env.ZERO);
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "setTaxAmt", e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close ();
//				}
//				
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "setTaxAmt - while closing objects", e);
//			}
//		}
//    }
//    
//    /**
//     * Calculamos la base de la version a partir del detalle
//     */
//    public void setBaseAmt() {
//        
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//    	
//        sql.append("SELECT SUM(Cantidad * PriceList) FROM EXME_PaqBaseDet ")
//        	.append("WHERE EXME_PaqBase_Version_ID = ? ")
//        	.append("AND IsActive = 'Y' ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_PaqBaseDet"));
//        
//        ResultSet rs = null;
//        
//		PreparedStatement pstmt = null;
//		try	{
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				setBaseAmt((rs.getBigDecimal(1).setScale(2,BigDecimal.ROUND_HALF_UP)));
//			} else {
//				setBaseAmt(Env.ZERO);
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "setBaseAmt", e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "setBaseAmt - Closing objects", e);
//			}
//		}
//    }
    
//    public static boolean isUsed(Properties ctx, int EXME_PaqBase_Version_ID, String trxName){
//    	boolean res = false;
//    	int cont = 0;
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//    	
//        sql.append("select count(c_invoice_id) from c_invoiceline where EXME_PaqBase_Version_ID = ? ")
//           .append("AND IsActive = 'Y' ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_InvoiceLine"));
//        
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_PaqBase_Version_ID);
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				cont = rs.getInt(1);
//			}
//
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//			rs=null;
//		}
//		catch (Exception e)
//		{
//		    e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if (pstmt != null)
//					pstmt.close ();
//			}
//			catch (Exception e)
//			{}
//			pstmt = null;
//		}
//		if(cont>0)
//			res = true;
//    	return res;
//    }
//    
//    @Override
//	public String[] getColumns() {
//		String[] cols = { "idColumn", "validFrom", "validTo", "name", "totalAmt", "description"};
//		return cols;
//	}
//
//	@Override
//	public IDColumn getIdColumn() {
//		return new IDColumn(getEXME_PaqBase_Version_ID());
//	}
	
	
//	public static  List<LabelValueBean> getPacksLVB(Properties ctx, int C_BPartner_ID, 
//			Timestamp fecha){
//    	List<LabelValueBean> lstLVB = new ArrayList<LabelValueBean>();
//    	List<MEXMEPaqBaseVersion> lst = MPaqBaseVersion.getPacks(ctx, C_BPartner_ID, fecha, null);
//    	for (int i = 0; i < lst.size(); i++) {
//    		lstLVB.add(new LabelValueBean(lst.get(i).getPaquete().getName()+" - "+ 
//    				lst.get(i).getName(), String.valueOf(lst.get(i).getEXME_PaqBase_Version_ID())));
//		}
//    	return lstLVB ;
//    }
	
//	 /**
//     * 
//     * @param ctx
//     * @param cadena
//     * @param fechaDesde
//     * @param fechaHasta
//     * @param trxName
//     * @return
//     */
//    private static List<MEXMEPaqBaseVersion> getPacks(Properties ctx, int C_BPartner_ID, 
//            Timestamp fecha, String trxName){
//        
//    	 List<MEXMEPaqBaseVersion> retValue = new ArrayList<MEXMEPaqBaseVersion>();
//		
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append(" SELECT EXME_PaqBase_Version.* " )
//	    .append(" FROM EXME_PaqBase_Version " )
//	    .append(" WHERE EXME_PaqBase_Version.IsActive = 'Y' " );
//        if (DB.isOracle()) {
//        	sql.append(" AND trunc( EXME_PaqBase_Version.ValidFrom,'dd') <=  trunc(? ,'dd') ");
//        } else if (DB.isPostgreSQL()) {
//        	// RQM: 2878. No se ven los paquetes.
//        	//Cuando se usa un date_trunc con un timestamp no funciona el pstmt.setTimestamp, se debe dar formato.
//        	//Como abajo se muestra con TO_DATE.
//        	sql.append(" AND DATE_TRUNC('day', EXME_PaqBase_Version.ValidFrom) <=  DATE_TRUNC('day', TO_DATE(?,'dd/MM/yyyy')) ");
//        }
//	    sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_PaqBase_Version.Table_Name));
//	                
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			if (DB.isOracle()) {
//				pstmt.setTimestamp(1, fecha);
//			} else if (DB.isPostgreSQL()) {
//				//Setear el String con un SimpleDateFormat ya que no funciona con el date_trunc el timestamp.
//				pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
//			}
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MEXMEPaqBaseVersion pack = new MEXMEPaqBaseVersion(ctx, rs, trxName);
//				pack.setPaqBase(new MEXMEPaqBase(ctx, pack.getEXME_PaqBase_ID(), trxName));
//				retValue.add(pack);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPaqBase_Version.get : sql = " + sql
//					+ " param : " + fecha, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return retValue;
//
//	}
}

