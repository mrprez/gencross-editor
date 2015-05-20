package com.mrprez.gencross.editor.save;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.EdtTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.task.ShowConfirmTask;
import com.mrprez.gencross.utils.GencrossFileFilter;

public class ChooseFileToSaveTask implements EdtTask {
	private File selectedFile;

	@Override
	public Task getNextTask() {
		if(selectedFile!=null){
			if(selectedFile.exists()){
				ShowConfirmTask overrideConfirmTask = new ShowConfirmTask(GencrossEditor.getInstance(), "Ce fichier existe déjà. Ecraser?");
				overrideConfirmTask.setTitle("Ecraser?");
				overrideConfirmTask.setMessageType(JOptionPane.WARNING_MESSAGE);
				overrideConfirmTask.setOptionType(JOptionPane.YES_NO_OPTION);
				overrideConfirmTask.setNextTask(JOptionPane.YES_OPTION, new SavePersonnageTask(selectedFile));
				return overrideConfirmTask;
			}else{
				return new SavePersonnageTask(selectedFile);
			}
		}
		return null;
	}

	@Override
	public void doInEdt() throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new GencrossFileFilter());
		fileChooser.setMultiSelectionEnabled(false);
		
		int chooseResult = fileChooser.showSaveDialog(GencrossEditor.getInstance());
		
		if(chooseResult==JFileChooser.APPROVE_OPTION){
			selectedFile = fileChooser.getSelectedFile();
			String extension = "";
			if(selectedFile.getName().contains(".")){
				extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
			}
			if( ! extension.equalsIgnoreCase(".gcr") && ! extension.equalsIgnoreCase(".xml")){
				selectedFile = new File(selectedFile.getAbsolutePath()+".gcr");
			}
		}
	}

}
