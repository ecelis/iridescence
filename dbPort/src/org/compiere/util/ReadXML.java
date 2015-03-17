package org.compiere.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;

import org.compiere.model.MEXMERecursoEducativo;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author vladimirrdz
 */
public class ReadXML {
    
	private static CLogger s_log = CLogger.getCLogger(ReadXML.class);
	
    private SAXBuilder builder = new SAXBuilder();
            
    private Namespace namespace;
    private String parentElement = "entry";

    public ReadXML(String parentElement) {
     
        this.parentElement = parentElement;
   
    }
    
    public void getData(StringBuffer xml, TreeMap<String,TreeMap<String,String>> childElements) {
        
        Document document;
        String element;
        String attribute;
        TreeMap<String,String> attributes = new TreeMap<String, String>();
        
        try {
            
        	builder.setValidation(false);
            document = builder.build (new StringReader(xml.toString()));
            
            Element elementRoot = document.getRootElement();
        
            namespace =  Namespace.getNamespace(elementRoot.getNamespaceURI());

            Element elementEntry = elementRoot.getChild(parentElement, namespace);
            
            if(elementEntry != null) {
            	
            	for( Iterator it = childElements.keySet().iterator(); it.hasNext();) { 
                    
                    attributes = new TreeMap<String, String>();
                    
                    element = (String)it.next();
                 
                    if(childElements.get(element) == null) {

                        attributes.put("text", elementEntry.getChild(element, namespace).getText());
                        
                        childElements.put(element, attributes);
                        
                        s_log.log(Level.FINE, element + ": " + elementEntry.getChild(element, namespace).getText());
                    
                    } else {
                        
                        
                        
                        for( Iterator it2 = childElements.get(element).keySet().iterator(); it2.hasNext();) { 
                            
                            attribute = (String)it2.next();
                            
                            if(elementEntry.getChild(element, namespace).getAttribute(attribute) != null) {
                                
                            	attributes.put(attribute, elementEntry.getChild(element, namespace).getAttribute(attribute).getValue());
                            	
                            } else {

                            	attributes.put(attribute, "");
                            	
                            }
                            
                            childElements.put(element, attributes);
                           
                            s_log.log(Level.FINE, element + "." + attribute + ": "  + elementEntry.getChild(element, namespace).getAttribute(attribute).getValue());
                            
                        }
                                            
                    }
                        
                }
            	
            }
                        
        }catch(JDOMException e) {
        	
        	s_log.log(Level.SEVERE, xml.toString(), e);
        
        }catch(IOException e) {
        	
        	s_log.log(Level.SEVERE, xml.toString(), e);
            
        }
        
    }

}
