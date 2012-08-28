package com.mrprez.gencross.edit;

import java.io.File;

import javax.swing.JFileChooser;

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
			PersonnageSaver.savePersonnageGcr(GenCrossEditor.getInstance().getPersonnage(), file);
		}
		
	}

	@Override
	public Work getNextWork() {
		return null;
	}

}
