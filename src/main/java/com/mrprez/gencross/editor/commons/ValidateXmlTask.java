package com.mrprez.gencross.editor.commons;

import java.io.ByteArrayInputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.task.ShowMessageTask;

public class ValidateXmlTask implements BackgroundTask {
	private Boolean valideXml;
	
	private Task valideXmlTask;
	private String errorMessage;
	private Document document;
	

	@Override
	public Task getNextTask() {
		if(valideXml){
			return valideXmlTask;
		}
		return new ShowMessageTask(GencrossEditor.getInstance(), errorMessage);
	}

	@Override
	public void doInBackground() throws Exception {
		try{
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = parser.parse(new ByteArrayInputStream(GencrossEditor.getInstance().getPersonnage().getBytes()));
			
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(getClass().getResource("/gencross-plugin.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document));
		}catch(SAXParseException saxParseException){
			if(saxParseException.getLineNumber()>=0 && saxParseException.getColumnNumber()>=0){
				errorMessage = "XML non-valide: (line "+saxParseException.getLineNumber()+", col "+saxParseException.getColumnNumber()+") "+saxParseException.getMessage();
			}else{
				errorMessage = "XML non-valide: "+saxParseException.getMessage();
			}	
			saxParseException.printStackTrace();
			valideXml = false;
			return;
		}catch(SAXException saxException){
			errorMessage = "XML non-valide: "+saxException.getMessage();
			saxException.printStackTrace();
			valideXml = false;
			return;
		}
		valideXml = true;
	}

	public Boolean isValideXml() {
		return valideXml;
	}

	public Task getValideXmlTask() {
		return valideXmlTask;
	}

	public void setValideXmlTask(Task valideXmlTask) {
		this.valideXmlTask = valideXmlTask;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Document getDocument() {
		return document;
	}

}
