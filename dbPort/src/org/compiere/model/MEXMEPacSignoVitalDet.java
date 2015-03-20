package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo para los signos vitales capturados
 * por el usuario paciente
 * @author raul
 *
 */
public class MEXMEPacSignoVitalDet extends X_EXME_PacSignoVitalDet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7806157898064229958L;
	
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MEXMEPacSignoVitalDet.class);
	
	/**
	 * signoVital del detalle
	 */
	
	private MEXMESignoVital signoVital = null;
	/**
	 * Constructor que recibe un ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPacSignoVitalDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Constructor que recibe un id
	 * @param ctx
	 * @param EXME_SectionBody_ID
	 * @param trxName
	 */
	public MEXMEPacSignoVitalDet(Properties ctx, int EXME_PacSignoVitalDet_ID, String trxName) {
		super(ctx, EXME_PacSignoVitalDet_ID, trxName);
	}
	
	/**
	 * Regresa una lista con los signos vitales capturados
	 * por el paciente ordenados por folio
	 * @param ctx
	 * @param pacienteId
	 * @param trxName
	 * @return
	 */
	public static List<List<MEXMEPacSignoVitalDet>> getDetFromPac(Properties ctx, int pacienteId, String trxName){
		
		List<List<MEXMEPacSignoVitalDet>> lst = new ArrayList<List<MEXMEPacSignoVitalDet>>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT DET.EXME_PACSIGNOVITALDET_ID FROM EXME_PACSIGNOVITALDET DET ")
        .append(" INNER JOIN EXME_SIGNOVITAL SV ON (SV.EXME_SIGNOVITAL_ID = DET.EXME_SIGNOVITAL_ID) ")
       	.append(" WHERE DET.EXME_PACIENTE_ID = ?  ")
       	.append(" AND SV.IsActive = 'Y' ")
       	.append(" ORDER BY DET.FOLIO, DET.FECHA, SV.SECUENCIA ");
		
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteId);
			rs = pstmt.executeQuery();

			Integer folioInt = null;
			List<MEXMEPacSignoVitalDet> listSigVitales = new ArrayList<MEXMEPacSignoVitalDet>();
			while (rs.next()) {

				MEXMEPacSignoVitalDet svtales = new MEXMEPacSignoVitalDet(ctx, rs.getInt(1), trxName);
				//Convertir a sistema de usuario -rvelazquez
				if(MUser.convertirUnidades(ctx)){

					BigDecimal valor = MUOM.convertirMedida(ctx, svtales.getSignoVital().getC_UOM().getC_UOM_ID(), svtales.getValor(), MUser.getSistMedicionUsuario(ctx));
					if(valor != null){
						svtales.setValor(valor);
					}

					//Nombre		
					int cuomToID = MUOMConversion.getCUOMToID(ctx, svtales.getSignoVital().getC_UOM().getC_UOM_ID(), MUser.getSistMedicionUsuario(ctx));
					if (cuomToID > 0){
						MUOM unidad = new MUOM(ctx, cuomToID, null);
						svtales.getSignoVital().getUom().setX12DE355(unidad.getX12DE355());
					}    
				}
				if (folioInt == null) {
					folioInt = svtales.getFolio();
				} else if (folioInt != svtales.getFolio()) {
					folioInt = svtales.getFolio();
					lst.add(listSigVitales);
					listSigVitales = new ArrayList<MEXMEPacSignoVitalDet>();
				}

				listSigVitales.add(svtales);
			}
			lst.add(listSigVitales);
		}catch(Exception e){
			s_log.log(Level.SEVERE, "getDetFromPac", e);
			
		} finally {
			DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
		
		return lst;
	}
	
	/**
	 * Regresa el objeto MEXMESignoVital
	 * al que pertenece el registro
	 * @return
	 */
	public MEXMESignoVital getSignoVital() {

		if(signoVital != null){
			return signoVital;
		}
		if(getEXME_SignoVital_ID()<=0){
			return null;
		}
		signoVital = new MEXMESignoVital(getCtx(), getEXME_SignoVital_ID(), get_TrxName());
		return signoVital;
	}
	
	/**
	 * Obtiene el siguiente folio
	 * @param trxName
	 * @param pacienteID
	 * @return
	 */
	public static int getNextFolio(String trxName, int pacienteID) {
		String sql = "SELECT COALESCE(MAX(folio),0)+1 FROM EXME_PacSignoVitalDet WHERE EXME_PACIENTE_ID=?";
		return DB.getSQLValue(trxName, sql, pacienteID);
	}
	
	
	private int unidadMedida = 0;
	
	public int getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}	
			
		
}
