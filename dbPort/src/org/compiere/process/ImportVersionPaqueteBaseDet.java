package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.compiere.model.MEXMEPaqBaseDet;
import org.compiere.model.MEXMEPaqBaseTax;
import org.compiere.model.MEXMEPaqBaseVersion;
import org.compiere.model.MEXMEProduct;
import org.compiere.model.MTax;
import org.compiere.model.X_I_EXME_PaqBaseDet;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**

 * Proceso para importar los paquetes base
 *
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/10/11 17:12:08 $<p>
 *
 * @author Omar Torres Atonal
 * @version $Revision: 1.7 $
 */

public class ImportVersionPaqueteBaseDet extends SvrProcess {

    
	/**	Client to be imported to		*/
	private int			 	m_AD_Client_ID = 0;
	
	 /** Organization to be imported to  */
    private int             m_AD_Org_ID = 0;

	/**	Delete old Imported				*/
	private boolean	m_deleteOldImported = false;


    /**
     * Constructor por defecto.
     */

    public ImportVersionPaqueteBaseDet() {
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

			sql = new StringBuffer ("DELETE I_EXME_PaqBaseDet "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(),this.get_TrxName());
			log.info("Delete Old Impored =" + no);

		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = new StringBuffer ("UPDATE I_EXME_PaqBaseDet "
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
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET EXME_PaqBase_Version_ID = (SELECT EXME_PaqBase_Version_ID " +
		        " FROM EXME_PaqBase_Version pbvd WHERE trim(pbvd.Name) =trim( ipbvd.EXME_PaqBase_Version_Value) AND isactive='Y' "+
		        " AND pbvd.AD_Client_ID IN  (ipbvd.AD_Client_ID,0)) " +
		        "WHERE ipbvd.EXME_PaqBase_Version_Value IS NOT NULL " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_PaqBase_Version_ID=" + no);
		
		//establecemos la version del paquete base detalle
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET EXME_PaqBaseDet_ID = (SELECT EXME_PaqBaseDet_ID " +
		        " FROM EXME_PaqBaseDet pbvd WHERE pbvd.EXME_PaqBase_Version_ID=ipbvd.EXME_PaqBase_Version_ID "+
		        " AND pbvd.AD_Client_ID IN  (ipbvd.AD_Client_ID,0) AND pbvd.secuencia=ipbvd.secuencia) " +
		        "WHERE ipbvd.EXME_PaqBase_Version_ID IS NOT NULL AND ipbvd.secuencia IS NOT NULL " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set EXME_PaqBaseDet_ID=" + no);
		
		
		
		//establecemos el producto
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET (M_Product_ID, Name, Description) = (SELECT M_Product_ID, Name, Description " +
		        " FROM M_Product p WHERE trim(p.Value) = trim(ipbvd.M_Product_Value) "+
		        " AND p.AD_Client_ID IN  (ipbvd.AD_Client_ID,0))  " +
		        "WHERE ipbvd.M_Product_Value IS NOT NULL  " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set M_Product_ID=" + no);

		
		//establecemos la unidad de medida del producto
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET C_UOM_ID = (SELECT C_UOM_ID " +
		        " FROM C_UOM uom WHERE trim(uom.X12DE355) = trim(ipbvd.C_UOM_Value) "+
		        " AND uom.isactive='Y' " +
		        " AND uom.AD_Client_ID IN  (ipbvd.AD_Client_ID,0))  " +
		        "WHERE ipbvd.C_UOM_Value IS NOT NULL " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);
		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_UOM_ID=" + no);

		
//		establecemos el tipo de moneda
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET C_Currency_ID = (SELECT C_Currency_ID " +
		        " FROM C_Currency c WHERE trim(c.ISO_CODE) =trim( ipbvd.C_Currency_Value) "+
		        " AND c.AD_Client_ID IN  (ipbvd.AD_Client_ID,0)) " +
		        "WHERE ipbvd.C_Currency_Value IS NOT NULL " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_Currency_ID=" + no);
		
//		establecemos el tipo de impuesto
		sql = new StringBuffer("UPDATE I_EXME_PaqBaseDet ipbvd " +
		        "SET C_Tax_ID = (SELECT C_Tax_ID " +
		        " FROM C_Tax t WHERE trim(t.Name) =trim( ipbvd.C_Tax_Value) "+
		        " AND t.AD_Client_ID IN  (ipbvd.AD_Client_ID,0)) " +
		        "WHERE ipbvd.C_Tax_Value IS NOT NULL " +
		        "AND ipbvd.I_IsImported = 'N' ").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),this.get_TrxName());
		log.info("Set C_Tax_ID=" + no);
		
	
		DB.commit(true,this.get_TrxName());
		
		//
		int noInsert = 0;
        int noUpdate = 0;   
        int noErrores=0;

		
		// Todos los registros
		log.fine("start inserting/updating ...");
		sql = new StringBuffer ("SELECT I_EXME_PaqBaseDet_ID "
			+ "FROM I_EXME_PaqBaseDet WHERE I_IsImported='N'").append(clientCheck)
            .append(" AND EXME_PaqBase_Version_ID IS NOT NULL AND Secuencia  IS NOT NULL");

		//Obtenemos la lista de versiones de paquetes que se van a importar
		StringBuilder sqlVersiones = new StringBuilder("SELECT DISTINCT EXME_PAQBASE_VERSION_ID "
				+ "FROM I_EXME_PaqBaseDet WHERE I_IsImported='N'").append(clientCheck)
	            .append(" AND EXME_PaqBase_Version_ID IS NOT NULL AND Secuencia  IS NOT NULL");	
		List<Integer> versiones = new ArrayList<Integer>();
		
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ResultSet rsV = null;

		try {
			//Guardar en un areglo las diferentes versiones de paquetes
			pstmt = DB.prepareStatement (sqlVersiones.toString(), get_TrxName());
			rsV = pstmt.executeQuery();
			while(rsV.next()) {//FIXME Error por indice
				versiones.add(rsV.getInt("EXME_PAQBASE_VERSION_ID"));
			}
			
			//Statement principal
		    stmt = DB.createStatement();
			rs = stmt.executeQuery(sql.toString());
			int id = 0;

			while(rs.next()) {
				Boolean nuevoRegistro;
				id = 0;
				
				try {
			        id = rs.getInt("I_EXME_PaqBaseDet_ID");

					X_I_EXME_PaqBaseDet ipbvd = new X_I_EXME_PaqBaseDet(getCtx(), id, get_TrxName());
                            	
					MEXMEPaqBaseDet pbvd= new  MEXMEPaqBaseDet(this.getCtx(),ipbvd.getEXME_PaqBaseDet_ID(),this.get_TrxName());
                    nuevoRegistro=pbvd.getEXME_PaqBaseDet_ID()==0?true:false;
                    
                    //Traer el Producto para obtener las unidades de medida.
                    //Jesus Cantu
                    final MEXMEProduct product = new MEXMEProduct(getCtx(),
                    		ipbvd.getM_Product_ID(), null);
                    
                    //Una vez trayendo el producto, seteamos sus unidades de medida. Jesus Cantu
                    pbvd.setC_UOM_ID(product.getC_UOM_ID());
                    pbvd.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
                    
                    //Si el value de la Unidad de Medida no existe, lanzar mensaje de Error.
                    if (ipbvd.getC_UOM_ID() == 0) { //msg.error.uom
                    	throw new Exception(Utilerias.getAppMsg(getCtx(), "msg.error.uom", ipbvd.getC_UOM_Value()));
                    }
                    
                    //Ahora se requiere calcular las conversiones de las cantidades a la mano y de volumen. Jesus Cantu
        			final BigDecimal[] qtys = product.qtyConversion(ipbvd.getCantidad(), ipbvd.getC_UOM_ID());
        			
                    //Si las cantidades son nulas, entonces la Unidad de Medida en el archivo no coincide con las del producto.
                    if (qtys[0] == null && qtys[1] == null) {
                    	throw new Exception(Utilerias.getAppMsg(getCtx(), "msg.error.uomNoCoincide", 
                    			ipbvd.getC_UOM_Value(), ipbvd.getM_Product_Value(), product.getName()));
                    }
                    
                    //Si la Unidad de Medida del Layout no coincide con al menos una de las del producto, mandar mensaje de error.
                    if (ipbvd.getC_UOM_ID() != product.getC_UOM_ID() && 
                    		ipbvd.getC_UOM_ID() != product.getC_UOMVolume_ID()) {
                    	throw new Exception(Utilerias.getAppMsg(getCtx(), "msg.error.uomNoCoincide", 
                    			ipbvd.getC_UOM_Value(), ipbvd.getM_Product_Value(), product.getName()));
                    } 
                    	
        			pbvd.setCantidadAlm((qtys[0]));
        			pbvd.setCantidadVol(qtys[1]); 
        			
             
                    pbvd.setEXME_PaqBase_Version_ID(ipbvd.getEXME_PaqBase_Version_ID());
					pbvd.setM_Product_ID(ipbvd.getM_Product_ID());
					
					pbvd.setC_Currency_ID(ipbvd.getC_Currency_ID());
					pbvd.setSecuencia(ipbvd.getSecuencia());
					pbvd.setName(ipbvd.getName());
					pbvd.setDescription(ipbvd.getDescription());
					pbvd.setCantidad(ipbvd.getCantidad());
					pbvd.setPriceList(ipbvd.getPriceList());
					pbvd.setPriceActual(ipbvd.getPriceActual());
					pbvd.setPriceLimit(ipbvd.getPriceLimit());
					pbvd.setCostStandard(ipbvd.getCostStandard());
					pbvd.setCostAverage(ipbvd.getCostAverage());
					pbvd.setPriceLastPO(ipbvd.getPriceLastPO());
					
					//Seteamos la unidad de medida que traemos desde el archivo de Importacion. Jesus Cantu
					pbvd.setOp_Uom(ipbvd.getC_UOM_ID());
					
					// importe sin impuesto. Jesus Cantu
					pbvd.setLineNetAmt(pbvd.getCantidad().multiply(pbvd.getPriceActual()));
					
					//Se agrega el tax id Del Producto. Jesus Cantu
					//Traer el rate para guardar el taxamount
					MTax tax = new MTax(getCtx(), product.getC_Tax_ID(), get_TrxName());
					
					pbvd.setC_Tax_ID(product.getC_Tax_ID());
					
					if (tax != null && !tax.getRate().equals(BigDecimal.ZERO)) {
						pbvd.setTaxAmt(pbvd.getLineNetAmt().multiply(tax.getRate().divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP));	
					} else {
						pbvd.setTaxAmt(tax.getRate());
					}
					
					
					pbvd.setCosto(ipbvd.getCosto());
					
					ipbvd.setProcessed(true);
					ipbvd.setProcessing(true);
					ipbvd.setI_IsImported(true);
					
					// total
					pbvd.setLineTotalAmt(pbvd.getLineNetAmt().add(pbvd.getTaxAmt()));
					// gisela lee
					
					if(!(pbvd.save(this.get_TrxName()) && ipbvd.save(this.get_TrxName()))) {                	                	              	 
                	    errorActualiza(sql, id, " No fue posible insertar el registro EXME_PaqBaseDet, ", this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                        noErrores++;
                        continue;
                    }
      			                         	 
              	    //completamos la transaccion
					DB.commit(true,  this.get_TrxName());
			      
				} catch (Exception e) {
					log.log(Level.SEVERE, "ImportVersionPaqueteBaseDet.doIt", e);
					errorActualiza(sql, id, e.getMessage().toString(), this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
					noErrores++;
					continue;
				}
			
			
				if (nuevoRegistro != null && nuevoRegistro)
					noInsert++;
				else
					noUpdate++;
    	  
			} // while
			
			//gisela lee
			for (int i=0; i<versiones.size(); i++) {
				MEXMEPaqBaseVersion pbv = new MEXMEPaqBaseVersion(getCtx(), Integer.parseInt(versiones.get(0).toString()), get_TrxName());
				
				//Calcular totales para la version
				pbv.setTotalAmt(); //sum(cantidad * priceactual)
				pbv.setBaseAmt();  //sum(cantidad * pricelist)
				pbv.setTaxAmt();   //sum((cantidad*priceactual) * rate/100)
				
				if(!pbv.save(get_TrxName())) {
					errorActualiza(sql,id," No fue posible actualizar los totales de la version " + pbv.getName(),this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                    noErrores++;
                    continue;
				}
				
				//Calcular impuestos para la version
				calculoTax(pbv.getEXME_PaqBase_Version_ID(), pbv.getBaseAmt(), get_TrxName());
			}
			 //completamos la transaccion
		      DB.commit(true,  this.get_TrxName());

			//gisela lee
			for (int i=0; i<versiones.size(); i++) {
				MEXMEPaqBaseVersion pbv = new MEXMEPaqBaseVersion(getCtx(), Integer.parseInt(versiones.get(0).toString()), get_TrxName());
				
				//Calcular totales para la version
				pbv.setTotalAmt(); //sum(cantidad * priceactual)
				pbv.setBaseAmt();  //sum(cantidad * pricelist)
				pbv.setTaxAmt();   //sum((cantidad*priceactual) * rate/100)
				
				if (!pbv.save(get_TrxName())) {
					errorActualiza(sql,id," No fue posible actualizar los totales de la version " + pbv.getName(),this.get_TrxName());  //Guarda un mensaje de error en el registro actual                		      
                    noErrores++;
                    continue;
				}
				
				//Calcular impuestos para la version
				calculoTax(pbv.getEXME_PaqBase_Version_ID(), pbv.getBaseAmt(), get_TrxName());
			}
			 //completamos la transaccion
		      DB.commit(true,  this.get_TrxName());
		} catch (Exception e) {
		    DB.rollback(false, this.get_TrxName());
		    log.log(Level.SEVERE, "ImportVersionPaqueteBaseDet.doIt", e);
		    log.warning("Importando EXME_PaqBaseDet:" + e);

		} finally {
            if (rs != null) {
                rs.close();
            }
            
            if (stmt != null) {
               stmt.close();
            }
            
            if (rsV != null) {
                rsV.close();
            }
            
            if (pstmt != null) {
               pstmt.close();
            }
		}
		
		addLog (0, null, new BigDecimal (noErrores), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_PaqBaseDet@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_PaqBaseDet@: @Updated@");       
		
        Trx.get(this.get_TrxName(), false).close();

        return "";
    }

	private  void errorActualiza(StringBuffer sql, int ID,String error, String trxName) {
	    	try 	{
	    		DB.rollback(false, trxName);     			        
	          
	    	    log.warning(error);
			    sql = new StringBuffer ("UPDATE I_EXME_PaqBaseDet  " 
			    + " SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || '" + error
			    + "'  WHERE I_EXME_PaqBaseDet_ID=" + String.valueOf(ID));
				 
				DB.executeUpdate(sql.toString(), trxName);
				DB.commit(true,  trxName);
	    	} catch(Exception ex) {
			    log.log(Level.SEVERE, "ImportVersionPaqueteBaseDet.errorActualiza", ex);
	    	}
	    }
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_PacBase_Version_ID
	 * @param totalBruto
	 * @param m_trx
	 */
	public void calculoTax (int EXME_PaqBase_Version_ID, 
			BigDecimal totalBruto, String trxName) {
		
		//Borramos las lineas anteriores que posiblemente existan de impuestos
		MEXMEPaqBaseTax.getDelTax(getCtx(), EXME_PaqBase_Version_ID, trxName);
	}	
}