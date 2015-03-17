package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
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

public class ImportUOM_TRL extends SvrProcess {

    
	/**	Client to be imported to		*/
	private int			 	m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean	m_deleteOldImported = false;


    /**
     * Constructor por defecto.
     */

    public ImportUOM_TRL() {
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

			sql = new StringBuffer ("DELETE I_UOM_TRL "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(),this.get_TrxName());
			log.info("Delete Old Impored =" + no);

		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_UOM_TRL "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
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

		


		
//		establecemos la unidad de medida
		sql = new StringBuffer("UPDATE I_UOM_TRL iut " +
		        "SET C_UOM_ID = (SELECT C_UOM_ID " +
		        " FROM C_UOM u " +		        
		        " WHERE trim(u.X12DE355) =trim(iut.X12DE355) AND "+
		        "  u.AD_Client_ID IN  (iut.AD_Client_ID,0)) " +
		        "WHERE iut.X12DE355 IS NOT NULL " +
		        "AND iut.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_UOM_ID=" + no);
		

		
		
	
		DB.commit(true,this.get_TrxName());
		
		//
		int noInsert = 0;
        int noUpdate = 0;   
        int noErrores=0;

		
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        
    		//	Insertar categoria de productos a partir de la importacion
			PreparedStatement pstmt_insertTraduccion = DB.prepareStatement
				("INSERT INTO C_UOM_TRL (C_UOM_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ " UOMSymbol, Name, Description,AD_Language,IsTranslated) "
				+ "SELECT C_UOM_ID,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ " TRIM(UOMSymbol), Name, Description,AD_Language,IsTranslated "
				+ "FROM I_UOM_TRL "
				+ "WHERE C_UOM_ID=? AND AD_Language=? ",this.get_TrxName());


			//	Actualiza Unidad de medida desde la importacion
			PreparedStatement pstmt_updateTraduccion = DB.prepareStatement
				("UPDATE C_UOM_TRL "
				+ "SET (UOMSymbol,Name,Description,AD_Language,IsTranslated,Updated,UpdatedBy)= "
				+ "(SELECT TRIM(UOMSymbol),Name,Description,TRIM(AD_Language),IsTranslated,SysDate,UpdatedBy"
				+ " FROM I_UOM_TRL WHERE I_UOM_TRL_ID=?) "
				+ "WHERE C_UOM_ID=? AND AD_Language=?",this.get_TrxName());

			
			//	Set Imported = Y
			PreparedStatement pstmt_setImported =DB.prepareStatement
				("UPDATE I_UOM_TRL SET I_IsImported='Y',  "
				+ "Updated=SysDate, Processed='Y' WHERE I_UOM_TRL_ID=?",this.get_TrxName());

			
			// Todos los registros
			log.fine("start inserting/updating ...");
			sql = new StringBuffer ("SELECT I_UOM_TRL_ID, t.C_UOM_ID, "+
					" trim(it.AD_Language), it.C_UOM_ID "+
					" FROM I_UOM_TRL it "+
					" LEFT JOIN C_UOM u on(trim(u.X12DE355)=trim(it.X12DE355)) "+ 
					" LEFT JOIN C_UOM_TRL t on(t.C_UOM_ID=it.C_UOM_ID and trim(t.AD_LANGUAGE)=trim(it.AD_LANGUAGE)) "+
					" WHERE it.C_UOM_ID is not null "					
				+ " AND it.I_IsImported='N' AND it.AD_Client_ID=" + String.valueOf(m_AD_Client_ID));

			
			pstmt = DB.prepareStatement(sql.toString(),this.get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int I_UOM_TRL_ID = rs.getInt(1);
				int C_UOM_ID = rs.getInt(2);
				String idioma= rs.getString(3);
				int real_C_UOM_ID= rs.getInt(4);
				boolean newUOM_TRL = C_UOM_ID == 0;
				
				log.fine("I_UOM_TRL_ID=" + I_UOM_TRL_ID + ", C_UOM_TRL_ID=" + I_UOM_TRL_ID);

				//	Unidad de medida
				if (newUOM_TRL)			//	Insertar nueva unidad de medida
				{
					try
					{
			
						if (real_C_UOM_ID <= 0)
							throw new DBException("No Next ID (" + real_C_UOM_ID + ")");
						
						pstmt_insertTraduccion.setInt(1,  real_C_UOM_ID);
						pstmt_insertTraduccion.setString(2, idioma);
						//
						no = pstmt_insertTraduccion.executeUpdate();
						log.finer("Insertar unidad de medida traduccion= " + no);
						noInsert++;
					}
					catch (Exception ex)
					{
						    errorActualiza(sql,I_UOM_TRL_ID ," No fue posible insertar el registro C_UOM_Trl, " + ex.getMessage(),this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
	                        noErrores++;
	                        continue;
					}
					
					
					
				}
				else					//	Update Traduccion
				{
					pstmt_updateTraduccion.setInt(1, I_UOM_TRL_ID);
					pstmt_updateTraduccion.setInt(2, C_UOM_ID);
					pstmt_updateTraduccion.setString(3, idioma);
					try
					{
						no = pstmt_updateTraduccion.executeUpdate();
						log.finer("Actualizar Unidad de Medida Traduccion= " + no);
						noUpdate++;
					}
					catch (SQLException ex)
					{
					    errorActualiza(sql,I_UOM_TRL_ID," No fue posible insertar el registro C_UOM_Trl," + ex.getMessage() ,this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                        noErrores++;
                        continue;
					}
					
				}
				
				//	Update 
				pstmt_setImported.setInt(1, I_UOM_TRL_ID);
				no = pstmt_setImported.executeUpdate();
				
		 	    //completamos la transaccion
			      DB.commit(true,  this.get_TrxName());
			      
			}
		
			if(rs!=null)
			   rs.close();
			
			if(pstmt!=null)
			 pstmt.close();

			
			if(pstmt_insertTraduccion!=null)
				pstmt_insertTraduccion.close();
			
			if(pstmt_updateTraduccion!=null)
			     pstmt_updateTraduccion.close();
			
			if(pstmt_setImported!=null)
				pstmt_setImported.close();
			
            pstmt_setImported=null;
            pstmt_updateTraduccion=null;
            pstmt_insertTraduccion=null;
            rs=null;
			pstmt=null;
            
		addLog (0, null, new BigDecimal (noErrores), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_UOM_TRLt@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_UOM_TRL@: @Updated@");       
		
        Trx.get(this.get_TrxName(), false).close();

        

        return "";

    }

	    
	    
	private  void errorActualiza(StringBuffer sql, int ID,String error, String trxName)
	    {
	    	try
	    	{
	        DB.rollback(false, trxName);     			        
	          
	    	  log.warning(error);
			  sql = new StringBuffer ("UPDATE I_UOM_TRL  " 
			+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || '" + error 
			+ "'  WHERE  I_UOM_TRL_ID=" + String.valueOf(ID));
				 
				DB.executeUpdate(sql.toString(), trxName);
	    	}
	    	catch(Exception ex)
	    	{
	    		
	    	}
	    }

}