package org.compiere.install;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.VitalSignsUtils;
import org.compiere.model.X_EXME_RangoSV;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMERangoSV extends X_EXME_RangoSV{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static CLogger s_log = CLogger.getCLogger (MEXMERangoSV.class);
	// valor convertido
	private BigDecimal valueIniTo = null;
	private BigDecimal valueFinTo = null;
	private VitalSignsUtils utils = null;
	private String imgShadowURL = null;

	public MEXMERangoSV(Properties ctx, int EXME_RangoSV_ID, String trxName) {
		super(ctx, EXME_RangoSV_ID, trxName);
	}

	public MEXMERangoSV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MEXMERangoSV> getRanks(Properties ctx, int exmeSignoID, String sex, int age, String trxName){
	
		 List<MEXMERangoSV> lista = new ArrayList<MEXMERangoSV>();
		 
		 StringBuilder sql = new StringBuilder();
	        
	     sql.append(" SELECT EXME_RANGOSV.* , EXME_SIGNOVITAL.C_UOM_ID AS UNITID, C_UOM.NAME AS UNIT ")
	     .append(" FROM EXME_RANGOSV  ")
	     .append(" INNER JOIN EXME_SIGNOVITAL ON EXME_RANGOSV.EXME_SIGNOVITAL_ID = EXME_SIGNOVITAL.EXME_SIGNOVITAL_ID ")
	     .append(" INNER JOIN C_UOM ON EXME_SIGNOVITAL.C_UOM_ID=C_UOM.C_UOM_ID ")
	     .append(" WHERE EXME_RANGOSV.EXME_SIGNOVITAL_ID=? ")
	     .append(" AND EXME_RANGOSV.EDADINI <= ? ")
	     .append(" AND EXME_RANGOSV.EDADFIN >= ? ")
	     .append(" AND (EXME_RANGOSV.SEXO=? OR EXME_RANGOSV.SEXO=?) ")
	     .append(" ORDER BY SEQUENCE ");

	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     
	     try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, exmeSignoID);
            pstmt.setInt(2, age);
            pstmt.setInt(3, age);
            pstmt.setString(4, SEXO_NotApplicable);
            pstmt.setString(5, sex);
            rs = pstmt.executeQuery();
        
            while (rs.next()) {
            	MEXMERangoSV obj = new MEXMERangoSV(ctx, rs, trxName);
            	obj.convertFromDB(rs.getInt("UNITID"));
//            	obj.setUnit(rs.getString("UNIT"));
//            	
//            	if(MUser.convertirUnidades(ctx)){
//            		
//            		// convertimos el valor minimo y maximo correspondiente
//            		BigDecimal valorMin = MUOM.convertirMedida(ctx, obj.getCuomID(), obj.getValorIni(), MUser.getSistMedicionUsuario(ctx));
//            		BigDecimal valorMax = MUOM.convertirMedida(ctx, obj.getCuomID(), obj.getValorFin(), MUser.getSistMedicionUsuario(ctx));
//
//					if (valorMin != null && valorMin.compareTo(Env.ZERO) > 0) {
//						obj.setValorIni(valorMin);
//					}
//					if (valorMax != null && valorMax.compareTo(Env.ZERO) > 0) {
//						obj.setValorFin(valorMax);
//					}
//
//                	//obtenemos la unidad de medida correspondiente al sistema de medicion del usuario
//					int cuomToID = MUOMConversion.getCUOMToID(ctx, obj.getCuomID(), MUser.getSistMedicionUsuario(ctx));
//					if (cuomToID > 0 && cuomToID != obj.getCuomID()) {
//						obj.setCuomID(cuomToID);
//						MUOM unidad = new MUOM(ctx, cuomToID, null);
//						obj.setUnit(unidad.getName());
//					}
//            	}
            	lista.add(obj);
            }
        
        } catch (Exception e) {
        	s_log.log(Level.SEVERE,e.toString());
        } finally {
        	DB.close(rs, pstmt);
        	pstmt = null;
            rs=null;
        }
        return lista;
	 }

	

	/**
	 * Convierte los valores valueMin y valueMax si el usuario requiere conversion
	 * y los asigna en valueMinTo y valueMaxTo
	 */
	private void convertFromDB(int uomID) {
		utils = new VitalSignsUtils(getCtx(), uomID);
		valueIniTo = utils.convertFromDB(getValorIni());
		valueFinTo = utils.convertFromDB(getValorFin());
	}
	
	/**
	 * Regresa el valor convertido si el usuario esta configurado que requiere conversion, de lo
	 * contrario regresa el valor original de la base de datos
	 * @return valor inicial del rango
	 */
	public BigDecimal getValorIniTo() {
		if (valueIniTo != null && utils.getUomTo() != null) {
			return valueIniTo;
		} else {
			return super.getValorIni();
		}
	}
	/**
	 * Regresa el valor convertido si el usuario esta configurado que requiere conversion, de lo
	 * contrario regresa el valor original de la base de datos
	 * @return valor final del rango
	 */
	public BigDecimal getValorFinTo() {
		if (valueFinTo != null && utils.getUomTo() != null) {
			return valueFinTo;
		} else {
			return super.getValorFin();
		}
	}
	/**Lama*/
	public String getImgShadowURL() {
		if (imgShadowURL == null && StringUtils.isNotEmpty(getImageURL())) {
			final StringBuilder imageURL = new StringBuilder();
			imageURL.append(StringUtils.substringBeforeLast(getImageURL(), "/"));
			imageURL.append("/sombra_");
			imageURL.append(StringUtils.substringAfterLast(getImageURL(), "/"));
			imgShadowURL = imageURL.toString();
		}
		return imgShadowURL;
	}
}
