package com.mrprez.gencross.editor.download;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class UploadPersonnageTask implements BackgroundTask {

	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doInBackground() throws Exception {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(GencrossEditor.getInstance().getPersonnage());
		
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(getClass().getResource("gencross-plugin.xsd"));
		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(document));
		

	}

}
