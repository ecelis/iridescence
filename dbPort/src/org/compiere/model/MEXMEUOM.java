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
import org.compiere.util.Env;
import org.compiere.util.Language;
/** @deprecated use MUOM instead */
public class MEXMEUOM extends MUOM {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MUOM.class);
	
	/**************************************************************************
	 *	Constructor.
	 *	@param ctx context
	 *  @param C_UOM_ID UOM ID
	 *  @param trxName transaction
	 */
	public MEXMEUOM (Properties ctx, int C_UOM_ID, String trxName)
	{
		super (ctx, C_UOM_ID, trxName);
	}	//	UOM

	/**
	 *	Load Constructor.
	 *	@param ctx context
	 *  @param rs result set
	 *  @param trxName transaction
	 */
	public MEXMEUOM (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	UOM
	
	/**
	 * Lista con todas las unidades de medida
	 * filtradas por el nivel de acceso de la tabla
	 * y por el lenguaje de contexto
	 * @param ctx
	 * @param trxName
	 * @return
	 * @deprecated
	 */
	public static List<LabelValueBean> getLstUomTrl(Properties ctx, String trxName) {
		// Lama: usar metodo tmb para idioma base
		boolean base = Language.isBaseLanguage(Env.getAD_Language(ctx));
		
		StringBuilder st = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		st.append("SELECT c.C_UOM_ID, ").append(base ? "c.Name" : "ct.Name").append(" as nameUOM ");
		st.append("FROM C_UOM c ");
		if(!base){
			st.append("INNER JOIN C_UOM_Trl ct ON (c.C_UOM_ID = ct.C_UOM_ID AND ct.AD_Language = ? ) ");
		}
		st.append(" WHERE c.IsActive = 'Y' ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "c"));
		st.append(" ORDER BY 2 ");

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			if(!base){
				pstmt.setString(1, Env.getAD_Language(ctx));
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(2), String.valueOf(rs.getInt(1))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 *  Devuelve el nombre de la unidad de medida.
	 *
	 *@param  udm         Description of the Parameter
	 *@return             Un valor String con el nombre de la unidad de medida.
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      la unidad de medida.
	 */
	public static String nombreUDM(Properties ctx, long udm) throws Exception {
        String name = null;
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

        sql.append(" SELECT C_UOM.Name, uom.Name as Trad FROM C_UOM ")
        .append(" LEFT OUTER JOIN C_UOM_Trl uom ON (C_UOM.C_UOM_ID = uom.C_UOM_ID AND uom.AD_Language='")
        .append(Env.getAD_Language(Env.getCtx())).append("') ")
        .append(" WHERE C_UOM.C_UOM_ID = ? ")
		.append(" AND C_UOM.IsActive = 'Y' ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_UOM"));
        
        pstmt = DB.prepareStatement(sql.toString(), null);
        pstmt.setLong(1, udm);
		try {
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("Trad");
				if(name == null){
					name = rs.getString("Name");
				}
			} else {
				throw new Exception("No existe la unidad de medida o esta inactiva.");
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
       
        return name;
	}

	/**
	 *  Devuelve las unidades de medida asociadas al producto (C_UOM_Convertion)
	 *  seleccionado que este asociado al almaceen dado (M_Repenish).
	 *  (EXME_Product_UOM) <i>Nota: La udm del producto no sera agregada a la lista
	 *  si no esta definida en la tabla EXME_Product_UOM</i> .
	 *
	 *@param  producto
	 *@param  ctx
	 *@return
	 *@throws  Exception
	 *@deprecated
	 */
	public static List<LabelValueBean> getProdUOM(long producto, Properties ctx) throws Exception {
		s_log.log(Level.INFO,"-------- uom ------ ");
		s_log.log(Level.INFO,"producto " + producto);
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<LabelValueBean> lstUdm = new ArrayList<LabelValueBean>();
		String resultado = "I";

		//verificar que tipo de prod es
		sql = "SELECT ProductType FROM M_Product WHERE M_Product_ID = ? AND isActive = 'Y' ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_Product");

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, producto);
			s_log.log(Level.SEVERE,"SQL : " + sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = rs.getString("ProductType");
			}

			//si es servicio la udm de m_product
			s_log.log(Level.INFO,"resultado : " + resultado);
			if (resultado.equalsIgnoreCase("S")) {
				sql = "SELECT M_Product.C_UOM_ID, uom.Name " +
						" FROM M_Product LEFT JOIN C_UOM uom ON (M_Product.C_UOM_ID = uom.C_UOM_ID) " +
						" WHERE  M_Product.M_Product_ID = ? ";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "M_Product");
				
			} else {
				sql = "SELECT DISTINCT (EXME_Product_UOM.C_UOM_ID), uom.Name " +
						" FROM EXME_Product_UOM LEFT JOIN C_UOM uom ON (EXME_Product_UOM.C_UOM_ID = uom.C_UOM_ID) " +
						" WHERE EXME_Product_UOM.M_Product_ID = ? ";
				
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Product_UOM");
			}

			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, producto);
			s_log.log(Level.SEVERE,"SQL2 : " + sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean elemento = new LabelValueBean(rs.getString("Name"), rs.getString("C_UOM_ID"));
				lstUdm.add(elemento);
			}

		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return lstUdm;
	}

}
