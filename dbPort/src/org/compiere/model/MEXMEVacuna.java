package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.compiere.util.WebEnv;


/**
 * Copyright (c) 2008 Expert Sistemas Computacionales S.A. de C.V.
 * Todos los derechos  reservados.
 * Nombre del Archivo:  MEXMEVacuna.java  
 * Proposito: Implementacion de la funcionalidad para la aplicacion de vacunas 
 *            y consulta de cartilla de vacunacion.
 * Clases: VacunacionAction.java
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2009/05/22 01:28:38 $ <p>
 *
 * @author Twry Perez
 * @version $Revision: 1.0.0 $
 */

public class MEXMEVacuna extends X_EXME_Vacuna {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEVacuna.class);
	
	/**
	 * Constructor ID
	 * @param ctx
	 * @param EXME_Hist_Vacuna_ID
	 * @param trxName
	 */
	public MEXMEVacuna(Properties ctx, int EXME_Vacuna_ID, String trxName) {
		super(ctx, EXME_Vacuna_ID, trxName);
	}

	/**
	 * Constructor ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEVacuna(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 * Obtener el objeto por medio de un ResultSet
	 * @param rs
	 * @throws SQLException
	 */
	public void get(ResultSet rs) 
	throws SQLException
	{
		setAD_Client_ID(rs.getInt("AD_Client_ID"));
		setAD_Org_ID(rs.getInt("AD_Org_ID"));
		setC_UOM_ID(rs.getInt("UOMVacuna"));
		setCantidad(rs.getBigDecimal("Cantidad"));
		setDescription(rs.getString("Description"));
		setEXME_Diagnostico_ID(rs.getInt("EXME_Diagnostico_ID"));
		setEXME_Diagnostico2_ID(rs.getInt("EXME_Diagnostico2_ID"));
		setEXME_Diagnostico3_ID(rs.getInt("EXME_Diagnostico3_ID"));;
		setEXME_Diagnostico4_ID(rs.getInt("EXME_Diagnostico4_ID"));
		setEXME_Diagnostico5_ID(rs.getInt("EXME_Diagnostico5_ID"));
		setIncluyeCartilla(rs.getString("IncluyeCartilla").equalsIgnoreCase("Y")?true:false);
		setIsActive(true);
		setM_Product_ID(rs.getInt("M_Product_ID"));
		setRel_Vacuna_ID(rs.getInt("Rel_Vacuna_ID"));
		setSexo(rs.getString("Sexo"));
		setValue(rs.getString("Value"));
		setVia(rs.getString("Via"));
		setEXME_Vacuna_ID(rs.getInt("EXME_Vacuna_ID"));
		setEnfermedades(rs.getString("Enfermedades"));
		setSeleccionada(false);
	}
	
	/**
	 * lista con el detalle de las vacunas
	 */
	private List<MEXMEVacunaDet> secuencias = null;
	
	public List<MEXMEVacunaDet> getSecuencias() {
		return secuencias;
	}

	public void setSecuencias(List<MEXMEVacunaDet> secuencias) {
		this.secuencias = secuencias;
	}
	
	/**
	 * Permite reconocer si esta seleccionada
	 * la vacuna para aplicarla
	 */
	private boolean seleccionada = false;

	public boolean getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

	/**
	 * Cadena con el nombre de las enfermedades a cubrir 
	 * con la aplicacion de la vacuna
	 */
	private String enfermedades = null;
	
	public String getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}

	/**
	 *  Devuelve un objeto tipo lista con las vacunas
	 *  de la cartilla con sus diversas aplicaciones
	 *  con la finalidad de formar la cartilla de vacunacion
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  cadena       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static List<MEXMEVacuna> vacunasCartilla(Properties ctx, 
			boolean soloCartilla, int AD_Reference_ID)
			throws Exception {

		//Lista de vacunas
		List<MEXMEVacuna> listaVacunas = new ArrayList<MEXMEVacuna>();
		
		if(ctx==null)
			return listaVacunas;
		
		//Objeto vacuna creado por el select
		MEXMEVacuna vacuna = null;
		
		//Objeto creado con el detalle de la vacuna
		MEXMEVacunaDet vacunaDet = null;
		
		//Consulta
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT v.EXME_Vacuna_ID , v.IncluyeCartilla ")
		.append(", v.C_UOM_ID AS UOMVacuna ")
		.append(", v.Cantidad ")
		.append(", v.Description ")
		.append(", v.EXME_Diagnostico_ID ")
		.append(", v.EXME_Diagnostico2_ID ")
		.append(", v.EXME_Diagnostico3_ID ")
		.append(", v.EXME_Diagnostico4_ID ")
		.append(", v.EXME_Diagnostico5_ID ")
		.append(", v.M_Product_ID ")
		.append(", v.Rel_Vacuna_ID ")
		.append(", v.Sexo ")
		.append(", v.Value ")
		.append(", v.Via ")
		.append(", v.AD_Client_ID ")
		.append(", v.AD_Org_ID ")
		.append(", vd.EXME_VacunaDet_ID ")
		.append(", vd.C_UOM_ID AS UOMDetalle ")
		.append(", vd.EdadMaxima ")
		.append(", vd.EdadMinima ")
		.append(", vd.Intervalo ")
		.append(", vd.Secuencia ")
		.append(", vd.TipoDosis , rl.name AS DosisIngles ")
		.append(", NVL(rlt.name,' ') AS DosisIdioma ")
		.append(", NVL(d1.Name,' ') || ' ' || NVL(d2.Name,' ') || ' ' || NVL(d3.Name,' ') || ' ' || NVL(d4.Name,' ') || ' ' || NVL(d5.Name,' ') AS Enfermedades ")
		.append(" FROM EXME_Vacuna v ")
		.append(" INNER JOIN EXME_VacunaDet vd   ON v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d1 ON v.EXME_Diagnostico_ID  = d1.EXME_Diagnostico_ID AND d1.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d2 ON v.EXME_Diagnostico2_ID = d2.EXME_Diagnostico_ID AND d2.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d3 ON v.EXME_Diagnostico3_ID = d3.EXME_Diagnostico_ID AND d3.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d4 ON v.EXME_Diagnostico4_ID = d4.EXME_Diagnostico_ID AND d4.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d5 ON v.EXME_Diagnostico5_ID = d5.EXME_Diagnostico_ID AND d5.IsActive = 'Y' ")
		
		.append(" INNER JOIN AD_Ref_List      rl ON rl.Value = vd.TipoDosis  AND  AD_Reference_ID = ? ")
		.append(" LEFT  JOIN AD_Ref_List_Trl rlt ON rl.AD_Ref_List_ID = rlt.AD_Ref_List_ID AND rlt.AD_Language = ? ")
		
		.append(" WHERE v.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Vacuna", "v"));
		
		if(soloCartilla)
		{
			sql.append(" AND v.IncluyeCartilla = 'Y'  ");
		}
		else
		{
			sql.append(" AND v.IncluyeCartilla = 'N'  ");
		}
		
		sql.append(" ORDER BY v.EXME_Vacuna_ID, vd.Secuencia  ");

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE, "SQL : " + sql.toString());
		}
		
        String AD_Language = Env.getAD_Language(ctx);
        //boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Reference_ID);
			pstmt.setString(2, AD_Language);
			
			rs = pstmt.executeQuery();

			//La primera coincidencia
			int vacunaOld = 0;
			int vacunaNew = 0;

			while (rs.next()) {

				//Vacuna iterada
				vacunaNew = rs.getInt("EXME_Vacuna_ID");

				//Creamos el objeto de vacuna sin que se repitan
				if ( vacunaOld != vacunaNew) 
				{
					vacuna = new MEXMEVacuna(ctx, 0, null);
					vacuna.get(rs);

					//Verificar que el detalle se agregue correctamente sobre la lista
					listaVacunas.add(vacuna);
					
					//Actualizamos la vacuna
					vacunaOld = rs.getInt("EXME_Vacuna_ID");
					vacuna.setSecuencias(new ArrayList<MEXMEVacunaDet>());
				}


				if (vacuna!=null)
				{
					vacunaDet = new MEXMEVacunaDet(ctx, 0, null);
					vacunaDet.get(rs);

					//Agregamos la secuencia a la lista
					if (vacuna.getSecuencias() != null)
						vacuna.getSecuencias().add(vacunaDet);
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs = null;
		}
		
		return listaVacunas;
	}

	/**
	 *  Devuelve un objeto apartir del value del Vacuna dado
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  value       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static X_EXME_Vacuna getObjVacunaFromValue(
			Properties ctx, String value)
			throws Exception {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//value = value.replaceAll("\\*", "%");//Lama: comodin estandar %

		sql.append(" SELECT EXME_Vacuna.* ")
		.append(" FROM EXME_Vacuna ")
		.append(" WHERE UPPER(EXME_Vacuna.Value ) LIKE UPPER( ? ) ")
		.append(" AND EXME_Vacuna.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Vacuna"));

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE,"SQL : " + sql.toString() + "  value: " + value);
		}

		X_EXME_Vacuna vacuna = null;
		try {
		    pstmt = DB.prepareStatement(sql.toString(), null);
		    pstmt.setString(1, value.trim());

		    rs = pstmt.executeQuery();

		    //La primera coincidencia
			if (rs.next()) {
				vacuna = new X_EXME_Vacuna(ctx, rs, null);
			}
			
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
    		DB.close(rs,pstmt);
    			pstmt = null;
    			rs = null;
    	}
		return vacuna;
	}
	/**
	 * Obtiene las vacunas de acuerdo a la edad y el sexo
	 * @param ctx
	 * @param sexo
	 * @param edad
	 * @return
	 * lhernandez
	 */
	public static List<MEXMEVacuna> get(Properties ctx, String sexo, double edad){
		List<MEXMEVacuna> lista = new ArrayList<MEXMEVacuna>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		boolean sexoBln = false;
		
		// solo considerar para el filtro F y M
		if(MEXMEVacuna.SEXO_Female.equals(sexo) || MEXMEVacuna.SEXO_Male.equals(sexo)){
			sexoBln = true;
		}
		
		if(sexoBln){
			str.append(" AND v.sexo in (")
			.append(DB.TO_STRING(MEXMEVacuna.SEXO_Ambiguous)).append(",")
			.append(DB.TO_STRING(MEXMEVacuna.SEXO_NotApplicable)).append(",")
			.append(DB.TO_STRING(MEXMEVacuna.SEXO_Other)).append(",")
			.append(DB.TO_STRING(MEXMEVacuna.SEXO_Unassigned)).append(",")
			.append("?) ");
		}
		
		/*if(MEXMEVacuna.SEXO_Female.equals(sexo) || MEXMEVacuna.SEXO_Male.equals(sexo)){
			sexoBln = true;
			str.append("('").append(MEXMEVacuna.SEXO_Ambiguous).append("','")
			.append(MEXMEVacuna.SEXO_NotApplicable).append("','")
			.append(MEXMEVacuna.SEXO_Other).append("','")
			.append(MEXMEVacuna.SEXO_Unassigned).append("',");
			
		}else if(MEXMEVacuna.SEXO_Ambiguous.equals(sexo)){
			
			str.append("('").append(MEXMEVacuna.SEXO_NotApplicable).append("','")
			.append(MEXMEVacuna.SEXO_Other).append("','")
			.append(MEXMEVacuna.SEXO_Unassigned).append("',");
			
		}else if(MEXMEVacuna.SEXO_NotApplicable.equals(sexo)){
			
			str.append("('").append(MEXMEVacuna.SEXO_Ambiguous).append("','")
			.append(MEXMEVacuna.SEXO_Other).append("','")
			.append(MEXMEVacuna.SEXO_Unassigned).append("',");
			
		}else if(MEXMEVacuna.SEXO_Other.equals(sexo)){
			
			str.append("('").append(MEXMEVacuna.SEXO_Ambiguous).append("','")
			.append(MEXMEVacuna.SEXO_NotApplicable).append("','")
			.append(MEXMEVacuna.SEXO_Unassigned).append("',");
			
		}else if(MEXMEVacuna.SEXO_Unassigned.equals(sexo)){
			
			str.append("('").append(MEXMEVacuna.SEXO_Ambiguous).append("','")
			.append(MEXMEVacuna.SEXO_NotApplicable).append("','")
			.append(MEXMEVacuna.SEXO_Other).append("',");
		}*/
		
		sql.append("SELECT Distinct(v.exme_vacuna_id), v.* ")
		.append("FROM exme_vacuna v ")
		.append("INNER JOIN exme_vacunadet vd on (v.exme_vacuna_id=vd.exme_vacuna_id) ")
		.append("WHERE ? between vd.edadMinima and vd.EdadMaxima ")
		.append(sexoBln ? str : "")
		.append("ORDER BY v.exme_vacuna_id ");
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 int i = 0;
			 pstmt.setDouble(++i, edad);
			 if(sexoBln){
				 pstmt.setString(++i, sexo.trim());
			 }
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacuna vac = new MEXMEVacuna(ctx,rs,null);
				 lista.add(vac);
			 }
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, "get(Properties ctx)", e);
    	} finally {
    		DB.close(rs, pstmt);
    	}
		return lista;
	}
	
	/**
	 * Obtiene una lista del catalogo de vacunas activas
	 * sin tomar en cuenta el detalle
	 * @param ctx
	 * @param trxName
	 * @return
	 * raul
	 *
	public static List<MEXMEVacuna> get(Properties ctx, String trxName) {
		List<MEXMEVacuna> lista = new ArrayList<MEXMEVacuna>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT EXME_Vacuna.EXME_Vacuna_ID ").append(
					" FROM EXME_Vacuna ").append(
					" WHERE EXME_Vacuna.isActive = 'Y' ").append(
					" ORDER BY EXME_Vacuna.Value ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MEXMEVacuna(ctx, rs.getInt(1), trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}*/

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(existeProducto(getM_Product_ID())){
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@VaccineProduct@"));
			return false;
		}		
		return true;
	}
	
	@Override
	protected boolean beforeDelete() {
		List<MEXMEVacunaDet> lista = new ArrayList<MEXMEVacunaDet>();
		lista.addAll(MEXMEVacunaDet.getDetalle(Env.getCtx(), getEXME_Vacuna_ID()));
		
		if(lista.size()>0){
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@VaccineDetailRel@"));
			return false;
		}
		
		return true;
	}
	/**
	 * Varifica si el producto seleccionado ya está asociado a una vacuna.
	 * @param M_Product_ID
	 * @return
	 */
	public boolean existeProducto(int M_Product_ID){
		boolean existe = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT EXME_Vacuna.M_Product_ID ")
		.append("FROM EXME_Vacuna WHERE EXME_Vacuna.M_Product_ID = ? ")
		.append("AND EXME_Vacuna_ID <> ? ");
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, M_Product_ID);
			 pstmt.setInt(2, getEXME_Vacuna_ID());
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()){
				 existe =  true;
			 }
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, "get(Properties ctx)", e);
    	} finally {
    		DB.close(rs,pstmt);
    			pstmt = null;
    			rs = null;
    	}
		return existe;
	}
	private List<MProduct> products;
	private List<LabelValueBean> productsLvb;
	@SuppressWarnings("unchecked")
	public List<LabelValueBean> getProductList(boolean reQuery, int labelerID){
		if(productsLvb == null || reQuery){
			productsLvb = getProducts(true, labelerID);
		}
		return productsLvb;
	}
	@SuppressWarnings("unchecked")
	public List<MProduct> getProduct(boolean reQuery, int labelerID){
		if(products == null || reQuery){
			products = getProducts(false, labelerID);
		}
		return products;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getProducts(boolean labelvaluebean, int labelerID) {
		List lst = new ArrayList();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT ").append(labelvaluebean?"prod.M_Product_ID, prod.value||' '||NVL(prod.Description,prod.Name) as Name ":"prod.* ");
		sql.append("FROM EXME_VacunaProduct ");
		sql.append("INNER JOIN M_Product prod ON (EXME_VacunaProduct.M_Product_ID=prod.M_Product_ID ");
		sql.append(labelerID > 0 ? " AND prod.EXME_labeler_id=? " : "");
		sql.append(" AND EXME_VacunaProduct.EXME_Vacuna_ID=? ) "); //AND prod.ProductClass=?) ");
		//if (warehouseID > 0) {
		//	sql.append("INNER JOIN M_Replenish ON (prod.m_product_id = M_Replenish.m_product_id  AND M_Replenish.m_warehouse_id=?) ");
		//}
		sql.append("WHERE EXME_VacunaProduct.isActive='Y' ");
		sql.append("AND prod.isActive='Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "EXME_VacunaProduct"));
		sql.append(" ORDER BY prod.Value ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			if (labelerID > 0) {
				pstmt.setInt(index++, labelerID);
			}
			pstmt.setInt(index++, getEXME_Vacuna_ID());
//			pstmt.setString(index++, MProduct.PRODUCTCLASS_Immunization);
			//if (warehouseID > 0) {
			//	pstmt.setInt(index++, warehouseID);
			//}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Object obj = null;
				if (labelvaluebean) {
					obj = new LabelValueBean(rs.getString("Name"), rs.getString("M_Product_ID"));
				} else {
					obj = new MProduct(getCtx(), rs, get_TrxName());
				}
				lst.add(obj);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get(Properties ctx)", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lst;
	}
	
	private MEXMEUOM uom = null;

	public MEXMEUOM getUom() {
		if(uom == null && getC_UOM_ID() > 0){
			setUom(new MEXMEUOM(getCtx(), getC_UOM_ID(), get_TrxName()));
		}
		return uom;
	}

	public void setUom(MEXMEUOM uom) {
		this.uom = uom;
	}

	/**
	 * 
	 * @param ctx
	 * @param labelerID
	 * @param productClass
	 * @param warehouseId
	 * @return
	 */
	public static List<MEXMEVacuna> get(Properties ctx, int labelerID, int warehouseId) {
		final List<MEXMEVacuna> list = new ArrayList<MEXMEVacuna>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		sql.append("SELECT DISTINCT EXME_vacuna.*  FROM EXME_vacuna ");//, M_Product.M_Product_ID as vaccineProd
		sql.append("INNER JOIN EXME_vacunaproduct vp ON (EXME_vacuna.EXME_vacuna_id = vp.EXME_vacuna_id AND EXME_vacuna.isActive='Y') ");
		sql.append("INNER JOIN M_Product ON (vp.m_product_id = m_product.m_product_id AND M_Product.isActive='Y' ");
		sql.append(" AND M_Product.EXME_labeler_id=? )");// AND M_Product.productclass=? ) ");// 1- 2
//		sql.append("INNER JOIN M_Replenish ON (M_Product.m_product_id = M_Replenish.m_product_id  AND M_Replenish.m_warehouse_id=?) ");
		sql.append(" ORDER BY NVL(EXME_vacuna.CodeCVX,EXME_vacuna.Value), EXME_vacuna.Name ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, labelerID);
//			pstmt.setString(2, MProduct.PRODUCTCLASS_Immunization);
//			pstmt.setInt(3, warehouseId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEVacuna vac = new MEXMEVacuna(ctx, rs, null);
				vac.getProduct(true, labelerID);
				list.add(vac);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Busca la vacuna a partir del medicamento generico
	 * @param ctx
	 * @param rxNormId
	 * @return
	 */
	public static MEXMEVacuna getFromRxNorm(Properties ctx, int rxNormId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" INNER JOIN EXME_vacunaproduct vp ON (EXME_vacuna.EXME_vacuna_id=vp.EXME_vacuna_id AND vp.isActive='Y') ");
		sql.append(" INNER JOIN M_Product p ON (vp.m_product_id=p.m_product_id ");
		sql.append("                          AND p.isActive='Y' ");
		sql.append("                          AND p.EXME_GenProduct_id=?) ");
		return new Query(ctx, Table_Name, null, null)//
				.setJoins(sql)//
				.setOrderBy("COALESCE(EXME_vacuna.CodeCVX,EXME_vacuna.Value), EXME_vacuna.Name")//
				.setParameters(rxNormId)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.first();
	}
	
	/**
	 * Busca la vacuna a partir del producto
	 * @param ctx
	 * @param rxNormId
	 * @return
	 */
	public static MEXMEVacuna getFromNDC(Properties ctx, int prodId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" INNER JOIN EXME_vacunaproduct vp ON (EXME_vacuna.EXME_vacuna_id=vp.EXME_vacuna_id ");
		sql.append(" AND vp.isActive='Y' AND  vp.M_Product_id=?) ");
		return new Query(ctx, Table_Name, null, null)//
				.setJoins(sql)//
				.setOrderBy("COALESCE(EXME_vacuna.CodeCVX,EXME_vacuna.Value), EXME_vacuna.Name")//
				.setParameters(prodId)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.first();
	}
	
	/**
	 * String Representation
	 * 
	 * @return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(getCodeCVX())){
			sb.append(getCodeCVX() + " - ");
		} else {
			sb.append(getValue() + " - ");
		}
		if(StringUtils.isNotEmpty(getDescription())){
			sb.append(getDescription());
		} else {
			sb.append(getName());
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param labelerID
	 * @param warehouseId
	 * @return
	 */
	public static List<LabelValueBean> getVB(Properties ctx) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		sql.append("SELECT DISTINCT EXME_vacuna.*  FROM EXME_vacuna ");//, M_Product.M_Product_ID as vaccineProd
		sql.append("INNER JOIN EXME_vacunaproduct vp ON (EXME_vacuna.EXME_vacuna_id = vp.EXME_vacuna_id AND EXME_vacuna.isActive='Y') ");
		sql.append("INNER JOIN M_Product ON (vp.m_product_id = m_product.m_product_id AND M_Product.isActive='Y' )");
		sql.append(" ORDER BY NVL(EXME_vacuna.CodeCVX, EXME_vacuna.Value), EXME_vacuna.Name ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				MEXMEVacuna vac = new MEXMEVacuna(ctx, rs, null);
//				vac.getProduct(true, labelerID, warehouseId);
				list.add(new LabelValueBean(vac.getCodeCVX() + " - " + vac.getValue() ,String.valueOf(vac.getEXME_Vacuna_ID())));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	public static List<LabelValueBean> getFabricantes(List<MProduct> prods, Properties ctx) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();		
		try {			
			for(MProduct prod : prods){
				list.addAll(MEXMELabeler.getManufacturer(ctx, prod.getM_Product_ID()));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return list;
	}
	

	/**
	 * Obtiene el listado de las vacunas(value name pair)
	 * @param ctx
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List<ValueNamePair> getVaccineLstVNP(Properties ctx, String where, String trxName){
		List<ValueNamePair> lista = new ArrayList<ValueNamePair>();
		
		List<MEXMEVacuna> vaccinesLst = getVaccineLst(ctx, where, trxName);
		for (int i = 0; i < vaccinesLst.size(); i++) {
			lista.add(new ValueNamePair(String.valueOf(vaccinesLst.get(i).getEXME_Vacuna_ID()), vaccinesLst.get(i).getName()));
		}
		return lista;
	}
	
	/**
	 * Obtiene el listado de las vacunas
	 * @param ctx
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEVacuna> getVaccineLst(Properties ctx, String where, String trxName){
		List<MEXMEVacuna> lista = new ArrayList<MEXMEVacuna>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder("SELECT * FROM EXME_Vacuna ");
		sql.append("where isActive = 'Y' ");
		if(StringUtils.isNotEmpty(where)){
			sql.append(where);
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Vacuna"));
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEVacuna vac = new MEXMEVacuna(ctx, rs, trxName);
				lista.add(vac);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	
	/**
	 * Detalle de la vacuna en base a producto
	 * @param ctx
	 * @param EXME_Vacuna_ID
	 * @return
	 * lhernandez.
	 */
	public static List<MEXMEVacunaDet> getByProduct(Properties ctx, int productID){
		List<MEXMEVacunaDet> lista = new ArrayList<MEXMEVacunaDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_VacunaDet "); 
		sql.append("WHERE M_product_ID = ? ");
		sql.append(" AND ISACTIVE = 'Y' ");
		sql.append("ORDER BY Secuencia ASC ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, productID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacunaDet det = new MEXMEVacunaDet(ctx, rs, null);
				 lista.add(det);
			 }
			 
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	

}
