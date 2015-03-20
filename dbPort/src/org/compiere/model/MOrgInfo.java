/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 *	Organization Info Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MOrgInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 *           Modificado por Lorena Lama (Ago 2013)
 */
public class MOrgInfo extends X_AD_OrgInfo
{	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return Org Info
	 */
	public static MOrgInfo get (Properties ctx, int AD_Org_ID)
	{
//		MOrgInfo retValue = null;
//		String sql = "SELECT * FROM AD_OrgInfo WHERE AD_Org_ID=?";
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, AD_Org_ID);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next())
//				retValue = new MOrgInfo (ctx, rs, null);
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			s_log.log(Level.SEVERE, "get", e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
		return new Query(ctx, Table_Name, " AD_Org_ID=? ", null).setParameters(AD_Org_ID).first();
	}	//	get

	/** Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (MOrgInfo.class);

	
	/**************************************************************************
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MOrgInfo (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MOrgInfo

	/**
	 * 	Organization constructor
	 *	@param org org
	 */
	public MOrgInfo (MOrg org)
	{
		super(org.getCtx(), 0, org.get_TrxName());
		setClientOrg(org);
		setDUNS ("?");
		setTaxID ("?");
	}	//	MOrgInfo
	
	public MOrgInfo (MOrg org, String trxName)
	{
		super(org.getCtx(), 0, trxName);
		setClientOrg(org);
		setDUNS ("?");
		setTaxID ("?");
	}
	
	/**
	 * 	Lizeth: Load Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *  @param trxName
	 *	@return Org Info
	 */
	public static MOrgInfo get (Properties ctx, int AD_Org_ID, String trxName)
	{
//		MOrgInfo retValue = null;
//		String sql = "SELECT * FROM AD_OrgInfo WHERE AD_Org_ID=?";
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql, trxName);
//			pstmt.setInt(1, AD_Org_ID);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next())
//				retValue = new MOrgInfo (ctx, rs, trxName);
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			s_log.log(Level.SEVERE, "get", e);
//		}
//		try
//		{
//			if (pstmt != null)
//				pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e)
//		{
//			pstmt = null;
//		}
		return new Query(ctx, Table_Name, " AD_Org_ID=? ", trxName).setParameters(AD_Org_ID).first();
	}	//	get	
	
	protected boolean beforeSave (boolean newRecord) {
		
			if (getParent_Org_ID() == 0) {    			
				if (getAD_OrgType_ID() == 0) {
					s_log.saveError(
							"", 
							Utilerias.getMessage(
									getCtx(), 
									null, 
									"error.citas.requerido", 
									Msg.translate(
											getCtx(), 
											MEXMEOrgType.COLUMNNAME_AD_OrgType_ID
									)
							)
					);
					return false;
				}
			}
			
			if (getAD_OrgType_ID() 
					== MEXMEOrgType.getIdFromName(getCtx(), "Inpatient", null)) {
				if (getNPI() == null || getNPI().trim().isEmpty()) {
					s_log.saveError(
							"", 
							Utilerias.getMessage(
									getCtx(), 
									null, 
									"error.citas.requerido", 
									COLUMNNAME_NPI
							)
					);
					return false;
				}
			}
		
		return true;
	}
	
	public static List<MOrg> getPrimary(Properties ctx, int adOrgId, String unitType, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  o.* ");
		sql.append("FROM ");
		sql.append("  ad_orginfo i ");
		sql.append("  INNER JOIN ad_org o ");
		sql.append("  ON i.ad_org_id = o.ad_org_id ");
		sql.append("WHERE ");
		sql.append("  i.ad_primaryorg_id = ? AND ");
		sql.append("  i.tipo_unidad = ? ");
		// sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "AD_OrgInfo", "i"));
		List<MOrg> orgs = new ArrayList<MOrg>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, adOrgId);
			pstmt.setString(2, unitType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orgs.add(new MOrg(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return orgs;
	}

	/*public static String getLogo2(Properties ctx) {
		MOrgInfo info = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx), null);
		StringBuilder logo = new StringBuilder(Env.getThemeUrl(ctx));//Expert: Lama
		if (info == null) {
			logo.append(Constantes.ECS_LOGO_SMALL);
		} else {
			if (MOrgInfo.TIPOCLIENTE_HOSPITALCENTER.equals(info.getTipoCliente())) {
				logo.append("/ecs_cirrus2.png");
			} else if (MOrgInfo.TIPOCLIENTE_PHYSICIANOFFICE.equals(info.getTipoCliente())) {
				logo.append("/ecs_nimbo2.png");
			} else {
				logo.append(Constantes.ECS_LOGO_SMALL);
			}
		}
		return logo.toString();
	}

	public static String getLogo(Properties ctx) {
		MOrgInfo info = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx), null);
		String logo = null;
		if (info == null) {
			logo = Constantes.ECS_LOGO;
		} else {
			if (MOrgInfo.TIPOCLIENTE_HOSPITALCENTER.equals(info.getTipoCliente())) {
				logo = Constantes.ECS_LOGO_CIRRUS;
			} else if (MOrgInfo.TIPOCLIENTE_PHYSICIANOFFICE.equals(info.getTipoCliente())) {
				logo = Constantes.ECS_LOGO_NIMBUS;
			} else {
				logo = Constantes.ECS_LOGO;
			}
		}
		return logo;
	}*/

	
	public static String getLogo2(Properties ctx){
		return MOrgInfo.getLogo(ctx, Env.getAD_Org_ID(ctx), "32");
	}
	
	public static String getLogo2(Properties ctx, int adOrgId){
		return MOrgInfo.getLogo(ctx, adOrgId, "32");
	}
	
	public static String getLogo(Properties ctx, String suffix) {
		return MOrgInfo.getLogo(ctx, Env.getAD_Org_ID(ctx), suffix);
	}
		
	public static String getLogo(Properties ctx, int adOrgId, String suffix) {
		StringBuilder logoName = new StringBuilder();
		if (adOrgId >= 0) {
			MOrgInfo info = MOrgInfo.get(ctx, adOrgId, null);
			if (info != null && StringUtils.isNotBlank(info.getTipoCliente())) {
				final String logo;
				if (MOrgInfo.TIPOCLIENTE_HOSPITALCENTER.equals(info.getTipoCliente())) {
					logo = "cirrus";
				} else if (MOrgInfo.TIPOCLIENTE_PHYSICIANOFFICE.equals(info.getTipoCliente())) {
					logo = "nimbo";
				} else if (MOrgInfo.TIPOCLIENTE_AMBULATORYSURGERYCENTER.equals(info.getTipoCliente())) {
					logo = "stratus";
				} else {
					logo = "";
				}
				if (StringUtils.isNotBlank(logo)) {
					logoName.append("ecs_").append(logo);
					if (StringUtils.isNotBlank(suffix)) {
						logoName.append("_").append(suffix);
					} else {
						logoName.append("_").append(Env.getLanguage(Env.getCtx()).getLocale().getCountry());
					}
					logoName.append(".png");
				}
			}
		}
		if (logoName.length() == 0) {
			logoName.append("Logo_eCareSoft");
			if (StringUtils.isNotBlank(suffix)) {
				logoName.append("_").append(suffix);
			}
			logoName.append(".png");
		}
		return logoName.toString();
	}
	
	/**
	 * Si el tipo de cliente es physician office, tambien llamado Nimbo
	 * @param ctx
	 * @param AD_Org_ID No se requiere
	 * @return 
	 */
	public static boolean isNimbo(Properties ctx, int AD_Org_ID){
//		MOrgInfo oi = MOrgInfo.get(ctx,AD_Org_ID);
//		return oi.getTipoCliente().equalsIgnoreCase(MOrgInfo.TIPOCLIENTE_PHYSICIANOFFICE);
		return MOrgInfo.TIPOCLIENTE_PHYSICIANOFFICE.equals(getTipoCliente(ctx));
	}
	/**
	 * Si el tipo de cliente es outpatient, tambien llamado Cirrus
	 * @param ctx
	 * @param AD_Org_ID
	 * @return 
	 */
	public static boolean isCirrus(Properties ctx){
		return MOrgInfo.TIPOCLIENTE_HOSPITALCENTER.equals(getTipoCliente(ctx));
	}
	/**
	 * Si el tipo de cliente pertenece a surgery, tambien llamado Stratus
	 * @param ctx
	 * @return 
	 */
	public static boolean isStratus(Properties ctx){
		return MOrgInfo.TIPOCLIENTE_AMBULATORYSURGERYCENTER.equals(getTipoCliente(ctx));//oi.getTipoCliente());
	}
	/**
	 * Regresa el tipo de cliente de una organización
	 * @param ctx
	 * @return
	 */
	public static String getTipoCliente(Properties ctx){
		return  DB.getSQLValueString(null, "SELECT TipoCliente FROM AD_OrgInfo WHERE AD_Org_ID=?", Env.getAD_Org_ID(ctx));
	}
}	//	MOrgInfo
