/*
 * Generated by XDoclet - Do not edit!
 */
package org.compiere.interfaces;

/**
 * Local home interface for compiere/Server.
 */
public interface ServerLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/compiere/ServerLocal";
   public static final String JNDI_NAME="compiere/ServerLocal";

   public org.compiere.interfaces.ServerLocal create()
      throws javax.ejb.CreateException;

}
