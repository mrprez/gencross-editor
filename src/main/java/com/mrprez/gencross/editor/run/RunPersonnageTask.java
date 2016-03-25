package com.mrprez.gencross.editor.run;

import org.dom4j.DocumentHelper;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.disk.PersonnageFactory;
import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class RunPersonnageTask implements BackgroundTask {
	private Personnage personnage;

	@Override
	public Task getNextTask() {
		return new GencrossUiTask(personnage);
	}

	@Override
	public void doInBackground() throws Exception {
		PersonnageFactory personnageFactory = new PersonnageFactory();
		personnage = personnageFactory.loadPersonnage(DocumentHelper.parseText(GencrossEditor.getInstance().getPersonnage()));
	}
	
	

}
