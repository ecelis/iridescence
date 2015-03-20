package org.compiere.util.confHL7;

import java.util.Properties;

import com.mirth.connect.model.Transformer;

public class DefaultSourceTransfromer {

	private DefaultSourceTransfromer() {

	}

	/*
	 * 
	 * &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
	 * &lt;result&gt; &lt;mensaje&gt;value&lt;/mensaje&gt; &lt;/result&gt;
	 */
//	private static StringBuilder inboundTemplate = new StringBuilder(
//			"&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt\n")
//			.append("&lt;result&gt;\n\t").append(
//					"&lt;mensaje&gt;value&lt;/mensaje&gt;\n").append(
//					"&lt;/result&gt;");

	public static Transformer getDefaultSourceTransfromer() {

		Transformer tran = new Transformer();


		//el protocolo lo asigna quien llama la tranformada
	//	tran.setInboundProtocol(Protocol.HL7V2);// conf?
	//	tran.setOutboundProtocol(Protocol.HL7V2);// conf?

		Properties inboundProperties = new Properties();
		inboundProperties.setProperty("useStrictParser", "false");
		inboundProperties.setProperty("stripNamespaces", "true");
		inboundProperties.setProperty("convertLFtoCR", "true");
		inboundProperties.setProperty("handleRepetitions", "false");
		inboundProperties.setProperty("useStrictValidation", "false");

		tran.setInboundProperties(inboundProperties);
	//	tran.setInboundTemplate(inboundTemplate.toString());

	//	tran.setSteps(getDefaultSteps());
		return tran;

	}
//never used
//	private static List<Step> getDefaultSteps() {
//
//		Step ponerMensajeEnCanal = new Step();
//
//		ponerMensajeEnCanal.setSequenceNumber(0);// solo sera uno?
//		ponerMensajeEnCanal.setName("ponerMensajeEnCanal");// nombre?
//
//		/*
//		 * var mapping; try { mapping = msg[&apos;mensaje&apos;].toString();
//		 * }catch (e) { logger.error(e); mapping = &apos;&apos; ; }
//		 * channelMap.put(&apos;mensaje&apos;, validate( mapping , &apos;&apos;,
//		 * new Array()));
//		 */
//		ponerMensajeEnCanal
//				.setScript("var mapping;try { mapping = msg['mensaje'].toString(); }catch (e) { logger.error(e);  mapping = '';} channelMap.put('mensaje', validate( mapping , '', new Array()));");
//
//		ponerMensajeEnCanal.setType("Mapper");
//
//		Map<Object, Object> data = new HashMap<Object, Object>();
//
//		String mapping = "msg['mensaje'].toString()";// el mismo
//		// nombre del
//		// alias
//		String variable = "mensaje";//
//		String isGlobal = "channel";// channel; se esta agregando la variable al
//		// canal
//
//		data.put("Mapping", mapping);
//		data.put("Variable", variable);
//		data.put("isGlobal", isGlobal);
//		data.put("RegularExpressions", new ArrayList<String>());// no se
//		// reemplazan
//		// expresiones
//		// regulares
//		data.put("DefaultValue", "");// el valor por defacto vendra desde el
//		// mensaje
//
//		ponerMensajeEnCanal.setData(data);
//
//		ArrayList<Step> steps = new ArrayList<Step>();
//		steps.add(ponerMensajeEnCanal);
//
//		return steps;
//	}

}
