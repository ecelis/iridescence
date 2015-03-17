package org.compiere.model.bpm;


/**
 * 
 * @author Expert
 * 
 */
public class QueryTreatments {//extends POQuery {
//	/** Static logger */
//	private static CLogger log = CLogger.getCLogger(QueryTreatments.class);
//	/** serialVersionUID */
//	private static final long serialVersionUID = -6073521135505484597L;
//	/** */
//	public HashMap<String, MetaData> parentClassesConfig = new HashMap<String, MetaData>();
//
//	/**
//	 * Constructor
//	 * 
//	 * @param ctx
//	 */
//	public QueryTreatments(final Properties pCtx, final boolean pTest) {
//		super(pCtx, pTest);
//		// invocar init();
//	}
//
//	/**
//	 * Constructor
//	 * 
//	 * @param ctx
//	 */
//	public QueryTreatments(final Properties pCtx) {
//		super(pCtx);
//		// invoicar init();
//	}
//
//	/**
//	 * Class listings begin parent-child classes
//	 */
//	public void init() {
//
//		parentClasses = new PO[] { new MEXMETratamientosCues(ctx, 0, null),
//				new MEXMETratamientosPaq(ctx, 0, null),
//				new MEXMETratamientosServ(ctx, 0, null),
//				new MEXMETratamientosProductos(ctx, 0, null),
//				new MEXMETratamientosPres(ctx, 0, null) };
//
//		// configuration
//		subclasses = new HashMap<String, PO[]>();
//		subclasses.put(X_EXME_Tratamientos_Cues.Table_Name,
//				new PO[] { new MCuestionario(ctx, 0, null) });
//		subclasses.put(X_EXME_Tratamientos_Paq.Table_Name,
//				new PO[] { new MPaqBase(ctx, 0, null) });
//		subclasses.put(X_EXME_Tratamientos_Serv.Table_Name,
//				new PO[] { new MEXMEProduct(ctx, 0, null) });
//		subclasses.put(X_EXME_Tratamientos_Productos.Table_Name,
//				new PO[] { new MEXMEProduct(ctx, 0, null) });
//		subclasses
//				.put(X_EXME_Tratamientos_Pres.Table_Name, new PO[] {
//						new MEXMEProduct(ctx, 0, null),
//						new MEXMEDoseForm(ctx, 0, null),
//						new MEXMERoute(ctx, 0, null) });
//
//		// record patient
//		relatedClasses = new HashMap<String, PO[]>();
//		relatedClasses.put(X_EXME_Tratamientos_Detalle.Table_Name, new PO[] {
//				new MEXMECitaMedica(ctx, 0, null),
//				new MEXMECuidadosPac(ctx, 0, null) });
//		relatedClasses.put(X_EXME_Tratamientos_Cues.Table_Name,
//				new PO[] { new MEXMEActPacienteDet(ctx, 0, null) });
//		relatedClasses.put(X_EXME_Tratamientos_Paq.Table_Name,
//				new PO[] { new MEXMECtaPacPaq(ctx, 0, null) });
//		relatedClasses.put(X_EXME_Tratamientos_Serv.Table_Name,
//				new PO[] { new MEXMEActPacienteIndH(ctx, 0, null) });
//		relatedClasses.put(X_EXME_Tratamientos_Productos.Table_Name,
//				new PO[] { new MEXMESalidaGasto(ctx, 0, null) });
//		relatedClasses.put(X_EXME_Tratamientos_Pres.Table_Name, new PO[] {
//				new MPlanMed(ctx, 0, null), new MEXMEDoseForm(ctx, 0, null),
//				new MEXMERoute(ctx, 0, null) });
//
//		// 
//		parentClassesConfig = new HashMap<String, MetaData>();
//
//		// 
//		for (int i = 0; i < parentClasses.length; i++) {
//
//			MetaData data = new MetaData(parentClasses[i].getP_info()
//					.getTableName(), parentClasses[i].getP_info()
//					.getKeyColumnInfo().ColumnLabel, null, null, null,
//					parentClasses[i]);
//			parentClassesConfig
//					.put(parentClasses[i].getClass().getName(), data);
//
//		}
//	}
//
//	/**
//	 * From
//	 * 
//	 * @param mainClass
//	 */
//	@Override
//	public StringBuilder from(final PO mainClass) {
//
//		String fromTable = mainClass.get_TableName();
//		String fromNick = mainClass.getNickName();
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" FROM ").append(mainClass.get_TableName()).append(" ")
//				.append(mainClass.getP_info().getNickname()).append(" ");
//		//
//		List<String> lstRep = new ArrayList<String>();
//		lstRep.add(mainClass.get_TableName());
//		for (int i = 0; i < parentClasses.length; i++) {
//			// String nickNamei = parentClasses[i].getNickName();
//			// if(lstRep.contains(parentClasses[i].get_TableName()))
//			// nickNamei="i"+i+parentClasses[i].getNickName();
//
//			lstRep.add(parentClasses[i].get_TableName());
//
//			// Relationship table refers to the main table trat_serv -> trat_det
//			sql.append(AccessDB.getLeftJoin(parentClasses[i].get_TableName(),
//					parentClasses[i].getNickName(), fromTable, fromNick));
//
//			// List of tables referenced
//			PO[] lstPO = subclasses.get(parentClasses[i].get_TableName());
//
//			// 
//			if (lstPO == null)
//				continue;
//
//			fromTable = parentClasses[i].get_TableName();
//			fromNick = parentClasses[i].getNickName();
//
//			// 
//			for (int j = 0; j < lstPO.length; j++) {
//				String nickName = lstPO[j].getNickName();
//				if (lstRep.contains(lstPO[j].get_TableName())) {
//					nickName = "j" + (i + j) + lstPO[j].getNickName();
//					lstPO[j].setNickName(nickName);
//				}
//
//				lstRep.add(lstPO[j].get_TableName());
//
//				// Relationship table refers to the main table prod -> trat_serv
//				sql.append(AccessDB.getLeftJoinInv(lstPO[j].get_TableName(),
//						nickName, fromTable, fromNick));
//
//				// From the second level
//				if (lstPO.length > 1 && j == 0) {
//					fromTable = lstPO[j].get_TableName();
//					fromNick = nickName;
//				}
//
//			}
//
//			fromTable = mainClass.get_TableName();
//			fromNick = mainClass.getNickName();
//		}
//
//		if (test) {
//			log.log(Level.INFO, "QueryTreatments.from" + sql.toString());
//		}
//		return sql;
//
//	}
//
//	/*
//	 * BasicoTresProps[] selectSesion= new BasicoTresProps[]{ new
//	 * BasicoTresProps
//	 * (tablas.get(X_M_Product.Table_Name),X_M_Product.COLUMNNAME_Name
//	 * ,"NameProd"), new
//	 * BasicoTresProps(tablas.get(X_EXME_DoseForm.Table_Name),X_EXME_DoseForm
//	 * .COLUMNNAME_Description,"NameDose"), new
//	 * BasicoTresProps(tablas.get(X_EXME_Route
//	 * .Table_Name),X_EXME_Route.COLUMNNAME_Description1,"NameRoute"), new
//	 * BasicoTresProps
//	 * (tablas.get(X_M_Product.Table_Name),X_M_Product.COLUMNNAME_Strength
//	 * ,"NameStrength"), new
//	 * BasicoTresProps(tablas.get(X_M_Product.Table_Name),X_M_Product
//	 * .COLUMNNAME_Strengthunits,"NameStrengthUOM")};
//	 * getP_info().setBuildSelect(selectSesion);
//	 */
//
//	@Override
//	public StringBuilder order(PO mainClass) {
//		return null;
//	}
//
//	@Override
//	public StringBuilder select(PO mainClass) {
//		return null;
//	}
//
//	@Override
//	public StringBuilder where(PO mainClass) {
//		return null;
//	}
}
