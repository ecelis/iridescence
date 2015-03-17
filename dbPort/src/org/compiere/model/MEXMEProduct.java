package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;

public class MEXMEProduct extends MProduct {

    /** serialVersionUID */
	private static final long serialVersionUID = -9214305060571781646L;
	/** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMEProduct.class);
    
   public MEXMEProduct(Properties ctx, int M_Product_ID, String trxName) {
        super(ctx, M_Product_ID, trxName);
    }

    public MEXMEProduct(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MEXMEProduct(MExpenseType et) {
        super(et);
    }

    public MEXMEProduct(MResource resource, MResourceType resourceType) {
        super(resource, resourceType);
    }

    public MEXMEProduct(X_I_Product impP) {
    	//Para permitir la modificacion necesaria para SICOME
        super(impP, false);
    }
    
    public boolean getNumSerie() {
        return isNumSerie();
    }
    /**
     *  Verifica que el prod cumpla todos los requerimiento
     *  @param El identificador del producto obligatorio
     *  @param El identificador del almacen obligatorio
     *  @param El identificador de la especialidad en caso de que sea una indicacion
     *  @param El identificador del tipo de prod en caso de que sea un servicio
     *  
     */
    public static void getProdEnAlm(Properties ctx, long prodID, long almacen, 
    		long especialidad, String tipo) 
    throws Exception{
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
                
		sql.append("SELECT M_Replenish.M_Product_ID FROM M_Replenish ");
		if (especialidad != 0) {
			sql.append(", EXME_ProductEsp pe ");
		} else if (tipo != null && tipo.length() > 0) {
			sql.append(", M_Product p ");
		}
        
        sql.append(" WHERE M_Replenish.M_Warehouse_ID = ")
        .append(almacen)
        .append(" AND M_Replenish.M_Product_ID = ")
        .append(prodID);

		if (especialidad != 0) {
        	sql.append(" AND M_Replenish.M_Product_ID = pe.M_Product_ID AND pe.EXME_Especialidad_ID = ")
        	.append(especialidad);
		} else if (tipo != null && tipo.length() > 0) {
        	sql.append(" AND M_Replenish.M_Product_ID = p.M_Product_ID AND p.producttype = '")
        	.append(tipo).append("' ");
		}

        sql .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Replenish"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
			if (!rs.next()) {
				if (especialidad != 0) {
					throw new Exception("error.citas.existeIndiAlm");
            	} else if(tipo != null && tipo.length() > 0) {
						throw new Exception("error.citas.existeServAlm");
					}
				}
        
		} catch (Exception e) {
            s_log.log(Level.WARNING, "getProdEnAlm", e);
            
            throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
	}
    
   
	public static MProduct getProducto(Properties ctx, String value, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM M_Product WHERE M_Product.IsActive = 'Y' AND UPPER(M_Product.Value) LIKE UPPER(?)");

		if (Env.getUserPatientID(ctx) <= 0){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));
		}
		sql.append(" ORDER BY M_Product.Name");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MProduct prod = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next())
				prod = new MProduct(ctx, rs, trxName);

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getProducto (" + sql + ")", e);
		}  finally {
			DB.close(rs, pstmt);
		}
		return prod;
	}
	
	
	//select p.* from M_PRODUCT p inner join EXME_LOINC l on L.EXME_LOINC_ID = p.exme_loinc_id and L.VALUE = '24331-1'
	/**
	 * Devuelve un resultSet con un producto a partir del value de LOINC.
	 *
	 * @param producto El producto del que se desea el nombre.
	 *
	 * @return Un valor String con el nombre del producto.
	 * @throws Exception en caso de ocurrir un error en la consulta o 
	 * no existir el producto.
	 */
	public static MProduct getProductoByLOINCValue(Properties ctx, String value, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select M_PRODUCT.* from M_PRODUCT, EXME_LOINC ").append(
				"where EXME_LOINC.EXME_LOINC_ID = m_Product.exme_loinc_id and upper(EXME_Loinc.VALUE) = ?");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));

		sql.append(" ORDER BY M_Product.Name");
		PreparedStatement	pstmt = null;
		ResultSet rs = null;

		MProduct prod = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			//value = value.replaceAll("\\*", "%");//Lama: comodin estandar %
			pstmt.setString(1, value);
		
			rs = pstmt.executeQuery();
			if(rs.next())
				prod = new MProduct(ctx, rs, trxName);
			
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getProducto (" + sql + ")", e);

        }  finally {
			DB.close(rs, pstmt);
		}

		return prod;
	}

	
	  /**
	 * Devuelve un resultSet con un producto a partir del value.
	 *
	 * @param producto El producto del que se desea el nombre.
	 *
	 * @return Un valor String con el nombre del producto.
	 * @throws Exception en caso de ocurrir un error en la consulta o 
	 * no existir el producto.
	 */

	public static MProduct getProductoValueID(Properties ctx, String value, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append( " SELECT * FROM M_Product WHERE ")
		.append( " M_Product.IsActive = 'Y' " )
		.append( " AND (UPPER(M_Product.Value) LIKE UPPER(?) OR M_Product.M_Product_ID = ? OR UPPER(M_Product.UPC) = UPPER(?)) ");
		           
		sql.append( MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));
		
		sql.append(" ORDER BY M_Product.Name");
		PreparedStatement	pstmt = null;
		ResultSet rs = null;

		MProduct prod = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			//value = value.replaceAll("\\*", "%");//Lama: comodin estandar %
			pstmt.setString(1, value);
			
			int producto = 0;
			try {
				producto = Integer.parseInt(value);
			} catch (Exception ex) {
				s_log.log(Level.INFO, "", ex);
			}

			pstmt.setInt(2, producto);
			//to lookup by UPC
			pstmt.setString(3, value);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				prod = new MProduct(ctx, rs, trxName);
			}
			
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getProducto (" + sql + ")", e);
        }  finally {
			DB.close(rs, pstmt);
		}

		return prod;

	}//Expert

	public static MProduct getProductbyName(Properties ctx, String name, String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MProduct prod = null;

		sql.append(" SELECT * ")
			.append(" FROM M_Product")
			.append(" INNER JOIN EXME_ProductoOrg org on M_Product.M_product_id = org.M_product_id ")
			.append(" WHERE UPPER( M_Product.Name) LIKE UPPER(?) AND M_Product.IsActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next())
				prod = new MProduct(ctx, rs, trxName);

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getProducto (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return prod;
	}
	
	/**
	 * Obtiene los productos excluyendo los ids enviados
	 * 
	 * @param ctx
	 * @param value
	 * @param notIn
	 * @param maxNum
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEProduct> get(Properties ctx, String value, Integer[] notIn, int maxNum, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM M_Product WHERE isActive = 'Y' ");
		if (notIn != null && notIn.length > 0) {
			sql.append("AND EXME_Diagnostico_ID not in (").append(StringUtils.join(notIn, ",")).append(") ");
		}
		if (!StringUtils.isEmpty(value)) {
			sql.append("AND  (upper(value) LIKE upper('%").append(value.toUpperCase()).append("%' ) OR ");
			sql.append("  upper(name) LIKE upper('%").append(value.toUpperCase()).append("%' )) ");
		}
		
		if (DB.isOracle()) {
			sql.append(" AND rownum < ").append(maxNum).append(" ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));
		sql.append(" order by value ");
		
		if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, maxNum-1));
		}
		
		List<MEXMEProduct> lista = new ArrayList<MEXMEProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMEProduct(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
	
	/**
	 * Obtiene la lista de los productos servicios
	 * @param ctx Contexto
	 * @param active Indicamos si queremos los regsitros activos (Y), inactivos (N) o todos (null)
	 * @return List<MEXMEProduct>
	 * @author mvrodriguez
	 */
	public static List<MEXMEProduct> getProductsService(Properties ctx, String active) {
		List<MEXMEProduct> list = new ArrayList<MEXMEProduct>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * ");
		sql.append("  FROM M_PRODUCT ");
		sql.append(" WHERE M_PRODUCT.EXME_INTERVENCION_ID IS NOT NULL ");
		
		if(active != null || !"".equals(active)) {
			sql.append(" AND M_PRODUCT.ISACTIVE = ? ");
		}
		
		sql .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_Product"));
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			if (active != null || !"".equals(active)) {
				pstmt.setString(1, active);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEProduct(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Obtenemos los atributos de un produto asociado a un Procedimiento
	 * @param ctx Contexto
	 * @param active Indicamos si queremos los regsitros activos (Y), inactivos (N) o todos (null)
	 * @param interventionId Indicamos el procedimiento al que deben de estar asociado el producto
	 * @return MEXMEProduct Producto que esta asociado al procedimiento
	 * @throws Exception
	 * @author mvrodriguez
	 */
	public static MEXMEProduct getProductRelIntervention(Properties ctx, String active, int interventionId) throws Exception {
		
		MEXMEProduct product = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT * ");
		sql.append("  FROM M_PRODUCT ");
		sql.append(" WHERE M_PRODUCT.EXME_INTERVENCION_ID = ? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_Product"));
		
		if(active != null || !"".equals(active)) {
			sql.append(" AND M_PRODUCT.ISACTIVE = ? ");
		} 

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, interventionId);
			
			if(active != null || !"".equals(active)) {
				
				pstmt.setString(2, active);
				
			} 
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				product = new MEXMEProduct(ctx, rs, null);
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}

		return product;

	}
	
	public static List<MEXMETGenProdTrade> getTradeNameByGenProduct(Properties ctx, String active, int genProductId) {
		List<MEXMETGenProdTrade> list = new ArrayList<MEXMETGenProdTrade>();
		MEXMETGenProdTrade tradeName;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		
		sql.append(" SELECT DISTINCT M_PRODUCT.TRADE_NAME_ID, EXME_TRADENAME.NAME ");
		sql.append("  FROM M_PRODUCT ");
		sql.append(" INNER JOIN EXME_GENPRODUCT ON (M_PRODUCT.EXME_GENPRODUCT_ID = EXME_GENPRODUCT.EXME_GENPRODUCT_ID) ");
		sql.append(" INNER JOIN EXME_TRADENAME ON M_PRODUCT.TRADE_NAME_ID = EXME_TRADENAME.TRADE_NAME_ID ");
		sql.append(" WHERE EXME_GENPRODUCT.GENPRODUCT_ID = ? ");
		
		if(active != null || !"".equals(active)) {
			sql.append(" AND M_PRODUCT.ISACTIVE = ? ");
		}
		
		sql .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_Product"));
		
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			
			pstmt.setInt(i++, genProductId);
			
			if (active != null || !"".equals(active)) {
				pstmt.setString(i, active);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tradeName = new MEXMETGenProdTrade(ctx, 0, null);
				tradeName.setTrade_Name(rs.getString(2));
				list.add(tradeName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

//	public static List<ProductView> getProductView(Properties ctx, int option, int max, String string) {
//		/**/
//		List<ProductView> list = new ArrayList<ProductView>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM M_Product WHERE M_Product.IsActive = 'Y' ");
//		sql.append(option(option,null).getCadena1());
//
//		if (Env.getUserPatientID(ctx) <= 0){
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Product"));
//		}
//		sql.append(" ORDER BY M_Product.Name");
//
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//			int count = 0;
//			while (rs.next()) {
//				ProductView tradeName = new ProductView(ctx, rs, null);
//				if(count<max)
//					list.add(tradeName);
//				else
//					break;
//				count++;
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return list;
//	}

	/**
	 * 
	 * @param option
	 * @param opc
	 * @return
	 * @deprecated
	 */
	public static BeanView option(int option, String opc) {
		StringBuilder caso = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		String filtro = "All";
		String tipo = null;
		
		if(option>0 || opc!=null){
			caso.append(" AND ProductClass = ");
			//
			if(option==1 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Drug)){
				opc = X_M_Product.PRODUCTCLASS_Drug;
				option=1;
				tipo = X_M_Product.PRODUCTTYPE_Item;
			} else if(option==2 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Procedures)){
				opc = X_M_Product.PRODUCTCLASS_Procedures;
				option=2;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==3 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Laboratory)){
				opc = X_M_Product.PRODUCTCLASS_Laboratory;
				option=3;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==4 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_XRay)){
				opc = X_M_Product.PRODUCTCLASS_XRay;
				option=4;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==5 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Immunization)){
				opc = X_M_Product.PRODUCTCLASS_Immunization;
				option=5;
				tipo = X_M_Product.PRODUCTTYPE_Service; //"DEF";//Puede ser un servicio o un medicamento
			} else if(option==6 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_MedicalSupplies)){
				opc = X_M_Product.PRODUCTCLASS_MedicalSupplies;
				option=6;
				tipo = X_M_Product.PRODUCTTYPE_Item;
			} else if(option==7 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Rooms)){
				opc = X_M_Product.PRODUCTCLASS_Rooms;
				option=7;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==8 || opc!=null && opc.equals("PK")){
				option=8;
				tipo = X_M_Product.PRODUCTTYPE_Package;
			} else if(option==9 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Others)){
				opc = X_M_Product.PRODUCTCLASS_Others;
				option=9;
				tipo = "DEF";
			} else if(option==10 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Ambulance)){
				opc = X_M_Product.PRODUCTCLASS_Ambulance;
				option=10;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==11 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_HomeHealt)){
				opc = X_M_Product.PRODUCTCLASS_HomeHealt;
				option=11;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==12 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Hospice)){
				opc = X_M_Product.PRODUCTCLASS_Hospice;
				option=12;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==13 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Surgeries)){
				opc = X_M_Product.PRODUCTCLASS_Surgeries;
				option=13;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==14 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_Anesthesic)){
				opc = X_M_Product.PRODUCTCLASS_Anesthesic;
				option=14;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			} else if(option==19 || opc!=null && opc.equals(X_M_Product.PRODUCTCLASS_OtherService)){
				opc = X_M_Product.PRODUCTCLASS_OtherService;
				option=19;
				tipo = X_M_Product.PRODUCTTYPE_Service;
			}
			if(opc!=null){ 
			caso.append(" = ").append(DB.TO_STRING(opc));
			filtro = opc;}
		}
			/*if(1==option){

				// DRUG
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Drug));
				filtro = X_M_Product.PRODUCTCLASS_Drug;

			} else if(2==option){

				// Procedures
				caso.append(" IN ( ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Anesthesic))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Procedures))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Surgeries))
				.append(") ");

				filtro = X_M_Product.PRODUCTCLASS_Anesthesic
				+"','"+X_M_Product.PRODUCTCLASS_Procedures
				+"','"+X_M_Product.PRODUCTCLASS_Surgeries;

			} else if(3==option){

				// Laboratory
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Laboratory));
				filtro = X_M_Product.PRODUCTCLASS_Laboratory;

			} else if(4==option){

				// Estudios
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_XRay)).append(" ");
				filtro = X_M_Product.PRODUCTCLASS_XRay;

			} else if(5==option){

				// Vacunas
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Immunization));
				filtro = X_M_Product.PRODUCTCLASS_Immunization;

			} else if(6==option){

				// Material
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_MedicalSupplies));
				filtro = X_M_Product.PRODUCTCLASS_MedicalSupplies;

			} else if(7==option){

				// Habitacion
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Rooms));
				filtro = X_M_Product.PRODUCTCLASS_Rooms;

			} else if(8==option){

				// Paquetes
				caso.append(" IN ( ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Laboratory))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_XRay))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Drug))
				.append(") ");

				filtro = X_M_Product.PRODUCTCLASS_XRay
				+"','"+X_M_Product.PRODUCTCLASS_Laboratory
				+"','"+X_M_Product.PRODUCTCLASS_Drug;

			} else if(9==option){
				// Otros
				caso.append(" IN (").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_OtherService))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Cultures))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Blood))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Treatment))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_OfficeVisit))
				.append(") ");

				//
				filtro = X_M_Product.PRODUCTCLASS_OtherService
				+"','"+X_M_Product.PRODUCTCLASS_Cultures
				+"','"+X_M_Product.PRODUCTCLASS_Blood
				+"','"+X_M_Product.PRODUCTCLASS_Treatment
				+"','"+X_M_Product.PRODUCTCLASS_OfficeVisit;
			} else if(10==option){
				// Ambulance	
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Ambulance));
				filtro = X_M_Product.PRODUCTCLASS_Ambulance;
			} else if(11==option){
				// Home Health
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_HomeHealt));
				filtro = X_M_Product.PRODUCTCLASS_HomeHealt;
			} else if(12==option){
				// Hospice			
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Hospice));
				filtro = X_M_Product.PRODUCTCLASS_Hospice;

			} else if(13==option){
				// FQHC/RHC
				caso.append(" = ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_FQHCRHC));
				filtro = X_M_Product.PRODUCTCLASS_FQHCRHC;

			} else if(14==option){
				// Physician Services / OfficeVisit
				caso.append(" IN (").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_PhysicianServices))
				.append(", ").append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_OfficeVisit))
				.append(") ");

				//
				filtro = X_M_Product.PRODUCTCLASS_PhysicianServices
				+"','"+X_M_Product.PRODUCTCLASS_OfficeVisit;
			}
			*/
		//}// fin option > 0

		BeanView bean = new BeanView();
		bean.setCadena1(caso.toString());
		bean.setCadena2(filtro);
		bean.setCadena3(tipo);
		bean.setInteger1(option);
		return bean;
	}
/*
	public static int optionRev(String option) {
		int filtro = 0;

		if(option!=null){

			// DRUG
			if(option.equals(X_M_Product.PRODUCTCLASS_Drug)){
				filtro =1;

			} else 

				// Procedures
				if(option.equals(X_M_Product.PRODUCTCLASS_Anesthesic)
						||option.equals(X_M_Product.PRODUCTCLASS_Procedures)
						||option.equals(X_M_Product.PRODUCTCLASS_Surgeries)){

					filtro = 2;

				} else 

					// laboratory
					if(option.equals(X_M_Product.PRODUCTCLASS_Laboratory)){
						filtro = 3;

					} else 

						// Estudios
						if(option.equals(X_M_Product.PRODUCTCLASS_XRay)){
							filtro = 4;

						} else 

							// Vacunas
							if(option.equals(X_M_Product.PRODUCTCLASS_Immunization)){
								filtro = 5;

							} else 

								// Material
								if(option.equals(X_M_Product.PRODUCTCLASS_MedicalSupplies)){
									filtro = 6;

								} else 

									// Habitacion
									if(option.equals(X_M_Product.PRODUCTCLASS_Rooms)){
										filtro = 7;

									} else 

										// Paquetes
										if(option.equals(X_M_Product.PRODUCTCLASS_Laboratory)
												||option.equals(X_M_Product.PRODUCTCLASS_XRay)
												||option.equals(X_M_Product.PRODUCTCLASS_Drug)){

											filtro = 8;

										} else 
											// Otros
											if(option.equals(X_M_Product.PRODUCTCLASS_OtherService)
													||option.equals(X_M_Product.PRODUCTCLASS_Cultures)
													||option.equals(X_M_Product.PRODUCTCLASS_Blood)
													||option.equals(X_M_Product.PRODUCTCLASS_Treatment)){

												filtro = 9;
											} else 
												// Ambulance	
												if(option.equals(X_M_Product.PRODUCTCLASS_Ambulance)){
													filtro = 10;
												} else 
													// Home Health
													if(option.equals(X_M_Product.PRODUCTCLASS_HomeHealt)){
														filtro = 11;
													} else
														// Hospice			
														if(option.equals(X_M_Product.PRODUCTCLASS_Hospice)){
															filtro = 12;

														} else
															// FQHC/RHC
															if(option.equals(X_M_Product.PRODUCTCLASS_FQHCRHC)){
																filtro = 13;

															} else
																// Physician Services / OfficeVisit
																if(option.equals(X_M_Product.PRODUCTCLASS_PhysicianServices)
																		|| option.equals(X_M_Product.PRODUCTCLASS_OfficeVisit)){
																	filtro = 14;
																}
		}// fin option > 0

		return filtro;
	}
*/

	/**
	 * Categoria de producto
	 */
	private MProductCategory productCategory;
	
	public MProductCategory getProductCategory(){
		if(productCategory==null){
			productCategory = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
		}
		return productCategory;
	}
	
	/**
	 * @deprecated
	 */
	public void getValuesForClass(){
		
		/**/
		if(option(0,getProductClass()).getCadena3().equals(X_M_Product.PRODUCTTYPE_Item)){
			//setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Item);
			setAD_Client_ID(0);
			setIsSold(true);
		} else if(option(0,getProductClass()).getCadena3().equals(X_M_Product.PRODUCTTYPE_Service)){
			//setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);
			setIsSold(false);
		} else if(option(0,getProductClass()).getCadena3().equals(X_M_Product.PRODUCTTYPE_Package)){
			//setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Package);
			//setAD_Client_ID(0);// A nivel de organizacion
			//setIsSold(false);
		} else if(option(0,getProductClass()).getCadena3().equals("DEF")){
			// Define el usuario cuales serian las caracteristicas de este producto
		}
		
		/*
		
		// medicamento
		if(option(0,getProductClass()).getInteger1()==1){
			setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Item);
			setAD_Client_ID(0);setIsSold(true);
		} else if(option(0,getProductClass()).getInteger1()==2){
			// procedimiento
			
		} else if(option(0,getProductClass()).getInteger1()==3){
			// hcpc
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==4){
			// laboratorio
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==5){
			// vacunas
			if(getProductType().equals(X_M_Product.PRODUCTTYPE_Service)){
				setIsEstudio(true);setIsSold(false);
			} else {
				setIsEstudio(false);setIsSold(true);
			}
			setAD_Client_ID(0);
		} else if(optionRev(getProductClass())==6){
			// material
			setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Item);setIsSold(false);
		} else if(optionRev(getProductClass())==7){
			// habitacion
			setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Item);setIsSold(false);
		} else if(optionRev(getProductClass())==8){
			// paquetes
			setIsEstudio(false);
			setProductType(X_M_Product.PRODUCTTYPE_Item);setIsSold(false);
		} else if(optionRev(getProductClass())==9){
			// otros
			if(getProductType().equals(X_M_Product.PRODUCTTYPE_Service)){
				setIsEstudio(true);
			} else {
				setIsEstudio(false);
			}
		} else if(optionRev(getProductClass())==10){
			// Ambulance	
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==11){
			// Home Health
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==12){
			// Hospice			
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==13){
			// FQHC/RHC
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		} else if(optionRev(getProductClass())==14){
			// Physician Services / OfficeVisit
			setIsEstudio(true);
			setProductType(X_M_Product.PRODUCTTYPE_Service);
			setAD_Client_ID(0);setIsSold(false);
		}
		*/
	}

	/**
	 * Obtiene la lista de los Productos que son Porcedimientos y que tengan cierto Tipo de Procedimiento
	 * @param ctx Contexto
	 * @param trxName
	 * @param procedureType
	 * @return List<MEXMEProduct>
	 * @author lherrera
	 */
	public static List<LabelValueBean> getProcedureType(Properties ctx, String trxName, String procedureType) {
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql.append("Select Name, M_Product_ID From M_Product Where Isestudio = 'N' And (Producttype = 'S' Or ProductType = 'P') ");
		sql.append("Select Name, M_Product_ID From M_Product Where (Producttype = 'S' Or ProductType = 'P') ");
		sql.append("And Exme_Intervencion_Id Is Not Null And Productclass = 'PR' And ProcedureType = ?");
		sql .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","M_Product"));
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, procedureType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Objeto que contiene el precio de l producto
	 */
	private MProductPrice productPrice;
	
	public void setProductPrice(MProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public MProductPrice getProductPrice(){
		return productPrice;
	}
	
	public static int getProduct(Properties ctx, String intervencion, String productClass, String trxName){
		int id = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.M_Product_ID ");
		sql.append("FROM ");
		sql.append("  M_Product p ");
		sql.append("  INNER JOIN EXME_Intervencion i ");
		sql.append("  ON p.EXME_Intervencion_ID = i.EXME_Intervencion_ID ");
		sql.append("WHERE ");
		sql.append("  i.value = ? AND ");
		sql.append("  i.isActive = 'Y' AND ");
		sql.append("  p.isActive = 'Y' AND ");
		sql.append("  p.productClass = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product", "p"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, intervencion);
			pstmt.setString(2, productClass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return id;
	}
	
	public static int getProduct(Properties ctx, int intervencionID, String trxName) {
		int id = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  M_Product_ID ");
		sql.append("FROM ");
		sql.append("  m_product ");
		sql.append("WHERE ");
		sql.append("  exme_intervencion_id = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, intervencionID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return id;
	}
	
	/**
	 * Obtener precio del producto
	 * 
	 * @return
	 */
	public BigDecimal getPrice() {
		BigDecimal price = BigDecimal.ZERO;
		MEXMEConfigPre config = MEXMEConfigPre.get(getCtx(), null);
		if (config != null) {
			int id = config.getPriceList().getVersionActual().getM_PriceList_Version_ID();
			MProductPrice mprice = MEXMEProductPrice.get(getCtx(), getM_Product_ID(), id, null);
			if (mprice != null) {
				price = mprice.getPriceList();
			}
		}
		return price;
	}
	
	/**
	 * Busca productos por clase de producto
	 * @param ctx: contexto
	 * @param productClass: clase producto, lista de referencia
	 * @param trxName: nombre de transaccion
	 * @return Listado con objetos MEXMEProduct
	 */
	public static List<MEXMEProduct> getProductClass(Properties ctx,  String productClass, String trxName){
		 List<MEXMEProduct> lstObj = new ArrayList<MEXMEProduct>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  * ");
		sql.append("FROM ");
		sql.append("  M_Product p ");
		sql.append("WHERE ");
		sql.append("  p.isActive = 'Y' AND ");
		sql.append("  p.productClass = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product", "p"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, productClass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstObj.add(new MEXMEProduct(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstObj;
	}
	
	/**
	 * Obtenemos si la organizacion destino tiene este producto mediante su CPT
	 * 
	 * @param ctx
	 * @param AD_Org_ID
	 * @param cptName
	 * @param trxName
	 * @return true Si trae resultados del query
	 * @throws SQLException
	 */

	public static boolean getProductoOrg(Properties ctx, int AD_Org_ID, String interValue, String trxName) throws SQLException {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT prod.M_Product_ID FROM M_Product prod ")
		.append("INNER JOIN EXME_Intervencion inter ON inter.EXME_Intervencion_ID = prod.EXME_Intervencion_ID ")
		.append("WHERE prod.AD_Org_ID = ? AND ")
		.append("inter.Value = ?") // FIXME: Se usara el Value de la Intervencion (CPT) para fines de Demo
		.append(" AND prod.isActive = 'Y' ");
		
		return DB.getSQLValue(trxName, sql.toString(), AD_Org_ID, interValue)	> 0	;
	}
	
	/**
	 * Busca productos por clase de producto
	 * @param ctx: contexto
	 * @param productClass: clase producto, lista de referencia
	 * @param trxName: nombre de transaccion
	 * @return Listado con objetos MEXMEProduct
	 */
	public static MEXMEProduct getProductPMID(Properties ctx,  String pmid, String trxName){
		MEXMEProduct prod = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  * ");
		sql.append("FROM ");
		sql.append("  M_Product  ");
		sql.append("WHERE ");
		sql.append("  pmid = ? ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, pmid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				prod = new MEXMEProduct(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return prod;
	}	
}