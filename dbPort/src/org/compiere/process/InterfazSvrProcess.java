/*
 * Created on 8/09/2005
 *
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import org.compiere.model.MCountry;
import org.compiere.model.MInterfazProcessor;
import org.compiere.model.MInterfazProcessorLog;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
//import org.compiere.util.ProgressCnn;
import org.compiere.util.Trx;

/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 8/09/2005 <p>
 * <b>Por        : </b> alberto reyes<p>
 * <b>Modificado : </b> $Date: 2006/02/20 22:40:01 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author alberto reyes
 * @version $Revision: 1.2 $
 */
public abstract class InterfazSvrProcess extends SvrProcess {
    
    /**
     * Cliente y Organizacion que se afecta.
     */
    protected int m_client_id = 0;
    
    protected int m_org_id = 0;
    
    /**
     * Procesador de Interfaces y Log.
     */
    protected MInterfazProcessor m_processor = null;
    
    protected StringBuffer m_summary = new StringBuffer("    ");
    protected StringBuffer m_reference = new StringBuffer();
    protected StringBuffer m_textmsg = new StringBuffer();
    protected StringBuffer m_errormsg = new StringBuffer();
    
    /**
     * Conexion a Progress
     */
    protected Connection cnn_pgs = null;
    protected String trx_pgs = null;
    
    /**
     * Conexion a Interfaz
     */
    protected Connection cnn_int = null;
    protected String trx_int = null;
    
    /**
     * Conexion a Oracle
     */
    protected String trx_ora = null;
    protected Connection cnn_ora = null;
    
    protected int m_InterfazProcessor_ID = 0;
    
    protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    protected SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
    protected String clientOrg = null;

    /**
     * 
     */
    public InterfazSvrProcess() {
        super();
    }

    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#prepare()
     */
    protected void prepare() {
        
        log.info("prepare()");
        
        ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("EXME_InterfazProcessor_ID"))
				m_InterfazProcessor_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		log.log(Level.INFO, "Parameter: EXME_InterfazProcessor_ID = " + m_InterfazProcessor_ID);
		
		m_processor = new MInterfazProcessor(getCtx(), m_InterfazProcessor_ID, null);
		
		m_client_id = m_processor.getAD_Client_Target_ID();
		m_org_id = m_processor.getAD_Org_Target_ID();
		clientOrg = " AD_Client_ID = " + m_client_id + " AND AD_Org_ID IN (0," + m_org_id + ")";
		
   //     cnn_int = ProgressCnn.createConnection_i();
        
   //     cnn_pgs = ProgressCnn.createConnection();

        trx_ora = Trx.createTrxName();
        Trx trx = Trx.get(trx_ora, true);
        
        cnn_ora = trx.getConnection();

    }

    /* (non-Javadoc)
     * @see org.compiere.process.SvrProcess#doIt()
     */
    protected String doIt() throws Exception {
        MInterfazProcessorLog m_log = new MInterfazProcessorLog(m_processor, null);
        m_log.setSummary(m_summary.toString());
        m_log.setReference(m_reference.toString());
        
        if(m_errormsg.length() > 0){
            m_log.setIsError(true);
            m_log.setTextMsg(m_errormsg.toString());
        }else{
            m_log.setIsError(false);
            m_log.setTextMsg(m_textmsg.toString());
        }
        
        m_log.save();
        return null;
    }
    
    /**
     * Rollback Connections
     *
     */
    protected void rollback(){
        try{
	        cnn_ora.rollback();
	        cnn_int.rollback();
	        cnn_pgs.rollback();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        log.log(Level.SEVERE, "Connections Rolledback()");
    }
    
    /**
     *Commit Connections
     *
     */
    protected void commit(){
        try{
	        cnn_ora.commit();
	        cnn_int.commit();
	        cnn_pgs.commit();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        log.log(Level.SEVERE, "Connections Commited()");
    }

    /**
     *Commit Connections
     *
     */
    protected void close(){
        try{
	        cnn_ora.close();
	        cnn_int.close();
	        cnn_pgs.close();
	        
	        cnn_ora = null;
	        cnn_int = null;
	        cnn_pgs = null;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        log.log(Level.SEVERE, "Connections Commited()");
    }
    
    protected void doLog(int prosecedRows){
        
        //Guardamos el Log.
        m_reference.append("Proc=" + m_processor.getEXME_InterfazProcessor_ID() + ", Interfaz=" + getName());
        m_summary.append("Rows=" + prosecedRows);
        
    }
    
    /**
     * Crea un MLocation (Direccion) a partir de el codigo de pais y nombre de estado (region).
     * 
     * @param cnn Conexion a Oacle activa.
     * @param rs ResultSet de origen de los codigos (Progress)
     * @param country_column Nombre de la columna del <code>rs</code> que tiene el codigo del pais.
     * @param region_column Nombre de la columna del <code>rs</code> que tiene el codigo del estado (region).
     * @return un MLocation.
     * @throws SQLException
     */
    protected MLocation getLocation(Connection cnn, ResultSet rs, String country_column, String region_column) throws SQLException{
        
        //Pais
        String pais = rs.getString(country_column);
        pais = (pais == null || pais.trim().length() == 0) ? "MX" : pais;
        int C_Country_ID = 0;
        String sql = "SELECT C_Country_ID FROM C_Country WHERE UPPER(CountryCode) = UPPER('" + pais +  "')";
        Statement stmt_qry = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs_qry = stmt_qry.executeQuery(sql);
        if(rs_qry.next())
            C_Country_ID = rs_qry.getInt("C_Country_ID");
        else
            m_errormsg.append("Coud't find the Country with CountryCode "+ pais +".\n");
        
        //Estado (Region)
        String estado = rs.getString(region_column);
        estado = (estado == null || estado.trim().length() == 0) ? "MEX" : estado;
        int C_Region_ID = 0;
        sql = "SELECT C_Region_ID FROM C_Region WHERE UPPER(name) = UPPER('" + estado +  "')";
        stmt_qry = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs_qry = stmt_qry.executeQuery(sql);
        if(rs_qry.next())
            C_Region_ID = rs_qry.getInt("C_Region_ID");
        else
            m_errormsg.append("Coud't find the Region with name "+ estado +".\n");
        
        MCountry country = new MCountry(getCtx(), C_Country_ID, trx_ora);
        MRegion region = new MRegion(getCtx(), C_Region_ID, trx_ora);
        
        MLocation location = new MLocation(country,region);
        location.setC_Country_ID(C_Country_ID);
        location.setC_Region_ID(C_Region_ID);
        return location;
    }
}
