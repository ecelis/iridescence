package org.compiere.util.confHL7;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.compiere.util.Constantes;

public class MessageArgs<K, V> extends Hashtable<K, V> {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public synchronized String toString() {
		int max = size() - 1;
		if (max == -1)
		    return "[]";

		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<K,V>> it = entrySet().iterator();

		//Create whereArgs according to SmartConnector specs
		for (int i = 0; ; i++) {
		    Map.Entry<K,V> e = it.next();
            K key = e.getKey();
            V value = e.getValue();
            String llave = "";
            if (value instanceof Number){
            	llave = "#"+key.toString();
		    }else if (value instanceof String){
		    	llave = "$"+key.toString();
		    }else if(value instanceof Timestamp){
		    	try {
					llave = "@" + Constantes.getSdfFecha().parse(key.toString()).toString() ;
				} catch (ParseException ex) {
					//FIXME: if parser fails, send the actual date
					llave = "@" + Constantes.getSdfFecha().format(new Date()); 
				}
		    }
            
            sb.append(key   == this ? "(this Map)" : "["+llave);
		    sb.append('=');
		    
		    sb.append(value == this ? "(this Map)" : value.toString()+"]");

		    if (i == max)
			return sb.toString();
		    sb.append(", ");
		}
	}

}
