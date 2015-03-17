/*
 * Created on 02-mar-2005
 *
 */
package org.compiere.model;


/**
 * @author hassan reyes
 * @deprecated
 */
public class MEXMEInOutLine { //extends MInOutLine {
    
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 4066030737705152114L;
//	/**
//     * Referencia de la linea del Movimiento que origino el registro.
//     * Nota: No se guarda en BD.
//     */
//    private MMovementLine m_movementLine = null;
//
//    /**
//     * @param ctx
//     * @param M_InOutLine_ID
//     * @param trxName
//     */
//    public MEXMEInOutLine(Properties ctx, int M_InOutLine_ID, String trxName) {
//        super(ctx, M_InOutLine_ID, trxName);
//    }
//
//    /**
//     * @param ctx
//     * @param rs
//     */
//    public MEXMEInOutLine(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//
//    /**
//     * @param inout
//     */
//    public MEXMEInOutLine(MInOut inout) {
//        super(inout);
//    }

    /**
     * Si la linea proviene de un SOTrx no esta obligada a tener relacion con una orden de venta C_OrderLine. 
     * Basado en Version: $Id: MEXMEInOutLine.java,v 1.7 2006/03/28 15:47:13 taniap Exp $
     *
    protected boolean beforeSave(boolean newRecord) {
        log.fine("beforeSave");
		//	Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM M_InOutLine WHERE M_InOut_ID=?";
			sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "M_InOutLine");
			int ii = DB.getSQLValue (get_TrxName(), sql, getM_InOut_ID());
			setLine (ii);
		}
		//	UOM/**
//     * Precios en inout deacuerdo al cargo de la cuenta paciente
//     * cuando exista un producto, Considera las unidades de medida
//     * @param mCtaPacDet
//     */
//	public void setPrices(MCtaPacDet mCtaPacDet) {
//		if(mCtaPacDet==null || mCtaPacDet.getProduct()==null){
//			return;
//		}
//		
//		// Misma unidad de medida
//		if(mCtaPacDet.getProduct().getC_UOM_ID() == mCtaPacDet.getProduct().getC_UOMVolume_ID()){
//			setPriceActual(mCtaPacDet.getPriceActual());
//			setPriceActual_Vol(mCtaPacDet.getPriceActual());
//		} else {
//			// minima
//			if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOM_ID()){
//				setPriceActual(mCtaPacDet.getPriceActual());
//				setPriceActual_Vol(Env.ZERO);
//			} 
//			// Volumen
//			if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOMVolume_ID()){
//				setPriceActual(Env.ZERO);
//				setPriceActual_Vol(mCtaPacDet.getPriceActual());
//			}
//		}
//	}
//		if (getC_UOM_ID() == 0)
//			setC_UOM_ID (Env.getContextAsInt(getCtx(), "#C_UOM_ID"));
//		if (getC_UOM_ID() == 0)
//		{
//			int C_UOM_ID = MUOM.getDefault_UOM_ID(getCtx());
//			if (C_UOM_ID > 0)
//				setC_UOM_ID (C_UOM_ID);
//		}
		
		/*	Order Line
		if (getC_OrderLine_ID() == 0)
		{
			MInOut inout = new MInOut (getCtx(), getM_InOut_ID(), get_TrxName());
			if (inout.isSOTrx())
			{
				log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
				return false;
			}
		}
		*
		return true;
    }*/
    
//    /**
//	 * 	Set Indicaci�n Line.
//	 * 	Does not set Quantity!
//	 *	@param oLine order line
//	 */
//	public void setOrderLine (MEXMEActPacienteInd aLine, int M_Locator_ID)
//	{
//		//setC_OrderLine_ID();
//		setLine(aLine.getLine());
//		setC_UOM_ID(aLine.getC_UOM_ID());
//		MProduct product = aLine.getProduct();
//		if (product == null)
//		{
//			set_ValueNoCheck("M_Product_ID", null);
//			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
//			set_ValueNoCheck("M_Locator_ID", null);
//		}
//		else
//		{
//			setM_Product_ID(aLine.getM_Product_ID());
//			setM_AttributeSetInstance_ID(0);
//			//
//			if (product.isItem())
//			{
//				if (M_Locator_ID == 0)
//					setM_Locator_ID(Env.ZERO);	//	requires warehouse, product, asi
//				else
//					setM_Locator_ID(M_Locator_ID);
//			}
//			else
//				set_ValueNoCheck("M_Locator_ID", null);
//		}
//		setDescription(aLine.getDescription());
//		setIsDescription(aLine.isDescription());
//	}	//	setOrderLine
	
	/**
	 * 	Set Indicaci�n Line.
	 * 	Does not set Quantity!
	 *	@param oLine order line
	 *
	public void setOrderLine (MEXMEActPacienteInd cLine, int M_Locator_ID, BigDecimal Qty)
	{
		//setC_OrderLine_ID();
		setEXME_ActPacienteInd_ID(cLine.getEXME_ActPacienteInd_ID());
		setLine(cLine.getLine());
		setC_UOM_ID(cLine.getC_UOM_ID());
		MProduct product = cLine.getProduct();
		if (product == null)
		{
			set_ValueNoCheck("M_Product_ID", null);
			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
			set_ValueNoCheck("M_Locator_ID", null);
		}
		else
		{
			setM_Product_ID(cLine.getM_Product_ID());
			setM_AttributeSetInstance_ID(0);
			//
			if (product.isItem())
			{
				if (M_Locator_ID == 0)
					setM_Locator_ID(Qty);	//	requires warehouse, product, asi
				else
					setM_Locator_ID(M_Locator_ID);
			}
			else
				set_ValueNoCheck("M_Locator_ID", null);
		}
		setC_Charge_ID(cLine.getC_Charge_ID());
		setDescription(cLine.getDescription());
		setIsDescription(cLine.isDescription());
		//
		/*setC_Project_ID(cLine.getC_Project_ID());
		setC_ProjectPhase_ID(cLine.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(cLine.getC_ProjectTask_ID());
		setC_Activity_ID(cLine.getC_Activity_ID());
		setC_Campaign_ID(cLine.getC_Campaign_ID());*/
	/*	setAD_OrgTrx_ID(cLine.getAD_OrgTrx_ID());
		/*setUser1_ID(cLine.getUser1_ID());
		setUser2_ID(cLine.getUser2_ID());*/
		
//	}	//	setOrderLine
	
//	/**
//	 * 	Set Indicaci�n Line.
//	 * 	Does not set Quantity!
//	 *	@param oLine order line
//	 */
//	public void setOrderLine (MCtaPacDet cLine, int M_Locator_ID, BigDecimal Qty)
//	{
//		//setC_OrderLine_ID();
//		setEXME_CtaPacDet_ID(cLine.getEXME_CtaPacDet_ID());
//		setLine(cLine.getLine());
//		setC_UOM_ID(cLine.getC_UOM_ID());
//		MProduct product = cLine.getProduct();
//		if (product == null)
//		{
//			set_ValueNoCheck("M_Product_ID", null);
//			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
//			set_ValueNoCheck("M_Locator_ID", null);
//		}
//		else
//		{
//			setM_Product_ID(cLine.getM_Product_ID());
//			setM_AttributeSetInstance_ID(0);
//			//
//			if (product.isItem())
//			{
//				if (M_Locator_ID == 0)
//					setM_Locator_ID(Qty);	//	requires warehouse, product, asi
//				else
//					setM_Locator_ID(M_Locator_ID);
//			}
//			else
//				set_ValueNoCheck("M_Locator_ID", null);
//		}
//		setC_Charge_ID(cLine.getC_Charge_ID());
//		setDescription(cLine.getDescription());
//		setIsDescription(cLine.isDescription());
//		//
//		/*setC_Project_ID(cLine.getC_Project_ID());
//		setC_ProjectPhase_ID(cLine.getC_ProjectPhase_ID());
//		setC_ProjectTask_ID(cLine.getC_ProjectTask_ID());
//		setC_Activity_ID(cLine.getC_Activity_ID());
//		setC_Campaign_ID(cLine.getC_Campaign_ID());*/
//		setAD_OrgTrx_ID(cLine.getAD_OrgTrx_ID());//La configurada en el detalle del cargo diario
//		/*setUser1_ID(cLine.getUser1_ID());
//		setUser2_ID(cLine.getUser2_ID());*/
//		
//	}	//	setOrderLine
//	/**
//	 * 	Set Movimiento Line.
//	 * 	Does not set Quantity!
//	 *	@param oLine order line
//	 */
//	public void setOrderLine (MMovementLine mLine, int M_Locator_ID)
//	{
//		//setC_OrderLine_ID();
//		setLine(mLine.getLine());
//		MProduct product = new MProduct(getCtx(), mLine.getM_Product_ID(), get_TrxName());
//		// La UOM se toma la del producto. � Los movimeintos entre almacenes solo son en UOM de almacenamiento!.
//		setC_UOM_ID(product.getC_UOM_ID());
//		
////		if (product == null) //dead code
////		{
////			set_ValueNoCheck("M_Product_ID", null);
////			set_ValueNoCheck("M_AttributeSetInstance_ID", null);
////			set_ValueNoCheck("M_Locator_ID", null);
////		}
////		else
////		{
//			setM_Product_ID(mLine.getM_Product_ID());
//			setM_AttributeSetInstance_ID(0);
//			//
//			if (product.isItem())
//			{
//				if (M_Locator_ID == 0)
//					setM_Locator_ID(Env.ZERO);	//	requires warehouse, product, asi
//				else
//					setM_Locator_ID(M_Locator_ID);
//			}
//			else
//				set_ValueNoCheck("M_Locator_ID", null);
////		}
//		setDescription(mLine.getDescription());
//		setIsDescription(false);
//	}	//	setOrderLine
//	
//	
//    public MMovementLine getMovementLine() {
//        return m_movementLine;
//    }
//    public void setMovementLine(MMovementLine line) {
//        m_movementLine = line;
//    }
//    
//    public MLocator getLocator(){
//        return new MLocator(getCtx(), getM_Locator_ID(), get_TrxName());
//    }

//    /**
//     * Precios en inout deacuerdo al cargo de la cuenta paciente
//     * cuando exista un producto, Considera las unidades de medida
//     * @param mCtaPacDet
//     */
//	public void setPrices(MCtaPacDet mCtaPacDet) {
//		if(mCtaPacDet==null || mCtaPacDet.getProduct()==null){
//			return;
//		}
//		
//		// Misma unidad de medida
//		if(mCtaPacDet.getProduct().getC_UOM_ID() == mCtaPacDet.getProduct().getC_UOMVolume_ID()){
//			setPriceActual(mCtaPacDet.getPriceActual());
//			setPriceActual_Vol(mCtaPacDet.getPriceActual());
//		} else {
//			// minima
//			if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOM_ID()){
//				setPriceActual(mCtaPacDet.getPriceActual());
//				setPriceActual_Vol(Env.ZERO);
//			} 
//			// Volumen
//			if(getC_UOM_ID()==mCtaPacDet.getProduct().getC_UOMVolume_ID()){
//				setPriceActual(Env.ZERO);
//				setPriceActual_Vol(mCtaPacDet.getPriceActual());
//			}
//		}
//	}
    
   
}
