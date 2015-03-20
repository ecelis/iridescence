/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Physical Inventory Model
 *
 *  @author Jorg Janke
 *  @version $Id: MEXMESalidaGasto.java,v 1.8 2006/08/11 02:26:22 mrojas Exp $
 */
public class MEXMESalidaGasto extends X_EXME_SalidaGasto implements DocAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 54204685784492819L;
	/** Static Logger */
    private static CLogger s_log = CLogger.getCLogger(MEXMESalidaGasto.class);
    
    /**********************************************************************/
    /**
     *  Get Inventory from Cache
     *  @param ctx context
     *  @param EXME_SalidaGasto_ID id
     *  @return MEXMESalidaGasto
     */
    public static MEXMESalidaGasto get (Properties ctx, int EXME_SalidaGasto_ID)
    {
        Integer key = new Integer (EXME_SalidaGasto_ID);
        MEXMESalidaGasto retValue = (MEXMESalidaGasto) s_cache.get (key);
        if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
            return retValue;
        retValue = new MEXMESalidaGasto (ctx, EXME_SalidaGasto_ID, null);
        if (retValue.get_ID () != 0)
            s_cache.put (key, retValue);
        return retValue;
    } //    get

    /** Cache                       */
    private static CCache<Integer,MEXMESalidaGasto> s_cache = new CCache<Integer,MEXMESalidaGasto>("EXME_SalidaGasto", 5, 5);
    
    
    /**
     *  Standard Constructor
     *  @param ctx context 
     *  @param EXME_SalidaGasto_ID id
     */
    public MEXMESalidaGasto (Properties ctx, int EXME_SalidaGasto_ID, String trxName)
    {
        super (ctx, EXME_SalidaGasto_ID, trxName);
        if (EXME_SalidaGasto_ID == 0)
        {
        //  setName (null);
        //  setM_Warehouse_ID (0);      //  FK
            setMovementDate (DB.getTimestampForOrg(getCtx()));
            setDocAction (DOCACTION_Complete);  // CO
            setDocStatus (DOCSTATUS_Drafted);   // DR
            setIsApproved (false);
            setMovementDate (DB.getTimestampForOrg(getCtx()));    // @#Date@
//            setPosted (false);
            setProcessed (ctx, false, trxName);
        }
    }   //  MEXMESalidaGasto

    /**
     *  Load Constructor
     *  @param ctx context
     *  @param rs result set
     */
    public MEXMESalidaGasto (Properties ctx, ResultSet rs, String trxName)
    {
        super(ctx, rs, trxName);
    }   //  MEXMESalidaGasto

    /**
     *  Warehouse Constructor
     *  @param wh warehouse
     */
    public MEXMESalidaGasto (MWarehouse wh)
    {
        this (wh.getCtx(), 0, wh.get_TrxName());
        setClientOrg(wh);
        setM_Warehouse_ID(wh.getM_Warehouse_ID());
    }   //  MEXMESalidaGasto
    
    
    /** Lines                       */
    private MEXMESalidaGastoLine[]    m_lines = null;
    
    /**
     *  Get Lines
     *  @param requery requery
     *  @return array of lines
     */
    public MEXMESalidaGastoLine[] getLines (boolean requery)
    {
        if (m_lines != null && !requery)
            return m_lines;
        //
        ArrayList<MEXMESalidaGastoLine> list = new ArrayList<MEXMESalidaGastoLine>();
        String sql = "SELECT * FROM EXME_SalidaGastoLine WHERE EXME_SalidaGasto_ID=? ORDER BY Line";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, get_TrxName());
            pstmt.setInt (1, getEXME_SalidaGasto_ID());
            rs = pstmt.executeQuery ();
            while (rs.next ()) {
                list.add (new MEXMESalidaGastoLine (getCtx(), rs, get_TrxName()));
            }
            
        } catch (Exception e)
        {
            log.log(Level.SEVERE, sql, e);
        }
       finally {DB.close(rs, pstmt);}
        
        m_lines = new MEXMESalidaGastoLine[list.size ()];
        list.toArray (m_lines);
        return m_lines;
    }   //  getLines
    
    /**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description)
    {
        String desc = getDescription();
        if (desc == null)
            setDescription(description);
        else
            setDescription(desc + " | " + description);
    }   //  addDescription
    
    /**
     *  Overwrite Client/Org - from Import.
     *  @param AD_Client_ID client
     *  @param AD_Org_ID org
     */
    public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
    {
        super.setClientOrg(AD_Client_ID, AD_Org_ID);
    }   //  setClientOrg

    /**
     *  String Representation
     *  @return info
     */
    public String toString ()
    {
        StringBuffer sb = new StringBuffer ("MEXMESalidaGasto[");
        sb.append (get_ID())
            .append ("-").append (getDocumentNo())
            .append (",M_Warehouse_ID=").append(getM_Warehouse_ID())
            .append ("]");
        return sb.toString ();
    }   //  toString
    
    /**
     *  Get Document Info
     *  @return document info (untranslated)
     */
    public String getDocumentInfo()
    {
        MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
        return dt.getName() + " " + getDocumentNo();
    }   //  getDocumentInfo

    /**
     *  Create PDF
     *  @return File or null
     */
    public File createPDF ()
    {
        try
        {
            File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
            return createPDF (temp);
        }
        catch (Exception e)
        {
            log.severe("Could not create PDF - " + e.getMessage());
        }
        return null;
    }   //  getPDF

    /**
     *  Create PDF file
     *  @param file output file
     *  @return file if success
     */
    public File createPDF (File file)
    {
    //  ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
    //  if (re == null)
            return null;
    //  return re.getPDF(file);
    }   //  createPDF

    
    /**
     *  Before Save
     *  @param newRecord new
     *  @return true
     */
    protected boolean beforeSave (boolean newRecord)
    {
        if (getC_DocType_ID() == 0)
        {
            MDocType types[] = MEXMEDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_InternalUseInventory);
            if (types.length > 0)   //  get first
                setC_DocType_ID(types[0].getC_DocType_ID());
            else
            {
                log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@ @C_DocType_ID@"));
                return false;
            }
        }
        return true;
    }   //  beforeSave
    
    
    /**
     *  Set Processed.
     *  Propergate to Lines/Taxes
     *  @param processed processed
     */
    public void setProcessed (Properties ctx, boolean processed, String trxName)
    {
        super.setProcessed (processed);
        if (get_ID() == 0)
            return;
        
      //Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_SalidaGastoLine_ID FROM EXME_SalidaGastoLine WHERE EXME_SalidaGasto_ID = ? ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {    		
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, getEXME_SalidaGasto_ID());
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMESalidaGastoLine obj = new MEXMESalidaGastoLine(ctx, rs, null);
    			obj.setProcessed(processed);
    			if (!obj.save(trxName)) {
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    			}
    			obj = null;    			
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
    		DB.close(rs, pstmt);
    		m_lines = null;
    	}	
        /*String sql = "UPDATE EXME_SalidaGastoLine SET Processed='"
            + (processed ? "Y" : "N")
        int noLine = DB.executeUpdate(sql, get_TrxName());
        m_lines = null;
        log.fine("Processed=" + processed + " - Lines=" + noLine);*/
    }   //  setProcessed

    
    /**************************************************************************
     *  Process document
     *  @param processAction document action
     *  @return true if performed
     */
    public boolean processIt (String processAction)
    {
        m_processMsg = null;
        DocumentEngine engine = new DocumentEngine (this, getDocStatus());
        return engine.processIt (processAction, getDocAction());
    }   //  processIt
    
    /** Process Message             */
    private String      m_processMsg = null;
    /** Just Prepared Flag          */
    private boolean     m_justPrepared = false;

    /**
     *  Unlock Document.
     *  @return true if success 
     */
    public boolean unlockIt()
    {
        log.info(toString());
        setProcessing(false);
        return true;
    }   //  unlockIt
    
    /**
     *  Invalidate Document
     *  @return true if success 
     */
    public boolean invalidateIt()
    {
        log.info(toString());
        setDocAction(DOCACTION_Prepare);
        return true;
    }   //  invalidateIt
    
    /**
     *  Prepare Document
     *  @return new status (In Progress or Invalid) 
     */
    public String prepareIt()
    {
        log.info(toString());
        m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
        if (m_processMsg != null)
            return DocAction.STATUS_Invalid;

        //  Std Period open?
        if (!MPeriod.isOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_InternalUseInventory,0))
        {
            m_processMsg = "@PeriodClosed@";
            return DocAction.STATUS_Invalid;
        }
        MEXMESalidaGastoLine[] lines = getLines(false);
        if (lines.length == 0)
        {
            m_processMsg = "@NoLines@";
            return DocAction.STATUS_Invalid;
        }

        //  TODO: Add up Amounts
    //  setApprovalAmt();
        
        
        m_justPrepared = true;
        if (!DOCACTION_Complete.equals(getDocAction()))
            setDocAction(DOCACTION_Complete);
        return DocAction.STATUS_InProgress;
    }   //  prepareIt
    
    /**
     *  Approve Document
     *  @return true if success 
     */
    public boolean  approveIt()
    {
        log.info(toString());
        setIsApproved(true);
        return true;
    }   //  approveIt
    
    /**
     *  Reject Approval
     *  @return true if success 
     */
    public boolean rejectIt()
    {
        log.info(toString());
        setIsApproved(false);
        return true;
    }   //  rejectIt
    
    /**
     *  Complete Document
     *  @return new status (Complete, In Progress, Invalid, Waiting ..)
     */
    public String completeIt()
    {
        //  Re-Check
        if (!m_justPrepared)
        {
            String status = prepareIt();
            if (!DocAction.STATUS_InProgress.equals(status))
                return status;
        }
        //  Implicit Approval
        if (!isApproved())
            approveIt();
        log.info(toString());
        //
        MEXMESalidaGastoLine[] lines = getLines(false);
        for (int i = 0; i < lines.length; i++)
        {
            MEXMESalidaGastoLine line = lines[i];
            if (!line.isActive())
                continue;
            
            //MTransaction trx = null; //Expert:Hassan - uso de List
            List<MTransaction> trxs = new ArrayList<MTransaction>(); //Expert:Hassan - uso de List para guardar referencias.
            if (line.getM_AttributeSetInstance_ID() == 0)
            {
                BigDecimal qtyDiff = line.getQtyInternalUse().negate();
                if (qtyDiff.signum() == 0)
                    qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
                //
                if (qtyDiff.signum() > 0)
                {
                    //  Storage
                    MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
                        line.getM_Product_ID(), 0, get_TrxName());
                    if (storage == null)
                        storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
                            line.getM_Product_ID(), 0, get_TrxName());
                    BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
                    log.fine("Diff=" + qtyDiff 
                        + " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);
                    
                    //BigDecimal tempRemove = storage.getQtyOnHand();//EXPERT.- RMontemayor - Guarda la cantidad que existia en cantidad a la mano
                    storage.setQtyOnHand(qtyNew);
                    storage.setDateLastInventory(getMovementDate());
                    //EXPERT.- RMontemayor
                    //Trae todas las cantidades a la mano con el locator, producto y attributeSetInstance
//                  BigDecimal productTotal = MStorage.getQtyOnHand(line.getM_Locator_ID(), line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
//                  productTotal = productTotal.subtract(tempRemove);  //Quita el valor que ya sabemos que no va a exisitir por que lo sobreescribiremos
//                  productTotal = productTotal.add(qtyNew); //Le agregamos lo que se supone que debe de quedar 
//                  if (productTotal.compareTo(line.getQtyCount()) < 0) { //Estas lineas verifican si fueron correctas las operaciones
//                      BigDecimal diffQty = line.getQtyCount().subtract(productTotal);
//                      storage.setQtyOnHand(qtyNew.add(diffQty));
//                  } else if (productTotal.compareTo(line.getQtyCount()) > 0) {  //Y si no, hace lo necesario para arreglarlo.
//                      BigDecimal diffQty = productTotal.subtract(line.getQtyCount());
//                      storage.setQtyOnHand(qtyNew.subtract(diffQty));
//                  }
                    //EXPERT.- RMontemayor
                    if (!storage.save(get_TrxName()))
                    {
                        m_processMsg = "Storage not updated(1)";
                        return DocAction.STATUS_Invalid;
                    }
                    log.fine(storage.toString());
                    /* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
                    MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
                    for(int h = 0; h < acctss.length; h++){
                        MAcctSchema as = acctss[h];
                        
                        //Current Costs
                        final MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
                		
//                        MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
//                        String costingMethod = as.getCostingMethod();
                       
//                		// Expert: Proyecto #102 Posteo,Costos y precios
//						// Si no encuentra el costo en cliente lo busca en system
//                		MProductCategoryAcct pca = MProductCategoryAcct.getAcct( 
//                			product, as);
//                		
//                        if (pca.getCostingMethodDefault() != null)
//                            costingMethod = pca.getCostingMethodDefault();
                        
                        //  Create Transaction
                        MTransaction trx = new MTransaction (
                                getCtx(), product,
                                line.getM_AttributeSetInstance_ID(),
                                as, line.getAD_Org_ID(), product.getCostingMethod(as), 
                                qtyDiff, 0, false, MTransaction.MOVEMENTTYPE_InternalUseInventory, line.getM_Locator_ID(),
                                getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable
                        
                        trx.setEXME_SalidaGastoLine_ID(line.getEXME_SalidaGastoLine_ID());
                        if (!trx.save())
                        {
                            m_processMsg = "Transaction not inserted(1)";
                            return DocAction.STATUS_Invalid;
                        }
                        trxs.add(trx);
                    }
                }
                else    //  negative qty
                {
                    MEXMESalidaGastoLineMA mas[] = MEXMESalidaGastoLineMA.get(getCtx(),
                        line.getEXME_SalidaGastoLine_ID(), get_TrxName());
                    for (int j = 0; j < mas.length; j++)
                    {
                        MEXMESalidaGastoLineMA ma = mas[j];
                        //  Storage
                        MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
                            line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
                        if (storage == null)
                            storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
                                line.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), get_TrxName());
                        //
                        BigDecimal maxDiff = qtyDiff;
                        if (maxDiff.signum() < 0 
                            && ma.getMovementQty().compareTo(maxDiff.negate()) < 0)
                            maxDiff = ma.getMovementQty().negate();
                        BigDecimal qtyNew = ma.getMovementQty().add(maxDiff);   //  Storage+Diff
                        log.fine("MA Qty=" + ma.getMovementQty() 
                            + ",Diff=" + qtyDiff + "|" + maxDiff 
                            + " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew
                            + " {" + ma.getM_AttributeSetInstance_ID() + "}");
                        //
                        //BigDecimal tempRemove = storage.getQtyOnHand();//EXPERT.- RMontemayor - Guarda la cantidad que existia en cantidad a la mano
                        storage.setQtyOnHand(qtyNew);
                        storage.setDateLastInventory(getMovementDate());
                        //EXPERT.- RMontemayor
                        //Trae todas las cantidades a la mano con el locator, producto y attributeSetInstance
//                      BigDecimal productTotal = MStorage.getQtyOnHand(line.getM_Locator_ID(), line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
//                      productTotal = productTotal.subtract(tempRemove);  //Quita el valor que ya sabemos que no va a exisitir por que lo sobreescribiremos
//                      productTotal = productTotal.add(qtyNew); //Le agregamos lo que se supone que debe de quedar 
//                      if (productTotal.compareTo(line.getQtyCount()) < 0) { //Estas lineas verifican si fueron correctas las operaciones
//                          BigDecimal diffQty = line.getQtyCount().subtract(productTotal);
//                          storage.setQtyOnHand(qtyNew.add(diffQty));
//                      } else if (productTotal.compareTo(line.getQtyCount()) > 0) {  //Y si no, hace lo necesario para arreglarlo.
//                          BigDecimal diffQty = productTotal.subtract(line.getQtyCount());
//                          storage.setQtyOnHand(qtyNew.subtract(diffQty));
//                      }
                        //EXPERT.- RMontemayor
                        if (!storage.save(get_TrxName()))
                        {
                            m_processMsg = "Storage not updated (MA)";
                            return DocAction.STATUS_Invalid;
                        }
                        log.fine(storage.toString());
                
                        /* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
                        MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
                        for(int h = 0; h < acctss.length; h++){
                            MAcctSchema as = acctss[h];
                            //  Transaction
                            //Current Costs
                            final MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
//                            MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
//                            String costingMethod = as.getCostingMethod();
//                           
//                            // Expert: Proyecto #102 Posteo,Costos y precios
//							// Si no encuentra el costo en cliente lo busca en system
//                			MProductCategoryAcct pca = MProductCategoryAcct.getAcct( 
//                				product, as);
//                            if (pca.getCostingMethodDefault() != null)
//                                costingMethod = pca.getCostingMethodDefault();
                            
                            //  Create Transaction
                            MTransaction trx = new MTransaction (
                                    getCtx(), product,
                                    ma.getM_AttributeSetInstance_ID(),
                                    as, line.getAD_Org_ID(), product.getCostingMethod(as), 
                                    maxDiff, 0, false, MTransaction.MOVEMENTTYPE_InternalUseInventory, line.getM_Locator_ID(),
                                    getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable

                            trx.setEXME_SalidaGastoLine_ID(line.getEXME_SalidaGastoLine_ID());
                            if (!trx.save())
                            {
                                m_processMsg = "Transaction not inserted (MA)";
                                return DocAction.STATUS_Invalid;
                            }
                            trxs.add(trx);
                        }/* Expert:Hassan - Fin del Bloque */
                        //
                        qtyDiff = qtyDiff.subtract(maxDiff);
                        if (qtyDiff.signum() == 0)
                            break;
                    }
                }   //  negative qty
            }
            
            //  Fallback
            if (trxs == null || trxs.size() == 0) //Expert:Hassan validar lista.
            {
                //  Storage
                MStorage storage = MStorage.get(getCtx(), line.getM_Locator_ID(), 
                    line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
                if (storage == null)
                    storage = MStorage.getCreate(getCtx(), line.getM_Locator_ID(), 
                        line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
                //
                BigDecimal qtyDiff = line.getQtyInternalUse().negate();
                if (Env.ZERO.compareTo(qtyDiff) == 0)
                    qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
                BigDecimal qtyNew = storage.getQtyOnHand().add(qtyDiff);
                log.fine("Count=" + line.getQtyCount()
                    + ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff 
                    + " - OnHand=" + storage.getQtyOnHand() + "->" + qtyNew);
                //
//              BigDecimal tempRemove = storage.getQtyOnHand();//EXPERT.- RMontemayor - Guarda la cantidad que existia en cantidad a la mano
                storage.setQtyOnHand(qtyNew);
                storage.setDateLastInventory(getMovementDate());
//              //EXPERT.- RMontemayor
//              //Trae todas las cantidades a la mano con el locator, producto y attributeSetInstance
//              BigDecimal productTotal = MStorage.getQtyOnHand(line.getM_Locator_ID(), line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), get_TrxName());
//              productTotal = productTotal.subtract(tempRemove);  //Quita el valor que ya sabemos que no va a exisitir por que lo sobreescribiremos
//              productTotal = productTotal.add(qtyNew); //Le agregamos lo que se supone que debe de quedar 
//              if (productTotal.compareTo(line.getQtyCount()) < 0) { //Estas lineas verifican si fueron correctas las operaciones
//                  BigDecimal diffQty = line.getQtyCount().subtract(productTotal);
//                  storage.setQtyOnHand(qtyNew.add(diffQty));
//              } else if (productTotal.compareTo(line.getQtyCount()) > 0) {  //Y si no, hace lo necesario para arreglarlo.
//                  BigDecimal diffQty = productTotal.subtract(line.getQtyCount());
//                  storage.setQtyOnHand(qtyNew.subtract(diffQty));
//              }
                //EXPERT.- RMontemayor
                if (!storage.save(get_TrxName()))
                {
                    m_processMsg = "Storage not updated(2)";
                    return DocAction.STATUS_Invalid;
                }
                log.fine(storage.toString());
            
                /* Expert:Hassan - Registramos una transaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
                MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
                for(int h = 0; h < acctss.length; h++){
                    final MAcctSchema as = acctss[h];
                    //  Transaction
                    
                    //Current Costs
                   final MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
//                    String costingMethod = as.getCostingMethod();
//                    MProductCategoryAcct pca = MProductCategoryAcct.get(getCtx(), 
//                        product.getM_Product_Category_ID(), as.getC_AcctSchema_ID(), line.get_TrxName());
//                    if (pca.getCostingMethod() != null)
//                        costingMethod = pca.getCostingMethod();
                    
                    //  Create Transaction
                    MTransaction trx = new MTransaction (
                            getCtx(), product,
                            line.getM_AttributeSetInstance_ID(),
                            as, line.getAD_Org_ID(), product.getCostingMethod(as), 
                            qtyDiff, 0, false, MTransaction.MOVEMENTTYPE_InternalUseInventory, line.getM_Locator_ID(),
                            getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable
                                
                    trx.setEXME_SalidaGastoLine_ID(line.getEXME_SalidaGastoLine_ID());
                    if (!trx.save())
                    {
                        m_processMsg = "Transaction not inserted(2)";
                        return DocAction.STATUS_Invalid;
                    }
                }/* Expert:Hassan - Fin del Bloque */
            }   //  Fallback
            
        }   //  for all lines
        
        //  User Validation
        String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
        if (valid != null)
        {
            m_processMsg = valid;
            return DocAction.STATUS_Invalid;
        }
        //
        setProcessed(getCtx(), true, null);
        setDocAction(DOCACTION_Close);
        return DocAction.STATUS_Completed;
    }   //  completeIt
    
    
    /**
     *  Check Material Policy.
     *  (NOT USED)
     *  Sets line ASI
     */
    @SuppressWarnings("unused")
	private void checkMaterialPolicy()
    {
        int no = MEXMESalidaGastoLineMA.deleteInventoryMA(getEXME_SalidaGasto_ID(), get_TrxName());
        if (no > 0)
            log.config("Delete old #" + no);
        MEXMESalidaGastoLine[] lines = getLines(false);
        
        //  Incoming Trx
        MClient client = MClient.get(getCtx());
        
        //  Check Lines
        for (int i = 0; i < lines.length; i++)
        {
            MEXMESalidaGastoLine line = lines[i];
            boolean needSave = false;

            //  Attribute Set Instance
            if (line.getM_AttributeSetInstance_ID() == 0)
            {
                MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
                BigDecimal qtyDiff = line.getQtyInternalUse().negate();
                if (Env.ZERO.compareTo(qtyDiff) == 0)
                    qtyDiff = line.getQtyCount().subtract(line.getQtyBook());
                log.fine("Count=" + line.getQtyCount()
                    + ",Book=" + line.getQtyBook() + ", Difference=" + qtyDiff); 
                if (qtyDiff.signum() > 0 && product.isLot())   //  In
                {
                    MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
                    asi.setClientOrg(getAD_Client_ID(), 0);
                    asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
                    if (asi.save())
                    {
                        line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
                        needSave = true;
                    }
                }
                else    //  Outgoing Trx
                {
                    MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
                    String MMPolicy = pc.getMMPolicy();
                    if (MMPolicy == null || MMPolicy.length() == 0)
                        MMPolicy = client.getMMPolicy();
                    //
                    MStorage[] storages = MStorage.getAllWithASI(getCtx(), 
                        line.getM_Product_ID(), line.getM_Locator_ID(), 
                        MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
                    BigDecimal qtyToDeliver = qtyDiff.negate();
                    for (int ii = 0; ii < storages.length; ii++)
                    {
                        MStorage storage = storages[ii];
                        if (ii == 0)
                        {
                            if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
                            {
                                line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
                                needSave = true;
                                log.config("Direct - " + line);
                                qtyToDeliver = Env.ZERO;
                            }
                            else
                            {
                                log.config("Split - " + line);
                                MEXMESalidaGastoLineMA ma = new MEXMESalidaGastoLineMA (line, 
                                    storage.getM_AttributeSetInstance_ID(),
                                    storage.getQtyOnHand().negate());
                                if (!ma.save())
                                    ;
                                qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
                                log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
                            }
                        }
                        else    //   create addl material allocation
                        {
                            MEXMESalidaGastoLineMA ma = new MEXMESalidaGastoLineMA (line, 
                                storage.getM_AttributeSetInstance_ID(),
                                qtyToDeliver.negate());
                            if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
                                qtyToDeliver = Env.ZERO;
                            else
                            {
                                ma.setMovementQty(storage.getQtyOnHand().negate());
                                qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
                            }
                            if (!ma.save())
                                ;
                            log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
                        }
                        if (qtyToDeliver.signum() == 0)
                            break;
                    }   //   for all storages
                    
                    //  No AttributeSetInstance found for remainder
                    if (qtyToDeliver.signum() != 0)
                    {
                        MEXMESalidaGastoLineMA ma = new MEXMESalidaGastoLineMA (line, 
                            0, qtyToDeliver.negate());
                        if (!ma.save())
                            ;
                        log.fine("##: " + ma);
                    }
                }   //  outgoing Trx
            }   //  attributeSetInstance
            
            if (needSave && !line.save())
                log.severe("NOT saved " + line);
        }   //  for all lines

    }   //  checkMaterialPolicy

    /**
     *  Void Document.
     *  @return false 
     */
    public boolean voidIt()
    {
        log.info(toString());
        if (DOCSTATUS_Closed.equals(getDocStatus())
            || DOCSTATUS_Reversed.equals(getDocStatus())
            || DOCSTATUS_Voided.equals(getDocStatus()))
        {
            m_processMsg = "Document Closed: " + getDocStatus();
            return false;
        }

        //  Not Processed
        if (DOCSTATUS_Drafted.equals(getDocStatus())
            || DOCSTATUS_Invalid.equals(getDocStatus())
            || DOCSTATUS_InProgress.equals(getDocStatus())
            || DOCSTATUS_Approved.equals(getDocStatus())
            || DOCSTATUS_NotApproved.equals(getDocStatus()) )
        {
            //  Set lines to 0
            MEXMESalidaGastoLine[] lines = getLines(false);
            for (int i = 0; i < lines.length; i++)
            {
                MEXMESalidaGastoLine line = lines[i];
                BigDecimal oldCount = line.getQtyCount();
                BigDecimal oldInternal = line.getQtyInternalUse();
                if (oldCount.compareTo(line.getQtyBook()) != 0 
                    || oldInternal.signum() != 0)
                {
                    line.setQtyInternalUse(Env.ZERO);
                    line.setQtyCount(line.getQtyBook());
                    line.addDescription("Void (" + oldCount + "/" + oldInternal + ")");
                    line.save(get_TrxName());
                }
            }
        }
        else
        {
            return reverseCorrectIt();
        }
            
        setProcessed(getCtx(), true, null);
        setDocAction(DOCACTION_None);
        return true;
    }   //  voidIt
    
    /**
     *  Close Document.
     *  @return true if success 
     */
    public boolean closeIt()
    {
        log.info(toString());

        setDocAction(DOCACTION_None);
        return true;
    }   //  closeIt
    
    /**
     *  Reverse Correction
     *  @return false 
     */
    public boolean reverseCorrectIt()
    {
        log.info(toString());
        MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
        if (!MPeriod.isOpen(getCtx(), getMovementDate(), dt.getDocBaseType(),0))
        {
            m_processMsg = "@PeriodClosed@";
            return false;
        }

        //  Deep Copy
        MEXMESalidaGasto reversal = new MEXMESalidaGasto(getCtx(), 0, get_TrxName());
        copyValues(this, reversal, getAD_Client_ID(), getAD_Org_ID());
        reversal.setDocStatus(DOCSTATUS_Drafted);
        reversal.setDocAction(DOCACTION_Complete);
        reversal.setIsApproved (false);
//        reversal.setPosted(false);
        reversal.setProcessed(getCtx(), false, null);
        reversal.addDescription("{->" + getDocumentNo() + ")");
        if (!reversal.save())
        {
            m_processMsg = "Could not create Inventory Reversal";
            return false;
        }
        
        //  Reverse Line Qty
        MEXMESalidaGastoLine[] oLines = getLines(true);
        for (int i = 0; i < oLines.length; i++)
        {
            MEXMESalidaGastoLine oLine = oLines[i];
            MEXMESalidaGastoLine rLine = new MEXMESalidaGastoLine(getCtx(), 0, get_TrxName());
            copyValues(oLine, rLine, oLine.getAD_Client_ID(), oLine.getAD_Org_ID());
            rLine.setEXME_SalidaGasto_ID(reversal.getEXME_SalidaGasto_ID());
            rLine.setParent(reversal);
            //
            rLine.setQtyBook (oLine.getQtyCount());     //  switch
            rLine.setQtyCount (oLine.getQtyBook());
            rLine.setQtyInternalUse (oLine.getQtyInternalUse().negate());
            if (!rLine.save())
            {
                m_processMsg = "Could not create Inventory Reversal Line";
                return false;
            }
        }
        //
        if (!reversal.processIt(DocAction.ACTION_Complete))
        {
            m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
            return false;
        }
        reversal.closeIt();
        reversal.setDocStatus(DOCSTATUS_Reversed);
        reversal.setDocAction(DOCACTION_None);
        reversal.save();
        m_processMsg = reversal.getDocumentNo();
        
        //  Update Reversed (this)
        addDescription("(" + reversal.getDocumentNo() + "<-)");
        setProcessed(getCtx(), true, null);
        setDocStatus(DOCSTATUS_Reversed);   //  may come from void
        setDocAction(DOCACTION_None);
        
        return true;
    }   //  reverseCorrectionIt
    
    /**
     *  Reverse Accrual
     *  @return false 
     */
    public boolean reverseAccrualIt()
    {
        log.info(toString());
        return false;
    }   //  reverseAccrualIt
    
    /** 
     *  Re-activate
     *  @return false 
     */
    public boolean reActivateIt()
    {
        log.info(toString());
        return false;
    }   //  reActivateIt
    
    
    /*************************************************************************
     *  Get Summary
     *  @return Summary of Document
     */
    public String getSummary()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getDocumentNo());
        //  : Total Lines = 123.00 (#1)
        sb.append(": ")
            .append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
            .append(" (#").append(getLines(false).length).append(")");
        //   - Description
        if (getDescription() != null && getDescription().length() > 0)
            sb.append(" - ").append(getDescription());
        return sb.toString();
    }   //  getSummary
    
    /**
     *  Get Process Message
     *  @return clear text error message
     */
    public String getProcessMsg()
    {
        return m_processMsg;
    }   //  getProcessMsg
    
    /**
     *  Get Document Owner (Responsible)
     *  @return AD_User_ID
     */
    public int getDoc_User_ID()
    {
        return getUpdatedBy();
    }   //  getDoc_User_ID
    
    /**
     *  Get Document Currency
     *  @return C_Currency_ID
     */
    public int getC_Currency_ID()
    {
    //  MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
    //  return pl.getC_Currency_ID();
        return 0;
    }   //  getC_Currency_ID
    
    
    /**
     *  Obtenemos una lista con los movimientos pendientes de surtir
     *
     *  @param movementID el movimiento pendiente
     *  @return Un resultset con los movimientos pendientes de surtir
     */
    public static List<MEXMESalidaGasto> getListByUser(Properties ctx, int userID, String estatus, String trxName) 
    throws SQLException {
    	
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        //movimientos por id de movimiento
        sql.append("SELECT distinct(M_Movement.m_movement_id), m.*, a.M_Warehouse_ID as almSol, ")
            .append(" surte.M_Warehouse_ID AS almSurt ")
            .append(" FROM M_Movement ")
            .append(" INNER JOIN M_MovementLine l ON M_Movement.M_Movement_ID = l.M_Movement_ID AND l.isActive = 'Y'")
            .append(" INNER JOIN M_Locator ll ON l.M_Locator_ID = ll.M_Locator_ID AND ll.isActive = 'Y'") //surte
            .append(" INNER JOIN M_Locator ls ON l.M_LocatorTo_ID = ls.M_Locator_ID AND ls.isActive = 'Y'") //solicitante
            .append(" INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID AND a.isActive = 'Y'")
            .append(" INNER JOIN M_Warehouse surte ON ll.M_Warehouse_ID = surte.M_Warehouse_ID AND surte.isActive = 'Y'")
            .append(" WHERE M_Movement.createdBy = ? ");
            //.append(" AND m.AD_Client_ID = ? ")
            //.append(" AND m.AD_Org_ID = ? ")
            
        //nivel de accceso
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_Movement"));
            
        sql.append(" AND M_Movement.DocStatus = '").append(estatus).append("' ")
            .append(" AND M_Movement.IsActive = 'Y' ")
            .append(" ORDER BY M_Movement.DocumentNo DESC ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MEXMESalidaGasto> lst = null;
        
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, Env.getAD_Client_ID(ctx));
            pstmt.setInt(3, Env.getAD_Org_ID(ctx));
            rs = pstmt.executeQuery();
            
            lst = new ArrayList<MEXMESalidaGasto>();
            
            while(rs.next()) {
                MEXMESalidaGasto mov = new MEXMESalidaGasto(ctx, rs, trxName);
            //    mov.m_AlmSolic = new MWarehouse(ctx, rs.getInt("almSol"), trxName);
          //      mov.m_AlmSurt  = new MWarehouse(ctx, rs.getInt("almSurt"), trxName);
                
                lst.add(mov); 
            }
           
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "get : ", e);
            lst = null;
        } finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        
        return lst;
    }
    
    /**********************************************************************
     * ****************************************************************** *
     **********************************************************************/
    
    
    private String estatus = null;
    private MUser m_user1 = null;
    private MUser m_user = null;
    private ArrayList<MEXMESalidaGastoLine> m_detail = null;
    private MOrg m_orgTrx = null;
    private MWarehouse m_warehouse = null;

    
    /**
     * Nombre del estatus del documento
     * @return
     */
    public String getEstatus(){

        if(getDocStatus().equals(DOCSTATUS_Drafted))
            estatus = Msg.translate(getCtx(),"Solicitados");
        else if(getDocStatus().equals(DOCSTATUS_Approved))
            estatus = Msg.translate(getCtx(),"Autorizado");
        else if(getDocStatus().equals(DOCSTATUS_NotApproved))
            estatus = Msg.translate(getCtx(),"Cancelados");
        else if(getDocStatus().equals(DOCSTATUS_Completed))
            estatus = Msg.translate(getCtx(),"Sorted");
        else
            estatus = MRefList.getListName(getCtx(),DOCSTATUS_AD_Reference_ID,getDocStatus());
        
        return estatus;
    }

    /**
     * Solicitante
     * @return
     */
    public MUser getUser1(){
        if (getUser1_ID() != 0 )
            m_user1 = MUser.get(getCtx(), getUser1_ID());
        return m_user1;
    }
    
    /**
     * Captura la solicitud
     * @return
     */
    public MUser getUser(){
        if (getCreatedBy() != 0 )
            m_user = MUser.get(getCtx(), getCreatedBy());
        return m_user;
    } 
    
    /**
     * Detalle
     * @return
     */
    public ArrayList<MEXMESalidaGastoLine> getDetalle(){
        if (getEXME_SalidaGasto_ID() > 0 && m_detail == null)
            m_detail = getDetail(getCtx(), getEXME_SalidaGasto_ID(), get_TrxName());
        return m_detail;
    }
    
    /**
     * Organizaci�n TRx que hace la solicitud
     * @return
     */
    public MOrg getOrgTrx(){
        if(getAD_OrgTrx_ID() != 0 )
            m_orgTrx = new MOrg(getCtx(),getAD_OrgTrx_ID(),get_TrxName());
        return m_orgTrx;
    }
    
    /**
     * Almacen de Resutirdo
     * @return
     */
    public MWarehouse getWarehouse(){
        if(getM_Warehouse_ID() > 0  )
            m_warehouse  = new MWarehouse(getCtx(),getM_Warehouse_ID(),get_TrxName());
        return m_warehouse;     
    }

    
    /**
     *  Obtenemos una lista de las solicitudes
     *
     *  @param movementID el movimiento pendiente
     *  @return Un resultset con los movimientos pendientes de surtir
     */
    public static List<MEXMESalidaGasto> getList(Properties ctx, boolean autorizador, String estatus, String whereclause, String trxName) 
    throws SQLException {
        
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
        sql.append("SELECT EXME_SalidaGasto.* ")
            .append("FROM EXME_SalidaGasto ")
            .append(" WHERE EXME_SalidaGasto.DocStatus LIKE '").append(estatus).append("' ")
            .append(" AND EXME_SalidaGasto.IsActive = 'Y' ");

        //si es autorizador, filtramos las solicitudes para su organizacion transaccional
        if(autorizador){
            sql.append(" AND ( EXME_SalidaGasto.AD_OrgTrx_ID IN ")
               .append(" ( SELECT utrx.AD_OrgTrx_ID FROM EXME_UserOrgTrx utrx")
               .append(" WHERE utrx.AD_User_ID = ").append(Env.getAD_User_ID(ctx))
               .append(" AND utrx.isActive = 'Y' ) OR 0 IN ( SELECT utrx.AD_OrgTrx_ID FROM EXME_UserOrgTrx utrx")
               .append(" WHERE utrx.AD_User_ID = ").append(Env.getAD_User_ID(ctx))
               .append(" AND utrx.isActive = 'Y' )) ");

        }else if(whereclause != null ){
            sql.append(whereclause);              
        }

        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_SalidaGasto"));

        sql.append(" ORDER BY EXME_SalidaGasto.DocumentNo DESC ");
            
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MEXMESalidaGasto> lst = null;
        
        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            rs = pstmt.executeQuery();
            
            lst = new ArrayList<MEXMESalidaGasto>();
            
            while(rs.next()) {
                MEXMESalidaGasto mov = new MEXMESalidaGasto(ctx, rs, trxName);
                lst.add(mov); 
            }
           
        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "get : ", e);
            lst = null;
        } finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        
        return lst;
    }
    
    /**
     * Obtenemos el detalle del movimiento.
     * @param ctx
     * @param M_Movement_ID
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static ArrayList<MEXMESalidaGastoLine> getDetail(Properties ctx, int EXME_SalidaGasto_ID, String trxName) {
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MEXMESalidaGastoLine> lst = null;

        try {

            sql.append("SELECT EXME_SalidaGastoLine.* ")
            .append(" FROM EXME_SalidaGastoLine ")
            .append(" WHERE EXME_SalidaGastoLine.EXME_SalidaGasto_ID = ? ")
            .append(" AND EXME_SalidaGastoLine.IsActive = 'Y' ");
            
            //nivel de acceso
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_SalidaGastoLine"));
            
            sql.append(" ORDER BY EXME_SalidaGastoLine.Line ");

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_SalidaGasto_ID);

            rs = pstmt.executeQuery();

            lst = new ArrayList<MEXMESalidaGastoLine>();
            
            while (rs.next()) {
                MEXMESalidaGastoLine ml = new MEXMESalidaGastoLine(ctx, rs, trxName);
                lst.add(ml);
            }

        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getDetail (" + sql + ")", e);
        } finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        return lst;
    }

    
    /**
     * 
     * @param ctx
     * @param movementID
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static boolean deleteLine(Properties ctx, int movementID, String trxName) throws SQLException {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	boolean success = true;

    	/*sql.append("DELETE FROM EXME_SalidaGastoLine ")
            .append("WHERE EXME_SalidaGastoLine.EXME_SalidaGasto_ID = ? ");*/
    	
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	sql.append("SELECT EXME_SalidaGastoLine_ID FROM EXME_SalidaGastoLine ")
    	.append(" WHERE EXME_SalidaGastoLine.EXME_SalidaGasto_ID = ? ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SalidaGastoLine"));
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, movementID);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMESalidaGastoLine obj = new MEXMESalidaGastoLine(ctx, rs, null);
    			if (!obj.delete(true, trxName)) {
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    				success = false;
    				break;
    			}
    			obj = null;
    		}
    	} catch (Exception e) {
    		success = false;
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		throw new SQLException(e.getMessage());    		
    	} finally {
    		DB.close(rs, pstmt);
    	}
    	return success;
    }
    
    /**
     * 
     * @param ctx
     * @param movementID
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static MEXMESalidaGasto get(Properties ctx, String document, String trxName) throws SQLException {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MEXMESalidaGasto retValue = null;
        
        try{
            sql.append("SELECT * FROM EXME_SalidaGasto ")
                .append("WHERE EXME_SalidaGasto.DocumentNo = ? AND EXME_SalidaGasto.isActive = 'Y' ");
    
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_SalidaGasto"));
                   
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setString(1, document);
            rs = pstmt.executeQuery();
    
            if (rs.next()) {
                retValue = new MEXMESalidaGasto(ctx, rs, trxName);
            }

        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getDetail (" + sql + ")", e);
        } finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        
        return retValue;

    }
    
    
    
    public static List<MEXMESalidaGasto> getTratamientosDetalle(Properties ctx, 
 			int id , String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(id);
 		String sql = " SELECT * FROM EXME_SalidaGasto "
 		//+ " inner join exme_cuestionario c on EXME_ActPacienteDet.exme_cuestionario_id = c.exme_cuestionario_id  "
 		+ " WHERE EXME_TRATAMIENTOS_SESION_ID = ? ";
 		return  MEXMESalidaGasto.get(
 				ctx, sql, params,  trxName);

 	}
 	
 	public static List<MEXMESalidaGasto> get(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMESalidaGasto> retorno = new ArrayList<MEXMESalidaGasto>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMESalidaGasto mtd = new MEXMESalidaGasto(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}
}	//	MEXMESalidaGasto
