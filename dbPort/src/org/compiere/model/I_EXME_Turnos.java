/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Turnos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Turnos 
{

    /** TableName=EXME_Turnos */
    public static final String Table_Name = "EXME_Turnos";

    /** AD_Table_ID=1000012 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

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

    /** Column name HoraEnt1Es */
    public static final String COLUMNNAME_HoraEnt1Es = "HoraEnt1Es";

	/** Set Check in Hour 1.
	  * Check in Hour 1
	  */
	public void setHoraEnt1Es (String HoraEnt1Es);

	/** Get Check in Hour 1.
	  * Check in Hour 1
	  */
	public String getHoraEnt1Es();

    /** Column name HoraEnt1Fs */
    public static final String COLUMNNAME_HoraEnt1Fs = "HoraEnt1Fs";

	/** Set Week End Check in Hour.
	  * Week End Check in Hour
	  */
	public void setHoraEnt1Fs (String HoraEnt1Fs);

	/** Get Week End Check in Hour.
	  * Week End Check in Hour
	  */
	public String getHoraEnt1Fs();

    /** Column name HoraEnt2Es */
    public static final String COLUMNNAME_HoraEnt2Es = "HoraEnt2Es";

	/** Set Check in Hour 2.
	  * Check in Hour 2
	  */
	public void setHoraEnt2Es (String HoraEnt2Es);

	/** Get Check in Hour 2.
	  * Check in Hour 2
	  */
	public String getHoraEnt2Es();

    /** Column name HoraEnt3Es */
    public static final String COLUMNNAME_HoraEnt3Es = "HoraEnt3Es";

	/** Set Check in Hour 3.
	  * Check in Hour 3
	  */
	public void setHoraEnt3Es (String HoraEnt3Es);

	/** Get Check in Hour 3.
	  * Check in Hour 3
	  */
	public String getHoraEnt3Es();

    /** Column name HoraSal1Es */
    public static final String COLUMNNAME_HoraSal1Es = "HoraSal1Es";

	/** Set Check out hour 1.
	  * Check out 1
	  */
	public void setHoraSal1Es (String HoraSal1Es);

	/** Get Check out hour 1.
	  * Check out 1
	  */
	public String getHoraSal1Es();

    /** Column name HoraSal1Fs */
    public static final String COLUMNNAME_HoraSal1Fs = "HoraSal1Fs";

	/** Set Week End Check Out Hour.
	  * Week End Check Out Hour
	  */
	public void setHoraSal1Fs (String HoraSal1Fs);

	/** Get Week End Check Out Hour.
	  * Week End Check Out Hour
	  */
	public String getHoraSal1Fs();

    /** Column name HoraSal2Es */
    public static final String COLUMNNAME_HoraSal2Es = "HoraSal2Es";

	/** Set Check Our Hour 2.
	  * Check out hour 2
	  */
	public void setHoraSal2Es (String HoraSal2Es);

	/** Get Check Our Hour 2.
	  * Check out hour 2
	  */
	public String getHoraSal2Es();

    /** Column name HoraSal3Es */
    public static final String COLUMNNAME_HoraSal3Es = "HoraSal3Es";

	/** Set Check Out Hour 3.
	  * Check out hour 3
	  */
	public void setHoraSal3Es (String HoraSal3Es);

	/** Get Check Out Hour 3.
	  * Check out hour 3
	  */
	public String getHoraSal3Es();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
