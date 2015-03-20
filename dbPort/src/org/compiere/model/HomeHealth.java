package org.compiere.model;

import static org.compiere.util.Utilerias.getAppMsg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Properties;

import org.compiere.model.bpm.BeanView;
import org.compiere.util.ExpertException;

/**
 * @author odelarosa
 * 
 */
public class HomeHealth implements Serializable {
	private static final long serialVersionUID = 5662131866634490451L;
	private boolean checkbox = false;
	private boolean checkbox2 = false;
	private boolean checkbox3 = false;
	private boolean checkbox4 = false;
	private boolean checkbox5 = false;
	private boolean checkbox6 = false;
	private boolean checkbox7 = false;
	private boolean checkbox8 = false;
	private boolean checkbox9 = false;
	private boolean checkbox10 = false;
	private boolean checkbox11 = false;
	private boolean checkbox12 = false;
	private boolean checkbox13 = false;
	private boolean checkbox14 = false;
	private double doublebox = 0;
	private double doublebox2 = 0;
	private double doublebox3 = 0;
	private double doublebox4 = 0;
	private double doublebox5 = 0;
	private double doublebox6 = 0;
	private double doublebox7 = 0;
	private double doublebox8 = 0;
	private double doublebox9 = 0;
	private double doublebox10 = 0;
	private double doublebox11 = 0;
	private double doublebox12 = 0;
	private double doublebox13 = 0;
	private double doublebox14 = 0;

	public HomeHealth() {

	}

	public double getDoublebox() {
		return doublebox;
	}

	public double getDoublebox10() {
		return doublebox10;
	}

	public double getDoublebox11() {
		return doublebox11;
	}

	public double getDoublebox12() {
		return doublebox12;
	}

	public double getDoublebox13() {
		return doublebox13;
	}

	public double getDoublebox14() {
		return doublebox14;
	}

	public double getDoublebox2() {
		return doublebox2;
	}

	public double getDoublebox3() {
		return doublebox3;
	}

	public double getDoublebox4() {
		return doublebox4;
	}

	public double getDoublebox5() {
		return doublebox5;
	}

	public double getDoublebox6() {
		return doublebox6;
	}

	public double getDoublebox7() {
		return doublebox7;
	}

	public double getDoublebox8() {
		return doublebox8;
	}

	public double getDoublebox9() {
		return doublebox9;
	}

	public HashMap<Integer, BeanView> getProductsIds(Properties ctx) throws ExpertException {
		HashMap<Integer, BeanView> map = new HashMap<Integer, BeanView>();
		if (checkbox) {
			int id = MEXMEProduct.getProduct(ctx, "G0151", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0151"));
			}
		}
		if (checkbox2) {
			int id = MEXMEProduct.getProduct(ctx, "G0157", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox2), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0157"));
			}
		}
		if (checkbox3) {
			int id = MEXMEProduct.getProduct(ctx, "G0159", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox3), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0159"));
			}
		}
		if (checkbox4) {
			int id = MEXMEProduct.getProduct(ctx, "G0152", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox4), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0152"));
			}
		}
		if (checkbox5) {
			int id = MEXMEProduct.getProduct(ctx, "G0158", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox5), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0158"));
			}
		}
		if (checkbox6) {
			int id = MEXMEProduct.getProduct(ctx, "G0160", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox6), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0160"));
			}
		}
		if (checkbox7) {
			int id = MEXMEProduct.getProduct(ctx, "G0153", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox7), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0153"));
			}
		}
		if (checkbox8) {
			int id = MEXMEProduct.getProduct(ctx, "G0161", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox8), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0161"));
			}
		}
		if (checkbox9) {
			int id = MEXMEProduct.getProduct(ctx, "G0154", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox9), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0154"));
			}
		}
		if (checkbox10) {
			int id = MEXMEProduct.getProduct(ctx, "G0162", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox10), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0162"));
			}
		}
		if (checkbox11) {
			int id = MEXMEProduct.getProduct(ctx, "G0163", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox11), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0163"));
			}
		}
		if (checkbox12) {
			int id = MEXMEProduct.getProduct(ctx, "G0164", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox12), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0164"));
			}
		}
		if (checkbox13) {
			int id = MEXMEProduct.getProduct(ctx, "G0155", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox13), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0155"));
			}
		}
		if (checkbox14) {
			int id = MEXMEProduct.getProduct(ctx, "G0156", MEXMEProduct.PRODUCTCLASS_HomeHealt, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox14), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "G0156"));
			}
		}
		return map;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public boolean isCheckbox10() {
		return checkbox10;
	}

	public boolean isCheckbox11() {
		return checkbox11;
	}

	public boolean isCheckbox12() {
		return checkbox12;
	}

	public boolean isCheckbox13() {
		return checkbox13;
	}

	public boolean isCheckbox14() {
		return checkbox14;
	}

	public boolean isCheckbox2() {
		return checkbox2;
	}

	public boolean isCheckbox3() {
		return checkbox3;
	}

	public boolean isCheckbox4() {
		return checkbox4;
	}

	public boolean isCheckbox5() {
		return checkbox5;
	}

	public boolean isCheckbox6() {
		return checkbox6;
	}

	public boolean isCheckbox7() {
		return checkbox7;
	}

	public boolean isCheckbox8() {
		return checkbox8;
	}

	public boolean isCheckbox9() {
		return checkbox9;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setCheckbox10(boolean checkbox10) {
		this.checkbox10 = checkbox10;
	}

	public void setCheckbox11(boolean checkbox11) {
		this.checkbox11 = checkbox11;
	}

	public void setCheckbox12(boolean checkbox12) {
		this.checkbox12 = checkbox12;
	}

	public void setCheckbox13(boolean checkbox13) {
		this.checkbox13 = checkbox13;
	}

	public void setCheckbox14(boolean checkbox14) {
		this.checkbox14 = checkbox14;
	}

	public void setCheckbox2(boolean checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public void setCheckbox3(boolean checkbox3) {
		this.checkbox3 = checkbox3;
	}

	public void setCheckbox4(boolean checkbox4) {
		this.checkbox4 = checkbox4;
	}

	public void setCheckbox5(boolean checkbox5) {
		this.checkbox5 = checkbox5;
	}

	public void setCheckbox6(boolean checkbox6) {
		this.checkbox6 = checkbox6;
	}

	public void setCheckbox7(boolean checkbox7) {
		this.checkbox7 = checkbox7;
	}

	public void setCheckbox8(boolean checkbox8) {
		this.checkbox8 = checkbox8;
	}

	public void setCheckbox9(boolean checkbox9) {
		this.checkbox9 = checkbox9;
	}

	public void setDoublebox(double doublebox) {
		this.doublebox = doublebox;
	}

	public void setDoublebox10(double doublebox10) {
		this.doublebox10 = doublebox10;
	}

	public void setDoublebox11(double doublebox11) {
		this.doublebox11 = doublebox11;
	}

	public void setDoublebox12(double doublebox12) {
		this.doublebox12 = doublebox12;
	}

	public void setDoublebox13(double doublebox13) {
		this.doublebox13 = doublebox13;
	}

	public void setDoublebox14(double doublebox14) {
		this.doublebox14 = doublebox14;
	}

	public void setDoublebox2(double doublebox2) {
		this.doublebox2 = doublebox2;
	}

	public void setDoublebox3(double doublebox3) {
		this.doublebox3 = doublebox3;
	}

	public void setDoublebox4(double doublebox4) {
		this.doublebox4 = doublebox4;
	}

	public void setDoublebox5(double doublebox5) {
		this.doublebox5 = doublebox5;
	}

	public void setDoublebox6(double doublebox6) {
		this.doublebox6 = doublebox6;
	}

	public void setDoublebox7(double doublebox7) {
		this.doublebox7 = doublebox7;
	}

	public void setDoublebox8(double doublebox8) {
		this.doublebox8 = doublebox8;
	}

	public void setDoublebox9(double doublebox9) {
		this.doublebox9 = doublebox9;
	}

}
