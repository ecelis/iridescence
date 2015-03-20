package org.compiere.model;
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;

import firstdatabank.database.FDBException;
import firstdatabank.dif.DAMScreenResult;
import firstdatabank.dif.DAMScreenResults;
import firstdatabank.dif.DDCMScreenResult;
import firstdatabank.dif.DDCMScreenResults;
import firstdatabank.dif.DDIMScreenResult;
import firstdatabank.dif.DDIMScreenResults;
import firstdatabank.dif.DFIMScreenResult;
import firstdatabank.dif.DFIMScreenResults;
import firstdatabank.dif.DTScreenDrugItem;
import firstdatabank.dif.DTScreenDrugItems;
import firstdatabank.dif.DTScreenResult;
import firstdatabank.dif.DTScreenResults;
import firstdatabank.dif.DrugSearchFilter;
import firstdatabank.dif.FDBAllergyType;
import firstdatabank.dif.FDBDDCMSeverityFilter;
import firstdatabank.dif.FDBDDIMSeverityFilter;
import firstdatabank.dif.FDBDFIMSignificanceFilter;
import firstdatabank.dif.FDBDrugType;
import firstdatabank.dif.FDBProxyPredictor;
import firstdatabank.dif.FDBScreenConditionType;
import firstdatabank.dif.Navigation;
import firstdatabank.dif.PackagedDrugs;
import firstdatabank.dif.ScreenAllergies;
import firstdatabank.dif.ScreenAllergy;
import firstdatabank.dif.ScreenCondition;
import firstdatabank.dif.ScreenConditions;
import firstdatabank.dif.ScreenDrug;
import firstdatabank.dif.ScreenDrugs;
import firstdatabank.dif.Screening;

/**
 * Clase para manejo de screening del framework Drug Information (First Data Bank)
 * @author jcarranza
 *
 */

public class DIFramework {
}
/*	private static CLogger s_log = CLogger.getCLogger(DIFramework.class);


	/**
	 * Obtiene el objeto ScreenDrugs a partir de un arreglo de medicamentos
	 * @param arrNDC
	 * @param NDC
	 * @return ScreenDrugs
	 * @author jcarranza
	 */
/*
	public static ScreenDrugs getObjScrnDrugs(Properties ctx, String arrNDC, String NDC) {

		Navigation nav = new Navigation();
		ScreenDrugs m_screenDrugs = new ScreenDrugs();

		try {		
			//String searchTexts[] = {"59741-307-12","0056-0168-90","0172-3984-60"}; //NDC

			String searchTexts[] = arrNDC.split("@@@");

			//tipo de busqueda, se utilizan las default
			DrugSearchFilter sf = new DrugSearchFilter();
			PackagedDrugs drug_searchResults = null;
			try{
				if(searchTexts.length > 0){
					for(int k = 0; (k < searchTexts.length); k++){

						if(!searchTexts[k].equals("")){
							try{
								drug_searchResults = nav.NDCSearch(searchTexts[k], sf);
							}catch(Exception e){
								s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.error"), e);
								drug_searchResults = null;
							}
							if(drug_searchResults!=null && drug_searchResults.count()>0){
								for(int i=0; i < drug_searchResults.count(); i++){
									ScreenDrug scrDrug = new ScreenDrug();
									scrDrug.load(drug_searchResults.item(i).getID(), FDBDrugType.fdbDTPackagedDrug );
									scrDrug.setDescription(drug_searchResults.item(i).getDescription());
									m_screenDrugs.addItem(scrDrug);

								}				        	
							}
						}
					}
				}

				if(NDC!=null && NDC.length()>0){
					try{
						drug_searchResults = nav.NDCSearch(NDC, sf);
					}catch(Exception e){
						s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.error"), e);
						drug_searchResults = null;
					}
					if(drug_searchResults!=null && drug_searchResults.count()>0){
						for(int i=0; i < drug_searchResults.count(); i++){
							ScreenDrug scrDrug = new ScreenDrug();
							scrDrug.load(drug_searchResults.item(i).getID(), FDBDrugType.fdbDTPackagedDrug );
							scrDrug.setDescription(drug_searchResults.item(i).getDescription());
							m_screenDrugs.addItem(scrDrug);

						}				        	
					}
				}

			}catch (FDBException fdbe){
				System.out.println(fdbe.getMessage());
				fdbe.printStackTrace();
			} 



		} catch (Exception e) {
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.error"), e);
			e.printStackTrace();
		}




		return m_screenDrugs;
	}

	/**
	 * Obtiene DDIM (drug-drug) screening
	 * @param arrNDC
	 * @param NDC
	 * @return ArrayList<String>
	 * @author jcarranza
	 */
/*
	public static ArrayList<String> doScreenDDIM(Properties ctx, String arrNDC, String NDC, String level){

		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, NDC);
		Screening objScreen = new Screening();

		ArrayList<String> msgDDIM = new ArrayList<String>();

		//msgDDIM.add(" DDIM (drug-drug) screening");  

		DDIMScreenResults objResultsDDIM = null;
		long lngCnt;

		// Identify what's being screened in the Screen Results text box
		//msgDDIM.add("[ Screen DDIM ]");

		// Perform the screening
		// The screening options are:
		//   Retrospective screening (screen all drugs)
		//   Minimum interaction severity level to report is Moderate
		//   Don't use custom interactions
		try
		{
			//default
			FDBDDIMSeverityFilter sev = FDBDDIMSeverityFilter.fdbDDIMSFModerate ;

			//parametro de la configuraci�n del m�dico
			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_Contraindicated)){ //CO
				sev = FDBDDIMSeverityFilter.fdbDDIMSFContraindicated;
			}

			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_Severe)){ //SE
				sev = FDBDDIMSeverityFilter.fdbDDIMSFSevere ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_Moderate)){ //MO
				sev = FDBDDIMSeverityFilter.fdbDDIMSFModerate ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_UnknownContraindicated)){ //UC
				sev = FDBDDIMSeverityFilter.fdbDDIMSFUnknownContraindicated ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_UnknownSevere)){ //US
				sev = FDBDDIMSeverityFilter.fdbDDIMSFUnknownSevere ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGDRUGLEVEL_UnknownModerate)){ //UM
				sev = FDBDDIMSeverityFilter.fdbDDIMSFUnknownModerate ;
			}

			objResultsDDIM =
				objScreen.DDIMScreen(objScrnDrugs,   // drugs to screen
						false,          // prospective only
						sev,            // severity level filter
						false,          // use custom interactions
						false);   // Load User Category
		}catch (Exception e){
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.error"), e);
			e.printStackTrace();
		}


		// See if the screening produced any results
		lngCnt = objResultsDDIM.count();
		if (!(lngCnt >= 0)){
			msgDDIM.add("    == No Results ==");
		}else{
			DDIMScreenResult objDDIMRes =null;

			for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
				objDDIMRes = objResultsDDIM.item(intInd);

				// Add an entry to the screen results listview control.
				msgDDIM.add(Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.initials") + " " + objDDIMRes.getDrug1Description() + " & " + objDDIMRes.getDrug2Description()+ objDDIMRes.getMonographID());

				// Add a description of the result to the screen results text box.
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.result") + " " + Integer.toString(intInd + 1));
				msgDDIM.add("    " + objDDIMRes.getDrug1Description() + " & " + objDDIMRes.getDrug2Description());
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.interaction") + ": " + objDDIMRes.getInteractionDescription());
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.sceening.message") + ": " + objDDIMRes.getScreenMessage());
			}
		}
		return msgDDIM;
	}



	/**
	 * Obtiene DDCMScreen (medical condition) screening
	 * @param arrNDC
	 * @param medConds
	 * @return ArrayList<String>
	 * @author rsolorzano
	 */
/*
	public static ArrayList<String> doDDCMScreen(Properties ctx, String arrNDC, List<MEXMECondMedica> medConds, String level){

		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, null);
		Screening objScreen = new Screening();
		ScreenConditions screenConditions = new ScreenConditions();


		//llenamos el objeto de condiciones medicas
		if(medConds!=null){
			for(int i=0; i< medConds.size(); i++){
				ScreenCondition screenCond = new ScreenCondition();
				MEXMECondMedica cond = (MEXMECondMedica) medConds.get(i);
				try{
					screenCond.load(cond.getDiagnostico(), FDBScreenConditionType.fdbSCTICD9);
				}catch (FDBException fdbe){
					// Display error message if the screen failed
					System.out.println(fdbe.toString());
				}
				screenConditions.addItem(screenCond);
			}
		}

		ArrayList<String> msgDDCM = new ArrayList<String>();

		DDCMScreenResults objResults = null;

		long lngCnt;

		try{
			//default
			FDBDDCMSeverityFilter sev = FDBDDCMSeverityFilter.fdbDDCMSFWarning ;

			//par�metros de la configuraci�n del m�dico
			if(level.equals(MEXMEConfigMedico.MEDICALCONDITIONLEVEL_Contraindicated)){ //CO
				sev = FDBDDCMSeverityFilter.fdbDDCMSFContraindicated ;
			}

			if(level.equals(MEXMEConfigMedico.MEDICALCONDITIONLEVEL_ExtremeCaution)){ //EC
				sev = FDBDDCMSeverityFilter.fdbDDCMSFExtremeCaution;
			}

			if(level.equals(MEXMEConfigMedico.MEDICALCONDITIONLEVEL_Warning)){ //WA
				sev = FDBDDCMSeverityFilter.fdbDDCMSFWarning ;
			}

			objResults =
				objScreen.DDCMScreen(objScrnDrugs,   // drugs to screen
						screenConditions,          // screen conditions
						false,				// Only screens against drugs marked as prospective
						sev,            // severity level filter
						FDBProxyPredictor.fdbPPDoNotUse); // optional
		}catch (Exception e){
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.error"), e);
			e.printStackTrace();
		}

		// See if the screening produced any results
		lngCnt = objResults.count();
		if (!(lngCnt >= 0)){
			msgDDCM.add("    == No Results ==");
		}else{
			DDCMScreenResult objDDCMRes =null;

			for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
				objDDCMRes = objResults.item(intInd);

				// Add an entry to the screen results listview control.
				msgDDCM.add(Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.initials") + " " + objDDCMRes.getConditionDescription() + " & " + objDDCMRes.getDrugDescription());

				// Add a description of the result to the screen results text box.
				msgDDCM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.result") + " " + Integer.toString(intInd + 1));
				msgDDCM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.interaction") + " " + objDDCMRes.getDescriptionDisease() + " & " + objDDCMRes.getDescriptionDrug());
				msgDDCM.add("    " + Utilerias.getMessage(ctx, null, "fdm.sceening.message") + ": " + objDDCMRes.getScreenMessage());
			}
		}
		return msgDDCM;
	} 

public static ArrayList<String> doDAMScreen(Properties ctx, String arrNDC, List<String> baseIngs, List<String> groups, List<String> drugs,String level){
		
		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, null);
		Screening objScreen = new Screening();
		ScreenAllergies allergies = new ScreenAllergies();
		ArrayList<String> msgDAM = new ArrayList<String>();

		//llenamos el objeto de condiciones medicas
		if(baseIngs!=null && baseIngs.size() > 0){
			for(int i=0; i< baseIngs.size(); i++){
				ScreenAllergy allergy = new ScreenAllergy();
				try{
					allergy.load(baseIngs.get(i), FDBAllergyType.fdbATIngredient);
				}catch (FDBException fdbe){
					System.out.println(fdbe.toString());
				}
				allergies.addItem(allergy);
			}
			DAMScreenResults objResults = null;

			long lngCnt;

			try{
		         // Returns a collection of drug-allergy results that will alert you 
				// if you have drugs that could result in an allergic reaction based 
				// on your patient�s known allergies.
				objResults =
					objScreen.DAMScreen(objScrnDrugs,   // drugs to screen
							allergies,          // screen allergies
							false);				// Only screens against drugs marked as prospective

			}catch (Exception e){
				s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.error"), e);
				e.printStackTrace();
			}

			// See if the screening produced any results
			lngCnt = objResults.count();
			if (!(lngCnt > 0)){
				//msgDAM.add("    == No Results ==");
			}else{
				DAMScreenResult objDAMRes =null;

				for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
					objDAMRes = objResults.item(intInd);

					// Add an entry to the screen results listview control.
					msgDAM.add(Utilerias.getMessage(ctx, null, "fdb.drugName") + ": " + objDAMRes.getDrugDescription());

					// Add a description of the result to the screen results text box.
					//msgDAM.add(objDAMRes.getAllergenCustomID());
					//msgDAM.add(objDAMRes.getAllergenDescription());
					//msgDAM.add(objDAMRes.getDrugCustomID());
					//msgDAM.add(objDAMRes.getDrugDescription());
					//msgDAM.add(objDAMRes.getInactiveHICLSeqNo());
					//msgDAM.add(objDAMRes.getMatchDescription());
					msgDAM.add(objDAMRes.getReaction());
					msgDAM.add(objDAMRes.getScreenMessage());

				}
			}
		}
		
		if(groups!=null && groups.size() > 0){
			for(int i=0; i< groups.size(); i++){
				ScreenAllergy allergy = new ScreenAllergy();
				try{
					allergy.load(groups.get(i), FDBAllergyType.fdbATAllergenGroup);
				}catch (FDBException fdbe){
					System.out.println(fdbe.toString());
				}
				allergies.addItem(allergy);
			}
			DAMScreenResults objResults = null;

			long lngCnt;

			try{
		         // Returns a collection of drug-allergy results that will alert you 
				// if you have drugs that could result in an allergic reaction based 
				// on your patient�s known allergies.
				objResults =
					objScreen.DAMScreen(objScrnDrugs,   // drugs to screen
							allergies,          // screen allergies
							false);				// Only screens against drugs marked as prospective

			}catch (Exception e){
				s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.error"), e);
				e.printStackTrace();
			}

			// See if the screening produced any results
			lngCnt = objResults.count();
			if (!(lngCnt > 0)){
				//msgDAM.add("    == No Results ==");
			}else{
				DAMScreenResult objDAMRes =null;

				for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
					objDAMRes = objResults.item(intInd);

					// Add an entry to the screen results listview control.
					msgDAM.add(Utilerias.getMessage(ctx, null, "fdb.drugName") + ": " + objDAMRes.getDrugDescription());

					// Add a description of the result to the screen results text box.
					//msgDAM.add(objDAMRes.getAllergenCustomID());
					//msgDAM.add(objDAMRes.getAllergenDescription());
					//msgDAM.add(objDAMRes.getDrugCustomID());
					//msgDAM.add(objDAMRes.getDrugDescription());
					//msgDAM.add(objDAMRes.getInactiveHICLSeqNo());
					//msgDAM.add(objDAMRes.getMatchDescription());
					msgDAM.add(objDAMRes.getReaction());
					msgDAM.add(objDAMRes.getScreenMessage());

				}
			}
		}
		
		if(drugs!=null && drugs.size() > 0){
			for(int i=0; i< drugs.size(); i++){
				ScreenAllergy allergy = new ScreenAllergy();
				try{
					allergy.load(drugs.get(i), FDBAllergyType.fdbATPackagedDrug);
				}catch (FDBException fdbe){
					System.out.println(fdbe.toString());
				}
				allergies.addItem(allergy);
			}
			DAMScreenResults objResults = null;

			long lngCnt;

			try{
		         // Returns a collection of drug-allergy results that will alert you 
				// if you have drugs that could result in an allergic reaction based 
				// on your patient�s known allergies.
				objResults =
					objScreen.DAMScreen(objScrnDrugs,   // drugs to screen
							allergies,          // screen allergies
							false);				// Only screens against drugs marked as prospective

			}catch (Exception e){
				s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugCondition.error"), e);
				e.printStackTrace();
			}

			// See if the screening produced any results
			lngCnt = objResults.count();
			if (!(lngCnt > 0)){
				//msgDAM.add("    == No Results ==");
			}else{
				DAMScreenResult objDAMRes =null;

				for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
					objDAMRes = objResults.item(intInd);

					// Add an entry to the screen results listview control.
					msgDAM.add(Utilerias.getMessage(ctx, null, "fdb.drugName") + ": " + objDAMRes.getDrugDescription());

					// Add a description of the result to the screen results text box.
					//msgDAM.add(objDAMRes.getAllergenCustomID());
					//msgDAM.add(objDAMRes.getAllergenDescription());
					//msgDAM.add(objDAMRes.getDrugCustomID());
					//msgDAM.add(objDAMRes.getDrugDescription());
					//msgDAM.add(objDAMRes.getInactiveHICLSeqNo());
					//msgDAM.add(objDAMRes.getMatchDescription());
					msgDAM.add(objDAMRes.getReaction());
					msgDAM.add(objDAMRes.getScreenMessage());

				}
			}
		}
		if(msgDAM.size() < 1){
			msgDAM.add("    == No Results ==");
		}
		return msgDAM;
} 
	/**
	 * Obtiene DFIM (drug-food) screening
	 * @param arrNDC
	 * @param NDC
	 * @return ArrayList<String>
	 * @author jcarranza
	 */
/*
	public static ArrayList<String> doScreenDFIM(Properties ctx, String arrNDC, String NDC, String level){

		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, NDC);
		Screening objScreen = new Screening();
		ArrayList<String> msgDFIM = new ArrayList<String>();

		DFIMScreenResults objResultsDFIM = null;
		long lngCnt;

		try{
			//default
			FDBDFIMSignificanceFilter sig = FDBDFIMSignificanceFilter.fdbDFIMSFMore ;

			if(level.equals(MEXMEConfigMedico.DRUGFOODLEVEL_Most)){ //MT
				sig = FDBDFIMSignificanceFilter.fdbDFIMSFMost;
			}

			if(level.equals(MEXMEConfigMedico.DRUGFOODLEVEL_More)){ //MR
				sig = FDBDFIMSignificanceFilter.fdbDFIMSFMore ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGFOODLEVEL_Significant)){ //SG
				sig = FDBDFIMSignificanceFilter.fdbDFIMSFSignificant ;
			}

			if(level.equals(MEXMEConfigMedico.DRUGFOODLEVEL_Less)){ //LS
				sig = FDBDFIMSignificanceFilter.fdbDFIMSFLess;
			}

			if(level.equals(MEXMEConfigMedico.DRUGFOODLEVEL_Minor)){ //MI
				sig = FDBDFIMSignificanceFilter.fdbDFIMSFMinor ;
			}


			//par�metro de la configuraci�n del m�dico
			objResultsDFIM = objScreen.DFIMScreen(objScrnDrugs,  // drugs to screen
					false,         // prospective only
					sig,           // significance filter
					false);
		}catch (Exception e){
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugFood.error"), e);
			e.printStackTrace();
		}

		// See if the screening produced any results
		lngCnt = objResultsDFIM.count();
		if (!(lngCnt >= 0)){
			System.out.println("    == No Results ==");
		}else{
			DFIMScreenResult objDFIMRes =null;

			for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
				objDFIMRes = objResultsDFIM.item(intInd);

				// Add an entry to the screen results listview control.
				msgDFIM.add(Utilerias.getMessage(ctx, null, "fdm.screening.drugFood.initials") + " " + objDFIMRes.getDrugDescription() + objDFIMRes.getMonographID());

				// Add a description of the result to the screen results text box.
				msgDFIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugFood.result") + " " + Integer.toString(intInd + 1));
				msgDFIM.add("    " + Utilerias.getMessage(ctx, null, "msj.medicamento") + ": " + objDFIMRes.getDrugDescription());
				msgDFIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.advice") + ": " + objDFIMRes.getAdviceMessage());
				msgDFIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.clinicalResult") + ": " + objDFIMRes.getClinicalResult());
				msgDFIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.sceening.message") + ": " + objDFIMRes.getScreenMessage());
			}
		}

		return msgDFIM;
	}  


	/**
	 * Obtiene DT (duplicate therapy) screening
	 * @param arrNDC
	 * @param NDC
	 * @return ArrayList<String>
	 * @author jcarranza
	 */
/*
	public static ArrayList<String> doScreenDT(Properties ctx, String arrNDC, String NDC, boolean allow){

		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, NDC);
		Screening objScreen = new Screening();
		ArrayList<String> msgDT = new ArrayList<String>();

		DTScreenResults objResultsDT = null;
		long lngCnt;

		try{
			objResultsDT = objScreen.DTScreen(objScrnDrugs, // drugs to screen
					false,        // prospective only
					allow         // use duplicate allowance
			);
		}catch (Exception e){
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.duplicateTherapy.error"), e);
			e.printStackTrace();
		}


		// See if the screening produced any results
		lngCnt = objResultsDT.count();
		if (!(lngCnt >= 0)){
			msgDT.add("    == No Results ==");
		}else{
			DTScreenResult objDTRes = null;

			for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
				objDTRes = objResultsDT.item(intInd);

				// Add an entry to the screen results listview control
				msgDT.add(Utilerias.getMessage(ctx, null, "fdm.screening.duplicateTherapy.initials") + " " + objDTRes.getClassDescription());

				// Add a description of the result to the screen results text box
				msgDT.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.duplicateTherapy.result") + " " + Integer.toString(intInd + 1));
				msgDT.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.duplicateTherapy.class")+ ": " + objDTRes.getClassDescription());

				// Create a list of the duplicated drugs reported by this result and
				// add it to the screen results text box
				DTScreenDrugItems objDrugItems = null;
				DTScreenDrugItem objDrugItem = null;
				long lngCnt2;
				int intInd2;
				String strDrugs = new String("");

				// Get the duplicated drugs collection object
				objDrugItems = objDTRes.getDrugItems();
				lngCnt2 = objDrugItems.count();
				for (intInd2 = 0; intInd2 < lngCnt2 - 1; intInd2++){
					objDrugItem = objDrugItems.item(intInd2);
					if (intInd2 > 0){
						strDrugs = strDrugs + ", ";
					}
					strDrugs = strDrugs + objDrugItem.getDrugDescription();
				}
				msgDT.add("    " + Utilerias.getMessage(ctx, null, "msj.internacion.farmacos")+ ": " + strDrugs);
				msgDT.add("    " + Utilerias.getMessage(ctx, null, "fdm.sceening.message") + ": " + objDTRes.getScreenMessage());
			}
		}
		return msgDT;
	} 



	public static boolean ExisteDIFwk(Properties ctx, String arrNDC, String NDC, List<MEXMECondMedica> list, MEXMEConfigMedico config, List<String> ings, List<String> groups, List<String> drugs){

		boolean ret = false;

		try
		{
			if( (config.isDrugDrug() ? doScreenDDIM(ctx, arrNDC, NDC, config.getDrugDrugLevel()).size() > 0  : false) ||
					(config.isMedicalCondition() ? doDDCMScreen(ctx, arrNDC+"@@@"+NDC, list,config.getMedicalConditionLevel()).size() > 0 : false) ||
					(config.isDrugFood() ? doScreenDFIM(ctx, arrNDC, NDC, config.getDrugFoodLevel()).size() > 0 : false) ||
					(config.isDrugAller() ? doDAMScreen(ctx, NDC, ings,groups,drugs,config.getDrugFoodLevel()).size() > 0 : false) ||
					(config.isDuplicateTherapy() ? doScreenDT(ctx, arrNDC, NDC, config.isDuplicateTherapyAllow()).size() > 0 : false) ){
				ret = true;
			}else{
				ret = false;
			}
		}
		catch (Exception fdbe)
		{
			s_log.log(Level.SEVERE, fdbe.getMessage());
			fdbe.printStackTrace();
		}


		return ret;
	} 

/**
	 * Obtiene DDIM (drug-alergen) screening
	 * @param arrNDC
	 * @param NDC
	 * @return ArrayList<String>
	 * @author jcarranza
	 */
/*
public static ArrayList<String> doScreenDAIM(Properties ctx, String arrNDC, String NDC){

		ScreenDrugs objScrnDrugs = getObjScrnDrugs(ctx, arrNDC, NDC);
		//ScreenAllergies screenAllergies = 

		Screening objScreen = new Screening();

		ArrayList<String> msgDDIM = new ArrayList<String>();

		//msgDDIM.add(" DDIM (drug-drug) screening");  

		DDIMScreenResults objResultsDDIM = null;
		long lngCnt;

		// Identify what's being screened in the Screen Results text box
		//msgDDIM.add("[ Screen DDIM ]");

		// Perform the screening
		// The screening options are:
		//   Retrospective screening (screen all drugs)
		//   Minimum interaction severity level to report is Moderate
		//   Don't use custom interactions
		try
		{
			FDBDDIMSeverityFilter sev = FDBDDIMSeverityFilter.fdbDDIMSFModerate ;
			/*objResultsDDIM =
			objScreen.DAMScreen(objScrnDrugs, allergens, true);
			 */                    //objScrnDrugs, allergens, prospectiveOnly
			//.DDIMScreen(objScrnDrugs,   // drugs to screen
			//false,          // prospective only
			//sev,            // severity level filter
			//false,          // use custom interactions
			//false);   // Load User Category
/*
		}catch (Exception e){
			s_log.log(Level.SEVERE, Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.error"), e);
			e.printStackTrace();
		}


		// See if the screening produced any results
		lngCnt = objResultsDDIM.count();
		if (!(lngCnt >= 0)){
			msgDDIM.add("    == No Results ==");
		}else{
			DDIMScreenResult objDDIMRes =null;

			for (int intInd = 0; intInd <= (int)lngCnt - 1; intInd++){
				objDDIMRes = objResultsDDIM.item(intInd);

				// Add an entry to the screen results listview control.
				msgDDIM.add(Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.initials") + " " + objDDIMRes.getDrug1Description() + " & " + objDDIMRes.getDrug2Description()+ objDDIMRes.getMonographID());

				// Add a description of the result to the screen results text box.
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.result") + " " + Integer.toString(intInd + 1));
				msgDDIM.add("    " + objDDIMRes.getDrug1Description() + " & " + objDDIMRes.getDrug2Description());
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.screening.drugDrug.interaction") + ": " + objDDIMRes.getInteractionDescription());
				msgDDIM.add("    " + Utilerias.getMessage(ctx, null, "fdm.sceening.message") + ": " + objDDIMRes.getScreenMessage());
			}
		}
		return msgDDIM;
}


}*/
