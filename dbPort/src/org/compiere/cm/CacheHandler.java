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
package org.compiere.cm;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import org.compiere.util.*;
import org.compiere.model.*;

/**
 * The CacheHandler handles deployment and clean of internal and external caches
 * 
 * @author Yves Sandfort
 * @version  $Id$ 
 */
public class CacheHandler {
	protected String[] cacheURLs;
	protected CLogger log;
	
	/**
	 * CacheHandler for single URL environment
	 * @param thisURL URL of this Server
	 * @param tLog thisLogger
	 * @param ctx Propertie Context
	 * @param trxName Transaction
	 */
	public CacheHandler(String thisURL, CLogger tLog, Properties ctx, String trxName) {
		int [] thisMessageIDs = MMessage.getAllIDs("AD_Message","value like 'org.compiere.cm.broadcast.server'", trxName);
		if (thisMessageIDs!=null && thisMessageIDs.length>0) {
			MMessage thisMessage = new MMessage(ctx,thisMessageIDs[0],trxName);
			String serverList = thisMessage.getMsgText();
			String [] thisURLs = split(serverList,"\n");
			cacheURLs = thisURLs;
		} else {
			// Okay we can't find any cluster config, so we will use the AppsServer from Client
			String [] thisURLs = new String[1];
			thisURLs[0] = thisURL;
			cacheURLs = thisURLs;
		}
		log = tLog;
	}
	
	/**
	 * CacheHandler form multiple URLs
	 * @param thisURLs Array of Cache Server URLs
	 * @param tLog Logger
	 */
	public CacheHandler(String [] thisURLs, CLogger tLog) {
		log = tLog;
		cacheURLs = thisURLs;
	}
	
	/**
	 * Clean Template cache for this ID
	 * @param ID ID of template to clean
	 */
	public void cleanTemplate(Integer ID) {
		cleanTemplate("" + ID);
	}
	
	/**
	 * Clean Template cache for this ID
	 * @param ID ID of template to clean
	 */
	public void cleanTemplate(String ID) {
		runURLRequest("Template", ID);
	}
	
	/**
	 * Empty all Template Caches
	 */
	public void emptyTemplate() {
		runURLRequest("Template", "0");
	}
	
	/**
	 * Clean ContainerCache for this ID
	 * @param ID for Container to clean
	 */
	public void cleanContainer(Integer ID) {
		cleanContainer("" + ID);
	}
	
	/**
	 * Clean ContainerCache for this ID
	 * @param ID for container to clean
	 */
	public void cleanContainer(String ID) {
		runURLRequest("Container", ID);
	}

	/**
	 * Clean Container Element for this ID
	 * @param ID for container element to clean
	 */
	public void cleanContainerElement(Integer ID) {
		cleanContainerElement("" + ID);
	}
	
	/**
	 * Clean Container Element for this ID
	 * @param ID for container element to clean
	 */
	public void cleanContainerElement(String ID) {
		runURLRequest("ContainerElement", ID);
	}
	
	private void runURLRequest(String cache, String ID) {
		String thisURL = null;
		for(int i=0; i<cacheURLs.length; i++) {
			try {
				thisURL = "http://" + cacheURLs[i] + "/cache/Service?Cache=" + cache + "&ID=" + ID;
				URL url = new URL(thisURL);
				Proxy thisProxy = Proxy.NO_PROXY;
				
			    URLConnection urlConn = url.openConnection(thisProxy);
			    urlConn.setUseCaches(false);
			    urlConn.connect();
			    Reader stream =             new java.io.InputStreamReader(
			                                urlConn.getInputStream());
			    StringBuffer srvOutput = new StringBuffer();

			    	try {
			    		int c;
			    		while ( (c=stream.read()) != -1 )
			    			srvOutput.append( (char)c );
			    		
			    	} catch (Exception E2) {
			    		E2.printStackTrace();
			    	}				
			} catch (IOException E) {
				if (log!=null)
					log.warning("Can't clean cache at:" + thisURL + " be carefull, your deployment server may use invalid or old cache data!");
			}
		}
	}

	/**
	 * Converts JNP URL to http URL for cache cleanup
	 * @param JNPURL String with JNP URL from Context
	 * @return clean servername
	 */
	public static String convertJNPURLToCacheURL(String JNPURL) {
		if (JNPURL.indexOf("jnp://")>=0) {
			JNPURL = JNPURL.substring(JNPURL.indexOf("jnp://")+6);
		}
		if (JNPURL.indexOf(":")>=0) {
			JNPURL = JNPURL.substring(0,JNPURL.indexOf(":"));
		}
		if (JNPURL.length()>0) {
			return JNPURL;
		} else {
			return null;
		}
	}
	
	/**
	 * This function will split a string based on a split character.
	 * @param searchIn The string to split
	 * @param splitter The separator
	 * @return String array of split values
	 */
	private String[] split(String searchIn, String splitter) {
		String[] results = new String[count(searchIn,splitter)+1];
		int position=0;
		int i=0;
		while (searchIn.indexOf(splitter,position)>=0) {
			results[i]=searchIn.substring(position,searchIn.indexOf(splitter,position+2));
			position = searchIn.indexOf(splitter,position)+1;
			i++;
		}
		results[(results.length-1)]=searchIn.substring(position);
		return results;
	}
	
	/**
	 * Should return you the number of occurences of "find" in "orig
	 * @param orig The String to look in
	 * @param find The String to look for
	 * @return Number of occurences, 0 if none
	 */
	
	private int count(String orig, String find) {
		int retVal = 0;
		int pos = 0;
		while (orig.indexOf(find,pos)>0) {
			pos = orig.indexOf(find,pos)+1;
			retVal++;
		}
		return retVal;
	}
	
}
