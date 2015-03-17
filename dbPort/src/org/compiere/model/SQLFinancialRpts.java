package org.compiere.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SQLFinancialRpts {

	/**
	 * Clase contenedora de consultas para reportes financieros
	 * 
	 * @author gderreza **18/05/2012**
	 **/

	
	public static StringBuilder demographicEncSQL() {
		StringBuilder sql = new StringBuilder("SELECT ")
		.append(" c.documentno as EncounterNo ")
		.append(" , (select name from EXME_TIPOPACIENTE P where P.EXME_TIPOPACIENTE_ID = c.EXME_TIPOPACIENTE_ID) as TypePatient ")
		.append(" , (select name from EXME_ADMITSOURCE ASR where ASR.EXME_ADMITSOURCE_ID = c.EXME_ADMITSOURCE_ID) as AdmitSource ")
		.append(" , c.Dateordered as AdmitDate ")
		.append(" , c.fechaalta as DischargeDate ")
		.append(" , (select name from exme_dischargestatus DS where ds.exme_dischargestatus_id = c.exme_dischargestatus_id) as DischargeStatus ")
		.append(" , MRN.documentno as MRN ")
		.append(" , p.nombre_pac as NAME")
		.append(" , p.sexo as Sex ");
		
		if (DB.isOracle()) {
			sql.append(" , trunc((sysdate - fechanac)/365.25,0)");
		} else if (DB.isPostgreSQL()) {
			//Funcion para traer la edad
			sql.append(" , date_part('year', age(fechanac))");			
		}
		
		sql.append(" as Age , p.TELPARTICULAR as Homephone ")
		.append(" , p.TELTRABAJO as WorkPhone ")
		.append(" , p.TELCELULAR as MobilePhone ")
		.append(" from exme_paciente P ")
		.append(" inner join exme_ctapac C on C.exme_paciente_id = P.exme_paciente_id AND C.IsActive = 'Y' ")
		.append(" inner join EXME_HIST_EXP MRN on c.EXME_PACIENTE_ID = MRN.EXME_PACIENTE_ID AND MRN.AD_CLient_ID = ? AND MRN.AD_Org_ID = ?")
		.append(" inner join exme_ctapacdatos ED on ed.exme_ctapac_id = c.exme_ctapac_id ");
		// le falta parentesis de cierre, para agregar las condiciones
		return sql;
	}

	/**
	 * Reporte de Ecounter Not Discharged
	 */
	public static String encountNotDisch() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  C.Documentno ");
		sql.append("  AS Documentno, ");
		sql.append("  HIS.DOCUMENTNO ");
		sql.append("  AS MRN, ");
		sql.append("  Pa.Nombre_Pac ");
		sql.append("  AS Nombre_Pac, ");
		sql.append("  C.DATEORDERED ");
		sql.append("  AS DateOrdered, ");
		sql.append("  PT.NAME ");
		sql.append("  AS Patienttype, ");
		sql.append("  Ar.name ");
		sql.append("  AS Service, ");
		sql.append("  Me.Name ");
		sql.append("  AS Nombre_Med ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE PA ");
		sql.append("  INNER JOIN EXME_CTAPAC C ");
		sql.append("  ON PA.EXME_PACIENTE_ID = C.EXME_PACIENTE_ID AND ");
		sql.append("  C.EncounterStatus NOT ");
		sql.append("  IN ('D' ) AND ");
		sql.append("  c.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = C.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID AND ");
		sql.append("  pas.ISACTIVE = ");
		sql.append("'Y' INNER JOIN C_BPARTNER BP ");
		sql.append("  ON BP.C_BPARTNER_ID = PAS.C_BPARTNER_ID AND ");
		sql.append("  BP.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID AND ");
		sql.append("  FC.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_MEDICO ME ");
		sql.append("  ON C.EXME_MEDICO2_ID = ME.EXME_MEDICO_ID AND ");
		sql.append("  Me.Isactive = ");
		sql.append("'Y' INNER JOIN Exme_Hist_Exp His ");
		sql.append("  ON PA.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  His.Isactive = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = C.AD_ORG_ID ");
		sql.append("  INNER JOIN EXME_AREA AR ");
		sql.append("  ON AR.EXME_AREA_ID = C.EXME_AREA_ID AND ");
		sql.append("  AR.ISACTIVE = 'Y' AND ");
		sql.append("  PA.ISACTIVE = 'Y' ");
		sql.append(" WHERE C.Ad_Org_Id IN (?) ");
		sql.append(" AND C.AD_CLIENT_ID IN (?) AND C.IsActive = 'Y' ");
		return sql.toString();
	}

	/**
	 * Reporte de Patient Registration Master
	 */
	public static String patientRegisMaster() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT");
		sql.append("  HIS.DOCUMENTNO, ");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  L.ADDRESS1, ");
		sql.append("  L.CITY, ");
		sql.append("  R.NAME ");
		sql.append("  AS REGIONNAME, ");
		sql.append("  L.POSTAL, ");
		sql.append("  P.TELPARTICULAR, ");
		sql.append("  P.FECHANAC, ");
		sql.append("  P.IMSS, ");
		sql.append("  (SELECT ");
		sql.append("      name ");
		sql.append("    FROM ");
		sql.append("      AD_REF_LIST ");
		sql.append("    WHERE ");
		sql.append("      value = CON.resstatus AND ");
		sql.append("      ad_reference_id = ? ");
		sql.append("  ) ");
		sql.append("  AS RESSTATUS, ");
		sql.append("  P.EMAIL ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN C_LOCATION L ");
		sql.append("  ON P.C_LOCATION_ID = L.C_LOCATION_ID AND ");
		sql.append("  L.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  HIS.AD_ORG_ID IN (?) ");
		sql.append("  INNER JOIN C_REGION R ");
		sql.append("  ON L.C_REGION_ID = R.C_REGION_ID AND ");
		sql.append("  R.ISACTIVE = 'Y' LEFT ");
		sql.append("  JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND CTA.IsActive = 'Y' LEFT ");
		sql.append("  JOIN EXME_CONSENTIMIENTOPAC CON ");
		sql.append("  ON CTA.EXME_CTAPAC_ID = CON.EXME_CTAPAC_ID ");
		return sql.toString();
	}
	
	/**
	 * 
	 * Reporte Unbilled Extensions
	 */
	
	public static String getCharges() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select NOMBRE_PAC, ENCOUNTER, EXME_TIPOPACIENTE_ID, \n");
		sql.append(" EXME_FinancialClass_ID, EXTENSIONNO, patientValue, patienType, MRN, Ad_Org_Id, Ad_Client_Id, \n");
		sql.append(" DATEORDERED, DISDATE, qtytotal, linetotal, FCLASS, PAYERNAME, CLAIMTYPE,  \n");
		sql.append(" CODINGDATE, TYPERECORD \n ");
		sql.append(" FROM (\n ");
		sql.append("	with CPD as (select sum(t1.qtydelivered) as qtytotal, sum(t1.linenetamt) as linetotal, \n");
		sql.append("			t1.exme_ctapacext_id, \n");
		sql.append("			t1.conftype, \n");
		sql.append("			count(*) as totalreg, \n"); 
		sql.append("			max(t1.nopricecheck) as noprice \n"); 
		sql.append("		from   (select qtydelivered, \n");
		sql.append("				linenetamt, \n");
		sql.append("				exme_ctapacext_id, \n"); 
		sql.append("				CASE WHEN fnc_isprofessional(fnc_getproductorg(m_product_id, ad_org_id), ad_org_id) = 'Y' \n"); 
		sql.append("					THEN 'Professional' \n");
		sql.append("					ELSE 'Institutional' \n");
		sql.append("				END AS CONFTYPE, \n");  
		sql.append("				CASE WHEN CALCULARPRECIO = 'N' THEN 1 ELSE 0 END AS nopricecheck \n");  
		sql.append("			from exme_ctapacdet  \n");
		sql.append("			where isactive = 'Y' \n");
		sql.append("				and ad_client_id = (?) and ad_org_id = (?)) t1 \n"); // 1,2 
		sql.append("		group by t1.exme_ctapacext_id, t1.conftype), \n");
		sql.append("		NO_CHARGES as ( \n");
		sql.append("			SELECT cp.EXME_CtaPac_ID \n"); 
		sql.append("			FROM EXME_CtaPac cp \n");
		sql.append("			WHERE AD_Client_ID = (?) and AD_Org_ID = (?) AND IsActive = 'Y' AND \n"); //3,4
		sql.append("			( ");
		sql.append("				SELECT COUNT(*) FROM EXME_CtaPacDet WHERE EXME_CtaPac_ID = cp.EXME_CtaPac_ID "); 
		sql.append("			) = 0 ");
		sql.append("		) \n");
		sql.append("	SELECT P.NOMBRE_PAC, CP.DOCUMENTNO AS ENCOUNTER, TP.EXME_TIPOPACIENTE_ID, \n");
		sql.append("		FC.EXME_FinancialClass_ID, EXTENSIONNO, TP.NAME AS patienType, TP.VALUE AS patientValue, HE.DOCUMENTNO AS MRN, cp.Ad_Org_Id, cp.Ad_Client_Id, \n");
		sql.append("		CP.DATEORDERED, CP.FECHAALTA AS DISDATE, cpd.qtytotal, cpd.linetotal, \n");
		sql.append("		CASE WHEN FC.EXME_FinancialClass_id IS NULL THEN 'SELF PAY' ELSE FC.NAME END AS FCLASS, \n"); 
		sql.append("		ASEG.NAME AS PAYERNAME, CT.CONFTYPE AS CLAIMTYPE,\n ");
		sql.append("		CASE WHEN CT.CONFTYPE = 'Institutional' \n");
		sql.append("			THEN CP.codingdate \n");
		sql.append("			ELSE CP.codingdateprof \n"); 
		sql.append("		END AS CODINGDATE, \n");
		sql.append("		CASE \n");
		sql.append("			WHEN cpd.noprice = 1 THEN 'CNP' \n"); 
		sql.append("			WHEN cpd.totalreg > 0 THEN 'CU' \n");
		sql.append("			ELSE NULL \n");
		sql.append("		END AS TYPERECORD \n");
		sql.append("	FROM EXME_CTAPAC CP \n");
		sql.append("  		inner join ad_orginfo oi ON oi.ad_org_id = CP.ad_org_id   ");
		sql.append("		INNER JOIN (SELECT 'Institutional' AS CONFTYPE, 'IN' AS BILLING UNION SELECT 'Professional', 'PR') CT ON \n"); 
		sql.append("		(OI.SUPPORTBILLING IN ('BO', 'IN') AND CT.CONFTYPE = 'Institutional' OR OI.SUPPORTBILLING IN ('BO', 'PR') AND CT.CONFTYPE = 'Professional') \n"); 
		sql.append("		INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID AND CP.ISACTIVE = 'Y' \n");
		sql.append("		INNER JOIN EXME_CTAPACEXT CPE ON CP.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID \n");
		sql.append("		INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID \n");  
		sql.append("		INNER JOIN EXME_HIST_EXP HE ON HE.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND HE.AD_ORG_ID = CP.AD_ORG_ID \n");  
		sql.append("		INNER JOIN CPD ON CPE.EXME_CTAPACEXT_ID = CPD.EXME_CTAPACEXT_ID AND CPD.CONFTYPE = CT.CONFTYPE \n");
		sql.append("		LEFT  JOIN EXME_PACIENTEASEG PASEG ON PASEG.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID \n"); 
		sql.append("			AND PASEG.SUPPORTBILLING = CT.BILLING AND PASEG.PRIORITY = 1 \n");
		sql.append("		LEFT JOIN C_BPARTNER ASEG ON ASEG.C_BPARTNER_ID = PASEG.C_BPARTNER_ID \n");  
		sql.append("		LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = ASEG.EXME_FINANCIALCLASS_ID \n"); 
		sql.append("	WHERE  cp.Ad_Client_Id IN (?) AND  cp.Ad_Org_Id IN (?) and cp.IsActive = 'Y' \n"); //5,6
		sql.append("		and cpe.exme_ctapacext_id NOT IN \n");
		sql.append("		(SELECT EXME_CTAPACEXT_ID  \n");
		sql.append("		 FROM EXME_CTAPACEXT  \n");
		sql.append("		 WHERE EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID \n");  
		sql.append("			AND EXTENSIONNO > 0 AND ISACTIVE = 'Y' \n");
		sql.append("			AND ad_client_id = (?) and ad_org_id = (?) \n"); //7,8
		sql.append("		) ");
		sql.append(") as charges ");

		
		return sql.toString();
		
	}
	
	public static String getNotCharges() {
		StringBuilder sql = new StringBuilder();
		/* get encounters with no charges */
		sql.append(" select NOMBRE_PAC, ENCOUNTER, EXME_TIPOPACIENTE_ID, ");
		sql.append(" EXME_FinancialClass_ID, EXTENSIONNO, patientValue, patienType, MRN, Ad_Org_Id, Ad_Client_Id, ");
		sql.append(" DATEORDERED, DISDATE, qtytotal, linetotal, FCLASS, PAYERNAME, CLAIMTYPE, ");
		sql.append(" CODINGDATE, TYPERECORD \n");
		sql.append(" FROM (\n");
		sql.append("	with NO_CHARGES as (\n");
		sql.append("			SELECT cp.EXME_CtaPac_ID \n");
		sql.append("			FROM EXME_CtaPac cp\n");
		sql.append("			WHERE AD_Client_ID = (?) and AD_Org_ID = (?) AND IsActive = 'Y' AND \n");// 1,2
		sql.append("			(");
		sql.append("				SELECT COUNT(*) FROM EXME_CtaPacDet WHERE EXME_CtaPac_ID = cp.EXME_CtaPac_ID ");
		sql.append("			) = 0");
		sql.append("		)\n");
		sql.append("	SELECT P.NOMBRE_PAC, CP.DOCUMENTNO AS ENCOUNTER, TP.EXME_TIPOPACIENTE_ID,\n");
		sql.append("		FC.EXME_FinancialClass_ID, EXTENSIONNO, TP.NAME AS patienType, TP.VALUE AS patientValue, HE.DOCUMENTNO AS MRN, cp.Ad_Org_Id, cp.Ad_Client_Id,\n");
		sql.append("		CP.DATEORDERED, CP.FECHAALTA AS DISDATE, 0 as qtytotal, 0 as linetotal,\n");
		sql.append("		CASE WHEN FC.EXME_FinancialClass_id IS NULL THEN 'SELF PAY' ELSE FC.NAME END AS FCLASS, \n");
		sql.append("		ASEG.NAME AS PAYERNAME, CT.CONFTYPE AS CLAIMTYPE, \n");
		sql.append("		CASE WHEN CT.CONFTYPE = 'Institutional' \n");
		sql.append("			THEN CP.codingdate \n");
		sql.append("			ELSE CP.codingdateprof \n");
		sql.append("		END AS CODINGDATE, ");
		sql.append("		'NC'::varchar AS TYPERECORD ");
		sql.append("	FROM EXME_CTAPAC CP \n");
		sql.append("  		inner join ad_orginfo oi ON oi.ad_org_id = CP.ad_org_id   ");
		sql.append("		INNER JOIN (SELECT 'Institutional' AS CONFTYPE, 'IN' AS BILLING UNION SELECT 'Professional', 'PR') CT ON \n");
		sql.append("		(OI.SUPPORTBILLING IN ('BO', 'IN') AND CT.CONFTYPE = 'Institutional' OR OI.SUPPORTBILLING IN ('BO', 'PR') AND CT.CONFTYPE = 'Professional') \n");
		sql.append("		INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID AND CP.ISACTIVE = 'Y' \n");
		sql.append("		INNER JOIN EXME_CTAPACEXT CPE ON CP.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID \n");
		sql.append("		INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID  \n");
		sql.append("		INNER JOIN EXME_HIST_EXP HE ON HE.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND HE.AD_ORG_ID = CP.AD_ORG_ID  \n");
		sql.append("		INNER  JOIN NO_CHARGES nc ON (CP.EXME_CtaPac_ID = nc.EXME_CtaPac_ID) \n");
		sql.append("		LEFT  JOIN EXME_PACIENTEASEG PASEG ON PASEG.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID \n");
		sql.append("			AND PASEG.SUPPORTBILLING = CT.BILLING AND PASEG.PRIORITY = 1 \n");
		sql.append("		LEFT JOIN C_BPARTNER ASEG ON ASEG.C_BPARTNER_ID = PASEG.C_BPARTNER_ID  \n");
		sql.append("		LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = ASEG.EXME_FINANCIALCLASS_ID \n");
		sql.append("	WHERE  cp.Ad_Client_Id IN (?) AND  cp.Ad_Org_Id IN (?) and cp.IsActive = 'Y' \n");// 3,4
		sql.append("		and cpe.exme_ctapacext_id NOT IN \n");
		sql.append("		(SELECT EXME_CTAPACEXT_ID  \n");
		sql.append("		 FROM EXME_CTAPACEXT  \n");
		sql.append("		 WHERE EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID  \n");
		sql.append("			AND EXTENSIONNO > 0 AND ISACTIVE = 'Y' \n");
		sql.append("			AND ad_client_id = (?) and ad_org_id = (?) ");// 5,6
		sql.append("		)\n");
		sql.append(" ) AS no_charges ");

		return sql.toString();
	}
	
	/**
	 * Reporte de Census by Date
	 */
	public static String censusbyDate() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  estserv.name ");
		sql.append("  AS unit, ");
		sql.append("  CA.NAME ");
		sql.append("  AS bed, ");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  CTA.DOCUMENTNO ");
		sql.append("  AS ENCOUNTER, ");
		sql.append("  HIS.DOCUMENTNO ");
		sql.append("  AS mrn, ");
		sql.append("  P.sexo, ");
		sql.append("  P.FECHANAC, ");
		sql.append("  substring(PT.name, ");
		sql.append("  0, ");
		sql.append("  4) ");
		sql.append("  AS patientType, ");
		sql.append("  substring(AR.name, ");
		sql.append("  0, ");
		sql.append("  10) ");
		sql.append("  AS SERVICE, ");
		sql.append("  DAT.ARRIVALDATE, ");
		sql.append("  cTA.dateordered ");
		sql.append("  AS admit, ");
		sql.append("  CTA.Diagnostico_Ingreso ");
		sql.append("  AS chiefComplaint, ");
		sql.append("  admi.name ");
		sql.append("  AS admitSource, ");
		sql.append("  MED.apellido1 ");
		sql.append("  AS PhysicianName, ");
		sql.append("  case when p.copyResponsible = 'Y' then 'Self Pay' else FC.NAME end ");
		sql.append("  AS FCLASS ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = 'Y' left ");
		sql.append("  JOIN EXME_ADMITSOURCE admi ");
		sql.append("  ON cta.EXME_ADMITSOURCE_ID = admi.EXME_ADMITSOURCE_ID LEFT ");
		sql.append("  JOIN EXME_PACIENTEASEG PASEG ");
		sql.append("  ON PASEG.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
		sql.append("  PASEG.SUPPORTBILLING = 'IN' AND ");
		sql.append("  PASEG.PRIORITY = 1 LEFT ");
		sql.append("  JOIN C_BPARTNER ASEG ");
		sql.append("  ON ASEG.C_BPARTNER_ID = PASEG.C_BPARTNER_ID LEFT ");
		sql.append("  JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = ASEG.EXME_FINANCIALCLASS_ID ");
		sql.append("  INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  his.ISACTIVE = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = CTA.AD_ORG_ID ");
		sql.append("  INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_AREA AR ");
		sql.append("  ON AR.EXME_AREA_ID = CTA.EXME_AREA_ID AND ");
		sql.append("  AR.ISACTIVE = 'Y' left ");
		sql.append("  JOIN EXME_MEDICO MED ");
		sql.append("  ON MED.EXME_MEDICO_ID = CTA.EXME_MEDICO_ID AND ");
		sql.append("  MED.ISACTIVE = 'Y' LEFT ");
		sql.append("  JOIN EXME_CAMA CA ");
		sql.append("  ON CTA.EXME_CAMA_ID = CA.EXME_CAMA_ID left ");
		sql.append("  JOIN exme_habitacion hab ");
		sql.append("  ON ca.exme_habitacion_id = hab.exme_habitacion_id left ");
		sql.append("  JOIN exme_estserv estserv ");
		sql.append("  ON hab.exme_estserv_id = estserv.exme_estserv_id ");
		sql.append("  INNER JOIN EXME_CTAPACDATOS dat ");
		sql.append("  ON DAT.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
		sql.append("  DAT.ISACTIVE = 'Y' AND ");
		sql.append("  DAT.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID left ");
		sql.append("  JOIN exme_admittype adt ");
		sql.append("  ON cta.exme_admittype_id = adt.exme_admittype_id ");
		sql.append("WHERE ");
		sql.append("  Cta.Ad_Org_Id IN (?) AND ");
		sql.append("  Cta.Ad_Client_Id IN (?) AND ");
		sql.append("  cta.IsActive = 'Y' ");
		
		return sql.toString();
	}
	
	/**
	 * Reporte ER Log
	 */
	public static String eRLog() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  HIS.DOCUMENTNO ");
		sql.append("  AS mrn, ");
		sql.append("  CTA.DOCUMENTNO ");
		sql.append("  AS ENCOUNTER, ");
		sql.append("  DAT.ARRIVALDATE, ");
		sql.append("  TO_CHAR(ARRIVALDATE,'HH:MI PM' ) ");
		sql.append("  AS TIMEARRIVE, ");
		sql.append("  CTA.DATEORDERED ");
		sql.append("  AS ADMIT, ");
		sql.append("  TO_CHAR(CTA.DATEORDERED,'HH:MI PM' ) ");
		sql.append("  AS TIMEAdmit, ");
		sql.append("  CTA.FECHAALTA, ");
		sql.append("  TO_CHAR(CTA.FECHAALTA,'HH:MI PM' ) ");
		sql.append("  AS TIMEDISCHAR, ");
		sql.append("  DC.name, ");
		sql.append("  PT.name ");
		sql.append("  AS PATIENTTYPE, ");
		sql.append("  (SELECT ");
		sql.append("      name ");
		sql.append("    FROM ");
		sql.append("      EXME_ADMITTYPE eat ");
		sql.append("    WHERE ");
		sql.append("      eat.EXME_ADMITTYPE_ID = CTA.EXME_ADMITTYPE_ID ");
		sql.append("  ) ");
		sql.append("  AS ADMITtYPE, ");
		sql.append("  (SELECT ");
		sql.append("      name ");
		sql.append("    FROM ");
		sql.append("      EXME_ADMITSOURCE ASR ");
		sql.append("    WHERE ");
		sql.append("      ASR.EXME_ADMITSOURCE_ID = CTA.EXME_ADMITSOURCE_ID ");
		sql.append("  ) ");
		sql.append("  AS ADMITSOURCE, ");
		sql.append("  fc.name ");
		sql.append("  AS financialClass ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  his.ISACTIVE = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = CTA.AD_ORG_ID ");
		sql.append("  INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = 'Y' left ");
		sql.append("  JOIN EXME_DISCHARGESTATUS DC ");
		sql.append("  ON DC.EXME_DISCHARGESTATUS_ID = CTA.EXME_DISCHARGESTATUS_ID ");
		sql.append("  INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID AND ");
		sql.append("  PAS.ISACTIVE = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  pas.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = paS.c_bpartner_id ");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON fc.EXME_FINANCIALCLASS_id = bp.EXME_FINANCIALCLASS_id ");
		sql.append("  INNER JOIN EXME_CTAPACDATOS DAT ");
		sql.append("  ON DAT.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID ");
		sql.append(" WHERE Cta.Ad_Org_Id IN (?) ");
		sql.append(" AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ");
		return sql.toString();
	}
	/**
	 * Discharge eCs modify
	 */
	public static String dischargeECmodify(String filters){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("ENCOUNTER, NOMBRE_PAC, name, SERVICE,  DATEORDERED, FECHAALTA, los,  FINANCIALCLASS, Code, PhysicianName, MRN  ");
		sql.append("from ( ");  
		
		sql.append("SELECT ");
		sql.append("  CTA.DOCUMENTNO AS ENCOUNTER,");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  PT.name, ");
		sql.append("  AR.name  AS SERVICE, ");
		sql.append("  CTA.DATEORDERED, CTA.FECHAALTA,");
		
		if (DB.isOracle()) {
			sql.append(" (CASE WHEN(TRUNC(CTA.FECHAALTA) -TRUNC(CTA.DATEORDERED)) = 0 THEN 1 ELSE TRUNC(CTA.FECHAALTA) -TRUNC(CTA.DATEORDERED) END) los, ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" CASE WHEN DATE_PART('day', fechaalta::date::timestamp - dateordered::date::timestamp) = 0 THEN 1 ");
			sql.append(" ELSE DATE_PART('day', fechaalta::date::timestamp - dateordered::date::timestamp) END as los, ");
		}
		
		sql.append("  Coalesce(FC.name, FCS.Name)  AS FINANCIALCLASS, ");
		sql.append(" DC.NAME as Code,");
		sql.append("  CPM.PHYSICIANNAME AS PhysicianName, ");
		sql.append("  HIS.DOCUMENTNO AS MRN ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = 'Y'");
		sql.append("  AND CTA.ENCOUNTERSTATUS = '").append(X_EXME_CtaPac.ENCOUNTERSTATUS_Discharge).append("' ");
		sql.append(" LEFT JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  his.ISACTIVE = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = CTA.AD_ORG_ID ");
		sql.append("  LEFT JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE =  'Y'");
		sql.append("  LEFT JOIN (EXME_PACIENTEASEG PAS");
		sql.append("  INNER JOIN c_bpartner BP   ON BP.c_bpartner_id = paS.c_bpartner_id");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID");
		sql.append("  ) ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID");
		sql.append("  AND   PAS.ISACTIVE = 'Y' AND   PAS.PRIORITY = 1 AND   pas.SUPPORTBILLING= 'IN'");
		sql.append("  INNER JOIN (c_bpartner BPS");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FCS ON FCS.EXME_FINANCIALCLASS_ID = BPS.EXME_FINANCIALCLASS_ID");
		sql.append("  ) ON BPS.c_bpartner_id = p.c_bpartner_id ");
		sql.append("  LEFT JOIN EXME_CTAPACMED CPM ");
		sql.append("  ON CPM.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
		sql.append("  CPM.ISACTIVE = 'Y' ");
		sql.append("  and CPM.type = '").append(X_EXME_CtaPacMed.TYPE_Attending).append("' ");
		sql.append("  LEFT JOIN EXME_AREA AR ");
		sql.append("  ON AR.EXME_AREA_ID = CTA.EXME_AREA_ID AND ");
		sql.append("  AR.ISACTIVE = 'Y' ");
		sql.append("  LEFT JOIN EXME_DISCHARGESTATUS DC ");
		sql.append("  ON CTA.EXME_DISCHARGESTATUS_ID = DC.EXME_DISCHARGESTATUS_ID AND DC.ISACTIVE = 'Y' ");
		sql.append(" WHERE Cta.AD_CLIENT_ID = ?");
		sql.append(" AND  Cta.Ad_Org_Id = ? AND CTA.IsActive = 'Y'");
		
		if(StringUtils.isNotEmpty(filters)){
			sql.append(filters);
		}
		sql.append(" ) subQ ");
		
		return sql.toString();
	}

	/**
	 * Reporte Payment Batch Detail
	 */
	public static StringBuilder getPaymentsBatchDetail(String supportBilling, boolean firstPass){
		StringBuilder sql = new StringBuilder();
		
		if(firstPass){
			sql.append("SELECT * FROM ( ");
		}
		sql.append("SELECT CP.EXME_CTAPAC_ID, CPE.EXME_CTAPACEXT_ID, CP.DOCUMENTNO AS encounterNo, CPE.EXTENSIONNO as extensionNo, ")
		   .append(" P.NOMBRE_PAC as patientName, TP.name AS patientType,   CP.FECHAALTA as dischargeDate, FC.name   AS fcName, ")
		   .append(" ARL.NAME  as type, cpe.totallines as totalCharges, MIN(CPD.CREATED) AS transDate,  C.NAME AS transName, TP.Value as ptValue, ")
		   .append(" SUM(CPD.AMT_PAID) AS transAmt, FC.value as fcValue, ARL.VALUE as billingType, C.Value as transCode, CPH.DOCUMENTNO AS batchNo ")
		   .append(" FROM   EXME_CLAIMPAYMENTH CPH ")
		   .append(" INNER JOIN EXME_CLAIMPAYMENT CPD ON CPD.EXME_CLAIMPAYMENTH_ID = CPH.EXME_CLAIMPAYMENTH_ID AND CPD.ISACTIVE = 'Y' ")
		   .append(" INNER JOIN C_BPARTNER CB ON CB.C_BPARTNER_ID = CPH.C_BPARTNER_ID ")
		   .append(" LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
		   .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = CPD.EXME_CTAPAC_ID ")
		   .append(" INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID ")
		   .append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		   .append(" LEFT JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPACEXT_ID = CPD.EXME_CTAPACEXT_ID ")
		   .append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = CPD.C_CHARGE_ID ")
		   .append(" LEFT JOIN AD_REF_LIST ARL ON ARL.ad_reference_id = ?  AND ARL.VALUE = ? ")
		   .append(" WHERE CPH.AD_CLIENT_ID = ? AND CPH.AD_ORG_ID =? AND CPH.ISACTIVE = 'Y' AND CPH.BILLINGTYPE = ? ")
		   .append(" GROUP BY CP.EXME_CTAPAC_ID, CPE.EXME_CTAPACEXT_ID, CP.DOCUMENTNO, CPE.EXTENSIONNO, P.NOMBRE_PAC,   TP.name, ")
		   .append(" CP.FECHAALTA, FC.name,  ARL.NAME, cpe.totallines, C.NAME, tp.value, FC.value, ARL.VALUE, C.Value, CPH.DOCUMENTNO  ");
		
		if(firstPass ){
			if (MOrgInfo.SUPPORTBILLING_Both.equalsIgnoreCase(supportBilling)){
				sql.append(" UNION ALL ");
				sql.append(getPaymentsBatchDetail(supportBilling, Boolean.FALSE));
			}
			sql.append(" ) AS T ");
			
		}
		return sql;
	}
	/**
	 * Encounter Detail Report
	 */
	public static String encountersDetailReport(){

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  CTA.DOCUMENTNO ");
		sql.append("  AS ENCOUNTER, ");
		sql.append("  HIS.DOCUMENTNO ");
		sql.append("  AS MRN, ");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  (SELECT ");
		sql.append("      name ");
		sql.append("    FROM ");
		sql.append("      EXME_ADMITTYPE eat ");
		sql.append("    WHERE ");
		sql.append("      eat.EXME_ADMITTYPE_ID = CTA.EXME_ADMITTYPE_ID ");
		sql.append("  ) ");
		sql.append("  AS ADMITTYPE, ");
		sql.append("  PT.name, ");
		sql.append("  (SELECT ");
		sql.append("      name ");
		sql.append("    FROM ");
		sql.append("      EXME_ADMITSOURCE ASR ");
		sql.append("    WHERE ");
		sql.append("      ASR.EXME_ADMITSOURCE_ID = CTA.EXME_ADMITSOURCE_ID ");
		sql.append("  ) ");
		sql.append("  AS ADMITSOURCE, ");
		sql.append("  DAT.ARRIVALDATE, ");
		sql.append("  CTA.DATEORDERED, ");
		sql.append("  CTA.FECHAALTA, ");
		
		
		if (DB.isOracle()) {
			sql.append("  (CASE WHEN(TRUNC(CTA.FECHAALTA) -TRUNC(CTA.DATEORDERED)) = 0 THEN 1 ELSE TRUNC(CTA.FECHAALTA) -TRUNC(CTA.DATEORDERED) END) LOS, ");
		} else if (DB.isPostgreSQL()) {
			sql.append("  (CASE WHEN((extract (epoch from (CTA.FECHAALTA - CTA.DATEORDERED)))/86400)::integer = 0 ");//60seg x 60min x24hrs = 86400
			sql.append("  THEN 1 ELSE ((extract (epoch from (CTA.FECHAALTA - CTA.DATEORDERED)))/86400)::integer END) as LOS, ");
		}
		
		sql.append("  DC.NAME ");
		sql.append("  AS Code, ");
		sql.append("  AR.name ");
		sql.append("  AS SERVICE, ");
		sql.append("  ADMIT.NAME ");
		sql.append("  AS ADMIT, ");
		sql.append("  ME.name ");
		sql.append("  AS PHYSICIANNAME, ");
		sql.append("  PFC.NAME ");
		sql.append("  AS FINANCIALCLASS, ");
		sql.append("  Ppc.name ");
		sql.append("  AS payerclass ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  his.ISACTIVE = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = CTA.AD_ORG_ID ");
		sql.append("  INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = 'Y' INNER ");
		sql.append("  JOIN EXME_MEDICO ADMIT ");
		sql.append("  ON ADMIT.EXME_MEDICO_ID = CTA.EXME_MEDICO_ID AND ");
		sql.append("  ADMIT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_AREA AR ");
		sql.append("  ON AR.EXME_AREA_ID = CTA.EXME_AREA_ID AND ");
		sql.append("  AR.ISACTIVE = ");
		sql.append("'Y' LEFT JOIN EXME_DISCHARGESTATUS DC ");
		sql.append("  ON CTA.EXME_DISCHARGESTATUS_ID = DC.EXME_DISCHARGESTATUS_ID AND ");
		sql.append("  DC.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_MEDICO ME ");
		sql.append("  ON CTA.EXME_MEDICO2_ID = ME.EXME_MEDICO_ID AND ");
		sql.append("  ME.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_CTAPACDATOS DAT ");
		sql.append("  ON DAT.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID ");
		sql.append("  INNER JOIN ");
		//Obtenemos los FC
		sql.append("  (SELECT DISTINCT ");
		sql.append("      CP.EXME_CTAPAC_ID, ");
		if (DB.isOracle()) {
			sql.append("      DECODE(PPA.EXME_PACIENTEASEG_ID, ");
			sql.append("      NULL, ");
			sql.append("      P.C_BPARTNER_ID, ");
			sql.append("      PPA.C_BPARTNER_ID) PRIMARY_CBPARTNER_ID ");
		} else if (DB.isPostgreSQL()) {

		    sql.append("      CASE WHEN PPA.EXME_PACIENTEASEG_ID is ");
			sql.append("      NULL THEN ");
			sql.append("      P.C_BPARTNER_ID ELSE ");
			sql.append("      PPA.C_BPARTNER_ID END as PRIMARY_CBPARTNER_ID ");
		}	
		sql.append("    FROM ");
		sql.append("      EXME_CTAPAC CP ");
		sql.append("      INNER JOIN EXME_PACIENTE P ");
		sql.append("      ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID LEFT ");
		sql.append("      JOIN EXME_PACIENTEASEG PA ");
		sql.append("      ON PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND ");
		sql.append("      PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND ");
		sql.append("      PA.SUPPORTBILLING = 'IN' LEFT ");
		sql.append("      JOIN EXME_PACIENTEASEG PPA ");
		sql.append("      ON PPA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND ");
		sql.append("      PPA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND ");
		sql.append("      PPA.PRIORITY = 1 AND ");
		sql.append("      PPA.SUPPORTBILLING = ");
		sql.append("'IN' ) PC ");
		//Terminamos de preguntar por los FC
		sql.append("  ON PC.EXME_CTAPAC_ID = CtA.EXME_CTAPAC_ID LEFT ");
		sql.append("  JOIN C_BPARTNER PI ");
		sql.append("  ON PI.C_BPARTNER_ID = PC.PRIMARY_CBPARTNER_ID LEFT ");
		sql.append("  JOIN EXME_FINANCIALCLASS PFC ");
		sql.append("  ON PFC.EXME_FINANCIALCLASS_ID = PI.EXME_FINANCIALCLASS_ID LEFT ");
		sql.append("  JOIN EXME_PAYERCLASS PPC ");
		sql.append("  ON PPC.EXME_FINANCIALCLASS_ID = PFC.EXME_FINANCIALCLASS_ID AND ");
		sql.append("  PI.EXME_PAYERCLASS_ID = PPC.EXME_PAYERCLASS_ID ");
		sql.append(" WHERE Cta.Ad_Org_Id IN (?) ");
		sql.append(" AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ");
		return sql.toString();		
	}
	
	/**
	 * Encounter Credit Balance
	 */
	public static String encounterCreditBalance(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( ")
		   .append("SELECT CTA.EXME_CtaPac_ID, CTA.DOCUMENTNO AS ENCOUNTER, P.NOMBRE_PAC, ")
		   .append(" CTA.DATEORDERED, CTA.FECHAALTA, PT.NAME, FC.NAME AS FCNAME, PC.NAME AS PCNAME, FC.VALUE AS FCVALUE, PC.VALUE AS PCVALUE, ")
		   .append("(SELECT ARL.NAME ")
		   .append("FROM AD_Ref_List ARL ")
		   .append("WHERE ARL.Value = Cta.EncounterStatus AND ARL.ad_reference_id = ?)   AS status, ")
		   .append("( (SELECT COALESCE(SUM(CPD.LineNetAmt), 0) ")
		   .append("FROM EXME_CTAPACDET CPD ")
		   .append("WHERE CPD.EXME_CtaPac_ID = Cta.EXME_CtaPac_ID AND CPD.IsActive = 'Y' ) - ")
		   .append("(SELECT COALESCE(SUM(P.PayAmt), 0) ")
		   .append("FROM C_Payment P ")
		   .append("INNER JOIN C_Charge CC on CC.C_Charge_ID = P.C_Charge_ID AND CC.Type NOT IN (?,?,?) ")
		   .append("WHERE P.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND P.ISACTIVE = 'Y' )) AS BALANCE ")
		   .append("FROM   EXME_CTAPAC CTA ")
		   .append("INNER JOIN EXME_PACIENTE P  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND CTA.ISACTIVE = 'Y' ")
		   .append("INNER JOIN EXME_PACIENTEASEG PASEG ON PASEG.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND PASEG.SUPPORTBILLING = 'IN' and PASEG.PRIORITY = 1 ")
		   .append("LEFT JOIN C_BPARTNER ASEG ON ASEG.C_BPARTNER_ID = PASEG.C_BPARTNER_ID  ")
		   .append("LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = ASEG.EXME_FINANCIALCLASS_ID ")
		   .append("LEFT JOIN EXME_PAYERCLASS PC ON PC.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID AND ASEG.EXME_PAYERCLASS_ID = PC.EXME_PAYERCLASS_ID  ")
		   .append("INNER JOIN EXME_TIPOPACIENTE PT   ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND   PT.ISACTIVE = 'Y' ")
		   .append("INNER JOIN EXME_DISCHARGESTATUS DC ON CTA.EXME_DISCHARGESTATUS_ID = DC.EXME_DISCHARGESTATUS_ID  ")
		   .append("AND   DC.ISACTIVE = 'Y' ")
		   .append("WHERE CTA.AD_ORG_ID    IN (?) AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ) AS LISTA ")
		   .append("WHERE Lista.Balance <0 ");
		return sql.toString();
	}
	/**
	 * Reporte Cashiering payment Details
	 * @param totals si pregunta por el detalle del reporte
	 * @param abrev alias de la tabla(abreviacion)
	 * @param filters
	 * @return query a ejecutar
	 */

	public static String cashieringPaymentDetails(String filters){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("  CTA.DOCUMENTNO   AS ENCOUNTER,   P.NOMBRE_PAC,  CTA.FECHAALTA,  PT.name,   FC.name   AS FINANCIALCLASS, ");
		sql.append("  CPAY.DATETRX,   CC.NAME AS TransCode,   CPAY.PAYAMT,   CPAY.RECIBONO,   OP.name   AS USUARIO ");			
		sql.append(" FROM C_CASHLINE LINE    ");			
		sql.append("  ");			
		sql.append(" INNER JOIN C_PAYMENT CPAY ON CPAY.C_PAYMENT_ID = LINE.C_PAYMENT_ID ");			
		sql.append(" INNER JOIN C_CHARGE CC ON CC.C_CHARGE_ID = CPAY.C_CHARGE_ID ");			
		sql.append(" INNER JOIN C_CASH CASH   ON CASH.C_CASH_ID = LINE.C_CASH_ID AND   CASH.ISACTIVE = 'Y' ");			
		sql.append(" INNER  JOIN C_CASHBOOK BOOK   ON BOOK.C_CASHBOOK_ID = CASH.C_CASHBOOK_ID AND   BOOK.ISACTIVE = 'Y' ");			
		sql.append(" INNER  JOIN EXME_OPERADOR OP   ON OP.EXME_OPERADOR_ID = CASH.EXME_OPERADOR_ID AND   op.ISACTIVE = 'Y' ");			
		sql.append(" INNER JOIN EXME_CtaPac CTA ON CTA.EXME_CtaPac_ID = LINE.EXME_CtaPac_ID ");			
		sql.append(" INNER JOIN EXME_PACIENTE P   ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND   CTA.ISACTIVE = 'Y' ");			
		sql.append(" INNER JOIN EXME_TIPOPACIENTE PT   ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND   PT.ISACTIVE = 'Y' ");			
		sql.append(" INNER JOIN c_bpartner BP   ON BP.c_bpartner_id = p.c_bpartner_id ");			
		sql.append(" INNER JOIN EXME_FINANCIALCLASS FC   ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID");
		sql.append(" WHERE ");
		sql.append("  Cta.Ad_Org_Id IN (?) ");
		sql.append(" AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ");
		if(StringUtils.isNotEmpty(filters))
			sql.append(" AND ").append(filters);
		return sql.toString();
	}
	/**
	 * Observation log
	 */
	public static String observationLog(boolean totals, StringBuilder filters){
		StringBuilder sql = new StringBuilder();
		if(totals){
			sql.append("SELECT ");
			sql.append("  FC.NAME, ");
			
			if (DB.isOracle()) {
				sql.append("  SUM(FLOOR(((CTA.FECHAALTA - CTA.DATEORDERED) *24))) HORAS, ");
			} else if (DB.isPostgreSQL()) {
				sql.append("  SUM(FLOOR((extract(day from (CTA.FECHAALTA - CTA.DATEORDERED)) *24))) as HORAS, ");
			}
			
			sql.append("  COUNT(FC.name) encountot ");
		
		}else{
			sql.append("SELECT DISTINCT ");
			sql.append("  CTA.DOCUMENTNO ");
			sql.append("  AS ENCOUNTER, ");
			sql.append("  P.NOMBRE_PAC, ");
			sql.append("  CTA.DATEORDERED, ");
			sql.append("  TO_CHAR(CTA.DATEORDERED, 'HH:MI PM') ");
			sql.append("  AS TIMEADMIT, ");
			sql.append("  CTA.FECHAALTA,");
			sql.append("  TO_CHAR(CTA.FECHAALTA, 'HH:MI PM') AS DCTIME,");
			
			if (DB.isOracle()) {
				sql.append("  floor(((CTA.FECHAALTA - CTA.DATEORDERED) *24)) HORAS, ");
			} else if (DB.isPostgreSQL()) {
				//FORMATO HORAS ENTERO
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/14/2012 09:13:00  
				//RESULTADO = 48
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/12/2012 09:13:00  
				//RESULTADO = 0
				//sql.append(" ((extract (epoch from (CTA.FECHAALTA - CTA.DATEORDERED)))/3600)::integer as HORAS, ");
				//SE MANTIENE ESTA COLUMNA PARA FILTER
				sql.append(" ((extract (epoch from (CTA.FECHAALTA - CTA.DATEORDERED)))/3600)::integer as HORASVAL, ");
				//FORMATO X DAYS HH24:MM:SS
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/14/2012 09:13:00  
				//RESULTADO = 2 DAYS 00:58:00
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/12/2012 09:13:00  
				//RESULTADO = 00:58:00
				//sql.append(" age(fechaalta, dateordered) as HORAS, ") ;
				
				//FORMATO HH:MM:SS
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/14/2012 09:13:00  
				//RESULTADO = 48:58:00
				//EJEMPLO DATEORDERED 04/12/2012 08:15:00 FECHAALTA 04/12/2012 09:13:00  
				//RESULTADO = 00:58:00
				sql.append(" (EXTRACT(EPOCH FROM fechaalta) - EXTRACT(EPOCH FROM dateordered)) * INTERVAL '1 SECOND' AS HORAS, ");
			}		
			
			sql.append("  DC.NAME ");
			sql.append("  AS status, ");
			sql.append("  PT.name, ");
			sql.append("  FC.NAME ");
			sql.append("  AS FINANCIALCLASS, ");
			sql.append("  (select name from EXME_ADMITSOURCE ASR where ASR.EXME_ADMITSOURCE_ID = cta.EXME_ADMITSOURCE_ID) as AdmitSource, ");
			sql.append("  HIS.DOCUMENTNO ");
			sql.append("  AS MRN ");
		}		
		
		sql.append("  FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  his.ISACTIVE = 'Y' AND ");
		sql.append("  HIS.AD_ORG_ID = CTA.AD_ORG_ID ");
		sql.append("  INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID AND ");
		sql.append("  PAS.ISACTIVE = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  pas.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = PAS.c_bpartner_id ");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID ");
		sql.append("  AND FC.ISACTIVE = 'Y' ");
		sql.append("  INNER JOIN EXME_DISCHARGESTATUS DC ");		
		sql.append("  ON CTA.EXME_DISCHARGESTATUS_ID = DC.EXME_DISCHARGESTATUS_ID AND ");
		sql.append("  DC.ISACTIVE = 'Y' ");
		sql.append("  WHERE Cta.Ad_Org_Id In (?) " );
		sql.append(" AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ");
		
		if(totals){
			sql.append(filters);
			sql.append(" GROUP BY ");
			sql.append("  FC.NAME ");
			sql.append(" ORDER BY FC.NAME");
		}
		
		return sql.toString();
	}

	public static StringBuilder getRptFinancialClass(StringBuilder where, boolean all){
		StringBuilder sql = new StringBuilder(" SELECT * ")
		   .append(" FROM (WITH BALANCE_CTAPAC AS (SELECT ((SELECT coalesce(SUM(CPD.LineNetAmt), 0) ")
		   .append(" FROM EXME_CtaPacDet CPD ")
		   .append(" INNER JOIN EXME_ConfigPre PRE ")
		   .append(" ON PRE.AD_Org_ID = CPD.AD_Org_ID ")
		   .append(" AND PRE.IsActive = 'Y' ")
		   .append(" INNER JOIN EXME_ProductoOrg PO ")
		   .append(" ON PO.EXME_ProductoOrg_ID = fnc_getProductOrg(CPD.M_Product_ID , CPD.AD_Org_ID) ")
		   .append(" WHERE CPD.EXME_CtaPac_ID = CP.EXME_CtaPac_ID ")
		   .append(" AND PRE.Exme_ModifiersProf_ID ")
		   .append(" NOT IN ")
		   .append(" (Coalesce(PO.EXME_Modifier1_ID,0), ")
		   .append(" Coalesce(PO.EXME_Modifier2_ID,0), Coalesce(PO.EXME_Modifier3_ID,0), ")
		   .append(" Coalesce(PO.EXME_Modifier4_ID,0))) - ")
		   .append(" (SELECT coalesce(SUM(P.PayAmt), 0) ")
		   .append(" FROM C_Payment P ")
		   .append(" INNER JOIN C_Charge CC ON CC.C_Charge_ID = P.C_Charge_ID ")
		   .append(" WHERE P.EXME_CtaPac_ID = ")
		   .append(" CP.EXME_CtaPac_ID ")
		   .append(" AND P.IsActive = 'Y' ")
		   .append(" AND P.ConfType =  ")
		   .append(DB.TO_STRING(MEXMEBatchClaimH.CONFTYPE_InstitutionalClaim))
		   .append(" AND CC.Type Not IN (")
		   .append(DB.TO_STRING(MCharge.TYPE_Coinsurance)).append(",")
		   .append(DB.TO_STRING(MCharge.TYPE_CoPay)).append(",")
		   .append(DB.TO_STRING(MCharge.TYPE_Deductible)).append("))) BALANCE, ")
		   .append(" CP.EXME_CTAPAC_ID ")
		   .append(" FROM EXME_CTAPAC CP) ")
		   .append(" SELECT SUM(BC.BALANCE) as total, ")
		   .append(" COUNT(C.EXME_CtaPac_ID) as Encounters ");
		if (all) {
		   sql.append(", FCI.Name as FinancialClass ");
		}
		sql.append(" FROM EXME_CtaPac C ")
		   .append(" INNER JOIN EXME_PACIENTE PA ")
		   .append(" ON PA.EXME_Paciente_ID = C.EXME_Paciente_ID ")
     	   .append(" INNER JOIN EXME_TipoPaciente PT ")
		   .append(" ON PT.EXME_TipoPaciente_ID = C.EXME_TipoPaciente_ID ")
		   .append(" LEFT  JOIN EXME_PacienteAseg PASI ")
		   .append(" ON PASI.IsActive = 'Y' ")
		   .append(" AND PASI.EXME_Paciente_ID = C.EXME_Paciente_ID ")
		   .append(" AND PASI.EXME_CtaPac_ID = C.EXME_CtaPac_ID ")
		   .append(" AND PASI.Priority = 1 ")
		   .append(" AND PASI.SupportBilling =  ")
		   .append(DB.TO_STRING(MEXMEPacienteAseg.SUPPORTBILLING_Institucional))
		   .append(" INNER JOIN C_BPartner BPI ")
		   .append(" ON BPI.C_BPartner_ID = PASI.C_BPartner_ID ")
		   .append(" LEFT  JOIN EXME_FinancialClass FCI ")
		   .append(" ON FCI.EXME_FinancialClass_ID = BPI.EXME_FinancialClass_ID ")
		   .append(" LEFT  JOIN EXME_PayerClass PCI ")
		   .append(" ON PCI.EXME_PayerClass_ID = BPI.EXME_PayerClass_ID ")
		   .append(" LEFT  JOIN EXME_PacienteAseg PASP ")
		   .append(" ON PASP.IsActive = 'Y' ")
		   .append(" AND PASP.EXME_Paciente_ID = C.EXME_Paciente_ID ")
		   .append(" AND PASP.EXME_CtaPac_ID = C.EXME_CtaPac_ID ")
		   .append(" AND PASP.Priority = 1 ")
		   .append(" AND PASP.SupportBilling =  ")
		   .append(DB.TO_STRING(MEXMEPacienteAseg.SUPPORTBILLING_Professional))
		   .append(" INNER JOIN C_BPartner BPP ")
		   .append(" ON BPP.C_BPartner_ID = PASP.C_BPartner_ID ")
		   .append(" LEFT  JOIN EXME_FinancialClass FCP ")
		   .append(" ON FCP.EXME_FinancialClass_ID = BPP.EXME_FinancialClass_ID ")
		   .append(" LEFT  JOIN EXME_PayerClass PCP ")
		   .append(" ON PCP.EXME_PayerClass_ID = BPP.EXME_PayerClass_ID ")
		   .append(" LEFT  JOIN EXME_DischargeStatus DC ")
		   .append(" ON DC.EXME_DischargeStatus_ID = C.EXME_DischargeStatus_ID ")
		   .append(" INNER JOIN Balance_CtaPac BC ")
		   .append(" ON BC.EXME_CtaPac_ID = C.EXME_CtaPac_ID ")
		   .append(" LEFT JOIN (EXME_BatchClaimD BD ")
		   .append(" INNER JOIN EXME_BatchClaimH BH ")
		   .append(" ON BH.EXME_BatchClaimH_ID = BD.EXME_BatchClaimH_ID) ")
		   .append(" ON BD.EXME_CtaPac_ID = C.EXME_CtaPac_ID ")
		   .append(" AND BH.CONFTYPE in(  ")
		   .append(DB.TO_STRING(MEXMEBatchClaimH.CONFTYPE_InstitutionalClaim))
		   .append(",")
		   .append(DB.TO_STRING(MEXMEBatchClaimH.CONFTYPE_PIStatement));
		   
		   sql.append(" ) AND BH.IsActive = 'Y' ")
		   .append(" WHERE C.AD_CLient_ID = ? ")
		   .append(" AND C.AD_Org_ID = ? ")
		   .append(" AND C.IsActive = 'Y'");
		
		if (!all) {
			sql.append(" AND FCI.Value <> 'SP' ");
		}
		
		if (StringUtils.isNotEmpty(where.toString())) {
			sql.append(where);
		}
		if (all) {
			sql.append(" Group By FCI.Name ");
		}
		sql.append(" )");
		return sql;
	}	

	
	/**
	 * Encounter payer group
	 */	
	public static String encountersPayerGroup(boolean totals, StringBuilder filters){

		StringBuilder sql = new StringBuilder();
		if(totals){
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  (WITH BALANCE_CTAPAC ");
			sql.append("  AS ");
			sql.append("  (SELECT ");
			sql.append("      ( ");
			sql.append("      (SELECT ");
//			sql.append("          coalesce(SUM(CPD.LineNetAmt), 0) ");
			if(DB.isOracle()){
				sql.append("      coalesce(SUM(CPD.LineNetAmt), 0)  ");
			}else if(DB.isPostgreSQL()){
				sql.append("      COALESCE(SUM(CPD.LineNetAmt), 0)  ");
			}
			sql.append("        FROM ");
			sql.append("          EXME_CtaPacDet CPD ");
			sql.append("        WHERE ");
			sql.append("          CPD.EXME_CtaPac_ID = Cta.EXME_CtaPac_ID AND ");
			sql.append("          CPD.IsActive = 'Y' AND ");
			sql.append("          CPD.isFacturado = ");
			sql.append("'N' ) - ");
			sql.append("      (SELECT ");			
			if(DB.isOracle()){
				sql.append("          coalesce(SUM(A.total), 0) ");
			}else if(DB.isPostgreSQL()){
				sql.append("      COALESCE(SUM(A.total), 0) ");
			}
			sql.append("        FROM ");
			sql.append("          EXME_ANTICIPO a ");
			sql.append("        WHERE ");
			sql.append("          A.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
			sql.append("          A.ISACTIVE = ");
			sql.append("'Y' )) BALANCE, ");
			sql.append("      CTA.EXME_CTAPAC_ID ");
			sql.append("    FROM ");
			sql.append("      EXME_CTAPAC CTA ");
			sql.append("  ) ");
			sql.append("SELECT ");
			sql.append("  Pc.name, ");
			sql.append("  COUNT(CTA.EXME_CTAPAC_ID) ");
			sql.append("  AS COUNTER, ");
			sql.append("  SUM(BC.BALANCE) ");
			sql.append("  AS CHARGE ");
		}else{
			sql.append("SELECT ");
			sql.append("  CTA.DOCUMENTNO ");
			sql.append("  AS ENCOUNTER, ");
			sql.append("  P.NOMBRE_PAC, ");
			sql.append("  CTA.DATEORDERED, ");
			sql.append("  CTA.FECHAALTA, ");
			sql.append("  PT.NAME, ");
			sql.append("  PC.NAME ");
			sql.append("  AS PAYERCLASS, ");
			sql.append(" (SELECT NAME FROM AD_REF_LIST WHERE VALUE = CTA.INSTITUTIONALSTEP AND AD_REFERENCE_ID = ? ) AS ENCINSSTEP,");
			sql.append(" (SELECT name FROM AD_REF_LIST WHERE value = cTA.professionalstep AND ad_reference_id = ? ) AS ENCPROFSTEP,");
			sql.append("(SELECT ");
			sql.append("      ARL.NAME ");
			sql.append("    FROM ");
			sql.append("      AD_REF_LIST ARL ");
			sql.append("    WHERE ");
			sql.append("      ARL.VALUE = CTA.ENCOUNTERSTATUS AND ");
			sql.append("      AD_REFERENCE_ID = ? ");
			sql.append("  ) ");
			sql.append("  AS STATUS, ");
			sql.append("  (SELECT ");
			sql.append("      name ");
			sql.append("    FROM ");
			sql.append("      AD_REF_LIST ");
			sql.append("    WHERE ");
			sql.append("      value = bch.conftype AND ");
			sql.append("      ad_reference_id = ? ");
			sql.append("  ) ");
			sql.append("  AS claimType,( ");
			sql.append("  (SELECT ");
			if(DB.isOracle()){
				sql.append("      coalesce(SUM(CPD.LineNetAmt), 0) ");
			}else if(DB.isPostgreSQL()){
				sql.append("      COALESCE(SUM(CPD.LineNetAmt), 0) ");//FIXME
			}			
			sql.append("    FROM ");
			sql.append("      EXME_CTAPACDET CPD ");
			sql.append("    WHERE ");
			sql.append("      CPD.EXME_CtaPac_ID = Cta.EXME_CtaPac_ID AND ");
			sql.append("      CPD.IsActive = 'Y' AND ");
			sql.append("      CPD.isFacturado = ");
			sql.append("'N' ) - ");
			sql.append("  (SELECT ");
			if(DB.isOracle()){
				sql.append("      coalesce(SUM(A.total), 0) ");
			}else if(DB.isPostgreSQL()){
				sql.append("      COALESCE(SUM(A.total), 0) ");//FIXME
			}
			sql.append("    FROM ");
			sql.append("      EXME_ANTICIPO a ");
			sql.append("    WHERE ");
			sql.append("      a.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
			sql.append("      a.ISACTIVE = ");
			sql.append("'Y' )) Balance ");
		}		
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID AND ");
		sql.append("  PAS.ISACTIVE = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  pas.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = paS.c_bpartner_id ");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID ");
		sql.append("  INNER JOIN EXME_PAYERCLASS PC ");
		sql.append("  ON PC.EXME_PAYERCLASS_ID = BP.EXME_PAYERCLASS_ID AND ");
		sql.append("  PC.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_BatchClaimd BCD ");
		sql.append("  ON bcd.exme_ctapac_id = CTA.EXME_CTAPAC_ID ");
		sql.append("  INNER JOIN EXME_BatchClaimh bch ");
		sql.append("  ON bch.exme_batchclaimh_id = bcd.exme_batchclaimh_id ");
		if(totals){
			sql.append(" INNER JOIN BALANCE_CTAPAC BC  ");
			sql.append(" ON BC.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID  ");
		}
		sql.append("  WHERE Cta.Ad_Org_Id In (?) " );
		sql.append(" AND Cta.Ad_Client_Id IN (?) AND cta.IsActive = 'Y' ");
		if(StringUtils.isNotEmpty(filters.toString()))
			sql.append(filters);
		if(totals)
			sql.append("GROUP BY Pc.name) sbq");
		return sql.toString();
	}
	
	public static String chargeRecap(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  CTA.DOCUMENTNO ");
		sql.append("  AS ENCOUNTER, ");
		sql.append("  P.NOMBRE_PAC, ");
		sql.append("  CTA.FECHAALTA, ");
		sql.append("  PT.NAME, ");
		sql.append("  FC.NAME ");
		sql.append("  AS FINANCIALCLASS, ");
		sql.append("  CPM.PHYSICIANNAME ");
		sql.append("  AS PHYSICIANNAME, ");
		sql.append("  MPR.VALUE ");
		sql.append("  AS ITEMNUM, ");
		sql.append("  mpr.name ");
		sql.append("  AS itemName, ");
		sql.append("  REV.VALUE ");
		sql.append("  AS REVENUEcODE ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID AND ");
		sql.append("  PAS.ISACTIVE = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  pas.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = paS.c_bpartner_id ");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID LEFT ");
		sql.append("  JOIN EXME_CTAPACMED CPM ");
		sql.append("  ON CPM.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
		sql.append("  CPM.EXME_MEDICO_ID = CTA.EXME_MEDICO_ID AND ");
		sql.append("  CPM.ISACTIVE = 'Y' LEFT ");
		sql.append("  JOIN EXME_CTAPACDET DET ");
		sql.append("  ON DET.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
		sql.append("  DET.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_REVENUECODE REV ");
		sql.append("  ON DET.EXME_REVENUECODE_ID = REV.EXME_REVENUECODE_ID AND ");
		sql.append("  Rev.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_PRODUCTOORG POR ");
		sql.append("  ON REV.EXME_REVENUECODE_ID = POR.EXME_REVENUECODE_ID AND ");
		sql.append("  POR.ISACTIVE = ");
		sql.append("'Y' INNER JOIN M_PRODUCT MPR ");
		sql.append("  ON POR.M_PRODUCT_ID = MPR.M_PRODUCT_ID AND ");
		sql.append("  MPR.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_HIST_EXP HIS ");
		sql.append("  ON P.EXME_PACIENTE_ID = HIS.EXME_PACIENTE_ID AND ");
		sql.append("  HIS.AD_ORG_ID IN (?) ");
		return sql.toString();
	}
	
	public static String dischargeSummary(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  PT.NAME AS PACTYPE, ");
		sql.append("   PT.VALUE AS PACVALUE, ");
		sql.append("  sts.value as VALUE ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND ");
		sql.append("  CTA.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = CTA.EXME_TIPOPACIENTE_ID AND ");
		sql.append("  PT.ISACTIVE = ");
		sql.append("'Y' INNER JOIN EXME_DISCHARGESTATUS STS ");
		sql.append("  ON CTA.EXME_DISCHARGESTATUS_ID = STS.EXME_DISCHARGESTATUS_ID ");
		return sql.toString();		
	}
	
	public static String financialClassEnounters(boolean filter, StringBuilder filters){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  FC.NAME, ");
		
		if (DB.isOracle()) {
			sql.append("  SUM(floor(((CTA.FECHAALTA - CTA.DATEORDERED) *24))) horas, ");
		} else if (DB.isPostgreSQL()) {
			sql.append("  SUM(floor((extract(day from (CTA.FECHAALTA - CTA.DATEORDERED)) *24))) as horas, ");
		}
		
		sql.append("  COUNT(FC.name) encountot  ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE P ");
		sql.append("  INNER JOIN EXME_CTAPAC CTA ");
		sql.append("  ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID AND cta.IsActive = 'Y' ");
		sql.append("  INNER JOIN EXME_PACIENTEASEG PAS ");
		sql.append("  ON PAS.EXME_CTAPAC_ID = Cta.EXME_CTAPAC_ID AND ");
		sql.append("  PAS.ISACTIVE = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  pas.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = PAS.c_bpartner_id ");
		sql.append("  INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON FC.EXME_FINANCIALCLASS_ID = BP.EXME_FINANCIALCLASS_ID ");
		if(filter)
			sql.append(" WHERE ").append(filters);
			
		sql.append("GROUP BY ");
		sql.append("  FC.NAME ");
		sql.append(" ORDER BY FC.NAME");
		return sql.toString();
	}
	
	public static String payersGroup(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  (WITH BALANCE_CTAPAC ");
		sql.append("  AS ");
		sql.append("  (SELECT ");
		if(DB.isOracle()){
			sql.append("      ( ");
			sql.append("      (SELECT ");
			sql.append("          coalesce(SUM(CPD.LineNetAmt), ");
			sql.append("          0) ");
			sql.append("        FROM ");
			sql.append("          EXME_CtaPacDet CPD ");
			sql.append("        WHERE ");
			sql.append("          CPD.EXME_CtaPac_ID = Cta.EXME_CtaPac_ID AND ");
			sql.append("          CPD.IsActive = 'Y' AND ");
			sql.append("          CPD.isFacturado = ");
			sql.append("'N' ) - ");
			sql.append("      (SELECT ");
			sql.append("          coalesce(SUM(A.total), ");
			sql.append("          0) ");
			sql.append("        FROM ");
			sql.append("          EXME_ANTICIPO a ");
			sql.append("        WHERE ");
			sql.append("          A.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
			sql.append("          A.ISACTIVE = ");
			sql.append("'Y' )) BALANCE, ");
		}else if(DB.isPostgreSQL()){
			sql.append("      ( ");
			sql.append("      (SELECT ");
			sql.append("          COALESCE(SUM(CPD.LineNetAmt), ");
			sql.append("          0) ");
			sql.append("        FROM ");
			sql.append("          EXME_CtaPacDet CPD ");
			sql.append("        WHERE ");
			sql.append("          CPD.EXME_CtaPac_ID = Cta.EXME_CtaPac_ID AND ");
			sql.append("          CPD.IsActive = 'Y' AND ");
			sql.append("          CPD.isFacturado = ");
			sql.append("'N' ) - ");
			sql.append("      (SELECT ");
			sql.append("          COALESCE(SUM(A.total), ");
			sql.append("          0) ");
			sql.append("        FROM ");
			sql.append("          EXME_ANTICIPO a ");
			sql.append("        WHERE ");
			sql.append("          A.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID AND ");
			sql.append("          A.ISACTIVE = ");
			sql.append("'Y' )) BALANCE, ");
		}
		sql.append("      CTA.EXME_CTAPAC_ID ");
		sql.append("    FROM ");
		sql.append("      EXME_CTAPAC CTA ");
		sql.append("  ) ");
		sql.append("SELECT ");
		sql.append("  Pc.name, ");
		sql.append("  COUNT(C.EXME_CTAPAC_ID) ");
		sql.append("  AS ENCOUNTERS, ");
		sql.append("  SUM(BC.BALANCE) ");
		sql.append("  AS total ");
		sql.append("FROM ");
		sql.append("  EXME_PACIENTE PA ");
		sql.append("  INNER JOIN EXME_CTAPAC C ");
		sql.append("  ON PA.EXME_PACIENTE_ID = C.EXME_PACIENTE_ID AND c.IsActive = 'Y' ");
		sql.append("  INNER JOIN EXME_TIPOPACIENTE PT ");
		sql.append("  ON PT.EXME_TIPOPACIENTE_ID = C.EXME_TIPOPACIENTE_ID ");
		sql.append("  INNER JOIN exme_pacienteaseg PAS ");
		sql.append("  ON paS.exme_ctapac_id = c.exme_ctapac_id ");
		sql.append("  INNER JOIN c_bpartner BP ");
		sql.append("  ON BP.c_bpartner_id = paS.c_bpartner_id AND ");
		sql.append("  paS.isactive = 'Y' AND ");
		sql.append("  PAS.PRIORITY = 1 AND ");
		sql.append("  PAS.SUPPORTBILLING= ");
		sql.append("'IN' INNER JOIN EXME_FINANCIALCLASS FC ");
		sql.append("  ON fc.EXME_FINANCIALCLASS_id = bp.EXME_FINANCIALCLASS_id LEFT ");
		sql.append("  JOIN EXME_PAYERCLASS PC ");
		sql.append("  ON PC.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID ");
		sql.append("  INNER JOIN BALANCE_CTAPAC BC ");
		sql.append("  ON BC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ");
		sql.append("GROUP BY ");
		sql.append("  PC.NAME ");
		sql.append("ORDER BY ");
		sql.append("  pc.name) ");
		return sql.toString();
	}

	public static String encounterStatusSummarySQL() {
		StringBuilder sql = new StringBuilder("")

		.append("Select ETP.Name, ETP.Tipo,   ")
		.append("   count(INS.EXME_CtaPac_ID) Discharged, count(nc.exme_ctapac_id) NullCharges, Coalesce(Sum(Ins.Total),0) Discharged_Total, ")
		.append("count(cc.exme_ctapac_id) Coded, Coalesce(Sum(cc.Total),0) CodedTotal, ")
		.append("count(uc.exme_ctapac_id) NotCoded, Coalesce(Sum(uc.Total),0) NotCodedTotal, ")
		.append("count(bc.exme_ctapac_id) PendingPayer, Coalesce(Sum(bc.Total),0) PendingPayerTotal, ")
		.append("count(sc.exme_ctapac_id) Statements, Coalesce(Sum(sc.Total),0) StatementsTotal, ")
		.append("count(xc.exme_ctapac_id) Exceptions, Coalesce(Sum(xc.Total),0) ExceptionsTotal ")
		.append("FROM (SELECT TP.AD_Org_ID, TP.EXME_TipoPaciente_ID, TP.Name, TP.Value, ")
		.append("LISTA.TIPO  ")
		.append("FROM EXME_TipoPaciente TP, (SELECT 'UNKNOWN' TIPO FROM DUAL  ")
		.append("UNION SELECT 'SELF PAY' FROM DUAL  ")
		.append("UNION SELECT 'INSURANCE' FROM DUAL) LISTA ")
		.append("WHERE TP.AD_CLIENT_ID = ? AND TP.AD_Org_ID = ? ) ETP ")
		.append("INNER JOIN EXME_CTAPAC C ON C.AD_ORG_ID = ETP.AD_ORG_ID AND C.EXME_TIPOPACIENTE_ID = ETP.EXME_TIPOPACIENTE_ID AND c.IsActive = 'Y' ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		.append("NULL,?,?)").append(DB.isOracle()?")":"").append(") INS ") 
		.append("ON INS.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND INS.Tipo = ETP.Tipo  AND INS.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		.append("NULL,?,?)").append(DB.isOracle()?")":"").append(") NC  ")
		.append("ON NC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND NC.Tipo = ETP.Tipo AND NC.TOTAL IS NULL AND NC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		.append("'''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingComplete+"''',?,?)").append(DB.isOracle()?")":"").append(") CC ") //4
		.append("ON CC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND CC.Tipo = ETP.Tipo  AND CC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		//"'''1'', ''2'', ''3'''
		.append("'''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled+"'', ''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable+"'', " +
				"''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete+"''',?,?)").append(DB.isOracle()?")":"").append(") UC ") 
		.append("ON UC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND UC.Tipo = ETP.Tipo AND UC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		.append("'''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_SelfPay+"''',?,?)").append(DB.isOracle()?")":"").append(")  SC ") //19
		.append("ON  SC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND SC.Tipo = ETP.Tipo AND SC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
		//MEXMECtaPac.in

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		//"'''5'', ''6'', ''7'', ''11'', ''12'''"
		.append("'''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete+"'', ''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero+"'', " +
				"''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields+"'', ''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMessage997+"'', " +
						"''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorRSPFile+"''',?,?)").append(DB.isOracle()?")":"").append(")  XC ") 
		.append("ON  XC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND XC.Tipo = ETP.Tipo AND XC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("LEFT JOIN (SELECT * FROM ").append(DB.isOracle()?"TABLE(":"").append("fnc_getTotalTipoCta(?,?,?,?,'Y', ")
		.append("'''"+MEXMECtaPacExt.INSTITUTIONALSTATUS_WaitingInsurancePayments+"''',?,?)").append(DB.isOracle()?")":"").append(") BC ") 
		.append("ON  BC.EXME_TipoPaciente_ID = etp.EXME_TipoPaciente_Id AND BC.Tipo = ETP.Tipo AND BC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")

		.append("GROUP BY  ETP.Name, ETP.Tipo ")
		.append("ORDER BY ETP.Name");//MHL7MessageConf.CONFTYPE_InstitutionalClaim, MEXMEPacienteAseg.SUPPORTBILLING_Institucional
		return sql.toString();
	}
	
	public static String getAgingByFC(String supportBilling){
		
		boolean isUnion = Boolean.FALSE;
		
		
		//if (MOrgInfo.SUPPORTBILLING_Institucional.equals(supportBilling)){
		StringBuilder sql = new StringBuilder(" SELECT *  FROM   ( ")
		 .append(" WITH CTX_DATA AS (SELECT ? AS SUPPORTBILLING_INST, ")//IN
        .append("                    ? AS SUPPORTBILLING_PROF, ")//PR
        .append("                    ? AS AD_CLIENT_ID, ")//10001000
        .append("                    ? AS AD_ORG_ID, ")//10001051
        .append("                    ? AS CONFTYPE_INST, ")//T
        .append("                    ? AS CONFTYPE_PROF ")//P
        .append("               FROM DUAL),")
        
        
         .append(" CHARGES_CTAPAC AS (SELECT CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID, ")
         .append("               SUM(CASE ")
         .append("                    WHEN (PO.ISPROFESSIONAL = 'Y' OR OI.SUPPORTBILLING = T.SUPPORTBILLING_PROF) ")
         .append("                     THEN ")
         .append("                       COALESCE(CPD.LINENETAMT,0) ")
         .append("                     ELSE ")
         .append("                       0 ")
         .append("                  END) AS CHARGES_PROF, ")
         .append("               SUM(CASE  ")
         .append("                       WHEN (PO.ISPROFESSIONAL = 'N' AND OI.SUPPORTBILLING !=  T.SUPPORTBILLING_PROF) ")
         .append("                          THEN  ")
         .append("                            COALESCE(CPD.LINENETAMT,0) ")
         .append("                          ELSE ")
         .append("                            0 ")
         .append("                  END) AS CHARGES_INST ")
         .append("      FROM EXME_CTAPACDET CPD ")
         .append("      INNER JOIN EXME_PRODUCTOORG PO ")
         .append("      ON PO.EXME_PRODUCTOORG_ID = ")
         .append("      fnc_getproductorg(CPD.M_PRODUCT_ID,CPD.AD_ORG_ID) ")
         .append("      INNER JOIN AD_ORGINFO OI  ")
         .append("            ON OI.AD_ORG_ID = CPD.AD_ORG_ID, ")
         .append("      CTX_DATA T ")
         .append("      WHERE CPD.AD_CLIENT_ID =T.AD_CLIENT_ID ")
         .append("      AND CPD.AD_ORG_ID = T.AD_ORG_ID ")
         .append("      AND CPD.ISACTIVE = 'Y' ")
         .append("      GROUP BY CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID), ")
         
         .append(" PAYMENT_CTAPAC AS (SELECT P.EXME_CTAPAC_ID, COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID, SUM(CASE ") 
         .append("                                 WHEN p.CONFTYPE = T.CONFTYPE_PROF ") 
         .append("                                      THEN P.PAYAMT  ")
         .append("                                      ELSE 0  ")
         .append("                                 END) AS PAYMENT_PROF, ")
         .append("                             SUM(CASE  ")
         .append("                                 WHEN p.CONFTYPE = T.CONFTYPE_INST ") 
         .append("                                      THEN P.PAYAMT  ")
         .append("                                      ELSE 0  ")
         .append("                                END) AS PAYMENT_INST ") 
         .append("                    FROM C_PAYMENT P  ")
         .append("                    INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID ")
         .append("                    LEFT JOIN EXME_CLAIMPAYMENT CLP ON CLP.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
         .append("                    LEFT JOIN C_INVOICE CI ON CI.C_PAYMENT_ID = P.C_PAYMENT_ID ")
         .append("                    INNER JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPACEXT_ID = COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID)  ")
         .append("                    INNER JOIN C_CHARGE C ") 
         .append("                    ON C.C_CHARGE_ID = P.C_CHARGE_ID ") 
         .append("                    AND C.TYPE NOT IN (?, ?, ?), CTX_DATA T ") 
         .append("                    WHERE P.AD_CLIENT_ID = T.AD_CLIENT_ID  ")
         .append("                    AND P.AD_ORG_ID = T.AD_ORG_ID  ")
         .append("                    AND P.ISACTIVE = 'Y'  ")
         .append("                    GROUP BY P.EXME_CTAPAC_ID, COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) ), ")
         
         
         .append(" BALANCE_CTAPAC as (SELECT CH.EXME_CTAPAC_ID, CH.EXME_CTAPACEXT_ID,  ")
         .append("            (CH.CHARGES_PROF - COALESCE(PY.PAYMENT_PROF, 0)) AS BALANCE_PROF, ") 
         .append("            (CH.CHARGES_INST - COALESCE(PY.PAYMENT_INST, 0)) AS BALANCE_INST  ")
         .append("     FROM CHARGES_CTAPAC CH  ")
         .append("     LEFT JOIN PAYMENT_CTAPAC PY ") 
         .append("     ON PY.EXME_CTAPAC_ID = CH.EXME_CTAPAC_ID AND PY.EXME_CTAPACEXT_ID = CH.EXME_CTAPACEXT_ID) ")
		.append("SELECT * FROM ( ");
        
//		.append(" WITH BALANCE_CTAPAC as ( ")
//		.append(" SELECT ((SELECT coalesce(SUM(CPD.LineNetAmt),0) FROM EXME_CtaPacDet CPD ")
//		.append(" INNER JOIN EXME_PRODUCTOORG PO ")
//		.append(" ON PO.EXME_PRODUCTOORG_ID = fnc_getproductorg(CPD.M_PRODUCT_ID,CPD.AD_ORG_ID) ")
//		.append(" INNER JOIN AD_ORGINFO OI ON OI.AD_ORG_ID = CPD.AD_ORG_ID ")
//		.append(" WHERE CPD.EXME_CtaPac_ID = CP.EXME_CtaPac_ID ) -  ")
//		.append(" (SELECT coalesce(SUM(P.PayAmt),0) FROM C_Payment P ")
//		.append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID AND C.TYPE NOT IN (?,?,?) ")
//		.append(" WHERE P.EXME_CtaPac_ID = CP.EXME_CtaPac_ID AND P.CONFTYPE = ?) ")
//		.append(" ) BALANCE, CP.EXME_CTAPAC_ID ")
//		.append(" FROM   EXME_CTAPAC  CP WHERE CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ?) ")
//		.append("SELECT * FROM ( ");
//		
		
		if (!MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)){
			isUnion = Boolean.TRUE;
			sql.append(" SELECT coalesce(c.SUPPORTBILLING,'IN') SUPPORTBILLING, FC.NAME ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS <> ? THEN BC.BALANCE_INST END), 0) AS NOT_DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS = ? THEN BC.BALANCE_INST END), 0) AS DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 0 AND C.DIAS_BILL <= 30 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS0_30 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 31 AND C.DIAS_BILL <= 60 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS31_60 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 61 AND C.DIAS_BILL <= 90 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS61_90 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 91 AND C.DIAS_BILL <= 120 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS91_120 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 121 AND C.DIAS_BILL <= 150 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS121_150 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 151 AND C.DIAS_BILL <= 180 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS151_180 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 181 THEN BC.BALANCE_INST END), 0) DAYS181 ")
			.append(" FROM   EXME_FINANCIALCLASS FC LEFT JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID ")
			.append(" LEFT JOIN ( ")
			.append(" SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.EXME_CTAPACEXT_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, ")
			.append(DB.isOracle() ? " TRUNC(" : " extract('day' from ")
			.append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
			.append("- CTA.FECHAALTA) AS DIAS_BILL ")
			.append(" , CTA.ENCOUNTERSTATUS ")
			.append(" , coalesce(pa.SUPPORTBILLING,'IN') SUPPORTBILLING ")
			.append(" , CASE ")
			.append(" WHEN TO_NUMBER(CP.INSTITUTIONALSTATUS) BETWEEN 1 AND 14 AND CP.INSTITUTIONALSTEP IN (?, ?) THEN CAST(? AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" WHEN TO_NUMBER(CP.INSTITUTIONALSTATUS) BETWEEN 1 AND 14 AND CP.INSTITUTIONALSTEP NOT IN (?, ?) THEN CAST(TO_NUMBER(CP.INSTITUTIONALSTATUS) + 24 AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" ELSE CAST(CP.INSTITUTIONALSTATUS AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" END BILLINGSTATUS ")
			.append(" , CASE ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			.append(" ELSE P.C_BPARTNER_ID ")
			.append(" END C_BPARTNER_ID ")
			.append(" FROM   EXME_CTAPACEXT CP INNER JOIN EXME_CTAPAC CTA ON CTA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID ")
			.append(" LEFT JOIN EXME_PACIENTEASEG PA  ON pa.isactive= 'Y' and PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			.append(" AND PA.PRIORITY = CASE  WHEN CP.INSTITUTIONALSTEP = ? THEN 1 ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? THEN 2 ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? THEN 3 END ")
			.append(" where  CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ?  AND cp.IsActive = 'Y') C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			.append(" LEFT JOIN BALANCE_CTAPAC BC ON BC.EXME_CTAPACEXT_ID = C.EXME_CTAPACEXT_ID ")
			.append(" GROUP BY FC.NAME , coalesce(c.SUPPORTBILLING,'IN')");
			
		}
		if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(supportBilling)){
			if (isUnion){
				sql.append(" UNION ALL ");
			}
			sql.append(" SELECT coalesce(c.SUPPORTBILLING,'PR') SUPPORTBILLING, FC.NAME ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS <> ? THEN BC.BALANCE_PROF END), 0) AS NOT_DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS = ? THEN BC.BALANCE_PROF END), 0) AS DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 0 AND C.DIAS_BILL <= 30 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS0_30 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 31 AND C.DIAS_BILL <= 60 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS31_60 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 61 AND C.DIAS_BILL <= 90 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS61_90 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 91 AND C.DIAS_BILL <= 120 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS91_120 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 121 AND C.DIAS_BILL <= 150 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS121_150 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 151 AND C.DIAS_BILL <= 180 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS151_180 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 181 THEN BC.BALANCE_PROF END), 0) DAYS181 ")
			.append(" FROM   EXME_FINANCIALCLASS FC LEFT JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID ")
			.append(" LEFT JOIN ( ")
			.append(" SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.EXME_CTAPACEXT_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, ")
			.append(DB.isOracle() ? " TRUNC(" : " extract('day' from ")
			.append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
			.append("- CTA.FECHAALTA) AS DIAS_BILL ")
			.append(" , CTA.ENCOUNTERSTATUS ")
			.append(" , coalesce(pa.SUPPORTBILLING,'PR') SUPPORTBILLING ")
			.append(" , CASE ")
			.append(" WHEN TO_NUMBER(CP.PROFESSIONALSTATUS) BETWEEN 1 AND 14 AND CP.PROFESSIONALSTEP IN (?, ?) THEN CAST(? AS VARCHAR").append(DB.isOracle() ? "2)":")")
			.append(" WHEN TO_NUMBER(CP.PROFESSIONALSTATUS) BETWEEN 1 AND 14 AND CP.PROFESSIONALSTEP NOT IN (?, ?) THEN CAST(TO_NUMBER(CP.PROFESSIONALSTATUS) + 24 AS VARCHAR").append(DB.isOracle() ? "2)":")")
			.append(" ELSE CAST(CP.PROFESSIONALSTATUS AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" END BILLINGSTATUS ")
			.append(" , CASE ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			.append(" ELSE P.C_BPARTNER_ID ")
			.append(" END C_BPARTNER_ID ")
			.append(" FROM   EXME_CTAPACEXT CP INNER JOIN EXME_CTAPAC CTA ON CTA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID ")
			.append(" LEFT JOIN EXME_PACIENTEASEG PA ON pa.isactive= 'Y' and PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			.append(" AND PA.PRIORITY = CASE  WHEN CP.PROFESSIONALSTEP = ? THEN 1 ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? THEN 2 ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? THEN 3 END ")
			.append(" where  CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ? AND cp.IsActive = 'Y') C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			.append(" LEFT JOIN BALANCE_CTAPAC BC ON BC.EXME_CTAPACEXT_ID = C.EXME_CTAPACEXT_ID ")
			.append(" GROUP BY FC.NAME , coalesce(c.SUPPORTBILLING,'PR')");
		}
		sql.append(" ) L1 ) T ");
			return sql.toString();
		
	}
	
	public static String getAgingByPC(String supportBilling){
		
		boolean isUnion = Boolean.FALSE;
		
		StringBuilder sql = new StringBuilder(" SELECT *  FROM   ( ")
		 .append(" WITH CTX_DATA AS (SELECT ? AS SUPPORTBILLING_INST, ")//IN
       .append("                    ? AS SUPPORTBILLING_PROF, ")//PR
       .append("                    ? AS AD_CLIENT_ID, ")//10001000
       .append("                    ? AS AD_ORG_ID, ")//10001051
       .append("                    ? AS CONFTYPE_INST, ")//T
       .append("                    ? AS CONFTYPE_PROF ")//P
       .append("               FROM DUAL),")
       
       
        .append(" CHARGES_CTAPAC AS (SELECT CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID, ")
         .append("               SUM(CASE ")
         .append("                    WHEN (PO.ISPROFESSIONAL = 'Y' OR OI.SUPPORTBILLING = T.SUPPORTBILLING_PROF) ")
         .append("                     THEN ")
         .append("                       COALESCE(CPD.LINENETAMT,0) ")
         .append("                     ELSE ")
         .append("                       0 ")
         .append("                  END) AS CHARGES_PROF, ")
         .append("               SUM(CASE  ")
         .append("                       WHEN (PO.ISPROFESSIONAL = 'N' AND OI.SUPPORTBILLING !=  T.SUPPORTBILLING_PROF) ")
         .append("                          THEN  ")
         .append("                            COALESCE(CPD.LINENETAMT,0) ")
         .append("                          ELSE ")
         .append("                            0 ")
         .append("                  END) AS CHARGES_INST ")
         .append("      FROM EXME_CTAPACDET CPD ")
         .append("      INNER JOIN EXME_PRODUCTOORG PO ")
         .append("      ON PO.EXME_PRODUCTOORG_ID = ")
         .append("      fnc_getproductorg(CPD.M_PRODUCT_ID,CPD.AD_ORG_ID) ")
         .append("      INNER JOIN AD_ORGINFO OI  ")
         .append("            ON OI.AD_ORG_ID = CPD.AD_ORG_ID, ")
         .append("      CTX_DATA T ")
         .append("      WHERE CPD.AD_CLIENT_ID =T.AD_CLIENT_ID ")
         .append("      AND CPD.AD_ORG_ID = T.AD_ORG_ID ")
         .append("      AND CPD.ISACTIVE = 'Y' ")
         .append("      GROUP BY CPD.EXME_CTAPAC_ID, CPD.EXME_CTAPACEXT_ID), ")
         
         .append(" PAYMENT_CTAPAC AS (SELECT P.EXME_CTAPAC_ID, COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID, SUM(CASE ") 
         .append("                                 WHEN p.CONFTYPE = T.CONFTYPE_PROF ") 
         .append("                                      THEN P.PAYAMT  ")
         .append("                                      ELSE 0  ")
         .append("                                 END) AS PAYMENT_PROF, ")
         .append("                             SUM(CASE  ")
         .append("                                 WHEN p.CONFTYPE = T.CONFTYPE_INST ") 
         .append("                                      THEN P.PAYAMT  ")
         .append("                                      ELSE 0  ")
         .append("                                END) AS PAYMENT_INST ") 
         .append("                    FROM C_PAYMENT P  ")
         .append("                    INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID ")
         .append("                    LEFT JOIN EXME_CLAIMPAYMENT CLP ON CLP.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
         .append("                    LEFT JOIN C_INVOICE CI ON CI.C_PAYMENT_ID = P.C_PAYMENT_ID ")
         .append("                    INNER JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPACEXT_ID = COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID)  ")
         .append("                    INNER JOIN C_CHARGE C ") 
         .append("                    ON C.C_CHARGE_ID = P.C_CHARGE_ID ") 
         .append("                    AND C.TYPE NOT IN (?, ?, ?), CTX_DATA T ") 
         .append("                    WHERE P.AD_CLIENT_ID = T.AD_CLIENT_ID  ")
         .append("                    AND P.AD_ORG_ID = T.AD_ORG_ID  ")
         .append("                    AND P.ISACTIVE = 'Y'  ")
         .append("                    GROUP BY P.EXME_CTAPAC_ID, COALESCE(CLP.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) ), ")
         
        
        .append(" BALANCE_CTAPAC as (SELECT CH.EXME_CTAPAC_ID, CH.EXME_CTAPACEXT_ID,  ")
        .append("            (CH.CHARGES_PROF - COALESCE(PY.PAYMENT_PROF, 0)) AS BALANCE_PROF, ") 
        .append("            (CH.CHARGES_INST - COALESCE(PY.PAYMENT_INST, 0)) AS BALANCE_INST  ")
        .append("     FROM CHARGES_CTAPAC CH  ")
        .append("     LEFT JOIN PAYMENT_CTAPAC PY ") 
        .append("     ON PY.EXME_CTAPAC_ID = CH.EXME_CTAPAC_ID AND PY.EXME_CTAPACEXT_ID = CH.EXME_CTAPACEXT_ID) ")
		.append("SELECT * FROM ( ");
		
		if (!MOrgInfo.SUPPORTBILLING_Professional.equals(supportBilling)){
			isUnion = Boolean.TRUE;
			
			sql.append(" SELECT coalesce(c.SUPPORTBILLING,'IN') SUPPORTBILLING, PC.NAME ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS <> ? THEN BC.BALANCE_INST END), 0) AS NOT_DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS = ? THEN BC.BALANCE_INST END), 0) AS DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 0 AND C.DIAS_BILL <= 30 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS0_30 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 31 AND C.DIAS_BILL <= 60 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS31_60 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 61 AND C.DIAS_BILL <= 90 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS61_90 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 91 AND C.DIAS_BILL <= 120 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS91_120 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 121 AND C.DIAS_BILL <= 150 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS121_150 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 151 AND C.DIAS_BILL <= 180 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_INST END), 0) DAYS151_180 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 181 THEN BC.BALANCE_INST END), 0) DAYS181 ")
			.append(" FROM   EXME_PAYERCLASS PC     LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = PC.EXME_FINANCIALCLASS_ID ")
			.append(" LEFT JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID AND BP.EXME_PAYERCLASS_ID = PC.EXME_PAYERCLASS_ID ")
			.append(" LEFT JOIN ( ")
			.append(" SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.EXME_CTAPACEXT_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, ")
			.append(DB.isOracle() ? " TRUNC(" : " extract('day' from ")
			.append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
			.append("- CTA.FECHAALTA) AS DIAS_BILL ")
			.append(" , CTA.ENCOUNTERSTATUS ")
			.append(" , coalesce(pa.SUPPORTBILLING,'IN') SUPPORTBILLING ")
			.append(" , CASE ")
			.append(" WHEN TO_NUMBER(CP.INSTITUTIONALSTATUS) BETWEEN 1 AND 14 AND CP.INSTITUTIONALSTEP IN (?, ?) THEN CAST(? AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" WHEN TO_NUMBER(CP.INSTITUTIONALSTATUS) BETWEEN 1 AND 14 AND CP.INSTITUTIONALSTEP NOT IN (?, ?) THEN CAST(TO_NUMBER(CP.INSTITUTIONALSTATUS) + 24 AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" ELSE CAST(CP.INSTITUTIONALSTATUS AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" END BILLINGSTATUS ")
			.append(" , CASE ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			.append(" ELSE P.C_BPARTNER_ID ")
			.append(" END C_BPARTNER_ID ")
			.append(" FROM   EXME_CTAPACEXT CP INNER JOIN EXME_CTAPAC CTA ON CTA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID ")
			.append(" LEFT JOIN EXME_PACIENTEASEG PA ON pa.isactive= 'Y' and PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			.append(" AND PA.PRIORITY = CASE  WHEN CP.INSTITUTIONALSTEP = ? THEN 1 ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? THEN 2 ")
			.append(" WHEN CP.INSTITUTIONALSTEP = ? THEN 3 END ")
			.append(" where  CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ? AND cp.IsActive = 'Y') C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID   ")
			.append(" LEFT JOIN BALANCE_CTAPAC BC ON BC.EXME_CTAPACEXT_ID = C.EXME_CTAPACEXT_ID ")
			.append(" GROUP BY PC.NAME, coalesce(c.SUPPORTBILLING,'IN') ");
			
		}
		if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(supportBilling)){
			if (isUnion){
				sql.append(" UNION ALL ");
			}
			
			sql.append(" SELECT coalesce(c.SUPPORTBILLING,'PR') SUPPORTBILLING, PC.NAME ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS <> ? THEN BC.BALANCE_PROF END), 0) AS NOT_DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.BILLINGSTATUS = ? AND C.ENCOUNTERSTATUS = ? THEN BC.BALANCE_PROF END), 0) AS DISCHARGE ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 0 AND C.DIAS_BILL <= 30 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS0_30 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 31 AND C.DIAS_BILL <= 60 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS31_60 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 61 AND C.DIAS_BILL <= 90 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS61_90 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 91 AND C.DIAS_BILL <= 120 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS91_120 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 121 AND C.DIAS_BILL <= 150 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS121_150 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 151 AND C.DIAS_BILL <= 180 AND to_number(C.BILLINGSTATUS) >= TO_NUMBER(?) THEN BC.BALANCE_PROF END), 0) DAYS151_180 ")
			.append(" , coalesce(SUM(CASE WHEN C.DIAS_BILL >= 181 THEN BC.BALANCE_PROF END), 0) DAYS181 ")
			.append(" FROM   EXME_PAYERCLASS PC     LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = PC.EXME_FINANCIALCLASS_ID ")
			.append(" LEFT JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID AND BP.EXME_PAYERCLASS_ID = PC.EXME_PAYERCLASS_ID ")
			.append(" LEFT JOIN ( ")
			.append(" SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.EXME_CTAPACEXT_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID,  ")
			.append(DB.isOracle() ? " TRUNC(" : " extract('day' from ")
			.append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
			.append("- CTA.FECHAALTA) AS DIAS_BILL ")
			.append(" , CTA.ENCOUNTERSTATUS ")
			.append(" , coalesce(pa.SUPPORTBILLING,'PR') SUPPORTBILLING ")
			.append(" , CASE ")
			.append(" WHEN TO_NUMBER(CP.PROFESSIONALSTATUS) BETWEEN 1 AND 14 AND CP.PROFESSIONALSTEP IN (?, ?) THEN CAST(? AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" WHEN TO_NUMBER(CP.PROFESSIONALSTATUS) BETWEEN 1 AND 14 AND CP.PROFESSIONALSTEP NOT IN (?, ?) THEN CAST(TO_NUMBER(CP.PROFESSIONALSTATUS) + 24 AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" ELSE CAST(CP.PROFESSIONALSTATUS AS VARCHAR").append(DB.isOracle() ? "2) ":") ")
			.append(" END BILLINGSTATUS ")
			.append(" , CASE ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			.append(" ELSE P.C_BPARTNER_ID ")
			.append(" END C_BPARTNER_ID ")
			.append(" FROM   EXME_CTAPACEXT CP INNER JOIN EXME_CTAPAC CTA ON CTA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CTA.EXME_PACIENTE_ID ")
			.append(" LEFT JOIN EXME_PACIENTEASEG PA ON pa.isactive= 'Y' and PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			.append(" AND PA.PRIORITY = CASE  WHEN CP.PROFESSIONALSTEP = ? THEN 1 ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? THEN 2 ")
			.append(" WHEN CP.PROFESSIONALSTEP = ? THEN 3 END ")
			.append(" where  CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ? AND cp.IsActive = 'Y') C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			.append(" LEFT JOIN BALANCE_CTAPAC BC ON BC.EXME_CTAPACEXT_ID = C.EXME_CTAPACEXT_ID ")
			.append(" GROUP BY PC.NAME, coalesce(c.SUPPORTBILLING,'PR') ");
		}
		sql.append(" ) L1 ) T ");
		return sql.toString();
		
	}
	
	
	public static StringBuilder getCollectedAnalysis(String supportBilling, boolean firstPass){
		
		boolean isUnion = Boolean.FALSE;
		String columnName;
		if(MOrgInfo.SUPPORTBILLING_Both.endsWith(supportBilling) ){
			isUnion = Boolean.TRUE;
			columnName = MEXMECtaPacExt.COLUMNNAME_InstitutionalStep;
		}else{
			columnName = MOrgInfo.SUPPORTBILLING_Institucional.equalsIgnoreCase(supportBilling) 
					? MEXMECtaPacExt.COLUMNNAME_InstitutionalStep
					: MEXMECtaPacExt.COLUMNNAME_ProfessionalStep;
		}
		StringBuilder sql = new StringBuilder("");
		if (firstPass){
			sql.append(" WITH PAYERS AS (SELECT PA.EXME_PACIENTEASEG_ID, PA.EXME_CTAPAC_ID, PA.PRIORITY,  ")
			 .append(" FC.NAME AS FCNAME, FC.VALUE AS FCVALUE, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME, PA.SUPPORTBILLING ")
			 .append(" FROM EXME_PACIENTEASEG PA ")
			 .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = PA.EXME_CTAPAC_ID ")
			 .append(" INNER JOIN C_BPARTNER CB ON CB.C_BPARTNER_ID = PA.C_BPARTNER_ID  ")
			 .append(" INNER JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
			 .append(" INNER JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
			 .append(" WHERE  PA.EXME_CTAPAC_ID >0 AND PA.ISACTIVE = 'Y' ")
			 .append(" AND CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?")
			 .append(" order by cp.exme_ctapac_id), ")
			 .append(" DEFPAYER AS (SELECT CP.EXME_CTAPAC_ID, FC.NAME AS FCNAME, FC.VALUE AS FCVALUE, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME ")
			 .append(" FROM EXME_CTAPAC CP ")
			 .append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			 .append(" INNER JOIN (C_BPARTNER CB ")
			 .append(" LEFT JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
			 .append(" LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID) ")
			 .append(" ON CB.C_BPARTNER_ID = P.C_BPARTNER_ID ")
			 .append(" WHERE CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?), ")
			 .append(" PMT AS (SELECT PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE, MAX(PAY.CREATED) AS LASTDATE, SUM(PAY.PAYAMT) AS TOTAL ")
			 .append(" FROM (SELECT P.EXME_CTAPAC_ID ,COALESCE (P.CONFTYPE, ?) AS CONFTYPE, P.CREATED, P.PAYAMT, ") 
			 .append(" COALESCE(D.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID ")
			 .append(" FROM C_PAYMENT P ")
			 .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ? ")
			 .append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID AND C.TYPE IN (?,?,?,?,?,?,?) ")
			 .append(" LEFT JOIN EXME_CLAIMPAYMENT D ON D.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
			 .append(" LEFT JOIN C_INVOICE CI ON CI.C_INVOICE_ID = P.C_INVOICE_ID ")
			 .append(" WHERE P.AD_ORG_ID = ? AND P.EXME_CTAPAC_ID > 0 AND P.ISACTIVE = 'Y') AS PAY ")
			 .append(" GROUP BY PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE) ")
			 .append(" SELECT * FROM ( ");
			 
		}
		sql.append(" SELECT CP.DOCUMENTNO as encounter, CPE.EXTENSIONNO as extension, BT.NAME AS type, P.NOMBRE_PAC as patientName, ")
		 .append(" TP.EXME_TipoPaciente_ID, TP.NAME AS patientType, CP.DATEORDERED as admitDate, CP.FECHAALTA as dischargeDate,  ")
		 .append(" COALESCE (PC.FCNAME, PD.FCNAME) AS FCNAME, ")
//Ticket 0102428 Mostrar siempre la primer Aseguradora				 
//		 .append(columnName)
//		 .append(" IN (?, ?) THEN PD.FCNAME ELSE PC.FCNAME END AS FCNAME, ")
		 .append(" COALESCE (PC.FCVALUE, PD.FCVALUE) AS FCVALUE, ")
//Ticket 0102428 Mostrar siempre la primer Aseguradora		
//		 .append(columnName)
//		 .append(" IN (?, ?) THEN PD.FCVALUE ELSE PC.FCVALUE END AS FCVALUE, ")
		 .append(" CPE.TOTALLINES as totCharges, PMT.TOTAL AS totPmts, ")
		 .append(" CASE WHEN PMT.TOTAL IS NULL OR PMT.TOTAL = 0 THEN 0 ")
		 .append(" WHEN CPE.TOTALLINES IS NULL OR CPE.TOTALLINES = 0 THEN 0 ")
		 .append(" ELSE pmt.total / cpe.totallines  END  as collected, ? AS SUPPORTBILLING  ")
		 .append(" FROM EXME_CTAPACEXT CPE ")
		 .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
		 .append(" INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID ")
		 .append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		 .append(" INNER JOIN EXME_HIST_EXP HE ON HE.AD_ORG_ID = CPE.AD_ORG_ID  AND HE.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID AND HE.ISACTIVE = 'Y' ")
		 .append(" LEFT JOIN PMT ON PMT.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID AND PMT.CONFTYPE = CPE.CONFTYPE AND PMT.EXME_CTAPACEXT_ID = CPE.EXME_CTAPACEXT_ID ")
		 .append(" LEFT JOIN PAYERS AS PC ON PC.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
//Ticket 0102428 Mostrar siempre la primer Aseguradora		 
//		 .append(" AND PC.PRIORITY = CASE WHEN CPE.")
//		 .append(columnName)
//		 .append("  = ? THEN 1 ")
//		 .append(" WHEN CPE.")
//		 .append(columnName)
//		 .append("   = ? THEN 2 ")
//		 .append(" WHEN CPE.")
//		 .append(columnName)
//		 .append("   = ? THEN 3 END ")
		 .append(" AND PC.PRIORITY = 1 ")
		 .append(" AND PC.SUPPORTBILLING = CASE WHEN CPE.CONFTYPE = ? THEN ? ELSE ? END ")
		 .append(" INNER JOIN DEFPAYER PD ON PD.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
		 .append(" INNER JOIN AD_REF_LIST BT ON BT.AD_REFERENCE_ID = ? AND BT.VALUE = ? ")
		 .append(" WHERE CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' AND CPE.CONFTYPE = ? AND CPE.TOTALLINES >0 ");
		
		if (isUnion){
			//Si es unión se manda llamar de nuevo pero ahora se pide Professional, pues iniciamos con Inst
			sql.append(" UNION ALL ")
				.append(getCollectedAnalysis(MOrgInfo.SUPPORTBILLING_Professional, Boolean.FALSE));
		} 
		if (firstPass){
			sql.append(" ) as T ");
		}
		//COMMENT: Al recuperar la cadena es necesario agregar el cierre del SELECT superior
		return sql;
		
	}
	
	public static StringBuilder getChargesAnalysis(String supportBilling, boolean firstPass){
		
		boolean isUnion = Boolean.FALSE;
		StringBuilder sql = new StringBuilder("");
		if (firstPass){
			sql.append(" WITH PAYERS AS (SELECT PA.EXME_PACIENTEASEG_ID, PA.EXME_CTAPAC_ID, PA.PRIORITY,  ")
			 .append(" FC.NAME AS FCNAME, FC.VALUE AS FCVALUE, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME, PA.SUPPORTBILLING ")
			 .append(" FROM EXME_PACIENTEASEG PA ")
			 .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = PA.EXME_CTAPAC_ID ")
			 .append(" INNER JOIN C_BPARTNER CB ON CB.C_BPARTNER_ID = PA.C_BPARTNER_ID  ")
			 .append(" INNER JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
			 .append(" INNER JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
			 .append(" WHERE  PA.EXME_CTAPAC_ID >0 AND PA.ISACTIVE = 'Y' ")
			 .append(" AND CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y') ")
			 .append(" SELECT * FROM ( ");
			 
		}
		sql.append("SELECT  CP.DOCUMENTNO AS encounter, P.NOMBRE_PAC AS patientName, HE.DOCUMENTNO AS MRN, TP.NAME AS patientType, ")
		.append(" 	TP.VALUE AS ptValue, COALESCE (P1.FCNAME, FC.NAME) AS fcName, M.NOMBRE_MED AS nombreMed, ")
		.append(" 	(SELECT name FROM AD_REF_LIST WHERE AD_REFERENCE_ID = ? ")
		.append(" 	AND VALUE = CASE WHEN OI.SUPPORTBIlLING = ? THEN CASE WHEN PO.ISPROFESSIONAL = 'N' THEN ? ELSE ? END ")
		.append(" 	ELSE OI.SUPPORTBILLING END) AS type,   ")
		.append(" 	CASE WHEN OI.SUPPORTBIlLING = ? THEN  CASE WHEN PO.ISPROFESSIONAL = 'N' THEN ? ELSE ? END ")
		.append(" 	ELSE OI.SUPPORTBIlLING  END AS supportBilling, ")
		.append(" 	MP.VALUE AS chargeCode, MP.NAME AS chargeName, RC.VALUE AS chargeRevCode, CPD.LINENETAMT AS chargeAmount, ")
		.append(" 	CPD.QTYDELIVERED AS chargeQty, CPD.DATEDELIVERED AS serviceDate, CPD.CREATED AS postedDate , ")
		.append(" 	COALESCE (P1.FCVALUE, FC.VALUE) AS fcValue, EI.VALUE AS cptCode ")
		.append(" FROM EXME_CTAPAC CP ")
		.append(" INNER JOIN EXME_CTAPACDET CPD ON CP.EXME_CTAPAC_ID = CPD.EXME_CTAPAC_ID ")
		.append(" INNER JOIN M_PRODUCT MP ON MP.M_PRODUCT_ID = CPD.M_PRODUCT_ID ")
		.append(" LEFT  JOIN EXME_INTERVENCION EI ON EI.EXME_INTERVENCION_ID = MP.EXME_INTERVENCION_ID ")
		.append(" INNER JOIN EXME_PRODUCTOORG PO ON PO.EXME_PRODUCTOORG_ID = FNC_GETPRODUCTORG(CPD.M_PRODUCT_ID , CPD.AD_ORG_ID) ")
		.append(" LEFT JOIN EXME_REVENUECODE RC ON RC.EXME_REVENUECODE_ID = PO.EXME_REVENUECODE_ID ")
		.append(" INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID ")
		.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		.append(" INNER JOIN EXME_HIST_EXP HE ON HE.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID AND HE.AD_ORG_ID = CPD.AD_ORG_ID AND HE.ISACTIVE = 'Y' ")
		.append(" LEFT JOIN (EXME_CTAPACMED CPM INNER JOIN EXME_MEDICO M ON M.EXME_MEDICO_ID = CPM.EXME_MEDICO_ID) ")
		.append(" ON CPM.EXME_CTAPAC_ID = CPD.EXME_CTAPAC_ID AND CPM.TYPE = ? AND CPM.ISACTIVE = 'Y' ")
		.append(" INNER JOIN AD_ORGINFO OI ON OI.AD_ORG_ID = CPD.AD_ORG_ID AND OI.ISACTIVE = 'Y' ")
		.append(" LEFT JOIN PAYERS AS P1 ON P1.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
		.append(" AND P1.SUPPORTBILLING = CASE WHEN OI.SUPPORTBIlLING = ? THEN ")
		.append(" CASE WHEN PO.ISPROFESSIONAL  = 'N' THEN ? ELSE ? END ELSE OI.SUPPORTBILLING END")
		.append(" AND P1.PRIORITY = 1")
		.append(" INNER JOIN (C_BPARTNER CB ")
		.append(" 	    INNER JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
		.append(" 	    INNER JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID) ON CB.C_BPARTNER_ID = P.C_BPARTNER_ID ")
		.append(" WHERE CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' ");
		
		if (isUnion){
			//Si es unión se manda llamar de nuevo pero ahora se pide Professional, pues iniciamos con Inst
			sql.append(" UNION ALL ")
				.append(getCollectedAnalysis(MOrgInfo.SUPPORTBILLING_Professional, Boolean.FALSE));
		} 
		if (firstPass){
			sql.append(" ) as T ");
		}
		//COMMENT: Al recuperar la cadena es necesario agregar el cierre del SELECT superior
		return sql;
		
	}
	
	public static StringBuilder getEncounterStatusDET(String supportBilling, boolean firstPass){
		
		boolean isUnion = Boolean.FALSE;
		String columnStep;
		String columnStatus;
		if(MOrgInfo.SUPPORTBILLING_Both.endsWith(supportBilling) ){
			isUnion = Boolean.TRUE;
			columnStep = MEXMECtaPacExt.COLUMNNAME_InstitutionalStep;
			columnStatus = MEXMECtaPacExt.COLUMNNAME_InstitutionalStatus;
		}else{
			columnStep = MOrgInfo.SUPPORTBILLING_Institucional.equalsIgnoreCase(supportBilling) 
					? MEXMECtaPacExt.COLUMNNAME_InstitutionalStep
					: MEXMECtaPacExt.COLUMNNAME_ProfessionalStep;
			columnStatus = MOrgInfo.SUPPORTBILLING_Institucional.equalsIgnoreCase(supportBilling) 
					? MEXMECtaPacExt.COLUMNNAME_InstitutionalStatus
					: MEXMECtaPacExt.COLUMNNAME_ProfessionalStatus;
		}
		StringBuilder sql = new StringBuilder("");
		if (firstPass){
			sql.append(" WITH PAYERS AS (SELECT PA.ISACTIVE, PA.EXME_PACIENTEASEG_ID, PA.EXME_CTAPAC_ID, PA.EXME_PACIENTE_ID, ")
			   .append(" PA.PRIORITY, FC.NAME AS FCNAME, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME, FC.VALUE AS FCVALUE, ")
			   .append(" PA.SUPPORTBILLING, CB.C_BPARTNER_ID ")
			   .append(" FROM EXME_CTAPAC CP ")
			   .append(" INNER JOIN EXME_PACIENTEASEG PA ON PA.ISACTIVE = 'Y' AND PA.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID  ")
			   .append(" AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			   .append(" INNER JOIN C_BPARTNER CB ON CB.C_BPARTNER_ID = PA.C_BPARTNER_ID ")
			   .append(" INNER JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
			   .append(" INNER JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
			   .append(" WHERE CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ? ")
			   .append(" AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?), ")
			   .append(" DEFPAYER AS (SELECT CP.EXME_CTAPAC_ID, FC.NAME AS FCNAME, FC.VALUE AS FCVALUE, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME, CB.C_BPARTNER_ID ")
			   .append(" FROM EXME_CTAPAC CP ")
			   .append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			   .append(" INNER JOIN(C_BPARTNER CB ")
			   .append(" LEFT JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
			   .append(" LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID =CB.EXME_FINANCIALCLASS_ID) ")
			   .append(" ON CB.C_BPARTNER_ID = P.C_BPARTNER_ID ")
			   .append(" WHERE CP.AD_Client_id = ? AND CP.AD_ORG_ID = ? ")
			   .append(" AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?), ")
			   .append(" PMT AS (SELECT PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE, MAX(PAY.CREATED) AS LASTDATE, SUM(PAY.PAYAMT) AS TOTAL ")
			   .append(" FROM (SELECT P.EXME_CTAPAC_ID ,COALESCE (P.CONFTYPE, 'T') AS CONFTYPE, P.CREATED, P.PAYAMT, ") 
			   .append(" COALESCE(D.EXME_CTAPACEXT_ID, CI.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID ")
			   .append(" FROM C_PAYMENT P ")
			   .append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = 'D' ")
			   .append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID AND C.TYPE NOT IN (?,?,?) ")
			   .append(" LEFT JOIN EXME_CLAIMPAYMENT D ON D.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
			   .append(" LEFT JOIN C_INVOICE CI ON CI.C_INVOICE_ID = P.C_INVOICE_ID ")
			   .append(" WHERE P.AD_ORG_ID = ? AND P.EXME_CTAPAC_ID > 0 AND P.ISACTIVE = 'Y') AS PAY ")
			   .append(" GROUP BY PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE), ")
			   .append(" BATCH AS ( SELECT H.CONFTYPE, D.EXME_CTAPAC_ID, CASE WHEN H.CONFTYPE = 'A' THEN NULL ELSE D.EXME_CTAPACEXT_ID END AS EXME_CTAPACEXT_ID, ")
			   .append(" D.C_BPARTNER_ID, MAX(H.UPDATED) AS SENTDATE ")
			   .append(" FROM EXME_BATCHCLAIMH H ")
			   .append(" INNER JOIN EXME_BATCHCLAIMD D ON D.EXME_BATCHCLAIMH_ID = H.EXME_BATCHCLAIMH_ID ")
			   .append(" WHERE H.AD_CLIENT_ID = ? AND H.AD_ORG_ID = ? AND H.ISACTIVE = 'Y' ")
			   .append(" GROUP BY H.CONFTYPE, D.EXME_CTAPAC_ID, CASE WHEN H.CONFTYPE = 'A' THEN NULL ELSE D.EXME_CTAPACEXT_ID END, D.C_BPARTNER_ID) ")
			   .append(" SELECT * FROM ( ");
			 
		}
		sql.append(" SELECT CP.EXME_CTAPAC_ID, CPE.EXME_CTAPACEXT_ID, CP.DOCUMENTNO as encounterNo, CPE.EXTENSIONNO as extensionNo, ")
		   .append(" P.NOMBRE_PAC as patientName, TP.NAME AS patientType, CP.DATEORDERED as AdmitDate, CP.FECHAALTA as DischargeDate, ")
		   .append(" BT.NAME AS type,  BT.VALUE AS billingType, TP.EXME_TIPOPACIENTE_ID,  ")
		   .append(" CASE WHEN PC1.FCNAME IS NULL THEN PD.FCNAME ELSE PC1.FCNAME END AS primaryFC, ")
		   .append(" CASE WHEN CPE.").append(columnStep).append(" IN (?, ?) THEN PD.FCNAME ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC1.FCNAME ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC2.FCNAME ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC3.FCNAME END AS currentFC, ")
		   .append(" CASE WHEN CPE.").append(columnStep).append("  IN (?, ?) THEN PD.FCVALUE ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC1.FCVALUE ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC2.FCVALUE ")
		   .append(" WHEN CPE.").append(columnStep).append("  IN (?) THEN PC3.FCVALUE END AS fcValue, ")
		   .append(" (SELECT NAME FROM AD_REF_LIST WHERE AD_REFERENCE_ID = ? AND VALUE = CPE.").append(columnStep).append(" ) AS step, ")
		   .append(" (SELECT NAME FROM AD_REF_LIST WHERE AD_REFERENCE_ID = ? AND VALUE = CPE.").append(columnStatus).append(" ) AS status, ")
		   .append(" CPE.INSTITUTIONALSTEP AS stepValue, CPE.INSTITUTIONALSTATUS AS statusValue, ")
		   .append(" B1.SENTDATE AS datePriFilled, B2.SENTDATE AS dateSecFilled, B3.SENTDATE AS dateTerFilled, ")
		   .append(" BD.SENTDATE AS dateSPFilled, CPE.TOTALLINES, PMT.TOTAL, CPE.TOTALLINES - COALESCE(PMT.TOTAL,0) AS balance ")
		   .append(" FROM EXME_CTAPAC CP ")
		   .append(" INNER  JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
		   .append(" AND CPE.CONFTYPE = ? AND CPE.TOTALLINES > 0 AND CPE.EXTENSIONNO >0 ")
		   .append(" INNER JOIN EXME_TIPOPACIENTE TP ON TP.EXME_TIPOPACIENTE_ID = CP.EXME_TIPOPACIENTE_ID ")
		   .append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		   .append(" INNER JOIN EXME_HIST_EXP HE ON HE.AD_ORG_ID = CPE.AD_ORG_ID ")
		   .append(" AND HE.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID AND HE.ISACTIVE = 'Y' ")
		   .append(" LEFT JOIN PMT ON PMT.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID AND PMT.CONFTYPE = CPE.CONFTYPE AND PMT.EXME_CTAPACEXT_ID = CPE.EXME_CTAPACEXT_ID ")
		   .append(" LEFT JOIN PAYERS AS PC1 ON PC1.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID AND PC1.PRIORITY = 1 AND PC1.SUPPORTBILLING = ? ")
		   .append(" LEFT JOIN PAYERS AS PC2 ON PC2.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID AND PC2.PRIORITY = 2 AND PC2.SUPPORTBILLING = ? ")
		   .append(" LEFT JOIN PAYERS AS PC3 ON PC3.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID AND PC3.PRIORITY = 3 AND PC3.SUPPORTBILLING = ? ")
		   .append(" LEFT JOIN DEFPAYER PD ON PD.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
		   .append(" LEFT JOIN BATCH AS B1 ON B1.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID  AND B1.EXME_CTAPACEXT_ID = CPE.EXME_CTAPACEXT_ID ")
		   .append(" AND B1.C_BPARTNER_ID = PC1.C_BPARTNER_ID AND B1.CONFTYPE = ? ")
		   .append(" LEFT JOIN BATCH AS B2 ON B2.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID  AND B2.EXME_CTAPACEXT_ID = CPE.EXME_CTAPACEXT_ID ")
		   .append(" AND B2.C_BPARTNER_ID = PC2.C_BPARTNER_ID AND B2.CONFTYPE = ? ")
		   .append(" LEFT JOIN BATCH AS B3 ON B1.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID  AND B3.EXME_CTAPACEXT_ID = CPE.EXME_CTAPACEXT_ID ")
		   .append(" AND B3.C_BPARTNER_ID = PC3.C_BPARTNER_ID AND B3.CONFTYPE = ? ")
		   .append(" LEFT JOIN BATCH AS BD ON BD.EXME_CTAPAC_ID = CPE.EXME_CTAPAC_ID ")
		   .append(" AND BD.C_BPARTNER_ID = PD.C_BPARTNER_ID AND BD.CONFTYPE = ? ")
		   .append(" LEFT JOIN AD_REF_LIST BT ON BT.AD_REFERENCE_ID = ? AND BT.VALUE = ? ")
		   .append(" WHERE CP.AD_CLIENT_ID = ? AND CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' ");
		
		if (isUnion){
			//Si es unión se manda llamar de nuevo pero ahora se pide Professional, pues iniciamos con Inst
			sql.append(" UNION ALL ")
				.append(getEncounterStatusDET(MOrgInfo.SUPPORTBILLING_Professional, Boolean.FALSE));
		} 
		if (firstPass){
			sql.append(" ) as T ");
		}
		//COMMENT: Al recuperar la cadena es necesario agregar el cierre del SELECT superior
		return sql;
		
	}
	
	public static StringBuilder getEncounterBalancesDetail() {
		StringBuilder sql = new StringBuilder("  SELECT * ")
		.append("FROM ( ")
		.append("     WITH PAYERS AS (SELECT    PA.EXME_PACIENTEASEG_ID, PA.EXME_CTAPAC_ID, PA.PRIORITY, FC.NAME AS FCNAME, ")
		.append(" PC.NAME AS PCNAME, CB.NAME AS PAYERNAME, PA.SUPPORTBILLING,  ")
		.append(" FC.VALUE AS FCVALUE, PC.VALUE AS PCVALUE  ")
		.append(" FROM EXME_PACIENTEASEG PA ")
		.append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = PA.EXME_CTAPAC_ID ")
		.append(" INNER JOIN C_BPARTNER CB ON CB.C_BPARTNER_ID = PA.C_BPARTNER_ID ")
		.append(" INNER JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
		.append(" INNER JOIN EXME_FINANCIALCLASS FC  ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID ")
		.append(" WHERE PA.EXME_CTAPAC_ID > 0 AND PA.ISACTIVE = 'Y' AND CP.AD_ORG_ID = ? ")
		.append(" AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ? ")
		.append(" order by cp.exme_ctapac_id),  ")
		.append(" DEFPAYER AS (SELECT CP.EXME_CTAPAC_ID, FC.NAME AS FCNAME, PC.NAME AS PCNAME, CB.NAME AS PAYERNAME,  ")
		.append(" FC.VALUE AS FCVALUE, PC.VALUE AS PCVALUE  ")
		.append(" FROM EXME_CTAPAC CP ")
		.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		.append(" INNER JOIN(C_BPARTNER CB ")
		.append(" LEFT JOIN EXME_PAYERCLASS PC ON PC.EXME_PAYERCLASS_ID = CB.EXME_PAYERCLASS_ID ")
		.append(" LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = CB.EXME_FINANCIALCLASS_ID) ")
		.append(" ON CB.C_BPARTNER_ID = P.C_BPARTNER_ID ")
		.append(" WHERE CP.AD_ORG_ID = ? AND CP.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?), ")
		.append(" PMT AS (SELECT PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE, MAX(PAY.CREATED) AS LASTDATE, SUM(PAY.PAYAMT) AS TOTAL ")
		.append(" FROM   (SELECT P.EXME_CTAPAC_ID, COALESCE(CI.CONFTYPE, P.CONFTYPE, ?) AS CONFTYPE, P.CREATED, P.PAYAMT, ")
		.append(" COALESCE(CI.EXME_CTAPACEXT_ID, D.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID ")
		.append(" FROM C_PAYMENT P ")
		.append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID ")
		.append(" AND CP.ISACTIVE = 'Y'  ")
		.append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID ")
		.append(" AND C.TYPE NOT IN (?,?,?) ")
		.append(" LEFT JOIN EXME_CLAIMPAYMENT D ON D.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
		.append(" LEFT JOIN C_INVOICE CI ON CI.C_INVOICE_ID = P.C_INVOICE_ID ")
		.append(" WHERE P.AD_ORG_ID = ? AND P.EXME_CTAPAC_ID > 0 AND P.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ?) AS PAY ")
		.append(" GROUP BY PAY.EXME_CTAPAC_ID, PAY.EXME_CTAPACEXT_ID, PAY.CONFTYPE), ")
		.append(" PAG AS (SELECT LP.EXME_CTAPAC_ID, LP.EXME_CTAPACEXT_ID, LP.CONFTYPE, MAX(LP.CREATED) AS LASTDATE, SUM(LP.PAYAMT) AS TOTAL ")
		.append(" FROM   (SELECT P.EXME_CTAPAC_ID, COALESCE(CI.CONFTYPE, P.CONFTYPE, ?) AS CONFTYPE, P.CREATED, P.PAYAMT, ")
		.append(" COALESCE(CI.EXME_CTAPACEXT_ID, D.EXME_CTAPACEXT_ID, CP.EXME_CTAPACEXT_ID) AS EXME_CTAPACEXT_ID ")
		.append(" FROM C_PAYMENT P ")
		.append(" INNER JOIN EXME_CTAPAC CP ON CP.EXME_CTAPAC_ID = P.EXME_CTAPAC_ID ")
		.append(" AND CP.ISACTIVE = 'Y' ")
		.append(" INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = P.C_CHARGE_ID ")
		.append(" AND C.TYPE IN (?,?,?,?,?,?,?) ")
		.append(" LEFT JOIN EXME_CLAIMPAYMENT D ON D.EXME_CLAIMPAYMENT_ID = P.EXME_CLAIMPAYMENT_ID ")
		.append(" LEFT JOIN C_INVOICE CI ON CI.C_INVOICE_ID = P.C_INVOICE_ID ")
		.append(" WHERE P.AD_ORG_ID = ? AND P.EXME_CTAPAC_ID > 0 AND P.ISACTIVE = 'Y' AND CP.ENCOUNTERSTATUS = ? ) AS LP ")
		.append(" GROUP BY LP.EXME_CTAPAC_ID, LP.EXME_CTAPACEXT_ID, LP.CONFTYPE) ")
		.append(" SELECT CP.EXME_CtaPac_ID, CPE.EXME_CtaPacExt_ID, CP.DocumentNo as encounter, CPE.ExtensionNo as extension, ")
		.append(" HE.DocumentNo as MRN, CPE.ConfType as SupportBilling, RLT.Name as billingType, COALESCE(PC.FCName, PD.FCName) as primaryFC, COALESCE(PC.PCName, PD.PCName) as primaryPC, ")
		.append(" COALESCE(PCC.FCName, PD.FCName) as currentFC, COALESCE(PCC.PCName, PD.PCName) as currentPC, COALESCE(PCC.PayerName, PD.PayerName) as payerName, ")
		.append(" COALESCE(PC.FCValue, PD.FCValue) as priFCValue, COALESCE(PC.PCValue, PD.PCValue) as priPCValue,  ")
		.append(" COALESCE(PCC.FCValue, PD.FCValue) as currFCValue, COALESCE(PCC.PCValue, PD.PCValue) as currPCValue,  ")
		.append(" COALESCE( ")
		.append(DB.isOracle() ? " TRUNC(" : " extract('day' from ")
		.append(DB.TO_DATE(DB.convert(Env.getCtx(), new Date())))
		.append(" - date_trunc('day',CP.FechaAlta)), -1) AS daysAged, ")
		.append(" PAG.LastDate AS lastPmt, ")
		.append(" PMT.Total as TotalPmt, CPE.TotalLines AS totalCharges, CPE.TotalLines - Coalesce(PMT.Total,0) AS balance, ")
		.append(" CASE WHEN CPE.TotalLines = 0 THEN 0  ELSE CASE WHEN Coalesce(PMT.Total,0) = 0 THEN 1 ")
		.append(" ELSE ((CPE.TotalLines - PMT.Total) / CPE.TotalLines) END END AS percent, ")
		.append(" RLP.Name AS Step, RLS.Name AS Status ")
		.append(" FROM EXME_CtaPac CP ")
		.append(" INNER JOIN EXME_CtaPacExt CPE ON CPE.EXME_CtaPac_ID = CP.EXME_CtaPac_ID AND CPE.ExtensionNo > 0 AND CPE.IsActive = 'Y' ")
		.append(" INNER JOIN EXME_Hist_Exp HE ON HE.AD_Org_ID = CPE.AD_Org_ID  AND HE.EXME_Paciente_ID = CP.EXME_Paciente_ID AND HE.IsActive = 'Y' ")
		.append(" LEFT JOIN PMT ON PMT.EXME_CtaPacExt_ID = CPE.EXME_CtaPacExt_ID ")
		.append(" LEFT JOIN PAG ON PAG.EXME_CtaPacExt_ID = CPE.EXME_CtaPacExt_ID ")
		.append(" LEFT JOIN PAYERS AS PC ON PC.EXME_CtaPac_ID = CPE.EXME_CtaPac_ID ")
		.append(" AND ((CPE.ConfType = ? AND  PC.SupportBilling = ? AND PC.Priority = 1) ")
		.append(" OR (CPE.ConfType = ? AND  PC.SupportBilling = ? AND  PC.Priority = 1)) ")
		.append(" LEFT JOIN PAYERS AS PCC ON PCC.EXME_CtaPac_ID = CPE.EXME_CtaPac_ID ")
		.append(" AND ((CPE.ConfType = ? AND  PCC.SupportBilling = ? AND PCC.Priority =  ")
		.append(" CASE CPE.InstitutionalStep WHEN ? THEN 1 WHEN ? THEN 2  WHEN ? THEN 3 END ) ")
		.append(" OR (CPE.ConfType = ? AND  PCC.SupportBilling = ? AND  PCC.Priority = ")
		.append(" CASE CPE.ProfessionalStep WHEN ? THEN 1 WHEN ? THEN 2  WHEN ? THEN 3 END )) ")
		.append(" INNER JOIN DEFPAYER PD ON PD.EXME_CtaPac_ID = CPE.EXME_CtaPac_ID ")
		.append(" INNER JOIN AD_REF_LIST RLT ON RLT.AD_Reference_ID = ? AND RLT.Value = CPE.ConfType ")
		.append(" INNER JOIN AD_REF_LIST RLP ON RLP.AD_Reference_ID = ? AND RLP.Value = ")
		.append(" CASE CPE.ConfType WHEN ? THEN CPE.InstitutionalStep WHEN ? THEN CPE.ProfessionalStep END ")
		.append(" INNER JOIN AD_REF_LIST RLS ON RLS.AD_Reference_ID = ? AND RLS.Value = ")
		.append(" CASE CPE.ConfType WHEN ? THEN CPE.InstitutionalStatus WHEN ? THEN CPE.ProfessionalStatus END ")
		.append(" WHERE CPE.EXTENSIONNO >0 )as T ");

		return sql;
	}
	
}
