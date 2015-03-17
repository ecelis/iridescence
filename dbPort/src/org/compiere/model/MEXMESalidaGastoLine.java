/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Physical Inventory Line Model
 *
 *  @author Jorg Janke
 *  @version $Id: MEXMESalidaGastoLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MEXMESalidaGastoLine extends X_EXME_SalidaGastoLine
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/***************************************************************/
    /**
     *  Get Inventory Line with parameters
     *  @param inventory inventory
     *  @param M_Locator_ID locator
     *  @param M_Product_ID product
     *  @param M_AttributeSetInstance_ID asi
     *  @return line or null
     */
    public static MEXMESalidaGastoLine get(MEXMESalidaGasto inventory, int M_Locator_ID,
			int M_Product_ID, int M_AttributeSetInstance_ID) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    
        MEXMESalidaGastoLine retValue = null;
        sql.append("SELECT * FROM EXME_SalidaGastoLine ")
            .append("WHERE isActive = 'Y' AND EXME_SalidaGasto_ID=? AND M_Locator_ID=?")
            .append(" AND M_Product_ID=? AND M_AttributeSetInstance_ID=?");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(inventory.getCtx(), " ", "EXME_SalidaGastoLine"));
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
			pstmt = DB.prepareStatement(sql.toString(), inventory.get_TrxName());
			pstmt.setInt(1, inventory.getEXME_SalidaGasto_ID());
			pstmt.setInt(2, M_Locator_ID);
			pstmt.setInt(3, M_Product_ID);
			pstmt.setInt(4, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
                retValue = new MEXMESalidaGastoLine (inventory.getCtx(), rs, inventory.get_TrxName());
           
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	} //  get
    
    
    /** Logger              */
    private static CLogger  s_log   = CLogger.getCLogger (MEXMESalidaGastoLine.class);
    
    
    /**************************************************************************
     *  Default Constructor
     *  @param ctx context
     *  @param EXME_SalidaGastoLine_ID line
     *  @param trxName transaction
     */
    public MEXMESalidaGastoLine (Properties ctx, int EXME_SalidaGastoLine_ID, String trxName)
    {
        super (ctx, EXME_SalidaGastoLine_ID, trxName);
        if (EXME_SalidaGastoLine_ID == 0)
        {
        //  setEXME_SalidaGasto_ID (0);          //  Parent
        //  setEXME_SalidaGastoLine_ID (0);      //  PK
        //  setM_Locator_ID (0);            //  FK
            setLine(0);
        //  setM_Product_ID (0);            //  FK
            setM_AttributeSetInstance_ID(0);    //  FK
            setInventoryType (INVENTORYTYPE_InventoryDifference);
            setQtyBook (Env.ZERO);
            setQtyCount (Env.ZERO);
            setProcessed(false);
        }
    }   //  MEXMESalidaGastoLine

    /**
     *  Load Constructor
     *  @param ctx context
     *  @param rs result set
     *  @param trxName transaction
     */
    public MEXMESalidaGastoLine (Properties ctx, ResultSet rs, String trxName)
    {
        super(ctx, rs, trxName);
    }   //  MEXMESalidaGastoLine

    /**
     *  Detail Constructor.
     *  Locator/Product/AttributeSetInstance must be unique
     *  @param inventory parent
     *  @param M_Locator_ID locator
     *  @param M_Product_ID product
     *  @param M_AttributeSetInstance_ID instance
     *  @param QtyBook book value
     *  @param QtyCount count value
     */
    public MEXMESalidaGastoLine (MEXMESalidaGasto inventory, 
        int M_Locator_ID, int M_Product_ID, int M_AttributeSetInstance_ID,
        BigDecimal QtyBook, BigDecimal QtyCount)
    {
        this (inventory.getCtx(), 0, inventory.get_TrxName());
        if (inventory.get_ID() == 0)
            throw new IllegalArgumentException("Header not saved");
        m_parent = inventory;
        setEXME_SalidaGasto_ID (inventory.getEXME_SalidaGasto_ID());      //  Parent
        setClientOrg (inventory.getAD_Client_ID(), inventory.getAD_Org_ID());
        setM_Locator_ID (M_Locator_ID);     //  FK
        setM_Product_ID (M_Product_ID);     //  FK
        setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
        //
        if (QtyBook != null)
            setQtyBook (QtyBook);
        if (QtyCount != null && QtyCount.signum() != 0)
            setQtyCount (QtyCount);
        m_isManualEntry = false;
    }   //  MEXMESalidaGastoLine

    /** Manually created                */
    private boolean     m_isManualEntry = true;
    /** Parent                          */
    private MEXMESalidaGasto  m_parent = null;
    /** Product                         */
    private MProduct    m_product = null;
    
    /**
     *  Get Qty Book
     *  @return Qty Book
     */
    public BigDecimal getQtyBook ()
    {
        BigDecimal bd = super.getQtyBook ();
        if (bd == null)
            bd = Env.ZERO;
        return bd;
    }   //  getQtyBook

    /**
     *  Get Qty Count
     *  @return Qty Count
     */
    public BigDecimal getQtyCount ()
    {
        BigDecimal bd = super.getQtyCount();
        if (bd == null)
            bd = Env.ZERO;
        return bd;
    }   //  getQtyBook

    /**
     *  Get Product
     *  @return product or null if not defined
     */
    public MProduct getProduct()
    {
        int M_Product_ID = getM_Product_ID();
        if (M_Product_ID == 0)
            return null;
        if (m_product != null && m_product.getM_Product_ID() != M_Product_ID)
            m_product = null;   //  reset
        if (m_product == null)
            m_product = MProduct.get(getCtx(), M_Product_ID);
        return m_product;
    }   //  getProduct
    
    /**
     *  Set Count Qty - enforce UOM 
     *  @param QtyCount qty
     */
    public void setQtyCount (BigDecimal QtyCount)
    {
        if (QtyCount != null)
        {
            MProduct product = getProduct();
            if (product != null)
            {
                int precision = product.getUOMPrecision(); 
                QtyCount = QtyCount.setScale(precision, BigDecimal.ROUND_HALF_UP);
            }
        }
        super.setQtyCount(QtyCount);
    }   //  setQtyCount

    /**
     *  Set Internal Use Qty - enforce UOM 
     *  @param QtyInternalUse qty
     */
    public void setQtyInternalUse (BigDecimal QtyInternalUse)
    {
        if (QtyInternalUse != null)
        {
            MProduct product = getProduct();
            if (product != null)
            {
                int precision = product.getUOMPrecision(); 
                QtyInternalUse = QtyInternalUse.setScale(precision, BigDecimal.ROUND_HALF_UP);
            }
        }
        super.setQtyInternalUse(QtyInternalUse);
    }   //  setQtyInternalUse

    
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
     *  Get Parent
     *  @param parent parent
     */
    protected void setParent(MEXMESalidaGasto parent)
    {
        m_parent = parent; 
    }   //  setParent

    /**
     *  Get Parent
     *  @return parent
     */
    private MEXMESalidaGasto getParent()
    {
        if (m_parent == null)
            m_parent = new MEXMESalidaGasto (getCtx(), getEXME_SalidaGasto_ID(), get_TrxName());
        return m_parent;
    }   //  getParent
    
    /**
     *  String Representation
     *  @return info
     */
    public String toString ()
    {
        StringBuffer sb = new StringBuffer ("MEXMESalidaGastoLine[");
        sb.append (get_ID())
            .append("-M_Product_ID=").append (getM_Product_ID())
            .append(",QtyCount=").append(getQtyCount())
            .append(",QtyInternalUse=").append(getQtyInternalUse())
            .append(",QtyBook=").append(getQtyBook())
            .append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
            .append("]");
        return sb.toString ();
    }   //  toString
    
    /**
     *  Before Save
     *  @param newRecord new
     *  @return true if can be saved
     */
    protected boolean beforeSave (boolean newRecord)
    {
        if (newRecord && m_isManualEntry)
        {
            //  Product requires ASI
            if (getM_AttributeSetInstance_ID() == 0)
            {
                MProduct product = MProduct.get(getCtx(), getM_Product_ID());
                if (product.getM_AttributeSet_ID() != 0)
                {
                    MAttributeSet mas = MAttributeSet.get(getCtx(), product.getM_AttributeSet_ID());
                    if (mas.isInstanceAttribute() 
                        && (mas.isMandatory() || mas.isMandatoryAlways()))
                    {
                        log.saveError("FillMandatory", Msg.getElement(getCtx(), "M_AttributeSetInstance_ID"));
                        return false;
                    }
                }
            }   //  No ASI
        }   //  new or manual
        
        //  Set Line No
        if (getLine() == 0)
        {
            String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM EXME_SalidaGastoLine WHERE EXME_SalidaGasto_ID=?";
            int ii = DB.getSQLValue (get_TrxName(), sql, getEXME_SalidaGasto_ID());
            setLine (ii);
        }

        //  Enforce Qty UOM
        if (newRecord || is_ValueChanged("QtyCount"))
            setQtyCount(getQtyCount());
        if (newRecord || is_ValueChanged("QtyInternalUse"))
            setQtyInternalUse(getQtyInternalUse());
        
        //  InternalUse Inventory
        if (getQtyInternalUse().signum() != 0)
        {
            if (!INVENTORYTYPE_ChargeAccount.equals(getInventoryType()))
                setInventoryType(INVENTORYTYPE_ChargeAccount);
            //
            if (getC_Charge_ID() == 0)
            {
                log.saveError("InternalUseNeedsCharge", "");
                return false;
            }
            
            //expert : gisela lee : validar contra el inventario
            //MLocator locator = new MLocator (getCtx(), getM_Locator_ID(), get_TrxName());
            BigDecimal QtyOnHand = MStorage.getQtyOnHand(getM_Locator_ID(), getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
            if(QtyOnHand==null)
                QtyOnHand = Env.ZERO;
            if(getQtyInternalUse().compareTo(QtyOnHand)>0) {
                log.saveError("SaveError", "La cantidad de uso interno es mayor que " +
                        "la cantidad a la mano para el localizador (" + QtyOnHand + ")");
                return false;
            }
            //expert : gisela lee : validar contra el inventario
        }
        else if (INVENTORYTYPE_ChargeAccount.equals(getInventoryType()))
        {
            if (getC_Charge_ID() == 0)
            {
                log.saveError("FillMandatory", Msg.getElement(getCtx(), "C_Charge_ID"));
                return false;
            }
        }
        else if (getC_Charge_ID() != 0)
            setC_Charge_ID(0);
        
        //  Set AD_Org to parent if not charge
        if (getC_Charge_ID() == 0)
            setAD_Org_ID(getParent().getAD_Org_ID());
        
        return true;
    }   //  beforeSave

    /**
     *  After Save
     *  @param newRecord new
     *  @param success success
     *  @return true
     */
    protected boolean afterSave (boolean newRecord, boolean success)
    {
    	if (!success){
			return success;
		}
    	//  Create MA
        if (newRecord && success 
            && m_isManualEntry && getM_AttributeSetInstance_ID() == 0)
            createMA();
        return true;
    }   //  afterSave
    
    /**
     *  Create Material Allocations for new Instances
     */
    private void createMA()
    {
        MStorage[] storages = MStorage.getAll(getCtx(), getM_Product_ID(), 
            getM_Locator_ID(), get_TrxName());
        boolean allZeroASI = true;
        for (int i = 0; i < storages.length; i++)
        {
            if (storages[i].getM_AttributeSetInstance_ID() != 0)
            {
                allZeroASI = false;
                break;
            }
        }
        if (allZeroASI)
            return;
        
        MEXMESalidaGastoLineMA ma = null; 
        BigDecimal sum = Env.ZERO;
        for (int i = 0; i < storages.length; i++)
        {
            MStorage storage = storages[i];
            if (storage.getQtyOnHand().signum() == 0)
                continue;
            if (ma != null 
                && ma.getM_AttributeSetInstance_ID() == storage.getM_AttributeSetInstance_ID())
                ma.setMovementQty(ma.getMovementQty().add(storage.getQtyOnHand()));
            else
                ma = new MEXMESalidaGastoLineMA (this, 
                    storage.getM_AttributeSetInstance_ID(), storage.getQtyOnHand());
            if (!ma.save())
                ;
            sum = sum.add(storage.getQtyOnHand());
        }
        if (sum.compareTo(getQtyBook()) != 0)
        {
            log.warning("QtyBook=" + getQtyBook() + " corrected to Sum of MA=" + sum);
            setQtyBook(sum);
        }
    }   //  createMA
    
    /***********************************************************/

    private BigDecimal costoTotal = Env.ZERO;
    
    /**
     * Total de costo por cantidad
     * @return
     */
    public BigDecimal getTotal(){
        costoTotal  = getCostAverage().multiply(getOriginalQty());
        return costoTotal;     
    }
    
    
}	//	MEXMESalidaGastoLine
