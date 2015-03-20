/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 */
package org.compiere.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.StorageVO;
import org.compiere.util.StorageVOList;

/**
 * Modelo que representa una linea de un traspaso entre almacenes
 * 
 * @author Gisela Lee Gonzalez
 * @deprecated
 */
public class MovementLine implements Serializable {
	private static final long serialVersionUID = -8074081825170694900L;

	/** Propiedad MovementLineID. */
	private long movementLineID = 0;
	/** unidad de medida de volumen */
	private String uomNameVolume = null;
	/** unidad de medida de volumen id */
	private int cUOMVolumeID = 0;
	/** nivel maximo en udm de volumen */
	private long levelMaxVol = 0;
	/** nivel minimo en udm de volumen */
	private long levelMinVol = 0;
	/** cantidad disponible en udm de volumen */
	private BigDecimal disponibleQtyVol = Env.ZERO;//rs.getLong("DisponibleQty"));
	/** cantidad on hand en udm de volumen */
	private BigDecimal qtyOnHandVol = Env.ZERO;//rs.getLong("QtyOnHand"));
	/** cantidad reserved en udm de volumen */
	private BigDecimal qtyReservedVol = Env.ZERO;//rs.getLong("QtyReserved"));
	/** cantidad ordered en udm de volumen */
	private BigDecimal qtyOrderedVol = Env.ZERO;//rs.getLong("QtyOrdered"));
	/** cantidad confirmada en udm de volumen */
	private BigDecimal confirmedQtyVol = Env.ZERO;//rs.getLong("ConfirmedQty"));
	/** cantidad transito en udm de volumen */
	private BigDecimal enTransitoVol = Env.ZERO;//rs.getBigDecimal("EnTransito"));
	/** cantidad original en udm de volumen */
	private BigDecimal originalQtyVol = Env.ZERO;
	/** Propiedad Level_Max. */
	private long level_MaxMinVol = 0;
	
	/**
	 * Obtener la propiedad movementLineID.
	 * 
	 * @return La propiedad MovementLineID.
	 */
	public long getMovementLineID() {
		return this.movementLineID;
	}

	/**
	 * Establecer la propiedad movementLineID.
	 * 
	 * @param movementLineID
	 *            Nueva propiedad movementLineID.
	 */
	public void setMovementLineID(long movementLineID) {
		this.movementLineID = movementLineID;
	}

	/**
	 * Propiedad Line.
	 */
	private long line = 0;

	/**
	 * Obtener la propiedad line.
	 * 
	 * @return La propiedad Line.
	 */
	public long getLine() {
		return this.line;
	}

	/**
	 * Establecer la propiedad line.
	 * 
	 * @param line
	 *            Nueva propiedad line.
	 */
	public void setLine(long line) {
		this.line = line;
	}

	/**
	 * Propiedad ProductID.
	 */
	private long productID = 0;

	/**
	 * Obtener la propiedad productID.
	 * 
	 * @return La propiedad ProductID.
	 */
	public long getProductID() {
		return this.productID;
	}

	/**
	 * Establecer la propiedad productID.
	 * 
	 * @param productID
	 *            Nueva propiedad productID.
	 */
	public void setProductID(long productID) {
		this.productID = productID;
	}

	/**
	 * Propiedad ProdName.
	 */
	private String prodName = null;

	/**
	 * Obtener la propiedad prodName.
	 * 
	 * @return La propiedad ProdName.
	 */
	public String getProdName() {
		return this.prodName;
	}

	/**
	 * Establecer la propiedad prodName.
	 * 
	 * @param prodName
	 *            Nueva propiedad prodName.
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * Propiedad UomName.
	 */
	private String uomName = null;

	/**
	 * Obtener la propiedad uomName.
	 * 
	 * @return La propiedad UomName.
	 */
	public String getUomName() {
		return this.uomName;
	}

	/**
	 * Establecer la propiedad uomName.
	 * 
	 * @param uomName
	 *            Nueva propiedad uomName.
	 */
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	/**
	 * Propiedad Value.
	 */
	private String Value = null;

	/**
	 * Obtener la propiedad Value.
	 * 
	 * @return La propiedad Value.
	 */
	public String getValue() {
		return this.Value;
	}

	/**
	 * Establecer la propiedad Value.
	 * 
	 * @param Value
	 *            Nueva propiedad Value.
	 */
	public void setValue(String Value) {
		this.Value = Value;
	}

	/**
	 * Propiedad OriginalQty.
	 */
	private long originalQty = 0;

	/**
	 * Obtener la propiedad originalQty.
	 * 
	 * @return La propiedad OriginalQty.
	 */
	public long getOriginalQty() {
		return this.originalQty;
	}

	/**
	 * Establecer la propiedad originalQty.
	 * 
	 * @param originalQty
	 *            Nueva propiedad originalQty.
	 */
	public void setOriginalQty(long originalQty) {
		this.originalQty = originalQty;
	}

	/**
	 * Propiedad TargetQty.
	 */
	private long targetQty = 0;

	/**
	 * Obtener la propiedad targetQty.
	 * 
	 * @return La propiedad TargetQty.
	 */
	public long getTargetQty() {
		return this.targetQty;
	}

	/**
	 * Establecer la propiedad targetQty.
	 * 
	 * @param targetQty
	 *            Nueva propiedad targetQty.
	 */
	public void setTargetQty(long targetQty) {
		this.targetQty = targetQty;
	}

	/**
	 * Propiedad Description.
	 */
	private String description = null;

	/**
	 * Obtener la propiedad description.
	 * 
	 * @return La propiedad Description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Establecer la propiedad description.
	 * 
	 * @param description
	 *            Nueva propiedad description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Propiedad uPC
	 */
	private String upC = null;

	/**
	 * Obtener la propiedad uPC
	 * 
	 * @return La propiedad uPC
	 */
	public String getUpC() {
		return this.upC;
	}

	/**
	 * Establecer la propiedad uPC.
	 * 
	 * @param prodName
	 *            Nueva propiedad uPC.
	 */
	public void setUpC(String upC) {
		this.upC = upC;
	}

	/**
	 * Propiedad SKU
	 */
	private String sku = null;

	/**
	 * Obtener la propiedad sKU.
	 * 
	 * @return La propiedad sKU.
	 */
	public String getSku() {
		return this.sku;
	}

	/**
	 * Establecer la propiedad sKU.
	 * 
	 * @param prodName
	 *            Nueva propiedad sKU.
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Propiedad UOM.
	 */
	private long uOM = 0;

	/**
	 * Obtener la propiedad uOM.
	 * 
	 * @return La propiedad uOM.
	 */
	public long getUOM() {
		return this.uOM;
	}

	/**
	 * Establecer la propiedad uOM.
	 * 
	 * @param productID
	 *            Nueva propiedad uOM.
	 */
	public void setUOM(long uOM) {
		this.uOM = uOM;
	}

	/**
	 * Propiedad DisponibleQty.
	 */
	private long disponibleQty = 0;

	/**
	 * Obtener la propiedad DisponibleQty
	 * 
	 * @return La propiedad DisponibleQty
	 */
	public long getDisponibleQty() {
		return this.disponibleQty;
	}

	/**
	 * Establecer la propiedad disponibleQty
	 * 
	 * @param productID
	 *            Nueva propiedad disponibleQty.
	 */
	public void setDisponibleQty(long disponibleQty) {
		this.disponibleQty = disponibleQty;
	}

	/**
	 * Propiedad QtyOnHand.
	 */
	private long qtyOnHand = 0;

	/**
	 * Obtener la propiedad qtyOnHand
	 * 
	 * @return La propiedad qtyOnHand
	 */
	public long getQtyOnHand() {
		return this.qtyOnHand;
	}

	/**
	 * Establecer la propiedad qtyOnHand
	 * 
	 * @param productID
	 *            Nueva propiedad qtyOnHand
	 */
	public void setQtyOnHand(long qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}

	/**
	 * Propiedad QtyReserved.
	 */
	private long qtyReserved = 0;

	/**
	 * Obtener la propiedad qtyReserved
	 * 
	 * @return La propiedad qtyReserved
	 */
	public long getQtyReserved() {
		return this.qtyReserved;
	}

	/**
	 * Establecer la propiedad qtyReserved
	 * 
	 * @param qtyReserved
	 *            Nueva propiedad qtyReserved
	 */
	public void setQtyReserved(long qtyReserved) {
		this.qtyReserved = qtyReserved;
	}

	/**
	 * Propiedad QtyOrdered.
	 */
	private long qtyOrdered = 0;

	/**
	 * Obtener la propiedad qtyOrdered
	 * 
	 * @return La propiedad qtyOrdered
	 */
	public long getQtyOrdered() {
		return this.qtyOrdered;
	}

	/**
	 * Establecer la propiedad qtyOrdered
	 * 
	 * @param qtyOrdered
	 *            Nueva propiedad qtyOrdered
	 */
	public void setQtyOrdered(long qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	/**
	 * Propiedad Level_Max.
	 */
	private long level_MaxMin = 0;

	/**
	 * Obtener la propiedad level_Max
	 * 
	 * @return La propiedad level_Max
	 */
	public long getLevel_MaxMin() {
		return this.level_MaxMin;
	}

	/**
	 * Establecer la propiedad level_Max
	 * 
	 * @param level_Max
	 *            Nueva propiedad level_Max
	 */
	public void setLevel_MaxMin(long level_MaxMin) {
		this.level_MaxMin = level_MaxMin;
	}

	/**
	 * Propiedad ConfirmedQty
	 */
	private long confirmedQty = 0;

	/**
	 * Obtener la propiedad confirmedQty
	 * 
	 * @return La propiedad confirmedQty
	 */
	public long getConfirmedQty() {
		return this.confirmedQty;
	}

	/**
	 * Establecer la propiedad confirmedQty
	 * 
	 * @param productID
	 *            Nueva propiedad confirmedQty
	 */
	public void setConfirmedQty(long confirmedQty) {
		this.confirmedQty = confirmedQty;
	}

	/**
	 * Propiedad Description.
	 */
	private String descripLine = null;

	/**
	 * Obtener la propiedad descripline
	 * 
	 * @return La propiedad Descripline.
	 */
	public String getDescripLine() {
		return this.descripLine;
	}

	/**
	 * Establecer la propiedad descripline
	 * 
	 * @param descripline
	 *            Nueva propiedad descripline
	 */
	public void setDescripLine(String descripLine) {
		this.descripLine = descripLine;
	}

	/**
	 * CAntidad en transito
	 */
	private BigDecimal enTransito = Env.ZERO;

	public BigDecimal getEnTransito() {
		return enTransito;
	}

	public void setEnTransito(BigDecimal enTransito) {
		if (enTransito == null)
			enTransito = Env.ZERO;
		this.enTransito = enTransito;
	}

	private String prodValue = null;

	public String getProdValue() {
		return prodValue;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	private BigDecimal prodCosto = null;
	private BigDecimal totLine = null;

	public BigDecimal getProdCosto() {
		return prodCosto;
	}

	public void setProdCosto(BigDecimal prodCosto) {
		this.prodCosto = prodCosto;
	}

	public BigDecimal getTotLine() {
		return totLine;
	}

	public void setTotLine(BigDecimal totLine) {
		this.totLine = totLine;
	}

	// Se agrega el numero de serie
	private String serie = null;

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	private int orgTrx = 0;

	public int getOrgTrx() {
		return orgTrx;
	}

	public void setOrgTrx(int orgTrx) {
		this.orgTrx = orgTrx;
	}

	private String lote;

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	private String fechaLote;

	public String getFechaLote() {
		return fechaLote;
	}

	public void setFechaLote(String fechaLote) {
		this.fechaLote = fechaLote;
	}

	private String loteInfo;

	public String getLoteInfo() {
		return loteInfo;
	}

	public void setLoteInfo(String loteInfo) {
		this.loteInfo = loteInfo;
	}

	/*
	 * Noelia: se agrega propiedad para saber si el producto de la linea es numero de serie
	 */
	public boolean numSerie = false;

	public boolean isNumSerie() {
		return numSerie;
	}

	public void setNumSerie(boolean numSerie) {
		this.numSerie = numSerie;
	}

	private boolean tieneLote;

	public boolean isTieneLote() {
		return tieneLote;
	}

	public void setTieneLote(boolean tieneLote) {
		this.tieneLote = tieneLote;
	}

	public static List<MovementLine> divideByLot(Properties ctx, List<MovementLine> original, StorageVOList lotes, String trxName) throws Exception {
		List<MovementLine> retValue = new ArrayList<MovementLine>();
		int linea = 10;
		for (int i = 0; i < original.size(); i++) {
			MovementLine ml = original.get(i);
			MProduct p = new MProduct(ctx, (int) ml.getProductID(), null);
			if (p.isLot()) {
				List<StorageVO> tmpList = lotes.getByProductID((int) ml.getProductID());
				if (tmpList == null) {
					throw new Exception("msj.selccionarLotes");
				} else {
					if (tmpList.size() == 0) {
						throw new Exception("msj.selccionarLotes");
					}
					for (int j = 0; j < tmpList.size(); j++) {
						StorageVO tmp = tmpList.get(j);
						MovementLine toAdd = new MovementLine();
						toAdd.setLote(tmp.getLot());
						toAdd.setQtyOnHand(tmp.getQtyOnHand().longValue());
						toAdd.setQtyOrdered(tmp.getQtyRequested().longValue());
						toAdd.setQtyReserved(tmp.getQtyReserved().longValue());
						toAdd.setUOM(tmp.getC_Uom_ID());
						MUOM uom = new MUOM(ctx, tmp.getC_Uom_ID(), null);
						toAdd.setUomName(uom.getName());
						toAdd.setFechaLote(tmp.getGuaranteeDate());
						toAdd.setM_AttributeSetInstance(tmp.getM_AttributeSetInstance_ID());
						toAdd.setDisponibleQty(tmp.getQtyOnHand().longValue());
						toAdd.setOriginalQty(tmp.getQtyRequested().longValue());
						toAdd.setTieneLote(true);
						toAdd.setProductID(tmp.getM_Product_ID());
						toAdd.setProdValue(tmp.getValue());
						toAdd.setProdName(tmp.getName());
						toAdd.setLote(tmp.getLot());
						toAdd.setDescription(ml.getDescription() + " - AutoLot");
						linea = linea + 10;
						toAdd.setLine(linea);
						// Colectiva no perder la cuenta paciente
						retValue.add(toAdd);
					}
				}
			} else {
				ml.setLine(linea);
				retValue.add(ml);
				linea = linea + 10;
			}
		}
		return retValue;
	}

	private int M_AttributeSetInstance = 0;

	public int getM_AttributeSetInstance() {
		return M_AttributeSetInstance;
	}

	public void setM_AttributeSetInstance(int attributeSetInstance) {
		M_AttributeSetInstance = attributeSetInstance;
	}

	/**
	 * Cuenta Paciente por l�nea de pedido para receta colectiva
	 */
	private int ctaPacID = 0;

	public int getCtaPacID() {
		return ctaPacID;
	}

	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}

	private String ctaPacValue = null;

	public String getCtaPacValue() {
		return ctaPacValue;
	}

	public void setCtaPacValue(String ctaPacValue) {
		this.ctaPacValue = ctaPacValue;
	}

	private int almacenID = 0;

	public int getAlmacenID() {
		return almacenID;
	}

	public void setAlmacenID(int almacenID) {
		this.almacenID = almacenID;
	}

	private MEXMEMovementLineB movementLineB = null;

	public MEXMEMovementLineB getMovementLineB() {
		return movementLineB;
	}

	public void setMovementLineB(MEXMEMovementLineB movementLineB) {
		this.movementLineB = movementLineB;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == MovementLine.class) {
			MovementLine otro = (MovementLine) obj;
			if (otro.getProductID() == this.productID) {
				return true;
			} else {
				return false;
			}
		} else if (obj.getClass() == Integer.class) {
			Integer i = (Integer) obj;
			if (i == this.productID) {
				return true;
			} else {
				return false;
			}
		}
		return super.equals(obj);
	}

	public String getStrLevelMaxMin() {
		return String.valueOf(getLevel_MaxMin());
	}

	public String getStrDisponibleQty() {
		return String.valueOf(getDisponibleQty());
	}

	public String getStrQtyOnHand() {
		return String.valueOf(getQtyOnHand());
	}

	public String getStrQtyReserved() {
		return String.valueOf(getQtyReserved());
	}

	public String getStrQtyOrdered() {
		return String.valueOf(getQtyOrdered());
	}

	public String getStrConfirmedQty() {
		return String.valueOf(getConfirmedQty());
	}

	public String getStrEnTransito() {
		return String.valueOf(getEnTransito());
	}

	public String getStrOriginalQty() {
		return String.valueOf(getOriginalQty());
	}

	private boolean isNew = false;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public long getuOM() {
		return uOM;
	}

	public void setuOM(long uOM) {
		this.uOM = uOM;
	}

	public String getUomNameVolume() {
		return uomNameVolume;
	}

	public void setUomNameVolume(String uomNameVolume) {
		this.uomNameVolume = uomNameVolume;
	}

	public int getcUOMVolumeID() {
		return cUOMVolumeID;
	}

	public void setcUOMVolumeID(int cUOMVolumeID) {
		this.cUOMVolumeID = cUOMVolumeID;
	}

	public long getLevelMaxVol() {
		return levelMaxVol;
	}

	public void setLevelMaxVol(long levelMaxVol) {
		this.levelMaxVol = levelMaxVol;
	}

	public long getLevelMinVol() {
		return levelMinVol;
	}

	public void setLevelMinVol(long levelMinVol) {
		this.levelMinVol = levelMinVol;
	}
	public BigDecimal getDisponibleQtyVol() {
		return disponibleQtyVol;
	}

	public void setDisponibleQtyVol(BigDecimal disponibleQtyVol) {
		this.disponibleQtyVol = disponibleQtyVol;
	}

	public BigDecimal getQtyOnHandVol() {
		return qtyOnHandVol;
	}

	public void setQtyOnHandVol(BigDecimal qtyOnHandVol) {
		this.qtyOnHandVol = qtyOnHandVol;
	}

	public BigDecimal getQtyReservedVol() {
		return qtyReservedVol;
	}

	public void setQtyReservedVol(BigDecimal qtyReservedVol) {
		this.qtyReservedVol = qtyReservedVol;
	}

	public BigDecimal getQtyOrderedVol() {
		return qtyOrderedVol;
	}

	public void setQtyOrderedVol(BigDecimal qtyOrderedVol) {
		this.qtyOrderedVol = qtyOrderedVol;
	}

	public BigDecimal getConfirmedQtyVol() {
		return confirmedQtyVol;
	}

	public void setConfirmedQtyVol(BigDecimal confirmedQtyVol) {
		this.confirmedQtyVol = confirmedQtyVol;
	}

	public BigDecimal getEnTransitoVol() {
		return enTransitoVol;
	}

	public void setEnTransitoVol(BigDecimal enTransitoVol) {
		this.enTransitoVol = enTransitoVol;
	}

	public BigDecimal getOriginalQtyVol() {
		return originalQtyVol;
	}

	public void setOriginalQtyVol(BigDecimal originalQtyVol) {
		this.originalQtyVol = originalQtyVol;
	}
	public long getLevel_MaxMinVol() {
		return level_MaxMinVol;
	}

	public void setLevel_MaxMinVol(long level_MaxMinVol) {
		this.level_MaxMinVol = level_MaxMinVol;
	}
}