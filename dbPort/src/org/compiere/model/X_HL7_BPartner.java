/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for HL7_BPartner
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_BPartner extends PO implements I_HL7_BPartner, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_BPartner (Properties ctx, int HL7_BPartner_ID, String trxName)
    {
      super (ctx, HL7_BPartner_ID, trxName);
      /** if (HL7_BPartner_ID == 0)
        {
			setC_BPartner_ID (0);
			setConnectorType (null);
			setHL7_BPartner_ID (0);
			setReceivingApplication (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_BPartner (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HL7_BPartner[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_BPartner_ID()));
    }

	/** ConnectorType AD_Reference_ID=1200218 */
	public static final int CONNECTORTYPE_AD_Reference_ID=1200218;
	/** File = HL7_BPartnerFile */
	public static final String CONNECTORTYPE_File = "HL7_BPartnerFile";
	/** FTP = HL7_BPartnerFTP */
	public static final String CONNECTORTYPE_FTP = "HL7_BPartnerFTP";
	/** SFTP = HL7_BPartnerSFTP */
	public static final String CONNECTORTYPE_SFTP = "HL7_BPartnerSFTP";
	/** Email = HL7_BPartnerEmail */
	public static final String CONNECTORTYPE_Email = "HL7_BPartnerEmail";
	/** Samba = HL7_BPartnerSMB */
	public static final String CONNECTORTYPE_Samba = "HL7_BPartnerSMB";
	/** LLP = HL7_BPartnerLLP */
	public static final String CONNECTORTYPE_LLP = "HL7_BPartnerLLP";
	/** Javascript Connector = HL7_BPartnerJS */
	public static final String CONNECTORTYPE_JavascriptConnector = "HL7_BPartnerJS";
	/** Set Connector Type.
		@param ConnectorType 
		Idenfities the connection type
	  */
	public void setConnectorType (String ConnectorType)
	{
		if (ConnectorType == null) throw new IllegalArgumentException ("ConnectorType is mandatory");
		if (ConnectorType.equals("HL7_BPartnerFile") || ConnectorType.equals("HL7_BPartnerFTP") || ConnectorType.equals("HL7_BPartnerSFTP") || ConnectorType.equals("HL7_BPartnerEmail") || ConnectorType.equals("HL7_BPartnerSMB") || ConnectorType.equals("HL7_BPartnerLLP") || ConnectorType.equals("HL7_BPartnerJS")); else throw new IllegalArgumentException ("ConnectorType Invalid value - " + ConnectorType + " - Reference_ID=1200218 - HL7_BPartnerFile - HL7_BPartnerFTP - HL7_BPartnerSFTP - HL7_BPartnerEmail - HL7_BPartnerSMB - HL7_BPartnerLLP - HL7_BPartnerJS");		set_Value (COLUMNNAME_ConnectorType, ConnectorType);
	}

	/** Get Connector Type.
		@return Idenfities the connection type
	  */
	public String getConnectorType () 
	{
		return (String)get_Value(COLUMNNAME_ConnectorType);
	}

	/** Set HL7 Business Partner.
		@param HL7_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID)
	{
		if (HL7_BPartner_ID < 1)
			 throw new IllegalArgumentException ("HL7_BPartner_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_BPartner_ID, Integer.valueOf(HL7_BPartner_ID));
	}

	/** Get HL7 Business Partner.
		@return Identifies a Business Partner
	  */
	public int getHL7_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** InboundConnector AD_Reference_ID=1200218 */
	public static final int INBOUNDCONNECTOR_AD_Reference_ID=1200218;
	/** File = HL7_BPartnerFile */
	public static final String INBOUNDCONNECTOR_File = "HL7_BPartnerFile";
	/** FTP = HL7_BPartnerFTP */
	public static final String INBOUNDCONNECTOR_FTP = "HL7_BPartnerFTP";
	/** SFTP = HL7_BPartnerSFTP */
	public static final String INBOUNDCONNECTOR_SFTP = "HL7_BPartnerSFTP";
	/** Email = HL7_BPartnerEmail */
	public static final String INBOUNDCONNECTOR_Email = "HL7_BPartnerEmail";
	/** Samba = HL7_BPartnerSMB */
	public static final String INBOUNDCONNECTOR_Samba = "HL7_BPartnerSMB";
	/** LLP = HL7_BPartnerLLP */
	public static final String INBOUNDCONNECTOR_LLP = "HL7_BPartnerLLP";
	/** Javascript Connector = HL7_BPartnerJS */
	public static final String INBOUNDCONNECTOR_JavascriptConnector = "HL7_BPartnerJS";
	/** Set Inbound Connector.
		@param InboundConnector Inbound Connector	  */
	public void setInboundConnector (String InboundConnector)
	{

		if (InboundConnector == null || InboundConnector.equals("HL7_BPartnerFile") || InboundConnector.equals("HL7_BPartnerFTP") || InboundConnector.equals("HL7_BPartnerSFTP") || InboundConnector.equals("HL7_BPartnerEmail") || InboundConnector.equals("HL7_BPartnerSMB") || InboundConnector.equals("HL7_BPartnerLLP") || InboundConnector.equals("HL7_BPartnerJS")); else throw new IllegalArgumentException ("InboundConnector Invalid value - " + InboundConnector + " - Reference_ID=1200218 - HL7_BPartnerFile - HL7_BPartnerFTP - HL7_BPartnerSFTP - HL7_BPartnerEmail - HL7_BPartnerSMB - HL7_BPartnerLLP - HL7_BPartnerJS");		set_Value (COLUMNNAME_InboundConnector, InboundConnector);
	}

	/** Get Inbound Connector.
		@return Inbound Connector	  */
	public String getInboundConnector () 
	{
		return (String)get_Value(COLUMNNAME_InboundConnector);
	}

	/** Set Receiving Application.
		@param ReceivingApplication Receiving Application	  */
	public void setReceivingApplication (String ReceivingApplication)
	{
		if (ReceivingApplication == null)
			throw new IllegalArgumentException ("ReceivingApplication is mandatory.");
		set_Value (COLUMNNAME_ReceivingApplication, ReceivingApplication);
	}

	/** Get Receiving Application.
		@return Receiving Application	  */
	public String getReceivingApplication () 
	{
		return (String)get_Value(COLUMNNAME_ReceivingApplication);
	}

	/** Set Sends Messages.
		@param SendsMessages Sends Messages	  */
	public void setSendsMessages (boolean SendsMessages)
	{
		set_Value (COLUMNNAME_SendsMessages, Boolean.valueOf(SendsMessages));
	}

	/** Get Sends Messages.
		@return Sends Messages	  */
	public boolean isSendsMessages () 
	{
		Object oo = get_Value(COLUMNNAME_SendsMessages);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}