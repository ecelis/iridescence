/*
 * Created on 04-abr-2005
 *
 */
package org.compiere.model;

/**
 * @author hassan reyes
 * @deprecated
 */
public class MPaqBase_Version { /*extends X_EXME_PaqBase_Version {
	/**	Static Logger				*
	private static CLogger		s_log = CLogger.getCLogger (MPaqBase_Version.class);
    
    /**
     * Variables miembro. 
     *
    private MPaqBaseDet[] m_paqBaseDet = null; 

    /**
     * @param ctx
     * @param EXME_PaqBase_Version_ID
     * @param trxName
     *
    public MPaqBase_Version(Properties ctx, int EXME_PaqBase_Version_ID,
            String trxName) {
        super(ctx, EXME_PaqBase_Version_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     *
    public MPaqBase_Version(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Obtenemos todos las versiones del paquete para la empresa logeada
     * @param ctx El contexto de la aplicacion
     * @param trxName El nombre de la transaccion
     * @return
     *
    public static MPaqBase_Version[] get(Properties ctx, String cadena, 
            String fechaDesde, String fechaHasta, String trxName){
        
        if(ctx == null)
            return null;
        
        ArrayList list = new ArrayList();
		String sql = " SELECT EXME_PaqBase_Version.EXME_PaqBase_Version_ID " + 
                     " FROM EXME_PaqBase_Version " + 
                     " WHERE EXME_PaqBase_Version.IsActive = 'Y'  ";
        /**+
                     " AND EXME_PaqBase_Version.ValidFrom >= " +
                     " (SELECT MAX(EXME_PaqBase_Version.validfrom) " +
                     " FROM EXME_PaqBase_Version " + 
                     " WHERE EXME_PaqBase_Version.IsActive = 'Y' " + 
                     " AND EXME_PaqBase_Version.Validfrom < TO_DATE('" + fechaDesde + "', 'dd/mm/yyyy') " +
                     " AND EXME_PaqBase_Version.ValidTo >= TO_DATE('" + fechaHasta + "', 'dd/mm/yyyy') )  ";
        *
        if(cadena != null)
            sql += cadena;
            
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_PaqBase_Version");
        
        sql+= " ORDER BY Name";
		            
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
			    MPaqBase_Version paquete = new MPaqBase_Version(ctx, rs.getInt(1), trxName);
				list.add(paquete);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			//expert
			rs=null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "MPaqBase_Version.get", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}

		//
		MPaqBase_Version[] paquete = new MPaqBase_Version[list.size()];
		list.toArray(paquete);
		return paquete;
        
    }
    
    /**
     * Obtenemos las lineas del paquete.
     * @param reQuery
     * @return
     *
    public MPaqBaseDet[] getLineas(boolean reQuery){
        
        if(m_paqBaseDet == null || m_paqBaseDet.length == 0 || reQuery){
            m_paqBaseDet = getLineas(null, false);
        }
        
        return m_paqBaseDet;
    }
    
    public List getLineas(Properties ctx, String trxName) {

		List lista = new ArrayList();
		try {
			//cambiar de arreglo a lista de labelvaluebean
		    MPaqBaseDet[] detalle = getLineas(null, false);
			for (int i = 0; i < detalle.length; i++) {
			    MPaqBaseDet line = detalle[i];
				lista.add(new MPaqBaseDet(ctx, line.getEXME_PaqBaseDet_ID(), trxName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
    
    
    /**
     * Obtenemos las lineas de la Version del Paquete.
     * @return
     *
    public MPaqBaseDet[] getLineas(String whereCluse, boolean conversion){
        
        ArrayList list = new ArrayList();
        
        String sql = "SELECT * FROM EXME_PaqBaseDet WHERE EXME_PaqBase_Version_ID = ? " +
        		"AND IsActive = 'Y' ";
        
        if(whereCluse != null)
            sql += whereCluse;
        
		PreparedStatement pstmt = null;
		sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql, "EXME_PaqBaseDet");
		
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getEXME_PaqBase_Version_ID());
			ResultSet rs = pstmt.executeQuery();
			BigDecimal cantidad =Env.ZERO;
			while (rs.next())
			{
			    MPaqBaseDet paqBaseVersion = new MPaqBaseDet(getCtx(), rs, get_TrxName());
			    if (conversion)
			        cantidad = MEXMEUOMConversion.convertProductFrom(getCtx(), paqBaseVersion.getM_Product_ID(), 
			            paqBaseVersion.getC_UOM_ID(), paqBaseVersion.getCantidad(), null); 
			    paqBaseVersion.setQtyPendiente(cantidad);
			    list.add(paqBaseVersion);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			rs=null;
		}
		catch (Exception e)
		{
		    e.printStackTrace();
			log.log(Level.SEVERE, "getLineas", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		
		MPaqBaseDet[] paqBaseDets = new MPaqBaseDet[list.size()];
		list.toArray(paqBaseDets);
		
		return paqBaseDets;
        
    }
    
    /**
     * Remplazamos las lineas.
     * (Usar con discrecion).
     *
     *
    public void setLineas(MPaqBaseDet[] paqBaseDets){
        
        m_paqBaseDet = paqBaseDets;
        
    }

  
    
    /**
	 * ID 
	 * @return
	 *
	public int getId() {

		if(getEXME_PaqBase_Version_ID() > 0)
			return getEXME_PaqBase_Version_ID();
		else
			return 0;
	}
	
	/**
	 * Producto
	 * @return
	 *
	public MProduct getProduct() {

		if(getM_Product_ID() > 0)
			return new MProduct(getCtx(), getM_Product_ID(), null);
		else
			return null;
	}
	
	/**
	 * Lista de precios
	 * @return
	 *
	public MPriceList getPriceList() {
		return new MPriceList(getCtx(), getM_PriceList_ID(), null);
	}

	/**
	 * Moneda
	 * @return
	 *
	public MEXMECurrency getCurrency() {
		return new MEXMECurrency(getCtx(), getC_Currency_ID(), null);
	}
    
	private MPaqBase paqBase = null;
      /**
     * Obtenemos la Version especifica del paquete que fue reservada a la cuenta paciente.
     * @return
     *
    public MPaqBase getPaqBase() {
    	
    	if(paqBase!=null)
    		return paqBase;
    	
        if(getEXME_PaqBase_ID() > 0)
        	return null;
        
        paqBase = new MPaqBase(getCtx(), getEXME_PaqBase_ID(), get_TrxName());	
        
        return paqBase;
        
    }

    
    
    
    /**
     * Obtenemos la Version especifica del paquete que fue reservada a la cuenta paciente.
     * @return
     *
    public MEXMETax getTax() {

        if(getC_Tax_ID() > 0)
            return new MEXMETax(getCtx(), getC_Tax_ID(), get_TrxName());
        else
            return null;

    }
    
    /**
     * monto total de la version del paquete a precio de lista 
     *
    public BigDecimal totalListaAmt = Env.ZERO;

	public BigDecimal getTotalListaAmt() {
		return totalListaAmt;
	}

	public void setTotalListaAmt(BigDecimal totalListaAmt) {
		this.totalListaAmt = totalListaAmt;
	}

	/**
	 * Secuencia 
	 *
	public int secuencia = 0;

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}
	
	/**
	 * Metodo get para struts
	 * @return
	 *
	public boolean getActive() {
		return isActive();
	}
	
	/**
	 * CtaPaciente a la que se le asigno el paquete
	 *
	private int ctaPacID = 0;

	public int getCtaPacID() {
		return ctaPacID;
	}

	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}
	
	 */
}

