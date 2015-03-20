package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.Env;

public class MHL7GenerationTable extends X_HL7_GenerationTable {

	public MHL7GenerationTable(Properties ctx, int HL7_GenerationTable_ID,
			String trxName) {
		super(ctx, HL7_GenerationTable_ID, trxName);

	}

	public MHL7GenerationTable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public static ArrayList<MHL7GenerationTable> getAll(){
		
		ArrayList<MHL7GenerationTable> generationTables = new ArrayList<MHL7GenerationTable>();		
		int ids[] = MTable.getAllIDs(Table_Name, null, null);
		
		for (int id : ids){
			generationTables.add(new MHL7GenerationTable(Env.getCtx(), id, null));
		}		

		return generationTables;
	}
	
	

}
