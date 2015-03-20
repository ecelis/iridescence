package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author arangel
 *
 */
public class CallOutReplenish extends CalloutEngine {
	
	private final static String LVLMINVOL = "Level_Min_Vol";
	private final static String LVLMIN = "Level_Min";
	private final static String LVLMAXVOL = "Level_Max_Vol";
	private final static String LVLMAX = "Level_Max";
	private final static String ERRORCONVERSION = "error.udm.noExisteConversion";
	
	/**
	 * calcula el precio de lista de la unidad de volumen en base al precio de lista de unidad minima capturado
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */	
	public String inventoryLvlMax(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		StringBuilder msg = new StringBuilder("");
		if (isCalloutActive()){
			return msg.toString();
		}		
		setCalloutActive(true);
		
		BigDecimal valueLstMax;
		MProduct mProduct = new MProduct(ctx, Integer.valueOf(mTab.getValue("M_Product_ID").toString()), null);
		MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), mProduct.getM_Product_ID(), mProduct.getC_UOMVolume_ID(),null);
		
		if(rates==null && mProduct.getC_UOM_ID()!=mProduct.getC_UOMVolume_ID()){
			log.saveError(Utilerias.getMsg(Env.getCtx(), ERRORCONVERSION), mProduct.getName());
			msg.append(Utilerias.getMsg(Env.getCtx(), ERRORCONVERSION));
			mField.setValue(mField.getOldValue(), true);
		}
		//Cual campo se va a modificar
		if(StringUtils.isBlank(msg.toString()) && mTab.getValue(LVLMAXVOL) instanceof BigDecimal && (mTab.getValue(LVLMAXVOL) != null) && mField.getColumnName().equals(LVLMAXVOL)){
			valueLstMax = new BigDecimal(mTab.getValue(LVLMAXVOL).toString());			
			conversion(mTab ,LVLMAX, valueLstMax, mProduct, true);
		}else if(StringUtils.isBlank(msg.toString()) && mTab.getValue(LVLMAX) instanceof BigDecimal && (mTab.getValue(LVLMAX) != null) && mField.getColumnName().equals(LVLMAX)){
			valueLstMax = new BigDecimal(mTab.getValue(LVLMAX).toString());
			conversion(mTab ,LVLMAXVOL, valueLstMax, mProduct, false);
			//Si el valor es null o 0 se cambian ambos campos a 0
		}else if((value == null || ((Number)value).intValue() == 0) || !StringUtils.isBlank(msg.toString())){
			mTab.setValue(LVLMAX, Env.ZERO);
			mTab.setValue(LVLMAXVOL, Env.ZERO);
		}
		setCalloutActive(false);
		return msg.toString();
	}
	/**
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String inventoryLvlMin(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		StringBuilder msg = new StringBuilder("");
		if (isCalloutActive()){
			return msg.toString();
		}		
		setCalloutActive(true);
		
		BigDecimal valueLstMin;
		MProduct mProduct = new MProduct(ctx, Integer.valueOf(mTab.getValue("M_Product_ID").toString()), null);
		MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), mProduct.getM_Product_ID(), mProduct.getC_UOMVolume_ID(),null);
		
		if(rates==null && mProduct.getC_UOM_ID()!=mProduct.getC_UOMVolume_ID()){
			log.saveError(Utilerias.getMsg(Env.getCtx(), ERRORCONVERSION), mProduct.getName());
			msg.append(Utilerias.getMsg(Env.getCtx(), ERRORCONVERSION));
			mField.setValue(mField.getOldValue(), true);
		}
		
		if(StringUtils.isBlank(msg.toString()) && mTab.getValue(LVLMINVOL) instanceof BigDecimal && (mTab.getValue(LVLMINVOL) != null) && mField.getColumnName().equals(LVLMINVOL)){
			valueLstMin = new BigDecimal(mTab.getValue(LVLMINVOL).toString());
			conversion(mTab ,LVLMIN, valueLstMin, mProduct, true);		
		}else if(StringUtils.isBlank(msg.toString()) && mTab.getValue(LVLMIN) instanceof BigDecimal && (mTab.getValue(LVLMIN) != null) && mField.getColumnName().equals(LVLMIN)){
			valueLstMin = new BigDecimal(mTab.getValue(LVLMIN).toString());
			conversion(mTab ,LVLMINVOL, valueLstMin, mProduct, false);
		//Si el valor es null o 0 se cambian ambos campos a 0
		}else if((value == null || ((Number)value).intValue() == 0) || !StringUtils.isBlank(msg.toString())){
			mTab.setValue(LVLMINVOL, Env.ZERO);
			mTab.setValue(LVLMIN, Env.ZERO);
		}
		setCalloutActive(false);
		return msg.toString();	
	}
	
	/**
	 * Realiza las conversiones para los limites del inventario
	 * @param mTab
	 * @param columnName Campo que se modificara
	 * @param value Valor que se utiliza para la conversion
	 * @param mProduct producto 
	 * @param multiply true multiple, false divide
	 */
	private void conversion(GridTab mTab, String columnName, BigDecimal value, MProduct mProduct, boolean multiply){		
		if(value.compareTo(Env.ZERO) == 0){
			mTab.setValue(columnName, Env.ZERO);
		}else{
			if(mProduct.getC_UOM_ID() == mProduct.getC_UOMVolume_ID()){
				mTab.setValue(columnName,value);
			}else{
				if(multiply){
					int uomVol = Integer.valueOf(mTab.getValue("C_UOM_Volume_ID").toString());
					mTab.setValue(columnName, MEXMEUOMConversion.convertProductFrom(Env.getCtx(), mProduct.getM_Product_ID(), uomVol, value, null));
				}else{
					mTab.setValue(columnName, MEXMEUOMConversion.convertProductTo(Env.getCtx(), mProduct.getM_Product_ID(), mProduct.getC_UOMVolume_ID(), value, null));
				}
			}
			
		}
	}
}