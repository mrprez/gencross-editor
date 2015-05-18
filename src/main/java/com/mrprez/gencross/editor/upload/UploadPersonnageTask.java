package com.mrprez.gencross.editor.upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import com.mrprez.gencross.editor.framework.Treatment;
import com.mrprez.gencross.editor.framework.TreatmentAwareTask;
import com.mrprez.gencross.editor.framework.task.ShowMessageTask;
import com.mrprez.gencross.editor.login.DisplayLoginTask;
import com.mrprez.gencross.editor.service.PersonnageServiceAccess;

public class UploadPersonnageTask implements BackgroundTask, TreatmentAwareTask {
	private ShowMessageTask nextTask;
	private Treatment treatment;
	

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInBackground() throws Exception {
		if(GencrossEditor.getInstance().getToken()==null){
			treatment.lauchChildTreatment(new DisplayLoginTask());
		}
		
		
		String xml = GencrossEditor.getInstance().getPersonnage();
		
		try{
			validateXml(xml);
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
		
		
		new PersonnageServiceAccess().savePersonnage(GencrossEditor.getInstance().getPersonnageId(), xml);

		nextTask = new ShowMessageTask(GencrossEditor.getInstance(), "Personnage sauvegardé sur le serveur.");
		nextTask.setMessageType(JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void validateXml(String xml) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(new ByteArrayInputStream(xml.getBytes()));
		
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(getClass().getResource("/gencross-plugin.xsd"));
		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(document));
	}

	@Override
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	

}
