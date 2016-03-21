package com.mrprez.gencross.editor.save;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.DocumentHelper;

import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

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
		String extention = "";
		if(file.getName().contains(".")){
			extention = file.getName().substring(file.getName().lastIndexOf("."));
		}
		if(extention.equalsIgnoreCase(".xml")){
			PersonnageSaver.savePersonnage(DocumentHelper.parseText(GencrossEditor.getInstance().getPersonnage()), new FileOutputStream(file));
		}else{
			PersonnageSaver.savePersonnageGcr(DocumentHelper.parseText(GencrossEditor.getInstance().getPersonnage()), new FileOutputStream(file));
		}
		GencrossEditor.getInstance().setTextModified(false);
		GencrossEditor.getInstance().setOpenedFile(file);
	}
	

}
