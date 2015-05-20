package com.mrprez.gencross.editor.download;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.commons.SetTextModifiedTask;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class DisplayPersonnageTask implements EdtTask {
	private String personnageXml;
	
	
	public DisplayPersonnageTask(String personnageXml) {
		super();
		this.personnageXml = personnageXml;
	}

	@Override
	public Task getNextTask() {
		return new SetTextModifiedTask(false);
	}

	@Override
	public void doInEdt() throws Exception {
		GencrossEditor.getInstance().setPersonnage(personnageXml);
	}

}
