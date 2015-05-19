package com.mrprez.gencross.editor.param;

import com.mrprez.gencross.editor.GencrossEditor;
import com.mrprez.gencross.editor.framework.BackgroundTask;
import com.mrprez.gencross.editor.framework.Task;

public class BackgroundParamValidationTask implements BackgroundTask {
	private DisplayParamTask displayParamTask;
	
	
	public BackgroundParamValidationTask(DisplayParamTask displayParamTask) {
		super();
		this.displayParamTask = displayParamTask;
	}
	
	
	@Override
	public Task getNextTask() {
		return null;
	}

	@Override
	public void doInBackground() throws Exception {
		GencrossEditor.getInstance().setGencrossWebUrl(displayParamTask.getWsUrl());
	}

}
