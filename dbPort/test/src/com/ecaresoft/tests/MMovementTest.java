/**
 * 
 */
package com.ecaresoft.tests;


import static org.compiere.util.Env.getCtx;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.bean.MovementLine;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.junit.Before;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 *
 */
public class MMovementTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//TestUtils.setUpLake();
		TestUtils.setMonclova200("ecsmxVer3R2");
	}

//	/**
//	 * Test method for {@link org.compiere.model.MMovement#inStock(java.util.Properties, int, int, int, java.math.BigDecimal)}.
//	 */
//	@Test
//	public final void testInStock() {
////		int productId = 1474335;// cambiar de cuenta segun sea el caso
////		BigDecimal qty = Env.ONE; // udm almacenamiento
////		assertTrue(MMovement.inStock(Env.getCtx(), Env.getM_Warehouse_ID(Env.getCtx()),
////				productId, 0, qty));
//	}


	/**
	 * Test method for {@link org.compiere.model.MMovement#returnProd(Properties, MMovement, List, String)}.
	 */
	@Test
	public final void testDevolutionQuirofano() {
		
//		final MMovement oldMovement = TestUtils.getRandomPO(MMovement.class
//				, " EXME_CtaPac_ID>0 AND DocStatus = 'CO' AND IsDevol = 'N' AND M_Warehouse_ID IS NOT NULL "
//				+ " AND EXME_ProgQuiro_ID IS NOT NULL "
//				, true);
//		
//		if(oldMovement!=null){
//			final ErrorList errorList = MMovement.returnProd(Env.getCtx()
//					, oldMovement
//					, MMovement.getLines(Env.getCtx(), oldMovement.getM_Movement_ID(), null)
//					, null);
//
//			assertTrue(errorList.isEmpty());
//		}
	}
	
	/**
	 * Test method for {@link org.compiere.model.MMovement#returnProd(Properties, MMovement, List, String)}.
	 */
	@Test
	public final void testDevolutionProducto() {
		
//		final MMovement oldMovement = TestUtils.getRandomPO(MMovement.class
//				, " EXME_CtaPac_ID>0 AND DocStatus = 'CO' AND IsDevol = 'N' AND M_Warehouse_ID IS NOT NULL "
//				+ " AND EXME_ProgQuiro_ID IS NULL "
//				, true);
//		
//		if(oldMovement!=null){
//			final ErrorList errorList = MMovement.returnProd(Env.getCtx()
//					, oldMovement
//					, MMovement.getLines(Env.getCtx(), oldMovement.getM_Movement_ID(), null)
//					, null);
//
//			assertTrue(errorList.isEmpty());
//		}
	}
	
	
	protected static void validateMovement(MMovement mMovement){
//		if (WebEnv.DEBUG){
//			MMovement mMovementTest = new Query( 
//					mMovement.getCtx()
//					, X_M_Movement.Table_Name
//					, " DocStatus IN ('CO') AND EXME_ctaPac_Id > 0 AND isDevol = 'Y' "
//					, mMovement.get_TrxName())//
//					.addAccessLevelSQL(true)//
//					.first();
//			
//			if(mMovementTest!=null){
//				List<MMovementLine> list = mMovementTest.getLines();
//				for (MMovementLine line: list) {
//					MMovementLineTest.printLine(line);
//					break;
//				}
//
//				list =  mMovement.getLines();
//				for (MMovementLine line: list) {
//					MMovementLineTest.printLine(line);
//					break;
//				}
//			}
//		}
	}
	
	
	/**
	 * Test method for {@link org.compiere.model.MMovement#returnProd(Properties, MMovement, List, String)}.
	 */
	@Test
	public final void testReturnProductLotConsigna() {
		boolean manejarLote = true;
		boolean success = true;
		ErrorList errorList  = new ErrorList();

		try {
			
			// CUENTA PACIENTE <EXISTENTE>
			final MEXMECtaPac mCtaPac = TestUtils.getRandomPO(MEXMECtaPac.class
					, "  FechaCierre is null "
					, true);
			if(mCtaPac==null) return;
			System.out.println("Cuenta: "+mCtaPac.toString());

			// ALMACENES CONFIGURADOS  <EXISTENTE>
			final MWarehouse mWarehouseToHospi = TestUtils.getRandomPO(MWarehouse.class
					, "  upper(Name) like '%HOSPI%' AND ControlExistencias = 'Y' AND ( Type like 'W%' OR Type = 'O' )  "
					, true);
			if(mWarehouseToHospi==null) return;
			System.out.println("mWarehouseToHospi: "+mWarehouseToHospi.toString());

			// DEBE TENER ALMACENES DE CONSIGNA RELACIONADO NO LO VALIDA EL CASO DE PRUEBA  <EXISTENTE>
			final MWarehouse mWarehouseGeneral = TestUtils.getRandomPO(MWarehouse.class
					, "  upper(Name) like '%ALMACEN GENERAL%'  AND ControlExistencias = 'Y' AND ( Type like 'W%' OR Type = 'O' )  "
					, true);
			if(mWarehouseGeneral==null) return;
			System.out.println("mWarehouseGeneral: "+mWarehouseGeneral.toString());

			
			// PRODUCTOS  <EXISTENTE>
			final List<MProduct> lstProd = getLines("N"
					, null
					, mWarehouseGeneral.getM_Warehouse_ID());
			if(lstProd==null || lstProd.isEmpty())  return;

			final List<MProduct> lstProdConsignaV = getLines("Y"
					," AND alm.Consigna = 'Y' AND alm.Virtual = 'Y' "
					, 0);
			if(lstProdConsignaV==null || lstProdConsignaV.isEmpty())  return;

			final List<MProduct> lstProdConsignaF = getLines("Y"
					," AND alm.Consigna = 'Y' AND alm.Virtual = 'N' "
					, 0);
			if(lstProdConsignaF==null || lstProdConsignaF.isEmpty())  return;

			// Buscar el numero de iteraciones posibles
			int menosReg = lstProdConsignaV.size();
			if(menosReg  > lstProdConsignaF.size()){
				menosReg = lstProdConsignaF.size();
			}
			if(menosReg > lstProd.size()){
				menosReg = lstProd.size();
			}
			int coun = menosReg>100?100:menosReg;
			
			
			// Solicitud
			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("test"), true);

				// create header
				MMovement mMovementInicial = new MMovement(Env.getCtx(), 0, null);
				mMovementInicial.setDescription("Test");
				mMovementInicial.setEXME_CtaPac_ID(mCtaPac.getEXME_CtaPac_ID());
				mMovementInicial.setM_Warehouse_ID(mWarehouseGeneral.getM_Warehouse_ID());
				mMovementInicial.setM_WarehouseTo_ID(mWarehouseToHospi.getM_Warehouse_ID());
				if(!mMovementInicial.save(trx.getTrxName()))  return;
				System.out.println("mMovementInicial: "+mMovementInicial.toString());

				for (int i = 0; i < coun; i++) {
					if(success){
						System.out.println("       ");
						/********************** PRIMER PRODUCTO *******/
						System.out.println("*** PRIMER PRODUCTO ***************************************");
						final MProduct prod = lstProd.get(i);
						if(prod==null) return;
						System.out.println("prod: "+prod.toString());
						cleanExiste(Env.getCtx(), prod.getM_Product_ID(), null);
								
						MLocator mLocatorGral = TestUtils.getRandomPO(MLocator.class
								, "  M_Warehouse_ID = "+mWarehouseGeneral.getM_Warehouse_ID()
								, true);
						if(mLocatorGral==null) return;
						System.out.println("mLocator: "+mLocatorGral.toString());

						MLocator mLocatorToHospi = TestUtils.getRandomPO(MLocator.class
								, "  M_Warehouse_ID = "+mWarehouseToHospi.getM_Warehouse_ID()
								, true);
						if(mLocatorToHospi==null) return;
						System.out.println("mLocatorTo: "+mLocatorToHospi.toString());

						// Crear lote
						int mAsi = manejarLote?MAttributeSetInstance.getAttributeSetInstance(Env.getCtx(), String.valueOf(prod.getM_Product_ID()), prod.getM_Product_ID(), true, null)
								:0;
						if(manejarLote && mAsi<0){
							final MAttributeSetInstance asi = new MAttributeSetInstance(Env.getCtx(), 0, null);
							asi.setM_Product_ID(prod.getM_Product_ID());
							asi.setLot(String.valueOf(prod.getM_Product_ID()));
							success = asi.save(trx.getTrxName());
							if(!success) return;
							mAsi = asi.getM_AttributeSetInstance_ID();
							System.out.println("mAsi 1 : "+asi.toString());
						}

						// Agregar existencias al almacen qye surte
						MStorage storage = MStorage.getCreate(Env.getCtx()
								, mLocatorGral.getM_Locator_ID()
								, prod.getM_Product_ID()
								, mAsi
								, trx.getTrxName());		
						if(storage==null) return;
						storage.setQtyOnHand(new BigDecimal(10000));
						success = storage.save(trx.getTrxName());
						if(!success) return;
						System.out.println("storage 1 : "+storage.toString());

						final MMovementLine line = new MMovementLine(Env.getCtx(), 0, trx.getTrxName());
						errorList = saveLine(mMovementInicial.getM_Movement_ID()
								, null
								, prod.getM_Product_ID()
								, prod.getC_UOMVolume_ID()
								, new BigDecimal(10)
						, "Test Prod"+i
						, mLocatorGral.getM_Locator_ID()
						, mLocatorToHospi.getM_Locator_ID()
						, trx.getTrxName()
						, line);
						success = errorList.isEmpty();
						if(!success) return;
						line.setM_AttributeSetInstance_ID(mAsi);
						line.setM_Movement_ID(mMovementInicial.getM_Movement_ID());
						line.setM_Locator_ID(mLocatorGral.getM_Locator_ID());
						line.setM_LocatorTo_ID(mLocatorToHospi.getM_Locator_ID());
						success = line.save(trx.getTrxName());
						if(!success) return;
						System.out.println("line 1 : "+line.toString());
						MMovementLineTest.printStock(line);
						System.out.println("       ");
						
						/********************** SEGUNDO PRODUCTO *******/
						System.out.println("*** SEGUNDO PRODUCTO ***************************************");
						final MProduct prodCV = lstProdConsignaV.get(i);
						if(prodCV==null) return;
						System.out.println("prodCV 2 : "+prodCV.toString());
						cleanExiste(Env.getCtx(), prodCV.getM_Product_ID(), null);

//						MLocator mLocator = TestUtils.getRandomPO(MLocator.class
//								, "  M_Warehouse_ID = "+mWarehouseGeneral.getM_Warehouse_ID()
//								, true);
//						if(mLocator==null) return;
//						System.out.println("mLocator 2 : "+mLocator.toString());
//
//						MLocator mLocatorTo = TestUtils.getRandomPO(MLocator.class
//								, "  M_Warehouse_ID = "+mWarehouseToHospi.getM_Warehouse_ID()
//								, true);
//						if(mLocatorTo==null) return;
//						System.out.println("mLocatorTo 2 : "+mLocatorTo.toString());

						// Crear lote
						mAsi = manejarLote?MAttributeSetInstance.getAttributeSetInstance(Env.getCtx()
								, String.valueOf(prodCV.getM_Product_ID())
								, prodCV.getM_Product_ID(), true, null)
								:0;
						if(manejarLote && mAsi<0){
							MAttributeSetInstance asi = new MAttributeSetInstance(Env.getCtx(), 0, null);
							asi.setM_Product_ID(prodCV.getM_Product_ID());
							asi.setLot(String.valueOf(prodCV.getM_Product_ID()));
							success = asi.save(trx.getTrxName());
							if(!success) return;
							mAsi = asi.getM_AttributeSetInstance_ID();
							System.out.println("mAsi 2 : "+asi.toString());
						}

						// Agregar existencias
						// Casilla 1 = Id del almacen o -1 si no hay, Casilla 2 = Localizador relacionado o -1
						int[] alms  = MWarehouse.getWarehouseRel(Env.getCtx(),mWarehouseGeneral.getM_Warehouse_ID(), prodCV.getM_Product_ID(),trx.getTrxName());
						storage = MStorage.getCreate(Env.getCtx()
								, alms[1] //mLocator.getM_Locator_ID() 10001606
										, prodCV.getM_Product_ID()
										, mAsi
										, trx.getTrxName());		
						if(storage==null) return;
						storage.setQtyOnHand(new BigDecimal(10000));
						success = storage.save(trx.getTrxName());
						if(!success) return;
						System.out.println("storage 2 : "+storage.toString());

						
						
						// Crear linea
						MMovementLine line2 = new MMovementLine(Env.getCtx(), 0, trx.getTrxName());
						errorList = saveLine(mMovementInicial.getM_Movement_ID()
								, null
								, prodCV.getM_Product_ID()
								, prodCV.getC_UOMVolume_ID()
								, new BigDecimal(10)
						, "Test Prod"+i
						, mLocatorGral.getM_Locator_ID()
						, mLocatorToHospi.getM_Locator_ID()
						, trx.getTrxName()
						, line2);
						success = errorList.isEmpty();
						if(!success) return;
						line2.setM_AttributeSetInstance_ID(mAsi);
						line2.setM_Movement_ID(mMovementInicial.getM_Movement_ID());
						line2.setM_Locator_ID(mLocatorGral.getM_Locator_ID());
						line2.setM_LocatorTo_ID(mLocatorToHospi.getM_Locator_ID());
						success = line2.save(trx.getTrxName());
						if(!success) return;
						System.out.println("line2 2 : "+line2.toString());
						MMovementLineTest.printStock(line2);
						System.out.println("       ");

						/********************** TERCER PRODUCTO *******/
						System.out.println("*** TERCER PRODUCTO ***************************************");
						final MProduct prodCF = lstProdConsignaF.get(i);
						if(prodCF==null) return;
						System.out.println("prodCF 3 : "+prodCF.toString());
						cleanExiste(Env.getCtx(), prodCF.getM_Product_ID(), null);

//						mLocator = TestUtils.getRandomPO(MLocator.class
//								, "  M_Warehouse_ID = "+mWarehouseGeneral.getM_Warehouse_ID()
//								, true);
//						if(mLocator==null) return;
//						System.out.println("mLocator 3 : "+mLocator.toString());
//
//						mLocatorTo = TestUtils.getRandomPO(MLocator.class
//								, "  M_Warehouse_ID = "+mWarehouseToHospi.getM_Warehouse_ID()
//								, true);
//						if(mLocatorTo==null) return;
//						System.out.println("mLocatorTo 3 : "+mLocatorTo.toString());

						// Crear lote
						mAsi = manejarLote?MAttributeSetInstance.getAttributeSetInstance(Env.getCtx()
								, String.valueOf(prodCF.getM_Product_ID())
								, prodCF.getM_Product_ID(), true, null)
								:0;
						if(manejarLote && mAsi<0){
							MAttributeSetInstance asi = new MAttributeSetInstance(Env.getCtx(), 0, null);
							asi.setM_Product_ID(prodCF.getM_Product_ID());
							asi.setLot(String.valueOf(prodCF.getM_Product_ID()));
							success = asi.save(trx.getTrxName());
							if(!success) return;
							mAsi = asi.getM_AttributeSetInstance_ID();
							System.out.println("asi 3 : "+asi.toString());
						}

						// Agregar existencias
						int[] alms2  = MWarehouse.getWarehouseRel(Env.getCtx(), mWarehouseGeneral.getM_Warehouse_ID(), prodCF.getM_Product_ID(),trx.getTrxName());
						storage = MStorage.getCreate(Env.getCtx()
								, alms2[1]//mLocator.getM_Locator_ID()
										, prodCF.getM_Product_ID()
										, mAsi
										, trx.getTrxName());		
						if(storage==null) return;
						storage.setQtyOnHand(new BigDecimal(10000));
						success = storage.save(trx.getTrxName());
						if(!success) return;
						System.out.println("storage 3 : "+storage.toString());

						// Crear linea
						MMovementLine line3 = new MMovementLine(Env.getCtx(), 0, trx.getTrxName());
						errorList = saveLine(mMovementInicial.getM_Movement_ID()
								, null
								, prodCF.getM_Product_ID()
								, prodCF.getC_UOMVolume_ID()
								, new BigDecimal(10)
						, "Test Prod"+i
						, mLocatorGral.getM_Locator_ID()
						, mLocatorToHospi.getM_Locator_ID()
						, trx.getTrxName()
						, line3);
						success = errorList.isEmpty();
						if(!success) return;
						line3.setM_AttributeSetInstance_ID(mAsi);
						line3.setM_Movement_ID(mMovementInicial.getM_Movement_ID());
						line3.setM_Locator_ID(mLocatorGral.getM_Locator_ID());
						line3.setM_LocatorTo_ID(mLocatorToHospi.getM_Locator_ID());
						success = line3.save(trx.getTrxName());
						if(!success) return;
						System.out.println("line3 3 : "+line3.toString());
						MMovementLineTest.printStock(line3);
						System.out.println("       ");
					}
				} // finForConfiguracion

				// SOLICITAR
				if(success){
					Trx.commit(trx);
					final List<MovementLine> lineas = MMovement.getLines(Env.getCtx(), mMovementInicial.getM_Movement_ID(), trx.getTrxName()); 
					errorList = MMovement.request(Env.getCtx(), mMovementInicial, lineas, trx.getTrxName());
					success = errorList.isEmpty();
				}

				// SURTIR 
				if(success){
					// Surtir virtuales antes de general
					Trx.commit(trx);
					List<MMovement> alsl = getParentMovementConsigna(Env.getCtx(), mMovementInicial.getM_Movement_ID(), trx.getTrxName());
					for (MMovement mov: alsl) {
						if(!mov.getWarehouseCosigna().isVirtual()){

							if(success && errorList.isEmpty()){
								Trx.commit(trx);
								surtir(errorList, mov, trx.getTrxName());
							}
							if(success && errorList.isEmpty()){
								Trx.commit(trx);
								confirmar(errorList, mov, trx.getTrxName());
							}
						}
					}

					if(success && errorList.isEmpty()){
						Trx.commit(trx);
						surtir(errorList, mMovementInicial, trx.getTrxName());
					}
				}

				//CONFIRMA
				if(success && errorList.isEmpty()){
					Trx.commit(trx);
					confirmar(errorList, mMovementInicial, trx.getTrxName());
				}

				if (errorList.isEmpty() && success) {
					Trx.commit(trx);

					// DEVOLVER
					if(success && errorList.isEmpty()){

						final List<MovementLine> lineas = MMovement.getLines(Env.getCtx(), mMovementInicial.getM_Movement_ID(), trx.getTrxName()); 
						for (MovementLine bean: lineas) {
							MMovementLine linePO = new MMovementLine(Env.getCtx(), bean.getId(), trx.getTrxName()); 
							if(bean.isVolumeSelected()){
								bean.setReturnVolQty((bean.getConfirmedVolQty().add(bean.getScrappedVolQty())).subtract(Env.ONE));
								linePO.setReturnQty_Vol((bean.getConfirmedVolQty().add(bean.getScrappedVolQty())).subtract(Env.ONE));
							}
							bean.setReturnQty((bean.getConfirmedQty().add(bean.getScrappedQty())).subtract(Env.ONE));
							linePO.setReturnQty((bean.getConfirmedQty().add(bean.getScrappedQty())).subtract(Env.ONE));
							success = linePO.save();

							final MStorage storageLoc = MStorage.getCreate(Env.getCtx()
									, bean.getLocatorToId()// Inverso
									, bean.getProduct().getM_Product_ID()
									, bean.getLotId()
									, trx.getTrxName());		
							if(storageLoc==null) 
								return;
							storageLoc.setQtyOnHand(bean.getReturnQty());
							success = storageLoc.save(trx.getTrxName());
							if(!success) 
								return;
							MMovementLineTest.printStock(linePO);
							System.out.println("       ");
						}

						if(success){
							Trx.commit(trx);
							mMovementInicial = new MMovement(Env.getCtx(), mMovementInicial.getM_Movement_ID(), trx.getTrxName());
							final List<MovementLine> lstLines = MMovement.getLines(Env.getCtx(), mMovementInicial.getM_Movement_ID(), trx.getTrxName());
							errorList = MMovement.returnProd(Env.getCtx(), mMovementInicial, lstLines, trx.getTrxName());
						}

					}
				}


				if (errorList.isEmpty() && success) {
					Trx.commit(trx);
				} else {
					Trx.rollback(trx);
				}

			} catch (final Exception ex) {
				errorList.add(Error.EXCEPTION_ERROR, Utilerias.getLabel("error.traspasos.noInsertMov"));
				Trx.rollback(trx);
			} finally {
				Trx.rollback(trx, true);
			}

		} finally {
			assertTrue(success && errorList.isEmpty());
		}
	}

	public static List<MProduct> getLines(final String consigna, final String almconsigna, final int M_Warehouse_ID) {
		final List<MProduct> lines = new ArrayList<MProduct>();
		int count = 0;
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT distinct prod.* ")
//		.append(" , stor.QtyOnHand , QtyReserved ")
		.append(" FROM M_Product prod ")
		.append(" INNER JOIN EXME_ProductoOrg org ON  prod.M_Product_ID  = org.M_Product_ID  ")
		.append(" 				AND org.Consigna = '").append(consigna).append("' ")
		.append(" INNER JOIN M_Storage       stor ON  stor.M_Product_ID  = org.M_Product_ID  ")
//		.append(" 				AND stor.QtyOnHand - QtyReserved > ").append(10)
		.append(" 				AND stor.AD_Org_ID = org.AD_Org_ID ")
		.append(" INNER JOIN M_Locator        loc ON  loc.M_Locator_ID   = stor.M_Locator_ID  ")
		.append(" 				AND loc.AD_Org_ID = org.AD_Org_ID ");
		
		
		if(almconsigna!=null && !almconsigna.isEmpty()){
			// Existencias en Consigna
			sql.append(" INNER JOIN M_Warehouse      alm ON  alm.M_Warehouse_ID = loc.M_Warehouse_ID  ")
			.append(" 				AND alm.AD_Org_ID = org.AD_Org_ID ")
			.append(" 				AND alm.ControlExistencias = 'Y' ")
			.append(" 				AND ( alm.Type like 'W%' OR alm.Type = 'O' ) ").append(almconsigna);
			
		} else {
			// Replenish
			sql.append(" INNER JOIN M_Warehouse      alm ON  alm.M_Warehouse_ID = loc.M_Warehouse_ID  ")
			.append(" 				AND alm.AD_Org_ID = org.AD_Org_ID ")
			.append(" 				AND alm.ControlExistencias = 'Y' ")
			.append("               AND alm.M_Warehouse_ID = ").append(M_Warehouse_ID);
		}
		
		sql.append(" WHERE org.AD_Org_ID = ").append(Env.getAD_Org_ID(Env.getCtx()));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(count <100){
					final MProduct line = new MProduct(Env.getCtx(), rs, null);
					count++;
					lines.add(line);
				}
			}
		} catch (Exception e) {
			System.out.println("SEVERE:" + e);
		} finally {
			DB.close(rs, pstmt);
		}

	return lines;
	}
	
	
	public static ErrorList saveLine(int movementId, MovementLine movementLine
			, int prodId, int uomId, BigDecimal amount, String description
			, int warehouseFrom, int locatorTo, String trxName, MMovementLine line) {
		ErrorList errorList  = new ErrorList();
		MMovement movement = new MMovement(Env.getCtx(), movementId, trxName);

		if (movementLine == null) {
			movementLine = new MovementLine();
//			line = new MMovementLine(Env.getCtx(), 0, null);
//		} else {
//			line = new MMovementLine(Env.getCtx(), movementLine.getId(), null);
		}

		movementLine.setMovementId(movementId);
		movementLine.setProduct(new MProduct(Env.getCtx(), prodId, null));
		movementLine.setSelectedUomId(uomId);
		movementLine.setOriginalQty(amount);
		movementLine.setDescription(description);

		if (movement.getEXME_CtaPac_ID() > 0) {
			movementLine.setCtaPac(new MEXMECtaPac(Env.getCtx(), movement.getEXME_CtaPac_ID(), null));
		}

		try {
			movementLine.setLocatorFromId(MLocator.getLocatorID(Env.getCtx(), warehouseFrom, null));
		} catch (final Exception e) {
			errorList.add(Error.CONFIGURATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.locatorID"));
		}
		
		if (errorList.isEmpty()) {

			movementLine.setLocatorToId(locatorTo);

//			MovementLine.fillRequest(movementLine, line);
			line.setM_Movement_ID(movementLine.getMovementId());
			line.setM_Product_ID(movementLine.getProduct().getM_Product_ID());

			if (movementLine.getProduct().getC_UOM_ID() != movementLine.getProduct().getC_UOMVolume_ID() && movementLine.getProduct().getC_UOMVolume_ID() == movementLine.getSelectedUomId()) {
				BigDecimal qtyVol = movementLine.getOriginalQty();
				qtyVol = qtyVol.setScale(0, BigDecimal.ROUND_FLOOR);

				line.setOriginalQty_Vol(qtyVol);
				movementLine.setOriginalVolQty(qtyVol);

				final BigDecimal qty = MEXMEUOMConversion.convertProductFrom(getCtx(), movementLine.getProduct().getM_Product_ID(), movementLine.getProduct().getC_UOMVolume_ID(), qtyVol, null, true);

				line.setOriginalQty(qty);
				movementLine.setOriginalQty(qty);
			} else {
				line.setOriginalQty(movementLine.getOriginalQty());
			}

			line.setC_UOM_ID(movementLine.getProduct().getC_UOM_ID());
			movementLine.setUomId(line.getC_UOM_ID());

			line.setC_UOMVolume_ID(movementLine.getProduct().getC_UOMVolume_ID());
			movementLine.setUomVolId(line.getC_UOMVolume_ID());

			line.setOp_Uom(movementLine.getSelectedUomId());

			line.setM_Locator_ID(movementLine.getLocatorFromId());
			line.setM_LocatorTo_ID(movementLine.getLocatorToId());

			line.setDescription(movementLine.getDescription());

			if (movementLine.getCtaPac() != null) {
				line.setEXME_CtaPac_ID(movementLine.getCtaPac().getEXME_CtaPac_ID());
			}
			
			

			errorList.getList().addAll(movementLine.validateRequest().getList());

			if (errorList.isEmpty()) {
				if (line.save(trxName)) {
					line.set_TrxName(null);
				} else {
					String error = Utilerias.getLastErrorMessage();
					errorList.add(Error.EXCEPTION_ERROR, StringUtils.defaultIfEmpty(error, Utilerias.getAppMsg(Env.getCtx(), "error.guardar")));
				}
			}
		}

		return errorList;
	}
	

	public boolean surtir(ErrorList errorList, MMovement movBackOrdr, String trxName){
		boolean success = false;
		List<MovementLine> lineas = MMovement.getLines(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trxName); 
		for (MovementLine bean: lineas) {
			MMovementLine linePO = new MMovementLine(Env.getCtx(), bean.getId(), trxName);
			if(bean.isVolumeSelected()){
				linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
				linePO.setMovementQty_Vol(bean.getOriginalVolQty().subtract(Env.ONE));
				bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setTargetVolQty(bean.getOriginalVolQty().subtract(Env.ONE));
				linePO.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
				linePO.setTargetQty_Vol(bean.getOriginalVolQty().subtract(Env.ONE));
			} else {
				linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
				linePO.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
			}
			success = linePO.save(trxName);	
		}
		if(success){
			movBackOrdr = new MMovement(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trxName);
			errorList.getList().addAll(MMovement.deliver(Env.getCtx(), movBackOrdr, lineas, trxName).getList());
		}		
		return success && errorList.isEmpty();
	}
	/**
	 * Obtener los registros hijos del movimiento entre consigna y propio 
	 * @param movementId Id de la linea del movimiento
	 * @return Puede devolver un null
	 */
	public static List<MMovement> getParentMovementConsigna(final Properties ctx, final int movementId, final String trxName){
		List<MMovement> all = new ArrayList<MMovement>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		
		.append(" SELECT * ")
		.append(" FROM M_Movement mov ") 
		.append(" INNER JOIN M_Movement submov ON submov.Parent_Movement_ID = mov.M_Movement_ID  ")
		.append("	                          AND submov.IsActive = 'Y'                           ")
		.append(" WHERE mov.M_Movement_ID = ?      ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, movementId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				final MMovement mLine = new MMovement(Env.getCtx(), rs, trxName);
				all.add(mLine);
			}//FINWHILE
		} catch (Exception e) {
			System.out.println("sEVERE"+e.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return all;
	}
	
	
	public static boolean confirmar(ErrorList errorList, MMovement movBackOrdr, String trxName){
		boolean success = false;
		List<MovementLine> lineas = MMovement.getLines(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trxName); 
		for (MovementLine bean: lineas) {
			MMovementLine linePO = new MMovementLine(Env.getCtx(), bean.getId(), trxName);
			if(bean.isVolumeSelected()){
				linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
				linePO.setMovementQty_Vol(bean.getOriginalVolQty().subtract(Env.ONE));
				
				bean.setConfirmedQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setConfirmedVolQty(bean.getOriginalVolQty().subtract(Env.ONE));
				
				bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setTargetVolQty(bean.getOriginalVolQty().subtract(Env.ONE));
				
			} else {
				linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setConfirmedQty(bean.getOriginalQty().subtract(Env.ONE));
				bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
			}
			success = linePO.save(trxName);	
		}
		if(success && errorList.isEmpty()){
			movBackOrdr = new MMovement(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trxName);
			errorList.getList().addAll(MMovementConfirm.confirm(Env.getCtx(), movBackOrdr, lineas, trxName).getList());
		}
		return success && errorList.isEmpty();
	}
	
	
	public static boolean cleanExiste(final Properties ctx,
			final int mProductID, final String trxName) {
		
		final String sql = "SELECT * FROM M_Storage WHERE AD_Org_ID = ? AND M_Product_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
			pstmt.setInt(2, mProductID);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MStorage mStorage = new MStorage(ctx, rset, null);
				mStorage.setQtyOnHand(Env.ZERO);
				mStorage.setQtyOrdered(Env.ZERO);
				mStorage.setQtyReserved(Env.ZERO);
				mStorage.save();
			}

		} catch (SQLException ex) {
			System.out.println("sEVERE "+ex.getLocalizedMessage());
		} finally {
			DB.close(rset, pstmt);
		}
		return true;
	} // getOfProduct
	
}

//List<MovementLine> lineas = MMovement.getLines(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trx.getTrxName()); 
//for (MovementLine bean: lineas) {
//	MMovementLine linePO = new MMovementLine(Env.getCtx(), bean.getId(), trx.getTrxName());
//	if(bean.isVolumeSelected()){
//		linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
//		linePO.setMovementQty_Vol(bean.getOriginalVolQty().subtract(Env.ONE));
//		bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
//		bean.setTargetVolQty(bean.getOriginalVolQty().subtract(Env.ONE));
//		linePO.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
//		linePO.setTargetQty_Vol(bean.getOriginalVolQty().subtract(Env.ONE));
//	} else {
//		linePO.setMovementQty(bean.getOriginalQty().subtract(Env.ONE));
//		bean.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
//		linePO.setTargetQty(bean.getOriginalQty().subtract(Env.ONE));
//	}
//	linePO.save(trx.getTrxName());
//}
//
//movBackOrdr = new MMovement(Env.getCtx(), movBackOrdr.getM_Movement_ID(), trx.getTrxName());
//errorList = MMovement.deliver(Env.getCtx(), movBackOrdr, lineas, trx.getTrxName());