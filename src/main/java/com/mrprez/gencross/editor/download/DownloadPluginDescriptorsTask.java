package com.mrprez.gencross.editor.download;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.Treatment;
import com.mrprez.gencross.editor.framework.TreatmentAwareTask;
import com.mrprez.gencross.editor.framework.task.ShowConfirmTask;
import com.mrprez.gencross.editor.login.DisplayLoginTask;
import com.mrprez.gencross.editor.service.PersonnageServiceAccess;

public class DownloadPluginDescriptorsTask implements BackgroundTask, TreatmentAwareTask {
	private Task nextTask;
	private Treatment treatment;

	
	
	@Override
	public Task getNextTask() {
		return nextTask;
	}

	
	@Override
	public void doInBackground() throws Exception {
		if(GencrossEditor.getInstance().isTextModified()){
			ShowConfirmTask showConfirmTask = new ShowConfirmTask(GencrossEditor.getInstance(), "Le personne actuel n'a pas été sauvegardé, vouez-vous continuez (les modifications du personnage actuel seront perdues)");
			showConfirmTask.setOptionType(JOptionPane.YES_NO_OPTION);
			showConfirmTask.setMessageType(JOptionPane.WARNING_MESSAGE);
			treatment.lauchChildTreatment(showConfirmTask);
			if(showConfirmTask.getResult() != JOptionPane.YES_OPTION){
				return;
			}
		}
		
		if(GencrossEditor.getInstance().getToken()==null){
			treatment.lauchChildTreatment(new DisplayLoginTask());
		}
		
		if(GencrossEditor.getInstance().getToken()!=null){
			PersonnageServiceAccess personnageService = new PersonnageServiceAccess();
			nextTask = new ChoosePluginTask(personnageService.getPluginList());
		}
	}
	

	@Override
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	
}
