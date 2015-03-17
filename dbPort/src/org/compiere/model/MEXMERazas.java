package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMERazas extends X_EXME_Razas implements Selectable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1408494186677529512L;
	/**
	 * Log
	 */
	@SuppressWarnings("unused")
	private static CLogger slog = CLogger.getCLogger (MEXMERazas.class);
	public MEXMERazas(Properties ctx, int EXME_Razas_ID, String trxName) {
		super(ctx, EXME_Razas_ID, trxName);
	}
	
	
	public MEXMERazas(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	/**
	 * Regresa una lista de todos los registros guardados en EXME_Razas
	 * que esten activos
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERazas> getAll(Properties ctx, String trxName) {
		final List<MEXMERazas> retValue = new Query(ctx, Table_Name, "", trxName)//
				.setOnlyActiveRecords(true)//
				// .addAccessLevelSQL(true)//
				.setOrderBy("EXME_Razas.Name")//
				.list();
		return retValue;
	}
	
	/**
	 * Obtener el valor de raza que se tenga por defecto en DB.
	 * @param ctx
	 * @return
	 */
	public static MEXMERazas getDefaultRace(Properties ctx){
		MEXMERazas race =  new Query(ctx, Table_Name, " isDefault = 'Y'", null)//
		.setOnlyActiveRecords(true)
		.first();
		
		if(race == null){
			slog.warning("Cannot find a default value (isDefault = 'Y') for in Exme_Razas");
			race =  new Query(ctx, Table_Name, " exme_razas_id = 1000005", null)//
			.setOnlyActiveRecords(true)
			.first();
		}
		return race;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int getId() {
		return get_ID();
	}

	private boolean isDefault = false;
	private boolean selected = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.Selectable#isDefault()
	 */
	@Override
	public boolean isDefault() {
		return isDefault;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.Selectable#isSelected()
	 */
	@Override
	public boolean isSelected() {
		return selected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.Selectable#setDefault(boolean)
	 */
	@Override
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.model.Selectable#setSelected(boolean)
	 */
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
