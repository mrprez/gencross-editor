package com.mrprez.gencross.edit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.swing.JFileChooser;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.edit.framework.BackgroundWork;
import com.mrprez.gencross.edit.framework.Work;

public class SaveAsWork implements BackgroundWork {
	
	
	@Override
	public void doInBackground() throws Exception {
		JFileChooser fileChooser = GenCrossEditor.getInstance().getFileChooser();
		int returnCode = fileChooser.showSaveDialog(GenCrossEditor.getInstance());
		if(returnCode == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if(!file.getName().endsWith(".gcr")){
	    		file = new File(file.getParentFile(), file.getName()+".gcr");
	    	}
			InputStream is = new ByteArrayInputStream(GenCrossEditor.getInstance().getXml().getBytes("UTF-8")); 
			Personnage personnage = GenCrossEditor.getInstance().getPersonnageFactory().loadPersonnage(is);
			PersonnageSaver.savePersonnageGcr(personnage, file);
		}
		
	}

	@Override
	public Work getNextWork() {
		return null;
	}

}
