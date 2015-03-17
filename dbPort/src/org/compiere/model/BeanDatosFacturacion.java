package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class BeanDatosFacturacion extends MLocation {

	/** serialVersionUID */
	private static final long serialVersionUID = -8187535599052275909L;
	/** Rfc */
	private String rfc = null;
	/** nombre */
	private String name = null;
	/** id socio */
	private int C_BPartner_ID = 0;
	
	/** Constructor */
	public BeanDatosFacturacion(Properties ctx, int C_Country_ID, int C_Region_ID, String city, String trxName) {
		super(ctx, C_Country_ID, C_Region_ID, city, trxName);
	}
	/** Constructor */
	public BeanDatosFacturacion(Properties ctx, int C_Location_ID, String trxName) {
		super(ctx, C_Location_ID, trxName);
	}
	/** Constructor */
	public BeanDatosFacturacion(MCountry country, MRegion region) {
		super(country, region);
	}
	/** Constructor */
	public BeanDatosFacturacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getC_BPartner_ID() {
		return C_BPartner_ID;
	}

	public void setC_BPartner_ID(int c_BPartner_ID) {
		C_BPartner_ID = c_BPartner_ID;
	}
}
