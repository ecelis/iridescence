package org.compiere.process;

import org.compiere.model.MHL7ComponentDef;
import org.compiere.model.MHL7FieldDef;
import org.compiere.model.MHL7MessageDef;
import org.compiere.model.MHL7SegmentDef;
import org.compiere.model.MHL7SubcomponentDef;
import org.compiere.util.Trx;


//TODO limpiar la logica de los meotdos createXXX() limpiar los break y la repetidas asginaciones de wereCreated
//TODO revisar metodos createXXX: checar que no se enreden las llamadas a save() con los break;
public class CopyHL7MessageDef extends SvrProcess {

	private int HL7_MessageDef_ID;
	private MHL7MessageDef original;


	//private String trxName = "trxName" +System.currentTimeMillis()+this.toString();
	
	private Trx trx = null;
	
	

	public CopyHL7MessageDef() {
		trx = Trx.get(this.toString()+System.currentTimeMillis(), true);
	}

	@Override
	protected String doIt() throws Exception {

		if (original == null) {
			return "Error en mensaje seleccionado";
		}
		
		//probar bine esto
		if(createSegments()){
			trx.commit();
			addLog(0, null, null, "Exito en Copiado");
		}else{
			addLog(0, null, null, "Error en Copidado");
			trx.rollback();
		}
		
		return "";
		
	}

	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("HL7_MessageDef_ID"))
				HL7_MessageDef_ID = para[i].getParameterAsInt();
		}
		original = new MHL7MessageDef(getCtx(), HL7_MessageDef_ID, null);
	}

	private boolean createSegments() {
		boolean wereCreated = true;

		MHL7MessageDef copy = new MHL7MessageDef(getCtx(), getRecord_ID(),
				null);

		for (MHL7SegmentDef segmentoOriginal : original.getSegmentsDefs()) {			

			MHL7SegmentDef segmentCopy = new MHL7SegmentDef(getCtx(), 0,
					null);
			
			segmentCopy.setSequence(segmentoOriginal.getSequence());
			segmentCopy.setRepeated(segmentoOriginal.getRepeated());
			segmentCopy.setDescription(segmentoOriginal.getDescription());
			segmentCopy.setHL7_Segment_ID(segmentoOriginal.getHL7_Segment_ID());
			segmentCopy.setOptionality(segmentoOriginal.getOptionality());
			segmentCopy.setIsParent(segmentoOriginal.isParent());
			segmentCopy.setIsGroup(segmentoOriginal.isGroup());
			segmentCopy.setHL7_SegmentDef2_ID(segmentoOriginal.getHL7_SegmentDef2_ID());
			segmentCopy.setHL7_MessageDef_ID(copy.getHL7_MessageDef_ID());

			if (segmentCopy.save(trx.getTrxName())) {
				
				if (!createFields(segmentoOriginal, segmentCopy.getHL7_SegmentDef_ID())){
					wereCreated = false;
					break;
				}
				
			}else{
				wereCreated = false;
				break;
			}

		}
		return wereCreated;

	}

	/*
	 * HL7_SegmentDef_ID = id del elemento padre
	 */
	private boolean createFields(MHL7SegmentDef orginalSegment, int segmentDef_ID) {
		boolean wereCreated = true;

		for (MHL7FieldDef originalField : orginalSegment.getFieldDefs()) {

			MHL7FieldDef fieldCopy = new MHL7FieldDef(getCtx(), 0, null);
			
			fieldCopy.setHL7_Field_ID(originalField.getHL7_Field_ID());
			fieldCopy.setDescription(originalField.getDescription());
			fieldCopy.setHasComponents(originalField.isHasComponents());
			fieldCopy.setHL7_LinkedTable_ID(originalField.getHL7_LinkedTable_ID());
			fieldCopy.setHL7_SegmentDef_ID(segmentDef_ID);
			//fieldCopy.setIncrementType(fi)
			fieldCopy.setIsIdentifier(originalField.isIdentifier());
			fieldCopy.setOptionality(originalField.getOptionality());
			fieldCopy.setRepeated(originalField.getRepeated());
			fieldCopy.setSequence(originalField.getSequence());
			
			if (fieldCopy.save(trx.getTrxName())) {
				
				if (!createComponent(originalField, fieldCopy.getHL7_FieldDef_ID())){
					wereCreated = false;
					break;
				}
				
			}else{
				wereCreated = false;
				break;					
			}
			
		}
		
		return wereCreated;
	}

	/*
	 * 
	 * HL7_FieldDef_ID = id del elemento padre
	 */
	private boolean createComponent(MHL7FieldDef originalfield, int fieldDef_ID) {

		boolean wereCreated = true;
		
		for (MHL7ComponentDef originalComp : originalfield.getComponentsDefs()) {
			MHL7ComponentDef compCopy = new MHL7ComponentDef(getCtx(), 0,
					null);

			compCopy.setDescription(originalComp.getDescription());
			compCopy.setHasSubcomponents(originalComp.isHasSubcomponents());
			compCopy.setHL7_Component_ID(originalComp.getHL7_Component_ID());
			compCopy.setHL7_FieldDef_ID(fieldDef_ID);
			compCopy.setHL7_LinkedTable_ID(originalComp.getHL7_LinkedTable_ID());
			compCopy.setOptionality(originalComp.getOptionality());
			compCopy.setRepeated(originalComp.getRepeated());
			compCopy.setSequence(originalComp.getSequence());

			if (compCopy.save(trx.getTrxName())) {
				
				if(!createSubcomponent(originalComp, compCopy.getHL7_ComponentDef_ID())){
					wereCreated = false;
					break;				
				}
				
			}else{
				wereCreated = false;
				break;					
			}

		}
		return wereCreated;

	}

	private boolean createSubcomponent(MHL7ComponentDef originalComp,
			int componentDef_ID) {
		boolean wereCreated = true;

		for (MHL7SubcomponentDef sub : originalComp.getSubcomponentsDefs()) {
			MHL7SubcomponentDef subCopy = new MHL7SubcomponentDef(getCtx(), 0,
					null);

			subCopy.setDescription(sub.getDescription());
			subCopy.setHL7_ComponentDef_ID(componentDef_ID);
			subCopy.setHL7_LinkedTable_ID(sub.getHL7_LinkedTable_ID());
			subCopy.setHL7_Subcomponent_ID(sub.getHL7_Subcomponent_ID());
			subCopy.setOptionality(sub.getOptionality());
			subCopy.setRepeated(sub.getRepeated());
			subCopy.setSequence(sub.getSequence());
			
			if(!subCopy.save(trx.getTrxName())){
				wereCreated = true;
				break;			
				
			}
			
		}
		
		return wereCreated;

	}

}
