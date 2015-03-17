package org.compiere.process;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;


import org.compiere.model.MLocation;
import org.compiere.model.X_EXME_Paciente_TS;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.model.MEXMEPaciente;
/**
 * <b>Prop&oacute;sito : </b> <p>
 * <b>Creado     : </b> 05/06/2006 <p>
 * <b>Por        : </b> Omar <p>
 * <b>Modificado : </b> $Date: 2006/07/21 23:33:09 $ <p>
 * <b>Por        : </b> $Author: mrojas $ <p>
 *
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class CpDirecPacTrabSocPerm extends SvrProcess {
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (CpDirecPacTrabSocPerm.class);

	Integer EXME_Paciente_ID=null;
	Integer EXME_Location_Perm_ID=null;
	
	
	 protected void prepare() {
		 s_log.log(Level.INFO,"entro1");
		// System.out.print("entro1");
	        ProcessInfoParameter[] para = getParameter();
	        try
	        {
	        EXME_Paciente_ID= para[0].getParameterAsInt();
	    	EXME_Location_Perm_ID= para[1].getParameterAsInt();
	        }
	        catch(Exception e)
	        {
	        	 s_log.log(Level.SEVERE,e.getMessage());
	        	// System.out.println(e.getMessage());
	        }
	    	
	 }
    //private X_EXME_Paciente_TS swPat = null;
    
        /**
     * @see org.compiere.process.SvrProcess#doIt()
     */
    protected String doIt() throws Exception {
    	
    	s_log.log(Level.INFO,"entro");
    	//System.out.print("entro");
        try {
        	
        	String trxName = Trx.createTrxName();
        	MLocation loc=null;
        	
        	if(EXME_Location_Perm_ID!=null)
        	     loc = new MLocation(getCtx(),EXME_Location_Perm_ID, trxName);
        	
        	if(loc==null)
        		loc = new MLocation(getCtx(),0, trxName);
        	
            
            MEXMEPaciente p= new MEXMEPaciente(this.getCtx(),EXME_Paciente_ID,null);
            
            if(p!=null)
            {
            
            MLocation locPat= new MLocation(getCtx(),p.getC_Location_ID(),null);
            log.info("Social Work Patient's Address : " + loc);		
            
            loc.setAddress1(locPat.getAddress1());
            loc.setAddress2(locPat.getAddress2());
            loc.setAddress3(locPat.getAddress3());
            loc.setAddress4(locPat.getAddress4());
            loc.setC_City_ID(locPat.getC_City_ID());
            loc.setC_Country_ID(locPat.getC_Country_ID());
            loc.setC_Region_ID(locPat.getC_Region_ID());
            loc.setCity(locPat.getCity());
            loc.setCountry(locPat.getCountry());
            loc.setPostal(locPat.getPostal());
            loc.setPostal_Add(locPat.getPostal_Add());
            loc.setRegion(locPat.getRegion());
            loc.setRegionName(locPat.getRegionName());
            loc.setNumExt(locPat.getNumExt());
            loc.setNumIn(locPat.getNumIn());
            
            if(!loc.save(trxName)) 
            {
                log.severe("Can not save responsible address.");
                DB.rollback(true, trxName);
                Trx.get(trxName, false).close();
                throw new Exception(Msg.getMsg(getCtx(), "NoSavePermAdd"));
            }
            else
            {
            	StringBuffer sql= new StringBuffer("select * from exme_paciente_ts where exme_paciente_id=" + EXME_Paciente_ID);
            	Integer idTs= this.obtenID(sql);
            	if(idTs!=null)
            	{
            	   X_EXME_Paciente_TS  ts= new X_EXME_Paciente_TS(getCtx(),idTs.intValue(),null);
            	 
            	   ts.setC_Location_Perm_ID(loc.getC_Location_ID());
            	   ts.save(trxName);
            	   
            	}
            }
           
             
            if(!DB.commit(true, trxName)) {
                log.severe("Can not commit changes to database.");
                DB.rollback(true, trxName);
                Trx.get(trxName, false).close();
                throw new Exception(Msg.getMsg(getCtx(), "NoCommitTrx"));
            }
            updateMunicipio("update c_location set EXME_TownCouncil_ID=" +  locPat.getEXME_TownCouncil_ID() + " where c_location_id=" + loc.getC_Location_ID());
         
         
            
           }
        }  
      catch (Exception e) 
          {
            log.log(Level.SEVERE, "Copying patient address", e);
        
        }
        
        return "";
    }
    
    
    private Integer obtenID(StringBuffer sql)
    {
       Integer exp=null;
          
       Statement state= null;          
       ResultSet registro= null;
       
        try
        {
            
           state= DB.createStatement();          
           registro= state.executeQuery(sql.toString());
           
           if(registro.next())
           {
                exp=new Integer(registro.getInt(1));
               
           }
           
           registro.close();
           state.close();
        
        }
        catch(Exception ex)
        {}finally{
            try {
                if(state!=null)
                    state.close();
                if(registro!=null)
                    registro.close();
            } catch (Exception e) {}
            registro= null;
            state=null;
        }
        
        return exp;
        
        
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
