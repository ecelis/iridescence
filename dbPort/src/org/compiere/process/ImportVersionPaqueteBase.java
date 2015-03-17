package org.compiere.process;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import org.compiere.model.MEXMEPaqBaseVersion;
import org.compiere.model.MPaqBaseVersion;
import org.compiere.model.X_I_EXME_PaqBase_Version;
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

public class ImportVersionPaqueteBase extends SvrProcess {

    
	/**	Client to be imported to		*/
	private int			 	m_AD_Client_ID = 0;
	
	 /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;

	/**	Delete old Imported				*/
	private boolean	m_deleteOldImported = false;


    /**
     * Constructor por defecto.
     */

    public ImportVersionPaqueteBase() {
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

			sql = new StringBuffer ("DELETE I_EXME_PaqBase_Version "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(),this.get_TrxName());
			log.info("Delete Old Impored =" + no);

		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_PaqBase_Version "
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
		
		//establecemos la version del paquete base
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET EXME_PaqBase_Version_ID = (SELECT EXME_PaqBase_Version_ID " +
		        " FROM EXME_PaqBase_Version pbv WHERE trim(pbv.Name) =trim( ipbv.Name) "+
		        " AND pbv.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.Name IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_PaqBase_Version_ID=" + no);
		
		//establecemos el paquete base
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET EXME_PaqBase_ID = (SELECT EXME_PaqBase_ID " +
		        " FROM EXME_PaqBase pb WHERE trim(pb.Value) =trim( ipbv.EXME_PaqBase_Value) "+
		        " AND pb.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.EXME_PaqBase_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_PaqBase_ID=" + no);


//		establecemos la lista de precios
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET M_PriceList_ID = (SELECT M_PriceList_ID " +
		        " FROM M_PriceList lp WHERE trim(lp.Name) =trim( ipbv.M_PriceList_Value) "+
		        " AND lp.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.M_PriceList_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set M_PriceList_ID=" + no);
	
//		establecemos el producto
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET M_Product_ID = (SELECT M_Product_ID " +
		        " FROM M_Product p WHERE trim(p.Value) =trim( ipbv.M_Product_Value) "+
		        " AND p.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.M_Product_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set M_Product_ID=" + no);
		
//		establecemos la organizacion transaccional
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET Ad_OrgTrx_ID = (SELECT Ad_Org_ID " +
		        " FROM Ad_Org o WHERE trim(o.Value) =trim( ipbv.Ad_OrgTrx_Value) "+
		        " AND o.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.Ad_OrgTrx_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set Ad_OrgTrx_ID=" + no);
		
//		establecemos el tipo de moneda
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET C_Currency_ID = (SELECT C_Currency_ID " +
		        " FROM C_Currency c WHERE trim(c.ISO_CODE) =trim( ipbv.C_Currency_Value) "+
		        " AND c.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.C_Currency_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_Currency_ID=" + no);
		
//		establecemos el tipo de moneda
		sql = new StringBuffer("UPDATE I_EXME_PaqBase_Version ipbv " +
		        "SET C_Tax_ID = (SELECT C_Tax_ID " +
		        " FROM C_Tax t WHERE trim(t.Name) =trim( ipbv.C_Tax_Value) "+
		        " AND t.AD_Client_ID IN  (ipbv.AD_Client_ID,0)) " +
		        "WHERE ipbv.C_Tax_Value IS NOT NULL " +
		        "AND ipbv.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_Tax_ID=" + no);
		
	
		
		DB.commit(true,this.get_TrxName());
		
		
		//
		int noInsert = 0;
        int noUpdate = 0;   
        int noErrores=0;

		
		// Todos los registros
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_EXME_PaqBase_Version_ID "
			+ "FROM I_EXME_PaqBase_Version WHERE I_IsImported='N'").append(clientCheck)
            .append(" AND Name IS NOT NULL ");

			
        
        Statement stmt = null;
        ResultSet rs = null;

		try {

		    stmt = DB.createStatement();

			rs = stmt.executeQuery(sql.toString());

			int id;
		

			while(rs.next()) 
			{
				  Boolean nuevoRegistro;
				  id=0; 
				try
				{
			        id = rs.getInt("I_EXME_PaqBase_Version_ID");

			         X_I_EXME_PaqBase_Version ipbv = new X_I_EXME_PaqBase_Version(getCtx(), id, get_TrxName());
                            	
			         MEXMEPaqBaseVersion pbv= new MEXMEPaqBaseVersion(this.getCtx(),ipbv.getEXME_PaqBase_Version_ID(),this.get_TrxName());
                    nuevoRegistro=pbv.getEXME_PaqBase_Version_ID()==0?true:false;                
             
               
                   pbv.setName(ipbv.getName());
                   pbv.setDescription(ipbv.getDescription());
                   pbv.setEXME_PaqBase_ID(ipbv.getEXME_PaqBase_ID());
                   pbv.setM_PriceList_ID(ipbv.getM_PriceList_ID());
                   pbv.setM_Product_ID(ipbv.getM_Product_ID());
                   pbv.setAD_OrgTrx_ID(ipbv.getAD_OrgTrx_ID());
                   pbv.setC_Currency_ID(ipbv.getC_Currency_ID());
                   pbv.setC_Tax_ID(ipbv.getC_Tax_ID());
                   pbv.setDiscount(ipbv.getDiscount());
                   
                   //gisela lee : calcular en base al detalle
                   //pbv.setTaxAmt(ipbv.getTaxAmt());
                   //pbv.setTotalAmt(ipbv.getTotalAmt());
                   //pbv.setBaseAmt(ipbv.getBaseAmt());
                   
                   pbv.setValidFrom(ipbv.getValidFrom());
                   pbv.setValidTo(ipbv.getValidTo());
                   ipbv.setProcessed(true);
                   ipbv.setProcessing(true);
                   ipbv.setI_IsImported(true);
                   
                  if(!(pbv.save(this.get_TrxName()) && ipbv.save(this.get_TrxName())))
      			     {                	                	              	 
                	    errorActualiza(sql,id," No fue posible insertar el registro EXME_PaqBase_Version,",this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                        noErrores++;
                        continue;
                     }
      			                         	 
              	    //completamos la transaccion
			      DB.commit(true,  this.get_TrxName());
			      
				}
			catch (Exception e)
			    {
			         errorActualiza(sql,id,e.getMessage().toString(),this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
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
		addLog (0, null, new BigDecimal (noInsert), "@EXME_PaqBase_Version@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_PaqBase_Version@: @Updated@");       
		
        Trx.get(this.get_TrxName(), false).close();

        

        return "";

    }

	    
	    
	private  void errorActualiza(StringBuffer sql, int ID,String error, String trxName)
	    {
	    	try
	    	{
	        DB.rollback(false, trxName);     			        
	          
	    	  log.warning(error);
			  sql = new StringBuffer ("UPDATE I_EXME_PaqBase_Version  " 
			+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '" + error 
			+ "'  WHERE I_EXME_PaqBase_Version_ID=" + String.valueOf(ID));
				 
				DB.executeUpdate(sql.toString(), trxName);
	    	}
	    	catch(Exception ex)
	    	{
	    		
	    	}
	    }
}
