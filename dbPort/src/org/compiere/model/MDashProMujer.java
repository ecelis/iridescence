package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Proyecto Pro Mujer
 * http://control.ecaresoft.com/epic/82/
 * Transferencia de información entre NIMBO (eCareSoft) y FIN+
 * 
 * @author abautista
 * Modificado por Lama, Feb 2015
 */
public class MDashProMujer extends X_EXME_DashProMujer {

	public static CLogger		s_log				= CLogger.getCLogger(MDashProMujer.class);
	private static final long	serialVersionUID	= 2129332483417727510L;

	public static final String	ERROR				= "ERROR";
	public static final String	OK					= "OK";

	public static final String	TRANSACTION_01		= "T01";
	public static final String	TRANSACTION_02		= "T02";
	public static final String	TRANSACTION_03		= "T03";
	public static final String	TRANSACTION_04		= "T04";

	public MDashProMujer(final Properties ctx, final int EXME_DashProMujer_ID, final String trxName) {
		super(ctx, EXME_DashProMujer_ID, trxName);
	}

	/**
	 * Inicializa un nuevo registro por pais, asigna el usuario del conexto
	 * 
	 * @param ctx
	 * @param country si es null toma el pais del contexto
	 * @param trxName
	 */
	public MDashProMujer(final Properties ctx, final MCountry country, final String trxName) {
		super(ctx, 0, trxName);
		setAD_User_ID(Env.getAD_User_ID(ctx));
		setC_Country_ID(country == null ? Env.getC_Country_ID(ctx) : country.getC_Country_ID());
		setProcessing(true);
	}

	public MDashProMujer(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

}
