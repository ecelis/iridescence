package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCountry;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
import org.compiere.model.MUser;
import org.compiere.model.X_I_EXME_Medico;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;
import org.compiere.util.Trx;

import com.ecaresoft.util.PasswordHandler;
/**
 *	Importaci&oacute;n de M&eacute;dicos
 *
 * 	@author 	miguel angel rojas 
 * 	@version 	$Id: ImportExmeMedico.java,v 1.13 2006/10/19 21:10:50 vgarcia Exp $
 */
public class ImportExmeMedico extends SvrProcess
{
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (ImportExmeMedico.class);
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
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
				log.log(Level.SEVERE, "ImportExmeMedico.prepare - Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_EXME_Medico "
				+ "WHERE I_IsImported='Y'");
			no = DB.executeUpdate(sql.toString());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_EXME_Medico "
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
		no = DB.executeUpdate(sql.toString());
		log.fine("Reset=" + no);
		
		//	Set Country
		sql = new StringBuffer ("UPDATE I_EXME_Medico i SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c " +
				"WHERE TRIM(i.CountryCode)=TRIM(c.CountryCode) AND c.AD_Client_ID IN (0, i.AD_Client_ID)) " +
				"WHERE C_Country_ID IS NULL AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Set Country=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Medico "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid Country' "
			+ "WHERE C_Country_ID IS NULL"
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.config("Invalid Country=" + no);

		//	Set Region
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r"
			+ " WHERE r.Name=i.RegionName"
			+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE C_Region_ID IS NULL"
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Set Region=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid Region' "
			+ "WHERE C_Region_ID IS NULL "
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.config("Invalid Region=" + no);
		
		
		//Set Country Cons
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "SET C_Country_Cons_ID=(SELECT C_Country_ID FROM C_Country c"
			+ " WHERE TRIM(i.CountryCodeCons) = TRIM(c.CountryCode) AND c.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE C_Country_Cons_ID IS NULL"
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Set CountryCons=" + no);

		//	Set Region Cons
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "Set C_Region_Cons_ID=(SELECT C_Region_ID FROM C_Region r"
			+ " WHERE r.Name=i.RegionNameCons "
			+ " AND r.AD_Client_ID IN (0, i.AD_Client_ID)) "
			+ "WHERE C_Region_ID IS NULL"
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Set Region=" + no);

		//	Ya existe el medico?
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "SET EXME_Medico_ID=(SELECT EXME_Medico_ID FROM EXME_Medico m"
			+ " WHERE i.Value=m.Value  "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEMedico.Table_Name, "m")                
            + ") "
			+ "WHERE EXME_Medico_ID IS NULL AND Value IS NOT NULL"
			+ " AND I_IsImported='N'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Found Medico=" + no);

		//	Existing Contact ? Match Name
		sql = new StringBuffer ("UPDATE I_EXME_Medico i "
			+ "SET AD_User_ID=(SELECT AD_User_ID FROM AD_User c"
			+ " WHERE i.ContactName=c.Name AND c.AD_Client_ID IN(0, i.AD_Client_ID)) "
			+ "WHERE EXME_Medico_ID IS NOT NULL AND AD_User_ID IS NULL AND ContactName IS NOT NULL"
			+ " AND I_IsImported='N'");
		no = DB.executeUpdate(sql.toString());
		log.fine("Found Contact=" + no);
		
		//establecemos el tipo de medico
		sql = new StringBuffer("UPDATE I_EXME_Medico i " +
		        "SET EXME_TipoMedico_ID = (SELECT EXME_TipoMedico_ID "+
		        " FROM EXME_TipoMedico t WHERE t.Value = i.EXME_TipoMedico_Value "+
		        " AND i.AD_Client_ID = t.AD_Client_ID) " +
		        "WHERE EXME_TipoMedico_ID IS NULL " +
		        "AND EXME_TipoMedico_Value IS NOT NULL " +
		        "AND i.I_IsImported = 'N' ");
		no = DB.executeUpdate(sql.toString());
		log.info("Tipo medico=" + no);
		//
		sql = new StringBuffer ("UPDATE I_EXME_Medico "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Tipo Medico no Valido' "
			+ "WHERE EXME_TipoMedico_ID IS NULL"
			+ " AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString());
		if (no != 0)
			log.warning("Tipo Medico no Valido=" + no);
		
//		establecemos la universidad
		sql = new StringBuffer("UPDATE I_EXME_Medico i " +
		        "SET EXME_Universidad_ID = (SELECT EXME_Universidad_ID "+
		        " FROM EXME_Universidad u WHERE u.Value = i.EXME_Universidad_Value "+
		        " AND i.AD_Client_ID = u.AD_Client_ID) " +
		        "WHERE EXME_Universidad_ID IS NULL " +
		        "AND EXME_Universidad_Value IS NOT NULL " +
		        "AND i.I_IsImported = 'N' ");
		no = DB.executeUpdate(sql.toString());
		log.info("Universidad=" + no);

//		establecemos el turno
		/*sql = new StringBuffer("UPDATE I_EXME_Medico i " +
		        "SET EXME_Turnos_ID = (SELECT EXME_Turnos_ID "+
		        " FROM EXME_Turnos t WHERE t.Value = i.Turnos_Value "+
		        " AND i.AD_Client_ID = t.AD_Client_ID) " +
		        "WHERE EXME_Turnos_ID IS NULL " +
		        "AND Turnos_Value IS NOT NULL " +
		        "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.info("Turnos=" + no);*/
		
//		establecemos el producto
		/*sql = new StringBuffer("UPDATE I_EXME_Medico i " +
		        "SET M_Product_ID = (SELECT M_Product_ID "+
		        " FROM M_Product p WHERE p.Value = i.M_Product_Value "+
		        " AND i.AD_Client_ID = p.AD_Client_ID) " +
		        "WHERE M_Product_ID IS NULL " +
		        "AND M_Product_Value IS NOT NULL " +
		        "AND i.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		log.info("Producto=" + no);*/


		

		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
	
			/*sql = new StringBuffer("insert into exme_medico (exme_medico_id, ad_client_id, ad_org_id,isactive,created,createdby,updated,updatedby," +
					"value,name,description,apellido1,apellido2,sexo,estasuspendido,fechanac,celular,email,esinterno,exme_tipomedico_id," +
					"rfc,curp,edocivil,c_location_id,telparticular,radio,fechaingreso,cedprofesional,codsanidad,fechatitulo," +
					"exme_universidad_id,fechacertifconsejo,fechavencimcertif,estarecertificado,tieneincentivo," +
					"c_location_cons_id,telconsultorio,exme_centromedico_id,noconsultorio,exme_turnos_id,intervaloconsulta,ad_user_id," +
					"m_product_id,estacertifconsejo,nombre_med)" +
					"select ?,ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby," +
					"value,name,description,apellido1,apellido2,sexo,NVL(estasuspendido,'N'),fechanac,celular,email,esinterno,exme_tipomedico_id," +
					"rfc,curp,edocivil,c_location_id,telparticular,radio,fechaingreso,cedprofesional,codsanidad,fechatitulo," +
					"exme_universidad_id,fechacertifconsejo,fechavencimcertif,NVL(estarecertificado,'N'),NVL(tieneincentivo,'N')," +
					"c_location_cons_id,telconsultorio,exme_centromedico_id,noconsultorio,exme_turnos_id,intervaloconsulta,ad_user_id," +
					"m_product_id,NVL(estacertifconsejo,'N'),name||' '||apellido1||' '||apellido2 from i_exme_medico where i_exme_medico_id=?");
			
			PreparedStatement pstmt_insert = 	DB.prepareStatement(sql.toString());

		sql = new StringBuffer("update exme_medico set(" +
				"name,description,apellido1,apellido2,sexo,estasuspendido,fechanac,celular,email,esinterno,exme_tipomedico_id," +
				"rfc,curp,edocivil,c_location_id,telparticular,radio,fechaingreso,cedprofesional,codsanidad,fechatitulo," +
				"exme_universidad_id,fechacertifconsejo,fechavencimcertif,estarecertificado,tieneincentivo," +
				"c_location_cons_id,telconsultorio,exme_centromedico_id,noconsultorio,exme_turnos_id,intervaloconsulta,ad_user_id," +
				"m_product_id,estacertifconsejo,nombre_med)=" +
				"(select " +
				"name,description,apellido1,apellido2,sexo,NVL(estasuspendido,'N'),fechanac,celular,email,esinterno,exme_tipomedico_id," +
				"rfc,curp,edocivil,c_location_id,telparticular,radio,fechaingreso,cedprofesional,codsanidad,fechatitulo," +
				"exme_universidad_id,fechacertifconsejo,fechavencimcertif,NVL(estarecertificado,'N'),NVL(tieneincentivo,'N')," +
				"c_location_cons_id,telconsultorio,exme_centromedico_id,noconsultorio,exme_turnos_id,intervaloconsulta,ad_user_id," +
				"m_product_id,NVL(estacertifconsejo,'N'),name||' '||apellido1||' '||apellido2 from i_exme_medico where i_exme_medico_id=?)" +
				"where exme_medico_id=?");
		PreparedStatement pstmt_update = DB.prepareStatement(sql.toString());*/

		
		PreparedStatement pstmt_Location = 
			DB.prepareStatement("SELECT C_LOCATION_ID,C_LOCATION_CONS_ID FROM EXME_MEDICO WHERE EXME_MEDICO_ID=?");

		//	Go through Records
		sql = new StringBuffer ("SELECT I_EXME_Medico_ID,  EXME_Medico_ID,  AD_User_ID "
			+ "FROM I_EXME_Medico "
			+ "WHERE I_IsImported='N'");
		//Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		
		// para buscar los datos del medico cuando es una actualizacion (vgarcia)
	//	StringBuffer sqlMedico = new StringBuffer("Select * from EXME_MEDICO where EXME_MEDICO_ID=? ");
		
		try
		{
			
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			
			ResultSet rsLocation = null;
	//		ArrayList lstImportados = new ArrayList();
			
			String trxName      = Trx.createTrxName();
		    int iExmeMedicoID   = 0;
		    int adUserID        = 0;
		    int ExmeMedicoID    = 0;
		   // int C_Location_Cons_ID = 0;
		    //int C_Location_ID = 0;
		    boolean nuevoMedico        =  false;
		    boolean nuevoContacto      = false;
			
		    PasswordHandler ph = new PasswordHandler(getCtx());


			while (rs.next()) {
			    iExmeMedicoID   = rs.getInt("I_EXME_Medico_ID");
			    adUserID        = rs.getInt("AD_User_ID");
			    ExmeMedicoID    = rs.getInt("EXME_Medico_ID");
			    nuevoMedico        = ExmeMedicoID == 0;
			    nuevoContacto      = adUserID == 0;
			    
			    try {
			        X_I_EXME_Medico iMedico = null;
			       
			        //el contacto del medico
			        MUser mUser = null;
			        mUser = new MUser(getCtx(), adUserID, trxName);
			        
			        if(nuevoMedico){
			        	
			        	//C_Location_ID=
			        		insertLocation("propio", iExmeMedicoID, getCtx(), trxName, log);
			        	//C_Location_Cons_ID=
			        		insertLocation("consultorio", iExmeMedicoID, getCtx(), trxName, log);
			        	
			        	//ExmeMedicoID = DB.getNextID(m_AD_Client_ID, "EXME_Medico", null);
                        //if (ExmeMedicoID <= 0)
                            //throw new DBException("No Next ID (" + ExmeMedicoID + ")");
                        iMedico = new X_I_EXME_Medico(getCtx(), iExmeMedicoID, trxName);
			        	MEXMEMedico med = new MEXMEMedico(getCtx(), 0, trxName);
			        	med.setValue(iMedico.getValue());
			        	med.setName(iMedico.getName());
			        	med.setDescription(iMedico.getDescription());
			        	med.setApellido1(iMedico.getApellido1());
			        	med.setApellido2(iMedico.getApellido2());
			        	med.setSexo(iMedico.getSexo());
			        	med.setEstaSuspendido(iMedico.isEstaSuspendido());
			        	med.setFechaNac(iMedico.getFechaNac());
			        	med.setCelular(iMedico.getCelular());
			        	med.setEMail(iMedico.getEMail());
			        	med.setEsInterno(iMedico.isEsInterno());
			        	med.setEXME_TipoMedico_ID(iMedico.getEXME_TipoMedico_ID());
			        	med.setRfc(iMedico.getRfc());
			        	med.setCurp(iMedico.getCurp());
			        	med.setEdoCivil(iMedico.getEdoCivil());
			        	med.setC_Location_ID(iMedico.getC_Location_ID());
			        	med.setTelParticular(iMedico.getTelParticular());
			        	med.setRadio(iMedico.getRadio());
			        	med.setFechaIngreso(iMedico.getFechaIngreso());
			        	med.setCedProfesional(iMedico.getCedProfesional());
			        	med.setCodSanidad(iMedico.getCodSanidad());
			        	med.setFechaTitulo(iMedico.getFechaTitulo());
			        	med.setEXME_Universidad_ID(iMedico.getEXME_Universidad_ID());
			        	med.setFechaCertifConsejo(iMedico.getFechaCertifConsejo());
			        	med.setFechaVencimCertif(iMedico.getFechaVencimCertif());
			        	med.setEstaRecertificado(iMedico.isEstaRecertificado());
			        	med.setTieneIncentivo(iMedico.isTieneIncentivo());
			        	med.setC_Location_Cons_ID(iMedico.getC_Location_Cons_ID());
			        	med.setTelConsultorio(iMedico.getTelConsultorio());
			        	med.setNoConsultorio(iMedico.getNoConsultorio());
			        	med.setIntervaloConsulta(iMedico.getIntervaloConsulta());
			        	med.setAD_User_ID(iMedico.getAD_User_ID());
			        	med.setEstaCertifConsejo(iMedico.isEstaCertifConsejo());
			        	med.setNombre_Med(iMedico.getName()+ " " +iMedico.getApellido1()+iMedico.getApellido2()!=null?" "+iMedico.getApellido2():"");
                       // pstmt_insert.setInt(1,ExmeMedicoID);
		        	   //pstmt_insert.setInt(2,iExmeMedicoID);
			        	
			        	if(nuevoContacto){
			        		 if(iMedico.getContactName() == null || iMedico.getContactName().length() <= 0)
				                    mUser.setName(iMedico.getName() + " " + (iMedico.getApellido1() == null ? "" : iMedico.getApellido1()) + (iMedico.getApellido2() == null ? "" : " "+iMedico.getApellido2()));
			        	}else{
			        		 mUser.setName(iMedico.getContactName());
			        	}
			        	 mUser.setPassword(ph.generatePassword());
			                if(!mUser.save(trxName)) {
			                    DB.rollback(false, trxName);
			                    
			                    log.log(Level.WARNING,"Insertando Medico: usuario incorrecto");
			                    sql = new StringBuffer("UPDATE I_EXME_Medico " +
			                    "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
			                    .append(DB.TO_STRING("Insertar Medico: usuario incorrecta"))
			                    .append(" WHERE I_EXME_Medico_ID=").append(iExmeMedicoID);

			                    DB.executeUpdate(sql.toString(), null);
			                    continue;
			                }
			        	
			                if(!med.save()) {
					            if(noInsert > 0)
						            noInsert--;
						        no++;
						        DB.rollback(false, trxName);
						        Trx trx = Trx.get(trxName, false);
						        trx.close();
						        log.log(Level.WARNING,"Insertando Medico: no se inserto el medico");
						        sql = new StringBuffer("UPDATE I_EXME_Medico " +
						                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '")
						                .append("Insertar Medico: no se inserto el medico ")
						                .append("' WHERE I_EXME_Medico_ID=").append(iExmeMedicoID);
						        DB.executeUpdate(sql.toString());
						        continue;
					        }
			                noInsert++;
			        }else{
			        	
			        	pstmt_Location.setInt(1,ExmeMedicoID);
			        	rsLocation=pstmt_Location.executeQuery();
			        	
			        	if(rsLocation.next())
			        	{
			        		//C_Location_ID=
			        			updateLocation("propio", iExmeMedicoID, rsLocation.getInt(1), getCtx(), trxName, log);
			        		
			        		if(rsLocation.getInt(2)>0)
			        		  		//C_Location_Cons_ID=
			        		  			updateLocation("consultorio", iExmeMedicoID,  rsLocation.getInt(2), getCtx(), trxName, log);
			        	
			        	}
			        	iMedico = new X_I_EXME_Medico(getCtx(), iExmeMedicoID, trxName);	
			        	MEXMEMedico med = new MEXMEMedico(getCtx(), ExmeMedicoID, trxName);
			        	med.setValue(iMedico.getValue());
			        	med.setName(iMedico.getName());
			        	med.setDescription(iMedico.getDescription());
			        	med.setApellido1(iMedico.getApellido1());
			        	med.setApellido2(iMedico.getApellido2());
			        	med.setSexo(iMedico.getSexo());
			        	med.setEstaSuspendido(iMedico.isEstaSuspendido());
			        	med.setFechaNac(iMedico.getFechaNac());
			        	med.setCelular(iMedico.getCelular());
			        	med.setEMail(iMedico.getEMail());
			        	med.setEsInterno(iMedico.isEsInterno());
			        	med.setEXME_TipoMedico_ID(iMedico.getEXME_TipoMedico_ID());
			        	med.setRfc(iMedico.getRfc());
			        	med.setCurp(iMedico.getCurp());
			        	med.setEdoCivil(iMedico.getEdoCivil());
			        	med.setC_Location_ID(iMedico.getC_Location_ID());
			        	med.setTelParticular(iMedico.getTelParticular());
			        	med.setRadio(iMedico.getRadio());
			        	med.setFechaIngreso(iMedico.getFechaIngreso());
			        	med.setCedProfesional(iMedico.getCedProfesional());
			        	med.setCodSanidad(iMedico.getCodSanidad());
			        	med.setFechaTitulo(iMedico.getFechaTitulo());
			        	med.setEXME_Universidad_ID(iMedico.getEXME_Universidad_ID());
			        	med.setFechaCertifConsejo(iMedico.getFechaCertifConsejo());
			        	med.setFechaVencimCertif(iMedico.getFechaVencimCertif());
			        	med.setEstaRecertificado(iMedico.isEstaRecertificado());
			        	med.setTieneIncentivo(iMedico.isTieneIncentivo());
			        	med.setC_Location_Cons_ID(iMedico.getC_Location_Cons_ID());
			        	med.setTelConsultorio(iMedico.getTelConsultorio());
			        	med.setNoConsultorio(iMedico.getNoConsultorio());
			        	med.setIntervaloConsulta(iMedico.getIntervaloConsulta());
			        	med.setAD_User_ID(iMedico.getAD_User_ID());
			        	med.setEstaCertifConsejo(iMedico.isEstaCertifConsejo());
			        	med.setNombre_Med(iMedico.getName()+ " " +iMedico.getApellido1()+iMedico.getApellido2()!=null?" "+iMedico.getApellido2():"");
			         	//pstmt_update.setInt(1,iExmeMedicoID);
			         	//pstmt_update.setInt(2,ExmeMedicoID);
			        	
				        	if(nuevoContacto){
				        		 if(iMedico.getContactName() == null || iMedico.getContactName().length() <= 0)
					                    mUser.setName(iMedico.getName() + " " + (iMedico.getApellido1() == null ? "" : iMedico.getApellido1()) + (iMedico.getApellido2() == null ? "" : " "+iMedico.getApellido2()));
				        	}else{
				        		 mUser.setName(iMedico.getContactName());
				        	}
				        	
				        	 mUser.setPassword(iMedico.getName());
				                if(!mUser.save(trxName)) {
				                    DB.rollback(false, trxName);
				                    
				                    log.log(Level.WARNING,"Insertando Medico: usuario incorrecto");
				                    sql = new StringBuffer("UPDATE I_EXME_Medico " +
				                    "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
				                    .append(DB.TO_STRING("Insertar Medico: usuario incorrecta "))
				                    .append("WHERE I_EXME_Medico_ID=").append(iExmeMedicoID);
				                    
				                    DB.executeUpdate(sql.toString());
				                    continue;
				                }
				                
				                if(!med.save(trxName)) {
						            if(noUpdate> 0)
							            noUpdate--;
							        no++;
							        DB.rollback(false, trxName);
							        Trx trx = Trx.get(trxName, false);
							        trx.close();
							        log.log(Level.WARNING,"Insertando Medico: no se inserto el medico");
							        sql = new StringBuffer("UPDATE I_EXME_Medico " +
							                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'")
							                .append("Insertar Medico: no se inserto el medico ")
							                .append("' WHERE I_EXME_Medico_ID=").append(iExmeMedicoID);
							        DB.executeUpdate(sql.toString());
							        continue;
						        }
				                noUpdate++;
			        }
			        
			        //lo marcamos como importado y procesado
			        iMedico.setI_IsImported(true);
			        iMedico.setProcessed(true);
			        
			        if(!iMedico.save(trxName))
				        log.log(Level.WARNING,"No se actualizo el medico importado: " + iMedico);
			        
			        DB.commit(true, trxName);
			        //DB.rollback(true, trxName);
			        Trx trx = Trx.get(trxName, false);
			        trx.close();
			        
			        
			        //lstImportados.add(iMedico);
			    
			    } catch (Exception e){
			        if(noInsert > 0)
			            noInsert--;
			        no++;
			        DB.rollback(false, trxName);
			        Trx trx = Trx.get(trxName, false);
			        trx.close();
			        log.log(Level.WARNING,"Insertando Medico: ", e);
			        sql = new StringBuffer("UPDATE I_EXME_Medico " +
			                "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || " ).append(DB.TO_STRING("Insertar Medico: " + e.toString()))
			                .append(" WHERE I_IsImported<>'E' and I_EXME_Medico_ID=").append(iExmeMedicoID);
			        DB.executeUpdate(sql.toString());
			    }
				
			}	//	for all I_EXME_Medico
			
		}
		catch (SQLException e)
		{
			throw new Exception ("ImportExmeMedico.doIt", e);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_EXME_Medico "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_Medico_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_Medico_ID@: @Updated@");
		return "";
	}	//	doIt
	
	
	 /**
     * Metodo para crear los locations del Medico (vgarcia)
     * @param i_Exme_Medico_ID
     * @param ctx
     * @param trxName
     * @param log
     * @return
     */
    public static int insertLocation(String tipoLocation, long i_Exme_Medico_ID, Properties ctx, String trxName, CLogger log){
    	
    	 //Para crear la locacion del medico
        PreparedStatement pstmt1 = null;
        ResultSet rsl = null;
        StringBuffer sql = null;
        int no=0;
        
        try{
        	
        	if(tipoLocation.equals("consultorio")){
                		
        		 sql = new StringBuffer("select i_exme_medico_id, ct.c_country_id, r.c_region_id, " +
                		  "m.address1cons, m.address2cons, m.citycons, m.postalcons  "+
                		 "from i_exme_medico m " +
                		 "inner join c_country  ct on (trim(ct.countrycode)=trim(m.countrycodecons)) " +
                		 "inner join c_region r on(trim(r.name)=trim(m.regionnamecons)) " +
                		 "where m.I_EXME_MEDICO_ID = ? and m.i_isimported<>'Y' ") ;
        		 
        		
        	}else{
        		  
        		 sql = new StringBuffer("select i_exme_medico_id, ct.c_country_id, r.c_region_id, " +        		 
        		 "m.address1, m.address2, m.city, m.postal  "+
        		 "from i_exme_medico m " +
        		 "inner join c_country  ct on (trim(ct.countrycode)=trim(m.countrycode)) " +
        		 "inner join c_region r on(trim(r.name)=trim(m.regionname)) " +
        		 "where m.I_EXME_MEDICO_ID = ? and m.i_isimported<>'Y' ") ;
        	}
        	
        	
            pstmt1 = DB.prepareStatement(sql.toString(), null);
            pstmt1.setLong(1, i_Exme_Medico_ID);
            
            rsl = pstmt1.executeQuery();
            MLocation location = null;

            while (rsl.next()){

                location = new MLocation(MCountry.get(ctx, rsl.getInt(2)),  MRegion.get(ctx,rsl.getInt(3)));

                location.setAddress1(rsl.getString(4));
                location.setAddress2(rsl.getString(5));
                location.setCity(rsl.getString(6));
                location.setPostal(rsl.getString(7));
                
                location.save(trxName);
                
            	if(tipoLocation.equals("consultorio")){
                  	 sql = new StringBuffer ("UPDATE I_EXME_Medico  "
                            + "SET C_Location_Cons_ID=").append(location.getC_Location_ID()).
                            append(" WHERE C_Location_Cons_ID IS NULL"
                                    + " AND I_IsImported<>'Y' and i_exme_medico_id=").append(i_Exme_Medico_ID);
                  	} else{
                  	 sql = new StringBuffer ("UPDATE I_EXME_Medico "
                            + "SET C_Location_ID=").append(location.getC_Location_ID()).
                            append(" WHERE C_Location_ID IS NULL"
                                    + " AND I_IsImported<>'Y' and i_exme_medico_id=").append(i_Exme_Medico_ID);
                  	}
            	s_log.log(Level.FINEST,sql.toString());
                //System.out.println(sql.toString());
                no = DB.executeUpdate(sql.toString(),trxName);

                log.fine("Set C_Location_ID =" + no);

            }

            DB.commit(false,trxName);

            
        
        }
        catch(SQLException e){
        	
        	if(tipoLocation.equals("consultorio")){
        		sql = new StringBuffer ("UPDATE I_EXME_Medico  "
                        + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid C_Location_Cons_ID'  "
                        + "WHERE C_Location_Cons_ID IS NULL"
                        + " AND I_IsImported<>'Y' and I_AD_Org_ID=").append(i_Exme_Medico_ID);

                    no = DB.executeUpdate(sql.toString());
                    log.config("Invalid C_Location_ID =" + no);
        	}else{
        		sql = new StringBuffer ("UPDATE I_EXME_Medico "
                        + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location_ID'  "
                        + "WHERE C_Location_ID IS NULL"
                        + " AND I_IsImported<>'Y' and I_AD_Org_ID=").append(i_Exme_Medico_ID);

                    no = DB.executeUpdate(sql.toString(),trxName);
                    log.config("Invalid C_Location_Cons_ID =" + no);
        	}
        	
        	
       

        }finally{
        	try {
        		if(pstmt1!=null)
				     pstmt1.close();
        		
        	  if(rsl!=null)
			    	rsl.close();
				
				pstmt1=null;
				rsl=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return 0;
        
    }

    /**
     * Crea los location de los consultorios (vgarcia)
     * @param tipoLocation
     * @param i_Exme_Medico_ID
     * @param c_Location_ID
     * @param ctx
     * @param trxName
     * @param log
     * @return
     */
    public static int updateLocation(String tipoLocation, int i_Exme_Medico_ID, int c_Location_ID, Properties ctx, String trxName, CLogger log){
    	
   	 //Para crear la locacion del consultorio
       PreparedStatement pstmt1 = null;
       ResultSet rsl = null;
       StringBuffer sql = null;
       int no=0;
       
       try{
       	
    		
       	if(tipoLocation.equals("consultorio")){
               		
       		 sql = new StringBuffer("select i_exme_medico_id, ct.c_country_id, r.c_region_id, " +
               		  "m.address1cons, m.address2cons, m.citycons, m.postalcons  "+
               		 "from i_exme_medico m " +
               		 "inner join c_country  ct on (trim(ct.countrycode)=trim(m.countrycodecons)) " +
               		 "inner join c_region r on(trim(r.name)=trim(m.regionnamecons)) " +
               		 "where m.I_EXME_MEDICO_ID = ? and m.i_isimported<>'Y' ") ;
       		 
       		
       	}else{
       		  
       		 sql = new StringBuffer("select i_exme_medico_id, ct.c_country_id, r.c_region_id, " +        		 
       		 "m.address1, m.address2, m.city, m.postal  "+
       		 "from i_exme_medico m " +
       		 "inner join c_country  ct on (trim(ct.countrycode)=trim(m.countrycode)) " +
       		 "inner join c_region r on(trim(r.name)=trim(m.regionname)) " +
       		 "where m.I_EXME_MEDICO_ID = ? and m.i_isimported<>'Y' ") ;
       	}
       	
           pstmt1 = DB.prepareStatement(sql.toString(), null);
           pstmt1.setInt(1, i_Exme_Medico_ID);
           
           rsl = pstmt1.executeQuery();
           MLocation location = null;

           while (rsl.next()){

               location = MLocation.get(ctx, c_Location_ID, trxName); 

               location.setCountry(MCountry.get(ctx, rsl.getInt(2)));
               location.setRegion(MRegion.get(ctx,rsl.getInt(3)));
               location.setAddress1(rsl.getString(4));
               location.setAddress2(rsl.getString(5));
               location.setCity(rsl.getString(6));
               location.setPostal(rsl.getString(7));
               
               location.save(trxName);
               
           	if(tipoLocation.equals("consultorio")){
           	 sql = new StringBuffer ("UPDATE I_EXME_Medico "
                     + "SET C_Location_Cons_ID=").append(c_Location_ID).
                     append(" WHERE C_Location_Cons_ID IS NULL"
                             + " AND I_IsImported<>'Y' and i_exme_medico_id=").append(i_Exme_Medico_ID);
           	} else{
           	 sql = new StringBuffer ("UPDATE I_EXME_Medico "
                     + "SET C_Location_ID=").append(c_Location_ID).
                     append(" WHERE C_Location_ID IS NULL"
                             + " AND I_IsImported<>'Y' and i_exme_medico_id=").append(i_Exme_Medico_ID);
           	}
           	   s_log.log(Level.FINEST,sql.toString());
               //System.out.println(sql.toString());
               no = DB.executeUpdate(sql.toString(),trxName);

               log.fine("Set C_Location_ID =" + no);

           }

           DB.commit(false,trxName);

    
           
//           return location.getC_Location_ID();
       }
       catch(SQLException e){
    	   
    	   if(tipoLocation.equals("consultorio")){
       		sql = new StringBuffer ("UPDATE I_EXME_Medico "
                       + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location_Cons ' "
                       + "WHERE C_Location_Cons_ID IS NULL"
                       + " AND I_IsImported<>'Y'");

                   no = DB.executeUpdate(sql.toString());
                   log.config("Invalid C_Location_Cons_ID =" + no);
       	}else{
       		sql = new StringBuffer ("UPDATE I_EXME_Medico "
                       + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location_ID ' "
                       + "WHERE C_Location_ID IS NULL"
                       + " AND I_IsImported<>'Y'");

                   no = DB.executeUpdate(sql.toString(),trxName);
                   log.config("Invalid C_Location_ID =" + no);
       	}
    	   
         

       }finally{
       	try {
       		   if(pstmt1!=null)
			     pstmt1.close();
   		
        	  if(rsl!=null)
		    	rsl.close();
			
			pstmt1=null;
			rsl=null;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
       }
       return 0;
   }

    
}	//	ImportMedico
