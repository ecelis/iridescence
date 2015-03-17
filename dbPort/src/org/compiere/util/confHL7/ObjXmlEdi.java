package org.compiere.util.confHL7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class ObjXmlEdi {
	private Document doc = null;
	
	public ObjXmlEdi(Document doc) {
		this.doc = doc;
	}
	
	public ObjXmlEdi(String xmlData) {
		File tempFile = null;
		SAXBuilder builder = new SAXBuilder ();
		try {
			tempFile = new File(File.createTempFile("prueba", "xml"), "");
			FileWriter fw = new FileWriter(tempFile);
			fw.write(xmlData);
			fw.flush();
			fw.close();
			this.doc = builder.build (new FileInputStream (tempFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Element getSegment(String seg, int pos) {
		Element root = this.doc.getRootElement();
		int n = 0;
		
		for (Object t : root.getChildren()) {
			Element et = (Element)t;
			if (et.getName().equals(seg)) {
				n++;
				if (n == pos) {
					return et;
				}
			}
		}
		return null;
	}
	
	private Element getSegment(String seg, int pos, String segP, int posP) {
		Element root = this.doc.getRootElement();
		int n = 0;
		int np = 0;
		boolean indice = false;
		
		for (Object t : root.getChildren()) {
			Element et = (Element)t;
			if (et.getName().equals(segP)) {
				np++;
				if (np == posP) {
					indice = true;
				}
			}
			if (indice) {
				if (et.getName().equals(seg)) {
					n++;
					if (n == pos) {
						return et;
					}
				}
			}
		}
		
		return null;
	}
	
	public Element getElement(String seg, int pos, int campo, int comp) {
		Element e = getSegment(seg, pos);
		if (e != null) {
			try {
				Element temp = e.getChild(seg+"."+ (campo > 9 ? campo : "0"+campo));
				temp = temp.getChild(seg+"."+ (campo > 9 ? campo : "0"+campo) + "." + comp);
				e = temp;
			} catch (Exception ex) {
				e = null;
			}
		}
		
		return e;
	}
	
	public Element getElement(String seg, int pos, int campo, int comp, String segP, int posP) {
		Element e = getSegment(seg, pos, segP, posP);
//		String s = "";
		if (e != null) {
			try {
				Element temp = e.getChild(seg+"."+ (campo > 9 ? campo : "0"+campo));
				temp = temp.getChild(seg+"."+ (campo > 9 ? campo : "0"+campo) + "." + comp);
				e = temp;
			} catch (Exception ex) {
				e = null;
			}
		}
		return e;
	}
	
	public String getString(String seg, int campo) {
		return getString(seg, 1, campo, 1);
	}
	
	public String getString(String seg, int pos, int campo) {
		return getString(seg, pos, campo, 1);
	}
	
	public String getString(String seg, int pos, int campo, int comp) {
		Element e = getElement(seg, pos, campo, comp);
		if (e != null) {
			return e.getText();
		} else {
			return null;
		}
		
	}
	
	public double getDouble(String seg, int campo) {
		return getDouble(seg, 1, campo, 1);
	}
	
	public double getDouble(String seg, int pos, int campo) {
		return getDouble(seg, pos, campo, 1);
	}
	
	public double getDouble(String seg, int pos, int campo, int comp) {
		String temp = getString(seg, pos, campo, comp);
		double res = 0;
		if (temp != null)
			res = Double.valueOf(temp);
		return res;
	}
	
	public int getInt(String seg, int campo) {
		return getInt(seg, 1, campo, 1);
	}
	
	public int getInt(String seg, int pos, int campo) {
		return getInt(seg, pos, campo, 1);
	}
	
	public int getInt(String seg, int pos, int campo, int comp) {
		String temp = getString(seg, pos, campo, comp);
//		String s = "";
		int res = 0;
		if (temp != null) {
			double dres = Double.valueOf(temp);
			res = (int) dres;
		}
		return res;
	}
	
	
	public String getString(String seg, int pos, int campo, int comp, String segP, int posP) {
		Element e = getElement(seg, pos, campo, comp, segP, posP);
		if (e != null) {
			return e.getText();
		} else {
			return null;
		}
	}
	
	public double getDouble(String seg, int pos, int campo, int comp, String segP, int posP) {
		double res = Double.valueOf(getString(seg, pos, campo, comp, segP, posP));
		return res;
	}
	
	public int getInt(String seg, int pos, int campo, int comp, String segP, int posP) {
		int res = Integer.valueOf(getString(seg, pos, campo, comp, segP, posP));
		return res;
	}
	
	public int getTotalSeg(String seg) {
		int total = 0;
		Element root = this.doc.getRootElement();
		
		for (Object t : root.getChildren()) {
			Element et = (Element)t;
			if (et.getName().equals(seg)) {
				total++;
			}
		}
		return total;
	}
	
	public ObjXmlEdi getGroup(String seg, int pos, String[] endSeg) {
		//ObjXmlEdi group = new ObjXmlEdi("<group></group>");
		Element list = new Element("group");
		
		Element root = this.doc.getRootElement();
		int n = 0;
		boolean iniciaGrupo = false;
		
		for (Object t : root.getChildren()) {
			Element et = (Element)t;
			if (!iniciaGrupo) {
				if (et.getName().equals(seg)) {
					n++;
					if (n == pos) {
						list.addContent((Element)et.clone());
						iniciaGrupo = true;
					}
				}
			} else {
				for (int p = 0; p < endSeg.length; p++) {
					if (endSeg[p].equals(et.getName())) {
						iniciaGrupo = false;
						break;
					}
				}
				if (iniciaGrupo) {
					list.addContent((Element)et.clone());
				}
			}
			
		}
		Document doc = new Document(list);
		ObjXmlEdi group = new ObjXmlEdi(doc);
		
		return group;
	}
}
