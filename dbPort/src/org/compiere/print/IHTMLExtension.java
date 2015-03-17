package org.compiere.print;

import org.apache.ecs.ConcreteElement;
import org.apache.ecs.xhtml.a;

/**
 * 
 * @author hengsin
 *
 */
public interface IHTMLExtension {

	public String getClassPrefix();
	
	public String getStyleURL();
	
	public String getScriptURL();
	
	public void extendRowElement(ConcreteElement row, PrintData printData);
	
	public void extendIDColumn(int row, ConcreteElement columnElement, a href, PrintDataElement dataElement);
}
