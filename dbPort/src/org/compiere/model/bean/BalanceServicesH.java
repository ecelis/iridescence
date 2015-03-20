/**
 * 
 */
package org.compiere.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MDHBalanceServices;

/**
 * Saldo de servicios que tiene derecho un paciente
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class BalanceServicesH {

	public static class BalanceService implements Cloneable {

		private int 	apptTempId;
		private int		actIndId;
		private int		paqBaseVerId;
		private int		patientId;
		private String	prodName;
		private int		productId;
		private boolean	universalComp;
		private boolean	unlimited;

		public BalanceService() {}

		public BalanceService(MDHBalanceServices from) {
			paqBaseVerId = from.getEXME_PaqBase_Version_ID();
			patientId = from.getEXME_PaqBase_Version_ID();
			productId = from.getM_Product_ID();
		}

		public int getActIndId() {
			return actIndId;
		}

		public int getApptTempId() {
			return apptTempId;
		}

		public int getPaqBaseVerId() {
			return paqBaseVerId;
		}

		public int getPatientId() {
			return patientId;
		}

		public String getProdName() {
			return prodName;
		}

		public int getProductId() {
			return productId;
		}

		public boolean isUniversalComp() {
			return universalComp;
		}
		
		public boolean isUnlimited() {
			return unlimited;
		}

		public void setActIndId(int actIndId) {
			this.actIndId = actIndId;
		}

		public void setApptTempId(int apptTempId) {
			this.apptTempId = apptTempId;
		}

		public void setPaqBaseVerId(int paqBaseVerId) {
			this.paqBaseVerId = paqBaseVerId;
		}
		
		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}
		
		public void setProdName(String prodName) {
			this.prodName = prodName;
		}
		
		public void setProductId(int productId) {
			this.productId = productId;
		}
		
		public void setUniversalComp(boolean universalComp) {
			this.universalComp = universalComp;
		}
		
		public void setUnlimited(boolean unlimited) {
			this.unlimited = unlimited;
		}
		
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final BalanceService service = (BalanceService) obj;
			if(service.getPatientId() != patientId || service.getProductId() != productId || service.getPaqBaseVerId() != paqBaseVerId){
				return false;
			}
			return true;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + patientId;
			result = prime * result + productId;
			result = prime * result + paqBaseVerId;
			return result;
		}

	}

	private final List<BalanceService>	packages		= new ArrayList<>();
	private final List<BalanceService>	services		= new ArrayList<>();
	private final List<BalanceService>	universalComp	= new ArrayList<>();

	/**
	 * 
	 */
	public BalanceServicesH() {}

	public void addPackage(BalanceService serv) {
		packages.add(serv);
	}

	public void addService(BalanceService serv) {
		services.add(serv);
	}

	public void addUniversalComp(BalanceService serv) {
		universalComp.add(serv);
	}

	public List<BalanceService> getPackages() {
		return packages;
	}

	public List<BalanceService> getServices() {
		return services;
	}

	public List<BalanceService> getUniversalComp() {
		return universalComp;
	}

}
