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
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 * Proceso de importaci&oacute;n de la tabla de Subtipos de Producto.<p>
 * Creado: 10/Mar/2005<p>
 * Modificado: $Date: 2006/08/11 02:35:28 $<p>
 * Por: $Author: mrojas $<p>
 *
 * @author mrojas
 * @version $Revision: 1.1 $
 */
public class ImportEncuesta extends SvrProcess {

    /** Client to be imported to        */
    private int             m_AD_Client_ID = 0;
    /** Delete old Imported             */
    private boolean         m_deleteOldImported = false;

    /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;
    /** Effective                       */
    private Timestamp       m_DateValue = null;

    /**
     * Constructor por defecto.
     */
    public ImportEncuesta() {
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

        //  ****    Prepare ****
        //  Delete Old Imported
        if (m_deleteOldImported)
        {
            sql = new StringBuffer ("DELETE I_EXME_Encuesta "
                + "WHERE I_IsImported='Y'").append(clientCheck);
            no = DB.executeUpdate(sql.toString());
            log.info("Delete Old Impored =" + no);
        }

        //  Set Client, Org, IaActive, Created/Updated,     ProductType
        sql = new StringBuffer ("UPDATE I_EXME_Encuesta "
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
        log.info("Reset=" + no);

        //  Set Exme_Paciente_ID
        sql = new StringBuffer ("UPDATE I_EXME_Encuesta i "
            + "SET EXME_Paciente_ID = (SELECT EXME_Paciente_ID FROM EXME_Paciente p WHERE "
            + " p.Value = i.numhist "
            + MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEPaciente.Table_Name, "p")                
            + ") "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set Exme_Paciente_ID=" + no);
        
        //SET EXME_ENCUESTA_ID
        sql = new StringBuffer(" Update I_EXME_Encuesta i set EXME_Encuesta_ID = ");
        sql.append("(SELECT EXME_ENCUESTA_ID FROM EXME_ENCUESTA e ");
        sql.append("WHERE e.EXME_PACIENTE_ID = i.EXME_Paciente_ID ");

        if (DB.isOracle()) {
        	sql.append(" AND ROWNUM=1) ");
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }

        sql.append("Where i.I_IsImported<>'Y' And  i.EXME_Paciente_ID Is Not Null And  i.EXME_Paciente_ID>0");
        no = DB.executeUpdate(sql.toString());
        log.fine("Set EXME_Encuesta_ID=" + no);

//      -------------------------------------------------------------------
        int noInsert = 0;
        int noUpdate = 0;
        //int noInsertPO = 0;
        //int noUpdatePO = 0;

        //  Go through Records
        log.fine("start inserting/updating ...");
        sql = new StringBuffer ("SELECT I_EXME_Encuesta_ID, EXME_Encuesta_ID "
            + "FROM I_EXME_Encuesta WHERE I_IsImported<>'Y'");
        Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            //  Insertar subtipo de producto a partir de la importacion
            PreparedStatement pstmt_insertEncuesta = conn.prepareStatement
                ("INSERT INTO EXME_Encuesta (EXME_Encuesta_ID,"
                + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
                + "EXME_Paciente_ID,"
                + "Mas100cig ,"
                + "Qty_Fumaba ,"
                + "Caj_Fumaba ,"
                + "Anios_Fumo ,"
                + "Meses_Fumo ,"
                + "Otra_Fumaba ,"
                + "Uso_Lena ,"
                + "Uso_Carbon ,"
                + "Tomo_Bebidas ,"
                + "Tomo_Vitaminas ,"
                + "Tomo_Med_Dormir ,"
                + "Tomo_Dieta ,"
                + "Fuma  ,"
                + "Qty_Fum  ,"
                + "Caj_Fuma ,"
                + "Anios_Fuma ,"
                + "Meses_Fuma ,"
                + "Otra_Fuma ,"
                + "Combust_Cocina ,"
                + "Otro_Combust ,"
                + "Toma_Bebidas ,"
                + "Toma_Vitaminas ,"
                + "Toma_Med_Dormir ,"
                + "Toma_Dieta ,"
                + "Tenido_Ave ,"
                + "Tenido_Perro ,"
                + "Tenido_Gato ,"
                + "Tenido_Cucar ,"
                + "Tenido_Animal ,"
                + "Tenido_Alfombra ,"
                + "Tenido_Humedad ,"
                + "Usado_Insectic ,"
                + "Expuso_Polvo ,"
                + "Tiene_Ave  ,"
                + "Tiene_Perro ,"
                + "Tiene_Gato  ,"
                + "Tiene_Cucar ,"
                + "Tiene_Animal ,"
                + "Tiene_Alfombra ,"
                + "Tiene_Humedad  ,"
                + "Usa_Insectic ,"
                + "Expone_Polvo ,"
                + "Expuso_Humo  ,"
                + "Manejo_Sustancia ,"
                + "Hay_Gasolinera ,"
                + "Hay_Avenida  ,"
                + "Hay_Ninos    ,"
                + "No_Pers_Pac  ,"
                + "Tiene_Telefono ,"
                + "Tiene_Microondas ,"
                + "Tiene_Computadora ,"
                + "Tiene_Tv   ,"
                + "Tiene_Refri ,"
                + "Tiene_Horno ,"
                + "Expone_Humo ,"
                + "Maneja_Sustancia ,"
                + "Hay_Basura  ,"
                + "Hay_Establo ,"
                + "Hay_Granja  ,"
                + "Hay_Fabrica ,"
                + "Tiene_Piso  ,"
                + "Tiene_Ventanas ,"
                + "Qty_Ventanas  ,"
                + "Qty_Hr_Abierta "
		+ " )  "
                + "SELECT ?," 
                + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
                + "EXME_Paciente_ID,  "
                + "Mas100cig ,"
                + "Qty_Fumaba ,"
                + "Caj_Fumaba ,"
                + "Anios_Fumo ,"
                + "Meses_Fumo ,"
                + "Otra_Fumaba ,"
                + "Uso_Lena ,"
                + "Uso_Carbon ,"
                + "Tomo_Bebidas ,"
                + "Tomo_Vitaminas ,"
                + "Tomo_Med_Dormir ,"
                + "Tomo_Dieta ,"
                + "Fuma  ,"
                + "Qty_Fum  ,"
                + "Caj_Fuma ,"
                + "Anios_Fuma ,"
                + "Meses_Fuma ,"
                + "Otra_Fuma ,"
                + "Combust_Cocina ,"
                + "Otro_Combust ,"
                + "Toma_Bebidas ,"
                + "Toma_Vitaminas ,"
                + "Toma_Med_Dormir ,"
                + "Toma_Dieta ,"
                + "Tenido_Ave ,"
                + "Tenido_Perro ,"
                + "Tenido_Gato ,"
                + "Tenido_Cucar ,"
                + "Tenido_Animal ,"
                + "Tenido_Alfombra ,"
                + "Tenido_Humedad ,"
                + "Usado_Insectic ,"
                + "Expuso_Polvo ,"
                + "Tiene_Ave  ,"
                + "Tiene_Perro ,"
                + "Tiene_Gato  ,"
                + "Tiene_Cucar ,"
                + "Tiene_Animal ,"
                + "Tiene_Alfombra ,"
                + "Tiene_Humedad  ,"
                + "Usa_Insectic ,"
                + "Expone_Polvo ,"
                + "Expuso_Humo  ,"
                + "Manejo_Sustancia ,"
                + "Hay_Gasolinera ,"
                + "Hay_Avenida  ,"
                + "Hay_Ninos    ,"
                + "No_Pers_Pac  ,"
                + "Tiene_Telefono ,"
                + "Tiene_Microondas ,"
                + "Tiene_Computadora ,"
                + "Tiene_Tv   ,"
                + "Tiene_Refri ,"
                + "Tiene_Horno ,"
                + "Expone_Humo ,"
                + "Maneja_Sustancia ,"
                + "Hay_Basura  ,"
                + "Hay_Establo ,"
                + "Hay_Granja  ,"
                + "Hay_Fabrica ,"
                + "Tiene_Piso  ,"
                + "Tiene_Ventanas ,"
                + "Qty_Ventanas  ,"
                + "Qty_Hr_Abierta  "
                + "FROM I_EXME_Encuesta "
                + "WHERE I_EXME_Encuesta_ID=?");
            
            PreparedStatement pstmt_updateEncuesta = conn.prepareStatement
            ("UPDATE EXME_Encuesta set("
            + "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
            + "EXME_Paciente_ID,"
            + "Mas100cig ,"
            + "Qty_Fumaba ,"
            + "Caj_Fumaba ,"
            + "Anios_Fumo ,"
            + "Meses_Fumo ,"
            + "Otra_Fumaba ,"
            + "Uso_Lena ,"
            + "Uso_Carbon ,"
            + "Tomo_Bebidas ,"
            + "Tomo_Vitaminas ,"
            + "Tomo_Med_Dormir ,"
            + "Tomo_Dieta ,"
            + "Fuma  ,"
            + "Qty_Fum  ,"
            + "Caj_Fuma ,"
            + "Anios_Fuma ,"
            + "Meses_Fuma ,"
            + "Otra_Fuma ,"
            + "Combust_Cocina ,"
            + "Otro_Combust ,"
            + "Toma_Bebidas ,"
            + "Toma_Vitaminas ,"
            + "Toma_Med_Dormir ,"
            + "Toma_Dieta ,"
            + "Tenido_Ave ,"
            + "Tenido_Perro ,"
            + "Tenido_Gato ,"
            + "Tenido_Cucar ,"
            + "Tenido_Animal ,"
            + "Tenido_Alfombra ,"
            + "Tenido_Humedad ,"
            + "Usado_Insectic ,"
            + "Expuso_Polvo ,"
            + "Tiene_Ave  ,"
            + "Tiene_Perro ,"
            + "Tiene_Gato  ,"
            + "Tiene_Cucar ,"
            + "Tiene_Animal ,"
            + "Tiene_Alfombra ,"
            + "Tiene_Humedad  ,"
            + "Usa_Insectic ,"
            + "Expone_Polvo ,"
            + "Expuso_Humo  ,"
            + "Manejo_Sustancia ,"
            + "Hay_Gasolinera ,"
            + "Hay_Avenida  ,"
            + "Hay_Ninos    ,"
            + "No_Pers_Pac  ,"
            + "Tiene_Telefono ,"
            + "Tiene_Microondas ,"
            + "Tiene_Computadora ,"
            + "Tiene_Tv   ,"
            + "Tiene_Refri ,"
            + "Tiene_Horno ,"
            + "Expone_Humo ,"
            + "Maneja_Sustancia ,"
            + "Hay_Basura  ,"
            + "Hay_Establo ,"
            + "Hay_Granja  ,"
            + "Hay_Fabrica ,"
            + "Tiene_Piso  ,"
            + "Tiene_Ventanas ,"
            + "Qty_Ventanas  ,"
            + "Qty_Hr_Abierta "
	+ " ) = "
            + "(SELECT " 
            + "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
            + "EXME_Paciente_ID,  "
            + "Mas100cig ,"
            + "Qty_Fumaba ,"
            + "Caj_Fumaba ,"
            + "Anios_Fumo ,"
            + "Meses_Fumo ,"
            + "Otra_Fumaba ,"
            + "Uso_Lena ,"
            + "Uso_Carbon ,"
            + "Tomo_Bebidas ,"
            + "Tomo_Vitaminas ,"
            + "Tomo_Med_Dormir ,"
            + "Tomo_Dieta ,"
            + "Fuma  ,"
            + "Qty_Fum  ,"
            + "Caj_Fuma ,"
            + "Anios_Fuma ,"
            + "Meses_Fuma ,"
            + "Otra_Fuma ,"
            + "Combust_Cocina ,"
            + "Otro_Combust ,"
            + "Toma_Bebidas ,"
            + "Toma_Vitaminas ,"
            + "Toma_Med_Dormir ,"
            + "Toma_Dieta ,"
            + "Tenido_Ave ,"
            + "Tenido_Perro ,"
            + "Tenido_Gato ,"
            + "Tenido_Cucar ,"
            + "Tenido_Animal ,"
            + "Tenido_Alfombra ,"
            + "Tenido_Humedad ,"
            + "Usado_Insectic ,"
            + "Expuso_Polvo ,"
            + "Tiene_Ave  ,"
            + "Tiene_Perro ,"
            + "Tiene_Gato  ,"
            + "Tiene_Cucar ,"
            + "Tiene_Animal ,"
            + "Tiene_Alfombra ,"
            + "Tiene_Humedad  ,"
            + "Usa_Insectic ,"
            + "Expone_Polvo ,"
            + "Expuso_Humo  ,"
            + "Manejo_Sustancia ,"
            + "Hay_Gasolinera ,"
            + "Hay_Avenida  ,"
            + "Hay_Ninos    ,"
            + "No_Pers_Pac  ,"
            + "Tiene_Telefono ,"
            + "Tiene_Microondas ,"
            + "Tiene_Computadora ,"
            + "Tiene_Tv   ,"
            + "Tiene_Refri ,"
            + "Tiene_Horno ,"
            + "Expone_Humo ,"
            + "Maneja_Sustancia ,"
            + "Hay_Basura  ,"
            + "Hay_Establo ,"
            + "Hay_Granja  ,"
            + "Hay_Fabrica ,"
            + "Tiene_Piso  ,"
            + "Tiene_Ventanas ,"
            + "Qty_Ventanas  ,"
            + "Qty_Hr_Abierta  "
            + "FROM I_EXME_Encuesta "
            + "WHERE I_EXME_Encuesta_ID=?) Where EXME_Encuesta_ID=?");

            //  Set Imported = Y  a la tabla temporal
            PreparedStatement pstmt_setImported = conn.prepareStatement
                ("UPDATE I_EXME_Encuesta SET I_IsImported='Y', EXME_Encuesta_ID=?, "
                + "Updated=SysDate, Processed='Y' WHERE I_EXME_Encuesta_ID=?");
            //
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            ResultSet rs = pstmt.executeQuery();
            
            int I_EXME_Encuesta_ID = 0;
            int EXME_Encuesta_ID   = 0;
            boolean nuevo = false;
            
            while (rs.next())
            {
                I_EXME_Encuesta_ID = rs.getInt(1);
                EXME_Encuesta_ID   = rs.getInt(2);
                nuevo = EXME_Encuesta_ID==0;
                
                log.fine("I_EXME_Encuesta_ID=" + I_EXME_Encuesta_ID + ", EXME_Encuesta_ID=" + EXME_Encuesta_ID);

                    try
                    {
                    	if(nuevo){
                    		EXME_Encuesta_ID = DB.getNextID(m_AD_Client_ID, "EXME_Encuesta", null);
                            if (EXME_Encuesta_ID <= 0)
                                throw new DBException("No Next ID (" + EXME_Encuesta_ID + ")");
                            pstmt_insertEncuesta.setInt(1, EXME_Encuesta_ID);
                            pstmt_insertEncuesta.setInt(2, I_EXME_Encuesta_ID);
                            //
                            no = pstmt_insertEncuesta.executeUpdate();
                            log.finer("Insert Encuesta = " + no);
                            noInsert++;
                    	}
                    	else{
                    		pstmt_updateEncuesta.setInt(1,  I_EXME_Encuesta_ID);
                            pstmt_updateEncuesta.setInt(2,EXME_Encuesta_ID);
                            //
                            no = pstmt_updateEncuesta.executeUpdate();
                            log.finer("Update Encuesta = " + no);
                            noUpdate++;
                    	}
                    }
                    catch (Exception ex)
                    {
                        log.warning("Insertando Encuesta - " + ex.toString());
                        sql = new StringBuffer ("UPDATE I_EXME_Encuesta i "
                            + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
                            .append(DB.TO_STRING("Insertando Encuesta: " + ex.toString()))
                            .append("WHERE I_Exme_Encuesta_ID=").append(I_EXME_Encuesta_ID);
                        DB.executeUpdate(sql.toString());
                        continue;
                    }

                    //  Update I_EXME_Diagnostico
                    pstmt_setImported.setInt(1, EXME_Encuesta_ID);
                    pstmt_setImported.setInt(2, I_EXME_Encuesta_ID);
                    no = pstmt_setImported.executeUpdate();
                    //
                    conn.commit();
                }
            rs.close();
            pstmt.close();
	    rs=null;
	    pstmt=null;

            //
            pstmt_insertEncuesta.close();
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
        sql = new StringBuffer ("UPDATE I_EXME_Encuesta "
            + "SET I_IsImported='N', Updated=SysDate "
            + "WHERE I_IsImported<>'Y'");
        no = DB.executeUpdate(sql.toString());
        addLog (0, null, new BigDecimal (no), "@Errors@");
        addLog (0, null, new BigDecimal (noInsert), "@I_Exme_Encuesta_ID@: @Inserted@");
        addLog (0, null, new BigDecimal (noUpdate), "@I_Exme_Encuesta_ID@: @Updated@");
        return "";
    }
}