package com.mrprez.gencross.editor.save;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.task.ShowMessageTask;

public class SavePersonnageTask implements BackgroundTask {
	private File file;
	private Task nextTask;
	
	
	public SavePersonnageTask(File file) {
		super();
		this.file = file;
	}

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInBackground() throws Exception {
		String personnageXml = GencrossEditor.getInstance().getPersonnage().replace("\t", "").replace("\r", "").replace("\n", "");
		
		try{
			validateXml(personnageXml);
		}catch(SAXParseException saxParseException){
			if(saxParseException.getLineNumber()>=0 && saxParseException.getColumnNumber()>=0){
				nextTask = new ShowMessageTask(GencrossEditor.getInstance(), "XML non-valide: (line "+saxParseException.getLineNumber()+", col "+saxParseException.getColumnNumber()+") "+saxParseException.getMessage());
			}else{
				nextTask = new ShowMessageTask(GencrossEditor.getInstance(), "XML non-valide: "+saxParseException.getMessage());
			}	
			saxParseException.printStackTrace();
			return;
		}catch(SAXException saxException){
			nextTask = new ShowMessageTask(GencrossEditor.getInstance(), "XML non-valide: "+saxException.getMessage());
			saxException.printStackTrace();
			return;
		}
		
		validateXml(personnageXml);
		String extention = "";
		if(file.getName().contains(".")){
			extention = file.getName().substring(file.getName().lastIndexOf("."));
		}
		if(extention.equalsIgnoreCase(".xml")){
			PersonnageSaver.savePersonnage(DocumentHelper.parseText(personnageXml), new FileOutputStream(file));
		}else{
			PersonnageSaver.savePersonnageGcr(DocumentHelper.parseText(GencrossEditor.getInstance().getPersonnage()), new FileOutputStream(file));
		}
		GencrossEditor.getInstance().setTextModified(false);
	}
	
	
	private void validateXml(String xml) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(new ByteArrayInputStream(xml.getBytes()));
		
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(getClass().getResource("/gencross-plugin.xsd"));
		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(document));
	}
	

}
