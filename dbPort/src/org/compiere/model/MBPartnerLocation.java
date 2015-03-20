/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.OptionItem;


/**
 *	Partner Location Model
 *
 *  @author Jorg Janke
 *  @version $Id: MBPartnerLocation.java,v 1.2 2006/08/23 00:43:45 mrojas Exp $
 */
public class MBPartnerLocation extends X_C_BPartner_Location
{
	/** serialVersionUID */
	private static final long serialVersionUID = -5611318695111111610L;

	/**
	 * 	Get Locations for BPartner
	 *	@param ctx context
	 *	@param C_BPartner_ID bp
	 *	@return array of locations
	 */
	public static MBPartnerLocation[] getForBPartner (Properties ctx, int C_BPartner_ID, String trxName)
	{
		ArrayList<MBPartnerLocation> list = new ArrayList<MBPartnerLocation>();
		String sql = "SELECT * FROM C_BPartner_Location WHERE C_BPartner_ID=? order by created asc ";
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_BPartner_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ()){
				list.add(new MBPartnerLocation(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getForBPartner"+sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MBPartnerLocation[] retValue = new MBPartnerLocation[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getForBPartner
	
	//TODO Documentar Metodo	
	/**
	 * Metodo que retorna Lista de CBPartnersLocation dependiendo del cbpartner
	 * seleccionado 
	 * 
	 */
	public Vector<OptionItem> getCBPartnerLocationList(Integer cBPartnerID){
		Vector<OptionItem> cbPartnersLocation = null;
		String sql = "select c_bpartner_location_id,name from c_bpartner_location " +
					 "where C_BPartner_ID=? AND IsBillTo='Y' AND IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, cBPartnerID);
			rs = pstmt.executeQuery();
			cbPartnersLocation = new Vector<OptionItem>();
			while (rs.next())
				cbPartnersLocation.add(new OptionItem(rs.getString(1), rs.getString(2)));
			
		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getCBPartnerLocationList", e);			
		}finally{
			DB.close(rs, pstmt);
		}
		return cbPartnersLocation;
	}
	
	
	/**	Static Logger					*/
	private static CLogger	s_log	= CLogger.getCLogger (MBPartnerLocation.class);

	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_BPartner_Location_ID id
	 *	@param trxName transaction
	 */
	public MBPartnerLocation (Properties ctx, int C_BPartner_Location_ID, String trxName)
	{
		super (ctx, C_BPartner_Location_ID, trxName);
		if (C_BPartner_Location_ID == 0)
		{
			setName (".");
			//
			setIsShipTo (true);
			setIsRemitTo (true);
			setIsPayFrom (true);
			setIsBillTo (true);
		}
	}	//	MBPartner_Location

	/**
	 * 	BP Parent Constructor
	 * 	@param bp partner
	 */
	public MBPartnerLocation (MBPartner bp)
	{
		this (bp.getCtx(), 0, bp.get_TrxName());
		setClientOrg(bp);
		//	may (still) be 0
		set_ValueNoCheck ("C_BPartner_ID", new Integer(bp.getC_BPartner_ID()));
	}	//	MBPartner_Location

	/**
	 * 	Constructor from ResultSet row
	 *	@param ctx context
	 * 	@param rs current row of result set to be loaded
	 *	@param trxName transaction
	 */
	public MBPartnerLocation (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBPartner_Location

	/**	Cached Location			*/
	private MLocation 	m_location = null;
	/**	Unique Name				*/
	private String		m_uniqueName = null;
	private int			m_unique = 0;
	
	/**
	 * 	Get Loaction/Address
	 * 	@param requery requery
	 *	@return location
	 */
	public MLocation getLocation (boolean requery)
	{
		if (m_location == null || requery)
			m_location = MLocation.get (getCtx(), getC_Location_ID(), get_TrxName());
		return m_location;
	}	//	getLoaction

	//lama
	
	public MLocation getBPLocation(){
        
        if(getC_BPartner_Location_ID() <= 0)
            return null;
        
        return new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
        
    }
	
	/**
	 *	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MBPartner_Location[ID=")
			.append(get_ID())
			.append(",C_Location_ID=").append(getC_Location_ID())
			.append(",Name=").append(getName())
			.append ("]");
		return sb.toString ();
	}	//	toString

	
	/**************************************************************************
	 * 	Before Save.
	 * 	- Set Name
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getC_Location_ID() == 0)
			return false;

		//	Set New Name
		if (!newRecord)
			return true;
		/*
		 * JCarranza - Se comento este bloque de codigo para optimizar el guardado de C_Location ya que no se esta implementanto
		 * correctamente el proceso de verificar las direcciones repetidas de los socios de negocios en el sistema. 
		 */
/*		MLocation address = getLocation(true);  
		m_uniqueName = getName();
		if (m_uniqueName != null && m_uniqueName.equals("."))	//	default
			m_uniqueName = null;
		m_unique = 0;
		makeUnique(address);
		
		//	Check uniqueness
		MBPartnerLocation[] locations = MBPartnerLocation.getForBPartner(getCtx(), getC_BPartner_ID());
		boolean unique = locations.length == 0;
		while (!unique)
		{
			unique = true;
			for (int i = 0; i < locations.length; i++)
			{
				MBPartnerLocation location = locations[i];
				if (location.getC_BPartner_Location_ID() == get_ID())
					continue;
				if (m_uniqueName.equals(location.getName()))
				{
					makeUnique(address);
					unique = false;
					break;
				}
			}
		}
		setName (m_uniqueName);                                      */
		return true;
	}	//	beforeSave
	
	/**
	 * 	Make name Unique
	 * 	@param address address
	 */
	private void makeUnique (MLocation address)
	{
	//	m_uniqueName = address.toString();
	//	return;
		
		if (m_uniqueName == null)
			m_uniqueName = "";
		m_unique++;
		
		//	0 - City
		if (m_uniqueName.length() == 0)
		{
			String xx = address.getCity(); 
			if (xx != null && xx.length() > 0)
				m_uniqueName = xx;
			m_unique = 0;
		}
		//	1 + Address1
		if (m_unique == 1 ||  m_uniqueName.length() == 0)
		{
			String xx = address.getAddress1();
			if (xx != null && xx.length() > 0)
			{
				if (m_uniqueName.length() > 0)
					m_uniqueName += " ";
				m_uniqueName += xx;
			}
			m_unique = 1;
		}
		//	2 + Address2
		if (m_unique == 2 ||  m_uniqueName.length() == 0)
		{
			String xx = address.getAddress2();
			if (xx != null && xx.length() > 0)
			{
				if (m_uniqueName.length() > 0)
					m_uniqueName += " ";
				m_uniqueName += xx;
			}
			m_unique = 2;
		}
		//	3 - Region	
		if (m_unique == 3 ||  m_uniqueName.length() == 0)
		{
			String xx = address.getRegionName(true);
			{
				if (m_uniqueName.length() > 0)
					m_uniqueName += " ";
				m_uniqueName += xx;
			}
			m_unique = 3;
		}
		//	4 - ID	
		if (m_unique == 4 ||  m_uniqueName.length() == 0)
		{
			int id = get_ID();
			if (id == 0)
				id = address.get_ID();
			m_uniqueName += "#" + id;		
			m_unique = 4;
		}
	}	//	makeUnique
	
	
	/**
	 * Obtenemos las ubicaciones de un socio de negocios
	 * 
	 *@param cBPartnerId  El socio de negocios a obtener su direcction
	 *@return La direccion del socio de negocios
	 *@exception Exception
	 *                Description of the Exception
	 */
	public static int getBPartnerLocation(final Properties ctx, int cBPartnerId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// buscamos la direccion del socio de negocios
		sql.append("SELECT C_Location_ID FROM C_BPartner_Location ");
		sql.append("WHERE C_BPartner_ID = ? AND C_Location_ID IS NOT NULL ");
		sql.append("AND IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY C_BPartner_Location_ID");
		return DB.getSQLValue(null, sql.toString(), cBPartnerId);
	}
	
	/**
	 * Obtenemos las ubicaciones de un socio de negocios
	 * 
	 *@param cBPartnerId  El socio de negocios a obtener su direcction
	 *@return La direccion del socio de negocios
	 *@exception Exception
	 *                Description of the Exception
	 */
	public static int getBPLocationID(final Properties ctx, int cBPartnerId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// buscamos la direccion del socio de negocios
		sql.append("SELECT C_BPartner_Location_ID FROM C_BPartner_Location ");
		sql.append("WHERE C_BPartner_ID = ? ");
		sql.append("AND IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY C_BPartner_Location_ID");
		return DB.getSQLValue(null, sql.toString(), cBPartnerId);
	}
	
}	//	MBPartnerLocation
