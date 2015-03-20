/*
 * Created on 5/05/2005
 */ package org.compiere.model; import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
/**
 * Modelo de Turnos
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2005/05/07 01:14:03 $<p>
 *
 * @author Twry Perez
 * @version $Revision: 1.2 $
 */
public class MEXMETurnos extends X_EXME_Turnos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMETurnos.class);
	
    /**
    * @param ctx
     * @param EXME_Turnos_ID
     * @param trxName
     */
    public MEXMETurnos(Properties ctx, int EXME_Turnos_ID, String trxName) {
        super(ctx, EXME_Turnos_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMETurnos(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord) {
		String errMsg = "";
		
		double ent1 = 0;
		if(getHoraEnt1Es()!=null && !getHoraEnt1Es().equals("")) {
		    try {
		        ent1 = Double.parseDouble(getHoraEnt1Es().replaceAll("\\:", "."));
		        if(ent1<0 || ent1>23.59) {
		            errMsg += "El valor de la hr. de entrada 1 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de entrada 1 no es valida (hh:mm)\n";
		    }
		}

		double ent2 = 0;
		if(getHoraEnt2Es()!=null && !getHoraEnt2Es().equals("")) {
		    try {
		        ent2 = Double.parseDouble(getHoraEnt2Es().replaceAll("\\:", "."));
		        if(ent2<0 || ent2>23.59) {
		            errMsg += "El valor de la hr. de entrada 2 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de entrada 2 no es valida (hh:mm)\n";
		    }
		}

		double ent3 = 0;
		if(getHoraEnt3Es()!=null && !getHoraEnt3Es().equals("")) {
		    try {
		        ent3 = Double.parseDouble(getHoraEnt3Es().replaceAll("\\:", "."));
		        if(ent3<0 || ent3>23.59) {
		            errMsg += "El valor de la hr. de entrada 3 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de entrada 3 no es valida (hh:mm)\n";
		    }
		}

		double sal1 = 0;
		if(getHoraSal1Es()!=null && !getHoraSal1Es().equals("")) {
		    try {
		        sal1 = Double.parseDouble(getHoraSal1Es().replaceAll("\\:", "."));
		        if(sal1<0 || sal1>23.59) {
		            errMsg += "El valor de la hr. de salida 1 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de salida 1 no es valida (hh:mm)\n";
		    }
		}

		double sal2 = 0;
		if(getHoraSal2Es()!=null&& !getHoraSal2Es().equals("")) {
		    try {
		        sal2 = Double.parseDouble(getHoraSal2Es().replaceAll("\\:", "."));
		        if(sal2<0 || sal2>23.59) {
		            errMsg += "El valor de la hr. de salida 2 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de salida 2 no es valida (hh:mm)\n";
		    }
		}

		double sal3 = 0;
		if(getHoraSal3Es()!=null&& !getHoraSal3Es().equals("")) {
		    try {
		        sal3 = Double.parseDouble(getHoraSal3Es().replaceAll("\\:", "."));
		        if(sal3<0 || sal3>23.59) {
		            errMsg += "El valor de la hr. de salida 3 debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de salida 3 no es valida (hh:mm)\n";
		    }
		}

		double entf = 0;
		if(getHoraEnt1Fs()!=null&& !getHoraEnt1Fs().equals("")) {
		    try {
		        entf = Double.parseDouble(getHoraEnt1Fs().replaceAll("\\:", "."));
		        if(entf<0 || entf>23.59) {
		            errMsg += "El valor de la hr. de de entrada de fin de semana debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de entrada de fin de semana no es valida (hh:mm)\n";
		    }
		}
		
		double salf = 0;
		if(getHoraSal1Fs()!=null&& !getHoraSal1Fs().equals("")) {
		    try {
		        salf = Double.parseDouble(getHoraSal1Fs().replaceAll("\\:", "."));
		        if(salf<0 || salf>23.59) {
		            errMsg += "El valor de la hr. de de entrada de fin de semana debe estar entre 0 y 23:59\n";
		        }
		    } catch (Exception e) {
		        errMsg += "La hora de entrada de fin de semana no es valida (hh:mm)\n";
		    }
		}
		
		//si no se pudo validar el formato de las horas
		if (errMsg.length() > 0) {
			log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
			return false;
		}

		//validar que la hora inicial sea menor a la final
		if((ent1!=0||sal1!=0) && ent1>=sal1) {
		    errMsg += "La hora de salida 1 debe ser mayor a la hora de entrada 1\n";
		}
		
		if((ent2!=0||sal2!=0) && ent2>=sal2) {
		    errMsg += "La hora de salida 2 debe ser mayor a la hora de entrada 2\n";
		}
		
		if((ent3!=0||sal3!=0) && ent3>=sal3) {
		    errMsg += "La hora de salida 3 debe ser mayor a la hora de entrada 3\n";
		}
		
		//si no se pudo validar el rango de los horarios
		if (errMsg.length() > 0) {
			log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
			return false;
		}

		//validar que los turnos no se traslapen
		if((ent2!=0||sal2!=0)) {
		    if(ent2>=ent1 && ent2<sal1){
		    	errMsg += "El horario 2 se traslapa con el horario 1\n";
		    }
		}
		
		if(ent3!=0||sal3!=0) {
		    if(ent3>=ent2 || ent3<sal2) {
		        errMsg += "El horario 3 se traslapa con el horario 2\n";
		    }
		}
		
		if (errMsg.length() > 0) {
			log.saveError("Error", Msg.parseTranslation(getCtx(), errMsg));
			return false;
		}

		return true;
	}	//	beforeSave 
	/**
	 * Metodo para validar si un turno ya existe
	 * @param ctx
	 * @param name
	 * @return
	 */
	public static boolean turnoExistente(Properties ctx, String name, String trxName){
		boolean existente = false;
		
		StringBuilder sql = new StringBuilder("select * from exme_turnos where ");
		sql.append("name = ? ");
		sql.append("and AD_Client_ID = ? " );
		sql.append("and ad_org_Id = ? " );
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			
			if(ctx.get("#AD_Client_ID") != null){
				pstmt.setInt(2, Integer.parseInt(ctx.get("#AD_Client_ID").toString()));
			}else{
				return false;
			}
			
			if(ctx.get("#AD_Org_ID") != null){
				pstmt.setInt(3, Integer.parseInt(ctx.get("#AD_Org_ID").toString()));
			}else{
				return false;
			}
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				existente =true;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return existente;
	}
	
	public static List<MEXMETurnos> getTurnosByOrg(int ad_org_id){
		List<MEXMETurnos> lista = new ArrayList<MEXMETurnos>();
		StringBuilder st = new StringBuilder("select * from exme_turnos where ad_org_id = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMETurnos turno = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, ad_org_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				turno = new MEXMETurnos(Env.getCtx(), rs, null);
				lista.add(turno);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
		
	}
	
	public static int [] getTurnoDetalle(int turnoID){
		int [] arr = new int [2];
		StringBuilder st = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		st.append(" select (case when (horaent1es is null) then  ");
		st.append(" (case when (horaent2es is null) then (case when (horaent3es is null) then '07' else SUBSTR(");
		
		if (DB.isOracle()) {
			st.append("to_char(horaent3es),0,2) ");
			st.append(" end) else SUBSTR(to_char(horaent2es),0,2) end) else SUBSTR(to_char(horaent1es),0,2) end) as HE, ");
			st.append(" (case when (horasal3es is null) then (case when (horasal2es is null) then (case when (horasal1es is null) then '19' ");
			st.append(" else SUBSTR(to_char(horasal1es),0,2) end) else SUBSTR(to_char(horasal2es),0,2) end) else SUBSTR(to_char(horasal3es),0,2) end)  as HS ");
			
		} else if (DB.isPostgreSQL()) {
			// Quitar el TO_CHAR ya que no se necesita particularmente en esta funcion
			// y el substring cambiarlo para que tome de la posicion 1 y que de ahi me traiga
			// 2 caracteres. No es como en Oracle que empieza de la posicion 0. Jesus Cantu.
			st.append("(horaent3es),1,2) ");
			st.append(" end) else SUBSTR((horaent2es),1,2) end) else SUBSTR((horaent1es),1,2) end) as HE, ");
			st.append(" (case when (horasal3es is null) then (case when (horasal2es is null) then (case when (horasal1es is null) then '19' ");
			st.append(" else SUBSTR((horasal1es),1,2) end) else SUBSTR((horasal2es),1,2) end) else SUBSTR((horasal3es),1,2) end)  as HS ");
		}
		
		st.append(" from exme_turnos where exme_turnos_id = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, turnoID);
			rs = pstmt.executeQuery();
			
			int he = 6;
			int hf = 20;
			
			if (rs.next()) {
				if(rs.getString(1).length() == 2){
					he = Integer.parseInt(rs.getString(1));
				}
				if(rs.getString(2).length() == 2){
					hf = Integer.parseInt(rs.getString(2));
				}
			}
			
			arr[0] = he;
			arr[1] = hf;
			
		} catch (Exception e) {
			log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return arr;
		
	}
} 