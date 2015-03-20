package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.compiere.util.WebEnv;

public class MEXMEFarmacia extends X_EXME_Farmacia {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEFarmacia.class);
	
	public static final String MAILORDER_NAME = "MailOrder";
	public static final String RETAIL_NAME = "Retail";
	
	
	public MEXMEFarmacia (Properties ctx, int EXME_Farmacia_ID, String trxName)
    {
      super (ctx, EXME_Farmacia_ID, trxName);
    }

    /** Load Constructor */
    public MEXMEFarmacia (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    MEXMEERXConfig config = MEXMEERXConfig.getInstance(getCtx());
    
    /**
     * Lista con las dosis deacuerdo a su nivel de acceso
     * @param ctx
     * @param trxName
     * @return
     * @throws Exception
     */
	public static List<LabelValueBean> get(Properties ctx, String trxName)
		throws Exception {

			List<LabelValueBean> list = new ArrayList<LabelValueBean>();
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			sql.append(" SELECT Value || ' ' || Name AS label, EXME_Farmacia_ID ")
			.append(" FROM EXME_Farmacia ")
			.append(" WHERE IsActive = 'Y'")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Farmacia"))
			.append(" ORDER BY Name ");

			if (WebEnv.DEBUG) {
				s_log.log(Level.FINE,"MEXMEFarmacia.get() SQL : " + sql.toString());
			}

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					list.add(new LabelValueBean(rs.getString(1), String.valueOf(rs.getInt(2))));
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
		
		return list;
	}

	
	public static MEXMEFarmacia get(String value, Properties ctx, String trxName){
		MEXMEFarmacia farmacia = new MEXMEFarmacia(ctx, 0, trxName);
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT *  FROM EXME_Farmacia ")
		.append(" WHERE ISACTIVE = 'Y' AND VALUE  = ?")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEFarmacia.Table_Name));

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE,"MEXMEFarmacia.get(value,ctx,trxName) SQL : " + sql.toString());
		}

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				farmacia = new MEXMEFarmacia(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return farmacia;
		
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
			
		if(config == null){
			log.saveError("Error", "First define the Electronic Prescribing configuration");
//			return false;
		}
		
		
		if(!Ini.APP_MODE_DEMO.equals(Ini.getProperty(Ini.P_APPLICATION_MODE))) {
			if(getEMail() != null && !getEMail().equals("")){
				if(!Pattern.matches(Constantes.EMAIL_PATTERN, getEMail())){
					log.saveError("Error", new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
					.append(Msg.parseTranslation(getCtx(), "@InvalidEMail@")).toString());					
					setDescription("Error Pharmacy ");
					setIsActive(false);
//					return false;
				}
			}
			
			
			/** Validacion de NCPDP */

			String ncpdp = getValue();
			if (getValue() != null) {
				if (ncpdp.length() != 7) {
					log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue()) 
							.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.pharmacyValue")).toString());
					setDescription("Error Pharmacy ");
					setIsActive(false);
//					return false;	
				} 
			}

			/** Validacion de Telefono */

			int i = 0;		// Length of Telephone Number
			String phone = getPhone();


			if(phone == null || phone.isEmpty()) { // El telefono debe ser obligatorio
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue()) 
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhone")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			} else {
				i = phone.length();
			}

			if (i != 10) { // El telefono no puede tener mas de 10 digitos
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhoneDigit")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			}

			while (i != 0) { // El telefono debe de aceptar unicamente numeros
				if (!isNumeric(phone.charAt(i - 1))) {
					log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue()) 
							.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhoneNumeric")).toString());
					setDescription("Error Pharmacy ");
					setIsActive(false);
//					return false;
				}
				i--;
			}

			if (maximoRepetido(phone) > 7) { // No puede haber mas de 7 digitos repetidos en un numero telefonico
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhoneRepeat")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			}

			/** Validacion de su area */

			if (phone.startsWith("555")) { // El area del telefono no puede tener 555
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhoneArea")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			}

			if (phone.startsWith("0") || phone.startsWith("1")) { // El area no puede empezar con 0 o 1
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.mainPhoneAreaStart")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			}

			/** Validacion del Fax */

			int j = 0;		// Length of Fax Number
			String fax = getFaxNumber();

			if(fax == null || fax.isEmpty()) { // El fax debe ser obligatorio
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.fax")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			} else {
				j = fax.length();
			}

			if (j != 10) { // El fax no puede tener mas de 10 digitos
				log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
						.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.faxDigit")).toString());
				setDescription("Error Pharmacy ");
				setIsActive(false);
//				return false;
			}

			while (j != 0) { // El fax debe de aceptar unicamente numeros
				if (!isNumeric(fax.charAt(j - 1))) {
					log.saveError(null, new StringBuilder().append("Error Pharmacy ").append("name: ").append(getName()).append(" value: ").append(getValue())
							.append(Utilerias.getAppMsg(Env.getCtx(), "msj.validate.faxNumeric")).toString());
					setDescription("Error Pharmacy ");
					setIsActive(false);
//					return false;
				}
				j--;
			}
		}

		return super.beforeSave(newRecord);
	}
	
	private static boolean isNumeric(char cadena) {
		try {
			Integer.parseInt(cadena + "");
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public static int maximoRepetido(String cadena) {
		int maxCount = 0;
		int repeticiones;
		for (int i = 0; i < cadena.length(); i++) {
			repeticiones = repeticionesPatron(cadena, String.valueOf(cadena.charAt(i)));
			if (repeticiones > maxCount) {
				maxCount = repeticiones;
			}
		}
		return maxCount;
	}

	public static int repeticionesPatron(String cadena, String patron) {
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(cadena);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}

	

	public static boolean inactivateAll(String trxName) {
		
		StringBuilder sql = new StringBuilder("UPDATE ");
		sql.append(X_EXME_Farmacia.Table_Name);
		sql.append(" SET isActive = 'N' WHERE IsActive = 'Y' ");
		
		//si el resultado es diferente de menos uno, fue exitoso
		return DB.executeUpdate(sql.toString(), trxName) != -1;
		
	}
	
	
}
