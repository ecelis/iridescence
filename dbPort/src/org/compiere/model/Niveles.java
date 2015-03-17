/**
 * 
 */
package org.compiere.model;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * @author twry
 * 
 */
public class Niveles {

	private List<NivelBean> lstNiveles = new ArrayList<NivelBean>();

	public List<NivelBean> getLstNiveles() {
		return lstNiveles;
	}

	public void setLstNiveles(List<NivelBean> lstNiveles) {
		this.lstNiveles = lstNiveles;
	}

	/**
	 * Niveles
	 */
	public Niveles() {
		super();

		lstNiveles.add(new NivelBean(14, Utilerias
				.getLabel(" Todo el Presupuesto "),
				X_EXME_PresupuestoEgr.COLUMNNAME_EXME_PresupuestoEgr_ID,
				X_EXME_PresupuestoEgr.Table_Name));
		lstNiveles.add(new NivelBean(1, Utilerias
				.getLabel("msj.unidadResponsable"),
				X_AD_ClientInfo.COLUMNNAME_UnidadResponsable,
				X_AD_ClientInfo.Table_Name));
		lstNiveles.add(new NivelBean(2, Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_Finalidad),
				X_EXME_ClasFuncional.COLUMNNAME_EXME_ClasFuncional_ID,
				X_EXME_ClasFuncional.Table_Name));
		lstNiveles.add(new NivelBean(3, Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_Funcion),
				X_EXME_ClasFuncional_Fun.COLUMNNAME_EXME_ClasFuncional_Fun_ID,
				X_EXME_ClasFuncional_Fun.Table_Name));
		lstNiveles.add(new NivelBean(4, Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_Subfuncion),
				X_EXME_ClasFuncional_Sfu.COLUMNNAME_EXME_ClasFuncional_Sfu_ID,
				X_EXME_ClasFuncional_Sfu.Table_Name));
		lstNiveles.add(new NivelBean(5,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_Reasignacion_ID),
				X_EXME_Reasignacion.COLUMNNAME_EXME_Reasignacion_ID,
				X_EXME_Reasignacion.Table_Name));
		lstNiveles.add(new NivelBean(6,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_ActInstitucional_ID),
				X_EXME_ActInstitucional.COLUMNNAME_EXME_ActInstitucional_ID,
				X_EXME_ActInstitucional.Table_Name));
		lstNiveles.add(new NivelBean(7,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_ProgPresupuestal_ID),
				X_EXME_ProgPresupuestal.COLUMNNAME_EXME_ProgPresupuestal_ID,
				X_EXME_ProgPresupuestal.Table_Name));
		lstNiveles.add(new NivelBean(8,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_ProgInstitucional_ID),
				X_EXME_ProgInstitucional.COLUMNNAME_EXME_ProgInstitucional_ID,
				X_EXME_ProgInstitucional.Table_Name));
		lstNiveles.add(new NivelBean(9,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_PartidaPres_ID),
				X_EXME_PartidaPres.COLUMNNAME_EXME_PartidaPres_ID,
				X_EXME_PartidaPres.Table_Name));
		lstNiveles.add(new NivelBean(10,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_TipoGasto_ID),
				X_EXME_TipoGasto.COLUMNNAME_EXME_TipoGasto_ID,
				X_EXME_TipoGasto.Table_Name));
		lstNiveles.add(new NivelBean(11,Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_EXME_FteFinanciamiento_ID),
				X_EXME_FteFinanciamiento.COLUMNNAME_EXME_FteFinanciamiento_ID,
				X_EXME_FteFinanciamiento.Table_Name));
		lstNiveles.add(new NivelBean(12, Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_Entidad),
				X_C_Region.COLUMNNAME_Entidad, X_C_Region.Table_Name));
		lstNiveles.add(new NivelBean(13, Utilerias
				.getLabel(X_I_EXME_PresupuestoEgr.COLUMNNAME_C_Project_ID),
				X_C_Project.COLUMNNAME_C_Project_ID, X_C_Project.Table_Name));
	}

	public static Niveles niveles(){
		return new Niveles();
	}
	
	/**
	 * Opciones de consulta
	 * @return
	 */
	public static List<KeyNamePair> getListKeyNamePair() {
		final List<KeyNamePair> options = new ArrayList<KeyNamePair>();
		for (NivelBean bean: niveles().getLstNiveles()) {
			options.add(new KeyNamePair(bean.getNivel(), bean.getNombre()));
		}
		return options;
	}
		
}
