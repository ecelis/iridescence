package org.compiere.util.mo;

/** @deprecated */
public class ServiciosMO {
	
	public ServiciosMO(){
		
	}
	
	private int m_product_id=0;
	private String value=null;
	private String name=null;
	private String  descripcion =null;
	private int c_uom_id=0;
	private int m_productcategory_id=0;
	private int m_freightcategory_id=0;
	private int m_warehouse_id=0;
	
	
	
	private String unidadMedidaTxt=null;
	private int unidadMedidaId=0;
	private int cantidad=1;
	private String indicacion=null;
	private String diagTxt1=null;
	private String diagTxt2=null;
	private String diagTxt3=null;
	private int diagId1=0;
	private int diagId2=0;
	private int diagId3=0;
	
	
	private String aplicar =null;
	private boolean applyBeforeClose = false;
	
	private String aplicarOdoTxt =null;
	private boolean aplicarOdo =false;
	
	public int getM_product_id() {
		return m_product_id;
	}
	public void setM_product_id(int mProductId) {
		m_product_id = mProductId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getC_uom_id() {
		return c_uom_id;
	}
	public void setC_uom_id(int cUomId) {
		c_uom_id = cUomId;
	}
	public int getM_productcategory_id() {
		return m_productcategory_id;
	}
	public void setM_productcategory_id(int mProductcategoryId) {
		m_productcategory_id = mProductcategoryId;
	}
	public int getM_freightcategory_id() {
		return m_freightcategory_id;
	}
	public void setM_freightcategory_id(int mFreightcategoryId) {
		m_freightcategory_id = mFreightcategoryId;
	}
	
	public String getUnidadMedidaTxt() {
		return unidadMedidaTxt;
	}
	public void setUnidadMedidaTxt(String unidadMedidaTxt) {
		this.unidadMedidaTxt = unidadMedidaTxt;
	}
	public int getUnidadMedidaId() {
		return unidadMedidaId;
	}
	public void setUnidadMedidaId(int unidadMedidaId) {
		this.unidadMedidaId = unidadMedidaId;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getIndicacion() {
		return indicacion;
	}
	public void setIndicacion(String indicacion) {
		this.indicacion = indicacion;
	}
	public String getDiagTxt1() {
		return diagTxt1;
	}
	public void setDiagTxt1(String diagTxt1) {
		this.diagTxt1 = diagTxt1;
	}
	public String getDiagTxt2() {
		return diagTxt2;
	}
	public void setDiagTxt2(String diagTxt2) {
		this.diagTxt2 = diagTxt2;
	}
	public String getDiagTxt3() {
		return diagTxt3;
	}
	public void setDiagTxt3(String diagTxt3) {
		this.diagTxt3 = diagTxt3;
	}
	public int getDiagId1() {
		return diagId1;
	}
	public void setDiagId1(int diagId1) {
		this.diagId1 = diagId1;
	}
	public int getDiagId2() {
		return diagId2;
	}
	public void setDiagId2(int diagId2) {
		this.diagId2 = diagId2;
	}
	public int getDiagId3() {
		return diagId3;
	}
	
	public void setDiagId3(int diagId3) {
		this.diagId3 = diagId3;
	}
	public int getM_warehouse_id() {
		return m_warehouse_id;
	}
	public void setM_warehouse_id(int mWarehouseId) {
		m_warehouse_id = mWarehouseId;
	}
	
	private String simbolo=null;
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	/*public DiagInfo busqueda(int diag){
		DiagInfo info = null ;
		StringBuilder sql= new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  try{
			  sql.append("SELECT * FROM ")
			     .append("  EXME_Diagnostico")
			     .append("  WHERE exme_diagnostico_id=?");
			  pstmt = DB.prepareStatement(sql.toString(), null);
			  pstmt = DB.prepareStatement(sql.toString(),null);
			  pstmt.setLong(1, diag);
			  rs=pstmt.executeQuery();
			  while(rs.next()){
				  info = new DiagInfo();
				  info.setM_diag_id(rs.getInt("Exme_Diagnostico_ID"));
				  info.setName(rs.getString("Name"));
			  }
			  
			  
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  
		  }
		
		return info;
	}*/
	
	public String getAplicar() {
		return aplicar;
	}
	public void setAplicar(String aplicar) {
		this.aplicar = aplicar;
	}
	
	public boolean getApplyBeforeClose() {
		return applyBeforeClose;
	}
	public void setApplyBeforeClose(boolean applyBeforeClose) {
		this.applyBeforeClose = applyBeforeClose;
	}
	public String getAplicarOdoTxt() {
		return aplicarOdoTxt;
	}
	public void setAplicarOdoTxt(String aplicarOdoTxt) {
		this.aplicarOdoTxt = aplicarOdoTxt;
	}
	public boolean isAplicarOdo() {
		return aplicarOdo;
	}
	public void setAplicarOdo(boolean aplicarOdo) {
		this.aplicarOdo = aplicarOdo;
	}
	/**lhernandez. ticket 2330**/
	private boolean add = false;

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}
	/**lhernandez. ticket 2330**/
}
