package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCountry;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
import org.compiere.model.X_I_EXME_Paciente;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Proceso de importaci&oacute;n de la tabla de Paciente.<p>
 * Creado: 02/jun/2006<p>
 * Modificado: $Date: 2006/10/19 21:10:50 $<p>
 * Por: $Author: vgarcia $<p>
 * 
 * @author Ivan Vargas
 * @version $Revision: 1.2 $
 */

public class ImportPaciente extends SvrProcess {
    

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
    public ImportPaciente() {
        super();
    }


    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {

    	ProcessInfoParameter[] para = getParameter();

        m_AD_Client_ID = Env.getAD_Client_ID(getCtx());

		for (int i = 0; i < para.length; i++) {

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

		//	Delete Old Imported
		if (m_deleteOldImported){
			sql = new StringBuffer ("DELETE I_EXME_Paciente "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_Paciente "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE (I_IsImported<>'Y' OR I_IsImported IS NULL)");
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Reset=" + no);

        //  Set tipo_de_paciente
        sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET EXME_TipoPaciente_ID=(SELECT EXME_TipoPaciente_ID FROM  EXME_TipoPaciente tp"
                    + " WHERE UPPER(TRIM(pac.EXME_TipoPaciente_Value)) = UPPER(TRIM(tp.Name)) AND tp.AD_Client_ID=pac.AD_Client_ID) "
                    + "WHERE EXME_TipoPaciente_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set tipo_de_Paciente =" + no);

        //  Set Religion, verificar si es correcto comparar el value con el name
        sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET EXME_Religion_ID=(SELECT EXME_Religion_ID FROM  EXME_Religion rel"
                    + " WHERE upper(trim(pac.EXME_Religion_Value)) = upper(trim(rel.Name)) AND rel.AD_Client_ID=pac.AD_Client_ID) "
                    + "WHERE EXME_Religion_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set Religion =" + no);
        
        //  Set Ocupacion
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET EXME_Ocupacion_ID=(SELECT EXME_Ocupacion_ID FROM  EXME_Ocupacion oc"
                    + " WHERE trim(pac.EXME_Ocupacion_Value) = trim(oc.Name) AND oc.AD_Client_ID=pac.AD_Client_ID "); 
                    
            if (DB.isOracle()) {
            	sql.append(" AND ROWNUM=1) ");
            } else if (DB.isPostgreSQL()) {
            	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
            	sql.append(") ");
            }
            
            sql.append("WHERE EXME_Ocupacion_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set Ocupacion =" + no);
        }catch(Exception e){
        	log.config("Invalid Employ Type =" + no);
        }
        
        //  Set C_Bpartner
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET C_BPartner_ID=(SELECT C_BPartner_ID FROM  C_BPartner bp"
                    + " WHERE trim(pac.C_BPARTNER_Value) = trim(bp.Value) AND bp.AD_Client_ID=pac.AD_Client_ID) "
                    + "WHERE C_BPartner_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set C_Bpartner =" + no);

        }catch(Exception e){
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Bpartner, ' "
                    + "WHERE C_Bpartner_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.config("Invalid C_Bpartner Type =" + no);
        }

        //  Set C_Bpartner_seg_id
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET C_BPartner_Seg_ID=(SELECT C_BPartner_ID FROM  C_BPartner bp"
                    + " WHERE trim(pac.C_BPartner_Seg_Value) = trim(bp.value) AND bp.AD_Client_ID=pac.AD_Client_ID) "
                    + "WHERE C_BPartner_Seg_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set C_Bpartner_Seg =" + no);
        }catch(Exception e){
        	log.config("Invalid C_Bpartner Type =" + no);
        }

        //  Set Parentesco
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET EXME_Parentesco_ID=(SELECT EXME_Parentesco_ID FROM  EXME_Parentesco par"
                    + " WHERE trim(pac.EXME_Parentesco_Value) = trim(par.NAME) AND par.AD_Client_ID=pac.AD_Client_ID) "
                    + "WHERE EXME_Parentesco_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set EXME_Parentesco_ID =" + no);
        }catch(Exception e){
        	log.config("Invalid Parentesco =" + no);
        }

        //  Set Ocupacion_Fam
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET EXME_Ocupacion_Fam_ID=(SELECT EXME_Ocupacion_ID FROM  EXME_Ocupacion ocu"
                    + " WHERE trim(pac.EXME_Ocupacion_Fam_Value) = trim(ocu.NAME) AND ocu.AD_Client_ID=pac.AD_Client_ID "); 

            if (DB.isOracle()) {
            	sql.append(" AND ROWNUM=1) ");
            } else if (DB.isPostgreSQL()) {
            	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
            	sql.append(") ");
            }
            
            sql.append("WHERE EXME_Ocupacion_Fam_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set EXME_Parentesco_ID =" + no);
        }catch(Exception e){
        	log.config("Invalid Parentesco =" + no);
        }

        // Para buscar el ID del paciente para los registros que solo se deben actualizar (vgarcia)        
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac SET pac.EXME_Paciente_ID=" +
            		"(SELECT EXME_Paciente_ID FROM  EXME_Paciente p " +
            		" WHERE pac.Value = p.Value ) " 
                    + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
                    + ") "
            		+ " WHERE EXME_Paciente_ID IS NULL  AND I_IsImported<>'Y'");
                no = DB.executeUpdate(sql.toString(), null);
                
                log.fine("Set EXME_Paciente_ID =" + no);
        } catch(Exception e) {
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid EXME_Paciente_ID, ' "
                    + "WHERE EXME_Paciente_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);

            no = DB.executeUpdate(sql.toString(), null);
            log.config("Invalid EXME_Paciente_ID =" + no);
        }

        //Set C_BPartner_location_ID
        //lhernandez
       /* try{

            sql = new StringBuffer ("UPDATE I_EXME_Paciente ip "
                    + "SET ip.C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID FROM  C_BPartner_Location cbpl"
                    + " WHERE ip.C_BPartner_ID = cbpl.C_BPartner_ID  AND cbpl.AD_Client_ID=ip.AD_Client_ID and rownum=1)  "
                    + "WHERE ip.C_BPartner_Location_ID IS NULL"
                    + " AND ip.I_IsImported<>'Y'").append(clientCheck);

                no = DB.executeUpdate(sql.toString(), null);

                log.fine("Set C_BPartner_Location_ID =" + no);


        } catch(Exception e){

            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "

                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_BPartner_Location, ' "

                    + "WHERE C_BPartner_Location_ID IS NULL"

                    + " AND I_IsImported<>'Y'").append(clientCheck);

                no = DB.executeUpdate(sql.toString(), null);

                log.config("Invalid C_BPartner_Location =" + no);

        }*/ //lhernandez

        //Set C_COUNTRY_PERSRESP_ID (vgarcia)
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
            		+ "SET C_COUNTRY_PERSRESP_ID=(SELECT C_Country_ID FROM  C_Country ct"
                    + " WHERE TRIM(pac.Pais_PersResp) = TRIM(ct.NAME) AND ct.AD_Client_ID in(pac.AD_Client_ID,0)) "
                    + "WHERE C_COUNTRY_PERSRESP_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set C_COUNTRY_PERSRESP_ID =" + no);
        }catch(Exception e){
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_COUNTRY_PERSRESP_ID, ' "
                    + "WHERE C_COUNTRY_PERSRESP_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.config("Invalid C_COUNTRY_PERSRESP_ID =" + no);
        }
        
        //Set C_REGION_PERSRESP_ID (vgarcia)
        try{
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
            		+ "SET C_REGION_PERSRESP_ID=(SELECT C_Region_ID FROM  C_Region reg"
                    + " WHERE TRIM(pac.Region_PersResp) = TRIM(reg.Description) AND reg.AD_Client_ID in (pac.AD_Client_ID,0)) "
                    + "WHERE C_REGION_PERSRESP_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Set C_REGION_PERSRESP_ID =" + no);
        }catch(Exception e){
            sql = new StringBuffer ("UPDATE I_EXME_Paciente pac "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_REGION_PERSRESP_ID, ' "
                    + "WHERE C_REGION_PERSRESP_ID IS NULL"
                    + " AND I_IsImported<>'Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.config("Invalid C_REGION_PERSRESP_ID =" + no);
        }
        
        // Set EXME_Institucion_ID
        sql = new StringBuffer ("UPDATE I_EXME_Paciente pac SET pac.EXME_Institucion_ID=(SELECT " +
            		"inst.EXME_Institucion_ID FROM  EXME_institucion inst " +
            		"WHERE TRIM(pac.Institucion_value) = TRIM(inst.value) AND inst.AD_Client_ID=pac.AD_Client_ID) " +
            		"WHERE pac.EXME_Institucion_ID IS NULL AND pac.I_IsImported<>'Y'").append(clientCheck);
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Institucion_ID =" + no);

        
        //Noelia: Set Unidad, Grupo, Grado, Arma
		sql = new StringBuffer ("UPDATE I_EXME_Paciente pac SET ")
					.append("pac.EXME_Unidad_ID = (SELECT EXME_Unidad_ID u FROM EXME_Unidad u WHERE u.value=pac.unidad AND u.AD_Client_ID = pac.AD_Client_ID), ")
					.append("pac.EXME_Grupo_Especialidad_ID = (SELECT EXME_Grupo_Especialidad_ID ge FROM EXME_Grupo_Especialidad ge WHERE ge.value=pac.groupesp AND ge.AD_Client_ID = pac.AD_Client_ID), ")
					.append("pac.EXME_Grado_ID = (SELECT EXME_Grado_ID g FROM EXME_Grado g WHERE g.value=pac.degree AND g.AD_Client_ID = pac.AD_Client_ID), ")
					.append("pac.EXME_Arma_ID = (SELECT EXME_Arma_ID a FROM EXME_Arma a WHERE a.value=pac.arma AND a.AD_Client_ID = pac.AD_Client_ID) ")
					.append("WHERE pac.I_IsImported<>'Y'").append(clientCheck);
			
		no = DB.executeUpdate(sql.toString(), null);
		log.info("Reset=" + no);
		
        //-------------------------------------------------------------------

		int noInsert = 0;
		int noUpdate = 0;
		
		//	Go through Records
		log.fine("start inserting/updating ...");
		
		sql = new StringBuffer ("SELECT I_EXME_Paciente_ID, EXME_Paciente_ID "
			+ "FROM I_EXME_Paciente WHERE I_IsImported='N'").append(clientCheck).append(" order by I_EXME_Paciente_ID  asc");

		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

        PreparedStatement pstmt = null;
        ResultSet rs = null; 

        //    Set Imported = Y
        PreparedStatement pstmt_setImported = conn.prepareStatement
            ("UPDATE I_EXME_Paciente SET I_IsImported='Y', EXME_Paciente_ID=?, "
            + "Updated=SysDate, Processed='Y' WHERE I_EXME_Paciente_ID=?");

		try{

			//Obtenemos todos los pacientes que no han sido importados
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			
			int I_EXME_Paciente_ID = 0;
			int EXME_Paciente_ID = 0;
			boolean newPaciente = false;
			
			//Para obtener el pais y la region del lugar de nacimiento (vgarcia)
			String sqlNac ="select c.C_COUNTRY_ID, r.C_REGION_ID from " +
					"C_Country c, C_Region r, I_EXME_PACIENTE i " +
					"where upper(trim(c.NAME)) = upper(trim(i.PAIS_NAC)) and upper(trim(r.NAME))=upper(trim(i.REGION_NAC)) and i.I_EXME_PACIENTE_ID=?";
			PreparedStatement psNac = DB.prepareStatement(sqlNac,null);
			ResultSet rsNac = null;

			while (rs.next()) {
				
				I_EXME_Paciente_ID = rs.getInt(1);
				EXME_Paciente_ID = rs.getInt(2);
				newPaciente = (EXME_Paciente_ID == 0);
				log.fine("I_EXME_Paciente_ID=" + I_EXME_Paciente_ID + ", EXME_Paciente_ID=" + EXME_Paciente_ID);
				
				//Para obtener el pais y la region del lugar de nacimiento (vgarcia)
				psNac.setInt(1, I_EXME_Paciente_ID);
				rsNac = psNac.executeQuery();
				
				X_I_EXME_Paciente iPaciente = new X_I_EXME_Paciente(getCtx(), I_EXME_Paciente_ID, null);
				MEXMEPaciente pac = null;
				
				//	Paciente
				if (newPaciente) {	//	Insert new Paciente
					try {
						// primero creamos el C_Location
						iPaciente.setC_Location_ID(insertLocation("domicilio",  I_EXME_Paciente_ID, getCtx(),  get_TrxName(),  log));
						
						//--Julio Gutierrez Inserta la localidad de Registro del paciente
						iPaciente.setC_LocationReg_ID(insertLocation("registro",  I_EXME_Paciente_ID, getCtx(),  get_TrxName(),  log));
						
						pac = new MEXMEPaciente(getCtx(), 0, get_TrxName());						
						pac = copyFromIEXMEPaciente(pac, iPaciente, newPaciente);
						
						if(rsNac.next()){
							pac.setC_Country_Nac_ID(rsNac.getInt(1));
							pac.setC_Region_Nac_ID(rsNac.getInt(2));
						}
						
						if(!pac.save(get_TrxName())){
							sql = new StringBuffer ("UPDATE I_EXME_Paciente Pac "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
									.append(DB.TO_STRING("Insert Paciente: No fue posible importar el registro."))
									.append("WHERE I_EXME_Paciente_ID=").append(I_EXME_Paciente_ID);
							DB.executeUpdate(sql.toString(), null);
						}else{
							noInsert++;
						}
						
					} catch (Exception ex){
						log.warning("Insert Paciente  - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_Paciente Pac "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insert Paciente: " + ex.toString()))
							.append("WHERE I_EXME_Paciente_ID=").append(I_EXME_Paciente_ID);
						DB.executeUpdate(sql.toString(), null);
						continue;
					}
				}else {
					try	{
						//Update Paciente
						pac = new MEXMEPaciente(getCtx(), EXME_Paciente_ID, null);
						
						//Actulizamos  los Location
						iPaciente.setC_Location_ID(updateLocation("domicilio",  I_EXME_Paciente_ID, pac.getC_Location_ID(), getCtx(),  get_TrxName(),  log));
						
						//--Julio Gutierrez actualiza la localidad de Registro del paciente
						iPaciente.setC_LocationReg_ID(updateLocation("registro",  I_EXME_Paciente_ID, pac.getC_Location_ID(), getCtx(),  get_TrxName(),  log));
						
						pac = copyFromIEXMEPaciente(pac, iPaciente, newPaciente);
						
						if(rsNac.next()){
							pac.setC_Country_Nac_ID(rsNac.getInt(1));
							pac.setC_Region_Nac_ID(rsNac.getInt(2));
						}
						
						if(!pac.save(get_TrxName())){
							sql = new StringBuffer ("UPDATE I_EXME_Paciente Pac "
									+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
									.append(DB.TO_STRING("Update Paciente: No fue posible actualizar el registro."))
									.append("WHERE I_EXME_Paciente_ID=").append(I_EXME_Paciente_ID);
							DB.executeUpdate(sql.toString(), null);
						}else{
							noUpdate++;
						}
					
					}catch (Exception ex){
						log.warning("Update Paciente - " + ex.toString());
						sql = new StringBuffer ("UPDATE I_EXME_Paciente Pac "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Paciente: " + ex.toString()))
							.append("WHERE I_EXME_Paciente_ID=").append(I_EXME_Paciente_ID);
						DB.executeUpdate(sql.toString(), null);
						continue;
					}

				}

				//	Update I_EXME_Paciente
				pstmt_setImported.setInt(1, EXME_Paciente_ID);
				pstmt_setImported.setInt(2, I_EXME_Paciente_ID);
				no = pstmt_setImported.executeUpdate();
				conn.commit();

			}
			rs.close();
			pstmt.close();
            rsNac.close();
            rsNac=null;
            psNac.close();
            psNac=null;

			pstmt_setImported.close();
			
			conn.close();
			conn = null;
		}catch (SQLException e)	{
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
			catch (SQLException ex){
				log.log(Level.SEVERE, "doIt", ex.getMessage());
			}
			log.log(Level.SEVERE, "doIt", e);
            rs=null;
            pstmt=null;
			throw new Exception ("doIt", e);
		} finally {
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
		sql = new StringBuffer ("UPDATE I_EXME_Paciente "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);

		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_Paciente_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_Paciente_ID@: @Updated@");

		return "";
    }

    
    /**
     * Metodo para crear los locations de el Paciente
     * @param i_Exme_Paciente_ID
     * @param ctx
     * @param trxName
     * @param log
     * @return
     */
    public static int insertLocation(String tipoLocation,
	    long i_Exme_Paciente_ID, Properties ctx, String trxName, CLogger log) {

	// Para crear la locacion del paciente
	PreparedStatement pstmt1 = null;
	ResultSet rsl = null;
	StringBuffer sql = null;
	int no = 0;
    //Se modifica el query para crear location del paciente
	try {
	    if (tipoLocation.equals("domicilio")) {
	    	sql = new StringBuffer("select a.i_exme_paciente_id, " 
	    			+ "b.c_country_id, "
	    			+ "c.c_region_id, "
	    			+ "a.callenumero,"
	    			+ "a.colonia, "
	    			+ "a.ciudad, a.cp,tw.exme_towncouncil_id "
	    			+ "from i_exme_paciente a "
 	    			+ "inner join c_country b on (upper(trim(a.pais))= upper(trim(b.name))) "
	    			+ "left join c_region c on (upper(trim(a.region))=upper(trim(c.name))) and b.c_country_id = c.c_country_id " 
	    			+ "left join exme_towncouncil tw  on (upper(trim(tw.name))=upper(trim(a.exme_towncouncil_name))) and tw.c_region_id = c.c_region_id "
	    			+ "where a.i_exme_paciente_id = ? "); 
 
	    } else if (tipoLocation.equals("registro")) {
	    	sql = new StringBuffer( "select a.i_exme_paciente_id, b.c_country_id, c.c_region_id, a.Adress1Reg, a.Adress2Reg, "
				+ "a.CITYREG, a.POSTALREG,tw.exme_towncouncil_id, a.NumExtReg, a.NumInReg "
				+ "from i_exme_paciente a, c_country b, c_region c, exme_towncouncil tw "
				+ "where a.i_exme_paciente_id = ? and a.i_isimported<>'Y' "
				+ "and upper(trim(a.CountryReg)) = upper(trim(b.name)) and upper(trim(a.RegionReg))=upper(trim(c.name)) "
				+ "and c.c_country_id = b.c_country_id");

	    } else {
	    	sql = new StringBuffer("select a.i_exme_paciente_id, b.c_country_id, c.c_region_id, a.callenumero_nac, a.colonia_nac, "
				+ "a.ciudad_nac, a.cp_nac,tw.exme_towncouncil_id  "
				+ "from i_exme_paciente a, c_country b, c_region c "
				+ "where a.i_exme_paciente_id = ? and a.i_isimported<>'Y' "
				+ "and a.pais_nac = b.name and a.region_nac=c.name" 
				+ "and c.c_country_id = b.c_country_id");
	    }

	    pstmt1 = DB.prepareStatement(sql.toString(), null);
	    pstmt1.setLong(1, i_Exme_Paciente_ID);

	    rsl = pstmt1.executeQuery();
	    MLocation location = null;

	    if (rsl.next()) {
	    	location = new MLocation(ctx, 0, trxName);
	    	location.setC_Country_ID(rsl.getInt(2));
	    	location.setC_Region_ID(rsl.getInt(3));
	    	location.setAddress1(rsl.getString(4));
	    	location.setAddress2(rsl.getString(5));
	    	location.setCity(rsl.getString(6));
	    	location.setPostal(rsl.getString(7));
	    	location.setEXME_TownCouncil_ID(rsl.getInt(8));

	    	if (tipoLocation.equals("registro")) {
	    		location.setNumExt(rsl.getString(9));
	    		location.setNumIn(rsl.getString(10));
	    	}

	    	location.save(trxName);
	    }

	    DB.commit(false, trxName);

	    pstmt1.close();
	    rsl.close();

	    if (location != null)
	    	return location.getC_Location_ID();
	    else
	    	return 0;
	} catch (SQLException e) {

	    if (tipoLocation.equals("domicilio")) {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location, ' "
				+ "WHERE C_Location_ID IS NULL"
				+ " AND I_IsImported<>'Y'");
	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_Location_ID =" + no);
	    } else if (tipoLocation.equals("registro")) {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_LOCATIONREG_ID, ' "
				+ "WHERE C_LOCATIONREG_ID IS NinsertLocation(iULL"
				+ " AND I_IsImported<>'Y'");

	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_Location_Nac_ID =" + no);
	    } else {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location_Nac_ID, ' "
				+ "WHERE C_Location_Nac_ID IS NinsertLocation(iULL"
				+ " AND I_IsImported<>'Y'");

	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_Location_Nac_ID =" + no);
	    }
	    
	    try {
	    	if (rsl != null)
	    		rsl.close();
	    	if (pstmt1 != null)
	    		pstmt1.close();
	    } catch (SQLException ex) {
	    	log.config("Exception =" + ex.getMessage());
	    }
	    
	    log.log(Level.SEVERE, "doIt", e);
	    rsl = null;
	    pstmt1 = null;
	    return 0;

	} finally {
	    try {
	    	if (rsl != null)
	    		rsl.close();
	    	if (pstmt1 != null)
	    		pstmt1.close();
	    } catch (SQLException e) {
	    	log.log(Level.SEVERE, e.getMessage());
	    }
	    rsl = null;
	    pstmt1 = null;
	}

    }

    
    public static int updateLocation(String tipoLocation,
	    int i_Exme_Paciente_ID, int c_Location_ID, Properties ctx,
	    String trxName, CLogger log) {

	// Para crear la locacion del paciente
	PreparedStatement pstmt1 = null;
	ResultSet rsl = null;
	StringBuffer sql = null;
	int no = 0;

	try {
	    if (tipoLocation.equals("domicilio")) {
	    	sql = new StringBuffer("select a.i_exme_paciente_id, b.c_country_id, c.c_region_id, a.callenumero, a.colonia, "
				+ "a.ciudad, a.cp,tw.exme_towncouncil_id  "
				+ "from i_exme_paciente a, c_country b, c_region c , exme_towncouncil tw "
				+ "where a.i_exme_paciente_id = ? and a.i_isimported<>'Y' "
				+ "and upper(trim(a.pais)) = upper(trim(b.name)) and upper(trim(a.region))=upper(trim(c.name)) and upper(trim(tw.name))=upper(trim(a.exme_towncouncil_name))");

	    } else if (tipoLocation.equals("registro")) {
	    	sql = new StringBuffer(	"select a.i_exme_paciente_id, b.c_country_id, c.c_region_id, a.ADRESS1REG, a.ADRESS2REG, "
				+ "a.CITYREG, a.POSTALREG,tw.exme_towncouncil_id, a.NUMEXTREG, a.NUMINREG "
				+ "from i_exme_paciente a, c_country b, c_region c, exme_towncouncil tw "
				+ "where a.i_exme_paciente_id = ? and a.i_isimported<>'Y' "
				+ "and a.COUNTRYREG = b.name and a.REGIONREG=c.name");

	    } else {
	    	sql = new StringBuffer("select a.i_exme_paciente_id, b.c_country_id, c.c_region_id, a.callenumero_nac, a.colonia_nac, "
				+ "a.ciudad_nac, a.cp_nac,tw.exme_towncouncil_id  "
				+ "from i_exme_paciente a, c_country b, c_region c "
				+ "where a.i_exme_paciente_id = ? and a.i_isimported<>'Y' "
				+ "and a.pais_nac = b.name and a.region_nac=c.name");
	    }

	    pstmt1 = DB.prepareStatement(sql.toString(), null);
	    pstmt1.setInt(1, i_Exme_Paciente_ID);

	    rsl = pstmt1.executeQuery();
	    MLocation location = null;

	    while (rsl.next()) {

			location = MLocation.get(ctx, c_Location_ID, trxName);

			location.setCountry(MCountry.get(ctx, rsl.getInt(2)));
			location.setRegion(MRegion.get(ctx, rsl.getInt(3)));
			location.setAddress1(rsl.getString(4));
			location.setAddress2(rsl.getString(5));
			location.setCity(rsl.getString(6));
			location.setPostal(rsl.getString(7));
			location.setEXME_TownCouncil_ID(rsl.getInt(8));

			if (tipoLocation.equals("registro")) {
			    location.setNumExt(rsl.getString(9));
			    location.setNumIn(rsl.getString(10));
			}

			location.save(trxName);
	    }
	 
	    DB.commit(true, trxName);
	    pstmt1.close();
	    rsl.close();

	    if (location != null)
	    	return location.getC_Location_ID();
	    else
	    	return c_Location_ID;
	    
	} catch (SQLException e) {

	    if (tipoLocation.equals("domicilio")) {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location, ' "
				+ "WHERE C_Location_ID IS NULL"
				+ " AND I_IsImported<>'Y'");

	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_Location_ID =" + no);

	    } else if (tipoLocation.equals("registro")) {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_LOCATIONREG_ID, ' "
				+ "WHERE C_LOCATIONREG_ID IS NULL"
				+ " AND I_IsImported<>'Y'");

	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_LOCATIONREG_ID =" + no);

	    } else {
	    	sql = new StringBuffer("UPDATE I_EXME_Paciente pac "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid C_Location_Nac_ID, ' "
				+ "WHERE C_Location_Nac_ID IS NULL"
				+ " AND I_IsImported<>'Y'");

	    	no = DB.executeUpdate(sql.toString(), null);
	    	log.config("Invalid C_Location_Nac_ID =" + no);
	    }
	    
	    try {
			if (rsl != null)
			    rsl.close();
			if (pstmt1 != null)
			    pstmt1.close();
	    } catch (SQLException ex) {
	    	log.log(Level.SEVERE, ex.getMessage());
	    }
	    log.log(Level.SEVERE, "doIt", e);
	    rsl = null;
	    pstmt1 = null;
	    
	    return 0;
	} finally {
	    try {
			if (rsl != null)
			    rsl.close();
			if (pstmt1 != null)
			    pstmt1.close();
	    } catch (SQLException e) {
	    	log.log(Level.SEVERE, e.getMessage());
	    }
	    rsl = null;
	    pstmt1 = null;
	}

    }
    /**
     * Metodo que vacia los datos de la importacion al paciente que se crea o actualiza.
     * Si lo que se va a realizar es una actualizacion, solo se realiza cuando no se elimine informacion ya existente.
     * @author Noelia
     * @param MEXMEPaciente pac: a actualizar
     * @param X_I_EXME_Paciente iPaciente: de donde se obtienen los datos.
     * @param isNew, indica si el MEXMEPaciente es nuevo es una actualizacion
     * @return MEXMEPaciente pac: ya actualizado
     */
    public MEXMEPaciente copyFromIEXMEPaciente(MEXMEPaciente pac, X_I_EXME_Paciente iPaciente, boolean isNew){
    	
    	//Datos del Paciente obligatorios
		if(isNew || (!isNew && iPaciente.getName()!=null && iPaciente.getName().trim()!=""))
			pac.setName(iPaciente.getName());
		if(isNew || (!isNew && iPaciente.getNombre2()!=null && iPaciente.getNombre2().trim()!=""))
			pac.setNombre2(iPaciente.getNombre2());
		if(isNew || (!isNew && iPaciente.getApellido1()!=null && iPaciente.getApellido1().trim()!=""))
			pac.setApellido1(iPaciente.getApellido1());
		if(isNew || (!isNew && iPaciente.getApellido2()!=null && iPaciente.getApellido2().trim()!=""))
			pac.setApellido2(iPaciente.getApellido2());
		pac.setSexo(iPaciente.getSexo());
		if(isNew || (!isNew && iPaciente.getRfc()!=null && iPaciente.getRfc().trim()!=""))
			pac.setRfc(iPaciente.getRfc());		
		pac.setEdoCivil(iPaciente.getEdoCivil());
		if(isNew || (!isNew && iPaciente.getFechaNac()!=null))
			pac.setFechaNac(iPaciente.getFechaNac());
		if(isNew)
			pac.setC_Location_ID(iPaciente.getC_Location_ID());
		if(isNew || (!isNew && iPaciente.getEXME_PatientClass_ID()>0))
			pac.setEXME_PatientClass_ID(iPaciente.getEXME_PatientClass_ID());	
		if(isNew || (!isNew && iPaciente.getDirPersResp()!=null && iPaciente.getDirPersResp().trim()!=""))
			pac.setDirPersResp(iPaciente.getDirPersResp());
		
		pac.setC_BPartner_ID(iPaciente.getC_BPartner_ID());
		
		//Datos del Paciente No obligatorios
		if(isNew || (!isNew && iPaciente.getApellido3()!=null && iPaciente.getApellido3().trim()!=""))
			pac.setApellido3(iPaciente.getApellido3());
		if(isNew || (!isNew && iPaciente.getDescription()!=null && iPaciente.getDescription().trim()!=""))
			pac.setDescription(iPaciente.getDescription());// RAZON SOCIAL
		if(isNew || (!isNew && iPaciente.getImss()!=null && iPaciente.getImss().trim()!=""))
			pac.setImss(iPaciente.getImss());
		if(isNew || (!isNew && iPaciente.getCurp()!=null && iPaciente.getCurp().trim()!=""))
			pac.setCurp(iPaciente.getCurp());
		if(isNew || (!isNew && iPaciente.getEXME_Institucion_ID()>0))
			pac.setEXME_Institucion_ID(iPaciente.getEXME_Institucion_ID());
		if(isNew || (!isNew && iPaciente.getHoraNac()!=null && iPaciente.getHoraNac().trim()!=""))
			pac.setHoraNac(iPaciente.getHoraNac());
		if(isNew || (!isNew && iPaciente.getEXME_Religion_ID()>0))
			pac.setEXME_Religion_ID(iPaciente.getEXME_Religion_ID());		
		if(isNew || (!isNew && iPaciente.getEXME_Ocupacion_ID()>0))
			pac.setEXME_Ocupacion_ID(iPaciente.getEXME_Ocupacion_ID());
		if(isNew || (!isNew && iPaciente.getLimiteCredito()!=null))
			pac.setLimiteCredito(iPaciente.getLimiteCredito());
		if(isNew || (!isNew && iPaciente.getTelParticular()!=null && iPaciente.getTelParticular().trim()!=""))
			pac.setTelParticular(iPaciente.getTelParticular());
		if(isNew || (!isNew && iPaciente.getEMail()!=null && iPaciente.getEMail().trim()!=""))
			pac.setEMail(iPaciente.getEMail());	
		if(isNew || (!isNew && iPaciente.getPoliza()!=null && iPaciente.getPoliza().trim()!=""))
			pac.setPoliza(iPaciente.getPoliza());
		if(isNew || (!isNew && iPaciente.getTitular()!=null && iPaciente.getTitular().trim()!=""))
			pac.setTitular(iPaciente.getTitular());
		if(isNew || (!isNew && iPaciente.getNoAutorizacion()!=null && iPaciente.getNoAutorizacion().trim()!=""))
			pac.setNoAutorizacion(iPaciente.getNoAutorizacion());
		if(isNew || (!isNew && iPaciente.getObservaciones()!=null && iPaciente.getObservaciones().trim()!=""))
			pac.setObservaciones(iPaciente.getObservaciones());
//		if(isNew || (!isNew && iPaciente.getC_BPartner_Seg_ID()>0))
//			pac.setC_BPartner_Seg_ID(iPaciente.getC_BPartner_Seg_ID());
		if(isNew)
			pac.setC_Location_Nac_ID(iPaciente.getC_Location_Nac_ID());
		if(isNew || (!isNew && iPaciente.getEXME_Parentesco_ID()>0))
			pac.setEXME_Parentesco_ID(iPaciente.getEXME_Parentesco_ID());
		if(isNew || (!isNew && iPaciente.getFechaBaja()!=null))
			pac.setFechaBaja(iPaciente.getFechaBaja());
		if(isNew || (!isNew && iPaciente.getFechaVigencia()!=null))
			pac.setFechaVigencia(iPaciente.getFechaVigencia());
		if(isNew || (!isNew && iPaciente.getTitular_ID()>0))
			pac.setTitular_ID(iPaciente.getTitular_ID());
		if(isNew || (!isNew && iPaciente.getEXME_ArchClinico_ID()>0))
			pac.setEXME_ArchClinico_ID(iPaciente.getEXME_ArchClinico_ID());
		if(isNew || (!isNew && iPaciente.getNoSiniestro()!=null))
			pac.setNoSiniestro(iPaciente.getNoSiniestro());
		if(isNew || (!isNew && iPaciente.getDiagnostico_Ingreso_Descr()!=null && iPaciente.getDiagnostico_Ingreso_Descr().trim()!=""))
			pac.setEXME_Diagnostico_Ingreso_Descr(iPaciente.getDiagnostico_Ingreso_Descr());
		if(isNew || (!isNew && iPaciente.getDiagnostico_Egreso_Descr()!=null && iPaciente.getDiagnostico_Egreso_Descr().trim()!=""))
			pac.setEXME_Diagnostico_Egreso_Descr(iPaciente.getDiagnostico_Egreso_Descr());	
		if(isNew || (!isNew && iPaciente.getSeguroPopular()!=null && iPaciente.getSeguroPopular().trim()!=""))
			pac.setSeguroPopular(iPaciente.getSeguroPopular());
		if(isNew || (!isNew && iPaciente.getDocumentoConvenio()!=null && iPaciente.getDocumentoConvenio().trim()!=""))
			pac.setDocumentoConvenio(iPaciente.getDocumentoConvenio());
		if(isNew || (!isNew && iPaciente.getEXME_Delegacion_Paciente_ID()>0))
			pac.setEXME_Delegacion_Paciente_ID(iPaciente.getEXME_Delegacion_Paciente_ID());
		if(isNew || (!isNew && iPaciente.getFechaVencimiento()!=null))
			pac.setFechaVencimiento(iPaciente.getFechaVencimiento());		
		
		pac.setEsAsegurado(iPaciente.isEsAsegurado());
		pac.setDerechohabiente(iPaciente.isDerechohabiente());
		pac.setEsTitular(iPaciente.isEsTitular());
		pac.setDerechohabienteOtro(iPaciente.isDerechohabienteOtro());
		pac.setPoblacionAbierta(iPaciente.isPoblacionAbierta());
		pac.setParticular(iPaciente.isParticular());
		pac.setLimCredDerechoh(iPaciente.getLimCredDerechoh());
				
		//Datos del Familiar
		if(isNew || (!isNew && iPaciente.getNombre_Fam()!=null && iPaciente.getNombre_Fam().trim()!=""))
			pac.setNombre_Fam(iPaciente.getNombre_Fam());
		if(isNew || (!isNew && iPaciente.getApellido1_Fam()!=null && iPaciente.getApellido1_Fam().trim()!=""))
			pac.setApellido1_Fam(iPaciente.getApellido1_Fam());
		if(isNew || (!isNew && iPaciente.getApellido2_Fam()!=null && iPaciente.getApellido2_Fam().trim()!=""))
			pac.setApellido2_Fam(iPaciente.getApellido2_Fam());
		if(isNew || (!isNew && iPaciente.getNombreFamiliar()!=null && iPaciente.getNombreFamiliar().trim()!=""))
			pac.setNombreFamiliar(iPaciente.getNombreFamiliar());
		if(isNew || (!isNew && iPaciente.getDirFamiliar()!=null && iPaciente.getDirFamiliar().trim()!=""))
			pac.setDirFamiliar(iPaciente.getDirFamiliar());
		if(isNew || (!isNew && iPaciente.getTelFamiliar()!=null && iPaciente.getTelFamiliar().trim()!=""))
			pac.setTelFamiliar(iPaciente.getTelFamiliar());
		if(isNew || (!isNew && iPaciente.getTelParticular_Fam()!=null && iPaciente.getTelParticular_Fam().trim()!=""))
			pac.setTelParticular_Fam(iPaciente.getTelParticular_Fam());
		if(isNew || (!isNew && iPaciente.getPuesto_Fam()!=null && iPaciente.getPuesto_Fam().trim()!=""))
			pac.setPuesto_Fam(iPaciente.getPuesto_Fam());
		if(isNew || (!isNew && iPaciente.getRFC_Fam()!=null && iPaciente.getRFC_Fam().trim()!=""))
			pac.setRFC_Fam(iPaciente.getRFC_Fam());
		if(isNew || (!isNew && iPaciente.getEXME_Ocupacion_Fam_ID()>0))
			pac.setEXME_Ocupacion_Fam_ID(iPaciente.getEXME_Ocupacion_Fam_ID());
		pac.setAntiguedad_Fam(new BigDecimal(iPaciente.getAntiguedad_Fam()));
		
		//Datos de la Persona Responsable
		if(isNew || (!isNew && iPaciente.getColoniaPersResp()!=null && iPaciente.getColoniaPersResp().trim()!=""))
			pac.setColoniaPersResp(iPaciente.getColoniaPersResp());
		if(isNew || (!isNew && iPaciente.getCiudadPersResp()!=null && iPaciente.getCiudadPersResp().trim()!=""))
			pac.setCiudadPersResp(iPaciente.getCiudadPersResp());
		if(isNew || (!isNew && iPaciente.getCpPersResp()!=null && iPaciente.getCpPersResp().trim()!=""))
			pac.setCpPersResp(iPaciente.getCpPersResp());
		if(isNew || (!isNew && iPaciente.getC_Country_PersResp_ID()>0))
			pac.setC_Country_PersResp_ID(iPaciente.getC_Country_PersResp_ID());
		if(isNew || (!isNew && iPaciente.getC_Region_PersResp_ID()>0))
			pac.setC_Region_PersResp_ID(iPaciente.getC_Region_PersResp_ID());
		if(isNew || (!isNew && iPaciente.getDirTrabPersResp()!=null && iPaciente.getDirTrabPersResp().trim()!=""))
			pac.setDirTrabPersResp(iPaciente.getDirTrabPersResp());
		
		//Datos de Registro
		if(isNew || (!isNew && iPaciente.getFechaRegistro()!=null))
			pac.setFechaRegistro(iPaciente.getFechaRegistro());
		if(isNew || (!isNew && iPaciente.getC_LocationReg_ID()>0))
			pac.setC_LocationReg_ID(iPaciente.getC_LocationReg_ID());
		
		//Informacion Militar
		if(isNew || (!isNew && iPaciente.getEXME_Unidad_ID()>0))
			pac.setEXME_Unidad_ID(iPaciente.getEXME_Unidad_ID());
		if(isNew || (!isNew && iPaciente.getEXME_Grupo_Especialidad_ID()>0))
			pac.setEXME_Grupo_Especialidad_ID(iPaciente.getEXME_Grupo_Especialidad_ID());
		if(isNew || (!isNew && iPaciente.getEXME_Grado_ID()>0))
			pac.setEXME_Grado_ID(iPaciente.getEXME_Grado_ID());
		if(isNew || (!isNew && iPaciente.getEXME_Arma_ID()>0))
			pac.setEXME_Arma_ID(iPaciente.getEXME_Arma_ID());
		
    	return pac;
    }

}

