/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Encuesta
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Encuesta 
{

    /** TableName=EXME_Encuesta */
    public static final String Table_Name = "EXME_Encuesta";

    /** AD_Table_ID=1200036 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Anios_Fuma */
    public static final String COLUMNNAME_Anios_Fuma = "Anios_Fuma";

	/** Set How many years have you been smoking?.
	  * How many years have you been smoking?
	  */
	public void setAnios_Fuma (int Anios_Fuma);

	/** Get How many years have you been smoking?.
	  * How many years have you been smoking?
	  */
	public int getAnios_Fuma();

    /** Column name Anios_Fumo */
    public static final String COLUMNNAME_Anios_Fumo = "Anios_Fumo";

	/** Set How many years did you smoke?.
	  * How many years did you smoke?
	  */
	public void setAnios_Fumo (int Anios_Fumo);

	/** Get How many years did you smoke?.
	  * How many years did you smoke?
	  */
	public int getAnios_Fumo();

    /** Column name Caj_Fuma */
    public static final String COLUMNNAME_Caj_Fuma = "Caj_Fuma";

	/** Set Small boxes.
	  * Small boxes
	  */
	public void setCaj_Fuma (boolean Caj_Fuma);

	/** Get Small boxes.
	  * Small boxes
	  */
	public boolean isCaj_Fuma();

    /** Column name Caj_Fumaba */
    public static final String COLUMNNAME_Caj_Fumaba = "Caj_Fumaba";

	/** Set Small boxes.
	  * Small boxes
	  */
	public void setCaj_Fumaba (boolean Caj_Fumaba);

	/** Get Small boxes.
	  * Small boxes
	  */
	public boolean isCaj_Fumaba();

    /** Column name Combust_Cocina */
    public static final String COLUMNNAME_Combust_Cocina = "Combust_Cocina";

	/** Set What combustible do you use to cook?.
	  * What combustible do you use to cook?
	  */
	public void setCombust_Cocina (String Combust_Cocina);

	/** Get What combustible do you use to cook?.
	  * What combustible do you use to cook?
	  */
	public String getCombust_Cocina();

    /** Column name EXME_Encuesta_ID */
    public static final String COLUMNNAME_EXME_Encuesta_ID = "EXME_Encuesta_ID";

	/** Set Poll of Exhibition Factors.
	  * Poll of Exhibition Factors
	  */
	public void setEXME_Encuesta_ID (int EXME_Encuesta_ID);

	/** Get Poll of Exhibition Factors.
	  * Poll of Exhibition Factors
	  */
	public int getEXME_Encuesta_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

    /** Column name Expone_Humo */
    public static final String COLUMNNAME_Expone_Humo = "Expone_Humo";

	/** Set During your work do you are exposed to smoke?.
	  * During your work do you are exposed to smoke?
	  */
	public void setExpone_Humo (boolean Expone_Humo);

	/** Get During your work do you are exposed to smoke?.
	  * During your work do you are exposed to smoke?
	  */
	public boolean isExpone_Humo();

    /** Column name Expone_Polvo */
    public static final String COLUMNNAME_Expone_Polvo = "Expone_Polvo";

	/** Set During your work do you are exposed to the dust?.
	  * During your work do you are exposed to the dust?
	  */
	public void setExpone_Polvo (boolean Expone_Polvo);

	/** Get During your work do you are exposed to the dust?.
	  * During your work do you are exposed to the dust?
	  */
	public boolean isExpone_Polvo();

    /** Column name Expuso_Humo */
    public static final String COLUMNNAME_Expuso_Humo = "Expuso_Humo";

	/** Set In some previous work, did you were exposed to smoke?.
	  * In some previous work, did you were exposed to smoke?
	  */
	public void setExpuso_Humo (boolean Expuso_Humo);

	/** Get In some previous work, did you were exposed to smoke?.
	  * In some previous work, did you were exposed to smoke?
	  */
	public boolean isExpuso_Humo();

    /** Column name Expuso_Polvo */
    public static final String COLUMNNAME_Expuso_Polvo = "Expuso_Polvo";

	/** Set In some previous work, did you were exposed to the dust?.
	  * In some previous work, did you were exposed to the dust?
	  */
	public void setExpuso_Polvo (boolean Expuso_Polvo);

	/** Get In some previous work, did you were exposed to the dust?.
	  * In some previous work, did you were exposed to the dust?
	  */
	public boolean isExpuso_Polvo();

    /** Column name Fuma */
    public static final String COLUMNNAME_Fuma = "Fuma";

	/** Set Do you smoke?.
	  * Do you smoke?
	  */
	public void setFuma (boolean Fuma);

	/** Get Do you smoke?.
	  * Do you smoke?
	  */
	public boolean isFuma();

    /** Column name Hay_Avenida */
    public static final String COLUMNNAME_Hay_Avenida = "Hay_Avenida";

	/** Set Is there any main avenue to less than 2 blocks?.
	  * Is there any main avenue to less than 2 blocks?
	  */
	public void setHay_Avenida (boolean Hay_Avenida);

	/** Get Is there any main avenue to less than 2 blocks?.
	  * Is there any main avenue to less than 2 blocks?
	  */
	public boolean isHay_Avenida();

    /** Column name Hay_Basura */
    public static final String COLUMNNAME_Hay_Basura = "Hay_Basura";

	/** Set Is there any garbage collector near your house?.
	  * Is there any garbage collector near your house?
	  */
	public void setHay_Basura (boolean Hay_Basura);

	/** Get Is there any garbage collector near your house?.
	  * Is there any garbage collector near your house?
	  */
	public boolean isHay_Basura();

    /** Column name Hay_Establo */
    public static final String COLUMNNAME_Hay_Establo = "Hay_Establo";

	/** Set Is there any stable in your district?.
	  * Is there any stable in your district?
	  */
	public void setHay_Establo (boolean Hay_Establo);

	/** Get Is there any stable in your district?.
	  * Is there any stable in your district?
	  */
	public boolean isHay_Establo();

    /** Column name Hay_Fabrica */
    public static final String COLUMNNAME_Hay_Fabrica = "Hay_Fabrica";

	/** Set Is there any factory to less than 10 blocks?.
	  * Is there any factory to less than 10 blocks?
	  */
	public void setHay_Fabrica (boolean Hay_Fabrica);

	/** Get Is there any factory to less than 10 blocks?.
	  * Is there any factory to less than 10 blocks?
	  */
	public boolean isHay_Fabrica();

    /** Column name Hay_Gasolinera */
    public static final String COLUMNNAME_Hay_Gasolinera = "Hay_Gasolinera";

	/** Set Is there any gas station to less than 10 blocks?.
	  * Is there any gas station to less than 10 blocks?
	  */
	public void setHay_Gasolinera (boolean Hay_Gasolinera);

	/** Get Is there any gas station to less than 10 blocks?.
	  * Is there any gas station to less than 10 blocks?
	  */
	public boolean isHay_Gasolinera();

    /** Column name Hay_Granja */
    public static final String COLUMNNAME_Hay_Granja = "Hay_Granja";

	/** Set Is there any aviaria farm in your house?.
	  * Is there any aviaria farm in your house?
	  */
	public void setHay_Granja (boolean Hay_Granja);

	/** Get Is there any aviaria farm in your house?.
	  * Is there any aviaria farm in your house?
	  */
	public boolean isHay_Granja();

    /** Column name Hay_Ninos */
    public static final String COLUMNNAME_Hay_Ninos = "Hay_Ninos";

	/** Set Are there smaller children of 6 years in your house?.
	  * Are there smaller children of 6 years in your house?
	  */
	public void setHay_Ninos (boolean Hay_Ninos);

	/** Get Are there smaller children of 6 years in your house?.
	  * Are there smaller children of 6 years in your house?
	  */
	public boolean isHay_Ninos();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name Maneja_Sustancia */
    public static final String COLUMNNAME_Maneja_Sustancia = "Maneja_Sustancia";

	/** Set During your work, do you handle chemical substances?.
	  * During your work, do you handle chemical substances?
	  */
	public void setManeja_Sustancia (boolean Maneja_Sustancia);

	/** Get During your work, do you handle chemical substances?.
	  * During your work, do you handle chemical substances?
	  */
	public boolean isManeja_Sustancia();

    /** Column name Manejo_Sustancia */
    public static final String COLUMNNAME_Manejo_Sustancia = "Manejo_Sustancia";

	/** Set In some previous work, did you handle chemical substances?.
	  * In some previous work, did you handle chemical substances?
	  */
	public void setManejo_Sustancia (boolean Manejo_Sustancia);

	/** Get In some previous work, did you handle chemical substances?.
	  * In some previous work, did you handle chemical substances?
	  */
	public boolean isManejo_Sustancia();

    /** Column name Mas100Cig */
    public static final String COLUMNNAME_Mas100Cig = "Mas100Cig";

	/** Set In all its life it has smoked more than 100 cigarettes?.
	  * In all its life it has smoked more than 100 cigarettes?
	  */
	public void setMas100Cig (boolean Mas100Cig);

	/** Get In all its life it has smoked more than 100 cigarettes?.
	  * In all its life it has smoked more than 100 cigarettes?
	  */
	public boolean isMas100Cig();

    /** Column name Meses_Fuma */
    public static final String COLUMNNAME_Meses_Fuma = "Meses_Fuma";

	/** Set Months.
	  * Months
	  */
	public void setMeses_Fuma (int Meses_Fuma);

	/** Get Months.
	  * Months
	  */
	public int getMeses_Fuma();

    /** Column name Meses_Fumo */
    public static final String COLUMNNAME_Meses_Fumo = "Meses_Fumo";

	/** Set Months.
	  * Months
	  */
	public void setMeses_Fumo (int Meses_Fumo);

	/** Get Months.
	  * Months
	  */
	public int getMeses_Fumo();

    /** Column name No_Pers_Pac */
    public static final String COLUMNNAME_No_Pers_Pac = "No_Pers_Pac";

	/** Set How many people sleep with the patient?.
	  * How many people sleep in the same room with the patient?
	  */
	public void setNo_Pers_Pac (int No_Pers_Pac);

	/** Get How many people sleep with the patient?.
	  * How many people sleep in the same room with the patient?
	  */
	public int getNo_Pers_Pac();

    /** Column name Otra_Fuma */
    public static final String COLUMNNAME_Otra_Fuma = "Otra_Fuma";

	/** Set Another person smokes in your house?.
	  * Another person smokes in your house?
	  */
	public void setOtra_Fuma (boolean Otra_Fuma);

	/** Get Another person smokes in your house?.
	  * Another person smokes in your house?
	  */
	public boolean isOtra_Fuma();

    /** Column name Otra_Fumaba */
    public static final String COLUMNNAME_Otra_Fumaba = "Otra_Fumaba";

	/** Set Another person smoked in your house?.
	  * Another person smoked in your house?
	  */
	public void setOtra_Fumaba (boolean Otra_Fumaba);

	/** Get Another person smoked in your house?.
	  * Another person smoked in your house?
	  */
	public boolean isOtra_Fumaba();

    /** Column name Otro_Combust */
    public static final String COLUMNNAME_Otro_Combust = "Otro_Combust";

	/** Set Other.
	  * Another combustible
	  */
	public void setOtro_Combust (String Otro_Combust);

	/** Get Other.
	  * Another combustible
	  */
	public String getOtro_Combust();

    /** Column name Qty_Fum */
    public static final String COLUMNNAME_Qty_Fum = "Qty_Fum";

	/** Set How many cigarettes do you smoke?.
	  * How many cigarettes do you smoke?
	  */
	public void setQty_Fum (int Qty_Fum);

	/** Get How many cigarettes do you smoke?.
	  * How many cigarettes do you smoke?
	  */
	public int getQty_Fum();

    /** Column name Qty_Fumaba */
    public static final String COLUMNNAME_Qty_Fumaba = "Qty_Fumaba";

	/** Set How many cigarettes did you smoke at day?.
	  * How many cigarettes did you smoke at day?
	  */
	public void setQty_Fumaba (int Qty_Fumaba);

	/** Get How many cigarettes did you smoke at day?.
	  * How many cigarettes did you smoke at day?
	  */
	public int getQty_Fumaba();

    /** Column name Qty_Hr_Abierta */
    public static final String COLUMNNAME_Qty_Hr_Abierta = "Qty_Hr_Abierta";

	/** Set How many hours are the windows open?.
	  * How many hours at day are the windows open?
	  */
	public void setQty_Hr_Abierta (int Qty_Hr_Abierta);

	/** Get How many hours are the windows open?.
	  * How many hours at day are the windows open?
	  */
	public int getQty_Hr_Abierta();

    /** Column name Qty_Ventanas */
    public static final String COLUMNNAME_Qty_Ventanas = "Qty_Ventanas";

	/** Set How many windows does the bedroom have?.
	  * How many windows does the bedroom have?
	  */
	public void setQty_Ventanas (int Qty_Ventanas);

	/** Get How many windows does the bedroom have?.
	  * How many windows does the bedroom have?
	  */
	public int getQty_Ventanas();

    /** Column name Tenido_Alfombra */
    public static final String COLUMNNAME_Tenido_Alfombra = "Tenido_Alfombra";

	/** Set Have you ever had carpet in your bedroom?.
	  * Have you ever had carpet in your bedroom?
	  */
	public void setTenido_Alfombra (boolean Tenido_Alfombra);

	/** Get Have you ever had carpet in your bedroom?.
	  * Have you ever had carpet in your bedroom?
	  */
	public boolean isTenido_Alfombra();

    /** Column name Tenido_Animal */
    public static final String COLUMNNAME_Tenido_Animal = "Tenido_Animal";

	/** Set Have you ever had another kind of animal?.
	  * Have you ever had another kind of animal in your house?
	  */
	public void setTenido_Animal (boolean Tenido_Animal);

	/** Get Have you ever had another kind of animal?.
	  * Have you ever had another kind of animal in your house?
	  */
	public boolean isTenido_Animal();

    /** Column name Tenido_Ave */
    public static final String COLUMNNAME_Tenido_Ave = "Tenido_Ave";

	/** Set Have you ever had birds in your house?.
	  * Have you ever had birds in your house?
	  */
	public void setTenido_Ave (boolean Tenido_Ave);

	/** Get Have you ever had birds in your house?.
	  * Have you ever had birds in your house?
	  */
	public boolean isTenido_Ave();

    /** Column name Tenido_Cucar */
    public static final String COLUMNNAME_Tenido_Cucar = "Tenido_Cucar";

	/** Set A year ago, did you see cockroaches in your house?.
	  * A year ago, did you see cockroaches in your house?
	  */
	public void setTenido_Cucar (boolean Tenido_Cucar);

	/** Get A year ago, did you see cockroaches in your house?.
	  * A year ago, did you see cockroaches in your house?
	  */
	public boolean isTenido_Cucar();

    /** Column name Tenido_Gato */
    public static final String COLUMNNAME_Tenido_Gato = "Tenido_Gato";

	/** Set Have you ever had cats within your house?.
	  * Have you ever had cats within your house?
	  */
	public void setTenido_Gato (boolean Tenido_Gato);

	/** Get Have you ever had cats within your house?.
	  * Have you ever had cats within your house?
	  */
	public boolean isTenido_Gato();

    /** Column name Tenido_Humedad */
    public static final String COLUMNNAME_Tenido_Humedad = "Tenido_Humedad";

	/** Set In the walls of your house, have their ever had humidity?.
	  * In the walls of your house, have their ever had humidity?
	  */
	public void setTenido_Humedad (boolean Tenido_Humedad);

	/** Get In the walls of your house, have their ever had humidity?.
	  * In the walls of your house, have their ever had humidity?
	  */
	public boolean isTenido_Humedad();

    /** Column name Tenido_Perro */
    public static final String COLUMNNAME_Tenido_Perro = "Tenido_Perro";

	/** Set Have you ever had dogs within your house?.
	  * Have you ever had dogs within your house?
	  */
	public void setTenido_Perro (boolean Tenido_Perro);

	/** Get Have you ever had dogs within your house?.
	  * Have you ever had dogs within your house?
	  */
	public boolean isTenido_Perro();

    /** Column name Tiene_Alfombra */
    public static final String COLUMNNAME_Tiene_Alfombra = "Tiene_Alfombra";

	/** Set Do you have carpet in your bedroom?.
	  * Do you have carpet in your bedroom?
	  */
	public void setTiene_Alfombra (boolean Tiene_Alfombra);

	/** Get Do you have carpet in your bedroom?.
	  * Do you have carpet in your bedroom?
	  */
	public boolean isTiene_Alfombra();

    /** Column name Tiene_Animal */
    public static final String COLUMNNAME_Tiene_Animal = "Tiene_Animal";

	/** Set Is there another kind of animal in your house?.
	  * Is there another kind of animal in your house?
	  */
	public void setTiene_Animal (boolean Tiene_Animal);

	/** Get Is there another kind of animal in your house?.
	  * Is there another kind of animal in your house?
	  */
	public boolean isTiene_Animal();

    /** Column name Tiene_Ave */
    public static final String COLUMNNAME_Tiene_Ave = "Tiene_Ave";

	/** Set Do you have birds in your house?.
	  * Do you have birds in your house?
	  */
	public void setTiene_Ave (boolean Tiene_Ave);

	/** Get Do you have birds in your house?.
	  * Do you have birds in your house?
	  */
	public boolean isTiene_Ave();

    /** Column name Tiene_Computadora */
    public static final String COLUMNNAME_Tiene_Computadora = "Tiene_Computadora";

	/** Set Do you have a computer?.
	  * Do you have a computer?
	  */
	public void setTiene_Computadora (boolean Tiene_Computadora);

	/** Get Do you have a computer?.
	  * Do you have a computer?
	  */
	public boolean isTiene_Computadora();

    /** Column name Tiene_Cucar */
    public static final String COLUMNNAME_Tiene_Cucar = "Tiene_Cucar";

	/** Set A month ago, did you see cockroaches in your house?.
	  * A month ago, did you see cockroaches in your house?
	  */
	public void setTiene_Cucar (boolean Tiene_Cucar);

	/** Get A month ago, did you see cockroaches in your house?.
	  * A month ago, did you see cockroaches in your house?
	  */
	public boolean isTiene_Cucar();

    /** Column name Tiene_Gato */
    public static final String COLUMNNAME_Tiene_Gato = "Tiene_Gato";

	/** Set Do you have cats within your house?.
	  * Do you have cats within your house?
	  */
	public void setTiene_Gato (boolean Tiene_Gato);

	/** Get Do you have cats within your house?.
	  * Do you have cats within your house?
	  */
	public boolean isTiene_Gato();

    /** Column name Tiene_Horno */
    public static final String COLUMNNAME_Tiene_Horno = "Tiene_Horno";

	/** Set Do you have electrical furnace or bread toaster?.
	  * Do you have electrical furnace or bread toaster?
	  */
	public void setTiene_Horno (boolean Tiene_Horno);

	/** Get Do you have electrical furnace or bread toaster?.
	  * Do you have electrical furnace or bread toaster?
	  */
	public boolean isTiene_Horno();

    /** Column name Tiene_Humedad */
    public static final String COLUMNNAME_Tiene_Humedad = "Tiene_Humedad";

	/** Set In the walls of your house, do they have humidity?.
	  * In the walls of your house, do they have humidity?
	  */
	public void setTiene_Humedad (boolean Tiene_Humedad);

	/** Get In the walls of your house, do they have humidity?.
	  * In the walls of your house, do they have humidity?
	  */
	public boolean isTiene_Humedad();

    /** Column name Tiene_Microondas */
    public static final String COLUMNNAME_Tiene_Microondas = "Tiene_Microondas";

	/** Set Do you have microwave?.
	  * Do you have microwave?
	  */
	public void setTiene_Microondas (boolean Tiene_Microondas);

	/** Get Do you have microwave?.
	  * Do you have microwave?
	  */
	public boolean isTiene_Microondas();

    /** Column name Tiene_Perro */
    public static final String COLUMNNAME_Tiene_Perro = "Tiene_Perro";

	/** Set Do you have dogs within your house?.
	  * Do you have dogs within your house?
	  */
	public void setTiene_Perro (boolean Tiene_Perro);

	/** Get Do you have dogs within your house?.
	  * Do you have dogs within your house?
	  */
	public boolean isTiene_Perro();

    /** Column name Tiene_Piso */
    public static final String COLUMNNAME_Tiene_Piso = "Tiene_Piso";

	/** Set Do you have land floor?.
	  * Do you have land floor?
	  */
	public void setTiene_Piso (boolean Tiene_Piso);

	/** Get Do you have land floor?.
	  * Do you have land floor?
	  */
	public boolean isTiene_Piso();

    /** Column name Tiene_Refri */
    public static final String COLUMNNAME_Tiene_Refri = "Tiene_Refri";

	/** Set Do you have refrigerator?.
	  * Do you have refrigerator?
	  */
	public void setTiene_Refri (boolean Tiene_Refri);

	/** Get Do you have refrigerator?.
	  * Do you have refrigerator?
	  */
	public boolean isTiene_Refri();

    /** Column name Tiene_Telefono */
    public static final String COLUMNNAME_Tiene_Telefono = "Tiene_Telefono";

	/** Set Do you have phone?.
	  * Do you have phone?
	  */
	public void setTiene_Telefono (boolean Tiene_Telefono);

	/** Get Do you have phone?.
	  * Do you have phone?
	  */
	public boolean isTiene_Telefono();

    /** Column name Tiene_TV */
    public static final String COLUMNNAME_Tiene_TV = "Tiene_TV";

	/** Set Do you have television?.
	  * Do you have television?
	  */
	public void setTiene_TV (boolean Tiene_TV);

	/** Get Do you have television?.
	  * Do you have television?
	  */
	public boolean isTiene_TV();

    /** Column name Tiene_Ventanas */
    public static final String COLUMNNAME_Tiene_Ventanas = "Tiene_Ventanas";

	/** Set Do you have windows in your bedroom?.
	  * Do you have windows in your bedroom?
	  */
	public void setTiene_Ventanas (boolean Tiene_Ventanas);

	/** Get Do you have windows in your bedroom?.
	  * Do you have windows in your bedroom?
	  */
	public boolean isTiene_Ventanas();

    /** Column name Toma_Bebidas */
    public static final String COLUMNNAME_Toma_Bebidas = "Toma_Bebidas";

	/** Set Do you drink alcohol regularly?.
	  * Do you drink alcohol regularly?
	  */
	public void setToma_Bebidas (boolean Toma_Bebidas);

	/** Get Do you drink alcohol regularly?.
	  * Do you drink alcohol regularly?
	  */
	public boolean isToma_Bebidas();

    /** Column name Toma_Dieta */
    public static final String COLUMNNAME_Toma_Dieta = "Toma_Dieta";

	/** Set Do you consume Canderel, Nutrasweet or diet drink?.
	  * Do you consume Canderel, Nutrasweet or diet drink?
	  */
	public void setToma_Dieta (boolean Toma_Dieta);

	/** Get Do you consume Canderel, Nutrasweet or diet drink?.
	  * Do you consume Canderel, Nutrasweet or diet drink?
	  */
	public boolean isToma_Dieta();

    /** Column name Toma_Med_Dormir */
    public static final String COLUMNNAME_Toma_Med_Dormir = "Toma_Med_Dormir";

	/** Set Do you took medicines to sleep?.
	  * Do you took medicines to sleep?
	  */
	public void setToma_Med_Dormir (boolean Toma_Med_Dormir);

	/** Get Do you took medicines to sleep?.
	  * Do you took medicines to sleep?
	  */
	public boolean isToma_Med_Dormir();

    /** Column name Toma_Vitaminas */
    public static final String COLUMNNAME_Toma_Vitaminas = "Toma_Vitaminas";

	/** Set Do you take vitamins regularly?.
	  * Do you take vitamins regularly?
	  */
	public void setToma_Vitaminas (boolean Toma_Vitaminas);

	/** Get Do you take vitamins regularly?.
	  * Do you take vitamins regularly?
	  */
	public boolean isToma_Vitaminas();

    /** Column name Tomo_Bebidas */
    public static final String COLUMNNAME_Tomo_Bebidas = "Tomo_Bebidas";

	/** Set Do you ever have drunk alcohol regularly?.
	  * Do you ever have drunk alcohol regularly?
	  */
	public void setTomo_Bebidas (boolean Tomo_Bebidas);

	/** Get Do you ever have drunk alcohol regularly?.
	  * Do you ever have drunk alcohol regularly?
	  */
	public boolean isTomo_Bebidas();

    /** Column name Tomo_Dieta */
    public static final String COLUMNNAME_Tomo_Dieta = "Tomo_Dieta";

	/** Set Did you consume Canderel, Nutrasweet or diet drink?.
	  * Did you consume Canderel, Nutrasweet or diet drink?
	  */
	public void setTomo_Dieta (boolean Tomo_Dieta);

	/** Get Did you consume Canderel, Nutrasweet or diet drink?.
	  * Did you consume Canderel, Nutrasweet or diet drink?
	  */
	public boolean isTomo_Dieta();

    /** Column name Tomo_Med_Dormir */
    public static final String COLUMNNAME_Tomo_Med_Dormir = "Tomo_Med_Dormir";

	/** Set Did you took medicines to sleep?.
	  * Did you took medicines to sleep?
	  */
	public void setTomo_Med_Dormir (boolean Tomo_Med_Dormir);

	/** Get Did you took medicines to sleep?.
	  * Did you took medicines to sleep?
	  */
	public boolean isTomo_Med_Dormir();

    /** Column name Tomo_Vitaminas */
    public static final String COLUMNNAME_Tomo_Vitaminas = "Tomo_Vitaminas";

	/** Set Do you used to taking vitamins regularly?.
	  * Do you used to taking vitamins regularly?
	  */
	public void setTomo_Vitaminas (boolean Tomo_Vitaminas);

	/** Get Do you used to taking vitamins regularly?.
	  * Do you used to taking vitamins regularly?
	  */
	public boolean isTomo_Vitaminas();

    /** Column name Usado_Insectic */
    public static final String COLUMNNAME_Usado_Insectic = "Usado_Insectic";

	/** Set Have you ever used insecticide in your house?.
	  * Have you ever used insecticide in your house?
	  */
	public void setUsado_Insectic (boolean Usado_Insectic);

	/** Get Have you ever used insecticide in your house?.
	  * Have you ever used insecticide in your house?
	  */
	public boolean isUsado_Insectic();

    /** Column name Usa_Insectic */
    public static final String COLUMNNAME_Usa_Insectic = "Usa_Insectic";

	/** Set Do you use insecticide in your house?.
	  * Do you use insecticide in your house?
	  */
	public void setUsa_Insectic (boolean Usa_Insectic);

	/** Get Do you use insecticide in your house?.
	  * Do you use insecticide in your house?
	  */
	public boolean isUsa_Insectic();

    /** Column name Uso_Carbon */
    public static final String COLUMNNAME_Uso_Carbon = "Uso_Carbon";

	/** Set Have you ever used coal to cook?.
	  * Have you ever used coal to cook?
	  */
	public void setUso_Carbon (boolean Uso_Carbon);

	/** Get Have you ever used coal to cook?.
	  * Have you ever used coal to cook?
	  */
	public boolean isUso_Carbon();

    /** Column name Uso_Lena */
    public static final String COLUMNNAME_Uso_Lena = "Uso_Lena";

	/** Set Have you ever used firewood to cook?.
	  * Have you ever used firewood to cook?
	  */
	public void setUso_Lena (boolean Uso_Lena);

	/** Get Have you ever used firewood to cook?.
	  * Have you ever used firewood to cook?
	  */
	public boolean isUso_Lena();
}
