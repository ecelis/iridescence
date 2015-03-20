/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author LLama
 *
 */
public class MEXMEDonador extends X_EXME_Donador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param EXME_Donador_ID
	 * @param trxName
	 */
	public MEXMEDonador(Properties ctx, int EXME_Donador_ID, String trxName) {
		super(ctx, EXME_Donador_ID, trxName);
		
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEDonador(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public String getFullName(){
		
		 StringBuffer name = new StringBuffer();
	        
		 name.append((getApellido1() == null ? "" : getApellido1() + " "));
		 name.append((getApellido2() == null ? "" : getApellido2() + " "));
		 name.append((getName() == null ? "" : getName() + " "));
		 name.append((getNombre2() == null ? "" : getNombre2() + " "));
		
		return name.toString();
	}

}
