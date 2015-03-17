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

import java.util.logging.*;
import org.compiere.util.*;

/**
 *	(Disk) Tree Node Model Menu
 *	
 *  @author Jorg Janke
 *  @version $Id: MTree_NodeMM.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $ <br>
 *  Modified by Lorena Lama, Oct 2012
 */
public class MTree_NodeMM extends X_AD_TreeNodeMM {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 	Get Tree Node
	 *	@param tree tree
	 *	@param Node_ID node
	 *	@return node or null
	 */
	public static MTree_NodeMM get(MTree_Base tree, int Node_ID) {
		MTree_NodeMM retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement("SELECT * FROM AD_TreeNodeMM WHERE AD_Tree_ID=? AND Node_ID=?", 
					tree.get_TrxName());
			pstmt.setInt(1, tree.getAD_Tree_ID());
			pstmt.setInt(2, Node_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MTree_NodeMM(tree.getCtx(), rs, tree.get_TrxName());
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get" , e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} // get

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MTree_NodeMM.class);

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTree_NodeMM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MTree_NodeMM

	/**
	 * 	Full Constructor
	 *	@param tree tree
	 *	@param Node_ID node
	 */
	public MTree_NodeMM(MTree_Base tree, int Node_ID) {
		super(tree.getCtx(), 0, tree.get_TrxName());
		setClientOrg(tree);
		setAD_Tree_ID(tree.getAD_Tree_ID());
		setNode_ID(Node_ID);
		// Add to root
		setParent_ID(0);
		setSeqNo(0);
	} // MTree_NodeMM
	
	/**
	 * Busca el nodo en el arbol y lo elimina
	 * 
	 * @param tree Id del arbol (menu)
	 * @param Node_ID Id del nodo a agregar (ad_menu_id)
	 * @return true si se elimino
	 */
	public static boolean deleteNode(MTree_Base tree, int Node_ID) {
		if (Node_ID > 0) {
			final MTree_NodeMM node = MTree_NodeMM.get(tree, Node_ID);
			if (node != null) {
				return node.delete(true);
			}
		}
		return false;
	}

	/**
	 * Crea un nuevo nodo (padre 0)
	 * 
	 * @param tree Id del arbol (menu)
	 * @param Node_ID Id del nodo a agregar (ad_menu_id)
	 * @return true si se inserto
	 */
	public static boolean addNode(MTree_Base tree, int Node_ID) {
		final MTree_NodeMM node = new MTree_NodeMM(tree, Node_ID);
		return node.save();
	}
	
}	//	MTree_NodeMM
