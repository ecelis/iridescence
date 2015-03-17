/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.util.logging.Level;
import java.sql.*;
import java.lang.reflect.Constructor;
import java.math.*;
import org.compiere.util.*;

public class X_EXME_T_Cuest extends SimplePO {
	/**
	 * Standard Constructor
	 * 
	 * @param ctx context
	 * @param EXME_T_Cuest_ID id
	 * @param trxName transaction
	 */
	public X_EXME_T_Cuest(Properties ctx, String trxName) {
		super(ctx, trxName);

	}

	/**
	 * Load Constructor
	 * 
	 * @param ctx context
	 * @param rs result set
	 * @param trxName transaction
	 */
	public X_EXME_T_Cuest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/** AD_Table_ID=1200211 */
	public static final int Table_ID = 1200211;

	/** TableName=EXME_T_Cuest */
	public static final String Table_Name = "EXME_T_Cuest";

	protected static KeyNamePair Model = new KeyNamePair(1200211, "EXME_T_Cuest");

	protected BigDecimal accessLevel = new BigDecimal(3);

	/**
	 * AccessLevel
	 * 
	 * @return 3 - Client - Org
	 */
	protected int get_AccessLevel() {
		return accessLevel.intValue();
	}

	/**
	 * Load Meta Data
	 * 
	 * @param ctx context
	 * @return PO Info
	 */
	protected POInfo initPO(Properties ctx) {
		POInfo poi = POInfo.getPOInfo(ctx, Table_ID);
		return poi;
	}

	/**
	 * Info
	 * 
	 * @return info
	 */
	/*
	 * public String toString()
	 * {
	 * StringBuffer sb = new StringBuffer ("X_EXME_T_Cuest[").append(get_ID()).append("]");
	 * return sb.toString();
	 * }
	 */
	/**
	 * Set Confidential.
	 * 
	 * @param Confidencial Indicates the level of confidenciality
	 */
	public void setConfidencial(String Confidencial) {
		if (Confidencial == null)
			throw new IllegalArgumentException("Confidencial is mandatory.");
		if (Confidencial.length() > 1) {
			log.warning("Length > 1 - truncated");
			Confidencial = Confidencial.substring(0, 0);
		}
		set_Value("Confidencial", Confidencial);
	}

	/**
	 * Get Confidential.
	 * 
	 * @return Indicates the level of confidenciality
	 */
	public String getConfidencial() {
		return (String) get_Value("Confidencial");
	}

	/**
	 * Set Description.
	 * 
	 * @param Description Optional short description of the record
	 */
	public void setDescription(String Description) {
		if (Description != null && Description.length() > 2000) {
			log.warning("Length > 2000 - truncated");
			Description = Description.substring(0, 1999);
		}
		set_Value("Description", Description);
	}

	/**
	 * Get Description.
	 * 
	 * @return Optional short description of the record
	 */
	public String getDescription() {
		return (String) get_Value("Description");
	}

	/**
	 * Set EsNotaMedica.
	 * 
	 * @param EsNotaMedica EsNotaMedica
	 */
	public void setEsNotaMedica(boolean EsNotaMedica) {
		set_Value("EsNotaMedica", new Boolean(EsNotaMedica));
	}

	/**
	 * Get EsNotaMedica.
	 * 
	 * @return EsNotaMedica
	 */
	public boolean isEsNotaMedica() {
		Object oo = get_Value("EsNotaMedica");
		if (oo != null) {
			if (oo instanceof Boolean)
				return ((Boolean) oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/**
	 * Set Medical Appointment.
	 * 
	 * @param EXME_CitaMedica_ID Medical appointment
	 */
	public void setEXME_CitaMedica_ID(int EXME_CitaMedica_ID) {
		if (EXME_CitaMedica_ID <= 0)
			set_Value("EXME_CitaMedica_ID", null);
		else
			set_Value("EXME_CitaMedica_ID", new Integer(EXME_CitaMedica_ID));
	}

	/**
	 * Get Medical Appointment.
	 * 
	 * @return Medical appointment
	 */
	public int getEXME_CitaMedica_ID() {
		Integer ii = (Integer) get_Value("EXME_CitaMedica_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Questionnaire.
	 * 
	 * @param EXME_Cuestionario_ID Questionnaire
	 */
	public void setEXME_Cuestionario_ID(int EXME_Cuestionario_ID) {
		if (EXME_Cuestionario_ID <= 0)
			set_Value("EXME_Cuestionario_ID", null);
		else
			set_Value("EXME_Cuestionario_ID", new Integer(EXME_Cuestionario_ID));
	}

	/**
	 * Get Questionnaire.
	 * 
	 * @return Questionnaire
	 */
	public int getEXME_Cuestionario_ID() {
		Integer ii = (Integer) get_Value("EXME_Cuestionario_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Infirmary staff.
	 * 
	 * @param EXME_Enfermeria_ID Infirmary staff
	 */
	public void setEXME_Enfermeria_ID(int EXME_Enfermeria_ID) {
		if (EXME_Enfermeria_ID <= 0)
			set_Value("EXME_Enfermeria_ID", null);
		else
			set_Value("EXME_Enfermeria_ID", new Integer(EXME_Enfermeria_ID));
	}

	/**
	 * Get Infirmary staff.
	 * 
	 * @return Infirmary staff
	 */
	public int getEXME_Enfermeria_ID() {
		Integer ii = (Integer) get_Value("EXME_Enfermeria_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Service Station Id.
	 * 
	 * @param EXME_EstServ_ID Service Station Id
	 */
	public void setEXME_EstServ_ID(int EXME_EstServ_ID) {
		if (EXME_EstServ_ID <= 0)
			set_Value("EXME_EstServ_ID", null);
		else
			set_Value("EXME_EstServ_ID", new Integer(EXME_EstServ_ID));
	}

	/**
	 * Get Service Station Id.
	 * 
	 * @return Service Station Id
	 */
	public int getEXME_EstServ_ID() {
		Integer ii = (Integer) get_Value("EXME_EstServ_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Doctor.
	 * 
	 * @param EXME_Medico_ID Doctor
	 */
	public void setEXME_Medico_ID(int EXME_Medico_ID) {
		if (EXME_Medico_ID <= 0)
			set_Value("EXME_Medico_ID", null);
		else
			set_Value("EXME_Medico_ID", new Integer(EXME_Medico_ID));
	}

	/**
	 * Get Doctor.
	 * 
	 * @return Doctor
	 */
	public int getEXME_Medico_ID() {
		Integer ii = (Integer) get_Value("EXME_Medico_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Patient.
	 * 
	 * @param EXME_Paciente_ID Patient
	 */
	public void setEXME_Paciente_ID(int EXME_Paciente_ID) {
		if (EXME_Paciente_ID <= 0)
			set_Value("EXME_Paciente_ID", null);
		else
			set_Value("EXME_Paciente_ID", new Integer(EXME_Paciente_ID));
	}

	/**
	 * Get Patient.
	 * 
	 * @return Patient
	 */
	public int getEXME_Paciente_ID() {
		Integer ii = (Integer) get_Value("EXME_Paciente_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Question.
	 * 
	 * @param EXME_Pregunta_ID Question
	 */
	public void setEXME_Pregunta_ID(int EXME_Pregunta_ID) {
		if (EXME_Pregunta_ID < 1)
			throw new IllegalArgumentException("EXME_Pregunta_ID is mandatory.");
		set_Value("EXME_Pregunta_ID", new Integer(EXME_Pregunta_ID));
	}

	/**
	 * Get Question.
	 * 
	 * @return Question
	 */
	public int getEXME_Pregunta_ID() {
		Integer ii = (Integer) get_Value("EXME_Pregunta_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Questionnaire.
	 * 
	 * @param EXME_T_Cuest_ID Questionnaire
	 */
	/*
	 * public void setEXME_T_Cuest_ID (int EXME_T_Cuest_ID)
	 * {
	 * if (EXME_T_Cuest_ID < 1) throw new IllegalArgumentException ("EXME_T_Cuest_ID is mandatory.");
	 * set_ValueNoCheck ("EXME_T_Cuest_ID", new Integer(EXME_T_Cuest_ID));
	 * }
	 */
	/**
	 * Get Questionnaire.
	 * 
	 * @return Questionnaire
	 */
	public int getEXME_T_Cuest_ID() {
		Integer ii = (Integer) get_Value("EXME_T_Cuest_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Date.
	 * 
	 * @param Fecha Date
	 */
	public void setFecha(Timestamp Fecha) {
		if (Fecha == null)
			throw new IllegalArgumentException("Fecha is mandatory.");
		set_Value("Fecha", Fecha);
	}

	/**
	 * Get Date.
	 * 
	 * @return Date
	 */
	public Timestamp getFecha() {
		return (Timestamp) get_Value("Fecha");
	}

	/**
	 * Set Folio.
	 * 
	 * @param Folio Folio
	 */
	public void setFolio(int Folio) {
		set_Value("Folio", new Integer(Folio));
	}

	/**
	 * Get Folio.
	 * 
	 * @return Folio
	 */
	public int getFolio() {
		Integer ii = (Integer) get_Value("Folio");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Imagen Binario.
	 * 
	 * @param ImagenBinary Imagen Binario
	 */
	public void setImagenBinary(byte[] ImagenBinary) {
		set_Value("ImagenBinary", ImagenBinary);
	}

	/**
	 * Get Imagen Binario.
	 * 
	 * @return Imagen Binario
	 */
	public byte[] getImagenBinary() {
		return (byte[]) get_Value("ImagenBinary");
	}

	/**
	 * Set Question's Fixed Answer Value.
	 * 
	 * @param Pregunta_Lista_Value Question's Fixed Answer Value
	 */
	public void setPregunta_Lista_Value(String Pregunta_Lista_Value) {
		if (Pregunta_Lista_Value != null && Pregunta_Lista_Value.length() > 90) {
			log.warning("Length > 90 - truncated");
			Pregunta_Lista_Value = Pregunta_Lista_Value.substring(0, 89);
		}
		set_Value("Pregunta_Lista_Value", Pregunta_Lista_Value);
	}

	/**
	 * Get Question's Fixed Answer Value.
	 * 
	 * @return Question's Fixed Answer Value
	 */
	public String getPregunta_Lista_Value() {
		return (String) get_Value("Pregunta_Lista_Value");
	}

	/** Set REF_EXME_PREGUNTA_ID.
	@param REF_EXME_PREGUNTA_ID REF_EXME_PREGUNTA_ID	  */
	public void setREF_EXME_PREGUNTA_ID (int REF_EXME_PREGUNTA_ID)
	{
		if (REF_EXME_PREGUNTA_ID < 1) 
			set_Value ("REF_EXME_PREGUNTA_ID", null);
		else 
			set_Value ("REF_EXME_PREGUNTA_ID", Integer.valueOf(REF_EXME_PREGUNTA_ID));
	}

	/** Get REF_EXME_PREGUNTA_ID.
		@return REF_EXME_PREGUNTA_ID	  */
	public int getREF_EXME_PREGUNTA_ID () 
	{
		Integer ii = (Integer)get_Value("REF_EXME_PREGUNTA_ID");
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	/**
	 * Set Answer.
	 * 
	 * @param Respuesta Answer
	 */
	public void setRespuesta(String Respuesta) {
		if (Respuesta != null && Respuesta.length() > 2000) {
			log.warning("Length > 2000 - truncated");
			Respuesta = Respuesta.substring(0, 1999);
		}
		set_Value("Respuesta", Respuesta);
	}

	/**
	 * Get Answer.
	 * 
	 * @return Answer
	 */
	public String getRespuesta() {
		return (String) get_Value("Respuesta");
	}

	/**
	 * Set Image Route.
	 * 
	 * @param RutaImagen Image Route
	 */
	public void setRutaImagen(String RutaImagen) {
		if (RutaImagen != null && RutaImagen.length() > 120) {
			log.warning("Length > 120 - truncated");
			RutaImagen = RutaImagen.substring(0, 119);
		}
		set_Value("RutaImagen", RutaImagen);
	}

	/**
	 * Get Image Route.
	 * 
	 * @return Image Route
	 */
	public String getRutaImagen() {
		return (String) get_Value("RutaImagen");
	}

	/**
	 * Set Sequence.
	 * 
	 * @param Secuencia Sequence
	 */
	public void setSecuencia(BigDecimal Secuencia) {
		if (Secuencia == null)
			throw new IllegalArgumentException("Secuencia is mandatory.");
		set_Value("Secuencia", Secuencia);
	}

	/**
	 * Get Sequence.
	 * 
	 * @return Sequence
	 */
	public BigDecimal getSecuencia() {
		BigDecimal bd = (BigDecimal) get_Value("Secuencia");
		if (bd == null)
			return Env.ZERO;
		return bd;
	}

	/**
	 * Set TextoBinario.
	 * 
	 * @param TextBinary TextoBinario
	 */
	public void setTextBinary(String TextBinary) {
		set_Value("TextBinary", TextBinary);
	}

	/**
	 * Get TextoBinario.
	 * 
	 * @return TextoBinario
	 */
	public String getTextBinary() {
		return (String) get_Value("TextBinary");
	}

	public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	 */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	 */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}

	  /** Column name EXME_ProgQuiro_ID */
    public static final String COLUMNNAME_EXME_ProgQuiro_ID = "EXME_ProgQuiro_ID";

	/** Set Schedule of Surgery Room.
	@param EXME_ProgQuiro_ID 
	Schedule of Surgery Room
	 */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
	}

	/** Get Schedule of Surgery Room.
	@return Schedule of Surgery Room
	 */
	public int getEXME_ProgQuiro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgQuiro_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";
    
	/** DocStatus AD_Reference_ID=1200577 */
	public static final int DOCSTATUS_AD_Reference_ID=1200577;
	/** None = 0 */
	public static final String DOCSTATUS_None = "0";
	/** Pending = 1 */
	public static final String DOCSTATUS_Pending = "1";
	/** Active = 3 */
	public static final String DOCSTATUS_Active = "3";
	/** Closed = 4 */
	public static final String DOCSTATUS_Closed = "4";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		if (DocStatus == null || DocStatus.equals("0") || DocStatus.equals("1") || DocStatus.equals("3") || DocStatus.equals("4")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=1200577 - 0 - 1 - 3 - 4");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}
	
	/** Column name FileContent */
    public static final String COLUMNNAME_FileContent = "FileContent";
	
	/**
	 * Set File Content.
	 * 
	 * @param FileContent
	 *            File Content
	 */
	public void setFileContent(byte[] FileContent) {
		set_Value(COLUMNNAME_FileContent, FileContent);
	}

	/**
	 * Get File Content.
	 * 
	 * @return File Content
	 */
	public byte[] getFileContent() {
		return (byte[]) get_Value(COLUMNNAME_FileContent);
	}

	/** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set.
	@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
	@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
		if (ii == null)
			return 0;
		return ii.intValue();
	}
}