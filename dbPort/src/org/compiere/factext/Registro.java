package org.compiere.factext;

import java.math.BigDecimal;
import java.util.HashMap;

public class Registro implements Comparable<Object> {

	public HashMap<String, Object> ht_Registro = new HashMap<String, Object>();
	
	public void clear(){
		this.ht_Registro.clear();
	}
	
	
	public Registro (HashMap<String, Object> ht){
		ht_Registro = ht;
	}
	
	public Registro (){
		super();
	}

	/**
	 * Convierte el valor del objeto a un String
	 * @param nombreCampo Llave de la HashTable
	 * @return si no es exitosa la conversion regresa ""
	 */
	public String getString(String nombreCampo){
		if ( ht_Registro.containsKey(nombreCampo)){
			String value = (String)ht_Registro.get(nombreCampo);
			if(value!=null){
				return value;
			}
		}		
		return "";
	}
	
	/**
	 * Convierte el valor del objeto a un double
	 * @param nombreCampo Llave de la HashTable
	 * @return si no es exitosa la conversion regresa 0
	 */
	public BigDecimal getBigDecimal(String nombreCampo) {
		if (ht_Registro.containsKey(nombreCampo)) {
			Object oValue = ht_Registro.get(nombreCampo);
			if (oValue != null) {
				BigDecimal value = new BigDecimal(0);
				if (oValue instanceof Integer) {
					value = new BigDecimal((Integer) oValue);
				}else {
					value = (BigDecimal) ht_Registro.get(nombreCampo);
				}

				return value;
			}
		}
		return new BigDecimal(0);
	}
	
	/**
	 * Convierte el valor del objeto a un int
	 * @param nombreCampo Llave de la HashTable
	 * @return si no es exitosa la conversion regresa 0
	 */
	public int getInt(String nombreCampo){
		if ( ht_Registro.containsKey(nombreCampo)){
			
			Object oValue = ht_Registro.get(nombreCampo);
			if (oValue != null) {
				int intValue = 0;
				
				if (oValue instanceof Integer) {
					intValue =  ((Integer) oValue).intValue();
				}else if(oValue instanceof BigDecimal){
					intValue = ((BigDecimal)oValue).intValue();
				}

				return intValue;
			}
		}		
		return 0;
	}
	/**
	 * Regresa el valor correspondiente al campo de un registro
	 * @param nombreCampo nombre del campo
	 * @return
	 */
	protected Object getValue(String nombreCampo){
		if ( ht_Registro.containsKey(nombreCampo)){
			Object value = ht_Registro.get(nombreCampo);
			return value;
		}
		return null;
	}
	
	/**
	 * Actualiza el valor del campo especificado
	 * @param nombreCampo nombre del campo
	 * @param value valor
	 */
	public void setValue(String nombreCampo, Object value){
		//if ( ht_Registro.containsKey(nombreCampo)){
			ht_Registro.put(nombreCampo, value);
		//}
	}
	
	public String toString(){
		
		return this.ht_Registro.toString();
	}
	
	public HashMap<String, Object> getAll(){
		return this.ht_Registro;
	}
	
	public boolean containsValue(Object value){
		return ht_Registro.containsValue(value);
		
	}
	
	public boolean containsKey(String key){
		return ht_Registro.containsKey(key);
	}
	
	public void removeKey (String key){
		ht_Registro.remove(key);
	}


	public int compareTo(Object oValue2) {
		Object oValue1 = null;
		
		oValue1   = ht_Registro.get("EXME_CTAPACDET_ID");
		int key1  = ((Integer) oValue1).intValue();
		
		oValue2   = ht_Registro.get("EXME_CTAPACDET_ID");
		int Key2  = ((Integer) oValue2).intValue();
		
		int ret = 0;
		
		
		if( key1  == Key2){
			ret=0;
		}
		
		if( key1  > Key2){
			ret=1;
		}
		
		if( key1 < Key2){
			ret=-1;
		}
		return ret;
	}
	
	
}
