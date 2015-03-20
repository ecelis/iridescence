package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * Callouts para Clasificacion
 *
 * <b>Fecha:</b> 02/Febrero/2006<p>
 * <b>Modificado: </b> $Author: otorres $<p>
 * <b>En :</b> $Date: 2006/09/07 22:22:43 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.5 $
 */
public class CalloutClasifica extends CalloutEngine {
    
    /**
     * Metodo para calcular el total de ingresos de la clasificacion
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String totalIng (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
        setCalloutActive(false);
        
        if (isCalloutActive() || value == null)
            return "";
        
        setCalloutActive(true);
        
        BigDecimal IngJefeFam, IngHijos, IngOtros;
        
        //get values
        IngJefeFam = (BigDecimal)mTab.getValue("IngJefeFam");
        IngHijos = (BigDecimal)mTab.getValue("IngHijos");
        IngOtros = (BigDecimal)mTab.getValue("IngOtros");
        
        //Total de ingresos
        BigDecimal TotalIng = IngJefeFam.add(IngHijos).add(IngOtros).setScale(2);
        mTab.setValue("TotalIng", TotalIng);
        
        //Porcentaje de Alimentacion
        BigDecimal EgrAlimentacion = ((BigDecimal)mTab.getValue("EgrAlimentacion")).setScale(2);
        double PorcAlimentacion = 0;
        if(TotalIng.compareTo(Env.ZERO)>0) {
            PorcAlimentacion = (EgrAlimentacion.doubleValue()/TotalIng.doubleValue()) * 100;
        }
        mTab.setValue("PorcAlimentacion", new BigDecimal(PorcAlimentacion));
        
        String msg = "";
        msg = totalIngEgr(ctx, WindowNo, mTab, mField, value);
        
        setCalloutActive(false);
        
        return msg;
    }    
    
    /**
     * Metodo para calcular el total de egresos de la clasificacion
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String totalEgr (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
    	setCalloutActive(false);
    	
        if (isCalloutActive() || value == null)
            return "";
        setCalloutActive(true);
        
        BigDecimal EgrAlimentacion, EgrVivienda, EgrServicios, EgrOtros,
        EgrLuzCombustible, EgrAgua, EgrTransporte;
               
        //get values
        EgrAlimentacion = (BigDecimal)mTab.getValue("EgrAlimentacion");
        EgrVivienda = (BigDecimal)mTab.getValue("EgrVivienda");
        EgrLuzCombustible = (BigDecimal)mTab.getValue("EgrLuzCombustible");
        EgrAgua = (BigDecimal)mTab.getValue("EgrAgua");
        EgrTransporte = (BigDecimal)mTab.getValue("EgrTransporte");
      
        EgrOtros = (BigDecimal)mTab.getValue("EgrOtros");
        
        //Total de Servicios
        
        if(EgrLuzCombustible!=null &&  EgrAgua!=null && EgrTransporte!=null)
        {
              EgrServicios= EgrLuzCombustible.add(EgrAgua).add(EgrTransporte).setScale(2);
               mTab.setValue("EgrServicios",EgrServicios);
        }
        else
        {
        	EgrServicios = (BigDecimal)mTab.getValue("EgrServicios");
        }
        
        //Total de egresos
        BigDecimal TotalEgr = EgrAlimentacion.add(EgrVivienda).add(EgrServicios).add(EgrOtros).setScale(2);
        mTab.setValue("TotalEgr", TotalEgr);
        
        //Porcentaje de Alimentacion
        BigDecimal TotalIng = ((BigDecimal)mTab.getValue("TotalIng")).setScale(2);
        double PorcAlimentacion = 0;
        if(TotalIng.compareTo(Env.ZERO)>0) {
            PorcAlimentacion = (EgrAlimentacion.doubleValue()/TotalIng.doubleValue()) * 100;
        }
        mTab.setValue("PorcAlimentacion", new BigDecimal(PorcAlimentacion));
        
        String msg = "";
        msg = totalIngEgr(ctx, WindowNo, mTab, mField, value);
        
        setCalloutActive(false);
        return msg;
    }    
    
    /**
     * Actualizar los puntos correspondientes a la zona seleccionada
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String zona (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_Zona_ID = null;
		if(value instanceof BigDecimal)
			EXME_Zona_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Zona_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_Zona_ID!=null && EXME_Zona_ID!=0) {
            X_EXME_Zona zona = new X_EXME_Zona(ctx, EXME_Zona_ID, null);
            puntos = new BigDecimal(zona.getPts());
        }
        mTab.setValue("PtsZona", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al tipo de vivienda seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String tipoVivienda (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_TipoVivienda_ID = null;
		if(value instanceof BigDecimal)
			EXME_TipoVivienda_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_TipoVivienda_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_TipoVivienda_ID!=null && EXME_TipoVivienda_ID!=0) {
            X_EXME_TipoVivienda tp = new X_EXME_TipoVivienda(ctx, EXME_TipoVivienda_ID, null);
            puntos = new BigDecimal(tp.getPts());
        }
        mTab.setValue("PtsTipoVivienda", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes a la tenencia seleccionada
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String tenencia (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_Tenencia_ID = null;
		if(value instanceof BigDecimal)
			EXME_Tenencia_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Tenencia_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_Tenencia_ID!=null && EXME_Tenencia_ID!=0) {
            X_EXME_Tenencia t = new X_EXME_Tenencia(ctx, EXME_Tenencia_ID, null);
            puntos = new BigDecimal(t.getPts());
        }
        mTab.setValue("PtsTenencia", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al servicio publico seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String servPublico (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_ServPublico_ID = null;
		if(value instanceof BigDecimal)
			EXME_ServPublico_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_ServPublico_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_ServPublico_ID!=null && EXME_ServPublico_ID!=0) {
            X_EXME_ServPublico sp = new X_EXME_ServPublico(ctx, EXME_ServPublico_ID, null);
            puntos = new BigDecimal(sp.getPts());
        }
        mTab.setValue("PtsServPublico", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al material de construccion seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String matConst (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_MatConst_ID = null;
		if(value instanceof BigDecimal)
			EXME_MatConst_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_MatConst_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_MatConst_ID!=null && EXME_MatConst_ID!=0) {
            X_EXME_Material_Const mc = new X_EXME_Material_Const(ctx, EXME_MatConst_ID, null);
            puntos = mc.getPuntaje();
        }
        mTab.setValue("PtsMatConst", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al numero de personas seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String numPers (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_NumPers_ID = null;
		if(value instanceof BigDecimal)
			EXME_NumPers_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_NumPers_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_NumPers_ID!=null && EXME_NumPers_ID!=0) {
            X_EXME_NumPers np = new X_EXME_NumPers(ctx, EXME_NumPers_ID, null);
            puntos = new BigDecimal(np.getPts());
        }
        mTab.setValue("PtsNumPers", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al numero de habitaciones seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String numHabts (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_NumHabts_ID = null;
		if(value instanceof BigDecimal)
			EXME_NumHabts_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_NumHabts_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_NumHabts_ID!=null && EXME_NumHabts_ID!=0) {
            X_EXME_NumHabts nh = new X_EXME_NumHabts(ctx, EXME_NumHabts_ID, null);
            puntos = new BigDecimal(nh.getPts());
        }
        mTab.setValue("PtsNumHabts", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al tipo de vivienda seleccionado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String numEnferm (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_NumEnferm_ID = null;
		if(value instanceof BigDecimal)
			EXME_NumEnferm_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_NumEnferm_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_NumEnferm_ID!=null && EXME_NumEnferm_ID!=0) {
            X_EXME_NumEnferm ne = new X_EXME_NumEnferm(ctx, EXME_NumEnferm_ID, null);
            puntos = new BigDecimal(ne.getPts());
        }
        mTab.setValue("PtsNumEnferm", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes a la procedencia seleccionada
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String procedencia (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_Procedencia_ID = null;
		if(value instanceof BigDecimal)
			EXME_Procedencia_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Procedencia_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_Procedencia_ID!=null && EXME_Procedencia_ID!=0) {
            X_EXME_Procedencia p = new X_EXME_Procedencia(ctx, EXME_Procedencia_ID, null);
            puntos = new BigDecimal(p.getPts());
        }
        mTab.setValue("PtsProcedencia", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes a la ocupacion seleccionada
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String ocupacionClas (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        Integer EXME_Ocupacion_Clas_ID = null;
		if(value instanceof BigDecimal)
			EXME_Ocupacion_Clas_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			EXME_Ocupacion_Clas_ID = (Integer)value;
        BigDecimal puntos = Env.ZERO;
        if(EXME_Ocupacion_Clas_ID!=null && EXME_Ocupacion_Clas_ID!=0) {
            X_EXME_Ocupacion_Clas o = new X_EXME_Ocupacion_Clas(ctx, EXME_Ocupacion_Clas_ID, null);
            puntos = o.getPuntaje();
        }
        mTab.setValue("PtsOcupacion", puntos);
        totalPts(ctx, mTab);
        return "";
    }
    
    /**
     * Actualizar los puntos correspondientes al ingreso acumulado
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String totalIngEgr (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
        String msg = "";
        BigDecimal TotalIng = (BigDecimal)mTab.getValue("TotalIng");
        BigDecimal PorcAlimentacion = (BigDecimal)mTab.getValue("PorcAlimentacion");
        
        BigDecimal NumMiembros = Env.ZERO;
        if(mTab.getValue("NumMiembros") instanceof BigDecimal)
        	NumMiembros = (BigDecimal)mTab.getValue("NumMiembros");
		else if(mTab.getValue("NumMiembros") instanceof Integer)
			NumMiembros = new BigDecimal (((Integer)mTab.getValue("NumMiembros")).intValue());
        //new BigDecimal (((Integer)mTab.getValue("NumMiembros")).intValue());//lhernandez.
        
        BigDecimal PtsIngresos = Env.ZERO;
        MEXMESalariosMinMes salminmes = MEXMESalariosMinMes.getByIndice(ctx, TotalIng, NumMiembros, null);
        if(salminmes!=null) 
        {
            PtsIngresos = new BigDecimal(salminmes.getPuntaje());
            mTab.setValue("EXME_Salarios_Min_Mes_ID", BigDecimal.valueOf((long)salminmes.getEXME_Salarios_Min_Mes_ID()));
        }
        else
            msg = "Existe algun problema con el catalogo de rangos de salarios minimos. "
                + "Por favor revise su catalogo y corrobore la informacion";

        mTab.setValue("PtsIngresos", PtsIngresos);
        
        BigDecimal PtsEgre = Env.ZERO;
        MEXMERangoPorcAlim rango = MEXMERangoPorcAlim.getByIndice(ctx, PorcAlimentacion, null);
        if(rango!=null) 
            PtsEgre = rango.getPuntaje();
        else {
            if(msg.equals(""))
                msg = "No esta registrado en los rangos de porcentajes de alimentacion. " 
                    + "Por favor revise en el Mantenimiento de Rango de Alimentacion.";
        }
        
        mTab.setValue("PtsEgre", PtsEgre);
        
        totalPts(ctx, mTab);
        return msg;
    }
     
    /**
     * Establecer el total de puntos obtenidos a traves de los diferentes
     * criterios y asignar la clasificacion correspondiente en base a estos
     * puntos
     * @param ctx
     * @param mTab
     */
    private void totalPts (Properties ctx, GridTab mTab) {
        BigDecimal totalPts = Env.ZERO;
        
        BigDecimal PtsZona, PtsTipoVivienda, PtsTenencia, PtsServPublico, 
            PtsMatConst, PtsNumHabts, PtsNumPers, PtsNumEnferm, PtsProcedencia,
            PtsOcupacion, PtsIngresos, PtsEgre;
        
        //get values
        PtsZona = (BigDecimal)mTab.getValue("PtsZona");
        PtsTipoVivienda = (BigDecimal)mTab.getValue("PtsTipoVivienda");
        PtsTenencia = (BigDecimal)mTab.getValue("PtsTenencia");
        PtsServPublico = (BigDecimal)mTab.getValue("PtsServPublico");
        PtsMatConst = (BigDecimal)mTab.getValue("PtsMatConst");
        PtsNumHabts = (BigDecimal)mTab.getValue("PtsNumHabts");
        PtsNumPers = (BigDecimal)mTab.getValue("PtsNumPers");
        PtsNumEnferm = (BigDecimal)mTab.getValue("PtsNumEnferm");
        PtsProcedencia = (BigDecimal)mTab.getValue("PtsProcedencia");
        PtsOcupacion = (BigDecimal)mTab.getValue("PtsOcupacion");
        PtsIngresos = (BigDecimal)mTab.getValue("PtsIngresos");
        PtsEgre = (BigDecimal)mTab.getValue("PtsEgre");
        
        totalPts = PtsZona.add(PtsTipoVivienda).add(PtsTenencia).
            add(PtsServPublico).add(PtsMatConst).add(PtsNumHabts).
            add(PtsNumPers).add(PtsNumEnferm).add(PtsProcedencia).
            add(PtsOcupacion).add(PtsIngresos).add(PtsEgre);
        
        mTab.setValue("TotalPts", totalPts);
        
        //asignamos la clasificacion
        int clasifica = 0;
        MEXMERangoPuntClas puntos = MEXMERangoPuntClas.getByIndice(ctx, totalPts, null);
        if(puntos!=null) 
            clasifica = puntos.getEXME_ClasCliente_ID();
        mTab.setValue("EXME_ClasCliente_ID", clasifica);
    }
    
    
    /**
     * Metodo para i
     * @param ctx
     * @param WindowNo
     * @param mTab
     * @param mField
     * @param value
     * @return
     */
    public String nivelAcceso (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    {
    	
        String usuario_ID =ctx.getProperty("#AD_User_ID");
        
        X_AD_User usr= new X_AD_User(ctx,Integer.parseInt(usuario_ID),null);
                          
        
        if(usr.getSupervisor_ID()==0)
      	    	ctx.setProperty("#NivelAcceso","0");
        else
        	     ctx.setProperty("#NivelAcceso","1");
    
    	
    	
       return "";
       
    }    
    
}