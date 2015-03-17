package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.util.DB;
import org.adempiere.exceptions.DBException;

public class ImportClasificacion extends SvrProcess {

    public ImportClasificacion() {
        super();
    }

    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;

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
                log.log(Level.SEVERE, "ImportExmeClasificacion.prepare - Unknown Parameter: " + name);
        }
        if (m_DateValue == null)
            m_DateValue = DB.getTimestampForOrg(getCtx());
    }   //  prepare


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

        //  ****    Prepare ****

        //  Delete Old Imported
        if (m_deleteOldImported)
        {
            sql = new StringBuffer ("DELETE I_EXME_Clasificacion "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString(), null);
            log.fine("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IsActive, Created/Updated
        sql = new StringBuffer ("UPDATE I_EXME_Clasificacion "
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
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Reset=" + no);

  /*****************************************************************************/      
        //  Set EXME_ClasCliente_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i ");
        
        sql.append(" SET i.EXME_ClasCliente_ID = (SELECT EXME_ClasCliente_ID ");
        sql.append(" FROM EXME_ClasCliente c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.Value = i.EXME_ClasCliente_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1)");	
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_ClasCliente_Value IS NOT NULL ");
        sql.append(" AND i.EXME_ClasCliente_ID IS NULL ");
        sql.append(" AND i.I_IsImported<>'Y'" );
                
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_ClasCliente_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=ClasCliente no existe,' "
                + " WHERE EXME_ClasCliente_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("ClasCliente no existe=" + no);
        
        
        //  Set EXME_MatConst_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_MatConst_ID = (SELECT EXME_Material_Const_ID ");
        sql.append(" FROM EXME_Material_Const c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_Material_Const_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_Material_Const_Value is not null ");
        sql.append(" AND i.EXME_MatConst_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_MatConst_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Material_Const no existe,' "
                + " WHERE EXME_MatConst_id IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Material_Const no existe=" + no);  
        
        
        //Set EXME_NumEnferm_ID
        
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_NumEnferm_ID = (SELECT EXME_NumEnferm_ID ");
        sql.append(" FROM EXME_NumEnferm c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_NumEnferm_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_NumEnferm_Value is not null ");
        sql.append(" AND i.EXME_NumEnferm_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumEnferm_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumEnferm no existe,' "
                + " WHERE EXME_NumEnferm_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumEnferm no existe=" + no);  
        
        
        //Set EXME_NumHabts_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
		sql.append(" SET  i.EXME_NumHabts_ID = (SELECT EXME_NumHabts_ID ");
		sql.append(" FROM EXME_NumHabts c "); 
		sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
		sql.append(" AND c.value = i.EXME_NumHabts_Value ");
		
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		
		sql.append(" WHERE i.EXME_NumHabts_Value is not null ");
		sql.append(" AND i.EXME_NumHabts_ID is null ");
		sql.append(" AND i.I_IsImported<>'Y'" );
		
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumHabts_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumHabts no existe,' "
                + " WHERE EXME_NumHabts_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumHabts no existe=" + no);  
        
        
        //Set EXME_NumPers_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_NumPers_ID = (SELECT EXME_NumPers_ID ");
        sql.append(" FROM EXME_NumPers c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_NumPers_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_NumPers_Value is not null ");
        sql.append(" AND i.EXME_NumPers_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_NumPers_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=NumPers no existe,' "
                + " WHERE EXME_NumPers_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("NumPers no existe=" + no);  
        
        
        //Set EXME_Ocupacion_Clas_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_Ocupacion_Clas_ID = (SELECT EXME_Ocupacion_Clas_ID ");
        sql.append(" FROM EXME_Ocupacion_Clas c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_Ocupacion_Clas_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_Ocupacion_Clas_Value is not null ");
        sql.append(" AND i.EXME_Ocupacion_Clas_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Ocupacion_Clas_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Ocupacion_Clas no existe,' "
                + " WHERE EXME_Ocupacion_Clas_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Ocupacion_Clas no existe=" + no);  
        
        
        //Set EXME_Procedencia_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_Procedencia_ID = (SELECT EXME_Procedencia_ID ");
        sql.append(" FROM EXME_Procedencia c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_Procedencia_Value ");

        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_Procedencia_Value is not null ");
        sql.append(" AND i.EXME_Procedencia_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Procedencia_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Procedencia no existe,' "
                + " WHERE EXME_Procedencia_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Procedencia no existe=" + no);  
        
        
        //Set EXME_ServPublico_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_ServPublico_ID = (SELECT EXME_ServPublico_ID ");
        sql.append(" FROM EXME_ServPublico c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_ServPublico_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        
        sql.append(" WHERE i.EXME_ServPublico_Value is not null ");
        sql.append(" AND i.EXME_ServPublico_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_ServPublico_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=ServPublico no existe,' "
                + " WHERE EXME_ServPublico_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("ServPublico no existe=" + no);  
        
        
        //Set EXME_Tenencia_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_Tenencia_ID = (SELECT EXME_Tenencia_ID ");
        sql.append(" FROM EXME_Tenencia c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_Tenencia_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }       
        
        sql.append(" WHERE i.EXME_Tenencia_Value is not null ");
        sql.append(" AND i.EXME_Tenencia_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Tenencia_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Tenencia no existe,' "
                + " WHERE EXME_Tenencia_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Tenencia no existe=" + no); 

        
        //Set EXME_TipoVivienda_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_TipoVivienda_ID = (SELECT EXME_TipoVivienda_ID ");
        sql.append(" FROM EXME_TipoVivienda c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_TipoVivienda_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }         
        
        sql.append(" WHERE i.EXME_TipoVivienda_Value is not null ");
        sql.append(" AND i.EXME_TipoVivienda_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_TipoVivienda_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=TipoVivienda no existe,' "
                + " WHERE EXME_TipoVivienda_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("TipoVivienda no existe=" + no); 

        
        //Set EXME_Zona_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_Zona_ID = (SELECT EXME_Zona_ID ");
        sql.append(" FROM EXME_Zona c "); 
        sql.append(" WHERE c.AD_Client_ID IN (0, i.AD_Client_ID) "); 
        sql.append(" AND c.value = i.EXME_Zona_Value ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        } 
        
        sql.append(" WHERE i.EXME_Zona_Value is not null ");
        sql.append(" AND i.EXME_Zona_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Zona_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Zona no existe,' "
                + " WHERE EXME_Zona_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Zona no existe=" + no); 
        
        
        //Set EXME_Paciente_ID
        sql = new StringBuffer (" UPDATE I_EXME_Clasificacion i "); 
        sql.append(" SET  i.EXME_Paciente_ID = (SELECT EXME_Paciente_ID ");
        sql.append(" FROM EXME_Paciente c "); 
        sql.append(" WHERE "); 
        sql.append(" c.value = i.EXME_Paciente_Value ");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "c"));
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        } 
        
        sql.append(" WHERE i.EXME_Paciente_Value is not null ");
        sql.append(" AND i.EXME_Paciente_ID is null ");
        sql.append(" AND i.I_IsImported<>'Y'" );
                
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Paciente_ID=" + no);
        //
        sql = new StringBuffer(" UPDATE I_EXME_Clasificacion "
                + " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Paciente no existe,' "
                + " WHERE EXME_Paciente_ID IS NULL"
                + " AND I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        if (no != 0)
            log.warning("Paciente no existe=" + no); 
        
        //Set EXME_Clasificacion_ID
        sql = new StringBuffer("Update I_EXME_Clasificacion i set EXME_Clasificacion_ID=");
        sql.append("(SELECT EXME_CLASIFICACION_ID FROM EXME_CLASIFICACION c ");
        sql.append("WHERE c.EXME_PACIENTE_ID = i.EXME_Paciente_ID ");
        
        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        } 
        
        sql.append("Where i.I_IsImported<>'Y' And  i.EXME_Paciente_ID Is Not Null And  i.EXME_Paciente_ID>0");
        
        no = DB.executeUpdate(sql.toString(), null);
        log.fine("Set EXME_Clasificacion_ID=" + no);

        
/******************************************************************************/        
        


//      -------------------------------------------------------------------

        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records

        log.fine("start inserting/updating ...");
        
        sql = new StringBuffer ("SELECT I_EXME_Clasificacion_ID, EXME_Clasificacion_ID "
            + "FROM I_EXME_Clasificacion WHERE I_IsImported='N'");
        
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

        try
        {
            //  Insertar Clasificacion a partir de la importacion
            PreparedStatement pstmt_insertClasif = conn.prepareStatement
                ("INSERT INTO EXME_Clasificacion (EXME_Clasificacion_ID"
                + ",  AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, NumMiembros, Observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, islocked, ad_user_ID ) "
                
                + "SELECT ?"
                + ", AD_Client_ID, AD_Org_ID, 'Y', SysDate, CreatedBy, SysDate, UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, IsLocked, AD_User_ID "
                + "  FROM I_EXME_Clasificacion "
                + "  WHERE I_EXME_Clasificacion_ID=? ");

            //  Update Clasificacion from Import
            PreparedStatement pstmt_updateClasif = conn.prepareStatement
                ("UPDATE EXME_Clasificacion "
                + " SET (Updated,UpdatedBy" 
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, IsLocked, AD_User_ID)= "
                + "(SELECT SysDate,UpdatedBy"
                + ", EXME_Paciente_ID, DateOrdered, EXME_Procedencia_ID, PtsProcedencia"
                + ", EXME_Ocupacion_Clas_ID, Ptsocupacion, EXME_zona_ID, Ptszona, EXME_tipovivienda_ID"
                + ", PtsTipoVivienda, EXME_tenencia_ID, Ptstenencia, EXME_servpublico_ID, Ptsservpublico"
                + ", EXME_matconst_ID, Ptsmatconst, EXME_numhabts_ID, Ptsnumhabts, EXME_numpers_ID"
                + ", Ptsnumpers, EXME_numenferm_ID, nummiembros, observaciones"
                + ", totalpts, EXME_clascliente_ID, ingjefefam, inghijos, ingotros, totaling"
                + ", Egralimentacion, Egrvivienda, Egrservicios, Egrotros, totalegr, Porcalimentacion"
                + ", Ptsingresos, Ptsegre, datevaidto, IsLocked, AD_User_ID"
                + " FROM I_EXME_Clasificacion WHERE I_EXME_Clasificacion_ID=?) "
                + "WHERE EXME_Clasificacion_ID=?");
            

            //  Set Imported = Y
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Clasificacion SET I_IsImported='Y', EXME_Clasificacion_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Clasificacion_ID=?");

            
            //Primero el select
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            
            int I_EXME_Clasificacion_ID = 0;
            int EXME_Clasificacion_ID = 0;
            boolean newRegistro =  false;
            
            while (rs.next())
            {
                I_EXME_Clasificacion_ID = rs.getInt(1);
                EXME_Clasificacion_ID = rs.getInt(2);
                newRegistro = EXME_Clasificacion_ID == 0;
                log.fine("I_EXME_Clasificacion_ID=" + I_EXME_Clasificacion_ID + ", EXME_EXME_Clasificacion_ID=" + EXME_Clasificacion_ID);

                //  nuevo registro
                if (newRegistro)
                {
                    try
                    {

                        EXME_Clasificacion_ID = DB.getNextID(m_AD_Client_ID, "EXME_Clasificacion", null);
                        if (EXME_Clasificacion_ID <= 0)
                            throw new DBException("No Next ID (" + EXME_Clasificacion_ID + ")");
                        pstmt_insertClasif.setInt(1, EXME_Clasificacion_ID);
                        pstmt_insertClasif.setInt(2, I_EXME_Clasificacion_ID);
                        //
                        no = pstmt_insertClasif.executeUpdate();
                        log.finer("Insert Clasificacion = " + no);
                        noInsert++;
                       
                   
            		    
                    }catch (Exception ex){

                        log.warning("Insert Clasificacion  - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Clasificacion i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insert Clasificacion: " + ex.toString()))
                            .append("WHERE I_EXME_Clasificacion_ID=").append(I_EXME_Clasificacion_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;

                    }

                }
                else    //  Update Clasificacion
                {
                    pstmt_updateClasif.setInt(1, I_EXME_Clasificacion_ID);
                    pstmt_updateClasif.setInt(2, EXME_Clasificacion_ID);

                    try
                    {
                        no = pstmt_updateClasif.executeUpdate();
                        log.finer("Update Clasificacion = " + no);
                        noUpdate++;
                    
			
		
 		    
		    }
		    
		 
                    catch (SQLException ex)
                    {
                        log.warning("Update Clasificacion- " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Clasificacion i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update Clasificacion: " + ex.toString()))
                            .append("WHERE I_EXME_Clasificacion_ID=").append(I_EXME_Clasificacion_ID);
                        DB.executeUpdate(sql.toString(), null);
                        continue;
                    }

                }

                //  Update I_EXME_Clasificacion
                pstmt_setImported.setInt(1, EXME_Clasificacion_ID);
                pstmt_setImported.setInt(2, I_EXME_Clasificacion_ID);
                no = pstmt_setImported.executeUpdate();
                //
                conn.commit();

            }

            
            rs.close();
            pstmt.close();
	       rs=null;
	       pstmt=null;
            //
            pstmt_insertClasif.close();
            pstmt_updateClasif.close();
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
            }
            catch (SQLException ex)
            {
            }
            log.log(Level.SEVERE, "doIt", e);
            throw new Exception ("doIt", e);

        }
        finally
        {
            if (conn != null)
                conn.close();
            conn = null;
        }

        //  Set Error to indicator to not imported
        sql = new StringBuffer ("UPDATE I_EXME_Clasificacion "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString(), null);
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@EXME_Clasificacion_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@EXME_Clasificacion_ID@: @Updated@");
        return "";

    }
}   // 
