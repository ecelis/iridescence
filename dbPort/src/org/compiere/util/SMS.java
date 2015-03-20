package org.compiere.util;


import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEMejoras;
/**
 * CLICKATELL SMS API
 *
 * This class is meant to send SMS messages via the Clickatell gateway
 * and provides support to authenticate to this service and also query
 * for the current account balance. This class use the fopen or CURL module
 * to communicate with the gateway via HTTP/S.
 *
 * For more information about CLICKATELL service visit http://www.clickatell.com
 *
 * @version 1.3d
 * @package sms_api
 * @author Aleksandar Markovic <mikikg@gmail.com>
 * @copyright Copyright 2004, 2005 Aleksandar Markovic
 * @link http://sourceforge.net/projects/sms-api/ SMS-API Sourceforge project page
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 *
 */

/**
 * Main SMS-API class
 *
 * Example:
 * <code>
 * <?php
 * require_once ("sms_api.php");
 * $mysms = new sms();
 * echo $mysms->session;
 * echo $mysms->getbalance();
 * $mysms->send ("38160123", "netsector", "TEST MESSAGE");
 * ?>
 * </code>
 * @package sms_api
 */
public class SMS {



	/**
	 * Clickatell API-ID
	 *
	 * @link http://sourceforge.net/forum/forum.php?thread_id=1005106&forum_id=344522 How to get
	 *       CLICKATELL API ID?
	 * @private String integer
	 */
	private String api_id = null; //"8541654185";

	/**
	 * Clickatell username
	 *
	 * @private String mixed
	 */
	private String user = null;//"enlainternet";

	/**
	 * Clickatell password
	 *
	 * @private String mixed
	 */
	private String password = null;//"785416hui";

	/**
	 * Use SSL (HTTPS) protocol
	 *
	 * @private String bool
	 */
	private boolean use_ssl = false;

	/**
	 * Define SMS balance limit below class will not work
	 *
	 * @private String integer
	 */
    private int balace_limit = 0;

	/**
	 * Gateway command sending method (curl,fopen)
	 *
	 * @private String mixed
	 */
	private String sending_method = "fopen";

	/**
	 * Optional CURL Proxy
	 *
	 * @private String bool
	 */
	private boolean curl_use_proxy = false;

	/**
	 * Proxy URL and PORT
	 *
	 * @private String mixed
	 */
	private String curl_proxy = "http://127.0.0.1:8080";

	/**
	 * Proxy username and password
	 *
	 * @private String mixed
	 */
	private String curl_proxyuserpwd = "login:secretpass";

	/**
	 * Callback 0 - Off 1 - Returns only intermediate statuses 2 - Returns only final statuses 3 -
	 * Returns both intermediate and final statuses
	 *
	 * @private String integer
	 */
	private int callback = 0;

    /**
	 * Session variable
	 *
	 * @private String mixed
	 */
	private String session;

	private String base;

	private String base_s;

	public String email = null;


    /**
	 * Class constructor Create SMS object and authenticate SMS gateway
	 *
	 * @return object New SMS object.
	 * @access public
	 */
	public SMS(Properties ctx) throws Exception{
		if (this.use_ssl) {
			this.base = "http://api.clickatell.com/http";
			this.base_s = "https://api.clickatell.com/http";
		} else {
			this.base = "http://api.clickatell.com/http";
			this.base_s = this.base;
		}
		this._data(ctx);
		this._auth();
	}

	/**
	 *
	 * @return mixed "OK" or script die
	 * @access private
	 */
	private void _data(Properties ctx) throws Exception {
		//de mejoras obtenemos los valores
		MEXMEMejoras mejora = MEXMEMejoras.get(ctx,null);
		if(mejora == null || mejora.getRequestID() == null ||
				mejora.getRequestUser() == null || mejora.getRequestUserPW() == null)
			throw new Exception("User and password must be configured");

		this.password=mejora.getRequestUserPW();
		this.api_id=mejora.getRequestID();
		this.user=mejora.getRequestUser();
		this.email =mejora.getRequestEMail();
	}

	/**
	 * "%s/auth?api_id=%s&user=%s&password=%s" Authenticate SMS gateway
	 *
	 * @return mixed "OK" or script die
	 * @access private
	 */
	private void _auth() throws Exception {
		StringBuilder comm = new StringBuilder();

		comm.append(this.base_s).append("/auth?api_id=").append(this.api_id).append("&user=")
				.append(this.user).append("&password=").append(this.password);
		this.session = this._parse_auth(this._execgw(comm.toString()));
	}

	/**
	 * "%s/getbalance?session_id=%s" Query SMS credis balance
	 *
	 * @return integer number of SMS credits
	 * @access public
	 */
	public int getbalance() throws Exception {
		StringBuilder comm = new StringBuilder();
		comm.append(this.base).append("/getbalance?session_id=").append(this.session);

		return this._parse_getbalance(this._execgw(comm.toString()));
	}

	/**
	 * "%s/sendmsg?session_id=%s&to=%s&from=%s&text=%s&callback=%s%s" Send SMS message
	 *
	 * @param to
	 *            mixed The destination address.
	 * @param from
	 *            mixed The source/sender address
	 * @param text
	 *            mixed The text content of the message
	 * @return mixed "OK" or script die
	 * @access public
	 */
     public String send(String to, String from, String text) throws Exception {

		/* Check SMS credits balance */
		if (this.getbalance() < this.balace_limit) {
			throw new Exception("You have reach the SMS credit limit!");
		}

		/* Check SMS $text length */
		if (text.length() > 465) {
			throw new Exception("Your message is to long! Current lenght=" + text.length());
		}
		String concat = "";
		/* Does message need to be concatenate */
		if (text.length() > 160) {
			concat = "&concat=3";
		}
		/* Check $to and $from is not empty */
		if (to == null) {
			throw new Exception("You not specify destination address (TO)!");
		}
		if (from == null) {
			throw new Exception("You not specify source address (FROM)!");
		}

		/* Reformat $to number */
		String[] cleanup_chr =  new String[]{"+", " ", "(", ")", "\r", "\n", "\r\n"};
		to = StringUtils.replaceEach(to,cleanup_chr,new String[]{"", "", "", "", "", "", ""});

		///* Reformat $to number */
        //$cleanup_chr = array ("+", " ", "(", ")", "\r", "\n", "\r\n");
        //$to = str_replace($cleanup_chr, "", $to);
		//StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"

		/* Send SMS now */
		StringBuilder comm = new StringBuilder();
		comm.append(this.base_s)
			.append("/sendmsg?session_id=").append(this.session)
			.append("&to=").append(StringUtils.replace(to," ", "%"))
			.append("&from=").append(StringUtils.replace(from," ", "%"))
			.append("&text=").append(StringUtils.replace(text," ", "%"))
			.append("&callback=").append(String.valueOf(this.callback))
			.append(concat);

		return this._parse_send(this._execgw(comm.toString()));
	}

     /**
		 * Execute gateway commands
		 *
		 * @access private
		 */
     private String _execgw(String command) throws Exception {
         if (this.sending_method == "curl")
             return this._curl(command);
         if (this.sending_method == "fopen")
             return this._fopen(command);
         throw new Exception ("Unsupported sending method!");
     }

     /**
     * CURL sending method
     * @access private
     */
     private String _curl(String command) {
         /*this._chk_curl();
         String ch = curl_init (command);
         curl_setopt (ch, CURLOPT_HEADER, 0);
         curl_setopt (ch, CURLOPT_RETURNTRANSFER,1);
         curl_setopt (ch, CURLOPT_SSL_VERIFYPEER,0);
         if (this.curl_use_proxy) {
             curl_setopt (ch, CURLOPT_PROXY, this.curl_proxy);
             curl_setopt (ch, CURLOPT_PROXYUSERPWD, this.curl_proxyuserpwd);
         }
         result=curl_exec (ch)
         curl_close (ch)
         */
         return null;
     }

     /**
     * fopen sending method
     * @access private
     */
     private String _fopen(String command) throws Exception {
		String result = null;
		try{
		URL url1 = new URL(command);
		result = IOUtils.toString(url1.openStream());
		}catch (Exception e) {
			throw new Exception ("Error while executing IOUtils.toString sending method!");
		}
		return result;
	}

	/**
	 * Parse authentication command response text
	 *
	 * @access private
	 */
     private String _parse_auth(String result) throws Exception {
		this.session = result.substring(4);
		String code = result.substring(0, 2);
		if (!code.equals("OK")) {
			throw new Exception("Error in SMS authorization! :"+result); //
		}
		return session;
	}

	/**
	 * Parse send command response text
	 *
	 * @access private
	 */
     private String _parse_send(String result) throws Exception {
		String code = result.substring(0, 2);
		if (!code.equals("ID")) {
			throw new Exception("Error sending SMS! ($result)");
		} else {
			code = "OK";
		}
		return code;
	}

	/**
	 * Parse getbalance command response text
	 * @access private
	 */
     private int _parse_getbalance(String result) {
		result = result.substring(8,result.indexOf('.'));
		return Integer.parseInt(result);
	}

	/**
	 * Check for CURL PHP module
	 * @access private
	 */
     private void _chk_curl() throws Exception {
//		if (!extension_loaded("curl")) { // duda!!
//			throw new Exception(
//					"This SMS API class can not work without CURL PHP module! Try using fopen sending method.");
//		}
	}



}
