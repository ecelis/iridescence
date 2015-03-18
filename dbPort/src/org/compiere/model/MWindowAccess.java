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

import java.sql.*;
import java.util.*;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 * 
 * @author Jorg Janke
 * @version $Id: MWindowAccess.java,v 1.4 2006/07/30 00:54:54 jjanke Exp $
 */
public class MWindowAccess extends X_AD_Window_Access {

	private static CLogger s_log = CLogger.getCLogger(MWindowAccess.class);

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param ignored
	 *            -
	 * @param trxName
	 *            transaction
	 */
	public MWindowAccess(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		else {
			// setAD_Role_ID (0);
			// setAD_Window_ID (0);
			setIsReadWrite(true);
		}
	} // MWindowAccess

	/**
	 * MWindowAccess
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 *            transaction
	 */
	public MWindowAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MWindowAccess

	/**
	 * Parent Constructor
	 * 
	 * @param parent
	 *            parent
	 * @param AD_Role_ID
	 *            role id
	 */
	public MWindowAccess(MWindow parent, int AD_Role_ID) {
		super(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setAD_Window_ID(parent.getAD_Window_ID());
		setAD_Role_ID(AD_Role_ID);
	} // MWindowAccess

	/**
	 * Obtiene los accesos de un rol
	 * @param ctx
	 * @param AD_Role_ID
	 * @param trxName
	 * @return
	 */
	public static List<MWindowAccess> getAccesFromRole(Properties ctx, int AD_Role_ID, String trxName) {
		StringBuilder st = new StringBuilder("select * from AD_Window_Access where AD_Role_ID = ? and isActive = 'Y'");
		List<MWindowAccess> lst = new ArrayList<MWindowAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, AD_Role_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MWindowAccess(ctx, rs, trxName));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
				pstmt = null;
				rs = null;
			}
		}
		return lst;
	}

} // MWindowAccess