package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.mo.MO_Radiografias_VO;

/**
 * Creado:15/Junio/2009<p>
 * @author Lizeth de la Garza
 */
public class MEXMERadiografiaDet extends X_EXME_MO_RadiografiaDet {

	private static CLogger		slog = CLogger.getCLogger (MEXMERadiografiaDet.class);
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public MEXMERadiografiaDet(Properties ctx, int EXME_MO_RadiografiaDet_ID, String trxName) {
		super(ctx, EXME_MO_RadiografiaDet_ID, trxName);

	}

	 public  MEXMERadiografiaDet(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }

	  /**
		 * Liz de la Garza
		 * Insertar el detalle de las radiograf�as (relaci�n radiograf�a-tratamiento)
		 * @param Properties ctx
		 * @param int radiografiaID
		 * @param String trxName
		 * @param int[] tratamientosPac
		 * @return ActionErrors errores
		 */
	 public static ActionErrors saveRadiografiasDet(Properties ctx, int radiografiaID, String trxName, int[] tratamientosPac) throws Exception {

		 ActionErrors errores = new ActionErrors();

		 try {
			 //Guardar la relacion entre radiografia y tratamientopacienteid en la tabla
			 for (int i = 0; i < tratamientosPac.length; i++) {

				 MEXMERadiografiaDet radio = new MEXMERadiografiaDet(ctx, 0, null);
				 radio.setEXME_MO_Radiografias_ID(radiografiaID);
				 radio.setEXME_TratamientosPaciente_ID(tratamientosPac[i]);
				 if (!radio.save(trxName)) {
					 DB.rollback(false, trxName);
					 throw new SQLException("odontologia.error.GuardarRadiografia");
				 }
			 }
		 }
		 catch (Exception e) {
			 errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("odontologia.error.GuardarRadiografia"));
			 slog.log(Level.SEVERE, "Error", e);
		 }
		 return errores;

	 }

	  /**
		 * Liz de la Garza
		 * Ordenar las radiograf�as del paciente
		 * @param Properties ctx
		 * @param List<MO_Radiografias_VO> lstAuxiliar
		 * @return List<MO_Radiografias_VO>
		 */
	 public static List<MO_Radiografias_VO> listaRadiografias(Properties ctx, List<MO_Radiografias_VO> lstAuxiliar) {
		 List<MO_Radiografias_VO> listas = new ArrayList<MO_Radiografias_VO>();

		 int radioID = 0;
		 String nombreRadio = null;
		 String nota = null;
		 String descrip = null;
		 String fecha = null;
		 int tratamID = 0;
		 MO_Radiografias_VO obj = new MO_Radiografias_VO();
		 //Lista de las radiografias asignadas al paciente

		 for (int i = 0; i < lstAuxiliar.size(); i++) {
			 MO_Radiografias_VO object = new MO_Radiografias_VO();
			 if (i == 0) {
				 radioID = lstAuxiliar.get(i).getRadioID();
				 nombreRadio = lstAuxiliar.get(i).getNombreRadio();
				 nota = lstAuxiliar.get(i).getNota();
				 descrip = lstAuxiliar.get(i).getDescriptionTratam();
				 fecha = lstAuxiliar.get(i).getFecha();
				 tratamID = lstAuxiliar.get(i).getTratamientoID();
				 //Si la radiografia tiene relacionada un tratamiento
				 if (lstAuxiliar.get(i).getTratamPacID() != 0) {
					 object.setNombreTratam(lstAuxiliar.get(i).getNombreTratam());
					 obj.getTratamientoPac().add(object);
				 }
			 } //Que coincidan el id de la radiografia, el nombre y su nota para obtener el nombre de los tratamientos relacionados
			 if (lstAuxiliar.get(i).getRadioID() == radioID
					 && lstAuxiliar.get(i).getNombreRadio().equals(nombreRadio) && lstAuxiliar.get(i).getNota().equals(nota)) {

				 obj.setRadioID(lstAuxiliar.get(i).getRadioID());
				 obj.setNombreRadio(lstAuxiliar.get(i).getNombreRadio());
				 obj.setNota(lstAuxiliar.get(i).getNota());
				 if (lstAuxiliar.get(i).getTratamPacID() !=0 ) { //Que coincidan la descripcion, fecha y id del tratamiento del paciente
					 if (!(lstAuxiliar.get(i).getDescriptionTratam().equals(descrip)
							 && lstAuxiliar.get(i).getFecha().equals(fecha) && lstAuxiliar.get(i).getTratamientoID() == tratamID)) {
						 object.setNombreTratam(lstAuxiliar.get(i).getNombreTratam());
						 obj.getTratamientoPac().add(object);
						 descrip = lstAuxiliar.get(i).getDescriptionTratam();
						 fecha = lstAuxiliar.get(i).getFecha();
						 tratamID = lstAuxiliar.get(i).getTratamientoID();
						 continue;

					 }


				 }
				 if (i == (lstAuxiliar.size() - 1))
					 listas.add(obj);

			 } else { //Pasamos a la informacion de la siguiente radiografia
				 listas.add(obj);
				 obj = new MO_Radiografias_VO();
				 if (lstAuxiliar.get(i).getTratamPacID() != 0) {
					 object.setNombreTratam(lstAuxiliar.get(i).getNombreTratam());
					 obj.getTratamientoPac().add(object);

				 }
				 obj.setRadioID(lstAuxiliar.get(i).getRadioID());
				 obj.setNombreRadio(lstAuxiliar.get(i).getNombreRadio());
				 obj.setNota(lstAuxiliar.get(i).getNota());

				 descrip = lstAuxiliar.get(i).getDescriptionTratam();
				 fecha = lstAuxiliar.get(i).getFecha();
				 tratamID = lstAuxiliar.get(i).getTratamientoID();
				 radioID = lstAuxiliar.get(i).getRadioID();
				 nombreRadio = lstAuxiliar.get(i).getNombreRadio();
				 nota = lstAuxiliar.get(i).getNota();
				 if (i == (lstAuxiliar.size() - 1))
					 listas.add(obj);
			 }

		 }

		 return listas;
	 }

}
