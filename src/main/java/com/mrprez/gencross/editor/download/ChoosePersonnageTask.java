package com.mrprez.gencross.editor.download;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.ws.api.bo.PersonnageLabel;

public class ChoosePersonnageTask implements EdtTask {
	private PersonnageLabel[] personnageLabels;
	
	

	public ChoosePersonnageTask(PersonnageLabel[] personnageMap) {
		super();
		this.personnageLabels = personnageMap;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JOptionPane.showInputDialog(GencrossEditor.getInstance(), "Choisissez un personnage", "Choisissez un personnage", JOptionPane.QUESTION_MESSAGE, null, personnageLabels, null);
	}

}
