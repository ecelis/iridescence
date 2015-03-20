/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Medico_Org
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Medico_Org 
{

    /** TableName=EXME_Medico_Org */
    public static final String Table_Name = "EXME_Medico_Org";

    /** AD_Table_ID=1200905 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AddToNetwork */
    public static final String COLUMNNAME_AddToNetwork = "AddToNetwork";

	/** Set Add prescriber to network.
	  * Add prescriber to network
	  */
	public void setAddToNetwork (String AddToNetwork);

	/** Get Add prescriber to network.
	  * Add prescriber to network
	  */
	public String getAddToNetwork();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name CanRx */
    public static final String COLUMNNAME_CanRx = "CanRx";

	/** Set Cancel Rx capable.
	  * Cancel Rx capable
	  */
	public void setCanRx (boolean CanRx);

	/** Get Cancel Rx capable.
	  * Cancel Rx capable
	  */
	public boolean isCanRx();

    /** Column name CellPhone */
    public static final String COLUMNNAME_CellPhone = "CellPhone";

	/** Set CellPhone	  */
	public void setCellPhone (String CellPhone);

	/** Get CellPhone	  */
	public String getCellPhone();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Medico_Org_ID */
    public static final String COLUMNNAME_EXME_Medico_Org_ID = "EXME_Medico_Org_ID";

	/** Set Doctor's Information for Organization	  */
	public void setEXME_Medico_Org_ID (int EXME_Medico_Org_ID);

	/** Get Doctor's Information for Organization	  */
	public int getEXME_Medico_Org_ID();

    /** Column name EXME_Turnos_ID */
    public static final String COLUMNNAME_EXME_Turnos_ID = "EXME_Turnos_ID";

	/** Set Shift.
	  * Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID);

	/** Get Shift.
	  * Shift
	  */
	public int getEXME_Turnos_ID();

    /** Column name IntervaloConsulta */
    public static final String COLUMNNAME_IntervaloConsulta = "IntervaloConsulta";

	/** Set Consult Interval	  */
	public void setIntervaloConsulta (int IntervaloConsulta);

	/** Get Consult Interval	  */
	public int getIntervaloConsulta();

    /** Column name IsServiceProvider */
    public static final String COLUMNNAME_IsServiceProvider = "IsServiceProvider";

	/** Set Doctor bills by himself	  */
	public void setIsServiceProvider (boolean IsServiceProvider);

	/** Get Doctor bills by himself	  */
	public boolean isServiceProvider();

    /** Column name MaxCitas */
    public static final String COLUMNNAME_MaxCitas = "MaxCitas";

	/** Set Max Appointments per Days	  */
	public void setMaxCitas (BigDecimal MaxCitas);

	/** Get Max Appointments per Days	  */
	public BigDecimal getMaxCitas();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

    /** Column name NewRx */
    public static final String COLUMNNAME_NewRx = "NewRx";

	/** Set NewRx Capabale.
	  * NewRx Capabale
	  */
	public void setNewRx (boolean NewRx);

	/** Get NewRx Capabale.
	  * NewRx Capabale
	  */
	public boolean isNewRx();

    /** Column name OverlapAppointments */
    public static final String COLUMNNAME_OverlapAppointments = "OverlapAppointments";

	/** Set Overlap Appointments	  */
	public void setOverlapAppointments (boolean OverlapAppointments);

	/** Get Overlap Appointments	  */
	public boolean isOverlapAppointments();

    /** Column name RefReq */
    public static final String COLUMNNAME_RefReq = "RefReq";

	/** Set Refill Request capable.
	  * Refill Request capable
	  */
	public void setRefReq (boolean RefReq);

	/** Get Refill Request capable.
	  * Refill Request capable
	  */
	public boolean isRefReq();

    /** Column name RxChange */
    public static final String COLUMNNAME_RxChange = "RxChange";

	/** Set Rx Change Response capable.
	  * Rx Change Response capable
	  */
	public void setRxChange (boolean RxChange);

	/** Get Rx Change Response capable.
	  * Rx Change Response capable
	  */
	public boolean isRxChange();

    /** Column name ScaleMin */
    public static final String COLUMNNAME_ScaleMin = "ScaleMin";

	/** Set Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin);

	/** Get Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin();

    /** Column name SPI */
    public static final String COLUMNNAME_SPI = "SPI";

	/** Set Surescripts provider ID	  */
	public void setSPI (String SPI);

	/** Get Surescripts provider ID	  */
	public String getSPI();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name TiempoEspera */
    public static final String COLUMNNAME_TiempoEspera = "TiempoEspera";

	/** Set Waiting Time.
	  * Waiting Time (in months)
	  */
	public void setTiempoEspera (int TiempoEspera);

	/** Get Waiting Time.
	  * Waiting Time (in months)
	  */
	public int getTiempoEspera();

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set Registered EMail.
	  * Email of the responsible for the System
	  */
	public void setUserName (String UserName);

	/** Get Registered EMail.
	  * Email of the responsible for the System
	  */
	public String getUserName();
}
