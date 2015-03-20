package org.compiere.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.model.MEXMEMirthConfig;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.connectors.ws.WebServiceSenderProperties;

public class GetWSData extends SvrProcess {

	private Client mirthClient;
//	private WSDefinition definition;
	private MEXMEMirthConfig mirthConfig;

	@Override
	protected void prepare() {

		try {
			mirthClient = MirthClient.getClient(getCtx());
		} catch (ClientException e) {
			addLog(e.getLocalizedMessage());
			e.printStackTrace();
		}

		mirthConfig = new MEXMEMirthConfig(getCtx(), getRecord_ID(),
				get_TrxName());

	}

	@Override
	protected String doIt() throws Exception {

		// if (mirthClient == null || !mirthClient.isLoggedIn()){
		// addLog("MirthError");
		// return "@MirthError@";
		// }

//		try {
		

		if (cacheWsdl()) {
			List<String> operations = (List<String>) invokeConnectorService("getOperations");
			String serviceName = (String) invokeConnectorService("getService");
			String portName = (String) invokeConnectorService("getPort");
			
			// definition = (WSDefinition)
						// mirthClient.invokeConnectorService(SOAPSenderProperties.name,
						// "getWebServiceDefinition", mirthConfig.getWSDLPath().trim());

						if (operations == null) {
							addLog("noWSDLMethods");
							return "@noWSDLMethods@";
						}

					
						if (operations.size() > 0) {
							mirthConfig.setMethod(operations.get(0));// method.setSelectedIndex(0);
							mirthConfig.setEndpointURI(serviceName);// serviceEndpoint.setText(definition.getServiceEndpointURI());
							mirthConfig.setSoapActionURI((String) invokeConnectorService("getSoapAction", "operation",  operations.get(0)));
							mirthConfig.setSoapEnvelope((String) invokeConnectorService("generateEnvelope", "operation", operations.get(0)));
							

						}
						System.out.println(mirthConfig.save());


					addLog("Success");
					return "@Success@";
		}else{
			

			addLog("Error");
			return "@Error@";
			
		}
			
	}

//	private void buildSoapEnvelope() {
////
////		generatedEnvelope = (String) invokeConnectorService("generateEnvelope",
////				"operation", (String) operationComboBox.getSelectedItem());
////		soapAction = (String) invokeConnectorService("getSoapAction",
////				"operation", (String) operationComboBox.getSelectedItem());
////
////		StringBuilder soapEnvelopeString = new StringBuilder();
////		soapEnvelopeString.append(SOAPSenderProperties.SOAP_ENVELOPE_HEADER);
////		WSOperation operation = null;
////		if (mirthConfig.getMethod() != null) {
////			operation = definition.getOperation(mirthConfig.getMethod());
////		}
////		if (operation != null) {
////			Document document = null;
////			try {
////				document = DocumentBuilderFactory.newInstance()
////						.newDocumentBuilder().newDocument();
////				Element root = document.createElement("root");
////				Element soapHeaderEl = null;
////				if (operation.getHeader() != null) {
////					WSParameter header = operation.getHeader();
////					soapHeaderEl = document.createElement("soap:Header");
////					Element headerEl = document.createElement(header.getType());
////					// ignore the name space for now, use the paramter nanmespce
////					// if (operation.getHeaderNamespace() != null &&
////					// operation.getHeaderNamespace().length() > 0){
////					// headerEl.setAttribute("xmlns",
////					// operation.getHeaderNamespace());
////					// }
////
////					if (header.isComplex()) {
////						if (header.getSchemaType() != null) {
////							headerEl.setAttribute("xmlns", header
////									.getSchemaType().getTypeName()
////									.getNamespaceURI());
////						}
////						buildSoapEnvelope(document, headerEl, header);
////					} else {
////						headerEl.appendChild(document.createTextNode(header
////								.getValue()));
////						// paramEl.setNodeValue(param.getValue());
////					}
////					soapHeaderEl.appendChild(headerEl);
////					root.appendChild(soapHeaderEl);
////
////				}
////				Element bodyEl = document.createElement("soap:Body");
////
////				Element operationEl = document.createElement(operation
////						.getName());
////				operationEl.setAttribute("xmlns", operation.getNamespace());
////				//
////				Iterator<WSParameter> iterator = operation.getParameters()
////						.iterator();
////				while (iterator.hasNext()) {
////					WSParameter param = iterator.next();
////					if (param.getName().equals("parameters")
////							&& param.isComplex()) {
////						buildSoapEnvelope(document, operationEl, param);
////					} else {
////						Element paramEl = document.createElement(param
////								.getName());
////						if (param.isComplex()) {
////							if (param.getSchemaType() != null) {
////								paramEl.setAttribute("xmlns", param
////										.getSchemaType().getTypeName()
////										.getNamespaceURI());
////							}
////							buildSoapEnvelope(document, paramEl, param);
////						} else {
////							paramEl.appendChild(document.createTextNode(param
////									.getValue()));
////							// paramEl.setNodeValue(param.getValue());
////						}
////						operationEl.appendChild(paramEl);
////					}
////
////				}
////				bodyEl.appendChild(operationEl);
////				root.appendChild(bodyEl);
////				document.appendChild(root);
////				document.getDocumentElement().normalize();
////				StringWriter output = new StringWriter();
////				try {
////					TransformerFactory tf = TransformerFactory.newInstance();
////					// tf.setAttribute("indent-number", new Integer(2));
////					Transformer t = tf.newTransformer();
////
////					t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
////					// Se elimina indentacion por cambio actualizacion deXalan
////					// en jboss
////					// t.setOutputProperty(OutputKeys.INDENT, "yes");
////					// t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
////					// "2");
////
////					if (soapHeaderEl != null) {
////						t.transform(new DOMSource(soapHeaderEl),
////								new StreamResult(output));
////					}
////					t.transform(new DOMSource(bodyEl), new StreamResult(output));
////				} catch (TransformerConfigurationException e) {
////					e.printStackTrace();
////				} catch (TransformerException e) {
////					e.printStackTrace();
////				} catch (TransformerFactoryConfigurationError e) {
////					e.printStackTrace();
////				}
////
////				soapEnvelopeString.append(output.toString());
////			} catch (ParserConfigurationException e) {
////				e.printStackTrace();
////			}
////		}
////
////		soapEnvelopeString.append(SOAPSenderProperties.SOAP_ENVELOPE_FOOTER);
////		mirthConfig.setSoapEnvelope(soapEnvelopeString.toString()
////				.replaceAll("&gt;", ">").replaceAll("&lt;", "<")
////				.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
////				.replaceAll("&amp;", "&"));
////		// parent.enableSave();
////	}
//
////	private void buildSoapEnvelope(Document document, Element parent,
////			WSParameter parameter) {
////		try {
////			Iterator<WSParameter> iterator = parameter.getChildParameters()
////					.iterator();
////			while (iterator.hasNext()) {
////				WSParameter param = iterator.next();
////				Element paramEl = document.createElement(param.getName());
////
////				if (param.isComplex()) {
////					if (param.getSchemaType() != null) {
////						// paramEl.setAttribute("xmlns",
////						// param.getSchemaType().getTypeName().getNamespaceURI());
////						Attr atr = document.createAttribute("xmlns");
////						atr.setValue("");
////						paramEl.setAttributeNodeNS(atr);
////					}
////					// only add co
////					buildSoapEnvelope(document, paramEl, param);
////				} else {
////					String value = param.getValue();// ==null?"${"+param.getName()+"}":param.getValue();
////					if ("".equals(value) || value == null) {
////						value = "${" + param.getName() + "}";
////					}
////
////					paramEl.appendChild(document.createTextNode(value));
////				}
////				if (parent != paramEl) {
////					parent.appendChild(paramEl);
////				}
////			}
////		} catch (Exception e) {
////
////		}
//	}

	private Object invokeConnectorService(String method, String paramName,
			String paramValue) {
		String wsdlUrl = mirthConfig.getWSDLPath().trim();
		Object returnObject = null;
		Object params = null;

		if (paramName == null) {
			params = wsdlUrl;
		} else {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("id", wsdlUrl);
			paramMap.put(paramName, paramValue);

			params = paramMap;
		}

		try {
			returnObject = mirthClient.invokeConnectorService(
					WebServiceSenderProperties.name, method, params);
		} catch (ClientException e) {
			if (method.equals("generateEnvelope")) {
				// parent.alertError(parent,
				// "There was an error generating the envelope.");
			} else {
				// parent.alertError(parent, "Error calling " + method +
				// " with parameter: " + params.toString());
			}
		}

		return returnObject;
	}

	private Object invokeConnectorService(String method) {
		return invokeConnectorService(method, null, null);
	}
	
    private boolean cacheWsdl() {
        try {
            String wsdlUrl = mirthConfig.getWSDLPath().trim();
            Map<String, String> cacheWsdlMap = new HashMap<String, String>();

            cacheWsdlMap.put("wsdlUrl", wsdlUrl);

           
            mirthClient.invokeConnectorService(WebServiceSenderProperties.name, "cacheWsdlFromUrl", cacheWsdlMap);

            return true;
        } catch (ClientException e) {

            return false;
        }
    }
}
