package com.mrprez.gencross.editor.download;

import java.util.Map;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;

public class ChoosePersonnageTask implements EdtTask {
	private Map<Integer,String> personnageMap;
	
	

	public ChoosePersonnageTask(Map<Integer,String> personnageMap) {
		super();
		this.personnageMap = personnageMap;
	}

	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		String[] personnageTap = new String[personnageMap.size()];
		
		int index = 0;
		for(int id : personnageMap.keySet()){
			personnageTap[index] = id+" - "+personnageMap.get(id);
			index++;
		}
		
		JOptionPane.showInputDialog(GencrossEditor.getInstance(), "Choisissez un personnage", "Choisissez un personnage", JOptionPane.QUESTION_MESSAGE, null, personnageTap, null);
	}

}
