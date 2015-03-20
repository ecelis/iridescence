package org.compiere.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;


public class MEXMEMirthConfig extends X_EXME_MirthConfig {

	private static CLogger		slog = CLogger.getCLogger (MEXMEMirthConfig.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8887374168686820686L;

	public MEXMEMirthConfig(Properties ctx, int MEXME_MirthConfig,
			String trxName) {
		super(ctx, MEXME_MirthConfig, trxName);

	}

	public MEXMEMirthConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**Obtine instancia de MEXMEMirthConfig en base a registro activo*/
	public static MEXMEMirthConfig get(Properties ctx, String trxName) {

		MEXMEMirthConfig retValue = null;

		//TODO asegurarse de que solo halla un registro activo
		//TODO Agregar un manejo de cache, corregir accesLevel
		int adClientId = Env.getContextAsInt(ctx, "@AD_Client_ID");		
		adClientId =adClientId <0?Env.getAD_Client_ID(ctx):adClientId;
		int ids[] = getAllIDs(Table_Name, "isActive = 'Y' and AD_Client_ID = "+adClientId , trxName);

		// transaccion nula, solo es para consulta
		if (ids.length!=0){
			retValue = new MEXMEMirthConfig(ctx, ids[0], null);
		}

		return retValue;

	}
	
	
	/**
	 * arma ruta del servidor mirth en base a configuracion activa*/
	public String getAddress(){
		String url = null;

			try {
				url = new URL("https", getMirthHost(), Integer.valueOf(getMirthPort()), "").toString();
			} catch (NumberFormatException e) {
				
				slog.log(Level.SEVERE, "Error", e);
			} catch (MalformedURLException e) {
				
				slog.log(Level.SEVERE, "Error", e);
			}
		
		return url;
	}
	
	public String getJDBCString() {
		StringBuilder jdbc = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			jdbc.append("jdbc:postgresql://").append(getdbhost()).append(":").append(getdbport()).append("/").append(getdbname());
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Error", e);
		}
		return jdbc.toString();
	}

}
