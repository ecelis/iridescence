package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MInvoiceTax;
import org.compiere.model.MLocation;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_I_Invoice;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Import invoices, currently implemented only to upload ARI Invoices for
 * new client implementation, must check the full procedure to include all the
 * cases.
 */
public class ImportInvoice extends SvrProcess
{
	/**	Client to be imported to		*/
	private transient int				adClientId = 0;
	/**	Organization to be imported to		*/
	private transient int				adOrgId = 0;
	/**	Delete old Imported				*/
	private transient boolean			delOldImported = false;
	/**	Document Action					*/
	private transient String			docAction = MInvoice.DOCACTION_Close;
	private transient String 			docStatus = MInvoice.DOCACTION_Complete;


	/** Effective						*/
	private transient Timestamp			dateValue = null;


	private final static String UPD_INVOICE = "UPDATE I_Invoice ";
	private final static String SQL_ROWNUM = " AND ROWNUM=1) ";
	private final static String SQL_UPDINV_O = "UPDATE I_Invoice o ";
	private final static String SQL_IS_IMPORTED = " AND I_IsImported<>'Y'";

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		final ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			final String name = para[i].getParameterName();
			if ("AD_Client_ID".equals(name)) {
				adClientId = ((BigDecimal)para[i].getParameter()).intValue();
			} else if ("AD_Org_ID".equals(name)){
				adOrgId = ((BigDecimal)para[i].getParameter()).intValue();
			} else if ("DeleteOldImported".equals(name)) {
				delOldImported = "Y".equals(para[i].getParameter());
			/*} else if ("DocAction".equals(name)) {
				docAction = (String)para[i].getParameter();*/
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}

		if (dateValue == null) {
			dateValue = new Timestamp(System.currentTimeMillis());
		}
	}	//	prepare

	/**
	 *  Perrform process.
	 *  @return clear Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		StringBuilder sql = new StringBuilder();
		int no = 0;
		final String clientCheck = " AND AD_Client_ID=" + adClientId;

		// **** Prepare ****

		// Delete Old Imported
		if (delOldImported) {
			sql.append("DELETE I_Invoice ").append("WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Imported =" + no);
		}

		updateBaseData(clientCheck);

		updateDocType(clientCheck);

		updateSOTrx(clientCheck);

		updatePriceList(clientCheck);

		updatePaymetTerm(clientCheck);

		updateBP(clientCheck);

//		updateBPLocation(clientCheck);

//		updateCountry(clientCheck);

//		updateRegion(clientCheck);

		updateProduct(clientCheck);
		updateCharge();

		updateTax(clientCheck);

//		createBPartner(clientCheck);

		createInvoice(clientCheck);

		commitEx();
		return "";
	} // doIt

	/**
	 * @param sql
	 * @param clientCheck
	 */
	private void updateBaseData(final String clientCheck) {
		int no;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// Set Client, Org, IsActive, Created/Updated
		sql.append("UPDATE I_Invoice SET AD_Client_ID = COALESCE (AD_Client_ID, ?),")
		.append(" AD_Org_ID = COALESCE (AD_Org_ID, ?),")
		.append(" IsActive = COALESCE (IsActive, 'Y'),")
		.append(" Created = COALESCE (Created, SysDate), CreatedBy = COALESCE (CreatedBy, 0),")
		.append(" Updated = COALESCE (Updated, SysDate),")
		.append(" UpdatedBy = COALESCE (UpdatedBy, 0), I_ErrorMsg = NULL, I_IsImported = 'N' ")
		.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), new Integer[] {adClientId, adOrgId}, null);
		log.info("Reset=" + no);

		// actualiza mensaje de error portener un registro actual
		sql =
			new StringBuilder("UPDATE I_Invoice o SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid Org, '")
			.append("WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0")
			.append(" OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);

		if (no != 0) {
			log.warning("Invalid Org=" + no);
		}
	}

	/**
	 * @param clientCheck
	 */
	private void updateTax(final String clientCheck) {
		int no;
		// Tax
		StringBuilder sql =
				new StringBuilder(SQL_UPDINV_O)
				.append("SET C_Tax_ID=(SELECT C_Tax_ID FROM C_Tax t")
				.append(" WHERE o.TaxIndicator=t.TaxIndicator AND o.AD_Client_ID=t.AD_Client_ID ");
		if (DB.isOracle()) {
			sql.append(SQL_ROWNUM);
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append("WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL").append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Tax=" + no);

		sql = new StringBuilder(UPD_INVOICE)
		.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid Tax, ' ")
		.append("WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL")
		.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning("Invalid Tax=" + no);
		}
	}

	/**
	 * Crea la factura en la tabla correcta a partir de la tabla de interfaz
	 */
	private void createInvoice(final String clientCheck) {
		StringBuilder sql;
		int no;
		// -- New Invoices -----------------------------------------------------

		int noInsert = 0;
		int noInsertLine = 0;

		// Go through Invoice Records w/o
		sql = new StringBuilder("SELECT * FROM I_Invoice WHERE I_IsImported='N'")
			.append(clientCheck)
			.append(" ORDER BY C_BPartner_ID, C_BPartner_Location_ID, C_DocType_ID, DocumentNo");

		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;
		try {

			rs = pstmt.executeQuery();
			// Group Change
			int oldC_BPartner_ID = 0;
			int oldCBPLocationId = 0;
			String oldDocumentNo = "";
			//
			MInvoice invoice = null;
			int lineNo = 0;
			while (rs.next()) {
				X_I_Invoice imp = new X_I_Invoice(getCtx(), rs, get_TrxName());

				 String cmpDocumentNo = imp.getDocumentNo();

				if (cmpDocumentNo == null) {
					cmpDocumentNo = StringUtils.EMPTY;
				}

				// New Invoice
				if (oldC_BPartner_ID != imp.getC_BPartner_ID()
						|| oldCBPLocationId != imp.getC_BPartner_Location_ID()
						|| !oldDocumentNo.equals(cmpDocumentNo)) {
					if (invoice != null) {

						createTaxLines(invoice);

						invoice.updateHeader();
//						invoice.processIt(docAction);
						invoice.setDocStatus(docStatus);
						invoice.setDocAction(docAction);
						invoice.save();
					}

					// Group Change
					oldC_BPartner_ID = imp.getC_BPartner_ID();
					oldCBPLocationId = imp.getC_BPartner_Location_ID();
					oldDocumentNo = imp.getDocumentNo();
					if (oldDocumentNo == null) {
						oldDocumentNo = StringUtils.EMPTY;
					}

					//
					invoice = new MInvoice(getCtx(), 0, get_TrxName());
					invoice.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
					invoice.setC_DocTypeTarget_ID(imp.getC_DocType_ID());
					invoice.setC_DocType_ID(imp.getC_DocType_ID());
					invoice.setTrxType(X_C_Invoice.TRXTYPE_CustomerInvoice);
					invoice.setIsSOTrx(imp.isSOTrx());
					if (imp.getDocumentNo() != null) {
						invoice.setDocumentNo(imp.getDocumentNo());
					}

					//
					invoice.setC_BPartner_ID(imp.getC_BPartner_ID());
					invoice.setC_BPartner_Location_ID(imp.getC_BPartner_Location_ID());
					if (imp.getAD_User_ID() != 0) {
						invoice.setAD_User_ID(imp.getAD_User_ID());
					}

					//
					if (imp.getDescription() != null) {
						invoice.setDescription(imp.getDescription());
					}
					invoice.setC_PaymentTerm_ID(imp.getC_PaymentTerm_ID());
					invoice.setM_PriceList_ID(imp.getM_PriceList_ID());

					// SalesRep from Import or the person running the import
					if (imp.getSalesRep_ID() != 0) {
						invoice.setSalesRep_ID(imp.getSalesRep_ID());
					}
					if (invoice.getSalesRep_ID() == 0) {
						invoice.setSalesRep_ID(getAD_User_ID());
					}

					//
					if (imp.getAD_OrgTrx_ID() != 0) {
						invoice.setAD_OrgTrx_ID(imp.getAD_OrgTrx_ID());
					}
					if (imp.getC_Activity_ID() != 0) {
						invoice.setC_Activity_ID(imp.getC_Activity_ID());
					}
					if (imp.getC_Campaign_ID() != 0) {
						invoice.setC_Campaign_ID(imp.getC_Campaign_ID());
					}
					if (imp.getC_Project_ID() != 0) {
						invoice.setC_Project_ID(imp.getC_Project_ID());
					}
					if (imp.getDateInvoiced() != null) {
						invoice.setDateInvoiced(imp.getDateInvoiced());
					}
					if (imp.getDateAcct() == null) {
						invoice.setDateAcct(imp.getDateInvoiced());
					} else {
						invoice.setDateAcct(imp.getDateAcct());
					}

					invoice.setNombre_Paciente(imp.getName());
					invoice.setDescription(imp.getDescription());
					invoice.setPosted(true);
//					invoice.setDocAction(X_C_Invoice.DOCACTION_Close);
					invoice.setProcessed(true);
					invoice.setProcessing(false);
					invoice.setIsPaid(false);

					invoice.save();
					noInsert++;
					lineNo = 10;
				}
				imp.setC_Invoice_ID(invoice.getC_Invoice_ID());

				// New InvoiceLine
				MInvoiceLine line = new MInvoiceLine(invoice);
				if (imp.getLineDescription() != null) {
					line.setDescription(imp.getLineDescription());
				}
				line.setLine(lineNo);
				lineNo += 10;

				if (imp.getM_Product_ID() != 0) {
					line.setM_Product_ID(imp.getM_Product_ID(), true);
				}

				if(imp.getC_Charge_ID() != 0) {
					line.setC_Charge_ID(imp.getC_Charge_ID());
				}

				line.setQty(BigDecimal.ONE);
				line.setQtyEntered_Vol(BigDecimal.ONE);
				line.setPrice();

				final BigDecimal price = imp.getPriceActual();
				if (price != null && Env.ZERO.compareTo(price) != 0) {
					line.setPrice(price);
				}

				if (imp.getC_Tax_ID() == 0) {
					line.setTax();
					imp.setC_Tax_ID(line.getC_Tax_ID());
				} else {
					line.setC_Tax_ID(imp.getC_Tax_ID());
				}

				final BigDecimal taxAmt = imp.getTaxAmt();
				if (taxAmt != null && BigDecimal.ZERO.compareTo(taxAmt) != 0) {
					line.setTaxAmt(taxAmt);
				}

//				line.updateHeaderTax();
				line.setLineNetAmt();
				line.save();
				//
				imp.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
				imp.setI_IsImported(true);
				imp.setProcessed(true);

				//
				if (imp.save()) {
					noInsertLine++;
				}
			}

			if (invoice != null) {
				createTaxLines(invoice);

				invoice.updateHeader();
//				invoice.processIt(docAction);
				invoice.setDocStatus(docStatus);
				invoice.setDocAction(docAction);
				invoice.save();
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "CreateInvoice", e);
		} finally {
			DB.close(rs, pstmt);
		}

		// Set Error to indicator to not imported
		sql =
				new StringBuilder("UPDATE I_Invoice SET I_IsImported='N', Updated=SysDate WHERE I_IsImported<>'Y'")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());

		addLog(0, null, new BigDecimal(no), "@Errors@");
		//
		addLog(0, null, new BigDecimal(noInsert), "@C_Invoice_ID@: @Inserted@");
		addLog(0, null, new BigDecimal(noInsertLine), "@C_InvoiceLine_ID@: @Inserted@");
	}

	/**
	 * Crea el socio de negocios en la tabla correcta
	 * @param clientCheck
	 */
	private void createBPartner(final String clientCheck) {
		int no;
		// -- New BPartner ---------------------------------------------------

		// Go through Invoice Records w/o C_BPartner_ID
		StringBuilder sql =
				new StringBuilder("SELECT * FROM I_Invoice WHERE I_IsImported='N' AND C_BPartner_ID IS NULL")
				.append(clientCheck);

		final PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;

		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final X_I_Invoice imp = new X_I_Invoice(getCtx(), rs, null);
				if (imp.getBPartnerValue() == null) {

					if (imp.getEMail() != null) {
						imp.setBPartnerValue(imp.getEMail());
					} else if (imp.getName() == null) {
						continue;
					} else {
						imp.setBPartnerValue(imp.getName());
					}
				}

				if (imp.getName() == null) {
					if (imp.getContactName() == null) {
						imp.setName(imp.getBPartnerValue());
					} else {
						imp.setName(imp.getContactName());
					}
				}
				// BPartner
				MBPartner bp = MBPartner.get(getCtx(), imp.getBPartnerValue());
				if (bp == null) {
					bp = new MBPartner(getCtx(), -1, get_TrxName());
					bp.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
					bp.setValue(imp.getBPartnerValue());
					bp.setName(imp.getName());
					if (!bp.save()) {
						continue;
					}
				}
				imp.setC_BPartner_ID(bp.getC_BPartner_ID());

				// BP Location
				MBPartnerLocation bpl = null;
				final MBPartnerLocation[] bpls = bp.getLocations(true);
				for (int i = 0; bpl == null && i < bpls.length; i++) {
					if (imp.getC_BPartner_Location_ID() == bpls[i].getC_BPartner_Location_ID()) {
						bpl = bpls[i];
					} else if (imp.getC_Location_ID() == bpls[i].getC_Location_ID()) {// Same Location ID
						bpl = bpls[i];
					} else if (imp.getC_Location_ID() == 0) {// Same Location Info
						bpl = new MBPartnerLocation(getCtx(), 0, get_TrxName());// TODO: revisar logica
						MLocation loc = bpl.getLocation(false);


						if (loc.equals(imp.getC_Country_ID(), imp.getC_Region_ID(),
								imp.getPostal(), "", imp.getCity(), imp.getAddress1(),
								imp.getAddress2())) {
							bpl = bpls[i];
						}
					}
				}

				if (bpl == null) {
					// New Location
					MLocation loc = new MLocation(getCtx(), 0, get_TrxName());
					loc.setAddress1(imp.getAddress1());
					loc.setAddress2(imp.getAddress2());
					loc.setCity(imp.getCity());
					loc.setPostal(imp.getPostal());
					if (imp.getC_Region_ID() != 0) {
						loc.setC_Region_ID(imp.getC_Region_ID());
					}
					loc.setC_Country_ID(imp.getC_Country_ID());
					if (!loc.save()) {
						continue;
					}
					//
					bpl = new MBPartnerLocation(bp);
					bpl.setC_Location_ID(imp.getC_Location_ID());
					if (!bpl.save()) {
						continue;
					}
				}
				imp.setC_Location_ID(bpl.getC_Location_ID());
				imp.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());

				// User/Contact
				if (imp.getContactName() != null || imp.getEMail() != null || imp.getPhone() != null) {
					MUser[] users = bp.getContacts(true);
					MUser user = null;
					for (int i = 0; user == null && i < users.length; i++) {
						String name = users[i].getName();
						if (name.equals(imp.getContactName()) || name.equals(imp.getName())) {
							user = users[i];
							imp.setAD_User_ID(user.getAD_User_ID());
						}
					}
					if (user == null) {
						user = new MUser(bp);
						if (imp.getContactName() == null) {
							user.setName(imp.getName());
						} else {
							user.setName(imp.getContactName());
						}
						user.setEMail(imp.getEMail());
						user.setPhone(imp.getPhone());
						if (user.save()) {
							imp.setAD_User_ID(user.getAD_User_ID());
						}
					}
				}
				imp.save();
			} // for all new BPartners

			//
		} catch (SQLException e) {
			log.log(Level.SEVERE, "CreateBP", e);
		} finally {
			DB.close(rs, pstmt);
		}


		sql =
				new StringBuilder(UPD_INVOICE)
				.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No BPartner, ' ")
				.append("WHERE C_BPartner_ID IS NULL").append(SQL_IS_IMPORTED)
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);

		if (no != 0) {
			log.warning("No BPartner=" + no);
		}
	}

	/**
	 * Actualiza el id de producto apartir del value del producto en la tabla de interfaz
	 * @param clientCheck
	 */
	private void updateProduct(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Product
		sql = new StringBuilder (SQL_UPDINV_O)
			.append("SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p")
			.append(" WHERE o.ProductValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID ");

		if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        sql.append("WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL")
        	.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Product from Value=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p")
			.append(" WHERE o.UPC=p.UPC AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }

        sql.append("WHERE M_Product_ID IS NULL AND UPC IS NOT NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Product from UPC=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p")
			.append(" WHERE o.SKU=p.SKU AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }

        sql.append("WHERE M_Product_ID IS NULL AND SKU IS NOT NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Product fom SKU=" + no);

		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid Product, ' ")
			.append("WHERE M_Product_ID IS NULL AND (ProductValue IS NOT NULL OR UPC IS NOT NULL OR SKU IS NOT NULL)")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("Invalid Product=" + no);
		}
	}







	/**
	 * Actualiza el socio de negocio
	 * @param clientCheck
	 */
	private void updateBP(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	BP from EMail
//		sql = new StringBuilder(SQL_UPDINV_O)
//			.append("SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u")
//			.append(" WHERE o.EMail=u.EMail AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) ")
//			.append("WHERE C_BPartner_ID IS NULL AND EMail IS NOT NULL")
//			.append(SQL_IS_IMPORTED).append(clientCheck);
//		no = DB.executeUpdate(sql.toString(), null);
//		log.fine("Set BP from EMail=" + no);
//
//		//	BP from ContactName
//		sql = new StringBuilder(SQL_UPDINV_O)
//			.append("SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u")
//			.append(" WHERE upper(o.ContactName)=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) ")
//			.append("WHERE C_BPartner_ID IS NULL AND ContactName IS NOT NULL")
//			.append(" AND EXISTS (SELECT Name FROM AD_User u WHERE upper(o.ContactName)=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1)")
//			.append(SQL_IS_IMPORTED).append(clientCheck);
//		no = DB.executeUpdate(sql.toString(), null);
//		log.fine("Set BP from ContactName=" + no);

		//	BP from Value
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner bp")
			.append(" WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set BP from Value=" + no);

		//	Default BP
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_BPartner_ID=(SELECT C_BPartnerCashTrx_ID FROM AD_ClientInfo c")
			.append(" WHERE o.AD_Client_ID=c.AD_Client_ID) ")
			.append("WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NULL AND Name IS NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);


		// set bp location
		sql = new StringBuilder("UPDATE I_Invoice o \n")
		.append("SET C_BPartner_Location_ID = ( \n")
		.append("	SELECT C_BPartner_Location_ID \n")
		.append("	FROM C_BPartner_Location bpl \n")
		.append("	WHERE bpl.C_BPartner_ID = o.C_BPartner_ID) \n")
		.append("WHERE C_BPartner_ID IS NOT NULL \n")
		.append("	AND C_BPartner_Location_ID IS NULL \n")
		.append("	AND I_IsImported <> 'Y'");
		no = DB.executeUpdate(sql.toString(), null);


		sql = new StringBuilder(UPD_INVOICE)
		.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No BPartner, ' ")
		.append("WHERE C_BPartner_ID IS NULL")
		.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("No BPartner=" + no);
		}


		log.fine("Set Default BP=" + no);
	}

	private void updatePaymetTerm(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Payment Term
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm p")
			.append(" WHERE o.PaymentTermValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) ")
			.append("WHERE C_PaymentTerm_ID IS NULL AND PaymentTermValue IS NOT NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set PaymentTerm=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm p")
			.append(" WHERE p.IsDefault='Y' AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_PaymentTerm_ID IS NULL AND o.PaymentTermValue IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Default PaymentTerm=" + no);

		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No PaymentTerm, ' ")
			.append("WHERE C_PaymentTerm_ID IS NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("No PaymentTerm=" + no);
		}
	}

	/**
	 * actualiza la lista de precios
	 * @param clientCheck
	 */
	private void updatePriceList(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Price List
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList p WHERE p.IsDefault='Y'")
			.append(" AND p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE M_PriceList_ID IS NULL AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Default Currency PriceList=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList p WHERE p.IsDefault='Y'")
			.append(" AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Default PriceList=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList p ")
			.append(" WHERE p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE M_PriceList_ID IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Currency PriceList=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET M_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList p ")
			.append(" WHERE p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set PriceList=" + no);

		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No PriceList, ' ")
			.append("WHERE M_PriceList_ID IS NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning("No PriceList=" + no);
		}
	}

	private void updateSOTrx(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Set IsSOTrx
		sql = new StringBuilder("UPDATE I_Invoice o SET IsSOTrx='Y' ")
			.append("WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='ARI' AND d.DocSubTypeSO = 'SR' AND o.AD_Client_ID=d.AD_Client_ID)")
			.append(" AND C_DocType_ID IS NOT NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set IsSOTrx=Y=" + no);


		sql = new StringBuilder("UPDATE I_Invoice o SET IsSOTrx='N' ")
			.append("WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='API' AND o.AD_Client_ID=d.AD_Client_ID)")
			.append(" AND C_DocType_ID IS NOT NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set IsSOTrx=N=" + no);
	}

	/**
	 * Metodo para obtener el id del cargo apartir del value
	 */
	private void updateCharge() {
		int no = 0;

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_Invoice intrf ")
			.append("SET c_charge_id=(SELECT chg.c_charge_id FROM c_charge chg WHERE upper(chg.value) ")
			.append("= upper(intrf.chargename) AND AD_Client_ID = ? AND chg.isActive='Y' ");

		if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }

		no = DB.executeUpdate(sql.toString(), adClientId, null);
		log.fine("Set Charge =" + no);


		no =
				DB.getSQLValue(
						null,
						"SELECT count(*) FROM I_Invoice WHERE AD_Client_ID = ? AND C_Charge_ID IS NULL",
						adClientId
				);

		if(no > 0) {
			// didn't find the charge?, try looking for standard
			sql = new StringBuilder();
			sql.append("UPDATE I_Invoice SET C_Charge_ID = (")
			.append("select C_Charge_ID FROM C_Charge WHERE AD_Client_ID = ? ")
			.append(" and name = ?) WHERE AD_Client_ID = ?");

			no =
					DB.executeUpdate(
							sql.toString(),
							new Object[]{adClientId, Msg.getMsg(getCtx(), "Standard"), adClientId},
							null
							);

			log.fine("Set Charge =" + no);
		}
	}

	/**
	 * actualiza el id del tipo de documento apartir del value
	 * @param clientCheck
	 */
	private void updateDocType(final String clientCheck) {
		StringBuilder sql = new StringBuilder();
		int no;
		//	Document Type - PO - SO obtiene el id de doctype apartir del nombre
		sql.append(SQL_UPDINV_O)
			.append("SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName")
			.append(" AND d.DocBaseType IN ('API','APC') AND o.AD_Client_ID=d.AD_Client_ID AND o.AD_Org_ID = d.AD_Org_ID) ")
			.append("WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
			//.append("WHERE C_DocType_ID IS NULL AND IsSOTrx='N' AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.fine("Set PO DocType=" + no);
		}

		//obtiene el id de doctype apartir del nombre para los ARI y ARC
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName")
			.append(" AND d.DocBaseType = 'ARI' AND d.DocSubTypeSO = 'SR' AND o.AD_Client_ID=d.AD_Client_ID  AND o.AD_Org_ID = d.AD_Org_ID) ")
			.append("WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
			//.append("WHERE C_DocType_ID IS NULL AND IsSOTrx='Y' AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.fine("Set SO DocType=" + no);
		}


		//Guarda mensaje de error
		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid DocTypeName, ' ")
			.append("WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("Invalid DocTypeName=" + no);
		}

		//	DocType Default
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.IsDefault='Y'")
			.append(" AND d.DocBaseType='API' AND o.AD_Client_ID=d.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_DocType_ID IS NULL AND IsSOTrx='N' AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.fine("Set PO Default DocType=" + no);
		}

		//Actualiza el doctype_id para los docBaseType ARI
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.IsDefault='Y'")
			.append(" AND d.DocBaseType='ARI' AND o.AD_Client_ID=d.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_DocType_ID IS NULL AND IsSOTrx='Y' AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.fine("Set SO Default DocType=" + no);
		}

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.IsDefault='Y'")
			.append(" AND d.DocBaseType IN('ARI','API') AND o.AD_Client_ID=d.AD_Client_ID ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.fine("Set Default DocType=" + no);
		}

		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No DocType, ' ")
			.append("WHERE C_DocType_ID IS NULL")
			.append(SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("No DocType=" + no);
		}
	}




	//TODO:check later if those are going to be used


	/**
	 * Actualiza la localizacion el socio de negocios
	 * @param clientCheck
	 */
	private void updateBPLocation(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Existing Location ? Exact Match
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID")
			.append(" FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)")
			.append(" WHERE o.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=o.AD_Client_ID")
			.append(" AND DUMP(o.Address1)=DUMP(l.Address1) AND DUMP(o.Address2)=DUMP(l.Address2)")
			.append(" AND DUMP(o.City)=DUMP(l.City) AND DUMP(o.Postal)=DUMP(l.Postal)")
			.append(" AND o.C_Region_ID = l.C_Region_ID AND o.C_Country_ID = l.C_Country_ID) ")
			.append("WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL")
			.append(" AND I_IsImported='N'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Found Location=" + no);

		//	Set Location from BPartner
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID FROM C_BPartner_Location l")
			.append(" WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID")
			.append(" AND ((l.IsBillTo='Y' AND o.IsSOTrx='Y') OR o.IsSOTrx='N') ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
        sql.append("WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
			  + SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set BP Location from BP=" + no);

		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=No BP Location, ' ")
			.append("WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("No BP Location=" + no);
		}
	}

	/**
	 * Actualiza el country id apartir del countryCode
	 * @param clientCheck
	 */
	private void updateCountry(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Set Country
		/**
		sql = new StringBuilder (SQL_UPDINV_O
			  + "SET CountryCode=(SELECT CountryCode FROM C_Country c WHERE c.IsDefault='Y'"
			  + " AND c.AD_Client_ID IN (0, o.AD_Client_ID) AND ROWNUM=1) "
			  + "WHERE C_BPartner_ID IS NULL AND CountryCode IS NULL AND C_Country_ID IS NULL"
			  + SQL_IS_IMPORTED).append (clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Country Default=" + no);
		**/
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c")
			.append(" WHERE o.CountryCode=c.CountryCode AND c.AD_Client_ID IN (0, o.AD_Client_ID)) ")
			.append("WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL AND CountryCode IS NOT NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Country=" + no);
		//
		sql = new StringBuilder(UPD_INVOICE)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid Country, ' ")
			.append("WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("Invalid Country=" + no);
		}
	}

	/**
	 * Actualiza la region apartir del
	 * @param clientCheck
	 */
	private void updateRegion(final String clientCheck) {
		StringBuilder sql;
		int no;
		//	Set Region
		sql = new StringBuilder(SQL_UPDINV_O)
			.append("Set RegionName=(SELECT Name FROM C_Region r")
			.append(" WHERE r.IsDefault='Y' AND r.C_Country_ID=o.C_Country_ID")
			.append(" AND r.AD_Client_ID IN (0, o.AD_Client_ID) ");
        if (DB.isOracle()) {
        	sql.append(SQL_ROWNUM);
        } else if (DB.isPostgreSQL()) {
        	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
        	sql.append(") ");
        }
		sql.append("WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Region Default=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r")
			.append(" WHERE r.Name=o.RegionName AND r.C_Country_ID=o.C_Country_ID")
			.append(" AND r.AD_Client_ID IN (0, o.AD_Client_ID)) ")
			.append("WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NOT NULL")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Set Region=" + no);

		sql = new StringBuilder(SQL_UPDINV_O)
			.append("SET I_IsImported='E', I_ErrorMsg=coalesce(I_ErrorMsg, '')||'ERR=Invalid Region, ' ")
			.append("WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL ")
			.append(" AND EXISTS (SELECT * FROM C_Country c")
			.append(" WHERE c.C_Country_ID=o.C_Country_ID AND c.HasRegion='Y')")
			.append(SQL_IS_IMPORTED).append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		if (no != 0) {
			log.warning ("Invalid Region=" + no);
		}
	}


	private boolean createTaxLines(MInvoice invoice) {
		BigDecimal totalLines = Env.ZERO;
		ArrayList<Integer> taxList = new ArrayList<Integer>();
		MInvoiceLine[] lines = invoice.getLines(false);
		for (int i = 0; i < lines.length; i++) {
			MInvoiceLine line = lines[i];
			if (!taxList.contains(line.getC_Tax_ID())) {
				MInvoiceTax iTax =
						MInvoiceTax.get (line, invoice.getPrecision(), false, get_TrxName()); //	current Tax
				if (iTax != null) {
					iTax.setIsTaxIncluded(invoice.isTaxIncluded());
					if (iTax.calculateTaxFromLines(true)) {
						iTax.saveEx();
						taxList.add(line.getC_Tax_ID());
						line.setTaxAmt(iTax.getTaxAmt());
						line.setLineTotalAmt(line.getLineNetAmt().add(line.getTaxAmt()));
						line.save();
					} else {
						return false;
					}
				}
			}

			totalLines = totalLines.add(line.getLineNetAmt());
		}

		//	Taxes
		BigDecimal grandTotal = totalLines;
		MInvoiceTax[] taxes = invoice.getTaxes(true);
		for (int i = 0; i < taxes.length; i++)
		{
			MInvoiceTax iTax = taxes[i];
			MTax tax = iTax.getTax();
			if (tax.isSummary())
			{
				MTax[] cTaxes = tax.getChildTaxes(false);	//	Multiple taxes
				for (int j = 0; j < cTaxes.length; j++)
				{
					MTax cTax = cTaxes[j];
					BigDecimal taxAmt = cTax.calculateTax(iTax.getTaxBaseAmt(), invoice.isTaxIncluded(), invoice.getPrecision());
					//
					MInvoiceTax newITax = new MInvoiceTax(getCtx(), 0, get_TrxName());
					//newITax.setClientOrg(this);
					newITax.setC_Invoice_ID(invoice.getC_Invoice_ID());
					newITax.setC_Tax_ID(cTax.getC_Tax_ID());
					//newITax.setPrecision(invoice.getPrecision());
					newITax.setIsTaxIncluded(invoice.isTaxIncluded());
					newITax.setTaxBaseAmt(iTax.getTaxBaseAmt());
					newITax.setTaxAmt(taxAmt);
					newITax.saveEx(get_TrxName());
					//
					if (!invoice.isTaxIncluded())
						grandTotal = grandTotal.add(taxAmt);
				}
				iTax.deleteEx(true, get_TrxName());
			}
			else
			{
				if (!invoice.isTaxIncluded())
					grandTotal = grandTotal.add(iTax.getTaxAmt());
			}
		}

		return true;
	}

}	//	ImportInvoice