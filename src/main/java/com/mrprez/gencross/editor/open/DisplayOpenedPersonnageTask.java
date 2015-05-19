package com.mrprez.gencross.editor.open;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayOpenedPersonnageTask implements EdtTask {
	private String personnageXml;
	
	
	
	public DisplayOpenedPersonnageTask(String personnageXml) {
		super();
		this.personnageXml = personnageXml;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		GencrossEditor.getInstance().setPersonnage(personnageXml);
	}

}
