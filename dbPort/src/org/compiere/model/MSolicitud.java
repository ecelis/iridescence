package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;

/**
 * 
 * @author Lizeth de la Garza
 * @deprecated
 */
public class MSolicitud extends X_SM_Solicitud {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger		log = CLogger.getCLogger (MSolicitud.class);
    
    public MSolicitud(Properties ctx, int SM_Solicitud_ID, String trxName) {
        super(ctx, SM_Solicitud_ID, trxName);
     
    }
    public MSolicitud(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    
    /**
	 * 
	 * @param ctx
	 * @param line
	 * @param trxName
	 * @return
	 */
	public static String solAlmacenVirtual(Properties ctx,
			MMovementLine line, String trxName) {

		String retValue = null;
		
		boolean esVirtual = false; 
		//LRHU. revisar si el objeto no viene null, se hace esta validacion ya que en solicitud de productos a consigna marcaba nullpointer exception.
		if(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null) != null)
			esVirtual = MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).isVirtual();
		
		if (esVirtual) {
			
			try {
				//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
				MSolicitud solicitud = new MSolicitud(ctx, 0, trxName);
				solicitud.setSM_Solicitud_ID(line.getM_MovementLine_ID());
				// FIXME: va dos veces a la BD por el mismo objeto, deberia usar solo el constructor
				solicitud.setDocumentNo(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getDocumentNo());
				solicitud.setMovementDate(MMovement.get(ctx, line.getM_Movement_ID(), trxName).getMovementDate() );
				MEXMEConfigInt configInt = MEXMEConfigInt.get(ctx, null,null);
				
				if (configInt != null && configInt.getInterfase_Inventarios().equalsIgnoreCase(MEXMEConfigInt.INTERFASE_INVENTARIOS_SIA)) {
					solicitud.setWHS_Sol_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getDescription());
					solicitud.setWHS_Sur_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getDescription());
					solicitud.setProducto_Value(MProduct.get(ctx, line.getM_Product_ID()).getSKU());
				} else { //Si no tiene interfaz configurada o tiene configurada cualquier otra interfaz diferente de SIA 
					solicitud.setWHS_Sol_Value(MWarehouse.getFromLocator(ctx, line.getM_LocatorTo_ID(), null).getValue());
					solicitud.setWHS_Sur_Value(MWarehouse.getFromLocator(ctx, line.getM_Locator_ID(), null).getValue());
					solicitud.setProducto_Value(String.valueOf(line.getM_Product_ID()));
				}
		
				configInt = null;
				solicitud.setCantidad(line.getOriginalQty());
				solicitud.setM_Movement_ID(line.getM_Movement_ID());
				solicitud.setM_MovementLine_ID(line.getM_MovementLine_ID());
				solicitud.setM_Product_ID(line.getM_Product_ID());
				solicitud.setTransferido(false);
				solicitud.setUOM_Value(new MUOM(ctx, MProduct.get(ctx,line.getM_Product_ID()).getC_UOM_ID(), trxName).getName());
				solicitud.setAD_Client_ID(line.getAD_Client_ID());
				solicitud.setAD_Org_ID(line.getAD_Org_ID());
				
				if (!solicitud.save(trxName)) {
					retValue = "Error";
					log.log(Level.SEVERE, "solAlmacenVirtual()");
				}
				
			} catch (SQLException e) {
				log.log(Level.SEVERE, "solAlmacenVirtual()");
				retValue = "Error";
			}
		}
		
		return retValue;
		
	}


}