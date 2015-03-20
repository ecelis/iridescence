package org.compiere.model;

import static org.compiere.util.Utilerias.getAppMsg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.model.bpm.BeanView;
import org.compiere.util.ExpertException;

/**
 * @author odelarosa
 * 
 */
public class Ambulance implements Serializable {

	private static final long serialVersionUID = 4275126934863367887L;

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
	private boolean checkbox15 = false;
	private boolean checkbox16 = false;
	private boolean radio = false;
	private boolean radio2 = false;
	private boolean radio3 = false;
	private boolean radio4 = false;
	private double doublebox;
	private double doublebox2;
	private String textbox;
	private String postalCode;
	private int index2;
	private int index3;
	private int diag;
	private int diag2;
	private int diag3;
	private int diag4;
	private List<String> medicalConditions = new ArrayList<String>();
	private String origin;
	private String destination;

	public String getDestination() {
		return destination;
	}

	public int getDiag() {
		return diag;
	}

	public int getDiag2() {
		return diag2;
	}

	public int getDiag3() {
		return diag3;
	}

	public int getDiag4() {
		return diag4;
	}

	public double getDoublebox() {
		return doublebox;
	}

	public double getDoublebox2() {
		return doublebox2;
	}

	public int getIndex2() {
		return index2;
	}

	public int getIndex3() {
		return index3;
	}

	public List<String> getMedicalConditions() {
		return medicalConditions;
	}

	public String getOrigin() {
		return origin;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public HashMap<Integer, BeanView> getProductsIds(Properties ctx) throws ExpertException {
		HashMap<Integer, BeanView> map = new HashMap<Integer, BeanView>();
		if (checkbox) {
			int id = MEXMEProduct.getProduct(ctx, "A0428", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0428"));
			}
		}
		if (checkbox2) {
			int id = MEXMEProduct.getProduct(ctx, "A0429", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0429"));
			}
		}
		if (checkbox3) {
			int id = MEXMEProduct.getProduct(ctx, "A0426", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0426"));
			}
		}
		if (checkbox4) {
			int id = MEXMEProduct.getProduct(ctx, "A0427", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0427"));
			}
		}
		if (checkbox5) {
			int id = MEXMEProduct.getProduct(ctx, "A0433", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0433"));
			}
		}
		if (checkbox13) {
			int id = MEXMEProduct.getProduct(ctx, "A0434", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0434"));
			}
		}
		if (checkbox14) {
			int id = MEXMEProduct.getProduct(ctx, "A0432", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView( BigDecimal.ONE, 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0432"));
			}
		}
		if (checkbox15) {
			int id = MEXMEProduct.getProduct(ctx, "A0425", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0425"));
			}
		}
		if (checkbox16) {
			int id = MEXMEProduct.getProduct(ctx, "A0888", MEXMEProduct.PRODUCTCLASS_Ambulance, null);
			if (id > 0) {
				map.put(id, new BeanView(BigDecimal.valueOf(doublebox2), 0, ""));
			} else {
				throw new ExpertException(getAppMsg(ctx, "error.cargo.noExisteElProducto", "A0888"));
			}
		}
		return map;
	}

	public String getTextbox() {
		return textbox;
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

	public boolean isCheckbox15() {
		return checkbox15;
	}

	public boolean isCheckbox16() {
		return checkbox16;
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

	public boolean isRadio() {
		return radio;
	}

	public boolean isRadio2() {
		return radio2;
	}

	public boolean isRadio3() {
		return radio3;
	}

	public boolean isRadio4() {
		return radio4;
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

	public void setCheckbox15(boolean checkbox15) {
		this.checkbox15 = checkbox15;
	}

	public void setCheckbox16(boolean checkbox16) {
		this.checkbox16 = checkbox16;
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

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDiag(int diag) {
		this.diag = diag;
	}

	public void setDiag2(int diag2) {
		this.diag2 = diag2;
	}

	public void setDiag3(int diag3) {
		this.diag3 = diag3;
	}

	public void setDiag4(int diag4) {
		this.diag4 = diag4;
	}

	public void setDoublebox(double doublebox) {
		this.doublebox = doublebox;
	}

	public void setDoublebox2(double doublebox2) {
		this.doublebox2 = doublebox2;
	}

	public void setIndex2(int index2) {
		this.index2 = index2;
	}

	public void setIndex3(int index3) {
		this.index3 = index3;
	}

	public void setMedicalConditions(List<String> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setRadio(boolean radio) {
		this.radio = radio;
	}

	public void setRadio2(boolean radio2) {
		this.radio2 = radio2;
	}

	public void setRadio3(boolean radio3) {
		this.radio3 = radio3;
	}

	public void setRadio4(boolean radio4) {
		this.radio4 = radio4;
	}

	public void setTextbox(String textbox) {
		this.textbox = textbox;
	}

}
