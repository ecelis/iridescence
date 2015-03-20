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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_AD_Client;
import org.compiere.model.X_T_Replenish;
import org.compiere.util.CompiereSystemError;
import org.compiere.util.CompiereUserError;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ReplenishInterface;
import org.compiere.util.Utilerias;

/**
 *	Replenishment Report
 *
 *  @author Jorg Janke
 *  @version $Id: ReplenishReport.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ReplenishReport extends SvrProcess
{
	/** Warehouse				*/
	private transient int		pMWarehouseID = 0;
	/**	Optional BPartner		*/
	private transient int		pCBPartnerID = 0;
	/** Create (POO)Purchse Order or (POR)Requisition or (MMM)Movements */
	private transient String	pReplenishCreate = null;
	/** Document Type			*/
	private transient int		pCDocTypeID = 0;
	/** Return Info				*/
	private transient String	minfo = "";

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare(){
		final ProcessInfoParameter[] para = getParameter();

		for (final ProcessInfoParameter element : para) {
			if (element.getParameter() != null) {
				final String name = element.getParameterName();
				if ("M_Warehouse_ID".equals(name)) {
					pMWarehouseID = element.getParameterAsInt();
				} else if ("C_BPartner_ID".equals(name)) {
					pCBPartnerID = element.getParameterAsInt();
				} else if ("ReplenishmentCreate".equals(name)) {
					pReplenishCreate = (String) element.getParameter();
				} else if ("C_DocType_ID".equals(name)) {
					pCDocTypeID = element.getParameterAsInt();
				} else {
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
				}
			}
		}
	}	//	prepare

	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception if not successful
	 */
	@Override
	protected String doIt() throws Exception
	{
		log.info("M_Warehouse_ID=" + pMWarehouseID
			+ ", C_BPartner_ID=" + pCBPartnerID
			+ " - ReplenishmentCreate=" + pReplenishCreate
			+ ", C_DocType_ID=" + pCDocTypeID);
		if (pReplenishCreate != null && pCDocTypeID == 0){
			throw new CompiereUserError("@FillMandatory@ @C_DocType_ID@");
		}


		final MWarehouse wHouse = MWarehouse.get(getCtx(), pMWarehouseID);
		if (wHouse.get_ID() == 0)  {
			throw new CompiereSystemError("@FillMandatory@ @M_Warehouse_ID@");
		}
		//
		prepareTable();
		fillTable(wHouse);
		//
		if (pReplenishCreate == null){
			return "OK";
		}
		//
		final MDocType docType = MDocType.get(getCtx(), pCDocTypeID);
		if (!docType.getDocBaseType().equals(pReplenishCreate)){
			throw new CompiereSystemError("@C_DocType_ID@=" + docType.getName() + " <> " + pReplenishCreate);
		}
		//
		if ("POO".equals(pReplenishCreate)){
			createPO();
		}
		else if ("POR".equals(pReplenishCreate)){
			createRequisition();
		}
		else if ("MMM".equals(pReplenishCreate)){
			createMovements();
		}

		return minfo;
	}	//	doIt

	/**
	 * 	Prepare/Check Replenishment Table
	 */
	private void prepareTable()
	{
		int retVal;
		//	Level_Max must be >= Level_Max
		final StringBuilder st1 = new StringBuilder("UPDATE M_Replenish ")
		.append("SET Level_Max = Level_Min ")
		.append("WHERE Level_Max < Level_Min ");
		retVal = DB.executeUpdate(st1.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Corrected Max_Level=" + retVal);
		}

		//	Minimum Order should be 1
		final StringBuilder st2 = new StringBuilder("UPDATE M_Product_PO ")
		.append("SET Order_Min = 1 ")
		.append("WHERE Order_Min IS NULL OR Order_Min < 1 ");
		retVal = DB.executeUpdate(st2.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Corrected Order Min=" + retVal);
		}

		//	Pack should be 1
		final StringBuilder st3 = new StringBuilder("UPDATE M_Product_PO ")
		.append(" SET Order_Pack = 1 ")
		.append("WHERE Order_Pack IS NULL OR Order_Pack < 1 ");
		retVal = DB.executeUpdate(st3.toString() , get_TrxName());
		if (retVal != 0){
			log.fine("Corrected Order Pack=" + retVal);
		}

		//	Set Current Vendor where only one vendor
		final StringBuilder st4 = new StringBuilder("UPDATE M_Product_PO p ")
		.append("SET IsCurrentVendor='Y' ")
		.append("WHERE IsCurrentVendor<>'Y' ")
		.append("AND EXISTS (SELECT * FROM M_Product_PO pp ")
		.append("WHERE p.M_Product_ID=pp.M_Product_ID ")
		.append("GROUP BY pp.M_Product_ID, pp.c_bpartner_id   ")
		.append("HAVING COUNT(*) = 1) ");
		retVal = DB.executeUpdate(st4.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Corrected CurrentVendor(Y)=" + retVal);
		}

		//	More then one current vendor
		final StringBuilder st5 = new StringBuilder("UPDATE M_Product_PO p ")
		.append(" SET IsCurrentVendor='N' ")
		.append("WHERE IsCurrentVendor = 'Y' ")
		.append("AND EXISTS (SELECT * FROM M_Product_PO pp  ")
		.append("WHERE p.M_Product_ID=pp.M_Product_ID AND pp.IsCurrentVendor='Y'  ")
		.append("GROUP BY pp.M_Product_ID, pp.c_bpartner_id  ")
		.append("HAVING COUNT(*) > 1) ");
		retVal = DB.executeUpdate(st5.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Corrected CurrentVendor(N)=" + retVal);
		}

		//	Just to be sure
		final StringBuilder st6 = new StringBuilder("DELETE T_Replenish WHERE AD_PInstance_ID= ")
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(st6.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Delete Existing Temp=" + retVal);
		}

	}	//	prepareTable

	/**
	 * 	Fill Table
	 * 	@param mWareHouse warehouse
	 */
	private void fillTable (final MWarehouse mWareHouse) throws Exception
	{
		int retVal;

		if (pCBPartnerID == 0){
			insertTReplenishnoPartner();
		}else{
			insertTReplenish();
		}

		final StringBuilder stUpRep = new StringBuilder("UPDATE T_Replenish t SET ")
		//expert : gisela lee : asignar 0 en caso de null
		.append("QtyOnHand = (SELECT NVL(SUM(QtyOnHand),0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID")
		.append( " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID),")
		.append("QtyReserved = (SELECT NVL(SUM(QtyReserved), 0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID")
		.append( " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID),")
		.append( "QtyOrdered = (SELECT NVL(SUM(QtyOrdered), 0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID")
		.append( " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID)");

		//expert : gisela lee : asignar 0 en caso de null
		if (pCDocTypeID != 0){
			stUpRep.append( ", C_DocType_ID=" + pCDocTypeID);
		}
		stUpRep.append( " WHERE AD_PInstance_ID=");
		stUpRep.append( getAD_PInstance_ID());
		retVal = DB.executeUpdate(stUpRep.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Update #" + retVal);
		}
		//Cambia a las cantidades correspondientes
		updateQtys(mWareHouse);
		fillTableReplenish(mWareHouse);

	}//	fillTable
	/**
	 *
	 * @param mWareHouse
	 * @throws CompiereUserError
	 */
		private void fillTableReplenish(final MWarehouse mWareHouse) throws CompiereUserError{
		/**
		 * @author rsolorzano
		 * cambios para unidades de medida de volumen
		 * Existe una transaccion que se debe tomar en cuenta para colocar las cantidades
		 */
		final X_T_Replenish[] replenishsVol = getReplenish(null);

		for (final X_T_Replenish replenish : replenishsVol) {

			final MProduct product = MProduct.get(Env.getCtx(), replenish.getM_Product_ID());

			if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){

				replenish.setLevel_Min_Vol(replenish.getLevel_Min());
				replenish.setLevel_Max_Vol(replenish.getLevel_Max());
				replenish.setQtyOnHand_Vol(replenish.getQtyOnHand());
				replenish.setQtyOrdered_Vol(replenish.getQtyOrdered());
				replenish.setQtyReserved_Vol(replenish.getQtyReserved());
				replenish.setQtyToOrder_Vol(replenish.getQtyToOrder());
				replenish.setOrder_Min_Vol(replenish.getOrder_Min());
				replenish.setOrder_Pack_Vol(replenish.getOrder_Pack());
				//Mostrar las cantidad minima a pedir ETS #04832
				//Las columnas no se utilizan en otro lado que no sea este reporte (26/abr/13)
				//La unidad minima a pedir es la cantidad a ordernar
				//Order_pack no se utilizaba, tambieb mostrara en volumen la cantidad a pedir(26/abr/13)
				replenish.setOrder_Min(replenish.getQtyToOrder());
				replenish.setOrder_Pack(replenish.getQtyToOrder());

			}else{

				final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);

				if(rates == null){

					throw new CompiereUserError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), product.getName());

				}else{
					//-----FIXME settear las variables del reporte----
					//---- defecto 3289
					final BigDecimal levelMin = replenish.getLevel_Min().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getLevel_Min(), get_TrxName());
					final BigDecimal levelMax = replenish.getLevel_Max().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getLevel_Max(), get_TrxName());
					//----- 3289 defecto//
					final BigDecimal qtyOnHand = replenish.getQtyOnHand().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getQtyOnHand(), get_TrxName());
					final BigDecimal qtyOrdered = replenish.getQtyOrdered().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getQtyOrdered(), get_TrxName());
					final BigDecimal qtyReserved = replenish.getQtyReserved().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getQtyReserved(), get_TrxName());
					final BigDecimal qtyToOrder = replenish.getQtyToOrder().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getQtyToOrder(), get_TrxName());

					final BigDecimal order_Min_Min = replenish.getOrder_Min().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductFrom (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getOrder_Min(), get_TrxName());
					final BigDecimal order_Pack_Min = replenish.getOrder_Pack().compareTo(BigDecimal.ZERO) == 0?BigDecimal.ZERO:MEXMEUOMConversion.convertProductFrom (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getOrder_Pack(), get_TrxName());


					replenish.setLevel_Min_Vol(levelMin);
					replenish.setLevel_Max_Vol(levelMax);
					replenish.setQtyOnHand_Vol(qtyOnHand);
					replenish.setQtyOrdered_Vol(qtyOrdered);
					replenish.setQtyReserved_Vol(qtyReserved);
					replenish.setQtyToOrder_Vol(qtyToOrder);

					//los datos de orderMin y orderPack ya se encuentran almacenados en la unidad de volumen
					replenish.setOrder_Min_Vol(replenish.getOrder_Min());
					replenish.setOrder_Pack_Vol(replenish.getOrder_Pack());

					replenish.setOrder_Min(order_Min_Min);
					replenish.setOrder_Pack(order_Pack_Min);

					//Mostrar las cantidad minima a pedir ETS #04832
					//Las columnas no se utilizan en otro lado que no sea este reporte (26/abr/13)
					//La unidad minima a pedir es la cantidad a ordernar
					//Order_pack no se utilizaba, tambieb mostrara en volumen la cantidad a pedir(26/abr/13)
					replenish.setOrder_Min(replenish.getQtyToOrder());
					replenish.setOrder_Pack(qtyToOrder);
				}

			}

			replenish.save();

		}
		//	Custom Replenishment
		final String className = mWareHouse.getReplenishmentClass();
		if (StringUtils.isBlank(className)){
			return;
		}

		//	Get Replenishment Class
		ReplenishInterface custom;
		try
		{
			final Class<?> clazz = Class.forName(className);
			custom = (ReplenishInterface)clazz.newInstance();
		}
		catch (final Exception e)
		{
			throw new CompiereUserError("No custom Replenishment class " + className + Constantes.SPACE + e.toString(), e);
		}

		final X_T_Replenish[] replenishs = getReplenish("ReplenishType='9'");
		for (final X_T_Replenish replenish : replenishs) {
			if (replenish.getReplenishType().equals(X_T_Replenish.REPLENISHTYPE_Custom))
			{
				BigDecimal qto = null;
				try
				{
					qto = custom.getQtyToOrder(mWareHouse, replenish);
				}
				catch (final Exception e)
				{
					log.log(Level.SEVERE, custom.toString(), e);
				}
				if (qto == null){
					qto = Env.ZERO;
				}
				replenish.setQtyToOrder(qto);
				replenish.save();
			}
		}
	}//	fillTable

	/**
	 *
	 */
	private void insertTReplenish(){
		int retVal;

		final StringBuilder stInsertRep = new StringBuilder("INSERT INTO T_Replenish ")
		.append("(AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, AD_Client_ID, AD_Org_ID, ")
		.append(" ReplenishType, Level_Min, Level_Max,")
		.append(" C_BPartner_ID, Order_Min, Order_Pack, seqnoinsert, QtyToOrder, ReplenishmentCreate) SELECT  ")
		.append(getAD_PInstance_ID())
		.append(", r.M_Warehouse_ID, r.M_Product_ID, r.AD_Client_ID, r.AD_Org_ID,")
		.append(" r.ReplenishType, r.Level_Min, r.Level_Max,")
		.append(" po.C_BPartner_ID, po.Order_Min, po.Order_Pack, ") //po.Order_Min y po.Order_Pack se encuentran en la unidad de volumen
		.append(" row_number() over (partition by  m_warehouse_id, r.m_product_id order by m_warehouse_id), 0, ");

		if (pReplenishCreate == null) {
			stInsertRep.append("null");
		} else {
			stInsertRep.append("'" + pReplenishCreate + "'");
		}

		stInsertRep.append(" FROM M_Replenish r")
		.append(" INNER JOIN M_Product_PO po ON (r.M_Product_ID=po.M_Product_ID) ")
		.append(" WHERE po.IsCurrentVendor='Y'")	// Only Current Vendor
		.append(" AND r.ReplenishType<>'0'")
		.append(" AND po.IsActive='Y' AND r.IsActive='Y' AND r.M_Warehouse_ID=")
		.append(pMWarehouseID);

		if (pCBPartnerID != 0) {
			stInsertRep.append(" AND po.C_BPartner_ID=" + pCBPartnerID);
		}

		retVal = DB.executeUpdate(stInsertRep.toString(), get_TrxName());
		log.fine(stInsertRep.toString());
		log.fine("Insert (1) #" + retVal);
		//Insert the products dont have cbpartner
		insertTReplenishnoPartner();
	}

	/**
	 *
	 */
	private void insertTReplenishnoPartner(){
		int retVal;
		final StringBuilder stInsertRep2 = new StringBuilder("INSERT INTO T_Replenish ")
		.append("(AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, AD_Client_ID, AD_Org_ID," )
		.append(" ReplenishType, Level_Min, Level_Max,")
		.append( " C_BPartner_ID, Order_Min, Order_Pack, QtyToOrder, seqNoInsert, ReplenishmentCreate) ")
		.append( "SELECT " + getAD_PInstance_ID())
		.append( ", r.M_Warehouse_ID, r.M_Product_ID, r.AD_Client_ID, r.AD_Org_ID,")
		.append( " r.ReplenishType, r.Level_Min, r.Level_Max,")
		.append( " 0, 1, 1, 0, ")//C_BPartner_ID not null
		//Secuency of the table since parter it's not mandatory anymore
		.append(" row_number() over (partition by  m_warehouse_id, r.m_product_id order by m_warehouse_id),");
		if (pReplenishCreate == null){
			stInsertRep2.append("null");
		}else{
			stInsertRep2.append("'" + pReplenishCreate + "'");
		}
//			row_number() OVER (ORDER BY r.M_Warehouse_ID)
		stInsertRep2.append(" FROM M_Replenish r LEFT JOIN M_Product_PO po ON (r.M_Product_ID=po.M_Product_ID) ")
		.append( "WHERE r.ReplenishType<>'0' AND r.IsActive='Y'")
		.append( " AND r.M_Warehouse_ID=" + pMWarehouseID)
		.append( " AND NOT EXISTS (SELECT * FROM T_Replenish t ")
		.append( "WHERE r.M_Product_ID=t.M_Product_ID  AND AD_PInstance_ID=")
		.append( getAD_PInstance_ID())
		.append(")");

		retVal = DB.executeUpdate(stInsertRep2.toString(), get_TrxName());
		log.fine("Insert (BP) #" + retVal);

	}

	/**
	 * Update t_replenish quantities
	 * @param mWareHouse
	 */
	private void updateQtys(final MWarehouse mWareHouse){
		int retVal;
		//	Delete inactive products and replenishments
		/**sql = "DELETE T_Replenish r "+ "WHERE (EXISTS (SELECT * FROM M_Product p "
			+ "WHERE p.M_Product_ID=r.M_Product_ID AND p.IsActive='N')"
			+ " OR EXISTS (SELECT * FROM M_Replenish rr "
			+ " WHERE rr.M_Product_ID=r.M_Product_ID AND rr.IsActive='N'))"
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
			no = DB.executeUpdate(sql, get_TrxName());
			if (no != 0) log.fine("Delete Inactive=" + no);*/

		//	Ensure Data consistency
		String sqlUpdate = "UPDATE T_Replenish SET QtyOnHand = 0 WHERE QtyOnHand IS NULL";
		retVal = DB.executeUpdate(sqlUpdate, get_TrxName());
		log.fine("Update " + retVal);

		sqlUpdate = "UPDATE T_Replenish SET QtyReserved = 0 WHERE QtyReserved IS NULL";
		retVal = DB.executeUpdate(sqlUpdate, get_TrxName());
		log.fine("Update " + retVal);

		sqlUpdate = "UPDATE T_Replenish SET QtyOrdered = 0 WHERE QtyOrdered IS NULL";
		retVal = DB.executeUpdate(sqlUpdate, get_TrxName());
		log.fine("Update #" + retVal);

		//	Set Minimum / Maximum Maintain Level
		//	X_M_Replenish.REPLENISHTYPE_ReorderBelowMinimumLevel
		final StringBuilder stMinLevel = new StringBuilder("UPDATE T_Replenish SET")
		.append(" QtyToOrder = Level_Min - QtyOnHand + QtyReserved - QtyOrdered ")
		.append(" WHERE ReplenishType='1' AND AD_PInstance_ID= ")
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(stMinLevel.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Update Type-1=" + retVal);
		}

		//	X_M_Replenish.REPLENISHTYPE_MaintainMaximumLevel
		final StringBuilder stMaxLevel = new StringBuilder("UPDATE T_Replenish SET")
		.append(" QtyToOrder = Level_Max - QtyOnHand + QtyReserved - QtyOrdered ")
		.append("WHERE ReplenishType='2'  AND AD_PInstance_ID= " )
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(stMaxLevel.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Update Type-2=" + retVal);
		}

		//	Minimum Order Quantity
		final StringBuilder st9 = new StringBuilder("UPDATE T_Replenish SET QtyToOrder = 0 ")
		.append("WHERE QtyToOrder < Order_Min AND AD_PInstance_ID=")
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(st9.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Set MinOrderQty=" + retVal);
		}

		//	Even dividable by Pack
		final StringBuilder st10= new StringBuilder("UPDATE T_Replenish")
		.append(" SET QtyToOrder = QtyToOrder - MOD(QtyToOrder, Order_Pack) + Order_Pack ")
		.append("WHERE MOD(QtyToOrder, Order_Pack) <> 0 AND AD_PInstance_ID=")
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(st10.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Set OrderPackQty=" + retVal);
		}

		//	Source from other warehouse
		if (mWareHouse.getM_WarehouseSource_ID() != 0)
		{
			final StringBuilder st11= new StringBuilder("UPDATE T_Replenish")
			.append(" SET M_WarehouseSource_ID=" + mWareHouse.getM_WarehouseSource_ID() )
			.append(" WHERE AD_PInstance_ID=" + getAD_PInstance_ID());
			retVal = DB.executeUpdate(st11.toString(), get_TrxName());
			if (retVal != 0){
				log.fine("Set Source Warehouse=" + retVal);
			}
		}

		//	Check Source Warehouse
		final StringBuilder st12= new StringBuilder("UPDATE T_Replenish")
		.append(" SET M_WarehouseSource_ID = NULL " )
		.append( "WHERE M_Warehouse_ID=M_WarehouseSource_ID  AND AD_PInstance_ID=")
		.append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(st12.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Set same Source Warehouse=" + retVal);
		}

		//	Delete rows where nothing to order
		final StringBuilder st13 = new StringBuilder("DELETE T_Replenish WHERE QtyToOrder < 1 ")
		 .append(" AND AD_PInstance_ID= ").append(getAD_PInstance_ID());
		retVal = DB.executeUpdate(st13.toString(), get_TrxName());
		if (retVal != 0){
			log.fine("Delete No QtyToOrder=" + retVal);
		}
		//Delete where no partner selected to only see what products to replenish
		if(pCBPartnerID == 0){
			final StringBuilder st14 = new StringBuilder("DELETE FROM T_replenish WHERE M_Product_ID IN ( SELECT M_Product_ID FROM T_Replenish ")
			.append(" WHERE Ad_Pinstance_ID = ").append(getAD_PInstance_ID()).append(" AND M_Product_ID IN ")
			.append(" (SELECT M_Product_ID FROM T_Replenish WHERE ad_pinstance_id = ").append(getAD_PInstance_ID())
			.append(" GROUP BY m_product_ID HAVING COUNT(*) > 1 ) GROUP BY M_Product_ID , Ad_pinstance_ID) ")
			.append(" AND ad_pinstance_id = ").append(getAD_PInstance_ID())
			//all duplicates product,warehouse,pistance only diference is seqnoinsert
			.append(" AND SeqNoInsert >1 ");
			retVal = DB.executeUpdate(st14.toString(), get_TrxName());
			if (retVal != 0){
				log.fine("Delete No QtyToOrder=" + retVal);
			}
		}

	}

	/**
	 * 	Create PO's
	 * 	Creación de ordenes de compra a partir de la impresión de reporte de reabasto.
	 *  doIt() / if("POO".equals(pReplenishCreate))
	 *
	 */
	private void createPO(){
		int noOrders = 0;
		final StringBuilder info = new StringBuilder();

		final List<I_C_BPartner> lstBPartners = new ArrayList<I_C_BPartner>();
		final X_T_Replenish[] replen = getReplenish("M_WarehouseSource_ID IS NULL  ");
		for(final X_T_Replenish rep : replen){
			if(!lstBPartners.contains(rep.getC_BPartner())){
				 lstBPartners.add(rep.getC_BPartner());
			}
		}

		for (int i = 0; i < lstBPartners.size(); i++)
		{
			final X_T_Replenish[] lstReplenish =  getReplenish("M_WarehouseSource_ID IS NULL AND C_BPartner_ID =" + lstBPartners.get(i).getC_BPartner_ID());
			final X_T_Replenish replenish = lstReplenish[0];
			final MWarehouse wareHouse = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			final MBPartner bPartner = new MBPartner(getCtx(), replenish.getC_BPartner_ID(), get_TrxName());
			BigDecimal totalLines = Env.ZERO;

			final MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			order.setIsSOTrx(false);
			order.setC_DocTypeTarget_ID(pCDocTypeID);
			order.setBPartner(bPartner);
			order.setSalesRep_ID(getAD_User_ID());
			order.setDescription(Msg.getMsg(getCtx(), "Replenishment"));

			//	Set Org/WH
			order.setAD_Org_ID(wareHouse.getAD_Org_ID());
			order.setM_Warehouse_ID(wareHouse.getM_Warehouse_ID());

			if (!order.save()){
				return;
			}

			log.fine(order.toString());
			noOrders++;
			info.append(Constantes.SPACE);
			info.append(order.getDocumentNo());

			for(final X_T_Replenish rep : lstReplenish){
				final MOrderLine line = new MOrderLine (order);

				//line.setPrice(); //TODO Agregar precio a partir de conversión de UOM.
				line.setM_Product_ID(rep.getM_Product_ID());
				line.setQty(rep.getQtyToOrder());
				line.setC_UOM_ID(rep.getM_Product().getC_UOM_ID());
				line.setC_UOMVolume_ID(rep.getM_Product().getC_UOMVolume_ID());


				/**
				 * @author rsolorzano
				 * cambios para unidad de medida
				 */
				final MProductPrice mPrice =  MProductPrice.getProductPrice(replenish.getC_BPartner_ID(), 0, rep.getM_Product_ID(), order.isSOTrx());
				final MProduct product = MProduct.get(Env.getCtx(), rep.getM_Product_ID());

				if(mPrice == null){

					line.setPriceActual (BigDecimal.ZERO);
					line.setPriceList (BigDecimal.ZERO);
					line.setPriceLimit (BigDecimal.ZERO);
					line.setPriceEntered(BigDecimal.ZERO);

					line.setPriceActual_Vol (BigDecimal.ZERO);
					line.setPriceList_Vol (BigDecimal.ZERO);
					line.setPriceEntered_Vol(BigDecimal.ZERO);

				}else{

					line.setPriceActual (mPrice.getPriceList());
					line.setPriceList (mPrice.getPriceList());
					line.setPriceLimit (mPrice.getPriceList());
					line.setPriceEntered(mPrice.getPriceList());

					line.setPriceActual_Vol (mPrice.getPriceList_Vol());
					line.setPriceList_Vol (mPrice.getPriceList_Vol());
					line.setPriceEntered_Vol(mPrice.getPriceList_Vol());

				}

				if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){

					line.setQtyEntered_Vol (rep.getQtyToOrder());
					line.setQtyOrdered_Vol (rep.getQtyToOrder());

				}else{

					final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);
					if(rates == null){
						log.severe("Not valid convertions for " +  product.toString());
						return;
					}

					//QtyTmp VOL QTY

					final BigDecimal QtyTmp =  rep.getQtyToOrder().compareTo(BigDecimal.ZERO)==0?BigDecimal.ZERO:
						MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), rep.getQtyToOrder(), null);
					line.setQtyEntered_Vol (QtyTmp==null?BigDecimal.ZERO:QtyTmp);
					line.setQtyOrdered_Vol (QtyTmp==null?BigDecimal.ZERO:QtyTmp);

				}


				totalLines = totalLines.add(line.getLineNetAmt());

				if(!line.save()){
					log.severe("Could not save line: " +  line.toString());
				}
			}

			order.setGrandTotal(totalLines);

			if (!order.save()){
				return;
			}

			/**
			 * @author rsolorzano
			 * se muestran los documentos creados
			 */
			if(order!=null){
				//se limpia el log
				this.getProcessInfo().setLogList(new ArrayList<ProcessInfoLog>());
				addLog(0, null, totalLines , Utilerias.getMsg(Env.getCtx(), "egresos.oc.title") + " : " + order.getDocumentNo());
			}

		}


		minfo = "#" + noOrders + info;
		log.info(minfo);
	}	//	createPO

	/**
	 * 	Create Requisition
	 */
	private void createRequisition(){
		int noReqs = 0;
		final StringBuilder info = new StringBuilder();
		//
		MRequisition requisition = null;
		MWarehouse wareHouse = null;
		final X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NULL");
		for (final X_T_Replenish replenish : replenishs) {
			if (wareHouse == null || wareHouse.getM_Warehouse_ID() != replenish.getM_Warehouse_ID()){
				wareHouse = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			}

			//
			if (requisition == null || requisition.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
			{
				requisition = new MRequisition (getCtx(), 0, get_TrxName());
				requisition.setAD_User_ID (getAD_User_ID());
				requisition.setC_DocType_ID(pCDocTypeID);
				requisition.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				requisition.setAD_Org_ID(wareHouse.getAD_Org_ID());
				requisition.setM_Warehouse_ID(wareHouse.getM_Warehouse_ID());
				if (!requisition.save()){
					return;
				}
				log.fine(requisition.toString());
				noReqs++;
				info.append(Constantes.SPACE);
				info.append(requisition.getDocumentNo());
			}
			//
			final MRequisitionLine line = new MRequisitionLine(requisition);
			final MProduct product = new MProduct(Env.getCtx(), replenish.getM_Product_ID(), get_TrxName());
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setC_BPartner_ID(replenish.getC_BPartner_ID());
			//C_UOM & C_UOMVOL changes
			line.setQty(replenish.getQtyToOrder());
			line.setC_UOM_ID(product.getC_UOM_ID());
			line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());

			if(product.getC_UOM_ID() == product.getC_UOMVolume_ID()){
				line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
				line.setQty_Vol(replenish.getQtyToOrder());
			}else{
				final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),null);
				if(rates == null){
					log.severe("Not valid convertions for " +  product.toString());
					return;
				}
				//QtyTmp VOL QTY
				final BigDecimal QtyTmp =  replenish.getQtyToOrder().compareTo(BigDecimal.ZERO)==0?BigDecimal.ZERO:
					MEXMEUOMConversion.convertProductTo (Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), replenish.getQtyToOrder(), null);
				line.setQty_Vol(QtyTmp==null?BigDecimal.ZERO:QtyTmp);
			}

			line.setPrice();
			if(!line.save()){
				log.severe("Could not save line: " +  line.toString());
			}
		}

		/**
		 * @author rsolorzano
		 * se muestran los documentos creados
		 */
		if(requisition!=null){
			//se limpia el log
			this.getProcessInfo().setLogList(new ArrayList<ProcessInfoLog>());
			final BigDecimal lines = replenishs.length == 0?BigDecimal.ZERO:new BigDecimal(replenishs.length);
			addLog(0, null, lines , Utilerias.getMsg(Env.getCtx(), "egresos.re.title") + " : " + requisition.getDocumentNo());
		}


		minfo = "#" + noReqs + info;
		log.info(minfo);
	}	//	createRequisition

	/**
	 * 	Create Inventory Movements
	 */
	private void createMovements()
	{
		int noMoves = 0;
		final StringBuilder info = new StringBuilder();
		//
		MClient client = null;
		MMovement move = null;
		int M_Warehouse_ID = 0;
		int whSourceId = 0;
		MWarehouse whSource = null;
		MWarehouse wareHouse = null;
		final X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NOT NULL");
		for (final X_T_Replenish replenish : replenishs) {
			if (whSource == null || whSource.getM_WarehouseSource_ID() != replenish.getM_WarehouseSource_ID()){
				whSource = MWarehouse.get(getCtx(), replenish.getM_WarehouseSource_ID());
			}
			if (wareHouse == null || wareHouse.getM_Warehouse_ID() != replenish.getM_Warehouse_ID()){
				wareHouse = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			}
			if (client == null || client.getAD_Client_ID() != whSource.getAD_Client_ID()){
				client = MClient.get(getCtx(), whSource.getAD_Client_ID());
			}

			//
			if (move == null
				|| whSourceId != replenish.getM_WarehouseSource_ID()
				|| M_Warehouse_ID != replenish.getM_Warehouse_ID())
			{
				whSourceId = replenish.getM_WarehouseSource_ID();
				M_Warehouse_ID = replenish.getM_Warehouse_ID();

				move = new MMovement (getCtx(), 0, get_TrxName());
				move.setC_DocType_ID(pCDocTypeID);
				move.setDescription(Msg.getMsg(getCtx(), "Replenishment")
					+ ": " + whSource.getName() + "->" + wareHouse.getName());
				//	Set Org
				move.setAD_Org_ID(whSource.getAD_Org_ID());
				if (!move.save()){
					return;
				}
				log.fine(move.toString());
				noMoves++;
				info.append(Constantes.SPACE);
				info.append(move.getDocumentNo());
			}
			//	To
			final int M_LocatorTo_ID = wareHouse.getDefaultLocator().getM_Locator_ID();
			//	From: Look-up Storage
			final MProduct product = MProduct.get(getCtx(), replenish.getM_Product_ID());
			final MProductCategory prodCategory = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
			String MMPolicy = prodCategory.getMMPolicy();
			if (MMPolicy == null || MMPolicy.length() == 0){
				MMPolicy = client.getMMPolicy();
			}

			//
			final MStorage[] storages = MStorage.getWarehouse(getCtx(),
				whSource.getM_Warehouse_ID(), replenish.getM_Product_ID(), 0, 0,
				true, null,
				X_AD_Client.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
			//
			BigDecimal target = replenish.getQtyToOrder();
			for (final MStorage storage : storages) {
				if (storage.getQtyOnHand().signum() <= 0){
					continue;
				}
				BigDecimal moveQty = target;
				if (storage.getQtyOnHand().compareTo(moveQty) < 0){
					moveQty = storage.getQtyOnHand();
				}
				//
				final MMovementLine line = new MMovementLine(move);
				line.setM_Product_ID(replenish.getM_Product_ID());
				line.setMovementQty(moveQty);
				if (replenish.getQtyToOrder().compareTo(moveQty) != 0){
					line.setDescription("Total: " + replenish.getQtyToOrder());
				}
				line.setM_Locator_ID(storage.getM_Locator_ID());		//	from
				line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
				line.setM_LocatorTo_ID(M_LocatorTo_ID);					//	to
				line.setM_AttributeSetInstanceTo_ID(storage.getM_AttributeSetInstance_ID());
				line.save();
				//
				target = target.subtract(moveQty);
				if (target.signum() == 0){
					break;
				}
			}
		}
		if (replenishs.length == 0)
		{
			minfo = "No Source Warehouse";
			log.warning(minfo);
		}
		else
		{
			minfo = "#" + noMoves + info;
			log.info(minfo);
		}
	}	//	createRequisition

	/**
	 * 	Get Replenish Records
	 *	@return replenish
	 */
	private X_T_Replenish[] getReplenish (final String where){
		final StringBuilder sql = new StringBuilder("SELECT * FROM T_Replenish r ")
		.append("INNER JOIN m_product p ON r.m_product_id = p.m_product_id ")
		.append("LEFT JOIN exme_productoorg org ON p.m_product_id = org.m_product_id  ")
		.append("WHERE AD_PInstance_ID=? AND isobsolete = ? ");// AND C_BPartner_ID > 0 "); C_BPartner_ID can be 0

		if (where != null && where.length() > 0){
			sql.append(" AND " + where)
			.append( " ORDER BY M_Warehouse_ID, M_WarehouseSource_ID, C_BPartner_ID");
		}

		final ArrayList<X_T_Replenish> list = new ArrayList<X_T_Replenish>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt (1, getAD_PInstance_ID());
			pstmt.setString(2, "N");
			rs = pstmt.executeQuery ();
			while (rs.next ()){
				list.add (new X_T_Replenish (getCtx(), rs, get_TrxName()));
			}
		}
		catch (final Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}

		final X_T_Replenish[] retValue = new X_T_Replenish[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getReplenish

}	//	Replenish
