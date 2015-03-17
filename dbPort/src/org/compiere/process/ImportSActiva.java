package org.compiere.process;

import java.math.BigDecimal;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Timestamp;import java.util.logging.Level;import org.compiere.util.DB;import org.compiere.util.Env;import org.adempiere.exceptions.DBException;

/** * Proceso de importaci&oacute;n de la tabla de Sustancia Activa.<p> * Creado: 29/Mar/2005<p> * Modificado: $Date: 2005/04/02 00:48:03 $<p> * Por: $Author: gisela $<p> *  * @author Gisela Lee * @version $Revision: 1.1 $ */public class ImportSActiva extends SvrProcess {        /**	Client to be imported to		*/	private int				m_AD_Client_ID = 0;	/**	Delete old Imported				*/	private boolean			m_deleteOldImported = false;	/** Organization to be imported to	*/	private int				m_AD_Org_ID = 0;	/** Effective						*/	private Timestamp		m_DateValue = null;        /**     * Constructor por defecto.     */    public ImportSActiva() {        super();    }        /**     * Preparar: obtener par&aacute;metros     */    protected void prepare() {        ProcessInfoParameter[] para = getParameter();		for (int i = 0; i < para.length; i++)		{			String name = para[i].getParameterName();			if (name.equals("AD_Client_ID"))				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();			else if (name.equals("AD_Org_ID"))				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();			else if (name.equals("DeleteOldImported"))				m_deleteOldImported = "Y".equals(para[i].getParameter());			else				log.log(Level.SEVERE, "Unknown Parameter: " + name);		}		if (m_DateValue == null)			m_DateValue = DB.getTimestampForOrg(Env.getCtx());    }    /**     * Corre el proceso.     * @return Un mensaje de estado     * @throws Exception     */    protected String doIt() throws Exception {                StringBuffer sql = null;		int no = 0;		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;		//	****	Prepare	****		//	Delete Old Imported		if (m_deleteOldImported)		{			sql = new StringBuffer ("DELETE I_EXME_SActiva "				+ "WHERE I_IsImported='Y'").append(clientCheck);			no = DB.executeUpdate(sql.toString(), get_TrxName());			log.info("Delete Old Impored =" + no);		}		//	Set Client, Org, IaActive, Created/Updated,		sql = new StringBuffer ("UPDATE I_EXME_SActiva "			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"			+ " IsActive = COALESCE (IsActive, 'Y'),"			+ " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"			+ " CreatedBy = COALESCE (CreatedBy, 0),"			+ " Updated = COALESCE (Updated, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"			+ " I_ErrorMsg = NULL,"			+ " I_IsImported = 'N' "			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");		no = DB.executeUpdate(sql.toString(), get_TrxName());		log.info("Reset=" + no);				//	Value		sql = new StringBuffer ("UPDATE I_EXME_SActiva i "			+ "SET i.EXME_SActiva_ID=(SELECT p.EXME_SActiva_ID FROM EXME_SActiva p"			+ " WHERE i.Value=p.Value AND i.AD_Client_ID=p.AD_Client_ID) "			+ "WHERE i.I_IsImported='N' AND i.AD_Client_ID=").append(m_AD_Client_ID);		no = DB.executeUpdate(sql.toString(), get_TrxName());		log.info("SActiva Existing Value=" + no);				DB.commit(false, get_TrxName());		//		-------------------------------------------------------------------		int noInsert = 0;		int noUpdate = 0;		//int noInsertPO = 0;		//int noUpdatePO = 0;		//	Go through Records		log.fine("start inserting/updating ...");		sql = new StringBuffer ("SELECT I_EXME_SActiva_ID, EXME_SActiva_ID "			+ "FROM I_EXME_SActiva WHERE I_IsImported='N'").append(clientCheck);		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);        PreparedStatement pstmt = null;        ResultSet rs = null;
		try		{			//	Insertar la sustancia activa a partir de la importacion			PreparedStatement pstmt_insertSActiva = conn.prepareStatement				("INSERT INTO EXME_SActiva (EXME_SActiva_ID,"				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"				+ "Value,Name,Description,Formula, BASEINGREDIENTID, HICSEQNO, CONCEPTTYPE, CONCEPTID) "				+ "SELECT ?,"				+ "AD_Client_ID,AD_Org_ID,'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",CreatedBy,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,"				+ "Value,Name,Description,Formula,(Select BASEINGREDIENTID from FDB_HICSEQNO_BASEINGREDIENT where HICSEQNO = I_EXME_SActiva.CONCEPTID AND I_EXME_SActiva.CONCEPTTYPE='6') as BASEINGREDIENTID,Value,CONCEPTTYPE,CONCEPTID "				+ "FROM I_EXME_SActiva "				+ "WHERE I_EXME_SActiva_ID=?");			//	Update Sustancia Activa from Import			PreparedStatement pstmt_updateSActiva = conn.prepareStatement				("UPDATE EXME_SActiva "				+ "SET (Value,Name,Description,Updated,UpdatedBy,Formula, BASEINGREDIENTID, HICSEQNO, CONCEPTTYPE, CONCEPTID)= "				+ "(SELECT Value,Name,Description,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",UpdatedBy,Formula, (Select BASEINGREDIENTID from FDB_HICSEQNO_BASEINGREDIENT where HICSEQNO = I_EXME_SActiva.CONCEPTID AND I_EXME_SActiva.CONCEPTTYPE='6') as BASEINGREDIENTID, Value, CONCEPTTYPE, CONCEPTID"				+ " FROM I_EXME_SActiva WHERE I_EXME_SActiva_ID=?) "				+ "WHERE EXME_SActiva_ID=?");						//	Set Imported = Y			PreparedStatement pstmt_setImported = conn.prepareStatement				("UPDATE I_EXME_SActiva SET I_IsImported='Y', EXME_SActiva_ID=?, "				+ "Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ", Processed='Y' WHERE I_EXME_SActiva_ID=?");			//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());			rs = pstmt.executeQuery();			while (rs.next())			{				int I_EXME_SActiva_ID = rs.getInt(1);				int EXME_SActiva_ID = rs.getInt(2);				boolean newSActiva = EXME_SActiva_ID == 0;
			log.fine("I_EXME_SActiva_ID=" + I_EXME_SActiva_ID + ", EXME_SActiva_ID=" + EXME_SActiva_ID);				//	Sustancia Activa				if (newSActiva)			//	Insert new Sustancia Activa				{					try					{						EXME_SActiva_ID = DB.getNextID(m_AD_Client_ID, "EXME_SActiva", null);						if (EXME_SActiva_ID <= 0)							throw new DBException("No Next ID (" + EXME_SActiva_ID + ")");						pstmt_insertSActiva.setInt(1, EXME_SActiva_ID);						pstmt_insertSActiva.setInt(2, I_EXME_SActiva_ID);						//						no = pstmt_insertSActiva.executeUpdate();						log.finer("Insert Sustancia Activa = " + no);						noInsert++;					}					catch (Exception ex)					{						log.warning("Insert Sustancia Activa  - " + ex.toString());						sql = new StringBuffer ("UPDATE I_EXME_SActiva i "							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")							.append(DB.TO_STRING("Insert Sustancia Activa: " + ex.toString()))							.append("WHERE I_EXME_SActiva_ID=").append(I_EXME_SActiva_ID);						DB.executeUpdate(sql.toString(), get_TrxName());						continue;					}				}				else	//	Update Sustancia Activa				{					pstmt_updateSActiva.setInt(1, I_EXME_SActiva_ID);					pstmt_updateSActiva.setInt(2, EXME_SActiva_ID);					try					{						no = pstmt_updateSActiva.executeUpdate();						log.finer("Update Sustancia Activa = " + no);						noUpdate++;					}					catch (SQLException ex)					{						log.warning("Update Sustancia Activa - " + ex.toString());						sql = new StringBuffer ("UPDATE I_EXME_SActiva i "							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Sustancia Activa: " + ex.toString()))							.append("WHERE I_EXME_SActiva_ID=").append(I_EXME_SActiva_ID);						DB.executeUpdate(sql.toString(), get_TrxName());						continue;					}									}								//	Update I_EXME_SActiva				pstmt_setImported.setInt(1, EXME_SActiva_ID);				pstmt_setImported.setInt(2, I_EXME_SActiva_ID);				no = pstmt_setImported.executeUpdate();				//				conn.commit();			}			rs.close();			pstmt.close();            rs=null;            pstmt=null;			//			pstmt_insertSActiva.close();			pstmt_updateSActiva.close();			pstmt_setImported.close();			//			conn.close();			conn = null;		}		catch (SQLException e)		{			try			{				if (conn != null)					conn.close();				conn = null;                if(rs != null)                    rs.close();                if(pstmt != null)                    pstmt.close();			}			catch (SQLException ex)			{			}            rs=null;            pstmt=null;
			log.log(Level.SEVERE, "doIt", e);			throw new Exception ("doIt", e);		}		finally		{			if (conn != null)				conn.close();			conn = null;			if(rs != null)                rs.close();            if(pstmt != null)                pstmt.close();            rs=null;            pstmt=null;		}		//	Set Error to indicator to not imported		sql = new StringBuffer ("UPDATE I_EXME_SActiva "			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "			+ "WHERE I_IsImported<>'Y'").append(clientCheck);		no = DB.executeUpdate(sql.toString(), null);		addLog (0, null, new BigDecimal (no), "@Errors@");		addLog (0, null, new BigDecimal (noInsert), "@EXME_SActiva_ID@: @Inserted@");		addLog (0, null, new BigDecimal (noUpdate), "@EXME_SActiva_ID@: @Updated@");				return "";            }}