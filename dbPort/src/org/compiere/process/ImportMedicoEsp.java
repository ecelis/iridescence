package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMedico;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla Medico/Especialidad.<p>
 * Creado: 23/Mar/2005<p>
 * Modificado: $Date: 2006/02/09 23:33:50 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.2 $
 */
public class ImportMedicoEsp extends SvrProcess {
    
    /**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;
    
    /**
     * Constructor por defecto.
     */
    public ImportMedicoEsp() {
        super();
    }
    
    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			 else if (name.equals("AD_Org_ID"))
					m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());

    }

    /**
     * Corre el proceso.
     * @return Un mensaje de estado
     * @throws Exception
     */
    protected String doIt() throws Exception {
        
        StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_MedicoEsp "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(),  null);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(),  null);
		log.info("Reset=" + no);
		
		//	Set Medico
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp i "
			+ "SET EXME_Medico_ID=(SELECT EXME_Medico_ID FROM EXME_Medico m"
			+ " WHERE i.Medico_Value=m.Value "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name, "m")                
            + ") "
			+ "WHERE EXME_Medico_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		log.fine("Set Medico=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Medico, ' "
			+ "WHERE EXME_Medico_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		log.config("Invalid Medico=" + no);
		
		//	Set Especialidad
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp i "
			+ "SET EXME_Especialidad_ID=(SELECT EXME_Especialidad_ID FROM EXME_Especialidad e"
			+ " WHERE i.Especialidad_Value = e.Value" /* AND i.AD_Client_ID = e.AD_Client_ID" Cambio para nuevo nivel de acceso - dnuncio2010 */
			+ ") WHERE i.EXME_Especialidad_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		log.fine("Set Especialidad=" + no);
		//
		
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Especialidad, ' "
			+ "WHERE EXME_Especialidad_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		log.config("Invalid Especialidad=" + no);
		
		//	Set Medico Sustituto
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp i "
			+ "SET EXME_Medico_Sust_ID=(SELECT EXME_Medico_ID FROM EXME_Medico m"
			+ " WHERE i.MedicoSust_Value=m.Value AND i.AD_Client_ID=m.AD_Client_ID) "
			+ "WHERE EXME_Medico_Sust_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		log.fine("Set Medico Sustituto=" + no);
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		
		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_EXME_MedicoEsp_ID, EXME_MedicoEsp_ID "
			+ "FROM I_EXME_MedicoEsp WHERE I_IsImported='N'").append(clientCheck);
		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
		{
			//	Insertar medico especialidad a partir de la importacion
			PreparedStatement pstmt_insertMedicoEsp = conn.prepareStatement
				("INSERT INTO EXME_MedicoEsp (EXME_MedicoEsp_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "EXME_Especialidad_ID, EXME_Medico_ID, HaceGuardia, EXME_Medico_Sust_ID) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ "EXME_Especialidad_ID, EXME_Medico_ID, COALESCE(HaceGuardia, 'N'), EXME_Medico_Sust_ID "
				+ "FROM I_EXME_MedicoEsp "
				+ "WHERE I_EXME_MedicoEsp_ID=?");


			//	Update medico especialidad from Import
			PreparedStatement pstmt_updateMedicoEsp = conn.prepareStatement
				("UPDATE EXME_MedicoEsp "
				+ "SET (Updated,UpdatedBy,EXME_Especialidad_ID, EXME_Medico_ID, HaceGuardia, EXME_Medico_Sust_ID)= "
				+ "(SELECT SysDate,UpdatedBy,EXME_Especialidad_ID, EXME_Medico_ID, HaceGuardia, EXME_MEDICO_SUST_ID"
				+ " FROM I_EXME_MedicoEsp WHERE I_EXME_MedicoEsp_ID=?) "
				+ "WHERE EXME_MedicoEsp_ID=?");

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
				("UPDATE I_EXME_MedicoEsp SET I_IsImported='Y', EXME_MedicoEsp_ID=?, "
				+ "Updated=SysDate, Processed='Y' WHERE I_EXME_MedicoEsp_ID=?");

			//
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_EXME_MedicoEsp_ID = rs.getInt(1);
				int EXME_MedicoEsp_ID = rs.getInt(2);
				boolean newMedicoEsp = EXME_MedicoEsp_ID == 0;
				log.fine("I_EXME_MedicoEsp_ID=" + I_EXME_MedicoEsp_ID + ", EXME_MedicoEsp_ID=" + EXME_MedicoEsp_ID);

				//	medico especialidad
				if (newMedicoEsp)	//	Insert new medico especialidad
				{
					try
					{
						EXME_MedicoEsp_ID = DB.getNextID(m_AD_Client_ID, "EXME_MedicoEsp", null);
						if (EXME_MedicoEsp_ID <= 0)
							throw new DBException("No Next ID (" + EXME_MedicoEsp_ID + ")");
						pstmt_insertMedicoEsp.setInt(1, EXME_MedicoEsp_ID);
						pstmt_insertMedicoEsp.setInt(2, I_EXME_MedicoEsp_ID);
						//
						no = pstmt_insertMedicoEsp.executeUpdate();
						log.finer("Insert medico especialidad = " + no);
						noInsert++;
					}
					catch (Exception ex)
					{
						log.warning("Insert medico especialidad  - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insert medico especialidad: " + ex.toString()))
							.append("WHERE I_EXME_MedicoEsp_ID=").append(I_EXME_MedicoEsp_ID);
						DB.executeUpdate(sql.toString(),  null);
						continue;
					}
				}
				else	//	Update medico especialidad
				{
					pstmt_updateMedicoEsp.setInt(1, I_EXME_MedicoEsp_ID);
					pstmt_updateMedicoEsp.setInt(2, EXME_MedicoEsp_ID);
					try
					{
						no = pstmt_updateMedicoEsp.executeUpdate();
						log.finer("Update medico especialidad = " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
						log.warning("Update medico especialidad - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update medico especialidad: " + ex.toString()))
							.append("WHERE I_EXME_MedicoEsp_ID=").append(I_EXME_MedicoEsp_ID);
						DB.executeUpdate(sql.toString(),  null);
						continue;
					}
					
				}
				
				//	Update I_EXME_MedicoEsp
				pstmt_setImported.setInt(1, EXME_MedicoEsp_ID);
				pstmt_setImported.setInt(2, I_EXME_MedicoEsp_ID);
				no = pstmt_setImported.executeUpdate();
				//
				conn.commit();
			}
			rs.close();
			pstmt.close();

			//
			pstmt_insertMedicoEsp.close();
			pstmt_updateMedicoEsp.close();
			pstmt_setImported.close();
			//
			conn.close();
			conn = null;
		}
		catch (SQLException e)
		{
            try
            {
                if (conn != null)
                    conn.close();
                conn = null;
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }
            catch (SQLException ex)
            {
            }
            log.log(Level.SEVERE, "doIt", e);
            rs=null;
            pstmt=null;
			throw new Exception ("doIt", e);
		}
        finally
        {
            if (conn != null)
                conn.close();
            conn = null;
            if(rs != null)
                rs.close();
            if(pstmt != null)
                pstmt.close();
            rs=null;
            pstmt=null;
        }


		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_MedicoEsp "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),  null);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_MedicoEsp_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_MedicoEsp_ID@: @Updated@");
		
		return "";
        
    }

}