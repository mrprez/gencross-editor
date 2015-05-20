package com.mrprez.gencross.editor.open;

import java.io.File;
import java.io.FileInputStream;

import org.dom4j.Document;

import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class OpenFileTask implements BackgroundTask {
	private File personnageFile;
	private String personnageXml;
	
	
	
	public OpenFileTask(File personnageFile) {
		super();
		this.personnageFile = personnageFile;
	}

	@Override
	public Task getNextTask() {
		return new DisplayOpenedPersonnageTask(personnageXml);
	}

	@Override
	public void doInBackground() throws Exception {
		String fileName = personnageFile.getName();
		String extension = "";
		if(fileName.contains(".")){
			extension = fileName.substring(fileName.lastIndexOf("."));
		}
		Document document;
		if(extension.equalsIgnoreCase(".XML")){
			document = PersonnageFactory.loadXml(new FileInputStream(personnageFile));
		}else{
			document = PersonnageFactory.loadGcr(new FileInputStream(personnageFile));
		}
		personnageXml = document.asXML();
		GencrossEditor.getInstance().setPersonnageId(null);
		GencrossEditor.getInstance().setTextModified(false);
	}

}
