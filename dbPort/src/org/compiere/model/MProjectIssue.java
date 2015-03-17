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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Project Issue Model
 *
 *	@author Jorg Janke
 *	@version $Id: MProjectIssue.java,v 1.6 2006/08/11 02:26:22 mrojas Exp $
 */
public class MProjectIssue extends X_C_ProjectIssue
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4714411434615096132L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_ProjectIssue_ID id
	 *	@param trxName transaction
	 */
	public MProjectIssue (Properties ctx, int C_ProjectIssue_ID, String trxName)
	{
		super (ctx, C_ProjectIssue_ID, trxName);
		if (C_ProjectIssue_ID == 0)
		{
		//	setC_Project_ID (0);
		//	setLine (0);
		//	setM_Locator_ID (0);
		//	setM_Product_ID (0);
		//	setMovementDate (new Timestamp(System.currentTimeMillis()));
			setMovementQty (Env.ZERO);
			setPosted (false);
			setProcessed (false);
		}
	}	//	MProjectIssue

	/**
	 * 	Load Constructor
	 * 	@param ctx context
	 * 	@param rs result set
	 *	@param trxName transaction
	 */
	public MProjectIssue (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProjectIssue

	/**
	 * 	New Parent Constructor
	 *	@param project parent
	 */
	public MProjectIssue (MProject project)
	{
		this (project.getCtx(), 0, project.get_TrxName());
		setClientOrg(project.getAD_Client_ID(), project.getAD_Org_ID());
		setC_Project_ID (project.getC_Project_ID());	//	Parent
		setLine (getNextLine());
		m_parent = project;
		//
	//	setM_Locator_ID (0);
	//	setM_Product_ID (0);
		//
		setMovementDate (DB.getTimestampForOrg(getCtx()));
		setMovementQty (Env.ZERO);
		setPosted (false);
		setProcessed (false);
	}	//	MProjectIssue

	/**	Parent				*/
	private MProject	m_parent = null;
	
	/**
	 *	Get the next Line No
	 * 	@return next line no
	 */
	private int getNextLine()
	{
		return DB.getSQLValue(get_TrxName(), 
			"SELECT COALESCE(MAX(Line),0)+10 FROM C_ProjectIssue WHERE C_Project_ID=?", getC_Project_ID());
	}	//	getLineFromProject

	/**
	 * 	Set Mandatory Values
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param MovementQty qty
	 */
	public void setMandatory (int M_Locator_ID, int M_Product_ID, BigDecimal MovementQty)
	{
		setM_Locator_ID (M_Locator_ID);
		setM_Product_ID (M_Product_ID);
		setMovementQty (MovementQty);
	}	//	setMandatory

	/**
	 * 	Get Parent
	 *	@return project
	 */
	public MProject getParent()
	{
		if (m_parent == null && getC_Project_ID() != 0)
			m_parent = new MProject (getCtx(), getC_Project_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	
	/**************************************************************************
	 * 	Process Issue
	 *	@return true if processed
	 */
	public boolean process()
	{
		if (!save())
			return false;
		if (getM_Product_ID() == 0)
		{
			log.log(Level.SEVERE, "No Product");
			return false;
		}

		MProduct product = MProduct.get (getCtx(), getM_Product_ID());

		//	If not a stocked Item nothing to do
		if (!product.isStocked())
		{
			setProcessed(true);
			return save();
		}

		/** @todo Transaction */

		/* Expert:Hassan - Registramos una tranzaccion por cada Esquema contable del Cliente (Bloque Modificado)*/
		MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		MTransaction[] trxs = new MTransaction[acctss.length];
		boolean trxs_ok = true;
		for(int h = 0; h < acctss.length; h++){
			MAcctSchema as = acctss[h];
			//	**	Create Material Transactions **
			
			//Current Costs
//			String costingMethod = as.getCostingMethod();
//			
//			// Expert: #Post,Cost And Price
//    		MProductCategoryAcct pca = MProductCategoryAcct.getAcct( 
//    				 product, as);
//			if (pca.getCostingMethodDefault() != null)
//				costingMethod = pca.getCostingMethodDefault();
			
			//	Create Transaction
			final MTransaction mTrx = new MTransaction (
					getCtx(), product,
					getM_AttributeSetInstance_ID(),
					as, getAD_Org_ID(), product.getCostingMethod(as), 
					getMovementQty().negate(), 0, false, MTransaction.MOVEMENTTYPE_WorkOrderPlus, getM_Locator_ID(),
					getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema contable
			

			mTrx.setC_ProjectIssue_ID(getC_ProjectIssue_ID());
			trxs[h] = mTrx;
		}/* Expert:Hassan - Fin del Bloque */
		
		//
		MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
		if (MStorage.add(getCtx(), loc.getM_Warehouse_ID(), getM_Locator_ID(), 
				getM_Product_ID(), getM_AttributeSetInstance_ID(), getM_AttributeSetInstance_ID(),
				getMovementQty().negate(), null, null, get_TrxName()))
		{
			/* Expert:Hassan - Verificamos cada transaccion*/
			for(int ih = 0; ih < trxs.length; ih++){
				if(!trxs[ih].save())
					trxs_ok = false;
			}
			/* Expert:Hassan - Fin del Bloque */
			if (trxs_ok) // Expert:Hassan - Prosegirmos?
			{
				setProcessed (true);
				if (save())
					return true;
				else
					log.log(Level.SEVERE, "Issue not saved");		//	requires trx !!
			}
			else
				log.log(Level.SEVERE, "Transaction not saved");	//	requires trx !!
		}
		else
			log.log(Level.SEVERE, "Storage not updated");			//	OK
		//
		return false;
	}	//	process

}	//	MProjectIssue
