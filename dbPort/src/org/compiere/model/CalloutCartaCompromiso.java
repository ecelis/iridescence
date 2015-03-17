package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * <b>Callout para Carta Compromiso</b>
 * <p>
 * <b>Fecha:</b> 02/Mayo/2006<p>
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/08/15 22:26:07 $<p>
 * <p>
 * @author Daniel Rodriguez
 * @version $Revision: 1.5 $
 */
public class CalloutCartaCompromiso extends CalloutEngine{

	public String afectaciones(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){

        if(value == null){
			return "";
		}
		
		Integer EXME_Paciente_ID = null;
		if(value instanceof BigDecimal)
			EXME_Paciente_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Paciente_ID = (Integer)value;

		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0){
			return "";
		}else {
			try {
				int ctaPacId = ctaPacID(EXME_Paciente_ID);
				amount(ctx, mTab,ctaPacId);
				clasification(ctx, mTab, ctaPacId);
				pagos(ctx, mTab,ctaPacId);
				dias(ctx, mTab,ctaPacId);
			} catch(Exception e) {
				log.log(Level.SEVERE, "socType", e);
				return e.getLocalizedMessage();
			}

			return "";
		}
	}

	public String amount(Properties ctx, GridTab mTab, Integer EXME_Paciente_ID){
		if(EXME_Paciente_ID == null){
			return "";
		}

		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0){
			return "";
		} else {
			try {
//            Revisamos si existe una cuenta paciente.
                MEXMECtaPac[] ctasPac = MEXMECtaPac.getOfPaciente(ctx, EXME_Paciente_ID, MEXMECtaPac.ENCOUNTERSTATUS_Admission, null);
				if(ctasPac!= null && ctasPac.length>0){
//					 BigDecimal saldo = ctasPac[0].getSaldo(); 
//					 if ( saldo == null)
//							 saldo = new BigDecimal(1);
//					mTab.setValue("Amount",saldo);
				}
			} catch(Exception e){
				log.log(Level.SEVERE, "socType", e);
				return e.getLocalizedMessage();
			}

			return "";
		}
	}

	public String pagos(Properties ctx, GridTab mTab, Integer EXME_Paciente_ID){
		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0)
			return "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
//              Revisamos si existe una cuenta paciente.
                MEXMECtaPac[] ctasPac = MEXMECtaPac.getOfPaciente(ctx, EXME_Paciente_ID, MEXMECtaPac.ENCOUNTERSTATUS_Admission, null);
                BigDecimal saldo = Env.ZERO;
                if(ctasPac!= null && ctasPac.length>0){
 //                   saldo = ctasPac[0].getSaldo(); 
                }

				pstmt = DB.prepareStatement(
						" SELECT EXME_Plazos_Clas.pagos " +
						" FROM EXME_Plazos_Clas, EXME_ClasCliente, EXME_CtaPac, EXME_Clasificacion " +
						" WHERE EXME_CtaPac.EXME_CtaPac_ID = ? AND "+
						" EXME_CtaPac.EXME_Paciente_ID = EXME_Clasificacion.EXME_Paciente_ID AND "+
						" EXME_Clasificacion.EXME_ClasCliente_ID = EXME_ClasCliente.EXME_ClasCliente_ID AND " +
						" EXME_ClasCliente.EXME_ClasCliente_ID = EXME_Plazos_Clas.EXME_ClasCliente_ID AND " +
						" ? between EXME_Plazos_Clas.Adeudo_Ini and EXME_Plazos_Clas.Adeudo_Fin ", 
                        null
				);
				pstmt.setInt(1,EXME_Paciente_ID.intValue());
				pstmt.setBigDecimal(2, saldo);
				rs = pstmt.executeQuery();
				if(rs.next()){
					Integer pago = rs.getInt(1);
					if ( pago == null)
						 pago = new Integer(1);
					mTab.setValue("Payments",pago);
				}

			} catch(Exception e) {
				log.log(Level.SEVERE, "socType", e);
				return e.getLocalizedMessage();
			} finally {
                DB.close(rs, pstmt);
                rs = null;
                pstmt = null;
            }
			return "";
	}

	public String dias(Properties ctx, GridTab mTab, Integer EXME_Paciente_ID){
		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0)
			return "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{			
//              Revisamos si existe una cuenta paciente.
                MEXMECtaPac[] ctasPac = MEXMECtaPac.getOfPaciente(ctx, EXME_Paciente_ID, MEXMECtaPac.ENCOUNTERSTATUS_Admission, null);
                BigDecimal saldo = Env.ZERO;
                if(ctasPac!= null && ctasPac.length>0){
 //                   saldo = ctasPac[0].getSaldo(); 
                }

				pstmt = DB.prepareStatement(
						" SELECT EXME_Plazos_Clas.dias " +
						" FROM EXME_Plazos_Clas, EXME_ClasCliente, EXME_CtaPac, EXME_Clasificacion " +
						" WHERE EXME_CtaPac.EXME_CtaPac_ID = ? AND "+
						" EXME_CtaPac.EXME_Paciente_ID = EXME_Clasificacion.EXME_Paciente_ID AND "+
						" EXME_Clasificacion.EXME_ClasCliente_ID = EXME_ClasCliente.EXME_ClasCliente_ID AND " +
						" EXME_ClasCliente.EXME_ClasCliente_ID = EXME_Plazos_Clas.EXME_ClasCliente_ID AND " +
						" ? between EXME_Plazos_Clas.Adeudo_Ini and EXME_Plazos_Clas.Adeudo_Fin ",
                        null
				);

				pstmt.setInt(1,EXME_Paciente_ID.intValue());
				pstmt.setBigDecimal(2, saldo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					Integer dia = rs.getInt(1);
					if ( dia == null)
						 dia = new Integer(1);

					mTab.setValue("Dias",dia);
				}

			} catch(Exception e) {
				log.log(Level.SEVERE, "socType", e);
				return e.getLocalizedMessage();
			} finally {
				DB.close(rs, pstmt);
                rs = null;
                pstmt = null;
            }

			return "";
	}

	public String clasification(Properties ctx, GridTab mTab, Integer EXME_Paciente_ID){
		if (EXME_Paciente_ID == null || EXME_Paciente_ID.intValue() == 0)
			return "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
				pstmt = DB.prepareStatement(
						" SELECT exme_clasificacion.exme_clasificacion_id " +
						" FROM exme_clasificacion, exme_ctapac " +
						" WHERE exme_ctapac.exme_ctapac_id = ? AND " +
						" exme_ctapac.exme_paciente_id = exme_clasificacion.exme_paciente_id " +
                        " AND exme_ctapac.isActive = 'Y' ", null//.-Lama
				);

				pstmt.setInt(1,EXME_Paciente_ID.intValue());
				rs = pstmt.executeQuery();
				if(rs.next()){
					mTab.setValue("EXME_Clasificacion_ID", new Integer(rs.getInt(1)));
                }
        } catch(Exception e) {
				log.log(Level.SEVERE, "socType", e);
				return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        
		return "";
	}

	public int ctaPacID(Integer EXME_Paciente_ID){
		int id = 0;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(
					" SELECT EXME_CtaPac.EXME_CtaPAc_ID " +
					" FROM EXME_CtaPac " +
					" WHERE EXME_CtaPac.EXME_Paciente_ID=? AND "+
					" EXME_CtaPac.IsActive = 'Y' AND "+
					" EXME_CtaPAc.EncounterStatus=?", null);
			pstmt.setInt(1,EXME_Paciente_ID.intValue());
			pstmt.setString(2, MEXMECtaPac.ENCOUNTERSTATUS_Admission);
			rs = pstmt.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
		} catch(Exception e){
			log.log(Level.SEVERE, "socType", e);
		} finally {
			DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

		return id;
	}
}