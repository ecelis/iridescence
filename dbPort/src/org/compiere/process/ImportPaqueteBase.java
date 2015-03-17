package org.compiere.process;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import org.compiere.model.MEXMEPaqBase;
import org.compiere.model.MPaqBase;
import org.compiere.model.X_I_EXME_PaqBase;
import org.compiere.util.DB;
import org.compiere.util.Trx;



/**

 * Proceso para importar los paquetes base
 *
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/10/11 17:12:08 $<p>
 *
 * @author Omar Torres Atonal
 * @version $Revision: 1.7 $
 */

public class ImportPaqueteBase extends SvrProcess {

    
	/**	Client to be imported to		*/

	private int			 	m_AD_Client_ID = 0;
	
	/** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;

	/**	Delete old Imported				*/

	private boolean	m_deleteOldImported = false;


    /** Sobreescribir version lista precios */

   

	

    /**

     * Constructor por defecto.

     */

    public ImportPaqueteBase() {

        super();

    }

    

    

    /**

     * Obtener los valores de los par&aacute;metros

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

        

    }

    

    /**

     * Corre el proceso.

     * @return Un mensaje de estado

     * @throws Exception

     */

    protected String doIt() throws Exception {

        

        StringBuffer sql = null;

		int no = 0;

		//aumentar la parte del cliente en el sql

		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;





		//	Delete Old Imported

		if (m_deleteOldImported)

		{

			sql = new StringBuffer ("DELETE I_EXME_PaqBase "

				+ "WHERE I_IsImported='Y'").append(clientCheck);

			no = DB.executeUpdate(sql.toString(),this.get_TrxName());

			log.info("Delete Old Impored =" + no);

		}



		//	Set Client, Org, IaActive, Created/Updated,

		sql = new StringBuffer ("UPDATE I_EXME_PaqBase "

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

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());

		log.info("Reset=" + no);

		
		//establecemos la organizacion
		sql = new StringBuffer("UPDATE I_EXME_PaqBase ipb " +
		        "SET EXME_PaqBase_ID = (SELECT EXME_PaqBase_ID " +
		        " FROM EXME_PaqBase pb WHERE trim(pb.Value) =trim(ipb.Value) "+
		        " AND pb.AD_Client_ID IN  (ipb.AD_Client_ID,0)) " +
		        "WHERE ipb.Value IS NOT NULL " +
		        "AND ipb.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_PaqBase_ID=" + no);
		
		//establecemos la organizacion padre
		sql = new StringBuffer("UPDATE I_EXME_PaqBase ipb " +
		        "SET C_BPartner_ID = (SELECT C_BPartner_ID " +
		        " FROM C_BPartner bp WHERE trim(bp.Value) =trim(ipb.C_BPartner_Value) "+
		        " AND bp.AD_Client_ID IN  (ipb.AD_Client_ID,0)) " +
		        "WHERE ipb.C_BPartner_Value IS NOT NULL " +
		        "AND ipb.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_BPartner_ID=" + no);

		
		DB.commit(true,this.get_TrxName());
		
		//
		int noInsert = 0;
        int noUpdate = 0;   
        int noErrores=0;

		
		
		// Todos los registros

		log.fine("start inserting/updating ...");

		sql = new StringBuffer ("SELECT I_EXME_PaqBase_ID "

			+ "FROM I_EXME_PaqBase WHERE I_IsImported='N'").append(clientCheck)

            .append(" AND Value IS NOT NULL ");

			
        
        Statement stmt = null;
        ResultSet rs = null;

		try {

		    stmt = DB.createStatement();

			rs = stmt.executeQuery(sql.toString());

			int id;
		
			while(rs.next()) 
			{
				  Boolean nuevoRegistro;
				   
				try
				{
			        id = rs.getInt("I_EXME_PaqBase_ID");

			         X_I_EXME_PaqBase ipb = new X_I_EXME_PaqBase(getCtx(), id, get_TrxName());
                            	
                    MEXMEPaqBase pb= new MEXMEPaqBase(this.getCtx(),ipb.getEXME_PaqBase_ID(),this.get_TrxName());
                    nuevoRegistro=pb.getEXME_PaqBase_ID()==0?true:false;                
             
                   pb.setValue(ipb.getValue());
                   pb.setName(ipb.getName());
                   pb.setDescription(ipb.getDescription());
                   pb.setIsMiniPack(ipb.isMiniPack());
              //     pb.setC_BPartner_ID(ipb.getC_BPartner_ID());
                   ipb.setProcessed(true);
                   ipb.setProcessing(true);
                   ipb.setI_IsImported(true);
                   
                  if(!(pb.save(this.get_TrxName()) && ipb.save(this.get_TrxName())))
      			     {                	                	              	 
                	    errorActualiza(sql,id," No fue posible insertar el registro EXME_PaqBase,",this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                        noErrores++;
                        continue;
                     }
      			                         	 
              	    //completamos la transaccion
			      DB.commit(true,  this.get_TrxName());
			      
				}
			catch (Exception e)
			    {
				    DB.rollback(false, this.get_TrxName());
			         e.printStackTrace();
			         log.warning("Importando Organizacion:" + e);
			         noErrores++;
			         continue;
			    }
			
			
			 if( nuevoRegistro!=null && nuevoRegistro)
	                noInsert++;
    	     else
    		     noUpdate++;
    	  
			
		    }
			
		} catch (Exception e) 
		{

		    DB.rollback(false, this.get_TrxName());
		    e.printStackTrace();
		    log.warning("Importando Organizacion:" + e);

		}finally
		{
            if(rs != null)
                rs.close();
            
            if(stmt != null)
               stmt.close();
            
            rs=null;
            stmt=null;
		}
		
		addLog (0, null, new BigDecimal (noErrores), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_PaqBase@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_PaqBase@: @Updated@");       
		
        Trx.get(this.get_TrxName(), false).close();

        

        return "";

    }



	    
	    
	private  void errorActualiza(StringBuffer sql, int ID,String error, String trxName)
	    {
	    	try
	    	{
	        DB.rollback(false, trxName);     			        
	          
	    	  log.warning(error);
			  sql = new StringBuffer ("UPDATE I_EXME_PaqBase  " 
			+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '" + error 
			+ "'  WHERE I_EXME_PaqBase_ID=" + String.valueOf(ID));
				 
				DB.executeUpdate(sql.toString(), trxName);
	    	}
	    	catch(Exception ex)
	    	{
	    		
	    	}
	    }

}