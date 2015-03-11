package com.mrprez.gencross.editor.task;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class DownloadPersonnageTask implements BackgroundTask {
	private Task nextTask;

	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public void doInBackground() throws Exception {
		if(GencrossEditor.getInstance().getToken()==null){
			DisplayLoginTask displayLoginTask = new DisplayLoginTask();
			displayLoginTask.setNextTask(this);
			nextTask = displayLoginTask;
		}else{
			nextTask = null;
		}
	}

	
}
