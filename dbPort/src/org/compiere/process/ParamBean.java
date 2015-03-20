package org.compiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;

/**
 * Clase para obtener los parametros de las metricas de MU stage 2
 * @author vperez
 */
public class ParamBean {
	private final CLogger s_log = CLogger.getCLogger(ParamBean.class);
	private final Properties props = new Properties();
	private final static String nfechaIni = "fechaIni";
	private final static String nfechaFin = "fechaFin";
	private final static String ninpatient = "inpatient";
	private final static String ndPsychiat = "dPsychiat";
	private final static String ndMentalD = "dMentalD";
//
	private final static String ntVTEProp = "tVTEProp";
	private final static String nestICU   = "estICU";
	private final static String ndVTE     = "dVTE";
	private final static String ndObstetr = "dObstetr";
	private final static String ndIschemi = "dIschemi";
	private final static String ndHemmora = "dHemmora";
	private final static String ntWarfari = "tWarfari";
	private final static String ntAnticoa = "tAnticoa";
	private final static String nlPlatele = "lPlatele";
	private final static String nplateMed = "plateMed";
	private final static String ntHeparin = "tHeparin";
//
	private final static String ntAntithr = "tAntithr";
	private final static String ndAtriaFF = "dAtriaFF";
	private final static String ntThrombo = "tThrombo";
	private final static String ntStatin = "tStatin";
	private final static String ntLipidLo = "tLipidLo";
	private final static String ntAtheros = "tAtheros";//439
	private final static String nlLDL = "lLDL";
	private final static String nldlMedid = "nldlMedid";
	
	private final static String ndis1 = "discharge1";
	private final static String ndis2 = "discharge2";
	private final static String ndis3 = "discharge3";
	private final static String ndis4 = "discharge4";
	private final static String ndis5 = "discharge5";
	private final static String ndis6 = "discharge6";
	private final static String ndis7 = "discharge7";

	private final static String npRehabil = "pRehabil";
	private final static String ndMyocard = "dMyocard";
	private static String ntAspirin = "tAspirin";

	public ParamBean() {
		loadParams();
	}

	/**
	 * Guarda los parametros en el arhvo properties
	 */
	public void saveParamChanges() throws FileNotFoundException, IOException {
            final File f = new File("CQMConf.properties");
            final OutputStream out = new FileOutputStream(f);
            props.store(out, "");
            out.close();
    }

	/**
	 * Carga los parametros de configuracion
	 */
	public void loadParams() {
        InputStream is;
        // First try loading from the current directory
        try {
            final File f = new File("CQMConf.properties");
            if (!f.exists()) {
                f.createNewFile();
            }
            is = new FileInputStream(f);
        } catch (final Exception e) {
            is = null;
            s_log.log(Level.SEVERE, null, e);
        }

        try {
            if (is == null) {
                // Try loading from classpath
                is = getClass().getResourceAsStream("CQMConf.properties");
            }
            // Try loading properties from the file (if found)
            props.load(is);
        } catch (final Exception e) {
        	s_log.log(Level.SEVERE, null, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (final IOException ex) {
                	s_log.log(Level.SEVERE, null, ex);
                }
            }
        }
    }

	public void setFechaIni(final Date fechaIni) {
		props.setProperty(nfechaIni, Constantes.getSdfFecha().format(fechaIni));
	}

	public void setFechaFin(final Date fechaFin) {
		props.setProperty(nfechaFin, Constantes.getSdfFecha().format(fechaFin));
	}

	public void setInpatient(final int inpatient) {
		props.setProperty(ninpatient, String.valueOf(inpatient));
	}

	public void setdPsychiat(final int dPsychiat) {
		props.setProperty(ndPsychiat, String.valueOf(dPsychiat));
	}

	public void setdMentalD(final int dMentalD) {
		props.setProperty(ndMentalD, String.valueOf(dMentalD));
	}

	public void settVTEProp(final int tVTEProp) {
		props.setProperty(ntVTEProp, String.valueOf(tVTEProp));
	}

	public void setEstICU(final int estICU) {
		props.setProperty(nestICU, String.valueOf(estICU));
	}

	public void setdVTE(final int dVTE) {
		props.setProperty(ndVTE, String.valueOf(dVTE));
	}

	public void setdObstetr(final int dObstetr) {
		props.setProperty(ndObstetr, String.valueOf(dObstetr));
	}

	public void setdIschemi(final int dIschemi) {
		props.setProperty(ndIschemi, String.valueOf(dIschemi));
	}

	public void setdHemmora(final int dHemmora) {
		props.setProperty(ndHemmora, String.valueOf(dHemmora));
	}

	public void settWarfari(final int tWarfari) {
		props.setProperty(ntWarfari, String.valueOf(tWarfari));
	}

	public void settAnticoa(final int tAnticoa) {
		props.setProperty(ntAnticoa, String.valueOf(tAnticoa));
	}

	public void setlPlatele(final int lPlatele) {
		props.setProperty(nlPlatele, String.valueOf(lPlatele));
	}

	public void setPlateMed(final int plateMed) {
		props.setProperty(nplateMed, String.valueOf(plateMed));
	}

	public void settHeparin(final int tHeparin) {
		props.setProperty(ntHeparin, String.valueOf(tHeparin));
	}

	public void settAntithr(final int tAntithr) {
		props.setProperty(ntAntithr, String.valueOf(tAntithr));
	}

	public void setdAtriaFF(final int dAtriaFF) {
		props.setProperty(ndAtriaFF, String.valueOf(dAtriaFF));
	}

	public void settThrombo(final int tThrombo) {
		props.setProperty(ntThrombo, String.valueOf(tThrombo));
	}

	public void settStatin(final int tStatin) {
		props.setProperty(ntStatin, String.valueOf(tStatin));
	}

	public void settLipidLo(final int tLipidLo) {
		props.setProperty(ntLipidLo, String.valueOf(tLipidLo));
	}

	public void settAtheros(final int tAtheros) {
		props.setProperty(ntAtheros, String.valueOf(tAtheros));
	}

	public void setlLDL(final int lLDL) {
		props.setProperty(nlLDL, String.valueOf(lLDL));
	}

	public void setLdlMedid(final int ldlMedid) {
		props.setProperty(nldlMedid, String.valueOf(ldlMedid));
	}

	public void setpRehabil(final int pRehabil) {
		props.setProperty(npRehabil, String.valueOf(pRehabil));
	}

	public void setdMyocard(final int dMyocard) {
		props.setProperty(ndMyocard, String.valueOf(dMyocard));
	}

	public void settAspirin(final int tAspirin) {
		props.setProperty(ntAspirin, String.valueOf(tAspirin));
	}
	
	public void setDisToHome(final String discharge) {
		props.setProperty(ndis1, discharge);
	}
	public void setDisToHomCare(final String discharge) {
		props.setProperty(ndis2, discharge);
	}
	public void setDisToHosCare(final String discharge) {
		props.setProperty(ndis3, discharge);
	}
	public void setDisOtherHospit(final String discharge) {
		props.setProperty(ndis4, discharge);
	}
	public void setDisLeftMedical(final String discharge) {
		props.setProperty(ndis5, discharge);
	}
	public void setDisExpired(final String discharge) {
		props.setProperty(ndis6, discharge);
	}
	public void setDisHealtFacili(final String discharge) {
		props.setProperty(ndis7, discharge);
	}
	
	public Date getFechaIni() throws ParseException {
		return StringUtils.isBlank(props.getProperty(nfechaIni)) ? null : Constantes.getSdfFecha().parse(props.getProperty(nfechaIni));
	}
	public Date getFechaFin() throws ParseException {
		return StringUtils.isBlank(props.getProperty(nfechaFin)) ? null : Constantes.getSdfFecha().parse(props.getProperty(nfechaFin));
	}
	public int getInpatient() {
		return StringUtils.isBlank(props.getProperty(ninpatient)) ? -1 : Integer.parseInt(props.getProperty(ninpatient));
	}
	public int getdPsychiat() {
		return StringUtils.isBlank(props.getProperty(ndPsychiat)) ? -1 : Integer.parseInt(props.getProperty(ndPsychiat));
	}
	public int getdMentalD() {
		return StringUtils.isBlank(props.getProperty(ndMentalD)) ? -1 : Integer.parseInt(props.getProperty(ndMentalD));
	}
	public int gettVTEProp() {
		return StringUtils.isBlank(props.getProperty(ntVTEProp)) ? -1 : Integer.parseInt(props.getProperty(ntVTEProp));
	}
	public int getEstICU() {
		return StringUtils.isBlank(props.getProperty(nestICU)) ? -1 : Integer.parseInt(props.getProperty(nestICU));
	}
	public int getdVTE() {
		return StringUtils.isBlank(props.getProperty(ndVTE)) ? -1 : Integer.parseInt(props.getProperty(ndVTE));
	}
	public int getdObstetr() {
		return StringUtils.isBlank(props.getProperty(ndObstetr)) ? -1 : Integer.parseInt(props.getProperty(ndObstetr));
	}
	public int getdIschemi() {
		return StringUtils.isBlank(props.getProperty(ndIschemi)) ? -1 : Integer.parseInt(props.getProperty(ndIschemi));
	}
	public int getdHemmora() {
		return StringUtils.isBlank(props.getProperty(ndHemmora)) ? -1 : Integer.parseInt(props.getProperty(ndHemmora));
	}
	public int gettWarfari() {
		return StringUtils.isBlank(props.getProperty(ntWarfari)) ? -1 : Integer.parseInt(props.getProperty(ntWarfari));
	}
	public int gettAnticoa() {
		return StringUtils.isBlank(props.getProperty(ntAnticoa)) ? -1 : Integer.parseInt(props.getProperty(ntAnticoa));
	}
	public int getlPlatele() {
		return StringUtils.isBlank(props.getProperty(nlPlatele)) ? -1 : Integer.parseInt(props.getProperty(nlPlatele));
	}
	public int getPlateMed() {
		return  StringUtils.isBlank(props.getProperty(nplateMed)) ? -1 : Integer.parseInt(props.getProperty(nplateMed));
	}
	public int gettHeparin() {
		return StringUtils.isBlank(props.getProperty(ntHeparin)) ? -1 : Integer.parseInt(props.getProperty(ntHeparin));
	}
	public int gettAntithr() {
		return StringUtils.isBlank(props.getProperty(ntAntithr)) ? -1 : Integer.parseInt(props.getProperty(ntAntithr));
	}
	public int getdAtriaFF() {
		return StringUtils.isBlank(props.getProperty(ndAtriaFF)) ? -1 : Integer.parseInt(props.getProperty(ndAtriaFF));
	}
	public int gettThrombo() {
		return StringUtils.isBlank(props.getProperty(ntThrombo)) ? -1 : Integer.parseInt(props.getProperty(ntThrombo));
	}
	public int gettStatin() {
		return StringUtils.isBlank(props.getProperty(ntStatin)) ? -1 : Integer.parseInt(props.getProperty(ntStatin));
	}
	public int gettLipidLo() {
		return StringUtils.isBlank(props.getProperty(ntLipidLo)) ? -1 : Integer.parseInt(props.getProperty(ntLipidLo));
	}
	public int gettAtheros() {
		return StringUtils.isBlank(props.getProperty(ntAtheros)) ? -1 : Integer.parseInt(props.getProperty(ntAtheros));
	}
	public int getlLDL() {
		return StringUtils.isBlank(props.getProperty(nlLDL)) ? -1 : Integer.parseInt(props.getProperty(nlLDL));
	}
	public int getLdlMedid() {
		return StringUtils.isBlank(props.getProperty(nldlMedid)) ? -1 : Integer.parseInt(props.getProperty(nldlMedid));
	}
	public int getpRehabil() {
		return StringUtils.isBlank(props.getProperty(npRehabil)) ? -1 : Integer.parseInt(props.getProperty(npRehabil));
	}
	public int getdMyocard() {
		return StringUtils.isBlank(props.getProperty(ndMyocard)) ? -1 : Integer.parseInt(props.getProperty(ndMyocard));
	}
	public int gettAspirin() {
		return StringUtils.isBlank(props.getProperty(ntAspirin)) ? -1 : Integer.parseInt(props.getProperty(ntAspirin));
	}
	public int getDisToHome() {
		return StringUtils.isBlank(props.getProperty(ndis1)) ? -1 : Integer.parseInt(props.getProperty(ndis1));
	}
	public int getDisToHomCare() {
		return StringUtils.isBlank(props.getProperty(ndis2)) ? -1 : Integer.parseInt(props.getProperty(ndis2));
	}
	public int getDisToHosCare() {
		return StringUtils.isBlank(props.getProperty(ndis3)) ? -1 : Integer.parseInt(props.getProperty(ndis3));
	}
	public int getDisOtherHospit() {
		return StringUtils.isBlank(props.getProperty(ndis4)) ? -1 : Integer.parseInt(props.getProperty(ndis4));
	}
	public int getDisLeftMedical() {
		return StringUtils.isBlank(props.getProperty(ndis5)) ? -1 : Integer.parseInt(props.getProperty(ndis5));
	}
	public int getDisExpired() {
		return StringUtils.isBlank(props.getProperty(ndis6)) ? -1 :Integer.parseInt( props.getProperty(ndis6));
	}
	public int getDisHealtFacili() {
		return StringUtils.isBlank(props.getProperty(ndis7)) ? -1 : Integer.parseInt(props.getProperty(ndis7));
	}
}