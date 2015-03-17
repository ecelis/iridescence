package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.X_A_Asset;
import org.compiere.model.X_EXME_EquipoH;
import org.compiere.model.X_I_EXME_EquipoH;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class MttoEquipos {
	
	
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	
	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	
	/**	Logger							*/
	protected CLogger		log = CLogger.getCLogger (getClass());
	
	/** Nombre de transaccion */
	private String          m_TrxName;
	
	/** Nombre de transaccion */
	private Properties      m_Ctx;
	
	/**
	 * @param ctx
	 * @param ID
	 */
	public MttoEquipos(Properties ctx, boolean deleteOldImported, String trxName) {
		m_TrxName = trxName;
		m_Ctx = ctx;
		m_AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		m_deleteOldImported = deleteOldImported;
		m_AD_Org_ID = Env.getContextAsInt(ctx, "#AD_Org_ID");
	}

	
	/**
	 * @param ctx
	 * @param ID
	 */
	public MttoEquipos(Properties ctx, int AD_Client_ID, int AD_Org_ID,
			boolean deleteOldImported, String trxName) {
		m_TrxName = trxName;
		m_Ctx = ctx;
		m_AD_Client_ID = Env.getContextAsInt(ctx, "#AD_Client_ID");
		m_deleteOldImported = deleteOldImported;
		m_AD_Org_ID = Env.getContextAsInt(ctx, "#AD_Org_ID");
	}
	
	
	/**
	 *  Perrform process.
	 *  EXME_Equipoh
	 (
	 EXME_Equipoh_id                  NUMBER   (10)                    NOT NULL
	 , ad_client_id                     NUMBER   (10)                    NOT NULL
	 , ad_org_id                        NUMBER   (10)                    NOT NULL
	 , isactive                         CHAR     (1)                     DEFAULT 'Y' NOT NULL
	 , created                          DATE                             DEFAULT SYSDATE NOT NULL
	 , createdby                        NUMBER   (10)                    NOT NULL
	 , updated                          DATE                             DEFAULT SYSDATE NOT NULL
	 , updatedby                        NUMBER   (10)                    NOT NULL
	 , exme_equipo_id                   NUMBER   (10)                    NOT NULL
	 , fecha_ini                        DATE                             NOT NULL
	 , fecha_fin                        DATE                             NOT NULL
	 , exme_area_id                     NUMBER   (10)                    NOT NULL
	 , estatus_equipo                   CHAR     (1)                     NOT NULL
	 
	 )
	 *  @return Message
	 *  @throws Exception
	 */
	protected LabelValueBean doIt() throws java.lang.Exception
	
	{
		
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID + " AND AD_Org_ID=" + m_AD_Org_ID;
		
		//	****	Prepare	****
		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder ("DELETE I_EXME_Equipoh WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Impored =" + no);
		}
		
		//Actualiza valores de los no importados
		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_EXME_Equipoh ")
		.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("), ")
		.append(" AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("), ")
		.append(" IsActive = COALESCE (IsActive, 'Y'), ")
		.append(" Created = COALESCE (Created, SysDate), ")
		.append(" CreatedBy = COALESCE (CreatedBy, 0), ")
		.append(" Updated = COALESCE (Updated, SysDate), ")
		.append(" UpdatedBy = COALESCE (UpdatedBy, 0), ")
		.append(" I_ErrorMsg = NULL, ")
		.append(" I_IsImported = 'N' ")
		.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL ");
		no = DB.executeUpdate(sql.toString(), m_TrxName);
		log.fine("Reset=" + no);
		
		
		//	Existing Equipo ?
		sql = new StringBuilder (" UPDATE I_EXME_Equipoh i ")
		.append(" SET i.EXME_Equipo_ID=(SELECT e.EXME_Equipo_ID FROM EXME_Equipo e ")
		.append(" WHERE trim(i.Codigo_Equipo)=trim(e.Value) AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.Codigo_Equipo IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), m_TrxName);
		log.fine("Found Equipo ID=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Equipoh ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalida llave de busqueda de equipo'  ")
		.append(" WHERE EXME_Equipo_ID IS NULL ")
		.append(" AND I_IsImported='N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), m_TrxName);
		log.config("Invalid Equipo ID=" + no);
		
		
		//	Existing Area ?
		sql = new StringBuilder (" UPDATE I_EXME_Equipoh i ")
		.append(" SET i.EXME_Area_ID=(SELECT e.EXME_Area_ID FROM EXME_Equipo e ")
		.append(" WHERE i.EXME_Equipo_ID =  e.EXME_Equipo_ID AND e.AD_Client_ID IN (i.AD_Client_ID,0)) ")
		.append(" WHERE i.EXME_Equipo_ID IS NOT NULL")
		.append(" AND i.I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), m_TrxName);
		log.fine("Found Equipo ID=" + no);
		
		sql = new StringBuilder ("UPDATE I_EXME_Equipoh ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=No existe un area de relacion al equipo'  ")
		.append(" WHERE EXME_Area_ID IS NULL ")
		.append(" AND I_IsImported<>'Y' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), m_TrxName);
		log.config("Invalid Area ID=" + no);
		
		DB.commit(true, m_TrxName);
		
		//Error en fechas: La fecha inicial no puede ser menor a la actual
		sql = new StringBuilder (" SELECT I_EXME_Equipoh_ID ")
		.append(" FROM I_EXME_Equipoh ")
		.append(" WHERE I_IsImported='N' AND EXME_Equipo_ID is not null AND EXME_Area_ID is not null ").append(clientCheck)
		.append(" AND ( to_date(Fecha_Ini, 'dd/mm/yyyy hh24:mi')  < ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(" OR to_date(Fecha_Fin, 'dd/mm/yyyy hh24:mi')   <  ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(" ")
		.append(" OR to_date(Fecha_Fin, 'dd/mm/yyyy hh24:mi')  <  to_date(Fecha_Ini, 'dd/mm/yyyy hh24:mi') ) ");
		
		Statement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt_setImported = null;
		try
		{
			pstmt = DB.createStatement();//, trxName);
			rs = pstmt.executeQuery(sql.toString());
			while (rs.next())
			{
				sql = new StringBuilder (" UPDATE I_EXME_Equipoh  ")
				.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Fecha(s) Invalida(s)'  ")
				.append(" WHERE I_EXME_Equipoh_ID=").append(rs.getInt("I_EXME_Equipoh_ID"));
				DB.executeUpdate(sql.toString(), m_TrxName);
				no++;
			}
		}	
		catch(Exception ex)
		{
			sql = new StringBuilder ("UPDATE I_EXME_Equipoh i ")
			.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  ':  " + ex.getMessage().toString())
			.append("'  WHERE I_EXME_Equipoh_ID=").append(rs.getInt("I_EXME_Equipoh_ID"));
			DB.executeUpdate(sql.toString(), m_TrxName);
			no++;
		}
		
		DB.commit(true, m_TrxName);
		
		//	-------------------------------------------------------------------
		int noInsert = 0;
		
		//	Go through Records
		sql = new StringBuilder (" SELECT I_EXME_Equipoh_ID, exme_equipo_id, to_date(Fecha_Ini, 'dd/mm/yyyy hh24:mi') as fecha_ini, ")
		.append(" to_date(Fecha_Fin, 'dd/mm/yyyy hh24:mi') as fecha_fin, exme_area_id ")
		.append(" FROM I_EXME_Equipoh ")
		.append(" WHERE I_IsImported='N' AND EXME_Equipo_ID is not null AND EXME_Area_ID is not null ").append(clientCheck);
		
		Trx m_trx = null;
		
		try
		{
			pstmt = DB.createStatement();//, trxName);
			rs = pstmt.executeQuery(sql.toString());
			
			m_trx = Trx.get(m_TrxName,true);
			//tring trxName = m_trx.getTrxName();
			
			while (rs.next())
			{
				//Fecha de ultima actualizaci#n ?
				try
				{
					int I_EXME_Equipoh_ID = rs.getInt("I_EXME_Equipoh_ID");
					
					X_EXME_EquipoH equipoH = new X_EXME_EquipoH(m_Ctx, 0, m_TrxName);
					equipoH.setEXME_Equipo_ID(rs.getInt(2));
					equipoH.setFecha_Ini(rs.getTimestamp(3));
					equipoH.setFecha_Fin(rs.getTimestamp(4));
					equipoH.setEXME_Area_ID(rs.getInt(5));
					equipoH.setEstatus_Equipo(X_EXME_EquipoH.ESTATUS_EQUIPO_Maintenance);
					if (!equipoH.save())
					{
						sql = new StringBuilder (" UPDATE I_EXME_Equipoh  ")
						.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Imposible insertar registro'  ")
						.append(" WHERE I_EXME_Equipoh_ID= ? ");
						
						pstmt_setImported = DB.prepareStatement(sql.toString(), m_TrxName);
						pstmt_setImported.setInt(1, I_EXME_Equipoh_ID);
						no = pstmt_setImported.executeUpdate();
						noInsert++;
					}
					else
					{
						
						X_I_EXME_EquipoH iEquipoH= new X_I_EXME_EquipoH(m_Ctx, I_EXME_Equipoh_ID, m_TrxName);
						
						iEquipoH.setEXME_Equipo_ID(equipoH.getEXME_EquipoH_ID());
						iEquipoH.setI_IsImported(true);
						iEquipoH.setProcessed(true);
						
						if (!iEquipoH.save()){
							sql = new StringBuilder (" UPDATE I_EXME_Equipoh  ")
							.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  'Imposible insertar registro'  ")
							.append(" WHERE I_EXME_Equipoh_ID= ? ");
							
							pstmt_setImported = DB.prepareStatement(sql.toString(), m_TrxName);
							pstmt_setImported.setInt(1, I_EXME_Equipoh_ID);
							no = pstmt_setImported.executeUpdate();
							noInsert++;
						}
						
						no++;
					}
					
					if(m_trx!=null)
						m_trx.commit();
					
					//no = pstmt_setImported.executeUpdate();
					DB.commit(true, m_TrxName);
					
					
				}
				catch(Exception ex)
				{
					sql = new StringBuilder ("UPDATE I_EXME_Equipoh i ")
					.append(" SET I_IsImported='E', I_ErrorMsg = I_ErrorMsg ||  ':  " + ex.getMessage().toString())
					.append("'  WHERE I_EXME_Equipoh_ID=").append(rs.getInt("I_EXME_Equipoh_ID"));
					DB.executeUpdate(sql.toString(), m_TrxName);
					//no++;
					continue;
					
				}
				

			}
			
			if(m_trx!=null){
				m_trx.rollback();
				m_trx.close();
			}
			
			m_trx = null;
			
			DB.commit(true, m_TrxName);
			
			if(pstmt!=null)
				pstmt.close();
			
			if(pstmt_setImported!=null)
				pstmt_setImported.close();
			
			pstmt = null;
			pstmt_setImported = null;
			
			if(rs!=null)
				rs.close();
			rs = null;
			
				/**
			 --	LastMaintenanceDate # Fecha inicial del #ltimo mantenimiento 
			 --	LeaseTerminationDate # Fecha final del #ltimo mantenimiento 
			 --	NextMaintenenceDate # Fecha inicial del pr#ximo mantenimiento 
			 --	AssetDisposalDate # Fecha final del pr#ximo mantenimiento 
			 */
			//Verificar las fechas de ultimo mtto y proximo mtto de activos
			//- la fecha de ultimo mtto debe ser menor o = al dia de hoy o no existir
			//- la fecha de proximo debe ser mayor al dia de hoy
			//si se hace la importacion y la fecha de proximo ya se cumplio esta pasa a ser la de ultimo 
			//y se busca la del proximo
			sql = new StringBuilder (" SELECT EXME_Equipo.EXME_Equipo_ID , a.A_Asset_ID , MIN ( eh.Fecha_Ini ) AS Fecha_Ini ")
			.append(" FROM EXME_Equipo ")
			.append(" INNER JOIN A_Asset a ON EXME_Equipo.A_Asset_ID = a.A_Asset_ID ")
			.append(" INNER JOIN EXME_EquipoH eh ON EXME_Equipo.EXME_Equipo_ID = eh.EXME_Equipo_ID AND eh.IsActive = 'Y' ")
			.append(" WHERE EXME_Equipo.IsActive = 'Y' ")
			.append(" AND ( a.NextMaintenenceDate IS NULL OR a.NextMaintenenceDate < ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(" ) ")
			.append(" AND eh.Estatus_Equipo = 'M' ")
			.append(" AND eh.Fecha_Ini IS NOT NULL ")
			.append(" AND eh.Fecha_Ini > NVL ( a.NextMaintenenceDate , ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(" ) ")
			.append(" AND eh.Fecha_Ini > ").append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(" ")
			.append(MEXMELookupInfo.addAccessLevelSQL(m_Ctx, " ", "EXME_Equipo"))
			.append(" GROUP BY EXME_Equipo.EXME_Equipo_ID, a.A_Asset_ID ")
			.append(" ORDER BY EXME_Equipo.EXME_Equipo_ID ");
			
			pstmt = DB.createStatement();//, trxName);
			rs = pstmt.executeQuery(sql.toString());
			while (rs.next()) {
				X_A_Asset equipo = new X_A_Asset(m_Ctx, rs.getInt(2), m_TrxName);
				//Fecha de ultimo mantenimiento
				equipo.setLastMaintenanceDate(equipo.getNextMaintenenceDate());
				equipo.setLeaseTerminationDate(equipo.getAssetDisposalDate());
				//Fecha de proximo mantenimiento
				equipo.setNextMaintenenceDate(rs.getTimestamp(3));
				System.out.println(equipo.getNextMaintenenceDate());
				equipo.setAssetDisposalDate(null);
				
				if (!equipo.save())
				{
					log.config("Fecha Init mtto Id Asset"+rs.getInt(2));
				}
			}
			
			if(pstmt!=null)
				pstmt.close();
			pstmt = null;
			if(rs!=null)
				rs.close();
			rs = null;
			
			sql = new StringBuilder (" SELECT EXME_Equipo.EXME_Equipo_ID , a.A_Asset_ID , eh.Fecha_Fin ")
			.append(" FROM EXME_Equipo EXME_Equipo")
			.append(" INNER JOIN A_Asset a ON EXME_Equipo.A_Asset_ID = a.A_Asset_ID ")
			.append(" INNER JOIN EXME_EquipoH eh ON EXME_Equipo.EXME_Equipo_ID = eh.EXME_Equipo_ID AND eh.IsActive = 'Y' ")
			.append(" WHERE EXME_Equipo.IsActive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(m_Ctx, " ", "EXME_Equipo"))
			.append(" AND a.NextMaintenenceDate IS NOT NULL ")
			.append(" AND a.AssetDisposalDate IS NULL ")
			.append(" AND eh.Fecha_Ini = a.NextMaintenenceDate ")
			.append(" AND eh.Estatus_Equipo = 'M' ")
			.append(" ORDER BY EXME_Equipo.EXME_Equipo_ID ");
			
			pstmt = DB.createStatement();//, trxName);
			rs = pstmt.executeQuery(sql.toString());
			while (rs.next()) {
				X_A_Asset equipo = new X_A_Asset(m_Ctx, rs.getInt(2), m_TrxName);
				equipo.setAssetDisposalDate(rs.getTimestamp(3));
				System.out.println(equipo.getAssetDisposalDate());
				
				if (!equipo.save())
				{
					log.config("Fecha Fin mtto. Id Asset"+rs.getInt(2));
				}
			}
			DB.commit(true, m_TrxName);
		}
		catch (SQLException e)
		{
			throw new Exception ("ImportMttoEquipos.doIt", e);
		}
		finally
		{
			//expert
			if(pstmt!=null)
				pstmt.close();
			pstmt = null;
			if(rs!=null)
				rs.close();
			rs = null;
			if(m_trx!=null){
				m_trx.rollback();
				m_trx.close();
			}
			
			m_trx = null;
			
		}
		
		//addLog (0, null, new BigDecimal (no), "@Errors@");
		//addLog (0, null, new BigDecimal (noInsert), "@EXME_Equipoh_ID@: @Inserted@");
		
		LabelValueBean result = new LabelValueBean(String.valueOf(no), String.valueOf(noInsert));
		return result;
		
	}	//	doIt
	
}
