package com.mrprez.gencross.editor.task;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;
import com.mrprez.gencross.editor.framework.Treatment;
import com.mrprez.gencross.editor.framework.TreatmentAwareTask;

public class DownloadPersonnageTask implements BackgroundTask, TreatmentAwareTask {
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
		}else{
			nextTask = null;
		}
	}

	@Override
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	
}
