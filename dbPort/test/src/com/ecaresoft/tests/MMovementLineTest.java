/**
 * 
 */
package com.ecaresoft.tests;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.PharmacistModel;
import org.compiere.model.X_M_MovementLine;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.TransferRequirement;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

import junit.framework.TestCase;

/**
 * @author mvrodriguez
 *
 */
public class MMovementLineTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		TestUtils.setUpLake();
	}

	/**
	 * Test method for {@link org.compiere.model.MMovementLine#createMovementLine(org.compiere.model.MEXMEPrescRXDet)}.
	 */
	@Test
	public void testCreateMovementLine() {
		// 
		final PharmacistModel mPharmacistModel = PharmacistModel.getTabMeds(Env.getCtx(), true, false, false, false);
		assertNotNull(mPharmacistModel);

		// 
		final List<MEXMEPrescRXDet> list = mPharmacistModel.getQuery().list();
		assertNotNull(list);
		assertFalse(list.isEmpty());

		//
		for (int i = 0; i < list.size(); i++) {
			final List<MMovementLine> lstMLine = createMovementLine(list.get(i));
			assertNotNull(lstMLine);
//			assertFalse(lstMLine.isEmpty());// PUEDE ESTAR VACIA SI NO HAY EXISTENCIAS
		}
	}
	
	/**
	 * Crear lineas a partir de la prescripción
	 * 
	 * @param presc
	 * @return
	 * @throws SQLException
	 */
	public static List<MMovementLine> createMovementLine(final MEXMEPrescRXDet presc) {

		// Listado del pedido de todos NDC solicitados
		List<MMovementLine> lst = new ArrayList<MMovementLine>();

		// Se selecciono distribucion?
		if (presc.getDistribucion() == null || presc.getDistribucion().isEmpty()) {
			lst = lstNDCs(presc);
		} else {
			for (BeanView dist : presc.getDistribucion()) {
				if (dist.getDcimal().compareTo(Env.ZERO) > 0) {
					final MProduct prod = new MProduct(presc.getCtx(), dist.getInteger1(), presc.get_TrxName());

					final MMovementLine line = addProduct(prod, prod.getC_UOM_ID(), null, dist.getDcimal());
					// Cantidad que tecleeo el usuario
					line.completeLine(dist.getDcimal(), true);
					line.setM_AttributeSetInstance_ID(dist.getInteger2());
					lst.add(line);
				}
			}
		}
		return lst;
	}
	
	
	/**
	 * Lineas de NDC que tienen existencias para surtir una prescripcion
	 * @param presc
	 * @return
	 */
	public static List<MMovementLine> lstNDCs(final MEXMEPrescRXDet presc){
		// Listado del pedido de todos NDC solicitados
		final List<MMovementLine> lst = new ArrayList<MMovementLine>();

		// Almacen de productclass-service/ en teoria solo surte
		// medicamentos
		// (GC:) la razón de esto es porque en un Hospital de USA solamente
		// habrá un solo almacén (farmacia interna) que surta todos los
//		// medicamentos que se requieran
		// Surte
		final int mWarehouseId = TransferRequirement.getWarehouseAssId(Env.getCtx());
		// Existencias
		final List<BeanView> lstExistencias = MStorage
				.getExistenciasGenProduct(
						Env.getCtx(),
						presc.getEXME_GenProduct(),
						mWarehouseId < 1 ? Env.getM_Warehouse_ID(Env
								.getCtx()) :mWarehouseId, true, null);

		if (lstExistencias != null && !lstExistencias.isEmpty()) {
			BigDecimal qtyPendiente = presc.getQuantity();

			// Deacuerdo a prioridades
			for (int i = 0; i < lstExistencias.size(); i++) {

				if (lstExistencias.get(i).getDcimal().compareTo(Env.ZERO) > 0
						&& qtyPendiente.compareTo(Env.ZERO) > 0) {

					// 799 = 800-1 || -799 = 1-800
					final BigDecimal qty = lstExistencias.get(i)
							.getDcimal().subtract(qtyPendiente);

					// Cantidad a cargar
					BigDecimal qtyFinal = Env.ZERO;
					if (qty.compareTo(Env.ZERO) > 0) {// 799 = 800-1
						qtyFinal = qtyPendiente;
						qtyPendiente = Env.ZERO;
					} else {
						qtyFinal = lstExistencias.get(i).getDcimal();
						qtyPendiente = qty.abs();
					}

					// Producto con el NDC y la cantidad
					final MProduct prod = new MProduct(presc.getCtx(),
							lstExistencias.get(i).getInteger1(),
							presc.get_TrxName());
					final MMovementLine line = addProduct(
							prod, prod.getC_UOM_ID(), null, qtyFinal);
					line.completeLine(qtyFinal, true);
					line.setM_AttributeSetInstance_ID(lstExistencias.get(i)
							.getInteger2());

					lst.add(line);

					if (qtyPendiente.compareTo(Env.ZERO) <= 0) {
						break;
					}
				}// Cantidad
			}// for
		}
		return lst;
	}
	

	/**
	 * Agrega un producto al listado de detalle
	 */
	public static MMovementLine addProduct(final MProduct prod,
			final int uomSel, final String descriptionDetail,
			final BigDecimal quantity) { // MovementLine line = new
											// MovementLine();
		final MMovementLine line = new MMovementLine(Env.getCtx(), 0, null);
		line.setM_Product_ID(prod.getM_Product_ID());
		line.setOp_Uom(uomSel);
		line.setDescription(descriptionDetail);
		line.setC_UOMVolume_ID(prod.getC_UOMVolume_ID());
		line.setC_UOM_ID(prod.getC_UOM_ID());
		line.setNew(true);
		line.setQuantityUser(quantity);
		// se establecen las demas cantidades en cero pq no se trata de una
		// devolucion
		line.setTargetQty_Vol(Env.ZERO);
		line.setMovementQty_Vol(Env.ZERO);
		line.setTargetQty(Env.ZERO);
		line.setMovementQty(Env.ZERO);

		return line;
	}
	
	/** Validar que las cantidades se muevan correctamente */
	public static void validateLine(MMovementLine newLine, MMovementLine oldLine){
		if (WebEnv.DEBUG){
			System.out.println("Return Line");
			//
			System.out.println("ORIGINAL LINE: ");
			printLine(oldLine);
			System.out.println("NEW LINE: ");
			printLine(newLine);
			//
			System.out.println("Stock Line");
			System.out.println("ORIGINAL LINE: ");
			printStock(oldLine);
			System.out.println("NEW LINE: ");
			printStock(newLine);

			//
			if(oldLine.getM_Locator_ID()!=newLine.getM_LocatorTo_ID()){
				System.out.println("No coinciden localizadores");
			}

			if(oldLine.getReturnQty().compareTo(newLine.getTargetQty())==0
					|| oldLine.getReturnQty().compareTo(newLine.getOriginalQty())==0
					|| oldLine.getReturnQty().compareTo(newLine.getMovementQty())==0){
				System.out.println("La cantidad minima no coindide");
			}

			if(oldLine.getReturnQty_Vol().compareTo(newLine.getTargetQty_Vol())==0
					|| oldLine.getReturnQty_Vol().compareTo(newLine.getOriginalQty_Vol())==0
					|| oldLine.getReturnQty_Vol().compareTo(newLine.getMovementQty_Vol())==0){
				System.out.println("La cantidad volumen no coindide");
			}

			if(Env.ZERO.compareTo(newLine.getConfirmedQty())==0
					|| Env.ZERO.compareTo(newLine.getScrappedQty())==0){
				System.out.println("La cantidad minima no es cero");
			}

			if(Env.ZERO.compareTo(newLine.getConfirmedQty_Vol())==0
					|| Env.ZERO.compareTo(newLine.getScrappedQty_Vol())==0){
				System.out.println("La cantidad volumen no es cero");
			}
		}
	}


	public static void printStock(MMovementLine oldLine){	
		if (WebEnv.DEBUG){
			System.out.println("Existencias M_Locator_ID: ");
			List<MStorage> lst = MStorage.getStorage(oldLine.getCtx()
					, oldLine.getM_Product_ID()
					, oldLine.getM_Locator_ID()
					, oldLine.getM_AttributeSetInstance_ID(), oldLine.get_TrxName());
			for (final MStorage mStorage:lst) {
				System.out.println("Storage: "+mStorage.toString());
			}

			System.out.println("Existencias M_LocatorTo_ID: ");
			lst = MStorage.getStorage(oldLine.getCtx()
					, oldLine.getM_Product_ID()
					,oldLine. getM_LocatorTo_ID()
					, oldLine.getM_AttributeSetInstance_ID(), oldLine.get_TrxName());
			for (final MStorage mStorage:lst) {
				System.out.println("Storage: "+mStorage.toString());
			}
		}
	}
	
	
	public static String printLine(MMovementLine line){
		final StringBuilder msg = new StringBuilder(1000);
		if (WebEnv.DEBUG){
			msg.append(X_M_MovementLine.COLUMNNAME_M_Movement_ID)      .append(": ").append(line.getM_Movement_ID()       ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_M_MovementLine_ID)  .append(": ").append(line.getM_MovementLine_ID()   ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_Ref_MovementLine_ID).append(": ").append(line.getRef_MovementLine_ID() ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_M_Locator_ID)       .append(": ").append(line.getM_Locator_ID()        ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_M_LocatorTo_ID)     .append(": ").append(line.getM_LocatorTo_ID()      ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_M_AttributeSetInstance_ID).append(": ").append(line.getM_AttributeSetInstance_ID()).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_Op_Uom)      .append(": ").append(line.getOp_Uom()       ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_M_Product_ID).append(": ").append(line.getM_Product_ID() ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_C_UOM_ID)    .append(": ").append(line.getC_UOM_ID()     ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_OriginalQty) .append(": ").append(line.getOriginalQty()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_MovementQty) .append(": ").append(line.getMovementQty()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_TargetQty)   .append(": ").append(line.getTargetQty()    ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ConfirmedQty).append(": ").append(line.getConfirmedQty() ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ScrappedQty) .append(": ").append(line.getScrappedQty()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ReturnQty)   .append(": ").append(line.getReturnQty()    ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_C_UOMVolume_ID)  .append(": ").append(line.getC_UOM_ID() ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_OriginalQty_Vol) .append(": ").append(line.getOriginalQty_Vol()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_MovementQty_Vol) .append(": ").append(line.getMovementQty_Vol()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_TargetQty_Vol)   .append(": ").append(line.getTargetQty_Vol()    ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ConfirmedQty_Vol).append(": ").append(line.getConfirmedQty_Vol() ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ScrappedQty_Vol) .append(": ").append(line.getScrappedQty_Vol()  ).append(Constantes.NEWLINE);
			msg.append(X_M_MovementLine.COLUMNNAME_ReturnQty_Vol)   .append(": ").append(line.getReturnQty_Vol()    ).append(Constantes.NEWLINE);
			System.out.println("LINE = " + msg.toString());
		}
		return msg.toString();
	}

}
