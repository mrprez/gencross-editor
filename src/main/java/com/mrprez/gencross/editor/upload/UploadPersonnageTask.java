package com.mrprez.gencross.editor.upload;

import javax.swing.JOptionPane;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.Treatment;
import com.mrprez.gencross.editor.framework.TreatmentAwareTask;
import com.mrprez.gencross.editor.framework.task.ShowMessageTask;
import com.mrprez.gencross.editor.login.DisplayLoginTask;
import com.mrprez.gencross.editor.service.PersonnageServiceAccess;

public class UploadPersonnageTask implements BackgroundTask, TreatmentAwareTask {
	private ShowMessageTask nextTask;
	private Treatment treatment;
	

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInBackground() throws Exception {
		if(GencrossEditor.getInstance().getToken()==null){
			treatment.lauchChildTreatment(new DisplayLoginTask());
		}
		
		new PersonnageServiceAccess().savePersonnage(GencrossEditor.getInstance().getPersonnageId(), GencrossEditor.getInstance().getPersonnage());

		nextTask = new ShowMessageTask(GencrossEditor.getInstance(), "Personnage sauvegardé sur le serveur.");
		nextTask.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		nextTask.setNextTask(new SetTextModifiedTask(false));
	}

	@Override
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	

}
