package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_I_Inventory;
import org.compiere.model.X_M_InventoryLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Import Physical Inventory fom I_Inventory
 *
 * @author Jorg Janke
 * @version $Id: ImportInventory.java,v 1.3 2006/10/25 16:50:20 vgarcia Exp $
 */
public class ImportInventory extends SvrProcess {
	/** Client to be imported to */
	private transient int p_AD_Client_ID = 0;
	/** Organization to be imported to */
	private transient int p_AD_Org_ID = 0;
	/** Location to be imported to */
	private transient int p_M_Locator_ID = 0;
	/** Warehouse to be imported to */
	// private transient int p_M_Warehouse_ID = 0;
	/** Default Date */
	private transient Timestamp p_MovementDate = null;
	/** Delete old Imported */
	private transient boolean p_DeleteOldImported = false;

	private transient int x_M_Warehouse_ID = -1;
	private transient Timestamp x_MovementDate = null;
	private transient boolean udmCorrect = true;// Boleano para udm
	private transient int noInsert = 0;
	private transient int noInsertLine = 0;
	private MInventory inventory = null;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		for (final ProcessInfoParameter element : para) {
			final String name = element.getParameterName();

			if (element.getParameter() != null) {
				if ("AD_Client_ID".equals(name)) {
					p_AD_Client_ID = ((BigDecimal) element.getParameter()).intValue();
				} else if ("AD_Org_ID".equals(name)) {
					p_AD_Org_ID = ((BigDecimal) element.getParameter()).intValue();
					// }else if ("M_Warehouse_ID".equals(name)) {
					// p_M_Warehouse_ID = ((BigDecimal) element.getParameter())
					// .intValue();
				} else if ("M_Locator_ID".equals(name)) {
					p_M_Locator_ID = ((BigDecimal) element.getParameter()).intValue();
				} else if ("MovementDate".equals(name)) {
					p_MovementDate = (Timestamp) element.getParameter();
				} else if ("DeleteOldImported".equals(name)) {
					p_DeleteOldImported = "Y".equals(element.getParameter());
				} else if ("M_Inventory_ID".equals(name)) {
					inventory = new MInventory(getCtx(), Integer.parseInt(element.getParameter().toString()), get_TrxName());
				} else {
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
				}
			}
		}
	} // prepare

	/**
	 * Perrform process.
	 *
	 * @return Message
	 * @throws Exception
	 */
	@Override
	public String doIt() throws java.lang.Exception {
		log.info("M_Locator_ID=" + p_M_Locator_ID + ",MovementDate=" + p_MovementDate);
		//
		StringBuilder sql = null;
		int no = 0;
		final String clientCheck = " AND AD_Client_ID=" + p_AD_Client_ID;

		// **** Prepare ****
		querys();
		/*********************************************************************/

		// int noInsert = 0;
		// int noInsertLine = 0;

		// Go through Inventory Records
		if (DB.isOracle()) {
			sql = new StringBuilder("SELECT * FROM I_Inventory ");
			sql.append(" WHERE I_IsImported='N' ");
			sql.append(clientCheck);
			sql.append(" ORDER BY M_Warehouse_ID, TRUNC(MovementDate), I_Inventory_ID");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder("SELECT * FROM I_Inventory ");
			sql.append("WHERE I_IsImported='N'");
			sql.append(clientCheck);
			sql.append(" ORDER BY M_Warehouse_ID, DATE_TRUNC('day', MovementDate), I_Inventory_ID");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			final CachedRowSet rows = new CachedRowSetImpl();
			rows.populate(rs);
			rs.close();

			while (rows.next()) {
				final X_I_Inventory imp = new X_I_Inventory(getCtx(), rows, get_TrxName());

				if (imp.getM_Product_ID() <= 0 || imp.getM_Warehouse_ID() <= 0 || imp.getM_Locator_ID() <= 0) {
					continue;
				}

				if (x_M_Warehouse_ID != imp.getM_Warehouse_ID() && x_M_Warehouse_ID > -1) {
					continue; // ??
				}

				// Producto y udm para realizar conversiones en las lineas
				final MProduct product = new MProduct(getCtx(), MProduct.getIDByValue(imp.getValue()), null);

				// Si el producto es valido, es decir se encuentra y esta
				// activo.
				if (product.getM_Product_ID() > 0) {

					final MEXMEProductoOrg productOrg = MEXMEProductoOrg.getProductoOrg(getCtx(), product.getM_Product_ID(), Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), null);

					if (productOrg.getEXME_ProductoOrg_ID() > 0) {
						final MUOM uom = MUOM.getByName(getCtx(), imp.getC_UOM_Name(), null);

						if (productOrg.isLot() && StringUtils.isBlank(imp.getLot())) {
							imp.setI_ErrorMsg("LOT ERROR, " + imp.getI_ErrorMsg());
							imp.save();
							continue;
						} else if (productOrg.isLot()){
							final int setInstanceId = MAttributeSetInstance.getAttributeSetInstance(getCtx(), imp.getLot(), productOrg.getM_Product_ID(), true, null);
							if (setInstanceId > 0) {
								imp.setM_AttributeSetInstance_ID(setInstanceId);

							} else {
								imp.setI_ErrorMsg("LOT ERROR, " + imp.getI_ErrorMsg());
								imp.save();
								continue;
							}
						}

						final Timestamp MovementDate = TimeUtil.getDay(imp.getMovementDate());

						x_M_Warehouse_ID = imp.getM_Warehouse_ID();
						x_MovementDate = MovementDate;

						inventory = createInventory(imp, uom, MovementDate);

						if (!inventory.save() || !udmCorrect) {
							log.log(Level.SEVERE, "Inventory not saved");

							imp.setI_ErrorMsg("UDM ERROR, " + imp.getI_ErrorMsg());

							imp.save();
							continue;
						}

						final MInventoryLine line = invLine(inventory, imp, product);
						if (line.save()) {
							imp.setI_IsImported(true);
							imp.setM_Inventory_ID(line.getM_Inventory_ID());
							imp.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
							imp.setProcessed(true);
						} else {
							log.log(Level.SEVERE, "Inventory not saved");

							imp.setI_ErrorMsg("Inventory line not saved, " + imp.getI_ErrorMsg());
						}
					} else {
						log.log(Level.SEVERE, "Product not in Charge Master");
						imp.setI_ErrorMsg("ERR=Product not in Charge Master");
						noInsertLine++;
					}

					if (imp.save()) {
						noInsertLine++;
					} else {
						log.log(Level.SEVERE, "Inventory not saved");
					}
				} else { // if product != null
					imp.setI_ErrorMsg("ERR=Product not valid");
					log.log(Level.SEVERE, "Product not valid, value = " + imp.getValue());
					noInsertLine++;
				}
			} // End while

		} catch (final Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		if (inventory != null) {
			MInventory.updateInventoryLines(getCtx(), inventory.getM_Inventory_ID(), get_TrxName());
		}

		// Set Error to indicator to not imported
		sql = new StringBuilder(" UPDATE I_Inventory ");
		sql.append(" SET I_IsImported='N', Updated=SysDate ");
		sql.append("WHERE I_IsImported<>'Y'");
		sql.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog(0, null, new BigDecimal(no), "@Errors@");
		//
		addLog(0, null, new BigDecimal(noInsert), "@M_Inventory_ID@: @Inserted@");
		addLog(0, null, new BigDecimal(noInsertLine), "@M_InventoryLine_ID@: @Inserted@");

		return "";
	} // doIt

	/**
	 * Querys de actualizacion
	 *
	 * @throws SQLException
	 */
	private void querys() {
		StringBuffer sql = null;
		int no = 0;
		final String clientCheck = " AND AD_Client_ID=" + p_AD_Client_ID;

		// **** Prepare ****

		// Delete Old Imported
		try {
			if (p_DeleteOldImported) {
				sql = new StringBuffer("DELETE I_Inventory " + "WHERE I_IsImported='Y'").append(clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("Delete Old Impored =" + no);
			}

			// Set Client, Org, Location, IsActive, Created/Updated
			sql = ImportInventorySql.updateInventory(p_AD_Client_ID, p_AD_Org_ID, p_MovementDate);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.info("Reset=" + no);

			sql = ImportInventorySql.iventoryIsImported(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0) {
				log.warning("Invalid Org=" + no);
			}

			// Location
			sql = ImportInventorySql.updateLocator(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Locator from Value =" + no);

			sql = ImportInventorySql.updateLocatorXYZ(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Locator from X,Y,Z =" + no);

			if (p_M_Locator_ID != 0) {
				sql = new StringBuffer("UPDATE I_Inventory SET M_Locator_ID = ").append(p_M_Locator_ID).append(" WHERE M_Locator_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("Set Locator from Parameter=" + no);
			}

			sql = ImportInventorySql.updateLocatorWarehouse(clientCheck, true);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0) {
				log.warning("No Location=" + no);
			}

			// Set M_Warehouse_ID
			sql = ImportInventorySql.updateWarehouse(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Warehouse from Locator =" + no);

			sql = ImportInventorySql.updateLocatorWarehouse(clientCheck, false);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0) {
				log.warning("No Warehouse=" + no);
			}

			// Product
			sql = ImportInventorySql.updateProduct(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Product from Value=" + no);

			sql = ImportInventorySql.updateProductImported(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Product from UPC=" + no);

			sql = ImportInventorySql.updateProductImportedErr(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0) {
				log.warning("No Product=" + no);
			}

			// No QtyCount
			sql = ImportInventorySql.updateQtyCount(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0) {
				log.warning("No QtyCount=" + no);
			}

			commitEx();

		} catch (final Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
	}

	/**
	 * Crea en encabezado de inventario
	 *
	 * @param inventory
	 * @param imp
	 * @param uom
	 * @param product
	 * @return
	 */
	private MInventory createInventory(final X_I_Inventory imp, final MUOM uom, final Timestamp movementDate) {
		if (inventory == null || imp.getM_Warehouse_ID() != x_M_Warehouse_ID || !movementDate.equals(x_MovementDate)) {
			inventory = new MInventory(getCtx(), 0, get_TrxName());
			inventory.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
			inventory.setDescription("I " + imp.getM_Warehouse_ID() + " " + movementDate);
			inventory.setM_Warehouse_ID(MWarehouse.getIdWHByValue(Env.getCtx(), imp.getWarehouseValue()));
			inventory.setMovementDate(movementDate);
		}

		if (imp.getM_Warehouse_ID() != x_M_Warehouse_ID) {
			x_M_Warehouse_ID = imp.getM_Warehouse_ID();
		}

		noInsert++;

		return inventory;
	}

//	/**
//	 * Obtenemos la cantidad de almacen
//	 *
//	 * @param productID
//	 * @return
//	 */
//	private BigDecimal getBookQty(final int productID) {
//		BigDecimal qty = BigDecimal.ZERO;
//		final String sql = CalloutInventory.qtySql(0);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, productID);
//			pstmt.setInt(2, p_M_Locator_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				qty = rs.getBigDecimal(1);
//
//			}
//		} catch (final SQLException e) {
//			log.log(Level.SEVERE, sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return qty;
//	}

	/**
	 * Crea las lineas de inventario
	 *
	 * @param inventory
	 * @param imp
	 * @param product
	 * @return
	 */
	private MInventoryLine invLine(final MInventory inventory, final X_I_Inventory imp, final MProduct product) {
		// Linea de inventario
		MInventoryLine line = MInventoryLine.get(inventory, MLocator.getMLocatorByValue(Env.getCtx(), imp.getLocatorValue()).getM_Locator_ID(), MProduct.getIDByValue(imp.getValue()), 0);
		if (line == null) {
			line = new MInventoryLine(inventory, MLocator.getMLocatorByValue(Env.getCtx(), imp.getLocatorValue()).getM_Locator_ID(),// Locator
																																	// apartir
																																	// del
																																	// value
					MProduct.getIDByValue(imp.getValue()),// Producto id atravez
															// de la columna
															// value
					imp.getM_AttributeSetInstance_ID(), BigDecimal.ZERO, BigDecimal.ZERO // Cero
																							// para
																							// poder
																							// sumar
																							// depues
																							// en
																							// caso
																							// de
																							// que
																							// contenga
																							// varias
																							// veces
																							// el
																							// mismo
																							// producto
			);
			line.setC_UOM_ID(product.getC_UOM_ID());
			line.setC_UOMVolume_ID(product.getC_UOMVolume_ID());

			if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
				line.setQtyCount(imp.getQtyCount_Uom().add(imp.getQtyCount_Vol()));
				line.setQtyCount_Uom(line.getQtyCount());
				line.setQtyCount_Vol(BigDecimal.ZERO);

			} else {
				final BigDecimal qty = MEXMEUOMConversion.convertProductFrom(getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), imp.getQtyCount_Vol(), null, true);
				line.setQtyCount(imp.getQtyCount_Uom().add(qty == null ? BigDecimal.ZERO : qty));
				line.setQtyCount_Vol(imp.getQtyCount_Vol());
				line.setQtyCount_Uom(imp.getQtyCount_Uom());
			}

		} else {
			//Ya existe una linea, sumar cantidades existentes mas las nuevas
			if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
				line.setQtyCount(line.getQtyCount().add(imp.getQtyCount_Uom().add(imp.getQtyCount_Vol())));
				line.setQtyCount_Uom(line.getQtyCount_Uom().add(imp.getQtyCount_Uom().add(imp.getQtyCount_Vol())));

			} else {
				final BigDecimal qty = MEXMEUOMConversion.convertProductFrom(getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), imp.getQtyCount_Vol(), null, true);
				line.setQtyCount(line.getQtyCount().add(imp.getQtyCount_Uom().add(qty == null ? BigDecimal.ZERO : qty)));
				line.setQtyCount_Vol(line.getQtyCount_Vol().add(imp.getQtyCount_Vol()));
				line.setQtyCount_Uom(line.getQtyCount_Uom().add(imp.getQtyCount_Uom()));
			}
		}

		// Se agrega por defecto Inventory Difference
		line.setInventoryType(X_M_InventoryLine.INVENTORYTYPE_InventoryDifference);
		return line;
	}

	/**
	 * Colocar la cant contada en caso de no ser importada o tener 0
	 *
	 * @param line
	 * @param imp
	 */
	public void setQtyBooks(final MInventoryLine line, final X_I_Inventory imp) {
		final BigDecimal value = MStorage.getQtyOnHand(imp.getM_Locator_ID(), imp.getM_Product_ID(), 0, null);
		final MProduct product = new MProduct(getCtx(), imp.getM_Product_ID(), null);
		BigDecimal vol = BigDecimal.ZERO;
		if (value.compareTo(BigDecimal.ZERO) != 0) {
			vol = MEXMEUOMConversion.convertProductTo(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(), value, null).setScale(0, RoundingMode.DOWN);
		}
		final BigDecimal qtyUom = MUOMConversion.getConversionDivisor(product, value);

		line.setQtyBook(value);
		line.setQtyBook_Uom(qtyUom);
		line.setQtyBook_Vol(vol);
	}

} // ImportInventory

/***
 * Querys del metodo doit que ahora estan en el metodo querys // Delete Old
 * Imported if (p_DeleteOldImported) { sql = new
 * StringBuffer("DELETE I_Inventory " +
 * "WHERE I_IsImported='Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Delete Old Impored =" + no); }
 *
 * // Set Client, Org, Location, IsActive, Created/Updated sql = new
 * StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
 * sql.append("UPDATE I_Inventory SET AD_Client_ID = COALESCE (AD_Client_ID,")
 * .append(p_AD_Client_ID) .append(")," + " AD_Org_ID = COALESCE (AD_Org_ID,")
 * .append(p_AD_Org_ID).append("),"); if (p_MovementDate != null) {
 * sql.append(" MovementDate = COALESCE (MovementDate,")
 * .append(DB.TO_DATE(p_MovementDate)).append("),"); }
 * sql.append(" IsActive = COALESCE (IsActive, 'Y')," +
 * " Created = COALESCE (Created, SysDate)," +
 * " CreatedBy = COALESCE (CreatedBy, 0)," +
 * " Updated = COALESCE (Updated, SysDate)," +
 * " UpdatedBy = COALESCE (UpdatedBy, 0)," + " I_ErrorMsg = NULL," +
 * " M_Warehouse_ID = NULL," // reset + " I_IsImported = 'N' " +
 * "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL"); no =
 * DB.executeUpdate(sql.toString(), get_TrxName()); log.info("Reset=" + no);
 *
 * sql = new StringBuffer( "UPDATE I_Inventory o " +
 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '" +
 * "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0" +
 * " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
 * + " AND I_IsImported<>'Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName()); if (no != 0) {
 * log.warning("Invalid Org=" + no); }
 *
 * // Location sql = new StringBuffer(); sql.append("UPDATE I_Inventory i ")
 * .append("SET M_Locator_ID=(SELECT M_Locator_ID FROM M_Locator l")
 * .append(" WHERE i.LocatorValue=l.Value AND i.AD_Client_ID=l.AD_Client_ID ");
 *
 * if (DB.isOracle()) { sql.append(" AND ROWNUM=1) "); } else if
 * (DB.isPostgreSQL()) { sql = new StringBuffer(DB.getDatabase().addPagingSQL(
 * sql.toString(), 1, 1)); sql.append(") "); }
 *
 * sql.append("WHERE M_Locator_ID IS NULL AND LocatorValue IS NOT NULL");
 * sql.append(" AND I_IsImported<>'Y'").append(clientCheck);
 *
 * no = DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Locator from Value =" + no);
 *
 * sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
 * sql.append("UPDATE I_Inventory i ")
 * .append("SET M_Locator_ID=(SELECT M_Locator_ID FROM M_Locator l") .append(
 * " WHERE i.X=l.X AND i.Y=l.Y AND i.Z=l.Z AND i.AD_Client_ID=l.AD_Client_ID ");
 *
 * if (DB.isOracle()) { sql.append(" AND ROWNUM=1) "); } else if
 * (DB.isPostgreSQL()) { sql = new StringBuffer(DB.getDatabase().addPagingSQL(
 * sql.toString(), 1, 1)); sql.append(") "); }
 *
 * sql.append(
 * "WHERE M_Locator_ID IS NULL AND X IS NOT NULL AND Y IS NOT NULL AND Z IS NOT NULL"
 * ); sql.append(" AND I_IsImported<>'Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Locator from X,Y,Z =" + no);
 *
 * if (p_M_Locator_ID != 0) { sql = new
 * StringBuffer("UPDATE I_Inventory SET M_Locator_ID = ")
 * .append(p_M_Locator_ID) .append(" WHERE M_Locator_ID IS NULL" +
 * " AND I_IsImported<>'Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Locator from Parameter=" + no); } sql = new StringBuffer(
 * "UPDATE I_Inventory " +
 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Location, ' " +
 * "WHERE M_Locator_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
 * no = DB.executeUpdate(sql.toString(), get_TrxName()); if (no != 0) {
 * log.warning("No Location=" + no); }
 *
 * // Set M_Warehouse_ID sql = new StringBuffer( "UPDATE I_Inventory i " +
 * "SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Locator l WHERE i.M_Locator_ID=l.M_Locator_ID) "
 * + "WHERE M_Locator_ID IS NOT NULL" +
 * " AND I_IsImported<>'Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Warehouse from Locator =" + no); sql = new StringBuffer(
 * "UPDATE I_Inventory " +
 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Warehouse, ' " +
 * "WHERE M_Warehouse_ID IS NULL" +
 * " AND I_IsImported<>'Y'").append(clientCheck); no =
 * DB.executeUpdate(sql.toString(), get_TrxName()); if (no != 0) {
 * log.warning("No Warehouse=" + no); }
 *
 * // Product sql = new StringBuffer("UPDATE I_Inventory i\n" +
 * "SET M_Product_ID = ( SELECT p.M_Product_ID \n" + "\tFROM   M_Product p \n" +
 * "\t\tINNER  JOIN EXME_ProductoOrg po ON p.M_Product_ID = po.M_Product_ID\n" +
 * "\tWHERE i.Value = p.Value\n" + "\t\tAND i.AD_Org_ID = po.AD_Org_ID\n "); //
 * + "                        AND    i.AD_Org_ID = p.AD_Org_ID  ");
 *
 * if (DB.isOracle()) { sql.append("\t\t AND ROWNUM=1)\n"); } else if
 * (DB.isPostgreSQL()) { sql = new StringBuffer(DB.getDatabase().addPagingSQL(
 * sql.toString(), 1, 1)); sql.append(")\n "); }
 *
 * sql.append( " WHERE M_Product_ID IS NULL AND Value IS NOT NULL" +
 * " AND I_IsImported<>'Y'").append(clientCheck);
 *
 * no = DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Product from Value=" + no);
 *
 * sql = new StringBuffer("UPDATE I_Inventory i " +
 * "SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p" +
 * " WHERE i.UPC=p.UPC AND i.AD_Client_ID=p.AD_Client_ID ");
 *
 * if (DB.isOracle()) { sql.append(" AND ROWNUM=1) "); } else if
 * (DB.isPostgreSQL()) { sql = new StringBuffer(DB.getDatabase().addPagingSQL(
 * sql.toString(), 1, 1)); sql.append(") "); }
 *
 * sql.append( "WHERE M_Product_ID IS NULL AND UPC IS NOT NULL" +
 * " AND I_IsImported<>'Y'").append(clientCheck);
 *
 * no = DB.executeUpdate(sql.toString(), get_TrxName());
 * log.fine("Set Product from UPC=" + no);
 *
 * sql = new StringBuffer( "UPDATE I_Inventory " +
 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Product, ' " +
 * "WHERE M_Product_ID IS NULL" + " AND I_IsImported<>'Y'").append(clientCheck);
 * no = DB.executeUpdate(sql.toString(), get_TrxName());
 *
 * if (no != 0) { log.warning("No Product=" + no); }
 *
 * // No QtyCount sql = new StringBuffer( "UPDATE I_Inventory " +
 * "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Qty Count, ' " +
 * "WHERE QtyCount IS NULL" + " AND I_IsImported<>'Y'") .append(clientCheck); no
 * = DB.executeUpdate(sql.toString(), get_TrxName()); if (no != 0) {
 * log.warning("No QtyCount=" + no); }
 *
 * commit();
 */
