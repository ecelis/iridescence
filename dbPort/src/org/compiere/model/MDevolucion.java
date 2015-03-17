package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
/**
 * 
 * @author Lizeth de la Garza
 * @deprecated
 */
public class MDevolucion extends X_SM_Devolucion {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger		log = CLogger.getCLogger (MDevolucion.class);
    
    public MDevolucion(Properties ctx, int SM_Devolucion_ID, String trxName) {
        super(ctx, SM_Devolucion_ID, trxName);
     
    }
    public MDevolucion(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
     
    
    /**
	 * 
	 * @param ctx
	 * @param line
	 * @param trxName
	 * @return
	 */
	public static String devAlmacenVirtual(Properties ctx,
			MMovementLine line, String trxName) {
		
		String retValue = null;

		boolean esVirtual = false; 
		//LRHU. revisar si el objeto no viene null, se hace esta validacion ya que en solicitud de productos a consigna marcaba nullpointer exception. 
		if(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null) != null)
			esVirtual = MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).isVirtual();
		
		if (esVirtual){
			try {
				//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
				MDevolucion devolucion = new MDevolucion(ctx, 0, trxName);
				devolucion.setSM_Devolucion_ID(line.getM_MovementLine_ID());
				// FIXME: deberia solo usar el constructor
				devolucion.setDocumentNo(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo());
				MEXMEConfigInt configInt = MEXMEConfigInt.get(ctx, null,null);		
				
				if (configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(MEXMEConfigInt.INTERFASE_INVENTARIOS_SIA)) {
					devolucion.setWHS_Dev_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getDescription());
					devolucion.setWHS_Rec_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getDescription());
					devolucion.setProducto_Value(MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				} else {
					devolucion.setWHS_Dev_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), trxName).getValue());
					devolucion.setWHS_Rec_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), trxName).getValue());
					devolucion.setProducto_Value(String.valueOf(line.getM_Product_ID()));
				}
				devolucion.setCantidad(line.getOriginalQty());
				devolucion.setM_Movement_ID(line.getM_Movement_ID());
				devolucion.setM_MovementLine_ID(line.getM_MovementLine_ID());
				devolucion.setM_Product_ID(line.getM_Product_ID());
				devolucion.setUOM_Value(new MUOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(),trxName).getName());
				devolucion.setTransferido(false);
				devolucion.setAD_Client_ID(line.getAD_Client_ID());
				devolucion.setAD_Org_ID(line.getAD_Org_ID());
				MAttributeSetInstance asi = new MAttributeSetInstance(ctx, line.getM_AttributeSetInstance_ID(), null);
				devolucion.setLot(asi.getLot());
				devolucion.setGuaranteeDate(asi.getGuaranteeDate());
				asi = null;
				
				if (!devolucion.save(trxName)) {
					retValue = "Error";
					log.log(Level.SEVERE, "devAlmacenVirtual()");
				}
				
			
				
			} catch (SQLException e) {
				if (WebEnv.DEBUG)
					e.printStackTrace();
				log.log(Level.SEVERE, "devAlmacenVirtual()");
				retValue = "Error";
			}
		}
		return retValue;
		
	}
}
