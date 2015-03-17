package org.compiere.process;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MProduct;
import org.compiere.model.MReplenish;
import org.compiere.model.X_I_Replenish;
import org.compiere.model.X_M_Product;
import org.compiere.model.X_M_Replenish;
import org.compiere.model.X_M_Warehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Importaci&oacute;n de Productos por almacen
 *
 * 	@author 	Gisela Lee
 * 	@version 	$Id: ImportMReplenish.java,v 1.5 2006/10/11 17:12:08 vgarcia Exp $
 */
public class ImportMReplenish extends SvrProcess {
	
	/**	Cliente de donde se obtendran los productos	*/
	private int				m_Select_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/** Nivel de acceso de la tabla I_Replenish **/
	private String          m_NivelAcceso = null;
	
	/** Cliente con el que se actualizara/insertara la informacion **/
	private int				m_Insert_Client_ID = 0;
	
	/** Seccion where referente al cliente del quien se obtendran los productos **/
	//private String          m_Product_Client = "";
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID")) {
				m_Select_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			} else if (name.equals("DeleteOldImported")) {
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "ImportMReplenish.prepare - Unknown Parameter: " + name);
			}
		}
		
		if (m_DateValue == null) {
			m_DateValue = DB.getTSForOrg(Env.getCtx());
		}
		
		//Cliente sobre el cual se insertaran o actualizaran los datos
		m_Insert_Client_ID = Env.getAD_Client_ID(getCtx());
		
		//Nivel de acceso de la tabla
		m_NivelAcceso = MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_I_Replenish.Table_Name);
		
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception {
		StringBuilder sql = null;
		int no = 0;


		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported) {
			sql = new StringBuilder (" DELETE I_Replenish ")
			.append(" WHERE I_IsImported='Y' ")
			.append(m_NivelAcceso);
			
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder (" UPDATE I_Replenish ")
		.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_Insert_Client_ID).append("), ")
		.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),   ")
		.append(" IsActive = COALESCE (IsActive, 'Y'),   ")
		.append(" Created = COALESCE (Created, SysDate), ")
		.append(" CreatedBy = COALESCE (CreatedBy, 0),   ")
		.append(" Updated = COALESCE (Updated, SysDate), ")
		.append(" UpdatedBy = COALESCE (UpdatedBy, 0),   ")
		.append(" I_ErrorMsg = NULL,                     ")
		.append(" I_IsImported = 'N'                     ")
		.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL")
		.append(m_NivelAcceso);
		
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);

		// establecer producto
		sql = new StringBuilder (" UPDATE I_Replenish ")
		.append(" SET M_Product_ID =( SELECT M_Product_ID ")
		.append("                     FROM M_Product p ")
		.append("                     WHERE TRIM(I_Replenish.M_Product_Value) = TRIM(p.Value) ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_M_Product.Table_Name,"p"))//.append("                     AND p.AD_Client_ID = ").append(m_Select_Client_ID)
		.append(" AND p.isactive = 'Y' ")
		.append("                   )  ")
		.append(" WHERE M_Product_ID IS NULL ")
		.append(" AND I_IsImported<>'Y'")
		.append(m_NivelAcceso);
		
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Establecer producto=" + no);
		
		//
		sql = new StringBuilder (" UPDATE I_Replenish ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Producto no valido, ' ")
		.append(" WHERE M_Product_ID IS NULL ")
		.append(" AND I_IsImported<>'Y'      ")
		.append(m_NivelAcceso);
		
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Producto no valido=" + no); 

		// establecer almacen
		sql = new StringBuilder (" UPDATE I_Replenish ")
		.append(" SET M_Warehouse_ID = ( SELECT M_Warehouse_ID FROM M_Warehouse w ")
		.append("                        WHERE TRIM(w.Value) = TRIM(I_Replenish.M_Warehouse_Value)  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_M_Warehouse.Table_Name,"w"))
		.append("                       )  ")
		.append(" WHERE M_Warehouse_ID IS NULL ")
		.append(" AND I_IsImported<>'Y' ")
		.append(m_NivelAcceso);
		
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Establecer almacen=" + no);
		//
		sql = new StringBuilder (" UPDATE I_Replenish  ")
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Almacen no valido, '  ")
		.append(" WHERE M_Warehouse_ID IS NULL ")
		.append(" AND I_IsImported<>'Y'")
		.append(m_NivelAcceso);
		
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Almacen no valido=" + no);


		//	-------------------------------------------------------------------
		int noInsert = 0;
		//int noUpdate = 0;

		//	Go through Records
		sql = new StringBuilder ("SELECT I_Replenish_ID ")
		.append(" FROM  I_Replenish ")
		.append(" WHERE I_IsImported='N'")
		.append(m_NivelAcceso);

		StringBuilder sql1 = new StringBuilder(" SELECT M_PRODUCT_ID, M_WAREHOUSE_ID ")
		.append(" FROM  I_Replenish ")
		.append(" WHERE I_Replenish_ID=? and I_IsImported='N' ")
		.append(m_NivelAcceso);
		
		StringBuilder sql2 = new StringBuilder(" SELECT * ")
		.append(" FROM  M_REPLENISH ")
		.append(" WHERE M_PRODUCT_ID=? AND M_WAREHOUSE_ID=? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_M_Replenish.Table_Name));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs1 = null;
        ResultSet rs2= null;
        
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {

			    String trxName   = Trx.createTrxName();

			    int iReplenishID = rs.getInt("I_Replenish_ID");
			    
			    ps1 = DB.prepareStatement(sql1.toString(), null);
				ps1.setInt(1, iReplenishID);
				
				rs1 = ps1.executeQuery();
				
				if (rs1.next()) {
					ps2 = DB.prepareStatement(sql2.toString(), null);
					ps2.setInt(1, rs1.getInt(1));
					ps2.setInt(2, rs1.getInt(2));
					
					rs2 = ps2.executeQuery();
			    
    			    try {

    			        X_I_Replenish iRep = 
    			            new X_I_Replenish(getCtx(), iReplenishID, trxName);
    			        MReplenish mRep = null;
    			        
    			        //Obtenemos el Producto para guardar la Unidad de Volumen en Replenish.
    			        //Jesus Cantu
    			        MProduct prod = null; 
    			        prod = new MProduct(getCtx(), iRep.getM_Product_ID(), trxName);
    			        
    			        //Convertimos a la Unidad de Volumen los máximos y mínimos para guardar la información en Unidad de Volumen.
    					BigDecimal qtyMax = MEXMEUOMConversion.convertProductFrom(getCtx(), prod.getM_Product_ID(), prod.getC_UOMVolume_ID(), iRep.getLevel_Max(), null, true);
    					BigDecimal qtyMin = MEXMEUOMConversion.convertProductFrom(getCtx(), prod.getM_Product_ID(), prod.getC_UOMVolume_ID(), iRep.getLevel_Min(), null, true);
    					
    					if (qtyMax == null) {
    						qtyMax = BigDecimal.ZERO;
    					} else {
    						qtyMax = qtyMax.setScale(0, BigDecimal.ROUND_FLOOR);
    					}

    					if (qtyMin == null) {
    						qtyMin = BigDecimal.ZERO;
    					} else {
    						qtyMin = qtyMin.setScale(0, BigDecimal.ROUND_FLOOR);
    					}
    					
    			        if (rs2.next()) {
    			        	mRep = new MReplenish(getCtx(), rs2, trxName);
   					        mRep.setReplenishType(iRep.getReplenishType());
    					    //mRep.setLevel_Min(iRep.getLevel_Min());
    					    //mRep.setLevel_Max(iRep.getLevel_Max());
    					    mRep.setLevel_Min(qtyMin);
    					    mRep.setLevel_Max(qtyMax);
    					        
    					    //Se agregan Niveles de empaque y Unidad de Medida Mínima. ETS 05487 y 05453
    					    //Jesus Cantu el 16 Agosto 2013
    					    //mRep.setLevel_Min_Vol(iRep.getLevel_Min());
    					    //mRep.setLevel_Max_Vol(iRep.getLevel_Max());
    					    mRep.setLevel_Min_Vol(qtyMin);
    					    mRep.setLevel_Max_Vol(qtyMax);
    					    mRep.setC_UOM_Volume_ID(prod.getC_UOMVolume_ID()); //Siempre guardar en Unidad Volumen, ETS 6915
    			        } else {
    			            mRep = new MReplenish(getCtx(), 0, trxName);
    				        //establecer los valores
    				        mRep.setM_Product_ID(iRep.getM_Product_ID());
    				        mRep.setM_Warehouse_ID(iRep.getM_Warehouse_ID());
    				        mRep.setReplenishType(iRep.getReplenishType());
    				        //mRep.setLevel_Min(iRep.getLevel_Min());
    				        //mRep.setLevel_Max(iRep.getLevel_Max());
    				        mRep.setLevel_Min(qtyMin);
    				        mRep.setLevel_Max(qtyMax);
    				        
					        //Se agregan Niveles de empaque y Unidad de Medida. ETS 05487 y 05453
					        //Jesus Cantu el 16 Agosto 2013
					        //mRep.setLevel_Min_Vol(iRep.getLevel_Min());
					        //mRep.setLevel_Max_Vol(iRep.getLevel_Max());
					        mRep.setLevel_Min_Vol(qtyMin);
					        mRep.setLevel_Max_Vol(qtyMax);
					        mRep.setC_UOM_Volume_ID(prod.getC_UOMVolume_ID()); //Siempre guardar en Unidad Volumen, ETS 6915
    			        }
    
    			        
    			        if (!mRep.save(trxName)) {
    			            if (noInsert > 0) {
    				            noInsert--;
    			            }
    			            
    				        no++;
    				        DB.rollback(false, trxName);
    				        Trx trx = Trx.get(trxName, false);
    				        trx.close();
    				        log.log(Level.WARNING,"Insertando Producto x Almacen: no se inserto el registro");
    				        sql = new StringBuilder("UPDATE I_Replenish " )
    				                .append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || ")
    				                .append(" 'Insertar Prod. x Almacen: no se inserto el registro' ")
    				                .append(" WHERE I_Replenish_ID = ")
    				                .append(iReplenishID);
    				        DB.executeUpdate(sql.toString(), null);
    				        continue;
    			        }
    			        
    			        //lo marcamos como importado y procesado
    			        iRep.setI_IsImported(true);
    			        iRep.setProcessed(true);
    			        
    			        if (!iRep.save(trxName)) {
    				        log.log(Level.WARNING,"No se actualizo el registro: " + iRep);
    				        DB.rollback(true, trxName);
    				        
    				        if (noInsert > 0) {
    				            noInsert--;
    				        }
    				        no++;
    			        } else {
    			            DB.commit(true, trxName);
    			            noInsert++;
    			        }
    			        
    			        Trx trx = Trx.get(trxName, false);
    			        trx.close();
    			    
    			    } catch (Exception e) {
    			        
    			    	if (noInsert > 0) {
    			            noInsert--;
    			        }
    			        
    			        no++;
    			        DB.rollback(false, trxName);
    			        Trx trx = Trx.get(trxName, false);
    			        trx.close();
    			        log.log(Level.WARNING,"Insertando Prod. x Almacen: ", e);
    			        sql = new StringBuilder("UPDATE I_Replenish ")
    			        .append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
    			        .append(DB.TO_STRING("Insertar Prod x Almacen: " + e.toString()))
    			        .append(" WHERE I_Replenish_ID=")
    			        .append(iReplenishID);
    			        DB.executeUpdate(sql.toString(), null);
    			    } //Catch
                } // rs1.next()
                rs1.close();
                rs2.close();
                ps1.close();
                ps2.close();
			}	//	for all I_EXME_Medico
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Error.doIt", e);
            throw new Exception ("ImportMReplenish.doIt", e);
		} finally {
            if (rs != null) {
                rs.close();
            }
            
            if (pstmt != null) {
                pstmt.close();
            }
            
            if (rs1 != null) {
                rs1.close();
            }
            
            if (rs2 != null) {
                rs2.close();
            }
            
            if (ps1 != null) {
                ps1.close();
            }
            
            if (ps2 != null) {
                ps2.close();
            }
        }

		//	Set Error to indicator to not imported
        sql = new StringBuilder (" UPDATE I_Replenish ")
        .append(" SET I_IsImported='N', Updated=SysDate ")
        .append(" WHERE I_IsImported<>'Y' ")
        .append(m_NivelAcceso);
        no = DB.executeUpdate(sql.toString(), null);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@M_Replenish_ID@: @Inserted@");
		return "";
	}	//	doIt
	
	public static void main(String[] args) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		try {
			List<String> lst = FileUtils.readLines(file);
			for (int i = 0; i < lst.size(); i++) {
				String str = lst.get(i);
				lst.set(i, "'%" + str + "'");
			}
			String text = StringUtils.join(lst, ", ");
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void create() {
		//Archivo con PMIDs
		File file = new File("/home/odelarosa/nuevo.txt");
		try {
			List<String> lines = FileUtils.readLines(file);
			List<String> ids = new ArrayList<String>();
			List<String> errors = new ArrayList<String>();
			for (String line : lines) {
				StringBuilder str = new StringBuilder();
				str.append("select value from m_product where pmid like '%").append(line).append("'");

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(str.toString(), null);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						//Value del almacen
						ids.add(rs.getString(1)+ "\t10001017 Pharmacy\t0\t0\t0");
					} else {
						errors.add(line);
					}
					if(rs.next()){
						System.out.println("mas de uno " + line);
					}
					rs.close();
					pstmt.close();
					pstmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
						pstmt = null;
						if (rs != null)
							rs.close();
						rs = null;
					} catch (Exception e) {
						pstmt = null;
						rs = null;
					}
				}
			}
			FileUtils.writeLines(new File("/home/odelarosa/ids.txt"), ids);
			FileUtils.writeLines(new File("/home/odelarosa/errors.txt"), errors);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}	//	ImportMReplenish