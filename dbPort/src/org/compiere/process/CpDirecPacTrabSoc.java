package org.compiere.process;

import java.sql.Statement;
import java.util.logging.Level;

import org.compiere.model.MLocation;
import org.compiere.model.X_EXME_Paciente_TS;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 22/06/2006 <p>
 * <b>Por        : </b> mrojas <p>
 * <b>Modificado : </b> $Date: 2006/07/21 23:33:08 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author mrojas
 * @version $Revision: 1.3 $
 */
public class CpDirecPacTrabSoc extends SvrProcess {

    //address to copy
    private int m_C_Location_Perm_ID = 0;
    //zone (delegation) to copy
    private int m_EXME_Delegacion_Perm_ID = 0;
    
    //destination address
    private int m_C_Location_D_Resp_ID = 0;
    
    //social work patient
    private int m_EXME_Paciente_TS_ID = 0;
    
    //transaction name
    private String trxName = null;
    
    //process exit message
    private String msg;
    
    //current s.w. pat. record
    private X_EXME_Paciente_TS swPat = null;
    
    /**
     * @see org.compiere.process.SvrProcess#prepare()
     */
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++)
        {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null)
                ;
            else if (name.equals("C_Location_Perm_ID"))
                m_C_Location_Perm_ID = para[i].getParameterAsInt();
            else if (name.equals("C_Location_D_Resp_ID"))
                m_C_Location_D_Resp_ID = para[i].getParameterAsInt();
            else if (name.equals("EXME_Delegacion_Perm_ID"))
                m_EXME_Delegacion_Perm_ID = para[i].getParameterAsInt();
            else if (name.equals("EXME_Paciente_TS_ID"))
                m_EXME_Paciente_TS_ID = para[i].getParameterAsInt();
            else
                log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
        }
    }

    /**
     * @see org.compiere.process.SvrProcess#doIt()
     */
    protected String doIt() throws Exception {
        
        try {
            trxName = Trx.createTrxName();
            
            //load current s.w. pat. record
            swPat =
                new X_EXME_Paciente_TS(
                        getCtx(),
                        m_EXME_Paciente_TS_ID,
                        trxName
                );
            
            //patient address
            if(m_C_Location_Perm_ID == 0)
                return "";
            
            MLocation locPat = new MLocation(getCtx(), m_C_Location_Perm_ID, null);
            log.info("Social Work Patient's Address : " + locPat);
                        
            //responsible address
            MLocation locResp = null;
            if(m_C_Location_D_Resp_ID == 0)
                locResp = new MLocation(getCtx(), 0, trxName);
            else
                locResp = new MLocation(getCtx(), m_C_Location_D_Resp_ID, trxName);
            
            locResp.setAddress1(locPat.getAddress1());
            locResp.setAddress2(locPat.getAddress2());
            locResp.setAddress3(locPat.getAddress3());
            locResp.setAddress4(locPat.getAddress4());
            locResp.setC_City_ID(locPat.getC_City_ID());
            locResp.setC_Country_ID(locPat.getC_Country_ID());
            locResp.setC_Region_ID(locPat.getC_Region_ID());
            locResp.setCity(locPat.getCity());
            locResp.setCountry(locPat.getCountry());
            locResp.setPostal(locPat.getPostal());
            locResp.setPostal_Add(locPat.getPostal_Add());
            locResp.setRegion(locPat.getRegion());
            locResp.setRegionName(locPat.getRegionName());
            locResp.setNumExt(locPat.getNumExt());
            locResp.setNumIn(locPat.getNumIn());
            
            if(!locResp.save(trxName)) {
                log.severe("Can not save responsible address.");
                DB.rollback(true, trxName);
                Trx.get(trxName, false).close();
                throw new Exception(Msg.getMsg(getCtx(), "NoSaveRespAdd"));
            }
                
            log.info("Social Work Patient's Address : " + locPat);
            
            swPat.setC_Location_D_Resp_ID(locResp.getC_Location_ID());
            
            //zone (delegation)
            if(m_EXME_Delegacion_Perm_ID != 0)
                swPat.setEXME_Delegacion_D_Resp_ID(m_EXME_Delegacion_Perm_ID);
            
            if(!swPat.save(trxName)) {
                log.severe("Can not update social work patient information.");
                DB.rollback(true, trxName);
                Trx.get(trxName, false).close();
                throw new Exception(Msg.getMsg(getCtx(), "NoUpdateSWPat"));
            }
            
            if(!DB.commit(true, trxName)) {
                log.severe("Can not commit changes to database.");
                DB.rollback(true, trxName);
                Trx.get(trxName, false).close();
                throw new Exception(Msg.getMsg(getCtx(), "NoCommitTrx"));
            }
            
            updateMunicipio("update c_location set EXME_TownCouncil_ID=" +  locPat.getEXME_TownCouncil_ID() + " where c_location_id=" + locResp.getC_Location_ID());
            
            msg = Msg.getMsg(getCtx(), "RespAddSaved");
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "Copying patient address", e);
            msg = e.getMessage();
        } finally {
            Trx.get(trxName, false).close();
        }
        
        return msg;
    }


    private boolean updateMunicipio(String query)
    {
       boolean resp=false;
          
       Statement state= null;          
       
       
        try
        {
            
           state= DB.createStatement();          
           state.executeUpdate(query);
           
           state.close();
           
        
        }
        catch(Exception ex)
        {
        	resp=false;
        }
        
        return resp;
        
        
    }




}
