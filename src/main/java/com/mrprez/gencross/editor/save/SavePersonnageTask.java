package com.mrprez.gencross.editor.save;

import java.io.File;

import com.mrprez.gencross.disk.PersonnageSaver;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class SavePersonnageTask implements BackgroundTask {
	private File file; 
	
	
	public SavePersonnageTask(File file) {
		super();
		this.file = file;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInBackground() throws Exception {
		PersonnageSaver.savePersonnage(personnage, file);
	}

}
