package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEPacBeneficiarios;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MLocation;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;


public class CreateBeneficiary extends SvrProcess{
	
	private int exmePacienteID = 0;

	@Override
	protected String doIt() throws Exception {
		String msg = "";
		if(exmePacienteID>0){
			MEXMEPaciente pacFrom = new MEXMEPaciente(getCtx(), exmePacienteID, get_TrxName());
			MEXMEPaciente pacTo = new MEXMEPaciente(getCtx(), 0, get_TrxName());
			
			MLocation locationPac = new MLocation(getCtx(), pacFrom.getC_Location_ID(), null);
			MLocation locationMailPac = null;
			MLocation locationMail = null;
			if(pacFrom.getC_Location_Mail_ID()>0){
				locationMailPac = new MLocation(getCtx(), pacFrom.getC_Location_Mail_ID(), null);
				locationMail = MLocation.copyFrom(getCtx(), locationMailPac, 0, null);
			}
			
			MLocation locationFact = MLocation.copyFrom(getCtx(), locationPac, 0, null);
			MLocation locationResp = MLocation.copyFrom(getCtx(), locationPac, 0, null);
			
			if(!locationFact.save() || !locationResp.save() || (locationMail!=null && !locationMail.save())){
				msg = Utilerias.getMessage(getCtx(), null, "error.guardar");
			}else{
				pacTo.setValue(DB.getDocumentNo (0, MEXMEPaciente.Table_Name, null) );
				pacTo.setName(pacTo.getValue() + "-Beneficiary");
				pacTo.setApellido1(pacFrom.getApellido1());
				pacTo.setApellido2(pacFrom.getApellido2());
				pacTo.setSexo(MEXMEPaciente.SEXO_Unassigned);
				pacTo.setTipoPaciente(pacFrom.getTipoPaciente());
				pacTo.setFechaNac(DB.getTimestampForOrg(Env.getCtx()));
				pacTo.setFechaRegistro(DB.getTimestampForOrg(Env.getCtx()));
				pacTo.setEXME_PacienteFam_ID(pacFrom.getEXME_Paciente_ID());
				pacTo.setC_Location_ID(locationFact.getC_Location_ID());
				if(locationMail!=null){
					pacTo.setC_Location_Mail_ID(locationMail.getC_Location_ID());
				}
				pacTo.setTelParticular(pacFrom.getTelParticular());
				pacTo.setParticularComCode(pacFrom.getParticularComCode());
				pacTo.setC_BPartner_ID(pacFrom.getC_BPartner_ID());
				pacTo.setEsAsegurado(pacFrom.isEsAsegurado());
				pacTo.setCreateBeneficiary("Y");
				pacTo.setEdoCivil(MEXMEPaciente.EDOCIVIL_Single);
				pacTo.setNombre_Fam(pacFrom.getName());
				pacTo.setApellido1_Fam(pacFrom.getApellido1());
				pacTo.setTelParticular_Fam(pacFrom.getTelParticular());
				pacTo.setEXME_Ocupacion_Fam_ID(pacFrom.getEXME_Ocupacion_ID());
				pacTo.setRFC_Fam(pacFrom.getRfc());
				pacTo.setC_LocationPerResp_ID(locationResp.getC_Location_ID());
				
//				if(pacFrom.isEsAsegurado()){
//					pacTo.setC_BPartner_Seg_ID(pacFrom.getC_BPartner_Seg_ID());
//				}
				if(!pacTo.save()){
					msg = Utilerias.getMessage(getCtx(), null, "error.guardar");
				}else{
					MEXMEPacBeneficiarios beneficiary = new MEXMEPacBeneficiarios(getCtx(), 0, get_TrxName());
					beneficiary.setEXME_Paciente_ID(pacFrom.getEXME_Paciente_ID());
					beneficiary.setEXME_Beneficario_ID(pacTo.getEXME_Paciente_ID());
					if(!beneficiary.save()){
						msg = Utilerias.getMessage(getCtx(), null, "error.guardar");
					}else{
						
						List<MEXMEPacienteAseg> array = new ArrayList<MEXMEPacienteAseg>();
						
						array = MEXMEPacienteAseg.getAllByPatient(getCtx(), pacFrom.getEXME_Paciente_ID());
						
						if(array!=null && !array.isEmpty()){
							boolean saved = true;
							for(MEXMEPacienteAseg aseg: array){
								MEXMEPacienteAseg asegTo = new MEXMEPacienteAseg(getCtx(), 0, get_TrxName());
								asegTo.setEXME_Paciente_ID(pacTo.getEXME_Paciente_ID());
								asegTo.setC_BPartner_ID(aseg.getC_BPartner_ID());
								asegTo.setIsMain(aseg.isMain());
								asegTo.setPoliza(aseg.getPoliza());
								asegTo.setFechaIni(aseg.getFechaIni());
								asegTo.setFechaFin(aseg.getFechaFin());
								asegTo.setEXME_PlanAseg_ID(aseg.getEXME_PlanAseg_ID());
								if(!asegTo.save()){
									saved = false;
									break;
								}
							}
							if(!saved){
								msg = Utilerias.getMessage(getCtx(), null, "error.guardar");
							}else{
								Object[] args = {pacTo.getNombre_Pac()};
								msg = Utilerias.getMessage(getCtx(), null, "diarioEnf.msg.save", args);
							}
						}else{
							Object[] args = {pacTo.getNombre_Pac()};
							msg = Utilerias.getMessage(getCtx(), null, "diarioEnf.msg.save", args);
						}
						
						
					}
				}
			
			}
			
				
		}
		
		return msg;
	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			if (para[i].getParameter() != null){
				String name = para[i].getParameterName();
				if(name.equals("EXME_Paciente_ID")){
					exmePacienteID = ((BigDecimal)para[i].getParameter()).intValue();
				}
			}else{
				log.log(Level.SEVERE, "prepare - Unknown Parameters ");
			}
		}
		
	}

}
