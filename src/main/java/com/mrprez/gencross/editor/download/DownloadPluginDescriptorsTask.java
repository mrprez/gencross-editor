package com.mrprez.gencross.editor.download;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.Treatment;
import com.mrprez.gencross.editor.framework.TreatmentAwareTask;
import com.mrprez.gencross.editor.login.DisplayLoginTask;
import com.mrprez.gencross.editor.service.PersonnageService;

public class DownloadPluginDescriptorsTask implements BackgroundTask, TreatmentAwareTask {
	private Task nextTask;
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
		if(GencrossEditor.getInstance().getToken()!=null){
			PersonnageService personnageService = new PersonnageService();
			
			nextTask = new ChoosePluginTask(personnageService.getPluginList());
		}
	}
	

	@Override
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	
}
