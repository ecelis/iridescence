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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import com.ecaresoft.api.Generic;

/**
 *	Organization Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MOrg.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MOrg extends X_AD_Org
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;


	/**
	 * 	Get Organizations Of Client
	 *	@param po persistent object
	 *	@return array of orgs
	 */
	public static MOrg[] getOfClient (PO po)
	{
		final List<MOrg> list = new Query(po.getCtx(), Table_Name, "AD_Client_ID=?", null)//
			.setParameters(po.getAD_Client_ID())//
			.setOrderBy("Value")//
			.list();
//		String sql = "SELECT * FROM AD_Org WHERE AD_Client_ID=? ORDER BY Value";
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, po.getAD_Client_ID());
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next())
//				list.add(new MOrg(po.getCtx(), rs, null));
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e) {
//			s_log.log(Level.SEVERE, sql, e);
//		}
//		try {
//			if (pstmt != null)
//				pstmt.close();
//			pstmt = null;
//		}
//		catch (Exception e) {
//			pstmt = null;
//		}

		MOrg[] retValue = new MOrg[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfClient
	
	/**
	 * Organizaciones Padre de un Cliente
	 * @param ctx Contexto
	 * @param trxName Nombre trx
	 * @return
	 */
	public static List<KeyNamePair> getParentOrg(Properties ctx, String trxName) {
		return getParentOrg(ctx, trxName, true, false, 0);
	}
		
	/**
	 * Obtiene las organizaciones Padre
	 * @param ctx Contexto
	 * @param trxName Nombre de Transaccion
	 * @param whithClient True al del cliente actual o false todas las organizaciones
	 * @param active Nos indica si nos regresa las organizaciones activas o todas
	 * @return List<LabelValueBean> de las organizaciones padres
	 * @author mvrodriguez
	 */
	public static List<KeyNamePair> getParentOrg(Properties ctx, String trxName, boolean whithClient
			, boolean active, int AD_OrgType_ID) {
		
		int i = 1;
		StringBuilder st = new StringBuilder();
		st.append("SELECT AD_ORG.AD_ORG_ID, AD_ORG.NAME ");
		st.append("  FROM AD_ORG");
		st.append(" INNER JOIN AD_ORGINFO ON (AD_ORG.AD_ORG_ID = AD_ORGINFO.AD_ORG_ID)");
		st.append(" WHERE AD_ORGINFO.PARENT_ORG_ID IS NULL ");
		
		if(active) {
			st.append("   AND AD_ORG.ISACTIVE = 'Y' ");	
		}

		if(whithClient) {
			st.append("   AND AD_ORG.AD_CLIENT_ID = ?");	
		}
		
		if(AD_OrgType_ID > 0) {
			st.append("   AND AD_ORGINFO.AD_ORGTYPE_ID = ?");
		}
		
		st.append(" order by ad_org.name ");
		
		List<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			
			if(whithClient) {
				pstmt.setInt(i++, Env.getAD_Client_ID(ctx));	
			}
			
			if(AD_OrgType_ID > 0) {
				pstmt.setInt(i, AD_OrgType_ID);		
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lst;
	}
	
	/**
	 * Obtiene la zona horaria de las organizaciones padres
	 * 
	 * @return Listado de Generics con la información de la zona horaria id =
	 *         orgId, value = timeZoneId, name = name
	 */
	public static List<Generic> getParentOrgTimeZones() {
		List<Generic> ids = new ArrayList<Generic>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  org.ad_org_id, ");
		sql.append("  info.timezone, ");
		sql.append("  org.name ");
		sql.append("FROM ");
		sql.append("  ad_org org ");
		sql.append("  INNER JOIN ad_orginfo info ");
		sql.append("  ON org.ad_org_id = info.ad_org_id ");
		sql.append("WHERE ");
		sql.append("  info.parent_org_id IS NULL AND ");
		sql.append("  org.ad_org_id > 0 AND ");
		sql.append("  org.ad_client_id > 0 AND ");
		sql.append("  org.isactive = ");
		sql.append("'Y' ORDER BY ");
		sql.append("  org.ad_client_id ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Generic generic = new Generic();
				generic.setId(rs.getInt(1));
				generic.setValue(rs.getString(2));
				generic.setName(rs.getString(3));
				ids.add(generic);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(null, pstmt);
		}

		return ids;
	}
	
	/**
	 * 	Get Org from Cache
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return MOrg
	 */
	public static MOrg get (Properties ctx, int AD_Org_ID)
	{
		Integer key = new Integer (AD_Org_ID);
		MOrg retValue = (MOrg) s_cache.get (key);
		if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MOrg (ctx, AD_Org_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Logger	*/
	private static CLogger 			s_log = CLogger.getCLogger (MOrg.class);
	/**	Cache						*/
	private static CCache<Integer,MOrg>	s_cache	= new CCache<Integer,MOrg>("AD_Org", 20);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@param trxName transaction
	 */
	public MOrg (Properties ctx, int AD_Org_ID, String trxName)
	{
		super(ctx, AD_Org_ID, trxName);
		if (AD_Org_ID == 0)
		{
		//	setValue (null);
		//	setName (null);
			setIsSummary (false);
		}
	}	//	MOrg

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MOrg (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MOrg

	/**
	 * 	Parent Constructor
	 *	@param client client
	 *	@param name name
	 */
	public MOrg (MClient client, String name)
	{
		this (client.getCtx(), 0, client.get_TrxName());
		setAD_Client_ID (client.getAD_Client_ID());
		setValue (name);
		setName (name);
	}	//	MOrg

	/**	Org Info						*/
	private MOrgInfo	m_info = null;
	/**	Linked Business Partner			*/
	private Integer 	m_linkedBPartner = null;

	/**
	 *	Get Org Info
	 *	@return Org Info
	 */
	public MOrgInfo getInfo()
	{
		if (m_info == null)
			m_info = MOrgInfo.get (getCtx(), getAD_Org_ID());
		return m_info;
	}	//	getMOrgInfo


	
	/**
	 * 	After Save
	 *	@param newRecord new Record
	 *	@param success save success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord) {
			// Info
			m_info = new MOrgInfo(this);
			m_info.setNPI(getValue());
			MClient client = new MClient(getCtx(), getAD_Client_ID(), get_TrxName());
			if (MClient.TIPOCLIENTE_HOSPITALCENTER.equals(client.getTipoCliente())) {
				int type = MEXMEOrgType.getIdFromName(getCtx(), "Inpatient", null);
				if (type > 0) {
					m_info.setAD_OrgType_ID(type);
				}
			} if (MClient.TIPOCLIENTE_AMBULATORYSURGERYCENTER.equals(client.getTipoCliente())) {
				int type = MEXMEOrgType.getIdFromName(getCtx(), "Outpatient", null);
				if (type > 0) {
					m_info.setAD_OrgType_ID(type);
				}
			} else {
				int type = MEXMEOrgType.getIdFromName(getCtx(), "Clinical", null);
				if (type > 0) {
					m_info.setAD_OrgType_ID(type);
				}
			}
			if (!m_info.save()) {
				s_log.log(Level.WARNING," MOrg.afterSave. While saving MOrgInfo");
			}
			// Access
			MRoleOrgAccess.createForOrg(this);
			MRole.getDefault(getCtx(), true); // reload
			// TreeNode
			insert_Tree(MTree_Base.TREETYPE_Organization);
		}
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
		{
			MAccount.updateValueDescription(getCtx(), "AD_Org_ID=" + getAD_Org_ID(), get_TrxName());
			if ("Y".equals(Env.getContext(getCtx(), "$Element_OT"))) 
				MAccount.updateValueDescription(getCtx(), "AD_OrgTrx_ID=" + getAD_Org_ID(), get_TrxName());
		}
		
		// Copy the place of service to Patient Types in case they don't have it
		// Se pone solo para USA, ya que esta informacion no se relaciona con mexico,
		// adicionalmente, se ejecuta para cada Organizacion Transaccional, y no solo para
		// la organizacion Padre. Debido a que los metodos en cascada toman la organizacion
		// de login, se valida que la organizacion EDITADA sea la misma del contexto
		// así se evita ejecutar el update en cascada para una Org transaccional (centro de costos).- Lama
		if (MEXMEPOS.isUpdateAfterSavePOS(success, this)) {
			final List<MEXMETipoPaciente> lista = MEXMETipoPaciente.getTiposPacienteNotPOS(getCtx(), null);
			for (MEXMETipoPaciente tipo : lista) {
				tipo.setEXME_POS_ID(getEXME_Pos_ID());
				if (!tipo.save(get_TrxName())) {
					log.severe(tipo.getName()+" "+tipo.toString());
				}
			}
		}

		return true;
	}	//	afterSave
			
//	//TODO Documentar Metodo
//	public List<OptionItem> getADOrgTrx(){
//		List<OptionItem> orgs = null;
//		StringBuilder sb = new StringBuilder("SELECT AD_ORG_ID,NAME FROM AD_ORG ")
//			.append("WHERE IsActive='Y'");
//		StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(), this.get_TableName()));
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try{
//			pstmt = DB.prepareStatement(sql.toString(),null);
//			rs = pstmt.executeQuery();
//			orgs = new ArrayList<OptionItem>();
//			while (rs.next())
//				orgs.add(new OptionItem(rs.getString(1),rs.getString(2)));
//			
//		}catch(SQLException e){
//			log.log(Level.SEVERE, "getTrxCampaigns", e);
//		}finally{
//			DB.close(rs,pstmt);	
//		}
//		return orgs;
//	}
	
	
	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			delete_Tree(MTree_Base.TREETYPE_Organization);
		return success;
	}	//	afterDelete


	/**
	 * 	Get Linked BPartner
	 *	@return C_BPartner_ID
	 */
	public int getLinkedC_BPartner_ID()
	{
		if (m_linkedBPartner == null)
		{
			int C_BPartner_ID = DB.getSQLValue(null,
				"SELECT C_BPartner_ID FROM C_BPartner WHERE AD_OrgBP_ID=?",
				getAD_Org_ID());
			if (C_BPartner_ID < 0)	//	not found = -1
				C_BPartner_ID = 0;
			m_linkedBPartner = new Integer (C_BPartner_ID);
		}
		return m_linkedBPartner.intValue();
	}	//	getLinkedC_BPartner_ID
	
	
	//TODO Documentar Metodo
		
	public List<MOrg> getTrxOrgs(Integer adOrgID){
		List<MOrg> list = null;
		
		StringBuilder sb = new StringBuilder(" SELECT * FROM AD_ORG  ORG ")
								.append(" WHERE ORG.ISACTIVE = 'Y' AND ORG.AD_ORG_ID = ? ")
								.append(" UNION ALL SELECT O.* FROM AD_ORG O, AD_ORGINFO I ")
								.append(" WHERE O.ISACTIVE = 'Y' AND O.AD_ORG_ID = I.AD_ORG_ID ")
								.append(" AND I.PARENT_ORG_ID = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			
			pstmt.setInt(1, adOrgID);
			pstmt.setInt(2, adOrgID);
			rs = pstmt.executeQuery();
			list = new ArrayList<MOrg>();
			
			while (rs.next ())
				list.add (new MOrg (getCtx(), rs, null));	
			
			
		}catch(Exception e){
			s_log.log (Level.SEVERE, sb.toString(), e);
		}finally{
			DB.close(rs,pstmt);	
		}		
		return list;
	}
	
	/**
	 * Metodo que obtiene un arreglo de lista con el identificador
	 * y el nombre de la organización.
	 *
	 * @param ctx Contexto del sistema
	 *
	 * @return Vector de tipo LabelValueBean que contiene los
	 *  identificadores y el codigo de C_Currency.
	 */
	public static List<LabelValueBean> getOrgList(Properties ctx){
		List<LabelValueBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AD_Org_ID, Name FROM AD_Org ");
		sql.append(" WHERE IsActive = 'Y' AND IsSummary = 'N' AND AD_Org_ID <> 0 ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","AD_Org"));
		sql.append(" ORDER BY Name ");
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			rs = pstmt.executeQuery();
			list = new ArrayList<LabelValueBean>();
			//list.add(new OptionItem("0"," "));
			while (rs.next())
				list.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getCurrencyList", e);
		}finally{
			DB.close(rs,pstmt);	
		}
		return list;
	}
	
	/**
	 * Organizacion por nombre/valor y cliente
	 * @param ctx
	 * @param orgName
	 * @return
	 */
	public static MOrg getFromValueName(Properties ctx, String orgName) {
		return new Query(ctx, Table_Name, "(upper(name)=? OR upper(value)=?) and AD_Client_ID=?", null)//
			.setParameters(orgName.toUpperCase(), orgName.toUpperCase(), Env.getAD_Client_ID(ctx))//
			.first();
//		StringBuilder st = new StringBuilder("select * from AD_Org ");
//		st.append("where (upper(name) = ? OR upper(value) = ?)  and AD_Client_ID = ?");
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MOrg client = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setString(1, orgName.toUpperCase());
//			pstmt.setString(2, orgName.toUpperCase());
//			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				client = new MOrg(ctx, rs, null);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs,pstmt);	
//		}
//		return client;
	}
	
	/**
	 * Copiar documentos de la primer Organizacion Padre
	 */
	public void copyDocTypeFromParent() {
		copyDocType(getCtx(), getFirstOrg(getCtx(), getAD_Client_ID(), get_TrxName()), getAD_Org_ID(), get_TrxName());
	}

	/**
	 * Obtener primer organizacion del cliente
	 * @param ctx
	 * @param AD_Client_ID
	 * @param trxName
	 * @return
	 */
	public static int getFirstOrg(Properties ctx, int AD_Client_ID, String trxName) {
		int id = DB.getSQLValue(trxName, "select AD_Org_ID from AD_Org where AD_Client_ID = ? order by created asc", AD_Client_ID);
		return id;
	}

	/**
	 * Copiar documentos de una organizacion a otra
	 * @param ctx
	 * @param from
	 * @param to
	 * @param trxName
	 */
	public static void copyDocType(Properties ctx, int from, int to, String trxName) {
		MOrg orgPadre = new MOrg(ctx, from, trxName);

		Env.getCtx().setProperty("#AD_Org_ID", String.valueOf(to));
		String NEXO1 = "@ORG->";
		String NEXO2 = "@->";

		List<MDocType> mdt = new ArrayList<MDocType>();
		HashMap<String, String> mapExist = new HashMap<String, String>();

		StringBuilder patternName = new StringBuilder();
		patternName.append(NEXO1 + from + NEXO2 + to);

		try {
			mdt = MRecreateDocType.getAllDocType(ctx, from, null);
			mapExist = MRecreateDocType.getExistentDocument(ctx, from, to, NEXO1, NEXO2);
			if (mdt != null) {
				for (int i = 0; i < mdt.size(); i++) {
					MDocType dadMDoc = mdt.get(i);

					if (mapExist.get(dadMDoc.getName() + patternName.toString()) == null) {
						MDocType clonMDoc = new MDocType(ctx, 0, trxName);
						if (MRecreateDocType.isSon(from, NEXO1)) {
							return;
						}
						if (MRecreateDocType.isGenerated(to, orgPadre.getValue(), NEXO1)) {
							return;
						}

						PO.copyValues(dadMDoc, clonMDoc);
						if (dadMDoc.getDocNoSequence_ID() > 0) {
							MSequence seqP = new MSequence(ctx, dadMDoc.getDocNoSequence_ID(), null);
							MSequence seqC = null;

							MSequence oldSeqNo = MSequence.getADSequenceIDFromSequenceName(ctx, (seqP.getName() + patternName.toString()).toUpperCase());
							if (oldSeqNo == null) {
								seqC = new MSequence(ctx, 0, trxName);
								seqC.setAD_Org_ID(to);
								seqC.setName(seqP.getName() + patternName.toString());
								seqC.setDescription(seqP.getName() + patternName.toString());
								seqC.setIncrementNo(1);
								seqC.setStartNo(10001000);
								seqC.setCurrentNext(1001000);
								seqC.setCurrentNextSys(1001000);
							}

							if (seqC.save(trxName)) {
								clonMDoc.setDocNoSequence_ID(seqC.getAD_Sequence_ID());
							} else {
								int adSeqNoSearched = MRecreateDocType.getIfExistNewSeqNo(seqP.getName(), to, to, trxName, NEXO1, NEXO2);
								if (adSeqNoSearched > 0) {
									clonMDoc.setDocNoSequence_ID(adSeqNoSearched);
								} else {
									throw new Exception("Error DocType" + seqP.getName() + patternName.toString());
								}
							}
						}
						if (!clonMDoc.save(trxName)) {
							return;
						}
					}
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "copyDocType", e);
		}
		Env.getCtx().setProperty("#AD_Org_ID", String.valueOf(from));
	}
	
	public String toString() {
		return getValue() + " - " + getName();
	}
	
	/**
	 * Busqueda por value.
	 * @param ctx
	 * @param orgValue
	 * @return
	 */
	public static MOrg getFromValue(Properties ctx, String orgValue) {
		return new Query(ctx, Table_Name, "Value=?", null).setParameters(orgValue).first();
//		StringBuilder st = new StringBuilder("select * from AD_Org ");
//		st.append("where  value = ?");
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MOrg client = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setString(1, orgValue);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				client = new MOrg(ctx, rs, null);
//			}
//			rs.close();
//			pstmt.close();
//			pstmt = null;
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs,pstmt);	
//		}
//		return client;
	}
	
	/**
	 * 
	 * @param po
	 * @return
	 */
	public static List<LabelValueBean> getOrgList() {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		String sql = "SELECT Name, AD_Org_ID FROM AD_Org ORDER BY Value";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Devuelve el nombre de la organizacion
	 * 
	 * @param ctx
	 *            - Contexto de la aplicacion
	 * @param AD_Org_ID
	 *            - ID de la organizacion
	 * @return Un valor String con el nombre de la organizacion
	 * @author Lorena Lama
	 */
	public static String getName(Properties ctx, int AD_Org_ID) {
		if (Env.getAD_Org_ID(ctx) == AD_Org_ID) {
			return Env.getAD_Org_Name(ctx);
		} else {
			return DB.getSQLValueString(null, "SELECT Name From AD_Org WHERE AD_Org_ID=?", AD_Org_ID);
		}
	}
	
	/**
	 * Get Organizations Of Client
	 * 
	 * @param ctx
	 *            Context
	 * @return array of orgs
	 */
	public static MOrg[] getOrgTrx(Properties ctx) {

		final List<MOrg> list = getOrgTrxList(ctx);

		MOrg[] retValue = new MOrg[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfClient

	/**
	 * Get Organizations Of Client
	 * 
	 * @param ctx
	 *            Context
	 * @return
	 */
	public static List<MOrg> getOrgTrxList(Properties ctx) {
		Query query = new Query(ctx, Table_Name, "AD_Client_ID=? AND IsSummary='N' AND AD_Org_ID<>0 ", null);
		query.setParameters(Env.getAD_Client_ID(ctx));
		query.setOrderBy("Name");

		return query.list();
	}
    
    /**
	 * Obtiene las organizaciones Padre
	 * @param ctx Contexto
	 * @param trxName Nombre de Transaccion
	 * @param whithClient True al del cliente actual o false todas las organizaciones
	 * @param active Nos indica si nos regresa las organizaciones activas o todas
	 * @return int[] de las organizaciones padres
	 * @author gvaldez
	 */
	public static Integer[] getParentOrgArray(Properties ctx, String trxName, boolean whithClient
			, boolean active, int AD_OrgType_ID) {
		List<Integer> lista = new ArrayList<Integer>();
		
		for (KeyNamePair lvb : getParentOrg(ctx, trxName, whithClient, active, AD_OrgType_ID)){
			lista.add(lvb.getKey());
		}
		
		Integer[] retValue = new Integer[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}
	
	
	
	public static List<KeyNamePair> getParentOrg(Properties ctx, String accessLevel, int clientID, String trxName) {

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT AD_Org.AD_Org_ID, AD_Org.Name ");
		sql.append("FROM AD_Org ");
		sql.append("INNER JOIN AD_OrgInfo oi ON (AD_ORG.AD_Org_ID = oi.AD_Org_ID) ");
		sql.append("WHERE oi.Parent_Org_ID IS NULL ");
		sql.append("AND AD_Org.IsActive='Y' ");

		// ADD Client
		if (MTable.ACCESSLEVEL_SystemPlusClient.equals(accessLevel) || MTable.ACCESSLEVEL_All.equals(accessLevel)) {
			sql.append("AND AD_Org.AD_Client_ID IN (0,?) ");
		} else {
			sql.append("AND AD_Org.AD_Client_ID=? ");
			if (MTable.ACCESSLEVEL_SystemOnly.equals(accessLevel)
					|| MTable.ACCESSLEVEL_ClientToSystem.equals(accessLevel)) {
				clientID = 0;
			}
		}
		// ADD Org
		int orgID = Env.getAD_Org_ID(ctx);
		if (MTable.ACCESSLEVEL_Organization.equals(accessLevel)) {
			sql.append("AND AD_Org.AD_Org_ID=? ");
		} else if (MTable.ACCESSLEVEL_ClientPlusOrganization.equals(accessLevel)
				|| MTable.ACCESSLEVEL_All.equals(accessLevel)) {
			sql.append("AND AD_Org.AD_Org_ID IN (0,?) ");
		} else { // all orgs
			orgID = -3;
		}

		final List<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, clientID);
			if (orgID != -3) {
				pstmt.setInt(2, orgID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
	
	public static List<KeyNamePair> getByClient(Properties ctx, int clientID){
		final StringBuilder st = new StringBuilder();
		st.append("SELECT AD_ORG.ad_org_id, AD_ORG.name ");
		st.append("  FROM AD_ORG");
		st.append(" INNER JOIN AD_ORGINFO ON (AD_ORG.AD_ORG_ID = AD_ORGINFO.AD_ORG_ID)");
		st.append(" WHERE AD_ORGINFO.PARENT_ORG_ID IS NULL ");
		st.append("   AND AD_ORG.ISACTIVE = 'Y' ");	
		st.append("   AND AD_ORG.AD_CLIENT_ID = ?");	
		
		return Query.getListKN(st.toString(), null, clientID);
	}
}	//	MOrg

