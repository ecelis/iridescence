package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MUser;
import org.compiere.model.X_I_EXME_User;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import com.ecaresoft.util.PasswordHandler;

/**
 * Proceso de importaci&oacute;n de la tabla I_Exme_User_Id.<p>
 * Creado: 23/Mar/2005<p>
 * Modificado: $Date: 2006/10/25 16:50:20 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author Ivan Vargas
 * @version $Revision: 1.2 $
 */
public class ImportUser extends SvrProcess {
    
    /**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Effective						*/
	private Timestamp		m_DateValue = null;
    
    /**
     * Constructor por defecto.
     */
    public ImportUser() {
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
		
		String trxName = Trx.createTrxName();
		String msg = "";

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_AD_User"
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), trxName);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_User "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + "),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), trxName);
		log.info("Reset=" + no);
		
		//Para las actualizaciones (vgarcia)
		sql = new StringBuffer("UPDATE I_EXME_USER i SET i.AD_USER_ID = " +
				"(SELECT u.AD_USER_ID from AD_USER u WHERE TRIM(upper(u.Name)) = TRIM(i.Name) " +
				"AND I.AD_USER_ID IS NULL ");

        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
				
		sql.append(" WHERE i.I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		log.info("AD_USER_ID=" + no);
		
//		-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		
		//	Go through Records
		log.fine("start inserting/updating ...");
		
		sql = new StringBuffer("SELECT * FROM I_EXME_User WHERE I_IsImported = 'N'");
		
		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
		ResultSet rs = pstmt.executeQuery();
		int AD_USER_ID = 0;
		MUser usuario = null;
		X_I_EXME_User iUser = null;

		PasswordHandler ph = new PasswordHandler(getCtx());

		while(rs.next()) {
			
			AD_USER_ID = rs.getInt("AD_USER_ID");
			boolean nuevo = AD_USER_ID>0;
			iUser = new X_I_EXME_User(getCtx(), rs, trxName);
			
	try {
			if(nuevo){
				usuario = new MUser(getCtx(), 0, trxName);
				
				//pasamos los datos al usuario
					usuario.setBirthday(iUser.getBirthday());
					usuario.setC_BPartner_ID(iUser.getC_BPartner_ID());
					usuario.setC_BPartner_Location_ID(iUser.getC_BPartner_Location_ID());
					usuario.setC_Greeting_ID(iUser.getC_Greeting_ID());
					usuario.setC_Job_ID(iUser.getC_Job_ID());
					usuario.setComments(iUser.getComments());
					usuario.setDescription(iUser.getDescription());
					usuario.setEMail(iUser.getEMail());
					usuario.setEMailUser(iUser.getEMailUser());
					usuario.setEMailUserPW(iUser.getEMailUserPW());
					usuario.setEMailVerify(iUser.getEMailVerify());
					usuario.setEMailVerifyDate(iUser.getEMailVerifyDate());
					usuario.setFax(iUser.getFax());
					usuario.setIsFullBPAccess(iUser.isFullBPAccess());
					usuario.setLastContact(iUser.getLastContact());
					usuario.setLastResult(iUser.getLastResult());
					usuario.setLDAPUser(iUser.getLDAPUser());
					usuario.setName(iUser.getName());
					usuario.setPassword(ph.generatePassword());
					usuario.setPhone(iUser.getPhone());
					usuario.setPhone2(iUser.getPhone2());
					usuario.setSupervisor_ID(iUser.getSupervisor_ID());
					usuario.setTitle(iUser.getTitle());
			}else {
				usuario = MUser.get(getCtx(), AD_USER_ID, get_TrxName());
				
				usuario.setName(rs.getString("Name"));
				usuario.setDescription(rs.getString("Description"));
			}
			
				
				if(!usuario.save(trxName)) {
					iUser.setI_ErrorMsg("No fue posible importar este usuario, verifique los datos.");
					no++;
				} else {
					iUser.setI_IsImported(true);
					
					if(nuevo)
						noInsert++;
					else
						noUpdate++;
				}
				
			} catch(Exception e) {
				log.log(Level.SEVERE, "Importando usuario " + iUser.getName(), e);
				iUser.setI_ErrorMsg(e.getMessage());
				no++;
			}
			iUser.save(trxName);
			
			usuario = null;
			iUser = null;
		}
        try {
            if (pstmt != null)
                pstmt.close ();
            if(rs!=null)
                rs.close();
        } catch (Exception e) {}
        pstmt=null;
        rs=null;
        
		
		//Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_User "
			+ "SET I_IsImported='N', Updated="+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@AD_User_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@AD_User_ID@: @Updated@");
		
		if(!DB.commit(true, trxName)) {
			DB.rollback(true, trxName);
			Trx.get(trxName, false).close();
		} else {
			msg = "No fue posible actualizar la informacion en la base de datos.";
		}
		//DB.rollback(true, trxName);
		//Trx.get(trxName, false).close();
		
		return msg;
        
    }

}