package com.mrprez.gencross.editor.open;

import java.io.File;

import javax.swing.JFileChooser;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.utils.GencrossFileFilter;

public class ChooseFileToOpenTask implements EdtTask {
	private File selectedFile;

	@Override
	public Task getNextTask() {
		if(selectedFile!=null){
			return new OpenFileTask(selectedFile);
		}
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new GencrossFileFilter());
		fileChooser.setMultiSelectionEnabled(false);
		int chooseResult = fileChooser.showOpenDialog(GencrossEditor.getInstance());
		if(chooseResult==JFileChooser.APPROVE_OPTION){
			selectedFile = fileChooser.getSelectedFile();
		}
	}
	
	
	

}
