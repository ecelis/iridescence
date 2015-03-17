/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 *	Modelo de Familia de Productos
 *	Creado: 01-Abril-05
 *  @author Gisela Lee
 *  @version $Id: MProductFam.java,v 1.2 2005/11/16 21:28:22 hassan Exp $
 */

public class MEXMEProductFam extends X_EXME_ProductFam

{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/**	Static Logger	*/
	@SuppressWarnings("unused")
	private static CLogger	s_log	= CLogger.getCLogger (MEXMEProductFam.class);

	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param EXME_ProductFam_ID id
	 */

	public MEXMEProductFam (Properties ctx, int EXME_ProductFam_ID, String trxName)
	{

		super(ctx, EXME_ProductFam_ID, trxName);
		if (EXME_ProductFam_ID == 0)
		{
			setPlannedMargin (Env.ZERO);
			setIsDefault (false);
			setIsSelfService (true);	// Y
		}

	}	//	MProductFam



	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */

	public MEXMEProductFam(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductFam



	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */

	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success){
			return success;
		}
		if (newRecord)
			insert_Accounting("EXME_ProductFam_Acct", "C_AcctSchema_Default", null, "PayDiscount_Exp_Acct");
		return success;
	}	//	afterSave



	/**
	 * 	Before Delete
	 *	@return true
	 */

	protected boolean beforeDelete ()
	{
		return delete_Accounting("EXME_ProductFam_Acct"); 
	}	//	beforeDelete

	

	/**
	 * 	Import Constructor
	 *	@param impP import
	 */

	public MEXMEProductFam (X_I_EXME_ProductFam impP)
	{
		this (impP.getCtx(), 0, impP.get_TrxName());
		setClientOrg(impP);
		setUpdatedBy(impP.getUpdatedBy());
		//
		setValue(impP.getValue());
		setName(impP.getName());
	}	//	MProductFam



	/**
	 * 	Insert Accounting Records
	 *	@param acctTable accounting sub table
	 *	@param acctBaseTable acct table to get data from
	 *	@param whereClause optional where clause with alisa "p" for acctBaseTable
	 *  @param columnName nombre de la columna de la cuenta contable 
	 *  				  (en este caso, PayDiscount_Exp_Acct) 
	 *	@return true if records inserted
	 */

	protected boolean insert_Accounting (String acctTable, 
		String acctBaseTable, String whereClause, String columnName)
	{
	    ArrayList<String> s_acctColumns = null;
		if (s_acctColumns == null	//	cannot cache C_BP_*_Acct as there are 3
			|| acctTable.startsWith("C_BP_"))
		{

			s_acctColumns = new ArrayList<String>();

			//agregamos a la lista el nombre de la columna de contabilidad (tabla default)
			s_acctColumns.add (columnName);

			if (s_acctColumns.size() == 0)
			{
				log.severe ("No Columns for " + acctTable);
				return false;
			}

		}
		

		//	Create SQL Statement - INSERT
		StringBuffer sb = new StringBuffer("INSERT INTO ")
			.append(acctTable)
			.append(" (").append(get_TableName())
			.append("_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy,P_DesctoFam_Acct ");
		sb.append(") SELECT ").append(get_ID())
			.append(", p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(",")
			.append(getUpdatedBy()).append(",").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(",").append(getUpdatedBy());

		for (int i = 0; i < s_acctColumns.size(); i++)
			sb.append(",p.").append(s_acctColumns.get(i));
		//	.. 	FROM
		sb.append(" FROM ").append(acctBaseTable)
			.append(" p WHERE p.AD_Client_ID=").append(getAD_Client_ID());

		if (whereClause != null && whereClause.length() > 0)
			sb.append (" AND ").append(whereClause);

		sb.append(" AND NOT EXISTS (SELECT * FROM ").append(acctTable)
			.append(" e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.")
			.append(get_TableName()).append("_ID=").append(get_ID()).append(")");

		//

		int no = DB.executeUpdate(sb.toString(), get_TrxName());

		if (no > 0)
			log.fine("#" + no);
		else
			log.warning("#" + no 
				+ " - Table=" + acctTable + " from " + acctBaseTable);

		return no > 0;

	}	//	insert_Accounting

	/**
	 * Retorna las Familias de productos correspondientes a los cargos de la extension junto con su total de lo cargado por familia
	 * para la tabla base EXME_TTCtaPacDet
	 * @param ctx: Contexto
	 * @param EXME_CtaPacExt_ID: Extension de la cual se obtendran las familias de producto
	 * @param configPre: Configuracion de Precios
	 * @param trxName: Transaccion
	 * @return MCtaPacFam[]
	 *
	public static MCtaPacFam[] getCtaPacFam(Properties ctx, int EXME_CtaPacExt_ID, MConfigPre configPre, String trxName) {
		return getCtaPacFam(ctx, EXME_CtaPacExt_ID, configPre, "EXME_TTCtaPacDet", trxName);
	}*/
	
	/**
	 * Retorna las Familias de productos correspondientes a los cargos de la extension junto con su total de lo cargado por familia
	 * @param ctx: Contexto
	 * @param EXME_CtaPacExt_ID: Extension de la cual se obtendran las familias de producto
	 * @param configPre: Configuracion de Precios
	 * @param tablaBase: Tabla Base sobre la cual se obtendra las familias de productos
	 * @param trxName: Transaccion
	 * @return MCtaPacFam[]
	 *
	public static MCtaPacFam[] getCtaPacFam(Properties ctx, int EXME_CtaPacExt_ID, 
			MConfigPre configPre, String tablaBase, String trxName) {

		ArrayList<MCtaPacFam> list = new ArrayList<MCtaPacFam>();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT EXME_ProductFam.EXME_ProductFam_ID, SUM(cd.Linenetamt), NVL(cpf.DiscountPorcent,0),  NVL(cpf.EXME_CtaPacFam_ID,0) ")
			.append(" FROM EXME_ProductFam  ")
			.append(" INNER JOIN M_Product p ON (p.exme_productfam_id = EXME_ProductFam.exme_productfam_id) ")
			.append(" INNER JOIN ").append(tablaBase).append(" cd ON (cd.m_product_id = p.m_product_id  AND  cd.EXME_CtaPacExt_id = ?")
			.append(" 								 AND cd.isActive = 'Y'  AND cd.QtyInvoiced > 0 AND cd.").append(tablaBase).append("_ID NOT IN (") 
			.append(" 														SELECT t.").append(tablaBase).append("_ID FROM ").append(tablaBase).append(" t WHERE ")
			.append(" 														t.EXME_PaqBase_Version_ID > 0 AND t.Visible = 'Y' AND t.TipoLinea = 'PB' ")
			.append(" 														AND t.IsActive = 'Y' AND t.EXME_CtaPacExt_ID = ? ) ")
			.append(" 								 ) ")
			.append(" LEFT JOIN EXME_CtaPacFam cpf ON (cpf.EXME_ProductFam_ID = EXME_ProductFam.EXME_ProductFam_ID AND cpf.EXME_CtaPacExt_id = ?)")
			.append(" WHERE p.IsActive = 'Y' AND p.M_Product_ID NOT IN (?,?) ");
		
		 sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_ProductFam"));

		 sql.append(" GROUP BY EXME_ProductFam.EXME_ProductFam_ID, cpf.DiscountPorcent, cpf.EXME_CtaPacFam_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			pstmt.setInt(2, EXME_CtaPacExt_ID);
			pstmt.setInt(3, EXME_CtaPacExt_ID);
			pstmt.setInt(4, configPre.getCoaseguro_ID());
			pstmt.setInt(5, configPre.getDeducible_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacFam descFam = new MCtaPacFam(ctx, rs.getInt(4), trxName);
				descFam.setBaseAmt(rs.getBigDecimal(2));
				descFam.setEXME_ProductFam_ID(rs.getInt(1));
				descFam.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
				descFam.setDiscountPorcent(rs.getBigDecimal(3));
				list.add(descFam);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCtaPacFam", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		MCtaPacFam[] ctasPacFam = new MCtaPacFam[list.size()];
		list.toArray(ctasPacFam);

		return ctasPacFam;

	}*/
	
	
	protected boolean beforeSave(boolean newRecord) {
		// El cargo permite el descuento por familia al momento de facturar
		//Deber�a ser el cargo configurado en configuraci�n de precios
		if(newRecord) {
			if(getC_Charge_ID()==0){
				String sql = " SELECT C_Charge_ID FROM EXME_ConfigPre WHERE AD_Client_ID = ? AND AD_Org_ID IN (0,?) ";
				int ii = DB.getSQLValue (get_TrxName(), sql, getAD_Client_ID(), getAD_Org_ID());
				
				//EXPERT: Lama
				if(ii>0){
					setC_Charge_ID(ii);
				} else {
					//En caso de que el cargo de configuraci�n de precios no exista tomar�
					//el primer cargo que exista con tasa de impuestos por defecto
					sql = " SELECT c.C_Charge_ID FROM C_Charge c "
						+ " INNER JOIN C_TaxCategory t ON t.C_TaxCategory_ID = c.C_TaxCategory_ID"
						+ " WHERE t.isDefault = 'Y' AND c.AD_Client_ID = ? ";
					ii = DB.getSQLValue (get_TrxName(), sql, getAD_Client_ID());
					setC_Charge_ID(ii);
				}
				// FIN Lama
			}
		}
		return true;
	} // beforeSave
	
	/**
	 * Obtiene la Lista de Familias de Productos
	 * @param active Estatus de los registros a consultar (Y/N), en caso de que se deseen todos los registros mandar null
	 * @return List<LabelValueBean>
	 * @author mvrodriguez
	 *
	public static List<LabelValueBean> getFamilyListCbo(Properties ctx, String active) {
		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT EXME_PRODUCTFAM.EXME_PRODUCTFAM_ID, EXME_PRODUCTFAM.NAME ");
		sql.append("  FROM EXME_PRODUCTFAM ");
		
		if(active != null) {
			sql.append(" WHERE EXME_PRODUCTFAM.ISACTIVE = ? ");
		} 
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_ProductFam"));
	
		sql.append(" ORDER BY EXME_PRODUCTFAM.NAME ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(active != null) {
				pstmt.setString(1, active);
			} 
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				LabelValueBean partner = new LabelValueBean(rs.getString(2), String.valueOf(rs.getString(1)));
				lst.add(partner);

			}
               
		} catch (Exception e) {
			e.printStackTrace();//FIXME
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	
		return lst;
	}*/
	
	/**
	 * Familia del producto por value
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MEXMEProductFam get(Properties ctx, String value, String trxName) {
		return new Query(ctx, Table_Name, " value = ? ", trxName).setParameters(value).first();
	}
	
	public static boolean isInsulin(Properties ctx, int genProdId) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT pf.EXME_ProductFam_ID ");
		sql.append(" FROM EXME_ProductFam pf ");
		sql.append(" INNER JOIN EXME_ConfigEnf ce ON (pf.EXME_ProductFam_ID=ce.Fam_Insulinas_ID ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEConfigEnf.Table_Name, "ce"));
		sql.append(" AND ce.isActive='Y' ) ");
		sql.append(" INNER JOIN M_Product p ON (pf.EXME_ProductFam_ID=p.EXME_ProductFam_ID  ");
		sql.append(" AND p.EXME_GenProduct_ID=?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pf"));
		sql.append(" ORDER BY ce.AD_Org_ID DESC ");
		final int retVal = DB.getSQLValue(null, sql.toString(), genProdId);
		return  retVal > 0;
	}
	
	/**
	 * Obtiene la familia de productos por value y cliente.
	 * 
	 * @param ctx el contexto actual de la aplicacion
	 * @param name el name a buscar regionalizado segun lenguaje
	 * @param iaAD_Client_ID el id del cliente.
	 * @param saTrxName el nombre de la transacción actual	
	 * @return el objeto MEXMEProductFam con la informacion
	 */
		public static MEXMEProductFam get(Properties ctx, String name, 
				int iaAD_Client_ID, String saTrxName) {
			return new Query(ctx, Table_Name, " AD_Client_ID = ? AND name = ? ", saTrxName).setParameters(iaAD_Client_ID, name).first();
		}
		
		public static List<KeyNamePair> getAll(Properties ctx) {
			StringBuilder sb = new StringBuilder();
			sb.append(" select exme_productfam_id,  name")
			.append("  from exme_productfam ")
			.append(" where IsActive = 'Y' ");
//			MEXMELookupInfo.addAccessLevelSQL(ctx, sb.toString(), Table_ID);
			StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), sb.toString(), Table_ID));
			sql.append(" order by name  ");
			return Query.getListKN(sql.toString(), null);
			
		}
}	//	MProductFam

